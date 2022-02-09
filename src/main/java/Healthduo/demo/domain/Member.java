package Healthduo.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long member_number;
    private String member_id;
    private String member_password;
    private String member_sex;
    private String member_email;
    private LocalDate member_date;
    private String member_pnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_no")
    private Bbs bbs;

    public void changBbs(Bbs bbs){
        this.bbs = bbs;
        bbs.getMembers().add(this);
    }

}
