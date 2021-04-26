package com.unibuc.examenAWJ.controller;

import com.unibuc.examenAWJ.dto.AuthRequest;
import com.unibuc.examenAWJ.dto.AuthResponse;
import com.unibuc.examenAWJ.service.VulnerableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class VulnerableController {

    @Autowired
    VulnerableService vulnerableService;

    @PostMapping
    public ResponseEntity<AuthResponse> createDriver(
            @RequestBody
            @Valid AuthRequest authRequest) {

        AuthResponse authResponse = vulnerableService.authorize(authRequest.getUsername(), authRequest.getPassword());

        return ResponseEntity
                .accepted()
                .body(authResponse);
    }


}
