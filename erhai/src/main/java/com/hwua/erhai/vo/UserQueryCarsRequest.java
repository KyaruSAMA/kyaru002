package com.hwua.erhai.vo;

public class UserQueryCarsRequest extends Request{
    private String id;
    public String name;
    private  String type;//type=1是价格降序，type=2是价格升序，type=3查询全部

    public UserQueryCarsRequest(String requestType,String id, String name, String type) {
        super(requestType);
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public UserQueryCarsRequest(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
