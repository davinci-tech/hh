package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.cei;
import defpackage.ckh;
import defpackage.fgm;
import defpackage.kob;
import defpackage.koj;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.SKIP_PRODUCER)
/* loaded from: classes8.dex */
public class SkipProducer extends SkipBaseProducer implements SportLifecycle {
    private static final int DEFAULT_INVALIDATION_DATA = -1;
    private static final int HASHMAP_INSTANTANEOUS_VALUE_DEFAULT_SIZE = 11;
    private static final int HASHMAP_INSTANTANEOUS_VALUE_MAX_SIZE = 10;
    private static final int INITIAL_TIME = -1;
    private static final int INTERVAL_FIVE = 5;
    private static final int INTERVAL_THREE = 3;
    private static final int MAP_INDEX_EIGHT = 8;
    private static final int MAP_INDEX_FOUR = 4;
    private static final int MAP_INDEX_NINE = 9;
    private static final int MAP_INDEX_SEVEN = 7;
    private static final int ROPE_PAUSE_HASHMAP_CLEAR_TIME = 5;
    private static final String TAG = "SportService_SkipProducer";
    private static final int TIME_INTERVAL = 1;
    private int mContinuousJumpTimes;
    private volatile int mExtraInterruptTimes;
    private int mInterruptTimes;
    private boolean mIsSupportPerformanceRope;
    private int mMaxContinuousJumpTimes;
    private long mMaxJumpTimePoint;
    private ckh mRopeSkippingTrick;
    private float mSkipSpeed;
    private int mSkippingTimes;
    private SportLaunchParams mSportLaunchParams;
    private int mLastSkipperTime = -1;
    private int mSportTarget = -1;
    private int mSkippingMode = -1;
    private Map<Integer, Integer> mSkipperNumberMap = new HashMap(11);
    private CopyOnWriteArrayList<kob> mSkippingSpeedList = new CopyOnWriteArrayList<>();
    private fgm mSportCallbackOption = new fgm();
    private LinkedList<Integer> mSkipSpeedLink = new LinkedList<>();

