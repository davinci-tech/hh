package defpackage;

import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class keg {
    public static void d(DeviceInfo deviceInfo) {
        HiDeviceInfo hiDeviceInfo;
        LogUtil.a("DeviceManagerUtils", "registerDeviceToHiHealth enter");
        if (deviceInfo == null) {
            LogUtil.h("DeviceManagerUtils", "device is null");
            return;
        }
        if ("Crius-B29".equals(deviceInfo.getDeviceModel())) {
            hiDeviceInfo = new HiDeviceInfo(97);
        } else {
            try {
                hiDeviceInfo = new HiDeviceInfo(a(deviceInfo.getProductType()));
            } catch (IllegalArgumentException unused) {
                LogUtil.b("DeviceManagerUtils", "get hiDevcieId error, save info fail, please check, originalType : ", Integer.valueOf(deviceInfo.getProductType()));
                e(deviceInfo.getProductType());
                return;
            }
        }
        d(hiDeviceInfo, deviceInfo);
        int b = b(deviceInfo.getHiLinkDeviceId());
        ReleaseLogUtil.e("BTSYNC_AlarmData_DeviceManagerUtils", "registerDeviceToHiHealth hilinkID", deviceInfo.getHiLinkDeviceId());
        int b2 = jrf.b(deviceInfo);
        if (b != -1) {
            LogUtil.a("DeviceManagerUtils", "registerDeviceToHiHealth getDeviceProductIdByHiLinId is ", Integer.valueOf(b));
            hiDeviceInfo.setDeviceType(b);
        } else if (b2 >= 1) {
            ReleaseLogUtil.e("BTSYNC_AlarmData_DeviceManagerUtils", "registerDeviceToHiHealth getCloudId from db:", Integer.valueOf(b2));
            hiDeviceInfo.setDeviceType(b2);
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
        LogUtil.a("DeviceManagerUtils", "device.getDeviceModel():", deviceInfo.getDeviceModel());
        if (CommonUtil.as() && "-1".equals(hiDeviceInfo.getDeviceUniqueCode()) && hiDeviceInfo.getDeviceType() == 10001) {
            LogUtil.h("DeviceManagerUtils", "deviceInfo error");
            return;
        }
        if (CommonUtil.as() && !"-1".equals(hiDeviceInfo.getDeviceUniqueCode()) && hiDeviceInfo.getDeviceType() == 1) {
            LogUtil.h("DeviceManagerUtils", "deviceInfo error");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(0);
        HiHealthManager.d(BaseApplication.getContext()).registerDataClient(hiDeviceInfo, arrayList, null);
    }

    public static int a(int i) {
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

    public static boolean b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return true;
        }
        int productType = deviceInfo.getProductType();
        int a2 = a(productType);
        LogUtil.a("DeviceManagerUtils", "checkHiHealthType productType = ", Integer.valueOf(productType), " ,hiHealthDevType", Integer.valueOf(a2));
        boolean z = a2 != 0;
        if (!z) {
            e(productType);
        }
        return z;
    }

    private static void d(HiDeviceInfo hiDeviceInfo, DeviceInfo deviceInfo) {
        String deviceModel = deviceInfo.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel) || !deviceModel.toUpperCase(Locale.ROOT).contains("HECTOR")) {
            return;
        }
        hiDeviceInfo.setDeviceType(112);
    }

    public static int b(String str) {
        try {
            JSONObject d = cwc.d(str);
            if (d == null) {
                LogUtil.h("DeviceManagerUtils", "getDeviceProductIdByHiLinId aiTipsJson is null");
                return -1;
            }
            if (d.has("health_cloud_productId")) {
                int i = d.getInt("health_cloud_productId");
                LogUtil.a("DeviceManagerUtils", "healthCloudId is ", Integer.valueOf(i));
                return i;
            }
            LogUtil.h("DeviceManagerUtils", "getDeviceProductIdByHiLinId aiTipsJson no health_cloud_productId key");
            return -1;
        } catch (JSONException unused) {
            LogUtil.b("DeviceManagerUtils", "getDeviceProductIdByHiLinId JSONException");
            return -1;
        }
    }

    private static void e(int i) {
        LogUtil.h("DeviceManagerUtils", "device list info is wrong, can not get right device info now, please check download device info from net");
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_HI_TYPE_ERROR_1040065.value(), hashMap, 0);
    }

    public static String e() {
        DeviceInfo e = e("DeviceManagerUtils");
        if (e == null) {
            return "";
        }
        return e.getSecurityUuid() + "#ANDROID21";
    }

    public static DeviceInfo e(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("DeviceManagerUtils", "getActiveDeviceInfo() deviceInfoList is null.", str);
            return null;
        }
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
        LogUtil.a("DeviceManagerUtils", "getActiveDeviceInfo deviceInfo ", deviceInfo, ", tag is ", str);
        return deviceInfo;
    }

    public static long a(long j) {
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date(j * 1000));
        try {
            return new SimpleDateFormat("yyyyMMddhhmm").parse(format + AgdConstant.INSTALL_TYPE_DEFAULT).getTime() / 1000;
        } catch (ParseException e) {
            LogUtil.b("DeviceManagerUtils", "getBeginOfDate ParseException : ", ExceptionUtils.d(e));
            return j;
        }
    }

    public static DeviceCapability a(String str) {
        DeviceCapability deviceCapability = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManagerUtils", "getCapability mac is null.");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(2, str, "DeviceManagerUtils");
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        return deviceCapability;
    }

    public static void c() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10001);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setForceSync(true);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }
}
