package com.huawei.hms.update.manager;

import android.app.Activity;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.updatesdk.UpdateSdkAPI;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes9.dex */
public class CheckUpdateLegacy {

    /* renamed from: a, reason: collision with root package name */
    private CheckUpdateCallBack f6054a;

    public void initCheckUpdateCallBack(Object obj, Activity activity) {
        final WeakReference weakReference = new WeakReference(obj);
        WeakReference weakReference2 = new WeakReference(activity);
        if (this.f6054a == null) {
            this.f6054a = new CheckUpdateCallBack() { // from class: com.huawei.hms.update.manager.CheckUpdateLegacy.1
                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onMarketInstallInfo(Intent intent) {
                    if (intent != null) {
                        int intExtra = intent.getIntExtra(UpdateKey.MARKET_DLD_STATUS, -99);
                        HMSLog.i("CheckUpdateLegacy", "onMarketInstallInfo installState: " + intent.getIntExtra(UpdateKey.MARKET_INSTALL_STATE, -99) + ",installType: " + intent.getIntExtra(UpdateKey.MARKET_INSTALL_TYPE, -99) + ",downloadCode: " + intExtra);
                    }
                }

                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onMarketStoreError(int i) {
                    HMSLog.e("CheckUpdateLegacy", "onMarketStoreError responseCode: " + i);
                }

                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onUpdateInfo(Intent intent) {
                    if (intent == null || weakReference == null) {
                        return;
                    }
                    try {
                        int intExtra = intent.getIntExtra("status", -99);
                        HMSLog.i("CheckUpdateLegacy", "onUpdateInfo status: " + intExtra + ",failcause: " + intent.getIntExtra(UpdateKey.FAIL_CODE, -99) + ",isExit: " + intent.getBooleanExtra(UpdateKey.MUST_UPDATE, false));
                        if (intExtra == 7) {
                            ApkUpgradeInfo apkUpgradeInfo = (ApkUpgradeInfo) intent.getSerializableExtra(UpdateKey.INFO);
                            if (apkUpgradeInfo != null) {
                                HMSLog.i("CheckUpdateLegacy", "onUpdateInfo: " + apkUpgradeInfo.toString());
                            }
                            CheckUpdateLegacy.this.a((WeakReference<Object>) weakReference, 1);
                        } else if (intExtra == 3) {
                            CheckUpdateLegacy.this.a((WeakReference<Object>) weakReference, 0);
                        } else {
                            CheckUpdateLegacy.this.a((WeakReference<Object>) weakReference, -1);
                        }
                        CheckUpdateLegacy.this.a(weakReference);
                    } catch (Exception e) {
                        HMSLog.e("CheckUpdateLegacy", "intent has some error" + e.getMessage());
                        CheckUpdateLegacy.this.a((WeakReference<Object>) weakReference, -1);
                    }
                }

                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onUpdateStoreError(int i) {
                    HMSLog.e("CheckUpdateLegacy", "onUpdateStoreError responseCode: " + i);
                }
            };
        }
        Activity activity2 = (Activity) weakReference2.get();
        if (activity2 == null) {
            HMSLog.e("CheckUpdateLegacy", "cpActivity is null");
        } else {
            UpdateSdkAPI.checkClientOTAUpdate(activity2, this.f6054a, true, 0, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WeakReference<Object> weakReference, int i) {
        Object obj = weakReference.get();
        if (obj == null) {
            HMSLog.e("CheckUpdateLegacy", "invokeOnResult: weakObj.get() is null");
            return;
        }
        try {
            Class.forName("com.huawei.hms.api.HuaweiApiClientImpl").getMethod("onResult", Integer.TYPE).invoke(obj, Integer.valueOf(i));
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            HMSLog.e("CheckUpdateLegacy", "invoke HuaweiApiClientImpl.onResult fail. " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WeakReference<Object> weakReference) {
        Object obj = weakReference.get();
        if (obj == null) {
            HMSLog.e("CheckUpdateLegacy", "invokeResetListener: weakObj.get() is null");
            return;
        }
        try {
            Class.forName("com.huawei.hms.api.HuaweiApiClientImpl").getMethod("resetListener", new Class[0]).invoke(obj, new Object[0]);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            HMSLog.e("CheckUpdateLegacy", "invoke HuaweiApiClientImpl.resetListener fail. " + e.getMessage());
        }
    }
}
