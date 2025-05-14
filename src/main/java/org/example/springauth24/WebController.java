package org.example.springauth24;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot! 👋";
    }

    @GetMapping("/private")
    public String privatePage() {
        return "This is a private page 🔐";
    }
}
