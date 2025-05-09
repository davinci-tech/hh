package com.huawei.updatesdk.a.b.c.c;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.seuqneceutils.SequenceDetailFieldConfig;
import com.huawei.updatesdk.a.a.d.g;
import com.huawei.updatesdk.service.appmgr.bean.SDKNetTransmission;
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
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class b {
    private static final char COMMA = ',';
    private static final String END_FLAG = "_";
    private static final String TAG = "b";

    public String toJson() {
        Field[] a2 = g.a(getClass());
        if (a2.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < a2.length; i++) {
            AccessController.doPrivileged(new a(a2[i]));
            String name = a2[i].getName();
            if (a2[i].isAnnotationPresent(SDKNetTransmission.class) || (name != null && name.endsWith("_"))) {
                if (name.endsWith("_")) {
                    name = name.substring(0, name.length() - 1);
                }
                String valueToJson = valueToJson(a2[i].get(this));
                if (valueToJson != null) {
                    sb.append("\"");
                    sb.append(name);
                    sb.append("\":");
                    sb.append(valueToJson);
                    sb.append(COMMA);
                }
            }
        }
        formatJsonStr(sb);
        sb.append("}");
        return sb.toString();
    }

    protected String listToJson(List list) {
        if (list.size() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            String valueToJson = valueToJson(list.get(i));
            if (valueToJson != null) {
                sb.append(valueToJson);
                sb.append(COMMA);
            }
        }
        formatJsonStr(sb);
        sb.append("]");
        return sb.toString();
    }

    public void fromJson(JSONObject jSONObject) {
        Field[] a2 = g.a(getClass());
        Object obj = null;
        for (int i = 0; i < a2.length; i++) {
            AccessController.doPrivileged(new a(a2[i]));
            String name = a2[i].getName();
            if (a2[i].isAnnotationPresent(SDKNetTransmission.class) || name.endsWith("_")) {
                if (name.endsWith("_")) {
                    name = name.substring(0, name.length() - 1);
                }
                if (jSONObject.has(name)) {
                    Object obj2 = jSONObject.get(name);
                    if (!JSONObject.NULL.equals(obj2)) {
                        try {
                            obj = valueFromJson(a2[i].getType(), g.a(a2[i]), obj2);
                            a2[i].set(this, obj);
                        } catch (Exception e) {
                            com.huawei.updatesdk.a.a.a.a(TAG, getClass().getName() + ".fromJson error, fieldName:" + name + e.getMessage());
                            processValueError(a2[i], obj);
                        }
                    }
                }
            }
        }
    }

    private String valueToJson(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                return JSONObject.quote(obj.toString());
            }
            if (isPrimitiveObj(obj)) {
                return String.valueOf(obj);
            }
            if (obj instanceof b) {
                return ((b) obj).toJson();
            }
            if (obj instanceof List) {
                return listToJson((List) obj);
            }
            if (obj instanceof Map) {
                return mapToJson((Map) obj);
            }
            if (obj.getClass().isArray()) {
                return arrayToJson(obj);
            }
        }
        return null;
    }

    private Object valueFromJson(Class cls, Class cls2, Object obj) {
        if (cls.isPrimitive() || cls.equals(String.class)) {
            return ("float".equals(cls.getName()) && (obj instanceof Double)) ? Float.valueOf(((Double) obj).floatValue()) : obj;
        }
        if (List.class.isAssignableFrom(cls)) {
            return listFromJson(cls2, obj);
        }
        if (b.class.isAssignableFrom(cls)) {
            if (!cls.equals(b.class)) {
                return jsonBeanFromJson(cls, obj);
            }
            throw new IllegalArgumentException("error type, type:" + cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return mapFromJson(cls2, obj);
        }
        throw new IllegalArgumentException("unsupport type, Type:" + cls);
    }

    private void processValueError(Field field, Object obj) {
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
            } catch (IllegalAccessException e) {
                com.huawei.updatesdk.a.a.c.a.a.a.b(TAG, "processValueError Throwable:" + e.getMessage());
            }
        }
    }

    private String mapToJson(Map map) {
        if (map.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Iterator it = map.entrySet().iterator();
        while (true) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            String valueToJson = valueToJson(entry.getValue());
            if (valueToJson != null) {
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                sb.append(valueToJson);
            }
            if (!it.hasNext()) {
                sb.append("}");
                return sb.toString();
            }
            if (valueToJson != null) {
                sb.append(COMMA);
            }
        }
    }

    private Object mapFromJson(Class cls, Object obj) {
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
            Object valueFromJson = valueFromJson(cls, null, jSONObject.get(next));
            if (valueFromJson != null) {
                if (cls.equals(valueFromJson.getClass())) {
                    linkedHashMap.put(next, valueFromJson);
                } else {
                    com.huawei.updatesdk.a.a.c.a.a.a.b(TAG, "mapFromJson error, memberClass:" + cls + ", valueClass:" + valueFromJson.getClass());
                }
            }
        }
        return linkedHashMap;
    }

    private Object listFromJson(Class cls, Object obj) {
        if (cls == null) {
            throw new IllegalArgumentException("generic type is null");
        }
        if (!(obj instanceof JSONArray)) {
            throw new IllegalArgumentException("jsonobject is not JSONArray, jsonValue:" + obj);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = (JSONArray) obj;
        for (int i = 0; i < jSONArray.length(); i++) {
            Object valueFromJson = valueFromJson(cls, null, jSONArray.get(i));
            if (valueFromJson != null) {
                if (cls.equals(valueFromJson.getClass())) {
                    arrayList.add(valueFromJson);
                } else {
                    com.huawei.updatesdk.a.a.c.a.a.a.b(TAG, "listFromJson error, memberClass:" + cls + ", valueClass:" + valueFromJson.getClass());
                }
            }
        }
        return arrayList;
    }

    private Object jsonBeanFromJson(Class cls, Object obj) {
        b bVar = (b) cls.newInstance();
        bVar.fromJson((JSONObject) obj);
        return bVar;
    }

    private boolean isPrimitiveObj(Object obj) {
        return isIntegerOrLongOrBooleanOrFloat(obj) || isByteOrCharacterOrDoubleOrShort(obj);
    }

    private boolean isIntegerOrLongOrBooleanOrFloat(Object obj) {
        return isIntegerOrLong(obj) || isBooleanOrFloat(obj);
    }

    private boolean isIntegerOrLong(Object obj) {
        return (obj instanceof Integer) || (obj instanceof Long);
    }

    private boolean isDoubleOrShort(Object obj) {
        return (obj instanceof Double) || (obj instanceof Short);
    }

    private boolean isByteOrCharacterOrDoubleOrShort(Object obj) {
        return isByteOrCharacter(obj) || isDoubleOrShort(obj);
    }

    private boolean isByteOrCharacter(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Character);
    }

    private boolean isBooleanOrFloat(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Float);
    }

    private void formatJsonStr(StringBuilder sb) {
        int length = sb.length();
        if (length > 0) {
            int i = length - 1;
            if (sb.charAt(i) == ',') {
                sb.delete(i, length);
            }
        }
    }

    static class a implements PrivilegedAction {

        /* renamed from: a, reason: collision with root package name */
        private Field f10817a;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.f10817a.setAccessible(true);
            return null;
        }

        a(Field field) {
            this.f10817a = field;
        }
    }

    private String arrayToJson(Object obj) {
        int length = Array.getLength(obj);
        if (length <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            String valueToJson = valueToJson(Array.get(obj, i));
            if (valueToJson != null) {
                sb.append(valueToJson);
                sb.append(COMMA);
            }
        }
        formatJsonStr(sb);
        sb.append("]");
        return sb.toString();
    }
}
