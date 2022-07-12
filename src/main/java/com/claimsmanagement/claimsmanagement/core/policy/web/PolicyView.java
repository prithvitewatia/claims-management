package com.claimsmanagement.claimsmanagement.core.policy.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PolicyView {
    private Long policyId;
    private String policyName;
    private String policyProvider;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private String description;
}
