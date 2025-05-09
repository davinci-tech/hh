package com.huawei.updatesdk.b.h;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.messagecenter.model.CommonUtil;

/* loaded from: classes7.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static String f10847a;
    private static Resources b;

    public static int c(Context context, String str) {
        return a(context, str, "string");
    }

    public static int b(Context context, String str) {
        return a(context, str, CommonUtil.LAYOUT);
    }

    private static String a(Context context) {
        if (f10847a == null) {
            f10847a = context.getPackageName();
        }
        return f10847a;
    }

    private static int a(Context context, String str, String str2) {
        if (b == null) {
            b = context.getResources();
        }
        return b.getIdentifier(str, str2, a(context));
    }

    public static int a(Context context, String str) {
        return a(context, str, "id");
    }
}
