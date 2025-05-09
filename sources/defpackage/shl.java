package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.UriConstants;
import health.compact.a.GRSManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class shl implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private ICloudOperationResult<String> f17059a;
    private WebView d;

    public shl(WebView webView, ICloudOperationResult<String> iCloudOperationResult) {
        this.d = webView;
        this.f17059a = iCloudOperationResult;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebView webView = this.d;
        if (webView == null) {
            LogUtil.b("OpenAuthService", "WebView is null");
            return;
        }
        webView.loadUrl(c(), a());
        WebView webView2 = this.d;
        WebViewClient webViewClient = new WebViewClient() { // from class: shl.5
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView3, String str) {
                LogUtil.a("OpenAuthService", "response = ", str);
                String queryParameter = Uri.parse(str).getQueryParameter("code");
                if (shl.this.f17059a != null) {
                    LogUtil.c("OpenAuthService", "parse code = ", queryParameter);
                    boolean isEmpty = TextUtils.isEmpty(queryParameter);
                    ICloudOperationResult iCloudOperationResult = shl.this.f17059a;
                    if (isEmpty) {
                        queryParameter = null;
                    }
                    iCloudOperationResult.operationResult(str, queryParameter, !isEmpty);
                }
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView3, String str) {
                super.onPageFinished(webView3, str);
                LogUtil.c("OpenAuthService", "onPageFinished:", str);
            }
        };
        if (webView2 instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView2, webViewClient);
        } else {
            webView2.setWebViewClient(webViewClient);
        }
    }

    private Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015));
        return hashMap;
    }

    private String c() {
        StringBuilder sb = new StringBuilder(GRSManager.a(BaseApplication.getContext()).getUrl("domainAccessTokenUrl") + UriConstants.OAUTH_URL_AUTHORIZE);
        sb.append("?response_type=code&access_type=offline&state=state_parameter_passthrough_value&client_id=105901045&redirect_uri=hms://localhost/&");
        sb.append(b());
        sb.append("&display=touch");
        LogUtil.c("OpenAuthService", "buildAuthUrl = ", sb.toString());
        return sb.toString();
    }

    private String b() {
        String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968726_res_0x7f040096);
        StringBuilder sb = new StringBuilder("scope=openid");
        for (String str : stringArray) {
            sb.append(" ");
            sb.append(str);
        }
        return sb.toString();
    }
}
