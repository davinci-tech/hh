package defpackage;

import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.suggestion.protobuf.PlanDataForDevice;
import com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gip extends BaseCoursePlanDataCustomer {
    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public int handleShakeBusinessType() {
        return SportHiWearBusinessType.FITNESS_RUN_PLAN_HANDLE_SHAKE.getTypeValue();
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public int distributeBusinessType() {
        return SportHiWearBusinessType.FITNESS_RUN_PLAN_INFO_FILE.getTypeValue();
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public int finishPlanBusinessType() {
        return SportHiWearBusinessType.FITNESS_RUN_PLAN_FINISH.getTypeValue();
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public byte[] getPlanBasicData(IntPlan intPlan, Map<String, FitWorkout> map) {
        PlanDataForDevice.PlanInfo.Builder b = b(intPlan, map);
        ReleaseLogUtil.e("Suggestion_RunPlanDataCustomer", "getPlanBasicData() ", b.build().toString());
        return b.build().toByteArray();
    }

    private PlanDataForDevice.PlanInfo.Builder b(IntPlan intPlan, Map<String, FitWorkout> map) {
        IntWeekPlan intWeekPlan;
        int i;
        IntWeekPlan intWeekPlan2;
        int i2;
        PlanDataForDevice.PlanInfo.Builder newBuilder = PlanDataForDevice.PlanInfo.newBuilder();
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(ase.g(intPlan));
        if (weekInfoByOrder != null) {
            PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.Builder e = e(weekInfoByOrder, intPlan);
            int a2 = (int) ase.a(intPlan, weekInfoByOrder.getWeekOrder());
            int i3 = 0;
            if (weekInfoByOrder.getDayCount() != 0) {
                int i4 = 0;
                while (i3 < weekInfoByOrder.getDayCount()) {
                    IntDayPlan dayByIdx = weekInfoByOrder.getDayByIdx(i3);
                    if (dayByIdx == null) {
                        intWeekPlan2 = weekInfoByOrder;
                        i2 = a2;
                    } else {
                        int c = ase.c(a2, (String) null, dayByIdx.getDayOrder());
                        intWeekPlan2 = weekInfoByOrder;
                        i2 = a2;
                        if (c > a2 - TimeUnit.DAYS.toSeconds(1L)) {
                            PlanDataForDevice.PlanInfo.DayPlanBasicInfo.Builder e2 = e(c, dayByIdx, map);
                            newBuilder.addDayPlanBasicInfo(e2);
                            i4 += e2.getCourseNum();
                            i3++;
                            weekInfoByOrder = intWeekPlan2;
                            a2 = i2;
                        }
                    }
                    i3++;
                    weekInfoByOrder = intWeekPlan2;
                    a2 = i2;
                }
                intWeekPlan = weekInfoByOrder;
                i = a2;
                i3 = i4;
            } else {
                intWeekPlan = weekInfoByOrder;
                i = a2;
            }
            e.setExerciseTimes(i3);
            e.setCurrentWeekDays(intWeekPlan.getDayCount());
            newBuilder.addWeekPlanBasicInfo(e);
            seconds = i;
        }
        newBuilder.setPlanBasicInfo(d(intPlan, seconds));
        return newBuilder;
    }

    private PlanDataForDevice.PlanInfo.PlanBasicInfo.Builder d(IntPlan intPlan, int i) {
        PlanDataForDevice.PlanInfo.PlanBasicInfo.Builder newBuilder = PlanDataForDevice.PlanInfo.PlanBasicInfo.newBuilder();
        newBuilder.setPlanId(intPlan.getPlanId());
        newBuilder.addAllPlanName(cbd.e(intPlan.getMetaInfo().getName()));
        newBuilder.setLanguageType(CommonUtil.u());
        newBuilder.setPlanTotalDays(intPlan.getMetaInfo().getDayCount());
        newBuilder.setPlanTotalWeeks(intPlan.getMetaInfo().getWeekCount());
        newBuilder.setPlanStartTime((int) intPlan.getPlanTimeInfo().getBeginDate());
        long millis = TimeUnit.SECONDS.toMillis(i);
        newBuilder.setZoneOffset(ase.a(jdl.q(millis), millis));
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (intPlan.getCompatiblePlan() != null) {
            currentTimeMillis = (int) (intPlan.getCompatiblePlan().getLatestClockInTime() / 1000);
        }
        newBuilder.setPunchTimeStamp(currentTimeMillis);
        return newBuilder;
    }

    private PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.Builder e(IntWeekPlan intWeekPlan, IntPlan intPlan) {
        PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.Builder newBuilder = PlanDataForDevice.PlanInfo.WeekPlanBasicInfo.newBuilder();
        newBuilder.addAllWeekPlanName(cbd.e(intWeekPlan.getWeekPeriod()));
        newBuilder.setCurrentWeekDays(intWeekPlan.getDayCount());
        newBuilder.setExerciseTimes(intWeekPlan.getDayCount());
        newBuilder.setWeekNum(intWeekPlan.getWeekOrder());
        newBuilder.setWeekPlanStartTime((int) ase.a(intPlan, intWeekPlan.getWeekOrder()));
        newBuilder.setWeekPlanEndTime((int) ase.e(intPlan, intWeekPlan.getWeekOrder()));
        return newBuilder;
    }

    private PlanDataForDevice.PlanInfo.DayPlanBasicInfo.Builder e(int i, IntDayPlan intDayPlan, Map<String, FitWorkout> map) {
        PlanDataForDevice.PlanInfo.DayPlanBasicInfo.Builder newBuilder = PlanDataForDevice.PlanInfo.DayPlanBasicInfo.newBuilder();
        newBuilder.setTime(i);
        if (intDayPlan.getInPlanActionCnt() != 0) {
            newBuilder.setIsDayPunch(intDayPlan.getPunchFlag());
            boolean z = false;
            for (int i2 = 0; i2 < intDayPlan.getInPlanActionCnt(); i2++) {
                IntAction inPlanAction = intDayPlan.getInPlanAction(i2);
                if (inPlanAction != null) {
                    if ("Race".equals(inPlanAction.getActionId())) {
                        ReleaseLogUtil.e("Suggestion_RunPlanDataCustomer", "getDayBasicInfo race day");
                        z = true;
                    } else {
                        newBuilder.addDayPlanCourse(d(inPlanAction, map.get(inPlanAction.getActionId())));
                    }
                }
            }
            newBuilder.setCourseNum(z ? 0 : intDayPlan.getInPlanActionCnt());
        } else {
            newBuilder.setCourseNum(0);
            newBuilder.setIsDayPunch(0);
        }
        return newBuilder;
    }

    private PlanDataForDevice.PlanInfo.CourseOutLine.Builder d(IntAction intAction, FitWorkout fitWorkout) {
        PlanDataForDevice.PlanInfo.CourseOutLine.Builder newBuilder = PlanDataForDevice.PlanInfo.CourseOutLine.newBuilder();
        newBuilder.setCourseId(intAction.getActionId());
        newBuilder.addAllCourseName(cbd.e(fitWorkout.acquireName()));
        int max = (int) (Math.max(fitWorkout.getPublishDate(), fitWorkout.getModified()) / 1000);
        LogUtil.a("Suggestion_RunPlanDataCustomer", "getCourseOutline updateTime:", Integer.valueOf(max));
        newBuilder.setUpdateTime(max);
        newBuilder.setCourseType(fitWorkout.getType());
        newBuilder.setIsCoursePunch(intAction.getPunchFlag());
        return newBuilder;
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer
    public String getPlanFileName() {
        return "planInfo.bin";
    }
}
