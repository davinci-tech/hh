package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.validator.KfsConstraintValidator;
import com.huawei.wisesecurity.kfs.validation.core.ValidatorDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class tvt<A extends Annotation> implements ValidatorDescriptor<A> {
    private final Map<Type, Class<? extends KfsConstraintValidator<A, ?>>> c = new ConcurrentHashMap();
    private final Map<Type, Class<? extends KfsConstraintValidator<A, ?>>> b = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, Class<?>> f17392a = new ConcurrentHashMap<Class<?>, Class<?>>() { // from class: tvt.4
        {
            put(Byte.TYPE, Byte.class);
            put(Short.TYPE, Short.class);
            put(Integer.TYPE, Integer.class);
            put(Long.TYPE, Long.class);
            put(Float.TYPE, Float.class);
            put(Double.TYPE, Double.class);
            put(Character.TYPE, Character.class);
            put(Boolean.TYPE, Boolean.class);
        }
    };
    private final Map<Class<?>, Class<?>> d = new ConcurrentHashMap<Class<?>, Class<?>>() { // from class: tvt.2
        {
            put(Byte.TYPE, byte[].class);
            put(Short.TYPE, short[].class);
            put(Integer.TYPE, int[].class);
            put(Long.TYPE, long[].class);
            put(Float.TYPE, float[].class);
            put(Double.TYPE, double[].class);
            put(Character.TYPE, char[].class);
            put(Boolean.TYPE, boolean[].class);
        }
    };

    @SafeVarargs
    public tvt(Class<? extends KfsConstraintValidator<A, ?>>... clsArr) {
        for (Class<? extends KfsConstraintValidator<A, ?>> cls : clsArr) {
            b(cls);
        }
    }

    private void b(Class<? extends KfsConstraintValidator<A, ?>> cls) {
        Type a2 = tvr.a(cls);
        if (a2 instanceof Class) {
            Class cls2 = (Class) a2;
            if (cls2.isArray()) {
                this.b.put(cls2.getComponentType(), cls);
            }
            this.c.put(a2, cls);
            return;
        }
        if (a2 instanceof GenericArrayType) {
            Class cls3 = (Class) ((GenericArrayType) a2).getGenericComponentType();
            if (this.d.containsKey(cls3)) {
                this.c.put(this.d.get(cls3), cls);
            }
        }
    }

    private Class<? extends KfsConstraintValidator<A, ?>> a(Map<Type, Class<? extends KfsConstraintValidator<A, ?>>> map, Class<?> cls) {
        if (map.containsKey(cls)) {
            return map.get(cls);
        }
        Class<? extends KfsConstraintValidator<A, ?>> e = e(map, cls.getGenericSuperclass());
        if (e != null) {
            return e;
        }
        for (Type type : cls.getGenericInterfaces()) {
            Class<? extends KfsConstraintValidator<A, ?>> e2 = e(map, type);
            if (e2 != null) {
                return e2;
            }
        }
        return null;
    }

    private Class<? extends KfsConstraintValidator<A, ?>> e(Map<Type, Class<? extends KfsConstraintValidator<A, ?>>> map, Type type) {
        Class<? extends KfsConstraintValidator<A, ?>> a2;
        if (type == null) {
            return null;
        }
        if ((type instanceof Class) && (a2 = a(map, (Class) type)) != null) {
            return a2;
        }
        if (!(type instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getRawType() instanceof Class) {
            return a(map, (Class) parameterizedType.getRawType());
        }
        return null;
    }

    private Class<?> c(Class<?> cls) {
        return this.f17392a.containsKey(cls) ? this.f17392a.get(cls) : cls;
    }

    @Override // com.huawei.wisesecurity.kfs.validation.core.ValidatorDescriptor
    public Class<? extends KfsConstraintValidator<A, ?>> getValidator(Class<?> cls) {
        Class<? extends KfsConstraintValidator<A, ?>> e;
        if (cls == null) {
            return null;
        }
        Class<?> c = c(cls);
        if (this.c.containsKey(Object.class)) {
            return this.c.get(Object.class);
        }
        if (this.c.containsKey(c)) {
            return this.c.get(c);
        }
        if (c.isArray()) {
            e = e(this.b, c.getComponentType());
        } else {
            e = e(this.c, c);
        }
        if (e != null) {
            this.c.put(c, e);
        }
        return e;
    }
}
