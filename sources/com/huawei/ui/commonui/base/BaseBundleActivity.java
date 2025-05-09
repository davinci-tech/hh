package com.huawei.ui.commonui.base;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.InstallGuide;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.haf.bundle.guide.BaseBundleInstaller;
import com.huawei.haf.bundle.guide.BundleInstallGuideHolder;
import com.huawei.haf.bundle.guide.BundleInstaller;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class BaseBundleActivity extends BaseActivity implements BundleInstaller.InstallHandler {
    private static final String TAG = "Bundle_BaseActivity";
    private static final int USER_CONFIRMATION_REQ_CODE = 110;
    private BaseBundleInstaller mInstaller;

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public boolean onForceDownloads() {
        return true;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onProgressMessage(InstallSessionState installSessionState, int i, String str) {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInstaller = new BaseBundleInstaller(this, this, getInstallGuide(), null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 110) {
            LogUtil.c(TAG, "onActivityResult resultCode=", Integer.valueOf(i2));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mInstaller.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mInstaller.clean();
        super.onDestroy();
    }

    protected final AppBundleInstallManager getInstallManager() {
        return this.mInstaller.getInstallManager();
    }

    protected final void startInstall(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mInstaller.startInstall(list);
    }

    protected final void cancelInstall() {
        this.mInstaller.cancelInstall();
    }

    protected boolean startConfirmationDialogForResult(InstallSessionState installSessionState, boolean z) throws IntentSender.SendIntentException {
        return this.mInstaller.startConfirmationDialogForResult(installSessionState, this, 110, z);
    }

    protected InstallGuide getInstallGuide() {
        return BundleInstallGuideHolder.c();
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public boolean onInstallRequestError(int i, String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 0).show();
        }
        return z;
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onRequiresUserConfirmation(InstallSessionState installSessionState) {
        try {
            startConfirmationDialogForResult(installSessionState, false);
        } catch (IntentSender.SendIntentException e) {
            LogUtil.e(TAG, "onRequiresUserConfirmation ex=", LogUtil.a(e));
        }
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onPending(InstallSessionState installSessionState, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    @Override // com.huawei.haf.bundle.guide.BundleInstaller.InstallHandler
    public void onFinish(boolean z) {
        finish();
    }
}
