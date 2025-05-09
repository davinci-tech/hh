package com.huawei.health.h5pro;

import android.content.Context;
import com.huawei.health.h5pro.core.H5ProBridgeManager;
import com.huawei.health.h5pro.core.H5ProCommand;
import com.huawei.health.h5pro.core.H5ProEngineFactory;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProReverseControlExecutor;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.h5pro.ext.H5ProResidentExtManager;
import com.huawei.health.h5pro.ext.hostapp.H5ProHostAppRuntimeApi;
import com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor;
import com.huawei.health.h5pro.ext.version.H5ProVersionUpgradeExtApi;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.preload.IH5PreloadStrategy;
import com.huawei.health.h5pro.scalable.H5ProScalableServiceManager;
import com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.vengine.H5ProExecJsValueCbk;
import com.huawei.health.h5pro.webkit.QuickPackageManager;

/* loaded from: classes.dex */
public class H5ProClient {
    public static void start(String str) {
    }

    public static void useHmsLite(boolean z, String str) {
        EnvironmentHelper.getInstance().useHmsLite(z, str);
    }

    public static void startH5MiniProgram(String str, H5ProWebView h5ProWebView, H5ProLaunchOption h5ProLaunchOption) {
        if (h5ProWebView == null) {
            LogUtil.w("H5PRO_H5ProClient", "startH5MiniProgram: webView is null");
            return;
        }
        LogUtil.init(h5ProWebView.getContext());
        IH5ProAppLoadStorageService h5ProAppLoadStorageService = H5ProScalableServiceManager.getInstance().getH5ProAppLoadStorageService();
        if (h5ProAppLoadStorageService != null) {
            h5ProAppLoadStorageService.incrLoadCount(h5ProWebView.getContext(), str);
        }
        h5ProWebView.execute(new H5ProCommand.Builder().setupLoad().setPackageName(str).setLaunchOption(CommonUtil.parseQueryOption(str, h5ProLaunchOption)).setEmbedded().build());
    }

    public static void startH5MiniProgram(Context context, String str, H5ProLaunchOption h5ProLaunchOption) {
        LogUtil.init(context);
        IH5ProAppLoadStorageService h5ProAppLoadStorageService = H5ProScalableServiceManager.getInstance().getH5ProAppLoadStorageService();
        if (h5ProAppLoadStorageService != null) {
            h5ProAppLoadStorageService.incrLoadCount(context, str);
        }
        startActivity(context, new H5ProCommand.Builder().setupLoad().setPackageName(str).setLaunchOption(h5ProLaunchOption).build());
    }

    public static void startH5LightApp(String str, H5ProWebView h5ProWebView, H5ProLaunchOption h5ProLaunchOption) {
        if (h5ProWebView == null) {
            LogUtil.w("H5PRO_H5ProClient", "startH5LightApp: h5ProWebView is null");
        } else {
            LogUtil.init(h5ProWebView.getContext());
            h5ProWebView.execute(new H5ProCommand.Builder().setupLoad().setUrl(str).setLaunchOption(h5ProLaunchOption).setEmbedded().build());
        }
    }

