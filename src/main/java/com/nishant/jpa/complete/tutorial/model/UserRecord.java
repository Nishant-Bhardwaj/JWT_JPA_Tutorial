package com.nishant.jpa.complete.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "UserRecord")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRecord {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    private String userEmail;

    private Long mobile;

    @Embedded
    private Department userDepartment;

    //One to one bi-directional

    @OneToOne(
            mappedBy = "userRecord"
    )
    private ComplaintRecord complaintRecord;

}
