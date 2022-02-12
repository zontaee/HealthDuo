package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


public class Bbs {

        private int bbs_no;
        private String bbs_title;
        private String bbs_content;
        private String bbs_date;
        private int bbs_hit;
        private String m;
        private MemberDTO member;

    }

