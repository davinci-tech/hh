package com.huawei.health.suggestion.ui.fitness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.LoadMoreInterface;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.fqa;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionRecyAdapter extends RecyclerView.Adapter<FitnessActionViewHolder> {
    private LoadMoreInterface d;
    private FitnessActionViewHolder e;
    private List<fqa> b = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private int f3149a = 1;

    public FitnessActionRecyAdapter(HealthRecycleView healthRecycleView, Context context) {
        if (healthRecycleView == null) {
            LogUtil.h("FitnessActionRecyAdapter", "RecyclerView can not be null");
        } else {
            healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.FitnessActionRecyAdapter.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (ViewCompat.canScrollVertically(recyclerView, FitnessActionRecyAdapter.this.f3149a) || FitnessActionRecyAdapter.this.d == null) {
                        return;
                    }
                    FitnessActionRecyAdapter.this.d.loadMore();
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aBl_, reason: merged with bridge method [inline-methods] */
    public FitnessActionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (nsn.s()) {
            this.e = new FitnessActionViewHolder(LayoutInflater.from(arx.b()).inflate(R.layout.sug_item_fitness_action_library_three_fold_fonts, viewGroup, false));
        } else {
            this.e = new FitnessActionViewHolder(LayoutInflater.from(arx.b()).inflate(R.layout.sug_item_fitness_action_library, viewGroup, false));
        }
        return this.e;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FitnessActionViewHolder fitnessActionViewHolder, int i) {
        fitnessActionViewHolder.b(this.b, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size() / 2;
    }

    public void e(List<fqa> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.b.clear();
        this.b.addAll(list);
        notifyDataSetChanged();
    }
}
