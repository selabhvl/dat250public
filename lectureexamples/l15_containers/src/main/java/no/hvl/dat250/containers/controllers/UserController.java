package no.hvl.dat250.containers.controllers;

import no.hvl.dat250.containers.dtos.ErrorMsg;
import no.hvl.dat250.containers.dtos.NewUserRequest;
import no.hvl.dat250.containers.entities.User;
import no.hvl.dat250.containers.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo repo;


    @GetMapping
    public List<Long> getUserIds() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).map(User::getId).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return repo.findById(id);
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRequest user) {
        if (repo.findByEmail(user.email()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with email '" + user.email() + "' exists already"));
        }
        if (repo.findByUsername(user.username()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with username '" + user.username() + "' exists already"));
        }
        User u = new User(user.username(), user.email());
        u = repo.save(u);
        return ResponseEntity.created(URI.create("/users/" + u.getId())).body(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (repo.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorMsg("User with '" + id + "' does not exist"));
        }
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }








}
