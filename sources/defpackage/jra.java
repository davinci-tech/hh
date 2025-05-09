package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class jra {
    public static void b(int i, boolean z, long j, DeviceInfo deviceInfo) {
        LogUtil.a("DeviceOperationUtils", "enter fitnessSyncEvent result:", Integer.valueOf(i));
        if (deviceInfo == null) {
            LogUtil.h("DeviceOperationUtils", "fitnessSyncEvent deviceInfo is null.");
            return;
        }
        if (j == 0) {
            LogUtil.h("DeviceOperationUtils", "fitnessSyncEvent startTime is error.");
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        b(linkedHashMap, deviceInfo, j);
        if (z) {
            linkedHashMap.put("sync_type", "1");
        } else {
            linkedHashMap.put("sync_type", "0");
        }
        if (i == 0 || i == 4) {
            linkedHashMap.put("result_code", "2");
        } else if (i == 300004) {
            linkedHashMap.put("result_code", "1");
        } else if (i == 100000) {
            linkedHashMap.put("result_code", "0");
        } else if (i == -1) {
            linkedHashMap.put("result_code", "4");
        } else {
            linkedHashMap.put("result_code", "3");
            linkedHashMap.put("error_code", String.valueOf(i));
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_BASE_DATA_SYNC_80020011.value(), linkedHashMap);
    }

    public static void b(int i, long j, int i2, DeviceInfo deviceInfo) {
        LogUtil.a("DeviceOperationUtils", "enter sendWorkoutSyncEvent result:", Integer.valueOf(i));
        if (deviceInfo == null) {
            LogUtil.h("DeviceOperationUtils", "workoutSyncEvent deviceInfo is null.");
            return;
        }
        if (j == 0) {
            LogUtil.h("DeviceOperationUtils", "workoutSyncEvent startTime is error.");
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        b(linkedHashMap, deviceInfo, j);
        LogUtil.a("DeviceOperationUtils", "workoutSyncEvent mWorkoutDataNum:", Integer.valueOf(i2));
        linkedHashMap.put("count", String.valueOf(i2));
        if (i == 0) {
            linkedHashMap.put("result_code", "2");
        } else if (i == 1) {
            linkedHashMap.put("result_code", "1");
        } else if (i == 100000) {
            linkedHashMap.put("result_code", "0");
        } else {
            linkedHashMap.put("result_code", "3");
            linkedHashMap.put("error_code", String.valueOf(i));
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_WORKOUT_DATA_SYNC_80020012.value(), linkedHashMap);
    }

    private static void b(LinkedHashMap<String, String> linkedHashMap, DeviceInfo deviceInfo, long j) {
        if (linkedHashMap == null) {
            LogUtil.h("DeviceOperationUtils", "fillCommonData map is null.");
            return;
        }
        int productType = deviceInfo.getProductType();
        if (productType == -1) {
            LogUtil.h("DeviceOperationUtils", "fillCommonData productType is unkonw");
            return;
        }
        linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(productType));
        linkedHashMap.put("device_classfication", String.valueOf(cwc.b(productType)));
        if (deviceInfo.getDeviceConnectState() == 2) {
            linkedHashMap.put("connect_status", "1");
        } else {
            linkedHashMap.put("connect_status", "0");
        }
        linkedHashMap.put("starttime", String.valueOf(j));
        long currentTimeMillis = System.currentTimeMillis();
        linkedHashMap.put("endtime", String.valueOf(currentTimeMillis));
        long j2 = currentTimeMillis - j;
        if (j2 > 0) {
            linkedHashMap.put("duration", String.valueOf(j2));
        }
    }
}
