package defpackage;

import android.content.Context;
import android.os.PowerManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jrs {
    private static PowerManager.WakeLock b;
    private static final Object d = new Object();

    public static void d(Context context) {
        synchronized (d) {
            if (b == null) {
                Object systemService = context.getSystemService("power");
                if (systemService instanceof PowerManager) {
                    LogUtil.a("WakeLockUtil", "acquireWakeLock");
                    PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(1, "WakeLockUtil");
                    b = newWakeLock;
                    newWakeLock.setReferenceCounted(false);
                    b.acquire();
                }
            }
            LogUtil.a("WakeLockUtil", "acquireWakeLock end");
        }
    }

    public static void b() {
        synchronized (d) {
            if (b != null) {
                LogUtil.a("WakeLockUtil", "release the wake lock now.");
                if (b.isHeld()) {
                    b.release();
                }
                b = null;
            }
            LogUtil.a("WakeLockUtil", "releaseWakeLock end");
        }
    }
}
