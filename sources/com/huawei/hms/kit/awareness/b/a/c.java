package com.huawei.hms.kit.awareness.b.a;

import android.util.Log;
import java.text.MessageFormat;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4826a = "LogsUtil";
    private static final String b = "HMS-Awa";

    public static void a(String str, String str2, Object... objArr) {
    }

    public static void d(String str, String str2, Object... objArr) {
        Log.e(b, a(str, a(str2, objArr)));
    }

    public static void c(String str, String str2, Object... objArr) {
        Log.w(b, a(str, a(str2, objArr)));
    }

    public static void b(String str, String str2, Object... objArr) {
        Log.i(b, a(str, a(str2, objArr)));
    }

    private static String a(String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            try {
                return MessageFormat.format(str, objArr);
            } catch (IllegalArgumentException e) {
                d(f4826a, e.getMessage(), new Object[0]);
            }
        }
        return str;
    }

    private static String a(String str, String str2) {
        return "[" + str + "] " + str2;
    }

    private c() {
    }
}
