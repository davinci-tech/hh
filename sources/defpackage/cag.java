package defpackage;

import android.os.Looper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.manager.powerkit.PowerKitManager;
import health.compact.a.EnvironmentInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class cag {
    private long b = 0;
    private final String e;

    public cag(int i) {
        StringBuilder sb = new StringBuilder("user-sport-");
        if (i == 258) {
            sb.append("run");
        } else if (i == 259) {
            sb.append("bike");
        } else if (i == 264) {
            sb.append("treadmill");
        } else {
            sb.append("other");
        }
        this.e = sb.toString();
    }

    public void a() {
        if (EnvironmentInfo.r() && System.currentTimeMillis() - this.b >= 540000) {
            this.b = System.currentTimeMillis();
            ReleaseLogUtil.e("Track_SportPowerKitHelper", "apply power kit now");
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().c("Track_SportPowerKitHelper", new Runnable() { // from class: cah
                    @Override // java.lang.Runnable
                    public final void run() {
                        cag.this.d();
                    }
                });
            } else {
                PowerKitManager.e().e("sport-module", 65535, this.e);
                PowerKitManager.e().d("sport-module", 65535, 600000L, this.e);
            }
        }
    }

    /* synthetic */ void d() {
        PowerKitManager.e().e("sport-module", 65535, this.e);
        PowerKitManager.e().d("sport-module", 65535, 600000L, this.e);
    }

    public void e() {
        if (EnvironmentInfo.r()) {
            ReleaseLogUtil.e("Track_SportPowerKitHelper", "unapply power kit now");
            this.b = 0L;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                ThreadPoolManager.d().c("Track_SportPowerKitHelper", new Runnable() { // from class: cac
                    @Override // java.lang.Runnable
                    public final void run() {
                        cag.this.b();
                    }
                });
            } else {
                PowerKitManager.e().e("sport-module", 65535, this.e);
            }
        }
    }

    /* synthetic */ void b() {
        PowerKitManager.e().e("sport-module", 65535, this.e);
    }
}
