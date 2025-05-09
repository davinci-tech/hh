package defpackage;

import android.content.Intent;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.plan.model.receiver.AlarmReceiver;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class etl {
    private static int d(int i, int i2) {
        return (i * 10) + i2;
    }

    public static boolean d() {
        return e() != -1;
    }

    public static int e() {
        return gic.a(ash.b("remindTime"), -1);
    }

    public static void c(int i) {
        ash.a("remindTime", String.valueOf(i));
    }

    public static boolean c() {
        return b() != -1;
    }

    public static int b() {
        return gic.a(ash.b("intRemindTime"), -1);
    }

    public static void d(int i) {
        ash.a("intRemindTime", String.valueOf(i));
    }

    public static void arY_(Date date, int i, Intent intent) {
        LogUtil.a("Suggestion_DataImplRemindHelper", " registerRemind remindDate: " + date + "; requestCode:" + i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.getTime().after(new Date())) {
            sqa.eky_(i, intent, 201326592, 0, calendar.getTimeInMillis());
        }
    }

    public static void e(Plan plan) {
        b(b(plan));
    }

    public static void b(epo epoVar) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (epoVar == null || accountInfo == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", " updatePlanRemind intPlan or userId is null");
            return;
        }
        if (epoVar.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) {
            a(epoVar);
            return;
        }
        if (epoVar.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            e(epoVar);
        } else if (epoVar.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || epoVar.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            d(epoVar);
        } else {
            LogUtil.h("Suggestion_DataImplRemindHelper", "cancelTodayPlanRemind planType error.");
        }
    }

    public static void e(Plan plan, Boolean bool, int i) {
        e(b(plan), bool, i);
    }

    public static void a(epo epoVar, Boolean bool, long j) {
        e(epoVar, bool, (int) j);
    }

    public static void e(epo epoVar, Boolean bool, int i) {
        int e;
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (epoVar == null || accountInfo == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", " updatePlanRemind intPlan or userId is null");
            return;
        }
        if (bool == null || bool.booleanValue()) {
            bool = Boolean.valueOf(i >= 0);
        } else {
            i = -1;
        }
        LogUtil.a("Suggestion_DataImplRemindHelper", "isOpenRemind :" + bool + "remindTime :" + i);
        if (epoVar.getPlanType() == IntPlan.PlanType.RUN_PLAN || epoVar.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            e = e();
        } else {
            e = b();
        }
        if (i == e) {
            LogUtil.a("Suggestion_DataImplRemindHelper", "remindTime not change.");
            return;
        }
        if (epoVar.getPlanType() == IntPlan.PlanType.RUN_PLAN || epoVar.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            c(i);
        } else {
            d(i);
        }
        if (IntPlan.PlanType.RUN_PLAN.equals(epoVar.getPlanType())) {
            a(epoVar, bool, i, accountInfo);
            return;
        }
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(epoVar.getPlanType())) {
            d(epoVar, bool, i, accountInfo);
        } else if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(epoVar.getPlanType()) || IntPlan.PlanType.FIT_PLAN.equals(epoVar.getPlanType())) {
            e(epoVar, bool, i, accountInfo);
        } else {
            LogUtil.h("Suggestion_DataImplRemindHelper", "updatePlanRemind planType error.");
        }
    }

    private static void a(epo epoVar, Boolean bool, int i, String str) {
        Plan compatiblePlan = epoVar.getCompatiblePlan();
        if (compatiblePlan == null || compatiblePlan.acquireWorkouts() == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "updateRunAndFitnessRemind runPlan or mWorkouts is null");
            return;
        }
        List<PlanWorkout> e = e(compatiblePlan.acquireWorkouts());
        for (int i2 = 0; i2 < e.size(); i2++) {
            PlanWorkout planWorkout = e.get(i2);
            if (planWorkout != null) {
                Intent intent = new Intent(arx.b(), (Class<?>) AlarmReceiver.class);
                if (bool.booleanValue()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(ggl.e(planWorkout.popDayInfo().acquireDate(), "yyyy-MM-dd"));
                    calendar.set(11, moe.a(i));
                    calendar.set(12, moe.b(i));
                    intent.putExtra(JsbMapKeyNames.H5_USER_ID, str);
                    intent.putExtra("planType", compatiblePlan.acquireType());
                    arY_(calendar.getTime(), (epoVar.getPlanType().getType() * 100) + i2, intent);
                } else {
                    sqa.ekn_(i2, intent, 201326592);
                }
            }
        }
    }

    private static void a(epo epoVar) {
        Plan compatiblePlan = epoVar.getCompatiblePlan();
        if (compatiblePlan == null || compatiblePlan.acquireWorkouts() == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "cancelTodayRunAndFitnessRemind or mWorkouts is null");
            return;
        }
        List<PlanWorkout> e = e(compatiblePlan.acquireWorkouts());
        for (int i = 0; i < e.size(); i++) {
            PlanWorkout planWorkout = e.get(i);
            if (planWorkout != null && c(ggl.e(planWorkout.popDayInfo().acquireDate(), "yyyy-MM-dd"), new Date())) {
                Intent intent = new Intent(arx.b(), (Class<?>) AlarmReceiver.class);
                int type = epoVar.getPlanType().getType();
                LogUtil.a("Suggestion_DataImplRemindHelper", "cancel today runAndFitness planRemind");
                sqa.ekn_((type * 100) + i, intent, 201326592);
            }
        }
    }

    private static void d(epo epoVar, Boolean bool, int i, String str) {
        Plan compatiblePlan = epoVar.getCompatiblePlan();
        if (compatiblePlan == null || compatiblePlan.getPlanWeekDataList() == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "updateAiRunPlanRemind aiRunPlan or planWeekDataList is null");
            return;
        }
        List<moa> planWeekDataList = compatiblePlan.getPlanWeekDataList();
        Calendar calendar = Calendar.getInstance();
        if (koq.b(planWeekDataList)) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "updateAiRunPlanRemind skipped by null weekData");
            return;
        }
        int e = ase.e(compatiblePlan);
        for (moa moaVar : planWeekDataList) {
            if (moaVar.f() == e) {
                if (!koq.b(moaVar.j())) {
                    for (mnu mnuVar : moaVar.j()) {
                        int type = (epoVar.getPlanType().getType() * 100) + d(moaVar.f(), mnuVar.c());
                        Intent intent = new Intent(arx.b(), (Class<?>) AlarmReceiver.class);
                        if (!bool.booleanValue()) {
                            sqa.ekn_(type, intent, 201326592);
                            LogUtil.a("Suggestion_DataImplRemindHelper", "updateAiRunPlanRemind cancelRemind: plan ", epoVar.getPlanId());
                        } else {
                            long a2 = ase.a(compatiblePlan, moaVar.f(), mnuVar.c());
                            calendar.setTimeInMillis(a2);
                            calendar.set(11, moe.a(i));
                            calendar.set(12, moe.b(i));
                            intent.putExtra(JsbMapKeyNames.H5_USER_ID, str);
                            arY_(calendar.getTime(), type, intent);
                            LogUtil.a("Suggestion_DataImplRemindHelper", "updateAiRunPlanRemind registerRemind: plan ", epoVar.getPlanId(), ", curTime=", Long.valueOf(a2), ", calTime=", Long.valueOf(calendar.getTimeInMillis()));
                        }
                    }
                }
            }
        }
    }

    private static void e(epo epoVar) {
        Plan compatiblePlan = epoVar.getCompatiblePlan();
        if (compatiblePlan == null || compatiblePlan.getPlanWeekDataList() == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "cancelTodayAiRunPlanRemind aiRunPlan or planWeekDataList is null");
            return;
        }
        List<moa> planWeekDataList = compatiblePlan.getPlanWeekDataList();
        if (koq.c(planWeekDataList)) {
            int e = ase.e(compatiblePlan);
            int d = gib.d(Calendar.getInstance().get(7));
            for (moa moaVar : planWeekDataList) {
                if (moaVar.f() == e && koq.c(moaVar.j())) {
                    Iterator<mnu> it = moaVar.j().iterator();
                    while (it.hasNext()) {
                        if (it.next().c() == d) {
                            int type = epoVar.getPlanType().getType();
                            int d2 = d(e, d);
                            Intent intent = new Intent(arx.b(), (Class<?>) AlarmReceiver.class);
                            LogUtil.a("Suggestion_DataImplRemindHelper", "cancel today aiRunPlan planRemind");
                            sqa.ekn_((type * 100) + d2, intent, 201326592);
                        }
                    }
                }
            }
        }
    }

    private static void e(epo epoVar, Boolean bool, int i, String str) {
        if (epoVar == null || epoVar.d() == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "updateIntPlanRemind intPlan weekInfoList is null");
            return;
        }
        long beginDate = epoVar.getPlanTimeInfo().getBeginDate();
        int g = ase.g(epoVar);
        for (epp eppVar : epoVar.d()) {
            int weekOrder = eppVar.getWeekOrder();
            if (!IntPlan.PlanType.AI_FITNESS_PLAN.equals(epoVar.getPlanType()) || weekOrder == g) {
                if (koq.c(eppVar.b())) {
                    Iterator<epl> it = eppVar.b().iterator();
                    while (it.hasNext()) {
                        int dayOrder = it.next().getDayOrder();
                        int type = (epoVar.getPlanType().getType() * 100) + d(weekOrder, dayOrder);
                        Intent intent = new Intent(arx.b(), (Class<?>) AlarmReceiver.class);
                        if (bool.booleanValue()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(gib.a(1000 * beginDate, weekOrder - 1, dayOrder));
                            calendar.set(11, moe.a(i));
                            calendar.set(12, moe.b(i));
                            LogUtil.a("Suggestion_DataImplRemindHelper", " updateIntPlanRemind registerRemind remindDate: " + calendar.getTime() + "; requestCode:" + type);
                            intent.putExtra(JsbMapKeyNames.H5_USER_ID, str);
                            arY_(calendar.getTime(), type, intent);
                        } else {
                            LogUtil.a("Suggestion_DataImplRemindHelper", " updateIntPlanRemind cancelRemind requestCode:" + type);
                            sqa.ekn_(type, intent, 201326592);
                        }
                    }
                }
            }
        }
    }

    private static void d(epo epoVar) {
        if (epoVar == null || epoVar.d() == null) {
            LogUtil.h("Suggestion_DataImplRemindHelper", "cancelTodayIntPlanRemind intPlan weekInfoList is null");
            return;
        }
        int g = ase.g(epoVar);
        int d = gib.d(Calendar.getInstance().get(7));
        if (eve.a(epoVar, g, d) != null) {
            Intent intent = new Intent(arx.b(), (Class<?>) AlarmReceiver.class);
            int type = epoVar.getPlanType().getType();
            int d2 = d(g, d);
            LogUtil.a("Suggestion_DataImplRemindHelper", "cancel today intPlan planRemind");
            sqa.ekn_((type * 100) + d2, intent, 201326592);
        }
    }

    public static epo b(Plan plan) {
        IntPlan.PlanType planType;
        if (plan == null) {
            return null;
        }
        int acquireType = plan.acquireType();
        if (acquireType != 0) {
            if (acquireType == 3) {
                planType = IntPlan.PlanType.FIT_PLAN;
            }
            planType = null;
        } else if (plan.getPlanCategory() == 1) {
            planType = IntPlan.PlanType.AI_RUN_PLAN;
        } else if (plan.getPlanCategory() == 0) {
            planType = IntPlan.PlanType.RUN_PLAN;
        } else {
            LogUtil.h("Suggestion_DataImplRemindHelper", " run planCategory error");
            planType = null;
        }
        if (planType == null) {
            return null;
        }
        epo a2 = epo.a();
        a2.b(plan);
        epq epqVar = new epq();
        epqVar.a(planType);
        a2.d(epqVar);
        return a2;
    }

    private static List<PlanWorkout> e(List<PlanWorkout> list) {
        ArrayList arrayList = new ArrayList(10);
        if (list != null) {
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    str = list.get(i).popDayInfo().acquireDate();
                    arrayList.add(list.get(i));
                } else {
                    String acquireDate = list.get(i).popDayInfo().acquireDate();
                    if (!acquireDate.equals(str)) {
                        arrayList.add(list.get(i));
                    }
                    str = acquireDate;
                }
            }
        }
        return arrayList;
    }

    private static boolean c(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }
}
