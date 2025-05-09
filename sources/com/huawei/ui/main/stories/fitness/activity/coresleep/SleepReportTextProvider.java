package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fdp;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SleepReportTextProvider extends MinorProvider<fdp> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9831a = true;
    private SectionBean d;
    private fdp e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        fdp fdpVar;
        return this.f9831a && (fdpVar = this.e) != null && fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE && this.e.f().h() > 0;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        this.d = sectionBean;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        this.e = fdpVar;
        hashMap.put("REPORT_TEXT", context.getResources().getString(R$string.IDS_sleep_report_text));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL && (fdpVar.d().q() || fdpVar.d().s())) {
            LogUtil.a("SleepReportTextProvider", "isManualOnlyBedTime");
            this.f9831a = false;
            sectionBean.e(this, fdpVar);
        } else {
            this.e = fdpVar;
            this.d = sectionBean;
            sectionBean.e(this, fdpVar);
        }
    }
}
