package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.DeviceToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.lau;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.CALORIE_WATCH_SOURCE)
/* loaded from: classes8.dex */
public class CalorieWatchSource extends BaseSource<Integer> implements SportLifecycle, DeviceToSource {
    private static final String TAG = "SportService_CalorieWatchSource";
    private int mCalorie;
    private boolean mIsSendCalorieToDevice;
    private int mPreWatchCalorie;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData("06D", "CALORIES_DATA", Integer.valueOf(this.mCalorie / 1000));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        int i;
        this.mCalorie = num.intValue();
        if (BaseSportManager.getInstance().getSportType() == 279) {
            LogUtil.a(TAG, "need to updateSourceData CalorieWatchSource ", Integer.valueOf(this.mCalorie));
            updateSourceData();
        }
        if (!this.mIsSendCalorieToDevice || (i = this.mCalorie) <= this.mPreWatchCalorie) {
            return;
        }
        LogUtil.a(TAG, "mIsSendCalorieToDevice updateSourceData CalorieWatchSource ", Integer.valueOf(i));
        updateSourceData();
        this.mPreWatchCalorie = this.mCalorie;
    }

    @Override // com.huawei.health.sportservice.inter.DeviceToSource
    public void onDeviceDataChanged(String str, Object obj) {
        if (obj instanceof Integer) {
            generateData((Integer) obj);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CALORIES_DATA");
        BaseSportManager.getInstance().subscribeToDevice(arrayList, this);
        this.mIsSendCalorieToDevice = lau.d().i();
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
