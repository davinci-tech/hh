package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.KeyEvent;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.hms.update.manager.HmsApkReallySizeManager;
import com.huawei.hms.update.manager.ThirdPartyMarketConfigManager;
import com.huawei.hms.utils.AgHmsUpdateState;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class ThirdPartyMarketWizard extends AbsUpdateWizard {

    /* renamed from: a, reason: collision with root package name */
    private final List<c> f6121a = new ArrayList();
    private HandlerThread b;
    private Handler c;
    private boolean d;
    private boolean e;
    private ThirdPartyMarketConfigManager.MarketConfig f;
    private String g;
    private b h;
    private d i;
    private boolean j;

    static class b implements ThirdPartyMarketConfigManager.MarketConfigCallback {

        /* renamed from: a, reason: collision with root package name */
        final ThirdPartyMarketWizard f6122a;
        final AbstractDialog b;

        public b(ThirdPartyMarketWizard thirdPartyMarketWizard, AbstractDialog abstractDialog) {
            this.f6122a = thirdPartyMarketWizard;
            this.b = abstractDialog;
        }

        @Override // com.huawei.hms.update.manager.ThirdPartyMarketConfigManager.MarketConfigCallback
        public void onResult(ThirdPartyMarketConfigManager.MarketConfig marketConfig) {
            this.f6122a.d = true;
            this.f6122a.f = marketConfig;
            ThirdPartyMarketWizard thirdPartyMarketWizard = this.f6122a;
            thirdPartyMarketWizard.b(thirdPartyMarketWizard, this.b);
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f6123a;
        private int b;
        private boolean c;

        public int a() {
            return this.b;
        }

        public boolean b() {
            return this.c;
        }

        public String toString() {
            return "JumpMarketState{packageName='" + this.f6123a + "', requestCode=" + this.b + ", isJumpSuccessful=" + this.c + '}';
        }

        public void a(String str) {
            this.f6123a = str;
        }

        public void a(int i) {
            this.b = i;
        }

        public void a(boolean z) {
            this.c = z;
        }

        private c() {
        }
    }

    static class d implements HmsApkReallySizeManager.CheckHmsApkSizeCallback {

        /* renamed from: a, reason: collision with root package name */
        final ThirdPartyMarketWizard f6124a;
        final AbsUpdateWizard b;
        final AbstractDialog c;

        public d(ThirdPartyMarketWizard thirdPartyMarketWizard, AbsUpdateWizard absUpdateWizard, AbstractDialog abstractDialog) {
            this.f6124a = thirdPartyMarketWizard;
            this.b = absUpdateWizard;
            this.c = abstractDialog;
        }

        @Override // com.huawei.hms.update.manager.HmsApkReallySizeManager.CheckHmsApkSizeCallback
        public void onResult(String str) {
            this.f6124a.e = true;
            this.f6124a.g = str;
            this.f6124a.b(this.b, this.c);
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 2008;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        super.onBridgeActivityCreate(activity);
        HMSLog.i("ThirdPartyMarketWizard", "<onBridgeActivityCreate>");
        if (this.bean == null) {
            HMSLog.i("ThirdPartyMarketWizard", "<onBridgeActivityCreate>");
            finishBridgeActivity(8, 9);
            return;
        }
        HandlerThread handlerThread = new HandlerThread("ThirdPartyMarketWizard");
        this.b = handlerThread;
        handlerThread.start();
        this.c = new Handler(this.b.getLooper());
        this.updateType = 9;
        if (this.bean.isNeedConfirm() && !TextUtils.isEmpty(this.mClientAppName)) {
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
        HMSLog.i("ThirdPartyMarketWizard", "<onBridgeActivityDestroy>");
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.c = null;
        }
        HandlerThread handlerThread = this.b;
        if (handlerThread != null) {
            handlerThread.quit();
            this.b = null;
        }
        HmsApkReallySizeManager.getInstance().release();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        HMSLog.i("ThirdPartyMarketWizard", "<onBridgeActivityResult> requestCode: " + i + ", updateType: " + this.updateType);
        for (c cVar : this.f6121a) {
            if (i == cVar.a()) {
                HMSLog.i("ThirdPartyMarketWizard", "<onBridgeActivityResult> " + cVar);
                if (!cVar.b()) {
                    return true;
                }
            }
        }
        if (this.needTransfer && (iBridgeActivityDelegate = this.mBridgeActivityDelegate) != null) {
            return iBridgeActivityDelegate.onBridgeActivityResult(i, i2, intent);
        }
        HMSLog.i("ThirdPartyMarketWizard", "mPackageName: " + this.mPackageName + ", mClientVersionCode: " + this.mClientVersionCode);
        if (this.updateType != 9 || !a(i)) {
            return false;
        }
        if (isUpdated(this.mPackageName, this.mClientVersionCode)) {
            finishBridgeActivity(0, this.updateType);
        } else {
            finishBridgeActivity(8, this.updateType);
        }
        return true;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        super.onBridgeConfigurationChanged();
        HMSLog.i("ThirdPartyMarketWizard", "<onBridgeConfigurationChanged>");
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onCancel(AbstractDialog abstractDialog) {
        HMSLog.i("ThirdPartyMarketWizard", "Enter onCancel.");
        if (abstractDialog instanceof InstallConfirm) {
            b();
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    public void onDoWork(AbstractDialog abstractDialog) {
        HMSLog.i("ThirdPartyMarketWizard", "Enter onDoWork.");
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
            HMSLog.i("ThirdPartyMarketWizard", "In onKeyUp, Call finish.");
            Activity activity = getActivity();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.setResult(0, null);
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AbsUpdateWizard absUpdateWizard, AbstractDialog abstractDialog) {
        HMSLog.i("ThirdPartyMarketWizard", "<onResultShowDialog> isSizeDone: " + this.e + ", mMarketConfig: " + this.f);
        if (this.e && this.d) {
            boolean z = false;
            this.j = false;
            Activity activity = getActivity();
            boolean z2 = activity == null || activity.isFinishing() || activity.isDestroyed();
            AbstractDialog abstractDialog2 = this.mLatestDialog;
            if (abstractDialog2 != null && abstractDialog2.isShowing()) {
                z = true;
            }
            if (z2 || z) {
                HMSLog.e("ThirdPartyMarketWizard", "<onResultShowDialog> Activity Destroyed or Dialog isShoing");
                return;
            }
            if (TextUtils.isEmpty(this.g)) {
                this.g = a(activity);
            }
            if (!TextUtils.isEmpty(this.mClientAppName) && (abstractDialog instanceof InstallConfirm)) {
                String string = ResourceLoaderUtil.getString("hms_update_title");
                this.mClientAppName = string;
                InstallConfirm installConfirm = (InstallConfirm) abstractDialog;
                installConfirm.intAppName(string);
                installConfirm.setHmsApkSize(this.g);
            }
            abstractDialog.show(absUpdateWizard);
            this.mLatestDialog = abstractDialog;
        }
    }

    private boolean a() {
        HMSLog.i("ThirdPartyMarketWizard", "<gotoAppMarketForUpdate> start");
        if (AgHmsUpdateState.getInstance().isUpdateHms()) {
            HMSLog.i("ThirdPartyMarketWizard", "<gotoAppMarketForUpdate> need go to HUAWEI app market server for update");
            this.bean.setClientVersionCode(AgHmsUpdateState.getInstance().getTargetVersionCode());
            return false;
        }
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            if (TextUtils.isEmpty(this.mPackageName)) {
                HMSLog.e("ThirdPartyMarketWizard", "<gotoAppMarketForUpdate> mPackageName is empty");
                return false;
            }
            ThirdPartyMarketConfigManager.MarketConfig marketConfig = this.f;
            if (marketConfig != null && marketConfig.getAppMarketList().size() != 0) {
                for (int i = 0; i < this.f.getAppMarketList().size(); i++) {
                    String packageName = this.f.getAppMarketList().get(i).getPackageName();
                    c cVar = new c();
                    cVar.a(packageName);
                    cVar.a(getRequestCode() + 1000 + i);
                    try {
                        SafeIntent safeIntent = new SafeIntent(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL));
                        safeIntent.setData(Uri.parse("market://details?id=" + this.mPackageName));
                        safeIntent.setPackage(packageName);
                        HMSLog.i("ThirdPartyMarketWizard", "startActivityForResult");
                        activity.startActivityForResult(safeIntent, cVar.a());
                        cVar.a(true);
                        this.f6121a.add(cVar);
                        HMSLog.i("ThirdPartyMarketWizard", "open AppMarket successful: " + packageName + ", requestCode: " + cVar.a());
                        return true;
                    } catch (ActivityNotFoundException | IllegalArgumentException unused) {
                        cVar.a(false);
                        this.f6121a.add(cVar);
                        HMSLog.e("ThirdPartyMarketWizard", "can not open AppMarket: " + packageName + ", requestCode: " + cVar.a());
                    }
                }
                return false;
            }
            HMSLog.e("ThirdPartyMarketWizard", "<gotoAppMarketForUpdate> mMarketConfig is null or marketlist is 0");
            return false;
        }
        HMSLog.e("ThirdPartyMarketWizard", "<gotoAppMarketForUpdate> activity is null or isFinishing");
        return false;
    }

    void b() {
        HMSLog.i("ThirdPartyMarketWizard", "<userCancelUpdate>");
        finishBridgeActivity(13, this.updateType);
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    void a(Class<? extends AbstractDialog> cls) {
        HMSLog.i("ThirdPartyMarketWizard", "<showDialog> start");
        if (this.j) {
            HMSLog.i("ThirdPartyMarketWizard", "<showDialog> isChecking true, return");
            return;
        }
        dismissDialog();
        try {
            AbstractDialog newInstance = cls.newInstance();
            ThirdPartyMarketConfigManager.MarketConfig marketConfig = ThirdPartyMarketConfigManager.getInstance().getMarketConfig();
            String apkSize = HmsApkReallySizeManager.getInstance().getApkSize();
            if (marketConfig != null && !TextUtils.isEmpty(apkSize)) {
                this.f = marketConfig;
                this.g = apkSize;
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
                    if (!TextUtils.isEmpty(this.mClientAppName) && (newInstance instanceof InstallConfirm)) {
                        String string = ResourceLoaderUtil.getString("hms_update_title");
                        this.mClientAppName = string;
                        ((InstallConfirm) newInstance).intAppName(string);
                        ((InstallConfirm) newInstance).setHmsApkSize(this.g);
                    }
                    newInstance.show(this);
                    this.mLatestDialog = newInstance;
                    return;
                }
                HMSLog.e("ThirdPartyMarketWizard", "<showDialog> not show Dialog, activity is null or finishing.");
                return;
            }
            HMSLog.i("ThirdPartyMarketWizard", "<showDialog> checkAndShowDialog.");
            a(this, newInstance);
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("ThirdPartyMarketWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
        }
    }

    private void a(AbsUpdateWizard absUpdateWizard, AbstractDialog abstractDialog) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            String clientPackageName = this.bean.getClientPackageName();
            HMSLog.i("ThirdPartyMarketWizard", "<checkAndShowDialog> pkgName: " + clientPackageName);
            this.j = true;
            if (this.h == null) {
                this.h = new b(this, abstractDialog);
            }
            ThirdPartyMarketConfigManager.getInstance().asyncGetMarketConfig(activity, this.c, this.h);
            if (this.i == null) {
                this.i = new d(this, absUpdateWizard, abstractDialog);
            }
            HmsApkReallySizeManager.getInstance().asyncGetSize(activity, clientPackageName, this.i);
            return;
        }
        HMSLog.e("ThirdPartyMarketWizard", "<checkHmsSizeAndShowDialog> not show Dialog, activity is null or finishing.");
    }

    private String a(Activity activity) {
        ThirdPartyMarketConfigManager.MarketConfig marketConfig = this.f;
        if (marketConfig == null || marketConfig.getAppMarketList().size() <= 0) {
            return "";
        }
        try {
            String formatFileSize = Formatter.formatFileSize(activity, SystemUtils.getMegabyte(Double.parseDouble(this.f.getAppMarketList().get(0).getPackageSize())));
            HMSLog.e("ThirdPartyMarketWizard", "<getConfigPkgSize> configPkgSize: " + formatFileSize);
            return formatFileSize;
        } catch (RuntimeException e) {
            HMSLog.e("ThirdPartyMarketWizard", "<getConfigPkgSize> parse config size failed. " + e.getMessage());
            return "";
        }
    }

    private boolean a(int i) {
        Iterator<c> it = this.f6121a.iterator();
        while (it.hasNext()) {
            if (i == it.next().a()) {
                return true;
            }
        }
        return false;
    }
}
