package com.huawei.health.sportservice.manager;

import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.dataproducer.SportDataStorageManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.pluginfitnessadvice.AIActionBundle;
import defpackage.ffd;
import defpackage.ghz;
import defpackage.mmo;
import defpackage.mmx;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SportComponentType(classify = 1, name = ComponentName.DATA_STORAGE_MANAGER)
/* loaded from: classes4.dex */
public class TrainDataStorageManager extends SportDataStorageManager implements ITargetUpdateListener {
    private static final int AI_MEASURE_TYPE_NUMBER = 2;
    private static final int AI_MEASURE_TYPE_TIME = 1;
    private static final int ONE_HUNDRED_VALUE = 100;
    private static final int ONE_THOUSAND_UNIT = 1000;
    private static final String TAG = "TrainDataStorageManager";
    private int mTargetType = -1;
    private float mTargetValue = 0.0f;
    private AIActionBundle mAiActionBundle = null;
    private long mStartTime = 0;
    private CourseApi mCourseApi = null;
    private WorkoutRecord mWorkoutRecord = null;

    public TrainDataStorageManager() {
        LogUtil.d(TAG, "TrainDataStorageManager()");
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        super.setParas(sportParamsType, obj);
        LogUtil.d(TAG, "setParas");
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            this.mTargetType = sportLaunchParams.getSportTarget();
            this.mTargetValue = sportLaunchParams.getTargetValue();
            this.mAiActionBundle = (AIActionBundle) sportLaunchParams.getExtra("AI_ACTION_BUNDLE", AIActionBundle.class);
        }
        this.mCourseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        this.mWorkoutRecord = new WorkoutRecord();
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
        LogUtil.d(TAG, "onStartSport");
        this.mStartTime = System.currentTimeMillis();
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.d(TAG, "onStopSport");
        if (!isToSave()) {
            LogUtil.d(TAG, "onStopSport, not save data");
        } else {
            saveWorkoutRecord();
        }
    }

    private int getValidDataType() {
        return this.mAiActionBundle.getAiMeasurement() == 1 ? 1 : 2;
    }

    private HashMap<Integer, Integer> getResultCodeMap() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Object data = BaseSportManager.getInstance().getData("AI_TRAIN_RESULT_CODE_LIST");
        if (!(data instanceof HashMap)) {
            return hashMap;
        }
        HashMap<Integer, Integer> hashMap2 = (HashMap) data;
        LogUtil.d(TAG, "getResultCodeList ", hashMap2);
        return hashMap2;
    }

    private float getCalorie() {
        Object data = BaseSportManager.getInstance().getData("CALORIES_DATA");
        if (!(data instanceof Integer)) {
            return 0.0f;
        }
        LogUtil.d(TAG, "calorie ", data);
        return ((Integer) data).intValue() * 1000;
    }

    private float getActiveCalorie() {
        Object data = BaseSportManager.getInstance().getData("ACTIVE_CALORIES_DATA");
        if (!(data instanceof Integer)) {
            return 0.0f;
        }
        LogUtil.d(TAG, "active calorie ", data);
        return ((Integer) data).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getValidTimes() {
        Object data = BaseSportManager.getInstance().getData("AI_TRAIN_VALID_TIMES");
        if (!(data instanceof Integer)) {
            return 0;
        }
        LogUtil.d(TAG, "valid times ", data);
        return ((Integer) data).intValue();
    }

    private long getSportTime() {
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        if (!(data instanceof Long)) {
            return 0L;
        }
        LogUtil.d(TAG, "sport time ", data);
        return ((Long) data).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryAndSetCloudData() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        getFitnessMaxScore(countDownLatch);
        getUserRank(countDownLatch);
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c(TAG, "queryAndSetCloudData InterruptedException");
        }
        LogUtil.d(TAG, "mWorkoutRecord: ", this.mWorkoutRecord);
        saveWorkoutRecordData(this.mWorkoutRecord);
    }

    private void getFitnessMaxScore(final CountDownLatch countDownLatch) {
        LogUtil.d(TAG, "getFitnessMaxScore start");
        this.mWorkoutRecord.setIsRewRecord(false);
        this.mCourseApi.getFitnessMaxScore(this.mAiActionBundle.getActionId(), 1, new UiCallback<mmo>() { // from class: com.huawei.health.sportservice.manager.TrainDataStorageManager.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.e(TrainDataStorageManager.TAG, "onFailure errorCode ", Integer.valueOf(i), " errorInfo ", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(mmo mmoVar) {
                if (mmoVar != null) {
                    TrainDataStorageManager.this.mWorkoutRecord.setIsRewRecord(((long) TrainDataStorageManager.this.getValidTimes()) > mmoVar.e());
                }
                LogUtil.d(TrainDataStorageManager.TAG, "getFitnessMaxScore onSuccess data ", new Gson().toJson(mmoVar));
                countDownLatch.countDown();
            }
        });
    }

    private void getUserRank(final CountDownLatch countDownLatch) {
        LogUtil.d(TAG, "getUserRank start");
        this.mWorkoutRecord.setCurRank(0);
        this.mCourseApi.getUserRank("aiFitnessSectionScore", this.mAiActionBundle.getActionId(), getValidTimes(), System.currentTimeMillis(), new UiCallback<mmx>() { // from class: com.huawei.health.sportservice.manager.TrainDataStorageManager.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.e(TrainDataStorageManager.TAG, "getUserRank, onFailure");
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(mmx mmxVar) {
                LogUtil.d(TrainDataStorageManager.TAG, "getUserRank, onSuccess, data ", new Gson().toJson(mmxVar));
                if (mmxVar != null) {
                    TrainDataStorageManager.this.mWorkoutRecord.setCurRank(mmxVar.c());
                }
                countDownLatch.countDown();
            }
        });
    }

    private void saveWorkoutRecord() {
        this.mWorkoutRecord.saveWorkoutName(this.mAiActionBundle.getActionName());
        this.mWorkoutRecord.setTargetType(this.mTargetType);
        this.mWorkoutRecord.setTargetValue((int) this.mTargetValue);
        this.mWorkoutRecord.saveWorkoutId(this.mAiActionBundle.getActionId());
        this.mWorkoutRecord.setStartTime(this.mStartTime);
        this.mWorkoutRecord.saveTotalScore(getValidTimes());
        this.mWorkoutRecord.setTrainMeasureType(getValidDataType());
        this.mWorkoutRecord.setTrainMeasureValue(getValidTimes());
        this.mWorkoutRecord.setPerfectTimes(getResultCodeMap().containsKey(2) ? getResultCodeMap().get(2).intValue() : 0);
        this.mWorkoutRecord.setGoodTimes(getResultCodeMap().containsKey(1) ? getResultCodeMap().get(1).intValue() : 0);
        this.mWorkoutRecord.setExcellentTimes(getResultCodeMap().containsKey(0) ? getResultCodeMap().get(0).intValue() : 0);
        this.mWorkoutRecord.setDuration((int) getSportTime());
        this.mWorkoutRecord.saveActualCalorie(getCalorie());
        this.mWorkoutRecord.setActiveCalorie(getActiveCalorie());
        this.mWorkoutRecord.saveRecordModeType(2);
        this.mWorkoutRecord.saveWorkoutDate(ghz.a(System.currentTimeMillis() / 1000, "yyyy-MM-dd"));
        this.mWorkoutRecord.saveExerciseTime(System.currentTimeMillis());
        this.mWorkoutRecord.saveFinishRate(getFinishRate());
        this.mWorkoutRecord.saveActionSummary(new Gson().toJson(getActionSummary()));
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.manager.TrainDataStorageManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TrainDataStorageManager.this.queryAndSetCloudData();
            }
        });
    }

    private int getFinishTargetValue() {
        if (this.mAiActionBundle.getTargetType() == 1 && this.mAiActionBundle.getAiMeasurement() == 10) {
            return (int) (getSportTime() / 1000);
        }
        return getValidTimes();
    }

    private float getFinishRate() {
        float validTimes;
        float f;
        if (Float.compare(this.mTargetValue, 0.0f) == 0) {
            return 100.0f;
        }
        if (this.mTargetType == 0) {
            validTimes = getSportTime() / 1000.0f;
            f = this.mTargetValue;
        } else {
            validTimes = getValidTimes();
            f = this.mTargetValue;
        }
        return (validTimes / f) * 100.0f;
    }

    private List<RecordAction> getActionSummary() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new RecordAction(this.mAiActionBundle.getActionId(), this.mAiActionBundle.getActionName(), getFinishTargetValue(), this.mTargetValue, this.mAiActionBundle.getTargetType()));
        return arrayList;
    }

    private void saveWorkoutRecordData(WorkoutRecord workoutRecord) {
        LogUtil.d(TAG, "saveWorkoutRecordData");
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.c(TAG, "planApi is null.");
        } else {
            planApi.updatePlanProgress(workoutRecord);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        super.destroy();
        LogUtil.d(TAG, "destroy");
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager
    public boolean isToSave() {
        return getValidTimes() > 0;
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        LogUtil.d(TAG, "onTargetDataUpdate curProgress ", Integer.valueOf((int) (ffdVar.a() * 100.0f)));
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        LogUtil.d(TAG, "type is ", Integer.valueOf(i), " command is ", str);
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager
    public boolean isStorageData(String str) {
        return "AI_TRAIN_WORKOUT_RECORD".equals(str);
    }

    @Override // com.huawei.health.sportservice.dataproducer.SportDataStorageManager
    public Object getData(String str) {
        if ("AI_TRAIN_WORKOUT_RECORD".equals(str)) {
            return this.mWorkoutRecord;
        }
        LogUtil.c(TAG, "get storage data with error tag:", str);
        return null;
    }
}
