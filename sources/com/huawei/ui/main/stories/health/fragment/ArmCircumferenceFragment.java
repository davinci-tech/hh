package com.huawei.ui.main.stories.health.fragment;

import com.huawei.ui.main.R$string;
import defpackage.qku;

/* loaded from: classes8.dex */
public class ArmCircumferenceFragment extends AiBodyDimensionFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected int getBiType() {
        return 32;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    double getValueFrom(qku qkuVar) {
        return qkuVar.a();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    int getTitleId() {
        return R$string.IDS_aibs_large_arm;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getBodyDataKey() {
        return "armCircumference";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getAIBodyShapeImageKey() {
        return "large_arm";
    }
}
