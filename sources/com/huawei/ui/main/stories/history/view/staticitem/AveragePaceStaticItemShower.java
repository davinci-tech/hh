package com.huawei.ui.main.stories.history.view.staticitem;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.view.BaseStasticItemShower;
import com.huawei.ui.main.stories.history.view.StasticViewType;
import defpackage.gvv;
import health.compact.a.UnitUtil;

@StasticViewType(getDataType = "AVERAGE_PACE")
/* loaded from: classes9.dex */
public class AveragePaceStaticItemShower extends BaseStasticItemShower {
    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public double standardize() {
        double d;
        if (isDataMapEmpty(this.mItemDataMap)) {
            return 0.0d;
        }
        if (!this.mItemDataMap.containsKey("TOTAL_TIME") || !this.mItemDataMap.containsKey("TOTAL_DISTANCE")) {
            LogUtil.b("Track_BaseStasticItemShower", "mItemDataMap is not containsKey TOTAL_TIME or TOTAL_DISTANCE");
            return 0.0d;
        }
        double doubleValue = this.mItemDataMap.get("TOTAL_DISTANCE").doubleValue() / 1000.0d;
        double doubleValue2 = this.mItemDataMap.get("TOTAL_TIME").doubleValue() / 1000.0d;
        double doubleValue3 = this.mItemDataMap.get("TOTAL_DISTANCE").doubleValue();
        LogUtil.c("Track_BaseStasticItemShower", "AveragePaceStaticItemShower  distance =", Double.valueOf(doubleValue));
        if (UnitUtil.h()) {
            if (this.mSportType == 262) {
                if (doubleValue3 <= 0.0d) {
                    LogUtil.b("Track_BaseStasticItemShower", "distance value is  invalied");
                    return 0.0d;
                }
                doubleValue3 *= 1.0936000347137451d;
                d = (doubleValue2 / doubleValue3) * 100.0d;
            } else {
                if (doubleValue2 <= 0.0d || doubleValue <= 0.0d) {
                    LogUtil.b("Track_BaseStasticItemShower", " distance value is  invalied");
                    return 0.0d;
                }
                doubleValue *= 0.6213712d;
                d = doubleValue2 / doubleValue;
            }
        } else if (this.mSportType == 262) {
            if (doubleValue3 <= 0.0d) {
                LogUtil.b("Track_BaseStasticItemShower", "buildTotalAveragePaceItem  distance value is  invalied");
                return 0.0d;
            }
            d = (doubleValue2 / doubleValue3) * 100.0d;
        } else {
            if (doubleValue2 <= 0.0d || doubleValue <= 0.0d) {
                LogUtil.b("Track_BaseStasticItemShower", "buildTotalAveragePaceItem  distance value is  invalied");
                return 0.0d;
            }
            d = doubleValue2 / doubleValue;
        }
        return (float) d;
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower
    public String processDataToString(double d) {
        return d == 0.0d ? "--" : gvv.a((float) d);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getMainStaticName() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_detail_avragepace);
    }

    @Override // com.huawei.ui.main.stories.history.view.BaseStasticItemShower, com.huawei.ui.main.stories.history.view.InterfaceItemDataShower
    public String getUnit() {
        if (UnitUtil.h()) {
            if (this.mSportType == 262) {
                return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
            }
            return "/" + BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_sport_unit_mi);
        }
        if (this.mSportType == 262) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
        }
        return "/" + BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_sport_unit_km);
    }
}
