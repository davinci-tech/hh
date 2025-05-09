package health.compact.a;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.lazyload.LazyLoadingMgr;

/* loaded from: classes.dex */
public final class ApplicationLazyLoad {
    private static Class<? extends LazyLoadingMgr> b;
    private static final Object c = new Object();
    private static volatile LazyLoadingMgr e;

    private ApplicationLazyLoad() {
    }

    public static void c(Class<? extends LazyLoadingMgr> cls) {
        if (b == null) {
            b = cls;
        }
    }

    public static void b() {
        Class<? extends LazyLoadingMgr> cls;
        synchronized (c) {
            if (e != null) {
                return;
            }
            if (e == null && (cls = b) != null) {
                LogUtil.a("ApplicationLazyLoad", " load with ", cls);
                Object b2 = ReflectionUtils.b(b);
                LazyLoadingMgr lazyLoadingMgr = b2 instanceof LazyLoadingMgr ? (LazyLoadingMgr) b2 : null;
                if (lazyLoadingMgr != null) {
                    e = lazyLoadingMgr;
                    lazyLoadingMgr.load(BaseApplication.e());
                }
            }
        }
    }

    public static boolean e() {
        return e != null;
    }
}
