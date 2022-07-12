package Healthduo.demo.dto;

import Healthduo.demo.domain.Member;

public class MessageSendDTO {
    private Long messageSendNo;  //기본키
    private String messageSendTitle;
    private String messageSendContent;
    private String messageSendDate;
    private String sendMemberId;
    private String receiveMemberId;
    private Member member;
}
