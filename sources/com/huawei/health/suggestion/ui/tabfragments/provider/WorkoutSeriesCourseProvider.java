package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class WorkoutSeriesCourseProvider extends YogaSeriesCourseProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 22;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "loadData");
        if (super.isNeedUpdate()) {
            ThreadPoolManager.d().execute(new FitnessSeriesCourseProvider.b(this, sectionBean));
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130848533_res_0x7f022b15);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Fitness_WorkoutSeriesCourseProvider";
    }
}
