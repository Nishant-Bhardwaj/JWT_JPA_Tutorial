package com.nishant.jpa.complete.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "username",
            referencedColumnName = "username",
            nullable = false
    )
    private AuthRequest authRequestUsername;

    private String userEmail;

    private Long mobile;

    @Embedded
    private Department userDepartment;

    //One to one bi-directional

    @OneToMany(
            mappedBy = "userRecord",
            fetch = FetchType.LAZY
    )
    private List<ComplaintRecord> complaintRecord;

}
