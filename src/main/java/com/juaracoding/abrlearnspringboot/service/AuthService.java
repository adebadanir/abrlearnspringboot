package com.juaracoding.abrlearnspringboot.service;

import com.juaracoding.abrlearnspringboot.dto.validation.RegisDTO;
import com.juaracoding.abrlearnspringboot.dto.validation.VerifyDTO;
import com.juaracoding.abrlearnspringboot.model.EmailDetails;
import com.juaracoding.abrlearnspringboot.model.User;
import com.juaracoding.abrlearnspringboot.repo.UserRepo;
import com.juaracoding.abrlearnspringboot.util.HashString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class AuthService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepo userRepo;


    private Random random = new Random();

    private HashString hashString = new HashString();

    public ResponseEntity<Object> registration(User user){
        int otp = random.nextInt(100000,999999);
        String hashOtp = hashString.hashString(String.valueOf(otp));
        emailService.sendSimpleMail(new EmailDetails(user.getEmail(), "OTP: " + String.valueOf(otp), "OTP CODE FOR REGISTRATION"));
        user.setOtp(hashOtp);
        userRepo.save(user);
        Map<String, Object> response = new HashMap<>();
        response.put("otp", hashOtp);
        response.put("email", user.getEmail());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> verifyOtp(VerifyDTO verifyDTO){
        String email = verifyDTO.getEmail();
        String otp = verifyDTO.getOtp();
        Optional<User> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            System.out.println("User found: " + user.getUsername());
        } else {
            System.out.println("No user with this email.");
        }
        if(existingUser.isEmpty()){
            return ResponseEntity.badRequest().body("email not found");
        }
        if (!hashString.checkHashedString(otp, existingUser.get().getOtp())) {
            return ResponseEntity.badRequest().body("invalid otp");
        }
        existingUser.get().setRegistered(true);
        userRepo.save(existingUser.get());
        return ResponseEntity.ok("verifikasi berhasil");
    }



    public User mapToUser(RegisDTO regisDTO) {
        User user = new User();
        user.setEmail(regisDTO.getEmail());
        user.setNoHp(regisDTO.getNoHp());
        user.setAlamat(regisDTO.getAlamat());
        user.setNamaLengkap(regisDTO.getNamaLengkap());
        user.setPassword(regisDTO.getPassword());
        user.setTanggalLahir(regisDTO.getTanggalLahir());
        user.setUsername(regisDTO.getUsername());
        return user;
    }
}
