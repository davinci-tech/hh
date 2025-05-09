package health.compact.a;

import android.os.Handler;
import android.os.Message;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxMonitorCenter$DfxMonitorHandler;
import com.huawei.haf.common.dfx.DfxMonitorMgr;
import com.huawei.haf.common.dfx.DfxMonitorTask;
import com.huawei.haf.common.os.MemoryUtils;

/* loaded from: classes.dex */
public final class DfxMonitorCenter {
    private static DfxMonitorMgr b;
    private static Class<? extends DfxMonitorMgr> c;

    private DfxMonitorCenter() {
    }

    public static void b(Class<? extends DfxMonitorMgr> cls) {
        if (c == null) {
            c = cls;
        }
    }

    public static void e() {
        Class<? extends DfxMonitorMgr> cls;
        if (b == null && (cls = c) != null) {
            Object b2 = ReflectionUtils.b(cls);
            DfxMonitorMgr dfxMonitorMgr = b2 instanceof DfxMonitorMgr ? (DfxMonitorMgr) b2 : null;
            if (dfxMonitorMgr != null) {
                dfxMonitorMgr.installMonitor(BaseApplication.e());
            }
            b = dfxMonitorMgr;
        }
    }

    public static void d() {
        DfxMonitorMgr dfxMonitorMgr = b;
        b = null;
        if (dfxMonitorMgr != null) {
            dfxMonitorMgr.uninstallMonitor(BaseApplication.e());
        }
    }

    public static void d(DfxMonitorTask dfxMonitorTask) {
        if (dfxMonitorTask != null) {
            Handler xj_ = DfxMonitorCenter$DfxMonitorHandler.xj_();
            xj_.removeMessages(100, dfxMonitorTask);
            xj_.sendMessageDelayed(Message.obtain(xj_, 100, dfxMonitorTask), Math.max(dfxMonitorTask.monitorDelayTime(), 1000L));
        }
    }

    public static void b(DfxMonitorTask dfxMonitorTask) {
        if (dfxMonitorTask != null) {
            DfxMonitorCenter$DfxMonitorHandler.xj_().removeMessages(100, dfxMonitorTask);
        }
    }

    public static void b(Runnable runnable) {
        b(runnable, 0L);
    }

    public static void b(Runnable runnable, long j) {
        if (runnable != null) {
            Handler xj_ = DfxMonitorCenter$DfxMonitorHandler.xj_();
            xj_.removeCallbacks(runnable);
            xj_.postDelayed(runnable, j);
        }
    }

    public static void b() {
        b(MemoryUtils.f2101a);
    }

    public static void a() {
        b(MemoryUtils.e, 3000L);
        b(MemoryUtils.f2101a, 5000L);
    }
}
