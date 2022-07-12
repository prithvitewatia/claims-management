package com.claimsmanagement.claimsmanagement.core.policy.converter;

import com.claimsmanagement.claimsmanagement.core.policy.Policy;
import com.claimsmanagement.claimsmanagement.core.policy.web.PolicyView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class PolicyToPolicyViewConverter implements Converter<Policy, PolicyView>{
    @Override
    public PolicyView convert(@NotNull Policy policy) {
        PolicyView policyView=new PolicyView();
        policyView.setPolicyId(policy.getPolicyId());
        policyView.setPolicyName(policy.getPolicyName());
        policyView.setPolicyProvider(policy.getPolicyProvider());
        policyView.setPolicyStartDate(policy.getPolicyStartDate());
        policyView.setPolicyEndDate(policy.getPolicyEndDate());
        policyView.setDescription(policy.getDescription());
        return policyView;
    }
}
