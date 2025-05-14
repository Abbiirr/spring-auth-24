package org.example.springauth24;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Optional;

@RestController
public class WebController {
    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot! 👋";
    }

    @GetMapping("/private")
    public String privatePage(Authentication authentication) {
        return "Welcome to the VIP Lounge " +
                getName(authentication) +
                " 👋";
    }

    private static String getName(Authentication authentication) {
        return Optional.of(authentication.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getGivenName)
                .orElse(authentication.getName());
    }
}
