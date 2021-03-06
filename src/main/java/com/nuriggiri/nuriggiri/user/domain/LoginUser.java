package com.nuriggiri.nuriggiri.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class LoginUser {
    private int userNo;
    private String userId; //유저아이디
    private String userPw; //유저비밀번호
    private String nickName; //유저닉네임
    private boolean autoLogin; //유저 권한

}
