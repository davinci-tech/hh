package com.huawei.appgallery.coreservice.internal.support.parcelable.b;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public enum a {
    PARCELABLE,
    BINDER,
    STRING_LIST,
    LIST,
    BUNDLE,
    PARCELABLE_ARRAY,
    STRING_ARRAY,
    BYTE_ARRAY,
    INTERFACE,
    INT_ARRAY,
    INTEGER,
    LONG,
    BOOLEAN,
    FLOAT,
    DOUBLE,
    STRING,
    HASH_MAP;

    private static a b(Class cls) {
        if (cls == Integer.TYPE || cls == Integer.class) {
            return INTEGER;
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return BOOLEAN;
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return LONG;
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return FLOAT;
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return DOUBLE;
        }
        if (cls == String.class) {
            return STRING;
        }
        return null;
    }

    private static a a(Field field, Class cls) {
        if (cls != List.class && cls != ArrayList.class) {
            return null;
        }
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            if (parameterizedType.getActualTypeArguments().length == 1 && parameterizedType.getActualTypeArguments()[0] == String.class) {
                return STRING_LIST;
            }
        }
        return LIST;
    }

    public static a a(Field field) {
        Class<?> type = field.getType();
        a b2 = b(type);
        if (b2 != null) {
            return b2;
        }
        a a2 = a(type);
        if (a2 != null) {
            return a2;
        }
        if (HashMap.class.isAssignableFrom(type)) {
            return HASH_MAP;
        }
        if (Bundle.class.isAssignableFrom(type)) {
            return BUNDLE;
        }
        if (Parcelable.class.isAssignableFrom(type)) {
            return PARCELABLE;
        }
        if (IBinder.class.isAssignableFrom(type)) {
            return BINDER;
        }
        if (IInterface.class.isAssignableFrom(type)) {
            return INTERFACE;
        }
        a a3 = a(field, type);
        if (a3 != null) {
            return a3;
        }
        throw new b("Type is not yet usable with ParcelUtil: " + type);
    }

    private static a a(Class cls) {
        if (cls.isArray() && Parcelable.class.isAssignableFrom(cls.getComponentType())) {
            return PARCELABLE_ARRAY;
        }
        if (cls.isArray() && String.class.isAssignableFrom(cls.getComponentType())) {
            return STRING_ARRAY;
        }
        if (cls.isArray() && Byte.TYPE.isAssignableFrom(cls.getComponentType())) {
            return BYTE_ARRAY;
        }
        if (cls.isArray() && Integer.TYPE.isAssignableFrom(cls.getComponentType())) {
            return INT_ARRAY;
        }
        return null;
    }

    public static class b extends RuntimeException {
        public b(String str) {
            super(str);
        }
    }
}
