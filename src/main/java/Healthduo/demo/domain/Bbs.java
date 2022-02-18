package Healthduo.demo.domain;

import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bbs {
        @Id
        @GeneratedValue
        @Column(name = "BBS_NO")
        private Long bbsNo;
        private String bbsTitle;
        private String bbsContent;
        private String bbsDate;
        private Integer bbsHit;
        private String bbsNotice;
        private String bbsSecret;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        private Member member;

        public void changMember(Member member){
                this.member=member;
                member.getBbs().add(this);
        }

        public Bbs(String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, String bbsNotice, String bbsSecret) {

                this.bbsTitle = bbsTitle;
                this.bbsContent = bbsContent;
                this.bbsDate = bbsDate;
                this.bbsHit = bbsHit;
                this.bbsNotice = bbsNotice;
                this.bbsSecret = bbsSecret;
        }
        public void updateBbs(Long bbsNo,String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, String bbsNotice, String bbsSecret) {

                this.bbsTitle = bbsTitle;
                this.bbsContent = bbsContent;
                this.bbsDate = bbsDate;
                this.bbsNotice = bbsNotice;
                this.bbsSecret = bbsSecret;
        }

}

