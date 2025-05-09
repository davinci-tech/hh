package com.huawei.ads.fund.util;

import android.text.TextUtils;
import com.huawei.ads.fund.anno.AnonymousField;
import com.huawei.ads.fund.anno.JsonField;
import com.huawei.ads.fund.anno.NoJsonField;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.wg;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class JsonUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Class[] f1693a = {String.class, Object.class, Integer.class, Short.class, Long.class, Byte.class, Float.class, Double.class, Character.class, Boolean.class};
    private static final Class[] b = {String.class, Object.class, Integer.class, Short.class, Long.class, Byte.class, Float.class, Double.class, Boolean.class};
    private static final String c = "JsonUtil";
    private static final Map<Class, i> d;

    interface i<D, S> {
        D a(S s);
    }

    public static <T> T toObjectNoException(String str, Class<T> cls, Class... clsArr) {
        String str2;
        String str3;
        try {
            return (T) toObject(str, cls, clsArr);
        } catch (JSONException unused) {
            str2 = c;
            str3 = "toObject JSONException";
            HiAdLog.w(str2, str3);
            return null;
        } catch (Exception unused2) {
            str2 = c;
            str3 = "toObject error";
            HiAdLog.w(str2, str3);
            return null;
        }
    }

    public static <T> T toObject(String str, Class<T> cls, Class... clsArr) {
        if (TextUtils.isEmpty(str)) {
            throw a(false, "Input json string cannot be empty!", new Object[0]);
        }
        a((Class) cls);
        return (T) a(str, cls, clsArr);
    }

    public static String toJsonNoException(Object obj) {
        try {
            return toJson(obj);
        } catch (JSONException unused) {
            HiAdLog.w(c, "toJson JSONException");
            return "";
        }
    }

    public static String toJson(Object obj) {
        try {
            return c(obj, false);
        } catch (IllegalAccessException unused) {
            throw a("toJson error", new Object[0]);
        }
    }

    public static Map<String, String> jsonToMap(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(trim);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next).toString().trim());
            }
            return hashMap;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static <T> T injectJsonObjectToTargetObj(JSONObject jSONObject, T t) {
        Object opt;
        for (Field field : com.huawei.ads.fund.e.a(t.getClass())) {
            Field a2 = com.huawei.ads.fund.e.a(field, true);
            if (b(a2) && (opt = jSONObject.opt(a(a2))) != null && JSONObject.NULL != opt) {
                a(t, a2, opt);
            }
        }
        return t;
    }

    private static String d(Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof String) && !(obj instanceof Character)) {
            return a(obj) ? obj.toString() : obj instanceof List ? a((List) obj, z) : obj instanceof Map ? a((Map) obj, z) : obj.getClass().isArray() ? a(obj, z) : c(obj, z);
        }
        if (StringUtils.isBlank(obj.toString())) {
            return null;
        }
        return "\"" + StringUtils.a(obj.toString()) + "\"";
    }

    public static JSONObject createJsonObj(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            HiAdLog.w(c, "create json obj err");
            return null;
        }
    }

    public static <T> T convertJsonArrayToPOJO(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            throw a(false, "Input json string cannot be empty!", new Object[0]);
        }
        a((Class) cls);
        try {
            T newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                injectJsonObjectToTargetObj(jSONArray.optJSONObject(i2), newInstance);
            }
            return newInstance;
        } catch (NoSuchMethodException unused) {
            throw a("No default constructor for class %s", cls);
        } catch (JSONException unused2) {
            throw a("Input string is not valid json string!", new Object[0]);
        }
    }

    private static String c(Object obj, boolean z) {
        if (obj == null) {
            return "";
        }
        a((Class) obj.getClass());
        return obj instanceof List ? a((List) obj, z) : obj instanceof Map ? a((Map) obj, z) : b(obj, z);
    }

    private static Object c(Class cls, Class cls2, Object obj) {
        if (b(cls)) {
            return a(cls, obj);
        }
        if (List.class.isAssignableFrom(cls)) {
            return a(cls, cls2, obj);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return b(cls, cls2, obj);
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj, cls, new Class[]{cls2});
        }
        if (obj instanceof JSONArray) {
            return a((JSONArray) obj, cls, new Class[]{cls2});
        }
        throw a("value from json error, field class: %s", cls);
    }

    private static boolean b(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && name != null && !name.contains(SampleEvent.SEPARATOR) && !field.isAnnotationPresent(NoJsonField.class)) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(Class cls) {
        if (cls.isPrimitive()) {
            return true;
        }
        int length = b.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (cls == b[i2]) {
                return true;
            }
        }
        return false;
    }

    private static void b(Object obj, Field field, Object obj2) {
        Object valueOf;
        if (obj2 == null || !(obj2 instanceof String)) {
            return;
        }
        try {
            Class<?> type = field.getType();
            if (type.isPrimitive()) {
                if (Integer.TYPE == type) {
                    valueOf = Integer.valueOf(Integer.parseInt((String) obj2));
                } else if (Float.TYPE == type) {
                    valueOf = Float.valueOf(Float.parseFloat((String) obj2));
                } else if (Long.TYPE == type) {
                    valueOf = Long.valueOf(Long.parseLong((String) obj2));
                } else if (Boolean.TYPE == type) {
                    valueOf = Boolean.valueOf(Boolean.parseBoolean((String) obj2));
                } else if (Double.TYPE == type) {
                    valueOf = Double.valueOf(Double.parseDouble((String) obj2));
                } else if (Short.TYPE == type) {
                    valueOf = Short.valueOf(Short.parseShort((String) obj2));
                } else if (Byte.TYPE == type) {
                    valueOf = Byte.valueOf(Byte.parseByte((String) obj2));
                } else if (Character.TYPE != type) {
                    return;
                } else {
                    valueOf = Character.valueOf(((String) obj2).charAt(0));
                }
                field.set(obj, valueOf);
            }
        } catch (Throwable unused) {
            HiAdLog.e(c, "processValueError");
        }
    }

    private static Map b(Class cls, Class cls2, Object obj) {
        Map map;
        if (cls2 == null) {
            cls2 = String.class;
        }
        if (!(obj instanceof JSONObject)) {
            throw a("jsonValue is not JSONObject", new Object[0]);
        }
        if (Map.class == cls) {
            map = new LinkedHashMap();
        } else {
            if (!Map.class.isAssignableFrom(cls)) {
                throw a("%s is not Map type", cls);
            }
            try {
                map = (Map) cls.newInstance();
            } catch (IllegalAccessException unused) {
                throw a("Fail to initiate %s", cls);
            } catch (InstantiationException unused2) {
                throw a("Fail to initiate %s", cls);
            }
        }
        JSONObject jSONObject = (JSONObject) obj;
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object c2 = c(cls2, null, jSONObject.get(next));
            if (c2 != null) {
                if (cls2.isAssignableFrom(c2.getClass())) {
                    map.put(next, c2);
                } else {
                    HiAdLog.e(c, "mapFromJson error, memberClass:" + cls2 + ", valueClass:" + c2.getClass());
                }
            }
        }
        return map;
    }

    private static String b(Object obj, boolean z) {
        Field[] a2 = com.huawei.ads.fund.e.a(obj.getClass());
        if (a2.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int length = a2.length;
        for (int i2 = 0; i2 < length; i2++) {
            Field a3 = com.huawei.ads.fund.e.a(a2[i2], true);
            a2[i2] = a3;
            if (b(a3)) {
                String a4 = a(a2[i2]);
                Object obj2 = a2[i2].get(obj);
                String d2 = (z && a2[i2].isAnnotationPresent(AnonymousField.class)) ? obj2 != null ? "\"******\"" : null : d(obj2, z);
                if (d2 != null) {
                    sb.append('\"');
                    sb.append(a4);
                    sb.append("\":");
                    sb.append(d2);
                    if (i2 < length - 1) {
                        sb.append(',');
                    }
                }
            }
        }
        a(sb);
        sb.append('}');
        return sb.toString();
    }

    private static boolean a(Object obj) {
        return (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Byte) || (obj instanceof Double) || (obj instanceof Short);
    }

    private static void a(StringBuilder sb) {
        int length = sb.length();
        if (length > 0) {
            int i2 = length - 1;
            if (sb.charAt(i2) == ',') {
                sb.delete(i2, length);
            }
        }
    }

    private static void a(Object obj, Field field, Object obj2) {
        Object obj3;
        try {
            try {
                try {
                    obj3 = c(field.getType(), com.huawei.ads.fund.e.a(field), obj2);
                } catch (RuntimeException unused) {
                    HiAdLog.w(c, obj.getClass().getName() + ".fromJson runtime exception, fieldName: " + field.getName() + ", field: " + field);
                }
                try {
                    field.set(obj, obj3);
                } catch (Exception unused2) {
                    HiAdLog.w(c, obj.getClass().getName() + ".fromJson error, fieldName: " + field.getName() + ", field:" + field);
                    b(obj, field, obj3);
                }
            } finally {
                a(obj, field);
            }
        } catch (Exception unused3) {
            obj3 = null;
        }
    }

    private static void a(Object obj, Field field) {
        try {
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null) {
                int length = annotations.length;
                for (int i2 = 0; i2 < length; i2++) {
                    com.huawei.ads.fund.c d2 = wg.d(annotations[i2]);
                    if (d2 != null) {
                        d2.a(obj, field, annotations[i2]);
                    }
                }
            }
        } catch (IllegalAccessException unused) {
            HiAdLog.w(c, obj.getClass().getName() + "postProcessFieldAnnotation exception, fieldName: " + field.getName() + ", field: " + field);
        } catch (Throwable th) {
            HiAdLog.w(c, obj.getClass().getName() + "postProcessFieldAnnotation " + th.getClass().getSimpleName() + ", fieldName: " + field.getName() + ", field: " + field);
        }
    }

    private static void a(Class cls) {
        if (cls.isPrimitive()) {
            throw a("Root obj class (%s) cannot be primitive type!", cls);
        }
        int length = f1693a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (cls == f1693a[i2]) {
                throw a("Root obj class (%s) is invalid in conversion", cls);
            }
        }
    }

    private static JSONException a(boolean z, String str, Object... objArr) {
        String formatMsg = StringUtils.getFormatMsg(str, objArr);
        if (z) {
            HiAdLog.w(c, formatMsg);
        }
        return new JSONException(formatMsg);
    }

    private static JSONException a(String str, Object... objArr) {
        return a(true, str, objArr);
    }

    private static List a(Class cls, Class cls2, Object obj) {
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
            } catch (IllegalAccessException unused) {
                throw a("Fail to initiate %s", cls);
            } catch (InstantiationException unused2) {
                throw a("Fail to initiate %s", cls);
            }
        }
        JSONArray jSONArray = (JSONArray) obj;
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            Object c2 = c(cls2, null, jSONArray.get(i2));
            if (c2 != null) {
                if (cls2.isAssignableFrom(c2.getClass())) {
                    list.add(c2);
                } else {
                    HiAdLog.e(c, "listFromJson error, memberClass:" + cls2 + ", valueClass:" + c2.getClass());
                }
            }
        }
        return list;
    }

    private static String a(Map map, boolean z) {
        if (map.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Set<Map.Entry> entrySet = map.entrySet();
        int size = entrySet.size();
        int i2 = 0;
        for (Map.Entry entry : entrySet) {
            i2++;
            String str = (String) entry.getKey();
            String d2 = d(entry.getValue(), z);
            if (d2 != null) {
                sb.append('\"');
                sb.append(str);
                sb.append("\":");
                sb.append(d2);
            }
            if (i2 < size && d2 != null) {
                sb.append(',');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private static String a(List list, boolean z) {
        if (list.size() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            String d2 = d(list.get(i2), z);
            if (d2 != null) {
                sb.append(d2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append(']');
        return sb.toString();
    }

    private static String a(Field field) {
        JsonField jsonField = (JsonField) field.getAnnotation(JsonField.class);
        if (jsonField != null && !TextUtils.isEmpty(jsonField.value())) {
            return jsonField.value();
        }
        String name = field.getName();
        return name.endsWith("__") ? name.substring(0, name.length() - 2) : name;
    }

    private static String a(Object obj, boolean z) {
        int length = Array.getLength(obj);
        if (length <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i2 = 0; i2 < length; i2++) {
            String d2 = d(Array.get(obj, i2), z);
            if (d2 != null) {
                sb.append(d2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append(']');
        return sb.toString();
    }

    private static <T> T a(JSONObject jSONObject, Class<T> cls, Class[] clsArr) {
        if (Collection.class.isAssignableFrom(cls)) {
            throw a("Obj class %s is Collection type which mismatches with JsonObject", cls);
        }
        if (cls.isArray()) {
            throw a("Obj class %s is array type which mismatches with JsonObject", cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return (T) b(cls, (clsArr == null || clsArr.length <= 0) ? null : clsArr[0], jSONObject);
        }
        try {
            return (T) injectJsonObjectToTargetObj(jSONObject, cls.getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (NoSuchMethodException unused) {
            throw a("No default constructor for class %s", cls);
        }
    }

    static class a implements i<Byte, Object> {
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Byte a(Object obj) {
            if (obj instanceof Number) {
                return Byte.valueOf(((Number) obj).byteValue());
            }
            if (obj instanceof String) {
                return StringUtils.toByte((String) obj);
            }
            return null;
        }

        private a() {
        }
    }

    static class b implements i<Boolean, Object> {
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        
            r2 = null;
         */
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Boolean a(java.lang.Object r2) {
            /*
                r1 = this;
                boolean r0 = r2 instanceof java.lang.Boolean     // Catch: java.lang.Throwable -> L16
                if (r0 == 0) goto L7
                java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Throwable -> L16
                goto L20
            L7:
                boolean r0 = r2 instanceof java.lang.String     // Catch: java.lang.Throwable -> L16
                if (r0 == 0) goto L1f
                java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L16
                boolean r2 = java.lang.Boolean.parseBoolean(r2)     // Catch: java.lang.Throwable -> L16
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> L16
                goto L20
            L16:
                java.lang.String r2 = com.huawei.ads.fund.util.JsonUtil.a()
                java.lang.String r0 = "read boolean - unKnow srcValue class"
                com.huawei.openplatform.abl.log.HiAdLog.w(r2, r0)
            L1f:
                r2 = 0
            L20:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ads.fund.util.JsonUtil.b.a(java.lang.Object):java.lang.Boolean");
        }

        private b() {
        }
    }

    static class d implements i<Double, Object> {
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Double a(Object obj) {
            if (obj instanceof Number) {
                return Double.valueOf(((Number) obj).doubleValue());
            }
            if (obj instanceof String) {
                return StringUtils.toDouble((String) obj);
            }
            return null;
        }

        private d() {
        }
    }

    static class e implements i<Float, Object> {
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float a(Object obj) {
            if (obj instanceof Number) {
                return Float.valueOf(((Number) obj).floatValue());
            }
            if (obj instanceof String) {
                return StringUtils.toFloat((String) obj);
            }
            return null;
        }

        private e() {
        }
    }

    static class f implements i<Integer, Object> {
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Integer a(Object obj) {
            if (obj instanceof Number) {
                return Integer.valueOf(((Number) obj).intValue());
            }
            if (obj instanceof String) {
                return StringUtils.toInteger((String) obj);
            }
            return null;
        }

        private f() {
        }
    }

    static class g implements i<Short, Object> {
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Short a(Object obj) {
            if (obj instanceof Number) {
                return Short.valueOf(((Number) obj).shortValue());
            }
            if (obj instanceof String) {
                return StringUtils.toShort((String) obj);
            }
            return null;
        }

        private g() {
        }
    }

    static class h implements i<Long, Object> {
        @Override // com.huawei.ads.fund.util.JsonUtil.i
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Long a(Object obj) {
            if (obj instanceof Number) {
                return Long.valueOf(((Number) obj).longValue());
            }
            if (obj instanceof String) {
                return StringUtils.toLong((String) obj);
            }
            return null;
        }

        private h() {
        }
    }

    private static <T> T a(JSONArray jSONArray, Class<T> cls, Class[] clsArr) {
        if (List.class.isAssignableFrom(cls)) {
            return (T) a(cls, (clsArr == null || clsArr.length <= 0) ? null : clsArr[0], jSONArray);
        }
        throw a("Obj class (%s) is not List type", cls);
    }

    private static <T> T a(String str, Class<T> cls, Class[] clsArr) {
        try {
            try {
                return (T) a(new JSONObject(str), cls, clsArr);
            } catch (JSONException unused) {
                throw a("Input string is not valid json string!", new Object[0]);
            }
        } catch (JSONException unused2) {
            return (T) a(new JSONArray(str), cls, clsArr);
        }
    }

    private static Object a(Class cls, Object obj) {
        i iVar;
        if (String.class == cls) {
            return StringUtils.a(obj);
        }
        if ((cls.isPrimitive() || Number.class.isAssignableFrom(cls)) && ((obj instanceof Number) || (obj instanceof String))) {
            iVar = d.get(cls);
            if (iVar == null) {
                HiAdLog.w(c, "cannot find value reader for: %s", cls);
                return null;
            }
            return iVar.a(obj);
        }
        if (cls != Boolean.class) {
            return obj;
        }
        iVar = d.get(cls);
        if (iVar == null) {
            HiAdLog.w(c, "cannot find value reader for: %s", cls);
            return null;
        }
        return iVar.a(obj);
    }

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        f fVar = new f();
        hashMap.put(Integer.TYPE, fVar);
        hashMap.put(Integer.class, fVar);
        h hVar = new h();
        hashMap.put(Long.TYPE, hVar);
        hashMap.put(Long.class, hVar);
        e eVar = new e();
        hashMap.put(Float.TYPE, eVar);
        hashMap.put(Float.class, eVar);
        d dVar = new d();
        hashMap.put(Double.TYPE, dVar);
        hashMap.put(Double.class, dVar);
        g gVar = new g();
        hashMap.put(Short.TYPE, gVar);
        hashMap.put(Short.class, gVar);
        a aVar = new a();
        hashMap.put(Byte.TYPE, aVar);
        hashMap.put(Byte.class, aVar);
        b bVar = new b();
        hashMap.put(Boolean.TYPE, bVar);
        hashMap.put(Boolean.class, bVar);
    }
}
