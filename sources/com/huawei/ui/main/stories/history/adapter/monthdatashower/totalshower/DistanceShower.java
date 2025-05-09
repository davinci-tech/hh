package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rcg;
import defpackage.rdu;
import health.compact.a.UnitUtil;

@MonthStatisticViewType(type = "DISTANCE")
/* loaded from: classes7.dex */
public class DistanceShower extends rcg {
    private static final String TAG = "Track_DistanceShower";

    @Override // defpackage.rcg
    public double standardization(double d) {
        return d / 1000.0d;
    }

    @Override // defpackage.rcg
    public String processDataToString(double d) {
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(d, 3), 1, 2);
        }
        return UnitUtil.e(d, 1, 2);
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mHwSportTypeInfo == null) {
            LogUtil.b(TAG, "DistanceShower mHwSportTypeInfo is null");
            return "";
        }
        if (rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext) == null) {
            LogUtil.b(TAG, "DistanceShower TrackSportHistoryUtils.getSportTypeString(mHwSportTypeInfo.getSportTypeId(), mContext) == null");
            return "";
        }
        if (UnitUtil.h()) {
            return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_all_unit, rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext), this.mContext.getResources().getString(R$string.IDS_band_data_sport_distance_unit_en));
        }
        return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_all_unit, rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext), this.mContext.getResources().getString(R$string.IDS_band_data_sport_distance_unit));
    }
}
