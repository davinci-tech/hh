package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jpy {
    private static int b;
    private static Map<String, jpw> d = new HashMap(16);

    public static void b(String str, int i) {
        jpw jpwVar;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceBatteryInfo", "configBattery id is empty");
            return;
        }
        if (d.containsKey(str)) {
            jpwVar = d.get(str);
            jpwVar.e(i);
        } else {
            jpwVar = new jpw(i);
        }
        d.put(str, jpwVar);
    }

    public static void c(String str, jpw jpwVar) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceBatteryInfo", "configBatteryCollection id is empty");
        } else {
            d.put(str, jpwVar);
        }
    }

    public static void d(int i) {
        b = i;
    }

    public static int b(String str) {
        return e(str).d();
    }

    public static jpw e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceBatteryInfo", "fetchDeviceBattery id is empty");
            return new jpw(-1);
        }
        jpw jpwVar = d.get(str);
        if (jpwVar != null) {
            return jpwVar;
        }
        LogUtil.h("DeviceBatteryInfo", "fetchDeviceBattery device battery is null");
        return new jpw(-1);
    }
}
