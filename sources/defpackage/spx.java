package defpackage;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class spx {
    public static void a(List<HiHealthClient> list) {
        if (koq.b(list)) {
            return;
        }
        HashSet<Integer> d = d("02B");
        Iterator<HiHealthClient> it = list.iterator();
        while (it.hasNext()) {
            HiHealthClient next = it.next();
            if (next.getHiDeviceInfo() != null) {
                int deviceType = next.getHiDeviceInfo().getDeviceType();
                if (d.contains(Integer.valueOf(deviceType)) || deviceType == 68) {
                    it.remove();
                }
            }
        }
    }

    public static HashSet<Integer> d(String str) {
        HashSet<Integer> hashSet = new HashSet<>(16);
        if (TextUtils.isEmpty(str)) {
            return hashSet;
        }
        Iterator<ProductMapInfo> it = ProductMap.d().iterator();
        while (it.hasNext()) {
            ProductMapInfo next = it.next();
            if (str.equals(next.e())) {
                hashSet.add(Integer.valueOf(next.c()));
            }
        }
        return hashSet;
    }

    public static String a(HiDeviceInfo hiDeviceInfo) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        HiUserPreference userPreference2 = HiHealthManager.d(BaseApplication.e()).getUserPreference("privacy_data_source_nfc_scan_D85D9FAF0AA347F39179FA0DC65CC9D7");
        Map map = userPreference != null ? (Map) HiJsonUtil.b(userPreference.getValue(), new TypeToken<Map<String, Object>>() { // from class: spx.3
        }.getType()) : null;
        Map map2 = userPreference2 != null ? (Map) HiJsonUtil.b(userPreference2.getValue(), new TypeToken<Map<String, Object>>() { // from class: spx.2
        }.getType()) : null;
        String deviceUniqueCode = hiDeviceInfo.getDeviceUniqueCode();
        if (a(map, deviceUniqueCode)) {
            return String.valueOf(map.get(deviceUniqueCode));
        }
        if (a(map2, deviceUniqueCode)) {
            return String.valueOf(map2.get(deviceUniqueCode));
        }
        String deviceName = hiDeviceInfo.getDeviceName();
        return "Sport Health Manual".equals(deviceName) ? "UNKNOWN" : deviceName;
    }

    private static boolean a(Map<String, Object> map, String str) {
        return (map == null || !map.containsKey(str) || map.get(str) == null) ? false : true;
    }

    public static void c(HiHealthClient hiHealthClient) {
        HiDeviceInfo hiDeviceInfo = hiHealthClient.getHiDeviceInfo();
        if (!e(hiDeviceInfo.getDeviceUniqueCode()) || hiDeviceInfo.getDeviceType() == 32) {
            return;
        }
        hiDeviceInfo.setDeviceName(a(hiDeviceInfo));
    }

    private static boolean e(String str) {
        return !"-1".equals(str);
    }
}
