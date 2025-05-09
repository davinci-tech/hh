package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "TOTAL_MAX_DISTANCE")
/* loaded from: classes9.dex */
public class DivingDepthItemShower extends BaseStasticItemShower {
    private static final int DMTOM = 10;

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_MAX_DISTANCE")) {
            LogUtil.h("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_MAX_DISTANCE");
            return 0.0d;
        }
        return this.mItemDataMap.get("TOTAL_MAX_DISTANCE").doubleValue();
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        if (d == 0.0d) {
            return "0.0";
        }
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(d / 10.0d, 1), 1, 1);
        }
        return UnitUtil.e(d / 10.0d, 1, 1);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return "--";
        }
        if (UnitUtil.h()) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_ft);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_maximum_depth_under_water);
    }
}
