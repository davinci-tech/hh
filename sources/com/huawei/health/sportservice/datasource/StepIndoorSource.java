package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import defpackage.fhs;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.STEP_INDOOR_SOURCE)
/* loaded from: classes8.dex */
public class StepIndoorSource extends BaseSource<Integer> implements IndoorToSource, SportLifecycle {
    private static final String TAG = "SportService_IndoorEquipStepSource";
    private int mPreTreadmillStep;
    private int mSportType = 0;
    private int mStep;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        int i = this.mStep;
        if (i > this.mPreTreadmillStep) {
            ReleaseLogUtil.e(TAG, "treadmillStep: ", Integer.valueOf(i));
            BaseSportManager.getInstance().updateSourceData("08F", "STEP_DATA", Integer.valueOf(this.mStep));
            this.mPreTreadmillStep = this.mStep;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        if (BaseSportManager.getInstance().getStatus() != 1) {
            return;
        }
        int i = this.mSportType;
        if (i == 264 || i == 281) {
            this.mStep = num.intValue();
        } else {
            this.mStep = num.intValue() / 10;
        }
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
        this.mSportType = BaseSportManager.getInstance().getSportType();
        ArrayList arrayList = new ArrayList();
        if (fhs.e(this.mSportType)) {
            arrayList.add(23);
        }
        if (fhs.d(this.mSportType)) {
            arrayList.add(8);
        }
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
