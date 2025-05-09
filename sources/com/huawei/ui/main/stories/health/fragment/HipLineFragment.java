package com.huawei.ui.main.stories.health.fragment;

import com.huawei.ui.main.R$string;
import defpackage.qku;

/* loaded from: classes8.dex */
public class HipLineFragment extends AiBodyDimensionFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected int getBiType() {
        return 31;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    double getValueFrom(qku qkuVar) {
        return qkuVar.d();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    int getTitleId() {
        return R$string.IDS_aibs_arm_circumference;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getBodyDataKey() {
        return "hipline";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getAIBodyShapeImageKey() {
        return "hip_line";
    }
}
