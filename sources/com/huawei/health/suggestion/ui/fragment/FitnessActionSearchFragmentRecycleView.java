package com.huawei.health.suggestion.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessActionTypeRecyAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.LoadMoreInterface;
import com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionSearchFragmentRecycleView extends Fragment {
    private HealthRecycleView b;
    private FitnessActionTypeRecyAdapter c;
    private FitSearchFragmentRecyclerView.LoadMoreListener d;

    public void a() {
    }

    public void d() {
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_fit_search_recycler, viewGroup, false);
        aFT_(inflate);
        b();
        return inflate;
    }

    private void aFT_(View view) {
        this.b = (HealthRecycleView) view.findViewById(R.id.search_recyclerView);
    }

    private void b() {
        FitnessActionTypeRecyAdapter fitnessActionTypeRecyAdapter = new FitnessActionTypeRecyAdapter(this.b, null);
        this.c = fitnessActionTypeRecyAdapter;
        fitnessActionTypeRecyAdapter.e((LoadMoreInterface) null);
        this.b.setAdapter(this.c);
        this.b.setLayoutManager(new LinearLayoutManager(arx.b()));
        this.b.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fragment.FitnessActionSearchFragmentRecycleView.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (!ViewCompat.canScrollVertically(recyclerView, 1) && FitnessActionSearchFragmentRecycleView.this.c.b() != 0) {
                    if (FitnessActionSearchFragmentRecycleView.this.d != null) {
                        FitnessActionSearchFragmentRecycleView.this.d.loadMore();
                        return;
                    }
                    return;
                }
                LogUtil.h("Suggestion_FitnessActionSearchFragmentRecycleView", "addOnScrollListener RecyclerView can not scroll");
            }
        });
    }

    public void e() {
        this.c.c();
    }

    public int c() {
        return this.c.b();
    }

    public void b(FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener) {
        this.d = loadMoreListener;
    }

    public void c(List<AtomicAction> list) {
        if (list != null) {
            this.c.b(list);
        } else {
            this.c.c();
        }
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
