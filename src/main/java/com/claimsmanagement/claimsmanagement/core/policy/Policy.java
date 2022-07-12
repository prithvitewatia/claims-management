package com.claimsmanagement.claimsmanagement.core.policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

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
    @Column
    private Long memberId;
}
