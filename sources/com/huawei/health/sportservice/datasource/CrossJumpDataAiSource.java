package com.huawei.health.sportservice.datasource;

import androidx.collection.SimpleArrayMap;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@SportComponentType(classify = 3, name = ComponentName.CROSS_JUMP_DATA_SOURCE)
/* loaded from: classes8.dex */
public class CrossJumpDataAiSource extends BaseSource implements SportLifecycle, OnSportCodeListenerWrapper {
    private static final String TAG = "SportService_CrossJumpDataAiSource";

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().setParas(SportParamsType.AI_SPORT_EXAM_SOURCE_LISTENER, this);
    }

    @Override // com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper
    public void getData(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap == null) {
            LogUtil.h(TAG, "getData map == null");
        } else {
            handleStatus(simpleArrayMap);
            handleStepScore(simpleArrayMap);
        }
    }

    private void handleStatus(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("errCode")) {
            Object obj = simpleArrayMap.get("errCode");
            if (obj instanceof Integer) {
                BaseSportManager.getInstance().stagingAndNotification("STATUS_CODE_DATA", Integer.valueOf(((Integer) obj).intValue()));
            }
        }
    }

    private void handleStepScore(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("step")) {
            Object obj = simpleArrayMap.get("step");
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                BaseSportManager.getInstance().stagingAndNotification("SPORT_EXAM_SCORE", Integer.valueOf(intValue));
                ReleaseLogUtil.e(TAG, "jumpStep:", Integer.valueOf(intValue));
            }
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
