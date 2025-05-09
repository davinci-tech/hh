package com.huawei.health.suggestion.wearengine.model;

import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.suggestion.protobuf.CourseDataForDevice;
import com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import defpackage.cbd;
import defpackage.cbe;
import defpackage.koq;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class BaseCoursePlanDataCustomer implements CoursePlanDataCustomer {
    @Override // com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public abstract int distributeBusinessType();

    @Override // com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public abstract int finishPlanBusinessType();

    @Override // com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public abstract byte[] getPlanBasicData(IntPlan intPlan, Map<String, FitWorkout> map) throws cbe;

    public abstract String getPlanFileName();

    @Override // com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public abstract int handleShakeBusinessType();

    @Override // com.huawei.health.suggestion.wearengine.CoursePlanDataCustomer
    public byte[] getCourseData(FitWorkout fitWorkout, int i) throws cbe {
        return convertFitWorkout(fitWorkout, i).build().toByteArray();
    }

    private CourseDataForDevice.CourseStorageInfo.Builder convertFitWorkout(FitWorkout fitWorkout, int i) throws cbe {
        CourseDataForDevice.CourseStorageInfo.Builder newBuilder = CourseDataForDevice.CourseStorageInfo.newBuilder();
        int max = (int) (Math.max(fitWorkout.getPublishDate(), fitWorkout.getModified()) / 1000);
        LogUtil.a(CoursePlanDataCustomer.TAG, "convertFitWorkout updateTime:", Integer.valueOf(max));
        newBuilder.setExerciseId(fitWorkout.acquireId()).addAllExerciseName(cbd.e(fitWorkout.acquireName())).setTotalMeasurementType(fitWorkout.acquireMeasurementType()).setTotalMeasurementValue(fitWorkout.acquireDuration()).setExerciseVersion(fitWorkout.accquireVersion() + "").setDifficult(fitWorkout.acquireDifficulty()).setUsingType(i).setUpdateTime(max).setDefineType(fitWorkout.getCourseDefineType()).setCourseType(fitWorkout.getType());
        if (fitWorkout.isNewRunCourse()) {
            addActions(newBuilder, fitWorkout.getCourseActions());
            newBuilder.setActionTotalNum(getTotalActions(fitWorkout));
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(fitWorkout.acquireWarmUpRunAction());
            arrayList.addAll(fitWorkout.acquireWorkoutActions());
            arrayList.add(fitWorkout.acquireCoolDownRunAction());
            addOldActions(newBuilder, arrayList);
            newBuilder.setActionTotalNum(arrayList.size());
        }
        if (newBuilder.getActionTotalNum() <= 0) {
            throw new cbe(-101, "course data convert error with null actions.");
        }
        LogUtil.h(CoursePlanDataCustomer.TAG, "courseInfo ", newBuilder.build().toString());
        newBuilder.build().toByteArray();
        return newBuilder;
    }

    private void addActions(CourseDataForDevice.CourseStorageInfo.Builder builder, List<ChoreographedMultiActions> list) {
        Iterator<ChoreographedMultiActions> it = list.iterator();
        while (it.hasNext()) {
            builder.addCombineInfo(convertAction(it.next()));
        }
    }

    private CourseDataForDevice.CourseStorageInfo.CombineInfo.Builder convertAction(WorkoutAction workoutAction) {
        CourseDataForDevice.CourseStorageInfo.ActionInfo.Builder newBuilder = CourseDataForDevice.CourseStorageInfo.ActionInfo.newBuilder();
        newBuilder.setActionId(workoutAction.getActionId()).setMeasurementType(workoutAction.acquireIntensityType()).setMeasurementValue(workoutAction.acquireMeasurementValue()).setInstensityType(workoutAction.acquireIntensityType()).setInstensityValueH(0).setInstensityValueL(0);
        CourseDataForDevice.CourseStorageInfo.CombineInfo.Builder newBuilder2 = CourseDataForDevice.CourseStorageInfo.CombineInfo.newBuilder();
        newBuilder2.setActionNum(1).setRepeatNum(1).addActionInfo(newBuilder);
        return newBuilder2;
    }

    private void addOldActions(CourseDataForDevice.CourseStorageInfo.Builder builder, List<WorkoutAction> list) {
        Iterator<WorkoutAction> it = list.iterator();
        while (it.hasNext()) {
            builder.addCombineInfo(convertAction(it.next()));
        }
    }

    private CourseDataForDevice.CourseStorageInfo.CombineInfo.Builder convertAction(ChoreographedMultiActions choreographedMultiActions) {
        CourseDataForDevice.CourseStorageInfo.CombineInfo.Builder newBuilder = CourseDataForDevice.CourseStorageInfo.CombineInfo.newBuilder();
        List<ChoreographedSingleAction> actionList = choreographedMultiActions.getActionList();
        if (actionList == null) {
            newBuilder.setActionNum(0);
            return newBuilder;
        }
        newBuilder.setActionNum(actionList.size());
        newBuilder.setRepeatNum(choreographedMultiActions.getRepeatTimes());
        for (ChoreographedSingleAction choreographedSingleAction : actionList) {
            if (choreographedSingleAction != null) {
                CourseDataForDevice.CourseStorageInfo.ActionInfo.Builder newBuilder2 = CourseDataForDevice.CourseStorageInfo.ActionInfo.newBuilder();
                newBuilder2.setActionId(choreographedSingleAction.getAction().getId());
                TargetConfig targetConfig = choreographedSingleAction.getTargetConfig();
                if (targetConfig != null) {
                    newBuilder2.setMeasurementType(CommonUtil.e(targetConfig.getId(), 255)).setMeasurementValue((int) Math.round(TargetConfig.ValueTypes.LOW_VALUE.getValue() == targetConfig.getValueType() ? targetConfig.getValueL() : targetConfig.getValueH()));
                } else {
                    newBuilder2.setMeasurementType(255).setMeasurementValue(0);
                }
                TargetConfig intensityConfig = choreographedSingleAction.getIntensityConfig();
                if (intensityConfig != null) {
                    newBuilder2.setInstensityType(CommonUtil.e(intensityConfig.getId(), 255)).setInstensityValueH((int) Math.round(intensityConfig.getValueH())).setInstensityValueL((int) Math.round(intensityConfig.getValueL()));
                } else {
                    newBuilder2.setInstensityType(255).setInstensityValueH(0).setInstensityValueL(0);
                }
                newBuilder.addActionInfo(newBuilder2);
            }
        }
        return newBuilder;
    }

    private int getTotalActions(FitWorkout fitWorkout) {
        List<ChoreographedMultiActions> courseActions = fitWorkout.getCourseActions();
        int i = 0;
        if (koq.b(courseActions)) {
            return 0;
        }
        for (ChoreographedMultiActions choreographedMultiActions : courseActions) {
            if (choreographedMultiActions != null && !koq.b(choreographedMultiActions.getActionList())) {
                i += choreographedMultiActions.getActionList().size();
            }
        }
        return i;
    }

    public static int getFinishPlanBusinessType(int i) {
        if (IntPlan.PlanType.AI_RUN_PLAN.getType() == i) {
            return SportHiWearBusinessType.FITNESS_RUN_PLAN_FINISH.getTypeValue();
        }
        return SportHiWearBusinessType.FITNESS_AI_FITNESS_PLAN_FINISH.getTypeValue();
    }
}
