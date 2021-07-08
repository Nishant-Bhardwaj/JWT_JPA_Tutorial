package com.nishant.jpa.complete.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity(name = "AuthRequest")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthRequest {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "upassword")
    private String password;

}
