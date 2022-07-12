package Healthduo.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;
    private String memberPassword;
    private String memberSex;
    private String memberEmail;
    private LocalDate memberDate;
    private String memberPnumber;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE,orphanRemoval = true )
    List<Bbs> bbs = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE,orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE,orphanRemoval = true)
    List<MessageReceive> messageReceive = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE,orphanRemoval = true)
    List<MessageSend> messageSend = new ArrayList<>();


    public Member() {

    }

    public Member(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }

    public Member(String memberId, String memberPassword, String memberSex, String memberEmail, LocalDate memberDate, String memberPnumber) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberSex = memberSex;
        this.memberEmail = memberEmail;
        this.memberDate = memberDate;
        this.memberPnumber = memberPnumber;
    }
}
