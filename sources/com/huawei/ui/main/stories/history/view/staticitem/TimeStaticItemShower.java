package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import defpackage.cat;

@StasticViewType(getDataType = "TOTAL_TIME")
/* loaded from: classes9.dex */
public class TimeStaticItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        return isDataMapEmpty(this.mItemDataMap) ? "--" : cat.a(this.mItemDataMap.get("TOTAL_TIME").doubleValue());
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        if (this.mSportType == 217 || this.mSportType == 219 || this.mSportType == 218) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_total_ski_time);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_total_duration);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_TIME")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_TIME");
            return 0.0d;
        }
        return this.mItemDataMap.get("TOTAL_TIME").doubleValue() / 1000.0d;
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        return d == 0.0d ? "--" : cat.b(d);
    }
}
