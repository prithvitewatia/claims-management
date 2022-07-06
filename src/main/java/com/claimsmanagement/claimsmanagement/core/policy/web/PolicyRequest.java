package com.claimsmanagement.claimsmanagement.core.policy.web;

import com.claimsmanagement.claimsmanagement.core.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PolicyRequest{
    private String policyName;
    private String policyProvider;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private String policyStatus;
    private String description;
    private Member member;
}
