package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ggs;
import defpackage.koq;
import defpackage.mmw;
import defpackage.mns;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanRunImproveViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private List<mmw> f3298a;
    private PlanInfoAdapter b;
    private RelativeLayout c;
    private HealthRecycleView d;
    private Context e;
    private HealthSubHeader i;

    public PlanRunImproveViewHolder(View view) {
        super(view);
        this.f3298a = new ArrayList();
        this.e = view.getContext();
        this.d = (HealthRecycleView) view.findViewById(R.id.sug_plans_rcy);
        this.c = (RelativeLayout) view.findViewById(R.id.sug_plans_layout);
        this.i = (HealthSubHeader) view.findViewById(R.id.plans_title);
        ggs.d(this.e, this.d, false);
    }

    public void b(mns mnsVar) {
        if (mnsVar != null) {
            LogUtil.a("Suggestion_PlanRunImproveViewHolder", "PlanRunImproveViewHolder item = ", mnsVar.c());
            this.i.setHeadTitleText(mnsVar.c());
            for (mmw mmwVar : mnsVar.b()) {
                if (mmwVar != null && !this.f3298a.contains(mmwVar)) {
                    if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() && (mmwVar.getCommodityFlag() == 2 || mmwVar.getCommodityFlag() == 1)) {
                        LogUtil.h("Suggestion_PlanRunImproveViewHolder", "initData isKidAccount getCommodityFlag:", Integer.valueOf(mmwVar.getCommodityFlag()));
                    } else {
                        this.f3298a.add(mmwVar);
                    }
                }
            }
            if (koq.b(this.f3298a)) {
                this.c.setVisibility(8);
                this.i.setVisibility(8);
                return;
            }
            PlanInfoAdapter planInfoAdapter = new PlanInfoAdapter(0, this.f3298a, this.e);
            this.b = planInfoAdapter;
            this.d.setAdapter(planInfoAdapter);
            this.i.setVisibility(0);
            this.c.setVisibility(0);
            this.i.setMoreLayoutVisibility(8);
            return;
        }
        LogUtil.h("Suggestion_PlanRunImproveViewHolder", "PlanRunImproveViewHolder init FitnessMyPlanUseCase null");
        this.c.setVisibility(8);
        this.i.setVisibility(8);
    }
}
