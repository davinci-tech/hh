package com.huawei.hwdevice.mainprocess.mgr.hwotamanager;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback;
import com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.bwf;
import defpackage.cwi;
import defpackage.jfq;
import defpackage.jkj;
import defpackage.jkk;
import defpackage.jkm;
import defpackage.jko;
import defpackage.jkv;
import defpackage.jqi;
import defpackage.jqj;
import defpackage.jrd;
import defpackage.kwx;
import defpackage.kxz;
import defpackage.kye;
import defpackage.kyh;
import defpackage.lsp;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwOtaUpgradeManager {
    private static final Object b = new Object();
    private static HwOtaUpgradeManager e;

    /* renamed from: a, reason: collision with root package name */
    private boolean f6276a = false;
    private Handler c = new Handler(Looper.getMainLooper()) { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("HwOtaUpgradeManager", "mHandle msg is null");
                return;
            }
            LogUtil.a("HwOtaUpgradeManager", "handleMessage what:", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                HwOtaUpgradeManager.this.f6276a = false;
                return;
            }
            if (i == 1 || i == 2 || i == 3) {
                HwOtaUpgradeManager.this.bHM_(message);
            } else if (i == 4) {
                HwOtaUpgradeManager.this.bHN_(message);
            } else {
                LogUtil.h("HwOtaUpgradeManager", "handleMessage what:", Integer.valueOf(message.what));
            }
        }
    };
    private Context d;

    public interface ClearServiceListener {
        void clearTask();
    }

    public interface RefreshLittleRedListener {
        void refreshLittleRed();
    }

    private HwOtaUpgradeManager(Context context) {
        this.d = context;
    }

    public static HwOtaUpgradeManager a() {
        HwOtaUpgradeManager hwOtaUpgradeManager;
        synchronized (b) {
            if (e == null) {
                e = new HwOtaUpgradeManager(BaseApplication.getContext());
            }
            hwOtaUpgradeManager = e;
        }
        return hwOtaUpgradeManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bHM_(Message message) {
        String str;
        int i = message.what;
        ClearServiceListener clearServiceListener = null;
        if (message.obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) message.obj;
            str = jSONObject.optString("mac");
            if (jSONObject.opt("listener") instanceof ClearServiceListener) {
                clearServiceListener = (ClearServiceListener) jSONObject.opt("listener");
            }
        } else {
            str = "";
        }
        e(clearServiceListener);
        int i2 = message.arg1;
        if (i == 1) {
            e(str);
        }
        if (i == 3 && i2 == 1) {
            kyh.b(11, 12, "", "", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bHN_(Message message) {
        if (bwf.a().isPluginAvaiable()) {
            LogUtil.h("HwOtaUpgradeManager", "processPluginLoadTimeout PluginWearOtaProxy isPluginAvaiable");
            return;
        }
        if (message == null || !(message.obj instanceof String)) {
            LogUtil.h("HwOtaUpgradeManager", "processPluginLoadTimeout msg is null");
            return;
        }
        String str = (String) message.obj;
        lsp.d().d("PluginWearAbility");
        kxz.b(kxz.c(), str, BaseApplication.getContext());
    }

    public void d(final String str, final InitCallback initCallback) {
        if (lsp.d().a("PluginWearAbility")) {
            LogUtil.h("HwOtaUpgradeManager", "initDueSdk PluginWearOtaProxy is no download");
        } else {
            jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    boolean z = (i == 0 && (obj instanceof String)) ? !"2".equals((String) obj) : false;
                    LogUtil.a("HwOtaUpgradeManager", "initDueSdk getSwitchSetting Code：", Integer.valueOf(i), ", isOpen:", Boolean.valueOf(z));
                    bwf.a().initDueSdk(str, String.valueOf(z), initCallback);
                }
            });
        }
    }

    public void d(final DeviceInfo deviceInfo, final RefreshLittleRedListener refreshLittleRedListener) {
        bwf.a().getNewVersion(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.15
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
            public void complete(int i, String str) {
                HwOtaUpgradeManager.this.a(i, str, deviceInfo);
                kye.a();
                RefreshLittleRedListener refreshLittleRedListener2 = refreshLittleRedListener;
                if (refreshLittleRedListener2 != null) {
                    refreshLittleRedListener2.refreshLittleRed();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public jkm a(int i, String str, DeviceInfo deviceInfo) {
        kxz.d("", deviceInfo.getDeviceIdentify(), this.d);
        jkm jkmVar = null;
        if (i != 0 || TextUtils.isEmpty(str)) {
            LogUtil.h("HwOtaUpgradeManager", "checkNewVersion newVersionInfo isEmpty or code is ", Integer.valueOf(i));
            return null;
        }
        try {
            jkm jkmVar2 = new jkm();
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i2 = jSONObject.getInt("status");
                jkmVar2.a(i2);
                int i3 = 0;
                if (i2 != 0) {
                    LogUtil.h("HwOtaUpgradeManager", "processVersionInfo not has new version, status:", Integer.valueOf(i2));
                    return jkmVar2;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("checkResults");
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    return jkmVar2;
                }
                JSONObject jSONObject2 = optJSONArray.getJSONObject(0);
                String string = jSONObject2.getString("displayVerName");
                kxz.d(string, deviceInfo.getDeviceIdentify(), this.d);
                jkmVar2.c(string);
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("upgradePackageList");
                if (optJSONArray2 != null) {
                    for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                        i3 = (int) (i3 + optJSONArray2.optJSONObject(i4).optLong("size"));
                    }
                }
                jkmVar2.b(i3);
                return jkmVar2;
            } catch (JSONException unused) {
                jkmVar = jkmVar2;
                LogUtil.b("HwOtaUpgradeManager", "processVersionInfo JSONException");
                return jkmVar;
            }
        } catch (JSONException unused2) {
        }
    }

    public void d(String str, String str2) {
        if (!bwf.a().isPluginAvaiable()) {
            KeyValDbManager.b(BaseApplication.getContext()).e("is_set_wifi_switch", "false");
            LogUtil.a("HwOtaUpgradeManager", "setSettingSwitch setValue IS_SET_WIFI_SWITCH");
        }
        bwf.a().setSettingSwitch(str, str2);
    }

    public void c(final DeviceInfo deviceInfo, final ClearServiceListener clearServiceListener) {
        if (this.f6276a) {
            this.c.removeMessages(0);
            this.c.sendEmptyMessageDelayed(0, OpAnalyticsConstants.H5_LOADING_DELAY);
            LogUtil.h("HwOtaUpgradeManager", "silentUpgrade mIsChecking is true");
            e(clearServiceListener);
            return;
        }
        final boolean e2 = jrd.e(deviceInfo.getDeviceUdid());
        final boolean a2 = kxz.a(kxz.c(this.d, deviceInfo.getDeviceIdentify()), deviceInfo.getProductType());
        LogUtil.a("HwOtaUpgradeManager", "silentUpgrade isAlreadyCheck ", Boolean.valueOf(a2), " isHasNewVersion ", Boolean.valueOf(e2));
        if (a2 && !e2) {
            e(clearServiceListener);
        } else {
            ThreadPoolManager.d().d("HwOtaUpgradeManager", new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.12
                @Override // java.lang.Runnable
                public void run() {
                    HwOtaUpgradeManager.this.c.removeMessages(4);
                    Message obtainMessage = HwOtaUpgradeManager.this.c.obtainMessage();
                    obtainMessage.what = 4;
                    obtainMessage.obj = deviceInfo.getDeviceIdentify();
                    HwOtaUpgradeManager.this.c.sendMessageDelayed(obtainMessage, 20000L);
                    bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.12.1
                        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                        public boolean call(Context context, Intent intent) {
                            HwOtaUpgradeManager.this.c.removeMessages(4);
                            HwOtaUpgradeManager.this.c(deviceInfo, clearServiceListener, e2, a2);
                            return false;
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final DeviceInfo deviceInfo, final ClearServiceListener clearServiceListener, final boolean z, final boolean z2) {
        d(deviceInfo.getDeviceUdid(), new InitCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.13
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
            public void onInitComplete(int i) {
                if (i == 0) {
                    HwOtaUpgradeManager.this.f6276a = true;
                    HwOtaUpgradeManager.this.c.sendEmptyMessageDelayed(0, OpAnalyticsConstants.H5_LOADING_DELAY);
                    if (!z) {
                        HwOtaUpgradeManager.this.d(deviceInfo, clearServiceListener);
                        return;
                    } else {
                        bwf.a().getSettingSwitch(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.13.5
                            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
                            public void complete(int i2, String str) {
                                HwOtaUpgradeManager.this.c.removeMessages(0);
                                LogUtil.a("HwOtaUpgradeManager", "requestNewVersion code:", Integer.valueOf(i2), ", wifiSwitch:", str);
                                jqi.a().setSwitchSetting("wlan_auto_update", "1".equals(str) ? "1" : "2", null);
                                if (!z2 || ("1".equals(str) && !jrd.e())) {
                                    HwOtaUpgradeManager.this.c.sendEmptyMessageDelayed(0, OpAnalyticsConstants.H5_LOADING_DELAY);
                                    HwOtaUpgradeManager.this.d(deviceInfo, clearServiceListener);
                                } else {
                                    LogUtil.h("HwOtaUpgradeManager", "requestNewVersion isHasNewVersion return");
                                }
                            }
                        });
                        return;
                    }
                }
                LogUtil.h("HwOtaUpgradeManager", "silentUpgrade onInitComplete code:", Integer.valueOf(i));
                HwOtaUpgradeManager.this.e(clearServiceListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final DeviceInfo deviceInfo, final ClearServiceListener clearServiceListener) {
        bwf.a().silentUpgrade(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.17
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
            public void complete(int i, String str) {
                HwOtaUpgradeManager.this.c.removeMessages(0);
                jkm a2 = HwOtaUpgradeManager.this.a(i, str, deviceInfo);
                HwOtaUpgradeManager.this.f6276a = false;
                kxz.b(kxz.c(), deviceInfo.getDeviceIdentify(), HwOtaUpgradeManager.this.d);
                if (a2 != null) {
                    HwOtaUpgradeManager.this.e(deviceInfo, a2);
                }
                HwOtaUpgradeManager.this.e(clearServiceListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ClearServiceListener clearServiceListener) {
        if (clearServiceListener != null) {
            clearServiceListener.clearTask();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final DeviceInfo deviceInfo, final jkm jkmVar) {
        boolean n = HwVersionManager.c(this.d).n(deviceInfo.getDeviceIdentify());
        if (jkmVar.a() != 0 || !n) {
            LogUtil.h("HwOtaUpgradeManager", "handleBandSilenceOtaUpgrade not has new version");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        jkj.d().a(deviceIdentify, 1);
        jkj.d().i(deviceIdentify);
        bwf.a().getSettingSwitch(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.20
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
            public void complete(int i, String str) {
                LogUtil.a("HwOtaUpgradeManager", "getSettingSwitch onVersionInfoObtain code:", Integer.valueOf(i), ", wifiSwitch:", str);
                jqi.a().setSwitchSetting("wlan_auto_update", "1".equals(str) ? "1" : "2", null);
                if (!"1".equals(str) || jrd.e()) {
                    HwOtaUpgradeManager.this.c(deviceInfo.getDeviceIdentify(), jkmVar);
                } else {
                    LogUtil.h("HwOtaUpgradeManager", "handleBandSilenceOtaUpgrade sdk start ota");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, jkm jkmVar) {
        LogUtil.a("HwOtaUpgradeManager", "enter showBandAutoCheckDialog");
        String c = jkmVar.c();
        int d = jkmVar.d();
        String b2 = jkmVar.b();
        if (CommonUtil.x(this.d) || jrd.f()) {
            LogUtil.a("HwOtaUpgradeManager", "showBandAutoCheckDialog isBackground or isBandShowing");
            KeyValDbManager.b(this.d).e("new_band_version", c);
            KeyValDbManager.b(this.d).e("new_band_size", String.valueOf(d));
            KeyValDbManager.b(this.d).e("new_band_change_log", b2);
            KeyValDbManager.b(this.d).e("is_new_checkbox", String.valueOf(true));
            KeyValDbManager.b(this.d).e("is_need_show_dialog", String.valueOf(true));
            KeyValDbManager.b(this.d).e("need_show_dialog_time", kxz.c());
            KeyValDbManager.b(this.d).e("need_show_dialog_mac", str);
            DeviceInfo e2 = jkj.d().e(str);
            KeyValDbManager.b(this.d).e("need_show_dialog_device_name", e2 != null ? e2.getDeviceName() : "");
            return;
        }
        if (kwx.c()) {
            LogUtil.a("HwOtaUpgradeManager", "HwOtaUpgradeManager showBandAutoCheckDialog is Sporting");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.putExtra("name", c);
            intent.putExtra("size", d);
            intent.putExtra("message", b2);
            intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, true);
            intent.putExtra("isAW70", false);
            intent.addFlags(268435456);
            intent.putExtra("mac", str);
            intent.setClassName(this.d.getPackageName(), "com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity");
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("HwOtaUpgradeManager", "showBandAutoCheckDialog ActivityNotFoundException");
        }
    }

    public void a(final String str, final boolean z, final ClearServiceListener clearServiceListener) {
        final DeviceInfo e2 = jkj.d().e(str);
        if (e2 == null) {
            LogUtil.h("HwOtaUpgradeManager", "checkNewVersion deviceInfo is null");
            return;
        }
        boolean a2 = kxz.a(kxz.c(this.d, str), e2.getProductType());
        if (z && a2) {
            e(clearServiceListener);
            LogUtil.h("HwOtaUpgradeManager", "requestNewVersion isAuto is true and isAlreadyCheck");
            return;
        }
        Message obtain = Message.obtain();
        final int i = z ? 2 : 1;
        obtain.what = i;
        obtain.obj = b(str, clearServiceListener);
        this.c.sendMessageDelayed(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
        jfq.c().c(c(), new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.19
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i2, String str2) throws RemoteException {
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i2, String str2, String str3) throws RemoteException {
                if (HwOtaUpgradeManager.this.c != null) {
                    HwOtaUpgradeManager.this.c.removeMessages(i);
                }
                LogUtil.a("HwOtaUpgradeManager", "autoRequestOtaFile() onSuccess successCode ", Integer.valueOf(i2), " transferDataContent ", str2, " transferStateContent ", str3);
                HwOtaUpgradeManager.this.c(str2, z, e2, clearServiceListener);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i2, String str2) throws RemoteException {
                if (HwOtaUpgradeManager.this.c != null) {
                    HwOtaUpgradeManager.this.c.removeMessages(i);
                }
                HwOtaUpgradeManager.this.e(clearServiceListener);
                if (z) {
                    return;
                }
                kyh.b(11, 0, "", "", str);
            }
        });
    }

    private jqj c() {
        jqj jqjVar = new jqj();
        jqjVar.a("ota_request");
        jqjVar.d(21);
        jqjVar.a(false);
        jqjVar.e((int[]) null);
        jqjVar.c((String) null);
        return jqjVar;
    }

    private JSONObject b(String str, ClearServiceListener clearServiceListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", str);
            jSONObject.put("listener", clearServiceListener);
        } catch (JSONException unused) {
            LogUtil.b("HwOtaUpgradeManager", "JSONException");
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, final boolean z, DeviceInfo deviceInfo, final ClearServiceListener clearServiceListener) {
        LogUtil.a("HwOtaUpgradeManager", "requestOtaDetection enter isAuto ", Boolean.valueOf(z));
        final String deviceIdentify = deviceInfo.getDeviceIdentify();
        final String b2 = b(str);
        if (TextUtils.isEmpty(b2)) {
            if (!z) {
                kyh.b(11, 12, "", "", deviceIdentify);
            }
            e(clearServiceListener);
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.arg1 = !z ? 1 : 0;
        obtain.obj = b(deviceIdentify, clearServiceListener);
        this.c.removeMessages(3);
        this.c.sendMessageDelayed(obtain, OpAnalyticsConstants.H5_LOADING_DELAY);
        ThreadPoolManager.d().d("HwOtaUpgradeManager", new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.18
            @Override // java.lang.Runnable
            public void run() {
                Message obtainMessage = HwOtaUpgradeManager.this.c.obtainMessage();
                obtainMessage.what = 4;
                obtainMessage.obj = deviceIdentify;
                HwOtaUpgradeManager.this.c.sendMessageDelayed(obtainMessage, 20000L);
                bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.18.5
                    @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                    public boolean call(Context context, Intent intent) {
                        HwOtaUpgradeManager.this.c.removeMessages(4);
                        HwOtaUpgradeManager.this.c(b2, z, deviceIdentify, clearServiceListener);
                        return false;
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, final boolean z, final String str2, final ClearServiceListener clearServiceListener) {
        bwf.a().checkNewVersion(d(str), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.2
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
            public void complete(int i, String str3) {
                LogUtil.a("HwOtaUpgradeManager", "requestOtaDetection status ", Integer.valueOf(i));
                HwOtaUpgradeManager.this.c.removeMessages(3);
                if (i == 0) {
                    HwOtaUpgradeManager.this.d(str3, z, str2, clearServiceListener);
                    return;
                }
                if (!z) {
                    kyh.b(11, 12, "", "", str2);
                }
                HwOtaUpgradeManager.this.e(clearServiceListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z, String str2, ClearServiceListener clearServiceListener) {
        kxz.b(kxz.c(), str2, BaseApplication.getContext());
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a("HwOtaUpgradeManager", "requestOtaDetection versionList ", str);
            String c = c(str2, str);
            if (!TextUtils.isEmpty(c)) {
                kyh.d();
                if (z) {
                    jko jkoVar = new jko();
                    jkoVar.a(str2);
                    jkoVar.e(kxz.j(BaseApplication.getContext(), str2));
                    jkoVar.c(CommonUtil.g(kxz.d(BaseApplication.getContext(), str2, c)));
                    jkoVar.a(System.currentTimeMillis() / 1000);
                    jkoVar.b(1);
                    jkoVar.a(2);
                    jkv.b().a(jkoVar);
                } else {
                    kyh.b(32, 13, "", "", str2);
                }
            } else if (!z) {
                kyh.b(11, 0, "", "", str2);
            }
        } else if (!z) {
            kyh.b(11, 12, "", "", str2);
        }
        e(clearServiceListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String c(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.google.gson.Gson r0 = new com.google.gson.Gson
            r0.<init>()
            java.lang.Class<com.huawei.hwdevice.mainprocess.mgr.hwotamanager.bean.NewVersionBean> r1 = com.huawei.hwdevice.mainprocess.mgr.hwotamanager.bean.NewVersionBean.class
            java.lang.Object r12 = r0.fromJson(r12, r1)
            com.huawei.hwdevice.mainprocess.mgr.hwotamanager.bean.NewVersionBean r12 = (com.huawei.hwdevice.mainprocess.mgr.hwotamanager.bean.NewVersionBean) r12
            java.lang.String r0 = "saveDeviceData versionInfo "
            java.lang.String r1 = r12.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "HwOtaUpgradeManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.lang.String r0 = r12.getHotaDisplayVersion()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L69
            java.lang.String r0 = r12.getHotaDisplayVersion()
            java.lang.String r2 = r12.getHotaChangeLogContent()
            long r3 = r12.getHotaByteSize()
            java.lang.String r5 = r12.getPatchDisplayVersion()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 16
            r2.<init>(r3)
            java.lang.String r3 = r12.getHotaChangeLogContent()
            r2.append(r3)
            java.lang.String r3 = java.lang.System.lineSeparator()
            r2.append(r3)
            java.lang.String r3 = r12.getPatchChangeLogContent()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            long r3 = r12.getHotaByteSize()
            long r5 = r12.getPatchByteSize()
            long r3 = r3 + r5
        L66:
            r12 = r2
        L67:
            r8 = r3
            goto L90
        L69:
            java.lang.String r0 = r12.getPatchDisplayVersion()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L80
            java.lang.String r0 = r12.getPatchDisplayVersion()
            java.lang.String r2 = r12.getPatchChangeLogContent()
            long r3 = r12.getPatchByteSize()
            goto L66
        L80:
            java.lang.String r12 = "saveDeviceData default"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r12)
            java.lang.String r0 = ""
            r3 = 0
            r12 = r0
            goto L67
        L90:
            java.lang.String r2 = " "
            boolean r3 = r0.contains(r2)
            if (r3 == 0) goto La4
            int r2 = r0.indexOf(r2)
            java.lang.String r0 = r0.substring(r2)
            java.lang.String r0 = r0.trim()
        La4:
            java.lang.String r2 = "saveDeviceData newVersion "
            java.lang.String r4 = " newChangeLog "
            java.lang.String r6 = " newByteSize "
            java.lang.Long r7 = java.lang.Long.valueOf(r8)
            r3 = r0
            r5 = r12
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3, r4, r5, r6, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.kxz.d(r0, r11, r1)
            java.lang.String r12 = r12.trim()
            android.content.Context r1 = r10.d
            defpackage.kxz.e(r12, r1, r0, r11)
            java.lang.String r12 = java.lang.String.valueOf(r8)
            android.content.Context r1 = r10.d
            defpackage.kxz.a(r11, r12, r0, r1)
            android.content.Context r12 = r10.d
            java.lang.String r12 = defpackage.nsn.b(r12, r8)
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.kxz.b(r12, r1, r0, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.c(java.lang.String, java.lang.String):java.lang.String");
    }

    private String b(String str) {
        String str2 = "";
        FileInputStream fileInputStream = null;
        try {
            try {
                File file = FileUtils.getFile(str);
                fileInputStream = FileUtils.openInputStream(file);
                long length = file.length();
                LogUtil.a("HwOtaUpgradeManager", "getRequestDateString fileLength ", Long.valueOf(length));
                byte[] bArr = new byte[(int) length];
                int read = fileInputStream.read(bArr);
                if (read == length) {
                    LogUtil.a("HwOtaUpgradeManager", "read inputStream success.");
                }
                if (read > 0) {
                    str2 = new String(bArr, "UTF-8");
                }
            } catch (IOException unused) {
                LogUtil.b("HwOtaUpgradeManager", "getRequestDateString getFileByte : IOException");
            }
            IoUtils.e(fileInputStream);
            LogUtil.a("HwOtaUpgradeManager", "getRequestDateString data ", CommonUtil.l(str2));
            return str2;
        } catch (Throwable th) {
            IoUtils.e(fileInputStream);
            throw th;
        }
    }

    private String d(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("packageName", BaseApplication.getContext().getPackageName());
            jSONObject.put("devUpgradeInfoJson", str);
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            String h = kxz.h();
            LogUtil.a("HwOtaUpgradeManager", "buildCheckInfo language ", h);
            jSONObject3.put("language", h);
            jSONObject2.put("dynamicInfo", jSONObject3.toString());
            jSONObject.put("upgradeInfoJson", jSONObject2.toString());
            if (CommonUtil.as() && TextUtils.equals(kxz.g(BaseApplication.getContext()), "test_mode")) {
                jSONObject.put("isGrsSwitch", false);
                jSONObject.put("defaultDomain", "");
            } else {
                jSONObject.put("isGrsSwitch", true);
            }
        } catch (JSONException e2) {
            LogUtil.b("HwOtaUpgradeManager", "buildCheckInfo JSONException:", e2.toString());
        }
        return jSONObject.toString();
    }

    private void e(String str) {
        Intent intent = new Intent("action_app_check_new_version_state");
        intent.addFlags(1610612736);
        intent.putExtra("result", 0);
        intent.putExtra("state", 11);
        intent.putExtra("extra_band_imei", str);
        intent.setPackage(BaseApplication.getAppPackage());
        this.d.sendBroadcast(intent, LocalBroadcast.c);
    }

    public boolean a(String str) {
        return cwi.c(jkk.d().j(str), 108) && !Utils.l();
    }

    public void a(final String str, final DeviceInfo deviceInfo, final ICheckCallback iCheckCallback) {
        LogUtil.a("HwOtaUpgradeManager", "enter getDeviceNewVersion");
        bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.3
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public boolean call(Context context, Intent intent) {
                HwOtaUpgradeManager.this.d(deviceInfo, iCheckCallback, str);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final DeviceInfo deviceInfo, final ICheckCallback iCheckCallback, final String str) {
        d(deviceInfo.getDeviceUdid(), new InitCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.5
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
            public void onInitComplete(int i) {
                LogUtil.a("HwOtaUpgradeManager", "initGetDeviceNewVersion onInitComplete code:", Integer.valueOf(i));
                bwf.a().getDeviceNewVersion(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.5.5
                    @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
                    public void complete(int i2, String str2) {
                        try {
                            if (iCheckCallback != null) {
                                iCheckCallback.onCheckComplete(str, str2);
                            }
                        } catch (RemoteException unused) {
                            LogUtil.b("HwOtaUpgradeManager", "getDeviceNewVersion RemoteException");
                        }
                    }
                });
            }
        });
    }

    public void d(final String str, final DeviceInfo deviceInfo, final String str2, final IUpgradeCallBack iUpgradeCallBack) {
        LogUtil.a("HwOtaUpgradeManager", "enter doUpgrade");
        bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.4
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public boolean call(Context context, Intent intent) {
                HwOtaUpgradeManager.this.e(deviceInfo, str2, iUpgradeCallBack, str);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final DeviceInfo deviceInfo, final String str, final IUpgradeCallBack iUpgradeCallBack, final String str2) {
        d(deviceInfo.getDeviceUdid(), new InitCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.6
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
            public void onInitComplete(int i) {
                LogUtil.a("HwOtaUpgradeManager", "initDoUpgrade onInitComplete code:", Integer.valueOf(i));
                bwf.a().doUpgrade(deviceInfo.getDeviceUdid(), str, new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.6.1
                    @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
                    public void complete(int i2, String str3) {
                        try {
                            if (iUpgradeCallBack != null) {
                                iUpgradeCallBack.onUpgradeStatus(str2, str3);
                            }
                        } catch (RemoteException unused) {
                            LogUtil.b("HwOtaUpgradeManager", "doUpgrade RemoteException");
                        }
                    }
                });
            }
        });
    }

    public void b(final String str, final IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        LogUtil.a("HwOtaUpgradeManager", "enter getUpgradeStatus");
        final DeviceInfo j = jkk.d().j(str);
        if (j == null) {
            LogUtil.h("HwOtaUpgradeManager", "getUpgradeStatus deviceInfo is null");
            e(str, iUpgradeStatusCallBack);
        } else {
            bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.7
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    HwOtaUpgradeManager.this.b(j, iUpgradeStatusCallBack, str);
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final DeviceInfo deviceInfo, final IUpgradeStatusCallBack iUpgradeStatusCallBack, final String str) {
        d(deviceInfo.getDeviceUdid(), new InitCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.10
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
            public void onInitComplete(int i) {
                LogUtil.a("HwOtaUpgradeManager", "initGetUpgradeStatus onInitComplete code:", Integer.valueOf(i));
                bwf.a().getUpgradeStatus(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.10.5
                    @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
                    public void complete(int i2, String str2) {
                        try {
                            if (iUpgradeStatusCallBack != null) {
                                iUpgradeStatusCallBack.onStatus(str, str2);
                            }
                        } catch (RemoteException unused) {
                            LogUtil.b("HwOtaUpgradeManager", "getUpgradeStatus RemoteException");
                        }
                    }
                });
            }
        });
    }

    private void e(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 5);
            jSONObject.put("errorCode", 11);
            jSONObject.put("errorMsg", jkk.d().e(11));
            iUpgradeStatusCallBack.onStatus(str, jSONObject.toString());
        } catch (RemoteException unused) {
            LogUtil.b("HwOtaUpgradeManager", "onError RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("HwOtaUpgradeManager", "onError JSONException");
        }
    }

    public void c(final String str, final IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        LogUtil.a("HwOtaUpgradeManager", "enter registerUpgradeListener");
        final DeviceInfo j = jkk.d().j(str);
        if (j == null) {
            LogUtil.h("HwOtaUpgradeManager", "registerUpgradeListener deviceInfo is null");
            e(str, iUpgradeStatusCallBack);
        } else {
            bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.8
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    HwOtaUpgradeManager.this.a(j, iUpgradeStatusCallBack, str);
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final DeviceInfo deviceInfo, final IUpgradeStatusCallBack iUpgradeStatusCallBack, final String str) {
        d(deviceInfo.getDeviceUdid(), new InitCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.9
            @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
            public void onInitComplete(int i) {
                LogUtil.a("HwOtaUpgradeManager", "initRegisterUpgradeListener onInitComplete code:", Integer.valueOf(i));
                bwf.a().registerUpgradeListener(deviceInfo.getDeviceUdid(), new CompleteCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.9.4
                    @Override // com.huawei.fullscenarioservice.pluginwearota.callback.CompleteCallback
                    public void complete(int i2, String str2) {
                        try {
                            if (iUpgradeStatusCallBack != null) {
                                iUpgradeStatusCallBack.onStatus(str, str2);
                            }
                        } catch (RemoteException | IllegalStateException e2) {
                            LogUtil.b("HwOtaUpgradeManager", "registerUpgradeListener RemoteException | IllegalStateException ", ExceptionUtils.d(e2));
                        }
                    }
                });
            }
        });
    }

    public void c(String str) {
        LogUtil.a("HwOtaUpgradeManager", "enter unRegisterUpgradeListener");
        final DeviceInfo j = jkk.d().j(str);
        if (j == null) {
            LogUtil.h("HwOtaUpgradeManager", "unRegisterUpgradeListener deviceInfo is null");
        } else {
            bwf.a().loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.14
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    HwOtaUpgradeManager.this.d(j.getDeviceUdid(), new InitCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager.14.3
                        @Override // com.huawei.fullscenarioservice.pluginwearota.callback.InitCallback
                        public void onInitComplete(int i) {
                            LogUtil.a("HwOtaUpgradeManager", "unRegisterUpgradeListener onInitComplete code:", Integer.valueOf(i));
                            bwf.a().unRegisterUpgradeListener(j.getDeviceUdid());
                        }
                    });
                    return false;
                }
            });
        }
    }
}
