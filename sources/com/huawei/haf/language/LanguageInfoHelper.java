package com.huawei.haf.language;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class LanguageInfoHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f2122a = new HashSet();

    private LanguageInfoHelper() {
    }

    static void a(Map<String, String> map, Set<String> set) {
        if (d(map, set)) {
            LogUtil.c("HAF_LanguageInfo", "initLanguageInfo size=", Integer.valueOf(map.size()), ", preset=", Integer.valueOf(set.size()), ", second=", Integer.valueOf(f2122a.size()));
            if (!map.isEmpty() && !set.isEmpty()) {
                return;
            }
        }
        map.clear();
        set.clear();
        f2122a.clear();
        LogUtil.e("HAF_LanguageInfo", "initLanguageInfo fail, the default language configuration will be used. please check.");
        c(map, set);
    }

    static boolean a(String str) {
        return f2122a.contains(str);
    }

    private static JSONObject e() throws IOException, JSONException {
        InputStream open = BaseApplication.e().getAssets().open("language_plugins/dynamic_language_config.json");
        if (open == null) {
            throw new IOException("not language info in assets.");
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject(FileUtils.a(open, 5242880L));
            LogUtil.c("HAF_LanguageInfo", "read language info from assets, times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return jSONObject;
        } finally {
            FileUtils.d(open);
        }
    }

    private static boolean d(Map<String, String> map, Set<String> set) {
        try {
            JSONArray jSONArray = e().getJSONArray("all_lang");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("name");
                String string2 = jSONObject.getString("uuid");
                String optString = jSONObject.optString("flag");
                if (!"unused".equals(optString)) {
                    map.put(string, string2);
                    if (!TextUtils.isEmpty(optString)) {
                        if ("preset".equals(optString)) {
                            set.add(string2);
                        } else if ("second".equals(optString)) {
                            f2122a.add(string2);
                        }
                    }
                }
            }
            return true;
        } catch (IOException | JSONException e) {
            LogUtil.a("HAF_LanguageInfo", "parseLanguageInfo ex=", LogUtil.a(e));
            return false;
        }
    }

    private static void c(Map<String, String> map, Set<String> set) {
        map.put("en", "e7edf7db-00a5-4e3a-9cff-032a854e29ef");
        set.add("e7edf7db-00a5-4e3a-9cff-032a854e29ef");
    }
}
