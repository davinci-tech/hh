package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.wearengine.monitor.MonitorItem;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class tra {

    /* renamed from: a, reason: collision with root package name */
    private static volatile int f17355a = 0;
    private static volatile int d = -1;
    private static Map<String, Integer> e;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        e = concurrentHashMap;
        concurrentHashMap.put("device_get_bonded_device_ex", 2);
        e.put("p2p_send_extra", 2);
        e.put("p2p_get_device_app_version_code", 4);
        e.put("device_get_hi_link_device_id", 2);
        e.put(MonitorItem.MONITOR_CHARGE_STATUS.getName(), 2);
        e.put(MonitorItem.MONITOR_ITEM_LOW_POWER.getName(), 2);
        e.put(MonitorItem.MONITOR_POWER_STATUS.getName(), 2);
        e.put(MonitorItem.MONITOR_ITEM_SLEEP.getName(), 2);
        e.put(MonitorItem.MONITOR_ITEM_SPORT.getName(), 2);
        e.put(MonitorItem.MONITOR_ITEM_WEAR.getName(), 2);
        e.put(MonitorItem.MONITOR_ITEM_HEART_RATE_ALARM.getName(), 2);
        e.put("monitor_query", 2);
        e.put("notify_notify", 2);
        e.put("sensor", 2);
        e.put("auth_pre_start_auth", 2);
        e.put("p2p_cancel_file_transfer", 5);
        e.put("wear_user_status", 6);
        e.put("motion_sensor", 6);
        e.put("device_get_all_bonded_device", 6);
        e.put("is_support_ota", 7);
        e.put("device_get_common_device", 8);
        e.put("set_frequency_type", 8);
        e.put("p2p_send_file_transfer_way_report", 9);
        e.put("query_device_capability", 10);
        e.put(PluginPayAdapter.KEY_DEVICE_INFO_SN, 12);
        e.put("powerMode", 13);
    }

    public static void d(int i) {
        d = i;
    }

    public static int a() {
        if (f17355a != 0) {
            return f17355a;
        }
        f17355a = c();
        return f17355a;
    }

    public static boolean a(String str) {
        tov.a("ApiLevelUtil", "isServiceSupport apiName:" + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int intValue = e.get(str) == null ? 0 : e.get(str).intValue();
        if (intValue == 0) {
            tov.c("ApiLevelUtil", "isServiceSupport inputApiLevel is null");
            return false;
        }
        tov.a("ApiLevelUtil", "isServiceSupport serviceApiLevel: " + d + ", minSupportApiLevel:" + intValue);
        return d >= intValue;
    }

    private static int c() {
        Context c = trr.c();
        PackageManager packageManager = c.getPackageManager();
        if (packageManager == null) {
            tov.c("ApiLevelUtil", "getMetaDataApiLevel PackageManager is null");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(c.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                int i = applicationInfo.metaData.getInt("com.huawei.wearengine.sdk.api_level");
                tov.a("ApiLevelUtil", "getMetaDataApiLevel apiLevel:" + i);
                return i;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            tov.d("ApiLevelUtil", "getMetaDataApiLevel PackageManager.NameNotFoundException");
        }
        return 0;
    }
}
