package com.test.security.security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, name = "phone_number")
    private String phoneNumber;
    private String otp;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

}
