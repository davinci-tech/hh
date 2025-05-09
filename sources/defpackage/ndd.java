package defpackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes6.dex */
public class ndd {
    public static Field a(Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException e) {
            ncx.e("RefectUtil.getFieldValue Error2 " + e.getMessage());
            return null;
        }
    }

    public static Object d(Field field, Object obj) {
        if (field == null) {
            return null;
        }
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            ncx.e("ReflectionUtils.getDeclareFieldValue Error22" + e.getMessage());
            return null;
        }
    }

    public static void a(Class cls, Object obj, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException e) {
            ncx.e("RefectUtil.setFieldValue Error1 " + e.getMessage());
        } catch (NoSuchFieldException e2) {
            ncx.e("RefectUtil.setFieldValue Error2 " + e2.getMessage());
        }
    }

    public static Object c(Class cls, Object obj, String str, Object... objArr) throws Exception {
        Class<?>[] clsArr;
        if (objArr != null) {
            clsArr = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = objArr[i].getClass();
            }
        } else {
            clsArr = null;
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, objArr);
    }
}
