package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "TOTAL_STEPS")
/* loaded from: classes9.dex */
public class StepStaticItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_STEPS")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_STEPS");
            return 0.0d;
        }
        return this.mItemDataMap.get("TOTAL_STEPS").doubleValue();
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        if (d == 0.0d) {
            return "--";
        }
        LogUtil.c("Track_BaseStasticItemShower", "total step =", Double.valueOf(d));
        if (this.mIsChinese && d >= 100000.0d) {
            return UnitUtil.e(d / 10000.0d, 1, 2);
        }
        return UnitUtil.e(d, 1, 0);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return "--";
        }
        if (this.mIsChinese && this.mItemDataMap.get("TOTAL_STEPS").doubleValue() >= 100000.0d) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_ten_thousand_step);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_settings_steps_unit);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_total_steps);
    }
}
