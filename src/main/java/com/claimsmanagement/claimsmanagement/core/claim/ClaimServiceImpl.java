package com.claimsmanagement.claimsmanagement.core.claim;

import com.claimsmanagement.claimsmanagement.core.claim.converter.ClaimToClaimViewConverter;
import com.claimsmanagement.claimsmanagement.core.claim.web.ClaimRequest;
import com.claimsmanagement.claimsmanagement.core.claim.web.ClaimView;
import com.claimsmanagement.claimsmanagement.core.policy.Policy;
import com.claimsmanagement.claimsmanagement.core.policy.PolicyRepo;
import com.claimsmanagement.claimsmanagement.error.EntityNotFoundException;
import com.claimsmanagement.claimsmanagement.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Autowired
    private ClaimRepo claimRepo;
    @Autowired
    private ClaimToClaimViewConverter claimViewConverter;
    @Autowired
    private PolicyRepo policyRepo;
    @Autowired
    private MessageUtil messageUtil;

    public ClaimView getClaimById(Long id) throws EntityNotFoundException {
        Claim claim = findClaimOrThrow(id);
        return claimViewConverter.convert(claim);
    }

    public Claim findClaimOrThrow(Long id) throws EntityNotFoundException {
        return claimRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageUtil.getMessage("claim.NotFound", id))
        );
    }

    public Page<ClaimView> getAllClaims(Pageable pageable) {
        Page<Claim> claims = claimRepo.findAll(pageable);
        List<ClaimView> claimViews = new ArrayList<>();
        claims.forEach(claim -> {
            ClaimView memberView = claimViewConverter.convert(claim);
            claimViews.add(memberView);
        });
        return new PageImpl<>(claimViews, pageable, claims.getTotalElements());
    }

    public ClaimView create(ClaimRequest claimRequest) {
        Claim claim = new Claim();
        this.prepare(claim, claimRequest);
        Claim saveClaim = claimRepo.save(claim);
        return claimViewConverter.convert(saveClaim);
    }

    private void prepare(Claim claim, ClaimRequest claimRequest) throws EntityNotFoundException {
        // setting policy details
        Policy policy = new Policy();
        policy.setPolicyId(claimRequest.getPolicyId());
        policy.setPolicyName(claimRequest.getPolicyName());
        policy.setPolicyProvider(claimRequest.getPolicyProvider());
        policy.setPolicyStartDate(claimRequest.getPolicyStartDate());
        policy.setPolicyEndDate(claimRequest.getPolicyEndDate());
        if (LocalDate.now().compareTo(claimRequest.getPolicyEndDate()) > 0) {
            policy.setPolicyStatus("Expired");
        } else {
            policy.setPolicyStatus("Not Expired");
        }
        policy.setDescription(claimRequest.getPolicyDescription());
        policy.setMemberId(claimRequest.getMemberId());
        policyRepo.save(policy);
        // setting the claim
        claim.setDescription(claimRequest.getClaimDescription());
        claim.setClaimRaisedDate(claimRequest.getClaimRaisedDate());
        claim.setClaimSettledDate(claimRequest.getClaimSettledDate());
        claim.setClaimAmount(claimRequest.getClaimAmount());
        claim.setClaimStatus(claimRequest.getClaimStatus());
        claim.setRemarks(claimRequest.getRemarks());
        claim.setMemberId(claimRequest.getMemberId());
        claim.setPolicyId(claimRequest.getPolicyId());
    }

    @Transactional
    public void delete(Long id) throws EntityNotFoundException {
        try {
            claimRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("claim.NotFound", id));
        }
    }

    public ClaimView update(Claim claim, ClaimRequest claimRequest) {
        this.prepare(claim, claimRequest);
        Claim updateClaim = claimRepo.save(claim);
        return claimViewConverter.convert(updateClaim);
    }

    @Override
    public List<ClaimView> getAllClaimsByMember(Long memberId) {
        return claimRepo.findClaimByMemberId(memberId).stream()
                .map(claim -> claimViewConverter.convert(claim))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimView> getAllPendingClaims(){
        return claimRepo.findClaimByClaimStatusIs("Pending")
                .stream()
                .map(claim -> claimViewConverter.convert(claim))
                .collect(Collectors.toList());
    }
}
