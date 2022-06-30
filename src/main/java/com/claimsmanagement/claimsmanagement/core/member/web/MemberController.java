package com.claimsmanagement.claimsmanagement.core.member.web;

import com.claimsmanagement.claimsmanagement.core.member.Member;
import com.claimsmanagement.claimsmanagement.core.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @RequestMapping("/{id}")
    @ResponseBody
    public MemberView getMemberById(@PathVariable("id") Long id){
        return memberService.getMemberById(id);
    }
    @GetMapping
    @ResponseBody
    public Page<MemberView> getAllMembers(@PageableDefault(sort = "id",
            direction = Sort.Direction.DESC)Pageable pageable){
        return memberService.getAllMembers(pageable);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MemberView create(@RequestBody @Valid MemberBaseRequest memberBaseRequest){
        return memberService.create(memberBaseRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("id") Long id){
        memberService.delete(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public MemberView updateMember(@PathVariable("id") Long id
            ,@RequestBody @Valid MemberBaseRequest memberBaseRequest){
        Member member=memberService.findMemberOrThrow(id);
        return memberService.update(member,memberBaseRequest);
    }
}
