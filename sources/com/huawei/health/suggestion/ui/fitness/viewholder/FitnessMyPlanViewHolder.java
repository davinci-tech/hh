package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.model.fitness.FitnessMyPlanUseCase;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessMyPlanViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ixx;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessMyPlanViewHolder extends AbsFitnessViewHolder<FitnessMyPlanUseCase> {

    /* renamed from: a, reason: collision with root package name */
    private final HealthRecycleView f3213a;
    private List<FitnessPackageInfo> b;
    private final HealthSubHeader c;
    private RecyclerView.Adapter d;
    private List<IntPlan> e;
    private int g;
    private final HealthRecycleView h;
    private List<Plan> i;
    private RecyclerView.Adapter j;

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void init(FitnessMyPlanUseCase fitnessMyPlanUseCase) {
        if (fitnessMyPlanUseCase == null) {
            LogUtil.h("Suggestion_FitnessMyPlanViewHolder", "FitnessMyPlanViewHolder init FitnessMyPlanUseCase null");
            return;
        }
        d();
        a();
        a(fitnessMyPlanUseCase);
        b(fitnessMyPlanUseCase);
        d(fitnessMyPlanUseCase);
    }

    private void a(FitnessMyPlanUseCase fitnessMyPlanUseCase) {
        if (FitnessExternalUtils.a()) {
            if (fitnessMyPlanUseCase.getIntPlan() != null && (fitnessMyPlanUseCase.getIntPlan().getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || fitnessMyPlanUseCase.getIntPlan().getPlanType() == IntPlan.PlanType.FIT_PLAN)) {
                this.f3213a.setVisibility(0);
                this.h.setVisibility(8);
                return;
            } else {
                this.f3213a.setVisibility(8);
                this.h.setVisibility(0);
                return;
            }
        }
        if (koq.b(fitnessMyPlanUseCase.getPlanList())) {
            this.f3213a.setVisibility(8);
            this.h.setVisibility(0);
        } else {
            this.f3213a.setVisibility(0);
            this.h.setVisibility(8);
        }
    }

    private void a() {
        this.c.setMoreViewClickListener(new View.OnClickListener() { // from class: fsc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessMyPlanViewHolder.this.aFB_(view);
            }
        });
    }

    public /* synthetic */ void aFB_(View view) {
        if (isFastClick()) {
            LogUtil.h("Suggestion_FitnessMyPlanViewHolder", "FitnessMyPlanViewHolder is fast click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", this.itemView.getContext().getString(R.string._2130848472_res_0x7f022ad8));
        hashMap.put("position", 6);
        ixx.d().d(this.itemView.getContext(), "1130015", hashMap, 0);
        hashMap.clear();
        hashMap.put("click", 1);
        hashMap.put("type", 3);
        ixx.d().d(this.itemView.getContext(), AnalyticsValue.INT_PLAN_2030075.value(), hashMap, 0);
        Bundle bundle = new Bundle();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf(IntPlan.PlanType.AI_RUN_PLAN.getType()));
        bundle.putIntegerArrayList("FILTERED_PLAN_TYPES", arrayList);
        bundle.putString("CUSTOM_TITLE", this.itemView.getContext().getString(R.string._2130848472_res_0x7f022ad8));
        AppRouter.b("/PluginFitnessAdvice/PlanListActivity").zF_(bundle).c(view.getContext());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(FitnessMyPlanUseCase fitnessMyPlanUseCase) {
        if (fitnessMyPlanUseCase == null || (koq.b(fitnessMyPlanUseCase.getPlanList()) && fitnessMyPlanUseCase.getIntPlan() == null)) {
            LogUtil.h("Suggestion_FitnessMyPlanViewHolder", "bindMyPlanView FitnessMyPlanUseCase null");
            return;
        }
        if (koq.c(fitnessMyPlanUseCase.getPlanList())) {
            this.i.clear();
            this.i.add(fitnessMyPlanUseCase.getPlanList().get(0));
        }
        if (fitnessMyPlanUseCase.getIntPlan() != null) {
            this.e.clear();
            this.e.add(fitnessMyPlanUseCase.getIntPlan());
        }
        this.d.notifyDataSetChanged();
    }

    private void b(FitnessMyPlanUseCase fitnessMyPlanUseCase) {
        if (fitnessMyPlanUseCase == null || koq.b(fitnessMyPlanUseCase.getFitnessPackageInfoList())) {
            LogUtil.h("Suggestion_FitnessMyPlanViewHolder", "bindRecommendedPlanView FitnessMyPlanUseCase null");
            return;
        }
        List<FitnessPackageInfo> fitnessPackageInfoList = fitnessMyPlanUseCase.getFitnessPackageInfoList();
        if (koq.c(fitnessPackageInfoList)) {
            if (fitnessPackageInfoList.size() > this.g) {
                this.b.clear();
                this.b.addAll(fitnessPackageInfoList.subList(0, this.g));
            } else {
                this.b.clear();
                this.b.addAll(fitnessPackageInfoList);
            }
        }
        this.j.notifyDataSetChanged();
    }

    private void d() {
        setRecyclerViewLayout(this.itemView.getContext(), this.f3213a);
        this.f3213a.setAdapter(this.d);
        setRecyclerViewLayout(this.itemView.getContext(), this.h);
        this.h.setAdapter(this.j);
    }
}
