package defpackage;

import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class lbc {
    public static boolean a(int i) {
        return i == 274 || i == 273 || i == 265;
    }

    public static boolean c(int i) {
        return i == 264;
    }

    public static boolean e(int i) {
        return i == 283;
    }

    public static boolean d(int i) {
        if (!a(i)) {
            return (i == 283 || cvs.d() == null || !cvs.d().isSupportWorkoutCapabilicy()) ? false : true;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_ConvertUtil");
        if (deviceInfo == null) {
            LogUtil.h("Track_ConvertUtil", "isSportTypeForLinkage getConnectDeviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 0);
    }

    public static boolean f(int i) {
        Object[] objArr = new Object[4];
        objArr[0] = "isSportTypeForLinkage(sportType)";
        objArr[1] = Boolean.valueOf(d(i));
        objArr[2] = "CapabilityUtils.getDeviceCapability() != null";
        objArr[3] = Boolean.valueOf(cvs.d() != null);
        LogUtil.a("Track_ConvertUtil", objArr);
        if (cvs.d() == null) {
            return false;
        }
        LogUtil.a("Track_ConvertUtil", "CapabilityUtils.getDeviceCapability().isSupportInformCloseOrOpen()", Boolean.valueOf(cvs.d().isSupportInformCloseOrOpen()), "CapabilityUtils.getDeviceCapability().isSupportWorkout()", Boolean.valueOf(cvs.d().isSupportWorkout()));
        return d(i) && cvs.d() != null && cvs.d().isSupportInformCloseOrOpen() && cvs.d().isSupportWorkout();
    }

    public static boolean b(int i) {
        boolean i2 = gwg.i(BaseApplication.getContext());
        boolean bd = CommonUtil.bd();
        boolean bf = CommonUtil.bf();
        boolean z = i == 264 || i == 283;
        LogUtil.a("Track_ConvertUtil", "isSupportMusic? ", Boolean.valueOf(i2), " and ", Boolean.valueOf(bd), " isHonor ", Boolean.valueOf(bf));
        LogUtil.a("Track_ConvertUtil", "isShowSportType ", Boolean.valueOf(z));
        if (i2 && bd) {
            return bf && !z;
        }
        return true;
    }
}
