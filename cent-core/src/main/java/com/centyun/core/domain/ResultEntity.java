package com.centyun.core.domain;

import java.io.Serializable;

/**
 * 返回值
 * @author yinww
 *
 */

public class ResultEntity implements Serializable{
    private static final long serialVersionUID = 4295416227189160052L;
    
    private int status;
    private Object data;
    private String msg;
    
    public ResultEntity() {
    }
    
    public ResultEntity(int status) {
        this.status = status;
    }
    
    public ResultEntity(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
