package com.huawei.hms.iapfull.webpay.thirdpay;

import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.hms.iapfull.webpay.WebViewActivity;
import com.huawei.hms.iapfull.y0;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<WebViewActivity> f4785a;
    private final String b;

    @JavascriptInterface
    public void wechatAppSign(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            y0.a("IAPFullSDK", "wechatAppSign sign params error");
            return;
        }
        WebViewActivity webViewActivity = this.f4785a.get();
        if (webViewActivity == null) {
            y0.a("IAPFullSDK", "wechatAppSign activity null");
            return;
        }
        Intent intent = new Intent(webViewActivity, (Class<?>) ThirdPayActivity.class);
        intent.putExtra("wechat_sign_appId", str2);
        intent.putExtra("wechat_sign_preEntrustwebId", str);
        webViewActivity.startActivityForResult(intent, 10002);
    }

    @JavascriptInterface
    public String getReturnUrl4ChannelSign(String str) {
        y0.b("IAPFullSDK", "getReturnUrl4ChannelSign " + str);
        if (!Objects.equals(str, String.valueOf(5))) {
            return "";
        }
        return "iapfull://" + this.b + "/alipaysignresult";
    }

    @JavascriptInterface
    public void alipaySign(String str) {
        if (TextUtils.isEmpty(str)) {
            y0.a("IAPFullSDK", "alipaySign sign params error");
            return;
        }
        WebViewActivity webViewActivity = this.f4785a.get();
        if (webViewActivity == null) {
            y0.a("IAPFullSDK", "alipaySign activity null");
            return;
        }
        Intent intent = new Intent(webViewActivity, (Class<?>) ThirdPayActivity.class);
        intent.putExtra("alipay_sign_url", str);
        webViewActivity.startActivityForResult(intent, 10001);
    }

    public a(WebViewActivity webViewActivity, String str) {
        this.f4785a = new WeakReference<>(webViewActivity);
        this.b = str;
    }
}
