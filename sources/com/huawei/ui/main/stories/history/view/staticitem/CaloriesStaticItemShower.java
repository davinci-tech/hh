package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "TOTAL_CALORIES")
/* loaded from: classes9.dex */
public class CaloriesStaticItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_track_total_calories);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return "--";
        }
        double doubleValue = this.mItemDataMap.get("TOTAL_CALORIES").doubleValue() / 1000.0d;
        if (this.mIsChinese && doubleValue >= 100000.0d) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_skiing_ten_thousand_kcal, "");
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_plugindameon_hw_show_sport_kcal_string, "");
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_CALORIES")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_CALORIES");
            return 0.0d;
        }
        return this.mItemDataMap.get("TOTAL_CALORIES").doubleValue() / 1000.0d;
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        if (d == 0.0d) {
            return "--";
        }
        LogUtil.c("Track_BaseStasticItemShower", "CALORIES  is  ", Double.valueOf(d));
        if (this.mIsChinese && d >= 100000.0d) {
            return UnitUtil.e(d / 10000.0d, 1, 2);
        }
        return UnitUtil.e(d, 1, 0);
    }
}
