package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Getter @Setter
public class MemberDTO {

    private Long member_number;
    @NotEmpty(message = "아이디를 입력 해주세요.")
    private String member_id;
    @NotEmpty(message = "비밀번호를 입력 해주세요.")
    private String member_password;
    @NotEmpty(message = "성별를 입력 해주세요.")
    private String member_sex;
    @NotEmpty(message = "이메일을 입력 해주세요.")
    private String member_email;
    private LocalDate member_date;
    @NotEmpty(message = "핸드폰번호를 입력 해주세요.")
    private String member_pnumber;

}
