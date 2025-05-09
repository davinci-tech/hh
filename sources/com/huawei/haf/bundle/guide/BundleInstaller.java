package com.huawei.haf.bundle.guide;

import android.app.Activity;
import android.content.IntentSender;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.InstallSessionState;
import java.util.List;

/* loaded from: classes.dex */
public interface BundleInstaller {
    public static final int INVALID_SESSION_ID = -1;

    public interface InstallHandler {
        void onFinish(boolean z);

        boolean onForceDownloads();

        boolean onInstallRequestError(int i, String str, boolean z);

        void onPending(InstallSessionState installSessionState, String str);

        void onProgressMessage(InstallSessionState installSessionState, int i, String str);

        void onRequiresUserConfirmation(InstallSessionState installSessionState);
    }

    void cancelInstall();

    void clean();

    AppBundleInstallManager getInstallManager();

    boolean isRunning();

    boolean onBackPressed();

    boolean startConfirmationDialogForResult(InstallSessionState installSessionState, Activity activity, int i, boolean z) throws IntentSender.SendIntentException;

    void startInstall(List<String> list);
}
