package com.huawei.health.sportservice.manager.heartratecontrol;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dtf;
import defpackage.dth;
import defpackage.lau;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.HEART_RATE_CONTROL_MANAGER)
/* loaded from: classes8.dex */
public class TreadmillHeartRateControlManager extends BaseHeartRateControlManager {
    private static final String TAG = "SportService_TreadmillHeartRateControlManager";
    private float mCurrentSpeed = -1.0f;

    public TreadmillHeartRateControlManager() {
        this.mModel = new dtf();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "enter onPreSport");
        this.mModel.initParas();
        this.mModel.subscribeModelResult(this);
        this.mSportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.heartratecontrol.TreadmillHeartRateControlManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                TreadmillHeartRateControlManager.this.m484x5ebc3a5c(list, map);
            }
        };
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("SPEED_DATA");
        this.mCallbackOption.a(arrayList);
        this.mCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mCallbackOption, this.mSportDataNotify);
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-heartratecontrol-TreadmillHeartRateControlManager, reason: not valid java name */
    /* synthetic */ void m484x5ebc3a5c(List list, Map map) {
        if (list.contains("HEART_RATE_DATA") && (map.get("HEART_RATE_DATA") instanceof Integer)) {
            this.mHeartRate = ((Integer) map.get("HEART_RATE_DATA")).intValue();
        }
        if (list.contains("SPEED_DATA") && (map.get("SPEED_DATA") instanceof Integer)) {
            this.mCurrentSpeed = ((Integer) map.get("SPEED_DATA")).intValue() / 100.0f;
        }
        if (list.contains("TIME_ONE_SECOND_DURATION") && (map.get("TIME_ONE_SECOND_DURATION") instanceof Long)) {
            this.mSportTime = Math.round(((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue() / 1000);
            if (!isInvalidData() && (this.mModel instanceof dtf)) {
                ((dtf) this.mModel).c(this.mSportTime, this.mHeartRate, this.mCurrentSpeed);
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "enter onPauseSport");
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a(TAG, "enter onResumeSport");
        BaseSportManager.getInstance().setParas(SportParamsType.HEART_RATE_CONTROL_LOCK, 1);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        LogUtil.a(TAG, "enter destroy");
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
            HashMap hashMap = (HashMap) t;
            if (hashMap.get("MODEL_SPEED_CHANGE") instanceof Float) {
                float floatValue = ((Float) hashMap.get("MODEL_SPEED_CHANGE")).floatValue();
                if (this.mCurrentSpeed == floatValue) {
                    ReleaseLogUtil.e(TAG, "onDataUpdate: speed not change");
                } else {
                    sendMachineSpeedControl(floatValue);
                }
            }
        }
    }

    private void sendMachineSpeedControl(float f) {
        if (this.mIsRestart) {
            LogUtil.a(TAG, "restart not support control, speed is ", Float.valueOf(f));
            return;
        }
        int intValue = new BigDecimal(Float.toString(dth.d(f, 1))).multiply(new BigDecimal("100")).intValue();
        ReleaseLogUtil.e(TAG, "sendMachineSpeedControl, controlSpeed = ", Integer.valueOf(intValue));
        lau.d().b(2, new int[]{intValue});
    }

    private boolean isInvalidData() {
        LogUtil.a(TAG, "isInvalidData mSportTime = ", Integer.valueOf(this.mSportTime), ", mHeartRate = ", Integer.valueOf(this.mHeartRate), ", mCurrentSpeed = ", Float.valueOf(this.mCurrentSpeed));
        return this.mSportTime < 0 || this.mHeartRate < 0 || this.mCurrentSpeed < 0.0f;
    }
}
