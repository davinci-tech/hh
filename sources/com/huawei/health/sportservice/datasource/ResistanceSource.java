package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.RESISTANCE_SOURCE)
/* loaded from: classes8.dex */
public class ResistanceSource extends BaseSource implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_ResistanceSource";
    private final SupportDataRange mSupportDataRange = new SupportDataRange();

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "SUPPORT_DATA_RANGE_DATA", this.mSupportDataRange);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        switch (i) {
            case 30006:
                LogUtil.a(TAG, "MIN_LEVEL", Integer.valueOf(intValue));
                this.mSupportDataRange.setMinLevel(intValue);
                break;
            case 30007:
                LogUtil.a(TAG, "MAX_LEVEL", Integer.valueOf(intValue));
                this.mSupportDataRange.setMaxLevel(intValue);
                break;
            case 30008:
                LogUtil.a(TAG, "INCREMENT_LEVEL", Integer.valueOf(intValue));
                this.mSupportDataRange.setMinIncrementLevel(intValue);
                break;
        }
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(30007);
        arrayList.add(30006);
        arrayList.add(30008);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
