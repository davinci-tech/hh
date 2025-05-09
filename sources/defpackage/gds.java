package defpackage;

import android.content.Context;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class gds {
    public static boolean c(FitWorkout fitWorkout) {
        if (fitWorkout != null) {
            return fitWorkout.isRunModelCourse();
        }
        return false;
    }

    public static void e(Context context, FitWorkout fitWorkout, IntPlan intPlan, long j, int i) {
        if (context == null || fitWorkout == null || intPlan == null) {
            LogUtil.h("Suggestion_WorkoutUtil", "context == null || fitWorkout == null || currentRunPlan == null");
            return;
        }
        int c = c(intPlan, j);
        if (intPlan.getCompatiblePlan() != null) {
            ggs.e(context, fitWorkout, intPlan.getCompatiblePlan(), j, i);
        } else {
            c(context, fitWorkout, c(intPlan.getPlanId(), j, i, c));
        }
    }

    public static Map<String, Object> c(String str, long j, int i, int i2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("planId", str);
        hashMap.put("plan_execute_time", Long.valueOf(j));
        hashMap.put("order", Integer.valueOf(i));
        hashMap.put("weekNum", Integer.valueOf(i2));
        return hashMap;
    }

    public static void d(Context context, FitWorkout fitWorkout, IntPlan intPlan, long j) {
        e(context, fitWorkout, intPlan, j, 1);
    }

    public static void c(Context context, FitWorkout fitWorkout, Map<String, Object> map) {
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        if (map != null) {
            Object obj = map.get("planId");
            if (obj instanceof String) {
                mmpVar.h((String) obj);
            }
            mmpVar.h((String) map.get("planId"));
            Object obj2 = map.get("order");
            if (obj2 instanceof Integer) {
                mmpVar.i(((Integer) obj2).intValue());
            }
            Object obj3 = map.get("plan_execute_time");
            if (obj3 instanceof Long) {
                mmpVar.e(((Long) obj3).longValue());
            }
            Object obj4 = map.get("weekNum");
            if (obj4 instanceof Integer) {
                mmpVar.f(((Integer) obj4).intValue());
            }
        }
        mod.b(context, fitWorkout, mmpVar);
    }

    public static void b(Context context, FitWorkout fitWorkout, IntPlan intPlan, long j) {
        if (context == null || fitWorkout == null || intPlan == null) {
            LogUtil.h("Suggestion_WorkoutUtil", "context == null || fitWorkout == null || currentRunPlan == null");
        } else {
            d(context, fitWorkout, intPlan, 1, j);
        }
    }

    public static void d(Context context, FitWorkout fitWorkout, IntPlan intPlan, int i, long j) {
        if (context == null || fitWorkout == null || intPlan == null) {
            LogUtil.h("Suggestion_WorkoutUtil", "context == null || fitWorkout == null || currentRunPlan == null");
        } else {
            ggs.e(context, fitWorkout, intPlan.getPlanId(), c(intPlan, j), i, j);
        }
    }

    private static int c(IntPlan intPlan, long j) {
        if (IntPlan.PlanType.FIT_PLAN.equals(intPlan.getPlanType())) {
            return ggs.a(TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getBeginDate()), j);
        }
        return fyw.i(intPlan);
    }

    public static List<FitWorkout> b(List<FitWorkout> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("Suggestion_WorkoutUtil", "removeDupWorkouts() workouts is null");
            return arrayList;
        }
        for (FitWorkout fitWorkout : list) {
            if (!arrayList.contains(fitWorkout)) {
                arrayList.add(fitWorkout);
            }
        }
        return arrayList;
    }
}
