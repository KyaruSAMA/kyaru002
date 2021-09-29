package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.util.ScannerUtil;

/**
 * 管理员启动界面
 */
public class AdminStartView extends Client {
    // 启动页面
    @Override
    public void start() {
        System.out.println("===================");
        System.out.println("欢迎访问二嗨租车");
        System.out.println("===================");
        System.out.println("1.登录  2.退出");
        String choose = ScannerUtil.next();
        switch (choose) {
            case "1":
                new AdminLoginView().start();
                break;
            case "2":
                System.exit(0);
            default:
                break;
        }
    }

    public static void main(String[] args) {
        AdminStartView adminView = new AdminStartView();
        adminView.start();
    }
}
