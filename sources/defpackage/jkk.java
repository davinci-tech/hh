package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.PowerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IDeviceListCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jkk {
    private static final Object b = new Object();
    private static jkk c;
    private Map<String, Integer> d = new HashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Integer> f13912a = new HashMap(16);
    private Map<String, ICheckCallback> e = new HashMap(16);
    private Map<String, IUpgradeCallBack> j = new HashMap(16);
    private Map<String, IUpgradeStatusCallBack> h = new HashMap(16);

    private jkk() {
    }

    public static jkk d() {
        jkk jkkVar;
        synchronized (b) {
            if (c == null) {
                c = new jkk();
            }
            jkkVar = c;
        }
        return jkkVar;
    }

    public int i(String str) {
        int intValue = this.d.containsKey(str) ? this.d.get(str).intValue() : 0;
        LogUtil.a("HwUpdateUtil", "getOtaDownloadProgress mac ", CommonUtil.l(str), " downloadProgress ", Integer.valueOf(intValue));
        return intValue;
    }

    public void d(String str, int i) {
        LogUtil.a("HwUpdateUtil", "setOtaDownloadProgress mac ", CommonUtil.l(str), " progress ", Integer.valueOf(i));
        this.d.put(str, Integer.valueOf(i));
    }

    public int f(String str) {
        int intValue = this.f13912a.containsKey(str) ? this.f13912a.get(str).intValue() : 0;
        LogUtil.a("HwUpdateUtil", "getOtaTransferProgress mac ", CommonUtil.l(str), " transferProgress ", Integer.valueOf(intValue));
        return intValue;
    }

    public void b(String str, int i) {
        LogUtil.a("HwUpdateUtil", "setOtaTransferProgress mac ", CommonUtil.l(str), " progress ", Integer.valueOf(i));
        this.f13912a.put(str, Integer.valueOf(i));
    }

    private DeviceCapability o(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwUpdateUtil", "capabilityNegotiation mac is empty");
            return null;
        }
        LogUtil.a("HwUpdateUtil", "serviceCapabilityNegotiation(mac):", CommonUtil.l(str));
        if (cvs.e(str) == null) {
            DeviceInfo e = jpt.e(str, "HwUpdateUtil");
            if (e != null && e.getDeviceConnectState() == 2) {
                Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "HwUpdateUtil");
                if (a2 != null && a2.get(str) != null) {
                    cvs.a(str, a2.get(str));
                    return a2.get(str);
                }
                LogUtil.h("HwUpdateUtil", "serviceCapabilityNegotiation(mac) cap is null");
                return null;
            }
            LogUtil.h("HwUpdateUtil", "serviceCapabilityNegotiation(mac) device is not connected");
            return null;
        }
        return cvs.e(str);
    }

    public DeviceInfo j(String str) {
        LogUtil.a("HwUpdateUtil", "getUuidDeviceInfo uuid ", CommonUtil.l(str));
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwUpdateUtil");
        if (deviceList != null && deviceList.size() > 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (str.equals(deviceInfo.getUuid()) || str.equals(deviceInfo.getDeviceUdid())) {
                    return deviceInfo;
                }
            }
        }
        return null;
    }

    public String c(String str) {
        LogUtil.a("HwUpdateUtil", "getCurrentDeviceUuid mac ", CommonUtil.l(str));
        DeviceInfo b2 = jpt.b(str, "HwUpdateUtil");
        if (b2 == null || !str.equals(b2.getDeviceIdentify())) {
            return "";
        }
        if (TextUtils.isEmpty(b2.getDeviceUdid())) {
            return !TextUtils.isEmpty(b2.getUuid()) ? b2.getUuid() : "";
        }
        return b2.getDeviceUdid();
    }

    public void a(final IDeviceListCallback iDeviceListCallback) {
        if (iDeviceListCallback == null) {
            LogUtil.h("HwUpdateUtil", "getConnectedDevices deviceListCallback == null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jkk.4
                @Override // java.lang.Runnable
                public void run() {
                    List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwUpdateUtil");
                    if (deviceList == null) {
                        LogUtil.a("HwUpdateUtil", "getConnectedDevices deviceInfoList == null");
                        cun.c().executeDeviceRelatedLogic(ExecuteMode.FORCE_INIT_PHONE_SERVICE, null, "HwUpdateUtil");
                        try {
                            Thread.sleep(3000L);
                        } catch (InterruptedException e) {
                            LogUtil.b("HwUpdateUtil", "getCurrentDeviceInfo InterruptedException ", ExceptionUtils.d(e));
                        }
                        deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwUpdateUtil");
                    }
                    LogUtil.a("HwUpdateUtil", "getConnectedDevices deviceInfoList ", deviceList);
                    try {
                        iDeviceListCallback.onDeviceListObtain(jkk.this.e(deviceList).toString());
                    } catch (RemoteException e2) {
                        LogUtil.b("HwUpdateUtil", "getConnectedDevices RemoteException ", ExceptionUtils.d(e2));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject e(List<DeviceInfo> list) {
        JSONObject jSONObject = new JSONObject();
        if (list != null) {
            try {
            } catch (JSONException e) {
                LogUtil.b("HwUpdateUtil", "getConnectedDevices JSONException ", ExceptionUtils.d(e));
            }
            if (list.size() > 0) {
                LogUtil.a("HwUpdateUtil", "getConnectedDevices deviceList ", list.toString());
                JSONArray jSONArray = new JSONArray();
                jSONObject.put("status", 0);
                for (DeviceInfo deviceInfo : list) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("name", deviceInfo.getDeviceName());
                    if (TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
                        jSONObject2.put("uuid", deviceInfo.getUuid());
                    } else {
                        jSONObject2.put("uuid", deviceInfo.getDeviceUdid());
                    }
                    jSONObject2.put("model", deviceInfo.getDeviceModel());
                    jSONObject2.put("productType", deviceInfo.getProductType());
                    jSONObject2.put(BleConstants.KEY_CONNECTSTATE, deviceInfo.getDeviceConnectState());
                    DeviceCapability o = o(deviceInfo.getDeviceIdentify());
                    if (o != null && o.isOtaUpdate()) {
                        jSONObject2.put("supportOTA", true);
                    } else {
                        jSONObject2.put("supportOTA", false);
                    }
                    jSONObject2.put("version", deviceInfo.getSoftVersion());
                    JSONObject jSONObject3 = new JSONObject();
                    d(deviceInfo, jSONObject2, jSONObject3);
                    jSONObject2.put(KnitFragment.KEY_EXTRA, jSONObject3);
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("deviceList", jSONArray);
                return jSONObject;
            }
        }
        jSONObject.put("status", 1);
        jSONObject.put("errorCode", 10);
        jSONObject.put("errorMsg", e(10));
        return jSONObject;
    }

    private void d(DeviceInfo deviceInfo, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jfu.c(deviceInfo.getProductType()).d() == -1) {
            jSONObject2.put("deviceType", -1);
            jfu.f();
        } else if (jfu.h(deviceInfo.getProductType())) {
            jSONObject2.put("deviceType", 0);
        } else {
            jSONObject2.put("deviceType", 1);
        }
        jSONObject2.put(HealthEngineRequestManager.PARAMS_DEVICE_SN, deviceInfo.getSecurityDeviceId());
        if (HwOtaUpgradeManager.a().a(deviceInfo.getUuid())) {
            jSONObject.put("supportOTA", true);
            jSONObject2.put("otaType", 1);
        }
    }

    public void d(String str, ICheckCallback iCheckCallback) {
        if (iCheckCallback == null) {
            LogUtil.h("HwUpdateUtil", "setNewVersionStatus checkCallback == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 1);
            jSONObject.put("errorCode", 11);
            jSONObject.put("errorMsg", e(11));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("status", 1);
            jSONObject2.put("newVersionInfoList", new JSONArray());
            jSONObject.put("newVersionInfo", jSONObject2);
            LogUtil.a("HwUpdateUtil", "setNewVersionStatus statusJsonObject.toString() ", jSONObject.toString());
            iCheckCallback.onCheckComplete(str, jSONObject.toString());
        } catch (RemoteException e) {
            LogUtil.b("HwUpdateUtil", "setNewVersionStatus RemoteException ", ExceptionUtils.d(e));
        } catch (JSONException e2) {
            LogUtil.b("HwUpdateUtil", "setNewVersionStatus JSONException ", ExceptionUtils.d(e2));
        }
    }

    public void d(String str, IUpgradeCallBack iUpgradeCallBack) {
        if (iUpgradeCallBack == null) {
            LogUtil.h("HwUpdateUtil", "setNewVersionStatus upgradeCallBack == null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", 1);
            jSONObject.put("errorCode", 11);
            jSONObject.put("errorMsg", e(11));
            iUpgradeCallBack.onUpgradeStatus(str, jSONObject.toString());
        } catch (RemoteException e) {
            LogUtil.b("HwUpdateUtil", "setUpgradeStatus RemoteException ", ExceptionUtils.d(e));
        } catch (JSONException e2) {
            LogUtil.b("HwUpdateUtil", "setUpgradeStatus JSONException ", ExceptionUtils.d(e2));
        }
    }

    public void b(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        if (iUpgradeStatusCallBack == null) {
            LogUtil.h("HwUpdateUtil", "getUpgradeStatus upgradeStatusCallBack == null");
            return;
        }
        DeviceInfo j = j(str);
        try {
            JSONObject jSONObject = new JSONObject();
            if (j == null) {
                jSONObject.put("status", 5);
                jSONObject.put("errorCode", 11);
                jSONObject.put("errorMsg", e(11));
                iUpgradeStatusCallBack.onStatus(str, jSONObject.toString());
                return;
            }
            int e = e(j);
            int i = 0;
            LogUtil.a("HwUpdateUtil", "getUpgradeStatus upgradeStatusCallBack status ", Integer.valueOf(e));
            if (e == -1) {
                jSONObject.put("status", -1);
                jSONObject.put("errorCode", 0);
                jSONObject.put("errorMsg", e(0));
                iUpgradeStatusCallBack.onStatus(str, jSONObject.toString());
                return;
            }
            b(e, jSONObject, str, 0, iUpgradeStatusCallBack);
            if (e == 1) {
                i = i(j.getDeviceIdentify());
            } else if (e == 3) {
                i = f(j.getDeviceIdentify());
            } else {
                LogUtil.h("HwUpdateUtil", "getUpgradeStatus progress");
            }
            jSONObject.put("status", e);
            jSONObject.put("progress", i);
            iUpgradeStatusCallBack.onStatus(str, jSONObject.toString());
        } catch (RemoteException e2) {
            LogUtil.b("HwUpdateUtil", "getUpgradeStatus RemoteException ", ExceptionUtils.d(e2));
        } catch (JSONException e3) {
            LogUtil.b("HwUpdateUtil", "getUpgradeStatus JSONException ", ExceptionUtils.d(e3));
        }
    }

    private void b(int i, JSONObject jSONObject, String str, int i2, IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        if (i == 0) {
            try {
                jSONObject.put("status", 0);
                jSONObject.put("errorCode", 13);
                jSONObject.put("errorMsg", e(13));
                jSONObject.put("progress", i2);
                iUpgradeStatusCallBack.onStatus(str, jSONObject.toString());
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateUtil", "handleStatusInitial RemoteException ", ExceptionUtils.d(e));
            } catch (JSONException e2) {
                LogUtil.b("HwUpdateUtil", "handleStatusInitial JSONException ", ExceptionUtils.d(e2));
            }
        }
    }

    public int e(DeviceInfo deviceInfo) {
        int i = -1;
        if (deviceInfo == null) {
            return -1;
        }
        String j = kxz.j(BaseApplication.getContext(), deviceInfo.getDeviceIdentify());
        if (!TextUtils.isEmpty(j) && !deviceInfo.getDeviceIdentify().equals(j)) {
            i = 0;
        }
        int c2 = jkj.d().c(deviceInfo.getDeviceIdentify());
        if (c2 == 3) {
            return 1;
        }
        if (c2 == 4 || c2 == 6) {
            return 3;
        }
        LogUtil.h("HwUpdateUtil", "getOtaUpgradeStatus upgradeStatus");
        return i;
    }

    public void a(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        if (iUpgradeStatusCallBack == null) {
            LogUtil.h("HwUpdateUtil", "registerUpgradeListener upgradeStatusCallBack == null");
            return;
        }
        try {
            if (j(str) == null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("status", 1);
                jSONObject.put("errorCode", 11);
                jSONObject.put("errorMsg", e(11));
                iUpgradeStatusCallBack.onStatus(str, jSONObject.toString());
                return;
            }
            d(str, iUpgradeStatusCallBack);
        } catch (RemoteException e) {
            LogUtil.b("HwUpdateUtil", "getConnectedDevices RemoteException ", ExceptionUtils.d(e));
        } catch (JSONException e2) {
            LogUtil.b("HwUpdateUtil", "getConnectedDevices JSONException ", ExceptionUtils.d(e2));
        }
    }

    public void b(String str, ICheckCallback iCheckCallback) {
        LogUtil.a("HwUpdateUtil", "setCheckCallback uuid ", CommonUtil.l(str), " checkCallback ", iCheckCallback);
        this.e.put(str, iCheckCallback);
    }

    public ICheckCallback d(String str) {
        String c2 = c(str);
        LogUtil.a("HwUpdateUtil", "getCheckCallback uuid ", CommonUtil.l(c2));
        if (this.e.containsKey(c2)) {
            return this.e.get(c2);
        }
        return null;
    }

    public void e(String str, int i) {
        ICheckCallback d = d(str);
        if (d != null) {
            String c2 = c(str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", 1);
                jSONObject.put("errorCode", i);
                jSONObject.put("errorMsg", e(i));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("status", 1);
                jSONObject2.put("newVersionInfoList", new JSONArray());
                jSONObject.put("newVersionInfo", jSONObject2);
                LogUtil.a("HwUpdateUtil", "checkOtaStatus statusJsonObject.toString() ", jSONObject.toString());
                d.onCheckComplete(c2, jSONObject.toString());
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateUtil", "checkOtaStatus RemoteException ", ExceptionUtils.d(e));
            } catch (JSONException e2) {
                LogUtil.b("HwUpdateUtil", "checkOtaStatus JSONException ", ExceptionUtils.d(e2));
            }
            b(c2, (ICheckCallback) null);
        }
    }

    public void a(String str) {
        String b2 = HwVersionManager.c(BaseApplication.getContext()).b(str);
        String j = HwVersionManager.c(BaseApplication.getContext()).j(str);
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(j) || b2.equals(j)) {
            e(str, 0);
            LogUtil.a("HwUpdateUtil", "checkOtaSuccess bandCurrentVersion or checkBandNewVersion is null || version is equal");
            return;
        }
        ICheckCallback d = d(str);
        if (d != null) {
            String c2 = c(str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("status", 0);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("currentVersion", b2);
                jSONObject3.put("targetVersion", j);
                jSONObject3.put("packageSize", kxz.d(BaseApplication.getContext(), str, j));
                jSONObject3.put("packageSizeUnit", "Byte");
                jSONObject3.put("changeLog", kxz.b(BaseApplication.getContext(), j, str));
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jSONObject3);
                jSONObject2.put("newVersionInfoList", jSONArray);
                jSONObject.put("newVersionInfo", jSONObject2);
                LogUtil.a("HwUpdateUtil", "checkOtaSuccess jsonObject.toString() ", jSONObject.toString());
                d.onCheckComplete(c2, jSONObject.toString());
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateUtil", "checkOtaSuccess RemoteException ", ExceptionUtils.d(e));
            } catch (JSONException e2) {
                LogUtil.b("HwUpdateUtil", "checkOtaSuccess JSONException ", ExceptionUtils.d(e2));
            }
            b(c2, (ICheckCallback) null);
        }
    }

    public void c(String str, IUpgradeCallBack iUpgradeCallBack) {
        LogUtil.a("HwUpdateUtil", "setUpgradeCallBack uuid ", CommonUtil.l(str), " callback ", iUpgradeCallBack);
        this.j.put(str, iUpgradeCallBack);
    }

    public IUpgradeCallBack g(String str) {
        String c2 = c(str);
        LogUtil.a("HwUpdateUtil", "getUpgradeCallBack uuid ", CommonUtil.l(c2));
        if (this.j.containsKey(c2)) {
            return this.j.get(c2);
        }
        return null;
    }

    public void d(String str, int i, int i2) {
        IUpgradeCallBack g = g(str);
        if (g != null) {
            String c2 = c(str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", i);
                jSONObject.put("errorCode", i2);
                jSONObject.put("errorMsg", e(i2));
                LogUtil.a("HwUpdateUtil", "doUpgradeOtaStatus jsonObject.toString() ", jSONObject.toString());
                g.onUpgradeStatus(c2, jSONObject.toString());
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateUtil", "doUpgradeOtaStatus RemoteException ", ExceptionUtils.d(e));
            } catch (JSONException e2) {
                LogUtil.b("HwUpdateUtil", "doUpgradeOtaStatus JSONException ", ExceptionUtils.d(e2));
            }
            c(c2, (IUpgradeCallBack) null);
        }
    }

    public void b(String str) {
        IUpgradeCallBack g = g(str);
        if (g != null) {
            String c2 = c(str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", 0);
                jSONObject.put("errorCode", 13);
                jSONObject.put("errorMsg", e(13));
                LogUtil.a("HwUpdateUtil", "doUpgradeSuccess jsonObject.toString() ", jSONObject.toString());
                g.onUpgradeStatus(c2, jSONObject.toString());
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateUtil", "doUpgradeSuccess RemoteException ", ExceptionUtils.d(e));
            } catch (JSONException e2) {
                LogUtil.b("HwUpdateUtil", "doUpgradeSuccess JSONException ", ExceptionUtils.d(e2));
            }
            c(c2, (IUpgradeCallBack) null);
        }
    }

    public void d(String str, IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        LogUtil.a("HwUpdateUtil", "setUpgradeStatusCallBack uuid ", CommonUtil.l(str), " callback ", iUpgradeStatusCallBack);
        this.h.put(str, iUpgradeStatusCallBack);
    }

    public IUpgradeStatusCallBack h(String str) {
        String c2 = c(str);
        LogUtil.a("HwUpdateUtil", "getUpgradeStatusCallBack uuid ", CommonUtil.l(c2));
        if (this.h.containsKey(c2)) {
            return this.h.get(c2);
        }
        return null;
    }

    public void b(int i, String str, int i2) {
        IUpgradeStatusCallBack h = h(str);
        LogUtil.a("HwUpdateUtil", "onUpgradeStatus upgradeStatusCallBack ", h);
        if (h != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", i2);
                jSONObject.put("progress", i);
                h.onStatus(c(str), jSONObject.toString());
                LogUtil.a("HwUpdateUtil", "onUpgradeStatus onStatus ", jSONObject.toString());
            } catch (RemoteException | IllegalStateException | JSONException e) {
                LogUtil.b("HwUpdateUtil", "onUpgradeStatus onStatus Exception ", ExceptionUtils.d(e));
            }
        }
    }

    public void d(int i, String str, int i2, String str2) {
        IUpgradeStatusCallBack h = h(str);
        if (h != null) {
            if (i2 == 1) {
                i2 = 5;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", i);
                jSONObject.put("errorCode", i2);
                if (TextUtils.isEmpty(str2)) {
                    str2 = e(i2);
                }
                jSONObject.put("errorMsg", str2);
                h.onStatus(c(str), jSONObject.toString());
                LogUtil.a("R_OTA_HwUpdateUtil", "upgradeFailStatus onStatus ", jSONObject.toString());
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateUtil", "upgradeFailStatus onStatus RemoteException ", ExceptionUtils.d(e));
            } catch (JSONException e2) {
                LogUtil.b("HwUpdateUtil", "upgradeFailStatus onStatus JSONException ", ExceptionUtils.d(e2));
            }
        }
    }

    public String e(int i) {
        if (i == 1 || i == 3) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_update_network_abnormal);
        }
        if (i == 5) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_update_download_check_failed);
        }
        if (i == 2) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_update_server_error);
        }
        if (i == 4) {
            return nsn.ae(BaseApplication.getContext()) ? BaseApplication.getContext().getString(R$string.IDS_pad_phone_low_battery, UnitUtil.e(10.0d, 2, 0)) : BaseApplication.getContext().getString(R$string.IDS_settings_firmware_upgrade_phone_low_battery);
        }
        if (i == 12) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_update_get_changelog_failed);
        }
        if (i == 0) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_me_settings_app_update);
        }
        if (i == 7) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_music_management_disconnection);
        }
        if (i == 10 || i == 11) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_equip_blutooth_disconnected);
        }
        if (i == 13) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_update_new_version_to_upgrade_app);
        }
        if (i == 14) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_update_low_memory);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_update_unknown_error);
    }

    public void c(DeviceInfo deviceInfo, kxj kxjVar) {
        File[] listFiles;
        if (deviceInfo == null || kxjVar == null) {
            return;
        }
        String str = BaseApplication.getContext().getFilesDir() + "changeLog" + File.separator + deviceInfo.getDeviceUdid();
        String str2 = deviceInfo.getSoftVersion() + "_changelog.xml";
        LogUtil.a("HwUpdateUtil", "deleteUnUseVersionChangeLog currentChangeLogFileName=", str2);
        String str3 = kxjVar.i() + "_changelog.xml";
        LogUtil.a("HwUpdateUtil", "deleteUnUseVersionChangeLog newVersionChangeLogFileName=", str3);
        File file = new File(str);
        if (file.exists() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                LogUtil.c("HwUpdateUtil", "deleteUnUseVersionChangeLog fileName=", file2.getName());
                if (!file2.getName().equals(str2) && !file2.getName().equals(str3)) {
                    LogUtil.c("HwUpdateUtil", "deleteUnUseVersionChangeLog isDelete=", Boolean.valueOf(file2.delete()));
                }
            }
        }
    }

    public void k(String str) {
        DeviceCapability e = cvs.e(str);
        if (e == null || !e.getIsSupportNotifyDeviceNewVersion()) {
            return;
        }
        int i = !CommonUtil.aa(BaseApplication.getContext()) ? 3 : 2;
        jko jkoVar = new jko();
        jkoVar.a(str);
        jkoVar.e("");
        jkoVar.c(0L);
        jkoVar.a(System.currentTimeMillis() / 1000);
        jkoVar.b(i);
        jkoVar.a(0);
        jkv.b().a(jkoVar);
    }

    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        if (!TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(26), "electronicPrefix" + knl.a(deviceInfo.getUuid())))) {
            LogUtil.a("HwUpdateUtil", "is electronic card");
            HwVersionManager.c(BaseApplication.getContext()).d(true);
        } else {
            LogUtil.h("HwUpdateUtil", "is not electronic card");
            HwVersionManager.c(BaseApplication.getContext()).d(false);
        }
        kxz.d(deviceInfo.getDeviceIdentify(), "checkElectronic", String.valueOf(!TextUtils.isEmpty(r0)));
    }

    public void a(String str, String str2, kxj kxjVar) {
        String str3;
        if (str == null || str2 == null || kxjVar == null) {
            LogUtil.h("HwUpdateUtil", "saveChangelog deviceInfo is null");
            return;
        }
        DeviceInfo e = jkj.d().e(str);
        if (e == null) {
            LogUtil.h("HwUpdateUtil", "saveChangelog deviceInfo is null");
            return;
        }
        kxl c2 = kxu.c(e.getSecurityDeviceId());
        if (c2 == null || c2.i() == null) {
            str3 = "";
        } else {
            str3 = c2.i().split("full/")[0] + "full/changelog.xml";
        }
        c(e, kxjVar);
        String str4 = e.getDeviceUdid() + "_" + kxz.j(BaseApplication.getContext(), str);
        HwVersionManager.c(BaseApplication.getContext()).e(str4 + "_changeLogUri", str3);
        HwVersionManager.c(BaseApplication.getContext()).d(str4 + "_changelog.xml", str2);
        String d = kxw.d(str2, kxz.h(), kxz.f());
        kxz.d(d, BaseApplication.getContext(), kxjVar.i(), str);
        LogUtil.a("HwUpdateUtil", "saveChangelog changelogFlag: ", str4, " features: ", d);
    }

    public void a(String str, String str2, String str3, boolean z) {
        DeviceInfo e = jkj.d().e(str);
        if (e == null) {
            LogUtil.h("HwUpdateUtil", "savePullChangeLog changLogFilePath: ", str3, " versionUrl: ", str2);
            return;
        }
        String str4 = e.getDeviceUdid() + "_" + e.getSoftVersion();
        HwVersionManager.c(BaseApplication.getContext()).e(str4 + "_changeLogUri", str2);
        HwVersionManager.c(BaseApplication.getContext()).d(str4 + "_changelog.xml", str3);
        String e2 = kxw.e(str3, kxz.h(), kxz.f());
        String d = kxw.d(str3, kxz.h(), kxz.f());
        kxz.d(d, BaseApplication.getContext(), e.getSoftVersion(), e.getDeviceIdentify());
        if (z) {
            jkr jkrVar = new jkr();
            String b2 = HwVersionManager.c(BaseApplication.getContext()).b(str);
            jkrVar.e(str2);
            jkrVar.b(b2);
            jkrVar.a(1);
            jkrVar.a(kxz.f());
            String e3 = e(e2);
            LogUtil.a("HwUpdateUtil", "AppBandPullChangeLogHandler, deviceUpdateLog = ", e3);
            jkrVar.d(e3);
            jkv.b().d(str, jkrVar);
        }
        LogUtil.a("HwUpdateUtil", "AppBandPullChangeLogHandler pullChangeLogSuccess() features = ", d);
    }

    public String e(String str) {
        int indexOf;
        LogUtil.a("HwUpdateUtil", "getDeviceUpdateLog updateLog = ", str);
        return (!TextUtils.isEmpty(str) && (indexOf = str.indexOf(System.lineSeparator(), System.lineSeparator().length())) >= 0) ? str.substring(indexOf + System.lineSeparator().length()) : "";
    }

    public void c(String str, int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("errorCode", Integer.valueOf(i));
        DeviceInfo e = jkj.d().e(str);
        if (e != null) {
            hashMap.put("deviceModel", e.getDeviceModel());
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DOWNLOAD_OTA_FILE_2180013.value(), hashMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        for (Map.Entry entry : hashMap.entrySet()) {
            linkedHashMap.put((String) entry.getKey(), entry.getValue().toString());
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.DOWNLOAD_OTA_FILE_2180013.value(), linkedHashMap);
    }

    public void b(String str, boolean z, boolean z2) {
        LogUtil.a("HwUpdateUtil", "reportStatus isSuccess: ", Boolean.valueOf(z));
        kxi kxiVar = new kxi();
        if (z2) {
            if (z) {
                kxiVar.d(3000);
            } else {
                kxiVar.d(3001);
            }
        } else if (z) {
            kxiVar.d(3);
        } else {
            kxiVar.d(4);
        }
        if (CommonUtil.br()) {
            kxiVar.b(CommonUtil.h());
        } else {
            kxiVar.b(CommonUtil.an());
        }
        DeviceInfo e = jkj.d().e(str);
        kxiVar.c(kxz.k(BaseApplication.getContext(), e != null ? e.getSecurityDeviceId() : "").w());
        kxiVar.e(kxu.a(kxu.j(), BaseApplication.getContext()));
        kxiVar.d("");
        ThreadPoolManager.d().execute(new kyk(kxiVar, false, false));
    }

    public void d(String str, boolean z, boolean z2) {
        if (z2) {
            DataDeviceInfo dataDeviceInfo = new DataDeviceInfo();
            dataDeviceInfo.setDeviceBtMac(str);
            if (m(dataDeviceInfo.getDeviceBtMac())) {
                return;
            }
            LogUtil.a("HwUpdateUtil", "setActivateDevice Set to Inactive , mac = ", iyl.d().e(dataDeviceInfo.getDeviceBtMac()));
            dataDeviceInfo.setDeviceEmmcId("false");
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(1017), dataDeviceInfo.getDeviceBtMac(), new Gson().toJson(dataDeviceInfo), new StorageParams());
            return;
        }
        if (m(str) || !z) {
            return;
        }
        kxz.u(BaseApplication.getContext(), str);
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(1017), str);
        try {
            if (b2 == null) {
                LogUtil.h("HwUpdateUtil", "setActivateDevicevalue == null");
                return;
            }
            DataDeviceInfo dataDeviceInfo2 = (DataDeviceInfo) new Gson().fromJson(b2, DataDeviceInfo.class);
            if (dataDeviceInfo2 == null) {
                LogUtil.h("HwUpdateUtil", "setActivateDevice device == null");
            } else {
                dataDeviceInfo2.setDeviceEmmcId("true");
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(1017), str, new Gson().toJson(dataDeviceInfo2), new StorageParams());
            }
        } catch (JsonSyntaxException e) {
            LogUtil.b("HwUpdateUtil", "setActivateDevice exception :", ExceptionUtils.d(e));
        }
    }

    private boolean m(String str) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(1017), str);
        if (b2 == null || "".equals(b2.trim())) {
            LogUtil.a("HwUpdateUtil", "isDeviceActivated ,Not locally");
            return false;
        }
        try {
            return "true".equalsIgnoreCase(((DataDeviceInfo) new Gson().fromJson(b2, DataDeviceInfo.class)).getDeviceEmmcId());
        } catch (JsonSyntaxException e) {
            LogUtil.b("HwUpdateUtil", "setActivateDevice exception : ", ExceptionUtils.d(e));
            LogUtil.a("HwUpdateUtil", "isDeviceActivated Local Inactive");
            return false;
        }
    }

    public boolean n(String str) {
        if (jrd.b()) {
            LogUtil.a("HwUpdateUtil", "isBandShowing return");
            return false;
        }
        DeviceInfo e = jkj.d().e(str);
        if (e == null || e.getDeviceConnectState() != 2) {
            LogUtil.a("HwUpdateUtil", "startAutoTransfer band is not connect");
            jkj.d().d(str, 0);
            jkn.a().e();
            return false;
        }
        int c2 = jkj.d().c(str);
        if (c2 != 3 && c2 != 4 && c2 != 6) {
            return true;
        }
        LogUtil.h("HwUpdateUtil", "band OTA is upgrading");
        return false;
    }

    public void l(String str) {
        Intent intent = new Intent("action_app_check_new_version_state");
        intent.addFlags(1610612736);
        intent.putExtra("result", 0);
        intent.putExtra("state", 11);
        intent.putExtra("extra_band_imei", str);
        intent.setPackage(BaseApplication.getAppPackage());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public void b(String str, boolean z) {
        LogUtil.a("HwUpdateUtil", "broadcastActivateResult: isActivated = ", Boolean.valueOf(z));
        if (HwVersionManager.c(BaseApplication.getContext()).i()) {
            return;
        }
        d(str, z, false);
        jfo.e().d(str, z);
    }

    public boolean e() {
        int b2 = PowerUtil.b(BaseApplication.getContext());
        if (b2 >= 10 || b2 == -1 || PowerUtil.a(BaseApplication.getContext())) {
            return false;
        }
        LogUtil.h("HwUpdateUtil", "MIN_PHONE_BATTERY_LEVEL not autoDownload");
        return true;
    }

    public void c(DeviceInfo deviceInfo, String str) {
        LogUtil.a("HwUpdateUtil", "Enter dealWithPrivacySwitchMessage");
        if (deviceInfo == null) {
            LogUtil.h("HwUpdateUtil", "deviceInfo is null");
        } else {
            jfq.c().d("privacySwitch", deviceInfo, 0, str);
        }
    }

    public void a(String str, kxj kxjVar) {
        if (kxjVar == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HwUpdateUtil", "syncTimeAndBandInfo (versionInfo == null) || (TextUtils.isEmpty(deviceId)");
            return;
        }
        kxz.j(kxjVar.e(), str, BaseApplication.getContext());
        kxz.d(kxjVar.i(), str, BaseApplication.getContext());
        kxz.e(kxjVar.g(), str, BaseApplication.getContext());
        long c2 = kxjVar.c();
        kxz.a(str, String.valueOf(c2), kxjVar.i(), BaseApplication.getContext());
        try {
            kxz.a(nsn.b(BaseApplication.getContext(), kxjVar.c()), BaseApplication.getContext(), kxjVar.i(), str);
        } catch (MissingFormatArgumentException e) {
            LogUtil.b("HwUpdateUtil", "syncTimeAndBandInfo NumberFormatException : ", ExceptionUtils.d(e));
        }
    }

    public void d(boolean z, String str) {
        LogUtil.a("HwUpdateUtil", "saveUpgradeParameters enter");
        String j = kxz.j(BaseApplication.getContext(), str);
        KeyValDbManager.b(BaseApplication.getContext()).e("new_band_version", j);
        KeyValDbManager.b(BaseApplication.getContext()).e("new_band_version_type", kxz.f(BaseApplication.getContext(), str));
        KeyValDbManager.b(BaseApplication.getContext()).e("new_band_size", String.valueOf(kxz.d(BaseApplication.getContext(), str, j)));
        KeyValDbManager.b(BaseApplication.getContext()).e("new_band_change_log", "");
        KeyValDbManager.b(BaseApplication.getContext()).e("is_new_checkbox", String.valueOf(z));
        KeyValDbManager.b(BaseApplication.getContext()).e("is_need_show_dialog", String.valueOf(true));
        KeyValDbManager.b(BaseApplication.getContext()).e("need_show_dialog_time", kxz.c());
        KeyValDbManager.b(BaseApplication.getContext()).e("need_show_dialog_mac", str);
        DeviceInfo e = jkj.d().e(str);
        KeyValDbManager.b(BaseApplication.getContext()).e("need_show_dialog_device_name", e != null ? e.getDeviceName() : "");
    }

    public jko e(String str, String str2) {
        LogUtil.a("HwUpdateUtil", "getUpdateMessageBean enter");
        jko jkoVar = new jko();
        jkoVar.a(str);
        String j = kxz.j(BaseApplication.getContext(), str);
        jkoVar.e(j);
        jkoVar.c(CommonUtil.g(kxz.d(BaseApplication.getContext(), str, j)));
        jkoVar.a(System.currentTimeMillis() / 1000);
        jkoVar.b(1);
        jkoVar.a(2);
        if (!TextUtils.isEmpty(str2)) {
            jkoVar.e(jds.c(str2, 10));
        }
        return jkoVar;
    }

    public void d(Context context, boolean z, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HwUpdateUtil", "startUpdateActivity (context == null) || TextUtils.isEmpty(deviceId)");
            return;
        }
        try {
            Intent intent = new Intent();
            String j = kxz.j(BaseApplication.getContext(), str);
            intent.putExtra("name", j);
            intent.putExtra("type", kxz.f(BaseApplication.getContext(), str));
            intent.putExtra("size", CommonUtil.g(kxz.d(BaseApplication.getContext(), str, j)));
            intent.putExtra("message", "");
            intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, z);
            intent.putExtra("isAW70", false);
            intent.addFlags(268435456);
            intent.putExtra("mac", str);
            intent.setClassName(context.getPackageName(), "com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("HwUpdateUtil", "startUpdateActivity e ", ExceptionUtils.d(e));
        }
    }

    public void a(String str, int i) {
        DeviceInfo e = jkj.d().e(str);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("otaType", String.valueOf(i));
        if (e != null) {
            linkedHashMap.put("otaDeviceModel", e.getDeviceModel());
        }
        LogUtil.h("HwUpdateUtil", "uploadOpAnalytics Status = ", Integer.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.OTA_UPGRADE_PROCESS_80070007.value(), linkedHashMap);
    }

    public boolean e(String str, String str2, IUpgradeCallBack iUpgradeCallBack, DeviceInfo deviceInfo) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || iUpgradeCallBack == null || deviceInfo == null || a(str, str2) != 11) {
            return true;
        }
        c(str, iUpgradeCallBack);
        int e = e(deviceInfo);
        if (e == -1) {
            d(deviceInfo.getDeviceIdentify(), 0, 0);
            return true;
        }
        if (e == 1 || e == 3) {
            d(deviceInfo.getDeviceIdentify(), 0, 13);
            return true;
        }
        boolean b2 = jkj.d().b(jds.c(kxz.d(BaseApplication.getContext(), deviceInfo.getDeviceIdentify(), kxz.j(BaseApplication.getContext(), deviceInfo.getDeviceIdentify())), 10));
        LogUtil.a("HwUpdateUtil", "doUpgrade: checkMemory = ", Boolean.valueOf(b2));
        if (b2) {
            return false;
        }
        d(deviceInfo.getDeviceIdentify(), 1, 14);
        return true;
    }

    private int a(String str, String str2) {
        int i;
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException e) {
            LogUtil.b("HwUpdateUtil", "doUpgrade JSONException ", ExceptionUtils.d(e));
        }
        if (jSONObject.has(AwarenessRequest.Field.COMMAND)) {
            i = jSONObject.getInt(AwarenessRequest.Field.COMMAND);
            LogUtil.a("HwUpdateUtil", "doUpgrade uuid ", CommonUtil.l(str), " command ", str2, " updateCommand ", Integer.valueOf(i));
            return i;
        }
        i = -1;
        LogUtil.a("HwUpdateUtil", "doUpgrade uuid ", CommonUtil.l(str), " command ", str2, " updateCommand ", Integer.valueOf(i));
        return i;
    }
}
