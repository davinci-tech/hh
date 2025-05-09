package com.huawei.haf.bundle.guide;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.InstallStateListener;
import com.huawei.haf.bundle.guide.BundleInstaller;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class BundleInstallTaskManager {

    /* renamed from: a, reason: collision with root package name */
    private static volatile BundleInstallTaskManager f2076a;
    private final Context e = BaseApplication.e();
    private final AppBundleInstallManager f;
    private static final Map<String, InstallHandlerWapper> b = new ConcurrentHashMap();
    private static final Map<Integer, BundleInstallerWapper> d = new ConcurrentHashMap();
    private static final Object c = new Object();

    private BundleInstallTaskManager() {
        AppBundleInstallManager installManager = AppBundle.e().getInstallManager();
        this.f = installManager;
        installManager.registerListener(new InnerInstallStateUpdatedListener());
    }

    public static BundleInstallTaskManager d() {
        if (f2076a == null) {
            synchronized (c) {
                if (f2076a == null) {
                    f2076a = new BundleInstallTaskManager();
                }
            }
        }
        return f2076a;
    }

    public BundleInstaller b(String str, BundleInstaller.InstallHandler installHandler) {
        InstallHandlerWapper installHandlerWapper = b.get(str);
        if (installHandlerWapper == null) {
            installHandlerWapper = d(str);
        }
        installHandlerWapper.d(installHandler);
        return installHandlerWapper.e();
    }

    private InstallHandlerWapper d(String str) {
        BundleInstallerWapper b2 = b(str);
        if (b2 == null) {
            b2 = new BundleInstallerWapper(str);
            b2.c(new InstallHandlerWapper(b2));
        } else {
            b2.c(new InstallHandlerWapper(b2));
            b2.a();
        }
        return b2.b();
    }

    private BundleInstallerWapper b(String str) {
        for (BundleInstallerWapper bundleInstallerWapper : d.values()) {
            if (str.equals(bundleInstallerWapper.b)) {
                return bundleInstallerWapper;
            }
        }
        return null;
    }

    public static void b(String str, String str2, BundleInstaller bundleInstaller) {
        if (!(bundleInstaller instanceof BundleInstallerWapper)) {
            LogUtil.a("Bundle_InstallTaskManager", "addTask please use getBundleInstaller method to get BundleInstaller");
            return;
        }
        InstallHandlerWapper b2 = ((BundleInstallerWapper) bundleInstaller).b();
        b2.e().clean();
        b2.d(str);
        b.put(str2, b2);
    }

    public static boolean d(String str, String str2) {
        InstallHandlerWapper installHandlerWapper;
        Map<String, InstallHandlerWapper> map = b;
        if (map.isEmpty() || (installHandlerWapper = map.get(str2)) == null) {
            return false;
        }
        return TextUtils.isEmpty(str) || str.equals(installHandlerWapper.d());
    }

    public static void e(String str) {
        String str2;
        BundleInstallerWapper bundleInstallerWapper;
        Map<String, InstallHandlerWapper> map = b;
        if (map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, InstallHandlerWapper> entry : map.entrySet()) {
            if (TextUtils.isEmpty(str) || str.equals(entry.getValue().d())) {
                str2 = entry.getKey();
                bundleInstallerWapper = entry.getValue().e();
                break;
            }
        }
        str2 = null;
        bundleInstallerWapper = null;
        if (bundleInstallerWapper != null) {
            b.remove(str2);
            bundleInstallerWapper.startInstall(Arrays.asList(str2));
        } else {
            e(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(InstallSessionState installSessionState) {
        if (installSessionState.moduleNames().isEmpty()) {
            return;
        }
        Integer valueOf = Integer.valueOf(installSessionState.sessionId());
        Map<Integer, BundleInstallerWapper> map = d;
        BundleInstallerWapper bundleInstallerWapper = map.get(valueOf);
        int status = installSessionState.status();
        if (status == 1 || status == 2 || status == 3 || status == 4) {
            if (bundleInstallerWapper == null) {
                bundleInstallerWapper = new BundleInstallerWapper(installSessionState.moduleNames().get(0));
                bundleInstallerWapper.e(installSessionState.sessionId());
                map.put(valueOf, bundleInstallerWapper);
            }
            bundleInstallerWapper.e(installSessionState);
            return;
        }
        if (status == 8 || status == 9 || bundleInstallerWapper == null) {
            return;
        }
        bundleInstallerWapper.e(-1);
        bundleInstallerWapper.e(installSessionState);
        map.remove(valueOf);
    }

    class BundleInstallerWapper implements BundleInstaller {

        /* renamed from: a, reason: collision with root package name */
        private InstallHandlerWapper f2077a;
        private final String b;
        private BaseBundleInstaller c;
        private int e = -1;

        BundleInstallerWapper(String str) {
            this.b = TextUtils.isEmpty(str) ? "" : str;
        }

        void e(int i) {
            this.e = i;
        }

        void c(InstallHandlerWapper installHandlerWapper) {
            this.f2077a = installHandlerWapper;
        }

        InstallHandlerWapper b() {
            return this.f2077a;
        }

        void e(InstallSessionState installSessionState) {
            BaseBundleInstaller baseBundleInstaller = this.c;
            if (baseBundleInstaller == null || baseBundleInstaller.isRunning()) {
                return;
            }
            baseBundleInstaller.e(installSessionState);
        }

        BaseBundleInstaller a() {
            BaseBundleInstaller baseBundleInstaller = this.c;
            if (baseBundleInstaller != null || this.f2077a == null) {
                return baseBundleInstaller;
            }
            BaseBundleInstaller baseBundleInstaller2 = new BaseBundleInstaller(BundleInstallTaskManager.this.e, this.f2077a, BundleInstallGuideHolder.c(), BundleInstallTaskManager.this.f);
            this.c = baseBundleInstaller2;
            return baseBundleInstaller2;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public AppBundleInstallManager getInstallManager() {
            return BundleInstallTaskManager.this.f;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public void startInstall(List<String> list) {
            BaseBundleInstaller a2;
            if (this.e != -1 || (a2 = a()) == null) {
                return;
            }
            a2.startInstall(list);
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public boolean startConfirmationDialogForResult(InstallSessionState installSessionState, Activity activity, int i, boolean z) throws IntentSender.SendIntentException {
            BaseBundleInstaller baseBundleInstaller = this.c;
            if (baseBundleInstaller == null) {
                return false;
            }
            return baseBundleInstaller.startConfirmationDialogForResult(installSessionState, activity, i, z);
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public void cancelInstall() {
            BaseBundleInstaller baseBundleInstaller = this.c;
            if (baseBundleInstaller != null) {
                baseBundleInstaller.cancelInstall();
            }
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public boolean isRunning() {
            if (this.e != -1) {
                return true;
            }
            BaseBundleInstaller baseBundleInstaller = this.c;
            return baseBundleInstaller != null && baseBundleInstaller.isRunning();
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public boolean onBackPressed() {
            BaseBundleInstaller baseBundleInstaller = this.c;
            return baseBundleInstaller != null && baseBundleInstaller.onBackPressed();
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller
        public void clean() {
            BaseBundleInstaller baseBundleInstaller = this.c;
            this.c = null;
            if (baseBundleInstaller != null) {
                baseBundleInstaller.clean();
            }
        }
    }

    static class InstallHandlerWapper implements BundleInstaller.InstallHandler {

        /* renamed from: a, reason: collision with root package name */
        private BundleInstallerWapper f2078a;
        private String b;
        private WeakReference<BundleInstaller.InstallHandler> e;

        InstallHandlerWapper(BundleInstallerWapper bundleInstallerWapper) {
            this.f2078a = bundleInstallerWapper;
        }

        BundleInstallerWapper e() {
            return this.f2078a;
        }

        void d(BundleInstaller.InstallHandler installHandler) {
            this.e = new WeakReference<>(installHandler);
        }

        void d(String str) {
            this.b = str;
        }

        String d() {
            return this.b;
        }

        private BundleInstaller.InstallHandler a() {
            WeakReference<BundleInstaller.InstallHandler> weakReference = this.e;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public boolean onForceDownloads() {
            BundleInstaller.InstallHandler a2 = a();
            if (a2 != null) {
                return a2.onForceDownloads();
            }
            return false;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public boolean onInstallRequestError(int i, String str, boolean z) {
            BundleInstaller.InstallHandler a2 = a();
            if (a2 != null) {
                return a2.onInstallRequestError(i, str, z);
            }
            return true;
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onRequiresUserConfirmation(InstallSessionState installSessionState) {
            BundleInstaller.InstallHandler a2 = a();
            if (a2 != null) {
                a2.onRequiresUserConfirmation(installSessionState);
            }
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onPending(InstallSessionState installSessionState, String str) {
            BundleInstaller.InstallHandler a2 = a();
            if (a2 != null) {
                a2.onPending(installSessionState, str);
            }
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onProgressMessage(InstallSessionState installSessionState, int i, String str) {
            BundleInstaller.InstallHandler a2 = a();
            if (a2 != null) {
                a2.onProgressMessage(installSessionState, i, str);
            }
        }

        @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
        public void onFinish(boolean z) {
            BundleInstaller.InstallHandler a2 = a();
            if (a2 != null) {
                a2.onFinish(z);
            }
        }
    }

    class InnerInstallStateUpdatedListener implements InstallStateListener {
        private InnerInstallStateUpdatedListener() {
        }

        @Override // com.huawei.haf.bundle.InstallStateListener
        public void onStateUpdate(InstallSessionState installSessionState) {
            BundleInstallTaskManager.this.d(installSessionState);
        }
    }
}
