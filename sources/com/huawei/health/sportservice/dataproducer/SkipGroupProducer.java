package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gvv;
import defpackage.kob;
import defpackage.kwt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.SKIP_GROUP_PRODUCER)
/* loaded from: classes8.dex */
public class SkipGroupProducer extends SkipBaseProducer implements SportLifecycle {
    private static final int ROPE_CONTROL_INTERMITTENT_REST = 2;
    private static final int ROPE_CONTROL_INTERMITTENT_SPORT = 1;
    private static final int SUBSCRIBE_NOTIFY_SUM = 10;
    private static final String TAG = "SportService_SkipGroupProducer";
    private int mCaloriesValue;
    private IntermitentJumpData mIntermitentJumpData;
    private int mInterruptTimes;
    private boolean mIsAbnormalSpeed;
    private int mRestDuration;
    private int mSkipNum;
    private int mSkipSpeed;
    private int mSportDuration;
    private int mTotalCaloriesValue;
    private int mTotalInterruptTimes;
    private int mTotalSkipNum;
    private int mTrackCode;
    private int mActionGroup = 1;
    private int mLastActionGroup = 1;
    private ArrayList<kwt> mSegments = new ArrayList<>();
    private final CopyOnWriteArrayList<HeartRateData> mHeartRateList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<kob> mSkippingSpeedList = new CopyOnWriteArrayList<>();
    private LinkedList<Integer> mSkipSpeedLink = new LinkedList<>();
    private final fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
        if (sportLaunchParams == null || sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class) == null) {
            return;
        }
        this.mIntermitentJumpData = (IntermitentJumpData) sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class);
        subscribeNotify();
    }

    private void subscribeNotify() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add("ROPE_INTERMITTENT_GROUP_NO_DATA");
        arrayList.add("SKIPPING_INTERMITTENT_SPORT_TIME_DATA");
        arrayList.add("SKIPPING_INTERMITTENT_REST_TIME_DATA");
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("TIME_ONE_SECOND_TIMESTAMP");
        arrayList.add("ROPE_INTERMITTENT_JUMP_ENERGY_DATA");
        arrayList.add("SKIP_NUM_DATA");
        arrayList.add("INSTANTANEOUS_SPEED");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("STUMBLING_ROPE_DATA");
        arrayList.add("ROPE_MACHINE_STATUS_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.SkipGroupProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SkipGroupProducer.this.m445x5904f6b4(list, map);
            }
        });
    }

    /* renamed from: lambda$subscribeNotify$0$com-huawei-health-sportservice-dataproducer-SkipGroupProducer, reason: not valid java name */
    /* synthetic */ void m445x5904f6b4(List list, Map map) {
        if (list.contains("ROPE_INTERMITTENT_GROUP_NO_DATA")) {
            int intValue = getIntValue(map.get("ROPE_INTERMITTENT_GROUP_NO_DATA"));
            this.mActionGroup = intValue;
            if (intValue > this.mLastActionGroup) {
                this.mSkipSpeedLink.clear();
                saveSegmentData();
                return;
            }
            return;
        }
        if (list.contains("ROPE_INTERMITTENT_JUMP_ENERGY_DATA")) {
            int intValue2 = getIntValue(map.get("ROPE_INTERMITTENT_JUMP_ENERGY_DATA"));
            if (intValue2 > this.mCaloriesValue) {
                this.mCaloriesValue = intValue2;
                return;
            }
            return;
        }
        if (list.contains("SKIP_NUM_DATA")) {
            int intValue3 = getIntValue(map.get("SKIP_NUM_DATA"));
            if (intValue3 > this.mSkipNum) {
                this.mSkipNum = intValue3;
                return;
            }
            return;
        }
        if (list.contains("STUMBLING_ROPE_DATA")) {
            int intValue4 = getIntValue(map.get("STUMBLING_ROPE_DATA"));
            if (intValue4 > this.mInterruptTimes) {
                this.mInterruptTimes = intValue4;
                return;
            }
            return;
        }
        onChangeSplit(list, map);
    }

    private void onChangeSplit(List<String> list, Map<String, Object> map) {
        if (list.contains("TIME_FIVE_SECOND_DURATION")) {
            if (this.mSkipSpeed >= 0) {
                this.mSkippingSpeedList.add(new kob(((Long) map.get("TIME_FIVE_SECOND_DURATION")).longValue(), Math.round(this.mSkipSpeed)));
                return;
            }
            return;
        }
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            this.mSkipSpeed = calculateInstantaneousValue();
            BaseSportManager.getInstance().stagingAndNotification("ROPE_INTERMITTENT_JUMP_SPEED_DATA", Integer.valueOf(this.mSkipSpeed));
            checkAvgSkipSpeed(this.mSkipSpeed);
            return;
        }
        if (list.contains("HEART_RATE_DATA")) {
            int intValue = getIntValue(map.get("HEART_RATE_DATA"));
            Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_TIMESTAMP");
            if (data instanceof Long) {
                addHeartRateIntoList(((Long) data).longValue(), intValue);
                return;
            }
            return;
        }
        if (list.contains("SKIPPING_INTERMITTENT_SPORT_TIME_DATA")) {
            int intValue2 = getIntValue(map.get("SKIPPING_INTERMITTENT_SPORT_TIME_DATA"));
            if (intValue2 > this.mSportDuration) {
                this.mSportDuration = intValue2;
                calculateSpeedPerformanceScore(intValue2 * 1000, this.mSkipNum);
                calculateEnduranceValue();
                return;
            }
            return;
        }
        if (list.contains("SKIPPING_INTERMITTENT_REST_TIME_DATA")) {
            int intValue3 = getIntValue(map.get("SKIPPING_INTERMITTENT_REST_TIME_DATA"));
            if (intValue3 > this.mRestDuration) {
                this.mRestDuration = intValue3;
                return;
            }
            return;
        }
        if (list.contains("INSTANTANEOUS_SPEED")) {
            Object obj = map.get("INSTANTANEOUS_SPEED");
            if (getIntValue(map.get("ROPE_MACHINE_STATUS_DATA")) != 1) {
                return;
            }
            if (this.mSpeedList == null) {
                this.mSpeedList = new CopyOnWriteArrayList<>();
            }
            if (obj instanceof Integer) {
                this.mSpeedList.add(Integer.valueOf(((Integer) obj).intValue()));
            }
        }
    }

    private int getIntValue(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private int calculateInstantaneousValue() {
        int i = 0;
        if (((Integer) BaseSportManager.getInstance().getData("ROPE_MACHINE_STATUS_DATA")).intValue() == 2) {
            this.mSkipSpeed = 0;
            return 0;
        }
        int intValue = ((Integer) BaseSportManager.getInstance().getData("INSTANTANEOUS_SPEED")).intValue();
        int i2 = this.mSportDuration;
        if (i2 > 6) {
            this.mSkipSpeedLink.addLast(Integer.valueOf(intValue));
            if (this.mSkipSpeedLink.size() <= 3) {
                return intValue;
            }
            this.mSkipSpeedLink.removeFirst();
        } else {
            if (i2 != 6) {
                if (i2 > 3) {
                    this.mSkipSpeedLink.addLast(Integer.valueOf(intValue));
                }
                return intValue;
            }
            this.mSkipSpeedLink.addLast(Integer.valueOf(intValue));
        }
        Iterator<Integer> it = this.mSkipSpeedLink.iterator();
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        int i3 = i / 3;
        this.mSkipSpeed = i3;
        return i3;
    }

    private void addHeartRateIntoList(long j, int i) {
        if (fhs.c(i)) {
            this.mHeartRateList.add(new HeartRateData(j, i));
            LogUtil.a(TAG, "addHeartRateIntoList heartRate: ", Integer.valueOf(i), " time", Long.valueOf(j));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (this.mIntermitentJumpData == null) {
            return;
        }
        this.mActionGroup++;
        saveSegmentData();
        onSaveData();
        stopCalculateSpeedPerformanceScore();
        this.mSegments.clear();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        if (this.mIntermitentJumpData == null) {
            return;
        }
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            motionPath.saveCommonSegmentList(this.mSegments);
            motionPath.saveSkippingSpeed(this.mSkippingSpeedList);
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
        Object data2 = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        long longValue = data2 instanceof Long ? ((Long) data2).longValue() : 0L;
        if (longValue == 0) {
            return;
        }
        int calculateAverageSkippingSpeed = this.mIsAbnormalSpeed ? -1 : calculateAverageSkippingSpeed(this.mTotalSkipNum, longValue);
        Object data3 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data3 instanceof MotionPathSimplify) {
            LogUtil.a(TAG, "onSaveData() action group: ", Integer.valueOf(this.mActionGroup - 1), " avgSkipSpeed = ", Integer.valueOf(calculateAverageSkippingSpeed));
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data3;
            motionPathSimplify.saveAbnormalTrack(this.mTrackCode);
            HashMap hashMap = new HashMap();
            hashMap.put("total_action_group", String.valueOf(this.mActionGroup - 1));
            hashMap.put("skipSpeed", String.valueOf(calculateAverageSkippingSpeed));
            hashMap.put("skipNum", String.valueOf(this.mTotalSkipNum));
            hashMap.put("interruptTimes", String.valueOf(this.mTotalInterruptTimes));
            hashMap.put("targetSegmentNum", String.valueOf(this.mIntermitentJumpData.getIntermittentJumpGroups()));
            hashMap.put("targetSegmentSkipNum", String.valueOf(this.mIntermitentJumpData.getIntermittentJumpExerciseNum()));
            hashMap.put("targetSegmentDuration", String.valueOf(this.mIntermitentJumpData.getIntermittentJumpExerciseTime()));
            motionPathSimplify.saveExtendDataMap(hashMap);
            motionPathSimplify.saveTotalCalories(this.mTotalCaloriesValue * 1000);
            motionPathSimplify.saveTotalSteps(this.mTotalSkipNum);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    private void saveSegmentData() {
        int i = this.mActionGroup;
        if (i > this.mLastActionGroup) {
            LogUtil.a(TAG, "saveSegmentData mActionGroup:", Integer.valueOf(i), "-mCaloriesValue:", Integer.valueOf(this.mCaloriesValue), "-mSkipNum:", Integer.valueOf(this.mSkipNum), "-mInterruptTimes:", Integer.valueOf(this.mInterruptTimes), "-mSportDuration:", Integer.valueOf(this.mSportDuration), "-mRestDuration:", Integer.valueOf(this.mRestDuration));
            kwt kwtVar = new kwt();
            kwtVar.d(this.mLastActionGroup);
            checkSkipTimeOrNumToGoal();
            kwtVar.b(this.mSportDuration);
            kwtVar.b(gvv.c((ArrayList<HeartRateData>) new ArrayList(this.mHeartRateList)));
            kwtVar.f(this.mIntermitentJumpData.getIntermittentJumpMode());
            kwtVar.g(this.mSkipNum);
            int i2 = this.mSportDuration;
            if (i2 != 0) {
                kwtVar.a(Math.round((this.mSkipNum * 60.0f) / i2));
            }
            kwtVar.e(this.mCaloriesValue * 1000);
            kwtVar.d(this.mRestDuration);
            kwtVar.c(this.mInterruptTimes);
            this.mSegments.add(kwtVar);
            this.mTotalSkipNum += this.mSkipNum;
            this.mTotalInterruptTimes += this.mInterruptTimes;
            this.mTotalCaloriesValue += this.mCaloriesValue;
            this.mLastActionGroup = this.mActionGroup;
            this.mSkipNum = 0;
            this.mInterruptTimes = 0;
            this.mSportDuration = 0;
            this.mRestDuration = 0;
            this.mCaloriesValue = 0;
            this.mHeartRateList.clear();
            if (this.mSkipNumList != null) {
                this.mSkipNumList.clear();
            }
            if (this.mSpeedEnduranceList != null) {
                this.mSpeedEnduranceList.clear();
            }
            if (this.mSpeedList != null) {
                this.mSpeedList.clear();
            }
            LogUtil.a(TAG, "end saveSegmentData");
        }
    }

    private void checkAvgSkipSpeed(int i) {
        if (i >= 480) {
            this.mIsAbnormalSpeed = true;
        }
        if (i > 360) {
            this.mTrackCode = 1;
        }
    }

    private void checkSkipTimeOrNumToGoal() {
        if (this.mRestDuration > this.mIntermitentJumpData.getIntermittentJumpBreakTime()) {
            this.mRestDuration = this.mIntermitentJumpData.getIntermittentJumpBreakTime();
        }
        int intermittentJumpMode = this.mIntermitentJumpData.getIntermittentJumpMode();
        if (intermittentJumpMode == 0) {
            if (this.mSkipNum > this.mIntermitentJumpData.getIntermittentJumpExerciseNum()) {
                this.mSkipNum = this.mIntermitentJumpData.getIntermittentJumpExerciseNum();
            }
        } else if (intermittentJumpMode == 1 && this.mSportDuration > this.mIntermitentJumpData.getIntermittentJumpExerciseTime()) {
            this.mSportDuration = this.mIntermitentJumpData.getIntermittentJumpExerciseTime();
        }
    }
}
