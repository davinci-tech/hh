package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ax {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f1759a;
    private static final List<String> b;

    public static ay a(Context context, String str, String str2, String str3) {
        if (str3 != null && b.contains(str3.toUpperCase(Locale.ENGLISH))) {
            return ba.a(context, new bb(str, str2, str3.toUpperCase(Locale.ENGLISH)));
        }
        Log.e("HiAnalyticsBridge", "RoutePolicy check failed: ".concat(String.valueOf(str3)));
        return null;
    }

    public static ay a(Context context, String str, String str2) {
        return ba.a(context, new bb(str, str2, ""));
    }

    static {
        String[] strArr = {"CN", "DE", "SG", "RU", "UNKNOWN"};
        f1759a = strArr;
        b = Collections.unmodifiableList(Arrays.asList(strArr));
    }
}
