package com.hwua.erhai.vo;

public class QueryBrandsRequest extends Request{
    private long brandsId;

    public QueryBrandsRequest(String requestType, long brandsId) {
        super(requestType);
        this.brandsId = brandsId;
    }
    public QueryBrandsRequest(){

    }

    public long getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(long brandsId) {
        this.brandsId = brandsId;
    }
}
