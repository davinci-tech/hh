package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public class dog {

    /* renamed from: a, reason: collision with root package name */
    private static dog f11752a;
    private Context b = BaseApplication.e();
    private volatile boolean e;

    private dog() {
    }

    public static dog e() {
        dog dogVar;
        synchronized (dog.class) {
            if (f11752a == null) {
                f11752a = new dog();
            }
            dogVar = f11752a;
        }
        return dogVar;
    }

    public boolean b() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public void a() {
        long j;
        Context context = this.b;
        if (context == null) {
            return;
        }
        if ("0".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "health_product_recommend"))) {
            b(false);
            return;
        }
        String b = SharedPreferenceManager.b(this.b, Integer.toString(11000), "last_closed_time");
        if (b == null || b.isEmpty()) {
            LogUtil.b("ShowStatusUtil", "get SharedPreference failed");
            b(true);
            return;
        }
        try {
            j = Long.parseLong(b);
        } catch (NumberFormatException e) {
            LogUtil.b("ShowStatusUtil", "exception is", e.getMessage());
            j = 0;
        }
        LogUtil.c("ShowStatusUtil", "lastClosedTime: " + j);
        if (d(j)) {
            b(false);
        } else {
            b(true);
        }
    }

    public void c() {
        int parseInt;
        if (SharedPreferenceManager.e(this.b, Integer.toString(11000), "last_closed_time", Long.toString(System.currentTimeMillis() / 1000), new StorageParams()) != 0) {
            LogUtil.b("ShowStatusUtil", "set lastClosedTime SharedPreference failed");
            return;
        }
        String b = SharedPreferenceManager.b(this.b, Integer.toString(11000), "total_closed_count");
        int i = 1;
        if (b == null || b.isEmpty()) {
            LogUtil.b("ShowStatusUtil", "totalClosedCountPreference is invalid");
        } else {
            try {
                parseInt = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                LogUtil.b("ShowStatusUtil", "set count exception", e.getMessage());
            }
            if (parseInt >= 0 && parseInt <= 3) {
                if (parseInt != 3) {
                    i = 1 + parseInt;
                }
                i = 0;
            }
        }
        if (SharedPreferenceManager.e(this.b, Integer.toString(11000), "total_closed_count", Integer.toString(i), new StorageParams()) != 0) {
            LogUtil.b("ShowStatusUtil", "set totalClosedCount SharedPreference failed");
        }
    }

    private boolean d(long j) {
        long j2 = ((j / 60) / 60) / 24;
        long currentTimeMillis = (((System.currentTimeMillis() / 1000) / 60) / 60) / 24;
        LogUtil.a("ShowStatusUtil", "cachedDays: " + j2 + ", currentDays: " + currentTimeMillis);
        return j2 - currentTimeMillis == 0;
    }

    public static void d() {
        synchronized (dog.class) {
            if (f11752a != null) {
                f11752a = null;
            }
        }
    }
}
