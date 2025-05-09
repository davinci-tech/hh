package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.callback.OtaResultCallbackInterface;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwdevice.outofprocess.callback.HwLocationCallback;
import com.huawei.hwdevice.outofprocess.datatype.DataOtaParametersV2;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.info.PsiUploadResponseInfo;
import com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public class kih {
    private kif b;
    private kif h;
    private String e = "";

    /* renamed from: a, reason: collision with root package name */
    private OtaResultCallbackInterface f14390a = null;
    private BroadcastReceiver c = null;
    private OtaResultCallbackInterface d = new OtaResultCallbackInterface() { // from class: kih.4
        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onFileTransferState(String str, int i) {
            if (i > 100) {
                i = 100;
            }
            kih.this.f14390a.onFileTransferState(str, i);
            if (i == 100) {
                jcg.d(str, false);
            }
        }

        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onUpgradeFailed(String str, int i, String str2) {
            kih.this.f14390a.onUpgradeFailed(str, i, str2);
            LogUtil.h("OtaManager", "iOtaResultCallback onUpgradeFailed errorCode", Integer.valueOf(i), " errorMessage", str2);
            jcg.d(str, false);
        }

        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onFileRespond(String str, int i) {
            kih.this.f14390a.onFileRespond(str, i);
            LogUtil.a("OtaManager", "iOtaResultCallback onFileRespond checkResult", Integer.valueOf(i));
            jcg.d(str, false);
        }

        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onFileTransferReport(String str) {
            kih.this.f14390a.onFileTransferReport(str);
        }
    };
    private OtaResultCallbackInterface g = new OtaResultCallbackInterface() { // from class: kih.1
        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onFileTransferState(String str, int i) {
            if (i > 100) {
                i = 100;
            }
            if (kih.this.f14390a != null) {
                kih.this.f14390a.onFileTransferState(str, i);
            }
            if (i == 100) {
                jst.d(str, false);
            }
        }

        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onUpgradeFailed(String str, int i, String str2) {
            if (kih.this.f14390a != null) {
                kih.this.f14390a.onUpgradeFailed(str, i, str2);
            }
            LogUtil.h("OtaManager", "iOtaResultCallback onUpgradeFailed errorCode:", Integer.valueOf(i), " errorMessage:", str2);
            jst.d(str, false);
        }

        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onFileRespond(String str, int i) {
            if (kih.this.f14390a != null) {
                kih.this.f14390a.onFileRespond(str, i);
            }
            LogUtil.a("OtaManager", "iOtaResultCallback onFileRespond checkResult:", Integer.valueOf(i));
            jst.d(str, false);
        }

        @Override // com.huawei.callback.OtaResultCallbackInterface
        public void onFileTransferReport(String str) {
            kih.this.f14390a.onFileTransferReport(str);
        }
    };

    public static kih e() {
        return a.f14392a;
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("OtaManager", "sendAgreementSign deviceInfo == null");
            return;
        }
        LogUtil.a("OtaManager", "sendAgreementSign enter");
        String i = kxz.i(BaseApplication.getContext(), deviceInfo.getDeviceIdentify());
        if (TextUtils.isEmpty(i)) {
            kia.c().c(deviceInfo, "0");
        } else {
            kia.c().c(deviceInfo, i);
        }
    }

    public void b() {
        if (!jrd.d(jrd.b("charging_key_band_auto_check_time"))) {
            LogUtil.h("OtaManager", "startChargingJob isChargingUpdated is true");
            e(1);
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "OtaManager");
        if (deviceList == null || deviceList.size() <= 0) {
            LogUtil.h("OtaManager", "startChargingJob (deviceList == null) || (deviceList.size() <= 0)");
            e(2);
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("OtaManager", "startChargingJob deviceInfo == null");
            e(2);
            return;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
        intent.putExtra("extra_band_imei", deviceInfo.getDeviceIdentify());
        intent.setAction("action_band_auto_check_new_version");
        try {
            LogUtil.a("OtaManager", "startChargingJob HwUpdateService start");
            e(0);
            jrd.c("charging_key_band_auto_check_time");
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            LogUtil.b("ChargingJobService Exception ", ExceptionUtils.d(e));
        }
    }

    private void e(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("chargingStatus", String.valueOf(i));
        LogUtil.a("OtaManager", "chargingOpAnalytics result = ", Integer.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.CHARGING_UPGRADE_PROCESS_80070009.value(), linkedHashMap);
    }

    public void a(DeviceParameter deviceParameter, String str, int i, OtaResultCallbackInterface otaResultCallbackInterface) {
        if (!jsn.d(deviceParameter.getProductType())) {
            e(deviceParameter, str, i, otaResultCallbackInterface);
        } else {
            d(deviceParameter, str, i, otaResultCallbackInterface);
        }
    }

    private void d(DeviceParameter deviceParameter, String str, int i, OtaResultCallbackInterface otaResultCallbackInterface) {
        DataOtaParametersV2 dataOtaParametersV2;
        if (!TextUtils.isEmpty(str)) {
            dataOtaParametersV2 = (DataOtaParametersV2) b(str, DataOtaParametersV2.class);
        } else {
            LogUtil.h("OtaManager", "sendOTAFileData parameter is null.");
            dataOtaParametersV2 = null;
        }
        if (dataOtaParametersV2 != null && dataOtaParametersV2.getOtaInterval() != 109000) {
            LogUtil.h("OtaManager", "sendOtaFileData otaParameter.getOtaInterval() != Constant.OTA_TAINTERVAL");
            kif kifVar = this.b;
            if (kifVar != null) {
                kifVar.d();
                this.b = null;
            }
        }
        if (deviceParameter != null) {
            if (this.b == null) {
                this.b = new kif(null, deviceParameter.getMac());
            }
            this.f14390a = otaResultCallbackInterface;
            if (i == 0) {
                jcg.d(deviceParameter.getMac(), true);
            }
            this.b.a(deviceParameter.getFilePath(), deviceParameter.getVersion(), dataOtaParametersV2, i, this.d);
        }
    }

    public void c(DeviceInfo deviceInfo, byte[] bArr) {
        if (jsn.d(deviceInfo.getProductType())) {
            kif kifVar = this.b;
            if (kifVar != null) {
                kifVar.d(bArr);
                return;
            }
            return;
        }
        kif kifVar2 = this.h;
        if (kifVar2 != null) {
            kifVar2.d(bArr);
        }
    }

    private void e(DeviceParameter deviceParameter, String str, int i, OtaResultCallbackInterface otaResultCallbackInterface) {
        DataOtaParametersV2 dataOtaParametersV2;
        LogUtil.a("OtaManager", "Enter sendOTAFileData enter.");
        if (!TextUtils.isEmpty(str)) {
            dataOtaParametersV2 = (DataOtaParametersV2) b(str, DataOtaParametersV2.class);
        } else {
            LogUtil.h("OtaManager", "sendOTAFileData parameter is null.");
            dataOtaParametersV2 = null;
        }
        if (dataOtaParametersV2 != null && dataOtaParametersV2.getOtaInterval() != 109000) {
            LogUtil.h("OtaManager", "sendOtaFileData otaParameter.getOtaInterval() != Constant.OTA_TAINTERVAL");
            kif kifVar = this.h;
            if (kifVar != null) {
                kifVar.d();
                this.h = null;
            }
        }
        if (deviceParameter != null) {
            if (this.h == null) {
                this.h = new kif(izy.b(BaseApplication.getContext()), deviceParameter.getMac());
            }
            this.f14390a = otaResultCallbackInterface;
            if (i == 0) {
                jst.d(deviceParameter.getMac(), true);
            }
            this.h.a(deviceParameter.getFilePath(), deviceParameter.getVersion(), dataOtaParametersV2, i, this.g);
        }
    }

    public void c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("OtaManager", "registerReminder deviceInfo == null");
            return;
        }
        boolean parseBoolean = Boolean.parseBoolean(SharedStoreManager.zZ_("keyvaldb_unencrypt").getString("device_is_reconnect", "true"));
        LogUtil.a("OtaManager", "registerReminder isReconnect ", Boolean.valueOf(parseBoolean));
        if (!parseBoolean) {
            kxz.c(0, deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
            kxz.a(0, deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
            b(deviceInfo, true);
            c(deviceInfo, true);
            return;
        }
        int b = kxz.b(deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
        LogUtil.a("OtaManager", "registerReminder psiUploadCount ", Integer.valueOf(b));
        if (b == 14) {
            return;
        }
        b(deviceInfo, false);
    }

    private void b(DeviceInfo deviceInfo, boolean z) {
        long j;
        this.e = deviceInfo.getDeviceIdentify();
        if (this.c == null) {
            this.c = new d();
            BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.c, new IntentFilter("action_broadcast_alarm_clock"), LocalBroadcast.c, null);
        }
        long e = nsj.e(System.currentTimeMillis(), 0) + 72000000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(e);
        LogUtil.a("OtaManager", "registerReminder hour ", Integer.valueOf(calendar.get(11)));
        long timeInMillis = calendar.getTimeInMillis();
        if (System.currentTimeMillis() <= timeInMillis || z) {
            j = timeInMillis;
        } else {
            calendar.add(5, 1);
            long timeInMillis2 = calendar.getTimeInMillis();
            b(deviceInfo, true, false, System.currentTimeMillis() + 60000);
            j = timeInMillis2;
        }
        LogUtil.a("OtaManager", "registerReminder requestCode ", 20230830, ", startTime ", Long.valueOf(e), " selectTime ", Long.valueOf(j));
        Intent intent = new Intent("action_broadcast_alarm_clock");
        intent.putExtra("device_id", deviceInfo.getDeviceIdentify());
        intent.putExtra("timed_task", true);
        intent.putExtra("reconnect_request_first", false);
        sqa.ekC_(20230830, intent, 201326592, 0, j, 86400000L);
    }

    private void b(DeviceInfo deviceInfo, boolean z, boolean z2, long j) {
        Intent intent = new Intent("action_broadcast_alarm_clock");
        intent.putExtra("device_id", deviceInfo.getDeviceIdentify());
        intent.putExtra("timed_task", z);
        intent.putExtra("reconnect_request_first", z2);
        sqa.ekx_(20230901, intent, 201326592, 0, j);
    }

    /* renamed from: kih$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    class AnonymousClass2 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f14391a;
        final /* synthetic */ DeviceInfo b;

        AnonymousClass2(DeviceInfo deviceInfo, boolean z) {
            this.b = deviceInfo;
            this.f14391a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            jrc.e().a(this.b.getDeviceIdentify(), new HwLocationCallback() { // from class: kih.2.4
                @Override // com.huawei.hwdevice.outofprocess.callback.HwLocationCallback
                public void onResult(String str) {
                    LogUtil.a("OtaManager", "requestPsiUpload initLocation message:", str);
                    kyf.a().c(AnonymousClass2.this.b, false, new PsiUploadResultCallback() { // from class: kih.2.4.1
                        @Override // com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback
                        public void onSuccess(PsiUploadResponseInfo psiUploadResponseInfo) {
                            LogUtil.a("OtaManager", "requestPsiUpload onSuccess:", psiUploadResponseInfo.toString());
                            kxz.d(System.currentTimeMillis(), AnonymousClass2.this.b.getDeviceIdentify(), BaseApplication.getContext());
                            if (jds.c(psiUploadResponseInfo.getResultCode(), 10) != 0) {
                                kih.this.a(AnonymousClass2.this.b, AnonymousClass2.this.f14391a);
                                return;
                            }
                            int b = kxz.b(AnonymousClass2.this.b.getDeviceIdentify(), BaseApplication.getContext());
                            if (b == 13) {
                                kih.this.e(AnonymousClass2.this.b.getDeviceIdentify(), 20230830);
                            }
                            if (b == -1) {
                                b = 0;
                            }
                            LogUtil.a("OtaManager", "requestPsiUpload onSuccess psiUploadCount:", Integer.valueOf(b), " +1");
                            kxz.c(b + 1, AnonymousClass2.this.b.getDeviceIdentify(), BaseApplication.getContext());
                            kih.this.e(AnonymousClass2.this.b);
                        }

                        @Override // com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback
                        public void onFailure(Throwable th) {
                            LogUtil.a("OtaManager", "requestPsiUpload onFailure:", th.getMessage());
                            kxz.d(System.currentTimeMillis(), AnonymousClass2.this.b.getDeviceIdentify(), BaseApplication.getContext());
                            kih.this.a(AnonymousClass2.this.b, AnonymousClass2.this.f14391a);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo, boolean z) {
        ThreadPoolManager.d().execute(new AnonymousClass2(deviceInfo, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo, boolean z) {
        int c = kxz.c(deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
        if (c == -1) {
            c = 0;
        }
        LogUtil.a("OtaManager", "retryRequestPsiUpload onFailure psiUploadFailCount :", Integer.valueOf(c), " isReconnectFirst ", Boolean.valueOf(z));
        if (z) {
            if (c < 6) {
                d(deviceInfo, c, true);
                return;
            } else {
                e(deviceInfo);
                return;
            }
        }
        if (c < 1) {
            d(deviceInfo, c, false);
        } else {
            e(deviceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo) {
        LogUtil.a("OtaManager", "resetPsiUploadResult psiUploadFailCount 0");
        e(deviceInfo.getDeviceIdentify(), 20230901);
        kxz.a(0, deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
    }

    private void d(DeviceInfo deviceInfo, int i, boolean z) {
        LogUtil.a("OtaManager", "setPsiUploadRetryAlarm  psiUploadFailCount :", Integer.valueOf(i), " +1");
        kxz.a(i + 1, deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
        b(deviceInfo, false, z, System.currentTimeMillis() + 1200000);
    }

    public void e(String str, int i) {
        if (!this.e.equals(str)) {
            LogUtil.h("OtaManager", "cancelReminder !mDeviceIdentify.equals(deviceIdentify)");
        } else {
            LogUtil.a("OtaManager", "cancelReminder requestCode ", Integer.valueOf(i));
            sqa.ekn_(i, new Intent("action_broadcast_alarm_clock"), 201326592);
        }
    }

    private <T> T b(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            str = "{}";
        }
        return (T) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
    }

    /* loaded from: classes5.dex */
    class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("OtaManager", "AlarmClockReceiver receiver");
            if (sqa.ekw_(intent)) {
                LogUtil.h("OtaManager", "AlarmClockReceiver isDifferentTimeZone.");
                return;
            }
            String stringExtra = intent.getStringExtra("device_id");
            boolean booleanExtra = intent.getBooleanExtra("timed_task", false);
            boolean booleanExtra2 = intent.getBooleanExtra("reconnect_request_first", false);
            LogUtil.a("OtaManager", "AlarmClockReceiver isReconnectFirst ", Boolean.valueOf(booleanExtra2), " isTimedTask ", Boolean.valueOf(booleanExtra));
            DeviceInfo deviceInfo = null;
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "OtaManager");
            if (!TextUtils.isEmpty(stringExtra) && deviceList != null && deviceList.size() > 0) {
                for (DeviceInfo deviceInfo2 : deviceList) {
                    if (stringExtra.equals(deviceInfo2.getDeviceIdentify())) {
                        deviceInfo = deviceInfo2;
                    }
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = "onReceive currentDeviceInfo ";
            objArr[1] = deviceInfo == null ? Constants.NULL : deviceInfo.toString();
            LogUtil.c("OtaManager", objArr);
            if (deviceInfo == null) {
                return;
            }
            int b = kxz.b(deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
            LogUtil.a("OtaManager", "registerReminder psiUploadCount ", Integer.valueOf(b));
            if (b == 14) {
                return;
            }
            if (booleanExtra) {
                long a2 = kxz.a(deviceInfo.getDeviceIdentify(), BaseApplication.getContext());
                long currentTimeMillis = System.currentTimeMillis();
                boolean d = jrd.d(a2, currentTimeMillis);
                LogUtil.a("OtaManager", "onReceive psiUploadTime ", Long.valueOf(a2), " currentTimeMillis ", Long.valueOf(currentTimeMillis), " isSameDay ", Boolean.valueOf(d));
                if (d) {
                    return;
                }
            }
            kih.this.c(deviceInfo, booleanExtra2);
        }
    }

    /* loaded from: classes5.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static kih f14392a = new kih();
    }
}
