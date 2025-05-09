package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.util.Arrays;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class e8 extends Throwable {
    public static final int d = 8;
    public static final int e = 20;
    public static final long f = 7129050843360571879L;

    /* renamed from: a, reason: collision with root package name */
    public String f5232a;
    public Throwable b;
    public Throwable c;

    @Override // java.lang.Throwable
    public String toString() {
        Throwable th = this.c;
        if (th == null) {
            return "";
        }
        String name = th.getClass().getName();
        if (this.f5232a == null) {
            return name;
        }
        String str = name + ": ";
        if (this.f5232a.startsWith(str)) {
            return this.f5232a;
        }
        return str + this.f5232a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f5232a;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        Throwable th = this.b;
        if (th == this) {
            return null;
        }
        return th;
    }

    public void b(String str) {
        this.f5232a = str;
    }

    public String a(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i % 2 != 0) {
                charArray[i] = '*';
            }
        }
        return new String(charArray);
    }

    private void b(Throwable th) {
        this.b = th;
    }

    public static Throwable a(Throwable th) {
        if (th == null) {
            return null;
        }
        e8 e8Var = new e8(th);
        Throwable cause = th.getCause();
        e8 e8Var2 = e8Var;
        while (cause != null) {
            e8 e8Var3 = new e8(cause);
            e8Var2.b(e8Var3);
            cause = cause.getCause();
            e8Var2 = e8Var3;
        }
        return e8Var;
    }

    public e8(Throwable th) {
        this.c = th;
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i = ((th instanceof IOException) || (th instanceof JSONException)) ? 8 : 20;
        if (stackTrace.length > i) {
            setStackTrace((StackTraceElement[]) Arrays.copyOf(stackTrace, i));
        } else {
            setStackTrace(stackTrace);
        }
        b(a(th.getMessage()));
    }
}
