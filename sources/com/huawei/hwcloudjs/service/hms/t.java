package com.huawei.hwcloudjs.service.hms;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import java.io.Serializable;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6258a = "JsonCodec";
    protected static final String b = "_val_type_";
    protected static final int c = -1;
    protected static final int d = 0;
    protected static final int e = 1;
    private static final String f = "_list_size_";
    private static final String g = "_list_item_";

    protected void a(String str, JSONArray jSONArray, Bundle bundle) throws JSONException {
        int i = bundle.getInt(f);
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = bundle.get(g + i2);
            if (obj.getClass().isPrimitive() || (obj instanceof String) || (obj instanceof Serializable)) {
                jSONArray.put(obj);
            } else if (obj instanceof Bundle) {
                Bundle bundle2 = (Bundle) obj;
                if (bundle2.getInt(b, -1) == 0) {
                    JSONObject jSONObject = new JSONObject();
                    a(str, bundle2, jSONObject);
                    jSONArray.put(jSONObject);
                }
            }
        }
    }

    protected void a(String str, String str2, JSONObject jSONObject, Bundle bundle) {
        JSONObject optJSONObject;
        if (jSONObject == null) {
            return;
        }
        if ("String".equals(str2)) {
            bundle.putString(str, jSONObject.optString("value"));
            return;
        }
        if ("Integer".equals(str2)) {
            bundle.putInt(str, jSONObject.optInt("value"));
            return;
        }
        if ("Short".equals(str2)) {
            Short c2 = c(jSONObject);
            if (c2 != null) {
                bundle.putShort(str, c2.shortValue());
                return;
            }
            return;
        }
        if ("Long".equals(str2)) {
            Long b2 = b(jSONObject);
            if (b2 != null) {
                bundle.putLong(str, b2.longValue());
                return;
            }
            return;
        }
        if ("Float".equals(str2)) {
            Float a2 = a(jSONObject);
            if (a2 != null) {
                bundle.putFloat(str, a2.floatValue());
                return;
            }
            return;
        }
        if ("Double".equals(str2)) {
            bundle.putDouble(str, jSONObject.optDouble("value"));
            return;
        }
        if ("Boolean".equals(str2)) {
            bundle.putBoolean(str, jSONObject.optBoolean("value"));
            return;
        }
        if ("List".equals(str2)) {
            a(str, jSONObject, bundle);
        } else {
            if (!"entity".equals(str2) || (optJSONObject = jSONObject.optJSONObject("value")) == null) {
                return;
            }
            Bundle a3 = a(optJSONObject, new Bundle());
            a3.putInt(b, 0);
            bundle.putBundle(str, a3);
        }
    }

    public void a(String str, Bundle bundle, JSONObject jSONObject) {
        Object obj;
        for (String str2 : bundle.keySet()) {
            Object obj2 = bundle.get(str2);
            if (obj2 != null) {
                try {
                    if (obj2 instanceof Bundle) {
                        Bundle bundle2 = (Bundle) obj2;
                        int i = bundle2.getInt(b, -1);
                        if (i == 1) {
                            JSONArray jSONArray = new JSONArray();
                            a(str, jSONArray, bundle2);
                            obj = jSONArray;
                        } else if (i == 0) {
                            JSONObject jSONObject2 = new JSONObject();
                            a(str, bundle2, jSONObject2);
                            obj = jSONObject2;
                        }
                    } else if (obj2 instanceof Intent) {
                        String str3 = str + "_" + UUID.randomUUID().toString() + "";
                        s.a().a(str3, (Intent) obj2);
                        obj = str3;
                    } else if (obj2 instanceof PendingIntent) {
                        String str4 = str + "_" + UUID.randomUUID().toString() + "";
                        s.a().a(str4, (PendingIntent) obj2);
                        obj = str4;
                    } else {
                        jSONObject.put(str2, obj2);
                    }
                    jSONObject.put(str2, obj);
                } catch (JSONException unused) {
                    com.huawei.hwcloudjs.f.d.b(f6258a, "signInRes2Json put json error", true);
                }
            }
        }
    }

    public Bundle a(JSONObject jSONObject, Bundle bundle) {
        String str;
        JSONObject optJSONObject;
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (next != null && (next instanceof String) && (optJSONObject = jSONObject.optJSONObject((str = next))) != null) {
                a(str, optJSONObject.optString("type"), optJSONObject, bundle);
            }
        }
        return bundle;
    }

    private Short c(JSONObject jSONObject) {
        Object opt = jSONObject.opt("value");
        if (opt == null) {
            return null;
        }
        if (opt instanceof Number) {
            return Short.valueOf(((Number) opt).shortValue());
        }
        if (opt instanceof String) {
            try {
                return Short.valueOf(Short.parseShort((String) opt));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    private Long b(JSONObject jSONObject) {
        Object opt = jSONObject.opt("value");
        if (opt == null) {
            return null;
        }
        if (opt instanceof Number) {
            return Long.valueOf(((Number) opt).longValue());
        }
        if (opt instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) opt));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static final class a extends Exception {
        public a(String str) {
            super(str);
        }

        public a() {
        }
    }

    private void a(String str, JSONObject jSONObject, Bundle bundle) {
        JSONArray optJSONArray = jSONObject.optJSONArray("value");
        if (optJSONArray != null) {
            String optString = jSONObject.optString("subType");
            Bundle bundle2 = new Bundle();
            bundle2.putInt(b, 1);
            bundle2.putInt(f, optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                Object opt = optJSONArray.opt(i);
                if (opt != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("value", opt);
                        jSONObject2.put("type", optString);
                        a(g + i, optString, jSONObject2, bundle2);
                    } catch (JSONException unused) {
                        com.huawei.hwcloudjs.f.d.b(f6258a, "writeList put json error", true);
                    }
                }
            }
            bundle.putBundle(str, bundle2);
        }
    }

    private Float a(JSONObject jSONObject) {
        Object opt = jSONObject.opt("value");
        if (opt == null) {
            return null;
        }
        if (opt instanceof Number) {
            return Float.valueOf(((Number) opt).floatValue());
        }
        if (opt instanceof String) {
            try {
                return Float.valueOf(Float.parseFloat((String) opt));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }
}
