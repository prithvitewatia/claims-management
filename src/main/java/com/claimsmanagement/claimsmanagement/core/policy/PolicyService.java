package com.claimsmanagement.claimsmanagement.core.policy;

import com.claimsmanagement.claimsmanagement.core.policy.web.PolicyRequest;
import com.claimsmanagement.claimsmanagement.core.policy.web.PolicyView;
import com.claimsmanagement.claimsmanagement.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PolicyService {
    PolicyView getPolicyById(Long id) throws EntityNotFoundException;

    Policy findPolicyOrThrow(Long id) throws EntityNotFoundException;

    Page<PolicyView> getAllPolicy(Pageable pageable);

    PolicyView create(PolicyRequest policyRequest);

    void delete(Long id);

    PolicyView update(Policy policy, PolicyRequest policyRequest);
}
