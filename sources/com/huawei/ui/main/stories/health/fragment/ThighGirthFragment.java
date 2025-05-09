package com.huawei.ui.main.stories.health.fragment;

import com.huawei.ui.main.R$string;
import defpackage.qku;

/* loaded from: classes8.dex */
public class ThighGirthFragment extends AiBodyDimensionFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected int getBiType() {
        return 33;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    double getValueFrom(qku qkuVar) {
        return qkuVar.j();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    int getTitleId() {
        return R$string.IDS_aibs_thigh_girth;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getBodyDataKey() {
        return "thighGirth";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getAIBodyShapeImageKey() {
        return "thigh_girth";
    }
}
