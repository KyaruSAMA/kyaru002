package com.hwua.erhai.vo;

public class LoginRequest extends Request {
    private String username;
    private String password;
    private int type;

    public LoginRequest(String requestType, String username, String password, int type) {
        super(requestType);
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public LoginRequest() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
