package Healthduo.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name="COMMENT_NO_SEQ", //시퀀스 제너레이터 이름
        sequenceName="COMMENT_NO", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Getter
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
