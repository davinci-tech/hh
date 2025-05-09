package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.Context;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class BloodPressureNoDataHintProvider extends BaseKnitDataProvider {
    SectionBean e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("BloodPressureNoDataTopProvider", "loadData");
        this.e = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("BloodPressureNoDataTopProvider", "loadDefaultData");
        super.loadDefaultData(sectionBean);
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("BloodPressureNoDataTopProvider", "parseParams");
        hashMap.put("REPORT_TEXT", context.getString(R$string.IDS_blood_pressure_no_data_hint));
        hashMap.put("MARGIN_TOP", 0);
    }
}
