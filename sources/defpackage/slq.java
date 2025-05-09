package defpackage;

import android.content.Context;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class slq {

    /* renamed from: a, reason: collision with root package name */
    private static final Method f17109a;
    private static final Method d;
    private View c;
    private Context e;
    private boolean b = false;
    private int j = 0;
    private int f = 0;
    private int i = 0;

    static {
        Class cls = Integer.TYPE;
        d = d("setShadowStyle", new Class[]{cls, cls, cls}, "android.view.View");
        f17109a = d("setShadowClip", new Class[]{Boolean.TYPE}, "android.view.View");
    }

    private void b() {
        int i = this.f;
        if (i == 2) {
            this.i = 2;
        } else if (i == 1 || d()) {
            this.i = 1;
        } else {
            this.i = 0;
        }
    }

    private void b(int i) {
        if (i < 0 || i > 2) {
            return;
        }
        this.f = i;
    }

    private void c(int i) {
        if (i < 0 || i > 6) {
            return;
        }
        this.j = i;
    }

    public void a(int i) {
        c(i);
    }

    public void a(boolean z) {
        if (this.b != z) {
            this.b = z;
            c();
        }
    }

    public void c() {
        View view;
        b();
        Method method = d;
        if (method == null || (view = this.c) == null) {
            Log.w("HwShadowEngine", "Method or target view is null!");
        } else if (this.b) {
            b(view, method, new Object[]{Integer.valueOf(this.j), Integer.valueOf(this.i), Integer.valueOf(a())});
        } else {
            b(view, method, new Object[]{-1, -1, -1});
        }
    }

    public void d(boolean z) {
        View view;
        Method method = f17109a;
        if (method == null || (view = this.c) == null) {
            Log.w("HwShadowEngine", "Method or target view is null!");
        } else {
            b(view, method, new Object[]{Boolean.valueOf(z)});
        }
    }

    public void e(int i) {
        b(i);
    }

    public slq(Context context, View view, int i, int i2) {
        this.e = context;
        this.c = view;
        c(i);
        b(i2);
    }

    private static Method d(String str, Class[] clsArr, String str2) {
        try {
            Method declaredMethod = Class.forName(str2).getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (ClassNotFoundException unused) {
            Log.e("HwShadowEngine", "ClassNotFoundException in reflect call " + str);
            return null;
        } catch (NoSuchMethodException unused2) {
            Log.e("HwShadowEngine", "there is no " + str + " method");
            return null;
        }
    }

    private boolean d() {
        return (this.e.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private Object b(Object obj, Method method, Object[] objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            Log.e("HwShadowEngine", "IllegalAccessException in reflect call " + method.getName());
            return null;
        } catch (InvocationTargetException unused2) {
            Log.e("HwShadowEngine", "InvocationTargetException in reflect call " + method.getName());
            return null;
        }
    }

    private int a() {
        return this.e.getResources().getConfiguration().uiMode & 15;
    }
}
