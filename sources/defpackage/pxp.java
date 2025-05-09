package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.threecircle.ActiveTipStringUtils;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class pxp {
    public static int d(int i, int i2, int i3, int i4) {
        return (i4 <= 0 || i3 != 4 || i == i4) ? i > i2 ? -1 : 1 : i2 == i4 ? i > i2 ? -3 : 3 : i > i2 ? -4 : 4;
    }

    private static boolean j(int i) {
        return i == 1;
    }

    private static void a(AnalyticsValue analyticsValue, Map<String, Object> map) {
        if (analyticsValue == null || map == null) {
            LogUtil.h("R_ActiveBiUtils", "setEvent analyticsValue ", analyticsValue, " map ", map);
        } else {
            map.put("click", 1);
            ixx.d().d(BaseApplication.e(), analyticsValue.value(), map, 0);
        }
    }

    private static int c(ActiveTipStringUtils.TipType tipType, int i) {
        if (tipType == ActiveTipStringUtils.TipType.STAND_HOUR || tipType == ActiveTipStringUtils.TipType.TASK_STAND_HOUR) {
            return 3;
        }
        if (tipType == ActiveTipStringUtils.TipType.WORKOUT || tipType == ActiveTipStringUtils.TipType.TASK_WORKOUT) {
            return i == 257 ? 6 : 7;
        }
        if (tipType == ActiveTipStringUtils.TipType.CALORIES || tipType == ActiveTipStringUtils.TipType.TASK_CALORIES) {
            return i == 257 ? 8 : 9;
        }
        return 2;
    }

    public static void b(int i, int i2, int i3, ActiveTipStringUtils.TipType tipType) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("type", Integer.valueOf(c(tipType, i3)));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040108, hashMap);
    }

    public static void b(Map<String, Object> map, int i, int i2) {
        map.put("from", Integer.valueOf(i));
        map.put("event", Integer.valueOf(i2));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040110, map);
    }

    public static void d(int i, int i2) {
        b(new HashMap(3), i, i2);
    }

    public static void e(int i, int i2, boolean z) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("suggest", Boolean.valueOf(z));
        b(hashMap, i, i2);
    }

    public static void b(String str, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        if (i2 != -1) {
            hashMap.put("type", Integer.valueOf(i2));
        }
        hashMap.put("from", Integer.valueOf(i));
        if ("3".equals(str)) {
            hashMap.put("switchStatus", String.valueOf(SharedPreferenceManager.a("fitness_step_module_id", "active_hours_set_alert_toggle", j(i2))));
        }
        a(AnalyticsValue.REMINDER_SWITCH_IS_CHANGE_1040096, hashMap);
    }

    public static void c(int i, String str, boolean z) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("event", "3");
        hashMap.put("type", Integer.valueOf(a(str)));
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("switchStatus", String.valueOf(z));
        a(AnalyticsValue.REMINDER_SWITCH_IS_CHANGE_1040096, hashMap);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int a(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1062371205:
                if (str.equals("900200004")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1062371178:
                if (str.equals("900200010")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1062371177:
                if (str.equals("900200011")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1062371146:
                if (str.equals("900200021")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return 1;
        }
        if (c == 1) {
            return 2;
        }
        if (c == 2) {
            return 3;
        }
        if (c == 3) {
            return 4;
        }
        LogUtil.h("R_ActiveBiUtils", "getConfigBiType with", str);
        return -1;
    }

    public static void b(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("from", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040113, hashMap);
    }

    public static void e(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040112, hashMap);
    }

    public static void c(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("adjustType", Integer.valueOf(i3));
        hashMap.put("value", Integer.valueOf(i4));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040111, hashMap);
    }

    public static void e(int i, int i2) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("event", Integer.valueOf(i2));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040109, hashMap);
    }

    public static void a(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_HOME_STEP_DETAIL_2010002, hashMap);
    }

    public static void a() {
        a(AnalyticsValue.HEALTH_HOME_CAL_DETAIL_2010006, new HashMap(1));
    }

    public static void d(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040115, hashMap);
    }

    public static void e(ActiveTipStringUtils.TipType tipType, int i) {
        int a2 = a(tipType);
        if (a2 < 0) {
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("type", Integer.valueOf(a2));
        hashMap.put("event", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_CIRCLE_RING_TASK_TIP_2010232, hashMap);
    }

    public static void e(ActiveTipStringUtils.TipType tipType) {
        e(tipType, a(tipType));
    }

    private static int a(ActiveTipStringUtils.TipType tipType) {
        if (ActiveTipStringUtils.TipType.TASK_SUBSCRIBE.equals(tipType)) {
            return 1;
        }
        if (ActiveTipStringUtils.TipType.TASK_BONUS_GET.equals(tipType)) {
            return 2;
        }
        return ActiveTipStringUtils.TipType.TASK_BONUS_USE.equals(tipType) ? 3 : -1;
    }

    public static void i(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("action", Integer.valueOf(i));
        a(AnalyticsValue.HEALTH_CIRCLE_RING_TASK_SUBSCRIBE_2010233, hashMap);
    }

    public static void c(int i) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("type", 1);
        hashMap.put("from", 1);
        hashMap.put("result", h(i));
        a(AnalyticsValue.HEALTH_CIRCLE_RING_TASK_GET_POINTS_2010234, hashMap);
    }

    private static String h(int i) {
        return i == 0 ? "true" : i == 12038138 ? "total_limit" : i == 12038139 ? "daily_limit" : i == 12038140 ? "account_problem" : i == 12030004 ? "network_problem" : String.valueOf(i);
    }

    public static void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("from", 1);
        a(AnalyticsValue.HEALTH_CIRCLE_RING_TASK_GO_VMALL_2010236, hashMap);
    }
}
