package com.huawei.hms.support.hwid.common.d;

import android.content.Context;

/* loaded from: classes9.dex */
public class a extends b {

    /* renamed from: a, reason: collision with root package name */
    private static a f6000a;

    public a(Context context, String str) {
        super(context, str);
    }

    public static a a(Context context) {
        if (f6000a == null) {
            synchronized (a.class) {
                if (f6000a == null) {
                    f6000a = new a(context, "SiteDomain");
                }
            }
        }
        return f6000a;
    }
}
