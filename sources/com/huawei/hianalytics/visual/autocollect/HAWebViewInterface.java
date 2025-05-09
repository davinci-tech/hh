package com.huawei.hianalytics.visual.autocollect;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.b;
import com.huawei.hianalytics.visual.g0;
import com.huawei.hianalytics.visual.k0;
import com.huawei.hianalytics.visual.n0;
import com.huawei.hianalytics.visual.s0;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HAWebViewInterface {
    public static final String NETWORK = "network";
    public static final String SESSION_ID = "sessionId";
    public static final String TAG = "HAWebViewInterface";

    @JavascriptInterface
    public boolean enableH5Collection() {
        return b.a().c();
    }

    @JavascriptInterface
    public String haGetDeviceInfo(String str) {
        char c;
        JSONObject jSONObject = new JSONObject();
        try {
            HiLog.d(TAG, "haGetDeviceInfo");
        } catch (Exception unused) {
            HiLog.w(TAG, "haGetDeviceInfo Exception");
        }
        if (str == null) {
            return "";
        }
        String[] split = str.split("\\|");
        if (split.length == 0) {
            return "";
        }
        for (String str2 : split) {
            int hashCode = str2.hashCode();
            if (hashCode != 607796817) {
                if (hashCode == 1843485230 && str2.equals(NETWORK)) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (str2.equals("sessionId")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                jSONObject.put("sessionId", k0.b.f3932a.f3930a);
            } else if (c != 1) {
                HiLog.d(TAG, "haGetDeviceInfo unknown type:" + str2);
            } else {
                jSONObject.put(NETWORK, g0.c(b.a().g()));
            }
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public String haGetVisualizedConfig() {
        try {
            HiLog.d(TAG, "haGetVisualizedConfig");
            if (!b.a().b() && b.a().c()) {
                return s0.a.f3949a.d;
            }
            return null;
        } catch (Exception unused) {
            HiLog.w(TAG, "haGetVisualizedConfig Exception");
            return null;
        }
    }

    @JavascriptInterface
    public void reportJsEvent(String str) {
        if (b.a().b() || !b.a().c() || str == null || str.isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).getString("data")).getJSONObject("data");
            JSONObject jSONObject2 = jSONObject.getJSONObject("preset_properties");
            if (jSONObject2.has("$net_type")) {
                jSONObject2.remove("$net_type");
            }
            String string = jSONObject.getString("event");
            if (jSONObject.has("v_event") && !TextUtils.isEmpty(jSONObject.getString("v_event"))) {
                jSONObject.put("event", jSONObject.getString("v_event"));
                jSONObject.remove("v_event");
            }
            b.a().a(string, jSONObject);
        } catch (JSONException unused) {
            HiLog.w(TAG, "the jsEvent is not a json, please check");
        }
    }

    @JavascriptInterface
    public String setIdsite() {
        return n0.a(b.a().g());
    }
}
