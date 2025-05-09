package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class yc {

    /* renamed from: a, reason: collision with root package name */
    private final List<String> f17753a;
    private final String b;
    private final String c;
    private final Map<String, yi> d;

    private yc(String str, String str2, Map<String, yi> map, List<String> list) {
        this.b = str;
        this.c = str2;
        this.d = map;
        this.f17753a = list;
    }

    public String d() {
        return this.b;
    }

    String a() {
        return this.c;
    }

    public List<String> b() {
        return this.f17753a;
    }

    Collection<yi> e() {
        return this.d.values();
    }

    yi c(String str) {
        return this.d.get(str);
    }

    public boolean c() {
        Iterator<yi> it = e().iterator();
        while (it.hasNext()) {
            if (!it.next().q()) {
                return false;
            }
        }
        return true;
    }

    static yc d(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("appBundleId");
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        String optString2 = jSONObject.optString("appVersionName");
        if (TextUtils.isEmpty(optString2)) {
            return null;
        }
        return new yc(optString, optString2, e(optString2, jSONObject), c(jSONObject));
    }

    private static List<String> b(JSONObject jSONObject) throws JSONException {
        List<String> emptyList = Collections.emptyList();
        JSONArray optJSONArray = jSONObject.optJSONArray("abiFilters");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            emptyList = new ArrayList<>(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                String string = optJSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    emptyList.add(string);
                }
            }
        }
        return emptyList;
    }

    private static List<String> c(JSONObject jSONObject) throws JSONException {
        List<String> emptyList = Collections.emptyList();
        JSONArray optJSONArray = jSONObject.optJSONArray("updateModules");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            emptyList = new ArrayList<>(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                String string = optJSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    emptyList.add(string);
                }
            }
        }
        return emptyList;
    }

    private static Map<String, yi> e(String str, JSONObject jSONObject) throws JSONException {
        Map<String, yi> emptyMap = Collections.emptyMap();
        JSONArray optJSONArray = jSONObject.optJSONArray("modules");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            List<String> b = b(jSONObject);
            emptyMap = new HashMap<>(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String optString = jSONObject2.optString("moduleName");
                if (!TextUtils.isEmpty(optString)) {
                    yi e = yi.e(str, optString, b, jSONObject2);
                    LogUtil.c("Bundle_ModuleDetails", e.toString());
                    emptyMap.put(optString, e);
                }
            }
        }
        return emptyMap;
    }
}
