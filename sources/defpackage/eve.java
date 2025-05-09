package defpackage;

import com.huawei.basefitnessadvice.model.FitnessDayPlan;
import com.huawei.basefitnessadvice.model.FitnessPlanCourse;
import com.huawei.basefitnessadvice.model.FitnessWeekPlan;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.intplan.StatInfoBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class eve {
    public static final boolean b = !Utils.l();

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f12358a = !Utils.l();
    private static final String[] e = {"calorie", "duration", StatInfo.STAT_TYPE_PUNCH_DAY};
    private static final String[] c = {"distance", "duration", "calorie"};

    public static epo a(UserFitnessPlanInfo userFitnessPlanInfo) {
        epo a2 = epo.a();
        epq epqVar = new epq();
        a2.a(ggl.b());
        a2.d(epqVar);
        epqVar.h(userFitnessPlanInfo.acquirePlanId());
        epqVar.a(IntPlan.PlanType.FIT_PLAN);
        epqVar.d(userFitnessPlanInfo.acquireDescription());
        epqVar.f(userFitnessPlanInfo.acquirePicture());
        epqVar.a(userFitnessPlanInfo.acquireCardPicture());
        epqVar.i(userFitnessPlanInfo.acquirePlanTempId());
        epr eprVar = new epr();
        long currentTimeMillis = System.currentTimeMillis();
        long b2 = gib.b(currentTimeMillis) / 1000;
        long j = currentTimeMillis / 1000;
        eprVar.d(j);
        a2.d(eprVar);
        eprVar.c(b2);
        eprVar.a(j);
        epqVar.e(userFitnessPlanInfo.acquireName());
        eprVar.b(userFitnessPlanInfo.acquireRemindTime());
        a(a2, userFitnessPlanInfo);
        eprVar.e(((a2.c() - 1) * k.b.m) + b2);
        a2.e(new epw("progress", Float.valueOf(0.0f)));
        a2.e(userFitnessPlanInfo.acquireTransactionStatus());
        return a2;
    }

    public static epo c(UserFitnessPlanInfo userFitnessPlanInfo) {
        epo a2 = epo.a();
        a2.b(userFitnessPlanInfo);
        Plan a3 = etz.a(userFitnessPlanInfo);
        a2.b(a3);
        epq epqVar = new epq();
        epqVar.h(userFitnessPlanInfo.acquirePlanId());
        epqVar.a(IntPlan.PlanType.FIT_PLAN);
        epqVar.d(userFitnessPlanInfo.acquireDescription());
        epqVar.f(userFitnessPlanInfo.acquirePicture());
        epqVar.a(userFitnessPlanInfo.acquireCardPicture());
        epqVar.i(userFitnessPlanInfo.acquirePlanTempId());
        epqVar.c("");
        epr eprVar = new epr();
        eprVar.d(userFitnessPlanInfo.acquireCreateTime() / 1000);
        a2.a(userFitnessPlanInfo.acquireTimeZone());
        epqVar.d(userFitnessPlanInfo.acquirePlanCategory());
        if (userFitnessPlanInfo.acquireDisplayStyle() == 0) {
            String acquireStartDate = a3.acquireStartDate();
            String endDate = a3.getEndDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                eprVar.c(simpleDateFormat.parse(acquireStartDate).getTime() / 1000);
                eprVar.e(simpleDateFormat.parse(endDate).getTime() / 1000);
                LogUtil.h("PlanService_IntPlanUtil", "convertIntPlan startDay : ", acquireStartDate, " startTime:", Long.valueOf(eprVar.getBeginDate()), " endDay : ", endDate, " endTime:", Long.valueOf(eprVar.getEndDate()));
            } catch (ParseException unused) {
                LogUtil.b("PlanService_IntPlanUtil", "convertFitnessPlan parse error");
            }
        } else {
            eprVar.c(userFitnessPlanInfo.acquireBeginDate());
            eprVar.e(userFitnessPlanInfo.acquireEndDate());
        }
        eprVar.a(userFitnessPlanInfo.acquireModifyTime() / 1000);
        eprVar.b(userFitnessPlanInfo.acquireRemindTime());
        epqVar.e(userFitnessPlanInfo.acquireName());
        epqVar.b(userFitnessPlanInfo.acquireDisplayStyle());
        a2.d(epqVar);
        a2.d(eprVar);
        a2.a(userFitnessPlanInfo.acquireIntroduction());
        a(a2, userFitnessPlanInfo);
        a2.e(new epw("progress", Float.valueOf(e(a2))));
        b(userFitnessPlanInfo.acquireStatistics(), a2);
        a2.e(userFitnessPlanInfo.acquireTransactionStatus());
        return a2;
    }

    private static void a(epo epoVar, UserFitnessPlanInfo userFitnessPlanInfo) {
        List<FitnessDayPlan> acquireDailyPlanList;
        boolean z = userFitnessPlanInfo.acquireDisplayStyle() == 0;
        List<FitnessWeekPlan> acquireWeekPlanList = z ? userFitnessPlanInfo.acquireWeekPlanList() : userFitnessPlanInfo.acquireWeeklyPlanList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(epoVar.getPlanTimeInfo().getBeginDate() * 1000);
        int d = gib.d(calendar.get(7));
        FitnessWeekPlan fitnessWeekPlan = acquireWeekPlanList.get(0);
        epp eppVar = new epp();
        ArrayList arrayList = new ArrayList();
        eppVar.d(arrayList);
        eppVar.d(1);
        eppVar.e(fitnessWeekPlan.acquireWeekDesc());
        eppVar.c(fitnessWeekPlan.acquireWeekPeriod());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(eppVar);
        int i = 1;
        for (int i2 = 0; i2 < acquireWeekPlanList.size(); i2++) {
            FitnessWeekPlan fitnessWeekPlan2 = acquireWeekPlanList.get(i2);
            if (z) {
                acquireDailyPlanList = fitnessWeekPlan2.acquireWeekList();
            } else {
                acquireDailyPlanList = fitnessWeekPlan2.acquireDailyPlanList();
            }
            for (FitnessDayPlan fitnessDayPlan : acquireDailyPlanList) {
                arrayList.add(e(fitnessDayPlan, d, z));
                LogUtil.a("PlanService_IntPlanUtil", "dayIndex: ", Integer.valueOf(d), " week:", Integer.valueOf(i), "dayOrder: ", Integer.valueOf(fitnessDayPlan.acquireDayOrder()));
                if (d == 7) {
                    arrayList = new ArrayList();
                    eppVar = new epp();
                    eppVar.d(arrayList);
                    i++;
                    eppVar.d(i);
                    eppVar.e(fitnessWeekPlan2.acquireWeekDesc());
                    eppVar.c(fitnessWeekPlan2.acquireWeekPeriod());
                    arrayList2.add(eppVar);
                    d = 1;
                } else {
                    d++;
                }
            }
        }
        if (eppVar.getDayCount() == 0) {
            arrayList2.remove(eppVar);
            i--;
        }
        LogUtil.c("PlanService_IntPlanUtil", "convertWeekInfo weekOrder : ", Integer.valueOf(i));
        epoVar.d(arrayList2);
        a(epoVar);
    }

    private static epl e(FitnessDayPlan fitnessDayPlan, int i, boolean z) {
        List<FitnessPlanCourse> acquireDayPlanCourses = z ? fitnessDayPlan.acquireDayPlanCourses() : fitnessDayPlan.acquireWorkoutList();
        ArrayList arrayList = new ArrayList();
        epl eplVar = new epl();
        eplVar.b(i);
        b(acquireDayPlanCourses, arrayList, eplVar, z);
        eplVar.c(arrayList);
        if (!z) {
            eplVar.a(fitnessDayPlan.acquirePunchFlag());
            eplVar.c(fitnessDayPlan.acquireSuggestedReading());
        }
        LogUtil.c("PlanService_IntPlanUtil", "convertDayInfo day order: ", Integer.valueOf(i), " actions:", Integer.valueOf(arrayList.size()));
        return eplVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void b(List<FitnessPlanCourse> list, List<epm> list2, epl eplVar, boolean z) {
        int i;
        if (koq.b(list)) {
            LogUtil.h("PlanService_IntPlanUtil", "convertInPlanAction fitnessPlanCourseList is null");
            return;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            epm epmVar = new epm();
            FitnessPlanCourse fitnessPlanCourse = list.get(i3);
            epmVar.e(fitnessPlanCourse.acquireCourseId());
            if (z) {
                i = fitnessPlanCourse.acquireFinishStatus();
            } else {
                i = fitnessPlanCourse.acquirePunchFlag();
            }
            epmVar.e(i);
            epmVar.b(fitnessPlanCourse.acquireWorkoutTime());
            epmVar.e(IntAction.ActionType.WORKOUT);
            epmVar.a(fitnessPlanCourse.getActionInfo());
            list2.add(epmVar);
            LogUtil.c("PlanService_IntPlanUtil", "convertInPlanAction in day : ", Integer.valueOf(eplVar.getDayOrder()), " actionIdx:", Integer.valueOf(i3));
            if (fitnessPlanCourse.acquireFinishStatus()) {
                i2 += fitnessPlanCourse.acquireCalorie();
            }
        }
        if (z) {
            eplVar.a(1);
            int i4 = 0;
            while (true) {
                if (i4 >= list2.size()) {
                    break;
                }
                if (list2.get(i4).getPunchFlag() != 1) {
                    eplVar.a(0);
                    break;
                }
                i4++;
            }
        }
        eplVar.a(new epw("calorie", Integer.valueOf(i2)));
    }

    public static epo a(Plan plan) {
        epo a2 = epo.a();
        if (plan == null) {
            LogUtil.h("PlanService_IntPlanUtil", "convertRunPlan data is null.");
            return a2;
        }
        a2.b(plan);
        epq d = d(plan);
        epr eprVar = new epr();
        eprVar.d(plan.getStartTime());
        a2.a(plan.getTimeZone());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String acquireStartDate = plan.acquireStartDate();
        String endDate = plan.getEndDate();
        try {
            eprVar.c(simpleDateFormat.parse(acquireStartDate).getTime() / 1000);
            eprVar.e(simpleDateFormat.parse(endDate).getTime() / 1000);
            LogUtil.h("PlanService_IntPlanUtil", "convertIntPlan startDay : ", acquireStartDate, " startTime:", Long.valueOf(eprVar.getBeginDate()), " endDay : ", endDate, "  endTime:", Long.valueOf(eprVar.getEndDate()), " weekcount:" + plan.getWeekCount());
        } catch (ParseException unused) {
            LogUtil.b("PlanService_IntPlanUtil", "convertRunPlan parse error");
        }
        eprVar.a(plan.getModified() / 1000);
        eprVar.b(plan.getRemindTime());
        a2.e(plan.getTransactionStatus());
        a2.d(eprVar);
        a2.d(d);
        a2.a(plan.acquireIntroduction());
        List<moa> planWeekDataList = plan.getPlanWeekDataList();
        if (planWeekDataList.size() == 0) {
            ArrayList arrayList = new ArrayList();
            a(a2, plan.acquireWorkouts(), arrayList, plan.getDays());
            a2.d(arrayList);
            a2.c.i(arrayList.size());
        } else {
            ArrayList arrayList2 = new ArrayList();
            c(a2, planWeekDataList, arrayList2);
            a2.d(arrayList2);
            a2.c.i(arrayList2.size());
        }
        a2.e(new epw("progress", Float.valueOf(e(a2))));
        return a2;
    }

    private static epq d(Plan plan) {
        epq epqVar = new epq();
        if (plan == null) {
            LogUtil.h("PlanService_IntPlanUtil", "setMetaInfo data is null.");
            return epqVar;
        }
        epqVar.h(plan.acquireId());
        epqVar.a(IntPlan.PlanType.AI_RUN_PLAN);
        epqVar.d(plan.getDescription());
        epqVar.f(plan.getPicture());
        epqVar.i(Integer.toString(plan.acquireTempId()));
        epqVar.e(plan.acquireName());
        epqVar.e(plan.getDays());
        return epqVar;
    }

    private static void c(epo epoVar, List<moa> list, List<epp> list2) {
        epp eppVar = new epp();
        ArrayList arrayList = new ArrayList();
        eppVar.d(arrayList);
        int i = 1;
        eppVar.d(1);
        list2.add(eppVar);
        for (int i2 = 0; i2 < list.size(); i2++) {
            moa moaVar = list.get(i2);
            eppVar.e(moaVar.d());
            eppVar.c(moaVar.b());
            for (mnu mnuVar : moaVar.j()) {
                arrayList.add(d(epoVar, mnuVar, mnuVar.c()));
                LogUtil.c("PlanService_IntPlanUtil", "convertAiRunWeekInfo new day : ", Integer.valueOf(mnuVar.c()), " week:", Integer.valueOf(mnuVar.c()));
                if (mnuVar.c() == 7) {
                    arrayList = new ArrayList();
                    eppVar = new epp();
                    eppVar.d(arrayList);
                    i++;
                    eppVar.d(i);
                    list2.add(eppVar);
                }
            }
        }
        if (eppVar.getDayCount() == 0) {
            list2.remove(eppVar);
            i--;
        }
        LogUtil.c("PlanService_IntPlanUtil", "convertWeekInfo weekOrder : ", Integer.valueOf(i));
        epoVar.c.i(i);
    }

    private static void a(epo epoVar, List<PlanWorkout> list, List<epp> list2, int i) {
        List<PlanWorkout> list3;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(epoVar.e.getBeginDate() * 1000);
        int d = gib.d(calendar.get(7));
        ArrayList arrayList = new ArrayList();
        epp eppVar = new epp();
        eppVar.d(arrayList);
        eppVar.d(1);
        list2.add(eppVar);
        int i2 = 1;
        int i3 = 1;
        int i4 = 0;
        for (int i5 = d; i5 < i + d; i5++) {
            i4++;
            if (i4 > 7) {
                i2++;
                list3 = list;
                i4 = 1;
            } else {
                list3 = list;
            }
            List<PlanWorkout> a2 = a(epoVar, list3, i2, i4);
            if (!a2.isEmpty() && eppVar.getWeekDesc() == null) {
                eppVar.e(a2.get(0).popWeekInfo().getSentence());
                eppVar.c(a2.get(0).popWeekInfo().getWeekName());
            }
            epl eplVar = new epl();
            eplVar.b(((i5 - 1) % 7) + 1);
            ArrayList arrayList2 = new ArrayList();
            eplVar.a(a(a2, arrayList2, 1));
            eplVar.c(arrayList2);
            arrayList.add(eplVar);
            if (i5 / 7 != i3 - 1) {
                epp eppVar2 = new epp();
                ArrayList arrayList3 = new ArrayList();
                eppVar2.d(arrayList3);
                i3++;
                eppVar2.d(i3);
                list2.add(eppVar2);
                eppVar = eppVar2;
                arrayList = arrayList3;
            }
        }
        if (eppVar.getDayCount() == 0) {
            list2.remove(eppVar);
        }
    }

    private static int a(List<PlanWorkout> list, List<epm> list2, int i) {
        for (PlanWorkout planWorkout : list) {
            if (!planWorkout.isDayClockIn()) {
                i = 0;
            }
            epm epmVar = new epm();
            epmVar.e(planWorkout.popWorkoutId());
            epmVar.e(IntAction.ActionType.RUN);
            epmVar.e(planWorkout.isDayClockIn() ? 1 : 0);
            list2.add(epmVar);
        }
        return i;
    }

    private static List<PlanWorkout> a(epo epoVar, List<PlanWorkout> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (PlanWorkout planWorkout : list) {
            int order = planWorkout.popDayInfo().getOrder();
            if (i == planWorkout.popWeekInfo().acquireOrder() && i2 == order) {
                arrayList.add(planWorkout);
            }
        }
        return arrayList;
    }

    private static epl d(epo epoVar, mnu mnuVar, int i) {
        List<CourseDataBean> a2 = mnuVar.a();
        ArrayList arrayList = new ArrayList();
        epl eplVar = new epl();
        eplVar.a(mnuVar.e() ? 1 : 0);
        eplVar.b(i);
        c(a2, arrayList, eplVar);
        eplVar.c(arrayList);
        List<mob> b2 = mnuVar.b();
        ArrayList arrayList2 = new ArrayList();
        b(b2, arrayList2, eplVar);
        eplVar.d(arrayList2);
        LogUtil.c("PlanService_IntPlanUtil", "convertAiRunDayInfo day order: ", Integer.valueOf(i), " actions:", Integer.valueOf(arrayList.size()));
        return eplVar;
    }

    private static void c(List<CourseDataBean> list, List<epm> list2, epl eplVar) {
        for (int i = 0; i < list.size(); i++) {
            epm epmVar = new epm();
            CourseDataBean courseDataBean = list.get(i);
            epmVar.e(courseDataBean.a());
            epmVar.e(courseDataBean.d() ? 1 : 0);
            if (courseDataBean.c() == 1) {
                epmVar.e(IntAction.ActionType.RUN);
            } else {
                epmVar.e(IntAction.ActionType.WORKOUT);
            }
            epmVar.a(courseDataBean.e());
            list2.add(epmVar);
            LogUtil.c("PlanService_IntPlanUtil", "convertAiRunInPlanAction in day : ", Integer.valueOf(eplVar.getDayOrder()), " actionIdx:", Integer.valueOf(i));
        }
    }

    private static void b(List<mob> list, List<epv> list2, epl eplVar) {
        if (koq.b(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            list2.add(new epv().c(list.get(i)));
            LogUtil.c("PlanService_IntPlanUtil", "convertAiRunInPlanAction in day : ", Integer.valueOf(eplVar.getDayOrder()), " recordIdx:", Integer.valueOf(i));
        }
    }

    public static epl a(epo epoVar, int i, int i2) {
        if (epoVar == null) {
            return null;
        }
        for (epp eppVar : epoVar.d()) {
            if (eppVar.getWeekOrder() == i) {
                for (epl eplVar : eppVar.b()) {
                    if (eplVar.getDayOrder() == i2) {
                        return eplVar;
                    }
                }
            }
        }
        return null;
    }

    public static epm a(epo epoVar, String str, int i, int i2) {
        epl a2 = a(epoVar, i, i2);
        if (a2 == null) {
            return null;
        }
        List<epm> d = a2.d();
        if (d != null) {
            for (epm epmVar : d) {
                if (epmVar.getActionId() != null && epmVar.getActionId().equals(str)) {
                    return epmVar;
                }
            }
        }
        List<epm> a3 = a2.a();
        if (a3 == null) {
            return null;
        }
        for (epm epmVar2 : a3) {
            if (epmVar2.getActionId().equals(str)) {
                return epmVar2;
            }
        }
        return null;
    }

    public static List<epm> e(epo epoVar, int i, int i2) {
        epl a2 = a(epoVar, i, i2);
        if (a2 == null) {
            return null;
        }
        return a2.d();
    }

    public static boolean a(IntPlan intPlan, WorkoutRecord workoutRecord) {
        if (intPlan == null) {
            ReleaseLogUtil.d("PlanService_IntPlanUtil", "intPlan == null");
            return false;
        }
        if (workoutRecord == null) {
            ReleaseLogUtil.d("PlanService_IntPlanUtil", "workoutRecord == null");
            return false;
        }
        long b2 = gib.b(intPlan.getPlanTimeInfo().getEndDate() * 1000);
        long b3 = gib.b(workoutRecord.acquireExerciseTime());
        ReleaseLogUtil.e("PlanService_IntPlanUtil", "endZeroTime:", Long.valueOf(b2), " currentRecordTime:", Long.valueOf(b3));
        return b3 > b2;
    }

    public static boolean b(String str, IntPlan intPlan, int i, int i2) {
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        if (weekInfoByOrder == null) {
            ReleaseLogUtil.c("PlanService_IntPlanUtil", "weekInfo not exist.", Integer.valueOf(i));
            return false;
        }
        IntDayPlan dayByOrder = weekInfoByOrder.getDayByOrder(i2);
        if (dayByOrder == null) {
            ReleaseLogUtil.c("PlanService_IntPlanUtil", "dayInfo not exist.", Integer.valueOf(i2));
            return false;
        }
        for (int i3 = 0; i3 < dayByOrder.getInPlanActionCnt(); i3++) {
            IntAction inPlanAction = dayByOrder.getInPlanAction(i3);
            if (inPlanAction != null) {
                ReleaseLogUtil.e("PlanService_IntPlanUtil", "courseId:", str, " day action id:", inPlanAction.getActionId());
                if (str.equals(inPlanAction.getActionId()) && (inPlanAction.getActionType() == IntAction.ActionType.RUN || inPlanAction.getActionType() == IntAction.ActionType.WORKOUT)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean c(String str, IntPlan intPlan, int i, int i2) {
        if (intPlan == null) {
            return false;
        }
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        if (weekInfoByOrder == null) {
            ReleaseLogUtil.c("PlanService_IntPlanUtil", "weekInfo not exist.", Integer.valueOf(i));
            return false;
        }
        IntDayPlan dayByOrder = weekInfoByOrder.getDayByOrder(i2);
        if (dayByOrder == null) {
            ReleaseLogUtil.c("PlanService_IntPlanUtil", "dayInfo not exist.", Integer.valueOf(i2));
            return false;
        }
        for (int i3 = 0; i3 < dayByOrder.getOutPlanActionCnt(); i3++) {
            IntAction outPlanAction = dayByOrder.getOutPlanAction(i3);
            if (outPlanAction != null) {
                ReleaseLogUtil.e("PlanService_IntPlanUtil", "isUserAddCourseInPlan courseId:", str, " day action id:", outPlanAction.getActionId());
                if (str.equals(outPlanAction.getActionId()) && (outPlanAction.getActionType() == IntAction.ActionType.RUN || outPlanAction.getActionType() == IntAction.ActionType.WORKOUT)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static float d(epo epoVar, long j) {
        float f = 0.0f;
        if (epoVar == null) {
            return 0.0f;
        }
        int e2 = ase.e(epoVar, j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int d = gib.d(calendar.get(7));
        int i = 0;
        for (epp eppVar : epoVar.d()) {
            for (epl eplVar : eppVar.b()) {
                boolean a2 = a(eplVar);
                if (!a2) {
                    LogUtil.a("PlanService_IntPlanUtil", "weekNo:", Integer.valueOf(eppVar.getWeekOrder()), " dayNo:", Integer.valueOf(eplVar.getDayOrder()));
                } else {
                    i++;
                    if (eppVar.getWeekOrder() <= e2) {
                        float e3 = e(eplVar);
                        f += e3;
                        LogUtil.a("PlanService_IntPlanUtil", "dayFinishRate:", Float.valueOf(e3), " weekNo:", Integer.valueOf(eppVar.getWeekOrder()), " dayNo:", Integer.valueOf(eplVar.getDayOrder()), " isTrainingDay:", Boolean.valueOf(a2));
                    }
                }
            }
        }
        float d2 = d(f, i);
        ReleaseLogUtil.e("PlanService_IntPlanUtil", "getFinishRate is ", Float.valueOf(d2), " weekOrder:", Integer.valueOf(e2), " dayOrder:", Integer.valueOf(d), " trainingDays:", Integer.valueOf(i));
        return d2;
    }

    public static float e(epo epoVar) {
        return d(epoVar, epoVar.getPlanTimeInfo().getEndDate() * 1000);
    }

    private static float e(epl eplVar) {
        if (a(eplVar)) {
            return d(eplVar);
        }
        return 0.0f;
    }

    private static boolean a(epl eplVar) {
        List<epm> d = eplVar.d();
        if (d == null) {
            LogUtil.b("PlanService_IntPlanUtil", "actionList == null.");
            return false;
        }
        if (d.size() == 0) {
            LogUtil.a("PlanService_IntPlanUtil", "rest day.");
            return false;
        }
        if (!a(d)) {
            return true;
        }
        LogUtil.a("PlanService_IntPlanUtil", "race day.");
        return false;
    }

    private static boolean a(List<epm> list) {
        boolean z = false;
        for (epm epmVar : list) {
            if (epmVar != null) {
                if (!"Race".equals(epmVar.getActionId())) {
                    return false;
                }
                LogUtil.a("PlanService_IntPlanUtil", "it is race day.");
                z = true;
            }
        }
        return z;
    }

    private static float d(epl eplVar) {
        if (eplVar.getPunchFlag() == 1) {
            return 1.0f;
        }
        if (eplVar.d().size() == 0) {
            return 0.0f;
        }
        Iterator<epm> it = eplVar.d().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getPunchFlag() == 1) {
                i++;
            }
        }
        return (i * 1.0f) / eplVar.d().size();
    }

    private static float d(float f, int i) {
        float f2 = i == 0 ? 0.0f : (f / i) * 100.0f;
        return moe.a(((double) f2) <= 99.9d ? f2 : 100.0f);
    }

    public static boolean e(mob mobVar, IntPlan intPlan) {
        if (mobVar == null || intPlan == null || mobVar.a() < intPlan.getPlanTimeInfo().getCreateTime() * 1000) {
            return false;
        }
        for (int i = 0; i < intPlan.getMetaInfo().getWeekCount(); i++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i);
            if (weekInfoByIdx != null) {
                for (int i2 = 0; i2 < weekInfoByIdx.getDayCount(); i2++) {
                    IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i2);
                    if (dayByIdx != null) {
                        for (int i3 = 0; i3 < dayByIdx.getRecordDataCnt(); i3++) {
                            RecordData recordData = dayByIdx.getRecordData(i3);
                            if (recordData != null && recordData.getRecordId().equals(mobVar.j())) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void b(List<StatInfoBean> list, epo epoVar) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("PlanService_IntPlanUtil", "the stats is empty.");
            return;
        }
        for (StatInfoBean statInfoBean : list) {
            epoVar.e(new epw(statInfoBean.getType(), statInfoBean.getValue()));
        }
        for (String str : d(epoVar) ? c : e) {
            if (epoVar.getStat(str) == null) {
                ReleaseLogUtil.d("PlanService_IntPlanUtil", str, "statistics is empty from cloud");
            }
        }
    }

    public static void a(epo epoVar) {
        List<epp> d = epoVar.d();
        int size = d.size();
        Iterator<epp> it = d.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getDayCount();
        }
        if (i == 0) {
            i = (int) ((epoVar.getPlanTimeInfo().getEndDate() - epoVar.getPlanTimeInfo().getBeginDate()) / k.b.m);
            size = gib.e(epoVar.getPlanTimeInfo().getBeginDate() * 1000, epoVar.getPlanTimeInfo().getEndDate() * 1000);
        }
        LogUtil.a("PlanService_IntPlanUtil", "intPlan planTempId: ", epoVar.c.getPlanTempId(), "totalWeek: ", Integer.valueOf(size), "totalDay: ", Integer.valueOf(i));
        epoVar.c.i(size);
        epoVar.c.e(i);
    }

    public static boolean d(epo epoVar) {
        if (epoVar.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            return true;
        }
        return epoVar.getPlanType() == IntPlan.PlanType.FIT_PLAN && epoVar.getMetaInfo().getPlanCategory() == 0;
    }

    public static List<moa> d(List<epp> list) {
        if (koq.b(list)) {
            LogUtil.h("PlanService_IntPlanUtil", "getPlanReportWeekList isEmpty");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (epp eppVar : list) {
            if (eppVar != null) {
                moa moaVar = new moa();
                moaVar.b(eppVar.getWeekOrder());
                List<epl> b2 = eppVar.b();
                if (!koq.b(b2)) {
                    c(moaVar, b2);
                    arrayList.add(moaVar);
                }
            }
        }
        return arrayList;
    }

    private static void c(moa moaVar, List<epl> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (epl eplVar : list) {
            if (eplVar != null) {
                mnu mnuVar = new mnu();
                mnuVar.b(eplVar.getDayOrder());
                mnuVar.b(eplVar.getPunchFlag() == 1);
                c(eplVar, mnuVar);
                arrayList.add(mnuVar);
            }
        }
        moaVar.c(arrayList);
    }

    private static void c(epl eplVar, mnu mnuVar) {
        if (koq.b(eplVar.d())) {
            LogUtil.h("PlanService_IntPlanUtil", "setCourseData isEmpty");
            return;
        }
        ArrayList arrayList = new ArrayList(eplVar.d().size());
        Iterator<epm> it = eplVar.d().iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                arrayList.add(new CourseDataBean());
            }
        }
        mnuVar.d(arrayList);
    }

    public static boolean e() {
        if (CommonUtil.bu()) {
            return false;
        }
        return !Utils.l();
    }
}
