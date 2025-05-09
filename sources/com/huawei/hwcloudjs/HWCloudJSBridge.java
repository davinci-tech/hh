package com.huawei.hwcloudjs;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.hwcloudjs.api.JsParam;
import com.huawei.hwcloudjs.core.JsCallback;
import com.huawei.hwcloudjs.f.h;
import com.huawei.hwcloudjs.service.jsapi.JsCoreApi;
import com.huawei.hwcloudjs.service.jsmsg.NativeMsg;
import com.huawei.hwcloudjs.support.enables.INoProguard;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HWCloudJSBridge implements com.huawei.hwcloudjs.e.b.b<NativeMsg>, INoProguard {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6181a = "HWCloudJSBridge";
    private String c;
    private WebView b = null;
    private final List<String> d = new ArrayList();
    private HashMap<String, String> e = new HashMap<>();

    public void setBridgeId(String str) {
        this.c = str;
    }

    @Override // com.huawei.hwcloudjs.e.b.b
    public boolean onReceive(NativeMsg nativeMsg) {
        NativeMsg nativeMsg2;
        if (nativeMsg != null && !TextUtils.isEmpty(nativeMsg.getType())) {
            String a2 = com.huawei.hwcloudjs.f.b.a(this.b);
            if (TextUtils.isEmpty(a2)) {
                return true;
            }
            String type = nativeMsg.getType();
            if ("channelMessage".equals(type) && (nativeMsg instanceof JsCoreApi.ChannelMessageReq)) {
                JsCoreApi.ChannelMessageReq channelMessageReq = (JsCoreApi.ChannelMessageReq) nativeMsg;
                nativeMsg2 = channelMessageReq;
                if (!this.e.containsKey(com.huawei.hwcloudjs.c.a.j + channelMessageReq.getChannelName())) {
                    return true;
                }
            } else {
                nativeMsg2 = nativeMsg;
                if (!this.e.containsKey(type)) {
                    return true;
                }
            }
            a(nativeMsg2, a2);
        }
        return true;
    }

    @JavascriptInterface
    public void invoke(String str) {
        com.huawei.hwcloudjs.f.d.c(f6181a, "invoke begin", true);
        JSONObject a2 = e.a(str);
        if (a2 == null) {
            com.huawei.hwcloudjs.f.d.b(f6181a, "invoke callJson null", true);
            e.a(this.b, 13);
            return;
        }
        String optString = a2.optString("_appId");
        String optString2 = a2.optString("_method");
        String optString3 = a2.optString("_args");
        String optString4 = a2.optString("_index");
        String a3 = com.huawei.hwcloudjs.f.b.a(this.b);
        if (TextUtils.isEmpty(a3)) {
            com.huawei.hwcloudjs.f.d.b(f6181a, "invoke webViewUrl null", true);
            e.a(this.b, optString4, 9999, this.c);
            return;
        }
        if (TextUtils.isEmpty(optString) && !a(a3)) {
            com.huawei.hwcloudjs.f.d.b(f6181a, "invoke JS_RET_CODE_METHOD_NOT_AUTH", true);
            e.a(this.b, optString4, 10, this.c);
            return;
        }
        if (TextUtils.isEmpty(optString2)) {
            com.huawei.hwcloudjs.f.d.b(f6181a, "invoke method isEmpty", true);
            e.a(this.b, optString4, 13, this.c);
            return;
        }
        if (optString2.equals("listenEvent")) {
            com.huawei.hwcloudjs.f.d.c(f6181a, "invoke method listenEvent", true);
            if (b(optString3)) {
                return;
            }
            com.huawei.hwcloudjs.f.d.b(f6181a, "invoke method listenEvent error", true);
            e.a(this.b, optString4, 13, this.c);
            return;
        }
        com.huawei.hwcloudjs.f.d.c(f6181a, "invoke method:" + optString2, false);
        com.huawei.hwcloudjs.core.a.a a4 = com.huawei.hwcloudjs.core.a.b.a().a(optString2);
        if (a4 != null) {
            a(optString, optString3, optString4, a3, a4);
        } else {
            com.huawei.hwcloudjs.f.d.b(f6181a, "invoke method cannot get!", true);
            e.a(this.b, optString4, 12, this.c);
        }
    }

    public String getBridgeId() {
        return this.c;
    }

    public void detach() {
        this.b.removeJavascriptInterface(Constants.HBS_SDK);
        com.huawei.hwcloudjs.service.jsmsg.a.a().a(this);
        com.huawei.hwcloudjs.f.d.c(f6181a, "detach begin", true);
    }

    @JavascriptInterface
    public void config(String str) {
        WebView webView;
        int i;
        String str2;
        com.huawei.hwcloudjs.f.d.c(f6181a, "config begin", true);
        JSONObject a2 = e.a(str);
        if (a2 == null) {
            str2 = "callJson == null";
        } else {
            String optString = a2.optString("appId");
            if (!TextUtils.isEmpty(optString)) {
                String a3 = com.huawei.hwcloudjs.f.b.a(this.b);
                if (TextUtils.isEmpty(a3)) {
                    com.huawei.hwcloudjs.f.d.b(f6181a, "webViewUrl isEmpty", true);
                    webView = this.b;
                    i = 9999;
                    e.a(webView, i);
                }
                a(optString, a3, com.huawei.hwcloudjs.f.c.a(a2.optJSONArray("needConfig")), a2);
                synchronized (this.d) {
                    this.d.add(optString);
                }
                return;
            }
            str2 = "appId isEmpty";
        }
        com.huawei.hwcloudjs.f.d.b(f6181a, str2, true);
        webView = this.b;
        i = 13;
        e.a(webView, i);
    }

    public void attach(WebView webView) {
        this.b = webView;
        webView.addJavascriptInterface(this, Constants.HBS_SDK);
        com.huawei.hwcloudjs.service.jsmsg.a.a().b(this);
        com.huawei.hwcloudjs.f.d.c(f6181a, "attach begin", true);
    }

    private boolean b(String str) {
        JSONObject a2 = e.a(str);
        if (a2 == null) {
            return false;
        }
        String optString = a2.optString("eventName");
        if (this.e.containsKey(optString)) {
            return true;
        }
        if (optString.length() >= 64 || this.e.size() >= 50) {
            return false;
        }
        this.e.put(optString, "");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, List<String> list, JSONObject jSONObject) {
        com.huawei.hwcloudjs.core.d.a().a(this.b.getContext(), str, str2, this.c, list, jSONObject);
    }

    private boolean a(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3);
        }
        return com.huawei.hwcloudjs.d.a.c.a().a(str, str2, arrayList);
    }

    private boolean a(String str) {
        return com.huawei.hwcloudjs.d.a.c.a().a(str);
    }

    private void a(String str, String str2, List<String> list, JSONObject jSONObject) {
        com.huawei.hwcloudjs.f.d.c(f6181a, "configAuth begin", true);
        b bVar = new b(this, str, str2, list, jSONObject);
        com.huawei.hwcloudjs.d.a.c.a().a(str, str2, new ArrayList(), bVar, this.b.getContext());
    }

    private void a(String str, String str2, String str3, String str4, com.huawei.hwcloudjs.core.a.a aVar) {
        com.huawei.hwcloudjs.f.d.c(f6181a, "callNativeMethod begin", true);
        if (a(str4)) {
            com.huawei.hwcloudjs.f.d.c(f6181a, "url Allow contains!", true);
            a(aVar, str2, str3, str, str4);
        } else if (!com.huawei.hwcloudjs.f.e.b(aVar)) {
            com.huawei.hwcloudjs.f.d.b(f6181a, "method Is not Open!", true);
            e.a(this.b, str3, 12, this.c);
        } else if (a(str, str4, com.huawei.hwcloudjs.f.e.a(aVar))) {
            com.huawei.hwcloudjs.f.d.c(f6181a, "checkAuth OK", true);
            a(aVar, str2, str3, str, str4);
        } else {
            com.huawei.hwcloudjs.f.d.b(f6181a, "checkAuth fail!", true);
            e.a(this.b, str3, 10, this.c);
        }
    }

    private void a(NativeMsg nativeMsg, String str) {
        if (a(str)) {
            com.huawei.hwcloudjs.f.d.c(f6181a, "url Allow contains!!", true);
            e.a(this.b, nativeMsg);
        } else if (nativeMsg.isOpen()) {
            synchronized (this.d) {
                Iterator<String> it = this.d.iterator();
                while (it.hasNext()) {
                    if (a(it.next(), str, nativeMsg.getPermission())) {
                        e.a(this.b, nativeMsg);
                    }
                }
            }
        }
    }

    private void a(com.huawei.hwcloudjs.core.a.a aVar, String str, String str2, String str3, String str4) {
        Object obj;
        com.huawei.hwcloudjs.f.d.c(f6181a, "callNative begin", true);
        if (aVar.b() == null) {
            obj = null;
        } else if (aVar.b() == JsParam.class) {
            com.huawei.hwcloudjs.f.d.c(f6181a, "callNative paramType is JsParam", true);
            obj = new JsParam(str3, str4, this.c);
        } else {
            com.huawei.hwcloudjs.f.d.c(f6181a, "callNative paramType is not JsParam", true);
            obj = new com.huawei.hwcloudjs.core.b().a(str, aVar.b());
            if (obj != null && (obj instanceof JsParam)) {
                obj = e.a((JsParam) obj, str3, str4, this.c);
            }
        }
        aVar.a(obj, new JsCallback(this.b, str2, this.c));
    }

    public HWCloudJSBridge() {
        this.c = "";
        this.c = (h.a() + System.currentTimeMillis()) + "";
    }
}
