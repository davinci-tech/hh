package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class BloodPressureNoDataTopProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    SectionBean f10160a;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("BloodPressureNoDataTopProvider", "loadData");
        this.f10160a = sectionBean;
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
        hashMap.put("DEVICE_INFO_FLAG", false);
        hashMap.put("IS_SHOW_TOAST", true);
        hashMap.put("TOP_IMAGE", Integer.valueOf(R.drawable._2131430909_res_0x7f0b0dfd));
        hashMap.put("TOP_IMAGE_TAHITI", Integer.valueOf(R.drawable._2131430910_res_0x7f0b0dfe));
        hashMap.put("ROUND_CORNER_IMAGE", Integer.valueOf(R.drawable._2131431288_res_0x7f0b0f78));
    }
}
