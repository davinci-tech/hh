package com.huawei.health.h5pro.jsbridge;

import com.huawei.openalliance.ad.constant.IntentFailError;

/* loaded from: classes3.dex */
public enum ErrorEnum {
    NO_PERMISSION(1001, IntentFailError.NO_PERMISSION),
    NO_PERMISSION_SPECIAL(10011, "Not Allowed"),
    CANCELED(1002, "cancel"),
    REQ_PARAM_EMPTY(1003, "request param empty"),
    REQ_FAST_REPEAT(1004, "request repeat fast"),
    DEFAULT_ERROR(-1, "default error");

    public int code;
    public String msg;

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    ErrorEnum(int i, String str) {
        this.code = i;
        this.msg = str;
    }
}
