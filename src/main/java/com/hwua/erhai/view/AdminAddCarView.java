package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;

/**
 * 添加汽车
 */
public class AdminAddCarView extends Client {
    private User user = null;// 登录后的用户信息

    public AdminAddCarView(User user) {
        this.user = user;
    }
}
