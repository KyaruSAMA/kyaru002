package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;

/**
 * 查询租赁记录
 */
public class UserRecordView extends Client {
    private User user = null;// 登录后的用户信息

    public UserRecordView(User user) {
        this.user = user;
    }
}
