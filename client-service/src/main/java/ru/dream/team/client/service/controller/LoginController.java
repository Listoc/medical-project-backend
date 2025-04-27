package ru.dream.team.client.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.dream.team.client.service.model.UserCreds;
import ru.dream.team.client.service.service.JwtService;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "Получить jwt-токен", responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Верные креды УЗ",
            content = @Content(examples = @ExampleObject(value = "ROLE_PATIENT"))),
        @ApiResponse(
            responseCode = "400",
            description = "Неверные креды УЗ",
            content = @Content(examples = @ExampleObject(value = "Wrong credentials")))
    })
    public ResponseEntity<String> getToken(@RequestBody UserCreds userCreds) {
        var creds = new UsernamePasswordAuthenticationToken(userCreds.getUsername(), userCreds.getPassword());
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(creds);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.badRequest().body("Wrong credentials");
        }

        String jwts = jwtService.getToken(auth.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .body(auth.getAuthorities().stream().findFirst().orElseThrow().getAuthority());
    }
}