    static /* synthetic */ int access$808(SkipProducer skipProducer) {
        int i = skipProducer.mExtraInterruptTimes;
        skipProducer.mExtraInterruptTimes = i + 1;
        return i;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
        this.mSportLaunchParams = sportLaunchParams;
        if (sportLaunchParams == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.mIsSupportPerformanceRope = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).isSupportPerformanceByProductId(cei.b().getProductId());
        BaseSportManager.getInstance().stagingAndNotification("IsSecondRope", Boolean.valueOf(this.mIsSupportPerformanceRope));
        int sportTarget = this.mSportLaunchParams.getSportTarget();
        this.mSportTarget = sportTarget;
        if (sportTarget != 8) {
            arrayList.add("TIME_FIVE_SECOND_DURATION");
        }
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("SKIP_NUM_DATA");
        arrayList.add("STUMBLING_ROPE_DATA");
        arrayList.add("INSTANTANEOUS_SPEED");
        arrayList.add("CONTINUOUS_SKIPPING_JUMP_DATA");
        arrayList.add("ROPE_SKIPPING_MODE_DATA");
        arrayList.add("SKIPPING_SPEED_SECONDS_DATA");
        arrayList.add("ROPE_SKIPPING_TRICK_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SkipFiveSecond());
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        if (BaseSportManager.getInstance().getDataSource() == 7) {
            int i = this.mInterruptTimes + 1;
            this.mInterruptTimes = i;
            LogUtil.a(TAG, "onResumeSport() mInterruptTimes: ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.mSkipperNumberMap.clear();
        BaseSportManager.getInstance().stagingAndNotification("SKIP_SPEED_DATA", 0);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        if (this.mIsSupportPerformanceRope && this.mSportTarget != 8) {
            stopCalculateSpeedPerformanceScore();
        }
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    private int checkSkipTimeToGoal(int i) {
        int targetValue = (int) BaseSportManager.getInstance().getTargetValue();
        return (this.mSportTarget != 5 || i <= targetValue) ? i : targetValue;
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        LogUtil.a(TAG, "onStopSport skip start onSaveData()");
        HashMap hashMap = new HashMap();
        if (this.mSportTarget == 7) {
            addPatternRopeMap(hashMap);
        }
        int i = this.mSkippingMode;
        if (i != -1) {
            hashMap.put("skippingMode", String.valueOf(i));
        }
        hashMap.put("maxSkippingTimes", String.valueOf(this.mMaxContinuousJumpTimes));
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        int calculateAverageSkippingSpeed = calculateAverageSkippingSpeed(this.mSkippingTimes, data instanceof Long ? ((Long) data).longValue() : 0L);
        boolean z = this.mSportTarget == 8;
        if (!z) {
            hashMap.put("skipSpeed", String.valueOf(calculateAverageSkippingSpeed));
            hashMap.put("interruptTimes", String.valueOf(adjustFinalInterruptTimes()));
            hashMap.put("skipNum", String.valueOf(checkSkipTimeToGoal(this.mSkippingTimes)));
        }
        hashMap.put("maxJumpsTime", String.valueOf(this.mMaxJumpTimePoint));
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data2 instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
            if (!z) {
                motionPathSimplify.saveAbnormalTrack(koj.d(calculateAverageSkippingSpeed, this.mSkippingSpeedList));
            }
            motionPathSimplify.saveExtendDataMap(hashMap);
            motionPathSimplify.saveHasTrackPoint(false);
            motionPathSimplify.saveSportDataSource(BaseSportManager.getInstance().getDataSource() != 5 ? 7 : 5);
            motionPathSimplify.saveChiefSportDataType(7);
            motionPathSimplify.saveTrackType(0);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
        Object data3 = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data3 instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data3;
            if (!z) {
                motionPath.saveSkippingSpeed(this.mSkippingSpeedList);
            }
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
    }

    private void addPatternRopeMap(Map<String, String> map) {
        ckh ckhVar = this.mRopeSkippingTrick;
        if (ckhVar != null) {
            map.put("singleShakeNum", String.valueOf(ckhVar.d()));
            map.put("doubleShakeNum", String.valueOf(this.mRopeSkippingTrick.b()));
            map.put("tripleShakeNum", String.valueOf(this.mRopeSkippingTrick.e()));
        }
    }

    class SkipFiveSecond implements SportDataNotify {
        private SkipFiveSecond() {
        }

        @Override // com.huawei.health.sportservice.SportDataNotify
        public void onChange(List<String> list, Map<String, Object> map) {
            if (list.contains("TIME_FIVE_SECOND_DURATION")) {
                if (SkipProducer.this.mSkipSpeed >= 0.0f) {
                    SkipProducer.this.mSkippingSpeedList.add(new kob(((Long) map.get("TIME_FIVE_SECOND_DURATION")).longValue(), Math.round(SkipProducer.this.mSkipSpeed)));
                    return;
                }
                return;
            }
            if (list.contains("TIME_ONE_SECOND_DURATION")) {
                if (SkipProducer.this.mSportTarget == 8) {
                    return;
                }
                Object obj = map.get("SKIP_NUM_DATA");
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
                    long longValue = data instanceof Long ? ((Long) data).longValue() : 0L;
                    BaseSportManager.getInstance().stagingAndNotification("SKIPPING_AVG_SPEED_DATA", Integer.valueOf(SkipProducer.this.calculateAverageSkippingSpeed(intValue, longValue)));
                    if (SkipProducer.this.mSportTarget == 7) {
                        SkipProducer.this.mSkipSpeed = r8.calculateInstantaneousValue(longValue / 1000);
                        LogUtil.a(SkipProducer.TAG, "getInstantaneousValue mSkipSpeed: ", Float.valueOf(SkipProducer.this.mSkipSpeed));
                        BaseSportManager.getInstance().stagingAndNotification("SKIP_SPEED_DATA", Integer.valueOf((int) SkipProducer.this.mSkipSpeed));
                        SkipProducer skipProducer = SkipProducer.this;
                        skipProducer.calculateSpeedPerformanceScore(longValue, skipProducer.mSkippingTimes);
                        SkipProducer.this.calculateEnduranceValue();
                        return;
                    }
                    SkipProducer.this.mSkipSpeed = r2.getInstantaneousValue(intValue, longValue);
                    LogUtil.a(SkipProducer.TAG, "getInstantaneousValue mSkipSpeed: ", Float.valueOf(SkipProducer.this.mSkipSpeed));
                    BaseSportManager.getInstance().stagingAndNotification("SKIP_SPEED_DATA", Integer.valueOf((int) SkipProducer.this.mSkipSpeed));
                    SkipProducer skipProducer2 = SkipProducer.this;
                    skipProducer2.calculatePerformanceScore(map, (int) skipProducer2.mSkipSpeed);
                    return;
                }
                return;
            }
            onChangeSplit(list, map);
        }

        private void onChangeSplit(List<String> list, Map<String, Object> map) {
            if (list.contains("SKIP_NUM_DATA")) {
                Object obj = map.get("SKIP_NUM_DATA");
                if (obj instanceof Integer) {
                    SkipProducer.this.mSkippingTimes = ((Integer) obj).intValue();
                    LogUtil.a(SkipProducer.TAG, "SkipFiveSecond mSkippingTimes: ", Integer.valueOf(SkipProducer.this.mSkippingTimes));
                    SkipProducer.this.mExtraInterruptTimes = 0;
                    return;
                }
                return;
            }
            if (list.contains("STUMBLING_ROPE_DATA")) {
                Object obj2 = map.get("STUMBLING_ROPE_DATA");
                if (obj2 instanceof Integer) {
                    SkipProducer.this.mInterruptTimes = ((Integer) obj2).intValue();
                    SkipProducer.this.mSkipperNumberMap.clear();
                    LogUtil.a(SkipProducer.TAG, "SkipFiveSecond mInterruptTimes: ", Integer.valueOf(SkipProducer.this.mInterruptTimes));
                    SkipProducer.access$808(SkipProducer.this);
                    return;
                }
                return;
            }
            if (list.contains("ROPE_SKIPPING_MODE_DATA")) {
                Object obj3 = map.get("ROPE_SKIPPING_MODE_DATA");
                if (obj3 instanceof Integer) {
                    SkipProducer.this.mSkippingMode = ((Integer) obj3).intValue();
                    return;
                }
                return;
            }
            if (list.contains("ROPE_SKIPPING_TRICK_DATA")) {
                Object obj4 = map.get("ROPE_SKIPPING_TRICK_DATA");
                if (obj4 instanceof ckh) {
                    SkipProducer.this.mRopeSkippingTrick = (ckh) obj4;
                    return;
                }
                return;
            }
            if (list.contains("CONTINUOUS_SKIPPING_JUMP_DATA")) {
                Object obj5 = map.get("CONTINUOUS_SKIPPING_JUMP_DATA");
                if (obj5 instanceof Integer) {
                    SkipProducer.this.mContinuousJumpTimes = ((Integer) obj5).intValue();
                    SkipProducer.this.calculateContinuousJumpsTime();
                    return;
                }
                return;
            }
            if (list.contains("INSTANTANEOUS_SPEED")) {
                Object obj6 = map.get("INSTANTANEOUS_SPEED");
                if (SkipProducer.this.mSportTarget != 7) {
                    return;
                }
                if (SkipProducer.this.mSpeedList == null) {
                    SkipProducer.this.mSpeedList = new CopyOnWriteArrayList<>();
                }
                if (obj6 instanceof Integer) {
                    SkipProducer.this.mSpeedList.add(Integer.valueOf(((Integer) obj6).intValue()));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateInstantaneousValue(long j) {
        int intValue = ((Integer) BaseSportManager.getInstance().getData("INSTANTANEOUS_SPEED")).intValue();
        if (j > 6) {
            this.mSkipSpeedLink.addLast(Integer.valueOf(intValue));
            if (this.mSkipSpeedLink.size() <= 3) {
                return intValue;
            }
            this.mSkipSpeedLink.removeFirst();
        } else {
            if (j != 6) {
                if (j > 3) {
                    this.mSkipSpeedLink.addLast(Integer.valueOf(intValue));
                }
                return intValue;
            }
            this.mSkipSpeedLink.addLast(Integer.valueOf(intValue));
        }
        Iterator<Integer> it = this.mSkipSpeedLink.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        return i / 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculatePerformanceScore(Map<String, Object> map, int i) {
        if (this.mIsSupportPerformanceRope) {
            LogUtil.c(TAG, "calculatePerformanceScore");
            if (i >= 70) {
                this.mEnduranceValue++;
                BaseSportManager.getInstance().stagingData("EnduranceValue", Integer.valueOf(this.mEnduranceValue));
            }
            Object obj = map.get("TIME_ONE_SECOND_DURATION");
            if (obj instanceof Long) {
                calculateSpeedPerformanceScore(((Long) obj).longValue(), this.mSkippingTimes);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateContinuousJumpsTime() {
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        int i = this.mContinuousJumpTimes;
        if (i <= this.mMaxContinuousJumpTimes || !(data instanceof Long)) {
            return;
        }
        this.mMaxContinuousJumpTimes = i;
        this.mMaxJumpTimePoint = ((Long) data).longValue();
        BaseSportManager.getInstance().stagingData("MaxContinuousJumpTimes", Integer.valueOf(this.mMaxContinuousJumpTimes));
        BaseSportManager.getInstance().stagingData("MaxJumpTimePoint", Long.valueOf(this.mMaxJumpTimePoint));
    }

    private int adjustFinalInterruptTimes() {
        int i;
        LogUtil.a(TAG, "adjustFinalInterruptTimes() mInterruptTimes: ", Integer.valueOf(this.mInterruptTimes), ", mExtraInterruptTimes: ", Integer.valueOf(this.mExtraInterruptTimes));
        if (BaseSportManager.getInstance().getDataSource() == 5) {
            return this.mInterruptTimes;
        }
        if (this.mExtraInterruptTimes >= 1) {
            i = this.mInterruptTimes - 1;
        } else {
            i = this.mInterruptTimes;
        }
        return Math.max(i, 0);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MaxContinuousJumpTimes");
        if (data instanceof Integer) {
            int intValue = ((Integer) data).intValue();
            this.mMaxContinuousJumpTimes = intValue;
            LogUtil.a(TAG, "recoveryData() mMaxContinuousJumpTimes: ", Integer.valueOf(intValue));
        }
        Object data2 = BaseSportManager.getInstance().getData("MaxJumpTimePoint");
        if (data2 instanceof Long) {
            long longValue = ((Long) data2).longValue();
            this.mMaxJumpTimePoint = longValue;
            LogUtil.a(TAG, "recoveryData() mMaxJumpTimePoint: ", Long.valueOf(longValue));
        }
        Object data3 = BaseSportManager.getInstance().getData("STUMBLING_ROPE_DATA");
        boolean z = data3 instanceof Integer;
        if (z) {
            int intValue2 = ((Integer) data3).intValue();
            this.mInterruptTimes = intValue2;
            LogUtil.a(TAG, "recoveryData() interruptTimes: ", Integer.valueOf(intValue2));
        }
        Object data4 = BaseSportManager.getInstance().getData("SKIP_NUM_DATA");
        if (z) {
            int intValue3 = ((Integer) data4).intValue();
            this.mSkippingTimes = intValue3;
            LogUtil.a(TAG, "recoveryData() skippingTimes: ", Integer.valueOf(intValue3));
        }
        Object data5 = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data5 instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data5;
            if (koq.c(motionPath.requestSkippingSpeedList()) && (motionPath.requestSkippingSpeedList() instanceof CopyOnWriteArrayList)) {
                this.mSkippingSpeedList = (CopyOnWriteArrayList) motionPath.requestSkippingSpeedList();
            }
        }
        Object data6 = BaseSportManager.getInstance().getData("IsSecondRope");
        if (data6 instanceof Boolean) {
            boolean booleanValue = ((Boolean) data6).booleanValue();
            this.mIsSupportPerformanceRope = booleanValue;
            LogUtil.a(TAG, "recoveryData() isSecondRope: ", Boolean.valueOf(booleanValue));
        }
        if (this.mIsSupportPerformanceRope) {
            recoveryPerformanceScoreData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getInstantaneousValue(int i, long j) {
        float f;
        int i2 = (int) (j / 1000);
        int i3 = this.mLastSkipperTime;
        if (i3 != -1 && Math.abs(i2 - i3) > 1) {
            this.mSkipperNumberMap.clear();
        }
        if (!this.mSkipperNumberMap.containsKey(Integer.valueOf(i2))) {
            this.mLastSkipperTime = i2;
            this.mSkipperNumberMap.put(Integer.valueOf(i2), Integer.valueOf(i));
        }
        if (this.mSkipperNumberMap.size() > 10) {
            this.mSkipperNumberMap.remove(Integer.valueOf(i2 - 10));
        }
        if (isRopeSkippingPaused(i)) {
            this.mSkipperNumberMap.clear();
        }
        if (this.mSkipperNumberMap.size() >= 4 && this.mSkipperNumberMap.size() < 7) {
            f = getInstantaneousValue(i2, 4, 3);
        } else if (this.mSkipperNumberMap.size() >= 7 && this.mSkipperNumberMap.size() < 10) {
            f = getInstantaneousValue(i2, 7, 5);
        } else if (this.mSkipperNumberMap.size() == 10) {
            f = ((getInstantaneousValue(i2, 10, 5) + getInstantaneousValue(i2, 9, 5)) + getInstantaneousValue(i2, 8, 5)) / 3.0f;
        } else {
            LogUtil.a(TAG, "getInstantaneousValue default");
            f = 0.0f;
        }
        return (int) f;
    }

    private float getInstantaneousValue(int i, int i2, int i3) {
        if (i3 <= 0) {
            return 0.0f;
        }
        int size = i - (this.mSkipperNumberMap.size() - i2);
        int i4 = size - i3;
        int intValue = this.mSkipperNumberMap.containsKey(Integer.valueOf(size)) ? this.mSkipperNumberMap.get(Integer.valueOf(size)).intValue() : 0;
        int intValue2 = this.mSkipperNumberMap.containsKey(Integer.valueOf(i4)) ? this.mSkipperNumberMap.get(Integer.valueOf(i4)).intValue() : 0;
        float f = ((intValue - intValue2) / i3) * 60.0f;
        LogUtil.a(TAG, "getInstantaneousValue maxValue", Integer.valueOf(intValue), ",minValue = ", Integer.valueOf(intValue2), "instantaneousValue = ", Float.valueOf(f));
        return f;
    }

    private boolean isRopeSkippingPaused(int i) {
        if (this.mSkipperNumberMap.size() <= 5) {
            return false;
        }
        Iterator<Map.Entry<Integer, Integer>> it = this.mSkipperNumberMap.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().getValue().intValue() == i) {
                i2++;
            }
        }
        return i2 >= 5;
    }
}
