package Healthduo.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class MessageSend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MESSAGE_SEND_NO")
    private Long messageSendNo;  //기본키
    private String messageSendTitle;
    private String messageSendContent;
    private String messageSendDate;
    private String sendMemberId;
    private String receiveMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMessageSendMember(Member member) {
        this.member = member;
        member.getMessageSend().add(this);
    }

    public MessageSend( String messageSendTitle, String messageSendContent, String messageSendDate, String sendMemberId, String receiveMemberId) {
        this.messageSendTitle = messageSendTitle;
        this.messageSendContent = messageSendContent;
        this.messageSendDate = messageSendDate;
        this.sendMemberId = sendMemberId;
        this.receiveMemberId = receiveMemberId;
    }
}
