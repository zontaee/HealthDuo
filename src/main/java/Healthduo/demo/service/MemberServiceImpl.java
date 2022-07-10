package Healthduo.demo.service;

import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.web.ServiceMethod;
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
    private final ServiceMethod serviceMethod;

    @Override
    public void memberSave(Member member) {
        log.info("memberSave(controller start");
        Member savedMember = memberRepository.save(member);
        log.info("savedMember Member_id =>" + savedMember.getMemberId());

    }

    @Override
    public int loginCheck(Member member) {
        log.info("memberfind(Service start)");
        Member loginCheck = memberRepository.findById(member.getMemberId()).orElse(null);
        //admin 추가 고려
        return serviceMethod.loginCheck(member, loginCheck);


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
        Member findId = memberRepository.findById(memberId).orElse(null);
        int checkIdNumber = serviceMethod.CheckIdNumber(findId);

        return checkIdNumber;
    }

    /**
     * 삭제에 성공하면 1 실패하면 0 반환
     * @param memberId
     * @return
     */
    @Override
    public int deleteMember(String memberId) {
        log.info("deleteMember(Service start)");
        int deleteCheckNumber = serviceMethod.getDeleteCheckNumber(memberId);
        return deleteCheckNumber;
    }

}
