package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.device.Device;
import defpackage.tqz;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Sha256;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class tqy {
    private static final String[] e = {"Vidar-"};

    public static int e(List<cwd> list, int i) {
        cwd d;
        if (list == null || list.size() == 0 || (d = d(list, i)) == null) {
            return -1;
        }
        return b(d);
    }

    public static cwd d(List<cwd> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == i) {
                return cwdVar;
            }
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "getTlvByTag tag:", Integer.valueOf(i), " tlv is null");
        return null;
    }

    public static int b(cwd cwdVar) {
        if (cwdVar == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "handleNum tlv is null");
            return -1;
        }
        String c = cwdVar.c();
        int w = TextUtils.isEmpty(c) ? -1 : CommonUtil.w(c);
        LogUtil.a("WearEngine_HiWearCommonUtil", "handleNum num is:", Integer.valueOf(w));
        return w;
    }

    public static String c(cwd cwdVar) {
        String str = "";
        if (cwdVar == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "handleString tlv is null");
            return "";
        }
        String c = cwdVar.c();
        if (!TextUtils.isEmpty(c)) {
            String e2 = cvx.e(c);
            if (e2 == null) {
                LogUtil.a("WearEngine_HiWearCommonUtil", "handleString hexToString value is null");
            } else {
                str = e2;
            }
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "handleString value is:", str);
        return str;
    }

    public static List<cwd> a(byte[] bArr) {
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "info data is error");
            return null;
        }
        String substring = d.substring(4);
        LogUtil.a("WearEngine_HiWearCommonUtil", "tlvsString is:", substring);
        try {
            return new cwl().a(substring).e();
        } catch (cwg unused) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "getResponseTlvFather TlvException");
            return null;
        }
    }

    public static List<Device> c(List<DeviceInfo> list, String str) {
        Device e2;
        ArrayList arrayList = new ArrayList(16);
        if (list != null && !list.isEmpty()) {
            for (DeviceInfo deviceInfo : list) {
                if (!m(deviceInfo) || b(str)) {
                    if (!c(deviceInfo, str) && (e2 = e(deviceInfo, str)) != null) {
                        LogUtil.a("WearEngine_HiWearCommonUtil", "device is bonded, deviceName is ", e2.getName());
                        b(deviceInfo, e2, str);
                        arrayList.add(e2);
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean c(DeviceInfo deviceInfo, String str) {
        boolean z = false;
        if (deviceInfo == null || TextUtils.isEmpty(str)) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "isFilterPowerSaveModelDevice params is null or empty");
            return false;
        }
        if (deviceInfo.getPowerSaveModel() != 1) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "isFilterPowerSaveModelDevice powerSaveModel off");
            return false;
        }
        if ("com.huawei.deveco.assistant".equals(str)) {
            return false;
        }
        int d = tri.d(str);
        LogUtil.a("WearEngine_HiWearCommonUtil", "isFilterPowerSaveModelDevice clientApiLevel is ", Integer.valueOf(d));
        if (d == 0) {
            z = !b(str);
        } else if (d < 12) {
            z = true;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "isFilterPowerSaveModelDevice : ", Boolean.valueOf(z));
        return z;
    }

    public static void d(Device device, DeviceInfo deviceInfo, boolean z) {
        int c;
        int d;
        if (device == null || deviceInfo == null) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "setDetailCapability device or deviceInfo isnull");
            return;
        }
        try {
            tqz tqzVar = new tqz(tqz.b(device));
            int b = tog.b(deviceInfo, z);
            if (d(deviceInfo.getDeviceModel())) {
                c = 0;
                d = 0;
            } else {
                c = tog.c(deviceInfo);
                d = tog.d(deviceInfo);
            }
            int i = tog.i(deviceInfo);
            tqzVar.c(b);
            tqzVar.e(c);
            tqzVar.b(d);
            tqzVar.d(i);
            device.setReservedness(tqzVar.toString());
        } catch (JSONException unused) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "setDetailCapability new DeviceJson exception");
        }
    }

    public static boolean d(DeviceInfo deviceInfo, boolean z) {
        int c;
        int d;
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "isSupportCommonDeviceCapability deviceInfo is null");
            return false;
        }
        int b = tog.b(deviceInfo, z);
        if (d(deviceInfo.getDeviceModel())) {
            c = 0;
            d = 0;
        } else {
            c = tog.c(deviceInfo);
            d = tog.d(deviceInfo);
        }
        return b == 0 || c == 0 || d == 0 || tog.i(deviceInfo) == 0;
    }

    public static void b(Device device) {
        if (device == null) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "setHealthAppCapability device is null");
            return;
        }
        try {
            tqz tqzVar = new tqz(tqz.b(device));
            tqzVar.c(0);
            tqzVar.e(1);
            tqzVar.b(1);
            tqzVar.d(1);
            device.setReservedness(tqzVar.toString());
        } catch (JSONException unused) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "setHealthAppCapability new DeviceJson exception");
        }
    }

    public static boolean m(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "isGalileoPowerSaveModel deviceInfo is null");
            return false;
        }
        if (!f(deviceInfo)) {
            return false;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "isGalileoPowerSaveModel powerSaveModel is ", Integer.valueOf(deviceInfo.getPowerSaveModel()));
        return deviceInfo.getPowerSaveModel() == 1;
    }

    public static boolean f(DeviceInfo deviceInfo) {
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel)) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "isGalileoDevice deviceModel is empty");
            return false;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "isGalileoDevice deviceModel is ", deviceModel);
        return deviceModel.contains("Galileo-");
    }

    public static boolean b(String str) {
        if (!TextUtils.equals(str, "com.huawei.health")) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "isHealthCall false");
            return false;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "isHealthCall true");
        return true;
    }

    public static List<DeviceInfo> c() {
        LogUtil.a("WearEngine_HiWearCommonUtil", "foundHiWearDevices enter");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_HiWearCommonUtil");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "discoverDevices device is empty");
            return new ArrayList(16);
        }
        ArrayList arrayList = new ArrayList(16);
        for (DeviceInfo deviceInfo : deviceList) {
            if (!p(deviceInfo)) {
                LogUtil.a("WearEngine_HiWearCommonUtil", "device not support hiwearkit, deviceName is ", deviceInfo.getDeviceName(), "connect state: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
            } else if (!m(deviceInfo) && !c(deviceInfo, "com.huawei.health") && q(deviceInfo)) {
                arrayList.add(deviceInfo);
            }
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "foundHiWearDevices device is ok");
        return arrayList;
    }

    private static boolean q(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            return tog.i(deviceInfo) == 0 || tog.c(deviceInfo) == 0 || tog.d(deviceInfo) == 0 || tog.b(deviceInfo) == 0;
        }
        LogUtil.b("WearEngine_HiWearCommonUtil", "isHaveCapability deviceInfo is null");
        return false;
    }

    public static DeviceInfo e(Device device, List<DeviceInfo> list) {
        if (device == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "getDeviceInfoByDevice device is null");
            return null;
        }
        String uuid = device.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "getDeviceInfoByDevice device Uuid is null");
            return null;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "getDeviceInfoByDevice deviceInfoList is null");
            return null;
        }
        for (DeviceInfo deviceInfo : list) {
            if (uuid.equals(deviceInfo.getUuid()) || uuid.equals(deviceInfo.getDeviceUdid())) {
                return deviceInfo;
            }
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "getDeviceInfoByDevice deviceInfo is not found");
        return null;
    }

    public static Device c(DeviceInfo deviceInfo) {
        Device j = j(deviceInfo);
        if (j == null || !j.isConnected()) {
            return null;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "device is connected, deviceName is ", j.getName());
        return j;
    }

    public static Device j(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            if (!t(deviceInfo)) {
                LogUtil.a("WearEngine_HiWearCommonUtil", "device not support hiwearkit, deviceName is ", deviceInfo.getDeviceName(), "connect state: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                return null;
            }
            LogUtil.a("WearEngine_HiWearCommonUtil", "device is not null, deviceName is ", deviceInfo.getDeviceName(), ", connect state: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
            return d(deviceInfo);
        }
        LogUtil.h("WearEngine_HiWearCommonUtil", "device is null");
        return null;
    }

    private static boolean t(DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HiWearCommonUtil", "enter isSupportHiWearCapability");
        DeviceCapability e2 = e(deviceInfo.getDeviceIdentify());
        if (e2 != null) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "deviceCapability != null: ", Boolean.valueOf(e2.isSupportHiWear()));
            return e2.isSupportHiWear();
        }
        boolean a2 = tog.a(deviceInfo);
        LogUtil.a("WearEngine_HiWearCommonUtil", "isSupportHiWearCapability: ", Boolean.valueOf(a2));
        return a2;
    }

    public static boolean g(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "isDeviceInfoSupportHiWear deviceInfo is null");
            return false;
        }
        if (t(deviceInfo)) {
            return true;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "isDeviceInfoSupportHiWear device not support hiwearkit.");
        return false;
    }

    public static boolean i(DeviceInfo deviceInfo) {
        Iterator<DeviceInfo> it = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_HiWearCommonUtil").iterator();
        while (it.hasNext()) {
            if (it.next().getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                return false;
            }
        }
        return true;
    }

    public static Device e(DeviceInfo deviceInfo, String str) {
        LogUtil.a("WearEngine_HiWearCommonUtil", "callingPackageName is :", str);
        Device j = j(deviceInfo);
        tqz tqzVar = null;
        if (j == null) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "getHiWearDevice device is null");
            return null;
        }
        if ("com.huawei.deveco.assistant".equals(str)) {
            String b = tqz.b(j);
            String o = o(deviceInfo);
            try {
                tqzVar = new tqz(b);
            } catch (JSONException unused) {
                LogUtil.b("WearEngine_HiWearCommonUtil", "getHiWearDevice new DeviceJson exception");
            }
            if (tqzVar == null) {
                return j;
            }
            tqzVar.b(o);
            j.setReservedness(tqzVar.toString());
        }
        return j;
    }

    private static String o(DeviceInfo deviceInfo) {
        String n;
        if (deviceInfo == null) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "getDeviceUdId btDeviceInfo is null");
            return "";
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (deviceIdentify == null) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "getDeviceUdId currentMac is null");
            return "";
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        String str = securityDeviceId + deviceIdentify;
        if (deviceIdentify.equals(securityDeviceId)) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "getDeviceUdId currentMac equals securityDeviceSn");
        } else {
            deviceIdentify = str;
        }
        if (deviceInfo.getProductType() >= 34) {
            n = knl.a(deviceIdentify);
        } else {
            LogUtil.a("WearEngine_HiWearCommonUtil", "getDeviceUdId productType < Latona");
            n = jsd.n(securityDeviceId);
        }
        if (n != null) {
            return n;
        }
        LogUtil.b("WearEngine_HiWearCommonUtil", "getDeviceUdId shaDeviceId is null");
        return "";
    }

    private static DeviceCapability e(String str) {
        return jsz.b(BaseApplication.getContext()).queryDeviceCapability(3, "", "WearEngine_HiWearCommonUtil").get(str);
    }

    public static Device d(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            Device device = new Device();
            device.setName(deviceInfo.getDeviceName());
            String deviceUdid = deviceInfo.getDeviceUdid();
            if (TextUtils.isEmpty(deviceUdid)) {
                LogUtil.a("WearEngine_HiWearCommonUtil", "udId is empty, use uuId");
                device.setUuid(deviceInfo.getUuid());
            } else {
                device.setUuid(deviceUdid);
            }
            device.setModel(deviceInfo.getDeviceModel());
            device.setProductType(0);
            device.setConnectState(deviceInfo.getDeviceConnectState());
            tqz r = r(deviceInfo);
            device.setReservedness(r == null ? "{}" : r.toString());
            return device;
        }
        LogUtil.h("WearEngine_HiWearCommonUtil", "device is null");
        return null;
    }

    private static tqz r(DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (!TextUtils.isEmpty(deviceIdentify)) {
            deviceIdentify = deviceIdentify.substring(0, deviceIdentify.length() - 4);
        }
        String e2 = Sha256.e(deviceIdentify, "SHA-256");
        String wearEngineDeviceId = deviceInfo.getWearEngineDeviceId();
        LogUtil.a("WearEngine_HiWearCommonUtil", "convertToWearDevice wearEngineDeviceId ");
        String softVersion = deviceInfo.getSoftVersion();
        LogUtil.a("WearEngine_HiWearCommonUtil", "convertToWearDevice softVersion:", softVersion);
        return new tqz.c().a(e2).d(wearEngineDeviceId).b(softVersion).b(h(deviceInfo)).c();
    }

    public static int h(DeviceInfo deviceInfo) {
        int sportVersion = deviceInfo.getSportVersion();
        LogUtil.a("WearEngine_HiWearCommonUtil", "getDeviceType source deviceType: ", Integer.valueOf(sportVersion));
        if (deviceInfo.getSportVersion() == -1) {
            sportVersion = f(deviceInfo) ? 1 : 0;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "getDeviceType final deviceType: ", Integer.valueOf(sportVersion));
        return sportVersion;
    }

    public static int b(DeviceInfo deviceInfo) {
        int powerSaveModel = deviceInfo.getPowerSaveModel();
        LogUtil.a("WearEngine_HiWearCommonUtil", "getDevicePowerMode source devicePowerMode is: ", Integer.valueOf(powerSaveModel));
        if (h(deviceInfo) == 0 || m(deviceInfo)) {
            powerSaveModel = -1;
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "getDevicePowerMode final devicePowerMode: ", Integer.valueOf(powerSaveModel));
        return powerSaveModel;
    }

    public static void c(DeviceInfo deviceInfo, Device device) {
        JSONObject jSONObject;
        try {
            tqz tqzVar = new tqz(tqz.b(device));
            String a2 = tqzVar.a();
            if (!TextUtils.isEmpty(a2)) {
                jSONObject = new JSONObject(a2);
            } else {
                jSONObject = new JSONObject();
            }
            String securityDeviceId = deviceInfo.getSecurityDeviceId();
            jSONObject.put("deviceUdid", Sha256.e(securityDeviceId, "SHA-256"));
            jSONObject.put(HealthEngineRequestManager.PARAMS_DEVICE_SN, securityDeviceId);
            tqzVar.a(jSONObject.toString());
            device.setReservedness(tqzVar.toString());
        } catch (JSONException unused) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "setExtraInfo has an JSONException");
        }
    }

    public static Device e(cjv cjvVar) {
        String nonWearableDeviceName = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getNonWearableDeviceName(cjvVar);
        Device device = new Device();
        device.setName(nonWearableDeviceName);
        device.setProductType(1);
        return device;
    }

    public static Intent feZ_(Context context, Intent intent) {
        LogUtil.a("WearEngine_HiWearCommonUtil", "createExplicitFromImplicitIntent enter");
        if (context == null || intent == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "createExplicitFromImplicitIntent context or implicitIntent is null");
            return null;
        }
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "createExplicitFromImplicitIntent resolveInfoList is null");
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        LogUtil.c("WearEngine_HiWearCommonUtil", "createExplicitFromImplicitIntent component = ", componentName.toString());
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    public static Device d() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "WearEngine_HiWearCommonUtil");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        return d(deviceInfo);
    }

    public static void a(DeviceCommand deviceCommand) {
        if (jst.s(deviceCommand.getmIdentify())) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "OTA update, other command can't send...");
            throw new IllegalStateException(String.valueOf(12));
        }
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static boolean p(DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HiWearCommonUtil", "enter isSupportHiWearCapabilityFromFromDeviceCfgMgr");
        DeviceCapability f = f(deviceInfo.getDeviceIdentify());
        if (f != null) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "deviceCapability != null: ", Boolean.valueOf(f.isSupportHiWear()));
            return f.isSupportHiWear();
        }
        boolean a2 = tog.a(deviceInfo);
        LogUtil.a("WearEngine_HiWearCommonUtil", "isSupportHiWearCapabilityFromDeviceCfgMgr: ", Boolean.valueOf(a2));
        return a2;
    }

    private static DeviceCapability f(String str) {
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "WearEngine_HiWearCommonUtil");
        if (a2 == null || !a2.containsKey(str)) {
            return null;
        }
        return a2.get(str);
    }

    public static List<DeviceInfo> e(List<DeviceInfo> list) {
        ArrayList arrayList = new ArrayList(16);
        if (list == null) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "deviceInfoList is null");
            return arrayList;
        }
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null) {
                String deviceModel = deviceInfo.getDeviceModel();
                if (!TextUtils.isEmpty(deviceModel) && a(deviceModel)) {
                    arrayList.add(deviceInfo);
                } else {
                    byte[] a2 = cvx.a(deviceInfo.getExpandCapability());
                    LogUtil.c("WearEngine_HiWearCommonUtil", "getExpandCapability expandCapability is " + deviceInfo.getExpandCapability());
                    boolean a3 = CommonUtil.a(a2, 14);
                    LogUtil.a("WearEngine_HiWearCommonUtil", "modelName is " + deviceModel + ", isSupport  " + a3);
                    if (a3) {
                        arrayList.add(deviceInfo);
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "modelName is Empty");
            return false;
        }
        for (String str2 : e) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearEngine_HiWearCommonUtil", "isFaraDevice modelName is Empty");
            return false;
        }
        return str.contains("Fara-");
    }

    private static boolean d(List list) {
        return list == null || list.isEmpty();
    }

    private static boolean e() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            return true;
        }
        LogUtil.h("WearEngine_HiWearCommonUtil", "not have net");
        return false;
    }

    public static void b(DeviceInfo deviceInfo, Device device, String str) {
        String g;
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        if (TextUtils.isEmpty(hiLinkDeviceId)) {
            g = deviceInfo.getProductType() == 34 ? "06D" : "unknown";
            LogUtil.a("WearEngine_HiWearCommonUtil", "setDeviceCategory ProductType is ", Integer.valueOf(deviceInfo.getProductType()));
        } else {
            g = g(hiLinkDeviceId);
        }
        try {
            tqz tqzVar = new tqz(tqz.b(device));
            tqzVar.d(g);
            device.setReservedness(tqzVar.toString());
        } catch (JSONException unused) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "setDeviceCategory has JSONException");
        }
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        int i = g.equals("unknown") ? -1 : 0;
        if (i == -1) {
            i();
        }
        wearEngineBiOperate.biApiCalling(BaseApplication.getContext(), str, "getDeviceCategory", String.valueOf(i));
    }

    public static String e(DeviceInfo deviceInfo) {
        String g;
        if (deviceInfo == null) {
            LogUtil.b("WearEngine_HiWearCommonUtil", "getDeviceCategory deviceInfo is null");
            return "unknown";
        }
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        if (TextUtils.isEmpty(hiLinkDeviceId)) {
            g = deviceInfo.getProductType() == 34 ? "06D" : "unknown";
            LogUtil.a("WearEngine_HiWearCommonUtil", "setDeviceCategory ProductType is ", Integer.valueOf(deviceInfo.getProductType()));
        } else {
            g = g(hiLinkDeviceId);
        }
        if (g.equals("unknown")) {
            i();
        }
        return g;
    }

    private static String g(String str) {
        if (TextUtils.isEmpty("unknown")) {
            return "unknown";
        }
        List<ProductMapInfo> b = ProductMap.b("smartProductId", str);
        if (!d(b) && b.get(0) != null) {
            String e2 = b.get(0).e();
            if (!TextUtils.isEmpty(e2)) {
                return e2;
            }
            LogUtil.h("WearEngine_HiWearCommonUtil", "getDeviceCategoryByHiLinkId deviceType is empty");
            return "unknown";
        }
        LogUtil.a("WearEngine_HiWearCommonUtil", "getDeviceCategoryByHiLinkId productMapInfoList is Empty");
        return "unknown";
    }

    private static void i() {
        boolean z = System.currentTimeMillis() - a() > 86400000;
        LogUtil.a("WearEngine_HiWearCommonUtil", "UpdateProductMap isNeedUpdate: ", Boolean.valueOf(z));
        if (z && e()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: tqx
                @Override // java.lang.Runnable
                public final void run() {
                    tqy.b();
                }
            });
        }
    }

    static /* synthetic */ void b() {
        boolean d = jbw.d(BaseApplication.getContext(), 1);
        f();
        if (d) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "UpdateProductMap download map Success");
        } else {
            LogUtil.a("WearEngine_HiWearCommonUtil", "UpdateProductMap download map fail");
        }
    }

    private static void f() {
        SharedPreferenceManager.e(String.valueOf(53), "key_productMap_Updated_Time", System.currentTimeMillis());
        LogUtil.a("WearEngine_HiWearCommonUtil", "setProductMapUpdatedTime success");
    }

    private static long a() {
        long b = SharedPreferenceManager.b(String.valueOf(53), "key_productMap_Updated_Time", 0L);
        LogUtil.a("WearEngine_HiWearCommonUtil", "getProductMapUpdatedTime is " + b);
        return b;
    }

    public static void l(final DeviceInfo deviceInfo) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: tqv
            @Override // java.lang.Runnable
            public final void run() {
                tqy.n(DeviceInfo.this);
            }
        });
    }

    static /* synthetic */ void n(DeviceInfo deviceInfo) {
        if (!k(deviceInfo)) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "updateProductMapForDeviceConnected is not need update.");
            s(deviceInfo);
        }
        ProductMapParseUtil.b(BaseApplication.getContext());
    }

    private static void s(DeviceInfo deviceInfo) {
        if (e()) {
            LogUtil.a("WearEngine_HiWearCommonUtil", "updateProductMapForDeviceConnected result: ", Boolean.valueOf(jbw.d(BaseApplication.getContext(), 1)));
            y(deviceInfo);
        }
    }

    private static void y(DeviceInfo deviceInfo) {
        SharedPreferenceManager.e(String.valueOf(53), trt.d(deviceInfo.getDeviceIdentify(), "SHA-256"), true);
        LogUtil.a("WearEngine_HiWearCommonUtil", "setProductMapUpdatedFlag success");
    }

    private static boolean k(DeviceInfo deviceInfo) {
        boolean a2 = SharedPreferenceManager.a(String.valueOf(53), trt.d(deviceInfo.getDeviceIdentify(), "SHA-256"), false);
        LogUtil.a("WearEngine_HiWearCommonUtil", "getProductMapUpdatedFlag is " + a2);
        return a2;
    }

    public static void a(DeviceInfo deviceInfo) {
        SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(53), trt.d(deviceInfo.getDeviceIdentify(), "SHA-256"));
        LogUtil.a("WearEngine_HiWearCommonUtil", "deleteProductMapUpdatedFlag is Success.");
    }

    public static boolean c(String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        List<ResolveInfo> queryIntentContentProviders = BaseApplication.getContext().getPackageManager().queryIntentContentProviders(intent, 0);
        if (queryIntentContentProviders != null && queryIntentContentProviders.size() == 1) {
            return true;
        }
        tos.d("WearEngine_HiWearCommonUtil", "contentProvider List are null");
        return false;
    }
}
