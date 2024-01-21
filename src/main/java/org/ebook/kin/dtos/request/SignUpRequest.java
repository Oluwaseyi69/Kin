package org.ebook.kin.dtos.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
}
