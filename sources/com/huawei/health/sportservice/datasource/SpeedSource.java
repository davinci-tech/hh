package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

@SportComponentType(classify = 3, name = ComponentName.SPEED_SOURCE)
/* loaded from: classes8.dex */
public class SpeedSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final int CRITICAL_RATIO = 5;
    private static final int INSTANTANEOUS_SPEED_RAW_MAX = 500;
    private static final String TAG = "SportService_SpeedSource";
    private static final double THRESHOLD = 1.0E-9d;
    private static final int UNIT_CONVERSION_TO_KM_PER_HOUR = 360;
    private int mCurrentTime;
    private boolean mIsSpeedNeedToDivisionTen;
    private boolean mIsSpeedNormal;
    private int mLastDistance;
    private int mLastTime;
    private int mSpeed;
    private int mTempSpeedViaCompute;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "SPEED_DATA", Integer.valueOf(this.mSpeed));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
        if (!this.mIsSpeedNormal) {
            cleanSpeedData(num.intValue());
        } else {
            this.mSpeed = num.intValue();
        }
        updateSourceData();
    }

    private void calculateCurrentSpeed(int i) {
        float f = this.mCurrentTime - this.mLastTime;
        float f2 = ((double) Math.abs(f)) < THRESHOLD ? 0.0f : i / f;
        if (f2 < 0.0f) {
            f2 = -f2;
        }
        this.mTempSpeedViaCompute = ((int) f2) * 360;
    }

    private void cleanSpeedData(int i) {
        if (i >= 500 || this.mIsSpeedNeedToDivisionTen) {
            LogUtil.a(TAG, "in detectInvalidSpeedValue, in branch of speed>=5km/h");
            if (this.mTempSpeedViaCompute > 0 || this.mIsSpeedNeedToDivisionTen) {
                LogUtil.a(TAG, "in detectInvalidSpeedValue, in branch of speed>=5km/h, now mTempSpeedViaCompute > 0");
                boolean z = this.mIsSpeedNeedToDivisionTen;
                if ((!z && i / this.mTempSpeedViaCompute > 5) || z) {
                    LogUtil.a(TAG, "in detectInvalidSpeedValue, in branch of speed>=5km/h, ", "now we think the speed is ten times larger than reality");
                    this.mSpeed = i / 10;
                    this.mIsSpeedNeedToDivisionTen = true;
                    BaseSportManager.getInstance().stagingData("IsSpeedNeedToDivisionTen", true);
                } else {
                    LogUtil.a(TAG, "in detectInvalidSpeedValue, in branch of speed>=5km/h, ", "but speed is normal, now set mIsSpeedNormal true");
                    this.mIsSpeedNormal = true;
                    saveStagingData();
                }
            } else {
                LogUtil.h(TAG, "in detectInvalidSpeedValue, mTempSpeedViaCompute is still", " under computation, set mInstantaneousSpeedRaw 0");
                this.mSpeed = 0;
                return;
            }
        } else if (i > 0) {
            LogUtil.a(TAG, "in detectInvalidSpeedValue, in branch of 0<speed<5, set mIsSpeedNormal true");
            this.mIsSpeedNormal = true;
            saveStagingData();
        } else {
            LogUtil.h(TAG, "Unknown state in detectAbnormalSpeedValue");
        }
        if (i >= 0 && i <= 4000) {
            LogUtil.a(TAG, "speed", Integer.valueOf(i));
            this.mSpeed = i;
        } else {
            LogUtil.a(TAG, "in detectInvalidSpeedValue, speed is invalid, now set it 0");
            this.mSpeed = 0;
        }
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        int i2;
        if (obj instanceof Integer) {
            if (i == 3) {
                generateData((Integer) obj);
                return;
            }
            if (i == 2) {
                Integer num = (Integer) obj;
                if (num.intValue() > this.mLastTime) {
                    this.mLastTime = this.mCurrentTime;
                    this.mCurrentTime = num.intValue();
                    return;
                }
                return;
            }
            if (i != 1) {
                if (i == 14) {
                    BaseSportManager.getInstance().stagingAndNotification("PACE_DATA", obj);
                    return;
                }
                return;
            }
            int intValue = ((Integer) obj).intValue();
            if (intValue > 0 && this.mLastDistance == 0) {
                this.mLastDistance = intValue;
                return;
            }
            if (this.mCurrentTime - this.mLastTime <= 0 || (i2 = this.mLastDistance) <= 0 || intValue <= i2) {
                return;
            }
            calculateCurrentSpeed(intValue - i2);
            this.mLastDistance = intValue;
            this.mLastTime = this.mCurrentTime;
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(14);
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
        this.mIsSpeedNormal = true;
        saveStagingData();
    }

    private void saveStagingData() {
        BaseSportManager.getInstance().stagingData("IsSpeedNormal", true);
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("IsSpeedNormal");
        if (data instanceof Boolean) {
            boolean booleanValue = ((Boolean) data).booleanValue();
            this.mIsSpeedNormal = booleanValue;
            LogUtil.a(TAG, "recoveryData() mIsSpeedNormal: ", Boolean.valueOf(booleanValue));
        }
        Object data2 = BaseSportManager.getInstance().getData("IsSpeedNeedToDivisionTen");
        if (data2 instanceof Boolean) {
            boolean booleanValue2 = ((Boolean) data2).booleanValue();
            this.mIsSpeedNeedToDivisionTen = booleanValue2;
            LogUtil.a(TAG, "recoveryData() mIsSpeedNeedToDivisionTen: ", Boolean.valueOf(booleanValue2));
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
