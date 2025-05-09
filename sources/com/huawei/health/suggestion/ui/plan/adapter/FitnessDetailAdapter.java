package com.huawei.health.suggestion.ui.plan.adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.suggestion.ui.plan.viewholder.PlanDetailImgVh;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class FitnessDetailAdapter extends RecyclerView.Adapter<PlanDetailImgVh> {
    private List<String> d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aGO_, reason: merged with bridge method [inline-methods] */
    public PlanDetailImgVh onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PlanDetailImgVh(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(PlanDetailImgVh planDetailImgVh, int i) {
        if (koq.d(this.d, i)) {
            planDetailImgVh.b(this.d.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    public void b(List<String> list) {
        this.d = list;
        notifyDataSetChanged();
    }
}
