package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: classes7.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    public static Boolean f831a;

    public static final boolean a(Context context) {
        Boolean bool = f831a;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f831a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
