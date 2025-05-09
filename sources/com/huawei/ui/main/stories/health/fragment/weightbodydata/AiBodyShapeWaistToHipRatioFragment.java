package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.ui.main.R$string;
import defpackage.doj;
import defpackage.qsj;

/* loaded from: classes8.dex */
public class AiBodyShapeWaistToHipRatioFragment extends AbstractWaistToHipRatioFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 28;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        this.mGender = MultiUsersManager.INSTANCE.getMainUser().c();
        this.b = this.mAiBodyShapeBean.h();
        this.f10191a = this.mResources.getString(R$string.IDS_weight_waist_to_hip_ratio);
        this.c = doj.b(this.mGender, this.b);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.weightbodydata.AbstractWaistToHipRatioFragment, com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public Boolean isShowHistoricalData() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public long getTrendEndTime() {
        return this.mAiBodyShapeBean.g();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public HiAggregateOption getHiAggregateOption() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(getTrendStartTime(), getTrendEndTime());
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setSortOrder(0);
        qsj.b(hiAggregateOption);
        return hiAggregateOption;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getBodyDataKey() {
        return "waistHipRatio";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public String getAIBodyShapeImageKey() {
        return "waist_hip_ratio";
    }
}
