package defpackage;

import android.util.Log;

/* loaded from: classes2.dex */
public class afp {
    public static final afp d = new afp();

    public void a(String str, String str2) {
    }

    public void c(String str, String str2) {
        Log.w("AutoParcelable", str + ":" + str2);
    }

    public void e(String str, String str2) {
        Log.e("AutoParcelable", str + ":" + str2);
    }

    public void c(String str, String str2, Throwable th) {
        Log.e("AutoParcelable", str + ":" + str2, th);
    }
}
