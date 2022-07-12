package com.claimsmanagement.claimsmanagement.core.claim.converter;

import com.claimsmanagement.claimsmanagement.core.claim.Claim;
import com.claimsmanagement.claimsmanagement.core.claim.web.ClaimView;
import com.claimsmanagement.claimsmanagement.core.policy.PolicyRepo;
import com.claimsmanagement.claimsmanagement.core.policy.converter.PolicyToPolicyViewConverter;
import com.claimsmanagement.claimsmanagement.error.EntityNotFoundException;
import com.claimsmanagement.claimsmanagement.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ClaimToClaimViewConverter implements Converter<Claim, ClaimView> {
    @Autowired
    private PolicyToPolicyViewConverter policyViewConverter;
    @Autowired
    private PolicyRepo policyRepo;
    @Autowired
    private MessageUtil messageUtil;
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
        claimView.setPolicy(policyViewConverter.convert(policyRepo.findById(claim.getPolicyId()).orElseThrow(
                ()->new EntityNotFoundException(messageUtil.getMessage("policy.NotFound",claim.getPolicyId()))
        )));
        claimView.setMemberId(claim.getMemberId());
        return claimView;
    }
}
