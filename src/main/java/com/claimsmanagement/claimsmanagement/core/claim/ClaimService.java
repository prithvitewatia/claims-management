package com.claimsmanagement.claimsmanagement.core.claim;

import com.claimsmanagement.claimsmanagement.core.claim.web.ClaimRequest;
import com.claimsmanagement.claimsmanagement.core.claim.web.ClaimView;
import com.claimsmanagement.claimsmanagement.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClaimService {
    ClaimView getClaimById(Long id) throws EntityNotFoundException;

    Claim findClaimOrThrow(Long id) throws EntityNotFoundException;

    Page<ClaimView> getAllClaims(Pageable pageable);

    ClaimView create(ClaimRequest claimRequest);

    void delete(Long id) throws EntityNotFoundException;

    ClaimView update(Claim claim, ClaimRequest claimRequest);

    List<ClaimView> getAllClaimsByMember(Long memberId);
    List<ClaimView> getAllPendingClaims();
}
