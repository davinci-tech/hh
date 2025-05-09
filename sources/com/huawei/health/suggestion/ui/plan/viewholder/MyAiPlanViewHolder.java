package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.view.View;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.run.adapter.MyPlanInfoAdapter;
import com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ggs;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class MyAiPlanViewHolder extends AbsFitnessViewHolder<Plan> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3293a;
    private HealthSubHeader b;
    private List<Plan> c;
    private MyPlanInfoAdapter d;
    private HealthRecycleView e;
    private RunPlanningAdapter h;

    public MyAiPlanViewHolder(View view) {
        super(view);
        this.c = new ArrayList();
        this.f3293a = view.getContext();
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.sug_plans_rcy);
        this.e = healthRecycleView;
        ggs.d(this.f3293a, healthRecycleView, false);
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.plans_title);
        this.b = healthSubHeader;
        healthSubHeader.setMoreLayoutVisibility(8);
        this.b.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void init(Plan plan) {
        if (plan == null) {
            LogUtil.h("Suggestion_MyAiPlanViewHolder", "FitnessMyPlanViewHolder data is null");
            return;
        }
        this.c.clear();
        this.c.add(plan);
        if (koq.c(this.c) && this.c.get(0).acquireType() == 0) {
            RunPlanningAdapter runPlanningAdapter = new RunPlanningAdapter(this.f3293a, this.c);
            this.h = runPlanningAdapter;
            this.e.setAdapter(runPlanningAdapter);
        } else {
            MyPlanInfoAdapter myPlanInfoAdapter = new MyPlanInfoAdapter(this.c, this.f3293a);
            this.d = myPlanInfoAdapter;
            this.e.setAdapter(myPlanInfoAdapter);
        }
    }
}
