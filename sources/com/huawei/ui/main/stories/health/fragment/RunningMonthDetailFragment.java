package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.linechart.common.DataInfos;

/* loaded from: classes6.dex */
public class RunningMonthDetailFragment extends BaseRunningDetailFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    protected void updateGenderView(int i) {
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        isCurrentFragment(false);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    protected void initFragmentView() {
        this.mHealthLevelIndicator.setVisibility(8);
        initChartConfig();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    protected void updateBar() {
        this.mProgressBar.setVisibility(8);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    public DataInfos getDataInfo() {
        return DataInfos.RunningMonthDetail;
    }
}
