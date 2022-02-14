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
    private String member_id;
    @NotEmpty(message = "비밀번호를 입력 해주세요.")
    private String member_password;

    public LoginDTO(String member_id, String member_password) {
        this.member_id = member_id;
        this.member_password = member_password;
    }
}
