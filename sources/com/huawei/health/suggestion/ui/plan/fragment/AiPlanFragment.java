package com.huawei.health.suggestion.ui.plan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.PlanTabAdapter;
import com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ary;
import defpackage.gdw;
import defpackage.nrh;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class AiPlanFragment extends BaseFragment {
    public View c;
    private Context e;
    private PlanTabAdapter g;
    private HealthRecycleView h;
    private PlanTabDataModel j;
    private HealthButton l;
    private View m;
    private int i = 0;
    private boolean b = false;
    private Observer<gdw> d = new Observer() { // from class: com.huawei.health.suggestion.ui.plan.fragment.AiPlanFragment$$ExternalSyntheticLambda0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AiPlanFragment.this.a((gdw) obj);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private boolean f3261a = true;
    private OnFitnessStatusChangeCallback f = new OnFitnessStatusChangeCallback() { // from class: com.huawei.health.suggestion.ui.plan.fragment.AiPlanFragment.3
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public void onUpdate() {
            AiPlanFragment.this.f3261a = true;
        }
    };

    public static AiPlanFragment c(int i) {
        AiPlanFragment aiPlanFragment = new AiPlanFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("plantype", i);
        aiPlanFragment.setArguments(bundle);
        LogUtil.a("Suggestion_AiPlanFragment", "AiPlanFragment newInstance type = ", Integer.valueOf(i));
        return aiPlanFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.e = getActivity();
        if (arguments != null) {
            this.i = arguments.getInt("plantype");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Suggestion_AiPlanFragment", "Enter onCreateView()");
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_ai_paln, viewGroup, false);
        this.m = inflate;
        aHC_(inflate);
        e();
        b();
        BaseActivity.setViewSafeRegion(false, inflate);
        ary.a().e(this.f, "PLAN_UPDATE");
        return inflate;
    }

    private void aHC_(View view) {
        LogUtil.a("Suggestion_AiPlanFragment", "enter, initView");
        this.b = true;
        this.c = view.findViewById(R.id.loading_layout);
        this.h = (HealthRecycleView) view.findViewById(R.id.plan_recycle_view);
        this.g = new PlanTabAdapter(this.e);
        this.h.setLayoutManager(new HealthLinearLayoutManager(this.e));
        this.h.setAdapter(this.g);
        this.h.setNestedScrollingEnabled(false);
        a();
    }

    private void c() {
        if (this.b) {
            this.b = false;
            this.c.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(gdw gdwVar) {
        if (gdwVar == null) {
            LogUtil.h("Suggestion_AiPlanFragment", "notifyNewItemData itemData is null");
            return;
        }
        LogUtil.a("Suggestion_AiPlanFragment", "notifyNewItemData viewId: ", Integer.valueOf(gdwVar.c()), " viewType: ", Integer.valueOf(gdwVar.a()));
        PlanTabAdapter planTabAdapter = this.g;
        if (planTabAdapter != null) {
            planTabAdapter.b(gdwVar);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a("Suggestion_AiPlanFragment", "onResume");
        if (!CommonUtil.aa(this.e)) {
            nrh.b(this.e, R.string._2130839508_res_0x7f0207d4);
        } else {
            if (this.j == null || !this.f3261a) {
                return;
            }
            d();
        }
    }

    private void d() {
        LogUtil.a("Suggestion_AiPlanFragment", "refreshMyPlanData enter");
        this.j.e(this.i);
        if (!this.j.b(this.i)) {
            this.g.d(0);
        }
        if (this.j.d(this.i)) {
            this.j.a(this.i);
        } else {
            this.g.d(1);
        }
        this.f3261a = false;
    }

    private void a() {
        HealthButton healthButton = (HealthButton) this.m.findViewById(R.id.btn_set_network);
        this.l = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.AiPlanFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommonUtil.q(AiPlanFragment.this.e);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e() {
        PlanTabDataModel planTabDataModel = (PlanTabDataModel) new ViewModelProvider(this).get(PlanTabDataModel.class);
        this.j = planTabDataModel;
        planTabDataModel.d(this.d);
    }

    private void b() {
        LogUtil.a("Suggestion_AiPlanFragment", "initData");
        this.j.c(this.i);
        c();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        PlanTabDataModel planTabDataModel = this.j;
        if (planTabDataModel != null) {
            planTabDataModel.c(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.a("Suggestion_AiPlanFragment", "onDestroyView() enter!");
        if (this.g != null) {
            this.g = null;
            LogUtil.a("Suggestion_AiPlanFragment", "DestroyView unregisterCallback");
            ary.a().c(this.f, "PLAN_UPDATE");
        }
        this.f = null;
        super.onDestroyView();
    }
}
