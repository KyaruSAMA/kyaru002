package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.util.ScannerUtil;

/**
 * 普通用户的界面
 */
public class UserStartView extends Client {
    // 启动页面
    @Override
    public void start() {
        System.out.println("===================");
        System.out.println("欢迎访问二嗨租车");
        System.out.println("===================");
        System.out.println("1.登录  2.注册  3.退出");
        String choose = ScannerUtil.next();
        switch (choose) {
            case "1":
                new UserLoginView().start();
                break;
            case "2":
                new UserRegisterView().start();
                break;
            case "3":
                System.exit(0);
            default:
                break;
        }
    }


    public static void main(String[] args) {
        UserStartView userView = new UserStartView();
        userView.start();
    }
}
