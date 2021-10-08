package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.UserRegisterRequest;
import com.hwua.erhai.vo.UserRegisterResponse;

/**
 * 注册
 */
public class UserRegisterView extends Client {

    @Override
    public void start() {
        System.out.println("======注册=====>>>>");
        System.out.println("用户名:");
        String userName = ScannerUtil.next();
        System.out.println("密码：");
        String password = ScannerUtil.next();
        System.out.println("性别：请输入数字(0:男 1：女)：");
        String sex = ScannerUtil.next();
        System.out.println("身份证号：");
        String idNumber = ScannerUtil.next();
        System.out.println("电话：");
        String tel = ScannerUtil.next();
        System.out.println("地址：");
        String addr = ScannerUtil.next();

        UserRegisterRequest registerRequest = new UserRegisterRequest(
                Constant.REGISTER, userName, password, Integer.parseInt(sex), idNumber, tel, addr, 0);
        String request = JsonUtil.toJson(registerRequest);
        String response = request(request);
        // 根据响应,接着往下走
        UserRegisterResponse registerResponse = JsonUtil.fromJson(response, UserRegisterResponse.class);
        if (registerResponse != null && registerResponse.getCode() == 200) { //注册成功
            System.out.println("恭喜你注册成功！");
            // 注册成功后，就进入登录界面
            new UserLoginView().start();
        } else { // 注册失败
            System.out.println("注册失败！");
            System.out.println("1.重新注册  2.返回首页  0.退出");
            String choose = ScannerUtil.next();
            switch (choose) {
                case "1":
                    new UserRegisterView().start();
                    break;
                case "2":
                    new UserStartView().start();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}
