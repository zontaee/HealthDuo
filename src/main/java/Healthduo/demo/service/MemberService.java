package Healthduo.demo.service;

import Healthduo.demo.domain.Member;

public interface MemberService {
    void memberSave(Member member);

    int loginCheck(Member member);

    Member memberfindById(String loginMember);

    int duplicatedMember(String memberId);

    int deleteMember(String memberId);

}
