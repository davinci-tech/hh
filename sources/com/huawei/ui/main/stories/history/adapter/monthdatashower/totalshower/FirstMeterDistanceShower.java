package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import health.compact.a.UnitUtil;

@MonthStatisticViewType(type = "FIRST_METER_DISTANCE")
/* loaded from: classes9.dex */
public class FirstMeterDistanceShower extends MeterDistanceShower {
    private static final String TAG = "Track_FirstMeterDistanceShower";

    @Override // com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower.MeterDistanceShower, defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mContext == null) {
            LogUtil.b(TAG, "FirstMeterDistanceShower mContext is null");
            return "";
        }
        if (UnitUtil.h()) {
            return this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_swim_distance_yard);
        }
        return this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_swim_distance_meter);
    }
}
