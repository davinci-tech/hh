package com.huawei.sim.multisim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import androidx.core.content.FileProvider;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.sim.multisim.MultiSimWebView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.jah;
import defpackage.ncd;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class MultiSimWebView extends BaseActivity {
    private ValueCallback<Uri[]> d;
    private Uri e;
    private HealthProgressBar f;
    private RelativeLayout g;
    private HealthButton i;
    private WebView r;
    private String m = "";
    private String b = "";
    private String k = "";
    private String n = "";

    /* renamed from: a, reason: collision with root package name */
    private String f8708a = Constant.TYPE_PHOTO;
    private String p = "video";
    private boolean h = false;
    private String c = "";
    private List<String> l = new ArrayList(16);
    private WebViewClient o = new WebViewClient() { // from class: com.huawei.sim.multisim.MultiSimWebView.2
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            LogUtil.c("MultiSimWebView", "shouldOverrideUrlLoading, url:", str);
            return super.shouldOverrideUrlLoading(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            LogUtil.c("MultiSimWebView", "shouldOverrideUrlLoading");
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            MultiSimWebView.this.f.setVisibility(0);
            super.onPageStarted(webView, str, bitmap);
            LogUtil.c("MultiSimWebView", "onPageStarted, url:", str, ",mCookie:", MultiSimWebView.this.b);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            LogUtil.c("MultiSimWebView", "onReceivedError");
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webView == null) {
                return;
            }
            webView.stopLoading();
            webView.removeAllViews();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            LogUtil.c("MultiSimWebView", "onReceivedError");
            super.onReceivedError(webView, i, str, str2);
            LogUtil.c("MultiSimWebView", "onReceivedError-end");
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            LogUtil.c("MultiSimWebView", "onPageFinished, url:", str);
            MultiSimWebView.this.f.setVisibility(8);
            webView.loadUrl("javascript:window.local_obj.showSource('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            WebViewInstrumentation.loadUrl(webView, "javascript:window.local_obj.showSource('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            LogUtil.c("MultiSimWebView", "shouldInterceptRequest url", str);
            return super.shouldInterceptRequest(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            LogUtil.c("MultiSimWebView", "shouldInterceptRequest");
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            LogUtil.c("MultiSimWebView", "onReceivedHttpError");
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            LogUtil.c("MultiSimWebView", "onReceivedHttpError-end");
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            LogUtil.c("MultiSimWebView", "onReceivedSslError");
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            LogUtil.c("MultiSimWebView", "onReceivedSslError-end");
        }

        @Override // android.webkit.WebViewClient
        public void onPageCommitVisible(WebView webView, String str) {
            LogUtil.c("MultiSimWebView", "shouldInterceptRequest");
            super.onPageCommitVisible(webView, str);
            LogUtil.c("MultiSimWebView", "onPageCommitVisible end");
        }

        @Override // android.webkit.WebViewClient
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            LogUtil.c("MultiSimWebView", "onTooManyRedirects");
            super.onTooManyRedirects(webView, message, message2);
            LogUtil.c("MultiSimWebView", "onTooManyRedirects-end");
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            LogUtil.c("MultiSimWebView", "shouldOverrideKeyEvent");
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            LogUtil.c("MultiSimWebView", "onFormResubmission");
            super.onFormResubmission(webView, message, message2);
            LogUtil.c("MultiSimWebView", "onFormResubmission-end");
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            LogUtil.c("MultiSimWebView", "doUpdateVisitedHistory");
            super.doUpdateVisitedHistory(webView, str, z);
            LogUtil.c("MultiSimWebView", "doUpdateVisitedHistory-end");
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            LogUtil.c("MultiSimWebView", "shouldInterceptRequest");
            super.onLoadResource(webView, str);
            LogUtil.c("MultiSimWebView", "onLoadResource end");
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            LogUtil.c("MultiSimWebView", "onUnhandledKeyEvent");
            super.onUnhandledKeyEvent(webView, keyEvent);
            LogUtil.c("MultiSimWebView", "onUnhandledKeyEvent-end");
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f, float f2) {
            LogUtil.c("MultiSimWebView", "onScaleChanged");
            super.onScaleChanged(webView, f, f2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            LogUtil.c("MultiSimWebView", "onReceivedLoginRequest");
            super.onReceivedLoginRequest(webView, str, str2, str3);
        }
    };
    private final BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.sim.multisim.MultiSimWebView.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("MultiSimWebView", "mNetBroadcastReceiver intent is null or action is null");
                return;
            }
            LogUtil.a("MultiSimWebView", "mNetBroadcastReceiver action = ", intent.getAction());
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                MultiSimWebView.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.multisim.MultiSimWebView.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MultiSimWebView.this.f();
                    }
                });
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        nsn.cLz_(this);
        LogUtil.a("MultiSimWebView", "onCreate");
        Intent intent = getIntent();
        if (intent == null) {
            sqo.o("intent is null");
            return;
        }
        this.m = intent.getStringExtra("url");
        this.k = intent.getStringExtra("post_data");
        String stringExtra = intent.getStringExtra("req_type");
        this.n = stringExtra;
        LogUtil.a("MultiSimWebView", "onCreate mType:", stringExtra, ",mUrl:", this.m, ",mPostData:", this.k);
        setContentView(R.layout.activity_multi_sim_web_view);
        b();
        WebSettings settings = this.r.getSettings();
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        this.r.addJavascriptInterface(this, "DataActivationController");
        e();
        this.r.setVisibility(0);
        this.r.requestFocus();
        setResult(1);
        f();
        c();
    }

    private void b() {
        this.f = (HealthProgressBar) nsy.cMc_(this, R.id.load_url_progress);
        this.g = (RelativeLayout) findViewById(R.id.no_net_work_layout);
        HealthButton healthButton = (HealthButton) findViewById(R.id.no_net_work_btn);
        this.i = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.multisim.MultiSimWebView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonUtil.q(MultiSimWebView.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.r = (WebView) findViewById(R.id.web_view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (ncd.a(this)) {
            this.f.setVisibility(0);
            this.g.setVisibility(8);
            this.r.setVisibility(0);
            a();
            return;
        }
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.r.setVisibility(8);
    }

    private void a() {
        ThreadPoolManager.d().d("MultiSimWebView", new Runnable() { // from class: com.huawei.sim.multisim.MultiSimWebView.3
            @Override // java.lang.Runnable
            public void run() {
                final String e = jah.c().e("domain_yihaoduozhongduan");
                final String e2 = jah.c().e("domain_zdnlpz_yhdzd_chinamobile");
                final String e3 = jah.c().e("domain_esim_yhdzd_chinamobile");
                final String e4 = jah.c().e("domain_open_10086_cn");
                final String e5 = jah.c().e("domain_url_array");
                MultiSimWebView.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.multisim.MultiSimWebView.3.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MultiSimWebView.this.l.add(e + ":8088/yhdzd/yhdzdLogin");
                        MultiSimWebView.this.l.add(e + ":8088/yhdzd/");
                        MultiSimWebView.this.l.add(e2 + ":9191");
                        MultiSimWebView.this.l.add(e3 + ":8015");
                        MultiSimWebView.this.l.add(e4 + "/websheet/signup");
                        MultiSimWebView.this.a(e5);
                        MultiSimWebView.this.d(MultiSimWebView.this.m, MultiSimWebView.this.k, MultiSimWebView.this.n);
                        LogUtil.a("MultiSimWebView", "validUrlArray size is:", Integer.valueOf(MultiSimWebView.this.l.size()));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimWebView", "validUrlArray is empty");
            sqo.o("validUrlArray is empty");
            return;
        }
        String[] split = str.split(",");
        if (split == null || split.length <= 0) {
            return;
        }
        this.l.addAll(Arrays.asList(split));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, String str3) {
        if (d(str)) {
            b(str, str2, str3);
            return;
        }
        ReleaseLogUtil.d("DEVMGR_MultiSimWebView", "this url is illegal:", str);
        sqo.o("this url is illegal:" + str);
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (Pattern.compile("[<>]").matcher(Normalizer.normalize(str, Normalizer.Form.NFKC)).find()) {
            LogUtil.h("MultiSimWebView", "multi sim url is illegal");
            return false;
        }
        LogUtil.a("MultiSimWebView", "multi sim url is correct");
        return e(str);
    }

    private boolean e(String str) {
        for (int i = 0; i < this.l.size(); i++) {
            if (str.startsWith(this.l.get(i))) {
                return true;
            }
        }
        return false;
    }

    @JavascriptInterface
    public void doneSelected() {
        LogUtil.c("MultiSimWebView", "doneSelected, mUrl:", this.m);
        if (this.h) {
            setResult(2);
        }
        finish();
    }

    @JavascriptInterface
    public void dataPlanAccountUpdatedWithInfo(String str) {
        LogUtil.c("MultiSimWebView", "json:", str);
        this.h = true;
    }

    private void e() {
        WebView webView = this.r;
        WebViewClient webViewClient = this.o;
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, webViewClient);
        } else {
            webView.setWebViewClient(webViewClient);
        }
        this.r.setWebChromeClient(new WebChromeClient() { // from class: com.huawei.sim.multisim.MultiSimWebView.1
            @Override // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView2, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                LogUtil.a("MultiSimWebView", "onShowFileChooser()");
                if (fileChooserParams == null || fileChooserParams.getAcceptTypes() == null || fileChooserParams.getAcceptTypes().length == 0) {
                    ReleaseLogUtil.c("MultiSimWebView", "fileChooserParams is error.");
                    sqo.o("fileChooserParams is error.");
                    return false;
                }
                MultiSimWebView.this.c = fileChooserParams.getAcceptTypes()[0];
                ReleaseLogUtil.e("DEVMGR_MultiSimWebView", "acceptType = ", MultiSimWebView.this.c);
                MultiSimWebView.this.d = valueCallback;
                MultiSimWebView.this.d();
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView2, int i) {
                super.onProgressChanged(webView2, i);
                MultiSimWebView.this.f.setProgress(i);
                webView2.requestFocus();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Intent intent;
        if (TextUtils.isEmpty(this.c)) {
            LogUtil.h("MultiSimWebView", "acceptType is empty.");
            return;
        }
        if (this.c.contains(this.f8708a)) {
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
            css_(intent, "jpg");
        } else if (this.c.contains(this.p)) {
            intent = new Intent("android.media.action.VIDEO_CAPTURE");
            intent.putExtra("android.intent.extra.videoQuality", 1);
            intent.putExtra("android.intent.extra.durationLimit", 6);
            css_(intent, "mp4");
        } else {
            LogUtil.h("MultiSimWebView", "acceptType is error.");
            return;
        }
        startActivityForResult(intent, 1);
    }

    private void css_(Intent intent, String str) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "imageCapture" + System.currentTimeMillis() + "." + str);
        StringBuilder sb = new StringBuilder();
        sb.append(getPackageName());
        sb.append(".fileprovider");
        Uri uriForFile = FileProvider.getUriForFile(this, sb.toString(), new File(file.getPath()));
        this.e = uriForFile;
        LogUtil.a("MultiSimWebView", "captureUri = ", uriForFile);
        intent.putExtra("output", this.e);
        intent.addFlags(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        PermissionUtil.b(this, PermissionUtil.PermissionType.CAMERA, new CustomPermissionAction(this) { // from class: com.huawei.sim.multisim.MultiSimWebView.6
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("MultiSimWebView", "enterVideoPage() onGranted");
                MultiSimWebView.this.i();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("MultiSimWebView", "enterVideoPage() onDenied");
                MultiSimWebView.this.j();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("MultiSimWebView", "enterVideoPage() onForeverDenied");
                MultiSimWebView.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        nsn.cLJ_(this, PermissionUtil.PermissionType.CAMERA, new View.OnClickListener() { // from class: nbz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiSimWebView.this.cst_(view);
            }
        }, new View.OnClickListener() { // from class: nby
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiSimWebView.this.csu_(view);
            }
        });
    }

    public /* synthetic */ void cst_(View view) {
        nsn.ak(this);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void csu_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.e("DEVMGR_MultiSimWebView", "resultCode = ", Integer.valueOf(i2), "requestCode = ", Integer.valueOf(i));
        if (i == 1 && i2 == -1) {
            try {
                try {
                    if (this.d == null) {
                        LogUtil.h("MultiSimWebView", "mFilePathCallback is null.");
                        sqo.o("mFilePathCallback is null.");
                        ValueCallback<Uri[]> valueCallback = this.d;
                        if (valueCallback != null) {
                            valueCallback.onReceiveValue(new Uri[]{Uri.parse("")});
                            this.d = null;
                            return;
                        }
                        return;
                    }
                    LogUtil.a("MultiSimWebView", "acceptType = ", this.c);
                    if (!TextUtils.isEmpty(this.c) && this.c.contains(this.f8708a)) {
                        LogUtil.a("MultiSimWebView", "captureUri" + this.e);
                        this.d.onReceiveValue(new Uri[]{this.e});
                    } else if (TextUtils.isEmpty(this.c) || !this.c.contains(this.p)) {
                        LogUtil.a("MultiSimWebView", "acceptType is invalid");
                    } else if (intent != null) {
                        LogUtil.a("MultiSimWebView", "data : " + intent.getData());
                        this.d.onReceiveValue(new Uri[]{intent.getData()});
                    }
                    this.d = null;
                } catch (Exception e) {
                    LogUtil.b("MultiSimWebView", TrackConstants$Events.EXCEPTION + ExceptionUtils.d(e));
                    ValueCallback<Uri[]> valueCallback2 = this.d;
                    if (valueCallback2 == null) {
                        return;
                    } else {
                        valueCallback2.onReceiveValue(new Uri[]{Uri.parse("")});
                    }
                }
            } catch (Throwable th) {
                ValueCallback<Uri[]> valueCallback3 = this.d;
                if (valueCallback3 != null) {
                    valueCallback3.onReceiveValue(new Uri[]{Uri.parse("")});
                    this.d = null;
                }
                throw th;
            }
        }
        ValueCallback<Uri[]> valueCallback4 = this.d;
        if (valueCallback4 != null) {
            valueCallback4.onReceiveValue(new Uri[]{Uri.parse("")});
            this.d = null;
        }
    }

    public void b(String str, String str2, String str3) {
        LogUtil.c("MultiSimWebView", "loadUrl type:", str3);
        try {
            if ("WS".equals(str3)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("carrierPostData", str2);
                String jSONObject2 = jSONObject.toString();
                WebView webView = this.r;
                String str4 = str + "?param=" + URLEncoder.encode(jSONObject2, "utf-8");
                webView.loadUrl(str4);
                WebViewInstrumentation.loadUrl(webView, str4);
            } else {
                WebView webView2 = this.r;
                String str5 = str + "?carrierPostData=" + URLEncoder.encode(str2, "utf-8");
                webView2.loadUrl(str5);
                WebViewInstrumentation.loadUrl(webView2, str5);
            }
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("MultiSimWebView", "UnsupportedEncodingException");
        } catch (JSONException unused2) {
            LogUtil.b("MultiSimWebView", "JSONException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        g();
    }

    private void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.j, intentFilter, LocalBroadcast.c, null);
    }

    private void g() {
        try {
            LogUtil.a("MultiSimWebView", "unregisterNetBroadcast");
            unregisterReceiver(this.j);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("MultiSimWebView", "unregisterNetBroadcast IllegalArgumentException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
