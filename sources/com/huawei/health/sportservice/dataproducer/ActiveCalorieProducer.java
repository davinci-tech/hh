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

@SportComponentType(classify = 2, name = ComponentName.ACTIVE_CALORIE_PRODUCER)
/* loaded from: classes8.dex */
public class ActiveCalorieProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_ActiveCalorieProducer";
    private int mActiveCaloriesValue;
    private SportLaunchParams mSportLaunchParams;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        onSaveData();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("ACTIVE_CALORIES_DATA", this);
        this.mSportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        int intValue = ((Integer) obj).intValue();
        this.mActiveCaloriesValue = intValue;
        LogUtil.a(TAG, "onSourceDataChanged ", Integer.valueOf(intValue));
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("ACTIVE_CALORIES_DATA", Integer.valueOf(this.mActiveCaloriesValue));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        SportLaunchParams sportLaunchParams = this.mSportLaunchParams;
        if (sportLaunchParams == null || ((IntermitentJumpData) sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class)) == null) {
            Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
            if (data instanceof MotionPathSimplify) {
                MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
                LogUtil.a(TAG, "onSaveData() mActiveCaloriesValue: ", Integer.valueOf(this.mActiveCaloriesValue));
                int i = this.mActiveCaloriesValue;
                if (i > 0) {
                    motionPathSimplify.saveActiveCalories(i);
                }
                BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
            }
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        int requestActiveCalories;
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (!(data instanceof MotionPathSimplify) || (requestActiveCalories = ((MotionPathSimplify) data).requestActiveCalories()) <= 0) {
            return;
        }
        this.mActiveCaloriesValue = requestActiveCalories;
        LogUtil.a(TAG, "recovery mCaloriesValue ", Integer.valueOf(requestActiveCalories));
    }
}
