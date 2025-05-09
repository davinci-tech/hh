package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RidePower;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.SkippingSpeed;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StepRateData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackAltitudeData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackPullFreqData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackSpeedData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackSwolfData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseDeviceUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseRunPlanUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.TriathlonUtils;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.ffr;
import defpackage.ffs;
import defpackage.jrq;
import defpackage.jsz;
import defpackage.kkm;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseAdviceManagerHelper {
    private static final int CADENCE_DENOMINATOR = 2;
    private static final int ERROR = -1;
    private static final float LESS_VALUE = 0.5f;
    private static final Object LOCK_OBJECT = new Object();
    private static final int MAX_VALUE = 255;
    private static final float MINUTE = 60.0f;
    private static final float NUMBER = 5.0f;
    private static final String TAG = "HwExerciseAdviceManagerHelper";
    private static final String TAG_RELEASE = "BTSYNC_HwExerciseAdviceManagerHelper";
    private static final String TAG_TRUST_HEART_RATE = "1";
    private static HwExerciseAdviceManagerHelper sInstance;
    private HwExerciseParams mParam = HwExerciseParams.getInstance();
    private HwExerciseAdviceManager mHwExerciseAdviceManager = HwExerciseAdviceManager.getInstance();

    public static HwExerciseAdviceManagerHelper getInstance() {
        HwExerciseAdviceManagerHelper hwExerciseAdviceManagerHelper;
        synchronized (LOCK_OBJECT) {
            if (sInstance == null) {
                sInstance = new HwExerciseAdviceManagerHelper();
            }
            hwExerciseAdviceManagerHelper = sInstance;
        }
        return hwExerciseAdviceManagerHelper;
    }

    public DeviceCapability getCapability() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", TAG);
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return filterCapability(queryDeviceCapability);
    }

    public void saveSuccessUpdateSuccessList(WorkoutSyncSuccessDetailData workoutSyncSuccessDetailData) {
        synchronized (this) {
            if (workoutSyncSuccessDetailData == null) {
                ReleaseLogUtil.d(TAG_RELEASE, "saveSuccessUpdateSuccessList successRecord is null.");
                return;
            }
            ReleaseLogUtil.e(TAG_RELEASE, "saveSuccessUpdateSuccessList workoutId:", Integer.valueOf(workoutSyncSuccessDetailData.getWorkoutId()), ", startTime:", Long.valueOf(workoutSyncSuccessDetailData.getStartTime()));
            List<WorkoutSyncSuccessDetailData> list = this.mParam.getSucceedRecordMap().get(HwExerciseUtils.getRecordKey(HwExerciseDeviceUtil.getCurrentDeviceId()));
            if (list == null) {
                list = new ArrayList<>(16);
            }
            WorkoutSyncSuccessDetailData successRecord = getSuccessRecord(workoutSyncSuccessDetailData);
            if (successRecord != null) {
                list.remove(successRecord);
            }
            list.add(workoutSyncSuccessDetailData);
            ReleaseLogUtil.e(TAG_RELEASE, "saveSuccessUpdateSuccessList succeedList:", list.toString());
            this.mParam.getSucceedRecordMap().put(HwExerciseUtils.getRecordKey(HwExerciseDeviceUtil.getCurrentDeviceId()), list);
            this.mHwExerciseAdviceManager.setSharedPreference(HwExerciseUtils.getRecordKey(HwExerciseDeviceUtil.getCurrentDeviceId()), HiJsonUtil.e(list), null);
        }
    }

    public WorkoutSyncSuccessDetailData getSuccessRecord(WorkoutSyncSuccessDetailData workoutSyncSuccessDetailData) {
        List<WorkoutSyncSuccessDetailData> list = this.mParam.getSucceedRecordMap().get(HwExerciseUtils.getRecordKey(HwExerciseDeviceUtil.getCurrentDeviceId()));
        if (list == null) {
            LogUtil.h(TAG, "getSuccessRecord succeedList is null.");
            return null;
        }
        for (WorkoutSyncSuccessDetailData workoutSyncSuccessDetailData2 : list) {
            if (workoutSyncSuccessDetailData2 != null && workoutSyncSuccessDetailData2.getWorkoutId() == workoutSyncSuccessDetailData.getWorkoutId() && workoutSyncSuccessDetailData2.getStartTime() == workoutSyncSuccessDetailData.getStartTime()) {
                return workoutSyncSuccessDetailData2;
            }
        }
        return null;
    }

    public void notifySaveData(final MotionPathSimplify motionPathSimplify, final MotionPath motionPath, final int i, final TriathlonUtils triathlonUtils, final HwExerciseAdviceManager hwExerciseAdviceManager) {
        jrq.b(TAG, new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManagerHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (HwExerciseAdviceManagerHelper.this.mParam.getCurrentDeviceId().equals(HwExerciseDeviceUtil.getCurrentDeviceId())) {
                    motionPathSimplify.setWorkoutTrajectories(HwExerciseAdviceManagerHelper.this.mParam.getWorkoutTrajectories());
                    motionPathSimplify.setDivingEvent(HwExerciseAdviceManagerHelper.this.mParam.getDivingEvent());
                    HwExerciseAdviceManagerHelper.this.mHwExerciseAdviceManager.setIsBeingSavedNewData(true);
                    HwExerciseUtils.saveTrackData(motionPathSimplify, motionPath, i, triathlonUtils, hwExerciseAdviceManager);
                }
            }
        });
    }

    public void saveDataToTrack(JSONObject jSONObject, JSONArray jSONArray, Map<Long, double[]> map, JSONArray jSONArray2, Map<String, Integer> map2) {
        int intValue = map2.get(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE).intValue();
        int intValue2 = map2.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID).intValue();
        MotionPath motionPath = new MotionPath();
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        HwExerciseUtils.setMapType(intValue, motionPathSimplify, map);
        if (map != null) {
            motionPath.setLbsDataMap(map);
        }
        try {
            int i = (int) jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DURATION);
            Map hashMap = new HashMap(16);
            Map hashMap2 = new HashMap(16);
            ReleaseLogUtil.e(TAG_RELEASE, "saveDataToTrack paceArray");
            if (jSONArray2 != null) {
                Map<Integer, Float> changePaceMapStruct = HwExerciseUtils.changePaceMapStruct(jSONArray2, 0);
                motionPath.setPaceMap(changePaceMapStruct);
                motionPathSimplify.setPaceMap(changePaceMapStruct);
                Map<Integer, Float> changePaceMapStruct2 = HwExerciseUtils.changePaceMapStruct(jSONArray2, 1);
                motionPath.setBritishPaceMap(changePaceMapStruct2);
                motionPathSimplify.setBritishPaceMap(changePaceMapStruct2);
                hashMap = HwExerciseUtils.changePartTimePaceMapStruct(jSONArray2, 0);
                hashMap2 = HwExerciseUtils.changePartTimePaceMapStruct(jSONArray2, 1);
            }
            HwExerciseUtils.handleSaveBaseInfo(jSONObject, motionPathSimplify, i, hashMap, hashMap2);
            packTrackData(jSONArray, motionPath, motionPathSimplify, jSONObject);
            HwExerciseRunPlanUtil.handleSaveWorkoutDisplayInfo(map, motionPathSimplify);
            HwExerciseUtils.handleSaveSimplifyBaseInfo(jSONObject, motionPathSimplify);
            HwExerciseUtils.handleSaveAltitudeData(jSONObject, motionPathSimplify);
            motionPathSimplify.setSportData(HwExerciseUtils.handleSaveSportDataMap(jSONObject, motionPathSimplify));
            motionPathSimplify.setIsWorkout(false);
        } catch (JSONException unused) {
            ReleaseLogUtil.c(TAG_RELEASE, "saveXxxxx json Exception.");
        }
        notifySaveData(motionPathSimplify, motionPath, intValue2, this.mParam.getTriathlonUtils(), this.mHwExerciseAdviceManager);
        ReleaseLogUtil.e(TAG_RELEASE, "save runPlan Record DatatoTrack finish");
    }

    public void packTrackData(JSONArray jSONArray, MotionPath motionPath, MotionPathSimplify motionPathSimplify, JSONObject jSONObject) {
        HwExerciseAdviceBean hwExerciseAdviceBean = new HwExerciseAdviceBean();
        parseJson(jSONArray, motionPathSimplify, jSONObject, hwExerciseAdviceBean);
        LogUtil.a(TAG, "trackSummaryTotalStep: ", Integer.valueOf(hwExerciseAdviceBean.getTrackSummaryTotalStep()), "trackTotalStep: ", Integer.valueOf(hwExerciseAdviceBean.getTrackTotalStep()), " totaltime: ", Long.valueOf((hwExerciseAdviceBean.getTrackTotalTime() / 1000) / 60));
        detailLessValue(hwExerciseAdviceBean);
        buildDetailValue(motionPath, jSONObject, hwExerciseAdviceBean);
        isNotworkType(motionPath, jSONObject, hwExerciseAdviceBean);
        motionPath.saveRunningPostureList(hwExerciseAdviceBean.getPostureDataList());
        motionPath.setExtraDataList(hwExerciseAdviceBean.getExtraDataList());
        motionPathSimplify.setSportId(hwExerciseAdviceBean.getSportId());
        motionPathSimplify.setSportType(258);
        speedValue(motionPathSimplify, hwExerciseAdviceBean);
        DeviceCapability capability = getCapability();
        if (hwExerciseAdviceBean.getTrackHeartTotalCount() == 0) {
            motionPathSimplify.setAvgHeartRate(0);
        } else {
            if (capability != null && capability.isSupportWorkoutTrustHeartRate()) {
                hwExerciseAdviceBean.setTrackTotalHeart(calculateTotalHeartRate(motionPath.getHeartRateList()));
                hwExerciseAdviceBean.setTrackHeartTotalCount(motionPath.getHeartRateList().size());
            }
            if (hwExerciseAdviceBean.getTrackHeartTotalCount() == 0) {
                motionPathSimplify.setAvgHeartRate(0);
            } else {
                motionPathSimplify.setAvgHeartRate((int) (hwExerciseAdviceBean.getTrackTotalHeart() / hwExerciseAdviceBean.getTrackHeartTotalCount()));
            }
        }
        LogUtil.c(TAG, "hwExerciseAdviceUtil.getTrackTotalHeart: ", Long.valueOf(hwExerciseAdviceBean.getTrackTotalHeart()), " avgHear: ", Integer.valueOf(motionPathSimplify.getAvgHeartRate()));
        int trackTotalStep = (int) ((hwExerciseAdviceBean.getTrackTotalStep() / (hwExerciseAdviceBean.getTrackTotalCount() * 5.0f)) * 60.0f);
        motionPathSimplify.setAvgStepRate(trackTotalStep);
        motionPathSimplify.addExtendDataMap("crossTrainerCadence", Integer.toString(motionPathSimplify.getAvgStepRate() / 2));
        motionPathSimplify.setBestStepRate(hwExerciseAdviceBean.getTrackMaxStep());
        LogUtil.c(TAG, "bestStep: ", Integer.valueOf(hwExerciseAdviceBean.getTrackMaxStep()), " avgStep: ", Integer.valueOf(trackTotalStep));
        motionPathSimplify.setTrackType(kkm.d(cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, TAG)));
    }

    private DeviceCapability filterCapability(Map<String, DeviceCapability> map) {
        Iterator<Map.Entry<String, DeviceCapability>> it = map.entrySet().iterator();
        DeviceCapability deviceCapability = null;
        while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
        }
        return deviceCapability;
    }

    private void dataValidValue(JSONArray jSONArray, JSONObject jSONObject, MotionPathSimplify motionPathSimplify, HwExerciseAdviceBean hwExerciseAdviceBean) throws JSONException {
        HwExerciseAdviceData hwExerciseAdviceData = new HwExerciseAdviceData(jSONObject);
        hwExerciseAdviceData.setDeviceCapability(getCapability());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            LogUtil.a(TAG, "dataValidValue data:", jSONObject2.toString());
            long j = jSONObject2.getJSONObject(HwExerciseConstants.JSON_NAME_DATA_HEADER).getLong("time");
            hwExerciseAdviceData.setTime(j);
            int i2 = jSONObject2.getJSONObject(HwExerciseConstants.JSON_NAME_DATA_HEADER).getInt(HwExerciseConstants.JSON_NAME_TIME_INTERVAL);
            hwExerciseAdviceData.setTimeInterval(i2);
            LogUtil.c(TAG, "timeInterval: ", Integer.valueOf(i2), " time: ", Long.valueOf(j), " detailId: ", Integer.valueOf(jSONObject2.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)), " index: ", Integer.valueOf(jSONObject2.getInt(HwExerciseConstants.WORKOUT_DATA_INDEX)));
            int length2 = jSONObject2.getJSONObject(HwExerciseConstants.JSON_NAME_DATA_HEADER).getJSONArray(HwExerciseConstants.JSON_NAME_INFO_LIST).length();
            hwExerciseAdviceData.setDataCount(length2);
            JSONArray jSONArray2 = jSONObject2.getJSONObject(HwExerciseConstants.JSON_NAME_DATA_HEADER).getJSONArray(HwExerciseConstants.JSON_NAME_CAPACITY);
            hwExerciseAdviceBean.setTrackTotalCount(hwExerciseAdviceBean.getTrackTotalCount() + length2);
            exerciseData(jSONObject2, hwExerciseAdviceData, motionPathSimplify, jSONArray2, hwExerciseAdviceBean);
        }
    }

    private void parseJson(JSONArray jSONArray, MotionPathSimplify motionPathSimplify, JSONObject jSONObject, HwExerciseAdviceBean hwExerciseAdviceBean) {
        LogUtil.a(TAG, "the size is ", Integer.valueOf(jSONArray.length()));
        try {
            hwExerciseAdviceBean.setTrackSummaryTotalStep(motionPathSimplify.getTotalSteps());
            hwExerciseAdviceBean.setTrackTotalTime(motionPathSimplify.getTotalTime());
            dataValidValue(jSONArray, jSONObject, motionPathSimplify, hwExerciseAdviceBean);
            if (hwExerciseAdviceBean.isSupportNewStep() || hwExerciseAdviceBean.getTrackMinuteStepCount() % 12 == 0) {
                return;
            }
            StepRateData stepRateData = new StepRateData();
            stepRateData.setTime(hwExerciseAdviceBean.getTrackMinuteStepTimestamp());
            if (hwExerciseAdviceBean.getTrackMinuteStep() >= hwExerciseAdviceBean.getTrackMaxStep()) {
                stepRateData.setStepRate(hwExerciseAdviceBean.getTrackMinuteStep());
                hwExerciseAdviceBean.getStepList().add(stepRateData);
                hwExerciseAdviceBean.setTrackMaxStep(hwExerciseAdviceBean.getTrackMinuteStep());
                hwExerciseAdviceBean.setStepLastIndex(hwExerciseAdviceBean.getStepList().size() - 1);
            } else {
                completionStep(hwExerciseAdviceBean, stepRateData);
            }
            LogUtil.c(TAG, "time: ", Long.valueOf(hwExerciseAdviceBean.getTrackMinuteStepTimestamp()), "trackMinuteStep: ", Integer.valueOf(hwExerciseAdviceBean.getTrackMinuteStep()));
        } catch (JSONException unused) {
            ReleaseLogUtil.c(TAG_RELEASE, "packXxxx json Exception.");
        }
    }

    private void completionStep(HwExerciseAdviceBean hwExerciseAdviceBean, StepRateData stepRateData) {
        int trackMinuteStepCount = hwExerciseAdviceBean.getTrackMinuteStepCount() % 12;
        hwExerciseAdviceBean.setTrackMinuteStep((hwExerciseAdviceBean.getTrackMinuteStep() * 12) / trackMinuteStepCount);
        LogUtil.a(TAG, "packTrackData last one lastStepCount:", Integer.valueOf(trackMinuteStepCount));
        if (hwExerciseAdviceBean.getTrackMinuteStep() > hwExerciseAdviceBean.getTrackMaxStep()) {
            hwExerciseAdviceBean.setTrackMinuteStep(hwExerciseAdviceBean.getStepList().get(hwExerciseAdviceBean.getStepList().size() - 1).getStepRate());
        }
        stepRateData.setStepRate(hwExerciseAdviceBean.getTrackMinuteStep());
        hwExerciseAdviceBean.getStepList().add(stepRateData);
    }

    private void parseStorage(MotionPath motionPath, JSONObject jSONObject) {
        if (jSONObject.has(HwExerciseConstants.JSON_NAME_RECOVERY_HEART_RATE)) {
            try {
                ArrayList<HeartRateData> arrayList = (ArrayList) new Gson().fromJson(jSONObject.get(HwExerciseConstants.JSON_NAME_RECOVERY_HEART_RATE).toString(), new TypeToken<ArrayList<HeartRateData>>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManagerHelper.2
                }.getType());
                LogUtil.a(TAG, "heartList = ", arrayList.toString());
                motionPath.setRecoveryHeartRateList(arrayList);
            } catch (JsonSyntaxException unused) {
                ReleaseLogUtil.c(TAG_RELEASE, "fromJson parse fail json parse exception");
            } catch (JSONException e) {
                ReleaseLogUtil.c(TAG_RELEASE, "fromJson parse fail JSONException:", ExceptionUtils.d(e));
            }
        }
    }

    private void buildDetailValue(MotionPath motionPath, JSONObject jSONObject, HwExerciseAdviceBean hwExerciseAdviceBean) {
        handleHeartRateList(getCapability(), motionPath, hwExerciseAdviceBean.getHeartRateList(), hwExerciseAdviceBean.getInvalidHeartRateList());
        motionPath.setAltitudeList(hwExerciseAdviceBean.getAltitudeList());
        motionPath.setSwolfList(hwExerciseAdviceBean.getSwolfList());
        motionPath.setPullFreqList(hwExerciseAdviceBean.getPullFrequentList());
        motionPath.setSpeedList(hwExerciseAdviceBean.getSpeedList());
        motionPath.setSkippingSpeedList(hwExerciseAdviceBean.getFrequencyList());
        if (hwExerciseAdviceBean.getPowerAccount() == 0) {
            hwExerciseAdviceBean.getRidePowerList().clear();
        }
        motionPath.setRidePowerList(hwExerciseAdviceBean.getRidePowerList());
        LogUtil.a(TAG, "ridePowerList is:", hwExerciseAdviceBean.getRidePowerList().toString());
        parseStorage(motionPath, jSONObject);
    }

    private void detailLessValue(HwExerciseAdviceBean hwExerciseAdviceBean) {
        if (hwExerciseAdviceBean.getTrackSummaryTotalStep() <= hwExerciseAdviceBean.getTrackTotalStep() || hwExerciseAdviceBean.getTrackSummaryTotalStep() - hwExerciseAdviceBean.getTrackTotalStep() <= (hwExerciseAdviceBean.getTrackTotalTime() / 1000) / 60) {
            return;
        }
        int trackSummaryTotalStep = (int) (((hwExerciseAdviceBean.getTrackSummaryTotalStep() - hwExerciseAdviceBean.getTrackTotalStep()) / hwExerciseAdviceBean.getStepList().size()) + 0.5f);
        Iterator<StepRateData> it = hwExerciseAdviceBean.getStepList().iterator();
        while (it.hasNext()) {
            StepRateData next = it.next();
            next.setStepRate(next.getStepRate() + trackSummaryTotalStep);
        }
        if (hwExerciseAdviceBean.getStepList().size() > hwExerciseAdviceBean.getStepLastIndex()) {
            hwExerciseAdviceBean.setTrackMaxStep(hwExerciseAdviceBean.getStepList().get(hwExerciseAdviceBean.getStepLastIndex()).getStepRate());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void isNotworkType(com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath r5, org.json.JSONObject r6, com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceBean r7) {
        /*
            r4 = this;
            java.lang.String r0 = "workout_type"
            boolean r1 = r6.has(r0)
            java.lang.String r2 = "BTSYNC_HwExerciseAdviceManagerHelper"
            if (r1 == 0) goto L19
            int r6 = r6.getInt(r0)     // Catch: org.json.JSONException -> L10
            goto L1a
        L10:
            java.lang.String r6 = "get int type JSONException"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r2, r6)
        L19:
            r6 = -1
        L1a:
            com.huawei.hwdevice.phoneprocess.mgr.exercise.HwWorkoutServiceManager r0 = com.huawei.hwdevice.phoneprocess.mgr.exercise.HwWorkoutServiceManager.getInstance()
            boolean r0 = r0.getIsSupportNewEllipticalAndRowingMachine()
            r1 = 135(0x87, float:1.89E-43)
            if (r0 == 0) goto L2f
            if (r6 != r1) goto L2f
            java.util.ArrayList r3 = r7.getStepList()
            r5.setPaddleRateList(r3)
        L2f:
            r3 = 3
            if (r6 == r3) goto L35
            r3 = 7
            if (r6 != r3) goto L3c
        L35:
            java.util.ArrayList r3 = r7.getCadenceList()
            r5.setCadenceRateList(r3)
        L3c:
            r3 = 134(0x86, float:1.88E-43)
            if (r0 == 0) goto L68
            if (r6 != r3) goto L68
            int r0 = r7.getTrackTotalStep()
            if (r0 != 0) goto L4f
            java.util.ArrayList r0 = r7.getStepList()
            r0.clear()
        L4f:
            java.util.ArrayList r0 = r7.getStepList()
            r5.setStepRateList(r0)
            java.util.ArrayList r0 = r7.getStepList()
            java.util.ArrayList r0 = r4.stepToCadence(r0)
            r7.setCadenceList(r0)
            java.util.ArrayList r0 = r7.getCadenceList()
            r5.setCadenceRateList(r0)
        L68:
            if (r6 == r1) goto L85
            if (r6 == r3) goto L85
            r0 = 255(0xff, float:3.57E-43)
            if (r6 == r0) goto L85
            int r6 = r7.getTrackTotalStep()
            if (r6 != 0) goto L7d
            java.util.ArrayList r6 = r7.getStepList()
            r6.clear()
        L7d:
            java.util.ArrayList r6 = r7.getStepList()
            r5.setStepRateList(r6)
            goto L8f
        L85:
            java.lang.String r5 = "stepList other workType"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r5)
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManagerHelper.isNotworkType(com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath, org.json.JSONObject, com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceBean):void");
    }

    private void speedValue(MotionPathSimplify motionPathSimplify, HwExerciseAdviceBean hwExerciseAdviceBean) {
        if (hwExerciseAdviceBean.getTrackTotalPace() == 0 || hwExerciseAdviceBean.getTrackTotalCount() == 0) {
            motionPathSimplify.setAvgPace(0.0f);
        } else {
            motionPathSimplify.setAvgPace(3600.0f / ((hwExerciseAdviceBean.getTrackTotalPace() / 10.0f) / hwExerciseAdviceBean.getTrackTotalCount()));
        }
        if (hwExerciseAdviceBean.getTrackMaxPace() == 0) {
            motionPathSimplify.setBestPace(0.0f);
        } else {
            motionPathSimplify.setBestPace(3600.0f / (hwExerciseAdviceBean.getTrackMaxPace() / 10.0f));
        }
        LogUtil.c(TAG, "best pace: ", Float.valueOf(motionPathSimplify.getBestPace()), " sportId: ", hwExerciseAdviceBean.getSportId(), " avg Pace: ", Float.valueOf(motionPathSimplify.getAvgPace()));
    }

    private ArrayList<StepRateData> stepToCadence(ArrayList<StepRateData> arrayList) {
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList<StepRateData> arrayList3 = new ArrayList<>(16);
        arrayList2.addAll(arrayList);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            StepRateData stepRateData = (StepRateData) it.next();
            StepRateData stepRateData2 = new StepRateData();
            stepRateData2.setTime(stepRateData.getTime());
            stepRateData2.setStepRate(stepRateData.getStepRate() / 2);
            arrayList3.add(stepRateData2);
        }
        return arrayList3;
    }

    private void handleHeartRateList(DeviceCapability deviceCapability, MotionPath motionPath, ArrayList<HeartRateData> arrayList, ArrayList<HeartRateData> arrayList2) {
        if (deviceCapability != null && deviceCapability.isSupportWorkoutTrustHeartRate()) {
            Map<String, ArrayList> filterHeartRate = HwWorkoutServiceUtils.filterHeartRate(arrayList);
            if (filterHeartRate.containsKey(HwWorkoutServiceUtils.HEART_RATE_LIST)) {
                motionPath.setHeartRateList(filterHeartRate.get(HwWorkoutServiceUtils.HEART_RATE_LIST));
            }
            dealInvalidHeart(filterHeartRate, arrayList2, motionPath);
            return;
        }
        motionPath.setHeartRateList(arrayList);
        motionPath.setInvalidHeartRateList(arrayList2);
    }

    private void dealInvalidHeart(Map<String, ArrayList> map, ArrayList<HeartRateData> arrayList, MotionPath motionPath) {
        if (map.containsKey(HwWorkoutServiceUtils.INVALID_HEART_RATE_LIST)) {
            ArrayList arrayList2 = map.get(HwWorkoutServiceUtils.INVALID_HEART_RATE_LIST);
            if (!arrayList2.isEmpty()) {
                arrayList.addAll(arrayList2);
                HwWorkoutServiceUtils.sortListByTime(arrayList);
            }
            motionPath.setInvalidHeartRateList(arrayList);
        }
    }

    private long calculateTotalHeartRate(List<HeartRateData> list) {
        ReleaseLogUtil.e(TAG_RELEASE, "calculateTotalHeartRate enter");
        long j = 0;
        if (list != null && list.size() > 0) {
            while (list.iterator().hasNext()) {
                j += r5.next().acquireHeartRate();
            }
        }
        return j;
    }

    private void exerciseData(JSONObject jSONObject, HwExerciseAdviceData hwExerciseAdviceData, MotionPathSimplify motionPathSimplify, JSONArray jSONArray, HwExerciseAdviceBean hwExerciseAdviceBean) throws JSONException {
        int dataIndex = hwExerciseAdviceData.getDataIndex();
        for (int i = 0; i < hwExerciseAdviceData.getDataCount(); i++) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(HwExerciseConstants.JSON_NAME_DATA_HEADER).getJSONArray(HwExerciseConstants.JSON_NAME_INFO_LIST).getJSONObject(i);
            if (hwExerciseAdviceBean.getTrackMinuteStepCount() % 12 == 0) {
                hwExerciseAdviceBean.setTrackMinuteStepTimestamp(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * i * 1000));
            }
            exerciseDetailsFields(jSONObject2, hwExerciseAdviceData, motionPathSimplify, i, hwExerciseAdviceBean);
            int i2 = jSONObject2.getInt("data2");
            TrackSpeedData trackSpeedData = new TrackSpeedData();
            long j = dataIndex;
            trackSpeedData.setTime(hwExerciseAdviceData.getTimeInterval() * j);
            trackSpeedData.setRealTimeSpeed(i2);
            hwExerciseAdviceBean.getSpeedList().add(trackSpeedData);
            LogUtil.c(TAG, "trackSpeedDataTime: ", Long.valueOf(j * hwExerciseAdviceData.getTimeInterval()), "trackSpeedData speed: ", Integer.valueOf(i2));
            ridePowerData(jSONArray, jSONObject2, hwExerciseAdviceData, i, hwExerciseAdviceBean);
            dataIndex++;
            hwExerciseAdviceData.setDataIndex(dataIndex);
        }
    }

    private void exerciseDetailsFields(JSONObject jSONObject, HwExerciseAdviceData hwExerciseAdviceData, MotionPathSimplify motionPathSimplify, int i, HwExerciseAdviceBean hwExerciseAdviceBean) throws JSONException {
        int i2 = jSONObject.getInt("data1");
        LogUtil.c(TAG, "heart value: ", Integer.valueOf(i2));
        int i3 = jSONObject.getInt("data2");
        if (i3 > hwExerciseAdviceBean.getTrackMaxPace()) {
            hwExerciseAdviceBean.setTrackMaxPace(i3);
        }
        long j = i;
        LogUtil.c(TAG, "workoutDataTimestamp: ", Long.valueOf(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000)), "6.9 detailWorkoutData data2 pace: ", Integer.valueOf(i3));
        hwExerciseAdviceBean.setTrackTotalPace(hwExerciseAdviceBean.getTrackTotalPace() + i3);
        int i4 = jSONObject.getInt("data3");
        LogUtil.c(TAG, "step time: ", Long.valueOf(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000)), "6.9 detailWorkoutData data3 step: ", Integer.valueOf(i4));
        hwExerciseAdviceBean.setTrackTotalStep(hwExerciseAdviceBean.getTrackTotalStep() + i4);
        hwExerciseAdviceBean.setTrackMinuteStep(hwExerciseAdviceBean.getTrackMinuteStep() + i4);
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.saveTime(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000));
        if (i2 > 0 && i2 < 255) {
            heartRateData.saveHeartRate(i2);
            hwExerciseAdviceBean.setTrackTotalHeart(hwExerciseAdviceBean.getTrackTotalHeart() + i2);
            hwExerciseAdviceBean.setTrackHeartTotalCount(hwExerciseAdviceBean.getTrackHeartTotalCount() + 1);
            hwExerciseAdviceBean.getHeartRateList().add(heartRateData);
            handleTrustHeartRateTag(motionPathSimplify, hwExerciseAdviceData.getDeviceCapability(), i2);
        } else {
            heartRateData.saveHeartRate(-1);
            hwExerciseAdviceBean.getInvalidHeartRateList().add(heartRateData);
        }
        hwExerciseAdviceBean.setTrackMinuteStepCount(hwExerciseAdviceBean.getTrackMinuteStepCount() + 1);
        isNotSupportNewStep(hwExerciseAdviceData.getTime(), hwExerciseAdviceData.getTimeInterval(), i4, i, hwExerciseAdviceBean);
    }

    private void ridePowerData(JSONArray jSONArray, JSONObject jSONObject, HwExerciseAdviceData hwExerciseAdviceData, int i, HwExerciseAdviceBean hwExerciseAdviceBean) throws JSONException {
        LinkedHashMap<String, String> linkedHashMap;
        int i2 = jSONObject.getInt("data10");
        RidePower ridePower = new RidePower();
        ridePower.setPower(i2);
        long j = i;
        ridePower.setTime(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000));
        hwExerciseAdviceBean.setPowerAccount(i2 + hwExerciseAdviceBean.getPowerAccount());
        hwExerciseAdviceBean.getRidePowerList().add(ridePower);
        JSONObject optJSONObject = jSONObject.optJSONObject("mRunPostureDataInfo");
        ffr ffrVar = optJSONObject != null ? (ffr) new Gson().fromJson(optJSONObject.toString(), ffr.class) : null;
        int i3 = hwExerciseAdviceData.getTotal().has(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) ? hwExerciseAdviceData.getTotal().getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) : -1;
        if (ffrVar != null) {
            StepRateData stepRateData = new StepRateData();
            stepRateData.setTime(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000));
            stepRateData.setStepRate(ffrVar.f());
            hwExerciseAdviceBean.getCadenceList().add(stepRateData);
        }
        if (ffrVar != null && i3 != 3 && i3 != 7) {
            LogUtil.a(TAG, "ridePowerData runPostureDataInfo: ", ffrVar.toString());
            dealRunPostData(ffrVar, hwExerciseAdviceBean.getPostureDataList(), hwExerciseAdviceData);
        }
        workTypeData(jSONArray, jSONObject, hwExerciseAdviceData, i, hwExerciseAdviceBean);
        JSONObject optJSONObject2 = jSONObject.optJSONObject("extraDataMap");
        if (optJSONObject2 == null || (linkedHashMap = (LinkedHashMap) new Gson().fromJson(CommonUtil.z(optJSONObject2.toString()), new TypeToken<LinkedHashMap<String, String>>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManagerHelper.3
        }.getType())) == null || linkedHashMap.isEmpty()) {
            return;
        }
        linkedHashMap.put("time", String.valueOf(hwExerciseAdviceData.getDataIndex() * hwExerciseAdviceData.getTimeInterval()));
        hwExerciseAdviceBean.getExtraDataList().add(linkedHashMap);
    }

    private void dealRunPostData(ffr ffrVar, ArrayList<ffs> arrayList, HwExerciseAdviceData hwExerciseAdviceData) {
        ffs ffsVar = new ffs();
        ffsVar.e(hwExerciseAdviceData.getDataIndex() * hwExerciseAdviceData.getTimeInterval());
        ffsVar.e(ffrVar.e());
        int a2 = ffrVar.a();
        int g = ffrVar.g();
        int o = ffrVar.o();
        int d = ffrVar.d();
        int b = ffrVar.b();
        int m = ffrVar.m();
        int i = ffrVar.i();
        int j = ffrVar.j();
        int h = ffrVar.h();
        ffsVar.c(d);
        ffsVar.a(a2);
        ffsVar.d(g);
        ffsVar.h(o);
        ffsVar.b(b);
        ffsVar.f(m);
        ffsVar.e(i);
        ffsVar.g(j);
        ffsVar.i(h);
        arrayList.add(ffsVar);
    }

    private void handleTrustHeartRateTag(MotionPathSimplify motionPathSimplify, DeviceCapability deviceCapability, int i) {
        if (deviceCapability != null && deviceCapability.isSupportWorkoutTrustHeartRate() && i == 254) {
            motionPathSimplify.addExtendDataMap("isTrustHeartRate", "1");
        }
    }

    private void isNotSupportNewStep(long j, long j2, int i, int i2, HwExerciseAdviceBean hwExerciseAdviceBean) {
        if (hwExerciseAdviceBean.isSupportNewStep()) {
            StepRateData stepRateData = new StepRateData();
            long j3 = j + (j2 * i2 * 1000);
            stepRateData.setTime(j3);
            stepRateData.setStepRate(i);
            hwExerciseAdviceBean.getStepList().add(stepRateData);
            hwExerciseAdviceBean.setTrackMinuteStep(i);
            if (hwExerciseAdviceBean.getTrackMinuteStep() > hwExerciseAdviceBean.getTrackMaxStep()) {
                hwExerciseAdviceBean.setTrackMaxStep(hwExerciseAdviceBean.getTrackMinuteStep());
                hwExerciseAdviceBean.setStepLastIndex(hwExerciseAdviceBean.getStepList().size() - 1);
            }
            LogUtil.c(TAG, "newStepTime: ", Long.valueOf(j3), "detailArray data step: ", Integer.valueOf(i), "trackMinuteStep: ", Integer.valueOf(hwExerciseAdviceBean.getTrackMinuteStep()));
            return;
        }
        oldStepDeal(hwExerciseAdviceBean);
    }

    private void oldStepDeal(HwExerciseAdviceBean hwExerciseAdviceBean) {
        if (hwExerciseAdviceBean.getTrackMinuteStepCount() % 12 == 0) {
            StepRateData stepRateData = new StepRateData();
            stepRateData.setTime(hwExerciseAdviceBean.getTrackMinuteStepTimestamp());
            stepRateData.setStepRate(hwExerciseAdviceBean.getTrackMinuteStep());
            hwExerciseAdviceBean.getStepList().add(stepRateData);
            if (hwExerciseAdviceBean.getTrackMinuteStep() > hwExerciseAdviceBean.getTrackMaxStep()) {
                hwExerciseAdviceBean.setTrackMaxStep(hwExerciseAdviceBean.getTrackMinuteStep());
                hwExerciseAdviceBean.setStepLastIndex(hwExerciseAdviceBean.getStepList().size() - 1);
            }
            LogUtil.c(TAG, "trackMinuteStepTimestamp: ", Long.valueOf(hwExerciseAdviceBean.getTrackMinuteStepTimestamp()), " max step: ", Integer.valueOf(hwExerciseAdviceBean.getTrackMinuteStep()));
            hwExerciseAdviceBean.setTrackMinuteStep(0);
        }
    }

    private void workTypeData(JSONArray jSONArray, JSONObject jSONObject, HwExerciseAdviceData hwExerciseAdviceData, int i, HwExerciseAdviceBean hwExerciseAdviceBean) throws JSONException {
        int i2 = hwExerciseAdviceData.getTotal().has(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) ? hwExerciseAdviceData.getTotal().getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) : -1;
        if (i2 != 1 && i2 != 2 && i2 != 3 && i2 != 4) {
            if (i2 == 6 || i2 == 8) {
                swolfBitMap(jSONArray, jSONObject, hwExerciseAdviceData, hwExerciseAdviceBean);
                return;
            }
            if (i2 != 11 && i2 != 25 && i2 != 214 && i2 != 222) {
                if (i2 == 21) {
                    int i3 = jSONObject.getInt("data9");
                    SkippingSpeed skippingSpeed = new SkippingSpeed();
                    skippingSpeed.setTime(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * i * 1000));
                    skippingSpeed.setSpeed(i3);
                    hwExerciseAdviceBean.getFrequencyList().add(skippingSpeed);
                    return;
                }
                if (i2 != 22) {
                    switch (i2) {
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                            break;
                        default:
                            LogUtil.c(TAG, "in default");
                            break;
                    }
                }
            }
        }
        LogUtil.c(TAG, "capacityBitMap altitude: ", jSONArray.optString(5));
        if ("1".equals(jSONArray.optString(5))) {
            int i4 = jSONObject.getInt("data6");
            TrackAltitudeData trackAltitudeData = new TrackAltitudeData();
            long j = i;
            trackAltitudeData.setTime(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000));
            trackAltitudeData.setAltitude(i4 / 10.0d);
            hwExerciseAdviceBean.getAltitudeList().add(trackAltitudeData);
            LogUtil.c(TAG, "altitude current time: ", Long.valueOf(hwExerciseAdviceData.getTime() + (hwExerciseAdviceData.getTimeInterval() * j * 1000)), "6.9 detailWorkoutData data6 altitude: ", Integer.valueOf(i4));
        }
    }

    private void swolfBitMap(JSONArray jSONArray, JSONObject jSONObject, HwExerciseAdviceData hwExerciseAdviceData, HwExerciseAdviceBean hwExerciseAdviceBean) throws JSONException {
        LogUtil.a(TAG, "capacityBitMap Swolf, TimeInterval: ", Long.valueOf(hwExerciseAdviceData.getTimeInterval()), " DataIndex: ", Integer.valueOf(hwExerciseAdviceData.getDataIndex()));
        if ("1".equals(jSONArray.optString(3))) {
            int i = jSONObject.getInt("data4");
            TrackSwolfData trackSwolfData = new TrackSwolfData();
            trackSwolfData.setTime(hwExerciseAdviceData.getDataIndex() * hwExerciseAdviceData.getTimeInterval());
            trackSwolfData.setSwolf(i);
            hwExerciseAdviceBean.getSwolfList().add(trackSwolfData);
            LogUtil.c(TAG, "time: ", Long.valueOf(hwExerciseAdviceData.getDataIndex() * hwExerciseAdviceData.getTimeInterval()), "6.9 detailWorkoutData data4 swolf: ", Integer.valueOf(i));
        }
        LogUtil.c(TAG, "capacityBitMap pullFrequent: ", jSONArray.optString(4));
        if ("1".equals(jSONArray.optString(4))) {
            int i2 = jSONObject.getInt("data5");
            TrackPullFreqData trackPullFreqData = new TrackPullFreqData();
            trackPullFreqData.setTime(hwExerciseAdviceData.getDataIndex() * hwExerciseAdviceData.getTimeInterval());
            trackPullFreqData.setPullFreq(i2);
            hwExerciseAdviceBean.getPullFrequentList().add(trackPullFreqData);
            LogUtil.c(TAG, "pullFrequent time: ", Long.valueOf(hwExerciseAdviceData.getDataIndex() * hwExerciseAdviceData.getTimeInterval()), "6.9 detailWorkoutData data5 pullFrequent: ", Integer.valueOf(i2));
        }
    }
}
