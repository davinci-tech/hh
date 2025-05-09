package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import defpackage.qkk;
import java.util.List;

/* loaded from: classes6.dex */
public class StateMonthDetailFragment extends StateChartDetailFragment {
    private boolean e;

    @Override // com.huawei.ui.main.stories.health.fragment.StateChartDetailFragment, com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.StateChartDetailFragment, com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected DataInfos getDataInfo() {
        return DataInfos.StateIndexMonthDetail;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.StateChartDetailFragment
    public void updateIndexData(List<qkk> list) {
        refreshRecyclerViewData(list, this.e);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.e = z;
        refreshRecyclerViewData(this.mRecyclerDataMap.get("key_month"), z);
    }
}
