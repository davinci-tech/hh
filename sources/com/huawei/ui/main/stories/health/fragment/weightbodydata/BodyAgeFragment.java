package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.text.SpannableString;
import android.view.View;
import com.huawei.health.R;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.qrd;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class BodyAgeFragment extends WeightBodyDataFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 9;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initView(View view) {
        super.initView(view);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        int b = (int) this.mWeightBean.b();
        String e = UnitUtil.e(b, 1, 0);
        updateTitleTextStyle(e, new SpannableString(this.mResources.getQuantityString(R.plurals._2130903043_res_0x7f030003, b, e)), R$string.IDS_hw_show_bodyage);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initAbout(qrd.e(0), qrd.e(1));
    }
}
