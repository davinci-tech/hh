package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;

@MonthStatisticViewType(type = "DIVING_COUNT")
/* loaded from: classes9.dex */
public class DivingCountShower extends TimesShower {
    @Override // com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower.TimesShower, defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        return this.mContext == null ? "" : this.mContext.getResources().getString(R$string.IDS_diving_time);
    }
}
