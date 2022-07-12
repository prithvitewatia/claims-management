package com.claimsmanagement.claimsmanagement.core.policy.web;

import com.claimsmanagement.claimsmanagement.core.policy.Policy;
import com.claimsmanagement.claimsmanagement.core.policy.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @RequestMapping("/{id}")
    public PolicyView getPolicyById(@PathVariable("id") Long id) {
        return policyService.getPolicyById(id);
    }

    @GetMapping
    public Page<PolicyView> getAllMembers(@PageableDefault(sort = "policyId",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return policyService.getAllPolicy(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PolicyView create(@RequestBody @Valid PolicyRequest policyRequest) {
        return policyService.create(policyRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePolicy(@PathVariable("id") Long id) {
        policyService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PolicyView updateMember(@PathVariable("id") Long id
            , @RequestBody @Valid PolicyRequest policyRequest) {
        Policy policy = policyService.findPolicyOrThrow(id);
        return policyService.update(policy, policyRequest);
    }
}
