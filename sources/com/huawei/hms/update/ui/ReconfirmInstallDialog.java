package com.huawei.hms.update.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.HwAlertDialog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public class ReconfirmInstallDialog {

    /* renamed from: a, reason: collision with root package name */
    private final AlertDialogBuilder f6113a;
    private final Activity b;
    private final AbstractDialog c;

    public ReconfirmInstallDialog(Activity activity, AbstractDialog abstractDialog) {
        this((AlertDialogBuilder) null, activity, abstractDialog);
    }

    private Dialog b(int i, int i2, int i3) {
        WatchInstallDialog watchInstallDialog = new WatchInstallDialog(this.b);
        watchInstallDialog.setMessage(System.lineSeparator() + this.b.getString(i3));
        watchInstallDialog.setInstallResourceId(i);
        watchInstallDialog.setCancelResourceId(i2);
        watchInstallDialog.setOnInstallClick(new View.OnClickListener() { // from class: com.huawei.hms.update.ui.ReconfirmInstallDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HMSLog.i("ReconfirmInstallDialog", "start fireDoWork...");
                ReconfirmInstallDialog.this.c.fireDoWork();
            }
        });
        watchInstallDialog.setOnCancelClick(new View.OnClickListener() { // from class: com.huawei.hms.update.ui.ReconfirmInstallDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HMSLog.i("ReconfirmInstallDialog", "start cancel...");
                ReconfirmInstallDialog.this.c.cancel();
            }
        });
        return watchInstallDialog;
    }

    public Dialog createNewDialog() {
        try {
            int stringId = ResourceLoaderUtil.getStringId("hms_install_after_cancel");
            int stringId2 = ResourceLoaderUtil.getStringId("hms_cancel_after_cancel");
            int stringId3 = ResourceLoaderUtil.getStringId("hms_cancel_install_message");
            Activity activity = this.b;
            if (activity != null && this.c != null) {
                if (!activity.isFinishing()) {
                    return HwDialogUtil.a(this.b) != 6 ? a(stringId, stringId2, stringId3) : b(stringId, stringId2, stringId3);
                }
                HMSLog.e("ReconfirmInstallDialog", "this mActivity is finished.");
                return null;
            }
            HMSLog.e("ReconfirmInstallDialog", "error:  mActivity or mDialog is null: " + this.b + this.c);
            return null;
        } catch (Exception e) {
            HMSLog.e("ReconfirmInstallDialog", "createNewDialog exception: " + e.getMessage());
            return null;
        }
    }

    public ReconfirmInstallDialog(AlertDialogBuilder alertDialogBuilder, Activity activity, AbstractDialog abstractDialog) {
        this.f6113a = alertDialogBuilder;
        this.b = activity;
        this.c = abstractDialog;
    }

    private Dialog a(int i, int i2, int i3) {
        AlertDialogBuilder alertDialogBuilder = this.f6113a;
        if (alertDialogBuilder == null) {
            HMSLog.e("ReconfirmInstallDialog", "error: mBuilder is null");
            return null;
        }
        alertDialogBuilder.a(this.b.getString(i3));
        this.f6113a.b(i, new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.ReconfirmInstallDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                HMSLog.i("ReconfirmInstallDialog", "start fireDoWork...");
                ReconfirmInstallDialog.this.c.fireDoWork();
            }
        });
        this.f6113a.a(i2, new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.ReconfirmInstallDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                HMSLog.i("ReconfirmInstallDialog", "start cancel...");
                ReconfirmInstallDialog.this.c.cancel();
            }
        });
        return this.f6113a.a();
    }

    public ReconfirmInstallDialog(HwAlertDialog.Builder builder, Activity activity, AbstractDialog abstractDialog) {
        this.f6113a = new AlertDialogBuilder(builder);
        this.b = activity;
        this.c = abstractDialog;
    }
}
