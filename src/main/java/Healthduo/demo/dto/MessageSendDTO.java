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
}
