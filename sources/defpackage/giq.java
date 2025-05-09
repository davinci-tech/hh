package defpackage;

import com.google.protobuf.UninitializedMessageException;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.health.R;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.suggestion.protobuf.GeneralPlanDataForDevice;
import com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes4.dex */
public class giq extends BaseCoursePlanDataCustomer {
    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public int handleShakeBusinessType() {
        return SportHiWearBusinessType.FITNESS_RUN_PLAN_HANDLE_SHAKE.getTypeValue();
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public int distributeBusinessType() {
        return SportHiWearBusinessType.FITNESS_AI_FITNESS_PLAN_INFO_FILE.getTypeValue();
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public int finishPlanBusinessType() {
        return SportHiWearBusinessType.FITNESS_AI_FITNESS_PLAN_FINISH.getTypeValue();
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer, com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public byte[] getPlanBasicData(IntPlan intPlan, Map<String, FitWorkout> map) throws cbe {
        GeneralPlanDataForDevice.GeneralPlanInfo.Builder a2 = a(intPlan, map);
        if (a2 == null) {
            ReleaseLogUtil.d("Suggestion_GeneralPlanDataCustomer", "getPlanBasicData() planBuilder null");
            return null;
        }
        try {
            ReleaseLogUtil.e("Suggestion_GeneralPlanDataCustomer", "getPlanBasicData() ", a2.build().toString());
            return a2.build().toByteArray();
        } catch (UninitializedMessageException e) {
            ReleaseLogUtil.c("Suggestion_GeneralPlanDataCustomer", "getPlanBasicData() exception", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    private GeneralPlanDataForDevice.GeneralPlanInfo.Builder a(IntPlan intPlan, Map<String, FitWorkout> map) throws cbe {
        GeneralPlanDataForDevice.GeneralPlanInfo.Plan.Builder newBuilder = GeneralPlanDataForDevice.GeneralPlanInfo.Plan.newBuilder();
        if (intPlan == null || intPlan.getPlanTimeInfo() == null || intPlan.getMetaInfo() == null) {
            ReleaseLogUtil.d("Suggestion_GeneralPlanDataCustomer", "convertPlanData intWeekPlan getPlanTimeInfo getMetaInfo == null");
            return null;
        }
        newBuilder.setPlanId(intPlan.getPlanId());
        newBuilder.setCategory(1);
        newBuilder.setSubCategory(0);
        newBuilder.setStatus(1);
        newBuilder.setTimeZone(intPlan.getTimeZone());
        newBuilder.setStartTime(intPlan.getPlanTimeInfo().getBeginDate());
        newBuilder.setEndTime(intPlan.getPlanTimeInfo().getEndDate());
        GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.Builder newBuilder2 = GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.newBuilder();
        GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata.Builder newBuilder3 = GeneralPlanDataForDevice.GeneralPlanInfo.PlanMetadata.newBuilder();
        newBuilder3.setPlanTotalDays(intPlan.getMetaInfo().getDayCount());
        newBuilder3.setPlanTotalWeeks(intPlan.getMetaInfo().getWeekCount());
        int g = ase.g(intPlan);
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(g);
        if (weekInfoByOrder == null) {
            ReleaseLogUtil.d("Suggestion_GeneralPlanDataCustomer", "convertPlanData intWeekPlan == null currentWeek:", Integer.valueOf(g));
            return null;
        }
        newBuilder3.addAllWeekPlanName(cbd.e(weekInfoByOrder.getWeekPeriod()));
        newBuilder3.setExerciseTimes(a(weekInfoByOrder));
        newBuilder3.setCurrentWeekDays(weekInfoByOrder.getDayCount());
        newBuilder3.setWeekNum(weekInfoByOrder.getWeekOrder());
        newBuilder3.setWeekPlanStartTime((int) ase.a(intPlan, weekInfoByOrder.getWeekOrder()));
        newBuilder3.setWeekPlanEndTime((int) ase.e(intPlan, weekInfoByOrder.getWeekOrder()));
        newBuilder2.setPlanMetadata(newBuilder3);
        newBuilder.setMetadata(newBuilder2);
        GeneralPlanDataForDevice.GeneralPlanInfo.Task.Builder newBuilder4 = GeneralPlanDataForDevice.GeneralPlanInfo.Task.newBuilder();
        newBuilder4.addAllName(cbd.e(BaseApplication.getContext().getString(R.string._2130845582_res_0x7f021f8e)));
        newBuilder4.setType(100);
        newBuilder4.setStatus(1);
        c(intPlan, map, weekInfoByOrder, newBuilder4);
        newBuilder.addTasks(newBuilder4);
        GeneralPlanDataForDevice.GeneralPlanInfo.Builder newBuilder5 = GeneralPlanDataForDevice.GeneralPlanInfo.newBuilder();
        newBuilder5.setPlanBasicInfo(newBuilder);
        return newBuilder5;
    }

    private int a(IntWeekPlan intWeekPlan) {
        int i = 0;
        for (int i2 = 0; i2 < intWeekPlan.getDayCount(); i2++) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i2);
            if (dayByIdx != null) {
                i += dayByIdx.getInPlanActionCnt();
            }
        }
        return i;
    }

    private void c(IntPlan intPlan, Map<String, FitWorkout> map, IntWeekPlan intWeekPlan, GeneralPlanDataForDevice.GeneralPlanInfo.Task.Builder builder) throws cbe {
        for (int i = 0; i < intWeekPlan.getDayCount(); i++) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i);
            if (dayByIdx != null) {
                for (int i2 = 0; i2 < dayByIdx.getInPlanActionCnt(); i2++) {
                    GeneralPlanDataForDevice.GeneralPlanInfo.Schedule.Builder newBuilder = GeneralPlanDataForDevice.GeneralPlanInfo.Schedule.newBuilder();
                    newBuilder.setAllDayType(false);
                    long d = ase.d(intPlan, intWeekPlan.getWeekOrder(), dayByIdx.getDayOrder());
                    newBuilder.setDtStart(d / 1000);
                    newBuilder.setDtEnd(gib.d(d) / 1000);
                    GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.Builder newBuilder2 = GeneralPlanDataForDevice.GeneralPlanInfo.Metadata.newBuilder();
                    newBuilder2.setCourseMsg(b(map, dayByIdx, i2));
                    newBuilder.setMetadata(newBuilder2);
                    newBuilder.setTimeZone(intPlan.getTimeZone());
                    builder.addSchedules(newBuilder);
                }
            }
        }
    }

    private GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.Builder b(Map<String, FitWorkout> map, IntDayPlan intDayPlan, int i) throws cbe {
        GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.Builder newBuilder = GeneralPlanDataForDevice.GeneralPlanInfo.CourseMetadata.newBuilder();
        newBuilder.setDayOrder(intDayPlan.getDayOrder());
        newBuilder.setCourseIndex(i);
        newBuilder.setIsDayPunch(intDayPlan.getPunchFlag());
        IntAction inPlanAction = intDayPlan.getInPlanAction(i);
        newBuilder.setCourseId(inPlanAction.getActionId());
        FitWorkout fitWorkout = map.get(inPlanAction.getActionId());
        if (fitWorkout == null) {
            ReleaseLogUtil.c("Suggestion_GeneralPlanDataCustomer", "relative courseId not has workout.");
            throw new cbe(-101, "relative courseId not has workout.");
        }
        newBuilder.addAllCourseName(cbd.e(fitWorkout.acquireName()));
        newBuilder.setCourseType(fitWorkout.getType());
        newBuilder.setUpdateTime((int) (Math.max(fitWorkout.getPublishDate(), fitWorkout.getModified()) / 1000));
        newBuilder.setIsCoursePunch(inPlanAction.getPunchFlag());
        return newBuilder;
    }

    @Override // com.huawei.health.suggestion.wearengine.model.BaseCoursePlanDataCustomer
    public String getPlanFileName() {
        return "dietcalplan.bin";
    }
}
