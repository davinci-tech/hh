package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hihealth.dictionary.model.HealthDataStatPolicy;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rdu;

@MonthStatisticViewType(type = HealthDataStatPolicy.COUNT)
/* loaded from: classes9.dex */
public class CountShower extends TimesShower {
    @Override // com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower.TimesShower, defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        return this.mContext == null ? "" : this.mContext.getResources().getString(R$string.IDS_motiontrack_history_all_unit, rdu.c(this.mHwSportTypeInfo.getSportTypeId(), this.mContext), this.mContext.getResources().getString(R$string.IDS_awake_times));
    }
}
