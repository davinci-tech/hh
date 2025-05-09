package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.cat;
import defpackage.koq;
import defpackage.rcg;
import defpackage.rdu;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

@MonthStatisticViewType(type = PlayInfo.KEY_DURATION)
/* loaded from: classes9.dex */
public class DurationShower extends rcg {
    @Override // defpackage.rcg
    public double standardization(double d) {
        return d / 1000.0d;
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getMainMonthData() {
        String str;
        ArrayList<String> allRequestString = this.mHwSportTypeInfo.getHistoryList().getAllRequestString(this.mHwSportTypeInfo.getSportTypeId());
        if (koq.b(allRequestString)) {
            return "--";
        }
        if (this.mHwSportTypeInfo.getSportTypeId() != 262) {
            str = allRequestString.get(0);
        } else {
            if (koq.b(allRequestString, 3)) {
                return "--";
            }
            str = allRequestString.get(3);
        }
        if (this.mMonthData != null && this.mMonthData.containsKey(str)) {
            this.mMainData = this.mMonthData.get(str);
            return this.mMainData == null ? "--" : processDataToString(standardization(this.mMainData.doubleValue()));
        }
        return UnitUtil.e(0.0d, 1, 0);
    }

    @Override // defpackage.rcg
    public String processDataToString(double d) {
        return cat.b(d);
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        String string;
        if (this.mHwSportTypeInfo == null) {
            LogUtil.b(rcg.TAG, "DurationShower mHwSportTypeInfo is null");
            return "";
        }
        if (rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext) == null) {
            LogUtil.b(rcg.TAG, "DurationShower getSportTypeString(mHwSportTypeInfo.getSportTypeId(), mContext) == null");
            return "";
        }
        if (this.mMainData.doubleValue() < 60000.0d) {
            string = this.mContext.getResources().getString(R$string.IDS_second);
        } else if (this.mMainData.doubleValue() < 3600000.0d) {
            string = this.mContext.getResources().getString(R$string.IDS_messagecenter_time_minute_value);
        } else {
            string = this.mContext.getResources().getString(R$string.IDS_hw_show_main_home_page_hours);
        }
        return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_all_unit, rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext), string);
    }
}
