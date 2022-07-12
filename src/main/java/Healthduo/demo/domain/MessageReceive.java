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
public class MessageReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MESSAGE_RECEIVE_NO")
    private Long messageReceiveNo;  //기본키
    private String messageReceiveTitle;
    private String messageReceiveContent;
    private String messageReceiveDate;
    private String sendMemberId;
    private String receiveMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void MessageReceive(Member member) {
        this.member = member;
        member.getMessageReceive().add(this);
    }
}
