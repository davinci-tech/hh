package com.huawei.agconnect.abtest.a;

import com.huawei.agconnect.abtest.BuildConfig;
import com.huawei.agconnect.common.api.AgcCrypto;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class d {

    /* renamed from: a, reason: collision with root package name */
    private static d f1697a = new d();
    private Map<String, List<a>> b = new HashMap();

    static Set<String> c() {
        Set<String> keySet;
        synchronized (d.class) {
            keySet = f1697a.b.keySet();
        }
        return keySet;
    }

    static void b() {
        synchronized (d.class) {
            JSONObject jSONObject = new JSONObject();
            for (String str : f1697a.b.keySet()) {
                List<a> list = f1697a.b.get(str);
                if (list != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i = 0; i < list.size(); i++) {
                        jSONArray.put(list.get(i).e());
                    }
                    try {
                        jSONObject.put(str, jSONArray);
                    } catch (JSONException e) {
                        Logger.e("ABTest", "json error", e);
                    }
                }
            }
            SharedPrefUtil.getInstance().put(BuildConfig.LIBRARY_PACKAGE_NAME, "experiments", String.class, jSONObject.toString(), AgcCrypto.class);
        }
    }

    static void a(a aVar, String str) {
        synchronized (d.class) {
            List<a> list = f1697a.b.get(str);
            if (list == null) {
                list = new ArrayList<>();
                f1697a.b.put(str, list);
            }
            list.add(aVar);
        }
    }

    static void a(a aVar) {
        synchronized (d.class) {
            if (aVar == null) {
                return;
            }
            Iterator<String> it = f1697a.b.keySet().iterator();
            while (it.hasNext()) {
                List<a> list = f1697a.b.get(it.next());
                if (list != null) {
                    int i = 0;
                    while (true) {
                        if (i >= list.size()) {
                            break;
                        }
                        if (list.get(i).a().equals(aVar.a())) {
                            list.remove(i);
                            break;
                        }
                        i++;
                    }
                }
            }
        }
    }

    static void a() {
        synchronized (d.class) {
            String str = (String) SharedPrefUtil.getInstance().get(BuildConfig.LIBRARY_PACKAGE_NAME, "experiments", String.class, "", AgcCrypto.class);
            if (str != null && str.length() > 0) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONArray jSONArray = jSONObject.getJSONArray(next);
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            a a2 = a.a((JSONObject) jSONArray.get(i));
                            if (a2 != null) {
                                arrayList.add(a2);
                            }
                        }
                        f1697a.b.put(next, arrayList);
                    }
                } catch (JSONException e) {
                    Logger.e("ABTest", "json error", e);
                }
            }
        }
    }

    static List<a> a(String str) {
        ArrayList arrayList;
        synchronized (d.class) {
            List<a> list = f1697a.b.get(str);
            arrayList = list != null ? new ArrayList(list) : new ArrayList();
        }
        return arrayList;
    }

    d() {
    }
}
