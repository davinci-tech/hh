package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ggs;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanFitnessViewHolder extends AbsFitnessViewHolder<List<FitnessPackageInfo>> {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f3297a;
    private HealthSubHeader b;
    private List<FitnessPackageInfo> c;
    private Context d;
    private PlanInfoAdapter e;
    private HealthRecycleView i;

    public PlanFitnessViewHolder(View view) {
        super(view);
        this.c = new ArrayList();
        this.d = view.getContext();
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.sug_plans_rcy);
        this.i = healthRecycleView;
        ggs.d(this.d, healthRecycleView, false);
        this.f3297a = (RelativeLayout) view.findViewById(R.id.sug_plans_layout);
        this.b = (HealthSubHeader) view.findViewById(R.id.plans_title);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void init(List<FitnessPackageInfo> list) {
        if (koq.c(list)) {
            this.b.setHeadTitleText(this.d.getResources().getString(R.string._2130848472_res_0x7f022ad8));
            this.b.setVisibility(0);
            this.f3297a.setVisibility(0);
            this.b.setMoreLayoutVisibility(8);
            for (FitnessPackageInfo fitnessPackageInfo : list) {
                if (fitnessPackageInfo != null && !this.c.contains(fitnessPackageInfo)) {
                    this.c.add(fitnessPackageInfo);
                }
            }
            PlanInfoAdapter planInfoAdapter = new PlanInfoAdapter(this.c, 3, this.d);
            this.e = planInfoAdapter;
            this.i.setAdapter(planInfoAdapter);
            return;
        }
        LogUtil.h("Suggestion_PlanFitnessViewHolder", "PlanFitnessViewHolder init FitnessMyPlanUseCase null");
        this.f3297a.setVisibility(8);
        this.b.setVisibility(8);
    }
}
