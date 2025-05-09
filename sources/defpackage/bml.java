package defpackage;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.SharedPreferenceManager;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class bml {
    public static boolean b(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.a("WearLinkCommonUtil", "isServiceRunning null");
            return false;
        }
        Object systemService = BaseApplication.e().getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> runningServices = systemService instanceof ActivityManager ? ((ActivityManager) systemService).getRunningServices(30) : null;
        if (runningServices == null || runningServices.size() <= 0) {
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
        while (it.hasNext()) {
            if (it.next().service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String d() {
        LogUtil.c("WearLinkCommonUtil", "WearLinkCommonUtil getSnInfo");
        String str = Build.SERIAL;
        if (Build.VERSION.SDK_INT >= 29) {
            String c = bkx.c();
            if (!TextUtils.isEmpty(c)) {
                str = c.toUpperCase(Locale.ENGLISH);
            } else {
                LogUtil.a("WearLinkCommonUtil", "androidId is isEmpty");
            }
        } else if (bma.a(BaseApplication.e(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            str = bkx.a();
        }
        String encodeToString = Base64.encodeToString(str.getBytes(Charset.defaultCharset()), 0);
        String substring = encodeToString.length() >= 6 ? encodeToString.substring(0, 6) : "000000";
        LogUtil.c("WearLinkCommonUtil", "sn:", blt.a(substring));
        return substring;
    }

    public static boolean b(Context context, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("WearLinkCommonUtil", "reconnectDeviceInfo is null");
            return false;
        }
        boolean z = deviceInfo.getSupportAccountSwitch() == 1;
        LogUtil.c("WearLinkCommonUtil", "reconnectDeviceInfo is ", deviceInfo.getDeviceName(), "isSupportAccountSwitch ", Boolean.valueOf(z));
        if (z) {
            return snu.e().isLogin(context);
        }
        return true;
    }

    public static boolean e(com.huawei.devicesdk.entity.DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("WearLinkCommonUtil", "reconnectDeviceInfo is null");
            return false;
        }
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            return false;
        }
        DeviceCapability b = bjx.a().b(deviceInfo.getDeviceMac());
        boolean isSupportAccountSwitch = b != null ? b.isSupportAccountSwitch() : false;
        LogUtil.c("WearLinkCommonUtil", "reconnectDeviceInfo is ", deviceInfo.getDeviceName(), "isSupportAccountSwitch ", Boolean.valueOf(isSupportAccountSwitch));
        if (isSupportAccountSwitch) {
            return snu.e().isLogin(BaseApplication.e());
        }
        return true;
    }

    public static boolean c() {
        return ProcessUtil.b().endsWith(":PhoneService");
    }
}
