package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rcg;
import defpackage.rdu;
import health.compact.a.UnitUtil;

@MonthStatisticViewType(type = "METER_DISTANCE")
/* loaded from: classes9.dex */
public class MeterDistanceShower extends rcg {
    private static final String TAG = "Track_MeterDistanceShower";

    @Override // defpackage.rcg
    public double standardization(double d) {
        LogUtil.a(TAG, "processDataToString Meter standardization");
        return d;
    }

    @Override // defpackage.rcg
    public String processDataToString(double d) {
        LogUtil.a(TAG, "processDataToString MeterDistanceShower standardization");
        if (UnitUtil.h()) {
            double e = UnitUtil.e(d, 2);
            return e < 0.005d ? "--" : UnitUtil.e(e, 1, 0);
        }
        return UnitUtil.e(d, 1, 0);
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mHwSportTypeInfo == null) {
            LogUtil.b(TAG, "mHwSportTypeInfo is null");
            return "";
        }
        if (rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext) == null) {
            LogUtil.b(TAG, "MeterDistanceShower TrackSportHistoryUtils.getSportTypeString(mHwSportTypeInfo.getSportTypeId(), mContext) == null");
            return "";
        }
        if (UnitUtil.h()) {
            return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_all_unit, rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext), this.mContext.getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, 100));
        }
        return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_all_unit, rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext), this.mContext.getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit));
    }
}
