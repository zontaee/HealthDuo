package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class Member {

    private Long member_number;
    private String member_id;
    private String member_password;
    private String member_sex;
    private String member_email;
    private LocalDate member_date;
    private String member_pnumber;

}
