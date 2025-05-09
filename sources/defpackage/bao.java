package defpackage;

import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class bao {

    /* renamed from: a, reason: collision with root package name */
    public static final String f305a = String.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION);
    private static int c;
    private static int d;

    public static boolean b(String str) {
        return jfa.c(f305a, str);
    }

    public static boolean c(String str) {
        return jfa.e(f305a, str);
    }

    public static void c(List<String> list) {
        jfa.c(f305a, list);
    }

    public static void e(String str, String str2) {
        jfa.a(f305a, str, str2);
    }

    public static String e(String str) {
        return jfa.h(f305a, str);
    }

    public static void c(String str, String str2) {
        jfa.b(f305a, str, str2);
    }

    public static String a(String str) {
        return jfa.c(f305a, str, "");
    }

    public static void b(String str, String str2) {
        jfa.f(f305a, str, str2);
    }

    public static String g(String str) {
        return jfa.d(f305a, str, "");
    }

    public static void c(String str, long j) {
        jfa.b(f305a, str, j);
    }

    public static long d(String str) {
        return jfa.d(f305a, str, 0L);
    }

    public static void c(String str, boolean z) {
        jfa.a(f305a, str, z);
    }

    public static boolean b(String str, boolean z) {
        return jfa.d(f305a, str, z);
    }

    public static int d() {
        LogUtil.a("HealthLife_HealthLifeCacheUtils", "getJoinTime sJoinTime ", Integer.valueOf(d));
        if (d <= 0) {
            d = CommonUtil.h(e("health_model_join_time"));
        }
        return d;
    }

    public static void e(int i, boolean z) {
        LogUtil.a("HealthLife_HealthLifeCacheUtils", "saveJoinTime sJoinTime ", Integer.valueOf(d), " joinTime ", Integer.valueOf(i), " isFromCloud ", Boolean.valueOf(z));
        if (z) {
            d = i;
        } else if (d > 0) {
            return;
        } else {
            d = i;
        }
        int i2 = d;
        e("health_model_join_time", i2 <= 0 ? "" : String.valueOf(i2));
    }

    public static int c() {
        LogUtil.a("HealthLife_HealthLifeCacheUtils", "getUpgradeTime sUpgradeTime ", Integer.valueOf(c));
        if (c <= 0) {
            c = CommonUtil.h(e("health_model_upgrade_time"));
        }
        return c;
    }

    public static void a(int i, boolean z) {
        LogUtil.a("HealthLife_HealthLifeCacheUtils", "saveUpgradeTime sUpgradeTime ", Integer.valueOf(c), " upgradeTime ", Integer.valueOf(i), " isFromCloud ", Boolean.valueOf(z));
        if (z) {
            c = i;
        } else if (c > 0) {
            return;
        } else {
            c = i;
        }
        e("health_model_upgrade_time", c <= 0 ? "" : String.valueOf(i));
    }

    public static void a() {
        ArrayList arrayList = new ArrayList(15);
        arrayList.add("health_model_join_time");
        arrayList.add("health_model_upgrade_time");
        arrayList.add("health_model_challenge_id");
        arrayList.add("health_model_first_join_challenge");
        arrayList.add("health_model_twice_same_challenge");
        arrayList.add("health_model_challenge_join");
        arrayList.add("health_model_current_challenge_join_time");
        arrayList.add("health_model_data_cache");
        arrayList.add("health_life_data_cache_last");
        arrayList.add("health_model_week_task_records_cache");
        arrayList.add("health_life_last_sync_date");
        arrayList.add("flush_data_time");
        arrayList.add("health_life_last_version");
        arrayList.add("health_life_last_sync_time_millis");
        arrayList.add("update_cloud_data_time");
        c(arrayList);
        ReleaseLogUtil.b("R_HealthLife_HealthLifeCacheUtils", "reset sJoinTime ", Integer.valueOf(d), " sUpgradeTime ", Integer.valueOf(c), " keyList ", arrayList);
        d = 0;
        c = 0;
    }
}
