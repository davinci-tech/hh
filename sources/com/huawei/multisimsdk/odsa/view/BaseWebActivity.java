package com.huawei.multisimsdk.odsa.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.huawei.agconnect.apms.instrument.okhttp3.OkHttp3Instrumentation;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.multisimsdk.odsa.response.OdsaResponseParam;
import defpackage.lop;
import defpackage.loq;
import defpackage.lpr;
import defpackage.lpz;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes5.dex */
public class BaseWebActivity extends Activity implements ILoginView, ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private int f6537a;
    private View b;
    private ProgressBar c;
    private Context d;
    private int e;
    private WebView i;
    private e j;

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public void finishView() {
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public Context getAppContext() {
        return null;
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public void startAuthActivity(Intent intent, int i) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        loq.c("BaseWebActivity", "onCreate start for no title.");
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.sign_web);
        this.i = (WebView) findViewById(R.id.sign_webView);
        this.c = (ProgressBar) findViewById(R.id.progress_bar);
        this.d = getApplicationContext();
        c();
        d();
        Intent intent = getIntent();
        if (intent == null) {
            loq.b("BaseWebActivity", "intent is null");
            return;
        }
        OdsaResponseParam odsaResponseParam = (OdsaResponseParam) ((Bundle) intent.getParcelableExtra("OdsaResponseParam")).getParcelable("OdsaResponseParamBundle");
        if (odsaResponseParam == null) {
            loq.b("BaseWebActivity", "BaseWebActivity odsaResponseParam is null");
            return;
        }
        String operationType = odsaResponseParam.getOperationType();
        String str = odsaResponseParam.getsubscriptionServiceContentsType();
        loq.c("BaseWebActivity", odsaResponseParam.toString());
        if (lpr.b[2].equals(operationType)) {
            a(odsaResponseParam.getNotEnabledUrl(), odsaResponseParam.getNotEnabledUserData(), str);
        } else if (lpr.b[1].equals(operationType)) {
            a(odsaResponseParam.getSubscriptionServiceUrl(), odsaResponseParam.getSubscriptionServiceUserData(), str);
        }
    }

    private void a(String str, String str2, String str3) {
        if (lop.e("ODSA_SUPPORT_SEND_DATA_DIRECTLY", (Boolean) false).booleanValue()) {
            e(str, str2);
            return;
        }
        if (!lop.e("ODSA_SUPPORT_DYNAMIC_LOAD_WEBVIEW", (Boolean) false).booleanValue()) {
            c(str, str2);
        } else if (TextUtils.isEmpty(str3)) {
            a(str, str2);
        } else {
            c(str, str2);
        }
    }

    private void c() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        this.f6537a = new BigDecimal(point.y).divide(new BigDecimal("3"), 0, 4).intValue();
        View findViewById = findViewById(R.id.sign_webView);
        this.b = findViewById;
        findViewById.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        int height = this.b.getRootView().getHeight() - this.b.getHeight();
        loq.c("BaseWebActivity", "mPreHeight:" + this.e + " heightDiff:" + height);
        if (this.e == height) {
            return;
        }
        this.e = height;
        if (height > this.f6537a) {
            loq.c("BaseWebActivity", "hide activity");
            getWindow().addFlags(8192);
        } else {
            loq.c("BaseWebActivity", "show activity");
            getWindow().clearFlags(8192);
        }
    }

    private void d() {
        loq.e("BaseWebActivity", "initWebView");
        WebSettings settings = this.i.getSettings();
        settings.setSupportZoom(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(-1);
        settings.setAllowFileAccess(false);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        this.i.addJavascriptInterface(new d(), "ODSAServiceFlow");
        loq.c("BaseWebActivity", "initWebView, currentUserAgent = " + this.i.getSettings().getUserAgentString());
        e eVar = new e();
        this.j = eVar;
        WebView webView = this.i;
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, eVar);
        } else {
            webView.setWebViewClient(eVar);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        cookieManager.flush();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(this.i, true);
        this.i.setWebChromeClient(new WebChromeClient() { // from class: com.huawei.multisimsdk.odsa.view.BaseWebActivity.5
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                loq.d("BaseWebActivity", "onProgressChanged newProgress = " + i);
                if (i > 80) {
                    BaseWebActivity.this.a(false);
                }
                super.onProgressChanged(webView2, i);
                webView2.requestFocus();
            }
        });
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public void loadUrl(final String str) {
        loq.e("BaseWebActivity", "loadUrl:" + str);
        if (this.i != null) {
            loq.e("BaseWebActivity", "loadUrl: begin " + str);
            this.i.post(new Runnable() { // from class: com.huawei.multisimsdk.odsa.view.BaseWebActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    WebView webView = BaseWebActivity.this.i;
                    String str2 = str;
                    webView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(webView, str2);
                }
            });
        }
    }

    public void a(String str, String str2) {
        final String str3 = str + "?" + str2;
        loq.e("BaseWebActivity", "loadUrl load Url = " + str3);
        WebView webView = this.i;
        if (webView != null) {
            webView.post(new Runnable() { // from class: com.huawei.multisimsdk.odsa.view.BaseWebActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    WebView webView2 = BaseWebActivity.this.i;
                    String str4 = str3;
                    webView2.loadUrl(str4);
                    WebViewInstrumentation.loadUrl(webView2, str4);
                }
            });
        }
    }

    private void e(final String str, final String str2) {
        WebView webView = this.i;
        if (webView != null) {
            webView.post(new Runnable() { // from class: com.huawei.multisimsdk.odsa.view.BaseWebActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        BaseWebActivity.this.j.d(str2);
                        BaseWebActivity.this.i.postUrl(str, str2.getBytes("UTF-8"));
                    } catch (UnsupportedEncodingException unused) {
                        loq.b("BaseWebActivity", "postUrlDirectly UnsupportedEncodingException");
                    }
                }
            });
        }
    }

    public void c(final String str, String str2) {
        JSONArray jSONArray;
        String[] split = str2 != null ? str2.split("=", 2) : new String[0];
        if (split.length < 2) {
            loq.b("BaseWebActivity", "base64Data.length < MIN_ARRAY_LENGTH");
            return;
        }
        JSONObject jSONObject = null;
        if ("encodedValue".equals(split[0])) {
            byte[] decode = Base64.decode(split[1], 0);
            StringBuilder sb = new StringBuilder();
            for (byte b : decode) {
                sb.append((char) b);
            }
            try {
                Object nextValue = new JSONTokener(sb.toString()).nextValue();
                if (nextValue instanceof JSONObject) {
                    jSONObject = (JSONObject) nextValue;
                    jSONArray = null;
                } else if (nextValue instanceof JSONArray) {
                    jSONArray = (JSONArray) nextValue;
                } else {
                    loq.b("BaseWebActivity", "json doesn't instanceof JSONObject nor JSONArray");
                    return;
                }
            } catch (JSONException unused) {
                loq.e("BaseWebActivity", "JSONException");
                jSONArray = null;
            }
        } else {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(split[0], split[1]);
            } catch (JSONException unused2) {
                loq.e("BaseWebActivity", "JSONException");
            }
            jSONArray = null;
            jSONObject = jSONObject2;
        }
        final String e2 = e(jSONObject, jSONArray);
        if (e2 == null) {
            loq.c("BaseWebActivity", "postUrl: json is null");
            return;
        }
        WebView webView = this.i;
        if (webView != null) {
            webView.post(new Runnable() { // from class: com.huawei.multisimsdk.odsa.view.BaseWebActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    BaseWebActivity.this.j.d(e2);
                    BaseWebActivity.this.i.postUrl(str, EncodingUtils.getBytes(e2, "BASE64"));
                }
            });
        }
    }

    private static String e(JSONObject jSONObject, JSONArray jSONArray) {
        if (jSONObject == null && jSONArray == null) {
            return null;
        }
        String jSONObject2 = jSONObject != null ? jSONObject.toString() : "";
        if (jSONArray != null) {
            jSONObject2 = jSONArray.toString();
        }
        if (TextUtils.isEmpty(jSONObject2)) {
            return null;
        }
        return jSONObject2;
    }

    class d {
        d() {
        }

        @JavascriptInterface
        public void profileReadyWithDefaultSmdp(String str, String str2) {
            loq.b("BaseWebActivity", "JS profileReadyWithDefaultSmdp defaultSmdpAddress=" + str + ", iccid=", str2);
            Intent intent = BaseWebActivity.this.getIntent();
            intent.putExtra("ActivationCode", "LPA:1$" + str + SampleEvent.SEPARATOR);
            BaseWebActivity.this.setResult(1, intent);
            BaseWebActivity.this.b();
        }

        @JavascriptInterface
        public void profileReadyWithActivationCode(String str) {
            loq.b("BaseWebActivity", "JS profileReadyWithActivationCode activationCode=", str);
            Intent intent = BaseWebActivity.this.getIntent();
            intent.putExtra("ActivationCode", str);
            BaseWebActivity.this.setResult(1, intent);
            BaseWebActivity.this.b();
        }

        @JavascriptInterface
        public void dismissFlow() {
            BaseWebActivity.this.finish();
            loq.c("BaseWebActivity", "JS dismissFlow");
        }

        @JavascriptInterface
        public void finishFlow() {
            BaseWebActivity.this.finish();
            loq.c("BaseWebActivity", "JS finishFlow");
        }

        @JavascriptInterface
        public void checkProfileServiceStatus() {
            BaseWebActivity.this.setResult(2, BaseWebActivity.this.getIntent());
            loq.c("BaseWebActivity", "JS checkProfileServiceStatus");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (lop.e("ODSA_SUPPORT_AUTO_FINISH_WEBVIEW", (Boolean) true).booleanValue()) {
            finish();
        }
    }

    class e extends WebViewClient {
        private String e;

        e() {
        }

        public void d(String str) {
            this.e = str;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            loq.e("BaseWebActivity", "onPageStarted url = " + str);
            super.onPageStarted(webView, str, bitmap);
            BaseWebActivity.this.a(true);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            loq.e("BaseWebActivity", "onPageFinished url = " + str);
            Log.e("BaseWebActivity", "onPageFinished Cookies = " + CookieManager.getInstance().getCookie(str));
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            loq.d("BaseWebActivity", "onReceivedError error =" + webResourceError.getErrorCode() + ", des = " + ((Object) webResourceError.getDescription()));
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webView == null) {
                return;
            }
            webView.removeAllViews();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            loq.d("BaseWebActivity", "onReceivedSslError error =" + sslError.toString());
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            loq.d("BaseWebActivity", "shouldOverrideUrlLoading request = " + webResourceRequest);
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            Request build;
            Call newCall;
            String uri = webResourceRequest.getUrl().toString();
            loq.c("BaseWebActivity", "shouldInterceptRequest , request.getUrl=" + webResourceRequest.getUrl());
            if (this.e == null) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
            try {
                OkHttpClient build2 = new OkHttpClient.Builder().cookieJar(new lpz()).build();
                RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), this.e);
                loq.c("BaseWebActivity", "shouldInterceptRequest body:" + create.toString());
                this.e = null;
                Request.Builder post = new Request.Builder().url(uri).addHeader("METHOD", "POST").post(create);
                if (post instanceof Request.Builder) {
                    Request.Builder builder = post;
                    build = OkHttp3Instrumentation.build(post);
                } else {
                    build = post.build();
                }
                if (build2 instanceof OkHttpClient) {
                    OkHttpClient okHttpClient = build2;
                    newCall = OkHttp3Instrumentation.newCall(build2, build);
                } else {
                    newCall = build2.newCall(build);
                }
                Response execute = newCall.execute();
                Headers headers = execute.headers();
                for (int i = 0; i < headers.size(); i++) {
                    loq.e("BaseWebActivity", "shouldInterceptRequest i=" + i + ", name=" + headers.name(i) + ", value=" + headers.value(i));
                }
                loq.e("BaseWebActivity", "shouldInterceptRequest response.header:" + execute.header("Set-cookie"));
                loq.c("BaseWebActivity", "response.body=" + execute.body().byteStream());
                return new WebResourceResponse("text/html", execute.header("content-encoding", "utf-8"), execute.body().byteStream());
            } catch (IOException e) {
                loq.e("BaseWebActivity", "occur an IOException:", e);
                return super.shouldInterceptRequest(webView, webResourceRequest);
            } catch (Exception e2) {
                loq.e("BaseWebActivity", "occur an exception:", e2);
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.i.setVisibility(8);
            this.c.setVisibility(0);
        } else {
            this.i.setVisibility(0);
            this.c.setVisibility(8);
        }
    }

    private void e() {
        WebView webView = this.i;
        if (webView != null) {
            webView.clearHistory();
            this.i.clearCache(true);
            this.i.removeAllViews();
            this.i.destroy();
            this.i = null;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        e();
        loq.c("BaseWebActivity", "destroy web view");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
