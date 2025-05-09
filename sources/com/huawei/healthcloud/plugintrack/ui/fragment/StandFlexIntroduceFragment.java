package com.huawei.healthcloud.plugintrack.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.StandingFlexionViewModel;
import defpackage.hji;

/* loaded from: classes4.dex */
public class StandFlexIntroduceFragment extends SportExamIntroduceFragment {
    private StandingFlexionViewModel c;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getTitle() {
        return getString(R.string._2130840168_res_0x7f020a68);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected void enableCanStart() {
        StandingFlexionViewModel standingFlexionViewModel = this.c;
        if (standingFlexionViewModel != null) {
            standingFlexionViewModel.enableCanStart(true);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFirstTips() {
        return getString(R.string._2130840169_res_0x7f020a69, 1, hji.d(getActivity(), 2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondTips() {
        return getString(R.string._2130840170_res_0x7f020a6a, 2);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        this.c = (StandingFlexionViewModel) new ViewModelProvider(requireActivity()).get(StandingFlexionViewModel.class);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondBitmapName() {
        return "pic_standflex_introduce_second";
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFistBitmapName() {
        return "pic_standflex_introduce_first";
    }
}
