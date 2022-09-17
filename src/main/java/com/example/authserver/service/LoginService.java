package com.example.authserver.service;

import com.example.authserver.model.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author pashtet
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LoginService {

    final RestTemplate restTemplate;
    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    String issueUrl;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
    String clientID;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
    String clientSecret;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
    String grantType;
    @SuppressWarnings("all")
    public HttpEntity<LoginResponse> login(LoginRequest loginRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientID);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);


        ResponseEntity<LoginResponse> response = restTemplate
                .postForEntity("http://localhost:8080/realms/auth-realm/protocol/openid-connect/token",
                httpEntity, LoginResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
    @SuppressWarnings("all")
    public HttpEntity<Response> logout(TokenRequest tokenRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientID);
        map.add("client_secret", clientSecret);
        map.add("refresh_token", tokenRequest.getToken());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);


        ResponseEntity<Response> response = restTemplate
                .postForEntity("http://localhost:8080/realms/auth-realm/protocol/openid-connect/logout",
                        httpEntity, Response.class);
        Response res = new Response();
        if(response.getStatusCode().is2xxSuccessful()){
            res.setMessage("Logged out successfully");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @SuppressWarnings("all")
    public HttpEntity<IntrospectResponse> introspect(TokenRequest tokenRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientID);
        map.add("client_secret", clientSecret);
        map.add("token", tokenRequest.getToken());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);


        ResponseEntity<IntrospectResponse> response = restTemplate
                .postForEntity("http://localhost:8080/realms/auth-realm/protocol/openid-connect/token/introspect",
                        httpEntity, IntrospectResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
