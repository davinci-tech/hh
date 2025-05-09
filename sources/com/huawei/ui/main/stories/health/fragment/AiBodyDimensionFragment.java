package com.huawei.ui.main.stories.health.fragment;

import android.text.SpannableString;
import android.util.Pair;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.koq;
import defpackage.qku;
import defpackage.qsj;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class AiBodyDimensionFragment extends WeightBodyDataFragment {
    private static final String TAG = "HealthWeight_AiBodyDimensionFragment";

    abstract int getTitleId();

    abstract double getValueFrom(qku qkuVar);

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected void initData() {
        if (this.mAiBodyShapeBean == null) {
            return;
        }
        double valueFrom = getValueFrom(this.mAiBodyShapeBean);
        String e = UnitUtil.e(UnitUtil.h() ? UnitUtil.e(valueFrom / 10.0d, 0) : valueFrom / 10.0d, 1, 1);
        SpannableString spannableString = new SpannableString(e);
        if (UnitUtil.h()) {
            this.mDataUnit.setText(R$string.IDS_ins);
        } else {
            this.mDataUnit.setText(R$string.IDS_cm);
        }
        updateTitleTextStyle(e, spannableString, getTitleId());
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected long getTrendEndTime() {
        return this.mAiBodyShapeBean.g();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected List<Pair<Long, Float>> buildTrendData(List<HiHealthData> list) {
        double d;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        boolean h = UnitUtil.h();
        for (HiHealthData hiHealthData : list) {
            if (h) {
                d = UnitUtil.e(hiHealthData.getDouble(getBodyDataKey()) / 10.0d, 0);
            } else {
                d = hiHealthData.getDouble(getBodyDataKey()) / 10.0d;
            }
            arrayList.add(new Pair(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf((float) d)));
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected HiAggregateOption getHiAggregateOption() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(getTrendStartTime(), getTrendEndTime());
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setSortOrder(0);
        qsj.b(hiAggregateOption);
        LogUtil.a(TAG, "getChartData() aggregateOption = ", hiAggregateOption.toString());
        return hiAggregateOption;
    }
}
