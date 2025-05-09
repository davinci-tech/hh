package com.huawei.health.h5pro.core;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.jsbridge.H5ProJsBridge;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.jsbridge.system.option.LaunchOptionEntry;
import com.huawei.health.h5pro.jsbridge.system.prerequest.PreRequestEntry;
import com.huawei.health.h5pro.load.JsInterfacePreRequestTask;
import com.huawei.health.h5pro.service.H5ProServiceLiveTerm;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.h5pro.service.theme.ThemeSvc;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.vengine.H5ProExecJsValueCbk;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatus;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatusListener;
import com.huawei.health.h5pro.view.LoadErrorView;
import com.huawei.health.h5pro.webkit.WebChromeCustomViewListener;
import com.huawei.health.h5pro.webkit.WebKitInstance;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class H5ProWebView extends LinearLayout implements H5ProAppLoadListener, WebViewConfigChangeListener, H5ProInstanceStatusListener {

    /* renamed from: a, reason: collision with root package name */
    public View f2374a;
    public boolean b;
    public boolean c;
    public boolean d;
    public final Analyzer e;
    public H5ProLoadingView f;
    public Context g;
    public H5ProInstance h;
    public H5ProAppLoader i;
    public H5ProCommand j;
    public ThemeSvc k;
    public PreRequestEntry l;
    public WeakReference<H5ProAppLoadListener> m;
    public String n;

    public void setViewControls(H5ProViewControls h5ProViewControls) {
        H5ProInstance h5ProInstance = this.h;
        if (h5ProInstance != null) {
            h5ProInstance.setViewControls(h5ProViewControls);
        }
    }

    public void setCustomViewListener(WebChromeCustomViewListener webChromeCustomViewListener) {
        H5ProInstance h5ProInstance = this.h;
        if (h5ProInstance instanceof WebKitInstance) {
            ((WebKitInstance) h5ProInstance).setCustomViewListener(webChromeCustomViewListener);
        }
    }

    public void setAppLoaderListener(H5ProAppLoadListener h5ProAppLoadListener) {
        this.m = new WeakReference<>(h5ProAppLoadListener);
    }

    public void requestFocusToContentView() {
        this.c = true;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onViewPreCreate(H5ProInstance h5ProInstance, View view) {
        this.f2374a = view;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onViewCreated(H5ProInstance h5ProInstance, View view) {
        H5ProReverseControlExecutor.f2372a.put(h5ProInstance);
        this.f2374a = view;
        post(new NoParameterRunnable(this, "LOAD_CONTENT_VIEW"));
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onViewCreated(h5ProInstance, view);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.load.H5ProInstanceStatusListener
    public void onStatusChanged(H5ProInstanceStatus h5ProInstanceStatus) {
        LogUtil.i("H5PRO_H5ProWebView", "onStatusChanged: " + h5ProInstanceStatus);
        H5ProInstance h5ProInstance = this.h;
        if (h5ProInstance != null && h5ProInstanceStatus == H5ProInstanceStatus.CONFIG_PREPARED) {
            if (h5ProInstance.getAppInfo() == null) {
                H5ProAppInfo h5ProAppInfo = new H5ProAppInfo();
                h5ProAppInfo.setPkgName(this.n);
                h5ProInstance.setAppInfo(h5ProAppInfo);
            }
            h5ProInstance.onStatusChanged(h5ProInstanceStatus);
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new JsInterfacePreRequestTask(this.n, this.g, h5ProInstance, this.l));
            newSingleThreadExecutor.shutdown();
        }
    }

    public void onResume() {
        H5ProInstance h5ProInstance = this.h;
        if (h5ProInstance != null) {
            h5ProInstance.onResume();
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onResourceLoadErr(H5ProInstance h5ProInstance, String str, int i) {
        this.e.loadPageErr(h5ProInstance, str, i);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onReceiveTitle(H5ProInstance h5ProInstance, String str) {
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onReceiveTitle(h5ProInstance, str);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onProgressChanged(H5ProInstance h5ProInstance, int i) {
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onProgressChanged(h5ProInstance, i);
        }
    }

    public void onPause() {
        H5ProInstance h5ProInstance = this.h;
        if (h5ProInstance != null) {
            h5ProInstance.onPause();
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onNewPageStartLoad(H5ProInstance h5ProInstance, String str) {
        if (this.d) {
            this.d = false;
            post(new NoParameterRunnable(this, "LOAD_CONTENT_VIEW"));
        }
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onNewPageStartLoad(h5ProInstance, str);
        }
        this.e.startLoading(str);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onNewPageLoaded(H5ProInstance h5ProInstance, String str) {
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onNewPageLoaded(h5ProInstance, str);
        }
        H5ProAppInfo appInfo = h5ProInstance == null ? null : h5ProInstance.getAppInfo();
        this.e.setAppInfo(appInfo);
        this.e.endLoading(str);
        this.e.reportLoadResult(str, "0", appInfo);
        if (h5ProInstance == null || h5ProInstance.isLoaded() || appInfo == null) {
            return;
        }
        h5ProInstance.setLoaded();
        this.e.biFinishLoadH5App(appInfo.getPkgName(), str);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onFloatingBarRequested(RecyclerView.Adapter adapter) {
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onFloatingBarRequested(adapter);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoadListener
    public void onException(H5ProInstance h5ProInstance, String str) {
        LogUtil.e(e(), "load h5 application failed", str);
        this.d = true;
        post(new NoParameterRunnable(this, "SHOW_EXCEPTION_VIEW"));
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.m);
        if (h5ProAppLoadListener != null) {
            h5ProAppLoadListener.onException(h5ProInstance, str);
        }
        this.e.reportLoadResult(h5ProInstance == null ? "" : h5ProInstance.getUrl(), str, h5ProInstance == null ? null : h5ProInstance.getAppInfo());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        b();
        super.onDetachedFromWindow();
        this.g = null;
        this.h = null;
        this.i = null;
        this.m = null;
    }

    @Override // com.huawei.health.h5pro.core.WebViewConfigChangeListener
    public void onConfigurationChanged() {
        if (this.h != null) {
            H5ProBridgeManager.getInstance().onConfigurationChanged(this.h);
        }
    }

    public H5ProInstance getInstance() {
        return this.h;
    }

    public void execute(H5ProCommand h5ProCommand, H5ProLoadingView h5ProLoadingView) {
        this.f = h5ProLoadingView;
        execute(h5ProCommand);
    }

    public void execute(H5ProCommand h5ProCommand) {
        d(h5ProCommand, true);
    }

    public void execJs(String str, H5ProExecJsValueCbk h5ProExecJsValueCbk) {
        H5ProAppLoader h5ProAppLoader = this.i;
        if (h5ProAppLoader == null) {
            LogUtil.w(e(), "execJs: mH5ProAppLoader is null");
        } else {
            h5ProAppLoader.execJs(str, h5ProExecJsValueCbk);
        }
    }

    private String e() {
        return LogUtil.getTag(this.h, "WebView");
    }

    private void b() {
        LogUtil.i(e(), "destroy");
        H5ProReverseControlExecutor.f2372a.remove(this.h);
        H5ProBridgeManager.getInstance().destroy(this.h);
        try {
            H5ProServiceManager.getInstance().autoUnregisterService(H5ProServiceLiveTerm.WEB_VIEW);
        } catch (Exception e) {
            LogUtil.e("H5PRO_H5ProWebView", e.getMessage());
        }
        H5ProInstance h5ProInstance = this.h;
        if (h5ProInstance != null) {
            h5ProInstance.destroy();
        }
        LogUtil.logCacheFlushing();
    }

    private void b(H5ProCommand h5ProCommand) {
        if (this.g == null) {
            LogUtil.w(e(), "loadApp: mContext is null");
            return;
        }
        if (this.h == null) {
            LogUtil.w(e(), "loadApp: mH5ProInstance is null");
            return;
        }
        post(new NoParameterRunnable(this, "SHOW_LOAD_VIEW"));
        a(h5ProCommand);
        if (this.i == null) {
            LogUtil.w(e(), "loadApp: mH5ProAppLoader is null");
            return;
        }
        String packageName = h5ProCommand.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            String url = h5ProCommand.getUrl();
            if (TextUtils.isEmpty(url)) {
                LogUtil.e(e(), "loadApp: packageName and url is empty");
                return;
            } else {
                LogUtil.i(e(), "load url");
                this.i.loadLightApp(url);
                return;
            }
        }
        LogUtil.i(e(), "load: " + packageName);
        this.n = packageName;
        H5ProLaunchOption launchOption = h5ProCommand.getLaunchOption();
        String path = launchOption == null ? null : launchOption.getPath();
        this.h.setPath(path);
        this.i.loadMiniProgram(packageName, path);
    }

    private void a(H5ProCommand h5ProCommand) {
        LaunchOptionEntry launchOptionEntry;
        if (this.b) {
            LogUtil.w("H5PRO_H5ProWebView", "initLaunchOption: isInitializedLaunchOption -> true");
            return;
        }
        this.b = true;
        H5ProLaunchOption launchOption = h5ProCommand.getLaunchOption();
        if (launchOption != null) {
            if (launchOption.getForceDarkMode() >= 0) {
                WebKitInstance.setDarkMode(this.g, this.h.getWebView(), launchOption.getForceDarkMode());
            }
            if (launchOption.getGlobalBiParams() != null) {
                this.e.setGlobalParams(launchOption.getGlobalBiParams());
            }
            this.h.setBackGroundColor(launchOption.getBackgroundColor());
            c(launchOption);
            launchOptionEntry = new LaunchOptionEntry(launchOption.getAllCustomizeArg());
            launchOptionEntry.onMount(this.g, this.h, launchOption.getBundles().get("option"));
            this.h.setIsEnableImageCache(launchOption.isEnableImageCache());
            this.h.setIsDisableImageProxy(launchOption.isDisableImageProxy());
        } else {
            c((H5ProLaunchOption) null);
            launchOptionEntry = new LaunchOptionEntry("");
            launchOptionEntry.onMount(this.g, this.h);
        }
        this.h.registerJsModule("option", launchOptionEntry);
        PreRequestEntry preRequestEntry = new PreRequestEntry();
        this.l = preRequestEntry;
        this.h.registerJsModule(ConfigMapKeys.CLCT_PRE_REQ, preRequestEntry);
    }

    private void c(H5ProLaunchOption h5ProLaunchOption) {
        H5ProBridgeManager h5ProBridgeManager = H5ProBridgeManager.getInstance();
        this.h.setJsCbkInvoker(h5ProBridgeManager.createJsCbkInvoker(new H5ProBridgeCbkExecutorImpl(this.i)));
        if (h5ProLaunchOption == null) {
            LogUtil.w("H5PRO_H5ProWebView", "initBridge: h5ProLaunchOption is null");
            return;
        }
        H5ProJsBridge.registerBridgeClass(h5ProLaunchOption.isDisableAllJsExtModules(), h5ProLaunchOption.getJsExtModules());
        for (Map.Entry<String, JsModuleBase> entry : h5ProBridgeManager.createBridges(this.g, this.h, h5ProLaunchOption.getCustomizeJsModules(), h5ProLaunchOption.getBundles()).entrySet()) {
            this.h.registerJsModule(entry.getKey(), entry.getValue());
        }
    }

    private void c(Context context) {
        if (context == null) {
            LogUtil.w("H5PRO_H5ProWebView", "init: context is null");
            return;
        }
        this.g = context;
        if (!(context instanceof H5ProWebViewActivity)) {
            context.setTheme(R.style.CommonActivityTheme);
            LogUtil.i("H5PRO_H5ProWebView", "init: Context#setTheme");
        }
        H5ProInstance createInstance = H5ProEngineFactory.getH5ProEngine().createInstance(this.g, this);
        this.h = createInstance;
        this.i = createInstance.getAppLoader();
        this.k = new ThemeSvc(getResources());
        this.h.setSafeUrls(this.g.getResources().getStringArray(R.array._2130968696_res_0x7f040078));
        try {
            H5ProServiceManager.getInstance().registerService(this.k);
        } catch (Exception e) {
            LogUtil.e("H5PRO_H5ProWebView", e.getMessage());
        }
        this.h.registerInstanceStatusListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(H5ProCommand h5ProCommand, boolean z) {
        H5ProInstance h5ProInstance;
        if (h5ProCommand == null) {
            LogUtil.e("H5PRO_H5ProWebView", "h5ProCommand is null");
            return;
        }
        this.j = h5ProCommand;
        H5ProLoadingView h5ProLoadingView = this.f;
        if (h5ProLoadingView != null) {
            h5ProLoadingView.initLoadingView(h5ProCommand, this.h);
        }
        if (z) {
            Analyzer analyzer = new Analyzer();
            H5ProInstance h5ProInstance2 = this.h;
            if (h5ProInstance2 != null) {
                analyzer.setAppInfo(h5ProInstance2.getAppInfo());
            }
            analyzer.visitH5App(this.j.getPackageName(), this.j.getUrl());
            analyzer.biStartLoadH5App(h5ProCommand.getPackageName(), h5ProCommand.getUrl());
        }
        if (this.j.isEmbedded() && (h5ProInstance = this.h) != null) {
            h5ProInstance.setEmbedded();
        }
        if (this.j.getCommand() == 2) {
            b(this.j);
        } else {
            LogUtil.e("H5PRO_H5ProWebView", "invalid command");
        }
    }

    public static class NoParameterRunnable implements Runnable {
        public final WeakReference<H5ProWebView> b;
        public String e;

        @Override // java.lang.Runnable
        public void run() {
            H5ProWebView h5ProWebView = (H5ProWebView) GeneralUtil.getReferent(this.b);
            if (h5ProWebView == null) {
                LogUtil.w("H5PRO_H5ProWebView", "h5ProWebView is null");
                return;
            }
            if (TextUtils.equals("LOAD_CONTENT_VIEW", this.e)) {
                b(h5ProWebView);
            } else if (TextUtils.equals("SHOW_LOAD_VIEW", this.e)) {
                a(h5ProWebView);
            } else if (TextUtils.equals("SHOW_EXCEPTION_VIEW", this.e)) {
                d(h5ProWebView);
            }
        }

        private void a(H5ProWebView h5ProWebView) {
            h5ProWebView.removeAllViews();
            if (h5ProWebView.f != null) {
                return;
            }
            if (h5ProWebView.g == null) {
                LogUtil.w("H5PRO_H5ProWebView", "showLoadView：mContext is null");
            } else {
                LayoutInflater.from(h5ProWebView.g).inflate(R.layout.loading, h5ProWebView);
            }
        }

        private void d(final H5ProWebView h5ProWebView) {
            if (h5ProWebView.g == null) {
                LogUtil.w("H5PRO_H5ProWebView", "showExceptionView：mContext is null");
                return;
            }
            h5ProWebView.removeAllViews();
            LoadErrorView loadErrorView = new LoadErrorView(h5ProWebView.g);
            h5ProWebView.addView(loadErrorView, h5ProWebView.getLayoutParams());
            loadErrorView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.h5pro.core.H5ProWebView.NoParameterRunnable.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    H5ProWebView h5ProWebView2 = h5ProWebView;
                    h5ProWebView2.d(h5ProWebView2.j, false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        private void b(H5ProWebView h5ProWebView) {
            h5ProWebView.removeAllViews();
            if (h5ProWebView.f2374a == null) {
                LogUtil.w("H5PRO_H5ProWebView", "loadContentView：mContentView is null");
                return;
            }
            h5ProWebView.addView(h5ProWebView.f2374a, h5ProWebView.getLayoutParams());
            if (h5ProWebView.c) {
                h5ProWebView.f2374a.setFocusable(true);
                h5ProWebView.f2374a.requestFocus();
            }
        }

        public NoParameterRunnable(H5ProWebView h5ProWebView, String str) {
            this.e = "";
            this.b = new WeakReference<>(h5ProWebView);
            this.e = str;
        }
    }

    public H5ProWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new Analyzer();
        this.f2374a = null;
        this.d = false;
        this.c = false;
        this.b = false;
        c(context);
    }

    public H5ProWebView(Context context) {
        super(context);
        this.e = new Analyzer();
        this.f2374a = null;
        this.d = false;
        this.c = false;
        this.b = false;
        c(context);
    }
}
