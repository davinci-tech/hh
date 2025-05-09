package com.huawei.hmf.orb.tbis;

import com.huawei.hmf.orb.tbis.TBNativeType;
import com.huawei.hmf.services.internal.GenericTypeReflector;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
class NativeTypeBridge {
    private static Map<Type, TypeAdapter> typeAdapterMap = new HashMap();
    private boolean mIsLinked;
    private Type[] mOriginalTypes;

    static class TypeAdapter {
        private final TBNativeType.Factory factory;
        private final Type type;

        TypeAdapter(Type type, TBNativeType.Factory factory) {
            this.type = type;
            this.factory = factory;
        }
    }

    static void registry(Type type, TBNativeType.Factory factory) {
        Type boxedType;
        if (factory instanceof TBNativeType.UnboxFactory) {
            boxedType = ((TBNativeType.UnboxFactory) factory).getType();
        } else {
            boxedType = getBoxedType(factory);
        }
        registry(type, boxedType, factory);
    }

    private static void registry(Type type, Type type2, TBNativeType.Factory factory) {
        typeAdapterMap.put(type, new TypeAdapter(type2, factory));
    }

    public NativeTypeBridge(Type[] typeArr) {
        this.mOriginalTypes = (Type[]) typeArr.clone();
        for (int i = 0; i < typeArr.length; i++) {
            TypeAdapter typeAdapter = typeAdapterMap.get(typeArr[i]);
            if (typeAdapter != null) {
                typeArr[i] = typeAdapter.type;
                this.mIsLinked = true;
            }
        }
    }

    public Object[] process(Object[] objArr) {
        TypeAdapter typeAdapter;
        if (this.mIsLinked) {
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] != null && (typeAdapter = typeAdapterMap.get(this.mOriginalTypes[i])) != null && typeAdapter.factory != null) {
                    objArr[i] = typeAdapter.factory.create(objArr[i]);
                }
            }
        }
        return objArr;
    }

    private static Type getBoxedType(TBNativeType.Factory factory) {
        Type type = factory.getClass().getGenericInterfaces()[0];
        if (type == null || !(type instanceof ParameterizedType)) {
            return String.class;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        return actualTypeArguments.length < 2 ? String.class : GenericTypeReflector.getType(actualTypeArguments[1]);
    }
}
