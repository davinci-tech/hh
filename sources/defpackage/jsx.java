package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jsx {
    private static jsx b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f14057a = BaseApplication.getContext();
    private izy e;

    private jsx() {
        this.e = null;
        LogUtil.h("HwDevicePartMgr", "Init HwDevicePartMgr.");
        this.e = izy.b(this.f14057a);
    }

    public static jsx c() {
        jsx jsxVar;
        synchronized (c) {
            if (b == null) {
                b = new jsx();
            }
            jsxVar = b;
        }
        return jsxVar;
    }

    public static boolean b(String str) {
        Date d;
        LogUtil.a("HwDevicePartMgr", "isAlreadyUpdated: lastTime", str);
        if (TextUtils.isEmpty(str) || (d = d(str)) == null) {
            return false;
        }
        return Math.abs(System.currentTimeMillis() - d.getTime()) <= 259200000;
    }

    private static Date d(String str) {
        try {
            return new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).parse(str);
        } catch (ParseException unused) {
            LogUtil.b("HwDevicePartMgr", "exception parseException");
            return null;
        }
    }

    public boolean c(DeviceInfo deviceInfo, JSONObject jSONObject) {
        if (deviceInfo == null || jSONObject == null) {
            LogUtil.h("HwDevicePartMgr", "isExistHiLinkDeviceId deviceInfo or jsonObject is null.");
            return true;
        }
        if ((CompileParameterUtil.a("IS_DEBUG_VERSION") || CompileParameterUtil.a("IS_BETA_VERSION", false)) && deviceInfo.getProductType() >= 55) {
            if (!jSONObject.has("hilink_device_id")) {
                LogUtil.a("HwDevicePartMgr", "isExistHiLinkDeviceId not exist hilink device id.");
                return false;
            }
            try {
                if (jSONObject.getString("hilink_device_id").length() != 4) {
                    LogUtil.a("HwDevicePartMgr", "isExistHiLinkDeviceId hilinkDeviceId length is error.");
                    return false;
                }
            } catch (JSONException unused) {
                LogUtil.b("HwDevicePartMgr", "isExistHiLinkDeviceId JSONException.");
                return false;
            }
        }
        LogUtil.a("HwDevicePartMgr", "isExistHiLinkDeviceId exist hilink device id.");
        return true;
    }

    public void bJm_(Intent intent, boolean z, List<DeviceInfo> list) {
        izf e;
        if (intent == null) {
            LogUtil.h("HwDevicePartMgr", "sendTimeSync intent is null");
            return;
        }
        if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()) || "android.intent.action.DATE_CHANGED".equals(intent.getAction()) || z) {
            LogUtil.a("HwDevicePartMgr", "System Time changed with type:", intent.getAction());
            List<String> a2 = jst.a();
            if (a2 == null || a2.isEmpty()) {
                return;
            }
            kkp.c().d();
            for (String str : a2) {
                if (jst.r(str) == 0) {
                    e = iyo.a(this.f14057a);
                } else {
                    DeviceCapability a3 = a(str, list);
                    if (a3 != null && a3.isSupportZoneId()) {
                        LogUtil.a("HwDevicePartMgr", "support zone id:", Boolean.valueOf(a3.isSupportZoneId()));
                        e = iyo.o();
                    } else {
                        e = iyo.e(false);
                    }
                }
                if (str.length() == 0) {
                    return;
                }
                if (e != null) {
                    e.e(str);
                }
                if (this.e != null) {
                    LogUtil.a("HwDevicePartMgr", "Start to set device time when system change.");
                    this.e.c(d(str, list), e);
                }
            }
        }
    }

    private DeviceCapability a(String str, List<DeviceInfo> list) {
        DeviceInfo e = e(str, list);
        if (e == null) {
            LogUtil.h("HwDevicePartMgr", "getCapability deviceInfo is null");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, e.getDeviceIdentify(), "HwDevicePartMgr");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(e.getDeviceIdentify());
    }

    private String b() {
        return SharedPreferenceManager.b(this.f14057a, String.valueOf(1018), "deviceAutoRemoveIdentify");
    }

    public void e(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwDevicePartMgr", "enter handleGetGoldCard.");
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "handleGetGoldCard btDeviceInfo is null.");
            return;
        }
        if (iyo.c(this.f14057a, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDevicePartMgr", "handleGetGoldCard timeout.");
            return;
        }
        LogUtil.a("HwDevicePartMgr", "Start to handle handleGetGoldCard.");
        boolean h = iyo.h(bArr);
        DeviceCapability d = jst.d(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwDevicePartMgr", " , isSupportGold :", Boolean.valueOf(h));
        if (h) {
            d.configureGoldCard(true);
        } else {
            d.configureGoldCard(false);
        }
    }

    public void e(List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "EMUI sendDisconnectBroadcastToEmui.");
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwDevicePartMgr", "btDeviceInfoList is null.");
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            if (!deviceInfo.getDeviceIdentify().equalsIgnoreCase("AndroidWear")) {
                if (!TextUtils.isEmpty(deviceInfo.getNodeId())) {
                    return;
                }
                try {
                    Intent intent = new Intent("com.huawei.iconnect.action.DEVICE_BOND_STATE_CHANGED");
                    intent.setComponent(new ComponentName("com.huawei.iconnect", "com.huawei.iconnect.MessageIntentService"));
                    if (jss.b()) {
                        intent.putExtra("DEVICE_ID", jss.e(deviceInfo.getDeviceIdentify()));
                    } else {
                        intent.putExtra("com.huawei.iconnect.extra.DEVICE_MAC_ADDRESS", deviceInfo.getDeviceIdentify());
                    }
                    intent.putExtra("android.bluetooth.device.extra.BOND_STATE", 10);
                    intent.putExtra("com.huawei.iconnect.extra.PACKAGE_NAME", "com.huawei.health");
                    this.f14057a.startService(intent);
                } catch (SecurityException unused) {
                    LogUtil.b("HwDevicePartMgr", "0xA0200008", ",securityException");
                }
                LogUtil.c("HwDevicePartMgr", "EMUI SendDisconnectBroadcastToEmui, send BOND_NONE broadcast, DeviceIdentify", iyl.d().e(deviceInfo.getDeviceIdentify()));
            } else {
                LogUtil.c("HwDevicePartMgr", "EMUI SendDisconnectBroadcastToEmui, don't need send broadcast for AndroidWear");
            }
        }
    }

    public void a(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            LogUtil.h("HwDevicePartMgr", "refreshLeoProblemCapability deviceCapability is null.");
            return;
        }
        deviceCapability.resetDeviceCapability();
        deviceCapability.configureSupportTimeSetting(false);
        deviceCapability.configureSupportGetFirmwareVersion(true);
        deviceCapability.configureSupportGetBattery(true);
        deviceCapability.configureAutoLightScreen(false);
        deviceCapability.configureAvoidDisturb(false);
        deviceCapability.configureFactoryReset(false);
        deviceCapability.configureSupportPairDevice(false);
        deviceCapability.configureSupportGetHandsetInfo(true);
        deviceCapability.configureSupportNotificationIntervalInfo(true);
        deviceCapability.configureSupportActivityType(true);
        deviceCapability.configureSupportAuthenticDevice(true);
        deviceCapability.configureGoldCard(true);
        deviceCapability.configureMessageAlert(false);
        deviceCapability.configureSupportMessageAlertInfo(false);
        deviceCapability.configureSupportMessageSupportInfo(false);
        deviceCapability.configureSupportWearMessagePush(false);
        deviceCapability.configureContacts(false);
        deviceCapability.configureSupportCallingOperationType(false);
        deviceCapability.configureMotionGoalCap(1);
        deviceCapability.configureFitnessFrameType(3);
        deviceCapability.configureActivityReminder(false);
        deviceCapability.configureSupportSetUserInfoEncrypt(false);
        deviceCapability.configureSupportSampleFrame(true);
        deviceCapability.configureSupportThreshold(true);
        deviceCapability.configureReserveSync(true);
        deviceCapability.configureIsSupportHeartRateZone(true);
        deviceCapability.configureSupportGetUserInfo(true);
        deviceCapability.configureIsSupportCoreSleep(false);
        deviceCapability.configureisSupportHeartRateEnable(false);
        deviceCapability.configureIsSupportSendCoreSleepOutState(false);
        deviceCapability.configureIsSupportQueryDeviceCoreSleepSwitch(false);
        deviceCapability.configureEventAlarm(false);
        deviceCapability.configureEventAlarmNum(0);
        deviceCapability.configureSmartAlarm(false);
        deviceCapability.configureOtaUpdate(false);
        deviceCapability.configureMaintenance(true);
        deviceCapability.configureMaintenanceInTime(false);
        deviceCapability.configureMaintenanceGetData(false);
        deviceCapability.configureSupportAntiLost(false);
        deviceCapability.configureBluetoothOffAlert(false);
        deviceCapability.configureLanguage(true);
        deviceCapability.configureWeatherPush(false);
        deviceCapability.configureSupportUnitWeather(false);
        deviceCapability.configureAtmosphereSupportExpand(false);
        deviceCapability.configureWeatherSupportErrorCode(false);
        deviceCapability.configureSupportExerciseAdvice(true);
        c(deviceCapability);
    }

    public void c(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            LogUtil.h("HwDevicePartMgr", "refreshLeoProblemCapabilityPart deviceCapability is null.");
            return;
        }
        deviceCapability.configureSupportExerciseAdviceTime(true);
        deviceCapability.configureSupportExerciseAdviceMonitor(false);
        deviceCapability.configureSupportWorkout(false);
        deviceCapability.configureSupportWorkoutInfo(false);
        deviceCapability.configureSupportWorkoutReminder(false);
        deviceCapability.configureSupportWorkoutRecord(true);
        deviceCapability.configureSupportWorkoutExerciseDisplayLink(false);
        deviceCapability.configureSupportWorkoutRecordPaceMap(false);
        deviceCapability.configureSupportGpsLocation(false);
        deviceCapability.configureSupportGpsData(false);
        deviceCapability.configureSupportGpsSetParameter(false);
        deviceCapability.configureSupportHeartRateInfo(false);
        deviceCapability.configureSleep(false);
        deviceCapability.configureClimb(true);
        deviceCapability.configureRiding(false);
        deviceCapability.configureStand(false);
        deviceCapability.configureSleepShallow(false);
        deviceCapability.configureSleepDeep(false);
        deviceCapability.configureWalk(true);
        deviceCapability.configureRun(true);
        deviceCapability.configureStep(true);
        deviceCapability.configureCalorie(true);
        deviceCapability.configureDistance(true);
        deviceCapability.configureIsSupportHeartRate(false);
        deviceCapability.configurePromptPush(0);
        deviceCapability.configureCallMute(false);
        deviceCapability.configureIsSupportHelp(true);
        deviceCapability.configureDistanceDetail(true);
        deviceCapability.configureSupportPay(true);
        deviceCapability.configureSupportEsim(false);
        deviceCapability.configureSupportMultiSim(false);
        deviceCapability.configureRotateSwitchScreen(false);
        deviceCapability.configureSupportLeftRightHandWearMode(false);
        deviceCapability.configureSupportStress(false);
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        if (str.isEmpty()) {
            LogUtil.a(a.t, 1, "HwDevicePartMgr", "saveAutoRemoveDevice clear auto remove device info");
        }
        LogUtil.a("HwDevicePartMgr", "saveAutoRemoveDevice identify", iyl.d().e(str));
        SharedPreferenceManager.e(this.f14057a, String.valueOf(1018), "deviceAutoRemoveIdentify", str, new StorageParams(1));
    }

    public void a() {
        a("");
    }

    public void d(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        if (list == null || deviceInfo == null || list.isEmpty()) {
            LogUtil.h("HwDevicePartMgr", "deviceInfoList or btDeviceInfo is null.");
            return;
        }
        boolean z = false;
        for (DeviceInfo deviceInfo2 : list) {
            if (deviceInfo2.getDeviceActiveState() == 1 && deviceInfo2.getAutoDetectSwitchStatus() == 0 && !deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.a("HwDevicePartMgr", "restoreAutoRemoveDeviceConnect has other band connected");
                z = true;
            }
        }
        if (z) {
            return;
        }
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        a();
        DeviceInfo deviceInfo3 = null;
        for (DeviceInfo deviceInfo4 : list) {
            if (b2.equalsIgnoreCase(deviceInfo4.getDeviceIdentify())) {
                LogUtil.a("HwDevicePartMgr", "restoreAutoRemoveDeviceConnect find ");
                deviceInfo4.setDeviceActiveState(1);
                deviceInfo3 = deviceInfo4;
            }
        }
        if (deviceInfo3 != null && deviceInfo3.getDeviceBluetoothType() == 1) {
            this.e.e(deviceInfo3, true);
        } else {
            this.e.e(true, b2);
            this.e.j(b2);
        }
    }

    public boolean c(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "handleAutoDetectSwitchStatusAndWorkMode ", ",has already connected device goingDisconnectDevice :", deviceInfo);
        if (list != null && deviceInfo != null && !list.isEmpty()) {
            for (DeviceInfo deviceInfo2 : list) {
                if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                    LogUtil.a("HwDevicePartMgr", "handleAutoDetectSwitchStatusAndWorkMode find ");
                    deviceInfo2.setDeviceActiveState(0);
                }
            }
            izy izyVar = this.e;
            if (izyVar != null) {
                izyVar.d(deviceInfo.getDeviceIdentify());
                this.e.e(false, deviceInfo.getDeviceIdentify());
                this.e.f(deviceInfo.getDeviceIdentify());
                this.e.o(deviceInfo.getDeviceIdentify());
            }
        }
        return false;
    }

    public List<DeviceInfo> b(List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "Enter getConnectedDeviceInfo");
        ArrayList arrayList = new ArrayList(24);
        if (list != null && !list.isEmpty()) {
            for (DeviceInfo deviceInfo : list) {
                if (deviceInfo != null && deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() == 2) {
                    ReleaseLogUtil.e("R_HwDevicePartMgr", "getConnectedDevices deviceName:", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        return arrayList;
    }

    public List<Integer> d(List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "Enter getActiveDevicesIndex().");
        ArrayList arrayList = new ArrayList(24);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDeviceActiveState() == 1) {
                    LogUtil.a("HwDevicePartMgr", "active device name:", list.get(i).getDeviceName());
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        return arrayList;
    }

    public boolean c(String str, List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "Enter checkActiveDevice.");
        List<Integer> d = d(list);
        if (d == null || list == null || d.isEmpty() || list.isEmpty()) {
            return false;
        }
        Iterator<Integer> it = d.iterator();
        while (it.hasNext()) {
            if (list.get(it.next().intValue()).getDeviceIdentify().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public int b(String str, List<DeviceInfo> list) {
        if (str == null || list == null || list.size() == 0) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equalsIgnoreCase(list.get(i).getDeviceIdentify())) {
                return i;
            }
        }
        return -1;
    }

    public DeviceInfo a(DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2 = new DeviceInfo();
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "createDeviceInfo devInfo is null");
            return deviceInfo2;
        }
        deviceInfo2.setBluetoothVersion(deviceInfo.getBluetoothVersion());
        deviceInfo2.setDeviceActiveState(deviceInfo.getDeviceActiveState());
        deviceInfo2.setDeviceConnectState(deviceInfo.getDeviceConnectState());
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setProductType(deviceInfo.getProductType());
        deviceInfo2.setLastConnectedTime(deviceInfo.getLastConnectedTime());
        deviceInfo2.setDeviceIdentify(deviceInfo.getDeviceIdentify());
        deviceInfo2.setAuthVersion(deviceInfo.getAuthVersion());
        deviceInfo2.setAutoDetectSwitchStatus(deviceInfo.getAutoDetectSwitchStatus());
        deviceInfo2.setFootWearPosition(deviceInfo.getFootWearPosition());
        deviceInfo2.setDeviceModel(deviceInfo.getDeviceModel());
        deviceInfo2.setSoftVersion(deviceInfo.getSoftVersion());
        if (TextUtils.isEmpty(deviceInfo.getUuid())) {
            deviceInfo2.setUuid(deviceInfo.getDeviceIdentify());
        } else {
            deviceInfo2.setUuid(deviceInfo.getUuid());
        }
        deviceInfo2.setDeviceIdType(deviceInfo.getDeviceIdType());
        deviceInfo2.setDeviceProtocol(deviceInfo.getDeviceProtocol());
        deviceInfo2.setEncryptType(deviceInfo.getEncryptType());
        deviceInfo2.setDeviceBluetoothType(deviceInfo.getDeviceBluetoothType());
        deviceInfo2.setNodeId(deviceInfo.getNodeId());
        deviceInfo2.setPowerSaveModel(deviceInfo.getPowerSaveModel());
        deviceInfo2.setUdidFromDevice(deviceInfo.getUdidFromDevice());
        deviceInfo2.setUnConvertedUdid(deviceInfo.getUnConvertedUdid());
        deviceInfo2.setSupportAccountSwitch(deviceInfo.getSupportAccountSwitch());
        deviceInfo2.setCertModel(deviceInfo.getCertModel());
        deviceInfo2.setManufacture(deviceInfo.getManufacture());
        deviceInfo2.setDeviceUdid(deviceInfo.getDeviceUdid());
        deviceInfo2.setHiLinkDeviceId(deviceInfo.getHiLinkDeviceId());
        deviceInfo2.setVersionType(deviceInfo.getVersionType());
        deviceInfo2.setExpandCapability(deviceInfo.getExpandCapability());
        deviceInfo2.setWearEngineDeviceId(deviceInfo.getWearEngineDeviceId());
        deviceInfo2.setMultiLinkBleMac(deviceInfo.getMultiLinkBleMac());
        if (deviceInfo.getProductType() == 10 && (!TextUtils.isEmpty(deviceInfo.getDeviceName()) || !TextUtils.isEmpty(deviceInfo.getDeviceModel()))) {
            deviceInfo2.setDeviceModel(deviceInfo.getDeviceModel());
            LogUtil.a("HwDevicePartMgr", "device name:", deviceInfo.getDeviceName(), ",deviceModel:", deviceInfo.getDeviceName());
            if (TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN") || TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN")) {
                deviceInfo2.setDeviceName("PORSCHE DESIGN");
            }
        }
        return deviceInfo2;
    }

    public List<Integer> c(List<DeviceInfo> list, String str) {
        LogUtil.a("HwDevicePartMgr", "findWantedSwitchDevice enter");
        ArrayList arrayList = new ArrayList(24);
        if (list == null) {
            LogUtil.h("HwDevicePartMgr", "findWantedSwitchDevice targetDevices is null");
            return arrayList;
        }
        for (int i = 0; i < list.size(); i++) {
            DeviceInfo deviceInfo = list.get(i);
            if (deviceInfo != null && deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() != 2) {
                if (deviceInfo.getDeviceIdentify().equals(str)) {
                    LogUtil.a("HwDevicePartMgr", "findWantedSwitchDevice find ", deviceInfo.getDeviceName());
                    arrayList.add(Integer.valueOf(i));
                } else {
                    LogUtil.h("HwDevicePartMgr", "manual reconnect:", iyl.d().e(str), ",traversal:", iyl.d().e(deviceInfo.getDeviceIdentify()));
                }
            }
        }
        return arrayList;
    }

    public void e(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        Object[] objArr = new Object[4];
        objArr[0] = "Enter handleSameTypeDevice(). deviceInfo isNull :";
        objArr[1] = Boolean.valueOf(deviceInfo == null);
        objArr[2] = ", deviceInfoList isNull :";
        objArr[3] = Boolean.valueOf(list == null);
        LogUtil.a("HwDevicePartMgr", objArr);
        kcw a2 = kcw.a();
        if (deviceInfo == null || list == null) {
            return;
        }
        if (a2.c(deviceInfo.getProductType(), deviceInfo.getDeviceIdentify())) {
            LogUtil.a("HwDevicePartMgr", "handleSameTypeDevice device is follow and is not aw70");
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            for (DeviceInfo deviceInfo2 : list) {
                if (cvt.c(deviceInfo2.getProductType()) && deviceInfo2.getDeviceActiveState() == 1 && !deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                    LogUtil.a("HwDevicePartMgr", "handleSameTypeDevice has active aw70 device");
                    c(deviceInfo2);
                }
            }
            return;
        }
        for (DeviceInfo deviceInfo3 : list) {
            if (!cvt.a(deviceInfo3.getProductType(), deviceInfo3.getAutoDetectSwitchStatus()) && deviceInfo3.getDeviceActiveState() == 1 && !deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo3.getDeviceIdentify()) && !a2.c(deviceInfo3.getProductType(), deviceInfo3.getDeviceIdentify())) {
                LogUtil.a("HwDevicePartMgr", "handleSameTypeDevice has active band mode device");
                c(deviceInfo3);
            }
        }
    }

    private void c(DeviceInfo deviceInfo) {
        deviceInfo.setDeviceActiveState(0);
        izy izyVar = this.e;
        if (izyVar != null) {
            izyVar.o(deviceInfo.getDeviceIdentify());
            this.e.i(deviceInfo.getDeviceIdentify());
        }
    }

    public boolean b(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        if (list != null && deviceInfo != null) {
            for (DeviceInfo deviceInfo2 : list) {
                if (deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && deviceInfo2.getDeviceActiveState() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int c(int i, String str, List<DeviceInfo> list) {
        if (i != -1 || str == null) {
            return i;
        }
        for (DeviceInfo deviceInfo : list) {
            if (str.equals(deviceInfo.getDeviceIdentify())) {
                return deviceInfo.getProductType();
            }
        }
        return i;
    }

    public void b(DeviceInfo deviceInfo, byte[] bArr, List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "Enter reportConnectFail()");
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "reportConnectFail btDeviceInfo is null");
            return;
        }
        jst.e(deviceInfo.getDeviceIdentify(), 4);
        deviceInfo.setDeviceConnectState(4);
        int b2 = b(deviceInfo.getDeviceIdentify(), list);
        if (b2 != -1 && list != null) {
            LogUtil.a("HwDevicePartMgr", "Update DeviceInfo state");
            list.get(b2).setDeviceConnectState(4);
        }
        izy izyVar = this.e;
        if (izyVar != null) {
            izyVar.b(deviceInfo, bArr);
            this.e.d(deviceInfo.getDeviceIdentify());
        }
    }

    public void c(DeviceInfo deviceInfo, byte[] bArr, JSONObject jSONObject, int i, List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "handleGetProductTypeContent() enter.");
        if (deviceInfo == null || jSONObject == null) {
            LogUtil.h("HwDevicePartMgr", "handleGetProductTypeContent() btDeviceInfo or jsonObject is null");
            return;
        }
        try {
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            jst.f(deviceIdentify, deviceIdentify);
            if (jSONObject.has("UUID")) {
                jst.s(deviceInfo.getDeviceIdentify(), jSONObject.getString("UUID"));
                deviceInfo.setUuid(jSONObject.getString("UUID"));
            }
            if (jSONObject.has("force_sn")) {
                deviceInfo.setDeviceIdType(jSONObject.getInt("force_sn"));
                jst.k(deviceInfo.getDeviceIdentify(), d(deviceInfo, i));
            }
            deviceInfo.setProductType(i);
            if (!c(deviceInfo, jSONObject)) {
                LogUtil.a("HwDevicePartMgr", "not exist hilink device id.");
                b(deviceInfo, bArr, list);
                izy izyVar = this.e;
                if (izyVar != null) {
                    izyVar.l();
                    return;
                }
                return;
            }
            if (i == -1) {
                LogUtil.a("HwDevicePartMgr", "Get Wrong Version Command.");
                b(deviceInfo, bArr, list);
                return;
            }
            LogUtil.a("HwDevicePartMgr", "the productType is not HUAWEI_UNKNOWN.");
            izf e = iyo.e(false);
            e.e(deviceInfo.getDeviceIdentify());
            if (this.e != null) {
                LogUtil.a("HwDevicePartMgr", "Start to set device time.");
                this.e.c(deviceInfo.getDeviceBluetoothType(), e);
            }
        } catch (JSONException unused) {
            LogUtil.b("HwDevicePartMgr", "0xA0200008", ",handleGetProductTypeContent jsonException.");
        }
    }

    public String d(DeviceInfo deviceInfo, int i) {
        String n;
        LogUtil.a("HwDevicePartMgr", "getPhdDeviceUdid enter");
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "getPhdDeviceUdid() btDeviceInfo is null");
            return null;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (deviceIdentify == null) {
            return null;
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        String str = securityDeviceId + deviceIdentify;
        if (!deviceIdentify.equals(securityDeviceId)) {
            deviceIdentify = str;
        }
        if (i >= 34 && (!Utils.o() || CommonUtil.cg())) {
            n = knl.a(deviceIdentify);
        } else {
            n = jsd.n(securityDeviceId);
        }
        if (n != null) {
            return n;
        }
        LogUtil.h("HwDevicePartMgr", "shaDeviceId is null");
        return null;
    }

    public void e(DeviceInfo deviceInfo, byte[] bArr, List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "Start to check GATT Response.");
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "handleSetDeviceTime() btDeviceInfo is null");
            return;
        }
        if (iyo.c(this.f14057a, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDevicePartMgr", "GATT Command send timeout.");
            b(deviceInfo, bArr, list);
            return;
        }
        boolean[] e = iyo.e(this.f14057a, bArr);
        if (!e[0]) {
            LogUtil.h("HwDevicePartMgr", "GATT Time Set fail.");
            b(deviceInfo, bArr, list);
            return;
        }
        LogUtil.a("HwDevicePartMgr", "handleSetDeviceTime:", e, ",isNeedRetry:", Boolean.valueOf(e[1]));
        if (e[1]) {
            b(deviceInfo);
            return;
        }
        jst.d(deviceInfo.getDeviceIdentify()).resetDeviceCapability();
        if (2 == deviceInfo.getDeviceProtocol()) {
            izf b2 = iyo.b(this.f14057a);
            b2.e(deviceInfo.getDeviceIdentify());
            if (this.e != null) {
                LogUtil.a("HwDevicePartMgr", "Start to get device service id list info. Command:", cvx.e(b2.g()), ", ", cvx.e(b2.a()), ", ", cvx.d(b2.d()));
                this.e.c(deviceInfo.getDeviceBluetoothType(), b2);
            }
        }
    }

    public void c(DeviceInfo deviceInfo, byte[] bArr, List<DeviceInfo> list) {
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "handleGetServiceIdListInfo() btDeviceInfo is null");
            return;
        }
        if (iyo.c(this.f14057a, deviceInfo.getDeviceProtocol(), bArr)) {
            LogUtil.a("HwDevicePartMgr", "Get device service id list info send timeout.");
            b(deviceInfo, bArr, list);
            return;
        }
        izf b2 = iyo.b(this.f14057a, iyo.d(this.f14057a, bArr));
        if (b2 != null) {
            int r = jst.r(deviceInfo.getDeviceIdentify());
            String l = jst.l(deviceInfo.getDeviceIdentify());
            String f = jst.f(deviceInfo.getDeviceIdentify());
            if (r == 10 && "73617766697368".equals(l) && "372E312E31".equals(f)) {
                LogUtil.h("HwDevicePartMgr", "refresh.");
                b2.e(cvx.a("0103814E020101030C040708090A0D0E1011121314020107030B01050708090A0E1013161502010A030301090A02010C03010102011603030103070201170306010406070B0C02011903010102011B030101"));
                b2.e(82);
            }
            b2.e(deviceInfo.getDeviceIdentify());
            if (this.e != null) {
                LogUtil.a("HwDevicePartMgr", "start to get device command id list info.command", cvx.e(b2.g()), ", ", cvx.e(b2.a()), ", ", cvx.d(b2.d()));
                this.e.c(deviceInfo.getDeviceBluetoothType(), b2);
                return;
            }
            return;
        }
        LogUtil.h("HwDevicePartMgr", "btDeviceCommand is null.");
    }

    public void a(List<DeviceInfo> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwDevicePartMgr", "unPairBtDevices deviceList is null.");
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && deviceInfo.getDeviceBluetoothType() == 2 && deviceInfo.getDeviceActiveState() == 0 && !iyl.d().c(deviceInfo.getDeviceIdentify())) {
                LogUtil.h("HwDevicePartMgr", "removeBTDeviceInstance Remove bond device fail.");
            }
        }
    }

    public void a(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        LogUtil.a("HwDevicePartMgr", "EMUI SendConnectBroadcastToEmui, btDeviceInfo.getDeviceConnectState, ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        mbo.a().a(deviceInfo);
        d(deviceInfo);
        kkg.e(deviceInfo);
        kkg.a(deviceInfo);
        kkg.d(deviceInfo);
        kkk.a(this.f14057a, deviceInfo);
        kjk.e(deviceInfo);
        kcm.a(deviceInfo);
        if (deviceInfo == null) {
            LogUtil.h("HwDevicePartMgr", "sendConnectBroadcastToEmui() btDeviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 2) {
            ReleaseLogUtil.e("HWWEAR_HwDevicePartMgr", "Enter sendConnectStateBroadcast isScreenOn:", Boolean.valueOf(ScreenUtil.a()));
            a(list);
            if (!deviceInfo.getDeviceIdentify().equalsIgnoreCase("AndroidWear")) {
                if (TextUtils.isEmpty(deviceInfo.getNodeId())) {
                    try {
                        Intent intent = new Intent("com.huawei.iconnect.action.DEVICE_BOND_STATE_CHANGED");
                        intent.setComponent(new ComponentName("com.huawei.iconnect", "com.huawei.iconnect.MessageIntentService"));
                        if (jss.b()) {
                            intent.putExtra("DEVICE_ID", jss.e(deviceInfo.getDeviceIdentify()));
                        } else {
                            intent.putExtra("com.huawei.iconnect.extra.DEVICE_MAC_ADDRESS", deviceInfo.getDeviceIdentify());
                        }
                        intent.putExtra("android.bluetooth.device.extra.BOND_STATE", 12);
                        intent.putExtra("com.huawei.iconnect.extra.PACKAGE_NAME", "com.huawei.health");
                        this.f14057a.startService(intent);
                    } catch (SecurityException unused) {
                        LogUtil.b("HwDevicePartMgr", "0xA0200008", ",securityException");
                    }
                    LogUtil.c("HwDevicePartMgr", "EMUI SendConnectBroadcastToEmui, send BOND_BONDED broadcast, getDeviceIdentify:", iyl.d().e(deviceInfo.getDeviceIdentify()));
                    return;
                }
                return;
            }
            LogUtil.c("HwDevicePartMgr", "EMUI SendConnectBroadcastToEmui, don't need send broadcast for AndroidWear");
        }
    }

    private void d(DeviceInfo deviceInfo) {
        LogUtil.a("HwDevicePartMgr", "notifyRemoteCameraCapability enter");
        if (deviceInfo.getDeviceConnectState() != 2) {
            return;
        }
        kin b2 = kin.b();
        boolean a2 = b2.a(deviceInfo);
        LogUtil.a("HwDevicePartMgr", "notifyRemoteCameraCapability isSupportControlCapability:", Boolean.valueOf(a2));
        if (a2) {
            b2.e(deviceInfo);
            return;
        }
        DeviceCapability e = e(deviceInfo);
        if (e == null || !e.isSupportRemoteCamera()) {
            LogUtil.h("HwDevicePartMgr", "device not support remoteCamera.");
            return;
        }
        if (CommonUtil.bi() || (CommonUtil.bf() && !Utils.o())) {
            LogUtil.a("HwDevicePartMgr", "isMoreThanEmui80");
            b(deviceInfo, 1, 42, new byte[]{1, 1, 0});
        } else {
            LogUtil.a("HwDevicePartMgr", "lower than Emui8.1");
        }
    }

    private void b(DeviceInfo deviceInfo, int i, int i2, byte[] bArr) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        deviceCommand.setDataLen(bArr.length);
        deviceCommand.setDataContent(bArr);
        deviceCommand.setPriority(1);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("notifyDeviceCapability", "sendDeviceData command", deviceCommand.toString());
        ByteBuffer allocate = ByteBuffer.allocate(deviceCommand.getDataLen() + 2);
        allocate.put(cvx.a(cvx.e(deviceCommand.getServiceID())));
        allocate.put(cvx.a(cvx.e(deviceCommand.getCommandID())));
        if (deviceCommand.getDataContent() != null) {
            allocate.put(deviceCommand.getDataContent());
            LogUtil.a("HwDevicePartMgr", "command data", cvx.d(deviceCommand.getDataContent()));
        } else {
            LogUtil.h("HwDevicePartMgr", "command data is null");
        }
        allocate.flip();
        izf izfVar = new izf();
        izfVar.e(allocate.array());
        izfVar.e(allocate.array().length);
        izfVar.e(deviceCommand.getNeedAck());
        izfVar.b(deviceCommand.getPriority());
        izfVar.e(deviceCommand.getmIdentify());
        izfVar.a(deviceCommand.getNeedEncrypt());
        izfVar.i(i);
        izfVar.d(i2);
        this.e.c(deviceInfo.getDeviceBluetoothType(), izfVar);
    }

    private int d(String str, List<DeviceInfo> list) {
        DeviceInfo deviceInfo;
        int b2 = b(str, list);
        if (list == null || b2 == -1 || b2 >= list.size() || (deviceInfo = list.get(b2)) == null) {
            return -1;
        }
        return deviceInfo.getDeviceBluetoothType();
    }

    private DeviceInfo e(String str, List<DeviceInfo> list) {
        int b2 = b(str, list);
        if (list == null || b2 == -1 || b2 >= list.size()) {
            return null;
        }
        DeviceInfo deviceInfo = list.get(b2);
        return deviceInfo != null ? deviceInfo : deviceInfo;
    }

    public boolean b(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null || bArr == null) {
            LogUtil.h("HwDevicePartMgr", "handlerSetTimeRetry deviceInfo or dataContents is null");
            return false;
        }
        if (bArr[0] == 1 && bArr[1] == 5) {
            LogUtil.a("HwDevicePartMgr", "otherCommandReceivedMethod 1.5");
            boolean[] e = iyo.e(this.f14057a, bArr);
            if (!e[0]) {
                LogUtil.h("HwDevicePartMgr", "GATT Time Set fail.");
                return false;
            }
            LogUtil.a("HwDevicePartMgr", "handleSetDeviceTime:", e, ",isNeedRetry:", Boolean.valueOf(e[1]));
            if (e[1]) {
                b(deviceInfo);
                return true;
            }
        }
        return false;
    }

    private void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null || this.e == null) {
            LogUtil.h("HwDevicePartMgr", "sendGattTimeRetry deviceInfo or mBtSdkApi is null");
            return;
        }
        izf e = iyo.e(true);
        LogUtil.a("HwDevicePartMgr", "resend set time Command:", e);
        e.e(deviceInfo.getDeviceIdentify());
        this.e.c(deviceInfo.getDeviceBluetoothType(), e);
    }

    public void e() {
        izy izyVar;
        if (this.f14057a != null && (izyVar = this.e) != null) {
            izyVar.f();
        }
        d();
    }

    private static void d() {
        synchronized (c) {
            b = null;
        }
    }

    private DeviceCapability e(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwDevicePartMgr");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }
}
