package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.DISTANCE_PRODUCER)
/* loaded from: classes8.dex */
public class DistanceProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_DistanceProducer";
    private int mDistance;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("DISTANCE_DATA", this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onSaveData();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mDistance = ((Integer) obj).intValue();
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("DISTANCE_DATA", Integer.valueOf(this.mDistance));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            motionPathSimplify.saveTotalDistance(this.mDistance);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            int requestTotalDistance = ((MotionPathSimplify) data).requestTotalDistance();
            this.mDistance = requestTotalDistance;
            LogUtil.a(TAG, "recovery mDistance ", Integer.valueOf(requestTotalDistance));
        }
    }
}
