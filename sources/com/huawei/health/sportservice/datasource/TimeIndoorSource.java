package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.TIME_INDOOR_SOURCE)
/* loaded from: classes8.dex */
public class TimeIndoorSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_TimeIndoorSource";
    private boolean mIsFirstBleTime = true;
    private int mSportDuration;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(2);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "TIME_ONE_SECOND_DURATION", Long.valueOf(this.mSportDuration * 1000));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        int intValue = num.intValue();
        LogUtil.a(TAG, "mCurSportDuration", Integer.valueOf(intValue));
        if (this.mIsFirstBleTime || intValue - this.mSportDuration >= 1) {
            this.mIsFirstBleTime = false;
            BaseSportManager.getInstance().stagingData("IsFirstBleTime", Boolean.valueOf(this.mIsFirstBleTime));
            this.mSportDuration = intValue;
            updateSourceData();
        }
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (obj instanceof Integer) {
            generateData((Integer) obj);
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("IsFirstBleTime");
        if (data instanceof Boolean) {
            boolean booleanValue = ((Boolean) data).booleanValue();
            this.mIsFirstBleTime = booleanValue;
            LogUtil.a(TAG, "recoveryData() mIsFirstBleTime: ", Boolean.valueOf(booleanValue));
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
