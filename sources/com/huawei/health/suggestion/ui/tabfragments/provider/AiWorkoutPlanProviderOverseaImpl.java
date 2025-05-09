package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.view.View;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ece;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class AiWorkoutPlanProviderOverseaImpl extends FitnessPlanProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void getRecommendedPlanList() {
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void onClickTitleEvent(Context context) {
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void setMyPlanClickListener(Context context, List<IntPlan> list, Map<String, Object> map) {
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void updateMyPlans(IntPlan intPlan) {
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (List<Object>) obj);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "loadData");
        this.mContext = context;
        this.mSectionBeanRef = new WeakReference<>(sectionBean);
        SectionBean sectionBean2 = this.mSectionBeanRef.get();
        if (sectionBean2 != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ece("智能减脂计划", "吃动平衡，智能化适配个性化减脂", R.drawable.fitness_background_1, new BaseKnitDataProvider.d() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiWorkoutPlanProviderOverseaImpl.1
                @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, android.view.View.OnClickListener
                public void onClick(View view) {
                    super.onClick(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }));
            arrayList.add(new ece("智能减脂计划2", "第二个 吃动平衡，智能化适配个性化减脂", R.drawable.fitness_background_1, new BaseKnitDataProvider.d() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiWorkoutPlanProviderOverseaImpl.3
                @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, android.view.View.OnClickListener
                public void onClick(View view) {
                    super.onClick(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }));
            arrayList.add(new ece("智能减脂计划3", "第三个 吃动平衡，智能化适配个性化减脂", R.drawable.fitness_background_1, new BaseKnitDataProvider.d() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.AiWorkoutPlanProviderOverseaImpl.2
                @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, android.view.View.OnClickListener
                public void onClick(View view) {
                    super.onClick(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }));
            sectionBean2.e(arrayList);
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<Object> list) {
        hashMap.put("SectionAiWorkoutPlanType_PLAN_LIST", list);
        hashMap.put("TITLE", getSubViewTitle());
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130848472_res_0x7f022ad8);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "AiWorkoutPlanProviderOverseaImpl";
    }
}
