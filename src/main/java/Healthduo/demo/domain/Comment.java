package Healthduo.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public void addMember(Member member){
        this.member=member;
        member.getComments().add(this);
    }

    public void addBbs(Bbs bbs){
        this.bbs=bbs;
        bbs.getComments().add(this);
    }

}
