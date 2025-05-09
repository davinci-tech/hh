package defpackage;

import android.content.Context;
import android.os.Looper;
import com.alipay.sdk.m.b.b;

/* loaded from: classes7.dex */
public class ih {
    public static boolean b = false;
    public static b e;

    public static String d(Context context) {
        synchronized (ih.class) {
            if (context == null) {
                throw new RuntimeException("Context is null");
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new IllegalStateException("Cannot be called from the main thread");
            }
            e(context);
            b bVar = e;
            if (bVar != null) {
                try {
                    return bVar.a(context);
                } catch (Exception unused) {
                }
            }
            return null;
        }
    }

    public static void e(Context context) {
        if (e != null || b) {
            return;
        }
        synchronized (ih.class) {
            if (e == null && !b) {
                e = im.d(context);
                b = true;
            }
        }
    }
}
