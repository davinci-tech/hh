package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rcg;

@MonthStatisticViewType(type = "FIRST_DURATION")
/* loaded from: classes9.dex */
public class FirstDurationShower extends DurationShower {
    @Override // com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower.DurationShower, defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mContext == null) {
            LogUtil.b(rcg.TAG, "FirstDurationShower  mContext is null");
            return "";
        }
        return this.mContext.getResources().getString(R$string.IDS_motiontrack_history_sum_catetory_time_unit_min);
    }
}
