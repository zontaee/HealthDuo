package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Getter @Setter
public class MemberDTO {

    @NotEmpty(message = "아이디를 입력 해주세요.")
    private String memberId;
    @NotEmpty(message = "비밀번호를 입력 해주세요.")
    private String memberPassword;
    @NotEmpty(message = "성별를 입력 해주세요.")
    private String memberSex;
    @NotEmpty(message = "이메일을 입력 해주세요.")
    private String memberEmail;
    private LocalDate memberDate;
    @NotEmpty(message = "핸드폰번호를 입력 해주세요.")
    private String memberPnumber;
    //저장용


    public MemberDTO() {
    }

    public MemberDTO(String memberId, String memberPassword, String memberSex, String memberEmail, LocalDate memberDate, String memberPnumber) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberSex = memberSex;
        this.memberEmail = memberEmail;
        this.memberDate = memberDate;
        this.memberPnumber = memberPnumber;
    }
}
