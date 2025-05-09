package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class c1 {
    private static void b(Map<String, ?> map) {
        Iterator<Map.Entry<String, ?>> it = map.entrySet().iterator();
        Set<String> a2 = n1.a(q0.b());
        while (it.hasNext()) {
            if (!a2.contains(it.next().getKey())) {
                it.remove();
            }
        }
    }

    public static Map<String, List<b1>> b(Context context, String str, String str2) {
        String a2 = d.a(context, str, str2, "");
        HashMap hashMap = new HashMap();
        a(str2, a2, hashMap);
        return hashMap;
    }

    private static void a(String str, String str2, Map<String, List<b1>> map) {
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str2);
            if (jSONArray.length() == 0) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                b1 b1Var = new b1();
                try {
                    b1Var.a(jSONArray.getJSONObject(i));
                    arrayList.add(b1Var);
                } catch (JSONException unused) {
                    v.e("hmsSdk", "JSON Exception happened when create data for report - readDataToRecord");
                }
            }
            map.put(str, arrayList);
        } catch (JSONException unused2) {
            v.e("hmsSdk", "When events turn to JSONArray,JSON Exception has happened");
        }
    }

    private static Map<String, List<b1>> a(Map<String, List<b1>> map, Map<String, List<b1>> map2) {
        if (map.size() == 0 && map2.size() == 0) {
            return new HashMap();
        }
        if (map.size() == 0) {
            return map2;
        }
        if (map2.size() == 0) {
            return map;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.putAll(map2);
        return hashMap;
    }

    private static Map<String, List<b1>> a(Map<String, ?> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() instanceof String) {
                a(key, (String) entry.getValue(), hashMap);
            }
        }
        return hashMap;
    }

    public static Map<String, List<b1>> a(Context context, String str, String str2) {
        Map<String, List<b1>> a2;
        Map<String, List<b1>> a3;
        int h = q0.h() * 1048576;
        if (c0.a(context, "cached_v2_1", h)) {
            v.f("hmsSdk", "cached event reach max size, delete it");
            d.a(context, "cached_v2_1", new String[0]);
        }
        if (c0.a(context, "stat_v2_1", h)) {
            v.f("hmsSdk", "stat event reach max size, delete it");
            d.a(context, "stat_v2_1", new String[0]);
        }
        if ("alltype".equals(str2) || TextUtils.isEmpty(str)) {
            v.a("hmsSdk", "read all event records");
            a2 = a(context, "stat_v2_1");
            a3 = a(context, "cached_v2_1");
        } else {
            String a4 = n1.a(str, str2);
            a2 = b(context, "stat_v2_1", a4);
            a3 = b(context, "cached_v2_1", a4);
        }
        return a(a2, a3);
    }

    public static Map<String, List<b1>> a(Context context, String str) {
        if (context == null) {
            return null;
        }
        Map<String, ?> a2 = d.a(context, str);
        b(a2);
        return a(a2);
    }
}
