package com.huawei.watchface.mvp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.DialogFragment;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.secure.android.common.webview.SafeWebSettings;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.huawei.secure.android.common.webview.WebViewLoadCallBack;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.R$string;
import com.huawei.watchface.aq;
import com.huawei.watchface.as;
import com.huawei.watchface.at;
import com.huawei.watchface.dc;
import com.huawei.watchface.ds;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.mvp.model.webview.JSInterface;
import com.huawei.watchface.mvp.model.webview.JsInteractAddition;
import com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.constants.WatchFaceConstant;

/* loaded from: classes9.dex */
public class BottomVipPayDialogFragment extends DialogFragment {

    /* renamed from: a, reason: collision with root package name */
    private Context f11105a;
    private SafeWebView b;
    private String c;

    public BottomVipPayDialogFragment() {
    }

    public BottomVipPayDialogFragment(Context context) {
        this.f11105a = context;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(JSInterface jSInterface) {
        HwLog.d("BottomPayDialogFragment", "BottomVipPayDialogFragment1 addJavascriptInterface");
        Object tag = this.b.getTag();
        if (tag != null && ((Boolean) tag).booleanValue()) {
            HwLog.e("BottomPayDialogFragment", "mWebView set JSInterface again");
            return;
        }
        jSInterface.setMagicWebView(this.b);
        this.b.addJavascriptInterface(jSInterface, "JsInteraction");
        JsInterfaceMarketing jsInterfaceMarketing = new JsInterfaceMarketing(this.f11105a);
        jsInterfaceMarketing.setWebView(this.b);
        this.b.addJavascriptInterface(jsInterfaceMarketing, WatchFaceConstant.JS_INTERFACE);
        this.b.setTag(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R$layout.watchface_choose_buy_vip_layout_webview, viewGroup, false);
        a(inflate);
        return inflate;
    }

    private void a(View view) {
        SafeWebView safeWebView = (SafeWebView) view.findViewById(R$id.web_view);
        this.b = safeWebView;
        a(safeWebView);
        b();
        a(new JSInterface(this.f11105a));
        a();
        SafeWebView safeWebView2 = this.b;
        if (safeWebView2 != null) {
            safeWebView2.setBackgroundColor(0);
            this.b.getBackground().setAlpha(0);
        }
    }

    private void a() {
        if (TextUtils.isEmpty(this.c)) {
            HwLog.i("BottomPayDialogFragment", "url is empty");
            dismiss();
            return;
        }
        FlavorConfig.safeHwLog("BottomPayDialogFragment", "mUrl:" + this.c);
        if (!CommonUtils.f()) {
            ds.a(R$string.IDS_network_connect_error);
            dismiss();
            return;
        }
        at a2 = as.b().a();
        if (a2 != null && a2.e(this.c)) {
            a(true);
            SafeWebView safeWebView = this.b;
            String str = this.c;
            safeWebView.loadUrl(str);
            WebViewInstrumentation.loadUrl(safeWebView, str);
            return;
        }
        HwLog.i("BottomPayDialogFragment", "Url verify fail");
        dismiss();
    }

    public void a(boolean z) {
        HwLog.i("BottomPayDialogFragment", "setJsEnable isEnable:" + z);
        SafeWebView safeWebView = this.b;
        if (safeWebView != null) {
            safeWebView.getSettings().setJavaScriptEnabled(z);
        }
    }

    private void a(SafeWebView safeWebView) {
        if (safeWebView == null || Build.VERSION.SDK_INT < 29 || CommonUtils.isAndroid13()) {
            return;
        }
        safeWebView.getSettings().setForceDark(0);
    }

    private void b() {
        HwLog.i("BottomPayDialogFragment", "initWebViewSettings");
        FlavorConfig.openWebviewDebugMode();
        WebSettings settings = this.b.getSettings();
        settings.setCacheMode(2);
        settings.setAllowFileAccess(false);
        settings.setGeolocationEnabled(false);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        String userAgentString = this.b.getSettings().getUserAgentString();
        this.b.getSettings().setUserAgentString(userAgentString + "; HuaweiHealth");
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setTextZoom(100);
        settings.setLoadWithOverviewMode(true);
        settings.setMixedContentMode(2);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.b, true);
        this.b.setWebViewLoadCallBack(new WebViewLoadCallBack() { // from class: com.huawei.watchface.mvp.ui.dialog.BottomVipPayDialogFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.secure.android.common.webview.WebViewLoadCallBack
            public final void onCheckError(String str, WebViewLoadCallBack.ErrorCode errorCode) {
                BottomVipPayDialogFragment.a(str, errorCode);
            }
        });
        SafeWebSettings.initWebviewAndSettings(this.b);
        SafeWebView safeWebView = this.b;
        a aVar = new a();
        if (safeWebView instanceof SafeWebView) {
            APMSH5LoadInstrument.setSafeWebViewClient(safeWebView, aVar, false);
        } else {
            safeWebView.setWebViewClient(aVar, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String str, WebViewLoadCallBack.ErrorCode errorCode) {
        HwLog.e("BottomPayDialogFragment", "initWebViewSettingsEvent -- onCheckError: " + errorCode);
    }

    class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            HwLog.i("BottomPayDialogFragment", "onPageStarted");
            JsSafeUrlSystemParamManager.getInstance().a(WebViewUtils.a((WebView) BottomVipPayDialogFragment.this.b));
            at a2 = as.b().a();
            if (a2 == null || a2.a(str)) {
                return;
            }
            HwLog.e("BottomPayDialogFragment", "onPageStarted url is not in white list");
            webView.stopLoading();
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            HwLog.i("BottomPayDialogFragment", "onPageFinished");
            JsSafeUrlSystemParamManager.getInstance().a(WebViewUtils.a((WebView) BottomVipPayDialogFragment.this.b));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            HwLog.e("BottomPayDialogFragment", "on received ssl error");
            if (sslError != null) {
                WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, BottomVipPayDialogFragment.this.f11105a);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!TextUtils.isEmpty(str)) {
                return BottomVipPayDialogFragment.this.a(webView, str);
            }
            HwLog.w("BottomPayDialogFragment", "load url is null");
            return false;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            dc.a(BottomVipPayDialogFragment.this.f11105a, webView);
            if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
                HwLog.w("BottomPayDialogFragment", "shouldOverrideUrlLoading() load url request is null");
                return false;
            }
            if (!aq.a(webResourceRequest.getUrl().toString())) {
                return BottomVipPayDialogFragment.this.a(webView, webResourceRequest.getUrl().toString());
            }
            HwLog.e("BottomPayDialogFragment", "shouldOverrideUrlLoading() url contain xss char.");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(WebView webView, String str) {
        if (webView != null) {
            at a2 = as.b().a();
            if (a2 != null && !a2.a(str)) {
                HwLog.e("BottomPayDialogFragment", "openApkToHandleUrl url is not in white list");
                return true;
            }
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }
        return true;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().setGravity(80);
        return onCreateDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getActivity() != null && getActivity().getWindowManager() != null && getActivity().getWindowManager().getDefaultDisplay() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(getResources().getIdentifier("navigation_bar_height", "dimen", OsType.ANDROID));
        HwLog.i("BottomPayDialogFragment", "navigationBarHeight:" + dimensionPixelSize);
        getDialog().getWindow().setLayout(-1, displayMetrics.heightPixels - dimensionPixelSize);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.MatchWidthDialog);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        super.dismiss();
        HwLog.i("BottomPayDialogFragment", "dismiss enter");
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        HwLog.i("BottomPayDialogFragment", "onDismiss enter");
        JsInteractAddition.getInstance().b((WebView) null);
        WebViewUtils.a(this.b);
        this.b = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
