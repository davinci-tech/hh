package com.huawei.hms.update.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.AndroidException;
import android.view.View;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.HwAlertDialog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public class InstallConfirm extends AbstractDialog {
    private static boolean g;
    private String c = ResourceLoaderUtil.getString("hms_update_title");
    private String d;
    private InstallConfirm e;
    private Dialog f;

    public void intAppName(String str) {
        this.c = str;
    }

    @Override // com.huawei.hms.update.ui.AbstractDialog
    protected Dialog onCreateDialog() {
        this.e = this;
        Activity activity = getActivity();
        this.c = a(activity, activity.getPackageName());
        if (TextUtils.isEmpty(this.d)) {
            this.d = Formatter.formatFileSize(activity, SystemUtils.getMegabyte(40.0d));
        }
        int a2 = HwDialogUtil.a(activity);
        HMSLog.i("InstallConfirm", "currentUiModeType is: " + a2);
        return a2 != 6 ? a(activity) : b(activity);
    }

    public void setHmsApkSize(String str) {
        this.d = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z) {
        g = z;
    }

    private Dialog b(final Activity activity) {
        if (ConfigChangeHolder.getInstance().isChanged() && g) {
            Dialog dialog = this.f;
            if (dialog != null) {
                return dialog;
            }
            Dialog createNewDialog = new ReconfirmInstallDialog(activity, this.e).createNewDialog();
            this.f = createNewDialog;
            if (createNewDialog != null) {
                return createNewDialog;
            }
        }
        b(false);
        int stringId = ResourceLoaderUtil.getStringId("hms_update_title");
        int stringId2 = ResourceLoaderUtil.getStringId("hms_install_confirm_message");
        int stringId3 = ResourceLoaderUtil.getStringId("hms_install");
        int stringId4 = ResourceLoaderUtil.getStringId("hms_cancel");
        WatchInstallDialog watchInstallDialog = new WatchInstallDialog(activity);
        watchInstallDialog.setTitle(stringId);
        watchInstallDialog.setMessage(activity.getString(stringId2, new Object[]{this.c, this.d}));
        watchInstallDialog.setInstallResourceId(stringId3);
        watchInstallDialog.setCancelResourceId(stringId4);
        watchInstallDialog.setOnInstallClick(new View.OnClickListener() { // from class: com.huawei.hms.update.ui.InstallConfirm.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InstallConfirm.this.fireDoWork();
            }
        });
        watchInstallDialog.setOnCancelClick(new View.OnClickListener() { // from class: com.huawei.hms.update.ui.InstallConfirm.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InstallConfirm installConfirm = InstallConfirm.this;
                installConfirm.f = new ReconfirmInstallDialog(activity, installConfirm.e).createNewDialog();
                if (InstallConfirm.this.f == null) {
                    InstallConfirm.this.cancel();
                    return;
                }
                InstallConfirm installConfirm2 = InstallConfirm.this;
                installConfirm2.replaceAlertDialog(installConfirm2.f);
                InstallConfirm.b(true);
            }
        });
        return watchInstallDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlertDialogBuilder a(Activity activity, int i) {
        if (SystemUtils.isTVDevice()) {
            return new AlertDialogBuilder(new AlertDialog.Builder(activity, i));
        }
        return new AlertDialogBuilder(new HwAlertDialog.Builder(activity, i));
    }

    private Dialog a(final Activity activity) {
        AlertDialogBuilder a2 = a(activity, getDialogThemeId());
        if (ConfigChangeHolder.getInstance().isChanged() && g) {
            Dialog dialog = this.f;
            if (dialog != null) {
                return dialog;
            }
            Dialog createNewDialog = new ReconfirmInstallDialog(a2, activity, this.e).createNewDialog();
            this.f = createNewDialog;
            if (createNewDialog != null) {
                return createNewDialog;
            }
        }
        b(false);
        if (!SystemUtils.isTVDevice()) {
            a2.a(ResourceLoaderUtil.getDrawableId("hms_core_icon"));
        }
        int stringId = ResourceLoaderUtil.getStringId("hms_update_title_new");
        int stringId2 = ResourceLoaderUtil.getStringId("hms_install_confirm_message");
        int stringId3 = ResourceLoaderUtil.getStringId("hms_install");
        a2.b(stringId);
        a2.a(activity.getString(stringId2, new Object[]{this.c, this.d}));
        a2.b(stringId3, new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.InstallConfirm.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                InstallConfirm.this.fireDoWork();
            }
        });
        a2.a(ResourceLoaderUtil.getStringId("hms_cancel"), new DialogInterface.OnClickListener() { // from class: com.huawei.hms.update.ui.InstallConfirm.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                InstallConfirm installConfirm = InstallConfirm.this;
                AlertDialogBuilder a3 = installConfirm.a(activity, installConfirm.getDialogThemeId());
                InstallConfirm installConfirm2 = InstallConfirm.this;
                installConfirm2.f = new ReconfirmInstallDialog(a3, activity, installConfirm2.e).createNewDialog();
                if (InstallConfirm.this.f == null) {
                    InstallConfirm.this.cancel();
                    return;
                }
                InstallConfirm installConfirm3 = InstallConfirm.this;
                installConfirm3.replaceAlertDialog(installConfirm3.f);
                InstallConfirm.b(true);
            }
        });
        return a2.a();
    }

    private static String a(Context context, String str) {
        if (context == null) {
            HMSLog.e("InstallConfirm", "In getAppName, context is null.");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("InstallConfirm", "In getAppName, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = context.getPackageName();
            }
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
            return applicationLabel == null ? "" : applicationLabel.toString();
        } catch (AndroidException e) {
            HMSLog.e("InstallConfirm", "In getAppName, Failed to get app name." + e.getMessage());
            return "";
        } catch (RuntimeException e2) {
            HMSLog.e("InstallConfirm", "In getAppName, Failed to get app name." + e2.getMessage());
            return "";
        }
    }
}
