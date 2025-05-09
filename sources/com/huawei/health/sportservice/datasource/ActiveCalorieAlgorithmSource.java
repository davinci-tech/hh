package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 3, name = ComponentName.ACTIVE_CALORIE_ALGORITHM_SOURCE_SOURCE)
/* loaded from: classes8.dex */
public class ActiveCalorieAlgorithmSource extends CalorieAlgorithmSource {
    private static final float SIT_METS = 1.3f;
    private static final String TAG = "SportService_ActiveCalorieAlgorithmSource";

    @Override // com.huawei.health.sportservice.datasource.CalorieAlgorithmSource
    protected float calculateSkipCalorie(float f) {
        float calculateSkipCalorie = super.calculateSkipCalorie(f);
        float f2 = (this.mUserWeight * 0.02171f) / 60.0f;
        LogUtil.a(TAG, "totalCal: ", Float.valueOf(calculateSkipCalorie), "restCal: ", Float.valueOf(f2));
        return Math.max(calculateSkipCalorie - f2, 0.0f) * 1000.0f;
    }

    @Override // com.huawei.health.sportservice.datasource.CalorieAlgorithmSource, com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        LogUtil.a(TAG, "updateSourceData: ", Float.valueOf(this.mCaloriesValue));
        BaseSportManager.getInstance().updateSourceData(TAG, "ACTIVE_CALORIES_DATA", Integer.valueOf(Math.round(this.mCaloriesValue)));
    }

    @Override // com.huawei.health.sportservice.datasource.CalorieAlgorithmSource, com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
