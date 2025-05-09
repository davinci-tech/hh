package defpackage;

import android.content.Context;

/* loaded from: classes5.dex */
public class krl extends krp {
    private static krl e;

    public krl(Context context, String str) {
        super(context, str);
    }

    public static krl e(Context context) {
        krl krlVar;
        synchronized (krl.class) {
            if (e == null) {
                e = new krl(context, "HwIDAuthInfo");
            }
            krlVar = e;
        }
        return krlVar;
    }
}
