package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 查询汽车
 */
public class UserCarView extends Client {
    private User user = null;// 登录后的用户信息

    public UserCarView(User user) {
        this.user = user;
    }

    public void showCar(String[] strings) {
        // TODO: 需要自行实现代码 显示登录后的查询汽车列表
        System.out.println();
        throw new NotImplementedException();
    }
}
