package com.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用於封裝後端返回前端的資料
 */
public class ResultInfo implements Serializable {
    private boolean flag;//後端返回結果正常為true，異常為false
    private Object data;//正常的話可以返回一個物件
    private String msg;//異常時的錯誤訊息
    private String redirect;//讓前端收到訊息後，由前端跳轉

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
