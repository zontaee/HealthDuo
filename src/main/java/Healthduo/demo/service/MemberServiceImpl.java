package Healthduo.demo.service;

import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public void memberSave(Member member) {
        log.info("memberSave(controller start");
        Member savedMember = memberRepository.save(member);
        log.info("savedMember Member_id =>" + savedMember.getMember_id());

    }
}
