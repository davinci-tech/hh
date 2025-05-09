package health.compact.a;

import defpackage.jeh;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ProductConfigUtil {
    private static boolean c = false;
    private static boolean d = false;

    private ProductConfigUtil() {
    }

    public static String b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("get", String.class);
            r3 = declaredMethod.invoke(cls, "ro.secure") instanceof String ? (String) declaredMethod.invoke(cls, "ro.secure") : null;
            com.huawei.hwlogsmodel.LogUtil.a("ProductConfigUtil", "getRootProperty, Root Property info: ", r3);
        } catch (ClassNotFoundException e) {
            com.huawei.hwlogsmodel.LogUtil.b("ProductConfigUtil", "getRootProperty", e.getMessage());
        } catch (IllegalAccessException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("ProductConfigUtil", "getRootProperty", e2.getMessage());
        } catch (IllegalArgumentException e3) {
            com.huawei.hwlogsmodel.LogUtil.b("ProductConfigUtil", "getRootProperty", e3.getMessage());
        } catch (NoSuchMethodException e4) {
            com.huawei.hwlogsmodel.LogUtil.b("ProductConfigUtil", "getRootProperty", e4.getMessage());
        } catch (InvocationTargetException e5) {
            com.huawei.hwlogsmodel.LogUtil.b("ProductConfigUtil", "getRootProperty", e5.getMessage());
        }
        return r3;
    }

    public static boolean e() {
        if (!c) {
            c = true;
            if ("0".equals(b())) {
                d = true;
            } else {
                d = jeh.a();
            }
        }
        return d;
    }
}
