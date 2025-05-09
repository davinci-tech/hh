package com.huawei.ui.main.stories.history.adapter.monthdatashower.totalshower;

import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthStatisticViewType;
import defpackage.hln;
import defpackage.koq;
import defpackage.rcg;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

@MonthStatisticViewType(type = "TIME")
/* loaded from: classes9.dex */
public class TimesShower extends rcg {
    private static final String TAG = "Track_TimeShower";
    private static final int TIME_INDEX = 2;

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getMainMonthData() {
        int sportTypeId = this.mHwSportTypeInfo.getSportTypeId();
        if (sportTypeId == 286 || sportTypeId == 287) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.mHwSportTypeInfo);
            arrayList.add(hln.c(this.mContext).d(sportTypeId == 286 ? HeartRateThresholdConfig.HEART_RATE_LIMIT : 291));
            getGolfAndDivingMonthData(arrayList);
        } else {
            ArrayList<String> trackRequestDataBase = this.mHwSportTypeInfo.getHistoryList().getTrackRequestDataBase(this.mHwSportTypeInfo.getSportTypeId());
            if (trackRequestDataBase == null || trackRequestDataBase.size() < 3 || trackRequestDataBase.get(2) == null) {
                LogUtil.a(TAG, "getMainMonthData TimesShower");
                return "--";
            }
            if (this.mMonthData != null && this.mMonthData.containsKey(trackRequestDataBase.get(2))) {
                this.mMainData = this.mMonthData.get(trackRequestDataBase.get(2));
            } else {
                return UnitUtil.e(0.0d, 1, 0);
            }
        }
        return this.mMainData == null ? "--" : processDataToString(standardization(this.mMainData.doubleValue()));
    }

    private void getGolfAndDivingMonthData(List<HwSportTypeInfo> list) {
        if (koq.b(list)) {
            return;
        }
        Double valueOf = Double.valueOf(0.0d);
        for (HwSportTypeInfo hwSportTypeInfo : list) {
            ArrayList<String> trackRequestDataBase = hwSportTypeInfo.getHistoryList().getTrackRequestDataBase(hwSportTypeInfo.getSportTypeId());
            if (trackRequestDataBase != null && trackRequestDataBase.size() >= 3 && trackRequestDataBase.get(2) != null) {
                String str = trackRequestDataBase.get(2);
                if (this.mMonthData != null && this.mMonthData.containsKey(str)) {
                    valueOf = Double.valueOf(valueOf.doubleValue() + this.mMonthData.get(str).doubleValue());
                    this.mMainData = valueOf;
                }
            }
        }
    }

    @Override // defpackage.rcg, com.huawei.ui.main.stories.history.adapter.monthdatashower.MonthDataShower
    public String getUnit() {
        if (this.mContext == null) {
            LogUtil.b(TAG, "TimesShower  mContext is null");
            return "";
        }
        if (this.mHwSportTypeInfo != null && this.mHwSportTypeInfo.getSportTypeId() == 271) {
            return this.mContext.getResources().getString(R$string.IDS_aw_version2_basketball_unit);
        }
        return this.mContext.getResources().getString(R$string.IDS_hw_show_main_home_page_sport_frequency);
    }
}
