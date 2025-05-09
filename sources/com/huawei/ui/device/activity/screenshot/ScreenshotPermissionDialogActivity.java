package com.huawei.ui.device.activity.screenshot;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.screenshot.ScreenshotPermissionDialogActivity;
import defpackage.jrm;
import defpackage.nsn;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class ScreenshotPermissionDialogActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        if (getIntent() != null) {
            PermissionUtil.PermissionResult e = jrm.e(this);
            if (e == PermissionUtil.PermissionResult.FOREVER_DENIED) {
                e(true);
                return;
            } else if (e == PermissionUtil.PermissionResult.DENIED) {
                d();
                return;
            } else {
                finish();
                return;
            }
        }
        finish();
    }

    private void e(final boolean z) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(z ? R.string._2130847340_res_0x7f02266c : R.string._2130847336_res_0x7f022668).d(z ? R.string.IDS_permission_description_storage : R.string._2130847337_res_0x7f022669).cyR_(R.string._2130847338_res_0x7f02266a, new View.OnClickListener() { // from class: nxp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenshotPermissionDialogActivity.this.cSc_(view);
            }
        }).cyU_(R.string._2130847339_res_0x7f02266b, new View.OnClickListener() { // from class: nxs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenshotPermissionDialogActivity.this.cSd_(z, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void cSc_(View view) {
        jrm.e();
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cSd_(boolean z, View view) {
        if (z) {
            nsn.ak(this);
            e();
        } else {
            PermissionUtil.b(this, PermissionUtil.PermissionType.STORAGE, c());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if (CommonUtil.a(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            e(false);
        } else {
            PermissionUtil.b(this, PermissionUtil.PermissionType.STORAGE, c());
        }
    }

    private CustomPermissionAction c() {
        return new CustomPermissionAction(this) { // from class: com.huawei.ui.device.activity.screenshot.ScreenshotPermissionDialogActivity.2
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                ScreenshotPermissionDialogActivity.this.e();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                super.onDenied(str);
                ScreenshotPermissionDialogActivity.this.e();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                super.onForeverDenied(permissionType);
                ScreenshotPermissionDialogActivity.this.e();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        jrm.e();
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
