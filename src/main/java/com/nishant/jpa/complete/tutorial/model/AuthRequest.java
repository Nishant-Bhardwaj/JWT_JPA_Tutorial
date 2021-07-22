package com.nishant.jpa.complete.tutorial.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(
        name = "AuthRequest",
        uniqueConstraints = @UniqueConstraint(
                name = "username_unique",
                columnNames = "username"
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthRequest {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "upassword")
    private String password;

    @Embedded
    private AuthResponse authResponse;

    @OneToOne(
            mappedBy = "authRequestUsername",
            fetch = FetchType.LAZY
    )
    private UserRecord userRecord;
}
