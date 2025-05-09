package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.Formatter;
import com.huawei.hms.adapter.ui.InstallerAdapter;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.AgHmsUpdateState;
import com.huawei.hms.utils.FileUtil;
import com.huawei.hwcloudjs.g.a;
import java.io.File;
import java.io.IOException;

/* loaded from: classes9.dex */
public class ConfirmInstallWizard extends AbsUpdateWizard {
    public static final String INVALID_SIZE = "";

    /* renamed from: a, reason: collision with root package name */
    private static final String f6088a;

    static {
        StringBuilder sb = new StringBuilder();
        String str = File.separator;
        sb.append(str);
        sb.append(a.c);
        sb.append(str);
        sb.append("hms.apk");
        f6088a = sb.toString();
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    void a(Class<? extends AbstractDialog> cls) {
        try {
            AbstractDialog newInstance = cls.newInstance();
            if (newInstance instanceof InstallConfirm) {
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                    ((InstallConfirm) newInstance).setHmsApkSize(a((Context) activity));
                }
                HMSLog.e("ConfirmInstallWizard", "<showDialog> not show Dialog, activity is null or finishing.");
                return;
            }
            newInstance.show(this);
            this.mLatestDialog = newInstance;
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("ConfirmInstallWizard", "In ConfirmInstallWizard showDialog, failed to show the dialog." + e.getMessage());
        }
    }

    public void getInstallHmsIntent(Activity activity, File file) {
        HMSLog.i("ConfirmInstallWizard", "install hms.apk path");
        if (activity == null) {
            HMSLog.e("ConfirmInstallWizard", "activity is null");
            finishBridgeActivity(13, 8);
            return;
        }
        String str = InstallerAdapter.sHmsApkHash;
        if (str == null || !FileUtil.verifyHash(str, file)) {
            HMSLog.e("ConfirmInstallWizard", "In startInstaller, hms.apk hash verification failed.");
            finishBridgeActivity(13, 8);
            return;
        }
        Uri a2 = a(activity, file);
        if (a2 == null) {
            HMSLog.e("ConfirmInstallWizard", "In startInstaller, failed to creates a uri from a file");
            finishBridgeActivity(13, 8);
            return;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(a2, "application/vnd.android.package-archive");
        intent.setFlags(1);
        intent.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
        intent.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", activity.getPackageName());
        try {
            activity.startActivityForResult(intent, getRequestCode());
        } catch (ActivityNotFoundException | SecurityException e) {
            HMSLog.e("ConfirmInstallWizard", "In getInstallHmsIntent start activity error: " + e.getMessage());
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 0;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        super.onBridgeActivityCreate(activity);
        if (activity == null || activity.isFinishing()) {
            return;
        }
        a(InstallConfirm.class);
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        super.onBridgeActivityDestroy();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        a();
        if (!isUpdated(this.mPackageName, this.mClientVersionCode)) {
            finishBridgeActivity(8, 8);
            HMSLog.i("ConfirmInstallWizard", "biReportEvent install failure");
            return true;
        }
        AgHmsUpdateState.getInstance().resetUpdateState();
        finishBridgeActivity(0, 8);
        HMSLog.i("ConfirmInstallWizard", "biReportEvent install success, package: " + this.mPackageName);
        return true;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onCancel(AbstractDialog abstractDialog) {
        HMSLog.i("ConfirmInstallWizard", "cancel to install hms.apk");
        a();
        finishBridgeActivity(13, 8);
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onDoWork(AbstractDialog abstractDialog) {
        HMSLog.i("ConfirmInstallWizard", "begin to install hms.apk");
        a(getActivity());
    }

    private void a(Activity activity) {
        try {
            String str = activity.getApplicationContext().getExternalCacheDir().getCanonicalPath() + f6088a;
            File file = new File(str);
            if (!file.exists()) {
                HMSLog.e("ConfirmInstallWizard", str + " is not exist");
                return;
            }
            getInstallHmsIntent(activity, file);
        } catch (IOException e) {
            HMSLog.e("ConfirmInstallWizard", "getExternalCacheDir fail:  " + e.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0029, code lost:
    
        if (r0.hasProvider(r1, r2) != false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.net.Uri a(android.content.Context r5, java.io.File r6) {
        /*
            com.huawei.hms.utils.PackageManagerHelper r0 = new com.huawei.hms.utils.PackageManagerHelper
            r0.<init>(r5)
            java.lang.String r1 = r5.getPackageName()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r3 = ".hms.update.provider"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.content.pm.ApplicationInfo r3 = r5.getApplicationInfo()     // Catch: java.lang.RuntimeException -> L30
            int r3 = r3.targetSdkVersion     // Catch: java.lang.RuntimeException -> L30
            r4 = 23
            if (r3 <= r4) goto L25
            goto L2b
        L25:
            boolean r0 = r0.hasProvider(r1, r2)     // Catch: java.lang.RuntimeException -> L30
            if (r0 == 0) goto L48
        L2b:
            android.net.Uri r5 = com.huawei.hms.update.provider.UpdateProvider.getUriForFile(r5, r2, r6)
            return r5
        L30:
            r5 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "In contentUriFromFile getApplicationInfo error: "
            r0.<init>(r1)
            java.lang.String r5 = r5.getMessage()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "ConfirmInstallWizard"
            com.huawei.hms.support.log.HMSLog.e(r0, r5)
        L48:
            android.net.Uri r5 = android.net.Uri.fromFile(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.update.ui.ConfirmInstallWizard.a(android.content.Context, java.io.File):android.net.Uri");
    }

    private void a() {
        try {
            File file = new File(getActivity().getApplicationContext().getExternalCacheDir().getCanonicalPath() + f6088a);
            if (!file.exists()) {
                HMSLog.i("ConfirmInstallWizard", "delete apk, but hms.apk is not exist");
            } else if (file.delete()) {
                HMSLog.i("ConfirmInstallWizard", "success delete hms.apk file");
            }
        } catch (IOException e) {
            HMSLog.e("ConfirmInstallWizard", "getExternalCacheDir or delete file error:  " + e.getMessage());
        }
    }

    private String a(Context context) {
        try {
            String str = context.getApplicationContext().getExternalCacheDir().getCanonicalPath() + f6088a;
            File file = new File(str);
            if (!file.exists()) {
                HMSLog.e("ConfirmInstallWizard", "<getHmsApkSize> " + str + " is not exist");
                return "";
            }
            long length = file.length();
            return length > 0 ? Formatter.formatFileSize(context, length) : "";
        } catch (IOException e) {
            HMSLog.e("ConfirmInstallWizard", "<getHmsApkSize> getExternalCacheDir fail:  " + e.getMessage());
            return "";
        }
    }
}
