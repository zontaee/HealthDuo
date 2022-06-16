package Healthduo.demo.dto;

import Healthduo.demo.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BbsDTO {

        private Long bbsNo;

        private String bbsTitle;
        private String bbsContent;
        private String bbsDate;
        private Integer bbsHit;
        private Boolean bbsNotice;
        private Boolean bbsSecret;
        private Member member;
        private String checkNS;

        public BbsDTO(Long bbsNo, String bbsTitle, String bbsContent, String bbsDate, Integer bbsHit, Boolean bbsNotice, Boolean bbsSecret,String checkNS ,Member member) {
                this.bbsNo = bbsNo;
                this.bbsTitle = bbsTitle;
                this.bbsContent = bbsContent;
                this.bbsDate = bbsDate;
                this.bbsHit = bbsHit;
                this.bbsNotice = bbsNotice;
                this.bbsSecret = bbsSecret;
                this.member= member;
                this.checkNS= checkNS;

        }

        public BbsDTO() {

        }
}

