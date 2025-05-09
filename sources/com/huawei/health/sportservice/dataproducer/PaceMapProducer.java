package com.huawei.health.sportservice.dataproducer;

import android.text.TextUtils;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ffd;
import defpackage.fgm;
import defpackage.fht;
import defpackage.jdx;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SportComponentType(classify = 2, name = ComponentName.PACE_MAP_PRODUCER)
/* loaded from: classes8.dex */
public class PaceMapProducer extends BaseProducer implements SportLifecycle, ITargetUpdateListener {
    private static final int PACE_DISTANCE_INTERVAL = 1000;
    private static final int ROW_DISTANCE_INTERVAL = 500;
    private static final String R_TAG = "R_SportService_PaceMapProducer";
    private static final int START_DISTANCE = 20;
    private static final int START_RECORD_TOKEN = 1;
    private static final String TAG = "SportService_PaceMapProducer";
    private static final int TEN_MILLION_TIMES_GAIN_FOR_KEY = 10000000;
    private static final float YARD_TO_MILE = 5.68E-4f;
    private long mCurrentTime;
    private int mDistance;
    private String mPaceZoneConfig;
    private int mPaceMapInterval = 1000;
    private float mBritishPaceInterval = 1000.0f;
    private int mStartDistance = -1;
    private double mStartRecordDistance = 1.0d;
    private double mStartRecordMileDistance = 1.0d;
    private int mKiloMeterCount = 1;
    private int mMileCount = 1;
    private long mStartTimeWhenStartComputePace = -1;
    private long mStartTimeWhenStartComputePaceForMile = -1;
    private final ConcurrentHashMap<Integer, Float> mPaceMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Float> mBritishPaceMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Double, Double> mPartTimeMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Double, Double> mBritishPartTimeMap = new ConcurrentHashMap<>();
    private fgm mSportCallbackOption = new fgm();
    private boolean mIsHasDetectStart = false;

