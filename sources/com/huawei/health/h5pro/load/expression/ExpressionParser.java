package com.huawei.health.h5pro.load.expression;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.h5pro.exception.H5ProException;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.operation.utils.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class ExpressionParser {
    public static Object[] transferParamTypes(H5ProInstance h5ProInstance, String[] strArr, Method method) {
        boolean z;
        if (method == null) {
            throw new H5ProException("method is null");
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (strArr.length != parameterTypes.length) {
            z = true;
            if (parameterTypes.length - strArr.length != 1 || !parameterTypes[0].equals(H5ProInstance.class)) {
                throw new H5ProException("The number of parameters does not match the number of parameter types.");
            }
        } else {
            z = false;
        }
        int length = parameterTypes.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            if (i == 0 && z) {
                objArr[i] = h5ProInstance;
            } else if (parameterTypes[i].equals(String.class)) {
                objArr[i] = strArr[z ? i - 1 : i];
            } else {
                objArr[i] = new Gson().fromJson(strArr[z ? i - 1 : i], (Class) parameterTypes[i]);
            }
        }
        return objArr;
    }

    public static Object parse(H5ProInstance h5ProInstance, String str) {
        ExpressionFunction expressionFunction;
        String substring = str.substring(2, str.length() - 1);
        int indexOf = substring.indexOf(".");
        String substring2 = substring.substring(0, indexOf);
        Object obj = ExpressionFunctionRegistry.get(substring2);
        if (obj == null) {
            LogUtil.e("H5PRO_DynamicParser", "fail to get module instance." + substring2);
            throw new H5ProException("fail to get module instance." + substring2);
        }
        int indexOf2 = substring.indexOf(Constants.LEFT_BRACKET_ONLY);
        String substring3 = substring.substring(indexOf + 1, indexOf2);
        Method method = null;
        for (Method method2 : obj.getClass().getMethods()) {
            if (Modifier.isPublic(method2.getModifiers()) && (expressionFunction = (ExpressionFunction) method2.getAnnotation(ExpressionFunction.class)) != null && substring3.equals(expressionFunction.alias())) {
                method = method2;
            }
        }
        if (method == null) {
            LogUtil.e("H5PRO_DynamicParser", "fail to get module method." + substring3);
            throw new H5ProException("fail to get module method." + substring3);
        }
        int lastIndexOf = substring.lastIndexOf(Constants.RIGHT_BRACKET_ONLY);
        String[] strArr = new String[0];
        int i = indexOf2 + 1;
        if (i < lastIndexOf) {
            strArr = substring.substring(i, lastIndexOf).trim().split(",");
        }
        try {
            return method.invoke(obj, transferParamTypes(h5ProInstance, strArr, method));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LogUtil.e("H5PRO_DynamicParser", "parse." + e.getMessage());
            throw new H5ProException(e.getMessage());
        }
    }

    public static boolean isMatchExpression(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("\\$\\{.+\\..+\\(.*\\)\\}", str);
    }
}
