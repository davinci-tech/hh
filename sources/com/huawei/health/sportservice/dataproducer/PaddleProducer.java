package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.PADDLE_PRODUCER)
/* loaded from: classes8.dex */
public class PaddleProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_PaddleProducer";
    private int mPaddles;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mPaddles = ((Integer) obj).intValue();
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("PADDLE_DATA", Integer.valueOf(this.mPaddles));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            motionPathSimplify.saveTotalSteps(this.mPaddles);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            int requestTotalSteps = ((MotionPathSimplify) data).requestTotalSteps();
            this.mPaddles = requestTotalSteps;
            LogUtil.a(TAG, "recovery mPaddles ", Integer.valueOf(requestTotalSteps));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("PADDLE_DATA", this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onSaveData();
    }
}
