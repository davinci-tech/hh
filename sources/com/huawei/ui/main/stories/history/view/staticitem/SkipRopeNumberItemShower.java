package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "TOTAL_SKIP_ROPE_NUMBERS")
/* loaded from: classes9.dex */
public class SkipRopeNumberItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_SKIP_ROPE_NUMBERS")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_SKIP_ROPE_NUMBERS");
            return 0.0d;
        }
        return this.mItemDataMap.get("TOTAL_SKIP_ROPE_NUMBERS").doubleValue();
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        LogUtil.c("Track_BaseStasticItemShower", "total rope number =", Double.valueOf(d));
        return d == 0.0d ? "--" : UnitUtil.e(d, 1, 0);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_total_rope_number);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (isDataMapEmpty(this.mItemDataMap) || !this.mItemDataMap.containsKey("TOTAL_SKIP_ROPE_NUMBERS")) {
            return "--";
        }
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, (int) this.mItemDataMap.get("TOTAL_SKIP_ROPE_NUMBERS").doubleValue(), "");
    }
}
