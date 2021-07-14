package com.nishant.jpa.complete.tutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ComplaintRecord")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintRecord {

    @Id
    @SequenceGenerator(
            name = "complaint_sequence",
            sequenceName = "complaint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "complaint_sequence"
    )
    private Long complaintId;

    private String complaintAssignedTo;
    private String status;
    private String complaintSubject;

    @Column(
            name = "complaint_body",
            nullable = false
    )
    private String complaintBody;

    @Embedded
    private Department complaintDepartment;

    @ManyToOne(
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties({"authRequestUsername","complaintRecord"})
    private UserRecord userRecord;
}
