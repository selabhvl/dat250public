package no.hvl.dat250.containers.controllers;

import no.hvl.dat250.containers.dtos.ErrorMsg;
import no.hvl.dat250.containers.dtos.NewPollRequest;
import no.hvl.dat250.containers.entities.Poll;
import no.hvl.dat250.containers.entities.User;
import no.hvl.dat250.containers.entities.Vote;
import no.hvl.dat250.containers.entities.VoteOption;
import no.hvl.dat250.containers.repositories.PollsRepo;
import no.hvl.dat250.containers.repositories.UserRepo;
import no.hvl.dat250.containers.repositories.VoteOptionRepo;
import no.hvl.dat250.containers.repositories.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/polls")
@RestController
public class PollsController {

    @Autowired
    private PollsRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VoteOptionRepo voteOptionRepo;

    @Autowired
    private VoteRepo voteRepo;

    @GetMapping()
    public Iterable<Poll> allPolls() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Poll> pollById( @PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> createPoll(@RequestBody NewPollRequest request) {
        Optional<User> maybeUser = userRepo.findById(request.createdBy());
        if (maybeUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with id '" + request.createdBy() + "' does not exist"));
        }
        User u = maybeUser.get();
        Poll p = u.createPoll(request.question());
        List<VoteOption> options = new ArrayList<>();
        for (String o : request.options()) {
            options.add(p.addOption(o));
        }
        p = repo.save(p);
        return ResponseEntity.created(URI.create("/polls/" + p.getId())).body(p);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        if (repo.findById(pollId).isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("Poll with id '" + pollId + "' does not exist"));
        }
        repo.deleteById(pollId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/votes")
    @Transactional
    public ResponseEntity<?> createPoll(@PathVariable Long id, @RequestParam Integer option, @RequestParam Long userId) {
        Optional<Poll> maybePoll = repo.findById(id);
        if (maybePoll.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("Poll with id '" + id + "' does not exist"));
        }
        Optional<User> maybeUser = userRepo.findById(userId);
        if (maybeUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with id '" + userId + "' does not exist"));
        }
        Poll poll = maybePoll.get();
        if (option < 0 || option >= poll.getVoteOptions().size()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("Poll with id  '" + id + "' does not have an option with index/order '" + option + "'!"));
        }
        VoteOption voteOption = poll.getVoteOptions().get(option);
        Vote vote = maybeUser.get().voteFor(voteOption);
        voteRepo.save(vote);
        return ResponseEntity.ok().build();
    }


}
