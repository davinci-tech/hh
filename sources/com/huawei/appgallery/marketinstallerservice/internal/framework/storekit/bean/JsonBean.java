package com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import defpackage.agr;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class JsonBean {
    public String toJson() {
        Object obj;
        Field[] a2 = com.huawei.appgallery.marketinstallerservice.b.b.f.a.a(getClass());
        if (a2.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < a2.length; i++) {
            Field field = a2[i];
            String name = field.getName();
            if (field.isAnnotationPresent(InstallerNetTransmission.class)) {
                boolean isAccessible = field.isAccessible();
                Field a3 = a(field, true);
                try {
                    try {
                        obj = a3.get(this);
                    } catch (IllegalAccessException unused) {
                        agr.e("JsonBean", "can not access");
                        a(a3, isAccessible);
                        obj = null;
                    }
                    String a4 = a(obj);
                    if (a4 != null) {
                        sb.append("\"");
                        sb.append(name);
                        sb.append("\":");
                        sb.append(a4);
                        sb.append(',');
                    } else {
                        agr.e("JsonBean", "valueToJson error, field:" + a2[i]);
                    }
                } finally {
                    a(a3, isAccessible);
                }
            }
        }
        a(sb);
        sb.append("}");
        return sb.toString();
    }

    public void fromJson(JSONObject jSONObject) {
        StringBuilder sb;
        String str;
        Field[] a2 = com.huawei.appgallery.marketinstallerservice.b.b.f.a.a(getClass());
        Object obj = null;
        for (int i = 0; i < a2.length; i++) {
            Field field = a2[i];
            String name = field.getName();
            if (a2[i].isAnnotationPresent(InstallerNetTransmission.class) && jSONObject.has(name)) {
                Object obj2 = jSONObject.get(name);
                if (!JSONObject.NULL.equals(obj2)) {
                    boolean isAccessible = field.isAccessible();
                    Field a3 = a(field, true);
                    try {
                        obj = a(a3.getType(), com.huawei.appgallery.marketinstallerservice.b.b.f.a.a(a3), obj2);
                        a2[i].set(this, obj);
                    } catch (ClassNotFoundException e) {
                        e = e;
                        sb = new StringBuilder();
                        sb.append(getClass().getName());
                        str = ".fromJson error ClassNotFoundException, fieldName:";
                        sb.append(str);
                        sb.append(name);
                        agr.a("JsonBean", sb.toString(), e);
                        a(a3, obj);
                        a(a3, isAccessible);
                    } catch (IllegalAccessException e2) {
                        e = e2;
                        sb = new StringBuilder();
                        sb.append(getClass().getName());
                        str = ".fromJson error IllegalAccessException, fieldName:";
                        sb.append(str);
                        sb.append(name);
                        agr.a("JsonBean", sb.toString(), e);
                        a(a3, obj);
                        a(a3, isAccessible);
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        sb = new StringBuilder();
                        sb.append(getClass().getName());
                        str = ".fromJson error IllegalArgumentException, fieldName:";
                        sb.append(str);
                        sb.append(name);
                        agr.a("JsonBean", sb.toString(), e);
                        a(a3, obj);
                        a(a3, isAccessible);
                    } catch (InstantiationException e4) {
                        e = e4;
                        sb = new StringBuilder();
                        sb.append(getClass().getName());
                        str = ".fromJson error InstantiationException, fieldName:";
                        sb.append(str);
                        sb.append(name);
                        agr.a("JsonBean", sb.toString(), e);
                        a(a3, obj);
                        a(a3, isAccessible);
                    } catch (JSONException unused) {
                        agr.e("JsonBean", getClass().getName() + ".fromJson error JSONException, fieldName:" + name + " :JSONException");
                        a(a3, obj);
                        a(a3, isAccessible);
                    }
                    a(a3, isAccessible);
                }
            }
        }
    }

    protected Object b(Class cls, Object obj) {
        if (cls == null) {
            throw new IllegalArgumentException("generic type is null");
        }
        if (!(obj instanceof JSONObject)) {
            throw new IllegalArgumentException("jsonobject is not JSONObject, jsonValue:" + obj);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONObject jSONObject = (JSONObject) obj;
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object a2 = a(cls, null, jSONObject.get(next));
            if (a2 != null) {
                if (a2.getClass().toString().equals(cls.toString())) {
                    linkedHashMap.put(next, a2);
                } else {
                    agr.e("JsonBean", "mapFromJson error, memberClass:" + cls + ", valueClass:" + a2.getClass());
                }
            }
        }
        return linkedHashMap;
    }

    protected Object a(Class cls, Object obj) {
        if (cls == null) {
            throw new IllegalArgumentException("generic type is null");
        }
        if (!(obj instanceof JSONArray)) {
            throw new IllegalArgumentException("jsonobject is not JSONArray, jsonValue:" + obj);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = (JSONArray) obj;
        for (int i = 0; i < jSONArray.length(); i++) {
            Object a2 = a(cls, null, jSONArray.get(i));
            if (a2 != null) {
                if (a2.getClass().toString().equals(cls.toString())) {
                    arrayList.add(a2);
                } else {
                    agr.e("JsonBean", "listFromJson error, memberClass:" + cls + ", valueClass:" + a2.getClass());
                }
            }
        }
        return arrayList;
    }

    public static String mapToJson(Map map) {
        if (map.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Iterator it = map.entrySet().iterator();
        while (true) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            String a2 = a(entry.getValue());
            if (a2 != null) {
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                sb.append(a2);
            }
            if (!it.hasNext()) {
                sb.append("}");
                return sb.toString();
            }
            if (a2 != null) {
                sb.append(',');
            }
        }
    }

    public static String listToJson(List list) {
        if (list.size() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            String a2 = a(list.get(i));
            if (a2 != null) {
                sb.append(a2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append("]");
        return sb.toString();
    }

    private Object c(Class cls, Object obj) {
        JsonBean jsonBean = (JsonBean) cls.newInstance();
        jsonBean.fromJson((JSONObject) obj);
        return jsonBean;
    }

    public static String arrayToJson(Array array) {
        int length = Array.getLength(array);
        if (length <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            String a2 = a(Array.get(array, i));
            if (a2 != null) {
                sb.append(a2);
                sb.append(',');
            }
        }
        a(sb);
        sb.append("]");
        return sb.toString();
    }

    private void a(Field field, Object obj) {
        Object valueOf;
        if (obj instanceof String) {
            try {
                Class<?> type = field.getType();
                if (type.isPrimitive()) {
                    String name = type.getName();
                    if ("int".equals(name)) {
                        valueOf = Integer.valueOf(Integer.parseInt((String) obj));
                    } else if ("float".equals(name)) {
                        valueOf = Float.valueOf(Float.parseFloat((String) obj));
                    } else if ("long".equals(name)) {
                        valueOf = Long.valueOf(Long.parseLong((String) obj));
                    } else if (TypedValues.Custom.S_BOOLEAN.equals(name)) {
                        valueOf = Boolean.valueOf(Boolean.parseBoolean((String) obj));
                    } else if (SequenceDetailFieldConfig.DOUBLE.equals(name)) {
                        valueOf = Double.valueOf(Double.parseDouble((String) obj));
                    } else if ("short".equals(name)) {
                        valueOf = Short.valueOf(Short.parseShort((String) obj));
                    } else if ("byte".equals(name)) {
                        valueOf = Byte.valueOf(Byte.parseByte((String) obj));
                    } else if (!SequenceDetailFieldConfig.CHAR.equals(name)) {
                        return;
                    } else {
                        valueOf = Character.valueOf(((String) obj).charAt(0));
                    }
                    field.set(this, valueOf);
                }
            } catch (Throwable unused) {
                agr.e("JsonBean", "processValueError error!");
            }
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

    static class a implements PrivilegedAction<Field> {
        private Field b;
        private boolean e;

        @Override // java.security.PrivilegedAction
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Field run() {
            this.b.setAccessible(this.e);
            return this.b;
        }

        a(Field field, boolean z) {
            this.b = field;
            this.e = z;
        }
    }

    private Field a(Field field, boolean z) {
        return (Field) AccessController.doPrivileged(new a(field, z));
    }

    private static String a(Object obj) {
        if (obj == null) {
            return "\"null\"";
        }
        if (obj instanceof String) {
            return "\"" + obj.toString() + "\"";
        }
        if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Short)) {
            return String.valueOf(obj);
        }
        if (obj instanceof JsonBean) {
            return ((JsonBean) obj).toJson();
        }
        if (obj instanceof List) {
            return listToJson((List) obj);
        }
        if (obj instanceof Map) {
            return mapToJson((Map) obj);
        }
        if (obj.getClass().isArray()) {
            return arrayToJson((Array) obj);
        }
        return null;
    }

    private Object a(Class cls, Class cls2, Object obj) {
        if (cls.isPrimitive() || String.class.toString().equals(cls.toString())) {
            return ("float".equals(cls.getName()) && (obj instanceof Double)) ? Float.valueOf(((Double) obj).floatValue()) : obj;
        }
        if (List.class.isAssignableFrom(cls)) {
            return a(cls2, obj);
        }
        if (JsonBean.class.isAssignableFrom(cls)) {
            if (!cls.toString().equals(JsonBean.class.toString())) {
                return c(cls, obj);
            }
            throw new IllegalArgumentException("error type, type:" + cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return b(cls2, obj);
        }
        throw new IllegalArgumentException("unsupport type, Type:" + cls);
    }
}
