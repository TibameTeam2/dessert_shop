package com.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用於封裝後端返回前端的資料
 */
public class ResultInfo implements Serializable {
    private boolean flag;//後端返回結果正常為true，異常為false
    private Object data;//正常的話返回一個物件
    private String errorMsg;//異常時的錯誤訊息

    public ResultInfo() {
    }
    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
