package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.AI_TRAIN_STATUS_CODE_PRODUCER)
/* loaded from: classes8.dex */
public class StatusCodeProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_StatusCodeProducer";
    private int mStatusCode;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        int intValue = ((Integer) obj).intValue();
        this.mStatusCode = intValue;
        LogUtil.a(TAG, "mStatusCode ", Integer.valueOf(intValue));
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("STATUS_CODE_DATA", this);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("STATUS_CODE_DATA", Integer.valueOf(this.mStatusCode));
    }
}
