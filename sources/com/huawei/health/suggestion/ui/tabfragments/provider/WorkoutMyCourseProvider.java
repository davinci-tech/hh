package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.gdz;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class WorkoutMyCourseProvider extends YogaMyCourseProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 22;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return LoginInit.getInstance(context).getIsLogined();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public List<gdz> getDefaultData() {
        return LoginInit.getInstance(BaseApplication.getContext()).getIsLogined() ? super.getDefaultData() : new ArrayList();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("Track_Provider_Track_WorkoutMyCourseProvider_" + getLogTag(), "onResume");
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130848532_res_0x7f022b14);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_WorkoutMyCourseProvider";
    }
}
