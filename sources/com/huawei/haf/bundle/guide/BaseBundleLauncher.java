package com.huawei.haf.bundle.guide;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleExtension;
import com.huawei.haf.bundle.AppBundleFramework;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundleSetting;
import com.huawei.haf.bundle.InstallException;
import com.huawei.haf.bundle.InstallGuide;
import com.huawei.haf.bundle.InstallRequest;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.InstallStateListener;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class BaseBundleLauncher implements AppBundleLauncher {

    /* renamed from: a, reason: collision with root package name */
    private final AppBundleFramework f2073a;
    private volatile AppBundleInstallManager b;
    private final Object c = new Object();

    public BaseBundleLauncher(AppBundleFramework appBundleFramework) {
        this.f2073a = appBundleFramework;
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public void launchActivity(Context context, Intent intent, AppBundleLauncher.InstallCallback installCallback) {
        launchActivity(context, intent, installCallback, null);
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public void launchActivity(Context context, Intent intent, AppBundleLauncher.InstallCallback installCallback, InstallGuide installGuide) {
        if (context == null) {
            throw new IllegalArgumentException("context == null");
        }
        if (intent == null) {
            throw new IllegalArgumentException("intent == null");
        }
        if (!HandlerExecutor.b()) {
            throw new IllegalThreadStateException("launchActivity need in ui thread run!");
        }
        List<String> wZ_ = wZ_(context, intent);
        if (!CollectionUtils.d(wZ_)) {
            xa_(context, wZ_, intent, installCallback, installGuide);
        } else {
            LogUtil.c("Bundle_Launcher", "launchActivity directly, className=", intent.getComponent());
            BaseBundleInstallGuide.wS_(context, intent, installCallback, "launchActivity");
        }
    }

    private void xa_(Context context, List<String> list, Intent intent, AppBundleLauncher.InstallCallback installCallback, InstallGuide installGuide) {
        if (!list.isEmpty()) {
            e().loadLocalModules(list);
        }
        BaseBundleInstallGuide.c(BaseBundleInstallGuide.wQ_(context, list, intent, installCallback).d(e(installGuide)).b(getInstallManager()));
    }

    private InstallGuide e(InstallGuide installGuide) {
        return installGuide == null ? BundleInstallGuideHolder.c() : installGuide;
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public void preDownloadPlugins(Context context, List<String> list, boolean z, boolean z2) {
        boolean z3;
        if (CollectionUtils.d(list)) {
            return;
        }
        Context e = context == null ? BaseApplication.e() : context.getApplicationContext();
        LogUtil.c("Bundle_Launcher", "preDownloadPlugins, isForceDownload=", Boolean.valueOf(z), ", isForce=", Boolean.valueOf(z2), ", modules=", list);
        AppBundleSetting d = d();
        List<String> e2 = a(list).e();
        Iterator<String> it = e2.iterator();
        while (true) {
            z3 = true;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (z) {
                d.setAllowDownloadModule(e, next, true);
            } else {
                d.updateModuleState(e, next, true);
            }
        }
        if (!z && !z2) {
            z3 = false;
        }
        d(e, e2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(List<String> list) {
        if (e().getUpdateModules(BaseApplication.e(), true).containsAll(list)) {
            return d(BaseApplication.e(), list, false);
        }
        return false;
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public void loadPlugins(Context context, List<String> list, AppBundleLauncher.InstallCallback installCallback) {
        if (CollectionUtils.d(list)) {
            throw new IllegalArgumentException("pluginNames is empty");
        }
        InstallRequest a2 = a(list);
        if (!a2.e().isEmpty()) {
            e().loadLocalModules(a2.e());
        }
        AppBundleInstallManager installManager = getInstallManager();
        if (!installManager.getInstalledModules().containsAll(a2.e())) {
            InstallResultStateListener installResultStateListener = new InstallResultStateListener(context, a2.e(), installCallback);
            installManager.startInstall(a2).addOnSuccessListener(installResultStateListener).addOnFailureListener(installResultStateListener);
        } else if (installCallback != null) {
            installCallback.call(context, null);
        }
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public boolean isInstalledModule(String str) {
        if (!TextUtils.isEmpty(str) && e().isBundleModule(str)) {
            return getInstalledModules().contains(str);
        }
        return true;
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public Set<String> getInstalledModules() {
        return getInstallManager().getInstalledModules();
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public void updateModules(Context context, boolean z) {
        LogUtil.c("Bundle_Launcher", "updateModules, isForce=", String.valueOf(z));
        Context e = context == null ? BaseApplication.e() : context.getApplicationContext();
        Set<String> updateModules = e().getUpdateModules(e, false);
        if (updateModules.isEmpty()) {
            return;
        }
        d(e, updateModules, z);
    }

    @Override // com.huawei.haf.bundle.AppBundleLauncher
    public AppBundleInstallManager getInstallManager() {
        if (this.b == null) {
            synchronized (this.c) {
                if (this.b == null) {
                    this.b = this.f2073a.create(BaseApplication.e());
                }
            }
        }
        return this.b;
    }

    private boolean d(Context context, Collection<String> collection, boolean z) {
        List<String> b = b(context, collection);
        if (!b.isEmpty()) {
            d(context, b);
            if (b.size() == collection.size()) {
                return true;
            }
            ArrayList arrayList = new ArrayList(collection);
            arrayList.removeAll(b);
            collection = arrayList;
        }
        boolean z2 = NetworkUtil.j() && (d().isAllowUsingMobileUpdateModule() || !NetworkUtil.f());
        if (!z2) {
            LogUtil.c("Bundle_Launcher", "backgroundDownloadPlugins isNetworkAllowDownload=", Boolean.valueOf(z2));
            return false;
        }
        boolean isAllowAutoUpdateModule = d().isAllowAutoUpdateModule();
        if (!z && !isAllowAutoUpdateModule) {
            LogUtil.c("Bundle_Launcher", "backgroundDownloadPlugins isAllowAutoUpdateModule=", Boolean.valueOf(isAllowAutoUpdateModule));
            return false;
        }
        d(context, collection);
        return true;
    }

    private void d(Context context, Collection<String> collection) {
        HashSet hashSet = new HashSet();
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            hashSet.addAll(e().getModuleDependencies(context, it.next()));
        }
        AppBundleInstallManager installManager = getInstallManager();
        for (String str : collection) {
            if (!hashSet.contains(str)) {
                List<String> singletonList = Collections.singletonList(str);
                DeferredInstallResultListener deferredInstallResultListener = new DeferredInstallResultListener(singletonList);
                installManager.deferredInstall(singletonList).addOnSuccessListener(deferredInstallResultListener).addOnFailureListener(deferredInstallResultListener);
            }
        }
    }

    private List<String> b(Context context, Collection<String> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (String str : collection) {
            if (d().isAllowDownloadModule(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private AppBundleExtension e() {
        return this.f2073a.getExtension();
    }

    private AppBundleSetting d() {
        return this.f2073a.getExtension().getSetting();
    }

    private List<String> wZ_(Context context, Intent intent) {
        InstallRequest.Builder d = InstallRequest.d();
        String wY_ = wY_(context, intent);
        if (!TextUtils.isEmpty(wY_) && e().isBundleModule(wY_)) {
            d.a(wY_);
        }
        String stringExtra = intent.getStringExtra("moduleName");
        if (!TextUtils.isEmpty(stringExtra) && e().isBundleModule(stringExtra)) {
            d.a(stringExtra);
        }
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(AppBundleLauncher.KEY_MODULE_NAME_LIST);
        c(d, stringArrayListExtra);
        LogUtil.c("Bundle_Launcher", "getRealModuleNameList incoming moduleName=", stringExtra, ", list=", stringArrayListExtra);
        return d.d().e();
    }

    private InstallRequest a(List<String> list) {
        InstallRequest.Builder d = InstallRequest.d();
        c(d, list);
        return d.d();
    }

    private void c(InstallRequest.Builder builder, List<String> list) {
        if (CollectionUtils.d(list)) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && e().isBundleModule(str)) {
                builder.a(str);
            }
        }
    }

    private String wY_(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null || !component.getPackageName().equals(context.getPackageName())) {
            return null;
        }
        return e().getModuleForComponent(component.getClassName());
    }

    class InstallResultStateListener implements OnSuccessListener<Integer>, OnFailureListener, InstallStateListener {

        /* renamed from: a, reason: collision with root package name */
        private final AppBundleInstallManager f2074a;
        private final List<String> c;
        private final AppBundleLauncher.InstallCallback d;
        private final Context e;
        private int i;

        InstallResultStateListener(Context context, List<String> list, AppBundleLauncher.InstallCallback installCallback) {
            this.e = context;
            this.c = new ArrayList(list);
            this.d = installCallback;
            AppBundleInstallManager installManager = BaseBundleLauncher.this.getInstallManager();
            this.f2074a = installManager;
            installManager.registerListener(this);
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Integer num) {
            this.i = num.intValue();
            if (this.f2074a.getInstalledModules().containsAll(this.c)) {
                e(true);
            }
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            Object[] objArr = new Object[4];
            objArr[0] = "loadPlugins fail, modules=";
            objArr[1] = this.c;
            objArr[2] = ", ";
            objArr[3] = exc instanceof InstallException ? exc.toString() : LogUtil.a(exc);
            LogUtil.c("Bundle_Launcher", objArr);
            e(false);
        }

        @Override // com.huawei.haf.bundle.InstallStateListener
        public void onStateUpdate(InstallSessionState installSessionState) {
            int status = installSessionState.status();
            if (this.i == installSessionState.sessionId() && status == 8) {
                e(false);
                LogUtil.c("Bundle_Launcher", "loadPlugins fail, need pre-download plugins, isDownloading=", Boolean.valueOf(BaseBundleLauncher.this.b(this.c)), ", modules=", this.c);
                return;
            }
            if (status == 5) {
                this.c.removeAll(this.f2074a.getInstalledModules());
            }
            if (this.c.isEmpty() || installSessionState.moduleNames().containsAll(this.c)) {
                if (status == 5) {
                    e(true);
                } else if (status == 6 || status == 7) {
                    e(false);
                }
            }
        }

        private void e(boolean z) {
            this.f2074a.unregisterListener(this);
            AppBundleLauncher.InstallCallback installCallback = this.d;
            if (installCallback != null) {
                if (z) {
                    installCallback.call(this.e, null);
                } else {
                    installCallback.onFailed(this.e, null);
                }
            }
        }
    }

    static class DeferredInstallResultListener implements OnSuccessListener<Void>, OnFailureListener {
        private final List<String> e;

        DeferredInstallResultListener(List<String> list) {
            this.e = list;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Void r2) {
            LogUtil.c("Bundle_Launcher", "deferredInstallPlugins success, modules=", this.e);
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            Object[] objArr = new Object[2];
            objArr[0] = "deferredInstallPlugins fail, ";
            objArr[1] = exc instanceof InstallException ? exc.toString() : LogUtil.a(exc);
            LogUtil.e("Bundle_Launcher", objArr);
        }
    }
}
