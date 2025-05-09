package com.tencent.open.apireq;

/* loaded from: classes10.dex */
public class BaseResp {
    public static final int CODE_ERROR_PARAMS = -2000;
    public static final int CODE_NOT_LOGIN = -2001;
    public static final int CODE_PERMISSION_NOT_GRANTED = -1003;
    public static final int CODE_QQ_LOW_VERSION = -1001;
    public static final int CODE_QQ_NOT_INSTALLED = -1000;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_UNSUPPORTED_BRANCH = -1002;

    /* renamed from: a, reason: collision with root package name */
    private int f11333a = 0;
    private String b = "";

    public boolean isSuccess() {
        return this.f11333a == 0;
    }

    public int getCode() {
        return this.f11333a;
    }

    public void setCode(int i) {
        String str;
        this.f11333a = i;
        if (i == -2001) {
            str = "Not login.";
        } else if (i == -2000) {
            str = "The given params check failed.";
        } else if (i != 0) {
            switch (i) {
                case CODE_UNSUPPORTED_BRANCH /* -1002 */:
                    str = "The QQ branch (e.g. TIM) is not supported";
                    break;
                case CODE_QQ_LOW_VERSION /* -1001 */:
                    str = "QQ version is too low.";
                    break;
                case -1000:
                    str = "QQ is not installed.";
                    break;
                default:
                    str = a(i);
                    break;
            }
        } else {
            str = "";
        }
        setErrorMsg(str);
    }

    public String getErrorMsg() {
        return this.b;
    }

    public void setErrorMsg(String str) {
        this.b = str;
    }

    public String toString() {
        return "BaseResp{mCode=" + this.f11333a + ", mErrorMsg='" + this.b + "'}";
    }

    protected String a(int i) {
        return "Api call failed.";
    }
}
