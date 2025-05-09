package com.huawei.health.suggestion.ui.fitness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.LoadMoreInterface;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionTypeHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionTypeRecyAdapter extends RecyclerView.Adapter<FitnessActionTypeHolder> {
    private LoadMoreInterface c;
    private FitnessActionTypeHolder d;
    private List<AtomicAction> e = new ArrayList();
    private int b = 1;

    public FitnessActionTypeRecyAdapter(HealthRecycleView healthRecycleView, Context context) {
        if (healthRecycleView == null) {
            LogUtil.h("FitnessActionTypeRecyAdapter", "RecyclerView can not be null");
        } else {
            healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.FitnessActionTypeRecyAdapter.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (ViewCompat.canScrollVertically(recyclerView, FitnessActionTypeRecyAdapter.this.b) || FitnessActionTypeRecyAdapter.this.c == null) {
                        return;
                    }
                    FitnessActionTypeRecyAdapter.this.c.loadMore();
                }
            });
        }
    }

    public void b(List<AtomicAction> list) {
        if (koq.c(list)) {
            this.e.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void c() {
        this.e.clear();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aBm_, reason: merged with bridge method [inline-methods] */
    public FitnessActionTypeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        FitnessActionTypeHolder fitnessActionTypeHolder = new FitnessActionTypeHolder(LayoutInflater.from(arx.b()).inflate(R.layout.sug_item_fitness_action_type, viewGroup, false));
        this.d = fitnessActionTypeHolder;
        return fitnessActionTypeHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FitnessActionTypeHolder fitnessActionTypeHolder, int i) {
        if (i < 0 || i >= this.e.size()) {
            return;
        }
        fitnessActionTypeHolder.c(this.e.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public int b() {
        return this.e.size();
    }

    public void e(LoadMoreInterface loadMoreInterface) {
        this.c = loadMoreInterface;
    }
}
