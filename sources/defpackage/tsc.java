package defpackage;

import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.nfc.PluginPayAdapter;

/* loaded from: classes7.dex */
public class tsc {
    public static String f(String str) {
        return Ctry.d(str, "getReservedness", "device_reservedness");
    }

    public static String b(String str) {
        return Ctry.d(str, "getCapability", "device_capability");
    }

    public static String a(String str) {
        return Ctry.d(str, "getBasicInfo", "device_basic_info");
    }

    public static String i(String str) {
        return Ctry.d(str, "getIdentify", "device_identify");
    }

    public static String o(String str) {
        return Ctry.d(str, "getWearEngineDeviceId", "device_wear_engine_device_id");
    }

    public static String n(String str) {
        return Ctry.d(str, "getSoftwareVersion", "device_soft_version");
    }

    public static boolean l(String str) {
        return Ctry.a(str, "getSupportOTA", "device_is_support_ota");
    }

    public static String e(String str) {
        return Ctry.d(str, "getExtra", "device_extra");
    }

    public static int h(String str) {
        return Ctry.d(str, "getP2pCapability", "device_p2p_capability", 2);
    }

    public static int g(String str) {
        return Ctry.d(str, "getMonitorCapability", "device_monitor_capability", 2);
    }

    public static int j(String str) {
        return Ctry.d(str, "getNotifyCapability", "device_notify_capability", 2);
    }

    public static int k(String str) {
        return Ctry.d(str, "getSensorCapability", "device_sensor_capability", 2);
    }

    public static String d(String str) {
        return Ctry.d(str, "getDeviceCategory", PluginPayAdapter.DEVICE_CATEGORY);
    }

    public static int c(String str) {
        if (tra.a("powerMode")) {
            return Ctry.d(str, "getDeviceType", DeviceCategoryFragment.DEVICE_TYPE, -1);
        }
        return -1;
    }
}
