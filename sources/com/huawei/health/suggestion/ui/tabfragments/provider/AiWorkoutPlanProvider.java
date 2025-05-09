package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class AiWorkoutPlanProvider extends FitnessPlanProvider {

    /* renamed from: a, reason: collision with root package name */
    private FitnessPlanProvider f3383a;

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

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (List<Object>) obj);
    }

    public AiWorkoutPlanProvider() {
        this.f3383a = !Utils.o() ? new AiWorkoutPlanProviderChinaImpl() : new AiWorkoutPlanProviderOverseaImpl();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        FitnessPlanProvider fitnessPlanProvider = this.f3383a;
        return fitnessPlanProvider != null && fitnessPlanProvider.isActive(context) && super.isActive(context);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void updateMyPlans(IntPlan intPlan) {
        if (this.f3383a == null) {
            return;
        }
        LogUtil.a("Track_Provider_Suggestion_CommonWorkoutPlanProvider", "updateMyPlans");
        this.f3383a.updateMyPlans(intPlan);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void getRecommendedPlanList() {
        if (this.f3383a == null) {
            return;
        }
        LogUtil.a("Track_Provider_Suggestion_CommonWorkoutPlanProvider", "getRecommendedPlanList");
        this.f3383a.getRecommendedPlanList();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void onClickTitleEvent(Context context) {
        FitnessPlanProvider fitnessPlanProvider = this.f3383a;
        if (fitnessPlanProvider == null) {
            return;
        }
        fitnessPlanProvider.onClickTitleEvent(context);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void setMyPlanClickListener(Context context, List<IntPlan> list, Map<String, Object> map) {
        FitnessPlanProvider fitnessPlanProvider = this.f3383a;
        if (fitnessPlanProvider == null) {
            return;
        }
        fitnessPlanProvider.setMyPlanClickListener(context, list, map);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        FitnessPlanProvider fitnessPlanProvider = this.f3383a;
        if (fitnessPlanProvider == null) {
            return;
        }
        fitnessPlanProvider.loadData(context, sectionBean);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<Object> list) {
        FitnessPlanProvider fitnessPlanProvider = this.f3383a;
        if (fitnessPlanProvider == null) {
            return;
        }
        fitnessPlanProvider.parseParams(context, hashMap, list);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130848472_res_0x7f022ad8);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Suggestion_CommonWorkoutPlanProvider";
    }
}
