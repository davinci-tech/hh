package defpackage;

import android.content.Context;
import android.text.TextUtils;
import health.compact.a.CommonUtils;

/* loaded from: classes3.dex */
public class bwc {

    /* renamed from: a, reason: collision with root package name */
    private static bwc f536a;
    private static final Object e = new Object();

    private bwc() {
    }

    public static bwc b() {
        bwc bwcVar;
        synchronized (bwc.class) {
            if (f536a == null) {
                f536a = new bwc();
            }
            bwcVar = f536a;
        }
        return bwcVar;
    }

    public void e(Context context, String str) {
        synchronized (e) {
            String sharedPreference = bqi.c(context).getSharedPreference(str + "num");
            int e2 = !nsn.c(sharedPreference) ? 0 : nsn.e(sharedPreference);
            bqi.c(context).setSharedPreference(str + "num", String.valueOf(e2 + 1), null);
            long currentTimeMillis = System.currentTimeMillis();
            bqi.c(context).setSharedPreference(str + "time", String.valueOf(currentTimeMillis), null);
        }
    }

    public gnb b(Context context, String str) {
        String sharedPreference = bqi.c(context).getSharedPreference(str + "num");
        int e2 = !nsn.c(sharedPreference) ? 0 : nsn.e(sharedPreference);
        String sharedPreference2 = bqi.c(context).getSharedPreference(str + "time");
        return new gnb(e2, TextUtils.isEmpty(sharedPreference2) ? 0L : CommonUtils.g(sharedPreference2));
    }

    public void b(Context context) {
        bqi.c(context).setSharedPreference("privacy_base_info_num", String.valueOf(0), null);
        bqi.c(context).setSharedPreference("privacy_base_info_time", String.valueOf(0), null);
        bqi.c(context).setSharedPreference("privacy_sport_data_num", String.valueOf(0), null);
        bqi.c(context).setSharedPreference("privacy_sport_data_time", String.valueOf(0), null);
        bqi.c(context).setSharedPreference("privacy_health_data_num", String.valueOf(0), null);
        bqi.c(context).setSharedPreference("privacy_health_data_time", String.valueOf(0), null);
    }
}
