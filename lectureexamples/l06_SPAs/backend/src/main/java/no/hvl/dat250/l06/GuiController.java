package no.hvl.dat250.l06;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class GuiController {

    private final LocationRepository repository;

    public GuiController(@Autowired LocationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/gui/locations")
    public String newHandler(Model model) {
        model.addAttribute("locations", new ArrayList<>(this.repository.getAllLocations()));

        return "Hello";
    }

    @GetMapping("/gui/test")
    public ResponseEntity<String> showIndex() {
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body("""
                <html>
                <head>
                <title>GUI</title>
                </head>
                <body>
                <h1>Hello World</h1>
                </body>
                </html>
                """);
    }

    @PostMapping("/gui/create")
    public ResponseEntity<String> createLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = new String(request.getInputStream().readNBytes(request.getContentLength()), StandardCharsets.UTF_8);

        System.out.println(requestBody);
        return ResponseEntity.ok("success!");

    }



}
