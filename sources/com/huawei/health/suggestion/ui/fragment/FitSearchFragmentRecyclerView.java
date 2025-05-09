package com.huawei.health.suggestion.ui.fragment;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.adapter.FitSearchRecyAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.ggs;
import java.util.List;

/* loaded from: classes8.dex */
public class FitSearchFragmentRecyclerView extends Fragment {
    private LoadMoreListener b;
    private FitSearchRecyAdapter d;
    private HealthRecycleView e;

    public interface LoadMoreListener {
        void loadMore();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_fit_search_recycler, viewGroup, false);
        aFS_(inflate);
        c();
        return inflate;
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HealthRecycleView healthRecycleView = this.e;
        if (healthRecycleView == null || this.d == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.e.setLayoutManager(null);
        this.e.setAdapter(this.d);
        ggs.a(arx.b(), this.e);
        this.d.notifyDataSetChanged();
    }

    private void aFS_(View view) {
        this.e = (HealthRecycleView) view.findViewById(R.id.search_recyclerView);
    }

    private void c() {
        this.e.setItemAnimator(new DefaultItemAnimator());
        FitSearchRecyAdapter fitSearchRecyAdapter = new FitSearchRecyAdapter(getActivity());
        this.d = fitSearchRecyAdapter;
        this.e.setAdapter(fitSearchRecyAdapter);
        ggs.a(arx.b(), this.e);
        this.e.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ViewCompat.canScrollVertically(recyclerView, 1) || FitSearchFragmentRecyclerView.this.d.b() == 0 || FitSearchFragmentRecyclerView.this.b == null) {
                    return;
                }
                FitSearchFragmentRecyclerView.this.b.loadMore();
            }
        });
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void b(List<FitWorkout> list) {
        this.d.b(list);
    }

    public void d() {
        this.d.c();
    }

    public void b() {
        this.d.a();
    }

    public void e() {
        this.d.e();
    }

    public int a() {
        return this.d.b();
    }

    public void d(LoadMoreListener loadMoreListener) {
        this.b = loadMoreListener;
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
