package com.huawei.hms.framework.network.download;

/* loaded from: classes9.dex */
public class Result {
    private int code;
    private String info;

    public static class ErrorCode {
        public static final int FAILED = 1;
        public static final int SUCCESS = 0;
    }

    public Result() {
        this.code = 0;
        this.info = "success";
    }

    public Result(int i, String str) {
        this.code = 0;
        this.info = "success";
        if (i == com.huawei.hms.network.file.api.Result.SUCCESS) {
            i = 0;
            str = "success";
        }
        this.code = i;
        this.info = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }
}
