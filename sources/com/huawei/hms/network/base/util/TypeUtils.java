package com.huawei.hms.network.base.util;

import com.huawei.hihealth.HiDataFilter;
import com.huawei.operation.utils.Constants;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class TypeUtils {

    /* renamed from: a, reason: collision with root package name */
    static final Type[] f5138a = new Type[0];
    private static final int b = 1;

    public static boolean hasUnresolvableType(Type type) {
        if (type instanceof Class) {
            return false;
        }
        if (type instanceof ParameterizedType) {
            for (Type type2 : ((ParameterizedType) type).getActualTypeArguments()) {
                if (hasUnresolvableType(type2)) {
                    return true;
                }
            }
            return false;
        }
        if (type instanceof GenericArrayType) {
            return hasUnresolvableType(((GenericArrayType) type).getGenericComponentType());
        }
        if ((type instanceof TypeVariable) || (type instanceof WildcardType)) {
            return true;
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? Constants.NULL : type.getClass().getName()));
    }

    public static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return a(type, cls, a(type, cls, cls2));
        }
        throw new IllegalArgumentException();
    }

    public static Type getSubmitResponseType(Type type) {
        if (type instanceof ParameterizedType) {
            return getParameterUpperBound(0, (ParameterizedType) type);
        }
        throw new IllegalArgumentException("Submit return type must be parameterized as Submit<Foo> or Submit<? extends Foo>");
    }

    public static Class<?> getRawType(Type type) {
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return (Class) rawType;
            }
            throw new IllegalArgumentException();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
    }

    public static Type getParameterUpperBound(int i, ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (i < actualTypeArguments.length && i >= 0) {
            Type type = actualTypeArguments[i];
            return type instanceof WildcardType ? ((WildcardType) type).getUpperBounds()[0] : type;
        }
        throw new IllegalArgumentException("Index " + i + " not in range [0," + actualTypeArguments.length + ") for " + parameterizedType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    private static boolean c(Type type, Type type2) {
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return b(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && b(parameterizedType.getRawType(), parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return b(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type2;
            WildcardType wildcardType2 = (WildcardType) type;
            return Arrays.equals(wildcardType2.getUpperBounds(), wildcardType.getUpperBounds()) && Arrays.equals(wildcardType2.getLowerBounds(), wildcardType.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            throw new IllegalArgumentException();
        }
    }

    private static Type c(Type type, Class<?> cls, Type type2) {
        Type a2;
        WildcardType wildcardType = (WildcardType) type2;
        Type[] lowerBounds = wildcardType.getLowerBounds();
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (lowerBounds.length == 1) {
            Type a3 = a(type, cls, lowerBounds[0]);
            if (lowerBounds[0] != a3) {
                return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{a3});
            }
        } else if (upperBounds.length == 1 && upperBounds[0] != (a2 = a(type, cls, upperBounds[0]))) {
            return new WildcardTypeImpl(new Type[]{a2}, f5138a);
        }
        return wildcardType;
    }

    public static Class<?> boxIfPrimitive(Class<?> cls) {
        return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        return type instanceof Class ? type.equals(type2) : c(type, type2);
    }

    private static Type b(Type type, Class<?> cls, Type type2) {
        ParameterizedType parameterizedType = (ParameterizedType) type2;
        Type ownerType = parameterizedType.getOwnerType();
        Type a2 = a(type, cls, ownerType);
        boolean z = a2 != ownerType;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments.length;
        for (int i = 0; i < length; i++) {
            Type a3 = a(type, cls, actualTypeArguments[i]);
            if (a3 != actualTypeArguments[i]) {
                if (!z) {
                    actualTypeArguments = (Type[]) actualTypeArguments.clone();
                    z = true;
                }
                actualTypeArguments[i] = a3;
            }
        }
        return z ? new ParameterizedTypeImpl(a2, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
    }

    static final class ParameterizedTypeImpl implements ParameterizedType {

        /* renamed from: a, reason: collision with root package name */
        private final Type f5140a;
        private final Type b;
        private final Type[] c;

        public String toString() {
            Type[] typeArr = this.c;
            if (typeArr.length == 0) {
                return TypeUtils.d(this.f5140a);
            }
            StringBuffer stringBuffer = new StringBuffer((typeArr.length + 1) * 30);
            stringBuffer.append(TypeUtils.d(this.f5140a));
            stringBuffer.append(HiDataFilter.DataFilterExpression.LESS_THAN).append(TypeUtils.d(this.c[0]));
            for (int i = 1; i < this.c.length; i++) {
                stringBuffer.append(", ").append(TypeUtils.d(this.c[i]));
            }
            return stringBuffer.append(HiDataFilter.DataFilterExpression.BIGGER_THAN).toString();
        }

        public int hashCode() {
            int hashCode = Arrays.hashCode(this.c);
            int hashCode2 = this.f5140a.hashCode();
            Type type = this.b;
            return (hashCode ^ hashCode2) ^ (type != null ? type.hashCode() : 0);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.f5140a;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.b;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.c.clone();
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && TypeUtils.b(this, (ParameterizedType) obj);
        }

        ParameterizedTypeImpl(@Nullable Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                if ((((Class) type2).getEnclosingClass() == null) != (type == null)) {
                    throw new IllegalArgumentException();
                }
            }
            for (Type type3 : typeArr) {
                if (type3 == null) {
                    throw new NullPointerException("typeArgument == null");
                }
                TypeUtils.c(type3);
            }
            this.f5140a = type2;
            this.b = type;
            this.c = (Type[]) typeArr.clone();
        }
    }

    static final class WildcardTypeImpl implements WildcardType {

        /* renamed from: a, reason: collision with root package name */
        private final Type f5141a;
        private final Type b;

        public String toString() {
            StringBuilder sb;
            Type type;
            if (this.b != null) {
                sb = new StringBuilder("? super ");
                type = this.b;
            } else {
                if (this.f5141a == Object.class) {
                    return "?";
                }
                sb = new StringBuilder("? extends ");
                type = this.f5141a;
            }
            sb.append(TypeUtils.d(type));
            return sb.toString();
        }

        public int hashCode() {
            Type type = this.b;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.f5141a.hashCode() + 31);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.f5141a};
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.b;
            return type != null ? new Type[]{type} : TypeUtils.f5138a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && TypeUtils.b(this, (WildcardType) obj);
        }

        WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            Type type;
            if (typeArr2.length > 1 || typeArr.length != 1) {
                throw new IllegalArgumentException();
            }
            if (typeArr2.length == 1) {
                Type type2 = typeArr2[0];
                type2.getClass();
                TypeUtils.c(type2);
                if (typeArr[0] != Object.class) {
                    throw new IllegalArgumentException();
                }
                this.b = typeArr2[0];
                type = Object.class;
            } else {
                Type type3 = typeArr[0];
                type3.getClass();
                TypeUtils.c(type3);
                this.b = null;
                type = typeArr[0];
            }
            this.f5141a = type;
        }
    }

    static final class GenericArrayTypeImpl implements GenericArrayType {

        /* renamed from: a, reason: collision with root package name */
        private final Type f5139a;

        public String toString() {
            return TypeUtils.d(this.f5139a) + "[]";
        }

        public int hashCode() {
            return this.f5139a.hashCode();
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.f5139a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && TypeUtils.b(this, (GenericArrayType) obj);
        }

        GenericArrayTypeImpl(Type type) {
            this.f5139a = type;
        }
    }

    private static Type a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        Class cls2 = genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
        if (cls2 == null) {
            return typeVariable;
        }
        Type a2 = a(type, cls, (Class<?>) cls2);
        if (a2 instanceof ParameterizedType) {
            return ((ParameterizedType) a2).getActualTypeArguments()[a(cls2.getTypeParameters(), typeVariable)];
        }
        return typeVariable;
    }

    private static Type a(Type type, Class<?> cls, Type type2) {
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type a2 = a(type, cls, (TypeVariable<?>) typeVariable);
            return a2 == typeVariable ? type2 : a(type, cls, a2);
        }
        if (type2 instanceof Class) {
            Class cls2 = (Class) type2;
            if (cls2.isArray()) {
                Class<?> componentType = cls2.getComponentType();
                if (componentType == null) {
                    return type2;
                }
                Type a3 = a(type, cls, (Type) componentType);
                return a3 == componentType ? cls2 : new GenericArrayTypeImpl(a3);
            }
        }
        if (!(type2 instanceof GenericArrayType)) {
            return type2 instanceof ParameterizedType ? b(type, cls, type2) : type2 instanceof WildcardType ? c(type, cls, type2) : type2;
        }
        GenericArrayType genericArrayType = (GenericArrayType) type2;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        Type a4 = a(type, cls, genericComponentType);
        return a4 == genericComponentType ? genericArrayType : new GenericArrayTypeImpl(a4);
    }

    static Type a(Type type, Class<?> cls, Class<?> cls2) {
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
                    return a(cls.getGenericInterfaces()[i], interfaces[i], cls2);
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
                    return a(cls.getGenericSuperclass(), (Class<?>) superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    private static int a(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
}
