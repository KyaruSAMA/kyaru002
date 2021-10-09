package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Category;

import java.util.List;

public class QueryCategoriesResponese extends Response{
    private List<Category>categories;

    public QueryCategoriesResponese(int code, String msg, List<Category> categories) {
        super(code, msg);
        this.categories = categories;
    }
    public QueryCategoriesResponese(){

    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
