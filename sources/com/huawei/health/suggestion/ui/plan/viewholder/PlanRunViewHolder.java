package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.adapter.PlanClassifyAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import defpackage.mns;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanRunViewHolder extends AbsFitnessViewHolder<List<mns>> {
    private HealthRecycleView b;
    private Context c;
    private PlanClassifyAdapter d;
    private List<mns> e;

    public PlanRunViewHolder(View view) {
        super(view);
        this.e = new ArrayList();
        this.c = view.getContext();
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.my_recommend_fitness_view);
        this.b = healthRecycleView;
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.c));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void init(List<mns> list) {
        if (koq.c(list)) {
            LogUtil.a("Suggestion_PlanRunViewHolder", "PlanRunViewHolder data：", Integer.valueOf(list.size()));
            for (mns mnsVar : list) {
                if (mnsVar != null && !this.e.contains(mnsVar)) {
                    this.e.add(mnsVar);
                }
            }
            Collections.sort(this.e, new Comparator<mns>() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.PlanRunViewHolder.1
                @Override // java.util.Comparator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public int compare(mns mnsVar2, mns mnsVar3) {
                    return Integer.compare(mnsVar3.d(), mnsVar2.d());
                }
            });
            PlanClassifyAdapter planClassifyAdapter = new PlanClassifyAdapter(this.e, this.c);
            this.d = planClassifyAdapter;
            this.b.setAdapter(planClassifyAdapter);
            this.b.setVisibility(0);
            return;
        }
        LogUtil.h("Suggestion_PlanRunViewHolder", "PlanRunViewHolder init RunPlanInfo null");
        this.b.setVisibility(8);
    }
}
