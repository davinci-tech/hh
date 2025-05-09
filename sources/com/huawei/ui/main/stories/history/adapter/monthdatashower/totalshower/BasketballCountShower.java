package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.rcg;
import health.compact.a.UnitUtil;

@MonthStatisticViewType(type = "BASKETBALL_COUNT")
/* loaded from: classes9.dex */
public class BasketballCountShower extends rcg {
    private static final String TAG = "Track_BasketballCountShower";

    @Override // defpackage.rcg
    public double standardization(double d) {
        LogUtil.a(TAG, "processDataToString BasketballCountShower");
        return d;
    }

    @Override // defpackage.rcg
    public String processDataToString(double d) {
        LogUtil.a(TAG, "processDataToString BasketballCountShower");
        return UnitUtil.e(d, 1, 0);
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mContext == null) {
            LogUtil.b(TAG, "BasketballCountShower  mContext is null");
            return "";
        }
        return this.mContext.getResources().getString(R$string.IDS_aw_version2_basketball_with_unit);
    }
}
