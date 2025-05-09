package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public abstract class a implements v {

    /* renamed from: a, reason: collision with root package name */
    public Method f812a;
    public byte[] b;
    public String c;
    public int d;
    public String e;
    public boolean f;

    public a(Method method, int i, String str, byte[] bArr, String str2, boolean z) {
        this.f812a = method;
        this.d = i;
        this.c = str;
        this.b = bArr;
        this.e = str2;
        this.f = z;
    }
}
