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

    @Override
    public int memberfind(Member member) {
        int result;
        log.info("memberSave(controller start");
        Member findMember = memberRepository.findById(member.getMember_id()).get();
        log.info(findMember.getMember_id());
        log.info(findMember.getMember_password());
        if(findMember == null){
            result = 1;//등록된 아이디가 없습니다.
            return result;

        }else {
            if(findMember.getMember_password().equals(member.getMember_password())){
                result =2;
                return result;//아이디 비밀번호 둘다 일치
            }else {
                result =3;
                return result;//등록된 비밀번호가 틀렸습니다.
            }
        }

    }
}
