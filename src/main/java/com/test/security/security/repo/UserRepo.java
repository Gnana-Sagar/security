package com.test.security.security.repo;

import com.test.security.security.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDomain, Long> {

    UserDomain findByPhoneNumber(String phoneNumber);
}
