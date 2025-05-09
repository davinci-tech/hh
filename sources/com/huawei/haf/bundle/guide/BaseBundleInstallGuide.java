package com.huawei.haf.bundle.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.InstallGuide;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.guide.BundleInstaller;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import health.compact.a.LogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
final class BaseBundleInstallGuide implements BundleInstaller.InstallHandler {

    /* renamed from: a, reason: collision with root package name */
    private final Activity f2067a;
    private InstallGuide c;
    private InstallGuide.InstallProgress d;
    private BaseBundleInstaller f;
    private final int g;
    private final boolean i;
    private static final AtomicInteger e = new AtomicInteger(-536870912);
    private static final Map<Integer, LaunchInfo> b = new ConcurrentHashMap();

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public boolean onForceDownloads() {
        return true;
    }

    BaseBundleInstallGuide(Activity activity, int i) {
        this(activity, i, true);
    }

    private BaseBundleInstallGuide(Activity activity, int i, boolean z) {
        this.f2067a = activity;
        this.g = i;
        this.i = z;
    }

    void c() {
        LaunchInfo launchInfo = b.get(Integer.valueOf(this.g));
        if (launchInfo == null) {
            LogUtil.a("Bundle_InstallGuide", "launchInfo is null, launcherIndex=", Integer.valueOf(this.g));
            onFinish(false);
        } else {
            this.c = launchInfo.d;
            if (this.f == null) {
                this.f = new BaseBundleInstaller(this.f2067a, this, launchInfo.d, launchInfo.e);
            }
            this.f.startInstall(launchInfo.g);
        }
    }

