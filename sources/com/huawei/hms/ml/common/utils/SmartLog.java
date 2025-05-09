package com.huawei.hms.ml.common.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class SmartLog {
    private static final int LEN_CONST = 2;
    private static final Pattern M_PATTERN = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");
    private static final char STAR = '*';

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

    public static void i(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, getLogMsg(str2, false));
    }

    public static void i(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(str, getLogMsg(str2, z), getNewThrowable(th));
    }

    public static void i(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(str, getLogMsg(str2, str3));
    }

    public static void i(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, getLogMsg(str2, z));
    }

    public static void i(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(str, getLogMsg(str2, false), getNewThrowable(th));
    }

    public static void i(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.i(str, getLogMsg(str2, str3), getNewThrowable(th));
    }

    public static void w(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.w(str, getLogMsg(str2, str3));
    }

    public static void w(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, getLogMsg(str2, z));
    }

    public static void w(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, getLogMsg(str2, false));
    }

    public static void w(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(str, getLogMsg(str2, z), getNewThrowable(th));
    }

    public static void w(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(str, getLogMsg(str2, false), getNewThrowable(th));
    }

    public static void w(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.w(str, getLogMsg(str2, str3), getNewThrowable(th));
    }

    public static void e(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(str, getLogMsg(str2, z), getNewThrowable(th));
    }

    public static void e(String str, String str2, String str3, Throwable th) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.e(str, getLogMsg(str2, str3), getNewThrowable(th));
    }

    public static void e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        Log.e(str, getLogMsg(str2, str3));
    }

    public static void e(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, getLogMsg(str2, z));
    }

    public static void e(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(str, getLogMsg(str2, false), getNewThrowable(th));
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, getLogMsg(str2, false));
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

    private static Throwable getNewThrowable(Throwable th) {
        if (th == null) {
            return null;
        }
        ThrowableWrapper throwableWrapper = new ThrowableWrapper(th);
        throwableWrapper.setStackTrace(th.getStackTrace());
        throwableWrapper.setThrowableMessage(modifyExceptionMessage(th.getMessage()));
        Throwable cause = th.getCause();
        ThrowableWrapper throwableWrapper2 = throwableWrapper;
        while (cause != null) {
            ThrowableWrapper throwableWrapper3 = new ThrowableWrapper(cause);
            throwableWrapper3.setStackTrace(cause.getStackTrace());
            throwableWrapper3.setThrowableMessage(modifyExceptionMessage(cause.getMessage()));
            throwableWrapper2.setCause(throwableWrapper3);
            cause = cause.getCause();
            throwableWrapper2 = throwableWrapper3;
        }
        return throwableWrapper;
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

    static class ThrowableWrapper extends Throwable {
        private static final long serialVersionUID = 7129050843360571878L;
        private Throwable ownerThrowableObj;
        private Throwable thisCauseObj;
        private String throwableMessage;

        public ThrowableWrapper(Throwable th) {
            this.ownerThrowableObj = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            Throwable th;
            synchronized (this) {
                th = this.thisCauseObj;
                if (th == this) {
                    th = null;
                }
            }
            return th;
        }

        public void setCause(Throwable th) {
            this.thisCauseObj = th;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.throwableMessage;
        }

        public void setThrowableMessage(String str) {
            this.throwableMessage = str;
        }

        @Override // java.lang.Throwable
        public String toString() {
            Throwable th = this.ownerThrowableObj;
            if (th == null) {
                return "";
            }
            String name = th.getClass().getName();
            if (this.throwableMessage == null) {
                return name;
            }
            String str = name + ": ";
            if (this.throwableMessage.startsWith(str)) {
                return this.throwableMessage;
            }
            return str + this.throwableMessage;
        }
    }
}
