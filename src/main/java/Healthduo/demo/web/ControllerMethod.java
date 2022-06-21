package Healthduo.demo.web;

import Healthduo.demo.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ControllerMethod {

    public void createCookie(LoginDTO loginDTO, HttpServletResponse response) {
        if (loginDTO.getIdRemember()) {
            Cookie rememberId = new Cookie("rememberId", loginDTO.getMemberId());
            response.addCookie(rememberId);
        } else {
            Cookie deleteCookie = new Cookie("rememberId", null);
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);
        }
    }
    public void addErrorMessage(BindingResult result, int loginCheck) {
        switch (loginCheck) {
            case 1:
                result.addError(new ObjectError("loginDTO", "등록된 아이디가 없습니다."));
                break;
            case 3:
                result.addError(new ObjectError("loginDTO", "등록된 비밀번호가 틀렸습니다."));
                break;
        }
    }
}
