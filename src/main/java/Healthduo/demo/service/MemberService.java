package Healthduo.demo.service;

import Healthduo.demo.domain.Member;

public interface MemberService {
    void memberSave(Member member);

    int memberfind(Member member);
}
