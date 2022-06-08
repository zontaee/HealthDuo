package Healthduo.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name="BBS_NO_SEQ", //시퀀스 제너레이터 이름
        sequenceName="BBS_NO", //시퀀스 이름
        initialValue= 1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Bbs {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "BBS_NO")
        private Long bbsNo;  //bbs기본키

        private String bbsTitle;
        private String bbsContent;
        private String bbsDate;
        @ColumnDefault("0")
        private Integer bbsHit;
        private String bbsNotice;
        private String bbsSecret;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "MEMBER_ID")

        private Member member;

        @OneToMany(mappedBy = "bbs")
        List<Comment> comments = new ArrayList<>();

        public void addMember(Member member){
                this.member=member;
                member.getBbs().add(this);
        }

        public Bbs(String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, String bbsNotice, String bbsSecret, Member member) {

                this.bbsTitle = bbsTitle;
                this.bbsContent = bbsContent;
                this.bbsDate = bbsDate;
                this.bbsHit = bbsHit;
                this.bbsNotice = bbsNotice;
                this.bbsSecret = bbsSecret;
                this.member = member;
        }
        public void updateBbs(Long bbsNo,String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, String bbsNotice, String bbsSecret) {

                this.bbsTitle = bbsTitle;
                this.bbsContent = bbsContent;
                this.bbsDate = bbsDate;
                this.bbsNotice = bbsNotice;
                this.bbsSecret = bbsSecret;
        }

}

