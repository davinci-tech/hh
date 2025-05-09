package com.huawei.maps.offlinedata.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f6531a = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");

    private static String a(String str, boolean z) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                sb.append(a(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static void a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, a(str2, z));
    }

    public static void a(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, a(str2, z), a(th));
    }

    public static void b(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, a(str2, z));
    }

    public static void b(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.i(str, a(str2, z), a(th));
    }

    public static void c(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, a(str2, z));
    }

    public static void c(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.w(str, a(str2, z), a(th));
    }

    public static void d(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, a(str2, z));
    }

    public static void d(String str, String str2, Throwable th, boolean z) {
        if (TextUtils.isEmpty(str2) && th == null) {
            return;
        }
        Log.e(str, a(str2, z), a(th));
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int i = 1;
        if (1 == length) {
            return String.valueOf('*');
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (f6531a.matcher(String.valueOf(charAt)).matches()) {
                if (i % 2 == 0) {
                    charAt = '*';
                }
                i++;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static Throwable a(Throwable th) {
        if (th == null) {
            return null;
        }
        a aVar = new a(th);
        aVar.setStackTrace(th.getStackTrace());
        aVar.a(b(th.getMessage()));
        Throwable cause = th.getCause();
        a aVar2 = aVar;
        while (cause != null) {
            a aVar3 = new a(cause);
            aVar3.setStackTrace(cause.getStackTrace());
            aVar3.a(b(cause.getMessage()));
            aVar2.a(aVar3);
            cause = cause.getCause();
            aVar2 = aVar3;
        }
        return aVar;
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i % 2 == 0) {
                charArray[i] = '*';
            }
        }
        return new String(charArray);
    }

    static class a extends Throwable {

        /* renamed from: a, reason: collision with root package name */
        private String f6532a;
        private Throwable b;
        private Throwable c;

        public a(Throwable th) {
            this.c = th;
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

        public void a(Throwable th) {
            this.b = th;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.f6532a;
        }

        public void a(String str) {
            this.f6532a = str;
        }

        @Override // java.lang.Throwable
        public String toString() {
            Throwable th = this.c;
            if (th == null) {
                return "";
            }
            String name = th.getClass().getName();
            if (this.f6532a == null) {
                return name;
            }
            String str = name + ": ";
            if (this.f6532a.startsWith(str)) {
                return this.f6532a;
            }
            return str + this.f6532a;
        }
    }
}
