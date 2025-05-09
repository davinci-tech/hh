package com.huawei.hms.adapter.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.NotInstalledHmsDialogHelper;
import com.huawei.hms.utils.UIUtil;

/* loaded from: classes4.dex */
public class NotInstalledHmsAdapter implements IBridgeActivityDelegate {
    private static final Object c = new Object();
    private static boolean d;

    /* renamed from: a, reason: collision with root package name */
    private Activity f4278a;
    private Dialog b;

    static class a implements DialogInterface.OnCancelListener {

        /* renamed from: a, reason: collision with root package name */
        private final Activity f4279a;

        public a(Activity activity) {
            this.f4279a = activity;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            HMSLog.i("NotInstalledHmsAdapter", "<Dialog onCancel>");
            SystemManager.getInstance().notifyUpdateResult(13);
            this.f4279a.finish();
        }
    }

    static class b implements DialogInterface.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final Activity f4280a;

        public b(Activity activity) {
            this.f4280a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            HMSLog.i("NotInstalledHmsAdapter", "<Dialog onClick>");
            SystemManager.getInstance().notifyUpdateResult(30);
            this.f4280a.finish();
        }
    }

    private void a(Activity activity) {
        Dialog dialog = this.b;
        if (dialog != null && dialog.isShowing()) {
            this.b.setOnCancelListener(null);
            this.b.cancel();
        }
        AlertDialog create = NotInstalledHmsDialogHelper.getDialogBuilder(activity).setPositiveButton(NotInstalledHmsDialogHelper.getConfirmResId(activity), new b(activity)).setOnCancelListener(new a(activity)).create();
        this.b = create;
        UIUtil.enableFocusedForButtonsInTV(create);
        this.b.show();
    }

    public static boolean getShowLock() {
        synchronized (c) {
            HMSLog.i("NotInstalledHmsAdapter", "<canShowDialog> sIsShowingDialog: " + d);
            if (d) {
                return false;
            }
            d = true;
            return true;
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        HMSLog.i("NotInstalledHmsAdapter", "<getRequestCode>");
        return 0;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeActivityCreate>");
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("NotInstalledHmsAdapter", "<onBridgeActivityCreate> activity is null or finishing");
        } else {
            this.f4278a = activity;
            a(activity);
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeActivityDestroy>");
        synchronized (c) {
            d = false;
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeActivityResult>");
        return false;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        HMSLog.i("NotInstalledHmsAdapter", "<onBridgeConfigurationChanged>");
        Activity activity = this.f4278a;
        if (activity == null || activity.isFinishing()) {
            HMSLog.e("NotInstalledHmsAdapter", "<onBridgeConfigurationChanged> mActivity is null or finishing");
        } else {
            a(this.f4278a);
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        HMSLog.i("NotInstalledHmsAdapter", "<onKeyUp>");
    }
}
