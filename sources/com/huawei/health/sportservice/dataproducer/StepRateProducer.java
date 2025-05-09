package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.fgm;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.STEP_RATE_PRODUCER)
/* loaded from: classes8.dex */
public class StepRateProducer extends BaseProducer implements SportLifecycle, SportDataNotify {
    private static final int GAIN_FOR_STEP_RATE = 6;
    private static final int MAX_STEP_RATE = 250;
    private static final int MINUTE_TO_MS = 60000;
    private static final int MIN_STEP_RATE = 0;
    private static final String TAG = "SportService_StepRateProducer";
    private int[] mStepInfo;
    private int mStepRate;
    private boolean mIsFirstIn = true;
    private CopyOnWriteArrayList<StepRateData> mStepRateList = new CopyOnWriteArrayList<>();
    private int[] mStepArray = {0, 0, 0, 0, 0, 0};
    private boolean mIsStepCountStop = false;
    private int mStepsWhenCountStop = 0;
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        if (BaseSportManager.getInstance().getStatus() == 1) {
            BaseSportManager.getInstance().stagingAndNotification("STEP_RATE_DATA", Integer.valueOf(this.mStepRate));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        BaseSportManager.getInstance().stagingAndNotification("STEP_RATE_DATA", -1);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        this.mStepInfo = getStepInfo(this.mStepRateList);
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            ArrayList<StepRateData> arrayList = new ArrayList<>(this.mStepRateList);
            motionPath.saveStepRateList(arrayList);
            LogUtil.a(TAG, "onStopSport MotionPath saveHeartRateList", Integer.valueOf(arrayList.size()));
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data2 instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
            constructSimplify(motionPathSimplify);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (!(data instanceof MotionPath)) {
            LogUtil.b(TAG, "recovery failed. data not MotionPath type");
            return;
        }
        MotionPath motionPath = (MotionPath) data;
        if (koq.c(motionPath.requestStepRateList())) {
            this.mStepRateList.addAll(motionPath.requestStepRateList());
        }
        LogUtil.a(TAG, "recovery mStepRateList.size() ", Integer.valueOf(this.mStepRateList.size()));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("STEP_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("TIME_ONE_SECOND_TIMESTAMP");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        cleanStepArray();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    private void cleanStepArray() {
        for (int i = 0; i < 6; i++) {
            this.mStepArray[i] = 0;
        }
        this.mStepRate = 0;
    }

    private void constructSimplify(MotionPathSimplify motionPathSimplify) {
        if (this.mStepRateList.size() > 1) {
            int[] iArr = this.mStepInfo;
            if (iArr != null && iArr.length > 1) {
                motionPathSimplify.saveBestStepRate(iArr[1]);
            }
        } else if (this.mStepRateList.size() > 0) {
            motionPathSimplify.saveBestStepRate(this.mStepRateList.get(0).acquireStepRate());
        } else {
            LogUtil.h(TAG, "mStepRateList is empty");
        }
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        if (data instanceof Integer) {
            LogUtil.b(TAG, "during == 0L");
            return;
        }
        long longValue = ((Long) data).longValue() / 1000;
        LogUtil.a(TAG, "totalStep =", Integer.valueOf(((Integer) BaseSportManager.getInstance().getData("STEP_DATA")).intValue()), "during =", Long.valueOf(longValue));
        int floor = (int) Math.floor(((r0 * 1.0f) / longValue) * 60.0f);
        if (this.mStepRateList.size() == 1 && this.mStepRateList.get(0).acquireStepRate() < floor) {
            floor = this.mStepRateList.get(0).acquireStepRate();
        }
        motionPathSimplify.saveAvgStepRate(floor);
        if (BaseSportManager.getInstance().getSportType() == 273) {
            String num = Integer.toString(floor / 2);
            HashMap hashMap = new HashMap(1);
            hashMap.put("crossTrainerCadence", num);
            motionPathSimplify.saveExtendDataMap(hashMap);
        }
    }

    private int[] getStepInfo(CopyOnWriteArrayList<StepRateData> copyOnWriteArrayList) {
        int i;
        int[] iArr = new int[3];
        int i2 = 0;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            i = 0;
        } else {
            Iterator<StepRateData> it = copyOnWriteArrayList.iterator();
            i = 0;
            while (it.hasNext()) {
                StepRateData next = it.next();
                if (next != null) {
                    i += next.acquireStepRate();
                    if (next.acquireStepRate() > i2) {
                        i2 = next.acquireStepRate();
                    }
                }
            }
        }
        iArr[1] = i2;
        iArr[2] = i;
        return iArr;
    }

