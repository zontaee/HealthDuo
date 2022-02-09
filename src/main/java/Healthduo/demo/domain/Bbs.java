package Healthduo.demo.domain;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Bbs {
        @Id
        @GeneratedValue
        private int bbs_no;
        private String bbs_title;
        private String bbs_content;
        private String bbs_date;
        private int bbs_hit;
        private String m;
        @OneToMany(mappedBy = "bbs")
        List<Member> members = new ArrayList<>();
    }

