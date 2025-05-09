package com.huawei.agconnect.config.impl;

import android.util.Log;
import com.huawei.agconnect.config.ConfigReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class i implements ConfigReader {

    /* renamed from: a, reason: collision with root package name */
    private final JSONObject f1711a;

    public String toString() {
        return "InputStreamReader{config=" + this.f1711a.toString().hashCode() + '}';
    }

    @Override // com.huawei.agconnect.config.ConfigReader
    public String getString(String str, String str2) {
        if (str.endsWith("/")) {
            return str2;
        }
        String[] split = str.split("/");
        try {
            JSONObject jSONObject = this.f1711a;
            for (int i = 1; i < split.length; i++) {
                if (i == split.length - 1) {
                    str = jSONObject.get(split[i]).toString();
                    return str;
                }
                jSONObject = jSONObject.getJSONObject(split[i]);
            }
        } catch (JSONException unused) {
            Log.w("InputStreamReader", "JSONException when reading 'path': " + str);
        }
        return str2;
    }

    private JSONObject b(String str) throws JSONException {
        JSONArray jSONArray = this.f1711a.getJSONArray("appInfos");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getString("package_name").equals(str)) {
                return jSONObject;
            }
        }
        return null;
    }

    private void a(String str, Object obj, JSONObject jSONObject) throws JSONException {
        if (str == null || obj == null || jSONObject == null) {
            return;
        }
        if (!(obj instanceof JSONObject)) {
            jSONObject.put(str, obj);
            return;
        }
        JSONObject jSONObject2 = (JSONObject) obj;
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            a(next, jSONObject2.get(next), jSONObject.getJSONObject(str));
        }
    }

    private void a(String str) {
        try {
            JSONObject b = b(str);
            if (b == null) {
                return;
            }
            String string = getString("/configuration_version", "");
            BigDecimal bigDecimal = new BigDecimal("0.0");
            try {
                bigDecimal = BigDecimal.valueOf(Double.parseDouble(string));
            } catch (NumberFormatException unused) {
                Log.d("InputStreamReader", "configuration_version to double error");
            }
            if (bigDecimal.compareTo(new BigDecimal("2.0")) == 0) {
                this.f1711a.getJSONObject("client").put("app_id", b.getString("app_id"));
                return;
            }
            if (bigDecimal.compareTo(new BigDecimal("3.0")) >= 0) {
                Iterator<String> keys = b.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!"package_name".equals(next)) {
                        a(next, b.get(next), this.f1711a);
                    }
                }
            }
        } catch (JSONException unused2) {
            Log.d("InputStreamReader", "JSONException when reading the 'appInfos' from InputStream.");
        }
    }

    private JSONObject a(InputStream inputStream) {
        String str;
        if (inputStream != null) {
            try {
                return new JSONObject(Utils.toString(inputStream, "UTF-8"));
            } catch (IOException unused) {
                str = "IOException when reading the 'Config' from InputStream.";
                Log.e("InputStreamReader", str);
                return new JSONObject();
            } catch (JSONException unused2) {
                str = "JSONException when reading the 'Config' from InputStream.";
                Log.e("InputStreamReader", str);
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    i(InputStream inputStream, String str) {
        this.f1711a = a(inputStream);
        a(str);
    }
}
