package Healthduo.demo.service;

import Healthduo.demo.domain.Member;
import Healthduo.demo.domain.MessageReceive;
import Healthduo.demo.domain.MessageSend;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.repository.MessageReceiveRepository;
import Healthduo.demo.repository.MessageSendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final MemberRepository memberRepository;
    private final MessageSendRepository messageSendRepository;
    private final MessageReceiveRepository messageReceiveRepository;


    @Override
    public int messageSave(String receiveMemberId, String messageSendTitle, String messageSendContent, String loginMember) {
        log.info("messageSave(Service start)");
        Optional<Member> findReciveMemberId = memberRepository.findById(receiveMemberId);
        int result = 0;
        result = CheckReceiveMemberId(findReciveMemberId, result);
        Optional<Member> SendMemberInfo = memberRepository.findById(loginMember);

        MessageSend messageSend = new MessageSend(receiveMemberId, messageSendContent, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), loginMember, receiveMemberId);
        messageSend.addMessageSendMember(SendMemberInfo.get());
        messageSendRepository.save(messageSend);

        MessageReceive messageReceive = new MessageReceive(receiveMemberId, messageSendContent, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), loginMember, receiveMemberId);
        messageReceive.addMessageReceive(SendMemberInfo.get());
        messageReceiveRepository.save(messageReceive);

        return result;
    }

    private int CheckReceiveMemberId(Optional<Member> findId, int result) {
        if(findId == null){
            result = 1;
            throw new RuntimeException("받는 사람 아이디가 존재하지 않습니다.");
        }
        return result;
    }
}
