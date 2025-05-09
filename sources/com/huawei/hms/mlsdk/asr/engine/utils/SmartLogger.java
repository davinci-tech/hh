package com.huawei.hms.mlsdk.asr.engine.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class SmartLogger {
    private static final int LEN_CONST = 2;
    private static final Pattern M_PATTERN = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");
    private static final char STAR = '*';
    public static final String TAG = "MLASR_";

    static class a extends Throwable {

        /* renamed from: a, reason: collision with root package name */
        private Throwable f5087a;
        private Throwable b;
        private String c;

        public a(Throwable th) {
            this.f5087a = th;
        }

        public void a(Throwable th) {
            this.b = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            Throwable th;
            synchronized (this) {
                th = this.b;
                if (th == this) {
                    th = null;
                }
            }
            return th;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.c;
        }

        @Override // java.lang.Throwable
        public String toString() {
            Throwable th = this.f5087a;
            if (th == null) {
                return "";
            }
            String name = th.getClass().getName();
            if (this.c == null) {
                return name;
            }
            String a2 = com.huawei.hms.mlsdk.asr.o.a.a(name, ": ");
            if (this.c.startsWith(a2)) {
                return this.c;
            }
            StringBuilder a3 = com.huawei.hms.mlsdk.asr.o.a.a(a2);
            a3.append(this.c);
            return a3.toString();
        }

        public void a(String str) {
            this.c = str;
        }
    }

    public static void d(String str, String str2) {
    }

    public static void d(String str, String str2, String str3) {
    }

    public static void d(String str, String str2, String str3, Throwable th) {
    }

    public static void d(String str, String str2, Throwable th) {
    }

    public static void d(String str, String str2, Throwable th, boolean z) {
    }

    public static void d(String str, String str2, boolean z) {
    }

    public static void e(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, z));
    }

    private static String formatLogWithStar(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int i = 1;
        if (1 == length) {
            return String.valueOf(STAR);
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (M_PATTERN.matcher(String.valueOf(charAt)).matches()) {
                if (i % 2 == 0) {
                    charAt = '*';
                }
                i++;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String getLogMsg(String str, boolean z) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                sb.append(formatLogWithStar(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private static Throwable getNewThrowable(Throwable th) {
        if (th == null) {
            return null;
        }
        a aVar = new a(th);
        aVar.setStackTrace(th.getStackTrace());
        aVar.a(modifyExceptionMessage(th.getMessage()));
        Throwable cause = th.getCause();
        a aVar2 = aVar;
        while (cause != null) {
            a aVar3 = new a(cause);
            aVar3.setStackTrace(cause.getStackTrace());
            aVar3.a(modifyExceptionMessage(cause.getMessage()));
            aVar2.a(aVar3);
            cause = cause.getCause();
            aVar2 = aVar3;
        }
        return aVar;
    }

    public static void i(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, z));
    }

    private static String modifyExceptionMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i % 2 == 0) {
                charArray[i] = STAR;
            }
        }
        return new String(charArray);
    }

    public static void w(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, z));
    }

    public static void e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.e(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, str3));
    }

    public static void i(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, str3));
    }

    public static void w(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.w(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, str3));
    }

    public static void e(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.e(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, str3), getNewThrowable(th));
    }

    public static void i(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, str3), getNewThrowable(th));
    }

    public static void w(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.w(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, str3), getNewThrowable(th));
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, false));
    }

    public static void i(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, false));
    }

    public static void w(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, false));
    }

    public static void e(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, z), getNewThrowable(th));
    }

    public static void i(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, z), getNewThrowable(th));
    }

    public static void w(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, z), getNewThrowable(th));
    }

    public static void e(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, false), getNewThrowable(th));
    }

    public static void i(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, false), getNewThrowable(th));
    }

    public static void w(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(com.huawei.hms.mlsdk.asr.o.a.a(TAG, str), getLogMsg(str2, false), getNewThrowable(th));
    }

    private static String getLogMsg(String str, String str2) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(formatLogWithStar(str2));
        }
        return sb.toString();
    }
}
