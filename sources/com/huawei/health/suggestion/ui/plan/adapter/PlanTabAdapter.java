package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.MyAiPlanViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.PlanFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.PlanRunViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.PlanStatisticViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gdw;

/* loaded from: classes4.dex */
public class PlanTabAdapter extends RecyclerView.Adapter<AbsFitnessViewHolder> {
    private LayoutInflater c;
    private Context d;
    private SparseArray<gdw> e = new SparseArray<>();

    public PlanTabAdapter(Context context) {
        this.d = context;
        this.c = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aGS_, reason: merged with bridge method [inline-methods] */
    public AbsFitnessViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("Suggestion_PlanTabAdapter", "onCreateViewHolder viewType: ", Integer.valueOf(i));
        if (i == 0) {
            return new MyAiPlanViewHolder(this.c.inflate(R.layout.sug_layout_plans, viewGroup, false));
        }
        if (i == 1) {
            AbsFitnessViewHolder e = e();
            return e != null ? e : new PlanStatisticViewHolder(this.c.inflate(R.layout.sug_fragment_ai_plan_statistic_layout, viewGroup, false));
        }
        if (i == 2) {
            return new PlanRunViewHolder(this.c.inflate(R.layout.sug_fragment_topic_list, viewGroup, false));
        }
        if (i == 3) {
            return new PlanFitnessViewHolder(this.c.inflate(R.layout.sug_layout_plans, viewGroup, false));
        }
        return new PlanRunViewHolder(this.c.inflate(R.layout.sug_fragment_topic_list, viewGroup, false));
    }

    private AbsFitnessViewHolder e() {
        SparseArray<gdw> clone = this.e.clone();
        if (clone == null || clone.size() <= 0) {
            LogUtil.b("Suggestion_PlanTabAdapter", "DATA ARRAY ISEMPTY");
            return new c(new View(this.d));
        }
        for (int i = 0; i < clone.size(); i++) {
            gdw gdwVar = clone.get(clone.keyAt(i));
            if (gdwVar == null || gdwVar.b() == null) {
                LogUtil.b("Suggestion_PlanTabAdapter", "onBindViewHolder recycleItemData is null");
            } else {
                AbsFitnessViewHolder a2 = a(gdwVar);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        return null;
    }

    private AbsFitnessViewHolder a(gdw gdwVar) {
        if (1 != gdwVar.a()) {
            return null;
        }
        if (gdwVar.b() instanceof PlanStatistics) {
            PlanStatistics planStatistics = (PlanStatistics) gdwVar.b();
            if (planStatistics == null) {
                LogUtil.b("Suggestion_PlanTabAdapter", "getAbsFitnessViewHolder itemData is null");
                return new c(new View(this.d));
            }
            if (planStatistics.acquireTotalPlan() == 0) {
                return new c(new View(this.d));
            }
            return null;
        }
        LogUtil.b("Suggestion_PlanTabAdapter", "itemData.getItemData() =", gdwVar.b());
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(AbsFitnessViewHolder absFitnessViewHolder, int i) {
        LogUtil.a("Suggestion_PlanTabAdapter", "onBindViewHolder position: ", Integer.valueOf(i));
        if (this.e.size() == 0) {
            LogUtil.h("Suggestion_PlanTabAdapter", "onBindViewHolder sparseArray size is 0");
            return;
        }
        LogUtil.a("Suggestion_PlanTabAdapter", "onBindViewHolder mDataArray value: ", this.e);
        gdw valueAt = this.e.valueAt(i);
        if (valueAt == null) {
            LogUtil.h("Suggestion_PlanTabAdapter", "onBindViewHolder itemData is null");
            return;
        }
        LogUtil.a("Suggestion_PlanTabAdapter", "onBindViewHolder itemData value: ", valueAt);
        if (valueAt instanceof gdw) {
            absFitnessViewHolder.init(valueAt.b());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.e.valueAt(i).a();
    }

    public void d(int i) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            gdw valueAt = this.e.valueAt(i2);
            if (valueAt != null && valueAt.a() == i) {
                valueAt.b(null);
                notifyItemChanged(i2);
            }
        }
    }

    public void b(gdw gdwVar) {
        LogUtil.a("Suggestion_PlanTabAdapter", "notifyNewItem itemData enter");
        if (gdwVar == null) {
            LogUtil.h("Suggestion_PlanTabAdapter", "notifyNewItem itemData is NULL");
            return;
        }
        int c2 = gdwVar.c();
        LogUtil.a("Suggestion_PlanTabAdapter", "notifyNewItem viewId =", Integer.valueOf(c2));
        if (c2 == -1) {
            LogUtil.a("Suggestion_PlanTabAdapter", "notifyNewItem Valid value itemData=", Integer.valueOf(gdwVar.a()));
            c(gdwVar);
            return;
        }
        int indexOfKey = this.e.indexOfKey(c2);
        if (indexOfKey < 0) {
            LogUtil.a("Suggestion_PlanTabAdapter", "notifyNewItem mDataArray size =", Integer.valueOf(this.e.size()), "index = ", Integer.valueOf(indexOfKey));
            d(gdwVar);
        } else {
            LogUtil.a("Suggestion_PlanTabAdapter", "notifyNewItem Valid value index > 0 getViewType=", Integer.valueOf(gdwVar.a()));
            c(gdwVar, indexOfKey);
        }
    }

    private void d(gdw gdwVar) {
        int c2 = gdwVar.c();
        this.e.append(c2, gdwVar);
        int indexOfKey = this.e.indexOfKey(c2);
        LogUtil.a("Suggestion_PlanTabAdapter", "addNewItem at position: ", Integer.valueOf(indexOfKey), " viewId: ", Integer.valueOf(c2));
        notifyItemInserted(indexOfKey);
    }

    private void c(gdw gdwVar, int i) {
        if (gdwVar.b() == null || i < 0) {
            LogUtil.h("Suggestion_PlanTabAdapter", "updateSingleItem itemData is NULL or data is NULL");
            return;
        }
        this.e.valueAt(i).b(gdwVar.b());
        LogUtil.a("Suggestion_PlanTabAdapter", "updateSingleItem at position: ", Integer.valueOf(i), " viewId: ", Integer.valueOf(gdwVar.c()));
        notifyItemChanged(i);
    }

    private void c(gdw gdwVar) {
        for (int i = 0; i < this.e.size(); i++) {
            gdw valueAt = this.e.valueAt(i);
            int a2 = valueAt.a();
            if (a2 == gdwVar.a()) {
                valueAt.b(gdwVar.b());
                LogUtil.a("Suggestion_PlanTabAdapter", "updateItemByViewType at position: ", Integer.valueOf(i), " viewType: ", Integer.valueOf(a2));
                notifyItemChanged(i);
            }
        }
    }

    static class c extends AbsFitnessViewHolder<PlanStatistics> {
        @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void init(PlanStatistics planStatistics) {
        }

        public c(View view) {
            super(view);
        }
    }
}
