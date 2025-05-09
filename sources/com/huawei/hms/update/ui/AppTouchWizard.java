package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.manager.HmsApkReallySizeManager;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class AppTouchWizard extends AbsUpdateWizard {
    public static final String APPTOUCH_UPDATE_HMS_ACTION = "com.apptouch.intent.action.update_hms";

    /* renamed from: a, reason: collision with root package name */
    private boolean f6081a;
    private a b;
    private String c;

    static class a implements HmsApkReallySizeManager.CheckHmsApkSizeCallback {

        /* renamed from: a, reason: collision with root package name */
        final AppTouchWizard f6082a;
        final AbstractDialog b;

        public a(AppTouchWizard appTouchWizard, AbstractDialog abstractDialog) {
            this.f6082a = appTouchWizard;
            this.b = abstractDialog;
        }

        @Override // com.huawei.hms.update.manager.HmsApkReallySizeManager.CheckHmsApkSizeCallback
        public void onResult(String str) {
            this.f6082a.c = str;
            AppTouchWizard appTouchWizard = this.f6082a;
            appTouchWizard.b(appTouchWizard, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AppTouchWizard appTouchWizard, AbstractDialog abstractDialog) {
        HMSLog.i("AppTouchWizard", "<onResultShowDialog> start");
        boolean z = false;
        this.f6081a = false;
        Activity activity = getActivity();
        boolean z2 = activity == null || activity.isFinishing() || activity.isDestroyed();
        AbstractDialog abstractDialog2 = this.mLatestDialog;
        if (abstractDialog2 != null && abstractDialog2.isShowing()) {
            z = true;
        }
        if (z2 || z) {
            HMSLog.e("AppTouchWizard", "<onResultShowDialog> Activity Destroyed or Dialog isShoing");
            return;
        }
        if (!TextUtils.isEmpty(this.mClientAppName) && (abstractDialog instanceof InstallConfirm)) {
            String string = ResourceLoaderUtil.getString("hms_update_title");
            this.mClientAppName = string;
            InstallConfirm installConfirm = (InstallConfirm) abstractDialog;
            installConfirm.intAppName(string);
            installConfirm.setHmsApkSize(this.c);
        }
        abstractDialog.show(appTouchWizard);
        this.mLatestDialog = abstractDialog;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 2007;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        super.onBridgeActivityCreate(activity);
        UpdateBean updateBean = this.bean;
        if (updateBean == null) {
            finishBridgeActivity(8, 7);
            HMSLog.w("AppTouchWizard", "bean is null");
            return;
        }
        this.updateType = 7;
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
        if (this.updateType != 7 || i != getRequestCode()) {
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
        HMSLog.i("AppTouchWizard", "Enter onCancel.");
        if (abstractDialog instanceof InstallConfirm) {
            b();
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onDoWork(AbstractDialog abstractDialog) {
        HMSLog.i("AppTouchWizard", "Enter onDoWork.");
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
            HMSLog.i("AppTouchWizard", "In onKeyUp, Call finish.");
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
        if (activity != null && !activity.isFinishing()) {
            if (TextUtils.isEmpty(this.mPackageName)) {
                HMSLog.e("AppTouchWizard", "No available App found, The packageName of App is empty.");
                return false;
            }
            try {
                Intent intent = new Intent();
                intent.setAction("com.huawei.appmarket.intent.action.AppDetail");
                intent.putExtra("APP_PACKAGENAME", this.mPackageName);
                a(intent);
                if (TextUtils.isEmpty(intent.getPackage())) {
                    HMSLog.e("AppTouchWizard", "No available AppTouch found.");
                    return false;
                }
                activity.startActivityForResult(intent, getRequestCode());
                return true;
            } catch (ActivityNotFoundException | IllegalArgumentException unused) {
                HMSLog.e("AppTouchWizard", "can not open AppTouch detail page");
                return false;
            }
        }
        HMSLog.e("AppTouchWizard", "activity is null or finishing.");
        return false;
    }

    void b() {
        finishBridgeActivity(13, this.updateType);
    }

    private void a(Intent intent) {
        List<ResolveInfo> list;
        String str = null;
        try {
            list = getActivity().getPackageManager().queryIntentServices(new Intent(APPTOUCH_UPDATE_HMS_ACTION), 0);
        } catch (Exception e) {
            HMSLog.e("AppTouchWizard", "query apptouch action failed. " + e.getMessage());
            list = null;
        }
        if (list != null && !list.isEmpty()) {
            Iterator<ResolveInfo> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ServiceInfo serviceInfo = it.next().serviceInfo;
                if (serviceInfo != null) {
                    String str2 = serviceInfo.packageName;
                    if (!TextUtils.isEmpty(str2) && SystemUtils.isSystemApp(getActivity(), str2)) {
                        str = str2;
                        break;
                    }
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            intent.setPackage(str);
        } catch (IllegalArgumentException unused) {
            HMSLog.e("AppTouchWizard", "IllegalArgumentException when AppTouchWizard-setIntentPackageName");
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    void a(Class<? extends AbstractDialog> cls) {
        dismissDialog();
        try {
            AbstractDialog newInstance = cls.newInstance();
            Activity activity = getActivity();
            if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                if (this.f6081a) {
                    HMSLog.i("AppTouchWizard", "<showDialog> isChecking true. return");
                    return;
                }
                String apkSize = HmsApkReallySizeManager.getInstance().getApkSize();
                if (TextUtils.isEmpty(apkSize)) {
                    HMSLog.i("AppTouchWizard", "<showDialog> checkAndShowDialog.");
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
            HMSLog.e("AppTouchWizard", "<checkHmsSizeAndShowDialog> not show Dialog, activity is null or finishing.");
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("AppTouchWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
        }
    }

    private void a(AppTouchWizard appTouchWizard, AbstractDialog abstractDialog) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            String clientPackageName = this.bean.getClientPackageName();
            HMSLog.i("AppTouchWizard", "<checkAndShowDialog> pkgName: " + clientPackageName);
            this.f6081a = true;
            if (this.b == null) {
                this.b = new a(appTouchWizard, abstractDialog);
            }
            HmsApkReallySizeManager.getInstance().asyncGetSize(activity, clientPackageName, this.b);
            return;
        }
        HMSLog.e("AppTouchWizard", "<checkHmsSizeAndShowDialog> not show Dialog, activity is null or finishing.");
    }
}
