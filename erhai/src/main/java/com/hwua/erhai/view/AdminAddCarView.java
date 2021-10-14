package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.*;

import java.util.List;

/**
 * 添加汽车
 */
public class AdminAddCarView extends Client {
    private User user = null;// 登录后的用户信息

    public AdminAddCarView(User user) {
        this.user = user;
    }
    public void ShowaddCar(){
        System.out.println("=========================================");
        System.out.println("请输入以下信息：");
        System.out.println("---------------------------------");
        System.out.println("请输入车牌号：");
        String carNumber = ScannerUtil.next();
        System.out.println("-------------------------------------");
        System.out.println("请选择品牌编号：");
        System.out.println("-------------------------------------");
        int brandId = ScannerUtil.nextInt();
        System.out.println("请输入型号：");
        String model = ScannerUtil.next();
        System.out.println("-------------------------------------");
        System.out.println("请输入汽车颜色：");
        String color = ScannerUtil.next();
        System.out.println("请选择类型编号：");
        int categoryId = ScannerUtil.nextInt();
        System.out.println("-------------------------------------");
        System.out.println("请输入汽车概要：");
        String comments = ScannerUtil.next();
        System.out.println("-------------------------------------");
        System.out.println("请输入汽车市场价：");
        double price = ScannerUtil.nextInt();
        System.out.println("-------------------------------------");
        System.out.println("请输入汽车每日租金：");
        double rent = ScannerUtil.nextInt();
        System.out.println("-------------------------------------");
        System.out.println("是否可借(0:可借,1：不可借)");
        int status = ScannerUtil.nextInt();
        System.out.println("-------------------------------------");
        System.out.println("是否上架(0:上架,1：下架)");
        int usable = ScannerUtil.nextInt();
        AddCarRequest addCarRequset = new AddCarRequest(Constant.ADD_CAR,carNumber,brandId,model,carNumber,categoryId,
                comments,price,rent,status,usable);
        String request2 = JsonUtil.toJson(addCarRequset);
        String response2 = request(request2);
        AddCarResponse addCarResponse = JsonUtil.fromJson(response2,AddCarResponse.class);
        if (addCarResponse.getCode() == 200 && addCarResponse != null){
            System.out.println("添加成功！");


            new AdminCarView(user).showCar(new String[]{"5"});
        }else {

            System.out.println("1.重新添加  2.返回首页  0.退出");
            String choose = ScannerUtil.next();
            switch (choose) {
                case "1":
                    new AdminAddCarView(user).ShowaddCar();
                    break;
                case "2":
                    new AdminStartView().start();
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
