package com.huawei.hms.push;

import com.huawei.hms.aaid.constant.ErrorEnum;

/* loaded from: classes4.dex */
public class BaseException extends Exception {

    /* renamed from: a, reason: collision with root package name */
    private final int f5662a;
    private final ErrorEnum b;

    public BaseException(int i) {
        ErrorEnum fromCode = ErrorEnum.fromCode(i);
        this.b = fromCode;
        this.f5662a = fromCode.getExternalCode();
    }

    public int getErrorCode() {
        return this.f5662a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.b.getMessage();
    }
}
