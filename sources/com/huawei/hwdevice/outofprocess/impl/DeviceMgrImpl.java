package com.huawei.hwdevice.outofprocess.impl;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.DeviceMgrApi;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.health.devicemgr.api.constant.DeviceStateConstants;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.constant.HwGetSetingItemConstants;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.impl.DeviceMgrImpl;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.cul;
import defpackage.cuo;
import defpackage.cuw;
import defpackage.jez;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jgi;
import defpackage.jgq;
import defpackage.jgw;
import defpackage.jho;
import defpackage.jiq;
import defpackage.jis;
import defpackage.jnr;
import defpackage.jnw;
import defpackage.jog;
import defpackage.joh;
import defpackage.jos;
import defpackage.jpn;
import defpackage.jpp;
import defpackage.jps;
import defpackage.jpt;
import defpackage.jpy;
import defpackage.jqi;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class DeviceMgrImpl implements DeviceMgrApi {
    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void registerDataCallback(IBaseResponseCallback iBaseResponseCallback, DataCallbackType dataCallbackType, String str) {
        LogUtil.a("DeviceMgrImpl", "registerDataCallback DeviceMgrImpl tag:", str);
        if (dataCallbackType == DataCallbackType.FITNESS_POSTURE_MANAGER) {
            jiq.a(BaseApplication.getContext()).d(iBaseResponseCallback);
            return;
        }
        if (dataCallbackType == DataCallbackType.EXERCISE_ADVICE_MANAGER) {
            jgw.d().e(iBaseResponseCallback);
            return;
        }
        if (dataCallbackType == DataCallbackType.DEVICE_FONT_MANAGER) {
            jis.b().b(iBaseResponseCallback);
        } else if (dataCallbackType == DataCallbackType.SUGGESTION_AIDL) {
            jez.i();
        } else {
            LogUtil.a("DeviceMgrImpl", "registerDataCallback The type does not match tag:", str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void unregisterDataCallback(int i) {
        if (i == 0) {
            jiq.a(BaseApplication.getContext()).a();
        } else if (i == 1) {
            jgw.d().c();
        } else {
            LogUtil.a("DeviceMgrImpl", "unregisterDataCallback The type does not match");
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void pushFitRunCourseData(FitWorkout fitWorkout) {
        jgw.d().d(fitWorkout);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public DeviceInfo getDeviceInfo(GetDeviceInfoMode getDeviceInfoMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        LogUtil.a("DeviceMgrImpl", "getDeviceInfo, mode:", getDeviceInfoMode, " , tag: ", str);
        if (getDeviceInfoMode == GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70) {
            return jpt.d(str);
        }
        if (getDeviceInfoMode == GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70) {
            return jpt.a(str);
        }
        LogUtil.a("DeviceMgrImpl", "getDeviceInfo not support:", getDeviceInfoMode, " , tag: ", str);
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public List<DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        LogUtil.a("DeviceMgrImpl", "getDeviceList, mode: ", hwGetDevicesMode, " , tag: ", str);
        if (EnvironmentUtils.e()) {
            return cuo.d().getDeviceList(hwGetDevicesMode, hwGetDevicesParameter, str);
        }
        return jfq.c().b(hwGetDevicesMode, hwGetDevicesParameter, str);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public Map<String, DeviceCapability> queryDeviceCapability(int i, String str, String str2) {
        LogUtil.a("DeviceMgrImpl", "queryDeviceCapability, queryType: ", Integer.valueOf(i), " , tag: ", str2);
        return jfq.c().a(i, str, str2);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getDevicesFromCloud(String str, IBaseResponseCallback iBaseResponseCallback) {
        jnw.e().d(str, iBaseResponseCallback);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void sendDeviceData(int i, int i2, Map<Integer, String> map, DeviceInfo deviceInfo, String str) {
        LogUtil.a("DeviceMgrImpl", "sendDeviceData serviceID:", Integer.valueOf(i), ", commandId:", Integer.valueOf(i2), " from :", str);
        if (i == 7) {
            jho.c(i2, map, deviceInfo);
        }
        if (i == 36 && i2 == 12 && map != null) {
            jiq.a(BaseApplication.getContext()).a(CommonUtil.w(map.get(1)), CommonUtil.w(map.get(2)));
        }
        if (i == 36 && ((i2 == 7 || i2 == 5) && map != null)) {
            jiq.a(BaseApplication.getContext()).b(i2, CommonUtil.w(map.get(1)), CommonUtil.w(map.get(2)));
        }
        if (i == 1 && i2 == 33) {
            joh.a().d(deviceInfo);
        }
        if (i == 1 && i2 == 8) {
            jog.c().d(deviceInfo.getDeviceIdentify());
        }
        if (i == 22 && i2 == 12) {
            jgw.d().a();
        }
        if (i == 12 && i2 == 2) {
            jis.b().c();
        }
        if (i == 5055 && i2 == 900100001) {
            jgi.e().c(deviceInfo, null, str);
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public int getDeviceStateById(DeviceStateConstants deviceStateConstants, String str, String str2, String str3) {
        LogUtil.a("DeviceMgrImpl", "getDeviceStateById: type: ", deviceStateConstants.name(), " mac: ", CommonUtil.l(str), " , tag: ", str3);
        int i = AnonymousClass3.b[deviceStateConstants.ordinal()];
        if (i == 1) {
            return jpy.b(str);
        }
        if (i == 2) {
            return jgq.d().d(str);
        }
        if (i == 3) {
            jfu.c(str2);
        }
        return -1;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public cuw getDeviceInfoUx(int i, String str) {
        LogUtil.a("DeviceMgrImpl", "getDeviceInfoUx: type: ", Integer.valueOf(i), " , tag: ", str);
        return jfu.c(i);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public String getDeviceSettingItemById(HwGetSetingItemConstants hwGetSetingItemConstants, String str, String str2) {
        LogUtil.a("DeviceMgrImpl", "getDeviceSettingItemById: deviceIdentify: ", CommonUtil.l(str), " , tag: ", str2);
        return hwGetSetingItemConstants == HwGetSetingItemConstants.RIM_SIZE ? jnr.b().a(str) : "";
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void downloadDeviceResources(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceMgrImpl", "downloadDeviceResources");
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(list, new c(iBaseResponseCallback));
    }

    static class c implements DownloadDeviceInfoCallBack {
        private final IBaseResponseCallback e;

        public c(IBaseResponseCallback iBaseResponseCallback) {
            this.e = iBaseResponseCallback;
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onSuccess() {
            LogUtil.a("DeviceMgrImpl", "downloadDeviceResources onSuccess");
            this.e.d(0, null);
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onFailure(int i) {
            LogUtil.a("DeviceMgrImpl", "downloadDeviceResources onFailure status = ", Integer.valueOf(i));
            this.e.d(i, null);
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void netWorkError() {
            LogUtil.a("DeviceMgrImpl", "downloadDeviceResources netWorkError");
        }

        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
        public void onDownload(int i) {
            this.e.d(20000, Integer.valueOf(i));
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void cancelDownloadDeviceResources() {
        LogUtil.a("DeviceMgrImpl", "cancelDownloadDeviceResources");
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void executeDeviceRelatedLogic(ExecuteMode executeMode, cul culVar, String str) {
        Object[] objArr = new Object[6];
        objArr[0] = "executeDeviceRelatedLogic mode:";
        objArr[1] = executeMode;
        objArr[2] = " param:";
        objArr[3] = culVar == null ? "" : culVar.toString();
        objArr[4] = " , tag: ";
        objArr[5] = str;
        LogUtil.a("DeviceMgrImpl", objArr);
        int i = AnonymousClass3.d[executeMode.ordinal()];
        if (i == 1) {
            jpn.a(2, false);
            return;
        }
        if (i == 2) {
            jfq.c().j();
            return;
        }
        if (i == 3) {
            a(culVar, str);
            return;
        }
        if (i == 4) {
            if (culVar == null || TextUtils.isEmpty(culVar.e())) {
                return;
            }
            d(culVar.e(), str);
            return;
        }
        LogUtil.a("DeviceMgrImpl", "executeDeviceRelatedLogic no suport mode:", executeMode, " , tag: ", str);
    }

    /* renamed from: com.huawei.hwdevice.outofprocess.impl.DeviceMgrImpl$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[ExecuteMode.values().length];
            d = iArr;
            try {
                iArr[ExecuteMode.CLEAR_AM_16_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[ExecuteMode.FORCE_INIT_PHONE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[ExecuteMode.NOTIFY_SEIZE_DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[ExecuteMode.NOTIFY_CONNECT_DEVICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[DeviceStateConstants.values().length];
            b = iArr2;
            try {
                iArr2[DeviceStateConstants.BATTERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[DeviceStateConstants.WEAR_PLACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[DeviceStateConstants.GET_BT_TYPE_BY_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void unPair(List<String> list, boolean z) {
        try {
            jfq.c().c(list, z);
        } catch (RemoteException unused) {
            LogUtil.h("DeviceMgrImpl", "unPair RemoteException ");
        }
    }

    private void a(cul culVar, String str) {
        if (culVar == null) {
            LogUtil.h("DeviceMgrImpl", "notifySeizeDevice param is null.");
            return;
        }
        LogUtil.a("DeviceMgrImpl", "notifySeizeDevice set device enable.");
        List<DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, str);
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null && deviceInfo.getDeviceIdentify().contains(culVar.e())) {
                deviceInfo.setDeviceActiveState(1);
                deviceInfo.setDeviceConnectState(1);
            }
        }
        jfv.a(deviceList, culVar.e());
    }

    private void d(String str, String str2) {
        LogUtil.a("DeviceMgrImpl", "connectParts enter,", " , tag: ", str2);
        List<DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, str2);
        if (deviceList == null || deviceList.size() == 0) {
            Object[] objArr = new Object[4];
            objArr[0] = "deviceInfoList is invalid.";
            objArr[1] = Boolean.valueOf(deviceList == null);
            objArr[2] = " , tag: ";
            objArr[3] = str2;
            LogUtil.h("DeviceMgrImpl", objArr);
            return;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.a("DeviceMgrImpl", "connectParts connectState: ", Integer.valueOf(deviceInfo.getDeviceConnectState()), " , tag: ", str2);
                if (deviceInfo.getDeviceConnectState() != 2) {
                    try {
                        jfq.c().e(deviceList, str);
                        return;
                    } catch (RemoteException unused) {
                        LogUtil.a("DeviceMgrImpl", "executeDeviceRelatedLogic NOTIFY_CONNECT_DEVICE RemoteException. ", str2);
                        return;
                    }
                }
            }
        }
        LogUtil.a("DeviceMgrImpl", "connectParts end,", " , tag: ", str2);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void startSleepBreatheDetectionPage() {
        BaseApplication.getActivity().runOnUiThread(new Runnable() { // from class: jpz
            @Override // java.lang.Runnable
            public final void run() {
                DeviceMgrImpl.e();
            }
        });
    }

    public static /* synthetic */ void e() {
        try {
            ReleaseLogUtil.b("R_DeviceMgrImpl", "startSleepBreatheDetectionPage runOnUiThread");
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.sleepbreathe.SleepBreatheActivity");
            intent.setFlags(268435456);
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("DeviceMgrImpl", "startSleepBreatheDetectionPage ActivityNotFoundException.");
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setSleepBreatheDetectionState(boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceMgrImpl", "isChecked ", Boolean.valueOf(z));
        jqi.a().setSwitchSetting("sleep_breathe_key", String.valueOf(z), new IBaseResponseCallback() { // from class: jqc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                DeviceMgrImpl.b(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    public static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("DeviceMgrImpl", "setSleepBreatheDetectionState, errorCode: ", Integer.valueOf(i));
        iBaseResponseCallback.d(i, null);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getSleepBreatheDetectionState(final IBaseResponseCallback iBaseResponseCallback) {
        jqi.a().getSwitchSetting("sleep_breathe_key", new IBaseResponseCallback() { // from class: jqg
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                DeviceMgrImpl.a(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    public static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("DeviceMgrImpl", " getSleepBreatheDetectionState, errorCode = ", Integer.valueOf(i));
        if (i == 0 && "1".equals(obj)) {
            obj = "true";
        }
        iBaseResponseCallback.d(i, obj);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public String getSwitchSetting(String str, String str2) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] strArr = {""};
        jqi.a().getSwitchSetting(str2, str, new IBaseResponseCallback() { // from class: jqb
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                DeviceMgrImpl.b(strArr, countDownLatch, i, obj);
            }
        });
        try {
            LogUtil.a("DeviceMgrImpl", "getSwitchSetting isSuccess:", Boolean.valueOf(countDownLatch.await(3L, TimeUnit.SECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("DeviceMgrImpl", "getSwitchSetting InterruptedException");
        }
        return strArr[0];
    }

    public static /* synthetic */ void b(String[] strArr, CountDownLatch countDownLatch, int i, Object obj) {
        LogUtil.a("DeviceMgrImpl", "getSwitchSetting onResponse errorCode:", Integer.valueOf(i), ", objData:", obj);
        if (i == 0 && (obj instanceof String)) {
            strArr[0] = (String) obj;
        }
        countDownLatch.countDown();
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public int getDeviceTypeId(DeviceInfo deviceInfo) {
        HiDeviceInfo b = jpp.b(deviceInfo);
        if (b != null) {
            return b.getDeviceType();
        }
        return 1;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public boolean isBindPhoneService() {
        return jez.e() != null;
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getHealthSwitch(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        jos.a(list, iBaseResponseCallback);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setHealthSwitch(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        jos.a(jSONObject, iBaseResponseCallback);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getEmotionAutoMonitorSwitch(IBaseResponseCallback iBaseResponseCallback) {
        jps.e(iBaseResponseCallback);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getStressAutoMonitorSwitch(IBaseResponseCallback iBaseResponseCallback) {
        jps.c(iBaseResponseCallback);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setEmotionAutoMonitorSwitch(String str) {
        jps.b(str);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void checkBindPhoneService() {
        if (isBindPhoneService()) {
            return;
        }
        if (ProcessUtil.d()) {
            jez.a(BaseApplication.getContext());
        } else {
            LogUtil.h("DeviceMgrImpl", "checkBindPhoneService failed");
        }
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void setEmotionRiskWarningSwitch(String str) {
        jps.a(str);
    }

    @Override // com.huawei.health.devicemgr.api.DeviceMgrApi
    public void getEmotionRiskWarningSwitch(IBaseResponseCallback iBaseResponseCallback) {
        jps.b(iBaseResponseCallback);
    }
}
