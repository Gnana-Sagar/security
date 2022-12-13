package com.test.security.security.config;

import com.test.security.security.domain.UserDomain;
import com.test.security.security.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserDetailsCustomImpl implements UserDetailsService {

    private final DBService dbService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDomain domain = dbService.getByPhoneNumber(username);
        if(domain == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                domain.getPhoneNumber(),
                domain.getOtp(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

}
