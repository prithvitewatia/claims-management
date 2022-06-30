package com.claimsmanagement.claimsmanagement.core.member.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberView {
    private Long memberId;
    private String memberName;
    private String email;
    private String phoneNo;
    private String address;
}
