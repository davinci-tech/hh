package com.huawei.phoneservice.faq.base.util;

import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: classes5.dex */
public class FaqRefectUtils {
    public static void c(Object obj, Class<?> cls, String str, int i) {
        if (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                a(declaredField, true);
                declaredField.set(obj, Integer.valueOf(i));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                b(e, "RefectUtilss");
            }
        }
    }

    public enum ResType {
        RES_TYPE_ID("id"),
        RES_TYPE_LAYOUT(CommonUtil.LAYOUT),
        RES_TYPE_STRING("string"),
        RES_TYPE_DRAWABLE("drawable"),
        RES_TYPE_XML("xml"),
        RES_TYPE_STYLEABLE("styleable"),
        RES_TYPE_STYLE(TemplateStyleRecord.STYLE),
        RES_TYPE_MENU("menu");

        private String type;

        @Override // java.lang.Enum
        public String toString() {
            return this.type;
        }

        ResType(String str) {
            this.type = str;
        }
    }

    public static void a(AccessibleObject accessibleObject, boolean z) {
        if (accessibleObject != null) {
            AccessController.doPrivileged(new a(accessibleObject, z));
        }
    }

    private static void b(Exception exc, String str) {
        if (exc == null || exc.getMessage() == null) {
            return;
        }
        i.c(str, exc.getMessage());
    }

    public static Object e(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        Class<?> cls;
        Object b;
        if (clsArr != null && objArr != null && clsArr.length == objArr.length) {
            try {
                cls = Class.forName(str);
            } catch (ClassNotFoundException e) {
                b(e, "RefectUtilss");
                cls = null;
            }
            if (cls != null && (b = b(cls, obj)) != null) {
                return e(cls, b, str2, clsArr, objArr);
            }
        }
        return null;
    }

    class a implements PrivilegedAction<Object> {
        final /* synthetic */ boolean b;
        final /* synthetic */ AccessibleObject d;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.d.setAccessible(this.b);
            return null;
        }

        a(AccessibleObject accessibleObject, boolean z) {
            this.d = accessibleObject;
            this.b = z;
        }
    }

    private static Object e(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        Method method;
        try {
            method = cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            b(e, "RefectUtilss");
            method = null;
        }
        if (method != null) {
            try {
                return method.invoke(obj, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                b(e2, "RefectUtilss");
            }
        }
        return null;
    }

    public static Object b(Class cls, Object obj) {
        if (cls == null) {
            i.c("RefectUtilss", "getReflectConstructor is null");
            return null;
        }
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        declaredConstructors[0].setAccessible(true);
        try {
            return declaredConstructors[0].newInstance(obj);
        } catch (Throwable unused) {
            i.c("RefectUtilss", "getReflectConstructor exception");
            return null;
        }
    }
}
