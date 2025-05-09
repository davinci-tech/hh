package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.plugindevice.UploadSmartLifeApi;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Sha256;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class dew {
    public static void a(dep depVar) {
        e(depVar, -1);
    }

    public static void e(final dep depVar, final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dey
            @Override // java.lang.Runnable
            public final void run() {
                dew.a(dep.this, i);
            }
        });
    }

    static /* synthetic */ void a(dep depVar, int i) {
        if (!new des().a(depVar, d()) || i < 0) {
            return;
        }
        b(depVar.c().getId(), i);
    }

    public static void c(String str, String str2, String str3) {
        ceu Ej_ = ceu.Ej_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str2));
        if (Ej_ != null) {
            ((UploadSmartLifeApi) Services.c("PluginDeviceOpenModule", UploadSmartLifeApi.class)).upload(str, str2, str3, Ej_);
        }
    }

    public static void b(final String str, final int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("smartlife_IntellLifeUtils", "reportDeviceConnectStatus deviceId is empty.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dez
                @Override // java.lang.Runnable
                public final void run() {
                    ReleaseLogUtil.e("DEVMGR_EcologyDevice_smartlife_IntellLifeUtils", "peer connection status:", Integer.valueOf(r1), ",result:", Boolean.valueOf(new des().c(str, i)));
                }
            });
        }
    }

    public static String d(String str) {
        return new des().a(str);
    }

    public static Map<String, String> c(List<String> list) {
        return new des().a(list);
    }

    public static void e(final String str, final String str2) {
        if (Utils.o()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: dex
            @Override // java.lang.Runnable
            public final void run() {
                new des().a(str, str2, dew.d());
            }
        });
    }

    public static void b(Map<String, dep> map, List<String> list) {
        if (!Utils.o()) {
            new des().e(map, list);
        }
        LogUtil.a("smartlife_IntellLifeUtils", "syncDeviceInfoToSmartLife enter");
    }

    public static ServiceProfile d(DeviceProfile deviceProfile, String str, String str2) {
        String id = deviceProfile.getId();
        ServiceProfile serviceProfile = new ServiceProfile();
        serviceProfile.setIsNeedCloud(true);
        serviceProfile.setDeviceId(id);
        serviceProfile.setId(str);
        serviceProfile.setType(str2);
        HashMap hashMap = new HashMap(10);
        hashMap.put("serviceId", str);
        hashMap.put("type", str2);
        serviceProfile.addEntities(hashMap);
        return serviceProfile;
    }

    public static ServiceCharacteristicProfile d(Map<String, Object> map) {
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        serviceCharacteristicProfile.setIsNeedCloud(true);
        if (map == null || map.isEmpty()) {
            serviceCharacteristicProfile.addEntityInfo("timestamp", Long.valueOf(System.currentTimeMillis()));
            return serviceCharacteristicProfile;
        }
        serviceCharacteristicProfile.addEntities(map);
        serviceCharacteristicProfile.addEntityInfo("timestamp", Long.valueOf(System.currentTimeMillis()));
        return serviceCharacteristicProfile;
    }

    public static boolean a(ServiceProfile serviceProfile) {
        if (serviceProfile == null) {
            LogUtil.h("smartlife_IntellLifeUtils", "checkServiceProfile, profile is null");
            return false;
        }
        if (TextUtils.isEmpty(serviceProfile.getDeviceId())) {
            LogUtil.h("smartlife_IntellLifeUtils", "checkServiceProfile, illegal device id");
            return false;
        }
        if (!TextUtils.isEmpty(serviceProfile.getId())) {
            return true;
        }
        LogUtil.h("smartlife_IntellLifeUtils", "checkServiceProfile, illegal service id");
        return false;
    }

    public static String b() {
        if (Build.VERSION.SDK_INT >= 29) {
            String androidId = FoundationCommonUtil.getAndroidId(BaseApplication.getContext());
            if (!TextUtils.isEmpty(androidId)) {
                return androidId.toUpperCase(Locale.ENGLISH);
            }
            LogUtil.b("smartlife_IntellLifeUtils", "androidId is isEmpty");
        }
        if (BaseApplication.getContext().getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", BaseApplication.getContext().getPackageName()) == 0) {
            LogUtil.a("smartlife_IntellLifeUtils", "getSerialNumber version > VERSION_CODES.0");
            return Build.getSerial();
        }
        LogUtil.a("smartlife_IntellLifeUtils", "getSerialNumber version < VERSION_CODES.0");
        return CommonUtil.ag();
    }

    public static String d() {
        String str;
        try {
            str = a();
        } catch (SocketException unused) {
            LogUtil.b("smartlife_IntellLifeUtils", "getConfusedMac SocketException");
            str = null;
        }
        if (str == null || "".equalsIgnoreCase(str)) {
            return "";
        }
        String replace = str.replace(":", "");
        StringBuilder sb = new StringBuilder(replace.length() / 2);
        for (int i = 1; i < replace.length(); i += 2) {
            sb.append(replace.charAt(i));
        }
        LogUtil.c("smartlife_IntellLifeUtils", "getConfusedMac evenDigitsMac ", sb.toString());
        return Sha256.e(sb.toString(), "SHA-256");
    }

    private static String a() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        String str = null;
        if (networkInterfaces == null) {
            LogUtil.h("smartlife_IntellLifeUtils", "can not get network interface!");
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            byte[] hardwareAddress = nextElement.getHardwareAddress();
            if (hardwareAddress != null && hardwareAddress.length != 0) {
                StringBuilder sb = new StringBuilder(10);
                for (byte b : hardwareAddress) {
                    sb.append(String.format(Locale.ROOT, "%02X:", Byte.valueOf(b)));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                String sb2 = sb.toString();
                if ("wlan0".equals(nextElement.getName())) {
                    str = sb2;
                }
            }
        }
        return str;
    }

    public static boolean a(String str) {
        if (str != null && !Constants.NULL.equalsIgnoreCase(str) && !"".equalsIgnoreCase(str)) {
            return false;
        }
        LogUtil.h("smartlife_IntellLifeUtils", "isEmptyCharacterValue characterValue is null");
        return true;
    }

    public static String a(String str, String str2, String str3) {
        if (str2 == null || str == null || str3 == null) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(10);
        for (String str4 : str.split(str3)) {
            if (!str2.equalsIgnoreCase(str4)) {
                stringBuffer.append(str4);
                stringBuffer.append(str3);
            }
        }
        return stringBuffer.length() - str3.length() < 0 ? "" : stringBuffer.substring(0, stringBuffer.length() - str3.length());
    }

    public static void d(Map<String, DeviceProfile> map, List<DeviceProfile> list, List<String> list2) {
        if (map == null || list == null || list2 == null) {
            LogUtil.a("smartlife_IntellLifeUtils", "saveDeviceCloudProfiles deviceCloudProfileMap or deviceCloudProfiles or filteredDevices null");
            return;
        }
        LogUtil.a("smartlife_IntellLifeUtils", "initCloudDeviceMap deviceCloudProfiles size ", Integer.valueOf(list.size()), ",initCloudDeviceMap deviceCloudProfileMap's size", Integer.valueOf(map.size()));
        StringBuffer stringBuffer = new StringBuffer();
        for (DeviceProfile deviceProfile : list) {
            Map<String, Object> profile = deviceProfile.getProfile();
            try {
                if (list2.contains(e(deviceProfile))) {
                    String str = (profile.containsKey("devType") && (profile.get("devType") instanceof String)) ? (String) profile.get("devType") : null;
                    if (str == null) {
                        LogUtil.h("smartlife_IntellLifeUtils", "cloudDevice type is null");
                    } else {
                        String id = deviceProfile.getId();
                        stringBuffer.append(id);
                        stringBuffer.append(" ");
                        stringBuffer.append(str);
                        stringBuffer.append(";");
                        if (!TextUtils.isEmpty(id)) {
                            map.put(id, deviceProfile);
                        }
                    }
                }
            } catch (ClassCastException unused) {
                LogUtil.b("smartlife_IntellLifeUtils", "initDeviceMap() ClassCastException");
            } catch (NumberFormatException unused2) {
                LogUtil.b("smartlife_IntellLifeUtils", "initDeviceMap() NumberFormatException");
            }
        }
    }

    private static String e(DeviceProfile deviceProfile) {
        if (deviceProfile == null) {
            LogUtil.h("smartlife_IntellLifeUtils", "getDeviceModel illegal arguments");
            return null;
        }
        Map<String, Object> profile = deviceProfile.getProfile();
        if (profile.containsKey("prodId") && (profile.get("prodId") instanceof String)) {
            return (String) profile.get("prodId");
        }
        return null;
    }

    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        LogUtil.a("smartlife_IntellLifeUtils", "filterServiceCharacter ", dku.b(str2));
        if (!str.contains(str2)) {
            LogUtil.h("smartlife_IntellLifeUtils", "characterValueList length", Integer.valueOf(str.length()), " not contain value :", dku.b(str2));
            return str;
        }
        return a(str, str2, ",");
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("smartlife_IntellLifeUtils", "convertIntoValidSerialNumber number isEmpty");
            return " ";
        }
        if (!"unknown".equalsIgnoreCase(str) && !Constants.NULL.equalsIgnoreCase(str)) {
            return str;
        }
        LogUtil.h("smartlife_IntellLifeUtils", "convertIntoValidSerialNumber number is null, number:", str);
        return " ";
    }

    public static String d(String str, String str2) {
        if (a(str)) {
            return str2;
        }
        if (str.contains(str2)) {
            return str;
        }
        return str + "," + str2;
    }

    public static ServiceCharacteristicProfile d(Map<String, Object> map, String[] strArr) {
        if (strArr == null || strArr.length < 2) {
            LogUtil.h("smartlife_IntellLifeUtils", "buildManagerNodeServiceCharacteristic illegal arguments");
            return null;
        }
        if (map != null) {
            map.put("characteristic.managerIdentifierList", strArr[0]);
            map.put("characteristic.managerIdentifierList2", strArr[1]);
        }
        return d(map);
    }

    public static String c(String str) {
        if (!TextUtils.isEmpty(str)) {
            str.hashCode();
            if (str.equals(w9.h)) {
                return "bt.connection";
            }
            if (str.equals("managerNode")) {
                return "managerNode";
            }
            LogUtil.h("smartlife_IntellLifeUtils", "getServiceTypeById unknown service id");
        }
        return null;
    }
}
