package defpackage;

import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class sln {

    /* renamed from: a, reason: collision with root package name */
    private static final Method f17106a;
    private static final Method c;
    private static final Method d;
    private static final Method e;
    private Object b;

    static {
        if (Build.VERSION.SDK_INT >= 28) {
            d = slc.b("isDeviceSupport", (Class[]) null, "huawei.android.widget.effect.engine.HwShadowEngine");
            c = slc.b("setEnable", new Class[]{Boolean.TYPE}, "huawei.android.widget.effect.engine.HwShadowEngine");
            e = slc.b("isEnable", (Class[]) null, "huawei.android.widget.effect.engine.HwShadowEngine");
            f17106a = slc.b("renderShadow", new Class[]{Canvas.class}, "huawei.android.widget.effect.engine.HwShadowEngine");
            return;
        }
        d = null;
        c = null;
        e = null;
        f17106a = null;
    }

    private static boolean d(Object obj) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public boolean b() {
        Object obj;
        Method method = e;
        if (method == null || (obj = this.b) == null) {
            return false;
        }
        return d(slc.b(obj, method));
    }

    public void edo_(Canvas canvas) {
        Object obj;
        Method method = f17106a;
        if (method == null || (obj = this.b) == null) {
            return;
        }
        slc.c(obj, method, new Object[]{canvas});
    }

    public sln(ViewGroup viewGroup, int i) {
        Class<?> cls;
        Object obj = null;
        try {
            cls = Class.forName("huawei.android.widget.effect.engine.HwShadowEngine$ShadowType");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        try {
            Object[] enumConstants = cls.getEnumConstants();
            int i2 = i - 1;
            if (enumConstants != null && i2 >= 0 && i2 < enumConstants.length) {
                obj = enumConstants[i2];
            }
        } catch (ClassNotFoundException unused2) {
            Log.e("HwShadowEngine", "ShadowType class not found");
            if (cls != null) {
                return;
            } else {
                return;
            }
        }
        if (cls != null || obj == null) {
            return;
        }
        try {
            Constructor<?> constructor = Class.forName("huawei.android.widget.effect.engine.HwShadowEngine").getConstructor(ViewGroup.class, cls);
            constructor.setAccessible(true);
            this.b = constructor.newInstance(viewGroup, obj);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused3) {
            Log.e("HwShadowEngine", "HwShadowEngine init failed");
        }
    }
}
