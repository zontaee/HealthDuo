package Healthduo.demo.dto;


import Healthduo.demo.domain.Member;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MessageReceiveDTO {

    private Long messageReceiveNo;
    private String messageReceiveTitle;
    private String messageReceiveContent;
    private String messageReceiveDate;
    private String sendMemberId;
    private String receiveMemberId;
    private Member member;

    public MessageReceiveDTO(Long messageReceiveNo, String messageReceiveTitle, String messageReceiveContent, String messageReceiveDate, String sendMemberId, String receiveMemberId, Member member) {
        this.messageReceiveNo = messageReceiveNo;
        this.messageReceiveTitle = messageReceiveTitle;
        this.messageReceiveContent = messageReceiveContent;
        this.messageReceiveDate = messageReceiveDate;
        this.sendMemberId = sendMemberId;
        this.receiveMemberId = receiveMemberId;
        this.member = member;
    }
}
