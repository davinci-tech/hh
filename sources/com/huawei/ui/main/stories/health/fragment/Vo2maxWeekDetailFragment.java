package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.linechart.common.DataInfos;

/* loaded from: classes6.dex */
public class Vo2maxWeekDetailFragment extends BaseVo2maxDetailFragment {
    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        isCurrentFragment(false);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    protected void initFragmentView() {
        this.mHealthLevelIndicator.setVisibility(8);
        initChartConfig();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    protected DataInfos getDataInfo() {
        return DataInfos.Vo2maxWeekDetail;
    }
}
