package com.huawei.ui.main.stories.fitness.activity.active.provider;

import com.huawei.ui.commonui.linechart.common.DataInfos;
import defpackage.nom;

/* loaded from: classes9.dex */
public class ActiveHoursYearBarChartProvider extends ActiveHoursBaseBarChartProvider {
    @Override // com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursBaseBarChartProvider
    protected DataInfos getDataInfo() {
        return DataInfos.ActiveHoursYearDetail;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursBaseBarChartProvider
    protected int getStartTimestamp(long j) {
        if (j > 0) {
            return nom.f(nom.t(j));
        }
        return 0;
    }
}
