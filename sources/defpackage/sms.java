package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* loaded from: classes7.dex */
public class sms {
    private static final Map<String, Class<?>> d = new ArrayMap();

    public static int c(Context context, int i, int i2) {
        int b = b(context);
        return ((i & 15) & b) == 0 ? i2 : b;
    }

    public static int d(Context context) {
        return b(context);
    }

    public static Object e(Context context, String str, Class<?> cls) {
        if (context == null || TextUtils.isEmpty(str) || cls == null) {
            Log.i("HwWidgetInstantiator", "instantiate: return is null.");
            return null;
        }
        try {
            Map<String, Class<?>> map = d;
            Class<?> cls2 = map.get(str);
            if (cls2 == null) {
                cls2 = context.getClassLoader().loadClass(str);
                if (!cls.isAssignableFrom(cls2)) {
                    Log.w("HwWidgetInstantiator", "Trying to instantiate the class that is not " + cls.getName());
                    return null;
                }
                map.put(str, cls2);
            }
            return cls2.getDeclaredConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException unused) {
            Log.w("HwWidgetInstantiator", str + ": make sure class name exists, is public, and has an empty constructor that is public");
            return null;
        } catch (IllegalAccessException unused2) {
            Log.w("HwWidgetInstantiator", str + ": calling constructor caused an IllegalAccessException");
            return null;
        } catch (InstantiationException unused3) {
            Log.w("HwWidgetInstantiator", str + ": calling constructor caused an InstantiationException");
            return null;
        } catch (NoSuchMethodException unused4) {
            Log.w("HwWidgetInstantiator", str + ": could not find constructor");
            return null;
        } catch (InvocationTargetException unused5) {
            Log.w("HwWidgetInstantiator", str + ": calling constructor caused an InvocationTargetException");
            return null;
        }
    }

    public static int b(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 15;
        int i2 = i != 3 ? i != 4 ? 1 : 2 : 4;
        if (i == 6) {
            return 8;
        }
        return i2;
    }

    public static String e(Context context, Class<?> cls, int i) {
        return cls.getName().replace("com.huawei.uikit", i != 2 ? i != 4 ? i != 8 ? "com.huawei.uikit.phone" : "com.huawei.uikit.watch" : "com.huawei.uikit.car" : "com.huawei.uikit.tv");
    }
}
