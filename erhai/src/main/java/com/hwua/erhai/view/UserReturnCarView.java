package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;

/**
 * 归还汽车
 */
public class UserReturnCarView extends Client {
    private User user = null;// 登录后的用户信息

    public UserReturnCarView(User user) {
        this.user = user;
    }
}
