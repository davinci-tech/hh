package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.manager.HmsApkReallySizeManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class HiappWizard extends AbsUpdateWizard {

    /* renamed from: a, reason: collision with root package name */
    private boolean f6090a;
    private a b;
    private String c;

    static class a implements HmsApkReallySizeManager.CheckHmsApkSizeCallback {

        /* renamed from: a, reason: collision with root package name */
        final HiappWizard f6091a;
        final AbstractDialog b;

        public a(HiappWizard hiappWizard, AbstractDialog abstractDialog) {
            this.f6091a = hiappWizard;
            this.b = abstractDialog;
        }

        @Override // com.huawei.hms.update.manager.HmsApkReallySizeManager.CheckHmsApkSizeCallback
        public void onResult(String str) {
            this.f6091a.c = str;
            HiappWizard hiappWizard = this.f6091a;
            hiappWizard.b(hiappWizard, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiappWizard hiappWizard, AbstractDialog abstractDialog) {
        HMSLog.i("HiappWizard", "<onResultShowDialog> start");
        boolean z = false;
        this.f6090a = false;
        Activity activity = getActivity();
        boolean z2 = activity == null || activity.isFinishing() || activity.isDestroyed();
        AbstractDialog abstractDialog2 = this.mLatestDialog;
        if (abstractDialog2 != null && abstractDialog2.isShowing()) {
            z = true;
        }
        if (z2 || z) {
            HMSLog.e("HiappWizard", "<onResultShowDialog> Activity Destroyed or Dialog isShoing");
            return;
        }
        if (!TextUtils.isEmpty(this.mClientAppName) && (abstractDialog instanceof InstallConfirm)) {
            String string = ResourceLoaderUtil.getString("hms_update_title");
            this.mClientAppName = string;
            InstallConfirm installConfirm = (InstallConfirm) abstractDialog;
            installConfirm.intAppName(string);
            installConfirm.setHmsApkSize(this.c);
        }
        abstractDialog.show(hiappWizard);
        this.mLatestDialog = abstractDialog;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 2005;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        super.onBridgeActivityCreate(activity);
        UpdateBean updateBean = this.bean;
        if (updateBean == null) {
            finishBridgeActivity(8, 5);
            return;
        }
        this.updateType = 5;
        if (updateBean.isNeedConfirm() && !TextUtils.isEmpty(this.mClientAppName)) {
            a(InstallConfirm.class);
        } else {
            if (a()) {
                return;
            }
            if (startNextWizard(false)) {
                biReportEvent(8, this.updateType);
            } else {
                finishBridgeActivity(8, this.updateType);
            }
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        super.onBridgeActivityDestroy();
        HmsApkReallySizeManager.getInstance().release();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        if (this.needTransfer && (iBridgeActivityDelegate = this.mBridgeActivityDelegate) != null) {
            return iBridgeActivityDelegate.onBridgeActivityResult(i, i2, intent);
        }
        if (this.updateType != 5 || i != getRequestCode()) {
            return false;
        }
        if (isUpdated(this.mPackageName, this.mClientVersionCode)) {
            finishBridgeActivity(0, this.updateType);
            return true;
        }
        finishBridgeActivity(8, this.updateType);
        return true;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        super.onBridgeConfigurationChanged();
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onCancel(AbstractDialog abstractDialog) {
        HMSLog.i("HiappWizard", "Enter onCancel.");
        if (abstractDialog instanceof InstallConfirm) {
            b();
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onDoWork(AbstractDialog abstractDialog) {
        HMSLog.i("HiappWizard", "Enter onDoWork.");
        if (abstractDialog instanceof InstallConfirm) {
            abstractDialog.dismiss();
            if (a()) {
                return;
            }
            if (startNextWizard(false)) {
                biReportEvent(8, this.updateType);
            } else {
                finishBridgeActivity(8, this.updateType);
            }
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        if (this.needTransfer && (iBridgeActivityDelegate = this.mBridgeActivityDelegate) != null) {
            iBridgeActivityDelegate.onKeyUp(i, keyEvent);
            return;
        }
        if (4 == i) {
            HMSLog.i("HiappWizard", "In onKeyUp, Call finish.");
            Activity activity = getActivity();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.setResult(0, null);
            activity.finish();
        }
    }

    private boolean a() {
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing() || TextUtils.isEmpty(this.mPackageName)) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("com.huawei.appmarket.intent.action.AppDetail");
            intent.putExtra("APP_PACKAGENAME", this.mPackageName);
            if (SystemUtils.isTVDevice()) {
                int packageVersionCode = new PackageManagerHelper(activity).getPackageVersionCode("com.hisilicon.android.hiRMService");
                HMSLog.i("HiappWizard", "version " + packageVersionCode);
                if (packageVersionCode == 1) {
                    HMSLog.i("HiappWizard", "startActivity");
                    intent.addFlags(268435456);
                    return a(intent, activity);
                }
            }
            HMSLog.i("HiappWizard", "startActivityForResult");
            if (a(intent)) {
                activity.startActivityForResult(intent, getRequestCode());
                return true;
            }
        } catch (ActivityNotFoundException | IllegalArgumentException unused) {
            HMSLog.e("HiappWizard", "can not open hiapp");
        }
        return false;
    }

    void b() {
        finishBridgeActivity(13, this.updateType);
    }

    private boolean a(Intent intent, Activity activity) {
        boolean a2 = a(intent);
        if (a2) {
            activity.startActivity(intent);
            activity.finish();
        }
        return a2;
    }

    private boolean a(Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = getActivity().getPackageManager().queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("com.huawei.appmarket.tv");
                arrayList.add("com.huawei.appmarket.car");
                arrayList.add("com.huawei.appmarket");
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (arrayList.contains(resolveInfo.activityInfo.packageName)) {
                        try {
                            intent.setPackage(resolveInfo.activityInfo.packageName);
                            return true;
                        } catch (IllegalArgumentException unused) {
                            HMSLog.e("HiappWizard", "IllegalArgumentException when HiappWizard-setIntentPackageName");
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            HMSLog.e("HiappWizard", "setIntentPackageName query intent failed. " + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    void a(Class<? extends AbstractDialog> cls) {
        dismissDialog();
        try {
            AbstractDialog newInstance = cls.newInstance();
            Activity activity = getActivity();
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                if (this.f6090a) {
                    HMSLog.i("HiappWizard", "<showDialog> isChecking true. return");
                    return;
                }
                String apkSize = HmsApkReallySizeManager.getInstance().getApkSize();
                if (TextUtils.isEmpty(apkSize)) {
                    HMSLog.i("HiappWizard", "<showDialog> checkAndShowDialog.");
                    a(this, newInstance);
                    return;
                }
                this.c = apkSize;
                if (!TextUtils.isEmpty(this.mClientAppName) && (newInstance instanceof InstallConfirm)) {
                    String string = ResourceLoaderUtil.getString("hms_update_title");
                    this.mClientAppName = string;
                    ((InstallConfirm) newInstance).intAppName(string);
                    ((InstallConfirm) newInstance).setHmsApkSize(this.c);
                }
                newInstance.show(this);
                this.mLatestDialog = newInstance;
                return;
            }
            HMSLog.e("HiappWizard", "<checkHmsSizeAndShowDialog> not show Dialog, activity is null or finishing.");
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("HiappWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
        }
    }

    private void a(HiappWizard hiappWizard, AbstractDialog abstractDialog) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            String clientPackageName = this.bean.getClientPackageName();
            HMSLog.i("HiappWizard", "<checkAndShowDialog> pkgName: " + clientPackageName);
            this.f6090a = true;
            if (this.b == null) {
                this.b = new a(hiappWizard, abstractDialog);
            }
            HmsApkReallySizeManager.getInstance().asyncGetSize(activity, clientPackageName, this.b);
            return;
        }
        HMSLog.e("HiappWizard", "<checkHmsSizeAndShowDialog> not show Dialog, activity is null or finishing.");
    }
}
