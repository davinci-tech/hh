package defpackage;

import android.util.Log;

/* loaded from: classes3.dex */
public class ahi {
    public static final ahi e = new ahi();
    private boolean b;

    public void d(String str, String str2) {
        if (this.b) {
            ahh.c.w(str, str2);
        } else {
            Log.w(str, str2);
        }
    }

    public void a(String str, String str2) {
        if (this.b) {
            ahh.c.i(str, str2);
        } else {
            Log.i(str, str2);
        }
    }

    public void d(String str, String str2, Throwable th) {
        if (this.b) {
            ahh.c.e(str, str2, th);
        } else {
            Log.e(str, str2, th);
        }
    }

    public void c(String str, String str2) {
        if (this.b) {
            ahh.c.e(str, str2);
        } else {
            Log.e(str, str2);
        }
    }

    private ahi() {
        try {
            Class.forName("com.huawei.appgallery.log.LogAdaptor");
            this.b = true;
        } catch (ClassNotFoundException unused) {
            this.b = false;
        }
    }
}
