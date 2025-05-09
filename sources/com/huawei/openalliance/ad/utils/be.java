package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.openalliance.ad.annotations.SecretField;
import com.huawei.openalliance.ad.ho;
import java.lang.annotation.Annotation;
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

/* loaded from: classes5.dex */
public abstract class be {

    /* renamed from: a, reason: collision with root package name */
    private static final Class[] f7625a = {String.class, Object.class, Integer.class, Short.class, Long.class, Byte.class, Float.class, Double.class, Character.class, Boolean.class};
    private static final Class[] b = {String.class, Object.class, Integer.class, Short.class, Long.class, Byte.class, Float.class, Double.class, Boolean.class};
    private static final String c = "be";
    private static final Map<Class, h> d;

    interface h<D, S> {
        D b(S s);
    }

    private static boolean d(Object obj) {
        return (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Byte) || (obj instanceof Double) || (obj instanceof Short);
    }

    private static String d(Context context, Object obj, boolean z) {
        int length = Array.getLength(obj);
        if (length <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < length; i++) {
            String c2 = c(context, Array.get(obj, i), z);
            if (c2 != null) {
                sb.append(c2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append(']');
        return sb.toString();
    }

    private static List c(Context context, Class cls, Class cls2, Object obj) {
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
        for (int i = 0; i < length; i++) {
            Object a2 = a(context, cls2, (Class) null, jSONArray.get(i));
            if (a2 != null) {
                if (cls2.isAssignableFrom(a2.getClass())) {
                    list.add(a2);
                } else {
                    ho.d(c, "listFromJson error, memberClass:" + cls2 + ", valueClass:" + a2.getClass());
                }
            }
        }
        return list;
    }

    public static String c(Object obj) {
        return c(null, obj);
    }

    private static String c(Context context, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof String) && !(obj instanceof Character)) {
            return d(obj) ? obj.toString() : obj instanceof List ? a(context, (List) obj, z) : obj instanceof Map ? a(context, (Map) obj, z) : obj.getClass().isArray() ? d(context, obj, z) : a(context, obj, z);
        }
        if (cz.b(obj.toString())) {
            return null;
        }
        return "\"" + cz.e(obj.toString()) + "\"";
    }

    public static String c(Context context, Object obj) {
        String str;
        String str2;
        try {
            return a(context, obj, true);
        } catch (IllegalAccessException unused) {
            str = c;
            str2 = "toAnonymousJson IllegalAccessException";
            ho.c(str, str2);
            return "";
        } catch (JSONException unused2) {
            str = c;
            str2 = "toAnonymousJson JSONException";
            ho.c(str, str2);
            return "";
        }
    }

    private static <T> T c(Context context, JSONObject jSONObject, Class<T> cls, Class[] clsArr) {
        if (Collection.class.isAssignableFrom(cls)) {
            throw a("Obj class %s is Collection type which mismatches with JsonObject", cls);
        }
        if (cls.isArray()) {
            throw a("Obj class %s is array type which mismatches with JsonObject", cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return (T) b(context, cls, (clsArr == null || clsArr.length <= 0) ? null : clsArr[0], jSONObject);
        }
        try {
            return (T) a(context, jSONObject, cls.getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (IllegalAccessException unused) {
            throw a("New instance failed for %s", cls);
        } catch (InstantiationException unused2) {
            throw a("New instance failed for %s", cls);
        } catch (NoSuchMethodException unused3) {
            throw a("No default constructor for class %s", cls);
        } catch (InvocationTargetException unused4) {
            throw a("New instance failed for %s", cls);
        }
    }

    private static <T> T c(Context context, String str, Class<T> cls, Class[] clsArr) {
        try {
            try {
                return (T) c(context, new JSONObject(str), cls, clsArr);
            } catch (JSONException unused) {
                throw a("Input string is not valid json string!", new Object[0]);
            }
        } catch (JSONException unused2) {
            return (T) a(context, new JSONArray(str), cls, clsArr);
        }
    }

    private static boolean b(Field field) {
        if (field != null) {
            String name = field.getName();
            if (!Modifier.isStatic(field.getModifiers()) && name != null && !name.contains(SampleEvent.SEPARATOR) && !field.isAnnotationPresent(com.huawei.openalliance.ad.annotations.f.class)) {
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
        for (int i = 0; i < length; i++) {
            if (cls == b[i]) {
                return true;
            }
        }
        return false;
    }

    private static Map b(Context context, Class cls, Class cls2, Object obj) {
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
            Object a2 = a(context, cls2, (Class) null, jSONObject.get(next));
            if (a2 != null) {
                if (cls2.isAssignableFrom(a2.getClass())) {
                    map.put(next, a2);
                } else {
                    ho.d(c, "mapFromJson error, memberClass:" + cls2 + ", valueClass:" + a2.getClass());
                }
            }
        }
        return map;
    }

    public static String b(Object obj) {
        return b(null, obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(android.content.Context r8, java.lang.Object r9, boolean r10) {
        /*
            java.lang.Class r0 = r9.getClass()
            java.lang.reflect.Field[] r0 = com.huawei.openalliance.ad.utils.ck.a(r0)
            int r1 = r0.length
            if (r1 > 0) goto Le
            java.lang.String r8 = ""
            return r8
        Le:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 123(0x7b, float:1.72E-43)
            r1.append(r2)
            int r2 = r0.length
            r3 = 0
        L1a:
            if (r3 >= r2) goto Lbd
            r4 = r0[r3]
            r5 = 1
            java.lang.reflect.Field r4 = com.huawei.openalliance.ad.utils.ck.a(r4, r5)
            r0[r3] = r4
            boolean r4 = b(r4)
            if (r4 != 0) goto L2d
            goto Lb9
        L2d:
            r4 = r0[r3]
            java.lang.String r4 = a(r4)
            r5 = r0[r3]
            java.lang.Object r5 = r5.get(r9)
            boolean r6 = r5 instanceof java.lang.String
            if (r6 == 0) goto L48
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L48
            goto Lb9
        L48:
            if (r10 == 0) goto L59
            r6 = r0[r3]
            java.lang.Class<com.huawei.openalliance.ad.annotations.a> r7 = com.huawei.openalliance.ad.annotations.a.class
            boolean r6 = r6.isAnnotationPresent(r7)
            if (r6 == 0) goto L59
            if (r5 == 0) goto L98
            java.lang.String r5 = "\"******\""
            goto L9e
        L59:
            if (r10 == 0) goto L7f
            r6 = r0[r3]
            java.lang.Class<com.huawei.openalliance.ad.annotations.g> r7 = com.huawei.openalliance.ad.annotations.g.class
            boolean r6 = r6.isAnnotationPresent(r7)
            if (r6 == 0) goto L7f
            if (r5 == 0) goto L98
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "\""
            r6.<init>(r7)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r5 = com.huawei.openalliance.ad.utils.cz.g(r5)
            r6.append(r5)
            r6.append(r7)
            java.lang.String r5 = r6.toString()
            goto L9e
        L7f:
            if (r8 == 0) goto L9a
            r6 = r0[r3]
            java.lang.Class<com.huawei.openalliance.ad.annotations.SecretField> r7 = com.huawei.openalliance.ad.annotations.SecretField.class
            boolean r6 = r6.isAnnotationPresent(r7)
            if (r6 == 0) goto L9a
            byte[] r6 = com.huawei.openalliance.ad.utils.cp.b(r8)
            if (r5 == 0) goto L98
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r5 = com.huawei.openalliance.ad.utils.f.a(r5, r6)
            goto L9e
        L98:
            r5 = 0
            goto L9e
        L9a:
            java.lang.String r5 = c(r8, r5, r10)
        L9e:
            if (r5 == 0) goto Lb9
            r6 = 34
            r1.append(r6)
            r1.append(r4)
            java.lang.String r4 = "\":"
            r1.append(r4)
            r1.append(r5)
            int r4 = r2 + (-1)
            if (r3 >= r4) goto Lb9
            r4 = 44
            r1.append(r4)
        Lb9:
            int r3 = r3 + 1
            goto L1a
        Lbd:
            a(r1)
            r8 = 125(0x7d, float:1.75E-43)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.be.b(android.content.Context, java.lang.Object, boolean):java.lang.String");
    }

    public static String b(Context context, Object obj) {
        try {
            return a(context, obj);
        } catch (JSONException unused) {
            ho.c(c, "toJson JSONException");
            return "";
        }
    }

    public static <T> T b(String str, Class<T> cls, Class... clsArr) {
        return (T) b((Context) null, str, cls, clsArr);
    }

    public static <T> T b(Context context, JSONObject jSONObject, Class<T> cls, Class... clsArr) {
        if (jSONObject == null) {
            throw a(false, "Input json obj cannot be null!", new Object[0]);
        }
        a((Class) cls);
        return (T) c(context, jSONObject, cls, clsArr);
    }

    public static <T> T b(Context context, String str, Class<T> cls, Class... clsArr) {
        String str2;
        String str3;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return (T) a(context, str, cls, clsArr);
        } catch (JSONException unused) {
            str2 = c;
            str3 = "toObject JSONException";
            ho.c(str2, str3);
            return null;
        } catch (Exception unused2) {
            str2 = c;
            str3 = "toObject error";
            ho.c(str2, str3);
            return null;
        }
    }

    private static void a(StringBuilder sb) {
        int length = sb.length();
        if (length > 0) {
            int i = length - 1;
            if (sb.charAt(i) == ',') {
                sb.delete(i, length);
            }
        }
    }

    private static void a(Object obj, Field field, Object obj2) {
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
            ho.d(c, "processValueError");
        }
    }

    private static void a(Object obj, Field field) {
        try {
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null) {
                int length = annotations.length;
                for (int i = 0; i < length; i++) {
                    com.huawei.openalliance.ad.cw a2 = com.huawei.openalliance.ad.cv.a(annotations[i]);
                    if (a2 != null) {
                        a2.a(obj, field, annotations[i]);
                    }
                }
            }
        } catch (IllegalAccessException unused) {
            ho.c(c, obj.getClass().getName() + "postProcessFieldAnnotation exception, fieldName: " + field.getName() + ", field: " + field);
        } catch (Throwable th) {
            ho.c(c, obj.getClass().getName() + "postProcessFieldAnnotation " + th.getClass().getSimpleName() + ", fieldName: " + field.getName() + ", field: " + field);
        }
    }

    private static void a(Class cls) {
        if (cls.isPrimitive()) {
            throw a("Root obj class (%s) cannot be primitive type!", cls);
        }
        int length = f7625a.length;
        for (int i = 0; i < length; i++) {
            if (cls == f7625a[i]) {
                throw a("Root obj class (%s) is invalid in conversion", cls);
            }
        }
    }

    private static void a(Context context, Object obj, Field field, Object obj2) {
        Object obj3 = null;
        try {
            try {
                try {
                    obj3 = a(context, field.getType(), ck.a(field), obj2);
                    field.set(obj, obj3);
                } catch (RuntimeException unused) {
                    ho.c(c, obj.getClass().getName() + ".fromJson runtime exception, fieldName: " + field.getName() + ", field: " + field);
                }
            } catch (Exception unused2) {
                ho.c(c, obj.getClass().getName() + ".fromJson error, fieldName: " + field.getName() + ", field:" + field);
                a(obj, field, obj3);
            }
        } finally {
            a(obj, field);
        }
    }

    private static JSONException a(boolean z, String str, Object... objArr) {
        String format = String.format(Locale.ENGLISH, str, objArr);
        if (z) {
            ho.c(c, format);
        }
        return new JSONException(format);
    }

    private static JSONException a(String str, Object... objArr) {
        return a(true, str, objArr);
    }

    public static Map<String, String> a(String str) {
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

    private static String a(Field field) {
        com.huawei.openalliance.ad.annotations.d dVar = (com.huawei.openalliance.ad.annotations.d) field.getAnnotation(com.huawei.openalliance.ad.annotations.d.class);
        if (dVar != null && !TextUtils.isEmpty(dVar.a())) {
            return dVar.a();
        }
        String name = field.getName();
        return name.endsWith("__") ? name.substring(0, name.length() - 2) : name;
    }

    public static String a(Object obj) {
        return a((Context) null, obj);
    }

    private static String a(Context context, Map map, boolean z) {
        if (map.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Set<Map.Entry> entrySet = map.entrySet();
        int size = entrySet.size();
        int i = 0;
        for (Map.Entry entry : entrySet) {
            i++;
            String str = (String) entry.getKey();
            String c2 = c(context, entry.getValue(), z);
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
        a(sb);
        sb.append('}');
        return sb.toString();
    }

    private static String a(Context context, List list, boolean z) {
        if (list.size() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String c2 = c(context, list.get(i), z);
                if (c2 != null) {
                    sb.append(c2);
                    sb.append(',');
                }
            }
            a(sb);
        } catch (Throwable th) {
            ho.c(c, "list2Json exception: %", th.getClass().getSimpleName());
        }
        sb.append(']');
        return sb.toString();
    }

    private static String a(Context context, Object obj, boolean z) {
        if (obj == null) {
            return "";
        }
        a((Class) obj.getClass());
        return obj instanceof List ? a(context, (List) obj, z) : obj instanceof Map ? a(context, (Map) obj, z) : b(context, obj, z);
    }

    public static String a(Context context, Object obj) {
        try {
            return a(context, obj, false);
        } catch (IllegalAccessException unused) {
            throw a("toJson error", new Object[0]);
        }
    }

    public static <T> T a(String str, Class<T> cls, Class... clsArr) {
        return (T) a((Context) null, str, cls, clsArr);
    }

    private static Object a(Class cls, Object obj) {
        h hVar;
        if (String.class == cls) {
            return cz.a(obj);
        }
        if ((cls.isPrimitive() || Number.class.isAssignableFrom(cls)) && (obj instanceof Number)) {
            obj = (Number) obj;
            hVar = d.get(cls);
            if (hVar == null) {
                ho.c(c, "cannot find value reader for: %s", cls);
                return null;
            }
            return hVar.b(obj);
        }
        if (cls != Boolean.class) {
            return obj;
        }
        hVar = d.get(cls);
        if (hVar == null) {
            ho.c(c, "cannot find value reader for: %s", cls);
            return null;
        }
        return hVar.b(obj);
    }

    private static <T> T a(Context context, JSONObject jSONObject, T t) {
        Object opt;
        Field[] a2 = ck.a((Class) t.getClass());
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            Field a3 = ck.a(a2[i], true);
            if (b(a3) && (opt = jSONObject.opt(a(a3))) != null && JSONObject.NULL != opt) {
                if (context != null && a2[i].isAnnotationPresent(SecretField.class)) {
                    opt = com.huawei.openalliance.ad.utils.f.b((String) opt, cp.b(context));
                }
                a(context, t, a3, opt);
            }
        }
        return t;
    }

    public static <T> T a(Context context, JSONObject jSONObject, Class<T> cls, Class... clsArr) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return (T) b(context, jSONObject, cls, clsArr);
        } catch (Throwable unused) {
            ho.c(c, "toObject error");
            return null;
        }
    }

    private static <T> T a(Context context, JSONArray jSONArray, Class<T> cls, Class[] clsArr) {
        if (List.class.isAssignableFrom(cls)) {
            return (T) c(context, cls, (clsArr == null || clsArr.length <= 0) ? null : clsArr[0], jSONArray);
        }
        throw a("Obj class (%s) is not List type", cls);
    }

    static class a implements h<Boolean, Object> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean b(Object obj) {
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            }
            if (obj instanceof String) {
                return Boolean.valueOf(Boolean.parseBoolean((String) obj));
            }
            return null;
        }

        private a() {
        }
    }

    static class b implements h<Byte, Number> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Byte b(Number number) {
            return Byte.valueOf(number.byteValue());
        }

        private b() {
        }
    }

    static class c implements h<Double, Number> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Double b(Number number) {
            return Double.valueOf(number.doubleValue());
        }

        private c() {
        }
    }

    static class d implements h<Float, Number> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float b(Number number) {
            return Float.valueOf(number.floatValue());
        }

        private d() {
        }
    }

    static class e implements h<Integer, Number> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer b(Number number) {
            return Integer.valueOf(number.intValue());
        }

        private e() {
        }
    }

    static class f implements h<Long, Number> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Long b(Number number) {
            return Long.valueOf(number.longValue());
        }

        private f() {
        }
    }

    static class g implements h<Short, Number> {
        @Override // com.huawei.openalliance.ad.utils.be.h
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Short b(Number number) {
            return Short.valueOf(number.shortValue());
        }

        private g() {
        }
    }

    public static <T> T a(Context context, String str, Class<T> cls, Class... clsArr) {
        if (TextUtils.isEmpty(str)) {
            throw a(false, "Input json string cannot be empty!", new Object[0]);
        }
        a((Class) cls);
        return (T) c(context, str, cls, clsArr);
    }

    private static Object a(Context context, Class cls, Class cls2, Object obj) {
        if (b(cls)) {
            return a(cls, obj);
        }
        if (List.class.isAssignableFrom(cls)) {
            return c(context, cls, cls2, obj);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return b(context, cls, cls2, obj);
        }
        if (obj instanceof JSONObject) {
            return c(context, (JSONObject) obj, cls, new Class[]{cls2});
        }
        if (obj instanceof JSONArray) {
            return a(context, (JSONArray) obj, cls, new Class[]{cls2});
        }
        throw a("value from json error, field class: %s", cls);
    }

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
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
}
