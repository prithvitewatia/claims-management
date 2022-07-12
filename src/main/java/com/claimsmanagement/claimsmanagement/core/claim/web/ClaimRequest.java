package com.claimsmanagement.claimsmanagement.core.claim.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@ToString
public class ClaimRequest{
    // member related attributes
    @NotNull
    private Long memberId;
    // policy related attributes
    @NotNull
    private Long policyId;
    @NotBlank
    private String policyName;
    @NotBlank
    private String policyProvider;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate policyStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate policyEndDate;
    private String policyDescription;
    // claim related attributes
    @NotBlank
    private String claimDescription;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate claimRaisedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate claimSettledDate;
    @NotNull
    private Integer claimAmount;
    @NotBlank
    private String claimStatus;
    private String remarks;
}
