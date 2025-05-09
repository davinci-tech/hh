package com.huawei.phoneservice.faq.base.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: classes5.dex */
public class g {
    public static void b(Context context) {
        e(context);
        d(context);
        c(context);
    }

    private static void e(Context context) {
        InputMethodManager inputMethodManager;
        StringBuilder sb;
        String message;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return;
        }
        String[] strArr = {"mCurRootView", "mServedView", "mNextServedView"};
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                Field declaredField = inputMethodManager.getClass().getDeclaredField(strArr[i2]);
                if (!declaredField.isAccessible()) {
                    AccessController.doPrivileged(new b(declaredField));
                }
                Object obj = declaredField.get(inputMethodManager);
                if (obj != null && (obj instanceof View)) {
                    if (((View) obj).getContext() != context) {
                        break;
                    } else {
                        declaredField.set(inputMethodManager, null);
                    }
                }
            } catch (ExceptionInInitializerError e2) {
                sb = new StringBuilder("ExceptionInInitializerError");
                message = e2.getMessage();
                sb.append(message);
                com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
                return;
            } catch (NoSuchMethodException e3) {
                sb = new StringBuilder("NoSuchMethodException:");
                message = e3.getMessage();
                sb.append(message);
                com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
                return;
            } catch (ReflectiveOperationException e4) {
                sb = new StringBuilder("ReflectiveOperationException");
                message = e4.getMessage();
                sb.append(message);
                com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
                return;
            } catch (Exception e5) {
                sb = new StringBuilder("Exception");
                message = e5.getMessage();
                sb.append(message);
                com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
                return;
            }
        }
        cdh_(inputMethodManager);
    }

    private static void d(Context context) {
        StringBuilder sb;
        String message;
        try {
            Class<?> cls = Class.forName("android.gestureboost.GestureBoostManager");
            Field declaredField = cls.getDeclaredField("sGestureBoostManager");
            AccessController.doPrivileged(new a(declaredField));
            Object obj = declaredField.get(cls);
            Field declaredField2 = cls.getDeclaredField("mContext");
            AccessController.doPrivileged(new e(declaredField2));
            if (context == declaredField2.get(obj)) {
                com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", "Checked GestureBoostManagerLeak");
                declaredField2.set(obj, null);
            }
        } catch (ExceptionInInitializerError e2) {
            sb = new StringBuilder("ExceptionInInitializerError");
            message = e2.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
        } catch (ReflectiveOperationException e3) {
            sb = new StringBuilder("ReflectiveOperationException");
            message = e3.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
        } catch (Exception e4) {
            sb = new StringBuilder("Exception");
            message = e4.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
        }
    }

    class a implements PrivilegedAction<Object> {
        final /* synthetic */ Field c;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.c.setAccessible(true);
            return null;
        }

        a(Field field) {
            this.c = field;
        }
    }

    class b implements PrivilegedAction<Object> {
        final /* synthetic */ Field d;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.d.setAccessible(true);
            return null;
        }

        b(Field field) {
            this.d = field;
        }
    }

    class c implements PrivilegedAction<Object> {
        final /* synthetic */ Field d;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.d.setAccessible(true);
            return null;
        }

        c(Field field) {
            this.d = field;
        }
    }

    class d implements PrivilegedAction<Object> {
        final /* synthetic */ Method e;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.e.setAccessible(true);
            return null;
        }

        d(Method method) {
            this.e = method;
        }
    }

    class e implements PrivilegedAction<Object> {
        final /* synthetic */ Field e;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.e.setAccessible(true);
            return null;
        }

        e(Field field) {
            this.e = field;
        }
    }

    class i implements PrivilegedAction<Object> {
        final /* synthetic */ Field b;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.b.setAccessible(true);
            return null;
        }

        i(Field field) {
            this.b = field;
        }
    }

    private static void cdh_(InputMethodManager inputMethodManager) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = inputMethodManager.getClass().getMethod("resetInTransitionState", new Class[0]);
        AccessController.doPrivileged(new d(method));
        method.invoke(inputMethodManager, new Object[0]);
    }

    private static void c(Context context) {
        StringBuilder sb;
        String message;
        try {
            Class<?> cls = Class.forName("android.rms.iaware.FastgrabConfigReader");
            Field declaredField = cls.getDeclaredField("mFastgrabConfigReader");
            AccessController.doPrivileged(new c(declaredField));
            Object obj = declaredField.get(cls);
            Field declaredField2 = cls.getDeclaredField("mContext");
            AccessController.doPrivileged(new i(declaredField2));
            if (context == declaredField2.get(obj)) {
                com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", "Checked FastgrabConfigReaderLeak");
                declaredField2.set(obj, null);
            }
        } catch (ExceptionInInitializerError e2) {
            sb = new StringBuilder("ExceptionInInitializerError:");
            message = e2.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
        } catch (ReflectiveOperationException e3) {
            sb = new StringBuilder("ReflectiveOperationException:");
            message = e3.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
        } catch (Exception e4) {
            sb = new StringBuilder("Exception:");
            message = e4.getMessage();
            sb.append(message);
            com.huawei.phoneservice.faq.base.util.i.d("FaqMemoryLeakUtils", sb.toString());
        }
    }
}
