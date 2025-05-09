package defpackage;

import android.bluetooth.BluetoothAdapter;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;

/* loaded from: classes.dex */
public final class gij {
    public static boolean d() {
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "FitnessDeviceUtils"), 41);
    }

    public static boolean a() {
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "FitnessDeviceUtils"), 40);
    }

    public static boolean b() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
        return false;
    }

    public static boolean e() {
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "FitnessDeviceUtils"), 169);
    }

    public static boolean c() {
        return d() || e();
    }
}
