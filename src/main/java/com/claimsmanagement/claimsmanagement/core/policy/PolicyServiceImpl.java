package com.claimsmanagement.claimsmanagement.core.policy;

import com.claimsmanagement.claimsmanagement.core.policy.converter.PolicyToPolicyViewConverter;
import com.claimsmanagement.claimsmanagement.core.policy.web.PolicyRequest;
import com.claimsmanagement.claimsmanagement.core.policy.web.PolicyView;
import com.claimsmanagement.claimsmanagement.error.EntityNotFoundException;
import com.claimsmanagement.claimsmanagement.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService{
    @Autowired
    private PolicyRepo policyRepo;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private PolicyToPolicyViewConverter policyViewConverter;
    public PolicyView getPolicyById(Long id) throws EntityNotFoundException {
        Policy policy=findPolicyOrThrow(id);
        return policyViewConverter.convert(policy);
    }

    public Policy findPolicyOrThrow(Long id) throws EntityNotFoundException{
        return policyRepo.findById(id).orElseThrow(
                ()->new EntityNotFoundException(messageUtil.getMessage("policy.NotFound",id))
        );
    }

    public Page<PolicyView> getAllPolicy(Pageable pageable){
        Page<Policy> policies=policyRepo.findAll(pageable);
        List<PolicyView> policyViews=new ArrayList<>();
        policies.forEach(policy -> {
            PolicyView policyView=policyViewConverter.convert(policy);
            policyViews.add(policyView);
        });
        return new PageImpl<>(policyViews,pageable,policies.getTotalElements());
    }

    public PolicyView create(PolicyRequest policyBaseRequest){
        Policy policy=new Policy();
        this.prepare(policy,policyBaseRequest);
        Policy policySave=policyRepo.save(policy);
        return policyViewConverter.convert(policySave);
    }

    private void prepare(Policy policy, PolicyRequest policyRequest) {
        policy.setPolicyName(policyRequest.getPolicyName());
        policy.setPolicyProvider(policyRequest.getPolicyProvider());
        policy.setPolicyStartDate(policyRequest.getPolicyStartDate());
        policy.setPolicyEndDate(policyRequest.getPolicyEndDate());
        policy.setPolicyStatus(policyRequest.getPolicyStatus());
        policy.setDescription(policyRequest.getDescription());
    }

    @Transactional
    public void delete(Long id){
        try{
            policyRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("policy.NotFound",id));
        }
    }
    public PolicyView update(Policy policy, PolicyRequest policyRequest){
        this.prepare(policy,policyRequest);
        Policy savePolicy=policyRepo.save(policy);
        return policyViewConverter.convert(savePolicy);
    }
}
