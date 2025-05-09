package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes3.dex */
public class blo {
    public static Map<String, UniteDevice> b(Map<String, DeviceInfo> map, Map<String, ExternalDeviceCapability> map2) {
        HashMap hashMap = new HashMap(16);
        if (map != null && !map.isEmpty() && map2 != null) {
            for (Map.Entry<String, DeviceInfo> entry : map.entrySet()) {
                String key = entry.getKey();
                DeviceInfo value = entry.getValue();
                UniteDevice uniteDevice = new UniteDevice();
                uniteDevice.setIdentify(key);
                uniteDevice.setDeviceInfo(value);
                uniteDevice.setCapability(map2.get(key));
                hashMap.put(key, uniteDevice);
            }
        }
        return hashMap;
    }

    public static UniteDevice d(String str, String str2) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceMac(str);
        deviceInfo.setDeviceName(str2);
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(str);
        uniteDevice.setDeviceInfo(deviceInfo);
        return uniteDevice;
    }

    public static String e() {
        return UUID.randomUUID().toString().replace(Constants.LINK, "").toUpperCase(Locale.ROOT);
    }

    public static String a(String str, String str2) {
        LogUtil.c("EntityConversionUtil", "getDeviceUdid enter");
        if (str == null) {
            return str;
        }
        String str3 = str2 + str;
        if (!str.equals(str2)) {
            str = str3;
        }
        String a2 = bmr.a(str);
        if (a2 == null) {
            LogUtil.a("EntityConversionUtil", "shaDeviceId is null");
        }
        return a2;
    }

    public static String a(Integer num, Integer num2) {
        bji bjiVar = new bji();
        if (num != null) {
            bjiVar.a(num.intValue());
        }
        if (num2 != null) {
            bjiVar.b(num2.intValue());
        }
        return new Gson().toJson(bjiVar);
    }

    public static boolean c(DeviceInfo deviceInfo) {
        ExternalDeviceCapability a2;
        DeviceCapability compatibleCapacity;
        if (deviceInfo == null) {
            return false;
        }
        if (deviceInfo.getSportVersion() == 1) {
            return true;
        }
        return (deviceInfo.getSportVersion() == 0 || (a2 = bjx.a().a(deviceInfo.getDeviceMac())) == null || (compatibleCapacity = a2.getCompatibleCapacity()) == null || !compatibleCapacity.isSupportSmartWatchVersionStatus()) ? false : true;
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains(":") || str.length() <= 12) {
            return str.replace(":", "");
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            String a2 = bnc.a(messageDigest.digest());
            return a2.length() >= 24 ? a2.replace("/", SampleEvent.SEPARATOR) : a2;
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
