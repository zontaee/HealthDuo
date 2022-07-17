package Healthduo.demo.dto;

import Healthduo.demo.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageSendDTO {
    private Long messageSendNo;
    private String messageSendTitle;
    private String messageSendContent;
    private String messageSendDate;
    private String sendMemberId;
    private String receiveMemberId;
    private Member member;


    public MessageSendDTO(Long messageSendNo, String messageSendTitle, String messageSendContent, String messageSendDate, String sendMemberId, String receiveMemberId, Member member) {
        this.messageSendNo = messageSendNo;
        this.messageSendTitle = messageSendTitle;
        this.messageSendContent = messageSendContent;
        this.messageSendDate = messageSendDate;
        this.sendMemberId = sendMemberId;
        this.receiveMemberId = receiveMemberId;
        this.member = member;
    }

    public MessageSendDTO() {

    }
}
