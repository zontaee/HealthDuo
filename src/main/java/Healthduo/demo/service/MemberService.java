package Healthduo.demo.service;

import Healthduo.demo.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    void memberSave(Member member);

    int loginCheck(Member member);

    Member memberfindById(String loginMember);

    int duplicatedMember(String memberId);

    int deleteMember(String memberId);

    Page<Member> memberList(Pageable pageable);
}
