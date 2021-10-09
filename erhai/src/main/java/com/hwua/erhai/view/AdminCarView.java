package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.AdminQueryCarsRequest;
import com.hwua.erhai.vo.AdminQueryCarsResponse;
import com.hwua.erhai.vo.UserQueryCarsResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * 查询汽车
 */
public class AdminCarView extends Client {
    private User user = null;// 登录后的用户信息

    public AdminCarView(User user) {
        this.user = user;
    }

    public void showCar(String[] strings) {
        AdminQueryCarsRequest adminQueryCarsRequest=null;
      if (strings!=null&&strings.length==1){
          String optionType =strings[0];
          if ("5".equals(optionType)){
              adminQueryCarsRequest=new AdminQueryCarsRequest(Constant.ADMIN_QUERY_CARS,null,null,"3");
          }
      }else if (strings!=null&&strings.length==2){
          String optionType=strings[0];
          String orderType=strings[1];
          if ("2".equals(optionType)&&"1".equals(orderType)){
              adminQueryCarsRequest=new AdminQueryCarsRequest(Constant.ADMIN_QUERY_CARS,null,null,"1");
          }else if ("2".equals(optionType)&&"2".equals(orderType)){
              adminQueryCarsRequest=new AdminQueryCarsRequest(Constant.ADMIN_QUERY_CARS,null,null,"2");
          }
      }
      String request = JsonUtil.toJson(adminQueryCarsRequest);
      String response =request(request);
        AdminQueryCarsResponse adminQueryCarsResponse=JsonUtil.fromJson(response,AdminQueryCarsResponse.class);
        if (adminQueryCarsResponse==null||adminQueryCarsResponse.getCode()==400){
            System.out.println("查询失败");
        }else {
            System.out.println("========================================================");
            System.out.println("编号\t\t汽车名称\t\t备注\t\t品牌\t\t类型\t\t价格\t\t是否可租");
            List<Car> cars=adminQueryCarsResponse.getCars();
            for (Car car:cars){
                System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
                        car.getId(),car.getModel(),car.getComments(),car.getBrandName(),car.getCategoryName(),car.getRent(),
                        car.getStatus()==0?"是":"否");
            }
            System.out.println();
            System.out.println("输入0退出");
            System.out.println("输入1+汽车编号    查看指定汽车");
            System.out.println("输入2+1          按价格降序排序");
            System.out.println("输入2+2          按价格升序排序 ");
            System.out.println("输入3+类型编号    按类型搜索");
            System.out.println("输入4+品牌编号    按品牌搜索");
            System.out.println("输入5            查看全部汽车");
            System.out.println("输入6            添加汽车");
            System.out.println("输入7+汽车编号     修改汽车编号");
            System.out.println("输入8             查看汽车记录");
            String result = ScannerUtil.next();
            switch (result){
                case "0" :
                    System.exit(0);
                case "1+汽车编号" :
                    showCar(new String[]{"1","汽车编号"});
        }}
    }
}
