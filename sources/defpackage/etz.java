package defpackage;

import com.huawei.basefitnessadvice.model.DayInfo;
import com.huawei.basefitnessadvice.model.FitnessDayPlan;
import com.huawei.basefitnessadvice.model.FitnessPlanCourse;
import com.huawei.basefitnessadvice.model.FitnessWeekPlan;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.WeekInfo;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class etz {
    public static Plan a(UserFitnessPlanInfo userFitnessPlanInfo) {
        if (userFitnessPlanInfo == null) {
            return null;
        }
        Plan plan = new Plan();
        ArrayList arrayList = new ArrayList(16);
        int acquireDisplayStyle = userFitnessPlanInfo.acquireDisplayStyle();
        List<FitnessWeekPlan> acquireWeekPlanList = acquireDisplayStyle == 0 ? userFitnessPlanInfo.acquireWeekPlanList() : userFitnessPlanInfo.acquireWeeklyPlanList();
        if (acquireWeekPlanList == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanDataConverter", "get weekPlanList from userFitnessPlanInfo is null");
            return plan;
        }
        int size = acquireWeekPlanList.size();
        d(userFitnessPlanInfo, plan, size);
        if (koq.b(acquireWeekPlanList)) {
            LogUtil.h("Suggestion_FitnessPackagePlanDataConverter", "get weekPlanList from userFitnessPlanInfo is empty");
            return plan;
        }
        return b(size, acquireWeekPlanList, acquireDisplayStyle, plan, arrayList);
    }

    private static Plan b(int i, List<FitnessWeekPlan> list, int i2, Plan plan, List<PlanWorkout> list2) {
        long j;
        long j2;
        List<PlanWorkout> list3;
        long j3 = 0;
        long j4 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            FitnessWeekPlan fitnessWeekPlan = list.get(i4);
            List<FitnessDayPlan> acquireWeekList = i2 == 0 ? fitnessWeekPlan.acquireWeekList() : fitnessWeekPlan.acquireDailyPlanList();
            if (koq.b(acquireWeekList)) {
                return plan;
            }
            int size = acquireWeekList.size();
            int i5 = 0;
            while (i5 < size) {
                FitnessDayPlan fitnessDayPlan = acquireWeekList.get(i5);
                if (i4 == 0 && i5 == 0) {
                    if (fitnessDayPlan == null) {
                        return plan;
                    }
                    long acquireDate = fitnessDayPlan.acquireDate();
                    j = j3;
                    long j5 = acquireDate / 1000;
                    plan.saveStartDate(ghz.a(j5, "yyyy-MM-dd"));
                    plan.setStartTime(j5);
                    j4 = acquireDate;
                } else {
                    j = j3;
                }
                if (i4 == i - 1 && i5 == size - 1) {
                    if (fitnessDayPlan == null) {
                        return plan;
                    }
                    j3 = fitnessDayPlan.acquireDate();
                    j2 = j4;
                    long j6 = j3 / 1000;
                    plan.saveEndDate(ghz.a(j6, "yyyy-MM-dd"));
                    plan.setEndTime(j6);
                    list3 = list2;
                } else {
                    j2 = j4;
                    list3 = list2;
                    j3 = j;
                }
                i3 = c(list3, i3, fitnessWeekPlan, fitnessDayPlan, i2);
                i5++;
                j4 = j2;
            }
        }
        plan.saveDays((int) (((j3 - j4) / 86400000) + 1));
        plan.saveWorkoutCount(i3);
        plan.saveWorkouts(list2);
        return plan;
    }

    private static int c(List<PlanWorkout> list, int i, FitnessWeekPlan fitnessWeekPlan, FitnessDayPlan fitnessDayPlan, int i2) {
        List<FitnessPlanCourse> acquireDayPlanCourses = i2 == 0 ? fitnessDayPlan.acquireDayPlanCourses() : fitnessDayPlan.acquireWorkoutList();
        if (acquireDayPlanCourses != null) {
            int size = acquireDayPlanCourses.size();
            i += size;
            for (int i3 = 0; i3 < size; i3++) {
                PlanWorkout planWorkout = new PlanWorkout();
                if (koq.d(acquireDayPlanCourses, i3) && acquireDayPlanCourses.get(i3) != null) {
                    planWorkout.putName(acquireDayPlanCourses.get(i3).acquireName());
                    planWorkout.putWorkoutId(acquireDayPlanCourses.get(i3).acquireCourseId());
                }
                a(fitnessWeekPlan, planWorkout, new WeekInfo());
                planWorkout.putDayInfo(e(fitnessDayPlan, i3));
                list.add(planWorkout);
            }
        }
        return i;
    }

    private static DayInfo e(FitnessDayPlan fitnessDayPlan, int i) {
        DayInfo dayInfo = new DayInfo();
        long acquireDate = fitnessDayPlan.acquireDate();
        dayInfo.saveOrder(ggl.h(acquireDate));
        dayInfo.saveDate(ghz.a(acquireDate / 1000, "yyyy-MM-dd"));
        dayInfo.saveSinglesCount(i + 1);
        dayInfo.saveDayTitle(fitnessDayPlan.acquireName());
        dayInfo.saveDayDesc(fitnessDayPlan.acquireDescription());
        return dayInfo;
    }

    private static void a(FitnessWeekPlan fitnessWeekPlan, PlanWorkout planWorkout, WeekInfo weekInfo) {
        weekInfo.saveWeekName(fitnessWeekPlan.acquireWeekPeriod());
        weekInfo.saveSentence(fitnessWeekPlan.acquireWeekDesc());
        weekInfo.saveOrder(fitnessWeekPlan.acquireWeekOrder());
        planWorkout.putWeekInfo(weekInfo);
    }

    private static void d(UserFitnessPlanInfo userFitnessPlanInfo, Plan plan, int i) {
        plan.saveCreateTime(userFitnessPlanInfo.acquireCreateTime());
        plan.setRemindTime(userFitnessPlanInfo.acquireRemindTime());
        plan.saveId(userFitnessPlanInfo.acquirePlanId());
        plan.putName(userFitnessPlanInfo.acquireName());
        plan.saveType(3);
        plan.savePicture(userFitnessPlanInfo.acquirePicture());
        plan.saveWeekCount(i);
        if (userFitnessPlanInfo.acquireDisplayStyle() == 0) {
            plan.saveCalorie(euc.e().e(userFitnessPlanInfo));
        }
    }

    public static PlanStat c(float f, String str) {
        if (str == null) {
            return null;
        }
        PlanStat planStat = new PlanStat();
        UserFitnessPlanInfo d = euc.e().d(str);
        planStat.saveMostCaloriePerWeek(c(d));
        planStat.saveMostWorkoutTimes(h(d));
        planStat.saveLongestTimePerWeek(b(d));
        planStat.saveHighestCompleteRate(evf.b(e(f, d) * 100.0f));
        return planStat;
    }

    private static float e(float f, UserFitnessPlanInfo userFitnessPlanInfo) {
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostCompleteRateByWeek enter");
        if (userFitnessPlanInfo == null) {
            return 0.0f;
        }
        float f2 = 0.0f;
        for (FitnessWeekPlan fitnessWeekPlan : userFitnessPlanInfo.acquireWeekPlanList()) {
            if (fitnessWeekPlan == null) {
                return f2;
            }
            List<FitnessDayPlan> acquireWeekList = fitnessWeekPlan.acquireWeekList();
            if (koq.b(acquireWeekList)) {
                return f2;
            }
            FitnessDayPlan fitnessDayPlan = acquireWeekList.get(0);
            FitnessDayPlan fitnessDayPlan2 = acquireWeekList.get(acquireWeekList.size() - 1);
            if (fitnessDayPlan == null || fitnessDayPlan2 == null) {
                return f2;
            }
            long acquireDate = fitnessDayPlan.acquireDate();
            long acquireDate2 = fitnessDayPlan2.acquireDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(Long.valueOf(acquireDate));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostCompleteRateByWeek strStartDate = ", format);
            String format2 = simpleDateFormat.format(Long.valueOf(acquireDate2));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostCompleteRateByWeek strEndDate = ", format2);
            float d = d(userFitnessPlanInfo, format, format2);
            float f3 = 0.0f;
            for (FitnessDayPlan fitnessDayPlan3 : fitnessWeekPlan.acquireWeekList()) {
                if (fitnessDayPlan3 == null) {
                    return f2;
                }
                f3 = a(f, f3, fitnessDayPlan3);
            }
            if (!CommonUtil.c(f3)) {
                float f4 = d / f3;
                LogUtil.c("Suggestion_FitnessPackagePlanDataConverter", "getMostCompleteRateByWeek completeRate = ", Float.valueOf(f4));
                if (f4 > f2) {
                    f2 = f4;
                }
            } else {
                ReleaseLogUtil.d("Suggestion_FitnessPackagePlanDataConverter", "getMostCompleteRateByWeek initWeekShouldTotal <= 0f");
                return 0.0f;
            }
        }
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostCompleteRateByWeek initValue = ", Float.valueOf(f2));
        return f2;
    }

    private static float a(float f, float f2, FitnessDayPlan fitnessDayPlan) {
        List<FitnessPlanCourse> acquireDayPlanCourses = fitnessDayPlan.acquireDayPlanCourses();
        if (acquireDayPlanCourses != null) {
            Iterator<FitnessPlanCourse> it = acquireDayPlanCourses.iterator();
            while (it.hasNext()) {
                FitnessPlanCourse next = it.next();
                f2 = (float) (f2 + (next != null ? euc.e().a(next.acquireCourseId()) * f : 0.0d));
            }
        }
        return f2;
    }

    private static float d(UserFitnessPlanInfo userFitnessPlanInfo, String str, String str2) {
        List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(userFitnessPlanInfo.acquirePlanId(), str, str2);
        if (workoutRecords == null) {
            return 0.0f;
        }
        Iterator<WorkoutRecord> it = workoutRecords.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            WorkoutRecord next = it.next();
            f += next != null ? next.acquireActualCalorie() : 0.0f;
        }
        return f;
    }

    private static int b(UserFitnessPlanInfo userFitnessPlanInfo) {
        int i;
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getLongestWorkoutPeriodByWeek enter");
        if (userFitnessPlanInfo == null) {
            return 0;
        }
        Iterator<FitnessWeekPlan> it = userFitnessPlanInfo.acquireWeekPlanList().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            List<FitnessDayPlan> acquireWeekList = it.next().acquireWeekList();
            if (koq.b(acquireWeekList)) {
                return i2;
            }
            FitnessDayPlan fitnessDayPlan = acquireWeekList.get(0);
            FitnessDayPlan fitnessDayPlan2 = acquireWeekList.get(acquireWeekList.size() - 1);
            if (fitnessDayPlan == null || fitnessDayPlan2 == null) {
                return i2;
            }
            long acquireDate = fitnessDayPlan.acquireDate();
            long acquireDate2 = fitnessDayPlan2.acquireDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(Long.valueOf(acquireDate));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getLongestWorkoutPeriodByWeek strStartDate = ", format);
            String format2 = simpleDateFormat.format(Long.valueOf(acquireDate2));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getLongestWorkoutPeriodByWeek strEndDate = ", format2);
            List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(userFitnessPlanInfo.acquirePlanId(), format, format2);
            if (workoutRecords != null) {
                Iterator<WorkoutRecord> it2 = workoutRecords.iterator();
                i = 0;
                while (it2.hasNext()) {
                    WorkoutRecord next = it2.next();
                    i += next != null ? next.getDuration() : 0;
                }
            } else {
                i = 0;
            }
            if (i > i2) {
                i2 = i;
            }
        }
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "workoutPeriod = ", Integer.valueOf(i2));
        return i2;
    }

    private static int h(UserFitnessPlanInfo userFitnessPlanInfo) {
        if (userFitnessPlanInfo == null) {
            return 0;
        }
        Iterator<FitnessWeekPlan> it = userFitnessPlanInfo.acquireWeekPlanList().iterator();
        int i = 0;
        while (it.hasNext()) {
            List<FitnessDayPlan> acquireWeekList = it.next().acquireWeekList();
            if (koq.b(acquireWeekList)) {
                return 0;
            }
            FitnessDayPlan fitnessDayPlan = acquireWeekList.get(0);
            FitnessDayPlan fitnessDayPlan2 = acquireWeekList.get(acquireWeekList.size() - 1);
            if (fitnessDayPlan == null || fitnessDayPlan2 == null) {
                return 0;
            }
            long acquireDate = fitnessDayPlan.acquireDate();
            long acquireDate2 = fitnessDayPlan2.acquireDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(Long.valueOf(acquireDate));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostWorkoutTimes strStartDate = ", format);
            String format2 = simpleDateFormat.format(Long.valueOf(acquireDate2));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostWorkoutTimes strEndDate = ", format2);
            List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(userFitnessPlanInfo.acquirePlanId(), format, format2);
            int size = workoutRecords != null ? workoutRecords.size() : 0;
            if (size > i) {
                i = size;
            }
        }
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "workoutTimes = ", Integer.valueOf(i));
        return i;
    }

    private static float c(UserFitnessPlanInfo userFitnessPlanInfo) {
        float f;
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostConsumerCalorieByWeek enter");
        if (userFitnessPlanInfo == null) {
            return 0.0f;
        }
        float f2 = 0.0f;
        for (FitnessWeekPlan fitnessWeekPlan : userFitnessPlanInfo.acquireWeekPlanList()) {
            if (fitnessWeekPlan == null) {
                return f2;
            }
            List<FitnessDayPlan> acquireWeekList = fitnessWeekPlan.acquireWeekList();
            if (koq.b(acquireWeekList)) {
                return f2;
            }
            FitnessDayPlan fitnessDayPlan = acquireWeekList.get(0);
            FitnessDayPlan fitnessDayPlan2 = acquireWeekList.get(acquireWeekList.size() - 1);
            if (fitnessDayPlan == null || fitnessDayPlan2 == null) {
                return f2;
            }
            long acquireDate = fitnessDayPlan.acquireDate();
            long acquireDate2 = fitnessDayPlan2.acquireDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(Long.valueOf(acquireDate));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostConsumerCalorieByWeek strStartDate = ", format);
            String format2 = simpleDateFormat.format(Long.valueOf(acquireDate2));
            LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getMostConsumerCalorieByWeek strEndDate = ", format2);
            List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(userFitnessPlanInfo.acquirePlanId(), format, format2);
            if (workoutRecords != null) {
                Iterator<WorkoutRecord> it = workoutRecords.iterator();
                f = 0.0f;
                while (it.hasNext()) {
                    WorkoutRecord next = it.next();
                    f += next != null ? next.acquireActualCalorie() : 0.0f;
                }
            } else {
                f = 0.0f;
            }
            if (f > f2) {
                f2 = f;
            }
        }
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "max_Cal = ", Float.valueOf(f2));
        return f2;
    }

    public static PlanRecord d(UserFitnessPlanInfo userFitnessPlanInfo) {
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "toPlanRecord enter");
        if (userFitnessPlanInfo == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanDataConverter", "userFitnessPlan info is null");
            return null;
        }
        PlanRecord planRecord = new PlanRecord();
        float e = euc.e().e(userFitnessPlanInfo);
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "planShouldTotal = ", Float.valueOf(e));
        planRecord.saveCalorie(e);
        List<WorkoutRecord> e2 = e(userFitnessPlanInfo);
        float a2 = a(e2);
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "planBurnedTotal = ", Float.valueOf(a2));
        planRecord.saveActualCalorie(a2);
        planRecord.savePlanId(userFitnessPlanInfo.acquirePlanId());
        epo c = eve.c(userFitnessPlanInfo);
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "toPlanRecord finishRate = ", Float.valueOf(eve.e(c)));
        if (!CommonUtil.c(eve.e(c))) {
            planRecord.saveFinishRate(eve.e(c));
        }
        planRecord.saveWeekCount(userFitnessPlanInfo.acquireWeekPlanList().size());
        planRecord.saveWorkoutDays(e(e2));
        planRecord.saveWorkoutTimes(c(e2));
        planRecord.savePlanName(userFitnessPlanInfo.acquireName());
        planRecord.savePlanType(3);
        planRecord.savePlanTempId(userFitnessPlanInfo.acquirePlanTempId());
        planRecord.saveFinishDate(userFitnessPlanInfo.acquireModifyTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        planRecord.saveStartDate(simpleDateFormat.format(Long.valueOf(userFitnessPlanInfo.acquireCreateTime())));
        planRecord.saveEndDate(simpleDateFormat.format(Long.valueOf(userFitnessPlanInfo.acquireCreateTime() + (((userFitnessPlanInfo.acquireWeekPlanList().size() * 7) - 1) * 86400000))));
        return planRecord;
    }

    public static float a(List<WorkoutRecord> list) {
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getPlanSumBurnedCalorie enter");
        float f = 0.0f;
        if (koq.b(list)) {
            return 0.0f;
        }
        if (list != null && list.size() > 0) {
            for (WorkoutRecord workoutRecord : list) {
                if (workoutRecord != null) {
                    f += workoutRecord.acquireActualCalorie();
                }
            }
        }
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "maxCalorie = ", Float.valueOf(f));
        return f / 1000.0f;
    }

    public static List<WorkoutRecord> e(UserFitnessPlanInfo userFitnessPlanInfo) {
        if (userFitnessPlanInfo == null) {
            LogUtil.b("Suggestion_FitnessPackagePlanDataConverter", "plan info is empty");
            return Collections.EMPTY_LIST;
        }
        if (koq.b(userFitnessPlanInfo.acquireWeekPlanList())) {
            LogUtil.b("Suggestion_FitnessPackagePlanDataConverter", "plan week plan list is empty", userFitnessPlanInfo.acquireDescription());
            return Collections.EMPTY_LIST;
        }
        List<FitnessDayPlan> acquireWeekList = userFitnessPlanInfo.acquireWeekPlanList().get(0).acquireWeekList();
        if (koq.b(acquireWeekList)) {
            LogUtil.b("Suggestion_FitnessPackagePlanDataConverter", "day plan list is empty", userFitnessPlanInfo.acquireDescription());
            return Collections.EMPTY_LIST;
        }
        FitnessDayPlan fitnessDayPlan = acquireWeekList.get(0);
        if (fitnessDayPlan == null) {
            LogUtil.b("Suggestion_FitnessPackagePlanDataConverter", "day plan list first is empty", userFitnessPlanInfo.acquireDescription());
            return Collections.EMPTY_LIST;
        }
        long acquireDate = fitnessDayPlan.acquireDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(Long.valueOf(acquireDate));
        List<FitnessDayPlan> acquireWeekList2 = userFitnessPlanInfo.acquireWeekPlanList().get(userFitnessPlanInfo.acquireWeekPlanList().size() - 1).acquireWeekList();
        FitnessDayPlan fitnessDayPlan2 = koq.c(acquireWeekList2) ? acquireWeekList2.get(acquireWeekList2.size() - 1) : null;
        if (fitnessDayPlan2 == null) {
            LogUtil.b("Suggestion_FitnessPackagePlanDataConverter", "day plan list last is empty", userFitnessPlanInfo.acquireDescription());
            return Collections.EMPTY_LIST;
        }
        String format2 = simpleDateFormat.format(Long.valueOf(fitnessDayPlan2.acquireDate()));
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getPlanRecord strStartDate = " + format + " strEndDate = ", format2);
        List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(userFitnessPlanInfo.acquirePlanId(), format, format2);
        Object[] objArr = new Object[2];
        objArr[0] = "getPlanRecords num is ";
        objArr[1] = Integer.valueOf(workoutRecords != null ? workoutRecords.size() : 0);
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", objArr);
        return koq.b(workoutRecords) ? Collections.EMPTY_LIST : workoutRecords;
    }

    private static int e(List<WorkoutRecord> list) {
        if (koq.b(list)) {
            return 0;
        }
        int a2 = a(0, list);
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getWorkoutDays = ", Integer.valueOf(a2));
        return a2;
    }

    private static int a(int i, List<WorkoutRecord> list) {
        int i2 = 0;
        while (i2 < list.size()) {
            if (i2 == 0) {
                i = 1;
            }
            String acquireWorkoutDate = list.get(i2).acquireWorkoutDate();
            LogUtil.c("Suggestion_FitnessPackagePlanDataConverter", "getWorkoutDays init = ", acquireWorkoutDate);
            i2++;
            if (i2 < list.size() && !e(acquireWorkoutDate, list.get(i2).acquireWorkoutDate())) {
                i++;
            }
        }
        return i;
    }

    private static boolean e(String str, String str2) {
        return ghz.a(str, "yyyy-MM-dd") == ghz.a(str2, "yyyy-MM-dd");
    }

    private static int c(List<WorkoutRecord> list) {
        if (koq.b(list)) {
            return 0;
        }
        int size = list.size();
        LogUtil.a("Suggestion_FitnessPackagePlanDataConverter", "getWorkoutTimes workoutTimes = ", Integer.valueOf(size));
        return size;
    }
}
