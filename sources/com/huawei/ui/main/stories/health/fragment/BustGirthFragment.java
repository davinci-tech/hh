package com.huawei.ui.main.stories.health.fragment;

import com.huawei.ui.main.R$string;
import defpackage.qku;

/* loaded from: classes8.dex */
public class BustGirthFragment extends AiBodyDimensionFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected int getBiType() {
        return 29;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    double getValueFrom(qku qkuVar) {
        return qkuVar.e();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    int getTitleId() {
        return R$string.IDS_aibs_bust_girth;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getBodyDataKey() {
        return "bustGirth";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getAIBodyShapeImageKey() {
        return "bust_girth";
    }
}
