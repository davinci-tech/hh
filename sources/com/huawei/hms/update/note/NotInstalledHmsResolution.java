package com.huawei.hms.update.note;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.availableupdate.b;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.NotInstalledHmsDialogHelper;
import com.huawei.hms.utils.UIUtil;

/* loaded from: classes4.dex */
public class NotInstalledHmsResolution implements IBridgeActivityDelegate {

    /* renamed from: a, reason: collision with root package name */
    private Dialog f6067a;
    private Activity b;

    static class a implements DialogInterface.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final Activity f6068a;

        public a(Activity activity) {
            this.f6068a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            HMSLog.i("NotInstalledHmsResolution", "<Dialog onClick>");
            this.f6068a.finish();
        }
    }

    private void a(Activity activity) {
        a();
        AlertDialog create = NotInstalledHmsDialogHelper.getDialogBuilder(activity).setPositiveButton(NotInstalledHmsDialogHelper.getConfirmResId(activity), new a(activity)).create();
        this.f6067a = create;
        UIUtil.enableFocusedForButtonsInTV(create);
        this.f6067a.show();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        HMSLog.i("NotInstalledHmsResolution", "<Resolution getRequestCode>");
        return 0;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.i("NotInstalledHmsResolution", "<Resolution onBridgeActivityCreate>");
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("NotInstalledHmsResolution", "<Resolution onBridgeActivityCreate> activity is null or finishing");
            return;
        }
        this.b = activity;
        b.b.a(activity);
        a(activity);
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.i("NotInstalledHmsResolution", "<Resolution onBridgeActivityDestroy>");
        a();
        b.b.b(this.b);
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.i("NotInstalledHmsResolution", "<Resolution onBridgeActivityResult>");
        return false;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.i("NotInstalledHmsResolution", "<Resolution onBridgeConfigurationChanged>");
        Activity activity = this.b;
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("NotInstalledHmsResolution", "<Resolution onBridgeActivityCreate> mActivity is null or finishing");
        } else {
            a(this.b);
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.i("NotInstalledHmsResolution", "<Resolution onKeyUp>");
    }

    private void a() {
        Dialog dialog = this.f6067a;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f6067a.cancel();
    }
}
