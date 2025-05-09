package defpackage;

import android.content.Context;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes6.dex */
public class nro {
    public static void e(Context context, int i) {
        SharedPreferenceManager.e(context, String.valueOf(10000), "HomeCardRefreshIndex", String.valueOf(i), new StorageParams());
    }
}
