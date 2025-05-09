package com.huawei.health.sportservice.manager.skip;

import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.BaseTargetManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;

@SportComponentType(classify = 1, name = ComponentName.TARGET_MANAGER)
/* loaded from: classes8.dex */
public class SkipTargetManager extends BaseTargetManager {
    private static final String TAG = "SportService_SkipTargetManager";

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        if (this.mHasTarget) {
            setTag(TAG);
            int i = this.mTargetType;
            if (i == 0) {
                registerTargetSportData("TIME_ONE_SECOND_DURATION", this.mTargetValue * 1000.0f);
            } else {
                if (i != 5) {
                    return;
                }
                registerTargetSportData("SKIP_NUM_DATA", this.mTargetValue);
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mCallbackOption);
    }
}
