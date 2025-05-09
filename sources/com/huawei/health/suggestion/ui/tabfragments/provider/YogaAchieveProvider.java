package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes8.dex */
public class YogaAchieveProvider extends FitnessAchieveProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 23;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider
    public String getEntranceTitle() {
        return BaseApplication.getContext().getString(R.string._2130851546_res_0x7f0236da);
    }
}
