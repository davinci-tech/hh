package com.huawei.health.sportservice.dataproducer;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.ffd;
import defpackage.ggs;
import defpackage.ghz;
import defpackage.kny;
import defpackage.koq;
import defpackage.moe;
import defpackage.nrv;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@SportComponentType(classify = 2, name = ComponentName.COURE_DATA_PRODUCER)
/* loaded from: classes8.dex */
public class CourseDataProducer extends BaseProducer implements SportLifecycle, ITargetUpdateListener {
    private static final Object LOCK = new Object();
    private static final String TAG = "SportService_CourseDataProducer";
    private static final String WORKOUT_TMP = "workoutTmp";
    private FitWorkout mFitWorkout;
    private CopyOnWriteArrayList<kny> mHeartRateAreaList = new CopyOnWriteArrayList<>();
    private final List<RecordAction> mRecordActions = new ArrayList();
    private List<ChoreographedSingleAction> mSingleActionList;
    private WorkoutRecord mWorkoutRecord;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.d(TAG, "enter onPreSport");
        BaseSportManager.getInstance().addObserver(SportObserverType.COURSE_TARGET_OBSERVE, TAG, this);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        saveRealDataToWorkout();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            if (koq.c(motionPath.requestRunningPostureList())) {
                this.mHeartRateAreaList = new CopyOnWriteArrayList<>(motionPath.getHeartRateAreaList());
            }
            LogUtil.d(TAG, "recovery mHeartRateAreaList.size() ", Integer.valueOf(this.mHeartRateAreaList.size()));
        }
        this.mWorkoutRecord = getDataFromSp();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.d(TAG, "enter onStopSport");
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            setSportDataToWorkout((MotionPathSimplify) data);
        }
        updateWorkoutRecordData();
        clearTmpData();
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        if (this.mFitWorkout == null) {
            Object data = BaseSportManager.getInstance().getData("COURSE_DATA");
            if (data instanceof FitWorkout) {
                this.mFitWorkout = (FitWorkout) data;
                initWorkout();
                this.mSingleActionList = ggs.c(this.mFitWorkout);
            }
            if (koq.c(this.mSingleActionList)) {
                initRecordActions();
            }
        }
        if (ffdVar == null || this.mFitWorkout == null) {
            ReleaseLogUtil.c(TAG, "target is ", ffdVar, "mFitWorkout is ", this.mFitWorkout);
            return;
        }
        int c = ffdVar.c() - 1;
        updateRecordActions(c, ffdVar);
        updateHeartRateArea(c, ffdVar);
    }

    private void initRecordActions() {
        LogUtil.d(TAG, "initRecordActions ");
        for (ChoreographedSingleAction choreographedSingleAction : this.mSingleActionList) {
            if (choreographedSingleAction == null || choreographedSingleAction.getAction() == null || choreographedSingleAction.getTargetConfig() == null) {
                ReleaseLogUtil.c(TAG, "singleAction error in initRecordActions");
                this.mRecordActions.add(new RecordAction("", "", 0, 0.0f, 1));
            } else {
                String name = choreographedSingleAction.getAction().getName();
                this.mRecordActions.add(new RecordAction(choreographedSingleAction.getAction().getId(), name, 0, (float) choreographedSingleAction.getTargetConfig().getValueL(), choreographedSingleAction.getTargetConfig().getValueType()));
            }
        }
    }

    private void initWorkout() {
        if (this.mFitWorkout == null) {
            LogUtil.e(TAG, "mFitWorkout is null in initWorkout");
            return;
        }
        if (this.mWorkoutRecord != null) {
            LogUtil.c(TAG, "mWorkoutRecord success in initWorkout");
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        this.mWorkoutRecord = workoutRecord;
        workoutRecord.saveWorkoutId(this.mFitWorkout.acquireId());
        this.mWorkoutRecord.saveWorkoutName(this.mFitWorkout.acquireName());
        this.mWorkoutRecord.putExtendProperty("difficulty", Integer.toString(this.mFitWorkout.acquireDifficulty()));
        this.mWorkoutRecord.putExtendProperty("courseDuration", Integer.toString(this.mFitWorkout.acquireDuration()));
        this.mWorkoutRecord.setSportRecordType(1);
        this.mWorkoutRecord.saveWorkoutDate(ghz.a(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), "yyyy-MM-dd"));
        if (TextUtils.isEmpty(this.mFitWorkout.accquireVersion())) {
            this.mWorkoutRecord.saveVersion("2.0");
        } else {
            this.mWorkoutRecord.saveVersion(this.mFitWorkout.accquireVersion());
        }
    }

    private void updateRecordActions(int i, ffd ffdVar) {
        RecordAction recordAction;
        if (!koq.d(this.mRecordActions, i) || (recordAction = this.mRecordActions.get(i)) == null) {
            return;
        }
        recordAction.setFinishRate(ffdVar.a() * 100.0f);
        recordAction.setFinishedAction((int) (ffdVar.j() - ffdVar.d()));
        if (recordAction.getFinishedAction() > recordAction.getActionTargetValue()) {
            recordAction.setFinishedAction((int) recordAction.getActionTargetValue());
        }
        setActionToWorkout();
    }

    private void updateHeartRateArea(int i, ffd ffdVar) {
        float f;
        if (koq.d(this.mSingleActionList, i)) {
            ChoreographedSingleAction choreographedSingleAction = this.mSingleActionList.get(i);
            if (choreographedSingleAction == null) {
                ReleaseLogUtil.c(TAG, "action is null in updateHeartRateArea");
                return;
            }
            int valueL = choreographedSingleAction.getIntensityConfig() != null ? (int) choreographedSingleAction.getIntensityConfig().getValueL() : 0;
            int valueH = choreographedSingleAction.getIntensityConfig() != null ? (int) choreographedSingleAction.getIntensityConfig().getValueH() : 0;
            float d = ffdVar.d();
            float j = ffdVar.j();
            float valueL2 = choreographedSingleAction.getTargetConfig() != null ? (float) choreographedSingleAction.getTargetConfig().getValueL() : Float.MAX_VALUE;
            if (j - d > valueL2) {
                LogUtil.c(TAG, "current is ", Float.valueOf(j), " start is ", Float.valueOf(d), "target is ", Float.valueOf(valueL2));
                f = valueL2 + d;
            } else {
                f = j;
            }
            kny knyVar = new kny(i + 1, d, valueH, f, valueL);
            if (koq.d(this.mHeartRateAreaList, i)) {
                this.mHeartRateAreaList.set(i, knyVar);
            } else {
                this.mHeartRateAreaList.add(i, knyVar);
            }
            Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
            if (data instanceof MotionPath) {
                ArrayList arrayList = new ArrayList(this.mHeartRateAreaList);
                LogUtil.d(TAG, "HeartRateAreaData list.size", Integer.valueOf(arrayList.size()));
                MotionPath motionPath = (MotionPath) data;
                motionPath.setHeartRateAreaList(arrayList);
                BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
            }
        }
    }

    private void saveRealDataToWorkout() {
        if (this.mFitWorkout == null) {
            LogUtil.c(TAG, "mFitWorkout is null in saveRealDataToWorkout");
            return;
        }
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            setSportDataToWorkout(motionPathSimplify);
            motionPathSimplify.saveRunCourseId(this.mFitWorkout.acquireId());
            motionPathSimplify.addExtendDataMap("courseName", this.mFitWorkout.acquireName());
            motionPathSimplify.addExtendDataMap("extra_Sport_Type", String.valueOf(1));
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    private void setSportDataToWorkout(MotionPathSimplify motionPathSimplify) {
        if (this.mWorkoutRecord == null) {
            LogUtil.c(TAG, "mWorkoutRecord is null in setSportDataToWorkout");
            return;
        }
        synchronized (LOCK) {
            this.mWorkoutRecord.setStartTime(motionPathSimplify.requestStartTime());
            this.mWorkoutRecord.setDuration((int) motionPathSimplify.requestTotalTime());
            this.mWorkoutRecord.saveCalorie(motionPathSimplify.requestTotalCalories());
            this.mWorkoutRecord.saveExerciseTime(motionPathSimplify.requestEndTime());
            this.mWorkoutRecord.saveActualDistance(moe.d(motionPathSimplify.requestTotalDistance()));
            this.mWorkoutRecord.saveActualCalorie(motionPathSimplify.requestTotalCalories());
            this.mWorkoutRecord.saveTrajectoryId(motionPathSimplify.requestStartTime() + "_" + motionPathSimplify.requestEndTime());
            saveDataToSp();
        }
    }

    private void setActionToWorkout() {
        if (this.mWorkoutRecord == null) {
            LogUtil.c(TAG, "mWorkoutRecord is null in setActionToWorkout");
            return;
        }
        synchronized (LOCK) {
            this.mWorkoutRecord.saveActionSummary(new Gson().toJson(this.mRecordActions));
            this.mWorkoutRecord.saveFinishRate(getFinishRate());
            saveDataToSp();
        }
    }

    private void updateWorkoutRecordData() {
        WorkoutRecord workoutRecord = this.mWorkoutRecord;
        if (workoutRecord == null) {
            LogUtil.c(TAG, "mWorkoutRecord is null in updateWorkoutRecordData");
            return;
        }
        LogUtil.d(TAG, "saveWorkoutRecordData", workoutRecord);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.c(TAG, "planApi is null.");
        } else {
            planApi.updatePlanProgress(this.mWorkoutRecord);
        }
    }

    private float getFinishRate() {
        Iterator<RecordAction> it = this.mRecordActions.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f += it.next().getFinishRate();
        }
        return f / this.mSingleActionList.size();
    }

    private void saveDataToSp() {
        if (this.mWorkoutRecord == null) {
            LogUtil.c(TAG, "saveDataToSp mWorkoutRecord == null");
            return;
        }
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeSpecialFloatingPointValues();
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), WORKOUT_TMP, gsonBuilder.create().toJson(this.mWorkoutRecord), (StorageParams) null);
        } catch (ConcurrentModificationException e) {
            ReleaseLogUtil.c(TAG, "saveDataToSp ConcurrentModificationException:", LogAnonymous.b((Throwable) e));
        }
    }

    private WorkoutRecord getDataFromSp() {
        return (WorkoutRecord) nrv.b(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(20002), WORKOUT_TMP), WorkoutRecord.class);
    }

    private void clearTmpData() {
        SharedPreferenceManager.c(BaseApplication.e(), Integer.toString(20002), WORKOUT_TMP);
    }
}
