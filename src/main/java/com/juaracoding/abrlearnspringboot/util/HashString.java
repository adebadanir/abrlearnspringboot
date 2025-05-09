package com.juaracoding.abrlearnspringboot.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashString {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hashString(String plainString) {
        return passwordEncoder.encode(plainString);
    }

    public boolean checkHashedString(String plainString, String hashedString) {
        return passwordEncoder.matches(plainString, hashedString);
    }
}
