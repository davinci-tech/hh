package defpackage;

import com.google.gson.Gson;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes5.dex */
public class kkm {
    public static Map<String, Object> d(Object obj, String str) {
        HashMap hashMap = new HashMap(16);
        Gson gson = new Gson();
        if (obj != null) {
            if ((obj instanceof String) || (obj instanceof JSONArray)) {
                hashMap.put("value", obj.toString());
            } else {
                hashMap.put("value", gson.toJson(obj));
            }
        }
        if (!hashMap.containsKey("code")) {
            hashMap.put("code", 100000);
        }
        hashMap.put("funcName", str);
        return hashMap;
    }

    public static boolean a(List<DeviceInfo> list) {
        if (list == null) {
            return false;
        }
        LogUtil.a("RemoteUtils", "getCurrentDeviceInfo() deviceInfoList.size(): ", Integer.valueOf(list.size()));
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getProductType() == 11) {
                return true;
            }
        }
        LogUtil.h("RemoteUtils", "getCurrentDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
        return false;
    }

    public static int d(List<DeviceInfo> list) {
        LogUtil.a("RemoteUtils", "getTrackTypeByClassification() enter");
        int i = -1;
        if (list == null) {
            LogUtil.h("RemoteUtils", "deviceInfoList is null.");
            return -1;
        }
        int c = c(list);
        LogUtil.a("RemoteUtils", "getTrackTypeByClassification() deviceClassification: ", Integer.valueOf(c));
        if (c == 1) {
            i = 4;
        } else if (c == 2) {
            i = 5;
        } else if (c != 3) {
            LogUtil.h("RemoteUtils", "getTrackTypeByClassification() no this trackType: ", -1);
        } else {
            i = 6;
        }
        LogUtil.a("RemoteUtils", "getTrackTypeByClassification() trackType: ", Integer.valueOf(i));
        return i;
    }

    private static int c(List<DeviceInfo> list) {
        DeviceInfo deviceInfo;
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                deviceInfo = null;
                break;
            }
            deviceInfo = it.next();
            if (deviceInfo.getDeviceActiveState() == 1 && !cvt.c(deviceInfo.getProductType())) {
                break;
            }
        }
        int e = deviceInfo != null ? jte.e(deviceInfo.getProductType()) : -1;
        LogUtil.a("RemoteUtils", "getConncetedDeviceClassification() deviceInfoList.size()", Integer.valueOf(list.size()), " deviceClassification", Integer.valueOf(e));
        return e;
    }
}
