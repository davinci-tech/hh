package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.CALORIE_INDOOR_SOURCE)
/* loaded from: classes8.dex */
public class CalorieIndoorSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_CalorieIndoorSource";
    private int mCalorie;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        if (this.mCalorie > 0) {
            BaseSportManager.getInstance().updateSourceData("08F", "CALORIES_DATA", Integer.valueOf(this.mCalorie));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        this.mCalorie = num.intValue();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(6);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (obj instanceof Integer) {
            generateData((Integer) obj);
        }
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
