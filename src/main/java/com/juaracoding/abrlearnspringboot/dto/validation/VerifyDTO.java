package com.juaracoding.abrlearnspringboot.dto.validation;

import jakarta.validation.constraints.Pattern;

public class VerifyDTO {
    @Pattern(regexp = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)(?:(?![.])[a-zA-Z0-9._%+-]+(?:(?<!\\\\)[.][a-zA-Z0-9-]+)*?)@[a-zA-Z0-9.-]+(?:\\.[a-zA-Z]{2,50})+$",
            message = "Format tidak valid ex : user_name123@sub.domain.com")
    private String email;

    @Pattern(regexp = "^[0-9]{6}$", message = "Format OTP Tidak Valid, ex : 123456")
    private String otp;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
