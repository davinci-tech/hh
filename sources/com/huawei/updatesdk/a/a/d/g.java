package com.huawei.updatesdk.a.a.d;

import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.model.SampleEvent;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10806a = "g";

    public static Field[] a(Class cls) {
        Field[] a2 = cls.getSuperclass() != null ? a(cls.getSuperclass()) : null;
        Field[] declaredFields = cls.getDeclaredFields();
        if (a2 != null && a2.length > 0) {
            Field[] fieldArr = new Field[declaredFields.length + a2.length];
            System.arraycopy(a2, 0, fieldArr, 0, a2.length);
            System.arraycopy(declaredFields, 0, fieldArr, a2.length, declaredFields.length);
            declaredFields = fieldArr;
        }
        ArrayList arrayList = new ArrayList();
        for (Field field : declaredFields) {
            if (!field.getName().contains(SampleEvent.SEPARATOR)) {
                arrayList.add(field);
            }
        }
        if (arrayList.size() == declaredFields.length) {
            return declaredFields;
        }
        Field[] fieldArr2 = new Field[arrayList.size()];
        arrayList.toArray(fieldArr2);
        return fieldArr2;
    }

    private static Class a(Type[] typeArr, int i) {
        try {
            Type type = typeArr[i];
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
        } catch (ClassNotFoundException e) {
            com.huawei.updatesdk.a.a.c.a.a.a.b(f10806a, "getType exception!" + e.getMessage());
            return null;
        }
    }

    private static Class a(Field field, int i) {
        Type[] actualTypeArguments;
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType) || (actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments()) == null || actualTypeArguments.length <= i) {
            return null;
        }
        return a(actualTypeArguments, i);
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
