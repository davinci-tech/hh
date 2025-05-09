package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "VERTICAL_JUMPS")
/* loaded from: classes9.dex */
public class VerticalJumpsStaticsItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("VERTICAL_JUMPS")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey VERTICAL_JUMPS");
            return 0.0d;
        }
        return this.mItemDataMap.get("VERTICAL_JUMPS").doubleValue();
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        return d == 0.0d ? "--" : UnitUtil.e(d, 1, 0);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_aw_version2_jump_times);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_awake_times);
    }
}
