package com.bigdata.util;

/**
 * Desciption 接口结果封装工具类
 * Create By  li.bo
 * CreateTime 2018/1/24 15:43
 * UpdateTime 2018/1/24 15:43
 */
public class ResultBody<T> {
    
    private String state;
    
    private String msg;
    
    private T data;
        
    public ResultBody() {
        
    }
    
    public ResultBody(String state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public ResultBody(String state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBody [state=" + state + ", msg=" + msg + ", data=" + data + "]";
    }    
    
}
