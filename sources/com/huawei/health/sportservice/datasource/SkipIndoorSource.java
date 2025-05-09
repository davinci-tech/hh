package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.SKIP_INDOOR_SOURCE)
/* loaded from: classes8.dex */
public class SkipIndoorSource extends BaseSource implements IndoorToSource, SportLifecycle {
    private static final String TAG = "SportService_SkipIndoorSource";
    private int mContinuesJumps;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(40003);
        arrayList.add(40009);
        arrayList.add(40012);
        arrayList.add(40010);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        switch (i) {
            case 40003:
                if (obj instanceof Integer) {
                    Integer num = (Integer) obj;
                    if (this.mContinuesJumps != num.intValue()) {
                        this.mContinuesJumps = num.intValue();
                        BaseSportManager.getInstance().stagingAndNotification("CONTINUOUS_SKIPPING_JUMP_DATA", Integer.valueOf(this.mContinuesJumps));
                        break;
                    }
                }
                break;
            case 40009:
                BaseSportManager.getInstance().stagingAndNotification("ROPE_SKIPPING_MODE_DATA", obj);
                break;
            case 40010:
                if (obj instanceof Integer) {
                    BaseSportManager.getInstance().stagingAndNotification("INSTANTANEOUS_SPEED", obj);
                    break;
                }
                break;
            case 40012:
                BaseSportManager.getInstance().stagingAndNotification("ROPE_SKIPPING_TRICK_DATA", obj);
                break;
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
