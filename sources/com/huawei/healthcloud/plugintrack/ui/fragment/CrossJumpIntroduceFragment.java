package com.huawei.healthcloud.plugintrack.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.CrossJumpViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;
import defpackage.hji;

/* loaded from: classes4.dex */
public class CrossJumpIntroduceFragment extends SportExamIntroduceFragment {

    /* renamed from: a, reason: collision with root package name */
    private SportExamViewModel f3714a;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getTitle() {
        return getString(R.string._2130840210_res_0x7f020a92);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected void enableCanStart() {
        SportExamViewModel sportExamViewModel = this.f3714a;
        if (sportExamViewModel != null) {
            sportExamViewModel.enableCanStart(true);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFirstTips() {
        return getString(R.string._2130840208_res_0x7f020a90, 1, hji.d(getActivity(), 2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondTips() {
        return getString(R.string._2130840209_res_0x7f020a91, 2);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        this.f3714a = (SportExamViewModel) new ViewModelProvider(requireActivity()).get(CrossJumpViewModel.class);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondBitmapName() {
        return "public_cross_jump_second";
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFistBitmapName() {
        return "public_cross_jump_first";
    }
}
