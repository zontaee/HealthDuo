package Healthduo.demo.dto;


import Healthduo.demo.domain.Member;

public class MessageReceiveDTO {

    private Long messageReceiveNo;
    private String messageReceiveTitle;
    private String messageReceiveContent;
    private String messageReceiveDate;
    private String sendMemberId;
    private String receiveMemberId;
    private Member member;
}
