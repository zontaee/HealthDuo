package Healthduo.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "COMMENT_NO_SEQ")
    @Column(name = "COMMENT_NO")
    private Long commentId;  //comment 기본키

    private String content;
    @ColumnDefault("0")
    private int commentCnt;
    //대댓글시 정렬을 위해 사용
    @ColumnDefault("0")
    private int commentGroup;
    private String Date;

    @ColumnDefault("0")
    private int commentSequence;

    @ColumnDefault("0")
    private int level;

    private String childInfo;
    @ColumnDefault("0")
    private int checkInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BBS_NO")
    private Bbs bbs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;



    public Comment() {
    }

    public Comment(String content, int commentCnt, int commentGroup, String date) {
        this.content = content;
        this.commentCnt = commentCnt;
        this.commentGroup = commentGroup;
        this.Date = date;
    }

    public Comment(String content, int commentCnt, int commentGroup, String date, int commentSequence, int level ,String childInfo, int checkInfo) {
        this.content = content;
        this.commentCnt = commentCnt;
        this.commentGroup = commentGroup;
        Date = date;
        this.commentSequence = commentSequence;
        this.level = level;
        this.childInfo = childInfo;
        this.checkInfo = checkInfo;


    }

    public void addMember(Member member){
        this.member=member;
        member.getComments().add(this);
    }

    public void addBbs(Bbs bbs){
        this.bbs=bbs;
        bbs.getComments().add(this);
    }

}
