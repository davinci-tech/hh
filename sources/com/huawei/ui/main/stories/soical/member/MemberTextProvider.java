package com.huawei.ui.main.stories.soical.member;

import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import defpackage.dpx;
import defpackage.dqj;
import defpackage.nsn;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class MemberTextProvider extends BaseKnitDataProvider {
    private Map<String, Object> e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, SectionBean sectionBean) {
        HashMap hashMap = new HashMap();
        this.e = hashMap;
        hashMap.put("ITEM_TITLE", context.getResources().getString(R.string._2130845688_res_0x7f021ff8));
        this.e.put("CLICK_EVENT_LISTENER", new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberTextProvider.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                dqj.e("", "VipPage");
                dpx.c(context, 0);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        sectionBean.e(this.e);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        hashMap.putAll((Map) obj);
    }
}
