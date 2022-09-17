package com.example.authserver.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author pashtet
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class LoginResponse {
    String access_token;
    String refresh_token;
    String expires_in;
    String refresh_expires_in;
    String token_type;
}
