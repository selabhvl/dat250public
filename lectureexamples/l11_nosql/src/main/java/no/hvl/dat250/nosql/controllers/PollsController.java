package no.hvl.dat250.nosql.controllers;

import no.hvl.dat250.nosql.entities.Poll;
import no.hvl.dat250.nosql.repositories.PollsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/polls")
@RestController
public class PollsController {

    @Autowired
    private PollsRepo repo;

    @GetMapping()
    public Iterable<Poll> allPolls() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Poll> pollById( @PathVariable Long id) {
        return repo.findById(id);
    }
}
