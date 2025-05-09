package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.callback.FitnessRecordCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManagerHelper;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseParams;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.WorkoutSyncSuccessDetailData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPath;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.MotionPathSimplify;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.PaceIndexStruct;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.WorkoutDisplayInfo;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.TriathlonUtils;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import defpackage.eme;
import defpackage.jdh;
import defpackage.jds;
import defpackage.jfc;
import defpackage.jqi;
import defpackage.jrn;
import defpackage.kob;
import defpackage.koj;
import defpackage.koq;
import defpackage.ktl;
import defpackage.kwr;
import defpackage.mmt;
import defpackage.sqo;
import defpackage.ur;
import defpackage.us;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.StorageResult;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HwExerciseUtils {
    private static final int CADENCE_DENOMINATOR = 2;
    private static final String COORDINATE = "coordinate";
    private static final String TAG = "HwExerciseUtils";
    private static final String TAG_RELEASE = "BTSYNC_HwExerciseUtils";
    private static LinkedList<WorkoutRecord> sFitnessList = new LinkedList<>();

    private static boolean checkNoEquipmentWorkType(int i) {
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 14 || i == 13 || i == 9 || i == 10 || i == 11;
    }

    private HwExerciseUtils() {
    }

    public static void convertHealthTrackDataToHiData(MotionPathSimplify motionPathSimplify, MotionPath motionPath, HiDataInsertOption hiDataInsertOption) {
        String str;
        if (motionPathSimplify == null || motionPath == null || hiDataInsertOption == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "convertHealthTrackDataToHiData param is null.");
            sqo.q("convertHealthTrackDataToHiData param is null.");
            return;
        }
        motionPathSimplify.setBestPace(calculateBestPace(motionPathSimplify.getPaceMap()));
        LogUtil.a(TAG, "convertHealthTrackDataToHiData, simplifyData ", motionPathSimplify.toString(), "convertHealthTrackDataToHiData, motionData ", motionPath.toString());
        HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
        setVendor(motionPathSimplify, hiTrackMetaData);
        setNormalData(hiTrackMetaData, motionPathSimplify);
        if (motionPathSimplify.getSwimSegments() != null) {
            hiTrackMetaData.setSwimSegments(motionPathSimplify.getSwimSegments());
            hiTrackMetaData.setBritishSwimSegments(motionPathSimplify.getBritishSwimSegments());
        }
        handleCommonSectionMetaData(hiTrackMetaData, motionPathSimplify);
        cheatCheck(motionPathSimplify, motionPath);
        hiTrackMetaData.setAbnormalTrack(motionPathSimplify.requestAbnormalTrack());
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setStartTime(motionPathSimplify.getStartTime());
        hiHealthData.setEndTime(motionPathSimplify.getEndTime());
        hiHealthData.setType(30001);
        hiHealthData.setSequenceData(motionPath.toString());
        wrapMotionSimplyToMeta(motionPathSimplify, hiTrackMetaData);
        setRunningPostureData(motionPathSimplify, hiTrackMetaData);
        if (TextUtils.isEmpty(motionPath.toString())) {
            setDefaultMotionData(motionPath, hiHealthData);
        }
        try {
            hiHealthData.setMetaData(new Gson().toJson(hiTrackMetaData, HiTrackMetaData.class));
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "gson exception:", ExceptionUtils.d(e));
        }
        DeviceInfo currentDeviceInfo = HwExerciseDeviceUtil.getCurrentDeviceInfo();
        if (currentDeviceInfo != null) {
            str = currentDeviceInfo.getSecurityUuid();
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "convertHealthTrackDataToHiData, deviceInfo is null");
            str = "";
        }
        hiHealthData.setDeviceUuid(str + "#ANDROID21");
        hiDataInsertOption.addData(hiHealthData);
    }

    public static void packExerciseData(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (motionPathSimplify == null || motionPath == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "packExerciseData parameter abnormal");
            sqo.q("packExerciseData parameter abnormal");
            return;
        }
        if (HwExerciseParams.getInstance().getTriathlonUtils().isLastData(motionPathSimplify.getSportData().get("record_id").intValue()) && HwExerciseAdviceManager.getInstance().isIsLastSaved()) {
            updateWorkoutSuccessCount();
            ReleaseLogUtil.e(TAG_RELEASE, "packExerciseData last data is saved");
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        initFitnessWorkoutRecord(workoutRecord, motionPathSimplify, motionPath);
        int a2 = CommonUtil.a(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_RECORD_FLAG), 10);
        if (a2 == 6) {
            workoutRecord.saveRecordType(1);
        } else {
            workoutRecord.saveRecordType(0);
        }
        if (a2 == 5 || a2 == 6 || a2 == 1) {
            workoutRecord.saveTrajectoryId(motionPathSimplify.getStartTime() + "_" + motionPathSimplify.getEndTime());
        }
        mmt mmtVar = new mmt();
        makeUpExtendBean(mmtVar, motionPathSimplify);
        if (a2 == 5 || a2 == 6) {
            try {
                packRunPlanExerciseData(motionPathSimplify, workoutRecord, mmtVar);
            } catch (NumberFormatException e) {
                ReleaseLogUtil.c(TAG_RELEASE, "fitnessWorkoutRecord NumberFormatException:", ExceptionUtils.d(e));
            }
        }
        workoutRecord.saveExtend(mmtVar, true);
        LogUtil.a(TAG, "fitnessWorkoutRecord :", workoutRecord.toString());
        sendFitnessWorkoutData(workoutRecord, motionPathSimplify);
    }

    private static void initFitnessWorkoutRecord(WorkoutRecord workoutRecord, MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        int i;
        workoutRecord.saveId(motionPathSimplify.getSportData().get("record_id").intValue());
        workoutRecord.saveWorkoutId(motionPathSimplify.getRuncourseId().trim());
        workoutRecord.saveExerciseTime(motionPathSimplify.getEndTime());
        workoutRecord.setDuration(motionPathSimplify.getExerciseTime() * 1000);
        workoutRecord.saveActualCalorie(motionPathSimplify.getTotalCalories());
        workoutRecord.saveFinishRate(motionPathSimplify.getTargetPercent());
        String str = motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_ACTIVECALORIE);
        LogUtil.a(TAG, "activeCalorie = ", str);
        workoutRecord.setActiveCalorie(CommonUtil.j(str));
        if (motionPathSimplify.getTrackType() == 4) {
            i = 1;
        } else {
            i = motionPathSimplify.getTrackType() == 5 ? 2 : 0;
        }
        workoutRecord.saveWearType(i);
        workoutRecord.saveActionSummary(actionDataList(motionPath).toString());
        workoutRecord.saveHeartRateDataList(motionPath.getHeartRateList());
        workoutRecord.saveInvalidHeartRateList(motionPath.getInvalidHeartRateList());
        workoutRecord.setStartTime(motionPathSimplify.getStartTime());
    }

    private static void makeUpExtendBean(mmt mmtVar, MotionPathSimplify motionPathSimplify) {
        mmtVar.b(motionPathSimplify.requestExtendDataMap().get("isTrustHeartRate"));
        mmtVar.a(motionPathSimplify.getAvgHeartRate());
        mmtVar.c(motionPathSimplify.getStartTime());
        mmtVar.e(motionPathSimplify.getExerciseLevel());
        mmtVar.j(motionPathSimplify.getmHeartrateZoneType());
        mmtVar.d((int) (motionPathSimplify.getTotalTime() / 1000));
    }

    private static void packRunPlanExerciseData(MotionPathSimplify motionPathSimplify, WorkoutRecord workoutRecord, mmt mmtVar) {
        if (motionPathSimplify.getSportType() != 20) {
            workoutRecord.savePlanId(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_PLAN_ID));
            workoutRecord.saveWorkoutName(motionPathSimplify.requestExtendDataMap().get("courseName"));
            workoutRecord.setPlanTrainDate(Integer.parseInt(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_PLAN_COURSE_TIME)));
            workoutRecord.setCourseDefineType(Integer.parseInt(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_RECORD_FLAG)));
            workoutRecord.saveActualDistance(motionPathSimplify.getTotalDistance() / 1000.0f);
            workoutRecord.setTargetType(Integer.parseInt(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_COURSE_TARGET_TYPE)));
            workoutRecord.setTargetValue(Integer.parseInt(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_COURSE_TARGET_VALUE)));
            workoutRecord.setTrainPoint(Integer.parseInt(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_TRAINING_POINTS)));
            workoutRecord.setSportRecordType(1);
            mmtVar.f(Integer.parseInt(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_TRAINING_EXPERIENCE)));
            mmtVar.a(Long.parseLong(motionPathSimplify.requestExtendDataMap().get(HwExerciseConstants.JSON_NAME_COURSE_MODIFIED_TIME)));
            mmtVar.c(motionPathSimplify.getSportType());
        }
    }

    private static void handleCommonSectionMetaData(HiTrackMetaData hiTrackMetaData, MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify.requestExtendDataMap() != null) {
            ReleaseLogUtil.e(TAG_RELEASE, "sectionMetaData targetPercent:", Integer.valueOf(motionPathSimplify.getTargetPercent()));
            motionPathSimplify.addExtendDataMap("completionRate", Integer.toString(motionPathSimplify.getTargetPercent()));
            hiTrackMetaData.setExtendTrackDataMap(motionPathSimplify.requestExtendDataMap());
        }
    }

    private static void setNormalData(HiTrackMetaData hiTrackMetaData, MotionPathSimplify motionPathSimplify) {
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
        hiTrackMetaData.setMaxSpo2(motionPathSimplify.getMaxSpo2());
        hiTrackMetaData.setMinSpo2(motionPathSimplify.getMinSpo2());
        hiTrackMetaData.setSwolfBase(motionPathSimplify.getSwolfBase());
        hiTrackMetaData.setBritishSwolfBase(motionPathSimplify.getBritishSwolfBase());
        hiTrackMetaData.setMaxAlti(motionPathSimplify.getMaxAlti());
        hiTrackMetaData.setMinAlti(motionPathSimplify.getMinAlti());
        hiTrackMetaData.setTotalDescent(motionPathSimplify.getTotalDescent());
        hiTrackMetaData.setHeartrateZoneType(motionPathSimplify.getmHeartrateZoneType());
        hiTrackMetaData.setRuncourseId(motionPathSimplify.getRuncourseId());
        for (Map.Entry<String, String> entry : motionPathSimplify.requestExtendDataMap().entrySet()) {
            if (COORDINATE.equals(entry.getKey())) {
                hiTrackMetaData.setCoordinate(entry.getValue());
                return;
            }
        }
    }

    private static void setDefaultMotionData(MotionPath motionPath, HiHealthData hiHealthData) {
        TreeMap treeMap = new TreeMap();
        treeMap.put(0L, new double[]{90.0d, -80.0d, 0.0d, 0.0d, System.currentTimeMillis()});
        motionPath.setLbsDataMap(treeMap);
        motionPath.setPaceMap(new TreeMap());
        motionPath.setBritishPaceMap(new TreeMap());
        motionPath.setNormalIntervalPaceMap(new TreeMap());
        motionPath.setBritishIntervalPaceMap(new TreeMap());
        motionPath.setHeartRateList(new ArrayList<>());
        motionPath.setInvalidHeartRateList(new ArrayList<>(16));
        motionPath.setStepRateList(new ArrayList<>());
        hiHealthData.setSequenceData(motionPath.toString());
    }

    private static void setVendor(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        String str;
        if (motionPathSimplify.getMapType() == 0) {
            str = "AMAP";
        } else if (motionPathSimplify.getMapType() == 1) {
            str = "GOOGLE";
        } else {
            ReleaseLogUtil.d(TAG_RELEASE, "no match map type.");
            str = null;
        }
        if (str != null) {
            ReleaseLogUtil.e(TAG_RELEASE, "vendor is : ", str);
            hiTrackMetaData.setVendor(str);
        }
    }

    private static float calculateBestPace(Map<Integer, Float> map) {
        float f = 0.0f;
        if (map == null || map.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "calculateBestPace map is empty.");
            return 0.0f;
        }
        Map<Integer, Float> validPaceMap = eme.b().validPaceMap(map);
        if (validPaceMap != null && !validPaceMap.isEmpty()) {
            LogUtil.a(a.t, 0, TAG, "calculateBestPace valid:", validPaceMap.toString());
            f = Float.MAX_VALUE;
            for (Map.Entry<Integer, Float> entry : validPaceMap.entrySet()) {
                if (f > entry.getValue().floatValue()) {
                    f = entry.getValue().floatValue();
                }
            }
            LogUtil.a(TAG, "calculateBestPace,setBestPace :", Float.valueOf(f));
        }
        return f;
    }

    private static void wrapMotionSimplyToMeta(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        hiTrackMetaData.setFatherSportItem(motionPathSimplify.requestFatherSportItem());
        hiTrackMetaData.setChildSportItems(motionPathSimplify.requestChildSportItems());
    }

    private static void setRunningPostureData(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
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

    public static int getHiDataSportType(int i) {
        if (!WorkoutTypeMapManager.getWorkoutTypeMap().containsKey(Integer.valueOf(i)) && !WorkoutTypeMapManager.isNewSportType(i)) {
            return 258;
        }
        int workoutType = WorkoutTypeMapManager.getWorkoutType(i);
        return workoutType == 0 ? i : workoutType;
    }

    public static String getTrackDataToOdmf(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        Object[] objArr = new Object[4];
        objArr[0] = "getTrackDataToOdmf MotionPath is enter, simplifyData is null = ";
        objArr[1] = Boolean.valueOf(motionPathSimplify == null);
        objArr[2] = ", motionData is null = ";
        objArr[3] = Boolean.valueOf(motionPath == null);
        ReleaseLogUtil.e(TAG_RELEASE, objArr);
        if (motionPathSimplify == null || motionPath == null) {
            return "";
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        convertHealthTrackDataToHiData(motionPathSimplify, motionPath, hiDataInsertOption);
        List<HiHealthData> datas = hiDataInsertOption.getDatas();
        int sportType = motionPathSimplify.getSportType();
        String m = HiDateUtil.m(datas.get(0).getStartTime());
        String deviceUuid = datas.get(0).getDeviceUuid();
        long totalTime = motionPathSimplify.getTotalTime() / 1000;
        int totalCalories = motionPathSimplify.getTotalCalories() / 1000;
        String sportStartGps = getSportStartGps(motionPath);
        String sportSpeedDistribution = getSportSpeedDistribution(datas);
        String heartDistribution = getHeartDistribution(motionPath, (int) totalTime);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", sportType);
            jSONObject.put("SportStartTime", m);
            jSONObject.put("DeviceID", deviceUuid);
            jSONObject.put("SportStartGPS", sportStartGps);
            jSONObject.put("SportDuration", totalTime);
            jSONObject.put("HeartDistribution", heartDistribution);
            jSONObject.put("SportSpeedDistribution", sportSpeedDistribution);
            jSONObject.put("HeatQuantity", totalCalories);
            return jSONObject.toString();
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "JSONException:", ExceptionUtils.d(e));
            return null;
        }
    }

    private static String getSportStartGps(MotionPath motionPath) {
        ReleaseLogUtil.e(TAG_RELEASE, "getSportStartGps enter");
        if (motionPath == null) {
            return null;
        }
        Map<Long, double[]> lbsDataMap = motionPath.getLbsDataMap();
        if (lbsDataMap == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "getSportStartGps ,lbsDataMap is null");
            return null;
        }
        if (lbsDataMap.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "getSportStartGps No GPS");
            return null;
        }
        double[] dArr = lbsDataMap.get(0L);
        if (dArr.length < 2) {
            return null;
        }
        double d = dArr[0];
        double d2 = dArr[1];
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(String.valueOf(d2));
        jSONArray.put(String.valueOf(d));
        return jSONArray.toString();
    }

    private static String getSportSpeedDistribution(List<HiHealthData> list) {
        ReleaseLogUtil.e(TAG_RELEASE, "getSportSpeedDistribution enter");
        int[] iArr = {0, 0, 0, 0, 0, 0};
        JSONArray jSONArray = new JSONArray();
        if (list == null || list.isEmpty()) {
            ReleaseLogUtil.d(TAG_RELEASE, "getSportSpeedDistribution params is empty.");
            return jSONArray.toString();
        }
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(list.get(0).getMetaData()).get("paceMap").toString());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next instanceof String) {
                    double d = jSONObject.getDouble(next);
                    if (d < 300.0d) {
                        iArr[0] = iArr[0] + 1;
                    } else if (d < 360.0d) {
                        iArr[1] = iArr[1] + 1;
                    } else if (d < 420.0d) {
                        iArr[2] = iArr[2] + 1;
                    } else if (d < 480.0d) {
                        iArr[3] = iArr[3] + 1;
                    } else if (d < 540.0d) {
                        iArr[4] = iArr[4] + 1;
                    } else {
                        iArr[5] = iArr[5] + 1;
                    }
                }
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "getSportSpeedDistribution JSONException:", ExceptionUtils.d(e));
        }
        for (int i = 0; i < 6; i++) {
            jSONArray.put(iArr[i]);
        }
        return jSONArray.toString();
    }

    private static String getHeartDistribution(MotionPath motionPath, int i) {
        ReleaseLogUtil.e(TAG_RELEASE, "getHeartDistribution enter");
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < 6; i2++) {
            jSONArray.put(0);
        }
        if (motionPath != null) {
            ArrayList<HeartRateData> heartRateList = motionPath.getHeartRateList();
            if (koq.c(heartRateList)) {
                int[] requestHeartInteDuration = HeartRateAndStepsUtils.requestHeartInteDuration(heartRateList);
                if (requestHeartInteDuration == null || requestHeartInteDuration.length < 5) {
                    return jSONArray.toString();
                }
                int i3 = requestHeartInteDuration[0];
                int i4 = requestHeartInteDuration[1];
                int i5 = requestHeartInteDuration[2];
                int i6 = requestHeartInteDuration[3];
                int i7 = requestHeartInteDuration[4];
                int i8 = i3 + i4 + i5 + i6 + i7;
                if (i8 >= i) {
                    i = i8;
                }
                if (i != 0) {
                    jSONArray = new JSONArray();
                    int i9 = (i7 * 100) / i;
                    jSONArray.put(i9);
                    int i10 = (i6 * 100) / i;
                    jSONArray.put(i10);
                    int i11 = (i5 * 100) / i;
                    jSONArray.put(i11);
                    int i12 = (i4 * 100) / i;
                    jSONArray.put(i12);
                    int i13 = (i3 * 100) / i;
                    jSONArray.put(i13);
                    jSONArray.put(((((100 - i13) - i12) - i11) - i10) - i9);
                } else {
                    ReleaseLogUtil.d(TAG_RELEASE, "getHeartDistribution sum is 0");
                }
                return jSONArray.toString();
            }
            ReleaseLogUtil.d(TAG_RELEASE, "getHeartDistribution mHeartRateList is null");
        }
        return jSONArray.toString();
    }

    public static void triggerHiHealthCloutSync() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils.1
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.e(HwExerciseUtils.TAG_RELEASE, "triggerHiHealthCloutSync enter thread");
                HiSyncOption hiSyncOption = new HiSyncOption();
                hiSyncOption.setSyncModel(2);
                hiSyncOption.setSyncAction(2);
                hiSyncOption.setSyncDataType(20000);
                hiSyncOption.setSyncMethod(2);
                HiHealthManager.d(BaseApplication.getContext()).synCloud(hiSyncOption, null);
            }
        });
    }

    public static void wrapRelativeSport(JSONObject jSONObject, RelativeSportData relativeSportData) {
        if (jSONObject == null || relativeSportData == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "wrapRelativeSport params is null.");
            return;
        }
        relativeSportData.setStartTime(jSONObject.optLong("workoutLinkDetailsStarttime", 0L) * 1000);
        relativeSportData.setEndTime(jSONObject.optLong("workoutLinkDetailsEndtime", 0L) * 1000);
        relativeSportData.setSportType(jSONObject.optInt("workoutLinkDetailsType", 0));
        relativeSportData.setChangeIntervalTime(jSONObject.optLong("workoutLinkTransitionTime", 0L) * 1000);
        relativeSportData.setHasDetailInfo(jSONObject.optBoolean("workoutLinkHasDetai", false));
        relativeSportData.setDistance(jSONObject.optInt("workoutLinkDetailsDistance", 0));
        relativeSportData.setDuration(jSONObject.optLong("workoutLinkDetailsTotaltime", 0L) * 1000);
        relativeSportData.setCalories(jSONObject.optInt("workoutLinkDetailsCalorie", 0));
        relativeSportData.setSportType(getHiDataSportType(relativeSportData.getSportType()));
    }

    public static void addTriathlonToMotionPathSimplify(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) {
        JSONObject optJSONObject;
        if (jSONObject == null || motionPathSimplify == null) {
            return;
        }
        try {
            if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE) == 12) {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("triathlonStructList");
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    ReleaseLogUtil.e(TAG_RELEASE, "triathlonStructList.size() is ", Integer.valueOf(optJSONArray.length()));
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        RelativeSportData relativeSportData = new RelativeSportData();
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        relativeSportData.setIndex(i);
                        wrapRelativeSport(optJSONObject2, relativeSportData);
                        addTriathlonTime(relativeSportData, jSONObject);
                        arrayList.add(relativeSportData);
                    }
                }
                LogUtil.a(a.t, 0, TAG, "relativeSportData is : ", arrayList.toString());
                motionPathSimplify.saveChildSportItems(arrayList);
                return;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("triathlonStructList");
            if (optJSONArray2 == null || (optJSONObject = optJSONArray2.optJSONObject(0)) == null) {
                return;
            }
            RelativeSportData relativeSportData2 = new RelativeSportData();
            relativeSportData2.setIndex(0);
            wrapRelativeSport(optJSONObject, relativeSportData2);
            if (relativeSportData2.getStartTime() == jSONObject.getLong(HwExerciseConstants.JSON_NAME_START_TIME)) {
                motionPathSimplify.setStartTime(motionPathSimplify.getStartTime() - 1000);
                ReleaseLogUtil.e(TAG_RELEASE, "main start time is equal,down 1000ms : ", Long.valueOf(motionPathSimplify.getStartTime()));
            }
            LogUtil.a(a.t, 0, TAG, "father sport : ", relativeSportData2.toString());
            motionPathSimplify.saveFatherSportItem(relativeSportData2);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "addTriathlonToMotionPathSimplify JSONException：", ExceptionUtils.d(e));
        }
    }

    private static void addTriathlonTime(RelativeSportData relativeSportData, JSONObject jSONObject) throws JSONException {
        if (relativeSportData.getStartTime() == jSONObject.getLong(HwExerciseConstants.JSON_NAME_START_TIME)) {
            relativeSportData.setStartTime(relativeSportData.getStartTime() - 1000);
            ReleaseLogUtil.e(TAG_RELEASE, "child sport start time is equal,down 1000ms : ", Long.valueOf(relativeSportData.getStartTime()));
        }
    }

    private static void sendFitnessWorkoutData(WorkoutRecord workoutRecord, final MotionPathSimplify motionPathSimplify) {
        if (HwExerciseAdviceManager.getInstance().getSuggestionAidl() == null) {
            sFitnessList.add(workoutRecord);
            HwExerciseAdviceManager.getInstance().registerFitnessRecordCallback(new FitnessRecordCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils.2
                @Override // com.huawei.callback.FitnessRecordCallback
                public void postData() {
                    while (!HwExerciseUtils.sFitnessList.isEmpty()) {
                        HwExerciseUtils.isSaveSuccessFitness((WorkoutRecord) HwExerciseUtils.sFitnessList.poll(), MotionPathSimplify.this);
                    }
                }
            });
            Intent intent = new Intent("com.huawei.health.track.broadcast");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            intent.putExtra("command_type", "SYNC_FITNESS_DATA");
            BaseApplication.getContext().sendBroadcast(intent, SecurityConstant.d);
            return;
        }
        isSaveSuccessFitness(workoutRecord, motionPathSimplify);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void isSaveSuccessFitness(WorkoutRecord workoutRecord, MotionPathSimplify motionPathSimplify) {
        ReleaseLogUtil.e(TAG_RELEASE, "isSaveSuccessFitness enter");
        int workoutRunPlayFailCount = HwExerciseAdviceManager.getInstance().getWorkoutRunPlayFailCount();
        WorkoutSyncSuccessDetailData workoutSyncSuccessDetailData = new WorkoutSyncSuccessDetailData(workoutRecord.acquireId(), workoutRecord.startTime(), workoutRecord.acquireExerciseTime());
        WorkoutSyncSuccessDetailData successRecord = HwExerciseAdviceManagerHelper.getInstance().getSuccessRecord(workoutSyncSuccessDetailData);
        if (successRecord != null) {
            workoutSyncSuccessDetailData = successRecord;
        }
        try {
            try {
                boolean postFitnessRecord = HwExerciseAdviceManager.getInstance().getSuggestionAidl().postFitnessRecord(workoutRecord);
                ReleaseLogUtil.e(TAG_RELEASE, "isSaveSuccessFitness isSaveSuccess:", Boolean.valueOf(postFitnessRecord));
                motionPathSimplify.setIsPackSaveSuccess(postFitnessRecord);
                if (postFitnessRecord) {
                    workoutSyncSuccessDetailData.updateClassDataParameter(true, true);
                    updateWorkoutSuccessCount();
                } else {
                    workoutRunPlayFailCount++;
                    HwExerciseAdviceManager.getInstance().setWorkoutRunPlayFailCount(workoutRunPlayFailCount);
                    workoutSyncSuccessDetailData.updateClassDataParameter(true, false);
                }
            } catch (RemoteException e) {
                motionPathSimplify.setIsPackSaveSuccess(false);
                HwExerciseAdviceManager.getInstance().setWorkoutRunPlayFailCount(workoutRunPlayFailCount + 1);
                workoutSyncSuccessDetailData.updateClassDataParameter(true, false);
                ReleaseLogUtil.c(TAG_RELEASE, "isSaveSuccessFitness RemoteException:", ExceptionUtils.d(e));
            }
        } finally {
            HwExerciseAdviceManagerHelper.getInstance().saveSuccessUpdateSuccessList(workoutSyncSuccessDetailData);
            HwExerciseAdviceManager.getInstance().updateWorkoutSyncCompleteTime();
            HwExerciseAdviceManager.getInstance().changeRecordSyncMark();
        }
    }

    private static void updateWorkoutSuccessCount() {
        HwExerciseAdviceManager.getInstance().setWorkoutCount(HwExerciseAdviceManager.getInstance().getWorkoutCount() + 1);
    }

    private static List<RecordAction> actionDataList(MotionPath motionPath) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList(16);
        List<CommonSegment> commonSegments = motionPath.getCommonSegments();
        ArrayList arrayList2 = new ArrayList();
        for (CommonSegment commonSegment : commonSegments) {
            if (!(commonSegment instanceof kwr)) {
                ReleaseLogUtil.d(TAG_RELEASE, "actionDataList not TrackExerciseSegment data");
                arrayList2.add(commonSegment);
            } else {
                kwr kwrVar = (kwr) commonSegment;
                String trim = kwrVar.e().trim();
                int c = kwrVar.c();
                int b = kwrVar.b();
                int a2 = kwrVar.a();
                if (a2 != 0) {
                    if (a2 != 1) {
                        i2 = a2 != 2 ? kwrVar.a() : 0;
                    } else {
                        i2 = 10;
                    }
                    i = i2;
                } else {
                    i = 1;
                }
                if (TextUtils.isEmpty(trim)) {
                    ReleaseLogUtil.d(TAG_RELEASE, "actionDataList action name is empty");
                    return arrayList;
                }
                arrayList.add(new RecordAction(trim, trim, c, b, i));
            }
        }
        motionPath.setCommonSegments(arrayList2);
        motionPath.setActionSummary(arrayList.toString());
        return arrayList;
    }

    public static void saveTrackData(MotionPathSimplify motionPathSimplify, MotionPath motionPath, int i, TriathlonUtils triathlonUtils, HwExerciseAdviceManager hwExerciseAdviceManager) {
        ReleaseLogUtil.e(TAG_RELEASE, "saveTrackData MotionPath is enter");
        if (motionPathSimplify == null || motionPath == null || triathlonUtils == null || hwExerciseAdviceManager == null) {
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        convertHealthTrackDataToHiData(motionPathSimplify, motionPath, hiDataInsertOption);
        ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).saveTrackDataToOdmf(getTrackDataToOdmf(motionPathSimplify, motionPath));
        String currentDeviceId = HwExerciseDeviceUtil.getCurrentDeviceId();
        if (triathlonUtils.isLastData(i) && HwExerciseAdviceManager.getInstance().isIsLastSaved()) {
            ReleaseLogUtil.d(TAG_RELEASE, "saveTrackData last data is saved");
            updateWorkoutSuccessCount();
            HwExerciseAdviceManager.getInstance().setIsLastSaved(false);
        } else if (triathlonUtils.isTriathlonData(motionPathSimplify)) {
            ReleaseLogUtil.e(TAG_RELEASE, "this data is triathlonData.", Integer.valueOf(motionPathSimplify.getSportType()));
            triathlonUtils.putCache(motionPathSimplify, motionPath, currentDeviceId, i);
        } else {
            ReleaseLogUtil.e(TAG_RELEASE, "ready start time : ", Long.valueOf(motionPathSimplify.getStartTime()), ", end time : ", Long.valueOf(motionPathSimplify.getEndTime()));
            IBaseResponseCallback lastSavedCallback = ((triathlonUtils.isLastData(i) || triathlonUtils.isRunPlayLastData(i)) && triathlonUtils.getCache(currentDeviceId).isEmpty()) ? getLastSavedCallback() : null;
            if (motionPathSimplify.getSportType() == 20) {
                ReleaseLogUtil.e(TAG_RELEASE, "passFitnessData is fitness data");
                packExerciseData(motionPathSimplify, motionPath);
            } else {
                hwExerciseAdviceManager.saveDataToHiHealthData(hiDataInsertOption, motionPathSimplify, lastSavedCallback, i, motionPath);
            }
        }
        if (triathlonUtils.isLastData(i)) {
            ReleaseLogUtil.e(TAG_RELEASE, "enter save data.");
            HashMap<String, List<TriathlonUtils.TriathlonCache>> cache = triathlonUtils.getCache(currentDeviceId);
            if (!cache.isEmpty()) {
                saveTriathlonData(hwExerciseAdviceManager, cache);
            } else {
                HwExerciseAdviceManager.getInstance().updateWorkoutSyncCompleteTime();
                HwExerciseAdviceManager.getInstance().changeRecordSyncMark();
            }
            triathlonUtils.clearCache(currentDeviceId);
            hwExerciseAdviceManager.sendWorkoutSyncEvent(100000);
        }
    }

    private static void cheatCheck(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (motionPathSimplify.getTotalDistance() == 0 && motionPathSimplify.getSportType() == 265) {
            ReleaseLogUtil.d(TAG_RELEASE, "cheatCheck INDOOR_BIKE invalid.");
            return;
        }
        if (motionPathSimplify.getSportType() == 283) {
            ArrayList arrayList = new ArrayList(16);
            if (motionPath.getSkippingSpeedList() == null || motionPath.getSkippingSpeedList().isEmpty()) {
                ReleaseLogUtil.d(TAG_RELEASE, "cheatCheck data invalid.");
                return;
            }
            for (int i = 0; i < motionPath.getSkippingSpeedList().size(); i++) {
                kob kobVar = new kob();
                kobVar.d(motionPath.getSkippingSpeedList().get(i).acquireTime());
                kobVar.c(motionPath.getSkippingSpeedList().get(i).getSpeed());
                arrayList.add(kobVar);
            }
            motionPathSimplify.saveAbnormalTrack(koj.d(calculateAverageSkippingSpeed(motionPathSimplify.getTotalSteps(), motionPathSimplify.getTotalTime()), arrayList));
            return;
        }
        us usVar = new us();
        usVar.c(motionPathSimplify.getAvgPace());
        usVar.b(motionPathSimplify.getPaceMap());
        usVar.c(motionPathSimplify.getSportType());
        usVar.d(motionPathSimplify.getTotalDistance());
        usVar.b(motionPathSimplify.getTotalTime());
        usVar.a(motionPathSimplify.getTrackType());
        usVar.b(motionPathSimplify.getSwimSegments());
        usVar.e(motionPathSimplify.getTotalSteps());
        usVar.d(true);
        usVar.c(motionPathSimplify.getPartTimeMap());
        usVar.a(motionPathSimplify.requestExtendDataMap());
        motionPathSimplify.saveAbnormalTrack(ur.a(usVar));
        motionPathSimplify.addExtendDataMap("STEP_STATICS_EXCEPTION", ur.e(usVar) ? "1" : "0");
    }

    private static int calculateAverageSkippingSpeed(int i, long j) {
        if (j == 0) {
            return 0;
        }
        int round = Math.round((i / ((j * 1.0f) / 1000.0f)) * 60.0f);
        if (round >= 480) {
            return -1;
        }
        return round;
    }

    private static void saveTriathlonData(HwExerciseAdviceManager hwExerciseAdviceManager, HashMap<String, List<TriathlonUtils.TriathlonCache>> hashMap) {
        Set<Map.Entry<String, List<TriathlonUtils.TriathlonCache>>> entrySet = hashMap.entrySet();
        int size = entrySet.size();
        Iterator<Map.Entry<String, List<TriathlonUtils.TriathlonCache>>> it = entrySet.iterator();
        int i = 0;
        IBaseResponseCallback iBaseResponseCallback = null;
        while (it.hasNext()) {
            List<TriathlonUtils.TriathlonCache> value = it.next().getValue();
            if (i == size - 1) {
                iBaseResponseCallback = getLastSavedCallback();
            }
            hwExerciseAdviceManager.saveTriathlonToHiHealthData(value, iBaseResponseCallback);
            i++;
        }
    }

    private static IBaseResponseCallback getLastSavedCallback() {
        return new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (HwExerciseAdviceManager.getInstance().isIsSyncSuccess()) {
                    ReleaseLogUtil.e(HwExerciseUtils.TAG_RELEASE, "start notifyMsg");
                    HwExerciseUtils.sendNotifyBySwitch();
                    HwExerciseAdviceManager.getInstance().setIsSyncSuccess(false);
                }
            }
        };
    }

    public static void sendNotifyBySwitch() {
        jqi.a().getSwitchSetting("motion_path_switch_status", new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e(HwExerciseUtils.TAG_RELEASE, "sendNotifyBySwitch errorCode:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    if (jds.c(String.valueOf(obj), 10) == 1) {
                        jrn.b(BaseApplication.getContext()).d(jrn.b(), jdh.c());
                        return;
                    } else {
                        ReleaseLogUtil.e(HwExerciseUtils.TAG_RELEASE, "sendNotifyBySwitch close");
                        return;
                    }
                }
                jrn.b(BaseApplication.getContext()).d(jrn.b(), jdh.c());
            }
        });
    }

    public static void getPacePointForDistance(List<JSONObject> list, Map<Integer, Map<Long, double[]>> map, Map<Integer, List<Long>> map2) {
        getTimeStampForDistance(list, map, map2, 0);
        getTimeStampForDistance(list, map, map2, 1);
    }

    private static void getTimeStampForDistance(List<JSONObject> list, Map<Integer, Map<Long, double[]>> map, Map<Integer, List<Long>> map2, int i) {
        if (map2 != null && map != null && list != null) {
            try {
                if (!map2.isEmpty()) {
                    for (Map.Entry<Integer, List<Long>> entry : map2.entrySet()) {
                        int intValue = entry.getKey().intValue();
                        ArrayList arrayList = new ArrayList(16);
                        ArrayList arrayList2 = new ArrayList(16);
                        parsePaceMapList(list, i, intValue, arrayList, arrayList2);
                        ArrayList arrayList3 = new ArrayList();
                        List<Long> value = entry.getValue();
                        if (value != null && !value.isEmpty()) {
                            Iterator it = arrayList2.iterator();
                            while (it.hasNext()) {
                                Integer num = (Integer) it.next();
                                if (num.intValue() < value.size() && num.intValue() > 0) {
                                    arrayList3.add(Double.valueOf(value.get(num.intValue() - 1).longValue()));
                                } else {
                                    arrayList3.add(Double.valueOf(value.get(value.size() - 1).longValue()));
                                }
                            }
                            Map<Long, double[]> map3 = map.get(Integer.valueOf(intValue));
                            if (map3 == null) {
                                ReleaseLogUtil.d(TAG_RELEASE, "gpsRecord is null");
                                return;
                            }
                            TreeSet treeSet = new TreeSet(map3.keySet());
                            ArrayList arrayList4 = new ArrayList();
                            parsePointTime(arrayList3, map3, treeSet, arrayList4);
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                JSONObject jSONObject = (JSONObject) arrayList.get(i2);
                                if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX) != 0 && i2 < arrayList4.size()) {
                                    jSONObject.put(HwExerciseConstants.JSON_NAME_POINT_INDEX, arrayList4.get(i2));
                                    LogUtil.a(TAG, HwExerciseConstants.JSON_NAME_POINT_INDEX, arrayList4.get(i2));
                                }
                            }
                        }
                        ReleaseLogUtil.d(TAG_RELEASE, "oldGpsRecord is empty");
                        return;
                    }
                    return;
                }
            } catch (JSONException e) {
                ReleaseLogUtil.c(TAG_RELEASE, "getTimeStampForDistance Exception:", ExceptionUtils.d(e));
                return;
            }
        }
        ReleaseLogUtil.d(TAG_RELEASE, "params is empty.");
    }

    private static void parsePointTime(ArrayList<Double> arrayList, Map<Long, double[]> map, Set<Long> set, ArrayList<Long> arrayList2) {
        Iterator<Double> it = arrayList.iterator();
        while (it.hasNext()) {
            double doubleValue = it.next().doubleValue();
            Iterator<Long> it2 = set.iterator();
            long j = 0;
            double d = 0.0d;
            while (true) {
                if (it2.hasNext()) {
                    Long next = it2.next();
                    double[] dArr = map.get(next);
                    if (dArr.length < 4) {
                        return;
                    }
                    long longValue = next.longValue();
                    double d2 = dArr[3];
                    if (doubleValue > d2) {
                        d = doubleValue - d2;
                        if (longValue == map.size() - 1) {
                            arrayList2.add(Long.valueOf(longValue));
                            break;
                        }
                        j = longValue;
                    } else if (doubleValue == d2) {
                        arrayList2.add(Long.valueOf(longValue));
                    } else if (d < d2 - doubleValue) {
                        arrayList2.add(Long.valueOf(j));
                    } else {
                        arrayList2.add(Long.valueOf(longValue));
                    }
                }
            }
        }
    }

    private static void parsePaceMapList(List<JSONObject> list, int i, int i2, ArrayList<JSONObject> arrayList, ArrayList<Integer> arrayList2) throws JSONException {
        for (JSONObject jSONObject : list) {
            if (i2 == jSONObject.optInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)) {
                JSONArray jSONArray = jSONObject.getJSONArray(HwExerciseConstants.JSON_NAME_PACE_MAP_LIST);
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    if (i == jSONObject2.optInt(HwExerciseConstants.JSON_NAME_UNIT_TYPE, -1)) {
                        arrayList.add(jSONObject2);
                        arrayList2.add(Integer.valueOf(jSONObject2.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX)));
                    }
                }
            }
        }
    }

    public static boolean isRunPlanRecord(int i, JSONObject jSONObject) {
        int optInt;
        if (jSONObject == null) {
            return false;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_RECORD_STRUCT);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                Object obj = jSONArray.get(i2);
                if ((obj instanceof JSONObject) && (optInt = ((JSONObject) obj).optInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_RECORD_ID, -1)) != -1 && i == optInt) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "isRunPlanRecord JSONException:", ExceptionUtils.d(e));
            return false;
        }
    }

    public static void setMapType(int i, MotionPathSimplify motionPathSimplify, Map<Long, double[]> map) {
        if (i != -1) {
            motionPathSimplify.setMapType(i);
            return;
        }
        if (map != null && !map.isEmpty()) {
            Iterator<Map.Entry<Long, double[]>> it = map.entrySet().iterator();
            double[] dArr = null;
            while (it.hasNext() && (dArr = it.next().getValue()) == null) {
            }
            if (dArr != null && dArr.length >= 2) {
                if (ktl.b(dArr[0], dArr[1]) == 1) {
                    motionPathSimplify.setMapType(0);
                    return;
                } else {
                    motionPathSimplify.setMapType(1);
                    return;
                }
            }
            motionPathSimplify.setMapType(i);
            return;
        }
        motionPathSimplify.setMapType(i);
    }

    public static boolean checkSupportWorkoutType(int i) {
        return checkNoEquipmentWorkType(i) || checkEquipmentWorkType(i) || i == 7 || i == 255;
    }

    private static boolean checkEquipmentWorkType(int i) {
        if (i == 134 || i == 135 || i == 6 || i == 8 || i == 5 || i == 12) {
            return true;
        }
        if (!WorkoutTypeMapManager.isNewSportType(i)) {
            return false;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "checkSupportWorkoutType is new 85 sportType");
        return true;
    }

    public static void checkWorkoutDisplayInfo(int i, Map<Long, double[]> map, WorkoutDisplayInfo workoutDisplayInfo, MotionPathSimplify motionPathSimplify) {
        if (map != null && map.size() > 2) {
            workoutDisplayInfo.setHasTrackPoint(true);
        } else {
            workoutDisplayInfo.setHasTrackPoint(false);
        }
        switch (i) {
            case 1:
            case 9:
            case 10:
                workoutDisplayInfo.setWorkoutType(258);
                break;
            case 2:
                workoutDisplayInfo.setWorkoutType(257);
                break;
            case 3:
                workoutDisplayInfo.setWorkoutType(259);
                break;
            case 4:
                workoutDisplayInfo.setWorkoutType(260);
                workoutDisplayInfo.setChiefSportDataType(0);
                workoutDisplayInfo.setFreeMotion(true);
                break;
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
                if (motionPathSimplify.getTotalDistance() > 0) {
                    workoutDisplayInfo.setChiefSportDataType(0);
                    break;
                }
                break;
            case 8:
                workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM);
                workoutDisplayInfo.setChiefSportDataType(0);
                workoutDisplayInfo.setFreeMotion(true);
                break;
            default:
                checkNewWorkoutDisplayInfo(i, workoutDisplayInfo);
                break;
        }
    }

    private static void checkNewWorkoutDisplayInfo(int i, WorkoutDisplayInfo workoutDisplayInfo) {
        if (i == 134) {
            workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
            workoutDisplayInfo.setChiefSportDataType(1);
            workoutDisplayInfo.setFreeMotion(true);
            return;
        }
        if (i == 135) {
            workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE);
            workoutDisplayInfo.setChiefSportDataType(1);
            workoutDisplayInfo.setFreeMotion(true);
            return;
        }
        if (i != 255) {
            switch (i) {
                case 11:
                    workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE);
                    workoutDisplayInfo.setChiefSportDataType(0);
                    workoutDisplayInfo.setFreeMotion(true);
                    break;
                case 12:
                    workoutDisplayInfo.setWorkoutType(512);
                    workoutDisplayInfo.setChiefSportDataType(4);
                    break;
                case 13:
                    workoutDisplayInfo.setWorkoutType(281);
                    break;
                case 14:
                    workoutDisplayInfo.setWorkoutType(282);
                    break;
                default:
                    if (WorkoutTypeMapManager.isNewSportType(i)) {
                        adaptNewSportType(i, workoutDisplayInfo);
                        break;
                    } else {
                        workoutDisplayInfo.setWorkoutType(258);
                        break;
                    }
            }
            return;
        }
        workoutDisplayInfo.setWorkoutType(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT);
        workoutDisplayInfo.setChiefSportDataType(1);
        workoutDisplayInfo.setFreeMotion(true);
    }

    private static void adaptNewSportType(int i, WorkoutDisplayInfo workoutDisplayInfo) {
        if (WorkoutTypeMapManager.getWorkoutTypeMap().containsKey(Integer.valueOf(i))) {
            workoutDisplayInfo.setWorkoutType(WorkoutTypeMapManager.getWorkoutType(i));
        } else {
            workoutDisplayInfo.setWorkoutType(i);
        }
        if (i == 15 || i == 16 || i == 17) {
            workoutDisplayInfo.setChiefSportDataType(0);
            return;
        }
        if (i == 18 || i == 19) {
            workoutDisplayInfo.setChiefSportDataType(6);
            return;
        }
        if (i == 21) {
            workoutDisplayInfo.setChiefSportDataType(7);
            return;
        }
        if (i == 22 || i == 25) {
            workoutDisplayInfo.setChiefSportDataType(8);
            return;
        }
        if (i == 23 || i == 24) {
            workoutDisplayInfo.setChiefSportDataType(9);
        } else if (i == 222) {
            workoutDisplayInfo.setChiefSportDataType(11);
        } else {
            workoutDisplayInfo.setFreeMotion(true);
            workoutDisplayInfo.setChiefSportDataType(1);
        }
    }

    public static Map<Double, Double> changePartTimePaceMapStruct(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        try {
            TreeMap treeMap = new TreeMap();
            int i2 = 0;
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i3);
                JSONArray jSONArray2 = new JSONArray();
                JSONArray jSONArray3 = jSONObject.getJSONArray(HwExerciseConstants.JSON_NAME_PACE_MAP_LIST);
                for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                    if (jSONArray3.get(i4) instanceof JSONObject) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray3.get(i4);
                        if (i == jSONObject2.optInt(HwExerciseConstants.JSON_NAME_UNIT_TYPE, -1)) {
                            jSONArray2.put(jSONObject2);
                        }
                    }
                }
                for (int i5 = 0; i5 < jSONArray2.length(); i5++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i5);
                    if (!jSONObject3.getBoolean(HwExerciseConstants.JSON_NAME_IS_LAST_DISTANCE)) {
                        int i6 = jSONObject3.getInt("distance");
                        i2 += jSONObject3.getInt("pace");
                        treeMap.put(Double.valueOf((i6 * 10000) / 10000.0d), Double.valueOf(i2));
                    }
                }
            }
            return treeMap;
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "changePartTimePaceMapStruct JSONException:", ExceptionUtils.d(e));
            return null;
        }
    }

    public static Map<Integer, Float> changePaceMapStruct(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        LogUtil.a(TAG, "paceArray = ", jSONArray);
        try {
            TreeMap treeMap = new TreeMap();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                Object obj = jSONArray.get(i2);
                if (obj instanceof JSONObject) {
                    JSONArray jSONArray2 = ((JSONObject) obj).getJSONArray(HwExerciseConstants.JSON_NAME_PACE_MAP_LIST);
                    if (i == 0) {
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            setMetricPaceMap(treeMap, jSONArray2, i3);
                        }
                    } else {
                        for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                            setEnglishPaceMap(treeMap, jSONArray2, i4);
                        }
                    }
                }
            }
            return treeMap;
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "changePaceMapStruct JSONException:", ExceptionUtils.d(e));
            return null;
        }
    }

    private static void setEnglishPaceMap(Map<Integer, Float> map, JSONArray jSONArray, int i) throws JSONException {
        JSONObject jSONObject = jSONArray.getJSONObject(i);
        if (jSONObject.optInt(HwExerciseConstants.JSON_NAME_UNIT_TYPE, -1) != 1) {
            return;
        }
        boolean z = jSONObject.getBoolean(HwExerciseConstants.JSON_NAME_IS_LAST_DISTANCE);
        int i2 = jSONObject.getInt("pace");
        int i3 = jSONObject.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX);
        if (!z) {
            map.put(Integer.valueOf((jSONObject.getInt("distance") * ExceptionCode.CRASH_EXCEPTION) + i3), Float.valueOf(i2));
            return;
        }
        int round = (int) Math.round(UnitUtil.e(jSONObject.getInt(HwExerciseConstants.JSON_NAME_LAST_DISTANCE) / 100.0f, 3));
        if (round == 0) {
            return;
        }
        map.put(Integer.valueOf((((jSONObject.getInt("distance") * 100) + round) * 100000) + i3), Float.valueOf(i2));
    }

    private static void setMetricPaceMap(Map<Integer, Float> map, JSONArray jSONArray, int i) throws JSONException {
        JSONObject jSONObject = jSONArray.getJSONObject(i);
        if (jSONObject.optInt(HwExerciseConstants.JSON_NAME_UNIT_TYPE, -1) != 0) {
            return;
        }
        boolean z = jSONObject.getBoolean(HwExerciseConstants.JSON_NAME_IS_LAST_DISTANCE);
        int i2 = jSONObject.getInt("pace");
        int i3 = jSONObject.getInt(HwExerciseConstants.JSON_NAME_POINT_INDEX);
        if (!z) {
            map.put(Integer.valueOf((jSONObject.getInt("distance") * ExceptionCode.CRASH_EXCEPTION) + i3), Float.valueOf(i2));
            return;
        }
        int round = Math.round(jSONObject.getInt(HwExerciseConstants.JSON_NAME_LAST_DISTANCE) / 100.0f);
        if (round == 0) {
            return;
        }
        map.put(Integer.valueOf((((jSONObject.getInt("distance") * 100) + round) * 100000) + i3), Float.valueOf(i2));
    }

    public static void handleSaveAltitudeData(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        if (jSONObject.has("highest_altitude") && jSONObject.getInt("highest_altitude") != Integer.MAX_VALUE) {
            motionPathSimplify.setMaxAlti(jSONObject.getInt("highest_altitude") / 10.0f);
        }
        if (jSONObject.has("lowest_altitude") && jSONObject.getInt("lowest_altitude") != Integer.MIN_VALUE) {
            motionPathSimplify.setMinAlti(jSONObject.getInt("lowest_altitude") / 10.0f);
        }
        if (jSONObject.has("accumulative_drop_height")) {
            motionPathSimplify.setTotalDescent(jSONObject.getInt("accumulative_drop_height"));
        }
    }

    public static void handleSaveBaseInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify, int i, Map<Double, Double> map, Map<Double, Double> map2) throws JSONException {
        double optDouble = jSONObject.optDouble("half_marathon_time");
        double optDouble2 = jSONObject.optDouble(HwExerciseConstants.JSON_NAME_TOTAL_MARATHON_TIME);
        if (map == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleSaveBaseInfo partTimePaceMetricMap is null.");
            return;
        }
        if (map2 == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleSaveBaseInfo partTimePaceEnglishMap is null.");
            return;
        }
        LogUtil.a(TAG, "RunPlan halfMarathonTime: ", Double.valueOf(optDouble), " marathonTotalTime: ", Double.valueOf(optDouble2));
        if (optDouble > 0.0d) {
            map.put(Double.valueOf(21.0975d), Double.valueOf(optDouble));
            map2.put(Double.valueOf(13.1099865d), Double.valueOf(optDouble));
        }
        if (optDouble2 > 0.0d) {
            map.put(Double.valueOf(42.195d), Double.valueOf(optDouble2));
            map2.put(Double.valueOf(26.219973d), Double.valueOf(optDouble2));
        }
        motionPathSimplify.setPartTimeMap(map);
        motionPathSimplify.setBritishPartTimeMap(map2);
        motionPathSimplify.setTotalSteps(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_STEP));
        motionPathSimplify.setTotalTime(i);
    }

    public static void handleSaveSimplifyBaseInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.setStartTime(jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_START_TIME));
        motionPathSimplify.setEndTime(jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_END_TIME));
        LogUtil.c(TAG, "handleSaveSimplifyBaseInfo startTime: ", Long.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_START_TIME)), ", endTime: ", Long.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_END_TIME)));
        motionPathSimplify.setMaxHeartRate(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_MAX_HEART_RATE));
        motionPathSimplify.setMinHeartRate(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_MIN_HEART_RATE));
        motionPathSimplify.setTotalDistance(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DISTANCE));
        motionPathSimplify.setTotalCalories(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_CALORIE) * 1000);
        motionPathSimplify.setCreepingWave(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_CLIMB));
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DISTANCE) == 0 || jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DURATION) == 0) {
            LogUtil.c(TAG, "run record speed: ", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_SPEED)));
            motionPathSimplify.setAvgPace(0.0f);
        } else {
            motionPathSimplify.setAvgPace(1000.0f / ((jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DISTANCE) * 1000.0f) / jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DURATION)));
        }
        if (jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DURATION) != 0) {
            LogUtil.a(TAG, "run record step: ", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_STEP)));
            motionPathSimplify.setAvgStepRate((int) ((jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_STEP) * 60000) / jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_DURATION)));
        }
        if (jSONObject.has("swolf_base_km") && jSONObject.has("swolf_base_mile")) {
            motionPathSimplify.setSwolfBase(jSONObject.getInt("swolf_base_km") / 10.0f);
            motionPathSimplify.setBritishSwolfBase(jSONObject.getInt("swolf_base_mile") / 10.0f);
            LogUtil.a(TAG, "run record setSwolfBase: ", Float.valueOf(jSONObject.getInt("swolf_base_km") / 10.0f), " setBritishSwolfBase: ", Float.valueOf(jSONObject.getInt("swolf_base_mile") / 10.0f));
        }
        motionPathSimplify.addExtendDataMap("eteAlgoKey", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RUN_PLAY_ALG_TYPE)));
    }

    public static Map<String, Integer> handleSaveSportDataMap(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        HashMap hashMap = new HashMap(16);
        hashMap.put("record_id", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ID)));
        hashMap.put("status", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_STATUS)));
        hashMap.put("load_peak", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_LOAD_PEAK)));
        hashMap.put("etraining_effect", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ETRAINING_EFFECT)));
        hashMap.put("extra_poc", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_EPOC)));
        hashMap.put("max_met", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_MAX_MET)));
        hashMap.put("recovery_time", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_RECOVERY_TIME)));
        hashMap.put("achieve_percent", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_SPORT_DATA_KEY_RECORD_ACHIEVE_PERCENT)));
        motionPathSimplify.setSportData(hashMap);
        return hashMap;
    }

    public static String getRecordKey(String str) {
        return HwExerciseParams.getInstance().getUserId() + str;
    }

    public static void handleNotRunToTrackExerciseInfo(JSONObject jSONObject, MotionPathSimplify motionPathSimplify) throws JSONException {
        motionPathSimplify.setStartTime(jSONObject.getLong(HwExerciseConstants.JSON_NAME_START_TIME));
        motionPathSimplify.setEndTime(jSONObject.getLong(HwExerciseConstants.JSON_NAME_END_TIME));
        LogUtil.c(TAG, "handleNotRunToTrackExerciseInfo startTime: ", Long.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_START_TIME)), ", endTime: ", Long.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_END_TIME)));
        motionPathSimplify.setMaxHeartRate(jSONObject.getInt(HwExerciseConstants.JSON_NAME_PEAK_MAX));
        motionPathSimplify.setMinHeartRate(jSONObject.getInt(HwExerciseConstants.JSON_NAME_PEAK_MIN));
        motionPathSimplify.setMaxSpo2(jSONObject.getInt("highestBloodOxygen"));
        motionPathSimplify.setMinSpo2(jSONObject.getInt("lowestBloodOxygen"));
        motionPathSimplify.setTotalDistance(jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE));
        motionPathSimplify.setTotalCalories(jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_CALORIE) * 1000);
        motionPathSimplify.setTotalTime(jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION));
        LogUtil.c(TAG, "exerciseDuration: ", Long.valueOf(jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION)));
        motionPathSimplify.setExerciseTime((int) jSONObject.getLong(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_TOTAL_TIME));
        motionPathSimplify.setCreepingWave((float) jSONObject.getDouble(HwExerciseConstants.JSON_NAME_WORKOUT_CLIMB));
        motionPathSimplify.setTotalSteps(((Integer) jSONObject.get(HwExerciseConstants.JSON_NAME_RECORD_STEP)).intValue());
        if (jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE) == 0 || jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION) == 0) {
            LogUtil.a(TAG, "record speed: ", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_SPEED)));
            motionPathSimplify.setAvgPace(0.0f);
        } else {
            motionPathSimplify.setAvgPace(1000.0f / ((jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_DISTANCE) * 1000.0f) / jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION)));
        }
        if (jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION) != 0) {
            LogUtil.a(TAG, "record step:", Integer.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_STEP)));
            motionPathSimplify.setAvgStepRate((int) ((jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_STEP) * 60000) / jSONObject.getLong(HwExerciseConstants.JSON_NAME_EXERCISE_DURATION)));
        }
        motionPathSimplify.addExtendDataMap("crossTrainerCadence", Integer.toString(motionPathSimplify.getAvgStepRate() / 2));
        motionPathSimplify.addExtendDataMap("eteAlgoKey", String.valueOf(jSONObject.getInt(HwExerciseConstants.JSON_NAME_ALG_TYPE)));
    }

    public static void handleNotRunToTrackDisplayInfo(JSONObject jSONObject, Map<Long, double[]> map, MotionPathSimplify motionPathSimplify, WorkoutDisplayInfo workoutDisplayInfo) throws JSONException {
        checkWorkoutDisplayInfo(jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE), map, workoutDisplayInfo, motionPathSimplify);
        motionPathSimplify.setChiefSportDataType(workoutDisplayInfo.getChiefSportDataType());
        motionPathSimplify.setIsFreeMotion(workoutDisplayInfo.getFreeMotion());
        motionPathSimplify.setSportType(workoutDisplayInfo.getWorkoutType());
        motionPathSimplify.setHasTrackPoint(workoutDisplayInfo.isHasTrackPoint());
        int i = jSONObject.getInt(HwExerciseConstants.JSON_NAME_RECORD_FLAG);
        if (i == 1 || i == 5 || i == 6) {
            motionPathSimplify.setSportDataSource(4);
        } else if (i == 2) {
            motionPathSimplify.setSportDataSource(6);
        } else {
            if (i == 4) {
                motionPathSimplify.addExtendDataMap("intervalRun", "1");
            }
            motionPathSimplify.setSportDataSource(0);
        }
        LogUtil.c(TAG, "workoutType: ", Integer.valueOf(workoutDisplayInfo.getWorkoutType()));
    }

    public static void getPaceIndexArray(int i, int i2) {
        HwExerciseParams hwExerciseParams = HwExerciseParams.getInstance();
        if (i2 == -1) {
            PaceIndexStruct paceIndexStruct = new PaceIndexStruct();
            paceIndexStruct.setRecordId(i);
            paceIndexStruct.setPaceIndex(-1);
            hwExerciseParams.getWorkoutRecordPaceMapIdList().add(paceIndexStruct);
            return;
        }
        if (i2 <= 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "no condition");
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            PaceIndexStruct paceIndexStruct2 = new PaceIndexStruct();
            paceIndexStruct2.setRecordId(i);
            paceIndexStruct2.setPaceIndex(i3);
            hwExerciseParams.getWorkoutRecordPaceMapIdList().add(paceIndexStruct2);
        }
    }

    public static void writeTrackTimeToList(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "pathSimplify is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put(Long.valueOf(motionPathSimplify.getStartTime()), Long.valueOf(motionPathSimplify.getEndTime()));
        jfc.c(BaseApplication.getContext(), hashMap, KeyValDbManager.b(BaseApplication.getContext()).e("user_id"), new StorageDataCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseUtils.5
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                if (storageResult != null) {
                    ReleaseLogUtil.e(HwExerciseUtils.TAG_RELEASE, "writeTrackTimeToList result:", Integer.valueOf(storageResult.d()));
                }
            }
        });
    }
}
