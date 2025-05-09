package defpackage;

import android.content.Context;
import android.util.ArrayMap;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.KeyValDbManager;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class ivo {
    private static Context b;

    static class a {
        private static final ivo c = new ivo();
    }

    public static ivo b(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
        }
        return a.c;
    }

    private ivo() {
    }

    public void a(String str) {
        if (d(b)) {
            LogUtil.c("Debug_DataPlatFormBIAnalytics", "hiLogin biSetUserInfo");
            ixx.d().a(str);
        }
    }

    public void d(String str) {
        if (d(b)) {
            String b2 = iwe.b(b);
            if (b2.equals(str)) {
                return;
            }
            LogUtil.c("Debug_DataPlatFormBIAnalytics", "hiLogin biAnalytics");
            ArrayMap arrayMap = new ArrayMap(1);
            arrayMap.put("newID", str);
            arrayMap.put("oldID", b2);
            ixx.d().d(b, AnalyticsValue.HEALTH_DATA_PLATFORM_ACCOUNTSWITCH_2150004.value(), arrayMap, 0);
            ixx.d().c(b);
            iwe.e(b, str);
        }
    }

    public void d(long j, int i) {
        if (d(b)) {
            LogUtil.c("Debug_DataPlatFormBIAnalytics", "sync biFirstSyncContinueTime is ", String.valueOf(j));
            ArrayMap arrayMap = new ArrayMap(4);
            arrayMap.put("during", Long.valueOf(j));
            ivz.d(b).e(b, AnalyticsValue.HEALTH_DATA_PLATFORM_FIRSTSYNC_TIME_2150002.value(), arrayMap, 0);
            ixx.d().c(b);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(5);
            linkedHashMap.put("during", String.valueOf(j));
            linkedHashMap.put("firstSyncFailCount", String.valueOf(iuz.e(i)));
            ivz.d(b).e(OperationKey.HEALTH_DATA_PLATFORM_FIRST_SYNC_TIME.value(), linkedHashMap, false);
        }
    }

    public void b(int i) {
        if (d(b)) {
            LogUtil.c("Debug_DataPlatFormBIAnalytics", "sync biAccountDevicesNum is ", String.valueOf(i));
            ArrayMap arrayMap = new ArrayMap(4);
            arrayMap.put("phoneDeviceNum", Integer.valueOf(i));
            ivz.d(b).e(b, AnalyticsValue.HEALTH_DATA_PLATFORM_DEVICENUM_2150003.value(), arrayMap, 0);
            ixx.d().c(b);
        }
    }

    public void b() {
        if (d(b)) {
            LogUtil.c("Debug_DataPlatFormBIAnalytics", "sync biOldDaySyncStatData");
            ArrayMap arrayMap = new ArrayMap(1);
            int m = iwe.m(b);
            int o = iwe.o(b);
            int r = iwe.r(b);
            int p = iwe.p(b);
            int k = iwe.k(b);
            int n = iwe.n(b);
            int l = iwe.l(b);
            int t = iwe.t(b);
            arrayMap.put("date", Integer.valueOf(m));
            arrayMap.put("totalSync", Integer.valueOf(o));
            arrayMap.put("UISync", Integer.valueOf(r));
            arrayMap.put("userinfoSync", Integer.valueOf(p));
            arrayMap.put("insertSyncDetail", Integer.valueOf(k));
            arrayMap.put("insertSyncStat", Integer.valueOf(n));
            arrayMap.put("syncFail", Integer.valueOf(l));
            arrayMap.put("wearSync", Integer.valueOf(t));
            ixx.d().d(b, AnalyticsValue.HEALTH_DATA_PLATFORM_OLDDAYSYNCSTATDATA_2150001.value(), arrayMap, 0);
            ixx.d().c(b);
        }
    }

    public void c() {
        LogUtil.c("Debug_DataPlatFormBIAnalytics", "sync setBiTodaySyncDataZero");
        iwe.d(b, 20080808);
        iwe.h(b, 0);
        iwe.g(b, 0);
        iwe.i(b, 0);
        iwe.e(b, 0);
        iwe.b(b, 0);
        iwe.j(b, 0);
        iwe.f(b, 0);
    }

    public void d() {
        LogUtil.c("Debug_DataPlatFormBIAnalytics", "sync saveTodaySyncData2Yesteday");
        int a2 = iwe.a(b);
        int g = iwe.g(b);
        int j = iwe.j(b);
        int f = iwe.f(b);
        int d = iwe.d(b);
        int e = iwe.e(b);
        int h = iwe.h(b);
        int i = iwe.i(b);
        iwe.l(b, a2);
        iwe.o(b, g);
        iwe.s(b, j);
        iwe.p(b, f);
        iwe.n(b, d);
        iwe.m(b, e);
        iwe.k(b, h);
        iwe.t(b, i);
    }

    private static boolean d(Context context) {
        LogUtil.c("Debug_DataPlatFormBIAnalytics", "report_switch statusIsChecked() enter ");
        String e = KeyValDbManager.b(context).e("key_user_experience_plan_check_box");
        LogUtil.c("Debug_DataPlatFormBIAnalytics", "report_switch status = ", e);
        return "true".equals(e);
    }
}
