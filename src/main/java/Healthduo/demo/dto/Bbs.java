package Healthduo.demo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter


public class Bbs {

        private int bbs_no;
        private String bbs_title;
        private String bbs_content;
        private String bbs_date;
        private int bbs_hit;
        private String m;
        private Member member;

    }

