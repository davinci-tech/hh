package defpackage;

import android.content.Context;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class iqv {
    private static Map<String, String> d = Collections.synchronizedMap(new HashMap(4));

    public static String b(Context context, String str) {
        if (context != null && str != null && !"".equals(str)) {
            if (d.containsKey(str)) {
                return d.get(str);
            }
            StringBuffer stringBuffer = new StringBuffer();
            try {
                InputStream open = context.getAssets().open("grs_app_global_route_config.json");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open, StandardCharsets.UTF_8));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer.append(readLine);
                        } finally {
                        }
                    }
                    bufferedReader.close();
                    if (open != null) {
                        open.close();
                    }
                    try {
                        JSONArray jSONArray = new JSONObject(stringBuffer.toString()).getJSONArray("services");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            if ("com.huawei.health".equals(jSONObject.getString("name"))) {
                                String d2 = d(jSONObject.getJSONArray("servings"), d(GRSManager.a(context).getCountryCode(), jSONObject.getJSONArray("countryGroups")), str);
                                d.put(str, d2);
                                return d2;
                            }
                        }
                    } catch (JSONException e) {
                        ReleaseLogUtil.d("HiH_GrsJsonUtil", "json exception", LogAnonymous.b((Throwable) e));
                    }
                    return "";
                } finally {
                }
            } catch (IOException e2) {
                ReleaseLogUtil.d("HiH_GrsJsonUtil", "io Exception", LogAnonymous.b((Throwable) e2));
            }
        }
        return "";
    }

    private static String d(String str, JSONArray jSONArray) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.getJSONObject(i).getString("countries").contains(str)) {
                return jSONArray.getJSONObject(i).getString("id");
            }
        }
        return "";
    }

    private static String d(JSONArray jSONArray, String str, String str2) throws JSONException {
        if (jSONArray.length() != 0 && !"".equals(str)) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (str.equals(jSONObject.getString("countryGroup"))) {
                    return jSONObject.getJSONObject("addresses").getString(str2);
                }
            }
        }
        return "";
    }
}
