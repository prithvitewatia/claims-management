package com.claimsmanagement.claimsmanagement.core.member;

import com.claimsmanagement.claimsmanagement.core.member.web.MemberRequest;
import com.claimsmanagement.claimsmanagement.core.member.web.MemberView;
import com.claimsmanagement.claimsmanagement.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    MemberView getMemberById(Long id) throws EntityNotFoundException;

    Member findMemberOrThrow(Long id) throws EntityNotFoundException;

    Page<MemberView> getAllMembers(Pageable pageable);

    MemberView create(MemberRequest memberRequest);

    void delete(Long id);

    MemberView update(Member member, MemberRequest memberRequest);
}
