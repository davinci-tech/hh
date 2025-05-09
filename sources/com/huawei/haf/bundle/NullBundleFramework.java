package com.huawei.haf.bundle;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.Tasks;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
final class NullBundleFramework implements AppBundleFramework {

    /* renamed from: a, reason: collision with root package name */
    private final AppBundleLauncher f2064a;
    private final AppBundleInstallManager b;
    private final AppBundleResources d;
    private final AppBundleExtension e;

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public void onApplicationAttachBaseContext(Context context) {
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public void onApplicationCreated(Context context, AppBundleConfiguration appBundleConfiguration) {
    }

    NullBundleFramework() {
        NullBundleInstallManager nullBundleInstallManager = new NullBundleInstallManager();
        this.b = nullBundleInstallManager;
        this.d = new NullBundleResources();
        this.e = new NullBundleExtension();
        this.f2064a = new NullBundleLauncher(nullBundleInstallManager);
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleInstallManager create(Context context) {
        return this.b;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleResources getResources() {
        return this.d;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleExtension getExtension() {
        return this.e;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleLauncher getLauncher() {
        return this.f2064a;
    }

    static class NullBundleInstallManager implements AppBundleInstallManager {
        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public void registerListener(InstallStateListener installStateListener) {
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public boolean startConfirmationDialogForResult(InstallSessionState installSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public void unregisterListener(InstallStateListener installStateListener) {
        }

        private NullBundleInstallManager() {
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Task<Integer> startInstall(InstallRequest installRequest) {
            return d(new InstallException(-5));
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Task<Void> deferredInstall(List<String> list) {
            return d(new InstallException(-5));
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Task<Void> cancelInstall(int i) {
            return d(new InstallException(-4));
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Task<InstallSessionState> getSessionState(int i) {
            return d(new InstallException(-4));
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Task<List<InstallSessionState>> getSessionStates() {
            return Tasks.fromResult(Collections.emptyList());
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Task<Void> deferredUninstall(List<String> list) {
            return d(new InstallException(-5));
        }

        @Override // com.huawei.haf.bundle.AppBundleInstallManager
        public Set<String> getInstalledModules() {
            return Collections.emptySet();
        }

        private static <TResult> Task<TResult> d(Exception exc) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(exc);
            return taskCompletionSource.getTask();
        }
    }

    static class NullBundleResources implements AppBundleResources {
        @Override // com.huawei.haf.bundle.AppBundleResources
        public void loadResources(Activity activity, Resources resources) {
        }

        @Override // com.huawei.haf.bundle.AppBundleResources
        public void loadResources(Service service) {
        }

        @Override // com.huawei.haf.bundle.AppBundleResources
        public void loadResources(BroadcastReceiver broadcastReceiver, Context context) {
        }

        @Override // com.huawei.haf.bundle.AppBundleResources
        public void loadResources(Context context) {
        }

        private NullBundleResources() {
        }

        @Override // com.huawei.haf.bundle.AppBundleResources
        public void loadLibrary(Context context, String str) {
            System.loadLibrary(str);
        }
    }

    static class NullBundleExtension implements AppBundleExtension {
        private final AppBundleSetting d;

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public long getModuleZipSize(Context context, String str) {
            return 0L;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public boolean isBuiltInModule(Context context, String str) {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public boolean isBundleModule(String str) {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public boolean isExistLocalModule(String str) {
            return true;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public boolean isUnistalledModule(String str) {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public boolean loadLocalModules(List<String> list) {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public boolean updateModuleInfo(Context context, String str, String str2) {
            return false;
        }

        private NullBundleExtension() {
            this.d = new NullBundleSetting();
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public Set<String> getAllBundleModules(Context context, boolean z) {
            return Collections.emptySet();
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public Set<String> getUpdateModules(Context context, boolean z) {
            return Collections.emptySet();
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public List<String> getModuleDependencies(Context context, String str) {
            return Collections.emptyList();
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public String getModuleDescription(Context context, String str) {
            return getModuleTitle(context, str);
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public AppBundleSetting getSetting() {
            return this.d;
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public String getModuleTitle(Context context, String str) {
            return "";
        }

        @Override // com.huawei.haf.bundle.AppBundleExtension
        public String getModuleForComponent(String str) {
            return "";
        }
    }

    static class NullBundleSetting implements AppBundleSetting {
        @Override // com.huawei.haf.bundle.AppBundleSetting
        public boolean isAllowAutoUpdateModule() {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public boolean isAllowDownloadModule(Context context, String str) {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public boolean isAllowUsingMobileUpdateModule() {
            return false;
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public void setAllowAutoUpdateModule(boolean z) {
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public void setAllowDownloadModule(Context context, String str, boolean z) {
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public void setAllowUsingMobileUpdateModule(boolean z) {
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public void setTestEnvironment(String str, boolean z, String str2) {
        }

        @Override // com.huawei.haf.bundle.AppBundleSetting
        public void updateModuleState(Context context, String str, boolean z) {
        }

        private NullBundleSetting() {
        }
    }

    static class NullBundleLauncher implements AppBundleLauncher {
        private final AppBundleInstallManager b;

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public boolean isInstalledModule(String str) {
            return true;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public void preDownloadPlugins(Context context, List<String> list, boolean z, boolean z2) {
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public void updateModules(Context context, boolean z) {
        }

        NullBundleLauncher(AppBundleInstallManager appBundleInstallManager) {
            this.b = appBundleInstallManager;
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public void launchActivity(Context context, Intent intent, AppBundleLauncher.InstallCallback installCallback) {
            launchActivity(context, intent, installCallback, null);
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public void launchActivity(Context context, Intent intent, AppBundleLauncher.InstallCallback installCallback, InstallGuide installGuide) {
            if (context == null || intent == null) {
                throw new IllegalArgumentException("context == null || intent == null");
            }
            if (installCallback == null || installCallback.call(context, intent)) {
                context.startActivity(intent);
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public void loadPlugins(Context context, List<String> list, AppBundleLauncher.InstallCallback installCallback) {
            if (installCallback != null) {
                installCallback.call(context, null);
            }
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public Set<String> getInstalledModules() {
            return this.b.getInstalledModules();
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher
        public AppBundleInstallManager getInstallManager() {
            return this.b;
        }
    }
}
