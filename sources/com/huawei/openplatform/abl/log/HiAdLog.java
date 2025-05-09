package com.huawei.openplatform.abl.log;

import android.content.Context;
import defpackage.lsq;

/* loaded from: classes5.dex */
public abstract class HiAdLog {

    /* renamed from: a, reason: collision with root package name */
    private static b f8219a;

    public static void w(String str, String str2, Object... objArr) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.d(str, str2, objArr);
        }
    }

    public static void w(String str, String str2) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.d(str, str2);
        }
    }

    public static void printSafeStackTrace(int i, Throwable th) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.a(i, th);
        }
    }

    public static void printSafeStackTrace(int i, String str, Throwable th) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.a(i, str, th);
        }
    }

    public static void printSafeExceptionMessage(int i, String str, String str2, Throwable th) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.a(i, str, str2, th);
        }
    }

    public static boolean isWarnEnable() {
        b bVar = f8219a;
        if (bVar != null) {
            return bVar.d();
        }
        return false;
    }

    public static boolean isInfoEnable() {
        b bVar = f8219a;
        if (bVar != null) {
            return bVar.c();
        }
        return false;
    }

    public static boolean isErrorEnable() {
        b bVar = f8219a;
        if (bVar != null) {
            return bVar.b();
        }
        return false;
    }

    public static boolean isDebugEnable() {
        b bVar = f8219a;
        if (bVar != null) {
            return bVar.a();
        }
        return false;
    }

    public static void init(Context context, lsq lsqVar) {
        if (lsqVar == null) {
            return;
        }
        if (lsqVar.h()) {
            f8219a = new l(context).b(lsqVar.i());
        } else {
            f8219a = new c(context);
        }
        f8219a.a(lsqVar);
    }

    public static void i(String str, String str2, Object... objArr) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.c(str, str2, objArr);
        }
    }

    public static void i(String str, String str2) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.c(str, str2);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.b(str, str2, objArr);
        }
    }

    public static void e(String str, String str2) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.b(str, str2);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.a(str, str2, objArr);
        }
    }

    public static void d(String str, String str2) {
        b bVar = f8219a;
        if (bVar != null) {
            bVar.a(str, str2);
        }
    }
}
