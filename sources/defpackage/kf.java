package defpackage;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class kf {

    /* renamed from: a, reason: collision with root package name */
    public static Method f14336a;
    public static Method b;
    public static Class<?> c;
    public static Method d;
    public static Object e;
    public static Method i;

    static {
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            c = cls;
            e = cls.newInstance();
            d = c.getMethod("getUDID", Context.class);
            b = c.getMethod("getOAID", Context.class);
            f14336a = c.getMethod("getVAID", Context.class);
            i = c.getMethod("getAAID", Context.class);
        } catch (Exception e2) {
            Log.e("IdentifierManager", "reflect exception!", e2);
        }
    }

    public static String d(Context context) {
        return b(context, b);
    }

    public static boolean e() {
        return (c == null || e == null) ? false : true;
    }

    public static String b(Context context, Method method) {
        Object obj = e;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e2) {
            Log.e("IdentifierManager", "invoke exception!", e2);
            return null;
        }
    }
}
