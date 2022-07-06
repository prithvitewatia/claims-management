package com.claimsmanagement.claimsmanagement.core.member;

import com.claimsmanagement.claimsmanagement.core.member.converter.MemberToMemberViewConverter;
import com.claimsmanagement.claimsmanagement.core.member.web.MemberRequest;
import com.claimsmanagement.claimsmanagement.core.member.web.MemberView;
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
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private MemberToMemberViewConverter memberViewConverter;
    @Autowired
    private MessageUtil messageUtil;
    public MemberView getMemberById(Long id) throws EntityNotFoundException {
        Member member=findMemberOrThrow(id);
        return memberViewConverter.convert(member);
    }

    public Member findMemberOrThrow(Long id) throws EntityNotFoundException{
        return memberRepo.findById(id).orElseThrow(
                ()->new EntityNotFoundException(messageUtil.getMessage("member.NotFound",id))
        );
    }

    public Page<MemberView> getAllMembers(Pageable pageable){
        Page<Member> members=memberRepo.findAll(pageable);
        List<MemberView> memberViews=new ArrayList<>();
        members.forEach(member -> {
            MemberView memberView=memberViewConverter.convert(member);
            memberViews.add(memberView);
        });
        return new PageImpl<>(memberViews,pageable,members.getTotalElements());
    }

    public MemberView create(MemberRequest memberRequest){
        Member member=new Member();
        this.prepare(member,memberRequest);
        Member memberSave=memberRepo.save(member);
        return memberViewConverter.convert(memberSave);
    }

    private void prepare(Member member, MemberRequest memberRequest) {
        member.setMemberName(memberRequest.getMemberName());
        member.setEmail(memberRequest.getEmail());
        member.setPassword(memberRequest.getPassword());
        member.setPhoneNo(memberRequest.getPhoneNo());
        member.setAddress(memberRequest.getAddress());
    }

    @Transactional
    public void delete(Long id){
        try{
            memberRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(messageUtil.getMessage("member.NotFound",id));
        }
    }
    public MemberView update(Member member, MemberRequest memberRequest){
        this.prepare(member,memberRequest);
        Member saveMember=memberRepo.save(member);
        return memberViewConverter.convert(saveMember);
    }
}
