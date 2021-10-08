package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Record;

public class RentCarResponse extends Response {
    private Record record;

    public RentCarResponse(int code, String msg, Record record) {
        super(code, msg);
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
