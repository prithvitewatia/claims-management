package com.claimsmanagement.claimsmanagement.core.policy.converter;

import com.claimsmanagement.claimsmanagement.core.member.converter.MemberToMemberViewConverter;
import com.claimsmanagement.claimsmanagement.core.policy.Policy;
import com.claimsmanagement.claimsmanagement.core.policy.web.PolicyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class PolicyToPolicyViewConverter implements Converter<Policy, PolicyView>{
    @Autowired
    private MemberToMemberViewConverter memberViewConverter;
    @Override
    public PolicyView convert(@NotNull Policy policy) {
        PolicyView policyView=new PolicyView();
        policyView.setPolicyName(policy.getPolicyName());
        policyView.setPolicyProvider(policy.getPolicyProvider());
        policyView.setPolicyStatus(policy.getPolicyStatus());
        policyView.setPolicyStartDate(policy.getPolicyStartDate());
        policyView.setPolicyEndDate(policy.getPolicyEndDate());
        return policyView;
    }
}
