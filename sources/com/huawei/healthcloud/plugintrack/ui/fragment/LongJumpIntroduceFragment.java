package com.huawei.healthcloud.plugintrack.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.LongJumpViewModel;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.SportExamViewModel;

/* loaded from: classes4.dex */
public class LongJumpIntroduceFragment extends SportExamIntroduceFragment {
    private SportExamViewModel d;

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getTitle() {
        return getString(R.string._2130840199_res_0x7f020a87);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected void enableCanStart() {
        SportExamViewModel sportExamViewModel = this.d;
        if (sportExamViewModel != null) {
            sportExamViewModel.enableCanStart(true);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFirstTips() {
        return getString(R.string._2130840200_res_0x7f020a88, 1);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondTips() {
        return getString(R.string._2130840201_res_0x7f020a89, 2);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment, com.huawei.healthcloud.plugintrack.ui.fragment.BaseSportingFragment
    public void initViewModel() {
        super.initViewModel();
        this.d = (SportExamViewModel) new ViewModelProvider(requireActivity()).get(LongJumpViewModel.class);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getSecondBitmapName() {
        return "public_jump_second";
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportExamIntroduceFragment
    protected String getFistBitmapName() {
        return "public_jump_first";
    }
}
