package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class slp {

    /* renamed from: a, reason: collision with root package name */
    private static Object f17108a;
    private static final Method aa;
    private static final Method ab;
    private static final Method ad;
    private static final Method b;
    private static final Method c;
    private static final Method d;
    private static final Method e;
    private static slp f = new slp();
    private static final Method g;
    private static final Method h;
    private static final Method i;
    private static final Method j;
    private static final Method k;
    private static final Method l;
    private static final Method m;
    private static final Method n;
    private static final Method o;
    private static final Method p;
    private static final Method q;
    private static final Method r;
    private static final Method s;
    private static final Method t;
    private static final Method u;
    private static final Method v;
    private static final Method w;
    private static final Method x;
    private static final Method y;
    private static final Method z;

    static {
        Class<?> cls;
        if (Build.VERSION.SDK_INT < 28) {
            i = null;
            g = null;
            h = null;
            j = null;
            k = null;
            o = null;
            n = null;
            l = null;
            m = null;
            t = null;
            q = null;
            s = null;
            r = null;
            p = null;
            w = null;
            v = null;
            x = null;
            y = null;
            u = null;
            z = null;
            ab = null;
            ad = null;
            aa = null;
            b = null;
            e = null;
            d = null;
            c = null;
            return;
        }
        try {
            cls = Class.forName("huawei.android.widget.effect.engine.HwBlurEngine$BlurType");
        } catch (ClassNotFoundException unused) {
            Log.e("HwBlurEngine", "BlurType class not found");
            cls = null;
        }
        i = slc.b("getInstance", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        g = slc.b("getInstance", new Class[]{View.class, cls}, "huawei.android.widget.effect.engine.HwBlurEngine");
        h = slc.b("isEnable", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        Class cls2 = Boolean.TYPE;
        j = slc.b("setGlobalEnable", new Class[]{cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        k = slc.b("setEnable", new Class[]{cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        o = slc.b("onAttachedToWindow", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        n = slc.b("onDetachedFromWindow", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        l = slc.b("draw", new Class[]{Canvas.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
        m = slc.b("draw", new Class[]{Canvas.class, View.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
        t = slc.b("setBlurEnable", new Class[]{cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        q = slc.b("isBlurEnable", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        s = slc.b("isShowHwBlur", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        r = slc.b("isShowHwBlur", new Class[]{View.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
        p = slc.b("onWindowVisibilityChanged", new Class[]{View.class, cls2, cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        w = slc.b("onWindowVisibilityChanged", new Class[]{View.class, cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        v = slc.b("onWindowFocusChanged", new Class[]{View.class, cls2, cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        Class cls3 = Integer.TYPE;
        x = slc.b("blur", new Class[]{View.class, cls3, cls3}, "huawei.android.widget.effect.engine.HwBlurEngine");
        y = slc.b("blur", new Class[]{Bitmap.class, cls3, cls3}, "huawei.android.widget.effect.engine.HwBlurEngine");
        u = slc.b("addBlurTargetView", new Class[]{View.class, cls}, "huawei.android.widget.effect.engine.HwBlurEngine");
        z = slc.b("removeBlurTargetView", new Class[]{View.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
        ab = slc.b("isDrawingViewSelf", (Class[]) null, "huawei.android.widget.effect.engine.HwBlurEngine");
        ad = slc.b("setTargetViewCornerRadius", new Class[]{View.class, cls3}, "huawei.android.widget.effect.engine.HwBlurEngine");
        aa = slc.b("setTargetViewBlurEnable", new Class[]{View.class, cls2}, "huawei.android.widget.effect.engine.HwBlurEngine");
        b = slc.b("setTargetViewOverlayColor", new Class[]{View.class, cls3}, "huawei.android.widget.effect.engine.HwBlurEngine");
        e = slc.b("isShowBlur", new Class[]{Context.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
        d = slc.b("isThemeSupportedBlurEffect", new Class[]{Context.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
        c = slc.b("isSettingEnabledBlurEffect", new Class[]{Context.class}, "huawei.android.widget.effect.engine.HwBlurEngine");
    }

    private static Object a(int i2) {
        try {
            Object[] enumConstants = Class.forName("huawei.android.widget.effect.engine.HwBlurEngine$BlurType").getEnumConstants();
            int i3 = i2 - 1;
            if (enumConstants == null || i3 < 0 || i3 >= enumConstants.length) {
                return null;
            }
            return enumConstants[i3];
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public boolean c() {
        Method method;
        Object obj = f17108a;
        if (obj == null || (method = s) == null) {
            return false;
        }
        return a(slc.b(obj, method));
    }

    public void edi_(View view, int i2) {
        Method method;
        if (f17108a == null || (method = u) == null) {
            return;
        }
        slc.c(f17108a, method, new Object[]{view, a(i2)});
    }

    public void edl_(View view) {
        Method method;
        Object obj = f17108a;
        if (obj == null || (method = z) == null) {
            return;
        }
        slc.c(obj, method, new Object[]{view});
    }

    public void edm_(View view, boolean z2) {
        Method method;
        Object obj = f17108a;
        if (obj == null || (method = aa) == null) {
            return;
        }
        slc.c(obj, method, new Object[]{view, Boolean.valueOf(z2)});
    }

    public void edn_(View view, int i2) {
        Method method;
        Object obj = f17108a;
        if (obj == null || (method = b) == null) {
            return;
        }
        slc.c(obj, method, new Object[]{view, Integer.valueOf(i2)});
    }

    public void edj_(Canvas canvas, View view) {
        Method method;
        Object obj = f17108a;
        if (obj == null || (method = m) == null) {
            return;
        }
        slc.c(obj, method, new Object[]{canvas, view});
    }

    public boolean edk_(View view) {
        Method method;
        Object obj = f17108a;
        if (obj == null || (method = r) == null) {
            return false;
        }
        return a(slc.c(obj, method, new Object[]{view}));
    }

    private static boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static slp e() {
        slp slpVar;
        synchronized (slp.class) {
            Method method = i;
            if (method != null && f17108a == null) {
                f17108a = slc.b(method);
            }
            slpVar = f;
        }
        return slpVar;
    }
}
