package com.huawei.haf.bundle.guide;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.InstallException;
import com.huawei.haf.bundle.InstallGuide;
import com.huawei.haf.bundle.InstallRequest;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.InstallStateListener;
import com.huawei.haf.bundle.guide.BundleInstaller;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class BaseBundleInstaller extends ContextWrapper implements BundleInstaller {

    /* renamed from: a, reason: collision with root package name */
    private InstallRequest f2069a;
    private final AppBundleInstallManager b;
    private InstallStateListener c;
    private final InstallGuide d;
    private final BundleInstaller.InstallHandler e;
    private int g;
    private boolean i;
    private int j;

    public BaseBundleInstaller(Context context, BundleInstaller.InstallHandler installHandler, InstallGuide installGuide, AppBundleInstallManager appBundleInstallManager) {
        super(context);
        this.g = -1;
        this.j = 0;
        this.e = installHandler;
        this.d = installGuide == null ? BundleInstallGuideHolder.c() : installGuide;
        this.b = appBundleInstallManager == null ? AppBundle.a(getApplicationContext()) : appBundleInstallManager;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public AppBundleInstallManager getInstallManager() {
        return this.b;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public void startInstall(List<String> list) {
        if (this.c == null) {
            InnerInstallStateUpdatedListener innerInstallStateUpdatedListener = new InnerInstallStateUpdatedListener();
            this.c = innerInstallStateUpdatedListener;
            this.b.registerListener(innerInstallStateUpdatedListener);
            this.f2069a = b(list);
            b();
        }
    }

    private InstallRequest b(List<String> list) {
        InstallRequest.Builder d = InstallRequest.d();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            d.a(it.next());
        }
        return d.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        InstallRequest installRequest = this.f2069a;
        if (installRequest == null) {
            return;
        }
        if (this.b.getInstalledModules().containsAll(installRequest.e())) {
            e();
        } else {
            if (this.c == null) {
                return;
            }
            InnerStartInstallListener innerStartInstallListener = new InnerStartInstallListener();
            this.b.startInstall(installRequest).addOnSuccessListener(innerStartInstallListener).addOnFailureListener(innerStartInstallListener);
        }
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public boolean startConfirmationDialogForResult(InstallSessionState installSessionState, Activity activity, int i, boolean z) throws IntentSender.SendIntentException {
        if (wV_(installSessionState, activity, i, z)) {
            return true;
        }
        return this.b.startConfirmationDialogForResult(installSessionState, activity, i);
    }

    private boolean wV_(InstallSessionState installSessionState, Activity activity, int i, boolean z) throws IntentSender.SendIntentException {
        PendingIntent resolutionIntent;
        if (installSessionState == null || activity == null) {
            throw new IntentSender.SendIntentException("state == null || activity == null");
        }
        if (installSessionState.status() != 8 || (resolutionIntent = installSessionState.resolutionIntent()) == null) {
            return false;
        }
        if (z) {
            return wX_(installSessionState, resolutionIntent, activity, i, false);
        }
        return wW_(installSessionState, resolutionIntent, activity, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean wX_(InstallSessionState installSessionState, PendingIntent pendingIntent, Activity activity, int i, boolean z) {
        Intent intent = new Intent();
        intent.putExtra("sessionId", installSessionState.sessionId());
        intent.putExtra(z ? "isSkipCancel" : "isSkipConfirm", true);
        try {
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, intent, 0, 0, 0);
            return true;
        } catch (IntentSender.SendIntentException e) {
            LogUtil.e("Bundle_BaseInstaller", "skipUserConfirmation ex=", LogUtil.a(e));
            return false;
        }
    }

    private boolean wW_(final InstallSessionState installSessionState, final PendingIntent pendingIntent, final Activity activity, final int i) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.c("Bundle_BaseInstaller", "showUserConfirmationDialog activity isFinishing=", String.valueOf(activity.isFinishing()));
            return false;
        }
        this.d.showDownloadAskDialog(activity, installSessionState.downloadModuleNames(), installSessionState.totalBytesToDownload(), new View.OnClickListener() { // from class: com.huawei.haf.bundle.guide.BaseBundleInstaller.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseBundleInstaller.this.wX_(installSessionState, pendingIntent, activity, i, true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, new View.OnClickListener() { // from class: com.huawei.haf.bundle.guide.BaseBundleInstaller.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseBundleInstaller.this.wX_(installSessionState, pendingIntent, activity, i, false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return true;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public void cancelInstall() {
        int i = this.g;
        if (i == -1) {
            return;
        }
        this.g = -1;
        InnerCancelInstallListener innerCancelInstallListener = new InnerCancelInstallListener(i);
        this.b.cancelInstall(i).addOnSuccessListener(innerCancelInstallListener).addOnFailureListener(innerCancelInstallListener);
        LogUtil.c("Bundle_BaseInstaller", "cancelInstall taskId=", Integer.valueOf(i));
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public boolean isRunning() {
        return this.c != null;
    }

    private void d(int i, String str) {
        c(i, str, true);
    }

    private void c(int i, String str, boolean z) {
        if (this.e.onInstallRequestError(i, str, z)) {
            a();
        }
    }

    private void i(InstallSessionState installSessionState) {
        this.e.onRequiresUserConfirmation(installSessionState);
    }

    private void g(InstallSessionState installSessionState) {
        this.e.onPending(installSessionState, a(this, installSessionState));
    }

    private void b(InstallSessionState installSessionState) {
        long bytesDownloaded = installSessionState.bytesDownloaded();
        long j = installSessionState.totalBytesToDownload();
        if (j <= 0 || bytesDownloaded < 0) {
            return;
        }
        int i = (int) ((bytesDownloaded * 98) / j);
        if (i > 97) {
            i = 97;
        }
        this.e.onProgressMessage(installSessionState, i, a(this, installSessionState));
    }

    private void a(InstallSessionState installSessionState) {
        this.e.onProgressMessage(installSessionState, 98, a(this, installSessionState));
    }

    private void c(InstallSessionState installSessionState) {
        this.e.onProgressMessage(installSessionState, 99, a(this, installSessionState));
    }

    private void d(InstallSessionState installSessionState) {
        this.e.onProgressMessage(installSessionState, 100, a(this, installSessionState));
        e();
    }

    private void e() {
        clean();
        this.e.onFinish(true);
    }

    private void a() {
        clean();
        this.e.onFinish(false);
    }

    private void d() {
        if (this.e.onForceDownloads()) {
            this.i = false;
            this.b.getSessionStates().addOnCompleteListener(new InnerSessionListCompleteListener(this));
        }
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public boolean onBackPressed() {
        int i = this.j;
        if (i == 0) {
            LogUtil.c("Bundle_BaseInstaller", "Module download is not started!");
            return true;
        }
        if (i == 9 || i == 3 || i == 4 || !this.i) {
            return false;
        }
        if (this.g == -1) {
            return true;
        }
        cancelInstall();
        return false;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller
    public void clean() {
        InstallStateListener installStateListener = this.c;
        if (installStateListener != null) {
            this.b.unregisterListener(installStateListener);
            this.c = null;
            this.f2069a = null;
            this.g = -1;
            this.j = 0;
            this.i = false;
        }
    }

    void e(InstallSessionState installSessionState) {
        switch (installSessionState.status()) {
            case 1:
                g(installSessionState);
                break;
            case 2:
                b(installSessionState);
                break;
            case 3:
                a(installSessionState);
                break;
            case 4:
                c(installSessionState);
                break;
            case 5:
                d(installSessionState);
                break;
            case 6:
                c(installSessionState.errorCode());
                break;
            case 7:
                a();
                break;
            case 8:
                i(installSessionState);
                break;
            default:
                LogUtil.a("Bundle_BaseInstaller", "updateState:", installSessionState.toString());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(InstallSessionState installSessionState) {
        InstallRequest installRequest;
        if (this.g == installSessionState.sessionId() || ((installRequest = this.f2069a) != null && installRequest.e().containsAll(installSessionState.moduleNames()) && installSessionState.moduleNames().containsAll(this.f2069a.e()))) {
            if (this.j == 8 && installSessionState.status() == this.j) {
                LogUtil.c("Bundle_BaseInstaller", "updateState same requires user confirmation states=", Integer.valueOf(installSessionState.status()));
            } else {
                this.j = installSessionState.status();
                e(installSessionState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == -8) {
            c(i, b((Context) this, i), false);
            return;
        }
        if (i == -1) {
            d();
        } else if (i != 0) {
            LogUtil.a("Bundle_BaseInstaller", "handleInstallFailure errorCode=", Integer.valueOf(i));
            d(i, b((Context) this, i));
        }
    }

    private String a(Context context, InstallSessionState installSessionState) {
        String installStateDesc = this.d.getInstallStateDesc(this, installSessionState);
        if (!TextUtils.isEmpty(installStateDesc)) {
            return installStateDesc;
        }
        if (BundleInstallGuideHolder.c() != this.d) {
            installStateDesc = BundleInstallGuideHolder.c().getInstallStateDesc(context, installSessionState);
        }
        return TextUtils.isEmpty(installStateDesc) ? "" : installStateDesc;
    }

    private String b(Context context, int i) {
        String installErrorDesc = this.d.getInstallErrorDesc(context, i);
        if (!TextUtils.isEmpty(installErrorDesc)) {
            return installErrorDesc;
        }
        if (BundleInstallGuideHolder.c() != this.d) {
            installErrorDesc = BundleInstallGuideHolder.c().getInstallErrorDesc(context, i);
        }
        return TextUtils.isEmpty(installErrorDesc) ? "" : installErrorDesc;
    }

    class InnerStartInstallListener implements OnSuccessListener<Integer>, OnFailureListener {
        private InnerStartInstallListener() {
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Integer num) {
            BaseBundleInstaller.this.g = num.intValue();
            BaseBundleInstaller.this.i = true;
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            BaseBundleInstaller.this.g = -1;
            BaseBundleInstaller.this.i = true;
            if (exc instanceof InstallException) {
                BaseBundleInstaller.this.c(((InstallException) exc).a());
            } else {
                LogUtil.a("Bundle_BaseInstaller", "onFailure ex=", LogUtil.a(exc));
            }
        }
    }

    class InnerInstallStateUpdatedListener implements InstallStateListener {
        private InnerInstallStateUpdatedListener() {
        }

        @Override // com.huawei.haf.bundle.InstallStateListener
        public void onStateUpdate(InstallSessionState installSessionState) {
            BaseBundleInstaller.this.h(installSessionState);
        }
    }

    static class InnerCancelInstallListener implements OnSuccessListener<Void>, OnFailureListener {
        private final int d;

        InnerCancelInstallListener(int i) {
            this.d = i;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Void r2) {
            LogUtil.c("Bundle_BaseInstaller", "Cancel task successfully, taskId=", Integer.valueOf(this.d));
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            LogUtil.c("Bundle_BaseInstaller", "Cancel task failed, taskId=", Integer.valueOf(this.d), ", ex=", LogUtil.a(exc));
        }
    }

    static class InnerSessionListCompleteListener implements OnCompleteListener<List<InstallSessionState>> {
        private final WeakReference<BaseBundleInstaller> b;

        InnerSessionListCompleteListener(BaseBundleInstaller baseBundleInstaller) {
            this.b = new WeakReference<>(baseBundleInstaller);
        }

        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task<List<InstallSessionState>> task) {
            BaseBundleInstaller baseBundleInstaller;
            if (task.isSuccessful() && (baseBundleInstaller = this.b.get()) != null) {
                for (InstallSessionState installSessionState : task.getResult()) {
                    if (installSessionState.status() == 2) {
                        baseBundleInstaller.b.cancelInstall(installSessionState.sessionId()).addOnCompleteListener(new InnerCancelCompleteListener(baseBundleInstaller));
                        LogUtil.c("Bundle_BaseInstaller", "InnerSessionListCompleteListener cancelInstall, taskId=", Integer.valueOf(installSessionState.sessionId()));
                    }
                }
            }
        }
    }

    static class InnerCancelCompleteListener implements OnCompleteListener<Void> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<BaseBundleInstaller> f2072a;

        InnerCancelCompleteListener(BaseBundleInstaller baseBundleInstaller) {
            this.f2072a = new WeakReference<>(baseBundleInstaller);
        }

        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task<Void> task) {
            BaseBundleInstaller baseBundleInstaller = this.f2072a.get();
            if (baseBundleInstaller == null) {
                return;
            }
            baseBundleInstaller.b();
        }
    }
}
