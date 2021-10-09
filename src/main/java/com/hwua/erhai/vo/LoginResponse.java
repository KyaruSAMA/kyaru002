package com.hwua.erhai.vo;

import com.hwua.erhai.entity.User;

public class LoginResponse extends Response {
    private User user;

    public LoginResponse() {

    }

    public LoginResponse(int code, String msg, User user) {
        super(code, msg);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
