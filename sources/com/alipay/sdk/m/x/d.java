package com.alipay.sdk.m.x;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.alipay.sdk.m.j.d;
import com.alipay.sdk.m.x.e;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.kh;
import defpackage.kl;
import defpackage.lv;
import defpackage.md;
import defpackage.mh;
import defpackage.ml;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class d extends com.alipay.sdk.m.x.c implements e.f, e.g, e.h {
    public boolean e;
    public final lv f;
    public boolean g;
    public boolean h;
    public String i;
    public com.alipay.sdk.m.x.e j;
    public mh o;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.f879a.finish();
        }
    }

    public class b extends e {
        public final /* synthetic */ com.alipay.sdk.m.x.e c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(com.alipay.sdk.m.x.e eVar) {
            super(null);
            this.c = eVar;
        }

        @Override // com.alipay.sdk.m.x.d.e, android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.c.a();
            d.this.h = false;
        }
    }

    public class c extends e {
        public final /* synthetic */ com.alipay.sdk.m.x.e b;
        public final /* synthetic */ String e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(com.alipay.sdk.m.x.e eVar, String str) {
            super(null);
            this.b = eVar;
            this.e = str;
        }

        @Override // com.alipay.sdk.m.x.d.e, android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            d.this.removeView(this.b);
            d.this.j.e(this.e);
            d.this.h = false;
        }
    }

    /* renamed from: com.alipay.sdk.m.x.d$d, reason: collision with other inner class name */
    public class RunnableC0015d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f880a;
        public final /* synthetic */ SslErrorHandler b;

        /* renamed from: com.alipay.sdk.m.x.d$d$c */
        public class c implements DialogInterface.OnClickListener {
            public c() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                RunnableC0015d.this.b.cancel();
                kl.c(d.this.f, "net", "SSLDenied", "2");
                kh.a(kh.a());
                RunnableC0015d.this.f880a.finish();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }

        public RunnableC0015d(Activity activity, SslErrorHandler sslErrorHandler) {
            this.f880a = activity;
            this.b = sslErrorHandler;
        }

        @Override // java.lang.Runnable
        public void run() {
            ml.bo_(this.f880a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，请安装支付宝后重试。", "确定", new c(), null, null);
        }
    }

    public static abstract class e implements Animation.AnimationListener {
        public e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public static class g implements d.a {
        public final String c;
        public final f d;

        public g(f fVar, String str) {
            this.d = fVar;
            this.c = str;
        }

        @Override // com.alipay.sdk.m.j.d.a
        public void a(boolean z, JSONObject jSONObject, String str) {
            try {
                this.d.a(new JSONObject().put("success", z).put("random", this.c).put("code", jSONObject).put("status", str));
            } catch (JSONException unused) {
            }
        }
    }

    public d(Activity activity, lv lvVar, String str) {
        super(activity, str);
        this.e = true;
        this.i = "GET";
        this.h = false;
        this.j = null;
        this.o = new mh();
        this.f = lvVar;
        g();
    }

    private boolean e() {
        synchronized (this) {
            if (this.o.e()) {
                this.f879a.finish();
            } else {
                this.h = true;
                com.alipay.sdk.m.x.e eVar = this.j;
                this.j = this.o.a();
                TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
                translateAnimation.setDuration(400L);
                translateAnimation.setFillAfter(false);
                translateAnimation.setAnimationListener(new b(eVar));
                eVar.setAnimation(translateAnimation);
                removeView(eVar);
                addView(this.j);
            }
        }
        return true;
    }

    private void f() {
        synchronized (this) {
            Activity activity = this.f879a;
            com.alipay.sdk.m.x.e eVar = this.j;
            if (activity != null && eVar != null) {
                if (this.e) {
                    activity.finish();
                } else {
                    eVar.e("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
                }
            }
        }
    }

    private boolean g() {
        synchronized (this) {
            try {
                com.alipay.sdk.m.x.e eVar = new com.alipay.sdk.m.x.e(this.f879a, this.f, new e.c(!a(), !a()));
                this.j = eVar;
                eVar.setChromeProxy(this);
                this.j.setWebClientProxy(this);
                this.j.setWebEventProxy(this);
                addView(this.j);
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    private void h() {
        synchronized (this) {
            WebView webView = this.j.getWebView();
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                mh mhVar = this.o;
                if (mhVar == null || mhVar.e()) {
                    a(false);
                } else {
                    e();
                }
            }
        }
    }

    private void i() {
        com.alipay.sdk.m.x.e eVar = this.j;
        if (eVar != null) {
            WebView webView = eVar.getWebView();
            webView.loadUrl("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
            WebViewInstrumentation.loadUrl(webView, "javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        }
    }

    @Override // com.alipay.sdk.m.x.c
    public void c() {
        synchronized (this) {
            this.j.a();
            this.o.b();
        }
    }

    @Override // com.alipay.sdk.m.x.e.g
    public boolean d(com.alipay.sdk.m.x.e eVar, String str) {
        synchronized (this) {
            kl.a(this.f, "biz", "h5ld", SystemClock.elapsedRealtime() + "|" + md.f(str));
            if (!TextUtils.isEmpty(str) && !str.endsWith(".apk")) {
                i();
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent;
        synchronized (this) {
            onInterceptTouchEvent = this.h ? true : super.onInterceptTouchEvent(motionEvent);
        }
        return onInterceptTouchEvent;
    }

    @Override // com.alipay.sdk.m.x.c
    public boolean b() {
        synchronized (this) {
            Activity activity = this.f879a;
            if (activity == null) {
                return true;
            }
            if (!a()) {
                if (!this.h) {
                    f();
                }
                return true;
            }
            com.alipay.sdk.m.x.e eVar = this.j;
            if (eVar != null && eVar.getWebView() != null) {
                if (!eVar.getWebView().canGoBack()) {
                    kh.a(kh.a());
                    activity.finish();
                } else if (d()) {
                    com.alipay.sdk.m.j.c b2 = com.alipay.sdk.m.j.c.b(com.alipay.sdk.m.j.c.NETWORK_ERROR.b());
                    kh.a(kh.c(b2.b(), b2.a(), ""));
                    activity.finish();
                }
                return true;
            }
            activity.finish();
            return true;
        }
    }

    @Override // com.alipay.sdk.m.x.e.f
    public void c(com.alipay.sdk.m.x.e eVar, String str) {
        synchronized (this) {
            if (!str.startsWith("http") && !eVar.getUrl().endsWith(str)) {
                this.j.getTitle().setText(str);
            }
        }
    }

    public void d(String str, String str2, boolean z) {
        synchronized (this) {
            this.i = str2;
            this.j.getTitle().setText(str);
            this.e = z;
        }
    }

    public boolean d() {
        return this.g;
    }

    private void a(boolean z) {
        synchronized (this) {
            kh.a(z);
            this.f879a.finish();
        }
    }

    @Override // com.alipay.sdk.m.x.c
    public void a(String str) {
        synchronized (this) {
            if ("POST".equals(this.i)) {
                this.j.e(str, null);
            } else {
                this.j.e(str);
            }
            com.alipay.sdk.m.x.c.a(this.j.getWebView());
        }
    }

    @Override // com.alipay.sdk.m.x.e.f
    public boolean a(com.alipay.sdk.m.x.e eVar, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        synchronized (this) {
            if (str2.startsWith("<head>") && str2.contains("sdk_result_code:")) {
                this.f879a.runOnUiThread(new a());
            }
            jsPromptResult.cancel();
        }
        return true;
    }

    public static class f {

        /* renamed from: a, reason: collision with root package name */
        public final String f881a;
        public final JSONObject b;
        public boolean c = false;
        public final String d;
        public final WeakReference<com.alipay.sdk.m.x.e> e;

        public f(com.alipay.sdk.m.x.e eVar, String str, String str2, JSONObject jSONObject) {
            this.e = new WeakReference<>(eVar);
            this.f881a = str;
            this.d = str2;
            this.b = jSONObject;
        }

        public void a(JSONObject jSONObject) {
            com.alipay.sdk.m.x.e eVar;
            if (this.c || (eVar = (com.alipay.sdk.m.x.e) md.b(this.e)) == null) {
                return;
            }
            this.c = true;
            eVar.e(String.format("javascript:window.AlipayJSBridge.callBackFromNativeFunc('%s','%s');", b(this.d), b(jSONObject.toString())));
        }

        public static String b(String str) {
            return TextUtils.isEmpty(str) ? "" : str.replace("'", "");
        }
    }

    @Override // com.alipay.sdk.m.x.e.g
    public boolean a(com.alipay.sdk.m.x.e eVar, String str) {
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Activity activity = this.f879a;
            if (activity == null) {
                return true;
            }
            if (md.bg_(this.f, str, activity)) {
                return true;
            }
            if (str.startsWith("alipayjsbridge://")) {
                c(str.substring(17));
            } else if (TextUtils.equals(str, "sdklite://h5quit")) {
                a(false);
            } else if (!str.startsWith("http://") && !str.startsWith("https://")) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                    intent.setData(Uri.parse(str));
                    activity.startActivity(intent);
                } catch (Throwable th) {
                    kl.e(this.f, "biz", th);
                }
            } else {
                this.j.e(str);
            }
            return true;
        }
    }

    private boolean e(String str, String str2) {
        synchronized (this) {
            com.alipay.sdk.m.x.e eVar = this.j;
            try {
                com.alipay.sdk.m.x.e eVar2 = new com.alipay.sdk.m.x.e(this.f879a, this.f, new e.c(!a(), !a()));
                this.j = eVar2;
                eVar2.setChromeProxy(this);
                this.j.setWebClientProxy(this);
                this.j.setWebEventProxy(this);
                if (!TextUtils.isEmpty(str2)) {
                    this.j.getTitle().setText(str2);
                }
                this.h = true;
                this.o.d(eVar);
                TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
                translateAnimation.setDuration(400L);
                translateAnimation.setFillAfter(false);
                translateAnimation.setAnimationListener(new c(eVar, str));
                this.j.setAnimation(translateAnimation);
                addView(this.j);
            } catch (Throwable unused) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alipay.sdk.m.x.e.g
    public boolean a(com.alipay.sdk.m.x.e eVar, int i, String str, String str2) {
        synchronized (this) {
            this.g = true;
            kl.c(this.f, "net", "webError", "onReceivedError:" + i + "|" + str2);
            eVar.getRefreshButton().setVisibility(0);
        }
        return false;
    }

    @Override // com.alipay.sdk.m.x.e.g
    public boolean a(com.alipay.sdk.m.x.e eVar, SslErrorHandler sslErrorHandler, SslError sslError) {
        synchronized (this) {
            Activity activity = this.f879a;
            if (activity == null) {
                return true;
            }
            kl.c(this.f, "net", "SSLError", "2-" + sslError);
            activity.runOnUiThread(new RunnableC0015d(activity, sslErrorHandler));
            return true;
        }
    }

    private void c(String str, String str2, String str3) {
        char c2;
        synchronized (this) {
            com.alipay.sdk.m.x.e eVar = this.j;
            if (eVar == null) {
                return;
            }
            JSONObject i = md.i(str3);
            f fVar = new f(eVar, str, str2, i);
            Context context = eVar.getContext();
            try {
                String str4 = fVar.f881a;
                switch (str4.hashCode()) {
                    case -1785164386:
                        if (str4.equals("canUseTaoLogin")) {
                            c2 = '\b';
                            break;
                        }
                        c2 = 65535;
                        break;
                    case -552487705:
                        if (str4.equals("taoLogin")) {
                            c2 = '\t';
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3015911:
                        if (str4.equals("back")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3127582:
                        if (str4.equals("exit")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 110371416:
                        if (str4.equals("title")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1085444827:
                        if (str4.equals("refresh")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1703426986:
                        if (str4.equals("pushWindow")) {
                            c2 = 6;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1906413305:
                        if (str4.equals("backButton")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1947723784:
                        if (str4.equals("sdkInfo")) {
                            c2 = 7;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 2033767917:
                        if (str4.equals("refreshButton")) {
                            c2 = 5;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                switch (c2) {
                    case 0:
                        if (i.has("title")) {
                            eVar.getTitle().setText(i.optString("title", ""));
                            break;
                        }
                        break;
                    case 1:
                        eVar.getWebView().reload();
                        break;
                    case 2:
                        h();
                        break;
                    case 3:
                        kh.a(i.optString("result", null));
                        a(i.optBoolean("success", false));
                        break;
                    case 4:
                        eVar.getBackButton().setVisibility(i.optBoolean(ParamConstants.CallbackMethod.ON_SHOW, true) ? 0 : 4);
                        break;
                    case 5:
                        eVar.getRefreshButton().setVisibility(i.optBoolean(ParamConstants.CallbackMethod.ON_SHOW, true) ? 0 : 4);
                        break;
                    case 6:
                        e(i.optString("url"), i.optString("title", ""));
                        break;
                    case 7:
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sdk_version", "15.8.14");
                        jSONObject.put("app_name", this.f.e());
                        jSONObject.put("app_version", this.f.d());
                        fVar.a(jSONObject);
                        break;
                    case '\b':
                        String url = eVar.getUrl();
                        if (!md.a(this.f, url)) {
                            kl.c(this.f, "biz", "jsUrlErr", url);
                            break;
                        } else {
                            JSONObject jSONObject2 = new JSONObject();
                            boolean b2 = com.alipay.sdk.m.j.d.b(this.f, context);
                            jSONObject2.put("enabled", b2);
                            kl.a(this.f, "biz", "TbChk", String.valueOf(b2));
                            fVar.a(jSONObject2);
                            break;
                        }
                    case '\t':
                        String url2 = eVar.getUrl();
                        if (!md.a(this.f, url2)) {
                            kl.c(this.f, "biz", "jsUrlErr", url2);
                            break;
                        } else {
                            String optString = i.optString("random");
                            JSONObject optJSONObject = i.optJSONObject("options");
                            if (!TextUtils.isEmpty("random") && optJSONObject != null) {
                                String optString2 = optJSONObject.optString("url");
                                String optString3 = optJSONObject.optString("action");
                                if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3) && (context instanceof Activity)) {
                                    com.alipay.sdk.m.j.d.aT_(this.f, (Activity) context, 1010, optString2, optString3, new g(fVar, optString));
                                    break;
                                }
                            }
                        }
                        break;
                }
            } catch (Throwable th) {
                kl.a(this.f, "biz", "jInfoErr", th, str);
            }
        }
    }

    @Override // com.alipay.sdk.m.x.e.g
    public boolean b(com.alipay.sdk.m.x.e eVar, String str) {
        synchronized (this) {
            kl.a(this.f, "biz", "h5ldd", SystemClock.elapsedRealtime() + "|" + md.f(str));
            i();
            eVar.getRefreshButton().setVisibility(0);
        }
        return true;
    }

    private void c(String str) {
        synchronized (this) {
            Map<String, String> b2 = md.b(this.f, str);
            if (str.startsWith("callNativeFunc")) {
                c(b2.get("func"), b2.get("cbId"), b2.get("data"));
            } else if (str.startsWith("onBack")) {
                h();
            } else if (str.startsWith("setTitle") && b2.containsKey("title")) {
                this.j.getTitle().setText(b2.get("title"));
            } else if (str.startsWith("onRefresh")) {
                this.j.getWebView().reload();
            } else if (str.startsWith("showBackButton") && b2.containsKey("bshow")) {
                this.j.getBackButton().setVisibility(TextUtils.equals("true", b2.get("bshow")) ? 0 : 4);
            } else if (str.startsWith("onExit")) {
                kh.a(b2.get("result"));
                a(TextUtils.equals("true", b2.get("bsucc")));
            } else if (str.startsWith("onLoadJs")) {
                this.j.e("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
            }
        }
    }

    @Override // com.alipay.sdk.m.x.e.h
    public void b(com.alipay.sdk.m.x.e eVar) {
        synchronized (this) {
            f();
        }
    }

    @Override // com.alipay.sdk.m.x.e.h
    public void a(com.alipay.sdk.m.x.e eVar) {
        synchronized (this) {
            eVar.getWebView().reload();
            eVar.getRefreshButton().setVisibility(4);
        }
    }
}
