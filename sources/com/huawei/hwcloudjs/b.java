package com.huawei.hwcloudjs;

import android.webkit.WebView;
import com.huawei.hwcloudjs.d.a.c;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
class b implements c.a {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f6188a;
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ JSONObject d;
    final /* synthetic */ HWCloudJSBridge e;

    @Override // com.huawei.hwcloudjs.d.a.c.a
    public void a(int i) {
        WebView webView;
        WebView webView2;
        int i2;
        WebView webView3;
        if (i == 0) {
            com.huawei.hwcloudjs.f.d.c("HWCloudJSBridge", "configAuthSUC", true);
            this.e.b(this.f6188a, this.b, this.c, this.d);
            webView3 = this.e.b;
            e.a(webView3);
            return;
        }
        if (i == -2) {
            com.huawei.hwcloudjs.f.d.b("HWCloudJSBridge", "AUTH_FAIL_NO_NET", true);
            webView2 = this.e.b;
            i2 = 12;
        } else if (i == -1) {
            com.huawei.hwcloudjs.f.d.b("HWCloudJSBridge", "AUTH_FAIL", true);
            webView2 = this.e.b;
            i2 = 10;
        } else if (i == -3) {
            com.huawei.hwcloudjs.f.d.b("HWCloudJSBridge", "AUTH_FAIL_NET_ERROR", true);
            webView2 = this.e.b;
            i2 = 11;
        } else if (i == -4) {
            com.huawei.hwcloudjs.f.d.b("HWCloudJSBridge", "AUTH_FAIL_OTHER", true);
            webView2 = this.e.b;
            i2 = 9999;
        } else {
            com.huawei.hwcloudjs.f.d.b("HWCloudJSBridge", "JS_CONFIG_RET_CODE_GW_ERROR" + i, true);
            webView = this.e.b;
            e.a(webView, 14, i + "");
            return;
        }
        e.a(webView2, i2);
    }

    b(HWCloudJSBridge hWCloudJSBridge, String str, String str2, List list, JSONObject jSONObject) {
        this.e = hWCloudJSBridge;
        this.f6188a = str;
        this.b = str2;
        this.c = list;
        this.d = jSONObject;
    }
}
