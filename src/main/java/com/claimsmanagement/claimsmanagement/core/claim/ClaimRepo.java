package com.claimsmanagement.claimsmanagement.core.claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepo extends JpaRepository<Claim,Long> {
}
