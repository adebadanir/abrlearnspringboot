package com.juaracoding.abrlearnspringboot.controller;

import com.juaracoding.abrlearnspringboot.dto.validation.RegisDTO;
import com.juaracoding.abrlearnspringboot.dto.validation.VerifyDTO;
import com.juaracoding.abrlearnspringboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody RegisDTO regisDTO) {
        return authService.registration(authService.mapToUser(regisDTO));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Object> verifyOtp(@RequestBody VerifyDTO verifyDTO) {
        return authService.verifyOtp(verifyDTO);
    }
}
