package com.huawei.health.sportservice.manager.heartratecontrol;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dti;
import defpackage.lau;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.HEART_RATE_CONTROL_MANAGER)
/* loaded from: classes8.dex */
public class BikeHeartRateControlManager extends BaseHeartRateControlManager {
    private static final String TAG = "SportService_HRControl_BikeHeartRateControlManager";
    private static final String TAG_RELEASE = "R_SportService_HRControl_BikeHeartRateControlManager";
    private int mPower = -1;
    private int mRpm = -1;
    private int mResistance = -1;

    public BikeHeartRateControlManager() {
        this.mModel = new dti();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport");
        this.mModel.initParas();
        this.mModel.subscribeModelResult(this);
        this.mSportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.heartratecontrol.BikeHeartRateControlManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                BikeHeartRateControlManager.this.m483x65ad35e9(list, map);
            }
        };
        ArrayList arrayList = new ArrayList(10);
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("POWER_DATA");
        arrayList.add("CADENCE_DATA");
        arrayList.add("RESISTANCE_LEVEL_DATA");
        this.mCallbackOption.a(arrayList);
        this.mCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mCallbackOption, this.mSportDataNotify);
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-heartratecontrol-BikeHeartRateControlManager, reason: not valid java name */
    /* synthetic */ void m483x65ad35e9(List list, Map map) {
        if (list.contains("HEART_RATE_DATA") && (map.get("HEART_RATE_DATA") instanceof Integer)) {
            this.mHeartRate = ((Integer) map.get("HEART_RATE_DATA")).intValue();
        }
        if (list.contains("POWER_DATA") && (map.get("POWER_DATA") instanceof Integer)) {
            this.mPower = ((Integer) map.get("POWER_DATA")).intValue();
        }
        if (list.contains("CADENCE_DATA") && (map.get("CADENCE_DATA") instanceof Integer)) {
            this.mRpm = ((Integer) map.get("CADENCE_DATA")).intValue();
        }
        if (list.contains("RESISTANCE_LEVEL_DATA") && (map.get("RESISTANCE_LEVEL_DATA") instanceof Integer)) {
            this.mResistance = ((Integer) map.get("RESISTANCE_LEVEL_DATA")).intValue();
        }
        if (list.contains("TIME_ONE_SECOND_DURATION") && (map.get("TIME_ONE_SECOND_DURATION") instanceof Long)) {
            this.mSportTime = Math.round(((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue() / 1000);
            if (!isInvalidData() && (this.mModel instanceof dti)) {
                ((dti) this.mModel).d(this.mSportTime, this.mHeartRate, this.mPower, this.mRpm, this.mResistance);
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport");
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        LogUtil.a(TAG, "destroy");
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mCallbackOption);
        if (this.mModel != null) {
            this.mModel.unsubscribeModelResult();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.health.sportservice.manager.heartratecontrol.BaseHeartRateControlManager, com.huawei.health.heartratecontrol.algorithm.HeartRateModelCallback
    public <T> void onDataUpdate(String str, T t) {
        super.onDataUpdate(str, t);
        str.hashCode();
        if (str.equals("HEART_RATE_MODEL_RESULT")) {
            HashMap hashMap = new HashMap();
            if (t instanceof HashMap) {
                hashMap = (HashMap) t;
            }
            if ((hashMap.get("RESULT_POWER") instanceof Integer) && (hashMap.get("RESULT_RPM") instanceof Integer)) {
                int intValue = ((Integer) hashMap.get("RESULT_POWER")).intValue();
                int intValue2 = ((Integer) hashMap.get("RESULT_RPM")).intValue();
                ReleaseLogUtil.e(TAG_RELEASE, "onDataUpdate adjustingIndoorBikeResistance power = ", Integer.valueOf(intValue), ", rpm = ", Integer.valueOf(intValue2));
                ReleaseLogUtil.e(TAG_RELEASE, "onDataUpdate adjustingIndoorBikeResistance isAdjustSuccess = ", Boolean.valueOf(lau.d().e(intValue2, intValue)));
            }
        }
    }

    private boolean isInvalidData() {
        LogUtil.a(TAG, "isInvalidData mSportTime = ", Integer.valueOf(this.mSportTime), ", mHeartRate = ", Integer.valueOf(this.mHeartRate), ", mPower = ", Integer.valueOf(this.mPower), ", mRpm = ", Integer.valueOf(this.mRpm), ", mResistance = ", Integer.valueOf(this.mResistance));
        return this.mSportTime < 0 || this.mHeartRate < 0 || this.mPower < 0 || this.mRpm < 0 || this.mResistance < 0;
    }
}
