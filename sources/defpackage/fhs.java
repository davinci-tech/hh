package defpackage;

import android.content.Context;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sportservice.manager.ManagerComponent;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import java.util.Map;

/* loaded from: classes4.dex */
public class fhs {
    public static boolean b(int i) {
        if (i == 264 || i == 265) {
            return true;
        }
        switch (i) {
            case OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER /* 273 */:
            case OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE /* 274 */:
            case OldToNewMotionPath.SPORT_TYPE_TREAD_MACHINE /* 275 */:
                return true;
            default:
                return false;
        }
    }

    public static boolean b(int i, int i2) {
        return (i2 == 264 || i2 == 265) && (i == 0 || i == 1 || i == 2);
    }

    public static boolean c(int i) {
        return i > 0 && i < 220;
    }

    public static boolean d(int i) {
        return i == 264 || i == 281 || i == 273;
    }

    public static boolean e(int i) {
        return i == 274 || i == 273 || i == 265;
    }

    public static boolean g(int i) {
        if (!e(i)) {
            return (i == 283 || cvs.d() == null || !cvs.d().isSupportWorkoutCapabilicy()) ? false : true;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "SportService_SportServiceUtils");
        if (deviceInfo == null) {
            LogUtil.h("SportService_SportServiceUtils", "isSportTypeForLinkage getConnectDeviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 0);
    }

    public static boolean i(int i) {
        return g(i) && cvs.d() != null && cvs.d().isSupportInformCloseOrOpen() && cvs.d().isSupportWorkout();
    }

    public static int a(int i) {
        return i(i) ? 1 : 2;
    }

    public static String c(Context context) {
        return FoundationCommonUtil.getAndroidId(context);
    }

    public static ManagerComponent e(Map<String, ManagerComponent> map, int i) {
        if (map == null) {
            LogUtil.h("SportService_SportServiceUtils", "getAchieveComponent managerMap == null");
            return null;
        }
        if (i == 283) {
            return map.get(ComponentName.SKIP_ACHIEVE_MANAGER);
        }
        switch (i) {
            case 400000:
                return map.get(ComponentName.STAND_FLEXION_ACHIEVE_MANAGER);
            case 400001:
                return map.get(ComponentName.SUPINE_LEG_ACHIEVE_MANAGER);
            default:
                LogUtil.a("SportService_SportServiceUtils", "getAchieveComponent sportType:", Integer.valueOf(i));
                return null;
        }
    }
}
