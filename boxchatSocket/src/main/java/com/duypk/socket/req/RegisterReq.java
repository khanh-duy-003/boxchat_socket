package com.duypk.socket.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterReq {
    
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;

}