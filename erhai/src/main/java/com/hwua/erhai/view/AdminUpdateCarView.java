package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.*;

import java.util.List;

/**
 * 修改汽车
 */
public class AdminUpdateCarView extends Client {
    private User user = null;// 登录后的用户信息

    public AdminUpdateCarView(User user) {
        this.user = user;
    }
    public void showupdatecar(String[]strings){
        AdminQueryCarsRequest adminQueryCarRequest = null;
        if (strings != null && strings.length == 2) {
            String optionYType = strings[0];
            String orderType = strings[1];
            if ("7".equals(optionYType)) {
                adminQueryCarRequest = new AdminQueryCarsRequest(Constant.ADMIN_QUERY_CARS,orderType,null,"7");
            }
            String request = JsonUtil.toJson(adminQueryCarRequest);
            String response = request(request);
            AdminQueryCarsResponse adminQueryCarResponse = JsonUtil.fromJson(response,  AdminQueryCarsResponse.class);
            if (adminQueryCarResponse == null || adminQueryCarResponse.getCode() == 400) {
                System.out.println(adminQueryCarResponse.getMsg());
            } else {
                System.out.println("=====================================================");
                System.out.println("编号\t\t汽车名称\t\t备注\t\t品牌\t\t类型\t\t价格\t\t是否可租\t\t是否上架");
                List<Car> cars = adminQueryCarResponse.getCars();
                for (Car car : cars) {
                    System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s \n",
                            car.getId(), car.getModel(), car.getComments(), car.getBrandName(),
                            car.getCategoryName(), car.getRent(), car.getStatus() == 0 ? "是" : "否", car.getUsable() == 0 ? "上架" : "下架");
                }





            System.out.println("=========================================");
            System.out.println("请输入以下信息：");


            System.out.println("-------------------------------------");
            System.out.println("请输入汽车每日租金：");
            double rent = ScannerUtil.nextInt();
            System.out.println("-------------------------------------");
            System.out.println("是否上架(0:上架,1：下架)");
            int usable = ScannerUtil.nextInt();
            UpdateCarRequest updateCarRequest = new UpdateCarRequest(Constant.UPDATE_CAR,Long.parseLong(orderType),(int)rent,usable);
            String request2 = JsonUtil.toJson(updateCarRequest);
            String response2 = request(request2);
            UpdateCarResponse updateCarResponse = JsonUtil.fromJson(response2,UpdateCarResponse.class);
            if (updateCarResponse.getCode() == 200 && updateCarResponse != null){ System.out.println("添加成功！");
            new AdminCarView(user).showCar(new String[]{"1",strings[1]});
        }
            }
        }
    }
}
