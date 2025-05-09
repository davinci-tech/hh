package com.huawei.health.suggestion.ui.run.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.plan.activity.AiPlanActivity;
import com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment;
import com.huawei.health.suggestion.ui.run.adapter.RunPlanningAdapter;
import com.huawei.health.suggestion.ui.run.listener.RunPlanRefreshCallback;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ary;
import defpackage.asc;
import defpackage.gge;
import defpackage.ggf;
import defpackage.ggs;
import defpackage.gpn;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class RunPlanFragment extends BaseFragment implements View.OnClickListener, RunPlanRefreshCallback {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f3325a;
    private HealthCardView b;
    private Context d;
    private Observer e;
    private PlanApi f;
    private OnFitnessStatusChangeCallback g;
    private HealthTextView h;
    private LinearLayout i;
    private HealthSubHeader j;
    private RunPlanningAdapter k;
    private HealthRecycleView m;
    private RelativeLayout n;
    private HealthTextView o;
    private List<Plan> l = new ArrayList();
    private boolean c = false;

    public static RunPlanFragment a() {
        return new RunPlanFragment();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.d = getActivity();
        super.onCreate(bundle);
        CommonUtil.aa(this.d);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_plan_recommend, viewGroup, false);
        aKn_(inflate);
        this.g = new OnFitnessStatusChangeCallback() { // from class: gco
            @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
            public final void onUpdate() {
                RunPlanFragment.this.c();
            }
        };
        ary.a().e(this.g, "PLAN_DELETE");
        BaseActivity.setViewSafeRegion(false, inflate);
        return inflate;
    }

    public /* synthetic */ void c() {
        asc.e().b(new a(this));
    }

    private void aKn_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_RunPlanFragment", "initLayout view is null.");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.plan_recommend_fragment_layout);
        this.i = linearLayout;
        linearLayout.setVisibility(8);
        this.j = (HealthSubHeader) view.findViewById(R.id.plan_title);
        aKo_(view);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.join_plan_layout);
        this.b = healthCardView;
        healthCardView.setOnClickListener(this);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.btn_join_training);
        this.f3325a = healthButton;
        healthButton.setOnClickListener(this);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.ai_run_plan_describe);
        this.h = healthTextView;
        healthTextView.setText(ggf.d());
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.time_good);
        this.o = healthTextView2;
        healthTextView2.setText(ggf.e());
        this.m = (HealthRecycleView) view.findViewById(R.id.my_plan_recycler_view);
        this.n = (RelativeLayout) view.findViewById(R.id.my_plan_ryt);
        e();
        ggs.d(this.d, this.m, false);
        Observer observer = new Observer() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (RunPlanFragment.this.o != null) {
                    LogUtil.a("Suggestion_RunPlanFragment", "notify mTimeGood label", str);
                    RunPlanFragment.this.o.setText(ggf.e());
                }
                if (RunPlanFragment.this.h != null) {
                    RunPlanFragment.this.h.setText(ggf.d());
                }
            }
        };
        this.e = observer;
        ObserverManagerUtil.d(observer, "RUN_PLAN_UPDATE_HELLO_TIME");
    }

    private void aKo_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_RunPlanFragment", "setPlanTitle view is null");
            return;
        }
        if (LanguageUtil.ba(this.d) || LanguageUtil.bj(this.d)) {
            this.j.setSubHeaderTitleScaleTextSize(0.75f);
        }
        this.i.setVisibility(8);
        this.j.setMoreTextVisibility(0);
        this.j.setSubHeaderBackgroundColor(ContextCompat.getColor(view.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.j.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (view2 == null) {
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                if (!nsn.o()) {
                    RunPlanFragment.this.b();
                    Intent intent = new Intent(RunPlanFragment.this.d, (Class<?>) AiPlanActivity.class);
                    intent.putExtra("plantype", 2);
                    RunPlanFragment.this.startActivity(intent);
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                LogUtil.b("Suggestion_RunPlanFragment", "mRunPlanTitle click too fast");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("enter", "1");
        gge.e("1120019", hashMap);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        e();
    }

    private void e() {
        d();
    }

    private void d() {
        ggs.b(this.d, this.m, false, false);
        RunPlanningAdapter runPlanningAdapter = new RunPlanningAdapter(this.d, this.l);
        this.k = runPlanningAdapter;
        runPlanningAdapter.b(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.d);
        linearLayoutManager.setOrientation(1);
        this.m.setLayoutManager(linearLayoutManager);
        this.m.setNestedScrollingEnabled(false);
        this.m.a(false);
        this.m.setAdapter(this.k);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        asc.e().b(new a(this));
        if (this.c) {
            g();
        }
    }

    private void g() {
        this.c = false;
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("Suggestion_RunPlanFragment", "refreshVipStateData VipApi is null");
        } else {
            vipApi.getVipInfo(new VipCallback() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment.4
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    if (obj == null) {
                        LogUtil.a("Suggestion_RunPlanFragment", "getVipInfo onSuccess result is null");
                        return;
                    }
                    UserMemberInfo userMemberInfo = obj instanceof UserMemberInfo ? (UserMemberInfo) obj : null;
                    if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1) {
                        LogUtil.h("Suggestion_RunPlanFragment", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                    } else {
                        if (gpn.d(userMemberInfo)) {
                            return;
                        }
                        if (RunPlanFragment.this.f != null) {
                            RunPlanFragment.this.f.setNeedUpdateCurrentPlan();
                        }
                        ary.a().e("PLAN_DELETE");
                    }
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_RunPlanFragment", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            ary.a().c(this.g, "PLAN_DELETE");
        }
        ObserverManagerUtil.e(this.e, "RUN_PLAN_UPDATE_HELLO_TIME");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.o()) {
            LogUtil.b("Suggestion_RunPlanFragment", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.btn_join_training || view.getId() == R.id.join_plan_layout) {
            b();
            Intent intent = new Intent(this.d, (Class<?>) AiPlanActivity.class);
            intent.putExtra("plantype", 2);
            startActivity(intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.run.listener.RunPlanRefreshCallback
    public void setRefreshState() {
        this.c = true;
    }

    static class a implements Runnable {
        WeakReference<RunPlanFragment> c;

        a(RunPlanFragment runPlanFragment) {
            this.c = new WeakReference<>(runPlanFragment);
        }

        @Override // java.lang.Runnable
        public void run() {
            RunPlanFragment runPlanFragment = this.c.get();
            if (runPlanFragment == null) {
                LogUtil.h("Suggestion_RunPlanFragment", "GetMyPlanInfoRunnable run fragment is null");
                return;
            }
            if (LoginInit.getInstance(BaseApplication.getContext()).getUsetId() != null) {
                runPlanFragment.f = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (runPlanFragment.f != null) {
                    runPlanFragment.f.b(new d(runPlanFragment));
                    return;
                } else {
                    LogUtil.h("Suggestion_RunPlanFragment", "RunPlanningRunnable run planApi is null.");
                    return;
                }
            }
            LogUtil.h("Suggestion_RunPlanFragment", "RunPlanningRunnable run loginInfo is null.");
        }
    }

    static class d extends UiCallback<IntPlan> {
        WeakReference<RunPlanFragment> b;
        RunPlanFragment d;

        d(RunPlanFragment runPlanFragment) {
            WeakReference<RunPlanFragment> weakReference = new WeakReference<>(runPlanFragment);
            this.b = weakReference;
            this.d = weakReference.get();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_RunPlanFragment", "CurrentPlanUiCallback errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
            RunPlanFragment runPlanFragment = this.d;
            if (runPlanFragment != null) {
                runPlanFragment.j();
            } else {
                LogUtil.b("Suggestion_RunPlanFragment", "mFragment is null.");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            if (this.d == null) {
                LogUtil.b("Suggestion_RunPlanFragment", "CurrentPlanUiCallback onSuccess mFragment is null.");
                return;
            }
            if (intPlan == null) {
                LogUtil.b("Suggestion_RunPlanFragment", "CurrentPlanUiCallback onSuccess data is null.");
                this.d.j();
                return;
            }
            LogUtil.a("Suggestion_RunPlanFragment", "plan id = ", intPlan.getPlanId());
            this.d.l.clear();
            if ((intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) && intPlan.getCompatiblePlan() != null) {
                this.d.l.add(intPlan.getCompatiblePlan());
            }
            if (!koq.b(this.d.l)) {
                this.d.h();
            } else {
                LogUtil.b("Suggestion_RunPlanFragment", "CurrentPlanUiCallback onSuccess mRunPlanning is null.");
                this.d.j();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (koq.b(this.l)) {
            LogUtil.b("Suggestion_RunPlanFragment", "showRunPlanning mRunPlanning is null.");
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.h("Suggestion_RunPlanFragment", "showRunPlanning activity is null.");
        } else if (this.k == null) {
            LogUtil.h("Suggestion_RunPlanFragment", "showRunPlanning mRunPlanningAdapter is null.");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    RunPlanFragment.this.k.c(RunPlanFragment.this.l);
                    RunPlanFragment.this.i.setVisibility(0);
                    RunPlanFragment.this.n.setVisibility(0);
                    RunPlanFragment.this.b.setVisibility(8);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    RunPlanFragment.this.o.setText(ggf.e());
                    RunPlanFragment.this.h.setText(ggf.d());
                    RunPlanFragment.this.i.setVisibility(0);
                    RunPlanFragment.this.b.setVisibility(0);
                    RunPlanFragment.this.n.setVisibility(8);
                }
            });
        }
    }
}
