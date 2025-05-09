package defpackage;

import android.content.Context;
import android.os.PowerManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class guu {

    /* renamed from: a, reason: collision with root package name */
    private static PowerManager.WakeLock f12949a;
    private static final Object c = new Object();

    public static void c(Context context) {
        synchronized (c) {
            if (f12949a == null) {
                Object systemService = context.getSystemService("power");
                if (systemService instanceof PowerManager) {
                    LogUtil.a("Track_WakeLockUtil", "acquireWakeLock");
                    PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(1, "SportWakeLock");
                    f12949a = newWakeLock;
                    newWakeLock.setReferenceCounted(false);
                    f12949a.acquire();
                }
            }
            LogUtil.a("Track_WakeLockUtil", "acquireWakeLock end");
        }
    }

    public static void d() {
        synchronized (c) {
            if (f12949a != null) {
                LogUtil.a("Track_WakeLockUtil", "release the wake lock now.");
                if (f12949a.isHeld()) {
                    f12949a.release();
                }
                f12949a = null;
            }
            LogUtil.a("Track_WakeLockUtil", "releaseWakeLock end");
        }
    }
}
