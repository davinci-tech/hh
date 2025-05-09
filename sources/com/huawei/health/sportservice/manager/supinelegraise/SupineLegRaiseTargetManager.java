package com.huawei.health.sportservice.manager.supinelegraise;

import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.manager.BaseTargetManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 1, name = ComponentName.TARGET_MANAGER)
/* loaded from: classes8.dex */
public class SupineLegRaiseTargetManager extends BaseTargetManager {
    private static final String TAG = "SportService_SupineLegRaiseTargetManager";

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
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mCallbackOption);
    }
}
