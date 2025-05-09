package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;

@SportComponentType(classify = 2, name = ComponentName.INCLINATION_PRODUCER)
/* loaded from: classes8.dex */
public class InclinationProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_GroupCountProducer";
    private double mInclination;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("INCLINATION_DATA", this);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mInclination = ((Double) obj).doubleValue();
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("INCLINATION_DATA", Double.valueOf(this.mInclination));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            LogUtil.a(TAG, "onSaveData() mInclination: ", Double.valueOf(this.mInclination));
            motionPathSimplify.addExtendDataMap("inclination", String.valueOf(this.mInclination));
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            double a2 = CommonUtils.a(((MotionPathSimplify) data).getExtendDataString("inclination"));
            this.mInclination = a2;
            LogUtil.a(TAG, "recovery mInclination ", Double.valueOf(a2));
        }
    }
}
