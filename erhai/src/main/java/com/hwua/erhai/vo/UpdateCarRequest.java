package com.hwua.erhai.vo;

public class UpdateCarRequest extends Request {
    private long id;
    private int name;
    private int type;

    public UpdateCarRequest(String requestType, long id, int name, int type) {
        super(requestType);
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public UpdateCarRequest(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
