package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.vo.ReturnCarRequest;
import com.hwua.erhai.vo.ReturnCarResponse;

import java.io.StringReader;
import java.util.jar.JarEntry;

/**
 * 归还汽车
 */
public class UserReturnCarView extends Client {
    private User user = null;// 登录后的用户信息

    public UserReturnCarView(User user) {
        this.user = user;
    }
    public void ReturnCar(String select2){
        ReturnCarRequest returnCarRequest=new ReturnCarRequest(Constant.RETURN_CAR, (int) user.getId(),Integer.parseInt(select2));
        String request = JsonUtil.toJson(returnCarRequest);
        String response=request(request);
        ReturnCarResponse returnCarResponse=JsonUtil.fromJson(response,ReturnCarResponse.class);
        System.out.println("=======================================");
        if (returnCarResponse!=null&&returnCarResponse.getCode()==200){
            System.out.println("还车成功,租车信息如下：");
            Record record=returnCarResponse.getRecord();
            System.out.println("编号\t\t汽车名称\t\t租金\t\t备注\t\t品牌\t\t类型\t\t租车时间\t\t还车时间");
            System.out.println(record.getId()+"\t\t"+record.getCarId()+"\t\t"+record.getPayment()+"\t\t"+
                    record.getComments()+"\t\t"+record.getBrandName()+"\t\t"
                    +record.getCategoryName()+"\t\t"+record.getStartDate()+"\t\t"+record.getReturnDate());
            new UserCarView(user).showCar((new String[]{"5"}));
    }
        else {
            System.out.println("还车失败");
        }
}
}
