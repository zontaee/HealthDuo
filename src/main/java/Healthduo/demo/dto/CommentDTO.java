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

    private Long id;

    private String content;

    private int commentCnt;

    private int commentGroup;
    private String Date;

    private int commentSequence;


    private int level;


    private Bbs bbs;


    private Member member;
}
