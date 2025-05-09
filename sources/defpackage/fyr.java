package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.awarenessdonate.AwarenessDonateApi;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bzn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SystemProperties;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

/* loaded from: classes.dex */
public class fyr {
    public static List<bzn.e> b(long j) {
        LogUtil.a("FaFitnessCardUtil", "getNearTimePeriod, sportStartTime is ", Long.valueOf(j));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j - 1800000);
        calendar.set(13, 0);
        calendar.set(14, 0);
        ArrayList arrayList = new ArrayList(7);
        for (int i = 0; i < 6; i++) {
            calendar.add(6, 1);
            long timeInMillis = calendar.getTimeInMillis();
            arrayList.add(new bzn.e(timeInMillis, 3600000 + timeInMillis));
        }
        return arrayList;
    }

    public static List<bzn.e> a(long j) {
        LogUtil.a("FaFitnessCardUtil", "getLastHourTimePeriod, remindTime is ", Long.valueOf(j));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new bzn.e(j - 3600000, j));
        return arrayList;
    }

    public static void d(Map<String, String> map) {
        if (map == null || !map.containsKey("sport_remind_switch_status")) {
            LogUtil.a("FaFitnessCardUtil", "donateBySportRemind remindData is null or not contain sportRemind switchStatus");
            return;
        }
        String str = map.get("sport_remind_switch_status");
        if (TextUtils.isEmpty(str) || "0".equals(str)) {
            LogUtil.h("FaFitnessCardUtil", "donateBySportRemind sportRemind switchStatus is ", str);
            return;
        }
        if (map.containsKey("sport_remind_period") && map.containsKey("sport_remind_time")) {
            String str2 = map.get("sport_remind_period");
            String str3 = map.get("sport_remind_time");
            LogUtil.a("FaFitnessCardUtil", "donateBySportRemind remindPeriod is ", str2, ", remindTime is ", str3);
            c(str2, CommonUtil.m(BaseApplication.e(), str3));
        }
    }

    private static void c(String str, int i) {
        if (TextUtils.isEmpty(str) || i <= 0) {
            LogUtil.a("FaFitnessCardUtil", "donateBySportRemind remindPeriod is empty or remindTime is empty");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int i2 = i / 60;
        calendar.set(11, moe.a(i2));
        calendar.set(12, moe.b(i2));
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.get(7);
        ArrayList arrayList = new ArrayList(7);
        for (int i3 = 0; i3 <= 6; i3++) {
            if (str.contains(String.valueOf(gib.d(calendar.get(7))))) {
                long timeInMillis = calendar.getTimeInMillis();
                if (System.currentTimeMillis() < timeInMillis) {
                    arrayList.addAll(a(timeInMillis));
                }
            }
            calendar.add(6, 1);
        }
        if (koq.c(arrayList)) {
            d(1, arrayList);
        }
    }

    public static void d(int i, List<bzn.e> list) {
        if (koq.b(list)) {
            LogUtil.h("FaFitnessCardUtil", "donateFitnessCard, predictInfo is empty");
            return;
        }
        AwarenessDonateApi awarenessDonateApi = (AwarenessDonateApi) Services.a("HWSmartInteractMgr", AwarenessDonateApi.class);
        if (awarenessDonateApi == null || !awarenessDonateApi.isSupportDonate()) {
            LogUtil.h("FaFitnessCardUtil", "donateFitnessCard, awarenessDonateApi is null or not support donate");
        } else {
            awarenessDonateApi.donateFeature(new bzn.b().f("featurePrediction").g(UUID.randomUUID().toString()).a(Build.MODEL).c(SystemProperties.b("ro.build.characteristics")).b(Build.PRODUCT).a(i).l(CommonUtil.am()).a(System.currentTimeMillis()).b(list.get(0).c()).c(list.get(list.size() - 1).e()).e("com.huawei.ohos.healthservice").o(FaCardAgdsApi.SPORT_FA_CARD).d("com.huawei.ohos.healthservice.sport.FitnessCoursesFormAbility").h("fitness_courses_medium").i("2*2").j(i == 1 ? "fitnessSchedule" : "fitnessTutorial").b(list).d());
        }
    }

    public static void d() {
        AwarenessDonateApi awarenessDonateApi = (AwarenessDonateApi) Services.a("HWSmartInteractMgr", AwarenessDonateApi.class);
        if (awarenessDonateApi == null || !awarenessDonateApi.isSupportDonate()) {
            LogUtil.h("FaFitnessCardUtil", "awarenessDonateApi is null or not support donate");
            return;
        }
        String uuid = UUID.randomUUID().toString();
        long currentTimeMillis = System.currentTimeMillis();
        awarenessDonateApi.deleteDonateFeature(new bzn.b().f("featurePrediction").g(uuid).a(Build.MODEL).c(SystemProperties.b("ro.build.characteristics")).b(Build.PRODUCT).a(0).l(TimeZone.getDefault().getDisplayName(false, 0)).a(currentTimeMillis).c(currentTimeMillis).b(currentTimeMillis).j("fitnessSchedule").b(new ArrayList()).d());
    }
}
