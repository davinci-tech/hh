package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.os.Message;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider;
import com.huawei.health.suggestion.util.ClickEventUtils;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ase;
import defpackage.fib;
import defpackage.fys;
import defpackage.fyw;
import defpackage.gds;
import defpackage.ggu;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mod;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class AiWorkoutPlanProviderChinaImpl extends FitnessPlanProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return (!FitnessExternalUtils.d() || CommonUtil.bu() || SportSupportUtil.f()) ? false : true;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void updateMyPlans(IntPlan intPlan) {
        ArrayList arrayList = new ArrayList(1);
        if (intPlan != null && (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || ase.f(intPlan))) {
            Message obtainMessage = this.mHandler.obtainMessage(0);
            arrayList.add(intPlan);
            obtainMessage.obj = arrayList;
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        getRecommendedPlanList();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void getRecommendedPlanList() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h(getLogTag(), "refreshFitnessPlan, getRecommendPlans : planApi is null.");
        } else {
            planApi.getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiWorkoutPlanProviderChinaImpl.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h(AiWorkoutPlanProviderChinaImpl.this.getLogTag(), "onFailure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitnessPackageInfo> list) {
                    LogUtil.a(AiWorkoutPlanProviderChinaImpl.this.getLogTag(), "onSuccess invoke");
                    Message obtainMessage = AiWorkoutPlanProviderChinaImpl.this.mHandler.obtainMessage(1);
                    ArrayList arrayList = new ArrayList();
                    for (FitnessPackageInfo fitnessPackageInfo : list) {
                        if (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType() || ase.d(fitnessPackageInfo)) {
                            arrayList.add(fitnessPackageInfo);
                        }
                    }
                    obtainMessage.obj = arrayList;
                    AiWorkoutPlanProviderChinaImpl.this.mHandler.sendMessage(obtainMessage);
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void onClickTitleEvent(Context context) {
        HashMap hashMap = new HashMap(2);
        if (FitnessExternalUtils.d()) {
            hashMap.clear();
            hashMap.put("click", 1);
            hashMap.put("type", 3);
            ixx.d().d(this.mContext, AnalyticsValue.INT_PLAN_2030075.value(), hashMap, 0);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("target", Integer.toString(IntPlan.PlanType.FIT_PLAN.getType()));
            JumpUtil.b(context, hashMap2);
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void setMyPlanClickListener(final Context context, final List<IntPlan> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiWorkoutPlanProviderChinaImpl.2
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("MORE_CLICK_EVENT".equals(str)) {
                    AiWorkoutPlanProviderChinaImpl.this.onClickTitleEvent(context);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Suggestion_CommonWorkoutPlanProvider", "position is out of bounds");
                    return;
                }
                IntPlan intPlan = (IntPlan) list.get(i);
                if (intPlan == null) {
                    LogUtil.b("Suggestion_CommonWorkoutPlanProvider", "workoutPlanInfo is null");
                } else if (fib.e().c()) {
                    JumpUtil.c(AiWorkoutPlanProviderChinaImpl.this.mContext);
                    ase.d(intPlan, "3", intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) ? new int[]{3, 1, 1} : null);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                AiWorkoutPlanProviderChinaImpl.this.d((List<IntPlan>) list, i, str);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130848472_res_0x7f022ad8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<IntPlan> list, int i, String str) {
        IntAction intAction;
        int i2;
        if (koq.b(list, i)) {
            return;
        }
        IntPlan intPlan = list.get(i);
        List<IntAction> b = fys.b(intPlan);
        int i3 = 0;
        if (b.size() > 0) {
            Iterator<IntAction> it = b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    intAction = null;
                    break;
                }
                intAction = it.next();
                i3++;
                if (intAction.getPunchFlag() == 0) {
                    break;
                }
            }
            int i4 = i3;
            i3 = 1;
            i2 = i4;
        } else {
            intAction = null;
            i2 = 0;
        }
        if (fyw.l(intPlan) && str.equals("BUTTON_CLICK_EVENT")) {
            if (this.mIsJump2VipView) {
                ClickEventUtils.e(ClickEventUtils.ClickEventType.JUMP_VIP, null);
                return;
            }
            if (i3 != 0) {
                if (intAction != null) {
                    d(i2, intAction, intPlan);
                }
            } else {
                if (this.hasWeight || !intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                    return;
                }
                ggu.d(this.mContext, this.mLastWeight, this.mLastBodyFat);
            }
        }
    }

    private void d(int i, IntAction intAction, IntPlan intPlan) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.getCourseById(intAction.getActionId(), null, null, new e(intPlan, i, this.mContext));
        }
    }

    static class e extends UiCallback<Workout> {
        private final int b;
        private final IntPlan d;
        private final WeakReference<Context> e;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        public e(IntPlan intPlan, int i, Context context) {
            this.d = intPlan;
            this.b = i;
            this.e = new WeakReference<>(context);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            Context context = this.e.get();
            if (context == null) {
                LogUtil.h("Suggestion_CommonWorkoutPlanProvider", "WorkoutUiCallback context null");
                return;
            }
            FitWorkout a2 = mod.a(workout);
            if (a2 == null) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (a2.isRunModelCourse()) {
                gds.e(context, a2, this.d, currentTimeMillis, this.b);
            } else {
                gds.d(context, a2, this.d, this.b, currentTimeMillis);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Suggestion_CommonWorkoutPlanProvider";
    }
}
