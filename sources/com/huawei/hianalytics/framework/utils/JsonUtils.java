package com.huawei.hianalytics.framework.utils;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JsonUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final Class[] f3870a = {String.class, Object.class, Integer.class, Short.class, Long.class, Byte.class, Float.class, Double.class, Character.class, Boolean.class};
    public static final Class[] b = {String.class, Object.class, Integer.class, Short.class, Long.class, Byte.class, Float.class, Double.class, Boolean.class};
    public static final Map<Class, h> c;

    public static class a implements h<Boolean, Object> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Boolean a(Object obj) {
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            }
            if (obj instanceof String) {
                return Boolean.valueOf(Boolean.parseBoolean((String) obj));
            }
            return null;
        }
    }

    public static class b implements h<Byte, Number> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Byte a(Number number) {
            return Byte.valueOf(number.byteValue());
        }
    }

    public static class c implements h<Double, Number> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Double a(Number number) {
            return Double.valueOf(number.doubleValue());
        }
    }

    public static class d implements h<Float, Number> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Float a(Number number) {
            return Float.valueOf(number.floatValue());
        }
    }

    public static class e implements h<Integer, Number> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Integer a(Number number) {
            return Integer.valueOf(number.intValue());
        }
    }

    public static class f implements h<Long, Number> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Long a(Number number) {
            return Long.valueOf(number.longValue());
        }
    }

    public static class g implements h<Short, Number> {
        @Override // com.huawei.hianalytics.framework.utils.JsonUtils.h
        public Short a(Number number) {
            return Short.valueOf(number.shortValue());
        }
    }

    public interface h<D, S> {
        D a(S s);
    }

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        e eVar = new e();
        hashMap.put(Integer.TYPE, eVar);
        hashMap.put(Integer.class, eVar);
        f fVar = new f();
        hashMap.put(Long.TYPE, fVar);
        hashMap.put(Long.class, fVar);
        d dVar = new d();
        hashMap.put(Float.TYPE, dVar);
        hashMap.put(Float.class, dVar);
        c cVar = new c();
        hashMap.put(Double.TYPE, cVar);
        hashMap.put(Double.class, cVar);
        g gVar = new g();
        hashMap.put(Short.TYPE, gVar);
        hashMap.put(Short.class, gVar);
        b bVar = new b();
        hashMap.put(Byte.TYPE, bVar);
        hashMap.put(Byte.class, bVar);
        a aVar = new a();
        hashMap.put(Boolean.TYPE, aVar);
        hashMap.put(Boolean.class, aVar);
    }

    public static <T> T a(JSONObject jSONObject, T t) {
        Object opt;
        Object obj;
        Object valueOf;
        for (Field field : com.huawei.hianalytics.framework.b.a(t.getClass())) {
            field.setAccessible(true);
            if (a(field) && (opt = jSONObject.opt(field.getName())) != null && opt != JSONObject.NULL) {
                try {
                    try {
                        obj = b(field.getType(), com.huawei.hianalytics.framework.b.a(field), opt);
                        try {
                            field.set(t, obj);
                        } catch (Exception unused) {
                            HiLog.w("JsonUtils", t.getClass().getName() + ".fromJson error, fieldName: " + field.getName() + ", field:" + field);
                            if (obj != null && (obj instanceof String)) {
                                try {
                                    Class<?> type = field.getType();
                                    if (type.isPrimitive()) {
                                        if (type == Integer.TYPE) {
                                            valueOf = Integer.valueOf(Integer.parseInt((String) obj));
                                        } else if (type == Float.TYPE) {
                                            valueOf = Float.valueOf(Float.parseFloat((String) obj));
                                        } else if (type == Long.TYPE) {
                                            valueOf = Long.valueOf(Long.parseLong((String) obj));
                                        } else if (type == Boolean.TYPE) {
                                            valueOf = Boolean.valueOf(Boolean.parseBoolean((String) obj));
                                        } else if (type == Double.TYPE) {
                                            valueOf = Double.valueOf(Double.parseDouble((String) obj));
                                        } else if (type == Short.TYPE) {
                                            valueOf = Short.valueOf(Short.parseShort((String) obj));
                                        } else if (type == Byte.TYPE) {
                                            valueOf = Byte.valueOf(Byte.parseByte((String) obj));
                                        } else if (type == Character.TYPE) {
                                            valueOf = Character.valueOf(((String) obj).charAt(0));
                                        }
                                        field.set(t, valueOf);
                                    }
                                } catch (Throwable unused2) {
                                    HiLog.e("JsonUtils", "processValueError");
                                }
                            }
                        }
                    } catch (Exception unused3) {
                        obj = null;
                    }
                } catch (RuntimeException unused4) {
                    HiLog.w("JsonUtils", t.getClass().getName() + ".fromJson runtime exception, fieldName: " + field.getName() + ", field: " + field);
                }
            }
        }
        return t;
    }

    public static String b(Object obj) {
        if (obj == null) {
            return "";
        }
        a((Class) obj.getClass());
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Map) {
            return a((Map) obj);
        }
        Field[] a2 = com.huawei.hianalytics.framework.b.a(obj.getClass());
        if (a2.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            Field field = a2[i];
            field.setAccessible(true);
            a2[i] = field;
            if (a(field)) {
                String name = a2[i].getName();
                String c2 = c(a2[i].get(obj));
                if (c2 != null) {
                    sb.append('\"');
                    sb.append(name);
                    sb.append("\":");
                    sb.append(c2);
                    if (i < length - 1) {
                        sb.append(',');
                    }
                }
            }
        }
        a(sb);
        sb.append('}');
        return sb.toString();
    }

    public static String c(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof String) && !(obj instanceof Character)) {
            return ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Byte) || (obj instanceof Double) || (obj instanceof Short)) ? obj.toString() : obj instanceof List ? a((List) obj) : obj instanceof Map ? a((Map) obj) : obj.getClass().isArray() ? a(obj) : b(obj);
        }
        String obj2 = obj.toString();
        if (obj2 == null || obj2.trim().isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder("\"");
        String obj3 = obj.toString();
        sb.append(obj3 != null ? obj3.replace("\"", "\\\"") : null);
        sb.append("\"");
        return sb.toString();
    }

    public static String toJsonNoException(Object obj) {
        try {
            try {
                try {
                    return b(obj);
                } catch (IllegalAccessException unused) {
                    throw a("toJson error", new Object[0]);
                }
            } catch (JSONException e2) {
                throw e2;
            }
        } catch (JSONException unused2) {
            HiLog.w("JsonUtils", "toJson error");
            return "";
        }
    }

    public static Object b(Class cls, Class cls2, Object obj) {
        h hVar;
        StringBuilder sb;
        if (!cls.isPrimitive()) {
            for (Class cls3 : b) {
                if (cls != cls3) {
                }
            }
            if (List.class.isAssignableFrom(cls)) {
                return a(cls, cls2, obj);
            }
            if (Map.class.isAssignableFrom(cls)) {
                return a(cls, new Class[]{cls2}, obj);
            }
            if (obj instanceof JSONObject) {
                return a((JSONObject) obj, cls, new Class[]{cls2});
            }
            if (obj instanceof JSONArray) {
                return a((JSONArray) obj, cls, new Class[]{cls2});
            }
            throw a("value from json error, field class: %s", cls);
        }
        if (cls == String.class) {
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj != null) {
                return String.valueOf(obj);
            }
        } else {
            if ((cls.isPrimitive() || Number.class.isAssignableFrom(cls)) && (obj instanceof Number)) {
                obj = (Number) obj;
                hVar = c.get(cls);
                if (hVar == null) {
                    sb = new StringBuilder("cannot find value reader for: %s");
                    sb.append(cls);
                    HiLog.w("JsonUtils", sb.toString());
                }
                return hVar.a(obj);
            }
            if (cls != Boolean.class) {
                return obj;
            }
            hVar = c.get(cls);
            if (hVar == null) {
                sb = new StringBuilder("cannot find value reader for: %s");
                sb.append(cls);
                HiLog.w("JsonUtils", sb.toString());
            }
            return hVar.a(obj);
        }
        return null;
    }

    public static JSONException a(String str, Object... objArr) {
        String format = String.format(Locale.ENGLISH, str, objArr);
        HiLog.w("JsonUtils", format);
        return new JSONException(format);
    }

    public static <T> T a(String str, Class<T> cls, Class... clsArr) {
        if (!TextUtils.isEmpty(str)) {
            a((Class) cls);
            try {
                try {
                    return (T) a(new JSONObject(str), cls, clsArr);
                } catch (JSONException unused) {
                    return (T) a(new JSONArray(str), cls, clsArr);
                }
            } catch (JSONException unused2) {
                throw a("string cannot be empty", new Object[0]);
            }
        }
        throw new JSONException(String.format(Locale.ENGLISH, "string cannot be empty", new Object[0]));
    }

    public static <T> T toObjectNoException(String str, Class<T> cls, Class... clsArr) {
        try {
            return (T) a(str, cls, clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static JSONObject put(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException unused) {
            HiLog.e("JsonUtils", "putJson exception");
        }
        return jSONObject;
    }

    public static String createABReqDefaultJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JsbMapKeyNames.H5_USER_ID, "");
            jSONObject.put("userAttribute", new JSONObject());
        } catch (Exception unused) {
            HiLog.e("JsonUtils", "json exception");
        }
        return jSONObject.toString();
    }

    public static boolean a(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && !name.contains(SampleEvent.SEPARATOR)) {
                return true;
            }
        }
        return false;
    }

    public static void a(StringBuilder sb) {
        int length = sb.length();
        if (length > 0) {
            int i = length - 1;
            if (sb.charAt(i) == ',') {
                sb.delete(i, length);
            }
        }
    }

    public static void a(Class cls) {
        if (cls.isPrimitive()) {
            throw a("obj class (%s) cannot be primitive type", cls);
        }
        for (Class cls2 : f3870a) {
            if (cls == cls2) {
                throw a("obj class (%s) is invalid in conversion", cls);
            }
        }
    }

    public static Map a(Class cls, Class[] clsArr, Object obj) {
        Map map;
        Class cls2;
        Class cls3;
        if (clsArr == null) {
            clsArr = new Class[]{String.class};
        }
        if (!(obj instanceof JSONObject)) {
            throw a("jsonValue is not JSONObject", new Object[0]);
        }
        if (cls == Map.class) {
            map = new LinkedHashMap();
        } else {
            if (!Map.class.isAssignableFrom(cls)) {
                throw a("%s is not Map type", cls);
            }
            try {
                map = (Map) cls.newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
                throw a("fail to initiate %s", cls);
            }
        }
        JSONObject jSONObject = (JSONObject) obj;
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (clsArr.length == 1) {
                cls2 = clsArr[0];
                cls3 = null;
            } else {
                cls2 = clsArr[0];
                cls3 = clsArr[1];
            }
            Object b2 = b(cls2, cls3, jSONObject.get(next));
            if (b2 != null) {
                if (clsArr[0].isAssignableFrom(b2.getClass())) {
                    map.put(next, b2);
                } else {
                    HiLog.e("JsonUtils", "mapFromJson error, memberClass:" + clsArr + ", valueClass:" + b2.getClass());
                }
            }
        }
        return map;
    }

    public static List a(Class cls, Class cls2, Object obj) {
        List list;
        if (cls2 == null) {
            cls2 = String.class;
        }
        if (!(obj instanceof JSONArray)) {
            throw a("jsonobject is not JSONArray", new Object[0]);
        }
        if (cls == List.class) {
            list = new ArrayList();
        } else {
            if (!List.class.isAssignableFrom(cls)) {
                throw a("%s is not List type", cls);
            }
            try {
                list = (List) cls.newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
                throw a("fail to initiate %s", cls);
            }
        }
        JSONArray jSONArray = (JSONArray) obj;
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object b2 = b(cls2, null, jSONArray.get(i));
            if (b2 != null) {
                if (cls2.isAssignableFrom(b2.getClass())) {
                    list.add(b2);
                } else {
                    HiLog.e("JsonUtils", "listFromJson error, memberClass:" + cls2 + ", valueClass:" + b2.getClass());
                }
            }
        }
        return list;
    }

    public static String a(Map map) {
        if (map.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Set<Map.Entry> entrySet = map.entrySet();
        int size = entrySet.size();
        int i = 0;
        for (Map.Entry entry : entrySet) {
            i++;
            String str = (String) entry.getKey();
            String c2 = c(entry.getValue());
            if (c2 != null) {
                sb.append('\"');
                sb.append(str);
                sb.append("\":");
                sb.append(c2);
            }
            if (i < size && c2 != null) {
                sb.append(',');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public static String a(List list) {
        if (list.size() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String c2 = c(list.get(i));
            if (c2 != null) {
                sb.append(c2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append(']');
        return sb.toString();
    }

    public static String a(Object obj) {
        int length = Array.getLength(obj);
        if (length <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < length; i++) {
            String c2 = c(Array.get(obj, i));
            if (c2 != null) {
                sb.append(c2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append(']');
        return sb.toString();
    }

    public static <T> T a(JSONObject jSONObject, Class<T> cls, Class[] clsArr) {
        if (Collection.class.isAssignableFrom(cls)) {
            throw a("class %s is Collection type which mismatches with JsonObject", cls);
        }
        if (cls.isArray()) {
            throw a("obj class %s is array type which mismatches with JsonObject", cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return (T) a(cls, clsArr, jSONObject);
        }
        try {
            return (T) a(jSONObject, cls.getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            throw a("new instance failed for %s", cls);
        } catch (NoSuchMethodException unused2) {
            throw a("no default constructor for class %s", cls);
        }
    }

    public static <T> T a(JSONArray jSONArray, Class<T> cls, Class[] clsArr) {
        if (List.class.isAssignableFrom(cls)) {
            return (T) a(cls, (clsArr == null || clsArr.length <= 0) ? null : clsArr[0], jSONArray);
        }
        throw a("obj class (%s) is not List type", cls);
    }
}
