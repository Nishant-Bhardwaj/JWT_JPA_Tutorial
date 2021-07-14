package com.nishant.jpa.complete.tutorial.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "AuthRequest")
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

    @Embedded
    private AuthResponse authResponse;

    @OneToOne(
            mappedBy = "authRequestUsername"
    )
    private UserRecord userRecord;
}
