package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.LoginRequest;
import com.hwua.erhai.vo.LoginResponse;

/**
 * 登录
 */
public class UserLoginView extends Client {
    private User user = null;// 登录后的用户信息

    @Override
    public void start() {
        System.out.println("======登录=====>>>>");
        // 获取用户输入的数据
        System.out.println("用户名:");
        String userName = ScannerUtil.next();
        System.out.println("密码:");
        String password = ScannerUtil.next();

        // 创建请求对象
        LoginRequest loginRequest = new LoginRequest(Constant.LOGIN, userName, password, 0);
        // 将请求对象转化为JSON字符串
        String request = JsonUtil.toJson(loginRequest);
        // 将请求JSON字符串发送给服务器端
        String response = request(request);
        // 根据响应,接着往下走
        LoginResponse loginResponse = JsonUtil.fromJson(response, LoginResponse.class);
        if (loginResponse == null || loginResponse.getCode() == 400) { // 失败
            System.out.println("用户名或密码错误!");
            System.out.println("1.重新输入  2.返回首页  0.退出");
            String choose = ScannerUtil.next();
            switch (choose) {
                case "1":
                    new UserLoginView().start();
                    break;
                case "2":
                    new UserStartView().start();
                    break;
                case "0":
                    System.exit(0);
                default:
                    break;
            }

        } else {// 成功
            user = loginResponse.getUser();
            System.out.println("============================================================");
            System.out.println("登录成功！");
            System.out.println("欢迎" + user.getUserName());
            // -------接着往下走,调到下一个方法,做查询所有的车
            new UserCarView(user).showCar((new String[]{"5"}));
        }
    }
}
