package com.example.greenshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    @CreatedDate
    private LocalDateTime createdDateTime = LocalDateTime.now();
    @OneToOne
    private Address addressId;
    @Column(nullable = false)
    private String orderNote;

}