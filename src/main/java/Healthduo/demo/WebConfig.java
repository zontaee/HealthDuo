package Healthduo.demo;

import Healthduo.demo.web.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .excludePathPatterns("/","/members/new","/member/checkinfomember","/login","/logout","/css/**", "/*.ico", "/error","/js/**","/checkinfomember","/duplicatedMember");
    }

}


