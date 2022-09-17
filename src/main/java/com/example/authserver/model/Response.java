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
public class Response {
    String message;
}