    private boolean isSupportPaceInterval(int i) {
        return i == 264 || i == 258 || i == 280;
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        calculatePaceMapInterval();
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("DISTANCE_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.PaceMapProducer$$ExternalSyntheticLambda1
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                PaceMapProducer.this.m439x187cfe34(list, map);
            }
        });
        BaseSportManager.getInstance().subscribeTargetStatus(TAG, this);
        getPaceZoneConfig();
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-PaceMapProducer, reason: not valid java name */
    /* synthetic */ void m439x187cfe34(List list, Map map) {
        if (map.containsKey("DISTANCE_DATA") && list.contains("TIME_ONE_SECOND_DURATION")) {
            this.mCurrentTime = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue() / 1000;
            this.mDistance = ((Integer) map.get("DISTANCE_DATA")).intValue();
            if (this.mIsHasDetectStart) {
                handlePartTimeMap();
                handlePaceMap();
            }
        }
    }

    private void getPaceZoneConfig() {
        jdx.b(new Runnable() { // from class: com.huawei.health.sportservice.dataproducer.PaceMapProducer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PaceMapProducer.this.m438x6d432142();
            }
        });
    }

    /* renamed from: lambda$getPaceZoneConfig$1$com-huawei-health-sportservice-dataproducer-PaceMapProducer, reason: not valid java name */
    /* synthetic */ void m438x6d432142() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_RunningPaceZone_Config");
        if (userPreference != null) {
            String value = userPreference.getValue();
            this.mPaceZoneConfig = value;
            LogUtil.a(TAG, "mPaceZoneConfig:", value);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.mStartDistance = this.mDistance;
        detectStartDistance();
        this.mIsHasDetectStart = true;
        ReleaseLogUtil.e(R_TAG, "onStartSport, mIsHasDetectStart is true");
    }

    private void detectStartDistance() {
        long j;
        double floor;
        if (!(BaseSportManager.getInstance().getData("DISTANCE_MILE_DATA") instanceof Float)) {
            ReleaseLogUtil.e(R_TAG, "object instanceof Float is false");
            return;
        }
        float floatValue = ((Float) BaseSportManager.getInstance().getData("DISTANCE_MILE_DATA")).floatValue();
        int i = this.mStartDistance;
        if (i < 1000) {
            this.mKiloMeterCount = 1;
            this.mMileCount = 1;
            this.mStartTimeWhenStartComputePace = 0L;
            this.mStartTimeWhenStartComputePaceForMile = 0L;
        } else {
            this.mKiloMeterCount = (i / this.mPaceMapInterval) + 1;
            float f = this.mBritishPaceInterval * 1000.0f;
            this.mMileCount = ((((int) (f * floatValue)) - 1) / ((int) f)) + 2;
            LogUtil.a(TAG, "mStartDistance", Integer.valueOf(i), "mKiloMeterCount", Integer.valueOf(this.mKiloMeterCount));
        }
        int i2 = this.mStartDistance;
        if (i2 > 20) {
            int i3 = this.mPaceMapInterval;
            if (i2 % i3 < 20) {
                double floor2 = Math.floor((i2 * 1.0d) / i3);
                this.mStartRecordDistance = floor2;
                LogUtil.a(TAG, "mStartRecordDistance", Double.valueOf(floor2));
                j = (long) (this.mPaceMapInterval * floatValue);
                floor = Math.floor(floatValue);
                if (j <= 20 && j % this.mPaceMapInterval < 20) {
                    this.mStartRecordMileDistance = floor;
                    LogUtil.a(TAG, "mStartRecordMileDistance", Double.valueOf(floor));
                    return;
                } else {
                    double d = floor + 1.0d;
                    this.mStartRecordMileDistance = d;
                    LogUtil.a(TAG, "mStartRecordMileDistance e", Double.valueOf(d));
                }
            }
        }
        this.mStartRecordDistance = Math.floor((i2 * 1.0d) / this.mPaceMapInterval) + 1.0d;
        LogUtil.a(TAG, "mStartRecordDistance e", Double.valueOf(this.mStartRecordMileDistance));
        j = (long) (this.mPaceMapInterval * floatValue);
        floor = Math.floor(floatValue);
        if (j <= 20) {
        }
        double d2 = floor + 1.0d;
        this.mStartRecordMileDistance = d2;
        LogUtil.a(TAG, "mStartRecordMileDistance e", Double.valueOf(d2));
    }

    private void calculatePaceMapInterval() {
        if (BaseSportManager.getInstance().getSportType() == 274) {
            this.mPaceMapInterval = 500;
            this.mBritishPaceInterval = 56.8f;
        } else {
            this.mPaceMapInterval = 1000;
            this.mBritishPaceInterval = 1000.0f;
        }
    }

    private void updatePaceMapOfMile() {
        float f = 0.0f;
        if (this.mBritishPaceMap.size() > 0) {
            Iterator<Float> it = this.mBritishPaceMap.values().iterator();
            while (it.hasNext()) {
                f += it.next().floatValue();
            }
        }
        int i = this.mMileCount * 10000000;
        float f2 = (this.mCurrentTime - this.mStartTimeWhenStartComputePaceForMile) - f;
        this.mBritishPaceMap.put(Integer.valueOf(i), Float.valueOf(f2));
        ReleaseLogUtil.e(R_TAG, "british updatePaceMapOfMile mapKey", Integer.valueOf(i), "mapValue", Float.valueOf(f2));
        this.mMileCount++;
    }

    private void recordPartTimeMapData(double d, double d2, Map<Double, Double> map) {
        if (map == null) {
            return;
        }
        double floor = Math.floor(d);
        if (floor < d2) {
            return;
        }
        LogUtil.c(TAG, "handlePartTimeMap,can record");
        double longValue = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION") instanceof Long ? (((Long) r7).longValue() * 1.0d) / 1000.0d : 0.0d;
        if (map.get(Double.valueOf(floor)) == null) {
            map.put(Double.valueOf(floor), Double.valueOf(longValue));
            LogUtil.a(TAG, "update part result", Double.valueOf(floor), "durationTimeUsed", Double.valueOf(longValue));
        } else {
            LogUtil.c(TAG, "handlePartTimeMap,do not need record!");
        }
    }

    private void handlePartTimeMap() {
        double intValue = (((Integer) BaseSportManager.getInstance().getData("DISTANCE_DATA")).intValue() * 1.0d) / this.mPaceMapInterval;
        LogUtil.a(TAG, "update part time map", Double.valueOf(intValue));
        recordPartTimeMapData(intValue, this.mStartRecordDistance, this.mPartTimeMap);
        double floatValue = BaseSportManager.getInstance().getData("DISTANCE_MILE_DATA") instanceof Float ? ((Float) r2).floatValue() : 0.0d;
        if (BaseSportManager.getInstance().getSportType() == 274) {
            floatValue = intValue / (this.mBritishPaceInterval * 1000.0f);
        }
        recordPartTimeMapData(floatValue, this.mStartRecordMileDistance, this.mBritishPartTimeMap);
    }

    private void handlePaceMap() {
        int i = this.mDistance / this.mPaceMapInterval;
        int i2 = this.mKiloMeterCount - 1;
        if (i == i2 && this.mStartTimeWhenStartComputePace == -1) {
            this.mStartTimeWhenStartComputePace = this.mCurrentTime;
            LogUtil.a(TAG, "mKiloMeterCount- 1 ", Integer.valueOf(i2));
            LogUtil.a(TAG, "mStartTimeWhenStartComputePace", Long.valueOf(this.mStartTimeWhenStartComputePace));
        }
        Object data = BaseSportManager.getInstance().getData("DISTANCE_MILE_DATA");
        if (data instanceof Float) {
            float floatValue = ((Float) data).floatValue();
            int i3 = (int) floatValue;
            int i4 = this.mMileCount - 1;
            if (i3 == i4 && this.mStartTimeWhenStartComputePaceForMile == -1) {
                this.mStartTimeWhenStartComputePaceForMile = this.mCurrentTime;
                LogUtil.a(TAG, "mMileCount - IndoorEquipmentConstants.NUMBER_ONE_FOR_PUBLIC_USE", Integer.valueOf(i4));
                LogUtil.a(TAG, "mStartTimeWhenStartComputePaceForMile", Long.valueOf(this.mStartTimeWhenStartComputePaceForMile));
            }
            if (this.mDistance / this.mPaceMapInterval >= this.mKiloMeterCount) {
                float f = 0.0f;
                if (this.mPaceMap.size() > 0) {
                    Iterator<Float> it = this.mPaceMap.values().iterator();
                    while (it.hasNext()) {
                        f += it.next().floatValue();
                    }
                }
                int i5 = this.mKiloMeterCount * 10000000;
                float f2 = (this.mCurrentTime - this.mStartTimeWhenStartComputePace) - f;
                ReleaseLogUtil.e(R_TAG, "mapKey ", Integer.valueOf(i5), "mapValue", Float.valueOf(f2), "durationTimeUsed", Long.valueOf(this.mCurrentTime), "secondOfLastKm", Float.valueOf(f));
                this.mPaceMap.put(Integer.valueOf(i5), Float.valueOf(f2));
                this.mKiloMeterCount++;
                if (BaseSportManager.getInstance().getSportType() == 274) {
                    int i6 = (this.mKiloMeterCount - 1) * 10000000;
                    if (this.mPaceMap.containsKey(Integer.valueOf(i6))) {
                        f2 += this.mPaceMap.get(Integer.valueOf(i6)).floatValue();
                    }
                }
                BaseSportManager.getInstance().stagingAndNotification("LAST_KILOMETER_PACE_DATA", Long.valueOf(Float.valueOf(f2).longValue()));
            }
            if (BaseSportManager.getInstance().getSportType() == 274) {
                float f3 = (floatValue / this.mBritishPaceInterval) * 1000.0f;
                LogUtil.a(TAG, "paceInterval", Float.valueOf(f3), "mTotalDistanceMileValid", Float.valueOf(floatValue));
                floatValue = f3;
            }
            if (floatValue >= this.mMileCount) {
                updatePaceMapOfMile();
                return;
            }
            return;
        }
        LogUtil.b(TAG, "return");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        saveLastPaceMap();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    private void saveLastPaceMap() {
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        if (data instanceof Long) {
            this.mCurrentTime = ((Long) data).longValue() / 1000;
        }
        Object data2 = BaseSportManager.getInstance().getData("DISTANCE_DATA");
        if (data2 instanceof Integer) {
            this.mDistance = ((Integer) data2).intValue();
        }
        LogUtil.h(TAG, "saveLastPaceMap mCurrentTime = ", Long.valueOf(this.mCurrentTime), " mDistance = ", Integer.valueOf(this.mDistance));
        handlePartTimeMap();
        handlePaceMap();
        onSaveData();
    }

    private void calculatePace(MotionPathSimplify motionPathSimplify) {
        if (this.mDistance - this.mStartDistance == 0) {
            LogUtil.h(TAG, "avg pace is 0 (no distance)");
            motionPathSimplify.saveAvgPace(0.0f);
        } else {
            float f = (this.mCurrentTime * (BaseSportManager.getInstance().getSportType() == 274 ? 500.0f : 1000.0f)) / this.mDistance;
            LogUtil.a(TAG, "avg pace is ", Float.valueOf(f));
            motionPathSimplify.saveAvgPace(f);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        if (BaseSportManager.getInstance().getSportMode().equals("291")) {
            return;
        }
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        int sportType = BaseSportManager.getInstance().getSportType();
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            motionPath.savePaceMap(this.mPaceMap);
            motionPath.saveBritishPaceMap(this.mBritishPaceMap);
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
        if (data2 instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
            motionPathSimplify.savePaceMap(this.mPaceMap);
            motionPathSimplify.savePartTimeMap(this.mPartTimeMap);
            motionPathSimplify.saveBritishPartTimeMap(this.mBritishPartTimeMap);
            motionPathSimplify.saveBritishPaceMap(this.mBritishPaceMap);
            if (isSupportPaceInterval(sportType) && !TextUtils.isEmpty(this.mPaceZoneConfig)) {
                HashMap hashMap = new HashMap(1);
                hashMap.put("runPaceZoneConfig", this.mPaceZoneConfig);
                motionPathSimplify.saveExtendDataMap(hashMap);
            }
            calculatePace(motionPathSimplify);
            Map<Integer, Float> e = fht.e(this.mPaceMap);
            if (e != null && e.size() > 0) {
                motionPathSimplify.saveBestPace(fht.d(e)[0].floatValue());
            }
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        this.mIsHasDetectStart = true;
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (!(data instanceof MotionPathSimplify)) {
            LogUtil.b(TAG, "recovery failed. data not simplify type");
            return;
        }
        MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
        if (motionPathSimplify.requestPaceMap() != null) {
            this.mPaceMap.putAll(motionPathSimplify.requestPaceMap());
        }
        if (motionPathSimplify.requestPartTimeMap() != null) {
            this.mPartTimeMap.putAll(motionPathSimplify.requestPartTimeMap());
        }
        if (motionPathSimplify.requestBritishPaceMap() != null) {
            this.mBritishPaceMap.putAll(motionPathSimplify.requestBritishPaceMap());
        }
        if (motionPathSimplify.requestBritishPartTimeMap() != null) {
            this.mBritishPartTimeMap.putAll(motionPathSimplify.requestBritishPartTimeMap());
        }
        this.mKiloMeterCount = this.mPaceMap.size() + 1;
        this.mMileCount = this.mBritishPaceMap.size() + 1;
        this.mStartTimeWhenStartComputePace = 0L;
        this.mStartTimeWhenStartComputePaceForMile = 0L;
        ReleaseLogUtil.e(R_TAG, "recovery pace map. kilo count ", Integer.valueOf(this.mKiloMeterCount), ", mile count ", Integer.valueOf(this.mMileCount));
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        if (i == 200) {
            saveLastPaceMap();
            Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
            if (data instanceof MotionPathSimplify) {
                MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
                motionPathSimplify.correctTotalTime(TAG);
                BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
            }
        }
    }
}
