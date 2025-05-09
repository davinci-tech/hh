package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.TOTAL_CREEP_PRODUCER)
/* loaded from: classes8.dex */
public class TotalCreepProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_TotalCreepProducer";
    private int mTotalCreep;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        if (obj instanceof Integer) {
            this.mTotalCreep = ((Integer) obj).intValue();
            onStagingAndNotification();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("CREEP_DATA", Integer.valueOf(this.mTotalCreep));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            motionPathSimplify.saveCreepingWave(this.mTotalCreep);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            int requestCreepingWave = (int) ((MotionPathSimplify) data).requestCreepingWave();
            this.mTotalCreep = requestCreepingWave;
            LogUtil.a(TAG, "recovery mTotalCreep ", Integer.valueOf(requestCreepingWave));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("CREEP_DATA", this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onSaveData();
    }
}
