package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.PADDLE_TIMES_SOURCE)
/* loaded from: classes8.dex */
public class PaddleTimesSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final String TAG = "SportService_PaddleTimesSource";
    private int mPaddles = 0;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(TAG, "PADDLE_DATA", Integer.valueOf(this.mPaddles));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        this.mPaddles = num.intValue();
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(27);
        arrayList.add(38);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (obj instanceof Integer) {
            if (i == 27 || i == 38) {
                generateData((Integer) obj);
            }
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
