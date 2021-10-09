package com.hwua.erhai.vo;

public class QueryCategoriesRequest extends Request{
    private long caterotyId;

    public QueryCategoriesRequest(String requestType, long caterotyId) {
        super(requestType);
        this.caterotyId = caterotyId;
    }
    public QueryCategoriesRequest(){

    }
    public long getCaterotyId() {
        return caterotyId;
    }

    public void setCaterotyId(long caterotyId) {
        this.caterotyId = caterotyId;
    }
}
