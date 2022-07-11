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
    private Integer bbsHit;
    private Boolean bbsNotice;
    private Boolean bbsSecret;
    private String checkNS;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "bbs",cascade = CascadeType.REMOVE,orphanRemoval = true )
    List<Comment> comments = new ArrayList<>();

    public void addMember(Member member) {
        this.member = member;
        member.getBbs().add(this);
    }

    public void noticeAddCheck() {
        this.checkNS += "n";
    }

    public void secretAddCheck() {
        this.checkNS += "s";
    }

    public Bbs(String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, Boolean bbsNotice, Boolean bbsSecret, Member member) {

        this.bbsTitle = bbsTitle;
        this.bbsContent = bbsContent;
        this.bbsDate = bbsDate;
        this.bbsHit = bbsHit;
        this.bbsNotice = bbsNotice;
        this.bbsSecret = bbsSecret;
        this.member = member;
    }

    public void updateBbs(Long bbsNo, String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, Boolean bbsNotice, Boolean bbsSecret) {

        this.bbsTitle = bbsTitle;
        this.bbsContent = bbsContent;
        this.bbsDate = bbsDate;
        this.bbsNotice = bbsNotice;
        this.bbsSecret = bbsSecret;
    }


}

