package com.huawei.hwdevice.mainprocess.mgr.hwotamanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager;
import com.huawei.hwdevice.outofprocess.callback.HwLocationCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IDeviceListCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.hwotamanager.IWearHwUpdateServiceAIDL;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwservicesmgr.ISilenceOTAAIDLCallback;
import com.huawei.hwversionmgr.info.EnterpriseResponseInfo;
import com.huawei.hwversionmgr.info.PsiUploadResponseInfo;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.hwversionmgr.selfupdate.appupdate.UpdateBase;
import com.huawei.hwversionmgr.utils.callback.EnterpriseResultCallback;
import com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.cun;
import defpackage.cvs;
import defpackage.cwi;
import defpackage.iyl;
import defpackage.jdk;
import defpackage.jds;
import defpackage.jfo;
import defpackage.jkj;
import defpackage.jkk;
import defpackage.jkl;
import defpackage.jkn;
import defpackage.jkq;
import defpackage.jkr;
import defpackage.jks;
import defpackage.jkv;
import defpackage.jqi;
import defpackage.jrc;
import defpackage.jrd;
import defpackage.jrj;
import defpackage.jrs;
import defpackage.kwx;
import defpackage.kxf;
import defpackage.kxj;
import defpackage.kxl;
import defpackage.kxm;
import defpackage.kxw;
import defpackage.kxz;
import defpackage.kya;
import defpackage.kyf;
import defpackage.kyh;
import defpackage.kyk;
import defpackage.kyp;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwUpdateService extends Service {
    private static final Object e = new Object();
    private Context d;
    private UpdateBase f;
    private HwVersionManager g;
    private final h c = new h();
    private Handler j = null;

    /* renamed from: a, reason: collision with root package name */
    private HandlerThread f6293a = null;
    private List<String> b = new ArrayList(16);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.c;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.d = getApplicationContext();
        LogUtil.a("HwUpdateService", "onCreate");
        this.f = new UpdateBase(this.d);
        this.g = HwVersionManager.c(this.d);
        b();
        jkj.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        DeviceInfo e2 = jkj.d().e(str);
        UpdateBase.d(e2);
        UpdateBase.c(-1);
        kyp.e(e2);
        kyk.d(e2);
        HwVersionManager.c(BaseApplication.getContext()).e(cwi.c(e2, 183));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("HwUpdateService", "onDestroy");
        if (this.f6293a != null) {
            Handler handler = this.j;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            this.f6293a.getLooper().quit();
            this.j = null;
            this.f6293a = null;
        }
        jrc.e().c();
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i2, final int i3) {
        super.onStartCommand(intent, i2, i3);
        LogUtil.a("HwUpdateService", "onStartCommand: intent = ", intent, " startId ", Integer.valueOf(i3));
        if (intent == null) {
            return 2;
        }
        String stringExtra = intent.getStringExtra("extra_band_imei");
        if (stringExtra == null) {
            stringExtra = "";
        }
        final String str = stringExtra;
        final String action = intent.getAction();
        LogUtil.a("R_OTA_HwUpdateService", "handleIntent: action = ", action);
        if (action == null) {
            return 2;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.2
            @Override // java.lang.Runnable
            public void run() {
                if (jkj.d().e(str) == null) {
                    LogUtil.a("HwUpdateService", "onStartCommand deviceInfo == null");
                    cun.c().executeDeviceRelatedLogic(ExecuteMode.FORCE_INIT_PHONE_SERVICE, null, "HwUpdateService");
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e2) {
                        LogUtil.b("HwUpdateService", "onStartCommand InterruptedException ", ExceptionUtils.d(e2));
                    }
                }
                HwUpdateService.this.d(str);
                if (HwUpdateService.this.bHQ_(intent, action, i3, str)) {
                    return;
                }
                HwUpdateService.this.e(i3, "handleIntent");
            }
        });
        return 2;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        LogUtil.a("HwUpdateService", "onTaskRemoved enter");
        jkn.a().e();
        String stringExtra = intent.getStringExtra("extra_band_imei");
        if (!TextUtils.isEmpty(stringExtra)) {
            jkj.d().d(stringExtra, 0);
        }
        super.onTaskRemoved(intent);
    }

    public void e(int i2, String str) {
        if (i2 != -1) {
            stopSelf(i2);
            LogUtil.a("HwUpdateService", "clearTaskService: tag = ", str, " startId ", Integer.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean bHQ_(Intent intent, String str, int i2, String str2) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -985442731:
                if (str.equals("action_device_request_download_ota")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -818643229:
                if (str.equals("action_cancel_download_all_ota")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -750211717:
                if (str.equals("action_band_manual_update_new_version")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -735037261:
                if (str.equals("action_band_auto_check_new_version")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -650386609:
                if (str.equals("action_band_manual_change_language")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -267998921:
                if (str.equals("action_band_auto_download")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 239008793:
                if (str.equals("action_band_check_new_version_to_activate")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 823869187:
                if (str.equals("action_band_download_new_version")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return bHR_(intent, i2, str2);
            case 1:
                jkj.d().e();
                return false;
            case 2:
                return g(i2, str2, intent.getBooleanExtra("key_device_request_check_new_version", false));
            case 3:
                return o(i2, str2);
            case 4:
                return a(i2);
            case 5:
                return l(i2, str2);
            case 6:
                return bHS_(intent, i2);
            case 7:
                return b(i2, str2, false);
            default:
                LogUtil.a("HwUpdateService", "handleIntent, default branch");
                return false;
        }
    }

    private boolean bHR_(Intent intent, int i2, String str) {
        if (intent.getBooleanExtra("key_is_user_agree_download", false)) {
            return m(i2, str);
        }
        if (jkv.b().b(str, intent.getIntExtra("key_auto_install_status", 255))) {
            return d(i2, str);
        }
        return false;
    }

    private boolean o(int i2, String str) {
        if (!ThermalCallback.getInstance().isTriggerOtaTask()) {
            LogUtil.h("startAutoCheckNewVersion does not have the conditions to trigger the task.", new Object[0]);
            return false;
        }
        synchronized (e) {
            this.b.clear();
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwUpdateService");
            if (deviceList != null && deviceList.size() > 0) {
                Iterator<DeviceInfo> it = deviceList.iterator();
                while (it.hasNext()) {
                    this.b.add(it.next().getDeviceIdentify());
                }
                LogUtil.a("HwUpdateService", "startAutoCheckNewVersion deviceInfoList.size ", Integer.valueOf(deviceList.size()));
            }
        }
        a(i2, str, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i2, String str, boolean z) {
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            c(i2, "initAutoBandCheckNewVersion deviceInfo is null");
            return;
        }
        if (kxz.a(e2, 108) && !Utils.l()) {
            n(i2, str);
            HwOtaUpgradeManager.a().c(e2, new HwOtaUpgradeManager.ClearServiceListener() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.15
                @Override // com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.ClearServiceListener
                public void clearTask() {
                    HwUpdateService.this.c(i2, "checkNewVersion return");
                }
            });
            return;
        }
        if ((kxz.a(e2, 108) && Utils.l()) || kxz.a(e2, 58)) {
            n(i2, str);
            HwOtaUpgradeManager.a().a(str, true, new HwOtaUpgradeManager.ClearServiceListener() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.11
                @Override // com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.ClearServiceListener
                public void clearTask() {
                    HwUpdateService.this.c(i2, "requestWearNewVersion return");
                }
            });
            return;
        }
        if (z) {
            jkj.d().d(str, e2.getDeviceEnterpriseVersion());
        } else {
            jkj.d().d(str, e2.getSoftVersion());
        }
        LogUtil.a("HwUpdateService", "initAutoBandCheckNewVersion deviceBtMac = ", iyl.d().e(e2.getSecurityDeviceId()));
        jrj.d(OpAnalyticsConstants.H5_LOADING_DELAY, "auto-deviceOta-checkFile");
        jkk.d().d("", false, true);
        b(i2, e2, z);
        d(e2);
    }

    private void d(final DeviceInfo deviceInfo) {
        jkk.d().b(deviceInfo);
        if (HwVersionManager.c(BaseApplication.getContext()).i()) {
            if (!HwVersionManager.c(BaseApplication.getContext()).g()) {
                LogUtil.h("HwUpdateService", "requestPsiUpload isElectronicCard is false");
                kxz.d(deviceInfo.getDeviceIdentify(), "2", (Boolean) false);
            } else {
                kyf.a().c(deviceInfo, true, new PsiUploadResultCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.14
                    @Override // com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback
                    public void onSuccess(PsiUploadResponseInfo psiUploadResponseInfo) {
                        if (jds.c(psiUploadResponseInfo.getResultCode(), 10) == 0) {
                            jkk.d().d(deviceInfo.getDeviceIdentify(), true, false);
                            jfo.e().d(deviceInfo.getDeviceIdentify(), true);
                        } else {
                            jkk.d().d(deviceInfo.getDeviceIdentify(), false, false);
                            jfo.e().d(deviceInfo.getDeviceIdentify(), false);
                        }
                    }

                    @Override // com.huawei.hwversionmgr.utils.callback.PsiUploadResultCallback
                    public void onFailure(Throwable th) {
                        jkk.d().d(deviceInfo.getDeviceIdentify(), false, false);
                        jfo.e().d(deviceInfo.getDeviceIdentify(), false);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, String str) {
        if (this.b.size() == 0) {
            e(i2, str);
        }
    }

    private void b(int i2, DeviceInfo deviceInfo, boolean z) {
        String softVersion;
        LogUtil.a("R_OTA_HwUpdateService", "autoBandCheckNewVersion enter");
        if (!z) {
            kxz.d(deviceInfo.getDeviceIdentify(), w9.o, "true");
        }
        if (!jrd.e(deviceInfo.getProductType(), deviceInfo.getDeviceIdentify()) && !kyh.c(this.d)) {
            if (z) {
                softVersion = deviceInfo.getDeviceEnterpriseVersion();
            } else {
                softVersion = deviceInfo.getSoftVersion();
            }
            String str = softVersion;
            if (TextUtils.isEmpty(str)) {
                c(i2, deviceInfo.getDeviceIdentify(), false, true, "autoBandCheckNewVersion");
                n(i2, deviceInfo.getDeviceIdentify());
                return;
            } else if (z) {
                e(i2, deviceInfo, true, str);
                return;
            } else {
                jkk.d().a(deviceInfo.getDeviceIdentify(), 19);
                c(i2, deviceInfo, true, false, str);
                return;
            }
        }
        if (!HwVersionManager.c(BaseApplication.getContext()).i()) {
            kxz.d(deviceInfo.getDeviceIdentify(), "0", (Boolean) false);
        }
        n(i2, deviceInfo.getDeviceIdentify());
        f(i2, deviceInfo.getDeviceIdentify());
    }

    public void e(final int i2, final DeviceInfo deviceInfo, final boolean z, final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.12
            @Override // java.lang.Runnable
            public void run() {
                jrc.e().a(deviceInfo.getDeviceIdentify(), new HwLocationCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.12.3
                    @Override // com.huawei.hwdevice.outofprocess.callback.HwLocationCallback
                    public void onResult(String str2) {
                        LogUtil.a("HwUpdateService", "initLocation message = ", str2);
                        HwUpdateService.this.f.checkBandNewVersion(deviceInfo.getProductType(), str, deviceInfo.getSecurityDeviceId(), z, HwUpdateService.this.new a(i2, deviceInfo, Looper.getMainLooper(), z));
                    }
                });
            }
        });
    }

    private boolean g(int i2, String str, boolean z) {
        if (z && !jkv.b().e(str)) {
            return false;
        }
        synchronized (e) {
            this.b.clear();
        }
        b(i2, str, z, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i2, String str, boolean z, boolean z2) {
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            jkk.d().l(str);
            e(i2, "checkManualBandNewVersion deviceInfo is null");
        } else if (kxz.a(e2, 58)) {
            HwOtaUpgradeManager.a().a(str, false, new HwOtaUpgradeManager.ClearServiceListener() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.13
                @Override // com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.ClearServiceListener
                public void clearTask() {
                    HwUpdateService.this.e(i2, "requestWearNewVersion return isAuto is false");
                }
            });
        } else {
            c(i2, str, e2, z, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, String str, DeviceInfo deviceInfo, boolean z, boolean z2) {
        String softVersion;
        if (z2) {
            softVersion = deviceInfo.getDeviceEnterpriseVersion();
            jkj.d().d(str, deviceInfo.getDeviceEnterpriseVersion());
        } else {
            softVersion = deviceInfo.getSoftVersion();
            jkj.d().d(str, softVersion);
        }
        if (TextUtils.isEmpty(softVersion)) {
            c(i2, deviceInfo.getDeviceIdentify(), z, false, "checkManualDeviceVersion");
            return;
        }
        LogUtil.a("R_OTA_HwUpdateService", "checkManualDeviceVersion deviceType ", Integer.valueOf(deviceInfo.getProductType()), " isEnterprise ", Boolean.valueOf(z2), " isDeviceRequestCheck ", Boolean.valueOf(z), " deviceSoftVersion ", softVersion, " deviceEnterpriseVersion ", deviceInfo.getDeviceEnterpriseVersion(), " deviceBtMac ", iyl.d().e(deviceInfo.getSecurityDeviceId()), " startId ", Integer.valueOf(i2));
        if (z2) {
            b(i2, deviceInfo, z, true, softVersion);
        } else {
            jkk.d().a(str, 20);
            c(i2, deviceInfo, false, z, softVersion);
        }
    }

    public void b(final int i2, final DeviceInfo deviceInfo, final boolean z, final boolean z2, final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.19
            @Override // java.lang.Runnable
            public void run() {
                jrc.e().a(deviceInfo.getDeviceIdentify(), new HwLocationCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.19.2
                    @Override // com.huawei.hwdevice.outofprocess.callback.HwLocationCallback
                    public void onResult(String str2) {
                        LogUtil.a("HwUpdateService", "initLocation message = ", str2);
                        jkl jklVar = new jkl();
                        jklVar.c(i2);
                        jklVar.e(deviceInfo.getDeviceIdentify());
                        jklVar.a(z2);
                        jklVar.e(z);
                        kyh.c(deviceInfo, str, HwUpdateService.this.f, z2, HwUpdateService.this.new i(jklVar, Looper.getMainLooper()));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, String str, boolean z, boolean z2) {
        LogUtil.a("R_OTA_HwUpdateService", "enter startId ", Integer.valueOf(i2), " = isAuto = ", Boolean.valueOf(z2));
        d(i2, str, z, z2);
    }

    private void d(final int i2, final String str, final boolean z, final boolean z2) {
        LogUtil.a("HwUpdateService", "checkManualQueryEnterpriseId isAuto ", Boolean.valueOf(z2));
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            jkk.d().l(str);
            c(i2, str, z, z2, "checkManualQueryEnterpriseId");
            e(i2, "checkManualBandNewVersion deviceInfo is null");
            return;
        }
        kya.d().c(e2, new EnterpriseResultCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.17
            @Override // com.huawei.hwversionmgr.utils.callback.EnterpriseResultCallback
            public void onSuccess(EnterpriseResponseInfo enterpriseResponseInfo) {
                LogUtil.h("HwUpdateService", "EnterpriseUpdateUtil,resp resultCode:", enterpriseResponseInfo.getEnterprise());
                if (!TextUtils.isEmpty(enterpriseResponseInfo.getEnterprise())) {
                    kxz.a(enterpriseResponseInfo.getEnterprise(), str, HwUpdateService.this.d);
                    if (z2) {
                        HwUpdateService.this.a(i2, str, true);
                        return;
                    } else {
                        HwUpdateService.this.b(i2, str, z, true);
                        return;
                    }
                }
                HwUpdateService.this.c(i2, str, z, z2, "checkManualEnterpriseDeviceVersion onSuccess");
                kxz.b(kxz.c(), str, HwUpdateService.this.d);
                LogUtil.h("HwUpdateService", "enterpriseResponse is null");
            }

            @Override // com.huawei.hwversionmgr.utils.callback.EnterpriseResultCallback
            public void onFailure(Throwable th) {
                HwUpdateService.this.c(i2, str, z, z2, "checkManualEnterpriseDeviceVersion onFailure");
                kxz.b(kxz.c(), str, HwUpdateService.this.d);
                LogUtil.h("HwUpdateService", "EnterpriseUpdateUtil onFailure:", th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, String str, boolean z, boolean z2, String str2) {
        if (z2) {
            n(i2, str);
        }
        if (z) {
            jkk.d().k(str);
        }
        jkk.d().e(str, 0);
        a(i2, str, str2);
        if (z2) {
            return;
        }
        kyh.b(11, 0, "", "", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(int i2, String str) {
        synchronized (e) {
            if (this.b.size() > 0 && this.b.contains(str)) {
                this.b.remove(str);
            }
            if (this.b.size() > 0) {
                String str2 = this.b.get(0);
                d(str2);
                a(i2, str2, false);
            }
            LogUtil.a("HwUpdateService", "removeDeviceId deviceIdList.size() = ", Integer.valueOf(this.b.size()));
        }
    }

    private void c(final int i2, final DeviceInfo deviceInfo, final boolean z, final boolean z2, final String str) {
        if (!jkv.b(deviceInfo.getDeviceIdentify())) {
            if (z) {
                e(i2, deviceInfo, false, str);
                return;
            } else {
                b(i2, deviceInfo, z2, false, str);
                return;
            }
        }
        if (this.j == null) {
            LogUtil.h("HwUpdateService", "checkDevicePackageType mUpdateHandler == null");
            b();
        }
        Message obtainMessage = this.j.obtainMessage();
        obtainMessage.what = 3;
        obtainMessage.arg1 = i2;
        Bundle bundle = new Bundle();
        bundle.putString("extra_band_imei", str);
        bundle.putBoolean("is_device_request_check", z2);
        bundle.putBoolean("is_auto", z);
        bundle.putParcelable("device_info", deviceInfo);
        obtainMessage.obj = bundle;
        this.j.sendMessageDelayed(obtainMessage, 5000L);
        jkv.b().e(deviceInfo.getDeviceIdentify(), new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.16
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (!(obj instanceof Integer)) {
                    LogUtil.h("HwUpdateService", "checkDevicePackageType error");
                    return;
                }
                LogUtil.a("HwUpdateService", "checkDevicePackageType enter");
                if (HwUpdateService.this.j != null) {
                    HwUpdateService.this.j.removeMessages(3);
                }
                int intValue = ((Integer) obj).intValue();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("0x01", intValue);
                } catch (JSONException unused) {
                    LogUtil.b("HwUpdateService", "queryDevicePackageType response JSONException");
                }
                jrd.b(deviceInfo, "050922", "0", "", jSONObject.toString());
                if (intValue == 2) {
                    OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("HwUpdateService", "trans updatePackageType 2 to 0");
                    intValue = 0;
                }
                UpdateBase.c(intValue);
                if (z) {
                    HwUpdateService.this.e(i2, deviceInfo, false, str);
                } else {
                    HwUpdateService.this.b(i2, deviceInfo, z2, false, str);
                }
            }
        });
    }

    private boolean m(int i2, String str) {
        LogUtil.a("HwUpdateService", "userAgreeDownloadOta startId ", Integer.valueOf(i2));
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            jkv.b().d(str, 3);
            return false;
        }
        if (TextUtils.isEmpty(kxz.j(this.d, str))) {
            LogUtil.a("HwUpdateService", "userAgreeDownloadOta no new version");
            jkv.b().d(str, 255);
            return false;
        }
        int c2 = jkj.d().c(str);
        if (c2 == 3) {
            jkv.b().d(str, 1);
            return false;
        }
        if (c2 == 4 || c2 == 6) {
            jkv.b().d(str, 2);
            return false;
        }
        if (jkk.d().e()) {
            LogUtil.h("HwUpdateService", "userAgreeDownloadOta low battery");
            jkv.b().d(str, 4);
            return false;
        }
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 != null && HwVersionManager.c(this.d).c(str, e2.getSecurityDeviceId())) {
            jkv.b().d(str, 2);
            jrj.d(1800000L, "user-otaDownloadFile");
            jrs.d(BaseApplication.getContext());
            jkj.d().c(str, 0);
            return true;
        }
        LogUtil.a("HwUpdateService", "userAgreeDownloadOta start download");
        jkv.b().d(str, 0);
        e(i2, str, true);
        return true;
    }

    private boolean d(int i2, String str) {
        LogUtil.a("HwUpdateService", "deviceRequestDownloadOta enter startId ", Integer.valueOf(i2));
        boolean b2 = jkj.d().b(str);
        if (b2) {
            e(i2, str, true);
        }
        return b2;
    }

    private boolean a(int i2) {
        LogUtil.a("R_OTA_HwUpdateService", "changeLanguage enter startId ", Integer.valueOf(i2));
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwUpdateService");
        if (deviceList == null || deviceList.size() <= 0) {
            return true;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo == null) {
                e(i2, "changeLanguage enter deviceInfo == null");
            } else {
                a(i2, deviceInfo.getDeviceIdentify(), "changeLanguage enter");
            }
        }
        HwDeviceReplyPhraseEngineManager.e().b();
        return true;
    }

    public boolean b(int i2, String str, boolean z) {
        LogUtil.a("R_OTA_HwUpdateService", "startDownloadNewVersion enter");
        d(i2, str, z);
        return true;
    }

    private boolean bHS_(Intent intent, final int i2) {
        LogUtil.a("R_OTA_HwUpdateService", "startToActivate enter");
        final int intExtra = intent.getIntExtra("extra_band_type", -1);
        final String stringExtra = intent.getStringExtra("extra_band_version");
        final String stringExtra2 = intent.getStringExtra("extra_band_imei");
        if (intExtra == -1 || TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            LogUtil.h("R_OTA_HwUpdateService", "bandVersion or bandImei is invalid!");
            return false;
        }
        jrc.e().a(stringExtra2, new HwLocationCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.3
            @Override // com.huawei.hwdevice.outofprocess.callback.HwLocationCallback
            public void onResult(String str) {
                LogUtil.a("HwUpdateService", "initLocation message = ", str);
                HwUpdateService.this.a(intExtra, stringExtra, stringExtra2, i2);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, String str2) {
        if (!jkv.a(str)) {
            c(i2, str2 + "not support query changelog");
            return;
        }
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            c(i2, str2 + "deviceInfo == null");
            return;
        }
        String e3 = this.g.e(e2.getDeviceUdid() + "_" + e2.getSoftVersion() + "_changelog.xml");
        if (a(e3)) {
            String a2 = this.g.a(e2.getDeviceUdid() + "_" + e2.getSoftVersion() + "_changeLogUri");
            String e4 = kxw.e(e3, kxz.h(), kxz.f());
            kxz.d(kxw.d(e3, kxz.h(), kxz.f()), this.d, e2.getSoftVersion(), e2.getDeviceIdentify());
            String e5 = jkk.d().e(e4);
            if (jkv.a(str)) {
                d(e2, e5, a2);
            }
            c(i2, str2 + "hasChangeLog is true");
            return;
        }
        g(i2, str);
    }

    private void b() {
        if (this.f6293a == null) {
            HandlerThread handlerThread = new HandlerThread("HwUpdateService");
            this.f6293a = handlerThread;
            handlerThread.start();
        }
        this.j = new jks(this.f6293a.getLooper(), this);
    }

    private boolean l(final int i2, final String str) {
        LogUtil.a("R_OTA_HwUpdateService", "startAutoDownload enter");
        if (!jkk.d().n(str)) {
            return false;
        }
        jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("HwUpdateService", "startAutoDownload errorCode = ", Integer.valueOf(i3));
                boolean z = (i3 == 0 && (obj instanceof String)) ? !"2".equals((String) obj) : false;
                LogUtil.a("R_OTA_HwUpdateService", "startAutoDownload wlan isOpen ", Boolean.valueOf(z));
                if (z) {
                    HwUpdateService.this.b(i2, str);
                } else {
                    jkk.d().a(str, 15);
                    HwUpdateService.this.e(i2, "WLAN auto update close, return");
                }
            }
        });
        return true;
    }

    private void f(int i2, String str, boolean z) {
        LogUtil.a("R_OTA_HwUpdateService", "enter startBatteryCheck startId ", Integer.valueOf(i2));
        if (jkk.d().e()) {
            c(i2, str, z, 4, "startBandBatteryCheck");
        } else {
            e(i2, str, z);
        }
    }

    private void e(int i2, String str, boolean z) {
        if (jkj.d().c(str) == 3) {
            return;
        }
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            c(i2, str, z, 3, "deviceInfo == null");
            return;
        }
        kxl h2 = HwVersionManager.c(BaseApplication.getContext()).h(e2.getSecurityDeviceId());
        if (h2 == null) {
            c(i2, str, z, 3, "bandInfo == null");
            return;
        }
        jrj.d(1800000L, "user-otaDownloadFile");
        jrs.d(BaseApplication.getContext());
        kyh.c(19, 0, str);
        jkk.d().a(str, 18);
        jkk.d().b(str);
        String i3 = h2.i();
        String str2 = this.d.getFilesDir() + File.separator + h2.r();
        String s = h2.s();
        String h3 = h2.h();
        jdk.e(str, true);
        jdk jdkVar = new jdk(new jdk.d(str, e2.getSecurityDeviceId(), i3, str2, new c(i2, str, Looper.getMainLooper(), z)).d(3).a(s).c(h3).d(true));
        jdk.e(str, false);
        ThreadPoolManager.d().execute(jdkVar);
    }

    private void c(int i2, String str, boolean z, int i3, String str2) {
        if (!z) {
            kyh.c(22, i3, str);
        }
        jkk.d().d(str, 1, i3);
        e(i2, str2);
    }

    private void f(final int i2, final String str) {
        if (!jkk.d().n(str)) {
            c(i2, "isNeedTransfer OTA upgrade not allowed");
        } else {
            a(i2, str, "isNeedTransfer enter");
            jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    LogUtil.a("HwUpdateService", "isNeedTransfer errorCode = ", Integer.valueOf(i3));
                    boolean z = (i3 == 0 && (obj instanceof String)) ? !"2".equals((String) obj) : false;
                    LogUtil.a("HwUpdateService", "isNeedTransfer get auto ota checkbox status,isAutoupdate = ", Boolean.valueOf(z));
                    if (z) {
                        HwUpdateService.this.i(i2, str);
                    } else {
                        HwUpdateService.this.e(i2, "isNeedTransfer WLAN auto update close");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final int i2, final String str) {
        if (!jkk.d().n(str)) {
            e(i2, "handleAutoIsOpen isAllowedUpdate");
            return;
        }
        if (this.g == null) {
            this.g = HwVersionManager.c(this.d);
        }
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            e(i2, "handleAutoIsOpen deviceInfo == null");
            return;
        }
        if (!this.g.c(str, e2.getSecurityDeviceId())) {
            boolean a2 = jrd.a(str);
            LogUtil.a("HwUpdateService", "handleAutoIsOpen isAutoUpdateBand ", Boolean.valueOf(a2));
            if (a2 && !jrd.e()) {
                b(i2, str);
                return;
            } else {
                e(i2, "handleAutoIsOpen band new package is not exist");
                return;
            }
        }
        String j2 = this.g.j(str);
        if (TextUtils.equals(j2, e2.getSoftVersion())) {
            e(i2, "handleAutoIsOpen no new version");
        } else {
            LogUtil.a("HwUpdateService", "handleAutoIsOpen isNeedTransfer");
            jkv.b().b(str, j2, new ISilenceOTAAIDLCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.1
                @Override // com.huawei.hwservicesmgr.ISilenceOTAAIDLCallback
                public void onResponse(int i3, String str2) {
                    LogUtil.a("HwUpdateService", "errorcode = ", Integer.valueOf(i3));
                    if (i3 == 100000) {
                        if (!jkk.d().n(str)) {
                            HwUpdateService.this.e(i2, "handleAutoIsOpen isAllowedUpdate");
                            return;
                        } else {
                            if (jkk.d().e()) {
                                HwUpdateService.this.e(i2, "handleAutoIsOpen isAllowUpgrade");
                                return;
                            }
                            jrj.d(1800000L, "auto-deviceOta-downloadFile");
                            jrs.d(BaseApplication.getContext());
                            jkj.d().c(str, 2);
                            return;
                        }
                    }
                    HwUpdateService.this.e(i2, "handleAutoIsOpen errorCode is not success");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, String str2, int i3) {
        kxz.u(this.d, str2);
        this.f.checkBandNewVersion(i2, str, str2, false, new jkq(str2, i3, Looper.getMainLooper(), this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, kxj kxjVar, String str2) {
        LogUtil.a("R_OTA_HwUpdateService", "autoFetchChangeLogForBand enter");
        this.f.saveChangeLog(new b(i2, str, kxjVar, str2), null, str2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, kxj kxjVar, String str2, boolean z) {
        LogUtil.a("R_OTA_HwUpdateService", "manualFetchChangeLogForBand enter");
        if (!z) {
            kyh.c(30, -1, str);
        }
        jkl jklVar = new jkl();
        jklVar.c(i2);
        jklVar.e(str);
        jklVar.a(str2);
        jklVar.e(z);
        this.f.saveChangeLog(new j(jklVar, kxjVar), null, str2, false);
    }

    private void e(int i2, String str, String str2, String str3, boolean z) {
        LogUtil.a("R_OTA_HwUpdateService", "appFetchChangeLogForBand enter");
        jkl jklVar = new jkl();
        jklVar.c(i2);
        jklVar.e(str);
        jklVar.c(z);
        jklVar.a(str3);
        jklVar.b(str2);
        this.f.saveChangeLog(new d(jklVar, Looper.getMainLooper()), str2, str3, false);
    }

    private void d(int i2, String str, boolean z) {
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 != null && HwVersionManager.c(this.d).c(str, e2.getSecurityDeviceId())) {
            LogUtil.a("R_OTA_HwUpdateService", "downloadFile isUpgradeFileValid true");
            if (jkk.d().e()) {
                c(i2, str, z, 4, "downloadFile");
                return;
            }
            if (jkk.d().g(str) != null) {
                jkk.d().b(str);
                jrj.d(1800000L, "user-otaDownloadFile");
                jrs.d(BaseApplication.getContext());
                jkj.d().c(str, 0);
            }
            if (!z) {
                jkj.d().d(str, 0);
                kyh.c(23, 0, str);
            }
            e(i2, "downloadFile false");
            return;
        }
        c(i2, str, z);
    }

    private void a(DeviceInfo deviceInfo, int i2) {
        if (jkk.d().e()) {
            jkk.d().a(deviceInfo.getDeviceIdentify(), 17);
            c(i2, "autoDownload isAllowUpgrade");
            return;
        }
        if (HwVersionManager.c(this.d).c(deviceInfo.getDeviceIdentify(), deviceInfo.getSecurityDeviceId())) {
            jrj.d(1800000L, "auto-deviceOta-downloadFile");
            jrs.d(BaseApplication.getContext());
            jkj.d().c(deviceInfo.getDeviceIdentify(), 2);
            c(i2, "autoDownload startOtaFileQueryTransfer");
            return;
        }
        if (jkj.d().c(deviceInfo.getDeviceIdentify()) == 3) {
            return;
        }
        kxl h2 = HwVersionManager.c(BaseApplication.getContext()).h(deviceInfo.getSecurityDeviceId());
        if (TextUtils.isEmpty(h2.i()) || deviceInfo.getSoftVersion().equals(h2.v())) {
            c(i2, "autoDownload downloadUrl is null or version is last");
            return;
        }
        jrj.d(1800000L, "auto-deviceOta-downloadFile");
        jrs.d(BaseApplication.getContext());
        jkk.d().a(deviceInfo.getDeviceIdentify(), 6);
        jdk.e(deviceInfo.getDeviceIdentify(), true);
        kyh.c(19, 0, deviceInfo.getDeviceIdentify());
        String i3 = h2.i();
        String str = this.d.getFilesDir() + File.separator + h2.r();
        jdk jdkVar = new jdk(new jdk.d(deviceInfo.getDeviceIdentify(), deviceInfo.getSecurityDeviceId(), i3, str, new e(i2, deviceInfo.getDeviceIdentify(), Looper.getMainLooper())).d(3).a(h2.s()).c(h2.h()).d(true));
        jdk.e(deviceInfo.getDeviceIdentify(), false);
        ThreadPoolManager.d().execute(jdkVar);
    }

    private void c(int i2, String str, boolean z) {
        if (!z) {
            kyh.c(20, -1, str);
        }
        f(i2, str, z);
    }

    class a extends AppCheckNewVersionHandler {
        private DeviceInfo c;
        private int d;
        private boolean e;

        a(int i, DeviceInfo deviceInfo, Looper looper, boolean z) {
            super(looper);
            this.d = i;
            this.c = deviceInfo;
            this.e = z;
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
        public void handleCheckFailed(int i) {
            LogUtil.a("R_OTA_HwUpdateService", "AutoBandVersionHandler handleCheckFailed");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0x01", "failed");
                jSONObject.put("0x03", 1);
            } catch (JSONException unused) {
                LogUtil.b("HwUpdateService", "AutoBandVersionHandler handleCheckFailed JSONException");
            }
            jrd.b(this.c, "090001", "1", String.valueOf(i), jSONObject.toString());
            c(this.c.getDeviceIdentify(), i);
            jrj.b("auto-deviceOta-checkFile");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
        public void handleCheckSuccess(kxj kxjVar) {
            if (kxjVar != null) {
                LogUtil.a("HwUpdateService", "AutoBandVersionHandler handleCheckSuccess: versionInfo = ", kxjVar.toString());
                jkk.d().a(this.c.getDeviceIdentify(), kxjVar);
                LogUtil.a("R_OTA_HwUpdateService", "AutoBandVersionHandler handleAutoCheck success");
                jkk.d().b(this.c.getDeviceIdentify(), true);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("0x01", "success");
                    jSONObject.put("0x02", kxjVar.b());
                    jSONObject.put("0x03", 1);
                } catch (JSONException unused) {
                    LogUtil.b("HwUpdateService", "AutoBandVersionHandler handleCheckSuccess JSONException");
                }
                jrd.b(this.c, "090001", "1", "", jSONObject.toString());
                HwUpdateService.this.a(this.d, this.c.getDeviceIdentify(), kxjVar, BaseApplication.getContext().getFilesDir() + File.separator + "changeLog" + File.separator + this.c.getDeviceUdid() + File.separator + (kxjVar.i() + "_changelog.xml"));
                return;
            }
            HwUpdateService.this.e(this.d, "AutoBandVersionHandler handleCheckSuccess");
        }

        private void c(String str, int i) {
            if (i != 0) {
                HwUpdateService.this.n(this.d, this.c.getDeviceIdentify());
                HwUpdateService.this.a(this.d, str, "onBandAutoCheckFail");
                LogUtil.b("R_OTA_HwUpdateService", "AutoBandVersionHandler handleAutoCheckFailed() reason = others");
                jkk.d().b(this.c.getDeviceIdentify(), false);
                return;
            }
            LogUtil.a("R_OTA_HwUpdateService", "AutoBandVersionHandler handleAutoCheckFailed FAILED_REASON_NOT_FOUND");
            c(str);
            jkk.d().b(this.c.getDeviceIdentify(), true);
            kxz.d(HwUpdateService.this.d, str);
            kxz.j("", str, HwUpdateService.this.d);
            kxz.d("", str, HwUpdateService.this.d);
            kxz.e("", str, HwUpdateService.this.d);
        }

        private void c(String str) {
            if (!kxz.a(this.c, 97)) {
                kxz.b(kxz.c(), str, HwUpdateService.this.d);
                HwUpdateService.this.n(this.d, this.c.getDeviceIdentify());
                jrd.c(str, false);
                HwUpdateService.this.a(this.d, str, "checkQueryEnterpriseId");
                return;
            }
            if (this.e) {
                kxz.b(kxz.c(), str, HwUpdateService.this.d);
                HwUpdateService.this.n(this.d, this.c.getDeviceIdentify());
                jrd.c(str, false);
                HwUpdateService.this.a(this.d, str, "checkQueryEnterpriseId isEnterprise");
                return;
            }
            HwUpdateService.this.c(this.d, str, true, true);
        }
    }

    class i extends AppCheckNewVersionHandler {
        private jkl c;

        i(jkl jklVar, Looper looper) {
            super(looper);
            this.c = jklVar;
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
        public void handleCheckFailed(int i) {
            LogUtil.a("R_OTA_HwUpdateService", "ManualBandVersionHandler handleCheckFailed", Boolean.valueOf(this.c.h()));
            DeviceInfo e = jkj.d().e(this.c.d());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0x01", "failed");
                jSONObject.put("0x03", this.c.c() ? 2 : 0);
            } catch (JSONException unused) {
                LogUtil.b("HwUpdateService", "ManualBandVersionHandler handleCheckFailed JSONException");
            }
            jrd.b(e, "090001", "1", String.valueOf(i), jSONObject.toString());
            b(this.c.d(), i);
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler
        public void handleCheckSuccess(kxj kxjVar) {
            LogUtil.a("R_OTA_HwUpdateService", "ManualBandVersionHandler handleCheckSuccess", Boolean.valueOf(this.c.h()));
            if (kxjVar != null) {
                LogUtil.a("HwUpdateService", "handleManualCheckSuccess: CheckNewVersionCode = ", kxjVar.j(), " AppMinCode = ", Integer.valueOf(kxjVar.a()));
                jkk.d().a(this.c.d(), kxjVar);
                kyh.b(12, (int) kxjVar.c(), kxjVar.i(), "1", this.c.d());
                DeviceInfo e = jkj.d().e(this.c.d());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("0x01", "success");
                    jSONObject.put("0x02", kxjVar.b());
                    jSONObject.put("0x03", this.c.c() ? 2 : 0);
                } catch (JSONException unused) {
                    LogUtil.b("HwUpdateService", "ManualBandVersionHandler handleCheckSuccess JSONException");
                }
                jrd.b(e, "090001", "1", "", jSONObject.toString());
                String str = kxjVar.i() + "_changelog.xml";
                if (HwUpdateService.this.d == null || e == null) {
                    HwUpdateService.this.e(this.c.e(), "handleCheckSuccess (mContext == null) || (deviceInfo == null)");
                    return;
                }
                String str2 = HwUpdateService.this.d.getFilesDir() + File.separator + "changeLog" + File.separator + e.getDeviceUdid() + File.separator + str;
                LogUtil.a("HwUpdateService", "ManualBandVersionHandler handleCheckSuccess changeLogFilePath=", str2);
                HwUpdateService.this.a(this.c.e(), this.c.d(), kxjVar, str2, this.c.c());
                return;
            }
            HwUpdateService.this.e(this.c.e(), "ManualBandVersionHandler handleCheckSuccess else");
        }

        private void b(String str, int i) {
            if (i == 0) {
                LogUtil.a("R_OTA_HwUpdateService", "onBandManualCheckFail FAILED_REASON_NOT_FOUND", Boolean.valueOf(this.c.c()));
                d(str, i, jkj.d().e(str));
                return;
            }
            if (!this.c.c()) {
                kyh.b(11, i, "", "", str);
            } else {
                jkk.d().k(this.c.d());
            }
            jkk.d().e(this.c.d(), i);
            HwUpdateService.this.a(this.c.e(), str, "onBandManualCheckFail");
        }

        private void d(String str, int i, DeviceInfo deviceInfo) {
            if (!kxz.a(deviceInfo, 97)) {
                kxz.b(kxz.c(), str, HwUpdateService.this.d);
                if (!this.c.c()) {
                    kyh.b(11, i, "", "", str);
                } else {
                    jkk.d().k(this.c.d());
                }
                jrd.c(str, false);
                jkk.d().e(this.c.d(), i);
                HwUpdateService.this.a(this.c.e(), str, "checkManualEnterpriseId");
                return;
            }
            a(str, i);
        }

        private void a(String str, int i) {
            if (this.c.h()) {
                kxz.b(kxz.c(), str, HwUpdateService.this.d);
                if (!this.c.c()) {
                    kyh.b(11, i, "", "", str);
                } else {
                    jkk.d().k(this.c.d());
                }
                jrd.c(str, false);
                jkk.d().e(this.c.d(), i);
                HwUpdateService.this.a(this.c.e(), str, "checkSupportEnterpriseId");
                return;
            }
            HwUpdateService.this.c(this.c.e(), str, this.c.c(), false);
        }
    }

    private void g(final int i2, final String str) {
        jkv.b().b(str, HwVersionManager.c(BaseApplication.getContext()).b(str), kxz.f(), new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("HwUpdateService", "queryChangelogFromDevice errorCode = ", Integer.valueOf(i3));
                if (!(obj instanceof jkr)) {
                    HwUpdateService.this.c(i2, "queryChangelogFromDevice invalid");
                    return;
                }
                jkr jkrVar = (jkr) obj;
                int e2 = jkrVar.e();
                DeviceInfo e3 = jkj.d().e(str);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("0x03", e2);
                } catch (JSONException unused) {
                    LogUtil.b("HwUpdateService", "queryChangelogFromDevice JSONException");
                }
                jrd.b(e3, "050921", "0", String.valueOf(i3), jSONObject.toString());
                String f = jkrVar.f();
                if (e2 == 0) {
                    HwUpdateService.this.d(i2, str, f);
                    return;
                }
                if (e2 == 1) {
                    HwUpdateService.this.b(str);
                } else if (e2 == 2) {
                    HwUpdateService.this.c(i2, str, f);
                } else {
                    LogUtil.a("HwUpdateService", "queryChangelogFromDevice invalid deviceResult = ", Integer.valueOf(e2));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HwUpdateService", "queryChangelogFromDevice error, versionUrl = ", str2);
            return;
        }
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            LogUtil.h("HwUpdateService", "sendChangeLog deviceInfo == null");
            return;
        }
        e(i2, str, str2, BaseApplication.getContext().getFilesDir() + File.separator + "changeLog" + File.separator + e2.getDeviceUdid() + File.separator + (e2.getSoftVersion() + "_changelog.xml"), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        LogUtil.a("HwUpdateService", "sendChangeLogToDevice, app no have changeLog");
        jkr jkrVar = new jkr();
        jkrVar.b(HwVersionManager.c(BaseApplication.getContext()).b(str));
        jkrVar.a(0);
        jkv.b().d(str, jkrVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, String str, String str2) {
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            LogUtil.h("HwUpdateService", "sendChangeLogToDevice deviceInfo == null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("HwUpdateService", "queryChangelogFromDevice error, versionUrl = ", str2);
            return;
        }
        e(i2, str, str2, BaseApplication.getContext().getFilesDir() + File.separator + "changeLog" + File.separator + e2.getDeviceUdid() + File.separator + (e2.getSoftVersion() + "_changelog.xml"), false);
    }

    private boolean a(String str) {
        return FileUtils.getFile(str).exists();
    }

    private void d(DeviceInfo deviceInfo, String str, String str2) {
        if (jkv.a(deviceInfo.getDeviceIdentify())) {
            jkr jkrVar = new jkr();
            String b2 = HwVersionManager.c(BaseApplication.getContext()).b(deviceInfo.getDeviceIdentify());
            String f = kxz.f();
            jkrVar.e(str2);
            jkrVar.b(b2);
            jkrVar.a(1);
            jkrVar.a(f);
            jkrVar.d(str);
            jkv.b().d(deviceInfo.getDeviceIdentify(), jkrVar);
        }
    }

    class b extends AppPullChangeLogHandler {
        private kxj b;
        private String c;
        private int d;
        private String e;

        b(int i, String str, kxj kxjVar, String str2) {
            this.d = i;
            this.e = str;
            this.b = kxjVar;
            this.c = str2;
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogFailed() {
            LogUtil.a("R_OTA_HwUpdateService", "AutoBandPullChangeLogHandler pullChangeLogFailed");
            jrd.c(this.e, false);
            HwUpdateService.this.n(this.d, this.e);
            jrj.b("auto-deviceOta-checkFile");
            HwUpdateService.this.c(this.d, "AutoBandPullChangeLogHandler pullChangeLogFailed");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogSuccess(List<kxm> list) {
            LogUtil.a("R_OTA_HwUpdateService", "AutoBandPullChangeLogHandler pullChangeLogSuccess");
            jkk.d().a(this.e, this.c, this.b);
            LogUtil.a("HwUpdateService", "AutoBandPullChangeLogHandler BAND_AUTO_UPDATE");
            kxz.c(this.b.h(), this.e, HwUpdateService.this.d);
            HwUpdateService.this.n(this.d, this.e);
            HwUpdateService.this.j(this.d, this.e);
            jrd.c(this.e, true);
            kyh.d();
            jrj.b("auto-deviceOta-checkFile");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2, String str) {
        kxz.b(kxz.c(), str, this.d);
        a(i2, str, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, boolean z, boolean z2) {
        String b2 = HwVersionManager.c(BaseApplication.getContext()).b(str);
        String j2 = HwVersionManager.c(BaseApplication.getContext()).j(str);
        LogUtil.a("HwUpdateService", "bandCurrentVersion = ", b2, " bandNewVersion ", j2);
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(j2)) {
            c(i2, "version is null");
            return;
        }
        DeviceCapability e2 = cvs.e(str);
        if (b2.equals(j2)) {
            if (z) {
                jkk.d().k(str);
            }
            c(i2, "pullChangeLogSuccess No new version");
            return;
        }
        e(i2, str, z, z2, e2);
    }

    private void e(final int i2, String str, boolean z, boolean z2, DeviceCapability deviceCapability) {
        String f = kxz.f(this.d, str);
        if (z || z2) {
            if (deviceCapability == null || !deviceCapability.getIsSupportNotifyDeviceNewVersion()) {
                return;
            }
            jkv.b().e(jkk.d().e(str, f), f, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i3, Object obj) {
                    HwUpdateService.this.c(i2, "checkPackageAlreadyExists errorCode = " + i3);
                }
            });
            return;
        }
        if (deviceCapability != null && deviceCapability.getIsSupportNotifyDeviceNewVersion()) {
            c(1, i2, str);
            b(i2, str, f);
        } else {
            LogUtil.a("HwUpdateService", "sendDeviceNewVersionMessage not getIsSupportNotifyDeviceNewVersion()");
            a(i2, str);
        }
    }

    private void b(final int i2, final String str, String str2) {
        jkv.b().e(jkk.d().e(str, str2), str2, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("HwUpdateService", "sendDeviceNewVersionMessage errorCode = ", Integer.valueOf(i3));
                if (HwUpdateService.this.j != null) {
                    HwUpdateService.this.j.removeMessages(1);
                }
                if (i3 == 109023 || i3 == 109028) {
                    HwUpdateService.this.c(i2, "sendDeviceNewVersionMessage Not Upgraded");
                } else {
                    if (i3 == 109024) {
                        jkj.d().a(str, 1);
                        jkj.d().i(str);
                        HwUpdateService.this.c(i2, true, str);
                        return;
                    }
                    HwUpdateService.this.a(i2, str);
                }
            }
        });
    }

    public void a(int i2, String str) {
        a(HwVersionManager.c(BaseApplication.getContext()).j(str), i2, str);
        jkj.d().a(str, 1);
        jkj.d().i(str);
    }

    private void a(String str, final int i2, final String str2) {
        LogUtil.a("HwUpdateService", "isSupportSilence enter");
        jkv.b().b(str2, str, new ISilenceOTAAIDLCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.8
            @Override // com.huawei.hwservicesmgr.ISilenceOTAAIDLCallback
            public void onResponse(int i3, String str3) {
                LogUtil.a("HwUpdateService", "isSupportSilence errorCode = ", Integer.valueOf(i3));
                if (i3 != 100000 && i3 != 109002) {
                    HwUpdateService.this.c(i2, false, str2);
                } else if (!jkk.d().n(str2)) {
                    HwUpdateService.this.c(i2, "isSupportSilence isAllowedUpdate");
                } else {
                    HwUpdateService.this.h(i2, str2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(final int i2, final String str) {
        jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("HwUpdateService", "handleBandSilenceOtaUpgrade errorCode = ", Integer.valueOf(i3));
                boolean z = (i3 == 0 && (obj instanceof String)) ? !"2".equals((String) obj) : false;
                LogUtil.a("HwUpdateService", "get auto ota checkbox status,isAutoupdate: ", Boolean.valueOf(z));
                if (!z) {
                    HwUpdateService.this.c(i2, true, str);
                } else if (!jrd.e()) {
                    HwUpdateService.this.b(i2, str);
                } else {
                    HwUpdateService.this.c(i2, true, str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, String str) {
        if (!jkk.d().n(str)) {
            jkk.d().a(str, 16);
            c(i2, "checkAutoDownload isAllowedUpdate");
            return;
        }
        DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            c(i2, "startAutoDownload deviceInfo == null");
        } else if (kxz.p(this.d, str)) {
            a(e2, i2);
        } else {
            c(i2, true, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, boolean z, String str) {
        if (!jkk.d().n(str)) {
            c(i2, "showBandAutoCheckDialog isAllowedUpdate");
            return;
        }
        if (CommonUtil.x(this.d) || jrd.b()) {
            jkk.d().d(z, str);
            c(i2, "showBandAutoCheckDialog isBackground");
        } else if (kwx.c()) {
            LogUtil.a("HwUpdateService", "HwUpdateService showBandAutoCheckDialog is Sporting");
        } else {
            jkk.d().d(this.d, z, str);
            c(i2, "showBandAutoCheckDialog end");
        }
    }

    class j extends AppPullChangeLogHandler {

        /* renamed from: a, reason: collision with root package name */
        private boolean f6311a;
        private kxj b;
        private jkl e;

        j(jkl jklVar, kxj kxjVar) {
            this.b = kxjVar;
            this.e = jklVar;
            this.f6311a = jkk.d().d(jklVar.d()) != null;
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogFailed() {
            LogUtil.b("R_OTA_HwUpdateService", "ManualBandPullChangeLogHandler pullChangeLogFailed");
            jrd.c(this.e.d(), false);
            if (!this.e.c() && !this.f6311a) {
                kyh.c(31, -1, this.e.d());
            }
            if (this.e.c()) {
                jkk.d().k(this.e.d());
            }
            jkk.d().e(this.e.d(), 12);
            HwUpdateService.this.e(this.e.e(), "ManualBandPullChangeLogHandler pullChangeLogFailed");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogSuccess(List<kxm> list) {
            LogUtil.a("R_OTA_HwUpdateService", "ManualBandPullChangeLogHandler pullChangeLogSuccess");
            jkk.d().a(this.e.d(), this.e.a(), this.b);
            kxz.c(this.b.h(), this.e.d(), HwUpdateService.this.d);
            kxz.b(kxz.c(), this.e.d(), HwUpdateService.this.d);
            if (this.e.c() || this.f6311a) {
                HwUpdateService.this.a(this.e.e(), this.e.d(), this.e.c(), this.f6311a);
            } else {
                kyh.b(32, 0, "", this.b.d(), this.e.d());
                HwUpdateService.this.e(this.e.e(), "pullChangeLogSuccess !isDeviceRequestCheck");
            }
            jkk.d().a(this.e.d());
            jrd.c(this.e.d(), true);
            kyh.d();
        }
    }

    class e extends AppDownloadHandler {

        /* renamed from: a, reason: collision with root package name */
        private String f6308a;
        private int c;
        private int e;

        e(int i, String str, Looper looper) {
            super(looper);
            this.e = i;
            this.f6308a = str;
            DeviceCapability e = cvs.e(str);
            if (e != null && e.getIsSupportDeviceRequestCheck()) {
                jkv.b().c(str, 0, 1, 0);
            }
            jkj.d().d(str, 3);
            jkk.d().d(str, 0);
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doInDownloadProgress(kxf kxfVar) {
            LogUtil.a("HwUpdateService", "AutoAppDownloadHandler doInDownloadProgress total=", Long.valueOf(kxfVar.c()), ",CurrentProgress=", Long.valueOf(kxfVar.e()), " startId ", Integer.valueOf(this.e));
            this.c = (int) kxfVar.e();
            if (jkj.d().c(this.f6308a) != 3) {
                jkj.d().d(this.f6308a, 3);
            }
            DeviceCapability e = cvs.e(this.f6308a);
            if (e != null && e.getIsSupportDeviceRequestCheck()) {
                jkv.b().c(this.f6308a, this.c, 1, 1);
            }
            kyh.c(21, this.c, this.f6308a);
            jkk.d().b(this.c, this.f6308a, 1);
            jkk.d().d(this.f6308a, this.c);
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadFailed(int i) {
            LogUtil.b("R_OTA_HwUpdateService", "AutoAppDownloadHandler: doDownloadFailed");
            jkk.d().a(this.f6308a, i);
            jkj.d().d(this.f6308a, 2);
            jkk.d().d(5, this.f6308a, i, "");
            jkk.d().d(this.f6308a, (IUpgradeStatusCallBack) null);
            DeviceCapability e = cvs.e(this.f6308a);
            jkk.d().d(this.f6308a, 0);
            if (e != null && e.getIsSupportDeviceRequestCheck()) {
                jkv.b().c(this.f6308a, this.c, 1, 2);
            }
            kyh.c(22, i, this.f6308a);
            jrj.b("auto-deviceOta-downloadFile");
            jrs.b();
            HwUpdateService.this.e(this.e, "AutoAppDownloadHandler doDownloadFailed");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadSuccess(kxf kxfVar) {
            if (kxfVar == null) {
                jkk.d().d(5, this.f6308a, 3, "");
                HwUpdateService.this.e(this.e, "AutoAppDownloadHandler doDownloadSuccess downloadInfo == null");
                return;
            }
            jkk.d().a(this.f6308a, 8);
            HwUpdateService.this.g.g(this.f6308a, kxfVar.a());
            LogUtil.a("R_OTA_HwUpdateService", "AutoAppDownloadHandler: doDownloadSuccess");
            jkk.d().b(100, this.f6308a, 2);
            jkk.d().d(this.f6308a, 100);
            jkj.d().d(this.f6308a, 0);
            kyh.c(23, 0, this.f6308a);
            if (!kxz.o(HwUpdateService.this.d)) {
                if (jkk.d().e()) {
                    HwUpdateService.this.e(this.e, "AutoAppDownloadHandler doDownloadSuccess isAllowUpgrade is true");
                    return;
                }
                jkj.d().c(this.f6308a, 2);
            }
            HwUpdateService.this.e(this.e, "AutoAppDownloadHandler doDownloadSuccess");
        }
    }

    class c extends AppDownloadHandler {
        private String b;
        private int c;
        private boolean d;
        private int e;

        c(int i, String str, Looper looper, boolean z) {
            super(looper);
            this.c = i;
            this.b = str;
            this.d = z;
            DeviceCapability e = cvs.e(str);
            if (e != null && e.getIsSupportDeviceRequestCheck()) {
                jkv.b().c(str, 0, 0, 0);
            }
            jkj.d().d(str, 3);
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadFailed(int i) {
            jkk.d().c(this.b, i);
            LogUtil.b("R_OTA_HwUpdateService", "ManualBandDownloadHandler: doDownloadFailed");
            kyh.c(22, i, this.b);
            DeviceCapability e = cvs.e(this.b);
            if (e != null && e.getIsSupportDeviceRequestCheck()) {
                jkv.b().c(this.b, this.e, 0, 2);
            }
            if (jkj.d().c(this.b) == 3) {
                jkj.d().d(this.b, 2);
            }
            jkk.d().d(5, this.b, i, "");
            jkk.d().d(this.b, (IUpgradeStatusCallBack) null);
            jkk.d().d(this.b, 0);
            jrj.b("user-otaDownloadFile");
            jrs.b();
            HwUpdateService.this.e(this.c, "ManualBandDownloadHandler doDownloadFailed");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadSuccess(kxf kxfVar) {
            LogUtil.a("R_OTA_HwUpdateService", "ManualBandDownloadHandler: doDownloadSuccess");
            jkk.d().c(this.b, 1);
            if (kxfVar == null) {
                HwUpdateService.this.e(this.c, "ManualBandDownloadHandler doDownloadSuccess appDownloadInfo == null");
                return;
            }
            HwUpdateService.this.g.g(this.b, kxfVar.a());
            jkj.d().d(this.b, 0);
            kyh.c(23, 0, this.b);
            if (!kxz.o(HwUpdateService.this.d)) {
                IUpgradeStatusCallBack h = jkk.d().h(this.b);
                if (jkk.d().e()) {
                    if (h != null) {
                        jkk.d().d(2, this.b, 4, "");
                    } else if (this.d) {
                        jkv.b().d(this.b, 4);
                    } else {
                        LogUtil.a("HwUpdateService", "ManualBandDownloadHandler: doDownloadSuccess isAllowUpgrade is true");
                    }
                    HwUpdateService.this.e(this.c, "ManualBandDownloadHandler: doDownloadSuccess isAllowUpgrade");
                    return;
                }
                if (this.d || h != null) {
                    LogUtil.a("HwUpdateService", "ManualBandDownloadHandler: doDownloadSuccess upgradeStatusCallBack != null");
                    jkj.d().c(this.b, 0);
                } else {
                    jkj.d().c(this.b, 2);
                }
            }
            jkk.d().b(100, this.b, 2);
            jkk.d().d(this.b, 100);
            HwUpdateService.this.e(this.c, "ManualBandDownloadHandler doDownloadSuccess");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doInDownloadProgress(kxf kxfVar) {
            if (kxfVar == null) {
                return;
            }
            LogUtil.a("HwUpdateService", "ManualBandDownloadHandler doInDownloadProgress total=", Long.valueOf(kxfVar.c()), ",CurrentProgress=", Long.valueOf(kxfVar.e()), " startId ", Integer.valueOf(this.c));
            int e = (int) kxfVar.e();
            this.e = e;
            kyh.c(21, e, this.b);
            if (jkj.d().c(this.b) != 3) {
                jkj.d().d(this.b, 3);
            }
            DeviceCapability e2 = cvs.e(this.b);
            if (e2 != null && e2.getIsSupportDeviceRequestCheck()) {
                jkv.b().c(this.b, this.e, 0, 1);
            }
            jkk.d().b(this.e, this.b, 1);
            jkk.d().d(this.b, this.e);
        }
    }

    class d extends AppPullChangeLogHandler {
        private jkl b;

        d(jkl jklVar, Looper looper) {
            super(looper);
            this.b = jklVar;
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogFailed() {
            HwUpdateService.this.e(this.b.e(), "AppBandPullChangeLogHandler pullChangeLogFailed");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler
        public void pullChangeLogSuccess(List<kxm> list) {
            jkk.d().a(this.b.d(), this.b.b(), this.b.a(), this.b.f());
            HwUpdateService.this.e(this.b.e(), "AppBandPullChangeLogHandler pullChangeLogSuccess");
        }
    }

    class h extends IWearHwUpdateServiceAIDL.Stub {
        private h() {
        }

        @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
        public void getConnectedDevices(IDeviceListCallback iDeviceListCallback) throws RemoteException {
            LogUtil.a("HwUpdateService", "getConnectedDevices enter");
            jkk.d().a(iDeviceListCallback);
        }

        @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
        public void getDeviceNewVersion(String str, ICheckCallback iCheckCallback) throws RemoteException {
            jkk.d().b(str, (ICheckCallback) null);
            LogUtil.a("HwUpdateService", "getDeviceNewVersion uuid", CommonUtil.l(str));
            DeviceInfo j = jkk.d().j(str);
            if (j == null) {
                jkk.d().d(str, iCheckCallback);
            } else if (HwOtaUpgradeManager.a().a(str)) {
                HwOtaUpgradeManager.a().a(str, j, iCheckCallback);
            } else {
                jkk.d().b(str, iCheckCallback);
                HwUpdateService.this.c(-1, j.getDeviceIdentify(), j, false, false);
            }
        }

        @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
        public void doUpgrade(String str, String str2, IUpgradeCallBack iUpgradeCallBack) throws RemoteException {
            final DeviceInfo j = jkk.d().j(str);
            if (j == null) {
                jkk.d().d(str, iUpgradeCallBack);
                return;
            }
            if (HwOtaUpgradeManager.a().a(str)) {
                HwOtaUpgradeManager.a().d(str, j, str2, iUpgradeCallBack);
                return;
            }
            if (jkk.d().e(str, str2, iUpgradeCallBack, j)) {
                return;
            }
            DeviceCapability e = cvs.e(j.getDeviceIdentify());
            LogUtil.a("HwUpdateService", "doUpgrade: deviceCapability.getIsSupportNotifyDeviceNewVersion() = ", Boolean.valueOf(e.getIsSupportNotifyDeviceNewVersion()));
            if (e != null && e.getIsSupportNotifyDeviceNewVersion()) {
                HwUpdateService.this.c(2, -1, j.getDeviceIdentify());
                String f = kxz.f(HwUpdateService.this.d, j.getDeviceIdentify());
                jkv.b().e(jkk.d().e(j.getDeviceIdentify(), f), f, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService.h.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        h.this.b(i, j);
                    }
                });
                return;
            }
            d(j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i, DeviceInfo deviceInfo) {
            LogUtil.a("HwUpdateService", "doUpgrade errorCode = ", Integer.valueOf(i));
            if (HwUpdateService.this.j != null) {
                HwUpdateService.this.j.removeMessages(2);
            }
            if (i == 109023) {
                jkk.d().d(deviceInfo.getDeviceIdentify(), 0, 0);
                return;
            }
            if (i == 109024) {
                if (jkk.d().g(deviceInfo.getDeviceIdentify()) != null) {
                    jkk.d().b(deviceInfo.getDeviceIdentify());
                }
                jkj.d().c(deviceInfo.getDeviceIdentify(), HwVersionManager.c(BaseApplication.getContext()).j(deviceInfo.getDeviceIdentify()), "is_package_already_exists", (IOTAResultAIDLCallback.Stub) null);
                return;
            }
            d(deviceInfo);
        }

        private void d(DeviceInfo deviceInfo) {
            jkj.d().d(deviceInfo.getDeviceIdentify());
            jkj.d().f();
            HwUpdateService.this.b(-1, deviceInfo.getDeviceIdentify(), true);
        }

        @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
        public void getUpgradeStatus(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) throws RemoteException {
            LogUtil.a("HwUpdateService", "getUpgradeStatus uuid ", CommonUtil.l(str));
            if (HwOtaUpgradeManager.a().a(str)) {
                HwOtaUpgradeManager.a().b(str, iUpgradeStatusCallBack);
            } else {
                jkk.d().b(str, iUpgradeStatusCallBack);
            }
        }

        @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
        public void registerUpgradeListener(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) throws RemoteException {
            LogUtil.a("HwUpdateService", "registerUpgradeListener uuid ", CommonUtil.l(str));
            if (HwOtaUpgradeManager.a().a(str)) {
                HwOtaUpgradeManager.a().c(str, iUpgradeStatusCallBack);
            } else {
                jkk.d().a(str, iUpgradeStatusCallBack);
            }
        }

        @Override // com.huawei.hwotamanager.IWearHwUpdateServiceAIDL
        public void unRegisterUpgradeListener(String str) throws RemoteException {
            LogUtil.a("HwUpdateService", "unRegisterUpgradeListener uuid ", CommonUtil.l(str));
            if (HwOtaUpgradeManager.a().a(str)) {
                HwOtaUpgradeManager.a().c(str);
                HwUpdateService.this.stopSelf();
            } else {
                jkk.d().d(str, (IUpgradeStatusCallBack) null);
                HwUpdateService.this.stopSelf();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, int i3, String str) {
        Handler handler = this.j;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i2;
            obtainMessage.arg1 = i3;
            new Bundle().putString("extra_band_imei", str);
            this.j.sendMessageDelayed(obtainMessage, 5000L);
        }
    }
}
