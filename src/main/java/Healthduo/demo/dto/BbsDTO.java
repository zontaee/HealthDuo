package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BbsDTO {

        private int bbs_no;

        private String bbs_title;
        private String bbs_content;
        private String bbs_date;
        private int bbs_hit;
        private String bbs_notice;
        private String bbs_secret;
        private MemberDTO member;

    }

