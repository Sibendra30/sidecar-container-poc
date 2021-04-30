package com.wp.consumer.controller;

import com.wp.consumer.config.TokenWatchConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private TokenWatchConfiguration tokenWatchConfiguration;
    public TokenController(TokenWatchConfiguration tokenWatchConfiguration) {
        this.tokenWatchConfiguration = tokenWatchConfiguration;
    }

    @GetMapping("/token")
    public String getToken() {
        return "token value is : " + tokenWatchConfiguration.getAuthToken();
    }
}
