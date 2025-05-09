package com.huawei.phoneservice.feedback.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.f;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.phoneservice.faq.base.util.q;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes9.dex */
public class FeedbackDispatchActivity extends FeedBaseActivity {
    private int j;
    private ViewGroup k;
    private FeedbackNoticeView m;
    private WebView n;
    private ProgressBar o;
    private ValueCallback<Uri> p;
    private e r;
    private boolean s;
    private ValueCallback<Uri[]> t;
    private WebChromeClient.CustomViewCallback x;
    public static final FrameLayout.LayoutParams e = new FrameLayout.LayoutParams(-1, -1);
    public static final int b = 2054;
    protected Handler i = new Handler();
    private String f = "com.android.gallery3d";
    private String g = "com.huawei.gallery";
    private String l = null;
    private String q = null;
    private Map<String, String> y = new HashMap();
    private Queue<String> w = new LinkedList();
    private WebChromeClient v = new b();
    private WebViewClient u = new c();
    protected Runnable h = new a();

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_dispatch_layout;
    }

    protected void e() {
        Queue<String> queue = this.w;
        if (queue == null || queue.size() <= 0) {
            return;
        }
        String poll = this.w.poll();
        this.q = poll;
        setTitle(poll);
    }

    public void b() {
        i.b("FeedDispatchActivity", "onPageTimeOut :" + this.l);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        setTitle(getTitle());
        super.onResume();
        WebView webView = this.n;
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        WebView webView = this.n;
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.n.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.s) {
            this.n.setVisibility(8);
            this.n.clearView();
            this.n.removeAllViews();
            this.m.setVisibility(8);
        }
        this.n.goBack();
        e();
        return true;
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.n != null) {
            this.i.removeCallbacks(this.h);
            if (this.n.getParent() != null) {
                this.k.removeView(this.n);
            }
        }
        q.cdu_(this.n);
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            if (decorView instanceof ViewGroup) {
                ((ViewGroup) decorView).removeAllViews();
            }
        }
        super.onDestroy();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        d();
        if (!TextUtils.isEmpty(this.q)) {
            setTitle(this.q);
        }
        super.onCreate(bundle);
        this.j = getWindow().getDecorView().getSystemUiVisibility();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ValueCallback<Uri[]> valueCallback;
        ValueCallback<Uri[]> valueCallback2;
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            if (i == 0) {
                if (this.p != null) {
                    this.p.onReceiveValue(i2 == -1 ? new SafeIntent(intent).getData() : null);
                    this.p = null;
                    return;
                }
                return;
            }
            if (i != 100 || (valueCallback = this.t) == null) {
                return;
            }
            valueCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(i2, intent));
            this.t = null;
        }
        if (i == 0) {
            ValueCallback<Uri> valueCallback3 = this.p;
            if (valueCallback3 != null) {
                valueCallback3.onReceiveValue(null);
                this.p = null;
                return;
            }
            return;
        }
        if (i != 100 || (valueCallback2 = this.t) == null) {
            return;
        }
        valueCallback2.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(i2, null));
        this.t = null;
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        this.k = (ViewGroup) findViewById(android.R.id.content);
        this.n = (WebView) findViewById(R.id.feedback_dispatch_web_view);
        this.o = (ProgressBar) findViewById(R.id.fbsdkProgressbar);
        this.m = (FeedbackNoticeView) findViewById(R.id.feedback_dispatch_noticeView);
        a();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.m.setOnClickListener(new d());
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        String[] feedbackOpenTypeConfig = ModuleConfigUtils.getFeedbackOpenTypeConfig();
        String str = feedbackOpenTypeConfig[0];
        String str2 = feedbackOpenTypeConfig[1];
        if (!com.huawei.phoneservice.faq.base.util.b.b(this)) {
            this.m.c(FaqConstants.FaqErrorCode.INTERNET_ERROR);
            return;
        }
        if (!FaqConstants.OPEN_TYPE_OUT.equals(str)) {
            if (!FaqConstants.OPEN_TYPE_IN.equals(str) || l.e(str2)) {
                return;
            }
            WebView webView = this.n;
            webView.loadUrl(str2);
            WebViewInstrumentation.loadUrl(webView, str2);
            return;
        }
        if (!l.e(str2)) {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setData(Uri.parse(str2));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                i.e("FeedDispatchActivity", "startActivity Exception");
            }
        }
        finish();
    }

    private void a() {
        WebSettings settings = this.n.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        q.cdv_(this.n);
        settings.setCacheMode(-1);
        settings.setDomStorageEnabled(true);
        this.n.setHorizontalScrollBarEnabled(false);
        WebView webView = this.n;
        WebViewClient webViewClient = this.u;
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, webViewClient);
        } else {
            webView.setWebViewClient(webViewClient);
        }
        this.n.setWebChromeClient(this.v);
    }

    private void d() {
        this.s = false;
        this.l = null;
        this.q = null;
        SafeIntent safeIntent = new SafeIntent(getIntent());
        try {
            if (!TextUtils.isEmpty(safeIntent.getStringExtra("url"))) {
                this.l = safeIntent.getStringExtra("url");
            }
            if (safeIntent.getIntExtra("title", 0) != 0) {
                this.q = getResources().getString(safeIntent.getIntExtra("title", 0));
            }
            if (!TextUtils.isEmpty(this.q) || TextUtils.isEmpty(safeIntent.getStringExtra("title"))) {
                return;
            }
            this.q = safeIntent.getStringExtra("title");
        } catch (Resources.NotFoundException | ClassCastException e2) {
            i.c("FeedDispatchActivity", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.n.getSettings().setJavaScriptEnabled(false);
            this.s = true;
        } else {
            this.n.getSettings().setJavaScriptEnabled(true);
            q.cdw_(this.n);
            this.s = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        getWindow().getDecorView().setSystemUiVisibility(z ? this.j : b);
        getWindow().setFlags(z ? 0 : 1024, 1024);
    }

    class b extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (FeedbackDispatchActivity.this.t != null) {
                FeedbackDispatchActivity.this.t.onReceiveValue(null);
                FeedbackDispatchActivity.this.t = null;
            }
            FeedbackDispatchActivity.this.t = valueCallback;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType(Constants.IMAGE_TYPE);
            try {
                FeedbackDispatchActivity.this.startActivityForResult(intent, 100);
                return true;
            } catch (ActivityNotFoundException e) {
                FeedbackDispatchActivity.this.t = null;
                i.c("FeedDispatchActivity", e);
                return false;
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            if (FeedbackDispatchActivity.this.r != null) {
                if (FeedbackDispatchActivity.this.x != null) {
                    FeedbackDispatchActivity.this.x.onCustomViewHidden();
                    return;
                }
                return;
            }
            FeedbackDispatchActivity.this.x = customViewCallback;
            FeedbackDispatchActivity.this.n.setVisibility(8);
            FeedbackDispatchActivity.this.setRequestedOrientation(6);
            FeedbackDispatchActivity.this.e(false);
            FrameLayout frameLayout = (FrameLayout) FeedbackDispatchActivity.this.getWindow().getDecorView();
            FeedbackDispatchActivity.this.r = new e(FeedbackDispatchActivity.this);
            e eVar = FeedbackDispatchActivity.this.r;
            FrameLayout.LayoutParams layoutParams = FeedbackDispatchActivity.e;
            eVar.addView(view, layoutParams);
            frameLayout.addView(FeedbackDispatchActivity.this.r, layoutParams);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            if (!TextUtils.isEmpty(FeedbackDispatchActivity.this.q) || TextUtils.isEmpty(str) || FeedbackDispatchActivity.this.s) {
                return;
            }
            FeedbackDispatchActivity.this.setTitle(str);
            FeedbackDispatchActivity.this.y.put(webView.getUrl(), str);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i > 10) {
                FeedbackDispatchActivity.this.n.setVisibility(0);
            }
            if (FeedbackDispatchActivity.this.o == null || FeedbackDispatchActivity.this.l == null || !FeedbackDispatchActivity.this.l.equals(webView.getUrl())) {
                return;
            }
            FeedbackDispatchActivity.this.o.setProgress(i, true);
            if (i >= 80) {
                FeedbackDispatchActivity.this.o.setVisibility(8);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            if (FeedbackDispatchActivity.this.r != null) {
                if (FeedbackDispatchActivity.this.x != null) {
                    FeedbackDispatchActivity.this.x.onCustomViewHidden();
                }
                FrameLayout frameLayout = (FrameLayout) FeedbackDispatchActivity.this.getWindow().getDecorView();
                FeedbackDispatchActivity.this.r.removeAllViews();
                frameLayout.removeView(FeedbackDispatchActivity.this.r);
                FeedbackDispatchActivity.this.r = null;
                FeedbackDispatchActivity.this.setRequestedOrientation(1);
                FeedbackDispatchActivity.this.e(true);
                FeedbackDispatchActivity.this.n.setVisibility(0);
            }
        }

        @Override // android.webkit.WebChromeClient
        public View getVideoLoadingProgressView() {
            return new TextView(FeedbackDispatchActivity.this);
        }

        b() {
        }
    }

    class c extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            FeedbackNoticeView feedbackNoticeView;
            FaqConstants.FaqErrorCode faqErrorCode;
            super.onReceivedError(webView, i, str, str2);
            FeedbackDispatchActivity.this.s = true;
            if (TextUtils.isEmpty(FeedbackDispatchActivity.this.q)) {
                FeedbackDispatchActivity.this.setTitle("");
            }
            if (com.huawei.phoneservice.faq.base.util.b.b(FeedbackDispatchActivity.this)) {
                feedbackNoticeView = FeedbackDispatchActivity.this.m;
                faqErrorCode = FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR;
            } else {
                feedbackNoticeView = FeedbackDispatchActivity.this.m;
                faqErrorCode = FaqConstants.FaqErrorCode.INTERNET_ERROR;
            }
            feedbackNoticeView.c(faqErrorCode);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            CharSequence title;
            FeedbackDispatchActivity feedbackDispatchActivity;
            FeedbackDispatchActivity.this.s = false;
            FeedbackDispatchActivity.this.l = str;
            FeedbackDispatchActivity.this.a(str);
            super.onPageStarted(webView, str, bitmap);
            FeedbackDispatchActivity feedbackDispatchActivity2 = FeedbackDispatchActivity.this;
            feedbackDispatchActivity2.i.postDelayed(feedbackDispatchActivity2.h, 20000L);
            if (FeedbackDispatchActivity.this.o != null) {
                FeedbackDispatchActivity.this.o.setVisibility(0);
            }
            if (TextUtils.isEmpty(FeedbackDispatchActivity.this.q)) {
                if (FeedbackDispatchActivity.this.y.containsKey(str)) {
                    feedbackDispatchActivity = FeedbackDispatchActivity.this;
                    title = (CharSequence) feedbackDispatchActivity.y.get(str);
                } else {
                    title = webView.getTitle();
                    if (TextUtils.isEmpty(title) || webView.getUrl() == null || webView.getUrl().contains(title)) {
                        try {
                            FeedbackDispatchActivity feedbackDispatchActivity3 = FeedbackDispatchActivity.this;
                            feedbackDispatchActivity3.setTitle(feedbackDispatchActivity3.getResources().getString(R.string._2130850857_res_0x7f023429));
                            return;
                        } catch (Resources.NotFoundException e) {
                            i.c("FeedDispatchActivity", e.getMessage());
                            return;
                        }
                    }
                    FeedbackDispatchActivity.this.y.put(webView.getUrl(), title);
                    feedbackDispatchActivity = FeedbackDispatchActivity.this;
                }
                feedbackDispatchActivity.setTitle(title);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            FeedbackDispatchActivity feedbackDispatchActivity = FeedbackDispatchActivity.this;
            feedbackDispatchActivity.i.removeCallbacks(feedbackDispatchActivity.h);
            if (FeedbackDispatchActivity.this.o != null && FeedbackDispatchActivity.this.l != null && FeedbackDispatchActivity.this.l.equals(str)) {
                FeedbackDispatchActivity.this.o.setVisibility(8);
                FeedbackDispatchActivity.this.o.setProgress(0);
            }
            if (FeedbackDispatchActivity.this.s) {
                return;
            }
            FeedbackDispatchActivity.this.n.setVisibility(0);
            FeedbackDispatchActivity.this.m.setVisibility(8);
        }

        c() {
        }
    }

    class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            FeedbackDispatchActivity feedbackDispatchActivity = FeedbackDispatchActivity.this;
            Handler handler = feedbackDispatchActivity.i;
            if (handler != null) {
                handler.removeCallbacks(feedbackDispatchActivity.h);
            }
            FeedbackDispatchActivity.this.b();
        }

        a() {
        }
    }

    class d implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (s.cdx_(view)) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            f.cdi_(view);
            FeedbackDispatchActivity feedbackDispatchActivity = FeedbackDispatchActivity.this;
            feedbackDispatchActivity.i.removeCallbacks(feedbackDispatchActivity.h);
            FeedbackDispatchActivity.this.n.clearView();
            FeedbackDispatchActivity.this.n.removeAllViews();
            FeedbackDispatchActivity.this.l();
            ViewClickInstrumentation.clickOnView(view);
        }

        d() {
        }
    }

    static class e extends FrameLayout {
        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return true;
        }

        e(Context context) {
            super(context);
            setBackgroundResource(android.R.color.black);
        }
    }
}
