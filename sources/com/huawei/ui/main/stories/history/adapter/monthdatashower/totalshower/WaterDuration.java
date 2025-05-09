package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rcg;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

@MonthStatisticViewType(type = "WATER_DURATION")
/* loaded from: classes9.dex */
public class WaterDuration extends rcg {
    @Override // defpackage.rcg
    public double standardization(double d) {
        return d;
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mContext == null) {
            LogUtil.h(rcg.TAG, "WaterDuration mContext is null");
            return "";
        }
        return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_sum_time_unit);
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getMainMonthData() {
        ArrayList<String> trackRequestDataBase = this.mHwSportTypeInfo.getHistoryList().getTrackRequestDataBase(this.mHwSportTypeInfo.getSportTypeId());
        if (trackRequestDataBase == null || trackRequestDataBase.size() < 2 || this.mMonthData == null || !this.mMonthData.containsKey(trackRequestDataBase.get(1))) {
            return "---";
        }
        this.mMainData = this.mMonthData.get(trackRequestDataBase.get(1));
        return this.mMainData == null ? "---" : processDataToString(standardization(this.mMainData.doubleValue()));
    }

    @Override // defpackage.rcg
    public String processDataToString(double d) {
        LogUtil.a(rcg.TAG, "processDataToString", Double.valueOf(d));
        return UnitUtil.e(d / 60.0d, 1, 2);
    }
}
