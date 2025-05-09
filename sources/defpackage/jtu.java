package defpackage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jtu {

    /* renamed from: a, reason: collision with root package name */
    private static long f14083a = 0;
    private static String e = "";

    private static boolean d(String str) {
        List<ResolveInfo> queryBroadcastReceivers;
        Intent intent = new Intent();
        intent.setAction(str);
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null || (queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0)) == null) {
            return false;
        }
        return !queryBroadcastReceivers.isEmpty();
    }

    public static void a(DeviceInfo deviceInfo) {
        if (!CommonUtil.bh()) {
            LogUtil.h("HiDiskInteractiveUtil", "sendConnectStateToHiDisk is not emui or HarmonyOS.");
            return;
        }
        if (!d("com.huawei.health.action.CONNECT_STATUS_CHANGE")) {
            LogUtil.h("HiDiskInteractiveUtil", "sendConnectStateToHiDisk broadcast is not registered.");
            return;
        }
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("HiDiskInteractiveUtil", "sendConnectStateToHiDisk deviceInfo or deviceIdentify is empty.");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceUdid()) && TextUtils.isEmpty(deviceInfo.getUuid())) {
            LogUtil.h("HiDiskInteractiveUtil", "sendConnectStateToHiDisk device uuid is empty.");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("HiDiskInteractiveUtil", "sendConnectStateToHiDisk connectState：", Integer.valueOf(deviceConnectState));
        if (deviceConnectState == 4) {
            e = deviceInfo.getDeviceIdentify();
            f14083a = System.currentTimeMillis();
        }
        if (deviceConnectState == 3) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = f14083a;
            if (e.equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && currentTimeMillis - j < 500) {
                LogUtil.h("HiDiskInteractiveUtil", "sendConnectStateToHiDisk pair fail, no send broadcast.");
                e = "";
                f14083a = 0L;
                return;
            }
        }
        c(deviceInfo, deviceConnectState);
    }

    private static void c(DeviceInfo deviceInfo, int i) {
        Intent intent = new Intent("com.huawei.health.action.CONNECT_STATUS_CHANGE");
        intent.setFlags(32);
        String deviceUdid = deviceInfo.getDeviceUdid();
        if (TextUtils.isEmpty(deviceUdid)) {
            intent.putExtra("deviceId", deviceInfo.getUuid());
        } else {
            intent.putExtra("deviceId", deviceUdid);
        }
        if (i == 2) {
            intent.putExtra("connectType", "1");
            intent.putExtra("deviceName", deviceInfo.getDeviceName());
            intent.putExtra("deviceModule", deviceInfo.getDeviceModel());
        } else if (i == 3) {
            intent.putExtra("connectType", "0");
        } else {
            LogUtil.h("HiDiskInteractiveUtil", "sendConnectStateToHiDisk no send broadcast.");
            return;
        }
        BaseApplication.getContext().sendBroadcast(intent, "com.huawei.health.permission.CONNECTSTATUS");
    }
}
