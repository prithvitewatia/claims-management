package com.claimsmanagement.claimsmanagement.core.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @Column
    @GenericGenerator(
            name = "member_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name="sequence_name",value="member_id_seq"),
                    @org.hibernate.annotations.Parameter(name="INCREMENT",value = "1"),
                    @org.hibernate.annotations.Parameter(name="MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name="MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_id_seq")
    private Long memberId;
    @Column(nullable = false)
    private String memberName;
    @Email
    @Column(nullable = false)
    private String email;
    @Column
    private String password;
    @Column
    private String phoneNo;
    @Column(length = 1000)
    private String address;
}
