package com.claimsmanagement.claimsmanagement.core.claim;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Claim {
    @Id
    @Column
    @GenericGenerator(
            name = "claim_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "claim_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_id_seq")
    private Long claimId;
    @Column(length = 1000)
    private String description; // Submitted by User
    @Column(nullable = false)
    private LocalDate claimRaisedDate;
    private LocalDate claimSettledDate;
    @Column(nullable = false)
    private Integer claimAmount;
    @Column(nullable = false)
    private String claimStatus;
    @Column(length = 1000)
    private String remarks; // Submitted by Admin/Approving Person
    private Long policyId; // determining the policy
    private Long memberId; // determining which member does this claim belong to.
}
