package com.huawei.healthcloud.plugintrack.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SupineLegRaiseModel;
import defpackage.hji;

/* loaded from: classes4.dex */
public class SupineLegIntroduceFragment extends SportExamIntroduceFragment {
    private SupineLegRaiseModel e;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    public String getTitle() {
        return getString(R.string._2130840175_res_0x7f020a6f);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected void enableCanStart() {
        this.e.enableCanStart(true);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFirstTips() {
        return getString(R.string._2130840176_res_0x7f020a70, 1, hji.d(getActivity(), 2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondTips() {
        return getString(R.string._2130840177_res_0x7f020a71, 2);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        this.e = (SupineLegRaiseModel) new ViewModelProvider(requireActivity()).get(SupineLegRaiseModel.class);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondBitmapName() {
        return "pic_supineleg_introduce_second";
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFistBitmapName() {
        return "pic_supineleg_introduce_first";
    }
}
