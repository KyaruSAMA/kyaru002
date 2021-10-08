package com.hwua.erhai.vo;

public class UserRegisterRequest extends Request {
    private String username;
    private String password;
    private int sex;
    private String idNumber;
    private String tel;
    private String addr;
    private int type;

    public UserRegisterRequest(String requestType, String username, String password, int sex, String idNumber, String tel, String addr, int type) {
        super(requestType);
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.idNumber = idNumber;
        this.tel = tel;
        this.addr = addr;
        this.type = type;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
