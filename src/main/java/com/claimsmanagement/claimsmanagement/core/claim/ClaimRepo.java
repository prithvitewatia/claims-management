package com.claimsmanagement.claimsmanagement.core.claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepo extends JpaRepository<Claim, Long> {
    List<Claim> findClaimByMemberId(Long memberId);
    List<Claim> findClaimByClaimStatusIs(String claimStatus);
}
