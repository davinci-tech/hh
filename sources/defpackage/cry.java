package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import health.compact.a.CommonUtil;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class cry {
    public static DeviceProfile d(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(10);
        if (!c(hashMap, deviceInfo)) {
            LogUtil.h("WeightIntellLifeUtils", "can not build entities !");
            return null;
        }
        DeviceProfile deviceProfile = new DeviceProfile();
        deviceProfile.setId(deviceInfo.getUuid());
        deviceProfile.setIsNeedCloud(true);
        Object obj = hashMap.get("devType");
        if (obj instanceof String) {
            deviceProfile.setType((String) obj);
        }
        deviceProfile.addEntities(hashMap);
        LogUtil.c("WeightIntellLifeUtils", "putDeviceProfile deviceProfile: ", deviceProfile.toString());
        return deviceProfile;
    }

    private static boolean c(Map<String, Object> map, DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceName() == null) {
            LogUtil.h("WeightIntellLifeUtils", "setBasicProfileInfo getDeviceName is null");
            return false;
        }
        cru a2 = a(deviceInfo);
        if (a2 == null) {
            LogUtil.h("WeightIntellLifeUtils", "setBasicProfileInfo unknown device");
            return false;
        }
        map.put("model", a2.d());
        map.put("devType", a2.e());
        map.put("prodId", a2.a());
        map.put(ProfileRequestConstants.MANU, e(deviceInfo.getProductType()));
        map.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        map.put(ProfileRequestConstants.HIV, "1");
        map.put("deviceName", deviceInfo.getDeviceName());
        map.put("udid", deviceInfo.getUuid());
        LogUtil.a("WeightIntellLifeUtils", "setBasicProfileInfo uuid: ", cpw.a(deviceInfo.getUuid()));
        String c = c(deviceInfo);
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("WeightIntellLifeUtils", "setBasicProfileInfo subMac is null");
            return false;
        }
        LogUtil.a("WeightIntellLifeUtils", "setBasicProfileInfo subMac: ", cpw.a(c));
        map.put("mac", c);
        map.put(ProfileRequestConstants.PROT_TYPE, Integer.valueOf("M0CJ".equals(a2.a()) ? 4 : 1));
        return true;
    }

    public static cru a(DeviceInfo deviceInfo) {
        cru cruVar;
        int productType = deviceInfo.getProductType();
        LogUtil.a("WeightIntellLifeUtils", "getDeviceModeInfo type:", Integer.valueOf(productType), " deviceNameKey:", deviceInfo.getDeviceName());
        if (productType == 94) {
            LogUtil.a("WeightIntellLifeUtils", "getLikeByMap device is HUAWEI Band hagrid");
            cruVar = new cru("HAG-B19", "007B", "025");
        } else if (productType != 106) {
            if (productType == 140) {
                LogUtil.a("WeightIntellLifeUtils", "getLikeByMap device is HUAWEI Band herms");
                cruVar = new cru("HEM-B19", "M00D", "025");
            } else if (productType == 385) {
                LogUtil.a("WeightIntellLifeUtils", "getLikeByMap device is HUAWEI Band hagrid2021");
                cruVar = new cru("HAG-B19", "M00F", "025");
            } else if (productType == 672) {
                LogUtil.a("WeightIntellLifeUtils", "getLikeByMap device is HUAWEI Band dobby");
                cruVar = new cru("DOBBY-B19", "M0CJ", "A2B");
            } else {
                LogUtil.h("WeightIntellLifeUtils", "getDeviceModeInfo unknow type: ", Integer.valueOf(productType));
                cruVar = null;
            }
        } else if (coz.d(BaseApplication.getContext(), deviceInfo.getUuid())) {
            LogUtil.a("WeightIntellLifeUtils", "getLikeByMap device is isNewWeightHonor mini");
            cruVar = new cru("LUP-B39", "N001", "025");
        } else {
            LogUtil.a("WeightIntellLifeUtils", "getLikeByMap device is honor Band MINI");
            cruVar = new cru("LUP-B19", "N001", "025");
        }
        Object[] objArr = new Object[2];
        objArr[0] = "deviceModeInfo:";
        objArr[1] = cruVar == null ? Constants.NULL : cruVar.toString();
        LogUtil.c("WeightIntellLifeUtils", objArr);
        return cruVar;
    }

    private static String e(int i) {
        int i2 = (i == 94 || i == 140 || i == 385 || i == 672) ? 1 : 0;
        LogUtil.a("WeightIntellLifeUtils", "ManufactureId is ", Integer.valueOf(i2));
        return i2 == 1 ? "001" : "002";
    }

    private static String c(DeviceInfo deviceInfo) {
        String replaceAll = deviceInfo.getDeviceIdentify().replaceAll(":", "");
        int length = replaceAll.length();
        if (length > 3) {
            return replaceAll.substring(length - 3, length);
        }
        return null;
    }

    public static ServiceProfile d(DeviceInfo deviceInfo, String str, String str2) {
        String uuid = deviceInfo.getUuid();
        ServiceProfile serviceProfile = new ServiceProfile();
        serviceProfile.setIsNeedCloud(true);
        serviceProfile.setDeviceId(uuid);
        serviceProfile.setId(str);
        serviceProfile.setType(str2);
        HashMap hashMap = new HashMap(10);
        hashMap.put("serviceId", str);
        hashMap.put("type", str2);
        serviceProfile.addEntities(hashMap);
        return serviceProfile;
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WeightIntellLifeUtils", "getServiceTypeById serviceId is null");
            return null;
        }
        str.hashCode();
        if (str.equals(w9.h)) {
            return "bt.connection";
        }
        if (str.equals("managerNode")) {
            return "managerNode";
        }
        LogUtil.h("WeightIntellLifeUtils", "getServiceTypeById know service id");
        return null;
    }

    public static ServiceCharacteristicProfile c(Map<String, Object> map) {
        ServiceCharacteristicProfile serviceCharacteristicProfile = new ServiceCharacteristicProfile();
        serviceCharacteristicProfile.addEntityInfo("timestamp", Long.valueOf(System.currentTimeMillis()));
        serviceCharacteristicProfile.setIsNeedCloud(true);
        if (map != null && !map.isEmpty()) {
            serviceCharacteristicProfile.addEntities(map);
        }
        return serviceCharacteristicProfile;
    }

    public static Map<String, Object> d(DeviceInfo deviceInfo, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WeightIntellLifeUtils", "getServiceTypeById serviceId is null");
            return null;
        }
        HashMap hashMap = new HashMap(10);
        str.hashCode();
        if (str.equals(w9.h)) {
            hashMap.put("characteristic.bt.connectionStatus", "0");
        } else if (str.equals("managerNode")) {
            hashMap.put("type", deviceInfo.getDeviceBluetoothType() == 2 ? "local" : "cloud");
        } else {
            LogUtil.h("WeightIntellLifeUtils", "getServiceTypeById know service id");
        }
        return hashMap;
    }

    public static boolean d(ServiceProfile serviceProfile) {
        if (serviceProfile == null) {
            LogUtil.h("WeightIntellLifeUtils", "checkServiceProfile profile is null");
            return false;
        }
        if (TextUtils.isEmpty(serviceProfile.getDeviceId())) {
            LogUtil.h("WeightIntellLifeUtils", "checkServiceProfile illegal devcie id");
            return false;
        }
        if (!TextUtils.isEmpty(serviceProfile.getId())) {
            return true;
        }
        LogUtil.h("WeightIntellLifeUtils", "checkServiceProfile illegal service id");
        return false;
    }

    public static String a() {
        if (Build.VERSION.SDK_INT >= 29) {
            return e();
        }
        if (BaseApplication.getContext().getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", BaseApplication.getContext().getPackageName()) == 0) {
            LogUtil.a("WeightIntellLifeUtils", "getSerialNumber version > VERSION_CODES.0");
            return Build.getSerial();
        }
        LogUtil.a("WeightIntellLifeUtils", "getSerialNumber version < VERSION_CODES.0");
        return CommonUtil.ag();
    }

    private static String e() {
        return ("abc" + (Build.BOARD == null ? 0 : Build.BOARD.length() % 10) + (Build.BRAND == null ? 0 : Build.BRAND.length() % 10) + (Build.CPU_ABI == null ? 0 : Build.CPU_ABI.length() % 10) + (Build.DEVICE == null ? 0 : Build.DEVICE.length() % 10) + (Build.DISPLAY == null ? 0 : Build.DISPLAY.length() % 10) + (Build.HOST == null ? 0 : Build.HOST.length() % 10) + (Build.ID == null ? 0 : Build.ID.length() % 10) + (Build.MANUFACTURER == null ? 0 : Build.MANUFACTURER.length() % 10) + (Build.MODEL == null ? 0 : Build.MODEL.length() % 10) + (Build.PRODUCT == null ? 0 : Build.PRODUCT.length() % 10) + (Build.TAGS == null ? 0 : Build.TAGS.length() % 10) + (Build.TYPE == null ? 0 : Build.TYPE.length() % 10) + (Build.USER != null ? Build.USER.length() % 10 : 0)).toUpperCase(Locale.ENGLISH);
    }

    public static boolean b(String str) {
        if (str != null && !Constants.NULL.equalsIgnoreCase(str) && !"".equalsIgnoreCase(str)) {
            return false;
        }
        LogUtil.h("WeightIntellLifeUtils", "isEmptyCharacterValue characterValue is null");
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

    public static String d(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.h("WeightIntellLifeUtils", "filterServiceCharacter illegal characterValue");
            return str;
        }
        LogUtil.a("WeightIntellLifeUtils", "filterServiceCharacter ", cpw.a(str2));
        if (!str.contains(str2)) {
            LogUtil.h("WeightIntellLifeUtils", "characterValueList length", Integer.valueOf(str.length()), " not contain value :", cpw.a(str2));
            return str;
        }
        LogUtil.a("WeightIntellLifeUtils", " filterServiceCharacter length before ", Integer.valueOf(str.length()));
        String a2 = a(str, str2, ",");
        Object[] objArr = new Object[2];
        objArr[0] = " filterServiceCharacter length after ";
        objArr[1] = a2 == null ? Constants.NULL : Integer.valueOf(a2.length());
        LogUtil.a("WeightIntellLifeUtils", objArr);
        return a2;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WeightIntellLifeUtils", "checkInvalidSerialNumber number isEmpty");
            return " ";
        }
        if (!"unknown".equalsIgnoreCase(str) && !Constants.NULL.equalsIgnoreCase(str)) {
            return str;
        }
        LogUtil.h("WeightIntellLifeUtils", "checkInvalidSerialNumber number is null , number:", str);
        return " ";
    }

    public static String c(String str, String str2) {
        LogUtil.a("WeightIntellLifeUtils", "addCharacterValueIfNotExist targetString ", cpw.a(str2));
        if (b(str)) {
            return str2;
        }
        if (str.contains(str2)) {
            return str;
        }
        return str + "," + str2;
    }

    public static ServiceCharacteristicProfile e(DeviceInfo deviceInfo, String[] strArr) {
        if (strArr == null || strArr.length < 2) {
            LogUtil.h("WeightIntellLifeUtils", "buildManagerNodeServiceCharacteristic illegal arguments");
            return null;
        }
        Map<String, Object> d = d(deviceInfo, "managerNode");
        if (d != null) {
            d.put("characteristic.managerIdentifierList", strArr[0]);
            d.put("characteristic.managerIdentifierList2", strArr[1]);
        }
        return c(d);
    }

    public static String c() {
        String str;
        try {
            str = d();
        } catch (SocketException unused) {
            LogUtil.b("WeightIntellLifeUtils", "getConfusedMac SocketException ");
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
        LogUtil.c("WeightIntellLifeUtils", "getConfusedMac evenDigitsMac ", sb.toString());
        return ResourceManager.e().d(sb.toString(), "SHA-256");
    }

    public static String d() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        String str = null;
        if (networkInterfaces == null) {
            LogUtil.h("WeightIntellLifeUtils", "can not get network interface !");
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            if ("wlan0".equals(nextElement.getName())) {
                byte[] hardwareAddress = nextElement.getHardwareAddress();
                if (hardwareAddress == null || hardwareAddress.length == 0) {
                    LogUtil.h("WeightIntellLifeUtils", "addressByte is empty");
                } else {
                    StringBuilder sb = new StringBuilder(10);
                    for (byte b : hardwareAddress) {
                        sb.append(String.format(Locale.ENGLISH, "%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    str = sb.toString();
                }
            }
        }
        return str;
    }
}
