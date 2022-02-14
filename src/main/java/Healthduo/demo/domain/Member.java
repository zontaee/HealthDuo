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
    private String member_id;
    private String member_password;
    private String member_sex;
    private String member_email;
    private LocalDate member_date;
    private String member_pnumber;
    @OneToMany(mappedBy = "member")
    List<Bbs> bbs = new ArrayList<>();

    public Member() {

    }

    public Member(String member_id, String member_password) {
        this.member_id = member_id;
        this.member_password = member_password;
    }

    public Member(String member_id, String member_password, String member_sex, String member_email, LocalDate member_date, String member_pnumber) {
        this.member_id = member_id;
        this.member_password = member_password;
        this.member_sex = member_sex;
        this.member_email = member_email;
        this.member_date = member_date;
        this.member_pnumber = member_pnumber;
    }
}
