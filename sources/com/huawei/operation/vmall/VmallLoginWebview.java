package com.huawei.operation.vmall;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.adapter.VmallLoginCallback;
import com.huawei.operation.js.InJavaScriptLocalObj;
import com.huawei.operation.utils.CasLoginUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.operation.view.CustomWebView;
import com.huawei.operation.vmall.VmallLoginWebview;
import defpackage.gmz;
import defpackage.jdx;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class VmallLoginWebview {
    private static final int MSG_NEXT_LOGIN_VMALL = 1;
    private static final String TAG = "PluginOperation_VmallLoginWebview";
    private static String sUser = "";
    private WebView mWebView;
    private long mLastLoginTime = 0;
    private Handler mloginHander = new LoginHandler(this);

    static class LoginHandler extends BaseHandler<VmallLoginWebview> {
        LoginHandler(VmallLoginWebview vmallLoginWebview) {
            super(vmallLoginWebview);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        public void handleMessageWhenReferenceNotNull(VmallLoginWebview vmallLoginWebview, Message message) {
            if (message.what == 1) {
                LogUtil.a(VmallLoginWebview.TAG, "handleMessage MSG_NEXT_LOGIN_VMALL");
                CustomWebView.setLoginStatus(false);
                if (vmallLoginWebview.mWebView != null) {
                    vmallLoginWebview.mLastLoginTime = 0L;
                    vmallLoginWebview.preLogin(true);
                    return;
                }
                return;
            }
            LogUtil.h(VmallLoginWebview.TAG, "mloginHander handleMessage what is unknown.");
        }
    }

    public VmallLoginWebview(WebView webView) {
        this.mWebView = webView;
        initWebviewSetting();
        addInterface();
    }

    private void addInterface() {
        InJavaScriptLocalObj inJavaScriptLocalObj = new InJavaScriptLocalObj();
        inJavaScriptLocalObj.setVmallLoginCallback(new LoginCallback());
        this.mWebView.addJavascriptInterface(inJavaScriptLocalObj, Constants.JAVA_OBJ);
    }

    private void initWebviewSetting() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setUserAgentString(this.mWebView.getSettings().getUserAgentString() + "; HuaweiHealth");
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setGeolocationEnabled(false);
    }

    public void preLogin(final boolean z) {
        if (CommonUtil.z(BaseApplication.getContext()) || !Utils.i()) {
            this.mLastLoginTime = 0L;
            return;
        }
        if (!BaseApplication.isRunningForeground()) {
            LogUtil.a(TAG, "preLogin isRunningBackground");
            CustomWebView.setLoginStatus(false);
            this.mLastLoginTime = 0L;
        } else if (isAllowedAuth()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.vmall.VmallLoginWebview$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    VmallLoginWebview.this.m763lambda$preLogin$0$comhuaweioperationvmallVmallLoginWebview(z);
                }
            });
        } else {
            LogUtil.h(TAG, "preLogin no auth");
        }
    }

    /* renamed from: lambda$preLogin$0$com-huawei-operation-vmall-VmallLoginWebview, reason: not valid java name */
    /* synthetic */ void m763lambda$preLogin$0$comhuaweioperationvmallVmallLoginWebview(boolean z) {
        startVmallLogin();
        if (!z || this.mloginHander.hasMessages(1)) {
            return;
        }
        LogUtil.a(TAG, "send MSG_NEXT_LOGIN_VMALL");
        this.mloginHander.sendEmptyMessageDelayed(1, 1200000L);
        Message obtainMessage = this.mloginHander.obtainMessage();
        obtainMessage.what = 1;
        this.mloginHander.sendMessageAtTime(obtainMessage, System.currentTimeMillis() + 1200000);
    }

    public boolean isVmallLogin() {
        LogUtil.a(TAG, "isVmallLogin:", Long.valueOf(this.mLastLoginTime), ",System.currentTimeMillis():", Long.valueOf(System.currentTimeMillis()));
        return System.currentTimeMillis() - this.mLastLoginTime < 1200000;
    }

    public void clear() {
        Handler handler = this.mloginHander;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void startVmallLoginByAt() {
        LogUtil.a(TAG, "enter startVmallLoginByAt");
        AuthVmall.vmallAtLogin(BaseApplication.getContext(), new VmallAtLoginResponseCallback(this));
    }

    public static boolean isAllowedAuth() {
        return String.valueOf(true).equals(gmz.d().c(401));
    }

    public static boolean isUserChangeAccount() {
        return sUser.equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)) || "".equals(sUser);
    }

    public static void updateLastAuthAccount() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            return;
        }
        sUser = accountInfo;
    }

    public static class LoginCallback implements VmallLoginCallback {
        @Override // com.huawei.operation.adapter.VmallLoginCallback
        public void onVmallLoginCallback(int i, Object obj) {
            LogUtil.a(VmallLoginWebview.TAG, "onVmallLoginCallback", Integer.valueOf(i));
            if (i == 0) {
                CustomWebView.setLoginStatus(true);
                VmallLoginWebview.updateLastAuthAccount();
            } else {
                LogUtil.h(VmallLoginWebview.TAG, "onVmallLoginCallback failed");
            }
        }
    }

    public void onPageFinishedForVmall(WebView webView, String str) {
        if (loadUpLoginUrl(webView, str)) {
            CustomWebView.setLoginStatus(true);
            updateLastAuthAccount();
        } else if (WebViewUtils.isVmallMcpLoginUrl(str)) {
            String url = WebViewUtils.getUrl("window.java_obj.showSource(document.getElementsByTagName", "pre", "[0].innerHTML);");
            webView.loadUrl(url);
            WebViewInstrumentation.loadUrl(webView, url);
            String url2 = WebViewUtils.getUrl("document.getElementsByTagName", Constants.SUFFIX_HTML, "[0].style.visibility='hidden';");
            webView.loadUrl(url2);
            WebViewInstrumentation.loadUrl(webView, url2);
        }
    }

    private boolean loadUpLoginUrl(WebView webView, String str) {
        if (!WebViewUtils.isUpLoginUrl(str)) {
            return false;
        }
        String autoLoginUrl = CasLoginUtil.getAutoLoginUrl();
        if (!TextUtils.isEmpty(autoLoginUrl) && webView != null) {
            webView.loadUrl(autoLoginUrl);
            WebViewInstrumentation.loadUrl(webView, autoLoginUrl);
        }
        this.mLastLoginTime = System.currentTimeMillis();
        return true;
    }

    static class VmallAtLoginResponseCallback implements IBaseResponseCallback {
        private final WeakReference<VmallLoginWebview> weakReference;

        public VmallAtLoginResponseCallback(VmallLoginWebview vmallLoginWebview) {
            this.weakReference = new WeakReference<>(vmallLoginWebview);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a(VmallLoginWebview.TAG, "vmallAtLogin success.");
                final VmallLoginWebview vmallLoginWebview = this.weakReference.get();
                if (vmallLoginWebview != null) {
                    HandlerExecutor.a(new Runnable() { // from class: com.huawei.operation.vmall.VmallLoginWebview$VmallAtLoginResponseCallback$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            VmallLoginWebview.VmallAtLoginResponseCallback.lambda$onResponse$0(VmallLoginWebview.this);
                        }
                    });
                    return;
                }
                return;
            }
            LogUtil.b(VmallLoginWebview.TAG, "vmallAtLogin failed:", Integer.valueOf(i));
        }

        static /* synthetic */ void lambda$onResponse$0(VmallLoginWebview vmallLoginWebview) {
            CustomWebView.setLoginStatus(true);
            String url = vmallLoginWebview.mWebView.getUrl();
            if (!TextUtils.isEmpty(url)) {
                AuthVmall.setCookie(url);
            }
            vmallLoginWebview.mLastLoginTime = System.currentTimeMillis();
        }
    }

    private void startVmallLogin() {
        LogUtil.a(TAG, "startVmallLogin enter");
        jdx.b(new Runnable() { // from class: com.huawei.operation.vmall.VmallLoginWebview$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VmallLoginWebview.this.m765x58b84209();
            }
        });
    }

    /* renamed from: lambda$startVmallLogin$2$com-huawei-operation-vmall-VmallLoginWebview, reason: not valid java name */
    /* synthetic */ void m765x58b84209() {
        try {
            String openApiVmallHost = WebViewUtils.getOpenApiVmallHost();
            if (TextUtils.isEmpty(openApiVmallHost)) {
                LogUtil.b(TAG, "startVmallLogin mCasLoginUrlDefault is empty");
                return;
            }
            LogUtil.c(TAG, "startVmallLogin casLoginUrlDefault = ", openApiVmallHost);
            final String casLoginUrl = CasLoginUtil.getCasLoginUrl(WebViewUtils.strBuilder(openApiVmallHost, "/"));
            if (this.mloginHander == null) {
                LogUtil.b(TAG, "startVmallLogin mloginHander is null.");
            } else {
                HandlerExecutor.a(new Runnable() { // from class: com.huawei.operation.vmall.VmallLoginWebview$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VmallLoginWebview.this.m764x9f40b46a(casLoginUrl);
                    }
                });
            }
        } catch (UnsupportedEncodingException e) {
            LogUtil.b(TAG, "startVmallLogin UnsupportedEncodingException ", e.getMessage());
        }
    }

    /* renamed from: lambda$startVmallLogin$1$com-huawei-operation-vmall-VmallLoginWebview, reason: not valid java name */
    /* synthetic */ void m764x9f40b46a(String str) {
        WebView webView = this.mWebView;
        webView.loadUrl(str);
        WebViewInstrumentation.loadUrl(webView, str);
        startVmallLoginByAt();
    }
}
