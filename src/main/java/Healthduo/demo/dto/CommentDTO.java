package Healthduo.demo.dto;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
@Getter
@Setter
@ToString
public class CommentDTO {

    private Long commentId;

    private String content;

    private int commentCnt;

    private int commentGroup;
    private String date;
    private int checkInfo;

    private int commentSequence;
    private String childinfo;
    private String memberId;
    private int level;

    public CommentDTO(Long commentId, String content, int commentCnt, int commentGroup, String date, int commentSequence, int level,  String memberId) {
        this.commentId = commentId;
        this.content = content;
        this.commentCnt = commentCnt;
        this.commentGroup = commentGroup;
        this.date = date;
        this.commentSequence = commentSequence;
        this.level = level;

        this.memberId = memberId;
    }

    private Bbs bbs;


    private Member member;
}
