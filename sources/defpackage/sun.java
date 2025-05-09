package defpackage;

import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class sun {
    private static final String b = "WalletHiAnalytics";
    private static volatile boolean e = false;

    public static void b(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        LinkedHashMap<String, String> c = c(linkedHashMap);
        if (e && sup.c()) {
            c.put("log_type", "1");
            c.put("eventHappenedTime", b());
            if (1 == i) {
                b(str, c);
                return;
            } else {
                a(str, c);
                return;
            }
        }
        stq.c(b, "onOperationEventForChart Fail, cause Switch is closed or not hasInitAnalytics", false);
    }

    public static void e(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        LinkedHashMap<String, String> c = c(linkedHashMap);
        if (e && sup.c()) {
            c.put("log_type", "2");
            c.put("eventHappenedTime", b());
            if (1 == i) {
                e(str, c);
                return;
            } else {
                d(str, c);
                return;
            }
        }
        stq.c(b, "onOperationEventForVoc Fail, cause Switch is closed or not hasInitAnalytics", false);
    }

    private static void a(String str, LinkedHashMap<String, String> linkedHashMap) {
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("hianalytics_for_health");
        if (instanceByTag == null || linkedHashMap == null) {
            return;
        }
        instanceByTag.setAppid("com.huawei.wallet");
        linkedHashMap.put("uid", e());
        instanceByTag.onEvent(1, str, linkedHashMap);
        stq.c(b, "onDefaultOperationEventForChart health SUCCESS", false);
    }

    private static void d(String str, LinkedHashMap<String, String> linkedHashMap) {
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("hianalytics_for_health");
        if (instanceByTag == null || linkedHashMap == null) {
            return;
        }
        instanceByTag.setAppid("com.huawei.wallet");
        linkedHashMap.put("uid", e());
        instanceByTag.onEvent(1, str, linkedHashMap);
        stq.c(b, "onDefaultOperationEventForChart health SUCCESS", false);
    }

    private static void b(String str, LinkedHashMap<String, String> linkedHashMap) {
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("hianalytics_no_udid");
        if (instanceByTag != null) {
            instanceByTag.onEvent(1, str, linkedHashMap);
            stq.c(b, "onNoUDIDOperationEventForChart SUCCESS", false);
        }
    }

    private static void e(String str, LinkedHashMap<String, String> linkedHashMap) {
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("hianalytics_no_udid");
        if (instanceByTag != null) {
            instanceByTag.onEvent(1, str, linkedHashMap);
            stq.c(b, "onNoUDIDOperationEventForVoc SUCCESS", false);
        }
    }

    private static String b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date());
    }

    private static String e() {
        return OverSeaMangerUtil.c(ssz.e()).e().m();
    }

    private static LinkedHashMap<String, String> c(LinkedHashMap linkedHashMap) {
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
        }
        linkedHashMap.put("real_device_model", OverSeaMangerUtil.c(ssz.e()).e().j());
        linkedHashMap.put("real_app_package", OverSeaMangerUtil.c(ssz.e()).e().c());
        linkedHashMap.put("real_device_info_soft_version", OverSeaMangerUtil.c(ssz.e()).e().n());
        linkedHashMap.put("wear_wallet_sdk_version", stx.b());
        return linkedHashMap;
    }
}
