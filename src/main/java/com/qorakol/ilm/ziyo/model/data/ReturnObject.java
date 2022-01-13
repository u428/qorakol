package com.qorakol.ilm.ziyo.model.data;


public class ReturnObject {

    public int code;
    public String message;
    public Object data;

    public ReturnObject() {
    }

    public ReturnObject(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ReturnObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
