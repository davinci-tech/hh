package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider;

import com.huawei.ui.commonui.linechart.common.DataInfos;
import defpackage.nom;

/* loaded from: classes9.dex */
public class BloodOxygenYearChartProvider extends BloodOxygenBaseChartProvider {
    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenBaseChartProvider
    protected int getStartTimestamp(long j) {
        if (j > 0) {
            return nom.f(nom.t(j));
        }
        return 0;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenBaseChartProvider
    protected DataInfos getDataInfo() {
        return DataInfos.BloodOxygenYearDetail;
    }
}
