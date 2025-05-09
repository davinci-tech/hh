package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.DeviceToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.MULTI_SOURCE)
/* loaded from: classes8.dex */
public class MultiSources extends BaseSource implements SportLifecycle, DeviceToSource {
    private static final String TAG = "SportService_MultiSources";

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.inter.DeviceToSource
    public void onDeviceDataChanged(String str, Object obj) {
        BaseSportManager.getInstance().stagingAndNotification(str, obj);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("AEROBIC_TRAINING_EFFECT_DATA");
        arrayList.add("ANAEROBIC_TRAINING_EFFECT_DATA");
        arrayList.add("RECOVERY_TIME_DATA");
        arrayList.add("DESCENT_DATA");
        arrayList.add("CREEP_DATA");
        arrayList.add("ALTITUDE_DATA");
        arrayList.add("VO2MAX_DATA");
        arrayList.add("REPORT_TIME_DATA");
        arrayList.add("ETE_ALGO_DATA");
        BaseSportManager.getInstance().subscribeToDevice(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
