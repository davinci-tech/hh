package defpackage;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.PushMessageToDeviceReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.DeviceUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jpp {
    public static int d(int i) {
        if (i == -1) {
            return 0;
        }
        if (EnvironmentUtils.e()) {
            int b = juu.a(i).b();
            return b < 1 ? juu.c(i) : b;
        }
        int o = jfu.c(i).o();
        return o < 1 ? jfu.a(i) : o;
    }

    public static String d() {
        DeviceInfo a2 = jpt.a("DeviceMgrUtils");
        if (a2 == null) {
            return "";
        }
        return a2.getSecurityUuid() + "#ANDROID21";
    }

    public static String b() {
        DeviceInfo d = jpu.d("DeviceMgrUtils");
        if (d == null) {
            return "";
        }
        return d.getSecurityUuid() + "#ANDROID21";
    }

    public static void i(DeviceInfo deviceInfo) {
        LogUtil.a("DeviceMgrUtils", "registerDeviceToHiHealth enter");
        if (deviceInfo == null) {
            LogUtil.h("DeviceMgrUtils", "device is null");
            return;
        }
        HiDeviceInfo b = b(deviceInfo);
        LogUtil.a("DeviceMgrUtils", "device.getDeviceModel():", deviceInfo.getDeviceModel());
        if (CommonUtil.as() && "-1".equals(b.getDeviceUniqueCode()) && b.getDeviceType() == 10001) {
            ReleaseLogUtil.d("R_DeviceMgrUtils", "deviceInfo error");
            return;
        }
        if (CommonUtil.as() && !"-1".equals(b.getDeviceUniqueCode()) && b.getDeviceType() == 1) {
            ReleaseLogUtil.d("R_DeviceMgrUtils", "deviceInfo error");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(0);
        HiHealthManager.d(BaseApplication.getContext()).registerDataClient(b, arrayList, null);
    }

    public static void e() {
        LogUtil.a("DeviceMgrUtils", "synCloud");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setForceSync(true);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public static HiDeviceInfo b(DeviceInfo deviceInfo) {
        HiDeviceInfo hiDeviceInfo;
        HiDeviceInfo hiDeviceInfo2 = new HiDeviceInfo(1);
        if (deviceInfo == null) {
            LogUtil.h("DeviceMgrUtils", "device is null");
            return hiDeviceInfo2;
        }
        if ("Crius-B29".equals(deviceInfo.getDeviceModel())) {
            hiDeviceInfo = new HiDeviceInfo(97);
        } else {
            try {
                int d = d(deviceInfo.getProductType());
                if (d <= 0) {
                    cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(deviceInfo.getProductType());
                    if (pluginInfoByDeviceType != null && pluginInfoByDeviceType.f() != null) {
                        d = pluginInfoByDeviceType.f().au();
                    }
                    LogUtil.b("DeviceMgrUtils", "getHiDeviceInfo descriptionInfo is null.");
                    d = 0;
                }
                hiDeviceInfo = new HiDeviceInfo(d);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("DeviceMgrUtils", "get hiDevcieId error, save info fail, please check, originalType : ", Integer.valueOf(deviceInfo.getProductType()));
                c(deviceInfo.getProductType());
                return hiDeviceInfo2;
            }
        }
        e(hiDeviceInfo, deviceInfo);
        int a2 = a(deviceInfo.getHiLinkDeviceId());
        ReleaseLogUtil.e("R_DeviceMgrUtils", "registerDeviceToHiHealth hilinkID", deviceInfo.getHiLinkDeviceId());
        int b = jrf.b(deviceInfo);
        if (a2 != -1) {
            LogUtil.a("DeviceMgrUtils", "registerDeviceToHiHealth getDeviceProductIdByHiLinId is ", Integer.valueOf(a2));
            hiDeviceInfo.setDeviceType(a2);
        } else if (b >= 1) {
            ReleaseLogUtil.e("R_DeviceMgrUtils", "registerDeviceToHiHealth getCloudId from db:", Integer.valueOf(b));
            hiDeviceInfo.setDeviceType(b);
        }
        if (!TextUtils.isEmpty(deviceInfo.getDeviceSubModelId())) {
            hiDeviceInfo.setSubProdId(deviceInfo.getDeviceSubModelId());
        }
        hiDeviceInfo.setDeviceUniqueCode(deviceInfo.getSecurityUuid() + "#ANDROID21");
        hiDeviceInfo.setDeviceName(deviceInfo.getDeviceName());
        if (!TextUtils.isEmpty(deviceInfo.getUuid())) {
            if (deviceInfo.getDeviceIdType() == 10) {
                hiDeviceInfo.setDeviceSN(deviceInfo.getUuid());
            } else {
                hiDeviceInfo.setDeviceSN(cvx.e(deviceInfo.getUuid()));
            }
        }
        if (!TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            hiDeviceInfo.setDeviceMac(deviceInfo.getDeviceIdentify());
        }
        if (!TextUtils.isEmpty(deviceInfo.getDeviceUdid())) {
            hiDeviceInfo.setDeviceUdid(deviceInfo.getDeviceUdid());
        }
        return hiDeviceInfo;
    }

    private static void e(HiDeviceInfo hiDeviceInfo, DeviceInfo deviceInfo) {
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel) || !deviceModel.toUpperCase(Locale.ENGLISH).contains("HECTOR")) {
            return;
        }
        hiDeviceInfo.setDeviceType(112);
    }

    private static void c(int i) {
        LogUtil.h("DeviceMgrUtils", "device list info is wrong, can not get right device info now, please check download device info from net");
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_HI_TYPE_ERROR_1040065.value(), hashMap, 0);
    }

    public static boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return true;
        }
        int productType = deviceInfo.getProductType();
        LogUtil.a("DeviceMgrUtils", "checkHiHealthType productType is: ", Integer.valueOf(productType));
        int d = d(productType);
        if (d <= 0) {
            cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(deviceInfo.getProductType());
            if (pluginInfoByDeviceType == null || pluginInfoByDeviceType.f() == null) {
                LogUtil.b("DeviceMgrUtils", "getHiDeviceInfo descriptionInfo is null.");
                d = 0;
            } else {
                d = pluginInfoByDeviceType.f().au();
            }
        }
        LogUtil.a("DeviceMgrUtils", "checkHiHealthType hiHealthDevType is: ", Integer.valueOf(d));
        boolean z = d != 0;
        if (!z) {
            c(productType);
        }
        return z;
    }

    public static int a(String str) {
        try {
            JSONObject d = cwc.d(str);
            if (d == null) {
                LogUtil.h("DeviceMgrUtils", "getDeviceProductIdByHiLinId aiTipsJson is null");
                return -1;
            }
            if (d.has("health_cloud_productId")) {
                int i = d.getInt("health_cloud_productId");
                LogUtil.a("DeviceMgrUtils", "healthCloudId is ", Integer.valueOf(i));
                return i;
            }
            LogUtil.h("DeviceMgrUtils", "getDeviceProductIdByHiLinId aiTipsJson no health_cloud_productId key");
            return -1;
        } catch (JSONException unused) {
            LogUtil.b("DeviceMgrUtils", "getDeviceProductIdByHiLinId JSONException");
            return -1;
        }
    }

    public static void d(String str, Context context) {
        PushMessageToDeviceReq pushMessageToDeviceReq = new PushMessageToDeviceReq();
        String a2 = CommonUtil.a(context, false);
        pushMessageToDeviceReq.setReqId(a2 + System.currentTimeMillis());
        pushMessageToDeviceReq.setDeviceType("00E");
        pushMessageToDeviceReq.setUdId(a2);
        pushMessageToDeviceReq.setTimeOut(30);
        String replace = str.replace(":", "");
        int length = replace.length();
        if (length > 3) {
            replace = replace.substring(length - 3);
        }
        pushMessageToDeviceReq.setTargetDevice(replace);
        CloudCommonReponse a3 = jbs.a(BaseApplication.getContext()).a(pushMessageToDeviceReq);
        if (a3 != null) {
            if (a3.getResultCode().intValue() == 0) {
                LogUtil.a("DeviceMgrUtils", "send push message is success");
            } else {
                LogUtil.a("DeviceMgrUtils", "send push message is failed:", a3.getResultDesc());
            }
        }
    }

    public static boolean a() {
        LogUtil.a("DeviceMgrUtils", "isHasBondedDevice enter.");
        if (!CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            String d = CommonUtil.d(Process.myPid());
            if (!TextUtils.isEmpty(d) && !BaseApplication.getAppPackage().equals(d)) {
                throw new RuntimeException("DeviceMgrUtils#isHasWearDevice must run in main process.");
            }
        }
        if (jpn.e(1)) {
            LogUtil.a("DeviceMgrUtils", "use device cache from memory.");
        } else {
            boolean a2 = DeviceUtil.a();
            jpn.a(1, true);
            jpn.a(Integer.MIN_VALUE, a2);
        }
        return jpn.e(Integer.MIN_VALUE);
    }

    public static boolean c() {
        LogUtil.a("DeviceMgrUtils", "isHasBondedDevice enter.");
        if (jpn.e(1)) {
            LogUtil.a("DeviceMgrUtils", "use device cache from memory.");
        } else {
            boolean a2 = DeviceUtil.a();
            jpn.a(1, true);
            jpn.a(Integer.MIN_VALUE, a2);
        }
        if (jpn.e(2)) {
            LogUtil.a("DeviceMgrUtils", "use device cache from memory.");
        } else {
            boolean isNoneHonourAm16BondedDevice = ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isNoneHonourAm16BondedDevice();
            jpn.a(2, true);
            jpn.a(1073741824, isNoneHonourAm16BondedDevice);
        }
        return jpn.e(Integer.MIN_VALUE) || jpn.e(1073741824);
    }

    public static String c(DeviceInfo deviceInfo) {
        HashMap<Integer, String> c;
        String q;
        if (deviceInfo == null) {
            LogUtil.h("DeviceMgrUtils", "deviceInfo is null");
            return null;
        }
        if (ProcessUtil.b().endsWith(":PhoneService")) {
            c = juu.a();
        } else {
            c = jfu.c();
        }
        int productType = deviceInfo.getProductType();
        if (c == null || !c.containsKey(Integer.valueOf(productType))) {
            if (ProcessUtil.b().endsWith(":PhoneService")) {
                q = juu.a(productType).g();
            } else {
                q = jfu.c(productType).q();
            }
            if (TextUtils.isEmpty(q)) {
                q = com.huawei.hms.hihealth.data.DeviceInfo.STR_TYPE_UNKNOWN;
            }
            LogUtil.a("DeviceMgrUtils", "getDeviceName deviceType: ", q);
            return q;
        }
        LogUtil.a("DeviceMgrUtils", "getDeviceName mapDeviceType: ", c.get(Integer.valueOf(productType)));
        return c.get(Integer.valueOf(productType));
    }

    public static boolean e(DeviceInfo deviceInfo) {
        DeviceCapability deviceCapability;
        if (deviceInfo == null) {
            return false;
        }
        if (deviceInfo.getSportVersion() == 1) {
            return true;
        }
        if (deviceInfo.getSportVersion() == 0) {
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "DeviceMgrUtils");
        if (a2 != null) {
            LogUtil.h("DeviceMgrUtils", "filter deviceCapabilityMap is null");
            deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        } else {
            deviceCapability = null;
        }
        return deviceCapability != null && deviceCapability.isSupportSmartWatchVersionStatus();
    }

    public static boolean a(DeviceInfo deviceInfo) {
        return deviceInfo != null && e(deviceInfo) && deviceInfo.getPowerSaveModel() == 0;
    }
}
