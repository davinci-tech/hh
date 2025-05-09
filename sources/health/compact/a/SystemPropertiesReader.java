package health.compact.a;

import android.text.TextUtils;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
final class SystemPropertiesReader {

    /* renamed from: a, reason: collision with root package name */
    private final Method f13143a;
    private final Method b;
    private final Method c;
    private final Method d;

    SystemPropertiesReader(String str) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> d = ReflectionUtils.d(str);
        this.f13143a = ReflectionUtils.b(d, "get", String.class, String.class);
        this.c = ReflectionUtils.b(d, "getBoolean", String.class, Boolean.TYPE);
        this.d = ReflectionUtils.b(d, "getInt", String.class, Integer.TYPE);
        this.b = ReflectionUtils.b(d, "getLong", String.class, Long.TYPE);
    }

    public String d(String str, String str2) {
        Method method;
        if (TextUtils.isEmpty(str) || (method = this.f13143a) == null) {
            return str2;
        }
        Object c = ReflectionUtils.c(method, (Object) null, str, str2);
        return c instanceof String ? (String) c : str2;
    }

    public boolean c(String str, boolean z) {
        Method method;
        if (TextUtils.isEmpty(str) || (method = this.c) == null) {
            return z;
        }
        Object c = ReflectionUtils.c(method, (Object) null, str, Boolean.valueOf(z));
        return c instanceof Boolean ? ((Boolean) c).booleanValue() : z;
    }

    public int c(String str, int i) {
        Method method;
        if (TextUtils.isEmpty(str) || (method = this.d) == null) {
            return i;
        }
        Object c = ReflectionUtils.c(method, (Object) null, str, Integer.valueOf(i));
        return c instanceof Integer ? ((Integer) c).intValue() : i;
    }
}
