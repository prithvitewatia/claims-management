package com.claimsmanagement.claimsmanagement.core.policy;

import com.claimsmanagement.claimsmanagement.core.claim.Claim;
import com.claimsmanagement.claimsmanagement.core.member.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Policy {
    @Id
    @Column
    private Long policyId;
    @Column(nullable = false)
    private String policyName;
    @Column(nullable = false)
    private String policyProvider;
    @Column(nullable = false)
    private LocalDate policyStartDate;
    @Column(nullable = false)
    private LocalDate policyEndDate;
    @Column(nullable = false)
    private String policyStatus;
    @Column
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @JsonManagedReference
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Claim> claims=new HashSet<>();
}
