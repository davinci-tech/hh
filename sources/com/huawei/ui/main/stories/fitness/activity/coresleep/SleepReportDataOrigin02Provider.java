package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import defpackage.fdp;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepReportDataOrigin02Provider extends SleepReportDataOriginProvider {
    private boolean e = false;

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportDataOriginProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportDataOriginProvider, com.huawei.health.knit.data.MinorProvider
    /* renamed from: e */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL && (fdpVar.d().q() || fdpVar.d().s())) {
            Map<String, Object> l = fdpVar.l();
            if (l != null) {
                Object obj = l.get("BAR_CHART_HELP_ICON_VISIBILITY");
                if ((obj instanceof Integer) && ((Integer) obj).intValue() == 0) {
                    this.e = true;
                    sectionBean.e(fdpVar);
                    return;
                }
                return;
            }
            return;
        }
        this.e = false;
        sectionBean.e(fdpVar);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportDataOriginProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        hashMap.put("REPORT_TEXT", drx_());
        hashMap.put("CORE_SLEEP_BTN_TIPS", true);
    }
}
