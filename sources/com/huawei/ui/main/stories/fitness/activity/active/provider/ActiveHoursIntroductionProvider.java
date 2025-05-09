package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.ui.main.R$string;
import defpackage.nsf;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class ActiveHoursIntroductionProvider extends BaseKnitDataProvider {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        hashMap.put("STAND_WHAT", nsf.h(R$string.IDS_hour_introduction_title));
        hashMap.put("STAND_EXPLAIN", nsf.h(R$string.IDS_hour_introduction));
    }
}
