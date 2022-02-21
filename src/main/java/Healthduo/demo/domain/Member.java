package Healthduo.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;
    private String memberPassword;
    private String memberSex;
    private String memberEmail;
    private LocalDate memberDate;
    private String memberPnumber;
    @OneToMany(mappedBy = "member")
    List<Bbs> bbs = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Comment> comments = new ArrayList<>();

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
