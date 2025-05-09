package com.huawei.hmf.orb.tbis;

import com.huawei.hmf.annotation.NamedArg;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class TBMethodInvoker {
    private final TBMethod mMethod;
    private Type[] mParam;

    public TBMethodInvoker(TBMethod tBMethod) {
        this.mMethod = tBMethod;
    }

    public Object invoke(Object obj, String... strArr) throws InvocationTargetException, IllegalAccessException {
        Type[] parameterTypes = getParameterTypes();
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            objArr[i] = parseArgument(parameterTypes[i], strArr[i]);
        }
        return this.mMethod.invoke(obj, objArr);
    }

    public Object invoke(Object obj, TBParameterProvider tBParameterProvider) throws InvocationTargetException, IllegalAccessException {
        if (tBParameterProvider == null) {
            throw new IllegalArgumentException("TBParameterProvider is null");
        }
        int parameterCount = tBParameterProvider.getParameterCount();
        ArrayList arrayList = new ArrayList();
        for (Annotation[] annotationArr : this.mMethod.getMethod().getParameterAnnotations()) {
            for (Annotation annotation : annotationArr) {
                if (annotation instanceof NamedArg) {
                    arrayList.add(((NamedArg) annotation).value());
                }
            }
        }
        if (arrayList.size() != 0 && arrayList.size() != parameterCount) {
            throw new IllegalArgumentException("Incorrect @NamedArg annotation.");
        }
        Type[] parameterTypes = getParameterTypes();
        Object[] objArr = new Object[parameterCount];
        boolean z = arrayList.size() > 0;
        for (int i = 0; i < parameterCount; i++) {
            objArr[i] = parseArgument(parameterTypes[i], z ? tBParameterProvider.getValueByName((String) arrayList.get(i)) : tBParameterProvider.getValueByIndex(i));
        }
        return this.mMethod.invoke(obj, objArr);
    }

    private Type[] getParameterTypes() {
        if (this.mParam == null) {
            this.mParam = this.mMethod.getGenericParameterTypes();
        }
        return this.mParam;
    }

    private static Object parseArgument(Type type, String str) {
        if (type == String.class) {
            return str;
        }
        if (type == Integer.TYPE) {
            return Integer.valueOf(str);
        }
        if (type == Long.TYPE) {
            return Long.valueOf(str);
        }
        if (type == Double.TYPE) {
            return Double.valueOf(str);
        }
        if (type == Float.TYPE) {
            return Float.valueOf(str);
        }
        if (type == Boolean.TYPE) {
            return Boolean.valueOf(str);
        }
        return TextCodecFactory.create().toObject(str, type);
    }
}
