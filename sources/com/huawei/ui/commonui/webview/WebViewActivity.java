package com.huawei.ui.commonui.webview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.jdx;
import defpackage.nrt;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class WebViewActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f8992a;
    private String b;
    private String c;
    private boolean d;
    private CustomTitleBar e;
    private CustomTextAlertDialog f;
    private HealthProgressBar g;
    private HealthButton h;
    private String i;
    private c l;
    private LinearLayout o;
    private a p;
    private WebView r;
    private LinearLayout s;
    private String m = "";
    private String k = "";
    private int j = -1;
    private String[] n = {"https://club.huawei.com/", "http://cn.club.vmall.com/", "https://health.vmall.com/help/", "https://health.vmall.com/help/userimprovement/index.jsp", "http://www.dol.cn/minisite/index.aspx", "https://m.vmall.com/", "http://v.youku.com/", "http://m.youku.com/", "http://player.youku.com/", "http://www.iqiyi.com/", "http://www.miaopai.com/", "http://3ms.huawei.com/", "http://v.qq.com/", "https://v.qq.com/", "https://m.v.qq.com/", "http://static.video.qq.com/", "http://www.letv.com/", "http://i7.imgs.letv.com/", "https://hwid1.vmall.com/", "http://hwid1.vmall.com/", "https://msale.vmall.com/"};

    public WebViewActivity() {
        this.l = new c();
        this.p = new a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web_view);
        this.f8992a = this;
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("WebViewActivity", "intent is null");
            return;
        }
        this.b = intent.getStringExtra("WebViewActivity.REQUEST_URL_KEY");
        this.m = intent.getStringExtra("WebViewActivity.TITLE");
        this.d = intent.getBooleanExtra("WebViewActivity.HANDLE_REDIRECT", false);
        this.i = intent.getStringExtra("WebViewActivity.RESULT_URL");
        this.j = intent.getIntExtra(Constants.JUMP_MODE_KEY, -1);
        e();
        d(this.j);
        if (c(this.f8992a) && this.j == 4) {
            j();
            return;
        }
        int i = this.j;
        if (i == 8 || i == 6 || i == 10 || i == 7) {
            LogUtil.a("WebViewActivity", "jump mode is form scale");
            g();
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.commonui.webview.WebViewActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainClubHuawei");
                    String url2 = GRSManager.a(BaseApplication.getContext()).getUrl("domainCnClubVmall");
                    if (!TextUtils.isEmpty(url)) {
                        WebViewActivity.this.n[0] = url;
                    }
                    if (!TextUtils.isEmpty(url2)) {
                        WebViewActivity.this.n[1] = url2;
                    }
                    WebViewActivity.this.s.post(new Runnable() { // from class: com.huawei.ui.commonui.webview.WebViewActivity.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            WebViewActivity.this.g();
                        }
                    });
                }
            });
        }
    }

    private void e() {
        this.s = (LinearLayout) nsy.cMc_(this, R.id.webview_layout);
        this.e = (CustomTitleBar) nsy.cMc_(this, R.id.app_help_title);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.refresh_btn);
        this.h = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.WebViewActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WebViewActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HealthButton healthButton2 = this.h;
        healthButton2.setText(healthButton2.getText().toString().toUpperCase(Locale.ENGLISH));
        this.g = (HealthProgressBar) nsy.cMc_(this, R.id.load_help_url_progress);
        this.o = (LinearLayout) nsy.cMc_(this, R.id.help_retry);
        this.e.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.WebViewActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewActivity.this.r.canGoBack()) {
                    LogUtil.a("WebViewActivity", "canGoBack");
                    WebViewActivity.this.r.goBack();
                } else {
                    LogUtil.a("WebViewActivity", "no GoBack");
                    if (!(WebViewActivity.this.f8992a instanceof Activity)) {
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    ((Activity) WebViewActivity.this.f8992a).finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        f();
    }

    private void f() {
        WebView webView = (WebView) nsy.cMc_(this, R.id.sns_app_help_web);
        this.r = webView;
        WebSettings settings = webView.getSettings();
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        if (this.j == 7) {
            settings.setAllowFileAccess(true);
        } else {
            settings.setAllowFileAccess(false);
        }
        if (this.j == 8) {
            settings.setTextZoom(100);
        }
        int i = this.j;
        if (i == 4 || i == 8) {
            settings.setCacheMode(2);
        } else if (CommonUtil.aa(this.f8992a)) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setMixedContentMode(2);
        nrt.cKg_(this.f8992a, this.r);
        settings.setSavePassword(false);
        settings.setUserAgentString(b().replace("; wv", ""));
        WebView webView2 = this.r;
        a aVar = this.p;
        if (webView2 instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView2, aVar);
        } else {
            webView2.setWebViewClient(aVar);
        }
        this.r.setWebChromeClient(this.l);
        this.r.setDownloadListener(new d());
    }

    private String b() {
        String defaultUserAgent = WebSettings.getDefaultUserAgent(this);
        if (TextUtils.isEmpty(defaultUserAgent)) {
            defaultUserAgent = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = defaultUserAgent.length();
        for (int i = 0; i < length; i++) {
            char charAt = defaultUserAgent.charAt(i);
            if (charAt <= 31 || charAt >= 127) {
                stringBuffer.append(String.format(Locale.ENGLISH, "\\u%04x", Integer.valueOf(charAt)));
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    class d implements DownloadListener {
        private d() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            WebViewActivity.this.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
        }
    }

    private void d(int i) {
        switch (i) {
            case 0:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_main_discovery_tab_service_help));
                break;
            case 1:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_main_discovery_tab_service_huawei_club));
                break;
            case 2:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_main_left_menu_vmall));
                break;
            case 3:
                this.s.setFitsSystemWindows(true);
                this.e.setVisibility(8);
                break;
            case 4:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_main_discovery_tab_service_help));
                break;
            case 5:
                this.e.setTitleText(this.m);
                break;
            case 6:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_nottification_settings_b2_ex));
                break;
            case 7:
                this.e.setTitleText(this.m);
                break;
            case 8:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_main_discovery_tab_service_help));
                break;
            case 9:
                this.e.setTitleText(this.f8992a.getString(R$string.IDS_main_discovery_tab_service_honor_club));
                break;
            default:
                this.e.setTitleText(this.m);
                break;
        }
    }

    private void j() {
        if (this.f == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.f8992a).b(R$string.IDS_service_area_notice_title).d(R$string.IDS_app_help_3gnet_diag_conent).cyR_(R$string.IDS_apphelp_pwindows_back_button, new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.WebViewActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WebViewActivity.this.d();
                    WebViewActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyU_(R$string.IDS_apphelp_pwindows_continue_button, new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.WebViewActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WebViewActivity.this.d();
                    WebViewActivity.this.g();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.f = a2;
            a2.setCancelable(false);
        }
        if (isFinishing()) {
            return;
        }
        this.f.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        CustomTextAlertDialog customTextAlertDialog;
        if (isFinishing() || (customTextAlertDialog = this.f) == null) {
            return;
        }
        customTextAlertDialog.cancel();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.r.canGoBack()) {
            LogUtil.a("WebViewActivity", "onBackPressed can goBack");
            this.r.goBack();
            return;
        }
        LogUtil.a("WebViewActivity", "onBackPressed not goBack");
        Context context = this.f8992a;
        if (!(context instanceof Activity)) {
            LogUtil.h("WebViewActivity", "context not activity");
        } else {
            ((Activity) context).finish();
            super.onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        int identifier = getResources().getIdentifier("EmergencyTheme", TemplateStyleRecord.STYLE, "com.huawei.health");
        if (identifier == 0) {
            LogUtil.a("WebViewActivity", "loadApplicationTheme themeResources is DEFAULT_THEME_ID");
        } else {
            LogUtil.a("WebViewActivity", "loadApplicationTheme themeResources is ", Integer.valueOf(identifier));
            setTheme(identifier);
        }
    }

    private boolean b(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    private static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (b(this.f8992a)) {
            String str = this.c;
            if (str != null) {
                a(str);
                this.c = null;
                return;
            }
            LogUtil.a("WebViewActivity", "loadWebByUrl load url");
            WebView webView = this.r;
            String str2 = this.b;
            webView.loadUrl(str2);
            WebViewInstrumentation.loadUrl(webView, str2);
            return;
        }
        if (this.j == 7) {
            WebView webView2 = this.r;
            String str3 = this.b;
            webView2.loadUrl(str3);
            WebViewInstrumentation.loadUrl(webView2, str3);
            return;
        }
        LogUtil.a("WebViewActivity", "loadWebByUrl showTryAgain");
        i();
    }

    private boolean e(String str) {
        if (Pattern.compile("[<>]").matcher(Normalizer.normalize(str, Normalizer.Form.NFKC)).find()) {
            LogUtil.a("WebViewActivity", " url is illegal.");
        } else {
            LogUtil.a("WebViewActivity", "url is correct.");
            for (String str2 : this.n) {
                if (str.startsWith(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(String str) {
        if (e(str)) {
            LogUtil.a("WebViewActivity", "checkUrlValid load url");
            WebView webView = this.r;
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
            return;
        }
        LogUtil.c("WebViewActivity", "This url is inValid");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.o.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.o.setVisibility(8);
    }

    public void a() {
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        return lowerCase.startsWith(ProxyConfig.MATCH_HTTPS) || lowerCase.startsWith("http") || lowerCase.startsWith("file");
    }

    class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            LogUtil.a("WebViewActivity", "shouldOverrideUrlLoading");
            if (str == null || !str.startsWith("huaweihealth://")) {
                if (WebViewActivity.this.j != 4 && WebViewActivity.this.j != 8) {
                    if (!TextUtils.isEmpty(WebViewActivity.this.i) && !TextUtils.isEmpty(str) && str.startsWith(WebViewActivity.this.i)) {
                        LogUtil.a("WebViewActivity", "Override Url startsWith ResultUrl.");
                        if (WebViewActivity.this.f8992a instanceof Activity) {
                            Activity activity = (Activity) WebViewActivity.this.f8992a;
                            Intent intent = new Intent();
                            intent.putExtra("WebViewActivity.RESULT_URL", str);
                            activity.setResult(-1, intent);
                            activity.finish();
                        }
                        return true;
                    }
                    if (TextUtils.isEmpty(str) || !str.startsWith("huaweischeme:")) {
                        if (WebViewActivity.this.d) {
                            WebView.HitTestResult hitTestResult = webView.getHitTestResult();
                            if (TextUtils.isEmpty(str) || hitTestResult != null) {
                                return super.shouldOverrideUrlLoading(webView, str);
                            }
                        }
                        if (WebViewActivity.this.d(str)) {
                            webView.loadUrl(str);
                            WebViewInstrumentation.loadUrl(webView, str);
                        }
                        return true;
                    }
                    WebViewActivity webViewActivity = WebViewActivity.this;
                    webViewActivity.d(webViewActivity.f8992a, str);
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
            WebViewActivity.this.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
            WebViewActivity.this.finish();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewActivity.this.g.setVisibility(0);
            WebViewActivity.this.c();
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebViewActivity.this.g.setVisibility(8);
            super.onPageFinished(webView, str);
            WebViewActivity.this.b(webView.getTitle());
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            LogUtil.a("WebViewActivity", "onReceivedError errorCode:", Integer.valueOf(i), " description:", str);
            WebViewActivity.this.c = str2;
            WebViewActivity.this.i();
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            super.doUpdateVisitedHistory(webView, str, z);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslError != null) {
                WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, WebViewActivity.this.f8992a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, String str) {
        LogUtil.a("WebViewActivity", "jumpToActivity");
        try {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setFlags(268435456);
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
            LogUtil.a("WebViewActivity", "jump to scheme view");
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WebViewActivity", "ActivityNotFoundException");
        }
    }

    class c extends WebChromeClient {
        private c() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            WebViewActivity.this.g.setProgress(i);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            WebViewActivity.this.b(str);
            if (WebViewActivity.this.j != 5 || TextUtils.isEmpty(str)) {
                return;
            }
            LogUtil.a("WebViewActivity", "title: ", str);
            if (str.trim().contains("https://") || str.contains("http://") || str.contains("huaweischeme://")) {
                return;
            }
            WebViewActivity.this.e.setTitleText(str);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            super.onReceivedTouchIconUrl(webView, str, z);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            return super.onCreateWindow(webView, z, z2, message);
        }
    }

    public void b(String str) {
        this.k = str;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CommonUtil.a(this.f8992a);
        WebView webView = this.r;
        if (webView != null) {
            webView.destroy();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
