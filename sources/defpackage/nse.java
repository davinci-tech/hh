package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReflectionUtils;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class nse {

    /* renamed from: a, reason: collision with root package name */
    private static final Method f15466a;
    private static final Class<?> b;
    private static final Class<?> c;
    private static final Method d;
    private static final Class<?> e;
    private static final Method h;
    private static final Method j;

    static {
        Class<?> b2 = ReflectionUtils.b("android.util.IMonitor");
        c = b2;
        Class<?> b3 = ReflectionUtils.b("android.util.IMonitorEventStreamImpl");
        b = b3;
        e = ReflectionUtils.b("android.util.IMonitorKeys");
        j = b2 != null ? ReflectionUtils.d(b2, "openEventStream", Integer.TYPE) : null;
        d = b3 != null ? ReflectionUtils.d(b3, "close", new Class[0]) : null;
        f15466a = b3 != null ? ReflectionUtils.d(b3, "commit", new Class[0]) : null;
        h = b3 != null ? ReflectionUtils.d(b3, "setParam", Short.TYPE, Integer.TYPE) : null;
    }

    public static Object d(int i) {
        Method method = j;
        if (method == null) {
            LogUtil.b("ReflectionIMonitor", "openEventStream failed, cause METHOD_OPEN_EVENT_STREAM is null!");
            return null;
        }
        try {
            return ReflectionUtils.c(method, (Object) null, Integer.valueOf(i));
        } catch (UnsupportedOperationException e2) {
            LogUtil.b("ReflectionIMonitor", "openEventStream get exception:", e2.getMessage());
            return null;
        }
    }

    public static void d(Object obj) {
        if (obj == null) {
            LogUtil.b("ReflectionIMonitor", "closeEventStream failed, cause stream is null!");
            return;
        }
        Method method = d;
        if (method == null) {
            LogUtil.b("ReflectionIMonitor", "closeEventStream failed, cause METHOD_CLOSE is null!");
            return;
        }
        try {
            ReflectionUtils.c(method, obj, new Object[0]);
        } catch (UnsupportedOperationException e2) {
            LogUtil.b("ReflectionIMonitor", "closeEventStream get exception: ", e2.getMessage());
        }
    }

    public static boolean e(Object obj) {
        if (obj == null) {
            LogUtil.b("ReflectionIMonitor", "sendEvent failed, cause stream is null!");
            return false;
        }
        Method method = f15466a;
        if (method == null) {
            LogUtil.b("ReflectionIMonitor", "closeEventStream failed, cause METHOD_COMMIT is null!");
            return false;
        }
        try {
            return ((Boolean) ReflectionUtils.c(method, obj, new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e2) {
            LogUtil.b("ReflectionIMonitor", "sendEvent get exception:", e2.getMessage());
            return false;
        }
    }

    public static void b(Object obj, short s, int i) {
        if (obj == null) {
            LogUtil.b("ReflectionIMonitor", "setParam failed, cause stream is null!");
            return;
        }
        Method method = h;
        if (method == null) {
            LogUtil.b("ReflectionIMonitor", "closeEventStream failed, cause METHOD_SET_PARAM_INT is null!");
            return;
        }
        try {
            ReflectionUtils.c(method, obj, Short.valueOf(s), Integer.valueOf(i));
        } catch (UnsupportedOperationException e2) {
            LogUtil.b("ReflectionIMonitor", "setParam get exception:", e2.getMessage());
        }
    }

    public static short b(String str) {
        Class<?> cls;
        if (!TextUtils.isEmpty(str) && (cls = e) != null) {
            Object b2 = ReflectionUtils.b(cls, str);
            if (b2 instanceof Number) {
                return ((Short) b2).shortValue();
            }
            LogUtil.b("ReflectionIMonitor", "getIMonitorKey failed, cause filedValue is not instance of Number!");
        }
        return (short) -1;
    }
}
