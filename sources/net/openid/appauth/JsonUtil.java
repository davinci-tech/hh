package net.openid.appauth;

import android.net.Uri;
import defpackage.utq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class JsonUtil {
    public static void a(JSONObject jSONObject, String str, int i) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        utq.d(Integer.valueOf(i), "value must not be null");
        try {
            jSONObject.put(str, i);
        } catch (JSONException unused) {
            throw new IllegalStateException("JSONException thrown in violation of contract, ex");
        }
    }

    public static void e(JSONObject jSONObject, String str, String str2) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        utq.d(str2, (Object) "value must not be null");
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static void a(JSONObject jSONObject, String str, JSONArray jSONArray) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        utq.d(jSONArray, "value must not be null");
        try {
            jSONObject.put(str, jSONArray);
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static void c(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        utq.d(jSONObject2, "value must not be null");
        try {
            jSONObject.put(str, jSONObject2);
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static void b(JSONObject jSONObject, String str, String str2) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (str2 == null) {
            return;
        }
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static void fgp_(JSONObject jSONObject, String str, Uri uri) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (uri == null) {
            return;
        }
        try {
            jSONObject.put(str, uri.toString());
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static void d(JSONObject jSONObject, String str, Long l) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (l == null) {
            return;
        }
        try {
            jSONObject.put(str, l);
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static void a(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (jSONObject2 == null) {
            return;
        }
        try {
            jSONObject.put(str, jSONObject2);
        } catch (JSONException e2) {
            throw new IllegalStateException("JSONException thrown in violation of contract", e2);
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            throw new JSONException("field \"" + str + "\" not found in json object");
        }
        String string = jSONObject.getString(str);
        if (string != null) {
            return string;
        }
        throw new JSONException("field \"" + str + "\" is mapped to a null value");
    }

    public static String d(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            return null;
        }
        String string = jSONObject.getString(str);
        if (string != null) {
            return string;
        }
        throw new JSONException("field \"" + str + "\" is mapped to a null value");
    }

    public static List<String> h(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            return null;
        }
        JSONArray jSONArray = jSONObject.getJSONArray(str);
        if (jSONArray == null) {
            throw new JSONException("field \"" + str + "\" is mapped to a null value");
        }
        return a(jSONArray);
    }

    public static Uri fgn_(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        String string = jSONObject.getString(str);
        if (string == null) {
            throw new JSONException("field \"" + str + "\" is mapped to a null value");
        }
        return Uri.parse(string);
    }

    public static Uri fgo_(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            return null;
        }
        String string = jSONObject.getString(str);
        if (string == null) {
            throw new JSONException("field \"" + str + "\" is mapped to a null value");
        }
        return Uri.parse(string);
    }

    public static Long b(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        try {
            return Long.valueOf(jSONObject.getLong(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static List<String> c(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            throw new JSONException("field \"" + str + "\" not found in json object");
        }
        return a(jSONObject.getJSONArray(str));
    }

    public static List<Uri> j(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            throw new JSONException("field \"" + str + "\" not found in json object");
        }
        return d(jSONObject.getJSONArray(str));
    }

    public static Map<String, String> g(JSONObject jSONObject, String str) throws JSONException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            return linkedHashMap;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            linkedHashMap.put(next, (String) utq.d(jSONObject2.getString(next), (Object) "additional parameter values must not be null"));
        }
        return linkedHashMap;
    }

    public static JSONObject e(JSONObject jSONObject, String str) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        utq.d(str, (Object) "field must not be null");
        if (!jSONObject.has(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject != null) {
            return optJSONObject;
        }
        throw new JSONException("field \"" + str + "\" is mapped to a null value");
    }

    public static List<String> a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(utq.a(jSONArray.get(i)).toString());
            }
        }
        return arrayList;
    }

    public static Map<String, Object> e(JSONObject jSONObject) throws JSONException {
        utq.d(jSONObject, "json must not be null");
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = e((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = e((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    public static List<Object> e(JSONArray jSONArray) throws JSONException {
        utq.d(jSONArray, "jsonArray must not be null");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = e((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = e((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static List<Uri> d(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(Uri.parse(utq.a(jSONArray.get(i)).toString()));
            }
        }
        return arrayList;
    }

    public static JSONArray d(Iterable<?> iterable) {
        utq.d(iterable, "objects cannot be null");
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toString());
        }
        return jSONArray;
    }

    public static JSONObject c(Map<String, String> map) {
        utq.a(map);
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            utq.d(entry.getKey(), (Object) "map entries must not have null keys");
            utq.d(entry.getValue(), (Object) "map entries must not have null values");
            e(jSONObject, entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public static <T> T d(JSONObject jSONObject, Field<T> field) {
        try {
            if (!jSONObject.has(field.key)) {
                return field.defaultValue;
            }
            return field.convert(jSONObject.getString(field.key));
        } catch (JSONException e2) {
            throw new IllegalStateException("unexpected JSONException", e2);
        }
    }

    public static abstract class Field<T> {
        public final T defaultValue;
        public final String key;

        abstract T convert(String str);

        Field(String str, T t) {
            this.key = str;
            this.defaultValue = t;
        }
    }

    public static final class c extends Field<Uri> {
        c(String str, Uri uri) {
            super(str, uri);
        }

        public c(String str) {
            this(str, null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // net.openid.appauth.JsonUtil.Field
        /* renamed from: fgq_, reason: merged with bridge method [inline-methods] */
        public Uri convert(String str) {
            return Uri.parse(str);
        }
    }

    public static final class a extends Field<String> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // net.openid.appauth.JsonUtil.Field
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public String convert(String str) {
            return str;
        }

        a(String str, String str2) {
            super(str, str2);
        }

        public a(String str) {
            this(str, null);
        }
    }

    public static final class e extends Field<Boolean> {
        public e(String str, boolean z) {
            super(str, Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // net.openid.appauth.JsonUtil.Field
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Boolean convert(String str) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
    }

    public static abstract class ListField<T> {
        public final List<T> defaultValue;
        public final String key;

        abstract T convert(String str);

        ListField(String str, List<T> list) {
            this.key = str;
            this.defaultValue = list;
        }
    }

    public static final class d extends ListField<String> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // net.openid.appauth.JsonUtil.ListField
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public String convert(String str) {
            return str;
        }

        public d(String str) {
            super(str, null);
        }

        public d(String str, List<String> list) {
            super(str, list);
        }
    }
}
