package defpackage;

import android.media.AudioManager;
import com.huawei.health.suggestion.protobuf.CourseProto;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ggn {
    private static int e(int i) {
        return i == 1 ? 6 : 1;
    }

    public static CourseProto.CourseStorageInfo.Builder e(FitWorkout fitWorkout, int i, boolean z) throws cbe {
        CourseProto.CourseStorageInfo.Builder newBuilder = CourseProto.CourseStorageInfo.newBuilder();
        if (fitWorkout == null) {
            ReleaseLogUtil.d("Suggestion_CourseLinkDataUtil", "convertFitWorkout fitWorkout == null");
            return newBuilder;
        }
        int max = (int) (Math.max(fitWorkout.getPublishDate(), fitWorkout.getModified()) / 1000);
        LogUtil.a("Suggestion_CourseLinkDataUtil", "convertFitWorkout updateTime:", Integer.valueOf(max));
        newBuilder.setExerciseId(fitWorkout.acquireId()).addAllExerciseName(cbd.e(fitWorkout.acquireName())).setTotalMeasurementType(fitWorkout.acquireMeasurementType()).setTotalMeasurementValue(fitWorkout.acquireDuration()).setExerciseVersion(fitWorkout.accquireVersion() + "").setDifficult(fitWorkout.acquireDifficulty()).setUsingType(i).setUpdateTime(max).setDefineType(fitWorkout.getCourseDefineType()).setCourseType(fitWorkout.getType());
        if (z) {
            return newBuilder;
        }
        if (fitWorkout.isNewRunCourse()) {
            d(newBuilder, fitWorkout.getCourseActions());
            newBuilder.setActionTotalNum(a(fitWorkout));
        } else {
            ArrayList arrayList = new ArrayList();
            e(fitWorkout, arrayList);
            c(newBuilder, arrayList);
            newBuilder.setActionTotalNum(arrayList.size());
        }
        if (newBuilder.getActionTotalNum() <= 0) {
            LogUtil.h("Suggestion_CourseLinkDataUtil", "courseInfo getActionTotalNum < 0");
        }
        LogUtil.h("Suggestion_CourseLinkDataUtil", "courseInfo ", newBuilder.build().toString());
        return newBuilder;
    }

    private static void e(FitWorkout fitWorkout, List<WorkoutAction> list) {
        if (fitWorkout.acquireWarmUpRunAction() != null) {
            list.add(fitWorkout.acquireWarmUpRunAction());
        }
        List<WorkoutAction> acquireWorkoutActions = fitWorkout.acquireWorkoutActions();
        if (acquireWorkoutActions != null) {
            list.addAll(acquireWorkoutActions);
        }
        if (fitWorkout.acquireWarmUpRunAction() != null) {
            list.add(fitWorkout.acquireCoolDownRunAction());
        }
    }

    private static int a(FitWorkout fitWorkout) {
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

    private static void c(CourseProto.CourseStorageInfo.Builder builder, List<WorkoutAction> list) {
        for (WorkoutAction workoutAction : list) {
            if (workoutAction != null) {
                builder.addCombineInfo(c(workoutAction));
            }
        }
    }

    private static void d(CourseProto.CourseStorageInfo.Builder builder, List<ChoreographedMultiActions> list) {
        for (ChoreographedMultiActions choreographedMultiActions : list) {
            if (choreographedMultiActions != null) {
                builder.addCombineInfo(a(choreographedMultiActions));
            }
        }
    }

    private static CourseProto.CourseStorageInfo.CombineInfo.Builder a(ChoreographedMultiActions choreographedMultiActions) {
        CourseProto.CourseStorageInfo.CombineInfo.Builder newBuilder = CourseProto.CourseStorageInfo.CombineInfo.newBuilder();
        List<ChoreographedSingleAction> actionList = choreographedMultiActions.getActionList();
        if (actionList == null) {
            newBuilder.setActionNum(0);
            return newBuilder;
        }
        newBuilder.setActionNum(actionList.size());
        newBuilder.setRepeatNum(choreographedMultiActions.getRepeatTimes());
        for (ChoreographedSingleAction choreographedSingleAction : actionList) {
            if (choreographedSingleAction != null) {
                CourseProto.CourseStorageInfo.ActionInfo.Builder newBuilder2 = CourseProto.CourseStorageInfo.ActionInfo.newBuilder();
                newBuilder2.setActionId(choreographedSingleAction.getAction().getId());
                newBuilder2.setActionName(choreographedSingleAction.getAction().getName());
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

    private static CourseProto.CourseStorageInfo.CombineInfo.Builder c(WorkoutAction workoutAction) {
        AtomicAction action = workoutAction.getAction();
        CourseProto.CourseStorageInfo.ActionInfo.Builder newBuilder = CourseProto.CourseStorageInfo.ActionInfo.newBuilder();
        if (action == null) {
            ReleaseLogUtil.d("Suggestion_CourseLinkDataUtil", "convertAction atomicAction == null");
            return CourseProto.CourseStorageInfo.CombineInfo.newBuilder();
        }
        ReleaseLogUtil.e("Suggestion_CourseLinkDataUtil", "convertAction actionType", Integer.valueOf(action.getActionSportType()));
        newBuilder.setActionId(workoutAction.getActionId() == null ? "" : workoutAction.getActionId()).setActionName(workoutAction.getAction() != null ? workoutAction.getAction().getName() : "").setMeasurementType(e(action.getActionSportType())).setMeasurementValue(b(action, workoutAction)).setInstensityType(workoutAction.acquireIntensityType()).setInstensityValueH(0).setInstensityValueL(0);
        CourseProto.CourseStorageInfo.CombineInfo.Builder newBuilder2 = CourseProto.CourseStorageInfo.CombineInfo.newBuilder();
        newBuilder2.setActionNum(1).setRepeatNum(1).addActionInfo(newBuilder);
        return newBuilder2;
    }

    private static int b(AtomicAction atomicAction, WorkoutAction workoutAction) {
        if ("timer".equals(ffq.e(atomicAction.getActionSportType()))) {
            return workoutAction.getDuration();
        }
        return atomicAction.getExtendPropertyInt("frequency");
    }

    public static CourseStateProto.CourseState.Builder c(int i, int i2, int i3) {
        if (i2 == 0) {
            ReleaseLogUtil.e("Suggestion_CourseLinkDataUtil", "setChapterBreak restTime == 0");
            return CourseStateProto.CourseState.newBuilder();
        }
        int i4 = i - 1;
        if (i4 < 0) {
            i4 = 0;
        }
        ReleaseLogUtil.e("Suggestion_CourseLinkDataUtil", "buildBreakCourseState tempCurrent:", Integer.valueOf(i4));
        CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
        newBuilder.setCourseIndex(i4);
        newBuilder.setCourseSleepTime(i3);
        newBuilder.setCourseSleepTotalTime(i2);
        return newBuilder;
    }

    public static int d() {
        if (((AudioManager) BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO)) != null) {
            return (int) ((r0.getStreamVolume(3) * 100.0d) / r0.getStreamMaxVolume(3));
        }
        return 0;
    }
}
