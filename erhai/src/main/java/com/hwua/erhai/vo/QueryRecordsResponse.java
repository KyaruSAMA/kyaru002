package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Record;

import java.util.List;

public class QueryRecordsResponse extends Response{
    private List<Record>records;

    public QueryRecordsResponse(int code, String msg,List<Record> records) {
        super(code, msg);
        this.records = records;
    }
    public QueryRecordsResponse(){

    }
    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
