package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Brand;

import java.util.List;

public class QueryBrandsResponse extends  Response{
    List<Brand>brands;

    public QueryBrandsResponse(int code, String msg, List<Brand> brands) {
        super(code, msg);
        this.brands = brands;
    }
    public QueryBrandsResponse(){

    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
