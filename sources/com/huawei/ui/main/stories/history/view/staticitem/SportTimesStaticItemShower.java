package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "TOTAL_SPORT_TIMES")
/* loaded from: classes9.dex */
public class SportTimesStaticItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (getSportType() == 271) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903371_res_0x7f03014b, (int) standardize(), "");
        }
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, (int) standardize(), "");
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        if (this.mSportType == 10001) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_fitness_times);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_times);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_SPORT_TIMES") || !this.mItemDataMap.containsKey("TOTAL_ABNORMAL_SPORT_TIMES")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_SPORT_TIMES or TOTAL_ABNORMAL_SPORT_TIMES");
            return 0.0d;
        }
        double doubleValue = this.mItemDataMap.get("TOTAL_SPORT_TIMES").doubleValue();
        double doubleValue2 = this.mItemDataMap.get("TOTAL_ABNORMAL_SPORT_TIMES").doubleValue();
        LogUtil.a("Track_BaseStasticItemShower", "times = ", Double.valueOf(doubleValue), "  abnormalTime =", Double.valueOf(doubleValue2));
        return doubleValue - doubleValue2;
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        return d == 0.0d ? "--" : UnitUtil.e(d, 1, 0);
    }
}
