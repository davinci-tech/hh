package com.huawei.health.suggestion.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.mny;

/* loaded from: classes4.dex */
public class RunPlanReportDisplayFragment extends Fragment {
    private HealthRecycleView d;
    private RunPlanReportAdapter e;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("RunPlanReportDisplayFragment", "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_run_plan_report_layout, viewGroup, false);
        ayF_(inflate);
        return inflate;
    }

    private void ayF_(View view) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.detail_report_display_rcy);
        this.d = healthRecycleView;
        healthRecycleView.setItemAnimator(null);
        this.d.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.d.setLayoutManager(linearLayoutManager);
    }

    public void e(UiCallback uiCallback) {
        RunPlanReportAdapter runPlanReportAdapter = this.e;
        if (runPlanReportAdapter != null) {
            runPlanReportAdapter.a(uiCallback);
        } else {
            LogUtil.b("RunPlanReportDisplayFragment", "mRunPlanReportAdapter is  null");
        }
    }

    public void b(mny mnyVar) {
        if (mnyVar == null) {
            LogUtil.b("RunPlanReportDisplayFragment", "trainReportBean is null");
            return;
        }
        int i = mnyVar.h() == null ? 1 : 0;
        if (isAdded()) {
            if (this.e == null) {
                RunPlanReportAdapter runPlanReportAdapter = new RunPlanReportAdapter(i, getContext());
                this.e = runPlanReportAdapter;
                this.d.setAdapter(runPlanReportAdapter);
            }
            this.e.a(mnyVar);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
