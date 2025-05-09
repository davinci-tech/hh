package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@SportComponentType(classify = 2, name = ComponentName.STEP_PRODUCER)
/* loaded from: classes8.dex */
public class StepProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_StepProducer";
    private int mPreStep;
    private int mSteps;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        int intValue = ((Integer) obj).intValue();
        if (this.mPreStep > intValue) {
            return;
        }
        this.mSteps = intValue;
        ReleaseLogUtil.e(TAG, "onChange step: ", Integer.valueOf(intValue));
        onStagingAndNotification();
        this.mPreStep = intValue;
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("STEP_DATA", Integer.valueOf(this.mSteps));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            motionPathSimplify.saveTotalSteps(this.mSteps);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (!(data instanceof MotionPathSimplify)) {
            LogUtil.b(TAG, "recovery failed. data not MotionPathSimplify type");
            return;
        }
        int requestTotalSteps = ((MotionPathSimplify) data).requestTotalSteps();
        this.mSteps = requestTotalSteps;
        LogUtil.a(TAG, "recovery mSteps ", Integer.valueOf(requestTotalSteps));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("STEP_DATA", this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onSaveData();
    }
}
