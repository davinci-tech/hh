package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.motiontrack.api.LocationApi;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;

/* loaded from: classes8.dex */
public class ema {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12089a = new Object();
    private static volatile LocationApi b;

    public static LocationApi c() {
        if (b == null) {
            synchronized (f12089a) {
                if (b == null) {
                    try {
                        b = (LocationApi) ReflectionUtils.b("com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteLocationHelper", BaseApplication.e().getClassLoader());
                    } catch (Exception e) {
                        LogUtil.e("RouteLocationHelperProxy", "create LocationApi fail, ex=", LogUtil.a(e));
                    }
                }
            }
        }
        return b;
    }
}
