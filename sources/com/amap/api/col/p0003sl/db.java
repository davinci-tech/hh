package com.amap.api.col.p0003sl;

import android.content.Context;
import android.util.Log;

/* loaded from: classes2.dex */
public final class db {

    /* renamed from: a, reason: collision with root package name */
    static String f963a;

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        f963a = sb.toString();
    }

    public static void a() {
        c(f963a);
        c("当前使用的自定义地图样式文件和目前版本不匹配，请到官网(lbs.amap.com)更新新版样式文件");
        c(f963a);
    }

    public static void a(String str) {
        c(f963a);
        c(str);
        c(f963a);
    }

    public static void a(Context context, String str) {
        c(f963a);
        if (context != null) {
            b("key:" + hn.f(context));
        }
        c(str);
        c(f963a);
    }

    private static void b(String str) {
        if (str.length() < 78) {
            StringBuilder sb = new StringBuilder("|");
            sb.append(str);
            for (int i = 0; i < 78 - str.length(); i++) {
                sb.append(" ");
            }
            sb.append("|");
            c(sb.toString());
            return;
        }
        c("|" + str.substring(0, 78) + "|");
        b(str.substring(78));
    }

    private static void c(String str) {
        Log.i("authErrLog", str);
    }
}
