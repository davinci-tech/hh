package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.TrackTriathlonDetailAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.hjw;
import defpackage.nsn;

/* loaded from: classes4.dex */
public class TriathlonSubDataFrag extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private hjw f3757a;
    private HealthRecycleView b;
    private TrackTriathlonDetailAdapter d;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Track_TriathlonSubDataFrag", "onCreateView--------");
        FragmentActivity activity = getActivity();
        if (activity == null || !(activity instanceof TrackDetailActivity)) {
            LogUtil.h("Track_TriathlonSubDataFrag", "trackDetailActivity == null");
            return null;
        }
        final TrackDetailActivity trackDetailActivity = (TrackDetailActivity) activity;
        hjw e = trackDetailActivity.e();
        this.f3757a = e;
        if (e == null) {
            LogUtil.h("Track_TriathlonSubDataFrag", "mTrackDetailDataManager == null");
            trackDetailActivity.finish();
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.track_sub_sport_detail, viewGroup, false);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.recyclerview_reco);
        this.b = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        TrackTriathlonDetailAdapter trackTriathlonDetailAdapter = new TrackTriathlonDetailAdapter(this.f3757a.o(), false) { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TriathlonSubDataFrag.5
            @Override // com.huawei.healthcloud.plugintrack.ui.adapter.TrackTriathlonDetailAdapter
            public boolean d() {
                return trackDetailActivity.d();
            }
        };
        this.d = trackTriathlonDetailAdapter;
        this.b.setAdapter(trackTriathlonDetailAdapter);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.c("Track_TriathlonSubDataFrag", "onConfigurationChanged");
        c();
    }

    private void c() {
        Context context = getContext();
        if (context == null) {
            LogUtil.h("Track_TriathlonSubDataFrag", "context == null");
            return;
        }
        for (int i = 0; i < this.b.getChildCount(); i++) {
            nsn.cLF_(context, true, true, this.b.getChildAt(i));
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
