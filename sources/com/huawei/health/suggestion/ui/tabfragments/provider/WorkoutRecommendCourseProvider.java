package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class WorkoutRecommendCourseProvider extends YogaRecommendCourseProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 1;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 22;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.b(getLogTag(), "loadData");
        if (super.isNeedUpdate()) {
            ThreadPoolManager.d().execute(new FitnessRecommendCourseProvider.c(this, sectionBean));
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Fitness_WorkoutRecommendCourseProvider";
    }
}
