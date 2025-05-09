package com.huawei.health.h5pro.core;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.android.view.WindowManagerEx;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.jsbridge.system.container.H5Container;
import com.huawei.health.h5pro.jsbridge.system.container.H5ContainerEntry;
import com.huawei.health.h5pro.utils.AdjustResizeWithSoftInput;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.H5ProBuild;
import com.huawei.health.h5pro.utils.LanguageUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.vengine.H5ProEventInterceptor;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProNativeView;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.health.h5pro.vengine.H5ResultCallback;
import com.huawei.health.h5pro.webkit.H5ProLoadingRecordManager;
import com.huawei.health.h5pro.webkit.WebChromeCustomViewListener;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudjs.JsClientApi;
import com.huawei.operation.beans.TitleBean;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class H5ProWebViewActivity extends AppCompatActivity implements H5ProAppLoadListener, H5Container, H5ProViewControls, View.OnClickListener, WebChromeCustomViewListener {

    /* renamed from: a, reason: collision with root package name */
    public View f2375a;
    public WebChromeClient.CustomViewCallback d;
    public String e;
    public H5ProLoadingView f;
    public H5ProCommand g;
    public H5ProLaunchOption h;
    public H5ProInstance i;
    public H5ProWebView j;
    public Map<String, H5ProNativeView> l;
    public RelativeLayout n;
    public LinearLayout q;
    public final Analyzer b = new Analyzer();
    public boolean c = true;
    public int k = 0;
    public final Map<String, Boolean> o = new LinkedHashMap();
    public final Map<String, Long> m = new LinkedHashMap();

    private int a(boolean z, boolean z2, int i) {
        return z ? z2 ? i | 8192 : i : i | 4;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onNewPageStartLoad(H5ProInstance h5ProInstance, String str) {
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onResourceLoadErr(H5ProInstance h5ProInstance, String str, int i) {
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onViewPreCreate(H5ProInstance h5ProInstance, View view) {
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public void updateNativeView(final String str, final H5ProNativeView h5ProNativeView) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.18
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i(H5ProWebViewActivity.this.a(), "updateNativeView");
                if (TextUtils.isEmpty(str)) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "updateNativeView -> viewId is null");
                    return;
                }
                H5ProNativeView h5ProNativeView2 = h5ProNativeView;
                if (h5ProNativeView2 == null || h5ProNativeView2.getView() == null) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "updateNativeView -> nativeView is null");
                    return;
                }
                if (H5ProWebViewActivity.this.n == null) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "mRootView is null");
                    return;
                }
                if (H5ProWebViewActivity.this.l == null) {
                    H5ProWebViewActivity.this.l = new HashMap();
                }
                H5ProNativeView h5ProNativeView3 = (H5ProNativeView) H5ProWebViewActivity.this.l.get(str);
                if (h5ProNativeView3 != null && h5ProNativeView3.getView() != null) {
                    H5ProWebViewActivity.this.n.removeView(h5ProNativeView3.getView());
                }
                H5ProWebViewActivity.this.l.put(String.valueOf(H5ProWebViewActivity.this.k), h5ProNativeView);
                H5ProWebViewActivity.this.Xw_(h5ProNativeView.getView());
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public String showNativeView(final H5ProNativeView h5ProNativeView) {
        this.k++;
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.15
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i(H5ProWebViewActivity.this.a(), "showNativeView");
                H5ProNativeView h5ProNativeView2 = h5ProNativeView;
                if (h5ProNativeView2 == null || h5ProNativeView2.getView() == null) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "customNativeView is null");
                    return;
                }
                if (H5ProWebViewActivity.this.n == null) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "mRootView is null");
                    return;
                }
                if (H5ProWebViewActivity.this.l == null) {
                    H5ProWebViewActivity.this.l = new HashMap();
                }
                H5ProWebViewActivity.this.l.put(String.valueOf(H5ProWebViewActivity.this.k), h5ProNativeView);
                H5ProWebViewActivity.this.Xw_(h5ProNativeView.getView());
            }
        });
        return String.valueOf(this.k);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public String showNativeView(final View view) {
        this.k++;
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.14
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i(H5ProWebViewActivity.this.a(), "showNativeView");
                if (view == null) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "nativeView is null");
                    return;
                }
                if (H5ProWebViewActivity.this.n == null) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "mRootView is null");
                    return;
                }
                if (H5ProWebViewActivity.this.l == null) {
                    H5ProWebViewActivity.this.l = new HashMap();
                }
                H5ProWebViewActivity.this.l.put(String.valueOf(H5ProWebViewActivity.this.k), new H5ProNativeView(view, null));
                H5ProWebViewActivity.this.Xw_(view);
            }
        });
        return String.valueOf(this.k);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void setScreenLandscape(boolean z) {
        setRequestedOrientation(!z ? 1 : 0);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void setPageTitle(String str) {
        LogUtil.i(a(), "setPageTitle: " + str);
        this.e = str;
        e(str);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void setImmerse(final ImmerseInfo immerseInfo) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.11
            @Override // java.lang.Runnable
            public void run() {
                if (!immerseInfo.isImmerse()) {
                    H5ProWebViewActivity.this.e(immerseInfo.isStatusBarTextBlack());
                    H5ProWebViewActivity.this.f();
                } else {
                    H5ProWebViewActivity.this.j();
                    H5ProWebViewActivity.this.d(immerseInfo.isShowStatusBar(), immerseInfo.isStatusBarTextBlack(), immerseInfo.isStartAtBottomOfStatusBar(), immerseInfo.isHideBottomVirtualKeys());
                }
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public void removeNativeView(final String str) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.16
            @Override // java.lang.Runnable
            public void run() {
                H5ProNativeView h5ProNativeView;
                LogUtil.i(H5ProWebViewActivity.this.a(), "removeCustomNativeView");
                if (TextUtils.isEmpty(str)) {
                    LogUtil.i(H5ProWebViewActivity.this.a(), "removeCustomNativeView -> viewId is null.");
                    return;
                }
                if (H5ProWebViewActivity.this.l == null || (h5ProNativeView = (H5ProNativeView) H5ProWebViewActivity.this.l.get(str)) == null) {
                    return;
                }
                H5ProWebViewActivity.this.l.remove(str);
                if (H5ProWebViewActivity.this.n != null) {
                    H5ProWebViewActivity.this.n.removeView(h5ProNativeView.getView());
                }
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public void removeAllNativeView() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.17
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i(H5ProWebViewActivity.this.a(), "removeAllCustomNativeView");
                if (H5ProWebViewActivity.this.l == null || H5ProWebViewActivity.this.l.isEmpty()) {
                    LogUtil.w(H5ProWebViewActivity.this.a(), "no custom native view");
                    return;
                }
                for (H5ProNativeView h5ProNativeView : H5ProWebViewActivity.this.l.values()) {
                    if (H5ProWebViewActivity.this.n != null && h5ProNativeView != null) {
                        H5ProWebViewActivity.this.n.removeView(h5ProNativeView.getView());
                    }
                }
                H5ProWebViewActivity.this.l.clear();
            }
        });
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void registryTitleBarCallback(long j, String str) {
        LogUtil.i(a(), "registryTitleBarCallback:" + str + j);
        str.hashCode();
        if (str.equals(TitleBean.RIGHT_BTN_TYPE_SHARE)) {
            this.m.put(str, Long.valueOf(j));
            this.o.put(str, Boolean.TRUE);
            c(true);
        } else {
            LogUtil.e(a(), "registryTitleBarCallback unknown type:" + str);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onViewCreated(H5ProInstance h5ProInstance, View view) {
        int i;
        LogUtil.i(a(), "onViewCreated");
        H5ProCommand h5ProCommand = this.g;
        if (h5ProCommand != null && h5ProCommand.getCommand() == 2) {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.progress_bar_bg);
            if (h()) {
                i = Color.rgb(30, 30, 30);
                H5ProLaunchOption h5ProLaunchOption = this.h;
                if (h5ProLaunchOption != null && h5ProLaunchOption.isImmerse()) {
                    setImmerse(new ImmerseInfo(true, this.h.isShowStatusBar(), false, this.h.isStartAtBottomOfStatusBar(), this.h.isHideBottomVirtualKeys()));
                }
            } else {
                H5ProLaunchOption h5ProLaunchOption2 = this.h;
                if (h5ProLaunchOption2 != null && h5ProLaunchOption2.isImmerse()) {
                    setImmerse(new ImmerseInfo(true, this.h.isShowStatusBar(), true, this.h.isStartAtBottomOfStatusBar(), this.h.isHideBottomVirtualKeys()));
                }
                i = -1;
            }
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
                relativeLayout.setBackgroundColor(i);
            }
        }
        if (TextUtils.isEmpty(this.e)) {
            String appName = h5ProInstance.getAppInfo().getAppName();
            if (TextUtils.isEmpty(appName)) {
                return;
            }
            e(appName);
        }
    }

    @Override // com.huawei.health.h5pro.webkit.WebChromeCustomViewListener
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        LogUtil.i(a(), "onShowCustomView start");
        if (this.f2375a != null) {
            customViewCallback.onCustomViewHidden();
        } else {
            if (this.n == null || view == null) {
                return;
            }
            this.f2375a = view;
            this.d = customViewCallback;
            runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.12
                @Override // java.lang.Runnable
                public void run() {
                    H5ProWebViewActivity.this.f2375a.setLayoutParams(new RelativeLayout.LayoutParams(H5ProWebViewActivity.this.n.getWidth(), H5ProWebViewActivity.this.n.getHeight()));
                    H5ProWebViewActivity.this.f2375a.setBackgroundColor(Color.rgb(0, 0, 0));
                    H5ProWebViewActivity h5ProWebViewActivity = H5ProWebViewActivity.this;
                    h5ProWebViewActivity.Xw_(h5ProWebViewActivity.f2375a);
                    if (H5ProWebViewActivity.this.j != null) {
                        H5ProWebViewActivity.this.j.setVisibility(8);
                    }
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.i(a(), "onSaveInstanceState");
        super.onSaveInstanceState(bundle);
        H5ProEnvParamsGuardian.saveInBundle(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.i(a(), "onResume");
        super.onResume();
        H5ProWebView h5ProWebView = this.j;
        if (h5ProWebView != null) {
            h5ProWebView.onResume();
        }
        H5ProInstance h5ProInstance = this.i;
        if (h5ProInstance != null) {
            this.b.resume(h5ProInstance.getUrl(), this.i.getAppInfo());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        H5ProBridgeManager.getInstance().notifyPermissionResult(this.i, i, strArr, iArr);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onReceiveTitle(H5ProInstance h5ProInstance, String str) {
        if (str == null) {
            str = "";
        }
        e(str);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onProgressChanged(H5ProInstance h5ProInstance, int i) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        if (progressBar != null) {
            if (i == 100) {
                progressBar.setVisibility(8);
            } else {
                progressBar.setVisibility(0);
                progressBar.setProgress(i);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.i(a(), "onPause");
        super.onPause();
        H5ProWebView h5ProWebView = this.j;
        if (h5ProWebView != null) {
            h5ProWebView.onPause();
        }
        H5ProInstance h5ProInstance = this.i;
        if (h5ProInstance != null) {
            this.b.pause(h5ProInstance.getUrl(), this.i.getAppInfo());
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onNewPageLoaded(H5ProInstance h5ProInstance, String str) {
        LogUtil.i(a(), "onNewPageLoaded.");
        H5ProLoadingView h5ProLoadingView = this.f;
        if (h5ProLoadingView != null) {
            h5ProLoadingView.post(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    String a2 = H5ProWebViewActivity.this.a();
                    String[] strArr = new String[1];
                    StringBuilder sb = new StringBuilder("mLoadingView.setVisibility - > mLoadingView: ");
                    sb.append(H5ProWebViewActivity.this.f == null);
                    strArr[0] = sb.toString();
                    LogUtil.i(a2, strArr);
                    if (H5ProWebViewActivity.this.f != null) {
                        H5ProWebViewActivity.this.f.setVisibility(8);
                    }
                }
            });
        }
        H5ProWebView h5ProWebView = this.j;
        if (h5ProWebView != null) {
            h5ProWebView.postDelayed(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    RelativeLayout relativeLayout = (RelativeLayout) H5ProWebViewActivity.this.findViewById(R.id.progress_bar_bg);
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(8);
                    }
                }
            }, 30L);
        }
        if (TextUtils.isEmpty(this.e)) {
            String title = h5ProInstance.getTitle();
            if (!TextUtils.isEmpty(title)) {
                e(title);
            }
        }
        if (this.c) {
            this.c = false;
            this.b.resume(str, h5ProInstance == null ? null : h5ProInstance.getAppInfo());
        }
        this.b.loadNewPage(str, h5ProInstance != null ? h5ProInstance.getAppInfo() : null);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i(a(), "onNewIntent");
        if (intent == null) {
            finish();
            return;
        }
        setIntent(intent);
        if (!g()) {
            finish();
        } else {
            this.c = true;
            d(false);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4) {
            return false;
        }
        b();
        return false;
    }

    @Override // com.huawei.health.h5pro.webkit.WebChromeCustomViewListener
    public void onHideCustomView() {
        LogUtil.i(a(), "onHideCustomView start");
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.13
            @Override // java.lang.Runnable
            public void run() {
                if (H5ProWebViewActivity.this.j != null) {
                    H5ProWebViewActivity.this.j.setVisibility(0);
                }
                if (H5ProWebViewActivity.this.f2375a == null || H5ProWebViewActivity.this.d == null || H5ProWebViewActivity.this.n == null) {
                    return;
                }
                H5ProWebViewActivity.this.f2375a.setVisibility(8);
                H5ProWebViewActivity.this.n.removeView(H5ProWebViewActivity.this.f2375a);
                H5ProWebViewActivity.this.d.onCustomViewHidden();
                H5ProWebViewActivity.this.f2375a = null;
                H5ProWebViewActivity.this.d = null;
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onFloatingBarRequested(final RecyclerView.Adapter adapter) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.5
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = (RecyclerView) H5ProWebViewActivity.this.findViewById(R.id.bottom_floating_bar);
                if (recyclerView == null) {
                    return;
                }
                recyclerView.setVisibility(0);
                recyclerView.setLayoutManager(new LinearLayoutManager(H5ProWebViewActivity.this, 0, false));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onException(H5ProInstance h5ProInstance, String str) {
        LogUtil.e(a(), "onException: load h5 application failed", str);
        H5ProLoadingView h5ProLoadingView = this.f;
        if (h5ProLoadingView == null) {
            LogUtil.w(a(), "onException->mLoadingView is null");
        } else {
            h5ProLoadingView.post(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    if (H5ProWebViewActivity.this.f != null) {
                        H5ProWebViewActivity.this.f.setVisibility(8);
                    }
                }
            });
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.i(a(), "onDestroy");
        H5ProCommand h5ProCommand = this.g;
        if (h5ProCommand != null) {
            H5ProLoadingRecordManager.e.removeLoadingRecord(h5ProCommand.getPackageName());
        }
        if (this.i != null) {
            this.i = null;
        }
        super.onDestroy();
        this.b.leave();
        Map<String, H5ProNativeView> map = this.l;
        if (map != null && !map.isEmpty()) {
            removeAllNativeView();
        }
        this.j = null;
        this.n = null;
        this.f2375a = null;
        this.d = null;
        this.f = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.i(a(), "onCreate");
        if (!g()) {
            finish();
            return;
        }
        this.c = true;
        if (bundle != null) {
            try {
                if (bundle.getBoolean("IS_TRIGGER_CONFIG_GUARDIAN_KEY", false)) {
                    H5ProConfigGuardian.b.onConfigGuardian(this);
                    H5ProEnvParamsGuardian.restoreFromBundle(bundle);
                }
            } catch (InflateException e) {
                LogUtil.e(a(), "onCreate: exception -> " + e.getMessage());
                finish();
                return;
            }
        }
        n();
        setContentView(R.layout.activity_h5pro_webview);
        this.n = (RelativeLayout) findViewById(R.id.activity);
        this.f = (H5ProLoadingView) findViewById(R.id.hplv_loadingView);
        i();
        Xx_(this);
        d(true);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        H5ProWebView h5ProWebView = this.j;
        if (h5ProWebView != null) {
            h5ProWebView.onConfigurationChanged();
        }
        k();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.hwappbarpattern_navigation_icon) {
            b();
        }
        if (view.getId() == R.id.hwappbarpattern_menu_icon) {
            m();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        JsClientApi.handleActivityResult(i, i2, intent);
        H5ProBridgeManager.getInstance().notifyActivityResult(this.i, i, i2, intent);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void keepScreenOn(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.10
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    H5ProWebViewActivity.this.getWindow().addFlags(128);
                } else {
                    H5ProWebViewActivity.this.getWindow().clearFlags(128);
                }
            }
        });
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void hideTitleBarIcon(String str) {
        this.o.put(str, Boolean.FALSE);
        c(false);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void goBack() {
        LogUtil.i(a(), "goBack");
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.7
            @Override // java.lang.Runnable
            public void run() {
                H5ProWebViewActivity.this.b();
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public H5ProNativeView getNativeView(String str) {
        Map<String, H5ProNativeView> map = this.l;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.l.get(str);
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void exitWithResult(String str) {
        LogUtil.i(a(), "exitWithResult");
        Intent intent = new Intent();
        intent.putExtra("result", str);
        setResult(-1, intent);
        exit();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.container.H5Container
    public void exit() {
        LogUtil.i(a(), "exit");
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.9
            @Override // java.lang.Runnable
            public void run() {
                H5ProEventInterceptor e = H5ProWebViewActivity.this.e();
                if (e == null) {
                    H5ProWebViewActivity.this.finish();
                } else {
                    e.onEvent(2, new BackOrExitCallback(H5ProWebViewActivity.this));
                }
            }
        });
    }

    private void m() {
        Long l = this.m.get(TitleBean.RIGHT_BTN_TYPE_SHARE);
        LogUtil.i(a(), "triggerShare:" + l);
        if (l != null) {
            this.i.getJsCbkInvoker().onSuccess("", l.longValue());
        }
    }

    private void c(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (H5ProWebViewActivity.this.q == null) {
                    return;
                }
                ViewStub viewStub = (ViewStub) H5ProWebViewActivity.this.q.findViewById(R.id.hwappbarpattern_menu_icon_container);
                int i = z ? 0 : 8;
                if (viewStub != null) {
                    viewStub.setVisibility(i);
                    ImageView imageView = (ImageView) H5ProWebViewActivity.this.findViewById(R.id.hwappbarpattern_menu_icon);
                    if (imageView != null) {
                        imageView.setAdjustViewBounds(true);
                        imageView.setMaxWidth(H5ProWebViewActivity.this.getResources().getDimensionPixelSize(R.dimen._2131363910_res_0x7f0a0846));
                        imageView.setImageResource(R.mipmap._2131821060_res_0x7f110204);
                        imageView.setOnClickListener(H5ProWebViewActivity.this);
                        imageView.setClickable(z);
                        imageView.setVisibility(i);
                        imageView.setImageTintList(new ColorStateList((int[][]) Array.newInstance((Class<?>) Integer.TYPE, 1, 1), new int[]{H5ProWebViewActivity.this.getResources().getColor(R.color._2131297142_res_0x7f090376)}));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        TextView textView;
        LinearLayout linearLayout = this.q;
        if (linearLayout == null || (textView = (TextView) linearLayout.findViewById(R.id.hwappbarpattern_title)) == null) {
            return;
        }
        textView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, boolean z2, boolean z3, boolean z4) {
        RelativeLayout relativeLayout = this.n;
        if (relativeLayout == null) {
            return;
        }
        relativeLayout.setFitsSystemWindows(false);
        this.n.setPadding(0, (z && z3) ? CommonUtil.getStatusBarHeight(this) : 0, 0, 0);
        Window window = getWindow();
        window.clearFlags(AppRouterExtras.COLDSTART);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(a(z, z2, z4 ? 5378 : 5376));
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
    }

    private void Xx_(Activity activity) {
        LogUtil.i(a(), "initStatusBar");
        H5ProLaunchOption h5ProLaunchOption = this.h;
        if (h5ProLaunchOption == null || !h5ProLaunchOption.isNeedSoftInputAdapter()) {
            return;
        }
        AdjustResizeWithSoftInput.assistActivity(activity);
    }

    private void n() {
        if (H5ProBuild.b) {
            return;
        }
        if (H5ProBuild.isEmuiGe100() || H5ProBuild.isNewHonor()) {
            new WindowManagerEx.LayoutParamsEx(getWindow().getAttributes()).setDisplaySideMode(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        RelativeLayout relativeLayout = this.n;
        if (relativeLayout == null) {
            return;
        }
        relativeLayout.setFitsSystemWindows(true);
        this.n.setPadding(0, CommonUtil.getStatusBarHeight(this), 0, 0);
        getWindow().getDecorView().setSystemUiVisibility(z ? 9216 : 1024);
    }

    private void e(final String str) {
        LogUtil.i(a(), "refreshTitle: " + str);
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.8
            @Override // java.lang.Runnable
            public void run() {
                H5ProWebViewActivity.this.d(str);
            }
        });
    }

    private void k() {
        if (this.i != null) {
            this.i.onMultiWindowModeChange(CommonUtil.getActivityWindowMode(this));
        }
    }

    private void d(boolean z) {
        if (z) {
            H5ProLaunchOption h5ProLaunchOption = this.h;
            if (h5ProLaunchOption == null || !h5ProLaunchOption.isImmerse()) {
                f();
            } else {
                j();
            }
        }
        H5ProWebView h5ProWebView = (H5ProWebView) findViewById(R.id.webView);
        this.j = h5ProWebView;
        if (this.n == null || h5ProWebView == null) {
            finish();
        }
        this.j.setAppLoaderListener(this);
        this.i = this.j.getInstance();
        this.j.setViewControls(this);
        H5ContainerEntry h5ContainerEntry = new H5ContainerEntry(this);
        h5ContainerEntry.onMount(this, this.i);
        this.i.registerJsModule("container", h5ContainerEntry);
        H5ProLaunchOption h5ProLaunchOption2 = this.h;
        if (h5ProLaunchOption2 != null) {
            this.i.setIsEnableOnPauseCallback(h5ProLaunchOption2.isEnableOnPauseCallback());
            this.i.setIsEnableOnResumeCallback(this.h.isEnableOnResumeCallback());
            this.i.setIsEnableOnDestroyCallback(this.h.isEnableOnDestroyCallback());
            this.i.setIsEnableSelfProtection(this.h.isEnableSelfProtection());
        }
        this.j.requestFocusToContentView();
        this.j.execute(this.g, this.f);
        this.j.setCustomViewListener(this);
    }

    private boolean h() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.toolbar);
        this.q = linearLayout;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setVisibility(0);
        ViewStub viewStub = (ViewStub) this.q.findViewById(R.id.hwappbarpattern_navigation_icon_container);
        if (viewStub != null) {
            viewStub.setVisibility(0);
            ImageView imageView = (ImageView) findViewById(R.id.hwappbarpattern_navigation_icon);
            if (imageView != null) {
                if (CommonUtil.isFlyme() && Build.VERSION.SDK_INT <= 28) {
                    Xv_(imageView);
                }
                imageView.setVisibility(0);
                imageView.setOnClickListener(this);
                imageView.setClickable(true);
            }
        }
        this.o.put(TitleBean.RIGHT_BTN_TYPE_SHARE, Boolean.FALSE);
    }

    private boolean g() {
        try {
            Bundle bundleExtra = getIntent().getBundleExtra("com.huawei.health.h5pro.MESSAGE");
            if (bundleExtra == null) {
                LogUtil.w(a(), "initConfigInfo: bundle is null");
                return false;
            }
            H5ProCommand h5ProCommand = (H5ProCommand) bundleExtra.getParcelable("com.huawei.health.h5pro.MESSAGE");
            this.g = h5ProCommand;
            if (h5ProCommand == null) {
                LogUtil.w(a(), "initConfigInfo: mH5ProCommand is null");
                return false;
            }
            this.h = h5ProCommand.getLaunchOption();
            return true;
        } catch (BadParcelableException e) {
            LogUtil.e(a(), "initConfigInfo: exception -> " + e.getMessage());
            return false;
        }
    }

    private void i() {
        LogUtil.i(a(), "initStatusBar");
        H5ProLaunchOption h5ProLaunchOption = this.h;
        int screenOrientation = h5ProLaunchOption == null ? -1 : h5ProLaunchOption.getScreenOrientation();
        if (screenOrientation != -1) {
            setRequestedOrientation(screenOrientation);
        }
        H5ProLaunchOption h5ProLaunchOption2 = this.h;
        if (h5ProLaunchOption2 == null || !h5ProLaunchOption2.isImmerse()) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(getResources().getColor(R.color._2131297136_res_0x7f090370));
            if (!h()) {
                getWindow().getDecorView().setSystemUiVisibility(9216);
            }
        } else {
            d(this.h.isShowStatusBar(), this.h.getStatusBarTextColor(), this.h.isStartAtBottomOfStatusBar(), this.h.isHideBottomVirtualKeys());
        }
        if (h()) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color._2131296658_res_0x7f090192));
        }
        H5ProLaunchOption h5ProLaunchOption3 = this.h;
        if (h5ProLaunchOption3 != null) {
            if ("none".equals(h5ProLaunchOption3.getAnim())) {
                overridePendingTransition(0, 0);
            }
            if (this.h.isEnableSelfProtection()) {
                getWindow().addFlags(8192);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.toolbar);
        this.q = linearLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    private void c() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProWebViewActivity.6
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView recyclerView = (RecyclerView) H5ProWebViewActivity.this.findViewById(R.id.bottom_floating_bar);
                if (recyclerView == null) {
                    return;
                }
                recyclerView.setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.i(a(), "h5GoBackRaw");
        if (this.f2375a != null && this.d != null) {
            onHideCustomView();
            return;
        }
        c();
        c(false);
        H5ProInstance h5ProInstance = this.i;
        if (h5ProInstance != null) {
            h5ProInstance.goBack(new BackOrExitCallback(this));
        } else {
            LogUtil.i(a(), "h5GoBackRaw mH5ProInstance is null");
            exit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.i(a(), "h5GoBack");
        H5ProEventInterceptor e = e();
        if (e == null) {
            d();
        } else {
            H5ProInstance h5ProInstance = this.i;
            e.onEvent((h5ProInstance == null || !h5ProInstance.canGoBack()) ? 2 : 1, new EventInterceptorCallback(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        return LogUtil.getTag(this.i, "WebViewActivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public H5ProEventInterceptor e() {
        Map<String, H5ProNativeView> map = this.l;
        if (map == null) {
            return null;
        }
        for (H5ProNativeView h5ProNativeView : map.values()) {
            if (h5ProNativeView.getEventInterceptor() != null) {
                return h5ProNativeView.getEventInterceptor();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Xw_(View view) {
        if (view == null || this.n == null) {
            LogUtil.w(a(), "addChildViewToRootView: childView or mRootView is null");
            return;
        }
        try {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                LogUtil.i(a(), "addChildViewToRootView: the specified child already has a parent");
                ((ViewGroup) parent).removeView(view);
            }
            this.n.addView(view);
        } catch (IllegalStateException e) {
            LogUtil.e(a(), "addChildViewToRootView: exception -> " + e.getMessage());
        }
    }

    private void Xv_(ImageView imageView) {
        imageView.setImageResource(LanguageUtil.isRtlLanguage(this) ? R.mipmap._2131820922_res_0x7f11017a : R.mipmap._2131820919_res_0x7f110177);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = CommonUtil.dip2px(this, 24.0f);
        layoutParams.height = CommonUtil.dip2px(this, 24.0f);
        imageView.setLayoutParams(layoutParams);
    }

    public static class BackOrExitCallback extends CommonResultCallback {
        @Override // com.huawei.health.h5pro.core.H5ProWebViewActivity.CommonResultCallback
        public void onCallResult(H5ProWebViewActivity h5ProWebViewActivity, Boolean bool) {
            if (bool.booleanValue()) {
                h5ProWebViewActivity.finish();
            }
        }

        public BackOrExitCallback(H5ProWebViewActivity h5ProWebViewActivity) {
            super(h5ProWebViewActivity);
        }
    }

    public static abstract class CommonResultCallback implements H5ResultCallback {
        public static final String RESULT_TAG = "H5PRO_CommonResultCallback";
        public final WeakReference<H5ProWebViewActivity> mWrH5ProWebViewActivity;

        public abstract void onCallResult(H5ProWebViewActivity h5ProWebViewActivity, Boolean bool);

        @Override // com.huawei.health.h5pro.vengine.H5ResultCallback
        public void onResult(Boolean bool) {
            H5ProWebViewActivity h5ProWebViewActivity = (H5ProWebViewActivity) GeneralUtil.getReferent(this.mWrH5ProWebViewActivity);
            if (h5ProWebViewActivity != null) {
                onCallResult(h5ProWebViewActivity, bool);
            } else {
                LogUtil.w(RESULT_TAG, "onResult: h5ProWebViewActivity is null");
            }
        }

        public CommonResultCallback(H5ProWebViewActivity h5ProWebViewActivity) {
            this.mWrH5ProWebViewActivity = new WeakReference<>(h5ProWebViewActivity);
        }
    }

    public static class EventInterceptorCallback extends CommonResultCallback {
        @Override // com.huawei.health.h5pro.core.H5ProWebViewActivity.CommonResultCallback
        public void onCallResult(H5ProWebViewActivity h5ProWebViewActivity, Boolean bool) {
            LogUtil.i(h5ProWebViewActivity.a(), "interceptor go back onResult " + bool);
            if (bool.booleanValue()) {
                h5ProWebViewActivity.d();
            }
        }

        public EventInterceptorCallback(H5ProWebViewActivity h5ProWebViewActivity) {
            super(h5ProWebViewActivity);
        }
    }
}
