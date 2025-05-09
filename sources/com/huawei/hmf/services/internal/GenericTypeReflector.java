package com.huawei.hmf.services.internal;

import com.huawei.hihealth.HiDataFilter;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/* loaded from: classes4.dex */
public class GenericTypeReflector {
    private GenericTypeReflector() {
    }

    public static Class<?> getType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            if (typeVariable.getBounds().length == 0) {
                return Object.class;
            }
            return getType(typeVariable.getBounds()[0]);
        }
        throw new IllegalArgumentException("Not supported: " + type.getClass());
    }

    public static <T> Class<T> getType(Class<T> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        while (!(genericSuperclass instanceof ParameterizedType)) {
            cls = cls.getSuperclass();
            genericSuperclass = cls.getGenericSuperclass();
        }
        return (Class<T>) getType(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        while (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type resolveType = resolveType(type, cls, typeVariable);
            if (resolveType == typeVariable) {
                return resolveType;
            }
            type2 = resolveType;
        }
        if (!(type2 instanceof ParameterizedType)) {
            return type2;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type2;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            Type resolve = resolve(type, cls, actualTypeArguments[i]);
            if (resolve != actualTypeArguments[i]) {
                if (!z) {
                    actualTypeArguments = (Type[]) actualTypeArguments.clone();
                    z = true;
                }
                actualTypeArguments[i] = resolve;
            }
        }
        return z ? new ParameterizedTypeImpl(null, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
    }

    public static Type resolveType(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> declaringClass = declaringClass(typeVariable);
        if (declaringClass == null) {
            return typeVariable;
        }
        Type genericSupertype = getGenericSupertype(type, cls, declaringClass);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClass.getTypeParameters(), typeVariable)];
    }

    private static Class<?> declaringClass(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                Class<?> cls3 = interfaces[i];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    private static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            int i = 0;
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z2) {
                    z = false;
                }
                assertArgument(z);
            }
            this.ownerType = type == null ? null : GenericTypeReflector.canonicalize(type);
            this.rawType = GenericTypeReflector.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i >= typeArr2.length) {
                    return;
                }
                assertNotNull(typeArr2[i]);
                assertNotPrimitive(this.typeArguments[i]);
                Type[] typeArr3 = this.typeArguments;
                typeArr3[i] = GenericTypeReflector.canonicalize(typeArr3[i]);
                i++;
            }
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        private static void assertArgument(boolean z) {
            if (!z) {
                throw new IllegalArgumentException();
            }
        }

        private static <T> void assertNotNull(T t) {
            t.getClass();
        }

        private void assertNotPrimitive(Type type) {
            assertArgument(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) obj;
                if (this == parameterizedType) {
                    return true;
                }
                Type ownerType = parameterizedType.getOwnerType();
                Type rawType = parameterizedType.getRawType();
                if (Objects.equals(this.ownerType, ownerType) && Objects.equals(this.rawType, rawType) && Arrays.equals(this.typeArguments, parameterizedType.getActualTypeArguments())) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int hashCode = Arrays.hashCode(this.typeArguments);
            int hashCode2 = this.rawType.hashCode();
            Type type = this.ownerType;
            return (hashCode ^ hashCode2) ^ (type == null ? 0 : type.hashCode());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
            sb.append(toString(this.rawType));
            if (this.typeArguments.length == 0) {
                return sb.toString();
            }
            sb.append(HiDataFilter.DataFilterExpression.LESS_THAN);
            sb.append(toString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                sb.append(", ");
                sb.append(toString(this.typeArguments[i]));
            }
            sb.append(HiDataFilter.DataFilterExpression.BIGGER_THAN);
            return sb.toString();
        }

        private static String toString(Type type) {
            return type instanceof Class ? ((Class) type).getName() : type.toString();
        }
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (!(type instanceof ParameterizedType)) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
    }
}
