package Healthduo.demo.service;

import Healthduo.demo.domain.Member;
import Healthduo.demo.domain.MessageReceive;
import Healthduo.demo.domain.MessageSend;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.repository.MessageReceiveRepository;
import Healthduo.demo.repository.MessageSendRepository;
import Healthduo.demo.web.ServiceMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ServiceMethod serviceMethod;


    @Override
    public void messageSave(String receiveMemberId, String messageSendTitle, String messageSendContent, String loginMember) {
        log.info("messageSave(Service start)");
        Optional<Member> findReciveMemberId = memberRepository.findById(receiveMemberId);
        CheckError(messageSendTitle, messageSendContent, findReciveMemberId);//오류 검증 기능
        Optional<Member> SendMemberInfo = memberRepository.findById(loginMember);

        MessageSend messageSend = new MessageSend(receiveMemberId, messageSendContent, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), loginMember, receiveMemberId);
        messageSend.addMessageSendMember(SendMemberInfo.get());
        messageSendRepository.save(messageSend);

        MessageReceive messageReceive = new MessageReceive(receiveMemberId, messageSendContent, String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))), loginMember, receiveMemberId);
        messageReceive.addMessageReceive(SendMemberInfo.get());
        messageReceiveRepository.save(messageReceive);

    }

    @Override
    public Page<MessageReceive> messageReceiveList(Pageable pageable, String loginMember) {
        log.info("messageReceiveList(Service start)");
        pageable = serviceMethod.getPageableMessage(pageable);
        return messageReceiveRepository.findMessageReceive(loginMember,pageable);
    }

    @Override
    public Optional<MessageReceive> messageReceivedContent(Long messageReceiveNo) {
        log.info("messageReceivedContent(Service start)");
        Optional<MessageReceive> messageReceivedContent = messageReceiveRepository.findById(messageReceiveNo);
        return messageReceivedContent;
    }

    private void CheckError(String messageSendTitle, String messageSendContent, Optional<Member> findReciveMemberId) {
        if(findReciveMemberId.isEmpty()){
            throw new RuntimeException("받는 사람 아이디가 존재하지 않습니다.");
        }
        if(messageSendTitle.isBlank()){
            throw new RuntimeException("쪽지 제목을 입력해주세요.");
        }
        if(messageSendContent.isBlank()){
            throw new RuntimeException("쪽지 내용을 입력해주세요.");
        }
    }


}
