package defpackage;

import health.compact.a.LogUtil;
import java.lang.reflect.Field;

/* loaded from: classes6.dex */
public class nsc {
    public static Field[] e(Class<?> cls, Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Field[] fieldArr = new Field[declaredFields.length];
        int i = 0;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getType() == cls) {
                fieldArr[i] = field;
                i++;
            }
        }
        return fieldArr;
    }

    public static void e(Object obj, Field field, Object obj2) {
        if (field == null) {
            return;
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            LogUtil.a("ReflectUtils", LogUtil.a(e));
        }
    }
}
