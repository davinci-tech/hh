package com.huawei.health.suggestion.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.AiFitnessPlanCardFragment;
import com.huawei.health.suggestion.ui.plan.viewholder.IntCurrentPlanViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanListViewHolder;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.base.BaseFragment;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes8.dex */
public class AiFitnessPlanCardFragment extends BaseFragment {
    private LinearLayout b;
    private LinearLayout c;
    private boolean d = false;
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: fjr
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i, Object obj) {
            AiFitnessPlanCardFragment.this.a(i, obj);
        }
    };

    public /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("Suggestion_AiFitnessPlanCard", "user info update.", Boolean.valueOf(isResumed()), Boolean.valueOf(isVisible()));
        if (isVisible()) {
            e();
        }
    }

    public static AiFitnessPlanCardFragment a() {
        return new AiFitnessPlanCardFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            return null;
        }
        View inflate = View.inflate(BaseApplication.e(), R.layout.ai_fitness_plan_card, null);
        this.c = (LinearLayout) inflate.findViewById(R.id.intplan_recommend);
        this.b = (LinearLayout) inflate.findViewById(R.id.intplan_progress);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi != null) {
            userProfileMgrApi.registerModifyCallback(this.e);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a("Suggestion_AiFitnessPlanCard", "onResume.");
        super.onResume();
        this.d = false;
        e();
    }

    private void e() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_AiFitnessPlanCard", "getCurrentPlan intPlanApi is null.");
        } else {
            planApi.getCurrentIntPlan(new AnonymousClass4());
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.AiFitnessPlanCardFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends UiCallback<IntPlan> {
        AnonymousClass4() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_AiFitnessPlanCard", "getCurrentPlan onFailure");
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.AiFitnessPlanCardFragment.4.4
                @Override // java.lang.Runnable
                public void run() {
                    AiFitnessPlanCardFragment.this.c.setVisibility(8);
                    AiFitnessPlanCardFragment.this.b.setVisibility(8);
                }
            });
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final IntPlan intPlan) {
            if (AiFitnessPlanCardFragment.this.d) {
                return;
            }
            HandlerExecutor.e(new Runnable() { // from class: fjo
                @Override // java.lang.Runnable
                public final void run() {
                    AiFitnessPlanCardFragment.AnonymousClass4.this.e(intPlan);
                }
            });
        }

        public /* synthetic */ void e(IntPlan intPlan) {
            if (!AiFitnessPlanCardFragment.this.isResumed() || !AiFitnessPlanCardFragment.this.isVisible()) {
                LogUtil.h("Suggestion_AiFitnessPlanCard", "isResume:", Boolean.valueOf(AiFitnessPlanCardFragment.this.isResumed()), "isVisible:", Boolean.valueOf(AiFitnessPlanCardFragment.this.isVisible()));
                return;
            }
            if (intPlan != null && intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                LogUtil.a("Suggestion_AiFitnessPlanCard", "loadIntPlanProgressLayout.");
                AiFitnessPlanCardFragment.this.c.setVisibility(8);
                AiFitnessPlanCardFragment.this.b.setVisibility(0);
                AiFitnessPlanCardFragment aiFitnessPlanCardFragment = AiFitnessPlanCardFragment.this;
                aiFitnessPlanCardFragment.ayn_(aiFitnessPlanCardFragment.b, intPlan);
                return;
            }
            LogUtil.a("Suggestion_AiFitnessPlanCard", "loadIntPlanRecommendLayout.");
            AiFitnessPlanCardFragment.this.c.setVisibility(0);
            AiFitnessPlanCardFragment.this.b.setVisibility(8);
            AiFitnessPlanCardFragment aiFitnessPlanCardFragment2 = AiFitnessPlanCardFragment.this;
            aiFitnessPlanCardFragment2.ayo_(aiFitnessPlanCardFragment2.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ayo_(final View view) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_AiFitnessPlanCard", "loadIntPlanRecommendLayout intPlanApi is null.");
        } else {
            planApi.getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.health.suggestion.ui.AiFitnessPlanCardFragment.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_AiFitnessPlanCard", "loadRecommendPlanList onFailure.");
                    AiFitnessPlanCardFragment.this.c.setVisibility(8);
                    AiFitnessPlanCardFragment.this.b.setVisibility(8);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitnessPackageInfo> list) {
                    for (FitnessPackageInfo fitnessPackageInfo : list) {
                        if (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType() && !AiFitnessPlanCardFragment.this.d) {
                            new IntPlanListViewHolder(view, "weight").init(fitnessPackageInfo);
                            return;
                        }
                    }
                    AiFitnessPlanCardFragment.this.c.setVisibility(8);
                    AiFitnessPlanCardFragment.this.b.setVisibility(8);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ayn_(View view, IntPlan intPlan) {
        new IntCurrentPlanViewHolder(view, 1).init(intPlan);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.d = true;
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi != null) {
            userProfileMgrApi.unRegisterModifyCallback(this.e);
        }
    }
}
