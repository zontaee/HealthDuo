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
        private String bbs_notice;
        private String bbs_secret;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        private Member member;

        public void changMember(Member member){
                this.member=member;
                member.getBbs().add(this);
        }
    }

