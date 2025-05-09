package com.huawei.ui.main.stories.health.lactatethreshold.provider;

import android.content.Context;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class LacticTextProvider extends BaseKnitDataProvider {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.a(b(BaseApplication.getContext()));
        sectionBean.e(new Object());
    }

    private HashMap<String, Object> b(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("TITLE", context.getResources().getString(R$string.IDS_running_lactate_threshold_q));
        hashMap.put("TEXT1", context.getResources().getString(R$string.IDS_running_lactate_threshold_a));
        hashMap.put("SUB_HEADER_MAX_LINES", 3);
        return hashMap;
    }
}