    void d() {
        c(false, false);
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public boolean onInstallRequestError(int i, String str, boolean z) {
        if (this.f2067a.isFinishing()) {
            return true;
        }
        if (!z) {
            if (!TextUtils.isEmpty(str)) {
                Toast.makeText(this.f2067a, str, 0).show();
            }
            return false;
        }
        a();
        b(i, str);
        return false;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onRequiresUserConfirmation(InstallSessionState installSessionState) {
        BaseBundleInstaller baseBundleInstaller = this.f;
        if (baseBundleInstaller != null) {
            try {
                baseBundleInstaller.startConfirmationDialogForResult(installSessionState, this.f2067a, 1100, false);
            } catch (IntentSender.SendIntentException e2) {
                LogUtil.e("Bundle_InstallGuide", "onRequiresUserConfirmation ex=", LogUtil.a(e2));
            }
        }
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onPending(InstallSessionState installSessionState, String str) {
        if (this.d != null) {
            return;
        }
        this.d = this.c.createInstallProgress(this.f2067a, str, new View.OnClickListener() { // from class: com.huawei.haf.bundle.guide.BaseBundleInstallGuide.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseBundleInstallGuide.this.b();
                BaseBundleInstallGuide.this.onFinish(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onProgressMessage(InstallSessionState installSessionState, int i, String str) {
        InstallGuide.InstallProgress installProgress = this.d;
        if (installProgress != null) {
            installProgress.update(i, str);
        }
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onFinish(boolean z) {
        c(z, true);
    }

    private void c(boolean z, boolean z2) {
        if (this.f != null) {
            a();
            this.f.clean();
            this.f = null;
        }
        if (!this.i) {
            d(this.g, z);
            return;
        }
        if (this.f2067a.getIntent() != null) {
            d(this.g, z);
            this.f2067a.setIntent(null);
        }
        if (!z2 || this.f2067a.isFinishing()) {
            return;
        }
        this.f2067a.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        BaseBundleInstaller baseBundleInstaller = this.f;
        if (baseBundleInstaller != null) {
            baseBundleInstaller.cancelInstall();
        }
    }

    private void b(int i, String str) {
        this.c.showDownloadErrorDialog(this.f2067a, i, str, new View.OnClickListener() { // from class: com.huawei.haf.bundle.guide.BaseBundleInstallGuide.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseBundleInstallGuide.this.onFinish(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a() {
        InstallGuide.InstallProgress installProgress = this.d;
        if (installProgress != null) {
            installProgress.close();
            this.d = null;
        }
    }

    static LaunchInfo wQ_(Context context, List<String> list, Intent intent, AppBundleLauncher.InstallCallback installCallback) {
        return new LaunchInfo(context, list, intent, installCallback);
    }

    static void wS_(Context context, Intent intent, AppBundleLauncher.InstallCallback installCallback, String str) {
        if (installCallback != null && !installCallback.call(context, intent)) {
            LogUtil.c("Bundle_InstallGuide", str, " install callback process");
            return;
        }
        try {
            if (!(context instanceof Activity)) {
                intent.setFlags(268435456);
            }
            context.startActivity(intent);
        } catch (Exception e2) {
            LogUtil.a("Bundle_InstallGuide", str, " startActivity fail. ex=", LogUtil.a(e2));
        }
    }

    static void c(LaunchInfo launchInfo) {
        Activity wa_;
        if (!(launchInfo.f2068a instanceof Activity)) {
            LogUtil.c("Bundle_InstallGuide", "launchActivity context is not Activity");
            wa_ = BaseApplication.wa_();
            if (wa_ == null) {
                wR_(null, launchInfo, false);
                return;
            }
        } else {
            wa_ = (Activity) launchInfo.f2068a;
        }
        if (wa_.isFinishing() || wa_.isDestroyed()) {
            LogUtil.c("Bundle_InstallGuide", "launchActivity activity isFinishing=", String.valueOf(wa_.isFinishing()));
            wR_(null, launchInfo, false);
        } else {
            wR_(wa_, launchInfo, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void wR_(final Activity activity, final LaunchInfo launchInfo, boolean z) {
        if (launchInfo.e.getInstalledModules().containsAll(launchInfo.g)) {
            d(launchInfo, true);
            return;
        }
        if (z) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.haf.bundle.guide.BaseBundleInstallGuide.3
                @Override // java.lang.Runnable
                public void run() {
                    BaseBundleInstallGuide.wR_(activity, launchInfo, false);
                }
            });
            return;
        }
        if (d(launchInfo)) {
            return;
        }
        int a2 = a(launchInfo);
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            new BaseBundleInstallGuide(activity, a2, false).c();
        } else {
            if (a(launchInfo, a2)) {
                return;
            }
            d(a2, false);
        }
    }

    private static boolean d(LaunchInfo launchInfo) {
        Map<Integer, LaunchInfo> map = b;
        if (map.isEmpty()) {
            return false;
        }
        for (LaunchInfo launchInfo2 : map.values()) {
            if (launchInfo.g.size() == launchInfo2.g.size() && launchInfo.g.containsAll(launchInfo2.g)) {
                LogUtil.a("Bundle_InstallGuide", "checkRepeatRequest, ignore, modules=", launchInfo.g);
                if (launchInfo.f2068a != launchInfo2.f2068a) {
                    d(launchInfo, false);
                }
                return true;
            }
        }
        return false;
    }

    private static boolean a(LaunchInfo launchInfo, int i) {
        Class e2 = e(launchInfo);
        if (e2 == null) {
            LogUtil.a("Bundle_InstallGuide", "startGuideActivity, guideActivity == null");
            return false;
        }
        try {
            Intent intent = new Intent(launchInfo.f2068a, (Class<?>) e2);
            intent.putExtra("launcherIndex", i);
            intent.setFlags(268435456);
            intent.addFlags(8388608);
            launchInfo.f2068a.startActivity(intent);
            return true;
        } catch (Exception e3) {
            LogUtil.a("Bundle_InstallGuide", "startGuideActivity fail. ex=", LogUtil.a(e3));
            return false;
        }
    }

    private static Class e(LaunchInfo launchInfo) {
        Class<? extends Activity> transparentGuideActivity = launchInfo.d.getTransparentGuideActivity();
        return (transparentGuideActivity == null && BundleInstallGuideHolder.c() != launchInfo.d) ? BundleInstallGuideHolder.c().getTransparentGuideActivity() : transparentGuideActivity;
    }

    private static int a(LaunchInfo launchInfo) {
        int andIncrement = e.getAndIncrement();
        Map<Integer, LaunchInfo> map = b;
        map.put(Integer.valueOf(andIncrement), launchInfo);
        LogUtil.c("Bundle_InstallGuide", "launchRequest index=", Integer.valueOf(andIncrement), ", size=", Integer.valueOf(map.size()), ", moduleNames=", launchInfo.g);
        return andIncrement;
    }

    private static void d(int i, boolean z) {
        Map<Integer, LaunchInfo> map = b;
        LaunchInfo remove = map.remove(Integer.valueOf(i));
        if (remove == null) {
            return;
        }
        LogUtil.c("Bundle_InstallGuide", "launchResult index=", Integer.valueOf(i), ", size=", Integer.valueOf(map.size()), ", result=", String.valueOf(z), ", className=", remove.b.getComponent());
        d(remove, z);
    }

    private static void d(LaunchInfo launchInfo, boolean z) {
        if (z) {
            wS_(launchInfo.f2068a, launchInfo.b, launchInfo.c, "launchResponse");
        } else if (launchInfo.c != null) {
            launchInfo.c.onFailed(launchInfo.f2068a, launchInfo.b);
        }
    }

    static class LaunchInfo {

        /* renamed from: a, reason: collision with root package name */
        private final Context f2068a;
        private final Intent b;
        private final AppBundleLauncher.InstallCallback c;
        private InstallGuide d;
        private AppBundleInstallManager e;
        private final List<String> g;

        LaunchInfo(Context context, List<String> list, Intent intent, AppBundleLauncher.InstallCallback installCallback) {
            this.f2068a = context;
            this.g = list;
            this.b = intent;
            this.c = installCallback;
        }

        LaunchInfo d(InstallGuide installGuide) {
            this.d = installGuide;
            return this;
        }

        LaunchInfo b(AppBundleInstallManager appBundleInstallManager) {
            this.e = appBundleInstallManager;
            return this;
        }
    }
}
