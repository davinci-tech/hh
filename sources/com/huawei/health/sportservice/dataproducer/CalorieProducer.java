package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.CALORIE_PRODUCER)
/* loaded from: classes8.dex */
public class CalorieProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_CalorieProducer";
    protected int mCaloriesValue;
    private SportLaunchParams mSportLaunchParams;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onSaveData();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("CALORIES_DATA", this);
        this.mSportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mCaloriesValue = ((Integer) obj).intValue();
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("CALORIES_DATA", Integer.valueOf(this.mCaloriesValue));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null || ((IntermitentJumpData) sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class)) == null) {
            Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
            if (data instanceof MotionPathSimplify) {
                MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
                LogUtil.a(TAG, "onSaveData() mCaloriesValue: ", Integer.valueOf(this.mCaloriesValue));
                motionPathSimplify.saveTotalCalories(Math.round(this.mCaloriesValue * 1000 * 1.0f));
                BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
            }
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            int requestTotalCalories = ((MotionPathSimplify) data).requestTotalCalories() / 1000;
            this.mCaloriesValue = requestTotalCalories;
            LogUtil.a(TAG, "recovery mCaloriesValue ", Integer.valueOf(requestTotalCalories));
        }
    }
}
