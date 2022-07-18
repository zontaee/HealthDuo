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
        serviceMethod.CheckError(messageSendTitle, messageSendContent, findReciveMemberId);//오류 검증 기능
        Optional<Member> SendMemberInfo = memberRepository.findById(loginMember);
        serviceMethod.messageSendSave(messageSendTitle,receiveMemberId, messageSendContent, loginMember, SendMemberInfo);//보낸 메시지 저장
        serviceMethod.messageReceivedSave(messageSendTitle,receiveMemberId, messageSendContent, loginMember, SendMemberInfo);//받은 메시지 저장
    }

    @Override
    public Page<MessageReceive> messageReceiveList(Pageable pageable, String loginMember) {
        log.info("messageReceiveList(Service start)");
        pageable = serviceMethod.getPageableMessageReceived(pageable);
        return messageReceiveRepository.findMessageReceiveList(loginMember,pageable);
    }

    @Override
    public Optional<MessageReceive> messageReceivedContent(Long messageReceiveNo) {
        log.info("messageReceivedContent(Service start)");
        Optional<MessageReceive> messageReceivedContent = messageReceiveRepository.findById(messageReceiveNo);
        return messageReceivedContent;
    }

    @Override
    public Page<MessageSend> messageSendList(Pageable pageable, String loginMember) {
        log.info("messageSendList(Service start)");
        pageable = serviceMethod.getPageableMessageSend(pageable);
        return messageReceiveRepository.findMessageSendList(loginMember,pageable);
    }

    @Override
    public Optional<MessageSend> messageSendContent(Long messageSendNo) {
        log.info("messageSendContent(Service start)");
        Optional<MessageSend> messageSendContent = messageSendRepository.findById(messageSendNo);
        return messageSendContent;
    }


}
