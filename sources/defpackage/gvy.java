package defpackage;

import android.content.Context;
import android.text.TextUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes4.dex */
public class gvy {
    public static void b(Context context) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "track_over_sea_status", "track_over_sea_status", new StorageParams());
    }

    public static void e(Context context) {
        if (TextUtils.isEmpty(SharedPreferenceManager.b(context, Integer.toString(20002), "track_over_sea_status"))) {
            return;
        }
        nrw.b(context, 0);
        SharedPreferenceManager.e(context, Integer.toString(20002), "track_over_sea_status", "", new StorageParams());
    }
}
