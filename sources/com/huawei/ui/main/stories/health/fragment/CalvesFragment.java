package com.huawei.ui.main.stories.health.fragment;

import com.huawei.ui.main.R$string;
import defpackage.qku;

/* loaded from: classes8.dex */
public class CalvesFragment extends AiBodyDimensionFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected int getBiType() {
        return 34;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    double getValueFrom(qku qkuVar) {
        return qkuVar.b();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.AiBodyDimensionFragment
    int getTitleId() {
        return R$string.IDS_aibs_calves_girth;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getBodyDataKey() {
        return "calves";
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    protected String getAIBodyShapeImageKey() {
        return "calves_girth";
    }
}
