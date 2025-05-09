package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.viewholder.PlanRunImproveViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.mns;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanClassifyAdapter extends RecyclerView.Adapter<PlanRunImproveViewHolder> {
    private List<mns> c;
    private Context e;

    public PlanClassifyAdapter(List<mns> list, Context context) {
        this.e = context;
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aGR_, reason: merged with bridge method [inline-methods] */
    public PlanRunImproveViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PlanRunImproveViewHolder(LayoutInflater.from(this.e).inflate(R.layout.sug_layout_plans, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(PlanRunImproveViewHolder planRunImproveViewHolder, int i) {
        if (koq.b(this.c, i)) {
            return;
        }
        mns mnsVar = this.c.get(i);
        LogUtil.a("Suggestion_PlanClassifyAdapter", "groupName = ", mnsVar.c());
        planRunImproveViewHolder.b(mnsVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }
}
