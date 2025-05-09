package defpackage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes7.dex */
public final class jk {
    public static boolean e(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(Boolean.class) || cls.equals(Short.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Void.class);
    }

    public static Class<?> a(Type type) {
        while (!(type instanceof Class)) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalArgumentException("TODO");
            }
            type = ((ParameterizedType) type).getRawType();
        }
        return (Class) type;
    }
}
