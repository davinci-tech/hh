package com.huawei.android.bundlecore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.android.bundlecore.install.remote.ModuleInstallSupervisor;
import com.huawei.haf.common.utils.CollectionUtils;
import defpackage.yk;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class UserConfirmationDialog extends Activity {
    private List<String> mDownloadModuleNames;
    private List<String> mModuleNames;
    private long mRealDownloadTotalBytes;
    private int mSessionId;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.mSessionId = intent.getIntExtra("sessionId", 0);
            this.mRealDownloadTotalBytes = intent.getLongExtra("realDownloadTotalBytes", 0L);
            this.mModuleNames = intent.getStringArrayListExtra("moduleNames");
            this.mDownloadModuleNames = intent.getStringArrayListExtra("downloadModuleNames");
        }
    }

    public void onUserConfirm() {
        ModuleInstallSupervisor a2 = yk.a();
        if (a2 != null && a2.continueInstallWithUserConfirmation(this.mSessionId)) {
            setResult(-1);
        }
        setIntent(null);
        finish();
    }

    public void onUserCancel() {
        ModuleInstallSupervisor a2 = yk.a();
        if (a2 != null && a2.cancelInstallWithoutUserConfirmation(this.mSessionId)) {
            setResult(0);
        }
        setIntent(null);
        finish();
    }

    protected final int getSessionId() {
        return this.mSessionId;
    }

    protected final long getRealDownloadTotalBytes() {
        return this.mRealDownloadTotalBytes;
    }

    protected final List<String> getModuleNames() {
        return this.mModuleNames;
    }

    protected final List<String> getDownloadModuleNames() {
        return this.mDownloadModuleNames;
    }

    protected boolean checkInternParametersIllegal() {
        return this.mSessionId == 0 || this.mRealDownloadTotalBytes <= 0 || CollectionUtils.d(this.mModuleNames) || CollectionUtils.d(this.mDownloadModuleNames);
    }
}
