package com.kevin.controller.imp;

import com.kevin.ResultEntity.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class GoogleLoginController {

    @GetMapping("/getGoogleUser")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Map.of("authenticated", false);
        }

        return Map.of(
                "authenticated", true,
                "name", principal.getAttribute("name"),
                "email", principal.getAttribute("email"),
                "picture", principal.getAttribute("picture")
        );
    }


    @GetMapping("/logout123")
    public Result logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("google log out");
        request.logout(); // 清除 Spring Security 身份
        response.setStatus(HttpServletResponse.SC_OK);
        return Result.success("log out successfull");
    }
}
