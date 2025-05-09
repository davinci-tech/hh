package defpackage;

import android.content.Context;
import com.huawei.wear.healthadapter.HealthAdapter;

/* loaded from: classes7.dex */
public class ssz {
    private static Context d;
    private static HealthAdapter e;

    public static Context e() {
        return d;
    }

    public static void d(Context context) {
        d = context;
    }

    public static HealthAdapter d() {
        return e;
    }

    public static void e(HealthAdapter healthAdapter) {
        e = healthAdapter;
    }
}
