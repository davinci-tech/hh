package com.huawei.wisecloud.drmclient.utils;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class JsonUtil {
    public static <T> T parseJsonText2Object(Class<T> cls, String str) throws HwDrmException {
        try {
            return (T) parseJson2Object(cls, new JSONObject(str));
        } catch (JSONException e) {
            String str2 = "fail to convert text to JSONObject, JSONException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("JsonUtil", str2);
            throw new HwDrmException(str2);
        }
    }

    public static <T> T parseJson2Object(Class<T> cls, JSONObject jSONObject) throws HwDrmException {
        try {
            T newInstance = cls.newInstance();
            for (Field field : cls.getDeclaredFields()) {
                handleFields(jSONObject, newInstance, field);
            }
            return newInstance;
        } catch (IllegalAccessException e) {
            String str = "fail to prase jsonObject, IllegalAccessException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("JsonUtil", str);
            throw new HwDrmException(str);
        } catch (InstantiationException e2) {
            String str2 = "fail to prase jsonObject, InstantiationException: " + HwDrmLog.printException((Exception) e2);
            HwDrmLog.e("JsonUtil", str2);
            throw new HwDrmException(str2);
        } catch (SecurityException e3) {
            String str3 = "fail to prase jsonObject, SecurityException: " + HwDrmLog.printException((Exception) e3);
            HwDrmLog.e("JsonUtil", str3);
            throw new HwDrmException(str3);
        } catch (InvocationTargetException e4) {
            String str4 = "fail to prase jsonObject, InvocationTargetException: " + HwDrmLog.printException((Exception) e4);
            HwDrmLog.e("JsonUtil", str4);
            throw new HwDrmException(str4);
        } catch (JSONException e5) {
            String str5 = "fail to prase jsonObject, JSONException: " + HwDrmLog.printException((Exception) e5);
            HwDrmLog.e("JsonUtil", str5);
            throw new HwDrmException(str5);
        }
    }

    private static <T> void handleFields(JSONObject jSONObject, T t, final Field field) throws IllegalAccessException, InvocationTargetException, InstantiationException, JSONException, HwDrmException {
        AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.wisecloud.drmclient.utils.JsonUtil.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                field.setAccessible(true);
                return null;
            }
        });
        Class<?> type = field.getType();
        if (type.isPrimitive()) {
            setProperty(t, field, jSONObject.opt(field.getName()));
            return;
        }
        Object newInstance = type.newInstance();
        if (newInstance instanceof List) {
            Class cls = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            JSONArray jSONArray = jSONObject.getJSONArray(field.getName());
            for (int i = 0; i < jSONArray.length(); i++) {
                ((List) newInstance).add(parseJson2Object(cls, jSONObject.getJSONArray(field.getName()).getJSONObject(i)));
            }
            setProperty(t, field, newInstance);
            return;
        }
        if (newInstance instanceof String) {
            setProperty(t, field, jSONObject.opt(field.getName()));
        } else if (newInstance instanceof JSONObject) {
            setProperty(t, field, jSONObject.opt(field.getName()));
        } else {
            setProperty(t, field, parseJson2Object(type, jSONObject.getJSONObject(field.getName())));
        }
    }

    private static void setProperty(Object obj, Field field, Object obj2) throws SecurityException, IllegalAccessException, InvocationTargetException {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + field.getName().substring(1), field.getType());
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, obj2);
        } catch (IllegalArgumentException e) {
            HwDrmLog.d("JsonUtil", "method [set" + field.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + field.getName().substring(1) + "] illegal argument:" + obj2 + "," + e.getMessage());
        } catch (NoSuchMethodException unused) {
            HwDrmLog.d("JsonUtil", "method [set" + field.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + field.getName().substring(1) + "] not found");
        }
    }

    public static void parseJson2Map(Map map, JSONObject jSONObject) throws HwDrmException {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                map.put(next, jSONObject.get(next));
            }
        } catch (JSONException e) {
            String str = "fail to prase jsonObject, JSONException: " + HwDrmLog.printException((Exception) e);
            HwDrmLog.e("JsonUtil", str);
            throw new HwDrmException(str);
        }
    }
}
