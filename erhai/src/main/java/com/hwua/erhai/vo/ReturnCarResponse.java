package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Record;

public class ReturnCarResponse extends Response{
    private Record record;

    public ReturnCarResponse(int code, String msg, Record record) {
        super(code, msg);
        this.record = record;
    }
    public ReturnCarResponse(){

    }
    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
