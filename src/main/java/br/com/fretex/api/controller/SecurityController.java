package br.com.fretex.api.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.config.AuthUser;

@RestController
@RequestMapping("/user-auth")
public class SecurityController {

    @GetMapping
    public AuthUser user() {
        return (AuthUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}