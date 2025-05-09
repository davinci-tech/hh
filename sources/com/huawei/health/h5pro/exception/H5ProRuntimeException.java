package com.huawei.health.h5pro.exception;

/* loaded from: classes8.dex */
public class H5ProRuntimeException extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    public int f2384a;

    public H5ProRuntimeException(String str) {
        super(str);
        this.f2384a = -1;
    }

    public H5ProRuntimeException(int i, String str) {
        super(str);
        this.f2384a = i;
    }
}
