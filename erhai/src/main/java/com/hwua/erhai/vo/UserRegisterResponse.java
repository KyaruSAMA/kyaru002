package com.hwua.erhai.vo;

import com.alibaba.fastjson.JSONObject;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.JsonUtil;

public class UserRegisterResponse extends Response {
    private User user;

    public UserRegisterResponse(int code, String msg, User user) {
        super(code, msg);
        this.user = user;
    }
    public UserRegisterResponse(){

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        String userName = "userName001";
        String password = "password001";
        int sex = 0;
        String idNumber = "idNumber001";
        String tel = "tel001";
        String addr = "addr001";
        int type = 0;
        User user = new User(userName, password, sex, idNumber, tel, addr, type);

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse(
                200, "注册成功", user);

        String jsonString = JsonUtil.toJson(userRegisterResponse);
        System.out.println("jsonString = " + jsonString);

        UserRegisterResponse response = JsonUtil.fromJson(jsonString, UserRegisterResponse.class);
        System.out.println("response = " + JsonUtil.toJson(response));

        JSONObject response2 = JsonUtil.fromJson(jsonString);
        System.out.println("response2 = " + JsonUtil.toJson(response2));

        String response3 = JsonUtil.valueByPath(response2, "$.msg", String.class);
        System.out.println("response3 = " + JsonUtil.toJson(response3));
    }

}
