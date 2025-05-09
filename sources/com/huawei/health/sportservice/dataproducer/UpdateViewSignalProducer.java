package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.UPDATE_VIEW_SIGNAL_PRODUCER)
/* loaded from: classes8.dex */
public class UpdateViewSignalProducer extends BaseProducer implements SportLifecycle, HeartRateGetterUtil.HeartRateConnectStateCallBack {
    private static final String TAG = "SportService_UpdateViewSignalProducer";

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport subscibeConnectCallback");
        BaseSportManager.getInstance().registerConnectCallback(this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport staging and notify update view signal");
        BaseSportManager.getInstance().stagingAndNotification("UPDATE_VIEW_SIGNAL", -1);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        BaseSportManager.getInstance().unRegisterConnectCallback();
    }

    @Override // com.huawei.health.suggestion.util.HeartRateGetterUtil.HeartRateConnectStateCallBack
    public void setConnectStateChange(boolean z) {
        LogUtil.a(TAG, "setConnectStateChange staging and notify update view signal");
        BaseSportManager.getInstance().stagingAndNotification("UPDATE_VIEW_SIGNAL", 27);
    }
}
