package com.claimsmanagement.claimsmanagement.core.member.converter;

import com.claimsmanagement.claimsmanagement.core.member.Member;
import com.claimsmanagement.claimsmanagement.core.member.web.MemberView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class MemberToMemberViewConverter implements Converter<Member,MemberView> {

    @Override
    public MemberView convert(@NotNull Member member) {
        MemberView memberView=new MemberView();
        memberView.setMemberId(member.getMemberId());
        memberView.setMemberName(member.getMemberName());
        memberView.setEmail(member.getEmail());
        memberView.setPhoneNo(member.getPhoneNo());
        memberView.setAddress(member.getAddress());
        return memberView;
    }
}
