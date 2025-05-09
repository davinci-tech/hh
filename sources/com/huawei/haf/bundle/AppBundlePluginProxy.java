package com.huawei.haf.bundle;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import java.util.Collections;

/* loaded from: classes.dex */
public abstract class AppBundlePluginProxy<T> {
    private InstallStateListener mInstallStateListener;
    private final String mModuleApiClassName;
    private final String mModuleName;
    private final String mModuleTag;

    protected T getPluginService() {
        return null;
    }

    protected abstract void initialize(T t);

    public abstract boolean isPluginAvaiable();

    public AppBundlePluginProxy(String str, String str2, String str3) {
        this.mModuleTag = str;
        this.mModuleName = str2;
        this.mModuleApiClassName = str3;
    }

    public final void launchActivity(Context context, Intent intent) {
        launchActivity(context, intent, null);
    }

    public final void launchActivity(Context context, Intent intent, AppBundleLauncher.InstallCallback installCallback) {
        intent.putExtra("moduleName", getPluginName());
        AppBundleLauncher e = AppBundle.e();
        if (!isPluginAvaiable()) {
            installCallback = new InnerInstallResultListener(installCallback);
        }
        e.launchActivity(context, intent, installCallback);
    }

    public final void loadPlugin(AppBundleLauncher.InstallCallback installCallback) {
        if (!isPluginAvaiable()) {
            AppBundle.e().loadPlugins(BaseApplication.e(), Collections.singletonList(getPluginName()), new InnerInstallResultListener(installCallback));
        } else if (installCallback != null) {
            installCallback.call(BaseApplication.e(), null);
        }
    }

    protected final void loadPlugin() {
        loadPlugin(null);
    }

    protected final void deferredInstallPlugin() {
        if (isPluginAvaiable()) {
            return;
        }
        getInstallManager().deferredInstall(Collections.singletonList(getPluginName()));
    }

    public final String getPluginName() {
        return this.mModuleName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initializePlugin() {
        unregisterPluginListener();
        if (isPluginAvaiable()) {
            return true;
        }
        T newPluginApi = newPluginApi();
        if (newPluginApi != null) {
            initialize(newPluginApi);
        }
        return isPluginAvaiable();
    }

    public final T createPluginApi() {
        T newPluginApi = newPluginApi();
        if (newPluginApi == null && AppBundle.c().isBundleModule(getPluginName())) {
            registerPluginListener();
        }
        return newPluginApi;
    }

    private T newPluginApi() {
        T t;
        if (!TextUtils.isEmpty(this.mModuleApiClassName)) {
            try {
                t = (T) ReflectionUtils.b(this.mModuleApiClassName, BaseApplication.e().getClassLoader());
            } catch (Exception e) {
                LogUtil.e(this.mModuleTag, "create plugin api fail, ex=", LogUtil.a(e));
                t = null;
            }
        } else {
            t = getPluginService();
        }
        if (t != null) {
            LogUtil.c(this.mModuleTag, "create plugin api success.");
        }
        return t;
    }

    private AppBundleInstallManager getInstallManager() {
        return AppBundle.e().getInstallManager();
    }

    private void registerPluginListener() {
        synchronized (this) {
            if (this.mInstallStateListener == null) {
                this.mInstallStateListener = new InnerInstallResultListener(null);
                getInstallManager().registerListener(this.mInstallStateListener);
            }
        }
    }

    private void unregisterPluginListener() {
        synchronized (this) {
            if (this.mInstallStateListener != null) {
                getInstallManager().unregisterListener(this.mInstallStateListener);
                this.mInstallStateListener = null;
            }
        }
    }

    class InnerInstallResultListener implements AppBundleLauncher.InstallCallback, InstallStateListener {

        /* renamed from: a, reason: collision with root package name */
        private final AppBundleLauncher.InstallCallback f2062a;

        InnerInstallResultListener(AppBundleLauncher.InstallCallback installCallback) {
            this.f2062a = installCallback;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            if (this.f2062a != null) {
                AppBundlePluginProxy.this.initializePlugin();
                return this.f2062a.call(context, intent);
            }
            return AppBundlePluginProxy.this.initializePlugin();
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public void onFailed(Context context, Intent intent) {
            AppBundleLauncher.InstallCallback installCallback = this.f2062a;
            if (installCallback != null) {
                installCallback.onFailed(context, intent);
            }
        }

        @Override // com.huawei.haf.bundle.InstallStateListener
        public void onStateUpdate(InstallSessionState installSessionState) {
            if (installSessionState.status() == 5 && installSessionState.moduleNames().contains(AppBundlePluginProxy.this.getPluginName())) {
                AppBundlePluginProxy.this.initializePlugin();
            }
        }
    }
}
