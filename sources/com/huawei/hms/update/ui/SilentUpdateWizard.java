package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.alipay.sdk.m.p.e;
import com.huawei.hms.activity.IBridgeActivityDelegate;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.ui.SafeBundle;
import com.huawei.hms.update.UpdateConstants;
import com.huawei.hms.update.receive.SilentInstallReceive;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SilentUpdateWizard extends AbsUpdateWizard {

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f6118a;
    private Handler b = new Handler();
    private int c = 0;
    private Handler d = new a();

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SafeBundle safeBundle = new SafeBundle((Bundle) message.obj);
            switch (message.what) {
                case 101:
                    SilentUpdateWizard.this.a(safeBundle);
                    break;
                case 102:
                    SilentUpdateWizard.this.b(safeBundle);
                    break;
                case 103:
                    SilentUpdateWizard.this.c(safeBundle);
                    break;
            }
        }
    }

    class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SilentUpdateWizard.this.b(14);
        }

        /* synthetic */ b(SilentUpdateWizard silentUpdateWizard, a aVar) {
            this();
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 2000;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        super.onBridgeActivityCreate(activity);
        if (this.bean == null) {
            finishBridgeActivity(8, 0);
            return;
        }
        this.updateType = 0;
        if (a(activity)) {
            return;
        }
        if (startNextWizard(true)) {
            biReportEvent(8, this.updateType);
        } else {
            finishBridgeActivity(8, this.updateType);
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        this.b.removeCallbacksAndMessages(null);
        b();
        super.onBridgeActivityDestroy();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        IBridgeActivityDelegate iBridgeActivityDelegate;
        if (this.needTransfer && (iBridgeActivityDelegate = this.mBridgeActivityDelegate) != null) {
            return iBridgeActivityDelegate.onBridgeActivityResult(i, i2, intent);
        }
        HMSLog.i("SilentUpdateWizard", "onBridgeActivityResult requestCode is " + i + "resultCode is " + i2);
        if (i != getRequestCode()) {
            return false;
        }
        if (i2 == 0) {
            a();
            a(20000);
            return true;
        }
        if (i2 == 4) {
            c();
            return true;
        }
        if (startNextWizard(true)) {
            biReportEvent(i2, this.updateType);
        } else {
            finishBridgeActivity(i2, this.updateType);
        }
        return true;
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        super.onBridgeConfigurationChanged();
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        super.onKeyUp(i, keyEvent);
    }

    private void b() {
        BroadcastReceiver broadcastReceiver;
        Activity activity = getActivity();
        if (activity == null || (broadcastReceiver = this.f6118a) == null) {
            return;
        }
        activity.unregisterReceiver(broadcastReceiver);
        this.f6118a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(SafeBundle safeBundle) {
        if (safeBundle.containsKey("packagename") && safeBundle.containsKey("status")) {
            String string = safeBundle.getString("packagename");
            int i = safeBundle.getInt("status");
            HMSLog.i("SilentUpdateWizard", "handlerInstallStatus-status is " + i);
            if (string == null || !string.equals(this.mPackageName)) {
                return;
            }
            if (i == 2) {
                this.b.removeCallbacksAndMessages(null);
                AbstractDialog abstractDialog = this.mLatestDialog;
                if (abstractDialog != null) {
                    ((DownloadProgress) abstractDialog).a(100);
                }
                finishBridgeActivity(0, this.updateType);
                return;
            }
            if (i == -1 || i == -2) {
                b(i);
            } else {
                a(60000);
            }
        }
    }

    private boolean a(Activity activity) {
        if (TextUtils.isEmpty(this.mPackageName)) {
            return false;
        }
        Intent intent = new Intent(UpdateConstants.ACTION_NAME_HIAPP_SILENT_DOWNLOAD);
        try {
            intent.setPackage("com.huawei.appmarket");
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SdkCfgSha256Record.PKGNAME, this.mPackageName);
                jSONObject.put("versioncode", this.mClientVersionCode);
                jSONArray.put(jSONObject);
                intent.putExtra(e.n, jSONArray.toString());
                intent.putExtra("isHmsOrApkUpgrade", this.bean.isHmsOrApkUpgrade());
                intent.putExtra("buttonDlgY", ResourceLoaderUtil.getString("hms_install"));
                intent.putExtra("buttonDlgN", ResourceLoaderUtil.getString("hms_cancel"));
                intent.putExtra("upgradeDlgContent", ResourceLoaderUtil.getString("hms_update_message_new", "%P"));
                try {
                    HMSLog.i("SilentUpdateWizard", "start silent activity of AppMarket");
                    activity.startActivityForResult(intent, getRequestCode());
                    HMSLog.i("SilentUpdateWizard", "start silent activity finished");
                    return true;
                } catch (ActivityNotFoundException unused) {
                    HMSLog.e("SilentUpdateWizard", "ActivityNotFoundException");
                    return false;
                }
            } catch (JSONException e) {
                HMSLog.e("SilentUpdateWizard", "create hmsJsonObject fail" + e.getMessage());
                return false;
            }
        } catch (IllegalArgumentException unused2) {
            HMSLog.e("SilentUpdateWizard", "IllegalArgumentException when silentInstall intent.setPackage");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.b.removeCallbacksAndMessages(null);
        b();
        dismissDialog();
        if (!startNextWizard(false)) {
            finishBridgeActivity(i, this.updateType);
        } else {
            biReportEvent(i, this.updateType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SafeBundle safeBundle) {
        String string = safeBundle.containsKey("UpgradePkgName") ? safeBundle.getString("UpgradePkgName") : null;
        if (string != null && string.equals(this.mPackageName) && safeBundle.containsKey("UpgradeDownloadProgress") && safeBundle.containsKey("UpgradeAppName")) {
            int i = safeBundle.getInt("UpgradeDownloadProgress");
            HMSLog.i("SilentUpdateWizard", "handlerDownloadProgress-progress is " + i);
            a(20000);
            if (i >= 99) {
                i = 99;
            }
            this.c = i;
            if (this.mLatestDialog == null) {
                a(DownloadProgress.class);
            }
            AbstractDialog abstractDialog = this.mLatestDialog;
            if (abstractDialog != null) {
                ((DownloadProgress) abstractDialog).a(i);
            }
        }
    }

    void c() {
        finishBridgeActivity(13, this.updateType);
    }

    private void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UpdateConstants.DOWNLOAD_STATUS_ACTION);
        intentFilter.addAction(UpdateConstants.DOWNLOAD_PROGRESS_ACTION);
        intentFilter.addAction(UpdateConstants.INSTALL_ACTION);
        this.f6118a = new SilentInstallReceive(this.d);
        Activity activity = getActivity();
        if (activity == null) {
            HMSLog.e("SilentUpdateWizard", "registerReceiver, but activity is null");
        } else if (Build.VERSION.SDK_INT >= 33) {
            activity.registerReceiver(this.f6118a, intentFilter, 2);
        } else {
            activity.registerReceiver(this.f6118a, intentFilter);
        }
    }

    private void a(int i) {
        this.b.removeCallbacksAndMessages(null);
        this.b.postDelayed(new b(this, null), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SafeBundle safeBundle) {
        String string = safeBundle.containsKey("UpgradePkgName") ? safeBundle.getString("UpgradePkgName") : null;
        if (string != null && string.equals(this.mPackageName) && safeBundle.containsKey("downloadtask.status")) {
            int i = safeBundle.getInt("downloadtask.status");
            HMSLog.i("SilentUpdateWizard", "handleDownloadStatus-status is " + i);
            if (i == 3 || i == 5 || i == 6 || i == 8) {
                b(i);
            } else if (i == 4) {
                a(60000);
            } else {
                a(20000);
            }
        }
    }

    @Override // com.huawei.hms.update.ui.AbsUpdateWizard
    void a(Class<? extends AbstractDialog> cls) {
        try {
            AbstractDialog newInstance = cls.newInstance();
            int i = this.c;
            if (i > 0 && (newInstance instanceof DownloadProgress)) {
                ((DownloadProgress) newInstance).intProgress(i);
            }
            newInstance.show(this);
            this.mLatestDialog = newInstance;
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("SilentUpdateWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
        }
    }
}
