package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import defpackage.fdp;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepReportDataOriginProvider extends MinorProvider<fdp> {
    private boolean e = false;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL && (fdpVar.d().s() || fdpVar.d().q())) {
            this.e = false;
            sectionBean.e(this, fdpVar);
            return;
        }
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            Map<String, Object> l = fdpVar.l();
            this.e = false;
            if (l != null) {
                Object obj = l.get("BAR_CHART_HELP_ICON_VISIBILITY");
                if ((obj instanceof Integer) && ((Integer) obj).intValue() == 0) {
                    this.e = true;
                    sectionBean.e(this, fdpVar);
                    return;
                }
                return;
            }
            return;
        }
        this.e = false;
        sectionBean.e(this, fdpVar);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        hashMap.put("REPORT_TEXT", drx_());
        hashMap.put("CORE_SLEEP_BTN_TIPS", false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x002f, code lost:
    
        if (r5 == (-1)) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected android.text.SpannableString drx_() {
        /*
            r9 = this;
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            int r1 = com.huawei.ui.main.R$string.IDS_manual_sleep_more_data
            java.lang.String r1 = r0.getString(r1)
            int r2 = com.huawei.ui.main.R$string.IDS_manual_sleep_manual_sleep_input
            java.lang.String r2 = r0.getString(r2)
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.lang.String r4 = ""
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r4}
            java.lang.String r1 = java.lang.String.format(r3, r1, r2)
            r2 = 0
            r3 = -1
            r4 = r2
            r5 = r3
            r6 = r5
        L21:
            int r7 = r1.length()
            if (r2 >= r7) goto L4d
            char r7 = r1.charAt(r2)
            r8 = 34
            if (r7 != r8) goto L32
            if (r5 != r3) goto L49
            goto L3f
        L32:
            char r7 = r1.charAt(r2)
            r8 = 8220(0x201c, float:1.1519E-41)
            if (r7 != r8) goto L41
            int r4 = r4 + 1
            r7 = 2
            if (r4 != r7) goto L4a
        L3f:
            r5 = r2
            goto L4a
        L41:
            char r7 = r1.charAt(r2)
            r8 = 8221(0x201d, float:1.152E-41)
            if (r7 != r8) goto L4a
        L49:
            r6 = r2
        L4a:
            int r2 = r2 + 1
            goto L21
        L4d:
            android.text.SpannableString r2 = new android.text.SpannableString
            r2.<init>(r1)
            if (r5 == r3) goto L6a
            if (r6 == r3) goto L6a
            int r1 = r1.length()
            int r1 = r1 + (-1)
            if (r5 == r1) goto L6a
            com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportDataOriginProvider$5 r1 = new com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportDataOriginProvider$5
            r1.<init>()
            int r6 = r6 + 1
            r0 = 18
            r2.setSpan(r1, r5, r6, r0)
        L6a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportDataOriginProvider.drx_():android.text.SpannableString");
    }
}