    public static void startH5LightApp(Context context, String str, H5ProLaunchOption h5ProLaunchOption) {
        LogUtil.init(context);
        startActivity(context, new H5ProCommand.Builder().setupLoad().setUrl(str).setLaunchOption(CommonUtil.parseQueryOption(str, h5ProLaunchOption)).build());
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a6, code lost:
    
        if (r6 != 0) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void startActivity(final android.content.Context r5, final com.huawei.health.h5pro.core.H5ProCommand r6) {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            java.lang.Thread r1 = r1.getThread()
            if (r0 == r1) goto L20
            android.os.Handler r0 = new android.os.Handler
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            r0.<init>(r1)
            com.huawei.health.h5pro.H5ProClient$1 r1 = new com.huawei.health.h5pro.H5ProClient$1
            r1.<init>()
            r0.post(r1)
            return
        L20:
            java.lang.String r0 = "startActivity"
            java.lang.String[] r0 = new java.lang.String[]{r0}
            java.lang.String r1 = "H5PRO_H5ProClient"
            com.huawei.health.h5pro.utils.LogUtil.i(r1, r0)
            com.huawei.health.h5pro.core.H5ProLaunchOption r0 = r6.getLaunchOption()
            com.huawei.health.h5pro.utils.CommonUtil.preProcessOption(r0)
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.huawei.health.h5pro.core.H5ProWebViewActivity> r2 = com.huawei.health.h5pro.core.H5ProWebViewActivity.class
            r0.<init>(r5, r2)
            java.lang.Class<com.huawei.health.h5pro.core.H5ProCommand> r2 = com.huawei.health.h5pro.core.H5ProCommand.class
            java.lang.ClassLoader r2 = r2.getClassLoader()
            r0.setExtrasClassLoader(r2)
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.String r3 = "com.huawei.health.h5pro.MESSAGE"
            r2.putParcelable(r3, r6)
            r0.putExtra(r3, r2)
            java.lang.String r2 = r6.getPackageName()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L5f
            java.lang.String r3 = r6.getUrl()
            goto L60
        L5f:
            r3 = r2
        L60:
            java.lang.String r4 = "h5Flag"
            r0.putExtra(r4, r3)
            boolean r4 = r5 instanceof android.app.Activity
            if (r4 == 0) goto La9
            com.huawei.health.h5pro.core.H5ProLaunchOption r4 = r6.getLaunchOption()
            if (r4 == 0) goto L7b
            com.huawei.health.h5pro.core.H5ProLaunchOption r4 = r6.getLaunchOption()
            boolean r4 = r4.isForResult()
            if (r4 == 0) goto L7b
            r4 = 1
            goto L7c
        L7b:
            r4 = 0
        L7c:
            com.huawei.health.h5pro.core.H5ProLaunchOption r6 = r6.getLaunchOption()
            if (r4 == 0) goto La2
            int r6 = r6.getRequestCode()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "startActivityForResult: "
            r2.<init>(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            java.lang.String[] r2 = new java.lang.String[]{r2}
            com.huawei.health.h5pro.utils.LogUtil.i(r1, r2)
            android.app.Activity r5 = (android.app.Activity) r5
            r5.startActivityForResult(r0, r6)
            return
        La2:
            int r6 = com.huawei.health.h5pro.utils.CommonUtil.getActivityStartFlag(r6, r3)
            if (r6 == 0) goto Lae
            goto Lab
        La9:
            r6 = 268435456(0x10000000, float:2.524355E-29)
        Lab:
            r0.setFlags(r6)
        Lae:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r4 = "startActivity flags: "
            r6.<init>(r4)
            int r4 = r0.getFlags()
            r6.append(r4)
            java.lang.String r4 = ",h5Flag: "
            r6.append(r4)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            java.lang.String[] r6 = new java.lang.String[]{r6}
            com.huawei.health.h5pro.utils.LogUtil.i(r1, r6)
            r5.startActivity(r0)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto Lde
            com.huawei.health.h5pro.webkit.H5ProLoadingRecordManager r5 = com.huawei.health.h5pro.webkit.H5ProLoadingRecordManager.e
            r5.recordStartTime(r2)
        Lde:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.H5ProClient.startActivity(android.content.Context, com.huawei.health.h5pro.core.H5ProCommand):void");
    }

    public static void setUserFlag(String str) {
        EnvironmentHelper.getInstance().setUserFlag(str);
    }

    public static void setMobileAppEngine(boolean z) {
        EnvironmentHelper.getInstance().setMobileAppEngine(z);
    }

    public static void setBuildType(EnvironmentHelper.BuildType buildType) {
        EnvironmentHelper.getInstance().setBuildType(buildType);
        CommonUtil.setTestVersion(buildType == EnvironmentHelper.BuildType.TEST);
        LogUtil.setPrintAble((buildType == EnvironmentHelper.BuildType.RELEASE || buildType == EnvironmentHelper.BuildType.GREEN) ? false : true);
        LogUtil.LogLevel[] logLevelArr = new LogUtil.LogLevel[1];
        logLevelArr[0] = buildType == EnvironmentHelper.BuildType.BETA ? LogUtil.LogLevel.DEBUG : null;
        LogUtil.setDisableLogLevel(logLevelArr);
    }

    public static void setBaseUrl(String str) {
        EnvironmentHelper.getInstance().setBaseUrl(str);
    }

    public static void setAccountGrsAppName(String str) {
        EnvironmentHelper.getInstance().setAccountGrsAppName(str);
    }

    public static void sendCommandToJs(String str, String str2, H5ProReverseControlExecutor.OnReverseControlCallback onReverseControlCallback) {
        H5ProReverseControlExecutor.f2372a.execute(str, str2, onReverseControlCallback);
    }

    public static void registerVersionUpgradeApi(H5ProVersionUpgradeExtApi h5ProVersionUpgradeExtApi) {
        H5ProResidentExtManager.setVersionUpgradeExtApi(h5ProVersionUpgradeExtApi);
    }

    public static void registerVersionInterceptor(H5ProInterceptor h5ProInterceptor) {
        H5ProResidentExtManager.setVersionInterceptor(h5ProInterceptor);
    }

    public static void registerScalableService(Object obj) {
        H5ProScalableServiceManager.getInstance().registerService(obj);
    }

    public static void registerHostRuntimeApi(H5ProHostAppRuntimeApi h5ProHostAppRuntimeApi) {
        H5ProResidentExtManager.setHostAppApi(h5ProHostAppRuntimeApi);
    }

    public static void registerBridgeClass(String str, Class<? extends JsModuleBase> cls) {
        H5ProBridgeManager.getInstance().registerBridgeClass(str, cls);
    }

    public static void preloadH5LightApp(Context context, String str, H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk) {
        LogUtil.init(context);
        H5ProEngineFactory.getH5ProEngine().createInstance(context, (H5ProAppLoadListener) null).getAppLoader().preloadLightApp(str, h5ProPreloadCbk);
    }

    public static void preLoadH5MiniProgram(Context context, String str, H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk) {
        LogUtil.init(context);
        H5ProEngineFactory.getH5ProEngine().createInstance(context, (H5ProAppLoadListener) null).getAppLoader().preloadMiniProgram(str, h5ProPreloadCbk);
    }

    public static boolean preInstallH5MiniProgram(Context context, String str, IH5PreloadStrategy iH5PreloadStrategy) {
        IH5ProAppLoadStorageService h5ProAppLoadStorageService;
        LogUtil.init(context);
        if (!iH5PreloadStrategy.isPreLoadNow(context, str)) {
            LogUtil.i("H5PRO_H5ProClient", "preInstallH5MiniProgram, not match preload condition ", str);
            return false;
        }
        boolean preInstallHpk = new QuickPackageManager(context).preInstallHpk(str);
        if (!preInstallHpk || (h5ProAppLoadStorageService = H5ProScalableServiceManager.getInstance().getH5ProAppLoadStorageService()) == null) {
            return preInstallHpk;
        }
        h5ProAppLoadStorageService.updatePreLoadTime(context, str);
        return preInstallHpk;
    }

    public static H5ProServiceManager getServiceManager() {
        return H5ProServiceManager.getInstance();
    }

    public static void execJs(Context context, String str, H5ProExecJsValueCbk h5ProExecJsValueCbk) {
        new H5ProWebView(context).execJs(str, h5ProExecJsValueCbk);
    }
}
