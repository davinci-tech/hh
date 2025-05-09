package defpackage;

import android.content.Context;
import defpackage.hx;

/* loaded from: classes7.dex */
public class ka {
    public static String b(Context context) {
        if (hw.b) {
            return hx.a.e.a(context.getApplicationContext(), "OUID");
        }
        throw new RuntimeException("SDK Need Init First!");
    }

    public static void d(Context context) {
        hw.e = hx.a.e.e(context.getApplicationContext());
        hw.b = true;
    }

    public static boolean c() {
        if (hw.b) {
            return hw.e;
        }
        throw new RuntimeException("SDK Need Init First!");
    }
}
