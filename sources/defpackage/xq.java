package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import com.huawei.android.appbundle.splitinstall.LoadedSplitFetcher;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.InstallException;
import com.huawei.haf.bundle.InstallRequest;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.InstallStateListener;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes8.dex */
final class xq implements AppBundleInstallManager {

    /* renamed from: a, reason: collision with root package name */
    private final xz f17748a;
    private final Context b;

    xq(xz xzVar, Context context) {
        this.b = context;
        this.f17748a = xzVar;
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public void registerListener(InstallStateListener installStateListener) {
        d().b(installStateListener);
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public void unregisterListener(InstallStateListener installStateListener) {
        d().a(installStateListener);
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Task<Integer> startInstall(InstallRequest installRequest) {
        if (installRequest == null || installRequest.e().isEmpty()) {
            return c(new InstallException(-3));
        }
        LoadedSplitFetcher b = xu.b();
        if (b != null) {
            b.loadLocalSplits(installRequest.e());
        }
        if (getInstalledModules().containsAll(installRequest.e())) {
            d().d(new c(this, installRequest));
            return e(0);
        }
        return this.f17748a.c(installRequest.e());
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public boolean startConfirmationDialogForResult(InstallSessionState installSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
        PendingIntent resolutionIntent;
        if (installSessionState == null || activity == null) {
            throw new IntentSender.SendIntentException("sessionState == null || activity == null");
        }
        if (installSessionState.status() != 8 || (resolutionIntent = installSessionState.resolutionIntent()) == null) {
            return false;
        }
        activity.startIntentSenderForResult(resolutionIntent.getIntentSender(), i, null, 0, 0, 0);
        return true;
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Task<Void> cancelInstall(int i) {
        return this.f17748a.b(i);
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Task<InstallSessionState> getSessionState(int i) {
        return this.f17748a.d(i);
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Task<List<InstallSessionState>> getSessionStates() {
        return this.f17748a.e();
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Task<Void> deferredInstall(List<String> list) {
        if (CollectionUtils.d(list)) {
            return c(new InstallException(-3));
        }
        LoadedSplitFetcher b = xu.b();
        if (b != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (!b.checkModuleExisted(it.next())) {
                }
            }
            return e(null);
        }
        return this.f17748a.a(list);
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Task<Void> deferredUninstall(List<String> list) {
        if (CollectionUtils.d(list)) {
            return c(new InstallException(-3));
        }
        LoadedSplitFetcher b = xu.b();
        if (b != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (b.checkModuleUninstalled(it.next())) {
                    return e(null);
                }
            }
        }
        return this.f17748a.e(list);
    }

    @Override // com.huawei.haf.bundle.AppBundleInstallManager
    public Set<String> getInstalledModules() {
        Set<String> d = yb.d(this.b);
        if (!CollectionUtils.d(d)) {
            return d;
        }
        LoadedSplitFetcher b = xu.b();
        if (b == null) {
            return Collections.emptySet();
        }
        return b.loadedSplits();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public xs d() {
        return xs.d(this.b);
    }

    private static <TResult> Task<TResult> e(TResult tresult) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    private static <TResult> Task<TResult> c(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(exc);
        return taskCompletionSource.getTask();
    }

    static class c implements Runnable {
        private final xq b;
        private final InstallRequest e;

        c(xq xqVar, InstallRequest installRequest) {
            this.b = xqVar;
            this.e = installRequest;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.d().d(xx.eo_(el_((String[]) this.e.e().toArray(new String[0]))));
        }

        private Bundle el_(String[] strArr) {
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strArr));
            Bundle bundle = new Bundle();
            bundle.putInt("session_id", 0);
            bundle.putInt("status", 5);
            bundle.putInt("error_code", 0);
            bundle.putStringArrayList("module_names", arrayList);
            bundle.putStringArrayList("download_module_names", arrayList);
            bundle.putLong("total_bytes_to_download", 0L);
            bundle.putLong("bytes_downloaded", 0L);
            return bundle;
        }
    }
}
