package com.huawei.health.superrule;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.operation.utils.Constants;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes4.dex */
public class SuperRule {
    private WebView b;
    private boolean c = false;
    private FireCallback<String> d;
    private Context e;

    public SuperRule(Context context) {
        this.e = context;
        WebView webView = new WebView(context);
        this.b = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.b.addJavascriptInterface(this, "jsbridge");
    }

    public void d(String str) {
        if (!this.c) {
            this.b.evaluateJavascript(b("node-rules.min.js"), null);
            this.b.evaluateJavascript(b("SuperRule.js"), null);
            this.c = true;
        }
        this.b.evaluateJavascript(str, null);
    }

    public void b(String str, FireCallback<String> fireCallback) {
        this.d = fireCallback;
        this.b.evaluateJavascript("javascript:fire(" + str + Constants.RIGHT_BRACKET_ONLY, null);
    }

    @JavascriptInterface
    public void setRuleResult(String str) {
        FireCallback<String> fireCallback = this.d;
        if (fireCallback != null) {
            fireCallback.onReceiveValue(str);
        }
    }

    private String b(String str) {
        String str2;
        try {
            InputStream open = this.e.getAssets().open(str);
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                str2 = new String(bArr, "utf8");
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e) {
                        e = e;
                        Log.e("SuperRule", "assertFile2String() IOException" + e.getMessage());
                        return str2;
                    }
                }
            } finally {
            }
        } catch (IOException e2) {
            e = e2;
            str2 = "";
        }
        return str2;
    }
}
