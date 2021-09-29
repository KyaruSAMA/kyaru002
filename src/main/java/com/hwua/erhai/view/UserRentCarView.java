package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;

/**
 * 租赁汽车
 */
public class UserRentCarView extends Client {
    private User user = null;// 登录后的用户信息

    public UserRentCarView(User user) {
        this.user = user;
    }
}
