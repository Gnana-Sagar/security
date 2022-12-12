package com.test.security.security.service;

import com.test.security.security.config.JwtUtil;
import com.test.security.security.domain.UserDomain;
import com.test.security.security.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepo userRepo;

    private final JwtUtil jwtUtil;

    public String generateOTP(String phoneNumber) {
        UserDomain userDomain = new UserDomain();
        int randomPin = (int) (Math.random() * 9000) + 1000;
        userDomain.setPhoneNumber(phoneNumber);
        userDomain.setOtp(String.valueOf(randomPin));
        userDomain.setUpdatedTime(new Date());
        UserDomain response = userRepo.save(userDomain);
        return response.getOtp();
    }

    public String validateOtp(String phoneNumber, String otp) {
        UserDomain response = userRepo.findByPhoneNumber(phoneNumber);
        Date currentDate = new Date();
        boolean timeout = (currentDate.getTime() - response.getUpdatedTime().getTime()) < 60000;
        if (response.getOtp().equalsIgnoreCase(otp) && timeout) {
            return jwtUtil.generateToken(response);
        }
        return null;
    }

    public UserDomain getByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber);
    }

}
