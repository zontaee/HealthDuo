package Healthduo.demo.service;

import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void memberSave(Member member) {
        log.info("memberSave(controller start");
        Member savedMember = memberRepository.saveMember(member);
        log.info("savedMember Member_id =>" + savedMember.getMemberId());

    }

    @Override
    public int memberfind(Member member) {
        int result;
        log.info("memberfind(Service start)");
        Member findMember = memberRepository.findById(member.getMemberId()).orElse(null);
        //admin 추가 고려
        if (findMember == null) {
            result = 1;//등록된 아이디가 없습니다.
            return result;
        } else {
            if (findMember.getMemberPassword().equals(member.getMemberPassword())) {
                result = 2;
                return result;//아이디 비밀번호 둘다 일치
            } else {
                result = 3;
                return result;//등록된 비밀번호가 틀렸습니다.
            }
        }

    }

    @Override
    public Member memberfindById(String loginMember) {
        log.info("memberfindById(Service start)");
        Member findMember = memberRepository.findById(loginMember).orElse(null);
        return findMember;
    }

    @Override
    public int duplicatedMember(String memberId) {
        log.info("duplicatedMember(Service start)");
        int checkIdNumber;
        Member findId = memberRepository.findById(memberId).orElse(null);

        if (findId == null) {
            checkIdNumber = 0;
        } else {
            checkIdNumber = 1;
        }

        return checkIdNumber;
    }
}
