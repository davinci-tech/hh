package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.DeviceToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.SENSOR_SOURCE)
/* loaded from: classes8.dex */
public class SensorStepSource extends BaseSource<Integer> implements SportLifecycle, DeviceToSource {
    private static final String TAG = "SportService_SensorStepSource";
    private int mSensorStep = 0;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        ReleaseLogUtil.e(TAG, "mSensorStep = ", Integer.valueOf(this.mSensorStep));
        BaseSportManager.getInstance().updateSourceData("A12", "STEP_DATA", Integer.valueOf(this.mSensorStep));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        this.mSensorStep += num.intValue();
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("STEP_DATA");
        BaseSportManager.getInstance().subscribeToSensor(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.inter.DeviceToSource
    public void onDeviceDataChanged(String str, Object obj) {
        if (obj instanceof Integer) {
            generateData((Integer) obj);
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
