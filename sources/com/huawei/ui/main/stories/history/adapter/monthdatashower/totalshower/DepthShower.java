package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rcg;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

@MonthStatisticViewType(type = "DEPTH")
/* loaded from: classes9.dex */
public class DepthShower extends rcg {
    private static final int DMTOM = 10;

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mContext == null) {
            LogUtil.b(rcg.TAG, "DepthShower mContext is null");
            return "";
        }
        return this.mContext.getResources().getString(UnitUtil.h() ? R$string.IDS_motiontrack_history_max_deep_inch_unit : R$string.IDS_motiontrack_history_max_deep_unit);
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getMainMonthData() {
        ArrayList<String> trackRequestDataBase = this.mHwSportTypeInfo.getHistoryList().getTrackRequestDataBase(this.mHwSportTypeInfo.getSportTypeId());
        if (trackRequestDataBase == null || trackRequestDataBase.size() < 1 || this.mMonthData == null || !this.mMonthData.containsKey(trackRequestDataBase.get(0))) {
            return "---";
        }
        this.mMainData = this.mMonthData.get(trackRequestDataBase.get(0));
        return this.mMainData == null ? "---" : processDataToString(standardization(this.mMainData.doubleValue()));
    }

    @Override // defpackage.rcg
    public String processDataToString(double d) {
        LogUtil.a(rcg.TAG, "processDataToString", Double.valueOf(d));
        return UnitUtil.e(UnitUtil.h() ? UnitUtil.d(d / 10.0d, 1) : d / 10.0d, 1, 1);
    }
}
