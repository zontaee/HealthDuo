package Healthduo.demo.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Bbs {
        @Id
        @GeneratedValue
        private Long bbs_no;
        private String bbs_title;
        private String bbs_content;
        private String bbs_date;
        private Integer bbs_hit;
        private String bbs_notice;
        private String bbs_secret;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        private Member member;

        public void changMember(Member member){
                this.member=member;
                member.getBbs().add(this);
        }

        public Bbs( String bbs_title, String bbs_content, String bbs_date, Integer bbs_hit, String bbs_notice, String bbs_secret) {

                this.bbs_title = bbs_title;
                this.bbs_content = bbs_content;
                this.bbs_date = bbs_date;
                this.bbs_hit = bbs_hit;
                this.bbs_notice = bbs_notice;
                this.bbs_secret = bbs_secret;
        }


}

