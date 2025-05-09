package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import defpackage.fdl;
import defpackage.fdn;
import defpackage.fdo;
import defpackage.fdp;
import defpackage.fdq;
import health.compact.a.UnitUtil;
import java.text.NumberFormat;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SleepReportText02Provider extends MinorProvider<fdp> {
    private boolean b = false;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        d(context, (HashMap<String, Object>) hashMap, (fdp) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        boolean z = fdpVar != null && (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL || fdpVar.o() || fdpVar.e() != SleepViewConstants.ViewTypeEnum.DAY || !fdpVar.m());
        LogUtil.a("SleepReportText02Provider", "Is active: " + z);
        if (z) {
            sectionBean.e(fdpVar);
        } else {
            sectionBean.e((Object) null);
        }
    }

    public void d(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        LogUtil.a("SleepReportText02Provider", "parseParams");
        if (fdpVar == null) {
            return;
        }
        LogUtil.a("SleepReportText02Provider", "getTimePeriodType: ", fdpVar.e());
        if (fdpVar.l().containsKey("OPEN_STATUS") && (fdpVar.l().get("OPEN_STATUS") instanceof Boolean)) {
            this.b = ((Boolean) fdpVar.l().get("OPEN_STATUS")).booleanValue();
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            c(context, fdpVar, hashMap);
        } else if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK) {
            b(context, fdpVar, hashMap);
        } else if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH) {
            d(context, fdpVar, hashMap);
        }
    }

    private void d(Context context, fdp fdpVar, HashMap<String, Object> hashMap) {
        String string;
        if (fdpVar.n()) {
            string = b(context, fdpVar);
        } else {
            string = context.getResources().getString(R$string.IDS_manual_sleep_month_instruction, c(fdpVar, context), a(fdpVar, context));
        }
        hashMap.put("REPORT_TEXT", string);
    }

    private void b(Context context, fdp fdpVar, HashMap<String, Object> hashMap) {
        String string;
        if (fdpVar.n()) {
            string = b(context, fdpVar);
        } else {
            string = context.getResources().getString(R$string.IDS_manual_sleep_week_instruction, c(fdpVar, context), a(fdpVar, context));
        }
        hashMap.put("REPORT_TEXT", string);
    }

    private String b(Context context, fdp fdpVar) {
        String format = NumberFormat.getInstance().format(3L);
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL || fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            return context.getResources().getString(R$string.IDS_manual_sleep_less_than_three, Integer.valueOf(format));
        }
        return context.getResources().getString(R$string.IDS_fitness_core_sleep_less_than_three, Integer.valueOf(format));
    }

    private void c(Context context, fdp fdpVar, HashMap<String, Object> hashMap) {
        String string;
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            LogUtil.a("SleepReportText02Provider", "isManualSleep");
            if (fdpVar.d().q()) {
                String e = UnitUtil.e(3.0d, 1, 0);
                if (VersionControlUtil.isSupportSleepManagement() && !this.b) {
                    string = context.getResources().getString(R$string.IDS_manual_sleep_less_than_three, Integer.valueOf(e));
                }
                string = "";
            } else if (fdpVar.d().s()) {
                string = context.getResources().getString(R$string.IDS_manual_sleep_only_bed);
            } else {
                LogUtil.a("SleepReportText02Provider", "isManualSleep no notice");
                string = "";
            }
        } else if (d(fdpVar)) {
            LogUtil.a("SleepReportText02Provider", "isMergePhoneSleep");
            string = context.getResources().getString(R$string.IDS_sleep_phone_records_reminders);
        } else if (b(fdpVar)) {
            LogUtil.a("SleepReportText02Provider", "isMergeCoreSleep");
            string = context.getResources().getString(R$string.IDS_sleep_wear_records_reminders);
        } else if (!fdpVar.m() && VersionControlUtil.isSupportSleepManagement() && this.b) {
            LogUtil.a("SleepReportText02Provider", "no data today");
            string = context.getResources().getString(R$string.IDS_intel_sleep_advice_1);
        } else {
            LogUtil.a("SleepReportText02Provider", "report text02 not visible");
            string = "";
        }
        hashMap.put("REPORT_TEXT", string);
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepReportText02Provider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            d = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private String a(fdp fdpVar, Context context) {
        int v;
        int u;
        int i;
        int i2 = AnonymousClass3.d[fdpVar.i().ordinal()];
        if (i2 == 1) {
            v = ((fdl) fdpVar.c()).v();
            u = ((fdl) fdpVar.c()).u();
        } else if (i2 == 2) {
            v = ((fdn) fdpVar.d()).ad();
            u = ((fdn) fdpVar.d()).aa();
        } else if (i2 == 3) {
            v = ((fdo) fdpVar.f()).am() + ((fdo) fdpVar.f()).al();
            u = ((fdo) fdpVar.f()).an();
        } else if (i2 == 4) {
            v = ((fdq) fdpVar.j()).bp() + ((fdq) fdpVar.j()).br();
            u = ((fdq) fdpVar.j()).bn();
        } else {
            LogUtil.h("SleepReportText02Provider", "other sleep types");
            i = 0;
            return context.getResources().getQuantityString(R.plurals._2130903331_res_0x7f030123, i).replace("%s", UnitUtil.e(i, 1, 0));
        }
        i = v + u;
        return context.getResources().getQuantityString(R.plurals._2130903331_res_0x7f030123, i).replace("%s", UnitUtil.e(i, 1, 0));
    }

    private String c(fdp fdpVar, Context context) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            return context.getResources().getString(R$string.IDS_manual_sleep_scientific_sleep_wear);
        }
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            return context.getResources().getString(R$string.IDS_manual_sleep_manual_sleep_input);
        }
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            return context.getResources().getString(R$string.IDS_manual_sleep_instruction_phone);
        }
        return context.getResources().getString(R$string.IDS_manual_sleep_wear_normal_sleep);
    }

    private boolean d(fdp fdpVar) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE && fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            return true;
        }
        Object obj = fdpVar.l().get("IS_PHONE_AND_CORE_NOON");
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue() && (fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH)) {
            return true;
        }
        if (fdpVar.f().o()) {
            return fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH;
        }
        return false;
    }

    private boolean b(fdp fdpVar) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE && fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            return true;
        }
        if (fdpVar.j().o()) {
            return fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH;
        }
        return false;
    }
}
