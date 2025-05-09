package com.huawei.android.defaultbundle;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import com.huawei.android.bundlecore.UserConfirmationDialog;
import com.huawei.haf.bundle.guide.BundleInstallGuideHolder;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import health.compact.a.LogUtil;

/* loaded from: classes8.dex */
public final class DefaultUserConfirmationDialog extends UserConfirmationDialog {
    @Override // com.huawei.android.bundlecore.UserConfirmationDialog, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (e()) {
            onUserConfirm();
            return;
        }
        if (d()) {
            onUserCancel();
            return;
        }
        if (checkInternParametersIllegal()) {
            setIntent(null);
            finish();
        } else {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            setFinishOnTouchOutside(false);
            b();
        }
    }

    private boolean e() {
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra("isSkipConfirm", false)) {
            return false;
        }
        LogUtil.c("Bundle_UserConfirmationDialog", "checkSkipConfirm taskId=", Integer.valueOf(getSessionId()));
        return true;
    }

    private boolean d() {
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra("isSkipCancel", false)) {
            return false;
        }
        LogUtil.c("Bundle_UserConfirmationDialog", "checkSkipCancel taskId=", Integer.valueOf(getSessionId()));
        return true;
    }

    private void b() {
        BundleInstallGuideHolder.c().showDownloadAskDialog(this, getDownloadModuleNames(), getRealDownloadTotalBytes(), new View.OnClickListener() { // from class: com.huawei.android.defaultbundle.DefaultUserConfirmationDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DefaultUserConfirmationDialog.this.onUserCancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        }, new View.OnClickListener() { // from class: com.huawei.android.defaultbundle.DefaultUserConfirmationDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DefaultUserConfirmationDialog.this.onUserConfirm();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
