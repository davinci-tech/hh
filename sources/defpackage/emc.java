package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.motiontrack.api.RunningRouteApi;
import health.compact.a.ReflectionUtils;

/* loaded from: classes3.dex */
public class emc {
    private static volatile RunningRouteApi b;
    private static final Object d = new Object();

    public static RunningRouteApi d() {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = (RunningRouteApi) ReflectionUtils.b("com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl", BaseApplication.e().getClassLoader());
                }
            }
        }
        return b;
    }
}
