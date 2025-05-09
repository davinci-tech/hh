package com.huawei.health.sportservice.manager.aitrain;

import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.BaseTargetManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 1, name = ComponentName.TARGET_MANAGER)
/* loaded from: classes8.dex */
public class AiActionTrainTargetManager extends BaseTargetManager {
    private static final String TAG = "SportService_AiActionTrainTargetManager";

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        if (!this.mHasTarget) {
            LogUtil.a(TAG, "mHasTarget false");
            return;
        }
        setTag(TAG);
        if (this.mTargetType == 0) {
            registerTargetSportData("TIME_ONE_SECOND_DURATION", this.mTargetValue * 1000.0f);
        } else if (this.mTargetType == 5) {
            registerTargetSportData("AI_TRAIN_VALID_TIMES", this.mTargetValue);
        } else {
            LogUtil.a(TAG, "not support: mTargetType = ", Integer.valueOf(this.mTargetType), " mTargetValue ", Float.valueOf(this.mTargetValue));
        }
        LogUtil.a(TAG, "onPreSport mTargetType = ", Integer.valueOf(this.mTargetType), " mTargetValue ", Float.valueOf(this.mTargetValue));
    }

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mCallbackOption);
    }
}
