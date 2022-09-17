package com.example.authserver.controller;

import com.example.authserver.model.*;
import com.example.authserver.service.LoginService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pashtet
 */
@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class LoginController {

    LoginService loginService;

    @RequestMapping("/login")
    public HttpEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @RequestMapping("/logout")
    public HttpEntity<Response> logout(@RequestBody TokenRequest tokenRequest){
        return loginService.logout(tokenRequest);
    }

    @RequestMapping("/introspect")
    public HttpEntity<IntrospectResponse> introspect(@RequestBody TokenRequest tokenRequest){
        return loginService.introspect(tokenRequest);
    }
}
