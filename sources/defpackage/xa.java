package defpackage;

import android.util.Log;

/* loaded from: classes2.dex */
public class xa {
    private static xa c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f17740a;
    private boolean e;
    private boolean g;
    private boolean i;
    private boolean j;
    public static final boolean d = wu.c("debug.hisight.performance", false);
    private static boolean b = false;

    public xa() {
        this.i = false;
        this.f17740a = false;
        this.j = true;
        this.g = true;
        this.e = false;
        if (ww.c()) {
            this.i = false;
            this.f17740a = true;
            this.j = true;
            this.g = true;
            this.e = true;
        }
    }

    public static xa c() {
        xa xaVar;
        synchronized (xa.class) {
            if (c == null) {
                c = new xa();
                Log.d("IICLOG", "user type is beta : " + ww.c() + ",remote diagnosis is " + b);
            }
            xaVar = c;
        }
        return xaVar;
    }

    public void e(String str, String str2) {
        if (this.f17740a || d || b) {
            Log.i(str, e(str2));
        }
    }

    public void a(String str, String str2) {
        Log.i(str, e(str2));
    }

    public void b(String str, String str2) {
        if (this.j) {
            Log.w(str, e(str2));
        }
    }

    public void d(String str, String str2) {
        if (this.g) {
            Log.e(str, e(str2));
        }
    }

    private String e(String str) {
        if (str != null && str.length() > 1000) {
            str = str.substring(0, 1000);
            Log.w("IICLOG", "msg length bigger than 1000");
        }
        StringBuilder sb = new StringBuilder(str != null ? 50 + str.length() : 50);
        if (this.e) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 4) {
                sb.append("[ ");
                sb.append(stackTrace[4].getFileName());
                sb.append(": ");
                sb.append(stackTrace[4].getLineNumber());
                sb.append("]");
            }
        }
        sb.append(str);
        return sb.toString();
    }
}
