package com.huawei.haf.bundle;

import android.app.Activity;
import android.content.IntentSender;
import com.huawei.hmf.tasks.Task;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public interface AppBundleInstallManager {
    Task<Void> cancelInstall(int i);

    Task<Void> deferredInstall(List<String> list);

    Task<Void> deferredUninstall(List<String> list);

    Set<String> getInstalledModules();

    Task<InstallSessionState> getSessionState(int i);

    Task<List<InstallSessionState>> getSessionStates();

    void registerListener(InstallStateListener installStateListener);

    boolean startConfirmationDialogForResult(InstallSessionState installSessionState, Activity activity, int i) throws IntentSender.SendIntentException;

    Task<Integer> startInstall(InstallRequest installRequest);

    void unregisterListener(InstallStateListener installStateListener);
}
