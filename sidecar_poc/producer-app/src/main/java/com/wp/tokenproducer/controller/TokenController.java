package com.wp.tokenproducer.controller;

import com.wp.tokenproducer.config.TokenWatchConfiguration;
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
