package com.huawei.secure.android.common.util;

/* loaded from: classes6.dex */
public class SecurityCommonException extends Exception {
    private static final long c = 1;

    /* renamed from: a, reason: collision with root package name */
    private String f8653a;
    private String b;

    public SecurityCommonException() {
    }

    public String getMsgDes() {
        return this.b;
    }

    public String getRetCd() {
        return this.f8653a;
    }

    public SecurityCommonException(Throwable th) {
        super(th);
    }

    public SecurityCommonException(String str, Throwable th) {
        super(str, th);
    }

    public SecurityCommonException(String str) {
        super(str);
        this.b = str;
    }

    public SecurityCommonException(String str, String str2) {
        this.f8653a = str;
        this.b = str2;
    }
}
