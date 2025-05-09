package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import defpackage.fci;
import defpackage.fda;
import defpackage.fdp;
import defpackage.koq;
import defpackage.pwt;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepInterpretationsProvider extends MinorProvider<fdp> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return VersionControlUtil.isSupportSleepManagement();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00af  */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onSetSectionBeanData(com.huawei.health.knit.section.model.SectionBean r7, defpackage.fdp r8) {
        /*
            r6 = this;
            java.lang.String r0 = "load data"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Sleep_SleepInterpretationsProvider"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            if (r8 != 0) goto L1b
            java.lang.String r8 = "data is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
            r7.e(r0)
            return
        L1b:
            boolean r2 = r8.m()
            if (r2 != 0) goto L2e
            java.lang.String r8 = "data is not valid"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            r7.e(r0)
            return
        L2e:
            com.huawei.health.sleep.sleepviewdata.SleepViewConstants$SleepTypeEnum r2 = r8.i()
            com.huawei.health.sleep.sleepviewdata.SleepViewConstants$SleepTypeEnum r3 = com.huawei.health.sleep.sleepviewdata.SleepViewConstants.SleepTypeEnum.COMMON
            if (r2 != r3) goto L43
            java.lang.String r8 = "sleep common data, return"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            r7.e(r0)
            return
        L43:
            int[] r2 = com.huawei.ui.main.stories.fitness.activity.coresleep.SleepInterpretationsProvider.AnonymousClass3.e
            com.huawei.health.sleep.sleepviewdata.SleepViewConstants$SleepTypeEnum r3 = r8.i()
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 1
            if (r2 == r3) goto L8a
            r3 = 2
            if (r2 == r3) goto L79
            r3 = 3
            r4 = 0
            if (r2 == r3) goto L70
            r3 = 4
            if (r2 == r3) goto L67
            java.lang.String r2 = "other sleep"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r2)
            r2 = r4
            goto La0
        L67:
            fdm r2 = r8.c()
            int r2 = r2.h()
            goto L9d
        L70:
            fdj r2 = r8.d()
            int r2 = r2.h()
            goto L9d
        L79:
            fdk r2 = r8.f()
            int r2 = r2.w()
            fdk r3 = r8.f()
            int r3 = r3.h()
            goto L9a
        L8a:
            fdr r2 = r8.j()
            int r2 = r2.ap()
            fdr r3 = r8.j()
            int r3 = r3.h()
        L9a:
            r4 = r2
            int r2 = r3 + r4
        L9d:
            r5 = r4
            r4 = r2
            r2 = r5
        La0:
            if (r4 != r2) goto Laf
            java.lang.String r8 = "total sleep time & total day sleep time is same, return"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            r7.e(r0)
            return
        Laf:
            com.huawei.health.sleep.sleepviewdata.SleepViewConstants$SleepTypeEnum r2 = r8.i()
            com.huawei.health.sleep.sleepviewdata.SleepViewConstants$SleepTypeEnum r3 = com.huawei.health.sleep.sleepviewdata.SleepViewConstants.SleepTypeEnum.MANUAL
            if (r2 != r3) goto Lce
            fdj r2 = r8.d()
            boolean r2 = r2.q()
            if (r2 == 0) goto Lce
            java.lang.String r8 = "sleep manual data not on three"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            r7.e(r0)
            return
        Lce:
            java.util.Map r2 = r8.l()
            java.lang.String r3 = "MGMT_DAILY_SLEEP_PROCESS"
            java.lang.Object r2 = r2.get(r3)
            boolean r3 = r2 instanceof defpackage.fda
            if (r3 != 0) goto Le9
            java.lang.String r8 = "not sleep daily result"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r8)
            r7.e(r0)
            return
        Le9:
            java.lang.String r0 = "dailySleepProcessObject is "
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r7.e(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepInterpretationsProvider.onSetSectionBeanData(com.huawei.health.knit.section.model.SectionBean, fdp):void");
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepInterpretationsProvider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            e = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        if (fdpVar == null) {
            LogUtil.b("Sleep_SleepInterpretationsProvider", "data is null, return");
            return;
        }
        Map<String, Object> l = fdpVar.l();
        Object obj = l.get("MGMT_DAILY_SLEEP_PROCESS");
        if (!(obj instanceof fda)) {
            LogUtil.b("Sleep_SleepInterpretationsProvider", "not daily result bean");
            return;
        }
        fda fdaVar = (fda) obj;
        hashMap.put("DAILY_SLEEP_INTERPRETATIONS", pwt.a(fdaVar));
        hashMap.put("DAILY_SLEEP_PROBLEM_FACTOR", pwt.b(fdaVar));
        Object obj2 = l.get("OPEN_STATUS");
        boolean booleanValue = obj2 instanceof Boolean ? ((Boolean) obj2).booleanValue() : false;
        fci d = d(fdaVar);
        if (!booleanValue && d != null) {
            hashMap.put("DAILY_SLEEP_ADVICE", pwt.e(d));
        } else {
            hashMap.remove("DAILY_SLEEP_ADVICE");
        }
        if (booleanValue) {
            hashMap.put("DAILY_SLEEP_INTERPRETATIONS_TITLE", BaseApplication.e().getResources().getString(R$string.IDS_sleep_interpretation));
        } else {
            hashMap.put("DAILY_SLEEP_INTERPRETATIONS_TITLE", BaseApplication.e().getResources().getString(R$string.IDS_sleep_interpretation_suggestions_new));
        }
    }

    private fci d(fda fdaVar) {
        if (fdaVar == null || koq.b(fdaVar.e())) {
            return null;
        }
        fci e = e(fdaVar, 2);
        if (e != null) {
            return e;
        }
        fci e2 = e(fdaVar, 1);
        return e2 != null ? e2 : e(fdaVar, 0);
    }

    private fci e(fda fdaVar, int i) {
        for (fci fciVar : fdaVar.e()) {
            if (fciVar != null && fciVar.e() == i) {
                return fciVar;
            }
        }
        return null;
    }
}
