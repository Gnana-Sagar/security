package com.test.security.security.controller;

import com.test.security.security.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final DBService dbService;

    @GetMapping("/")
    public String healthCheck() {
        return "Welcome";
    }

    @GetMapping("/generate/{phoneNumber}")
    public String generateOTP(@PathVariable(name = "phoneNumber") String phoneNUmber) {
        return dbService.generateOTP(phoneNUmber);
    }

    @GetMapping("/validate/{phone}/{otp}")
    public String OtpValidate(@PathVariable(name="phone") String phone, @PathVariable(name = "otp") String otp) {
        return dbService.validateOtp(phone, otp);
    }

}
