package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;

/**
 * 修改汽车
 */
public class AdminUpdateCarView extends Client {
    private User user = null;// 登录后的用户信息

    public AdminUpdateCarView(User user) {
        this.user = user;
    }
}
