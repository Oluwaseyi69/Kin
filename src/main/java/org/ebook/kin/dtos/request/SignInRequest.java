package org.ebook.kin.dtos.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
