package defpackage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.selfupdate.appupdate.UpdateBase;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import health.compact.a.LocalBroadcast;
import java.util.List;

/* loaded from: classes5.dex */
public class kyh {
    public static void c(int i, int i2, String str) {
        b(i, i2, "", "", str);
    }

    public static void b(int i, int i2, String str, String str2, String str3) {
        LogUtil.a("WearOtaUpdateUtil", "broadcastCheckState: state = ", Integer.valueOf(i), ", result = ", Integer.valueOf(i2), ",content = ", str);
        Intent intent = new Intent("action_app_check_new_version_state");
        intent.addFlags(1610612736);
        intent.putExtra("state", i);
        intent.putExtra("result", i2);
        intent.putExtra("content", str);
        intent.putExtra("extra_band_imei", str3);
        if (TextUtils.equals(str2, "true")) {
            intent.putExtra("isForced", true);
        } else {
            intent.putExtra("isForced", false);
        }
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void c(DeviceInfo deviceInfo, String str, UpdateBase updateBase, boolean z, AppCheckNewVersionHandler appCheckNewVersionHandler) {
        if (deviceInfo == null) {
            LogUtil.a("WearOtaUpdateUtil", "deviceInfo is null");
            return;
        }
        LogUtil.a("WearOtaUpdateUtil", "manualBandCheckNewVersion");
        c(10, -1, deviceInfo.getSecurityDeviceId());
        if (updateBase != null) {
            updateBase.checkBandNewVersion(deviceInfo.getProductType(), str, deviceInfo.getSecurityDeviceId(), z, appCheckNewVersionHandler);
        }
    }

    public static boolean c(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = context.getSystemService("activity") instanceof ActivityManager ? (ActivityManager) context.getSystemService("activity") : null;
        if (activityManager == null || (runningTasks = activityManager.getRunningTasks(1)) == null || runningTasks.size() <= 0 || runningTasks.get(0).topActivity == null) {
            return false;
        }
        String className = runningTasks.get(0).topActivity.getClassName();
        LogUtil.a("WearOtaUpdateUtil", "current activity :", className);
        return TextUtils.equals(className, "com.huawei.ui.main.stories.about.activity.update.AppUpdateDialogActivity") || TextUtils.equals(className, "com.huawei.ui.device.activity.update.UpdateVersionActivity") || TextUtils.equals(className, "com.huawei.ui.device.activity.update.DeviceOtaActivity");
    }

    public static void d() {
        Intent intent = new Intent("action_ota_check_new_version_state");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }
}
