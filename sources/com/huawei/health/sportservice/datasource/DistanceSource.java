package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.DISTANCE_SOURCE)
/* loaded from: classes8.dex */
public class DistanceSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final float DISTANCE_COEFFICIENT = 6.214E-4f;
    private static final int JUMP_LONG_DISTANCE = 200;
    private static final int JUMP_SHORT_DISTANCE = 100;
    private static final int JUMP_SHORT_TIME = 5;
    private static final String TAG = "SportService_DistanceSource";
    private int mDistance;
    private float mDistanceMile = 0.0f;
    private int mLastDistance = 0;
    private long mLastTime;
    private long mTime;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "DISTANCE_DATA", Integer.valueOf(this.mDistance));
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "DISTANCE_MILE_DATA", Float.valueOf(this.mDistanceMile));
        BaseSportManager.getInstance().stagingAndNotification("DISTANCE_MILE_DATA", Float.valueOf(this.mDistanceMile));
        this.mLastDistance = this.mDistance;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        if (this.mLastDistance > 0) {
            int intValue = num.intValue();
            int i = this.mLastDistance;
            if (intValue - i > 100 && this.mTime - this.mLastTime < 5) {
                LogUtil.b(TAG, "distance short jump", " distance = ", num, " mLastDistance = ", Integer.valueOf(i), " mLastTime ", Long.valueOf(this.mLastTime), " mTime ", Long.valueOf(this.mTime));
                return;
            }
            int intValue2 = num.intValue();
            int i2 = this.mLastDistance;
            if (intValue2 - i2 >= 200 || i2 > num.intValue()) {
                LogUtil.b(TAG, "distance jump or short", " distance = ", num, " mLastDistance = ", Integer.valueOf(this.mLastDistance));
                return;
            }
        }
        this.mDistance = num.intValue();
        this.mDistanceMile = num.intValue() * DISTANCE_COEFFICIENT;
        updateSourceData();
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (!(obj instanceof Integer)) {
            LogUtil.b(TAG, "onIndoorDataChanged object is invalid.");
            return;
        }
        if (i == 1) {
            generateData((Integer) obj);
        } else if (i == 2) {
            this.mLastTime = this.mTime;
            this.mTime = ((Integer) obj).intValue();
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
