package defpackage;

import com.huawei.basefitnessadvice.model.DayInfo;
import com.huawei.basefitnessadvice.model.FitnessDayPlan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.WeekInfo;
import com.huawei.health.R;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.model.RestDescConstructor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes8.dex */
public class evb {
    public static void d(List<ffi> list, int i) {
        Calendar calendar = Calendar.getInstance();
        RestDescConstructor restDescConstructor = new RestDescConstructor();
        restDescConstructor.init(BaseApplication.getContext());
        if (list == null) {
            LogUtil.h("Suggestion_ShowPlanAnotherHelper", "weekWorkouts == null");
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ffi ffiVar = list.get(i2);
            for (int i3 = 0; i3 < 7; i3++) {
                if (ffiVar.d().get(i3) == null) {
                    ffiVar.d().set(i3, a(i, (i2 * 7) + i3, ffiVar.a(), calendar, restDescConstructor));
                }
            }
        }
    }

    public static void a(List<ffi> list, int i) {
        if (list == null) {
            LogUtil.h("Suggestion_ShowPlanAnotherHelper", "weekWorkouts == null");
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ffi ffiVar = list.get(i2);
            for (int i3 = 0; i3 < 7; i3++) {
                if (ffiVar.d().get(i3) == null) {
                    ffiVar.d().set(i3, d(i, (i2 * 7) + i3, ffiVar.a()));
                }
            }
        }
    }

    private static fex a(int i, int i2, WeekInfo weekInfo, Calendar calendar, RestDescConstructor restDescConstructor) {
        PlanWorkout planWorkout = new PlanWorkout();
        planWorkout.putWeekInfo(weekInfo);
        DayInfo a2 = a(i, i2, calendar);
        planWorkout.putName(arx.b().getString(R.string._2130839507_res_0x7f0207d3));
        planWorkout.putDescription(restDescConstructor.constructorDesc());
        planWorkout.putWorkoutId(null);
        planWorkout.putDayInfo(a2);
        fex fexVar = new fex(i + i2, planWorkout.popDayInfo());
        fexVar.c(true);
        fexVar.e().add(planWorkout);
        return fexVar;
    }

    private static fex d(int i, int i2, WeekInfo weekInfo) {
        PlanWorkout planWorkout = new PlanWorkout();
        planWorkout.putWeekInfo(weekInfo);
        planWorkout.putDayInfo(d(i, i2));
        fex fexVar = new fex(i + i2, planWorkout.popDayInfo());
        fexVar.c(true);
        fexVar.e().add(planWorkout);
        return fexVar;
    }

    private static DayInfo d(int i, int i2) {
        DayInfo dayInfo = new DayInfo();
        dayInfo.saveOrder((i2 % 7) + 1);
        dayInfo.saveSinglesCount(1);
        UserFitnessPlanInfo i3 = euc.e().i();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.add(5, i + i2);
        if (i3 == null) {
            LogUtil.h("Suggestion_ShowPlanAnotherHelper", "info == null");
            dayInfo.saveDate(ggl.a(calendar.getTime(), "yyyy-MM-dd"));
            return dayInfo;
        }
        for (int i4 = 0; i4 < i3.acquireWeekPlanList().size(); i4++) {
            for (FitnessDayPlan fitnessDayPlan : i3.acquireWeekPlanList().get(i4).acquireWeekList()) {
                if (fitnessDayPlan != null) {
                    long b = gib.b(fitnessDayPlan.acquireDate());
                    if (b == gib.b(calendar.getTimeInMillis())) {
                        LogUtil.c("Suggestion_ShowPlanAnotherHelper", Long.valueOf(b));
                        dayInfo.saveDayDesc(fitnessDayPlan.acquireDescription());
                        dayInfo.saveDayTitle(arx.b().getString(R.string._2130839507_res_0x7f0207d3));
                    }
                }
            }
        }
        dayInfo.saveDate(ggl.a(calendar.getTime(), "yyyy-MM-dd"));
        return dayInfo;
    }

    private static DayInfo a(int i, int i2, Calendar calendar) {
        DayInfo dayInfo = new DayInfo();
        dayInfo.saveOrder((i2 % 7) + 1);
        dayInfo.saveSinglesCount(1);
        calendar.clear();
        calendar.add(5, i + i2);
        dayInfo.saveDate(ggl.a(calendar.getTime(), "yyyy-MM-dd"));
        return dayInfo;
    }
}
