package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.util.ScannerUtil;
import com.hwua.erhai.vo.AdminQueryCarsRequest;
import com.hwua.erhai.vo.QueryRecordsRequest;
import com.hwua.erhai.vo.QueryRecordsResponse;

import java.util.List;

/**
 * 查询租赁记录
 */
public class AdminRecordView extends Client {
    private User user = null;// 登录后的用户信息

    public AdminRecordView(User user) {
        this.user = user;
    }
    public void showRecord(String[] strings) {

        QueryRecordsRequest queryRecordsRequest=null;
        if (strings!=null&&strings.length==1){
            String optionType =strings[0];
            if ("6".equals(optionType)){
                queryRecordsRequest=new QueryRecordsRequest(Constant.QUERY_RECORDS,null,null,"3");
            }
        }else if (strings!=null&&strings.length==2){
            String optionType=strings[0];
            String orderType=strings[1];
            if ("7".equals(optionType)){
                queryRecordsRequest=new QueryRecordsRequest(Constant.QUERY_RECORDS,orderType,null,"1");
            }
            else if ("8".equals(optionType)){
                queryRecordsRequest=new QueryRecordsRequest(Constant.QUERY_RECORDS,orderType,null,"2");
            }
        }

        String request = JsonUtil.toJson(queryRecordsRequest);

        String response= request(request);

        QueryRecordsResponse queryRecordsResponse=JsonUtil.fromJson(response,QueryRecordsResponse.class);

        if (queryRecordsResponse==null||queryRecordsResponse.getCode()==400){
            System.out.println("查询失败");
        }else {
            System.out.println("===============================");

            System.out.println("编号\t\t\t用户id\t\t\t汽车id\t\t\t开始时间\t\t\t结束时间\t\t\t租金");
            List<Record> records= queryRecordsResponse.getRecords();
            for (Record record:records){
                System.out.printf("%d\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s\n",
                        record.getId(),record.getUserId(),record.getCarId(),record.getStartDate(),record.getReturnDate(),record.getPayment());}
            System.out.println("==========================================================");
            System.out.println("输入0        退出");
            System.out.println("输入5        查看全部汽车");
            System.out.println("输入6        查看全部租赁记录");
            System.out.println("输入7+用户编号 查询指定用户租赁记录");
            System.out.println("输入8+汽车    查询指定汽车租赁记录");


            String result = ScannerUtil.next();
            String[] split;
            split =result.split("\\+");

            switch (split[0]){
                case "0" :
                    System.exit(0);
                case "5":
                    new AdminCarView(user).showCar(new String[]{"5"});

                case "6":
                    showRecord(new String[]{"6"});
                case "7":
                    showRecord(new String[]{"7",split[1]});
                case "8":
                    showRecord(new String[]{"8",split[1]});

            }
        }
    }


}

