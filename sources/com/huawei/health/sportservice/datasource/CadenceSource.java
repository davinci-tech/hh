package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.CADENCE_SOURCE)
/* loaded from: classes8.dex */
public class CadenceSource extends BaseSource<Integer> implements IndoorToSource, SportLifecycle {
    private static final String TAG = "SportService_CadenceSource";
    private int mCadence = 0;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(TAG, "CADENCE_DATA", Integer.valueOf(this.mCadence));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        this.mCadence = num.intValue() / 2;
        updateSourceData();
        LogUtil.a(TAG, "mCadence", Integer.valueOf(this.mCadence));
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
        arrayList.add(31);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
