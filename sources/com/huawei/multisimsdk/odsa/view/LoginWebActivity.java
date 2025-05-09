package com.huawei.multisimsdk.odsa.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.multisimsdk.odsa.presenter.ILoginPresenter;
import com.huawei.operation.utils.Constants;
import defpackage.loq;
import defpackage.lpd;
import defpackage.lpm;
import java.math.BigDecimal;

/* loaded from: classes5.dex */
public class LoginWebActivity extends Activity implements ILoginView, ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private int f6541a;
    private Context b;
    private View c;
    private final Handler d = new Handler(Looper.getMainLooper()) { // from class: com.huawei.multisimsdk.odsa.view.LoginWebActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                loq.c("LoginWebActivity", "EVENT_DISPOSE_WEBVIEW");
                LoginWebActivity.this.e();
            } else {
                super.handleMessage(message);
            }
        }
    };
    private int e;
    private WebView g;
    private ProgressBar h;
    private int i;
    private ILoginPresenter j;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        loq.c("LoginWebActivity", "onCreate start");
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.sign_web);
        Intent intent = getIntent();
        if (intent != null) {
            this.i = intent.getIntExtra("slotId", -1);
        }
        this.b = getApplicationContext();
        this.j = new lpm(this, this.i);
        this.g = (WebView) findViewById(R.id.sign_webView);
        this.h = (ProgressBar) findViewById(R.id.progress_bar);
        d();
        c();
        this.j.startLogin();
    }

    private void d() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        this.e = new BigDecimal(point.y).divide(new BigDecimal("3"), 0, 4).intValue();
        View findViewById = findViewById(R.id.sign_webView);
        this.c = findViewById;
        findViewById.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        int height = this.c.getRootView().getHeight() - this.c.getHeight();
        loq.c("LoginWebActivity", "mPreHeight:" + this.f6541a + " heightDiff:" + height);
        if (this.f6541a == height) {
            return;
        }
        this.f6541a = height;
        if (height > this.e) {
            loq.c("LoginWebActivity", "hide activity");
            getWindow().addFlags(8192);
        } else {
            loq.c("LoginWebActivity", "show activity");
            getWindow().clearFlags(8192);
        }
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public Context getAppContext() {
        return this.b;
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.j.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.j.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        loq.c("LoginWebActivity", "destroy web view");
        e();
        this.j.onDestroy();
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public void finishView() {
        finish();
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public void startAuthActivity(Intent intent, int i) {
        startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        loq.c("LoginWebActivity", "onActivityResult->requestCode: " + i + " resultCode: " + i2);
        StringBuilder sb = new StringBuilder("onActivityResult->data: ");
        sb.append(intent == null ? Constants.NULL : intent.getExtras());
        loq.e("LoginWebActivity", sb.toString());
        if (i == 1) {
            this.j.processAuthResult(i, i2, intent);
        } else {
            loq.g("LoginWebActivity", "unknown request code!");
        }
    }

    @Override // com.huawei.multisimsdk.odsa.view.ILoginView
    public void loadUrl(final String str) {
        loq.e("LoginWebActivity", "loadUrl:" + str);
        if (this.g != null) {
            loq.e("LoginWebActivity", "loadUrl: begin " + str);
            this.g.post(new Runnable() { // from class: com.huawei.multisimsdk.odsa.view.LoginWebActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    WebView webView = LoginWebActivity.this.g;
                    String str2 = str;
                    webView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(webView, str2);
                }
            });
        }
    }

    private void c() {
        this.g.getSettings().setSupportZoom(true);
        this.g.getSettings().setUseWideViewPort(true);
        this.g.getSettings().setAllowFileAccessFromFileURLs(false);
        this.g.getSettings().setJavaScriptEnabled(true);
        this.g.getSettings().setDomStorageEnabled(true);
        this.g.getSettings().setGeolocationEnabled(false);
        this.g.getSettings().setAllowContentAccess(true);
        this.g.getSettings().setLoadsImagesAutomatically(true);
        this.g.getSettings().setAllowFileAccess(false);
        this.g.getSettings().setPluginState(WebSettings.PluginState.ON);
        this.g.addJavascriptInterface(new e(), "HTMLOUT");
        WebView.setWebContentsDebuggingEnabled(false);
        WebView webView = this.g;
        c cVar = new c();
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, cVar);
        } else {
            webView.setWebViewClient(cVar);
        }
        this.g.setWebChromeClient(new WebChromeClient() { // from class: com.huawei.multisimsdk.odsa.view.LoginWebActivity.4
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                loq.d("LoginWebActivity", "onProgressChanged newProgress = " + i);
                if (i > 80) {
                    LoginWebActivity.this.e(false);
                }
                super.onProgressChanged(webView2, i);
                webView2.requestFocus();
            }
        });
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        WebView webView = this.g;
        if (webView != null && i == 4) {
            if (webView.canGoBack()) {
                this.g.goBack();
                return true;
            }
            loq.c("LoginWebActivity", "user click back");
            if (lpd.a() != null) {
                lpd.a().d(1056);
            }
            finish();
            overridePendingTransition(R.anim._2130772073_res_0x7f010069, R.anim._2130772076_res_0x7f01006c);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        if (z) {
            this.g.setVisibility(8);
            this.h.setVisibility(0);
        } else {
            this.g.setVisibility(0);
            this.h.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        WebView webView = this.g;
        if (webView != null) {
            webView.clearHistory();
            this.g.clearCache(true);
            this.g.removeAllViews();
            this.g.destroy();
            this.g = null;
        }
    }

    class e {
        e() {
        }

        @JavascriptInterface
        public void processHtml(String str) {
            loq.e("LoginWebActivity", "processHtml " + str);
            String tokenValue = LoginWebActivity.this.j.getTokenValue(str);
            loq.e("LoginWebActivity", "processHtml token " + tokenValue);
            if (TextUtils.isEmpty(tokenValue)) {
                return;
            }
            LoginWebActivity.this.j.processAuthResultFromWebView(tokenValue);
            LoginWebActivity.this.d.sendEmptyMessage(1);
            loq.c("LoginWebActivity", "processHtml end ");
        }
    }

    class c extends WebViewClient {
        c() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            loq.e("LoginWebActivity", "onPageStarted url = " + str);
            super.onPageStarted(webView, str, bitmap);
            LoginWebActivity.this.e(true);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            loq.e("LoginWebActivity", "onPageFinished url = " + str);
            super.onPageFinished(webView, str);
            webView.loadUrl("javascript:window.HTMLOUT.processHtml(document.body.innerHTML);");
            WebViewInstrumentation.loadUrl(webView, "javascript:window.HTMLOUT.processHtml(document.body.innerHTML);");
            if (str.contains("state=") && str.contains("code=")) {
                LoginWebActivity.this.j.setUrlWithAuthorizationCodeFromWebView(str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            loq.d("LoginWebActivity", "onReceivedError error =" + webResourceError.getErrorCode() + ", des = " + ((Object) webResourceError.getDescription()));
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webView == null) {
                return;
            }
            webView.stopLoading();
            webView.removeAllViews();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            loq.d("LoginWebActivity", "onReceivedSslError error =" + sslError.toString());
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            loq.d("LoginWebActivity", "shouldOverrideUrlLoading request = " + webResourceRequest);
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
