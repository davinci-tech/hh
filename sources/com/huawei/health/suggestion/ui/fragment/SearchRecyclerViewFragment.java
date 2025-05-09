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
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.ggs;

/* loaded from: classes8.dex */
public class SearchRecyclerViewFragment extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f3234a;
    private FitSearchRecyAdapter b;
    private LoadMoreListener e;

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
        aGf_(inflate);
        a();
        return inflate;
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HealthRecycleView healthRecycleView = this.f3234a;
        if (healthRecycleView == null || this.b == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.f3234a.setLayoutManager(null);
        this.f3234a.setAdapter(this.b);
        ggs.a(arx.b(), this.f3234a);
        this.b.notifyDataSetChanged();
    }

    private void aGf_(View view) {
        this.f3234a = (HealthRecycleView) view.findViewById(R.id.search_recyclerView);
    }

    private void a() {
        this.f3234a.setItemAnimator(new DefaultItemAnimator());
        FitSearchRecyAdapter fitSearchRecyAdapter = new FitSearchRecyAdapter(getActivity());
        this.b = fitSearchRecyAdapter;
        this.f3234a.setAdapter(fitSearchRecyAdapter);
        ggs.a(arx.b(), this.f3234a);
        this.f3234a.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fragment.SearchRecyclerViewFragment.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ViewCompat.canScrollVertically(recyclerView, 1) || SearchRecyclerViewFragment.this.b.b() == 0 || SearchRecyclerViewFragment.this.e == null) {
                    return;
                }
                SearchRecyclerViewFragment.this.e.loadMore();
            }
        });
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
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
