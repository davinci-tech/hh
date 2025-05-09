package com.huawei.hianalytics;

import android.content.Context;

/* loaded from: classes4.dex */
public class h1 {

    /* renamed from: a, reason: collision with root package name */
    public static h1 f3874a;
    public static final byte[] b = new byte[0];

    /* renamed from: a, reason: collision with other field name */
    public Context f38a;

    /* renamed from: a, reason: collision with other field name */
    public final byte[] f39a = new byte[0];

    public final Context a(Context context) {
        return context.createDeviceProtectedStorageContext();
    }

    public h1(Context context) {
        this.f38a = a(context);
    }
}
