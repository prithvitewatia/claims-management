package com.claimsmanagement.claimsmanagement.core.member.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class MemberRequest {
    @NotBlank(message = "Member name cannot be blank")
    private String memberName;
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = "($|\\d{10})", message = "Phone number must be 10 digit number")
    private String phoneNo;
    @Length(max = 1000, message = "Provide valid address in maximum 1000 characters")
    private String address;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
