package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;

/* loaded from: classes8.dex */
public class RopeSkipRecommendCourseProvider extends YogaRecommendCourseProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 283;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 27;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.b(getLogTag(), "loadData");
        ThreadPoolManager.d().execute(new FitnessRecommendCourseProvider.c(this, sectionBean));
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R$string.IDS_track_skip_all_jump_course);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider
    protected void d() {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportAllCourseActivity.class);
        intent.putExtra("COURSE_CATEGORY_KEY", getCourseCategory());
        intent.addFlags(268435456);
        intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        try {
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_Provider_Fitness_RopeSkipRecommendCourseProvider", "onClickSubTitleEvent ActivityNotFoundException.");
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Fitness_RopeSkipRecommendCourseProvider";
    }
}
