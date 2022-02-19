package Healthduo.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@ToString
public class LoginDTO {
    @NotEmpty(message = "아이디를 입력 해주세요.")
    private String memberId;
    @NotEmpty(message = "비밀번호를 입력 해주세요.")
    private String memberPassword;
    //저장용
    private Boolean idRemember;

    public LoginDTO(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
}
