package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.UserQueryCarsRequest;
import com.hwua.erhai.vo.UserQueryCarsResponse;

import java.util.List;

/**
 * 查询汽车
 */
public class UserCarView extends Client {
    private User user = null;// 登录后的用户信息



    public UserCarView(User user) {
        this.user = user;
    }
    public void showCar(String[] strings) {
        UserQueryCarsRequest userQueryCarsRequest=null;


        if (strings!=null&&strings.length==1){
            String optionType =strings[0];
            if ("5".equals(optionType)){
                userQueryCarsRequest =new UserQueryCarsRequest(Constant.USER_QUERY_CARS,null,null,"3");

            }
            else if ("5".equals(optionType)){
                userQueryCarsRequest =new UserQueryCarsRequest(Constant.USER_QUERY_CARS,null,null,"3");
            }

        }else if (strings!=null&&strings.length==2){
                String optionType =strings[0];
                String orderType = strings[1];
                int    orderType1=Integer.parseInt(strings[1]);

              if ("2".equals(optionType)&&"1".equals(orderType)){
                    userQueryCarsRequest=new UserQueryCarsRequest(Constant.USER_QUERY_CARS,null,null,"1");
                }else if ("2".equals(optionType)&&"2".equals(orderType)){
                    userQueryCarsRequest=new UserQueryCarsRequest(Constant.USER_QUERY_CARS,null,null,"2");
                }
                else if ("3".equals(optionType)){
                    userQueryCarsRequest=new UserQueryCarsRequest(Constant.USER_QUERY_CARS,orderType,null,"5");
                }
                else if ("4".equals(optionType)){
                    userQueryCarsRequest=new UserQueryCarsRequest(Constant.USER_QUERY_CARS,orderType,null,"6");
                }


            }
        String request = JsonUtil.toJson(userQueryCarsRequest);

        String response= request(request);

        UserQueryCarsResponse userQueryCarsResponse=JsonUtil.fromJson(response,UserQueryCarsResponse.class);

        if (userQueryCarsResponse==null||userQueryCarsResponse.getCode()==400){
            System.out.println("查询失败");
        }else {
            System.out.println("===============================");
            System.out.println("编号\t\t\t汽车名称\t\t\t备注\t\t\t品牌\t\t\t类型\t\t\t价格\t\t\t是否可租");
            List<Car> cars= userQueryCarsResponse.getCars();
            for (Car car:cars){
                System.out.printf("%d\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\n",
                        car.getId(),car.getModel(),car.getComments(),car.getBrandName(),car.getCategoryName(),car.getRent(),
                car.getStatus()==0?"是":"否");
            }
            System.out.println();
            System.out.println("输入0退出");
            System.out.println("输入1+汽车编号    进入租车订单 如1+2");
            System.out.println("输入2+1          按价格降序排序");
            System.out.println("输入2+2          按价格升序排序 ");
            System.out.println("输入3+类型编号    按类型搜索");
            System.out.println("输入4+品牌编号    按品牌搜索");
            System.out.println("输入5            查看全部汽车");
            System.out.println("输入6            查看我的租车记录");
            System.out.println("输入7+汽车编号     还车");
            String result = ScannerUtil.next();
            String[] split;
            split =result.split("\\+");

            switch (split[0]){
                case "0" :
                    System.exit(0);
                case "1":
                    new UserRentCarView(user).RentCar(split[1]);
                case "2+1":
                    showCar(new String[]{"2","1"});
                case "2+2":
                    showCar(new String[]{"2","2"});
                case "3":
                    showCar(new String[]{"3",split[1]});
                case "4":
                    showCar(new String[]{"4",split[1]});
                case "5":
                    showCar(new String[]{"5"});
                case "6":
                    showCar(new String[]{"6"});
        }
        }
    }
}

