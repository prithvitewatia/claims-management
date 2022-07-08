package com.claimsmanagement.claimsmanagement.core.claim.converter;

import com.claimsmanagement.claimsmanagement.core.claim.Claim;
import com.claimsmanagement.claimsmanagement.core.claim.web.ClaimView;
import com.claimsmanagement.claimsmanagement.core.policy.converter.PolicyToPolicyViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ClaimToClaimViewConverter implements Converter<Claim, ClaimView> {
    @Autowired
    private PolicyToPolicyViewConverter policyViewConverter;
    @Override
    public ClaimView convert(@NotNull Claim claim) {
        ClaimView claimView=new ClaimView();
        claimView.setClaimId(claim.getClaimId());
        claimView.setDescription(claim.getDescription());
        claimView.setClaimRaisedDate(claim.getClaimRaisedDate());
        claimView.setClaimSettledDate(claim.getClaimSettledDate());
        claimView.setClaimAmount(claim.getClaimAmount());
        claimView.setRemarks(claim.getRemarks());
        claimView.setClaimStatus(claim.getClaimStatus());
        return claimView;
    }
}
