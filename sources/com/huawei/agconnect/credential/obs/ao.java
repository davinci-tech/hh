package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.https.annotation.Result;

/* loaded from: classes2.dex */
public class ao {

    @Result("code")
    private int code;

    @Result("msg")
    private String msg;

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}
