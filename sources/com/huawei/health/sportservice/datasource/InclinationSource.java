package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.INCLINATION_SOURCE)
/* loaded from: classes8.dex */
public class InclinationSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_InclinationSource";
    private double mInclinationSource;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(TAG, "INCLINATION_DATA", Double.valueOf(this.mInclinationSource));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        double intValue = num.intValue() / 10.0d;
        this.mInclinationSource = intValue;
        LogUtil.a(TAG, "mInclinationSource", Double.valueOf(intValue));
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (obj instanceof Integer) {
            generateData((Integer) obj);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(10);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
