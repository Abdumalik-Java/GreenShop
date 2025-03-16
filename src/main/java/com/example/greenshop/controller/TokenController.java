package com.example.greenshop.controller;

import com.example.greenshop.dto.JwtDto;
import com.example.greenshop.security.JWTUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping({"/generate"})
    public ResponseEntity<String> generateToken() {
        String jwt = JWTUtil.encode("ADMIN", "ADMIN");
        return ResponseEntity.ok(jwt);
    }


    @GetMapping({"/parse/{token}"})
    public HttpEntity<JwtDto> parseToken(@PathVariable("token") String token) {
        JwtDto jwtDto = JWTUtil.decode(token);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}