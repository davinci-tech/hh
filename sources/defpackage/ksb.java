package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.ProxyConfig;
import com.huawei.hwidauth.i.a;
import com.huawei.hwidauth.ui.f;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.secure.android.common.webview.SafeWebView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ksb extends WebViewClient {

    /* renamed from: a, reason: collision with root package name */
    private Context f14564a;
    private kso b;
    private SafeWebView c;
    private f.b d;
    private ksc e;

    public ksb(ksc kscVar, Context context, SafeWebView safeWebView, kso ksoVar, f.b bVar) {
        this.c = safeWebView;
        this.f14564a = context;
        this.b = ksoVar;
        this.e = kscVar;
        this.d = bVar;
        ksy.b("SafeWebViewClient", "SafeWebViewClient start.", true);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        ksy.c("SafeWebViewClient", "onReceivedSslError:" + sslError, false);
        ksy.b("SafeWebViewClient", "is not joint debug. check server certificate", true);
        WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, this.f14564a);
        this.d.a(2015, "server certificate error");
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        ksy.b("SafeWebViewClient", "shouldOverrideUrlLoading", true);
        ksy.b("SafeWebViewClient", "enter shouldOverrideUrlLoading url: " + str, false);
        if ("from_other_app_signin".equalsIgnoreCase(this.e.c()) && this.e.m(str)) {
            ksy.b("SafeWebViewClient", "shouldOverrideUrlLoading sign contains service", false);
            ksg.c(this.f14564a, 907115001, 200, "loginSuccess", this.e.j(), "accountPickerH5.signIn_v3", "api_ret");
            if (this.e.l(str)) {
                return true;
            }
        }
        if ("open_personal_info".equalsIgnoreCase(this.e.c()) && !str.contains("ticket")) {
            ksy.b("SafeWebViewClient", "PersonalInfoGoBack", true);
            if (a(webView)) {
                ksy.b("SafeWebViewClient", "PersonalInfoGoBack true", true);
                return false;
            }
        }
        if (this.e.i(str) || this.e.n(str)) {
            return false;
        }
        HashMap<String, String> j = this.e.j(str);
        if (str.contains("service/windex")) {
            j.put("Referer", a.a().n());
        }
        a((SafeWebView) webView, str, j);
        ksy.b("SafeWebViewClient", "shouldOverrideUrlLoading map = " + j.toString(), false);
        return true;
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return super.shouldInterceptRequest(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        WebResourceResponse bQC_;
        String uri = webResourceRequest.getUrl().toString();
        return (!a(uri) || (bQC_ = bQC_(uri)) == null) ? super.shouldInterceptRequest(webView, webResourceRequest) : bQC_;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        ksy.b("SafeWebViewClient", "onPageFinished-", true);
        super.onPageFinished(webView, str);
        this.d.c(str);
        c();
    }

    private WebResourceResponse bQC_(String str) {
        WebResourceResponse bQF_;
        if (!this.b.d(str) || (bQF_ = this.b.bQF_(str)) == null) {
            return null;
        }
        return bQF_;
    }

    private boolean a(WebView webView) {
        String str;
        WebBackForwardList copyBackForwardList = webView.copyBackForwardList();
        if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
            ksy.b("SafeWebViewClient", "PersonalInfo historyList is no empty", true);
            WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex());
            if (itemAtIndex != null) {
                str = itemAtIndex.getUrl();
                return str.contains("ticket");
            }
        }
        str = "";
        return str.contains("ticket");
    }

    private void c() {
        if ("from_signin".equalsIgnoreCase(this.e.c()) || "from_v3_signin".equalsIgnoreCase(this.e.c())) {
            this.e.e();
        }
    }

    private boolean a(String str) {
        String a2 = ktf.a(str);
        if (!TextUtils.isEmpty(str) && (ProxyConfig.MATCH_HTTPS.equals(a2) || "http".equals(a2) || com.huawei.hwcloudjs.g.a.c.equals(a2) || "mqq".equals(a2) || "weixin".equals(a2) || "wtloginmqq".equals(a2))) {
            return this.c.isWhiteListUrl(str);
        }
        ksy.b("SafeWebViewClient", "is not a right url", true);
        return false;
    }

    private void a(SafeWebView safeWebView, String str, Map map) {
        if (d(str) && safeWebView.isWhiteListUrl(str)) {
            safeWebView.loadUrl(str, map);
        }
    }

    private boolean d(String str) {
        String a2 = ktf.a(str);
        if (!TextUtils.isEmpty(str) && (ProxyConfig.MATCH_HTTPS.equals(a2) || "http".equals(a2) || com.huawei.hwcloudjs.g.a.c.equals(a2) || "mqq".equals(a2) || "weixin".equals(a2) || "wtloginmqq".equals(a2))) {
            return true;
        }
        ksy.b("SafeWebViewClient", "is not a right url", true);
        return false;
    }
}
