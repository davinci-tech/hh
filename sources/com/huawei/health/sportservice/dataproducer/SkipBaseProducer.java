package com.huawei.health.sportservice.dataproducer;

import android.text.TextUtils;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DateType;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes8.dex */
public abstract class SkipBaseProducer extends BaseProducer {
    protected static final int ENDURANCE_LIMIT = 70;
    private static final int FASTEST_SPEED_TIME_LEFT = 60;
    protected static final int INTERVAL_FIVE_THOUSAND_MS = 5000;
    protected static final int SECONDS_SIX = 6;
    protected static final int SECONDS_THREE = 3;
    private static final int SPEED_ENDURANCE_TIME_LEFT = 180;
    private static final String TAG = "SportService_SkipBaseProducer";
    protected int mEnduranceValue;
    private int mFastestSpeed;
    protected List<Integer> mSkipNumList;
    protected List<Integer> mSpeedEnduranceList;
    private int mSpeedEnduranceValue;
    protected CopyOnWriteArrayList<Integer> mSpeedList;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
    }

    protected void recoveryPerformanceScoreData() {
        Object data = BaseSportManager.getInstance().getData("FastestSpeed");
        if (data instanceof Integer) {
            this.mFastestSpeed = ((Integer) data).intValue();
            LogUtil.a(TAG, "recoveryData() mFastestSpeed: ", data);
        }
        Object data2 = BaseSportManager.getInstance().getData("SpeedEnduranceValue");
        if (data2 instanceof Integer) {
            this.mSpeedEnduranceValue = ((Integer) data2).intValue();
            LogUtil.a(TAG, "recoveryData() speedEnduranceValue: ", data2);
        }
        Object data3 = BaseSportManager.getInstance().getData("EnduranceValue");
        if (data3 instanceof Integer) {
            this.mEnduranceValue = ((Integer) data3).intValue();
            LogUtil.a(TAG, "recoveryData() mEnduranceValue: ", data3);
        }
    }

    protected void calculateSpeedPerformanceScore(long j, int i) {
        LogUtil.a(TAG, "sportTime = ", Long.valueOf(j), "  skippingTimes = ", Integer.valueOf(i));
        if (j > 0) {
            calculateFastestSpeed(j, i);
            calculateSpeedEndurance(j, i);
        }
    }

    protected void stopCalculateSpeedPerformanceScore() {
        LogUtil.a(TAG, "stopCalculateSpeedPerformanceScore");
        stopCalculateFastestSpeed();
        stopCalculateSpeedEndurance();
        savePerformanceScore();
    }

    private void calculateFastestSpeed(long j, int i) {
        if (this.mSkipNumList == null) {
            this.mSkipNumList = Collections.synchronizedList(new ArrayList());
        }
        toSupplementMissingData(j, this.mSkipNumList, i);
        if (this.mSkipNumList.size() > 60) {
            this.mSkipNumList.remove(0);
        }
        this.mSkipNumList.add(Integer.valueOf(i));
        if (j <= 60000) {
            this.mFastestSpeed = Math.max(this.mFastestSpeed, i);
            BaseSportManager.getInstance().stagingData("FastestSpeed", Integer.valueOf(this.mFastestSpeed));
        } else if (j % 5000 == 0) {
            this.mFastestSpeed = Math.max(i - this.mSkipNumList.get(0).intValue(), this.mFastestSpeed);
            BaseSportManager.getInstance().stagingData("FastestSpeed", Integer.valueOf(this.mFastestSpeed));
        } else {
            LogUtil.c(TAG, "mSkipNumList add element");
        }
    }

    private void toSupplementMissingData(long j, List<Integer> list, int i) {
        if (koq.c(list)) {
            return;
        }
        int i2 = (int) (j / 1000);
        int i3 = 1;
        boolean z = i > i2;
        if (!z) {
            while (i3 < i2) {
                list.add(0);
                i3++;
            }
        } else {
            LogUtil.c(TAG, "isSkipNumThanTime : ", Boolean.valueOf(z));
            while (i3 < i2) {
                list.add(Integer.valueOf(i3));
                i3++;
            }
        }
    }

    private void stopCalculateFastestSpeed() {
        if (koq.b(this.mSkipNumList) || this.mSkipNumList.size() <= 60) {
            return;
        }
        LogUtil.a(TAG, "calculate fastestSpeed");
        this.mFastestSpeed = Math.max(this.mSkipNumList.get(r0.size() - 1).intValue() - this.mSkipNumList.get(0).intValue(), this.mFastestSpeed);
    }

    private void calculateSpeedEndurance(long j, int i) {
        if (this.mSpeedEnduranceList == null) {
            this.mSpeedEnduranceList = Collections.synchronizedList(new ArrayList());
        }
        toSupplementMissingData(j, this.mSpeedEnduranceList, i);
        if (this.mSpeedEnduranceList.size() > 180) {
            this.mSpeedEnduranceList.remove(0);
        }
        this.mSpeedEnduranceList.add(Integer.valueOf(i));
        if (j <= 180000) {
            this.mSpeedEnduranceValue = Math.max(this.mSpeedEnduranceValue, i);
            BaseSportManager.getInstance().stagingData("SpeedEnduranceValue", Integer.valueOf(this.mSpeedEnduranceValue));
        } else if (j % 5000 == 0) {
            this.mSpeedEnduranceValue = Math.max(i - this.mSpeedEnduranceList.get(0).intValue(), this.mSpeedEnduranceValue);
            BaseSportManager.getInstance().stagingData("SpeedEnduranceValue", Integer.valueOf(this.mSpeedEnduranceValue));
        } else {
            LogUtil.c(TAG, "mSpeedEnduranceList add element");
        }
    }

    private void stopCalculateSpeedEndurance() {
        if (koq.b(this.mSpeedEnduranceList) || this.mSpeedEnduranceList.size() <= 180) {
            return;
        }
        LogUtil.a(TAG, "calculate speedEndurance");
        this.mSpeedEnduranceValue = Math.max(this.mSpeedEnduranceList.get(r0.size() - 1).intValue() - this.mSpeedEnduranceList.get(0).intValue(), this.mSpeedEnduranceValue);
    }

    protected void calculateEnduranceValue() {
        if (koq.c(this.mSpeedList)) {
            LogUtil.a(TAG, "calculateEnduranceValue");
            long j = 0;
            while (this.mSpeedList.iterator().hasNext()) {
                j += r0.next().intValue();
            }
            if (j / this.mSpeedList.size() >= 70) {
                this.mEnduranceValue++;
            }
            this.mSpeedList.clear();
            BaseSportManager.getInstance().stagingData("EnduranceValue", Integer.valueOf(this.mEnduranceValue));
        }
    }

    private void savePerformanceScore() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            Map<String, String> requestExtendDataMap = motionPathSimplify.requestExtendDataMap();
            if (requestExtendDataMap == null || requestExtendDataMap.get("skipSpeed") == null || Integer.valueOf(requestExtendDataMap.get("skipSpeed")).intValue() <= 0) {
                LogUtil.a(TAG, "onStopSport skipSpeed abnormalTrack");
                return;
            }
            LogUtil.a(TAG, "savePerformanceScore fastestSpeed = ", Integer.valueOf(this.mFastestSpeed), " speedEnduranceValue = ", Integer.valueOf(this.mSpeedEnduranceValue), " enduranceValue = ", Integer.valueOf(this.mEnduranceValue));
            requestExtendDataMap.put("maxSkipSpeed", String.valueOf(this.mFastestSpeed));
            requestExtendDataMap.put("enduranceAbility", String.valueOf(this.mSpeedEnduranceValue));
            requestExtendDataMap.put("enduranceTimeAbility", String.valueOf(this.mEnduranceValue));
            EcologyDeviceApi ecologyDeviceApi = (EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class);
            requestExtendDataMap.put("skipNumAbilityRank", String.valueOf(queryPerformanceRank(ecologyDeviceApi, "skipNumAbilityRank", requestExtendDataMap.get("skipNum"))));
            requestExtendDataMap.put("maxSkippingTimesAbilityRank", String.valueOf(queryPerformanceRank(ecologyDeviceApi, "maxSkippingTimesAbilityRank", requestExtendDataMap.get("maxSkippingTimes"))));
            requestExtendDataMap.put("maxSkipSpeedRank", String.valueOf(queryPerformanceRank(ecologyDeviceApi, "maxSkipSpeedRank", requestExtendDataMap.get("maxSkipSpeed"))));
            requestExtendDataMap.put("enduranceAbilityRank", String.valueOf(queryPerformanceRank(ecologyDeviceApi, "enduranceAbilityRank", requestExtendDataMap.get("enduranceAbility"))));
            requestExtendDataMap.put("enduranceTimeAbilityRank", String.valueOf(queryPerformanceRank(ecologyDeviceApi, "enduranceTimeAbilityRank", requestExtendDataMap.get("enduranceTimeAbility"))));
            motionPathSimplify.saveExtendDataMap(requestExtendDataMap);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    private float queryPerformanceRank(EcologyDeviceApi ecologyDeviceApi, String str, String str2) {
        if (TextUtils.isEmpty(str2) || ecologyDeviceApi == null) {
            return 0.0f;
        }
        return ecologyDeviceApi.queryPerformanceRank(str, Float.valueOf(str2).floatValue(), DateType.DATE_NONE);
    }

    protected int calculateAverageSkippingSpeed(int i, long j) {
        if (j == 0) {
            return 0;
        }
        int round = Math.round((i / ((j * 1.0f) / 1000.0f)) * 60.0f);
        if (round >= 480) {
            return -1;
        }
        return round;
    }
}
