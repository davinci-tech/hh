package com.huawei.health;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;
import androidx.core.view.PointerIconCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.dfx.DfxMonitorMgr;
import com.huawei.haf.dynamic.DynamicResourcesLoader;
import com.huawei.haf.language.LanguageManager;
import com.huawei.haf.language.LanguagePackage;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.HealthApplication;
import com.huawei.health.arkuix.engine.StageDelegateProxy;
import com.huawei.health.h5pro.core.H5ProConfigGuardian;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hwcommonmodel.application.RunningForegroundListener;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.h5pro.H5proUtil;
import defpackage.bxh;
import defpackage.bxp;
import defpackage.bzc;
import defpackage.ccj;
import defpackage.dyf;
import defpackage.ezv;
import defpackage.msn;
import defpackage.nrs;
import defpackage.nsw;
import health.compact.a.ApplicationLazyLoad;
import health.compact.a.BuildConfigProperties;
import health.compact.a.CommonUtil;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.DataBaseHelper;
import health.compact.a.DeviceConfigInit;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.EnvironmentUtils;
import health.compact.a.GrsDownloadPluginUrl;
import health.compact.a.HealthApplicationLazyLoadMgr;
import health.compact.a.HealthDfxMonitorMgr;
import health.compact.a.KeyManager;
import health.compact.a.LanguageDownloader;
import health.compact.a.LogUtil;
import health.compact.a.MessageCenterConfig;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class HealthApplication extends BaseApplication {

    /* renamed from: a, reason: collision with root package name */
    private bxh f2169a;
    private long e = 0;

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (EnvironmentUtils.c()) {
            return nrs.cKi_(super.getResources(), 1.45f);
        }
        return super.getResources();
    }

    @Override // com.huawei.haf.application.BaseApplication, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        BuildConfigProperties.c(getClass().getPackage().getName());
        SharedStoreManager.d(new bzc());
        if (q()) {
            AppBundle.c(context);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        long currentTimeMillis = System.currentTimeMillis();
        super.onCreate();
        o();
        b(System.currentTimeMillis());
        DfxMonitorCenter.b((Class<? extends DfxMonitorMgr>) HealthDfxMonitorMgr.class);
        ApplicationLazyLoad.c(HealthApplicationLazyLoadMgr.class);
        if (EnvironmentUtils.c() || EnvironmentUtils.e()) {
            ApplicationLazyLoad.b();
            DfxMonitorCenter.e();
        } else if (DaemonServiceSpUtils.d(this)) {
            ApplicationLazyLoad.b();
        }
        if (q()) {
            ReleaseLogUtil.b("TimeEat_HealthApplication", "HealthBundleConfiguration enter!");
            AppBundle.a(this, new ccj());
        }
        n();
        LogUtil.c("TimeEat_HealthApplication", "BUILD_TYPE is ", "release");
        if (ProcessUtil.d() && Process.is64Bit()) {
            StageDelegateProxy.getInstance().initStageApplication(this);
        }
        ReleaseLogUtil.b("TimeEat_HealthApplication", "onCreate finished, time cost: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.c("TimeEat_HealthApplication", "onConfigurationChanged");
        if (ProcessUtil.d() && Process.is64Bit()) {
            StageDelegateProxy.getInstance().changeConfiguration(configuration);
        }
    }

    private void o() {
        if (EnvironmentUtils.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bxn
                @Override // java.lang.Runnable
                public final void run() {
                    HealthApplication.this.l();
                }
            });
            H5ProConfigGuardian.b.addOnConfigGuardianListener(new H5ProConfigGuardian.OnConfigGuardianListener() { // from class: bxo
                @Override // com.huawei.health.h5pro.core.H5ProConfigGuardian.OnConfigGuardianListener
                public final void onConfigGuardian(Context context) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: bxm
                        @Override // java.lang.Runnable
                        public final void run() {
                            HealthApplication.h();
                        }
                    });
                }
            });
        }
    }

    public /* synthetic */ void l() {
        long currentTimeMillis = System.currentTimeMillis();
        HiHealthDictManager.d(getApplicationContext()).e(false);
        MessageCenterConfig.e();
        nsw.e(new WeakReference(this), R.layout.home_item_layout_three_circle, 2);
        ReleaseLogUtil.b("TimeEat_HealthApplication", "asyncInit finished, time cost: " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public static /* synthetic */ void h() {
        LogUtil.c("TimeEat_HealthApplication", "onConfigGuardian");
        H5proUtil.initH5pro();
    }

    private void n() {
        BaseApplication.we_(this, EnvironmentUtils.c());
        if (EnvironmentUtils.c()) {
            this.f2169a = new bxh();
            DynamicResourcesLoader.a(new bxp());
            BroadcastManager.wj_(this.f2169a);
            ezv.c();
            msn.c().e(new GrsDownloadPluginUrl());
            LanguageManager.a(new LanguagePackage(new LanguageDownloader(new GrsDownloadPluginUrl(true), null)));
        } else if (Build.VERSION.SDK_INT >= 28) {
            String b = ProcessUtil.b();
            if (TextUtils.isEmpty(b)) {
                WebView.setDataDirectorySuffix(String.valueOf(Process.myPid()));
                OpAnalyticsUtil.getInstance().setRiskWarningEvent("WebViewConfig", "WebView DataDirectorySuffix is empty");
            } else {
                WebView.setDataDirectorySuffix(b);
            }
            WebView.disableWebView();
        }
        if (EnvironmentUtils.c()) {
            r();
            p();
            t();
            s();
        }
        if (EnvironmentUtils.e()) {
            dyf.d();
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        if (EnvironmentUtils.c()) {
            m();
            DeviceConfigInit.destroy();
        }
        bxh bxhVar = this.f2169a;
        if (bxhVar != null) {
            BroadcastManager.wx_(bxhVar);
            this.f2169a = null;
        }
        DfxMonitorCenter.d();
        super.onTerminate();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i == 20) {
            ReleaseLogUtil.b("TimeEat_HealthApplication", "onTrimMemory level = TRIM_MEMORY_UI_HIDDEN");
            return;
        }
        if (EnvironmentUtils.c()) {
            ReleaseLogUtil.b("TimeEat_HealthApplication", "onTrimMemory level = ", Integer.valueOf(i));
            if (i >= 40) {
                DfxMonitorCenter.b();
            }
            if (i >= 80 && !c(60000L)) {
                ReleaseLogUtil.b("TimeEat_HealthApplication", "exit by app kill self");
                Process.killProcess(Process.myPid());
            } else {
                if (((i < 80 || !a(300000L)) && (i < 60 || !a(600000L))) || CommonUtil.g(this, "com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity")) {
                    return;
                }
                BaseApplication.b();
            }
        }
    }

    private boolean q() {
        return EnvironmentUtils.c() || EnvironmentUtils.e();
    }

    private void m() {
        if (CommonUtil.ax()) {
            ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).destroyOdmf();
        }
    }

    private void r() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.HealthApplication.2
            @Override // java.lang.Runnable
            public void run() {
                KeyManager.a(2);
                KeyManager.a(1);
            }
        });
    }

    private void t() {
        int[] iArr = {PointerIconCompat.TYPE_GRAB, 20005, 101010};
        for (int i = 0; i < 3; i++) {
            ThreadPoolManager.d().execute(new DataBaseHelper.a(String.valueOf(iArr[i])));
        }
    }

    private void p() {
        com.huawei.hihealth.HiAppInfo hiAppInfo = new com.huawei.hihealth.HiAppInfo();
        hiAppInfo.setPackageName("com.huawei.health");
        HiHealthNativeApi.a(this).initHiHealth(hiAppInfo);
    }

    private void s() {
        Object systemService = getApplicationContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (systemService instanceof WindowManager) {
            Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            CommonUtil.e(displayMetrics.widthPixels, "BaseApplication");
        }
    }

    public void c(RunningForegroundListener runningForegroundListener) {
        bxh bxhVar = this.f2169a;
        if (bxhVar != null) {
            bxhVar.e(runningForegroundListener);
        }
    }

    public long k() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }
}
