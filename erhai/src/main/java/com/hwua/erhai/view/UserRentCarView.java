package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.vo.RentCarRequest;
import com.hwua.erhai.vo.RentCarResponse;

import java.util.jar.JarEntry;

/**
 * 租赁汽车
 */
public class UserRentCarView extends Client {
    private User user = null;// 登录后的用户信息

    public UserRentCarView(User user) {
        this.user = user;
    }



    public void RentCar(String select2){
        RentCarRequest rentCarRequest=new RentCarRequest(Constant.RENT_CAR,user.getId(),Integer.parseInt(select2));
        String request = JsonUtil.toJson(rentCarRequest);
        String response =request(request);
        RentCarResponse rentCarResponse=JsonUtil.fromJson(response,RentCarResponse.class);
        System.out.println("====================================================");
        if (rentCarResponse!=null&&rentCarResponse.getCode()==200){
            System.out.println("租车成功,租车信息如下：");
            Record record = rentCarResponse.getRecord();
            System.out.println("编号\t\t汽车名称\t\t 每日租金\t\t 备注\t\t 品牌\t\t 类型\t\t 借车时间");
            System.out.println(record.getId()+"\t\t"+record.getModel()+"\t\t"
                    +record.getRent()+"\t\t"+record.getComments()+"\t\t"
                    +record.getBrandName()+"\t\t"+record.getCategoryName()+"\t\t"
                    +record.getStartDate());

            new UserCarView(user).showCar((new String[]{"5"}));
        }
        else {
            System.out.println("租车失败，当前车辆不可租");
        }
    }
}
