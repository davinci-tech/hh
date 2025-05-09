package com.huawei.hwcloudjs;

import android.webkit.WebView;
import com.huawei.hwcloudjs.api.JsParam;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.service.jsmsg.NativeMsg;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6215a = "JSUtils";

    public static void a(WebView webView, String str, int i, String str2) {
        new JsCallback(webView, str, str2).failure(i);
    }

    public static void a(WebView webView, String str) {
        webView.post(new d(webView, str));
    }

    public static void a(WebView webView, NativeMsg nativeMsg) {
        a(webView, com.huawei.hwcloudjs.c.a.a(nativeMsg));
    }

    public static void a(WebView webView, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            jSONObject.put("message", str);
            a(webView, com.huawei.hwcloudjs.c.a.a(com.huawei.hwcloudjs.c.a.h, jSONObject));
        } catch (JSONException unused) {
        }
    }

    public static void a(WebView webView, int i) {
        a(webView, i, "");
    }

    public static void a(WebView webView) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 0);
        } catch (JSONException unused) {
            com.huawei.hwcloudjs.f.d.b(f6215a, "jsReady  put data error", true);
        }
        a(webView, com.huawei.hwcloudjs.c.a.a(com.huawei.hwcloudjs.c.a.i, jSONObject));
    }

    public static JSONObject a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            com.huawei.hwcloudjs.f.d.b(f6215a, "json2Object error", true);
            return null;
        }
    }

    public static JsParam a(JsParam jsParam, String str, String str2, String str3) {
        if (jsParam != null) {
            jsParam.setAppId(str);
            jsParam.setWebUrl(str2);
            jsParam.setBridgeId(str3);
        }
        return jsParam;
    }
}
