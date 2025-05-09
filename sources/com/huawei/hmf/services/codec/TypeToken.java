package com.huawei.hmf.services.codec;

import com.huawei.hmf.services.internal.GenericTypeReflector;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes4.dex */
public class TypeToken<T> {
    private final Class<? super T> rawType;
    private final Type type;

    public TypeToken() {
        Type superclassTypeParameter = getSuperclassTypeParameter(getClass());
        this.type = superclassTypeParameter;
        this.rawType = (Class<? super T>) GenericTypeReflector.getType(superclassTypeParameter);
    }

    public TypeToken(Type type) {
        Type canonicalize = GenericTypeReflector.canonicalize(type);
        this.type = canonicalize;
        this.rawType = (Class<? super T>) GenericTypeReflector.getType(canonicalize);
    }

    static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type-parameter.");
        }
        return GenericTypeReflector.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public static TypeToken<?> get(Type type) {
        return new TypeToken<>(type);
    }

    public static <T> TypeToken<T> get(Class<T> cls) {
        return new TypeToken<>(cls);
    }
}
