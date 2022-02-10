package Healthduo.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "member")
    List<Bbs> bbs = new ArrayList<>();



}
