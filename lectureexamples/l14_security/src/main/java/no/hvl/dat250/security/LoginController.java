package no.hvl.dat250.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String regiser() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        User u = new User(username, email, password);
        userRepository.save(u);
        return "redirect:/login?created=" + username;
    }
}
