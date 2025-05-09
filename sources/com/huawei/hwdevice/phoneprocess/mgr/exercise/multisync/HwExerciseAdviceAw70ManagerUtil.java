package com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync;

import android.content.Context;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanRecordInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.StepRateData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TrackSpeedData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.eme;
import defpackage.ffs;
import defpackage.ixt;
import defpackage.ur;
import defpackage.us;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseAdviceAw70ManagerUtil {
    private static final String DATA_HEADER = "dataHeader";
    private static final float DEFAULT_AVG_PACE = 0.0f;
    private static final int DEFAULT_DATA_VALUE = 0;
    private static final int DEFAULT_FALLBACK = -1;
    private static final String DISTANCE = "distance";
    private static final int DISTANCE_PARAM = 100000;
    private static final int DISTANCE_RATIO = 100;
    private static final int HALF_LENGTH = 2;
    private static final String IS_LAST_LESS_DISTANCE = "isLastLessDistance";
    private static final long MILLI_SECOND_UNIT = 1000;
    private static final String PACE = "pace";
    private static final int RADIX_DEFAULT_16 = 16;
    private static final String RUN_PLAN_RECORD_INFO_DISTANCE = "run_plan_record_info_distance";
    private static final String RUN_PLAN_RECORD_INFO_EXERCISE_DURATION = "run_plan_record_info_exercise_duration";
    private static final String RUN_PLAN_RECORD_INFO_STEP = "run_plan_record_info_step";
    private static final int SUPPORT_EXTRA_DATA_BIT_MAP = 8;
    private static final String SWIM_AVG_SWOLF = "swim_avg_swolf";
    private static final int SWIM_AVG_SWOLF_DEFAULT = -1;
    private static final int SWIM_POOL_LENGTH_DEFAULT = -1;
    private static final String SWIM_PULL_LENGTH = "swim_pool_length";
    private static final String SWIM_PULL_RATE = "swim_pull_rate";
    private static final int SWIM_PULL_RATE_DEFAULT = -1;
    private static final String SWIM_PULL_TIMES = "swim_pull_times";
    private static final int SWIM_PULL_TIMES_DEFAULT = -1;
    private static final String SWIM_TRIP_TIMES = "swim_trip_times";
    private static final int SWIM_TRIP_TIMES_DEFAULT = -1;
    private static final String SWIM_TYPE = "swim_type";
    private static final int SWIM_TYPE_DEFAULT = -1;
    private static final String TAG = "HwExerciseAdviceAw70ManagerUtil";
    private static final String TAG_RELEASE = "R_HwExerciseAdviceAw70ManagerUtil";
    private static final int TEN_DEFAULT_10 = 10;
    private static final int THOUSAND = 1000;
    private static final float THOUSAND_FLOAT = 1000.0f;
    private static final int TIME_CONVERT = 60;
    private static final int TOTAL_DISTANCE_PARAM = 10000;
    private static final float TOTAL_DISTANCE_PARAM_FLOAT = 10000.0f;
    private static final String UNIT_TYPE = "unit_type";
    private static final String WORKOUT_DATA_INFO_LISTS = "workoutDataInfoLists";
    private static final String WORKOUT_EXERCISE_DURATION = "workout_exercise_duration";
    private static final String WORKOUT_RECORD_DISTANCE = "workout_record_distance";
    private static final String WORKOUT_RECORD_ID = "workout_record_id";
    private static final String WORKOUT_RECORD_SPEED = "workout_record_speed";
    private static final String WORKOUT_RECORD_STEP = "workout_record_step";
    private Context mContext;

    public static boolean isSupportCapability(int i, int i2) {
        return (i & i2) == i2;
    }

    public boolean checkSupportWorkoutType(int i) {
        return i == 1 || i == 2 || i == 3 || i == 9 || i == 10 || i == 6 || i == 8 || i == 5 || i == 7 || i == 255 || i == 132;
    }

    public HwExerciseAdviceAw70ManagerUtil(Context context) {
        this.mContext = context;
    }

    public String getCurrentDeviceId() {
        DeviceInfo currentDeviceInfo = getCurrentDeviceInfo();
        return currentDeviceInfo != null ? currentDeviceInfo.getSecurityDeviceId() : "";
    }

    private DeviceInfo getCurrentDeviceInfo() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, TAG);
        if (!deviceList.isEmpty()) {
            LogUtil.a(TAG, "getCurrentDeviceInfo() deviceInfoList.size() : ", Integer.valueOf(deviceList.size()));
            for (DeviceInfo deviceInfo : deviceList) {
                if (cvt.c(deviceInfo.getProductType())) {
                    return deviceInfo;
                }
            }
            LogUtil.a(TAG, "getCurrentDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
            return null;
        }
        LogUtil.h(TAG, "getCurrentDeviceInfo() deviceInfoList is null");
        return null;
    }

    public Map<Integer, Float> changePaceMapStruct(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            LogUtil.h(TAG, "changePaceMapStruct paceArray is null");
            return null;
        }
        TreeMap treeMap = new TreeMap();
        try {
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                if (jSONArray.get(i2) instanceof JSONObject) {
                    JSONArray jSONArray2 = ((JSONObject) jSONArray.get(i2)).getJSONArray(HwExerciseConstants.JSON_NAME_PACE_MAP_LIST);
                    if (i == 0) {
                        dealMetricPaceMap(jSONArray2, treeMap);
                    } else {
                        dealMetricPaceMapElse(jSONArray2, treeMap);
                    }
                }
            }
            LogUtil.a(TAG, "changePaceMapStruct paceMap size:", Integer.valueOf(treeMap.size()));
            return treeMap;
        } catch (JSONException unused) {
            LogUtil.b(TAG, "changePaceMapStruct error");
            return null;
        }
    }

    private void dealMetricPaceMap(JSONArray jSONArray, Map<Integer, Float> map) {
        try {
            if (jSONArray == null || map == null) {
                LogUtil.h(TAG, "dealMetricPaceMap param is null");
                return;
            }
            int length = jSONArray.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.optInt("unit_type", -1) == 0) {
                    boolean z = jSONObject.getBoolean("isLastLessDistance");
                    int i3 = jSONObject.getInt("pace");
                    int i4 = jSONObject.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX);
                    if (!z) {
                        i = jSONObject.getInt("distance");
                        map.put(Integer.valueOf((ExceptionCode.CRASH_EXCEPTION * i) + i4), Float.valueOf(i3));
                    } else {
                        int round = (int) Math.round(jSONObject.getInt(HwExerciseConstants.JSON_NAME_LAST_DISTANCE) / 100.0d);
                        if (round != 0) {
                            map.put(Integer.valueOf((((i * 100) + round) * 100000) + i4), Float.valueOf(i3));
                        }
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "dealMetricPaceMap error");
        }
    }

    private void dealMetricPaceMapElse(JSONArray jSONArray, Map<Integer, Float> map) {
        try {
            int length = jSONArray.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.optInt("unit_type", -1) == 1) {
                    boolean z = jSONObject.getBoolean("isLastLessDistance");
                    int i3 = jSONObject.getInt("pace");
                    int i4 = jSONObject.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX);
                    LogUtil.a(TAG, "pace = ", Integer.valueOf(i3), "index =", Integer.valueOf(i4));
                    if (!z) {
                        i = jSONObject.getInt("distance");
                        map.put(Integer.valueOf((ExceptionCode.CRASH_EXCEPTION * i) + i4), Float.valueOf(i3));
                    } else {
                        int round = (int) Math.round(UnitUtil.e(jSONObject.getInt(HwExerciseConstants.JSON_NAME_LAST_DISTANCE) / 100.0d, 3));
                        if (round != 0) {
                            map.put(Integer.valueOf((((i * 100) + round) * 100000) + i4), Float.valueOf(i3));
                        }
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "dealMetricPaceMapElse error");
        }
    }

    public void dealRunPostData(JSONObject jSONObject, ArrayList<ffs> arrayList, int i, int i2) {
        try {
            if (jSONObject == null || arrayList == null) {
                LogUtil.a(TAG, "dealRunPostData param is null");
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("mRunPostureDataInfo");
            int i3 = optJSONObject.getInt("mGroundContactTime");
            int i4 = optJSONObject.getInt("mGroundImpactAcceleration");
            int i5 = optJSONObject.getInt("mSwingAngle");
            int i6 = optJSONObject.getInt("mEversionExcursion");
            int i7 = optJSONObject.getInt("mForeFootStrikePattern");
            int i8 = optJSONObject.getInt("mWholeFootStrikePattern");
            int i9 = optJSONObject.getInt("mHindPawStrikePattern");
            int i10 = optJSONObject.getInt("mHangTime");
            int i11 = optJSONObject.getInt("mImpactHangRate");
            ffs ffsVar = new ffs();
            ffsVar.e(i * i2);
            ffsVar.c(i6);
            ffsVar.a(i3);
            ffsVar.d(i4);
            ffsVar.h(i5);
            ffsVar.b(i7);
            ffsVar.f(i8);
            ffsVar.e(i9);
            ffsVar.g(i10);
            ffsVar.i(i11);
            arrayList.add(ffsVar);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "dealRunPostData JSONException");
        }
    }

    public void parseSpeedData(int i, JSONArray jSONArray, ArrayList<TrackSpeedData> arrayList) {
        try {
            if (jSONArray == null || arrayList == null) {
                LogUtil.h(TAG, "parseSpeedData param is null");
                return;
            }
            int i2 = i;
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                JSONObject jSONObject = jSONArray.getJSONObject(i3);
                int i5 = jSONObject.getJSONObject("dataHeader").getInt(HwExerciseConstants.JSON_NAME_TIME_INTERVAL);
                int length = jSONObject.getJSONObject("dataHeader").getJSONArray("workoutDataInfoLists").length();
                for (int i6 = 0; i6 < length; i6++) {
                    long j = i4 * i5;
                    int i7 = jSONObject.getJSONObject("dataHeader").getJSONArray("workoutDataInfoLists").getJSONObject(i6).getInt("data2");
                    LogUtil.c(TAG, "packTrackData pace value :", Integer.valueOf(i7));
                    i4++;
                    arrayList.add(new TrackSpeedData(j, i7));
                }
                i3++;
                i2 = i;
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "packTrackData speedData JSONException");
        }
    }

    public void parseJumpData(List<JSONArray> list, ArrayList<ixt> arrayList) {
        if (list != null) {
            try {
                int size = list.size();
                LogUtil.c(TAG, "jumpData size : ", Integer.valueOf(size));
                for (int i = 0; i < size; i++) {
                    int length = list.get(i).length();
                    LogUtil.a(TAG, "jumpData.get(i).length() : ", Integer.valueOf(length));
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject jSONObject = list.get(i).getJSONObject(i2);
                        long j = jSONObject.getInt("mJumpTime");
                        int i3 = jSONObject.getInt("mJumpHeight");
                        int i4 = jSONObject.getInt("mJumpDuration");
                        LogUtil.c(TAG, "jumpTime:", Long.valueOf(j), ", jumpHeight:", Integer.valueOf(i3), ", jumpDuration:", Integer.valueOf(i4));
                        arrayList.add(new ixt(j, i3, i4));
                    }
                }
            } catch (JSONException unused) {
                LogUtil.b(TAG, "packTrackData jumpData JSONException");
            }
        }
    }

    public void setHeartRateList(long j, int i, ArrayList<HeartRateData> arrayList) {
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.saveTime(j);
        heartRateData.saveHeartRate(i);
        arrayList.add(heartRateData);
    }

    public void setStepList(int i, long j, ArrayList<StepRateData> arrayList) {
        StepRateData stepRateData = new StepRateData();
        stepRateData.setTime(j);
        stepRateData.setStepRate(i);
        arrayList.add(stepRateData);
    }

    public void setMotionList(MotionPath motionPath, ArrayList<HeartRateData> arrayList, ArrayList<StepRateData> arrayList2, ArrayList<ffs> arrayList3, ArrayList<ixt> arrayList4) {
        if (motionPath == null) {
            LogUtil.h(TAG, "setMotionList param is null");
            return;
        }
        motionPath.setHeartRateList(arrayList);
        motionPath.setStepRateList(arrayList2);
        motionPath.saveRunningPostureList(arrayList3);
        motionPath.saveJumpDataList(arrayList4);
    }

    public void checkWorkoutDisplayInfo(int i, Map<Long, double[]> map, WorkoutDisplayInfo workoutDisplayInfo) {
        dealDisplayInfo(map, workoutDisplayInfo);
        if (i != 1) {
            if (i == 2) {
                workoutDisplayInfo.setWorkoutType(257);
                return;
            }
            if (i == 3) {
                workoutDisplayInfo.setWorkoutType(259);
                return;
            }
            if (i == 132) {
                workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_BASKETBALL);
                workoutDisplayInfo.setChiefSportDataType(5);
                workoutDisplayInfo.setFreeMotion(true);
                return;
            }
            if (i != 255) {
                switch (i) {
                    case 5:
                        workoutDisplayInfo.setWorkoutType(264);
                        workoutDisplayInfo.setFreeMotion(true);
                        break;
                    case 6:
                        workoutDisplayInfo.setWorkoutType(262);
                        workoutDisplayInfo.setChiefSportDataType(0);
                        workoutDisplayInfo.setFreeMotion(true);
                        break;
                    case 7:
                        workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
                        workoutDisplayInfo.setChiefSportDataType(1);
                        workoutDisplayInfo.setFreeMotion(true);
                        break;
                    case 8:
                        workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM);
                        workoutDisplayInfo.setChiefSportDataType(0);
                        workoutDisplayInfo.setFreeMotion(true);
                        break;
                    case 9:
                    case 10:
                        break;
                    default:
                        workoutDisplayInfo.setWorkoutType(258);
                        break;
                }
            }
            workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT);
            workoutDisplayInfo.setChiefSportDataType(1);
            workoutDisplayInfo.setFreeMotion(true);
            return;
        }
        workoutDisplayInfo.setWorkoutType(258);
    }

    private void dealDisplayInfo(Map<Long, double[]> map, WorkoutDisplayInfo workoutDisplayInfo) {
        if (map != null && map.size() > 2) {
            workoutDisplayInfo.setHasTrackPoint(true);
        } else {
            workoutDisplayInfo.setHasTrackPoint(false);
        }
    }

    public void convertHealthTrackDataToHiData(MotionPathSimplify motionPathSimplify, MotionPath motionPath, HiDataInsertOption hiDataInsertOption) {
        motionPathSimplify.setBestPace(calculateBestPace(motionPathSimplify.getPaceMap()));
        LogUtil.a(TAG, "convertHealthTrackDataToHiData, simplifyData ", motionPathSimplify.toString(), "convertHealthTrackDataToHiData, motionData ", motionPath.toString());
        HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
        String str = motionPathSimplify.getMapType() == 0 ? "AMAP" : null;
        if (motionPathSimplify.getMapType() == 1) {
            str = "GOOGLE";
        }
        if (str != null) {
            hiTrackMetaData.setVendor(str);
        }
        saveMetaData(hiTrackMetaData, motionPathSimplify);
        us usVar = new us();
        usVar.c(motionPathSimplify.getSportType());
        usVar.b(motionPathSimplify.getPaceMap());
        usVar.c(motionPathSimplify.getAvgPace());
        usVar.d(motionPathSimplify.getTotalDistance());
        usVar.e(motionPathSimplify.getTotalSteps());
        usVar.d(true);
        usVar.c(motionPathSimplify.getPartTimeMap());
        usVar.a(motionPathSimplify.getTrackType());
        usVar.b(TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.getTotalTime()));
        int a2 = ur.a(usVar);
        boolean e = ur.e(usVar);
        LogUtil.a(TAG, "mAbnormalTrack:", Integer.valueOf(a2), "mIsStepAbnormal", Boolean.valueOf(e), ", abnormalData", usVar.toString());
        motionPathSimplify.saveAbnormalTrack(a2);
        motionPathSimplify.addExtendDataMap("STEP_STATICS_EXCEPTION", e ? "1" : "0");
        hiTrackMetaData.setAbnormalTrack(motionPathSimplify.requestAbnormalTrack());
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(motionPathSimplify.getStartTime());
        hiHealthData.setEndTime(motionPathSimplify.getEndTime());
        hiHealthData.setType(30001);
        hiHealthData.setSequenceData(motionPath.toString());
        try {
            hiHealthData.setMetaData(new Gson().toJson(hiTrackMetaData, HiTrackMetaData.class));
        } catch (JsonIOException unused) {
            LogUtil.b(TAG, "convertHealthTrackDataToHiData JsonIOException");
        }
        DeviceInfo currentDeviceInfo = getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            hiHealthData.setDeviceUuid(currentDeviceInfo.getSecurityUuid() + "#ANDROID21");
            LogUtil.h(TAG, "convertHealthTrackDataToHiData, deviceInfo is not null");
        }
        hiDataInsertOption.addData(hiHealthData);
    }

    private static void saveMetaData(HiTrackMetaData hiTrackMetaData, MotionPathSimplify motionPathSimplify) {
        hiTrackMetaData.setAvgStepRate(motionPathSimplify.getAvgStepRate());
        hiTrackMetaData.setAvgHeartRate(motionPathSimplify.getAvgHeartRate());
        hiTrackMetaData.setAvgPace(motionPathSimplify.getAvgPace());
        hiTrackMetaData.setBestPace(motionPathSimplify.getBestPace());
        hiTrackMetaData.setBestStepRate(motionPathSimplify.getBestStepRate());
        hiTrackMetaData.setMaxHeartRate(motionPathSimplify.getMaxHeartRate());
        hiTrackMetaData.setSportType(motionPathSimplify.getSportType());
        hiTrackMetaData.setTotalCalories(motionPathSimplify.getTotalCalories());
        hiTrackMetaData.setTotalDistance(motionPathSimplify.getTotalDistance());
        hiTrackMetaData.setTotalSteps(motionPathSimplify.getTotalSteps());
        hiTrackMetaData.setTotalTime(motionPathSimplify.getTotalTime());
        hiTrackMetaData.setWearSportData(motionPathSimplify.getSportData());
        hiTrackMetaData.setCreepingWave(motionPathSimplify.getCreepingWave());
        hiTrackMetaData.setMinHeartRate(motionPathSimplify.getMinHeartRate());
        hiTrackMetaData.setTrackType(motionPathSimplify.getTrackType());
        hiTrackMetaData.setWearSportData(motionPathSimplify.getSportData());
        hiTrackMetaData.setIsFreeMotion(motionPathSimplify.getIsFreeMotion());
        hiTrackMetaData.setSportDataSource(motionPathSimplify.getSportDataSource());
        hiTrackMetaData.setChiefSportDataType(motionPathSimplify.getChiefSportDataType());
        hiTrackMetaData.setHasTrackPoint(motionPathSimplify.getHasTrackPoint());
        hiTrackMetaData.setPaceMap(motionPathSimplify.getPaceMap());
        hiTrackMetaData.setPartTimeMap(motionPathSimplify.getPartTimeMap());
        hiTrackMetaData.setBritishPaceMap(motionPathSimplify.getBritishPaceMap());
        hiTrackMetaData.setBritishPartTimeMap(motionPathSimplify.getBritishPartTimeMap());
        hiTrackMetaData.setAvgGroundContactTime(motionPathSimplify.requestAvgGroundContactTime());
        hiTrackMetaData.setAvgGroundImpactAcceleration(motionPathSimplify.requestAvgGroundImpactAcceleration());
        hiTrackMetaData.setAvgEversionExcursion(motionPathSimplify.requestAvgEversionExcursion());
        hiTrackMetaData.setAvgSwingAngle(motionPathSimplify.requestAvgSwingAngle());
        hiTrackMetaData.setAvgForeFootStrikePattern(motionPathSimplify.requestAvgForeFootStrikePattern());
        hiTrackMetaData.setAvgWholeFootStrikePattern(motionPathSimplify.requestAvgWholeFootStrikePattern());
        hiTrackMetaData.setAvgHindFootStrikePattern(motionPathSimplify.requestAvgHindFootStrikePattern());
        hiTrackMetaData.saveAverageHangTime(motionPathSimplify.requestAverageHangTime());
        hiTrackMetaData.saveGroundHangTimeRate(motionPathSimplify.requestGroundHangTimeRate());
    }

    private static float calculateBestPace(Map<Integer, Float> map) {
        float f = 0.0f;
        if (map != null && !map.isEmpty()) {
            Map<Integer, Float> validPaceMap = eme.b().validPaceMap(map);
            if (validPaceMap != null && !validPaceMap.isEmpty()) {
                LogUtil.a(TAG, "calculateBestPace,valid:", validPaceMap.toString());
                f = Float.MAX_VALUE;
                for (Map.Entry<Integer, Float> entry : validPaceMap.entrySet()) {
                    if (f > entry.getValue().floatValue()) {
                        f = entry.getValue().floatValue();
                    }
                }
                LogUtil.a(TAG, "calculateBestPace,setBestPace:", Float.valueOf(f));
            } else {
                LogUtil.h(TAG, "calculateBestPace,no valadePacemap ");
            }
            LogUtil.a(TAG, "calculateBestPace,bestValue:", Float.valueOf(f));
        }
        return f;
    }

    public Map<Double, Double> changePartTimePaceMapStruct(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            LogUtil.h(TAG, "changePartTimePaceMapStruct paceArray is null");
            return null;
        }
        TreeMap treeMap = new TreeMap();
        try {
            int length = jSONArray.length();
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                try {
                    JSONArray paceJson = getPaceJson((JSONObject) jSONArray.get(i3), i);
                    int length2 = paceJson.length();
                    for (int i4 = 0; i4 < length2; i4++) {
                        JSONObject jSONObject = paceJson.getJSONObject(i4);
                        if (!jSONObject.getBoolean("isLastLessDistance")) {
                            int i5 = jSONObject.getInt("distance");
                            i2 += jSONObject.getInt("pace");
                            treeMap.put(Double.valueOf((i5 * 10000) / 10000.0d), Double.valueOf(i2));
                        }
                    }
                } catch (JSONException unused) {
                    LogUtil.b(TAG, "changePartTimePaceMapStruct");
                    return null;
                }
            }
            LogUtil.c(TAG, "changePartTimePaceMapStruct paceMap size:", Integer.valueOf(treeMap.size()));
            return treeMap;
        } catch (JSONException unused2) {
        }
    }

    private JSONArray getPaceJson(JSONObject jSONObject, int i) {
        if (jSONObject == null || !jSONObject.has(HwExerciseConstants.JSON_NAME_PACE_MAP_LIST)) {
            return new JSONArray();
        }
        JSONArray jSONArray = new JSONArray();
        try {
            JSONArray jSONArray2 = jSONObject.getJSONArray(HwExerciseConstants.JSON_NAME_PACE_MAP_LIST);
            int length = jSONArray2.length();
            for (int i2 = 0; i2 < length; i2++) {
                if (jSONArray2.get(i2) instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) jSONArray2.get(i2);
                    if (i == jSONObject2.optInt("unit_type", -1)) {
                        jSONArray.put(jSONObject2);
                    }
                }
            }
            LogUtil.c(TAG, "leave getPaceJson method");
            return jSONArray;
        } catch (JSONException unused) {
            LogUtil.b(TAG, "changePartTimePaceMapStruct");
            return new JSONArray();
        }
    }

    public void saveSportData(Map<String, Integer> map, JSONObject jSONObject) {
        try {
            map.put("record_id", Integer.valueOf(jSONObject.getInt("workout_record_id")));
            map.put("status", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_STATUS)));
            map.put("load_peak", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_LOAD_PEAK)));
            map.put("etraining_effect", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_ETRAINING_EFFECT)));
            map.put("extra_poc", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_EPOC)));
            map.put("max_met", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_MAX_MET)));
            map.put("recovery_time", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECOVERY_TIME)));
            if (jSONObject.getInt("swim_type") != -1) {
                map.put("swim_stroke", Integer.valueOf(jSONObject.getInt("swim_type")));
            }
            if (jSONObject.getInt("swim_pull_times") != -1) {
                map.put("swim_pull_times", Integer.valueOf(jSONObject.getInt("swim_pull_times")));
            }
            if (jSONObject.getInt("swim_pull_rate") != -1) {
                map.put("swim_pull_freq", Integer.valueOf(jSONObject.getInt("swim_pull_rate")));
            }
            if (jSONObject.getInt("swim_pool_length") != -1) {
                map.put("swim_pool_length", Integer.valueOf(jSONObject.getInt("swim_pool_length")));
            }
            if (jSONObject.getInt("swim_trip_times") != -1) {
                map.put("swim_laps", Integer.valueOf(jSONObject.getInt("swim_trip_times")));
            }
            if (jSONObject.getInt("swim_avg_swolf") != -1) {
                map.put("swim_avg_swolf", Integer.valueOf(jSONObject.getInt("swim_avg_swolf")));
            }
            int optInt = jSONObject.optInt("mActiveDuration", -1);
            if (optInt != -1) {
                map.put("active_time", Integer.valueOf(optInt));
            }
            int optInt2 = jSONObject.optInt("mJumpTimes", -1);
            if (optInt2 != -1) {
                map.put("jump_times", Integer.valueOf(optInt2));
            }
            int optInt3 = jSONObject.optInt("mMaxJumpHeight", -1);
            if (optInt3 != -1) {
                map.put("max_jump_height", Integer.valueOf(optInt3));
            }
            int optInt4 = jSONObject.optInt("mMaxJumpDuration", -1);
            if (optInt4 != -1) {
                map.put("max_duration_of_passage", Integer.valueOf(optInt4));
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "saveSportData JSONException");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00bd A[Catch: JSONException -> 0x0134, TryCatch #0 {JSONException -> 0x0134, blocks: (B:3:0x0013, B:6:0x007f, B:9:0x0088, B:10:0x00b3, B:12:0x00bd, B:13:0x00e4, B:15:0x00ec, B:19:0x00f4, B:21:0x010a, B:22:0x0116, B:24:0x012c, B:26:0x0130, B:28:0x0113, B:29:0x009d), top: B:2:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x010a A[Catch: JSONException -> 0x0134, TryCatch #0 {JSONException -> 0x0134, blocks: (B:3:0x0013, B:6:0x007f, B:9:0x0088, B:10:0x00b3, B:12:0x00bd, B:13:0x00e4, B:15:0x00ec, B:19:0x00f4, B:21:0x010a, B:22:0x0116, B:24:0x012c, B:26:0x0130, B:28:0x0113, B:29:0x009d), top: B:2:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x012c A[Catch: JSONException -> 0x0134, TryCatch #0 {JSONException -> 0x0134, blocks: (B:3:0x0013, B:6:0x007f, B:9:0x0088, B:10:0x00b3, B:12:0x00bd, B:13:0x00e4, B:15:0x00ec, B:19:0x00f4, B:21:0x010a, B:22:0x0116, B:24:0x012c, B:26:0x0130, B:28:0x0113, B:29:0x009d), top: B:2:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0130 A[Catch: JSONException -> 0x0134, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0134, blocks: (B:3:0x0013, B:6:0x007f, B:9:0x0088, B:10:0x00b3, B:12:0x00bd, B:13:0x00e4, B:15:0x00ec, B:19:0x00f4, B:21:0x010a, B:22:0x0116, B:24:0x012c, B:26:0x0130, B:28:0x0113, B:29:0x009d), top: B:2:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0113 A[Catch: JSONException -> 0x0134, TryCatch #0 {JSONException -> 0x0134, blocks: (B:3:0x0013, B:6:0x007f, B:9:0x0088, B:10:0x00b3, B:12:0x00bd, B:13:0x00e4, B:15:0x00ec, B:19:0x00f4, B:21:0x010a, B:22:0x0116, B:24:0x012c, B:26:0x0130, B:28:0x0113, B:29:0x009d), top: B:2:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setWorkoutRecordSimplify(org.json.JSONObject r19, com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo r20, com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify r21) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70ManagerUtil.setWorkoutRecordSimplify(org.json.JSONObject, com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo, com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify):void");
    }

    private void setSportType(MotionPathSimplify motionPathSimplify, WorkoutDisplayInfo workoutDisplayInfo) {
        motionPathSimplify.setChiefSportDataType(workoutDisplayInfo.getChiefSportDataType());
        motionPathSimplify.setIsFreeMotion(workoutDisplayInfo.getFreeMotion());
        motionPathSimplify.setSportType(workoutDisplayInfo.getWorkoutType());
        motionPathSimplify.setHasTrackPoint(workoutDisplayInfo.isHasTrackPoint());
        motionPathSimplify.setSportDataSource(workoutDisplayInfo.getSportDataSource());
    }

    public void dealRunPlanRecordTlvList(List<cwe> list, List<RunPlanRecordInfo> list2) {
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            RunPlanRecordInfo runPlanRecordInfo = new RunPlanRecordInfo();
            for (cwd cwdVar : e) {
                switch (Integer.parseInt(cwdVar.e(), 16)) {
                    case 2:
                        runPlanRecordInfo.setRunPlanRecordInfoId(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 3:
                        runPlanRecordInfo.setRunPlanRecordInfoStatus(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 4:
                        runPlanRecordInfo.setRunPlanRecordInfoStartTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                        break;
                    case 5:
                        runPlanRecordInfo.setRunPlanRecordInfoEndTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                        break;
                    case 6:
                        runPlanRecordInfo.setRunPlanRecordInfoCalorie(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 7:
                        runPlanRecordInfo.setRunPlanRecordInfoDistance(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    case 8:
                        runPlanRecordInfo.setRunPlanRecordInfoStep(Integer.parseInt(cwdVar.c(), 16));
                        break;
                    default:
                        dealMoreRunPlanRecordTlvList(Integer.parseInt(cwdVar.e(), 16), runPlanRecordInfo, cwdVar);
                        break;
                }
            }
            list2.add(runPlanRecordInfo);
        }
    }

    private void dealMoreRunPlanRecordTlvList(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        try {
            switch (i) {
                case 9:
                    runPlanRecordInfo.setRunPlanRecordInfoTotalTime(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 10:
                    runPlanRecordInfo.setRunPlanRecordInfoSpeed(Integer.parseInt(cwdVar.c(), 16) / 10.0f);
                    break;
                case 11:
                    runPlanRecordInfo.setRunPlanRecordInfoClimb(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 12:
                    runPlanRecordInfo.setRunPlanRecordInfoHrabsMin(Integer.parseInt(cwdVar.c().substring(0, 2), 16));
                    runPlanRecordInfo.setRunPlanRecordInfoHrabsMax(Integer.parseInt(cwdVar.c().substring(2, 4), 16));
                    break;
                case 13:
                    runPlanRecordInfo.setRunPlanRecordInfoLoadPeak(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 14:
                    runPlanRecordInfo.setRunPlanRecordEtrainingEffect(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 15:
                    runPlanRecordInfo.setRunPlanRecordAchievePercent(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 16:
                    runPlanRecordInfo.setRunPlanRecordInfoEpoc(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 17:
                    runPlanRecordInfo.setRunPlanRecordInfoMaxMet(Integer.parseInt(cwdVar.c(), 16));
                    break;
                default:
                    dealMuchMoreRunPlanRecordTlvList(Integer.parseInt(cwdVar.e(), 16), runPlanRecordInfo, cwdVar);
                    break;
            }
        } catch (NumberFormatException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "dealMoreRunPlanRecordTlvList NumberFormatException : ", ExceptionUtils.d(e));
        }
    }

    private void dealMuchMoreRunPlanRecordTlvList(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        switch (i) {
            case 18:
                runPlanRecordInfo.setRunPlanRecordInfoRecoveryTime(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 19:
                break;
            case 20:
                runPlanRecordInfo.setRunPlanRecordInfoExerciseDuration(Integer.parseInt(cwdVar.c(), 16) * 1000);
                break;
            case 21:
                runPlanRecordInfo.setRunPlanRecordInfoDateInfo(Integer.parseInt(cwdVar.c(), 16));
                break;
            default:
                LogUtil.h(TAG, "dealMuchMoreRunPlanRecordTlvList default branch");
                break;
        }
    }

    public void dealNotificationRunPlanRecordInfo(List<cwe> list, List<RunPlanRecordInfo> list2) {
        try {
            Iterator<cwe> it = list.iterator();
            while (it.hasNext()) {
                List<cwd> e = it.next().e();
                RunPlanRecordInfo runPlanRecordInfo = new RunPlanRecordInfo();
                for (cwd cwdVar : e) {
                    switch (Integer.parseInt(cwdVar.e(), 16)) {
                        case 2:
                            runPlanRecordInfo.setRunPlanRecordInfoWourkoutId(Integer.parseInt(cwdVar.c(), 16));
                            break;
                        case 3:
                            runPlanRecordInfo.setRunPlanRecordInfoStatus(Integer.parseInt(cwdVar.c(), 16));
                            break;
                        case 4:
                            runPlanRecordInfo.setRunPlanRecordInfoStartTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                            break;
                        case 5:
                            runPlanRecordInfo.setRunPlanRecordInfoEndTime(Long.parseLong(cwdVar.c(), 16) * 1000);
                            break;
                        case 6:
                            runPlanRecordInfo.setRunPlanRecordInfoCalorie(Integer.parseInt(cwdVar.c(), 16));
                            break;
                        case 7:
                            runPlanRecordInfo.setRunPlanRecordInfoDistance(Integer.parseInt(cwdVar.c(), 16));
                            break;
                        default:
                            dealMoreNotificationRunPlanRecordInfo(Integer.parseInt(cwdVar.e(), 16), runPlanRecordInfo, cwdVar);
                            break;
                    }
                }
                list2.add(runPlanRecordInfo);
            }
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "NOTIFICATION_RUN_PLAN_RECORD_INFO");
        }
    }

    private void dealMoreNotificationRunPlanRecordInfo(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        switch (i) {
            case 8:
                runPlanRecordInfo.setRunPlanRecordInfoStep(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 9:
                runPlanRecordInfo.setRunPlanRecordInfoTotalTime(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 10:
                runPlanRecordInfo.setRunPlanRecordInfoSpeed(Integer.parseInt(cwdVar.c(), 16) / 10.0f);
                break;
            case 11:
                runPlanRecordInfo.setRunPlanRecordInfoClimb(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 12:
                runPlanRecordInfo.setRunPlanRecordInfoHrabsMin(Integer.parseInt(cwdVar.c().substring(0, 2), 16));
                runPlanRecordInfo.setRunPlanRecordInfoHrabsMax(Integer.parseInt(cwdVar.c().substring(2, 4), 16));
                break;
            case 13:
                runPlanRecordInfo.setRunPlanRecordInfoLoadPeak(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 14:
                runPlanRecordInfo.setRunPlanRecordEtrainingEffect(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 15:
                runPlanRecordInfo.setRunPlanRecordAchievePercent(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 16:
                runPlanRecordInfo.setRunPlanRecordInfoEpoc(Integer.parseInt(cwdVar.c(), 16));
                break;
            default:
                dealMuchMoreNotificationRunPlanRecordInfo(i, runPlanRecordInfo, cwdVar);
                break;
        }
    }

    private void dealMuchMoreNotificationRunPlanRecordInfo(int i, RunPlanRecordInfo runPlanRecordInfo, cwd cwdVar) {
        try {
            switch (i) {
                case 17:
                    runPlanRecordInfo.setRunPlanRecordInfoMaxMet(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 18:
                    runPlanRecordInfo.setRunPlanRecordInfoRecoveryTime(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 19:
                    runPlanRecordInfo.setRunPlanRecordInfoDailyScore(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 20:
                    runPlanRecordInfo.setRunPlanRecordInfoExerciseDuration(Integer.parseInt(cwdVar.c(), 16) * 1000);
                    break;
                case 21:
                    runPlanRecordInfo.setRunPlanRecordInfoDateInfo(Integer.parseInt(cwdVar.c(), 16));
                    break;
                default:
                    Object[] objArr = new Object[1];
                    objArr[0] = "dealMuchMoreNotificationRunPlanRecordInfo branch default";
                    LogUtil.h(TAG, objArr);
                    break;
            }
        } catch (NumberFormatException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "dealMuchMoreNotificationRunPlanRecordInfo NumberFormatException : ", ExceptionUtils.d(e));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00aa A[Catch: JSONException -> 0x00da, TRY_LEAVE, TryCatch #0 {JSONException -> 0x00da, blocks: (B:6:0x0037, B:9:0x0086, B:12:0x008d, B:13:0x00a4, B:15:0x00aa, B:20:0x00a0), top: B:5:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSimplifyMore(org.json.JSONObject r7, java.util.Map<java.lang.Long, double[]> r8, com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify r9) {
        /*
            r6 = this;
            java.lang.String r0 = "run_plan_record_info_distance"
            java.lang.String r1 = "HwExerciseAdviceAw70ManagerUtil"
            if (r7 == 0) goto Le5
            if (r9 != 0) goto Lb
            goto Le5
        Lb:
            com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo r2 = new com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo
            r2.<init>()
            r3 = 1
            r6.checkWorkoutDisplayInfo(r3, r8, r2)
            int r8 = r2.getChiefSportDataType()
            r9.setChiefSportDataType(r8)
            boolean r8 = r2.getFreeMotion()
            r9.setIsFreeMotion(r8)
            int r8 = r2.getWorkoutType()
            r9.setSportType(r8)
            boolean r8 = r2.isHasTrackPoint()
            r9.setHasTrackPoint(r8)
            int r8 = r2.getSportDataSource()
            r9.setSportDataSource(r8)
            java.lang.String r8 = "run_plan_record_info_start_time"
            long r4 = r7.getLong(r8)     // Catch: org.json.JSONException -> Lda
            r9.setStartTime(r4)     // Catch: org.json.JSONException -> Lda
            java.lang.String r8 = "run_plan_record_info_end_time"
            long r4 = r7.getLong(r8)     // Catch: org.json.JSONException -> Lda
            r9.setEndTime(r4)     // Catch: org.json.JSONException -> Lda
            java.lang.String r8 = "run_plan_record_info_HrABS_max"
            int r8 = r7.getInt(r8)     // Catch: org.json.JSONException -> Lda
            r9.setMaxHeartRate(r8)     // Catch: org.json.JSONException -> Lda
            java.lang.String r8 = "run_plan_record_info_HrABS_min"
            int r8 = r7.getInt(r8)     // Catch: org.json.JSONException -> Lda
            r9.setMinHeartRate(r8)     // Catch: org.json.JSONException -> Lda
            int r8 = r7.getInt(r0)     // Catch: org.json.JSONException -> Lda
            r9.setTotalDistance(r8)     // Catch: org.json.JSONException -> Lda
            java.lang.String r8 = "run_plan_record_info_calorie"
            int r8 = r7.getInt(r8)     // Catch: org.json.JSONException -> Lda
            int r8 = r8 * 1000
            r9.setTotalCalories(r8)     // Catch: org.json.JSONException -> Lda
            java.lang.String r8 = "run_plan_record_info_climb"
            int r8 = r7.getInt(r8)     // Catch: org.json.JSONException -> Lda
            float r8 = (float) r8     // Catch: org.json.JSONException -> Lda
            r9.setCreepingWave(r8)     // Catch: org.json.JSONException -> Lda
            int r8 = r7.getInt(r0)     // Catch: org.json.JSONException -> Lda
            java.lang.String r2 = "run_plan_record_info_exercise_duration"
            if (r8 == 0) goto La0
            int r8 = r7.getInt(r2)     // Catch: org.json.JSONException -> Lda
            if (r8 != 0) goto L8d
            goto La0
        L8d:
            int r8 = r7.getInt(r0)     // Catch: org.json.JSONException -> Lda
            float r8 = (float) r8     // Catch: org.json.JSONException -> Lda
            r0 = 1148846080(0x447a0000, float:1000.0)
            float r8 = r8 * r0
            int r4 = r7.getInt(r2)     // Catch: org.json.JSONException -> Lda
            float r4 = (float) r4     // Catch: org.json.JSONException -> Lda
            float r8 = r8 / r4
            float r0 = r0 / r8
            r9.setAvgPace(r0)     // Catch: org.json.JSONException -> Lda
            goto La4
        La0:
            r8 = 0
            r9.setAvgPace(r8)     // Catch: org.json.JSONException -> Lda
        La4:
            int r8 = r7.getInt(r2)     // Catch: org.json.JSONException -> Lda
            if (r8 == 0) goto Le4
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: org.json.JSONException -> Lda
            java.lang.String r0 = "record step:"
            r4 = 0
            r8[r4] = r0     // Catch: org.json.JSONException -> Lda
            java.lang.String r0 = "workout_record_step"
            int r0 = r7.getInt(r0)     // Catch: org.json.JSONException -> Lda
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: org.json.JSONException -> Lda
            r8[r3] = r0     // Catch: org.json.JSONException -> Lda
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)     // Catch: org.json.JSONException -> Lda
            java.lang.String r8 = "run_plan_record_info_step"
            int r8 = r7.getInt(r8)     // Catch: org.json.JSONException -> Lda
            int r8 = r8 * 1000
            float r8 = (float) r8     // Catch: org.json.JSONException -> Lda
            int r7 = r7.getInt(r2)     // Catch: org.json.JSONException -> Lda
            float r7 = (float) r7     // Catch: org.json.JSONException -> Lda
            float r8 = r8 / r7
            r7 = 1114636288(0x42700000, float:60.0)
            float r8 = r8 * r7
            int r7 = (int) r8     // Catch: org.json.JSONException -> Lda
            r9.setAvgStepRate(r7)     // Catch: org.json.JSONException -> Lda
            goto Le4
        Lda:
            java.lang.String r7 = "setSimplifyMore Exception"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r7)
        Le4:
            return
        Le5:
            java.lang.String r7 = "setSimplifyMore param is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync.HwExerciseAdviceAw70ManagerUtil.setSimplifyMore(org.json.JSONObject, java.util.Map, com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify):void");
    }

    public void testRunPlanRecordInfoDebug(SparseArray<JSONObject> sparseArray) {
        try {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                JSONObject valueAt = sparseArray.valueAt(i);
                LogUtil.c(TAG, "workout id:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ID), ",workout statuc:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_STATUS), ",startime:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_START_TIME), ",endtime：", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_END_TIME), "calorie：", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_CALORIE), ",distance:", valueAt.get("run_plan_record_info_distance"), ",step=", valueAt.get("run_plan_record_info_step"), ",time=", valueAt.get("run_plan_record_info_total_time"), ",speed=", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_SPEED), ",climb=", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_CLIMB), "HrMax:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_MAX_HEART_RATE), ",HrMin=", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_MIN_HEART_RATE), ",loadPeak:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_LOAD_PEAK), ",effect:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ETRAINING_EFFECT), ",epoc:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_EPOC), "MET:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_MAX_MET), ", finishRate:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ACHIEVE_PERCENT), ",revoeryTime:", valueAt.get(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_RECOVERY_TIME), ",duration:", valueAt.get("run_plan_record_info_exercise_duration"), ",date:", valueAt.get("run_plan_record_info_date_info"), "totalTime:", valueAt.get("run_plan_record_info_total_time"));
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "testRunPlanRecordInfoDebug JSONException");
        }
    }

    public void printWorkoutRecordInfo(SparseArray<JSONObject> sparseArray) {
        try {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                JSONObject valueAt = sparseArray.valueAt(i);
                LogUtil.c(TAG, "printWorkoutRecordInfo workout id:", valueAt.get("workout_record_id"), ",workout statuc:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_STATUS), ",startime:", valueAt.get(HwExerciseConstants.JSON_NAME_START_TIME), ",endtime:", valueAt.get(HwExerciseConstants.JSON_NAME_END_TIME), "calorie:", valueAt.get(HwExerciseConstants.JSON_NAME_RECORD_CALORIE), ",distance:", valueAt.get("workout_record_distance"), ",step:", valueAt.get("workout_record_step"), ",time:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_TOTAL_TIME), ",speed:", valueAt.get("workout_record_speed"), ",climb:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_CLIMB), "HrMax:", valueAt.get(HwExerciseConstants.JSON_NAME_PEAK_MAX), ",HrMin:", valueAt.get(HwExerciseConstants.JSON_NAME_PEAK_MIN), ",loadPeak:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_LOAD_PEAK), ",effect:", valueAt.get(HwExerciseConstants.JSON_NAME_ETRAINING_EFFECT), ",epoc:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_EPOC), "MET:", valueAt.get(HwExerciseConstants.JSON_NAME_MAX_MET), ",revoeryTime:", valueAt.get(HwExerciseConstants.JSON_NAME_RECOVERY_TIME), ",duration:", valueAt.get("workout_exercise_duration"), ",date:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_DATE_INFO), "totalTime:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_TOTAL_TIME), ",workout_type:", valueAt.get(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE), "swim, swim_type:", valueAt.get("swim_type"), ",swim_pull_times:", valueAt.get("swim_pull_times"), ",swim_pull_rate:", valueAt.get("swim_pull_rate"), ",swim_pool_length:", valueAt.get("swim_pool_length"), ",swim_trip_times:", valueAt.get("swim_trip_times"), ",swim_avg_swolf:", valueAt.get("swim_avg_swolf"));
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "printWorkoutRecordInfo JSONException");
        }
    }

    public static void parseWorkoutCapability(List<cwd> list, List<IBaseResponseCallback> list2) {
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h(TAG, "tlv is empty.");
            responseCallbackList(0, 0, list2);
            return;
        }
        int i = 0;
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 1) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                if (w == 127) {
                    responseCallbackList(CommonUtil.w(cwdVar.c()), i, list2);
                    return;
                }
                LogUtil.h(TAG, "unSupport tag");
            }
        }
        responseCallbackList(0, i, list2);
    }

    private static void responseCallbackList(int i, int i2, List<IBaseResponseCallback> list) {
        Iterator<IBaseResponseCallback> it = list.iterator();
        while (it.hasNext()) {
            it.next().d(i, Integer.valueOf(i2));
        }
        list.clear();
    }

    public static String getWorkoutRecordStatisticStructHex(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            LogUtil.h(TAG, "getWorkoutRecordStatisticStructHex param is null.");
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        try {
            LogUtil.a(TAG, "the parameters are ", jSONObject.toString(), "deviceCapability : ", Integer.valueOf(i));
            String a2 = cvx.a(jSONObject.getInt("workout_record_id"));
            String d = cvx.d(a2.length() / 2);
            String e = cvx.e(2);
            String a3 = cvx.a(jSONObject.getInt(HwExerciseConstants.WORKOUT_DATA_INDEX));
            String d2 = cvx.d(a2.length() / 2);
            String e2 = cvx.e(3);
            StringBuilder sb2 = new StringBuilder(16);
            sb2.append(e);
            sb2.append(d);
            sb2.append(a2);
            sb2.append(e2);
            sb2.append(d2);
            sb2.append(a3);
            if (isSupportCapability(i, 8)) {
                sb2.append(cvx.e(7));
                sb2.append(cvx.e(0));
            }
            int length = sb2.toString().length() / 2;
            sb.append(cvx.e(129));
            sb.append(cvx.e(length));
            sb.append(sb2.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getWorkoutRecordStatisticStructHex json exception.");
        }
        return sb.toString();
    }
}
