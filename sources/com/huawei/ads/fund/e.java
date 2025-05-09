package com.huawei.ads.fund;

import com.huawei.hihealth.HiDataFilter;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class e {
    public static Field[] a(Class cls) {
        Field[] a2 = cls.getSuperclass() != null ? a(cls.getSuperclass()) : null;
        Field[] declaredFields = cls.getDeclaredFields();
        if (a2 == null || a2.length <= 0) {
            return declaredFields;
        }
        Field[] fieldArr = new Field[declaredFields.length + a2.length];
        System.arraycopy(a2, 0, fieldArr, 0, a2.length);
        System.arraycopy(declaredFields, 0, fieldArr, a2.length, declaredFields.length);
        return fieldArr;
    }

    public static Field a(Field field, boolean z) {
        field.setAccessible(z);
        return field;
    }

    public static Class a(Field field, int i) {
        Type[] actualTypeArguments;
        String str;
        Type genericType = field.getGenericType();
        if ((genericType instanceof ParameterizedType) && (actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments()) != null && actualTypeArguments.length > i) {
            try {
                Type type = actualTypeArguments[i];
                if (type instanceof Class) {
                    return (Class) type;
                }
                String obj = type.toString();
                int indexOf = obj.indexOf("class ");
                if (indexOf < 0) {
                    indexOf = 0;
                }
                int indexOf2 = obj.indexOf(HiDataFilter.DataFilterExpression.LESS_THAN);
                if (indexOf2 < 0) {
                    indexOf2 = obj.length();
                }
                return Class.forName(obj.substring(indexOf, indexOf2));
            } catch (ClassNotFoundException unused) {
                str = "getType ClassNotFoundException";
                HiAdLog.w("ReflectAPI", str);
                return null;
            } catch (Exception unused2) {
                str = "getType Exception";
                HiAdLog.w("ReflectAPI", str);
                return null;
            }
        }
        return null;
    }

    public static Class a(Field field) {
        int i;
        if (Map.class.isAssignableFrom(field.getType())) {
            i = 1;
        } else {
            if (!List.class.isAssignableFrom(field.getType())) {
                return null;
            }
            i = 0;
        }
        return a(field, i);
    }
}
