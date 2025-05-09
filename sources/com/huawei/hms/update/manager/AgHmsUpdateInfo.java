package com.huawei.hms.update.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.common.HmsCheckedState;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.ui.SafeIntent;
import com.huawei.hms.update.http.HttpWiseContentHelper;
import com.huawei.hms.update.http.WiseContentUrlHelper;
import com.huawei.hms.utils.AgHmsUpdateState;
import com.huawei.updatesdk.UpdateSdkAPI;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AgHmsUpdateInfo {
    private static final AgHmsUpdateInfo c = new AgHmsUpdateInfo();

    /* renamed from: a, reason: collision with root package name */
    private final Handler f6050a = new Handler(Looper.getMainLooper());
    private boolean b;

    static class CheckTimeoutRunnable implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final AgHmsUpdateInfo f6053a;

        public CheckTimeoutRunnable(AgHmsUpdateInfo agHmsUpdateInfo) {
            this.f6053a = agHmsUpdateInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.w("CheckAgHmsUpdateInfo", "updateSDK check timeout, set NOT_NEED_UPDATE");
            this.f6053a.a(HmsCheckedState.NOT_NEED_UPDATE, 0);
        }
    }

    private AgHmsUpdateInfo() {
    }

    public static AgHmsUpdateInfo getInstance() {
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void checkHmsAuthAndUpdateInfo(final android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = "false"
            java.lang.String r1 = "<checkHmsAuthAndUpdateInfo> enter"
            java.lang.String r2 = "CheckAgHmsUpdateInfo"
            com.huawei.hms.support.log.HMSLog.i(r2, r1)
            java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask
            com.huawei.hms.update.manager.AgHmsUpdateInfo$1 r3 = new com.huawei.hms.update.manager.AgHmsUpdateInfo$1
            r3.<init>()
            r1.<init>(r3)
            r3 = 10000(0x2710, double:4.9407E-320)
            java.util.concurrent.ExecutorService r5 = java.util.concurrent.Executors.newSingleThreadExecutor()     // Catch: java.util.concurrent.RejectedExecutionException -> L40 java.util.concurrent.TimeoutException -> L42 java.lang.InterruptedException -> L44 java.util.concurrent.ExecutionException -> L46 java.util.concurrent.CancellationException -> L48
            r5.execute(r1)     // Catch: java.util.concurrent.RejectedExecutionException -> L40 java.util.concurrent.TimeoutException -> L42 java.lang.InterruptedException -> L44 java.util.concurrent.ExecutionException -> L46 java.util.concurrent.CancellationException -> L48
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.util.concurrent.RejectedExecutionException -> L40 java.util.concurrent.TimeoutException -> L42 java.lang.InterruptedException -> L44 java.util.concurrent.ExecutionException -> L46 java.util.concurrent.CancellationException -> L48
            java.lang.Object r5 = r1.get(r3, r5)     // Catch: java.util.concurrent.RejectedExecutionException -> L40 java.util.concurrent.TimeoutException -> L42 java.lang.InterruptedException -> L44 java.util.concurrent.ExecutionException -> L46 java.util.concurrent.CancellationException -> L48
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.util.concurrent.RejectedExecutionException -> L40 java.util.concurrent.TimeoutException -> L42 java.lang.InterruptedException -> L44 java.util.concurrent.ExecutionException -> L46 java.util.concurrent.CancellationException -> L48
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.util.concurrent.RejectedExecutionException -> L36 java.util.concurrent.TimeoutException -> L38 java.lang.InterruptedException -> L3a java.util.concurrent.ExecutionException -> L3c java.util.concurrent.CancellationException -> L3e
            java.lang.String r7 = "authTask get recommendV3: "
            r6.<init>(r7)     // Catch: java.util.concurrent.RejectedExecutionException -> L36 java.util.concurrent.TimeoutException -> L38 java.lang.InterruptedException -> L3a java.util.concurrent.ExecutionException -> L3c java.util.concurrent.CancellationException -> L3e
            r6.append(r5)     // Catch: java.util.concurrent.RejectedExecutionException -> L36 java.util.concurrent.TimeoutException -> L38 java.lang.InterruptedException -> L3a java.util.concurrent.ExecutionException -> L3c java.util.concurrent.CancellationException -> L3e
            java.lang.String r6 = r6.toString()     // Catch: java.util.concurrent.RejectedExecutionException -> L36 java.util.concurrent.TimeoutException -> L38 java.lang.InterruptedException -> L3a java.util.concurrent.ExecutionException -> L3c java.util.concurrent.CancellationException -> L3e
            com.huawei.hms.support.log.HMSLog.i(r2, r6)     // Catch: java.util.concurrent.RejectedExecutionException -> L36 java.util.concurrent.TimeoutException -> L38 java.lang.InterruptedException -> L3a java.util.concurrent.ExecutionException -> L3c java.util.concurrent.CancellationException -> L3e
            goto L64
        L36:
            r6 = move-exception
            goto L4b
        L38:
            r6 = move-exception
            goto L4b
        L3a:
            r6 = move-exception
            goto L4b
        L3c:
            r6 = move-exception
            goto L4b
        L3e:
            r6 = move-exception
            goto L4b
        L40:
            r5 = move-exception
            goto L49
        L42:
            r5 = move-exception
            goto L49
        L44:
            r5 = move-exception
            goto L49
        L46:
            r5 = move-exception
            goto L49
        L48:
            r5 = move-exception
        L49:
            r6 = r5
            r5 = r0
        L4b:
            r7 = 1
            r1.cancel(r7)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r7 = "authTask get result has exception: "
            r1.<init>(r7)
            java.lang.String r6 = r6.getMessage()
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.huawei.hms.support.log.HMSLog.w(r2, r1)
        L64:
            boolean r0 = r0.equalsIgnoreCase(r5)
            if (r0 == 0) goto L85
            com.huawei.hms.utils.AgHmsUpdateState r9 = com.huawei.hms.utils.AgHmsUpdateState.getInstance()
            com.huawei.hms.common.HmsCheckedState r0 = com.huawei.hms.common.HmsCheckedState.NOT_NEED_UPDATE
            r9.setCheckedState(r0)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "not need to check update, recommendV3: "
            r9.<init>(r0)
            r9.append(r5)
            java.lang.String r9 = r9.toString()
            com.huawei.hms.support.log.HMSLog.w(r2, r9)
            return
        L85:
            r0 = 0
            r8.b = r0
            com.huawei.hms.update.manager.AgHmsUpdateInfo$CheckTimeoutRunnable r0 = new com.huawei.hms.update.manager.AgHmsUpdateInfo$CheckTimeoutRunnable
            r0.<init>(r8)
            android.os.Handler r1 = r8.f6050a
            r1.postDelayed(r0, r3)
            r8.a(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.update.manager.AgHmsUpdateInfo.checkHmsAuthAndUpdateInfo(android.content.Context):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(Context context) {
        if (context == null) {
            HMSLog.e("CheckAgHmsUpdateInfo", "<getHmsAuth> context is null");
            return "false";
        }
        String syncGetUrl = WiseContentUrlHelper.syncGetUrl(context);
        if (TextUtils.isEmpty(syncGetUrl)) {
            HMSLog.e("CheckAgHmsUpdateInfo", "<getHmsAuth> get url failed.");
            return "false";
        }
        HMSLog.i("CheckAgHmsUpdateInfo", "<getHmsAuth> get url successful.");
        String syncGetContent = HttpWiseContentHelper.syncGetContent(context, syncGetUrl);
        if (TextUtils.isEmpty(syncGetContent)) {
            HMSLog.e("CheckAgHmsUpdateInfo", "<getHmsAuth> download h5 config failed.");
            return "false";
        }
        HMSLog.i("CheckAgHmsUpdateInfo", "<getHmsAuth> download h5 config successful.");
        return a(syncGetContent);
    }

    private String a(String str) {
        String str2;
        str2 = "false";
        try {
            String string = new JSONObject(str).getJSONObject("hmscoreAuth").getString("recommendV3");
            str2 = ("true".equalsIgnoreCase(string) || "false".equalsIgnoreCase(string)) ? string : "false";
            HMSLog.i("CheckAgHmsUpdateInfo", "<parseHmsAuthJson> parse H5 config successful, recommendV3: " + str2);
        } catch (RuntimeException e) {
            HMSLog.e("CheckAgHmsUpdateInfo", "parse H5 config RuntimeException: " + e.getMessage());
        } catch (JSONException e2) {
            HMSLog.e("CheckAgHmsUpdateInfo", "parse H5 config JSONException: " + e2.getMessage());
        }
        return str2;
    }

    private void a(Context context) {
        UpdateSdkAPI.checkTargetAppUpdate(context.getApplicationContext(), "com.huawei.hwid", new CheckUpdateCallBack() { // from class: com.huawei.hms.update.manager.AgHmsUpdateInfo.2
            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onMarketInstallInfo(Intent intent) {
                HMSLog.i("CheckAgHmsUpdateInfo", "<onMarketInstallInfo> intent: ".concat(intent == null ? "is null" : "not null"));
                AgHmsUpdateInfo.this.a(HmsCheckedState.NOT_NEED_UPDATE, 0);
            }

            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onMarketStoreError(int i) {
                HMSLog.e("CheckAgHmsUpdateInfo", "<onMarketStoreError> responseCode: " + i);
                AgHmsUpdateInfo.this.a(HmsCheckedState.NOT_NEED_UPDATE, 0);
            }

            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onUpdateInfo(Intent intent) {
                HMSLog.i("CheckAgHmsUpdateInfo", "<onUpdateInfo> intent: ".concat(intent == null ? "is null" : "not null"));
                AgHmsUpdateInfo.this.a(intent);
            }

            @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
            public void onUpdateStoreError(int i) {
                HMSLog.e("CheckAgHmsUpdateInfo", "<onUpdateStoreError> responseCode: " + i);
                AgHmsUpdateInfo.this.a(HmsCheckedState.NOT_NEED_UPDATE, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        try {
            int intExtra = safeIntent.getIntExtra("status", -99);
            HMSLog.i("CheckAgHmsUpdateInfo", "<checkHmsUpdateInfo> status is " + intExtra);
            String stringExtra = safeIntent.getStringExtra(UpdateKey.FAIL_REASON);
            if (!TextUtils.isEmpty(stringExtra)) {
                HMSLog.e("CheckAgHmsUpdateInfo", "<checkHmsUpdateInfo> reason is " + stringExtra);
            }
            if (intExtra != 7) {
                if (intExtra == 3) {
                    HMSLog.e("CheckAgHmsUpdateInfo", "<checkHmsUpdateInfo> UpdateStatusCode.NO_UPGRADE_INFO");
                    a(HmsCheckedState.NOT_NEED_UPDATE, 0);
                    return;
                } else {
                    HMSLog.e("CheckAgHmsUpdateInfo", "<checkHmsUpdateInfo> other CHECK_FAILURE");
                    a(HmsCheckedState.NOT_NEED_UPDATE, 0);
                    return;
                }
            }
            Serializable serializableExtra = safeIntent.getSerializableExtra(UpdateKey.INFO);
            if (serializableExtra instanceof ApkUpgradeInfo) {
                StringBuilder sb = new StringBuilder("HMS new version code: ");
                ApkUpgradeInfo apkUpgradeInfo = (ApkUpgradeInfo) serializableExtra;
                sb.append(apkUpgradeInfo.getVersionCode_());
                HMSLog.i("CheckAgHmsUpdateInfo", sb.toString());
                a(HmsCheckedState.NEED_UPDATE, apkUpgradeInfo.getVersionCode_());
            }
        } catch (Throwable th) {
            HMSLog.e("CheckAgHmsUpdateInfo", "<checkHmsUpdateInfo> intent has some error" + th.getMessage());
            a(HmsCheckedState.NOT_NEED_UPDATE, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HmsCheckedState hmsCheckedState, int i) {
        synchronized (this) {
            if (this.b) {
                return;
            }
            AgHmsUpdateState.getInstance().setCheckedState(hmsCheckedState);
            AgHmsUpdateState.getInstance().setTargetVersionCode(i);
            a();
            this.b = true;
        }
    }

    private void a() {
        this.f6050a.removeCallbacksAndMessages(null);
    }
}