    @Override // com.huawei.health.sportservice.SportDataNotify
    public void onChange(List<String> list, Map<String, Object> map) {
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            long longValue = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue();
            int intValue = map.containsKey("STEP_DATA") ? ((Integer) map.get("STEP_DATA")).intValue() : 0;
            if (this.mIsFirstIn && longValue > 0) {
                fillStepRatePoint(longValue);
                this.mIsFirstIn = false;
            }
            if (needSaveStepRateList(longValue)) {
                LogUtil.a(TAG, "duration is ", Long.valueOf(longValue));
                long longValue2 = map.containsKey("TIME_ONE_SECOND_TIMESTAMP") ? ((Long) map.get("TIME_ONE_SECOND_TIMESTAMP")).longValue() : 0L;
                if (longValue2 == 0) {
                    return;
                } else {
                    addStepsToList(longValue2, intValue);
                }
            }
            if (longValue % 2000 == 0) {
                computeStepRateForShow(intValue);
            }
        }
    }

    private void fillStepRatePoint(long j) {
        LogUtil.a(TAG, "mIsFirstIn", Long.valueOf(j));
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = j;
        while (j / 60000 > this.mStepRateList.size()) {
            long j3 = currentTimeMillis - j2;
            LogUtil.a(TAG, "currentTime - tempDuration", Long.valueOf(j3));
            this.mStepRateList.add(new StepRateData(j3, 0));
            j2 -= 60000;
        }
    }

    private boolean needSaveStepRateList(long j) {
        if (j <= 0 || j % 60000 != 0) {
            return !this.mIsFirstIn && ((long) this.mStepRateList.size()) < j / 60000;
        }
        return true;
    }

    private void addStepsToList(long j, int i) {
        if (koq.b(this.mStepRateList)) {
            this.mStepRateList.add(new StepRateData(j, i));
            return;
        }
        Iterator<StepRateData> it = this.mStepRateList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            StepRateData next = it.next();
            if (next != null) {
                i2 += next.acquireStepRate();
            }
        }
        int i3 = i - i2;
        LogUtil.a(TAG, "now will add steps into list: time is ", Long.valueOf(j), ",step:", Integer.valueOf(i3));
        this.mStepRateList.add(new StepRateData(j, i3));
    }

    private void computeStepRateForShow(int i) {
        addStepIntoArray(i);
        if (stepArrayIsFull()) {
            countStepRate();
        }
    }

    private void addStepIntoArray(int i) {
        if (stepArrayIsFull()) {
            int[] iArr = this.mStepArray;
            System.arraycopy(iArr, 1, iArr, 0, 5);
            this.mStepArray[5] = i;
        } else {
            for (int i2 = 0; i2 < 6; i2++) {
                int[] iArr2 = this.mStepArray;
                if (iArr2[i2] == 0) {
                    iArr2[i2] = i;
                    return;
                }
            }
        }
    }

    private boolean stepArrayIsFull() {
        return this.mStepArray[5] != 0;
    }

    private void countStepRate() {
        int[] iArr = this.mStepArray;
        int i = iArr[5];
        int i2 = iArr[0];
        if (i == i2) {
            this.mIsStepCountStop = true;
            this.mStepsWhenCountStop = i2;
            this.mStepRate = 0;
        } else {
            if (!this.mIsStepCountStop) {
                int i3 = (i - i2) * 6;
                if (i3 >= 0 && i3 <= 250) {
                    this.mStepRate = i3;
                } else {
                    this.mStepRate = 0;
                }
                onStagingAndNotification();
                return;
            }
            if (i2 != this.mStepsWhenCountStop) {
                this.mIsStepCountStop = false;
            }
        }
    }
}
