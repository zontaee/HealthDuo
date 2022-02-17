package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Getter @Setter
public class MemberDTO {

    private Long memberNumber;
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

}
