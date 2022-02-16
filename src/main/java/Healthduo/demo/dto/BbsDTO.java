package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BbsDTO {

        private Long bbs_no;

        private String bbs_title;
        private String bbs_content;
        private String bbs_date;
        private Integer bbs_hit;
        private String bbs_notice;
        private String bbs_secret;
        private MemberDTO member;

        public BbsDTO(Long bbs_no, String bbs_title, String bbs_content, String bbs_date, Integer bbs_hit, String bbs_notice, String bbs_secret) {
                this.bbs_no = bbs_no;
                this.bbs_title = bbs_title;
                this.bbs_content = bbs_content;
                this.bbs_date = bbs_date;
                this.bbs_hit = bbs_hit;
                this.bbs_notice = bbs_notice;
                this.bbs_secret = bbs_secret;
        }

        public BbsDTO() {

        }
}

