package com.huawei.health.h5pro.webkit;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.ProxyConfig;
import androidx.webkit.WebViewAssetLoader;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class H5ProWebViewClient extends WebViewClient {

    /* renamed from: a, reason: collision with root package name */
    public final WebKitInstance f2467a;
    public WeakReference<H5ProAppLoaderImp> b;
    public AnnualReportAdaptation c;
    public final H5ProAppLoadListener d;

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        LogUtil.i(c(), "shouldOverrideUrlLoading");
        Context context = webView.getContext();
        if (context == null) {
            LogUtil.w(c(), "context is null");
            return true;
        }
        if (WebKitUtil.isLocalUrl(str)) {
            return !WebKitUtil.isUrlTrusted(context, str);
        }
        if (H5ProTrustListChecker.isWeChatPayUrl(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put("Referer", this.f2467a.getUrl());
            webView.loadUrl(str, hashMap);
            return true;
        }
        if (!H5ProTrustListChecker.isTrustedToLoad(str)) {
            LogUtil.w(c(), "untrusted -> " + str);
            return true;
        }
        if (WebKitUtil.isRemoteUrl(str)) {
            return false;
        }
        try {
            context.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtil.e(c(), e, new String[0]);
            return true;
        }
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        WebResourceResponse nativeGet;
        Uri url = webResourceRequest.getUrl();
        String uri = url.toString();
        if (TextUtils.isEmpty(uri)) {
            LogUtil.w(c(), "shouldInterceptRequest: url is empty");
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        String c = c();
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder("shouldInterceptRequest: ");
        sb.append((Object) ((EnvironmentHelper.getInstance().getBuildType() == EnvironmentHelper.BuildType.TEST || EnvironmentHelper.getInstance().getBuildType() == EnvironmentHelper.BuildType.DEBUG) ? uri : TextUtils.concat("*****", url.getPath())));
        strArr[0] = sb.toString();
        LogUtil.i(c, strArr);
        if (H5ProTrustListChecker.isRequestProxyNeeded(uri) && (nativeGet = ResourceUtil.nativeGet(webResourceRequest, "application/json")) != null) {
            return nativeGet;
        }
        WebKitInstance webKitInstance = this.f2467a;
        if (webKitInstance == null) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        H5ProAppInfo appInfo = webKitInstance.getAppInfo();
        if (ResourceUtil.isLocalResource(uri)) {
            return ResourceUtil.getLocalResource(appInfo, this.f2467a.getUrl(), uri, webView.getContext());
        }
        if (this.c == null) {
            this.c = new AnnualReportAdaptation();
        }
        if (this.c.isAnnualMedalResourcesPath(appInfo, this.f2467a.getUrl(), uri, webView.getContext())) {
            return this.c.getAnnualMedalResourceResponse(uri);
        }
        if (ResourceUtil.isNeedNativeGet(webResourceRequest)) {
            if (this.f2467a.isDisableImageProxy() || ResourceUtil.isDisableImageProxy(webResourceRequest)) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
            if (this.f2467a.isEnableImageCache() || ResourceUtil.isNeedImageCache(webResourceRequest)) {
                WebResourceResponse loadImage = ResourceUtil.loadImage(uri, webView);
                if (loadImage != null) {
                    return loadImage;
                }
            } else {
                WebResourceResponse nativeGet2 = ResourceUtil.nativeGet(webResourceRequest);
                if (nativeGet2 != null) {
                    return nativeGet2;
                }
            }
        }
        if (appInfo != null && appInfo.getH5ProAppType() != H5ProAppInfo.H5ProAppType.H5_LIGHT_APP && !TextUtils.isEmpty(appInfo.getBaseUrl()) && uri.startsWith(appInfo.getBaseUrl())) {
            return XW_(uri, appInfo, webView, webResourceRequest);
        }
        if (H5ProTrustListChecker.disableHttpLoad(uri)) {
            LogUtil.w(c(), "shouldInterceptRequest: disableHttpLoad -> " + uri);
            WebKitUtil.closeWebViewSettings(webView);
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    public void setH5ProAppLoader(H5ProAppLoaderImp h5ProAppLoaderImp) {
        this.b = new WeakReference<>(h5ProAppLoaderImp);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        LogUtil.i(c(), "onReceivedSslError");
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        LogUtil.i(c(), String.format(Locale.ROOT, "onReceivedHttpError: %s, %s", Integer.valueOf(webResourceResponse.getStatusCode()), webResourceResponse.getReasonPhrase()));
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        Uri url = webResourceRequest.getUrl();
        if (url != null) {
            this.d.onResourceLoadErr(this.f2467a, url.toString(), webResourceResponse.getStatusCode());
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        LogUtil.i(c(), "onReceivedHttpAuthRequest");
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        LogUtil.i(c(), String.format(Locale.ROOT, "onReceivedError: %s, %s, %s", Boolean.valueOf(webResourceRequest.isForMainFrame()), Integer.valueOf(webResourceError.getErrorCode()), webResourceError.getDescription()));
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (webResourceRequest.isForMainFrame()) {
            this.d.onException(this.f2467a, webResourceError.getDescription().toString());
        }
        Uri url = webResourceRequest.getUrl();
        if (url == null || !url.toString().startsWith(ProxyConfig.MATCH_HTTPS)) {
            return;
        }
        this.d.onResourceLoadErr(this.f2467a, url.toString(), webResourceError.getErrorCode());
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
        super.onReceivedClientCertRequest(webView, clientCertRequest);
        LogUtil.i(c(), "onReceivedClientCertRequest");
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        LogUtil.i(c(), "onPageStarted");
        if (H5ProTrustListChecker.disableHttpLoad(str)) {
            LogUtil.w(c(), "onPageStarted: disableHttpLoad -> " + str);
            WebKitUtil.closeWebViewSettings(webView);
            return;
        }
        WebKitInstance webKitInstance = this.f2467a;
        boolean isEnableSelfProtection = webKitInstance != null ? webKitInstance.isEnableSelfProtection() : false;
        if (!this.f2467a.isEmbedded() && (webView.getContext() instanceof Activity)) {
            Activity activity = (Activity) webView.getContext();
            boolean isShouldSelfProtection = GeneralUtil.isShouldSelfProtection(activity, str, this.f2467a.getSafeUrls(), isEnableSelfProtection);
            Window window = activity.getWindow();
            if (isShouldSelfProtection) {
                window.addFlags(8192);
            } else {
                window.clearFlags(8192);
            }
        }
        this.d.onNewPageStartLoad(this.f2467a, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        LogUtil.i(c(), "onPageFinished");
        String title = webView.getTitle();
        WebKitInstance webKitInstance = this.f2467a;
        if (!webKitInstance.isTitleValid(title)) {
            title = "";
        }
        webKitInstance.updateTitle(title);
        H5ProAppLoaderImp h5ProAppLoaderImp = (H5ProAppLoaderImp) GeneralUtil.getReferent(this.b);
        if (h5ProAppLoaderImp != null) {
            h5ProAppLoaderImp.onViewCreated(webView, str);
        }
        this.d.onNewPageLoaded(this.f2467a, str);
        this.d.onProgressChanged(this.f2467a, 100);
    }

    private WebResourceRequest XX_(final WebResourceRequest webResourceRequest, final String str) {
        return new WebResourceRequest() { // from class: com.huawei.health.h5pro.webkit.H5ProWebViewClient.1
            @Override // android.webkit.WebResourceRequest
            public boolean isRedirect() {
                return webResourceRequest.isRedirect();
            }

            @Override // android.webkit.WebResourceRequest
            public boolean isForMainFrame() {
                return webResourceRequest.isForMainFrame();
            }

            @Override // android.webkit.WebResourceRequest
            public boolean hasGesture() {
                return webResourceRequest.hasGesture();
            }

            @Override // android.webkit.WebResourceRequest
            public Uri getUrl() {
                return Uri.parse(str);
            }

            @Override // android.webkit.WebResourceRequest
            public Map<String, String> getRequestHeaders() {
                return webResourceRequest.getRequestHeaders();
            }

            @Override // android.webkit.WebResourceRequest
            public String getMethod() {
                return webResourceRequest.getMethod();
            }
        };
    }

    private WebResourceResponse XW_(String str, H5ProAppInfo h5ProAppInfo, WebView webView, WebResourceRequest webResourceRequest) {
        String baseUrl = h5ProAppInfo.getBaseUrl();
        Context context = webView.getContext();
        if (context == null) {
            LogUtil.e(c(), "shouldInterceptRequest context is null");
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        if (WebKitUtil.isStrictMode()) {
            File file = new File(context.getFilesDir(), "h5pro");
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                LogUtil.i(c(), "shouldInterceptRequest(strict mode) mkdirs-> " + mkdirs);
            }
            return new WebViewAssetLoader.Builder().setDomain(baseUrl.replace("https://", "")).addPathHandler("/h5pro/", new WebViewAssetLoader.InternalStoragePathHandler(context, file)).build().shouldInterceptRequest(webResourceRequest.getUrl());
        }
        if (!str.startsWith(baseUrl)) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        File externalFilesDir = context.getExternalFilesDir("");
        if (!externalFilesDir.exists()) {
            boolean mkdirs2 = externalFilesDir.mkdirs();
            LogUtil.i(c(), "shouldInterceptRequest mkdirs-> " + mkdirs2);
        }
        String obj = TextUtils.concat(externalFilesDir.getAbsolutePath(), str.replace(baseUrl, "")).toString();
        try {
            String str2 = obj.contains("#") ? obj.split("#")[0] : obj;
            return new WebResourceResponse(this.f2467a.getMimeType(str2), StandardCharsets.UTF_8.name(), new FileInputStream(str2));
        } catch (IOException unused) {
            return super.shouldInterceptRequest(webView, XX_(webResourceRequest, Constants.PREFIX_FILE + obj));
        }
    }

    private String c() {
        return LogUtil.getTag(this.f2467a, "WebViewClient");
    }

    public H5ProWebViewClient(WebKitInstance webKitInstance, H5ProAppLoadListener h5ProAppLoadListener) {
        this.f2467a = webKitInstance;
        this.d = h5ProAppLoadListener;
    }
}
