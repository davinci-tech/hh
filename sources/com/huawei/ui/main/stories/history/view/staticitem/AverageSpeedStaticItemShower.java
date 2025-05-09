package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import defpackage.hji;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "AVERAGE_SPEED")
/* loaded from: classes9.dex */
public class AverageSpeedStaticItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_TIME") || !this.mItemDataMap.containsKey("TOTAL_DISTANCE")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_TIME or TOTAL_DISTANCE");
            return 0.0d;
        }
        double doubleValue = this.mItemDataMap.get("TOTAL_DISTANCE").doubleValue() / 1000.0d;
        if (doubleValue <= 0.0d) {
            return 0.0d;
        }
        double doubleValue2 = this.mItemDataMap.get("TOTAL_TIME").doubleValue() / 1000.0d;
        if (UnitUtil.h()) {
            doubleValue *= 0.6213712d;
        }
        return (float) (doubleValue2 / doubleValue);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        return d == 0.0d ? "--" : hji.o((float) d);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (UnitUtil.h()) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903130_res_0x7f03005a, 0, "");
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_plugin_fitnessadvice_km_per_hour, "");
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        if (this.mSportType == 217 || this.mSportType == 219 || this.mSportType == 218) {
            return BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_motiontrack_sport_data_average_ski_speed);
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_detail_averagespeed);
    }
}
