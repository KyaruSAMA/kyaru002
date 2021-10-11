package com.hwua.erhai.view;

import com.hwua.erhai.client.Client;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.vo.QueryRecordsRequest;
import com.hwua.erhai.vo.QueryRecordsResponse;

import java.util.List;

/**
 * 查询租赁记录
 */
public class UserRecordView extends Client {
    private User user = null;// 登录后的用户信息

    public UserRecordView(User user) {
        this.user = user;
    }
    public void showRecord() {
        QueryRecordsRequest queryRecordsRequest=new QueryRecordsRequest(Constant.QUERY_RECORDS,String.valueOf(user.getId()),null,"1");
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
                        record.getId(),record.getUserId(),record.getCarId(),record.getStartDate(),record.getReturnDate(),record.getPayment());
            }
    }
}
}
