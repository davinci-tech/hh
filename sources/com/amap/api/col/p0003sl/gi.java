package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;

/* loaded from: classes2.dex */
public final class gi {

    /* renamed from: a, reason: collision with root package name */
    static ki f1068a;

    public static void a(Context context, String str, long j, boolean z) {
        try {
            String a2 = a(str, j, z);
            if (a2 != null && a2.length() > 0) {
                if (f1068a == null) {
                    f1068a = new ki(context, "sea", "9.2.0", "O002");
                }
                f1068a.a(a2);
                kj.a(f1068a, context);
            }
        } catch (Throwable th) {
            fd.a(th, "StatisticsUtil", "recordResponseAction");
        }
    }

    private static String a(String str, long j, boolean z) {
        try {
            return "{\"RequestPath\":\"" + str + "\",\"ResponseTime\":" + j + ",\"Success\":" + z + "}";
        } catch (Throwable th) {
            fd.a(th, "StatisticsUtil", "generateNetWorkResponseStatisticsEntity");
            return null;
        }
    }

    public static void a(String str, String str2, AMapException aMapException) {
        if (str != null) {
            String errorType = aMapException.getErrorType();
            String a2 = a(aMapException);
            if (a2 == null || a2.length() <= 0) {
                return;
            }
            iv.a(fc.a(true), str, errorType, str2, a2);
        }
    }

    private static String a(AMapException aMapException) {
        if (aMapException == null) {
            return null;
        }
        if (aMapException.getErrorLevel() == 0) {
            int errorCode = aMapException.getErrorCode();
            if (errorCode == 0) {
                return "4";
            }
            int pow = (int) Math.pow(10.0d, Math.floor(Math.log10(errorCode)));
            return String.valueOf((errorCode % pow) + (pow * 4));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(aMapException.getErrorCode());
        return sb.toString();
    }

    public static void a(Context context, String str, boolean z) {
        try {
            String a2 = a(str, z);
            if (a2 != null && a2.length() > 0) {
                ki kiVar = new ki(context, "sea", "9.2.0", "O006");
                kiVar.a(a2);
                kj.a(kiVar, context);
            }
        } catch (Throwable th) {
            fd.a(th, "StatisticsUtil", "recordResponseAction");
        }
    }

    private static String a(String str, boolean z) {
        String str2;
        try {
            int indexOf = str.indexOf("?");
            int length = str.length();
            str2 = "";
            if (indexOf > 0) {
                String substring = str.substring(0, indexOf);
                int i = indexOf + 1;
                str2 = i < length ? str.substring(i) : "";
                str = substring;
            }
            return "{\"RequestPath\":\"" + str + "\",\"RequestParm\":\"" + str2 + "\",\"IsCacheRequest\":" + z + "}";
        } catch (Throwable th) {
            fd.a(th, "StatisticsUtil", "generateNetWorkResponseStatisticsEntity");
            return null;
        }
    }
}
