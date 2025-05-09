package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity;
import defpackage.edr;
import defpackage.edz;
import defpackage.eej;
import defpackage.gnm;
import defpackage.jdl;
import defpackage.pxp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class ActiveRecordTrendListProvider extends MinorProvider<edr> {
    private boolean e = true;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        b(context, (HashMap<String, Object>) hashMap, (edr) obj);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.e;
    }

    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, edr edrVar) {
        this.e = !edrVar.af();
        super.onSetSectionBeanData(sectionBean, edrVar);
    }

    public void b(final Context context, HashMap<String, Object> hashMap, final edr edrVar) {
        LogUtil.a("SCUI_ActiveRecordTrendListProvider", "enter parseParams");
        hashMap.put("VIEW_LIST", a(context, edrVar));
        hashMap.put("RIGHT_TOP_TEXT", DateFormatUtil.d(edrVar.j(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMMDD));
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordTrendListProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                ActiveRecordTrendListProvider.this.a(i, context, edrVar);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Context context, edr edrVar) {
        if (i == 0) {
            pxp.e(1, 1);
            e(context, edrVar);
        } else if (i == 1) {
            pxp.e(1, 2);
            b(context, edrVar);
        } else if (i == 2) {
            pxp.e(1, 3);
            KnitActiveHoursActivity.e(context);
        } else {
            LogUtil.h("SCUI_ActiveRecordTrendListProvider", "enterPageByPosition switch default");
        }
    }

    private void e(Context context, edr edrVar) {
        Intent intent = new Intent();
        intent.setClass(context, FitnessStepDetailActivity.class);
        intent.putExtra("today_current_steps_total", edrVar.ab());
        gnm.aPB_(context, intent);
    }

    private void b(Context context, edr edrVar) {
        Intent intent = new Intent();
        intent.setClass(context, FitnessSportIntensityDetailActivity.class);
        intent.putExtra("today_current_middle_and_high_total", edrVar.w());
        gnm.aPB_(context, intent);
    }

    private List<edz> a(Context context, edr edrVar) {
        LogUtil.a("SCUI_ActiveRecordTrendListProvider", "enter getListData");
        ArrayList arrayList = new ArrayList(10);
        if (edrVar == null) {
            LogUtil.h("SCUI_ActiveRecordTrendListProvider", "getListData data is null");
            return arrayList;
        }
        long j = edrVar.j();
        if (jdl.c(j) > jdl.c(System.currentTimeMillis())) {
            LogUtil.h("SCUI_ActiveRecordTrendListProvider", "day is future", Long.valueOf(j));
            return arrayList;
        }
        boolean f = jdl.f(j, System.currentTimeMillis());
        c(context, edrVar, arrayList, j, f);
        d(context, edrVar, arrayList, j, f);
        e(context, edrVar, arrayList, j, jdl.ac(j));
        return arrayList;
    }

    private void c(Context context, edr edrVar, List<edz> list, long j, boolean z) {
        edz edzVar = new edz();
        edzVar.b(0);
        edzVar.agM_(dqb_(context, z, edrVar, DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_MD)));
        edzVar.a(edrVar.ad());
        edzVar.e(edrVar.p());
        edzVar.b(f(context, z, edrVar));
        edzVar.c(edrVar.k());
        edzVar.a(d(context, z, edrVar));
        edzVar.b(z);
        list.add(edzVar);
    }

    private void d(Context context, edr edrVar, List<edz> list, long j, boolean z) {
        edz edzVar = new edz();
        edzVar.b(1);
        edzVar.agM_(dqa_(context, z, edrVar, DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_MD)));
        edzVar.a(edrVar.ac());
        edzVar.e(edrVar.f());
        edzVar.c(edrVar.o());
        edzVar.b(e(context, z, edrVar));
        edzVar.a(a(context, z, edrVar));
        edzVar.b(z);
        list.add(edzVar);
    }

    private void e(Context context, edr edrVar, List<edz> list, long j, boolean z) {
        edz edzVar = new edz();
        edzVar.b(2);
        edzVar.agM_(dpZ_(context, z, edrVar, DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MONTH_DAY_MD)));
        edzVar.a(edrVar.ag());
        edzVar.e(edrVar.q());
        edzVar.c(edrVar.r());
        edzVar.b(b(context, z, edrVar));
        edzVar.a(c(context, z, edrVar));
        edzVar.b(z);
        list.add(edzVar);
    }

    private SpannableString dqb_(Context context, boolean z, edr edrVar, String str) {
        String string;
        int ad = edrVar.ad();
        int k = edrVar.k();
        if (ad == 0 && k == 0) {
            return new SpannableString(context.getResources().getString(R$string.IDS_active_step_none, eej.d(8000.0d), eej.d(10000.0d)));
        }
        boolean z2 = true;
        float abs = ((Math.abs(r1) * 1.0f) / (ad == 0 ? 1 : ad)) * 100.0f;
        int abs2 = Math.abs(ad - k);
        String d = eej.d(abs2);
        String quantityString = context.getResources().getQuantityString(R.plurals._2130903359_res_0x7f03013f, abs2, d);
        if (abs >= 5.0f && Math.abs(abs - 5.0f) > 1.0E-6f) {
            z2 = false;
        }
        if (z) {
            if (z2) {
                return new SpannableString(context.getResources().getString(R$string.IDS_active_step_equal_value));
            }
            if (ad > k) {
                string = context.getResources().getString(R$string.IDS_active_step_more_value, quantityString);
            } else {
                string = context.getResources().getString(R$string.IDS_active_step_less_value, quantityString);
            }
        } else {
            if (z2) {
                return new SpannableString(context.getResources().getString(R$string.IDS_active_step_equal_date_value, str));
            }
            if (ad > k) {
                string = context.getResources().getString(R$string.IDS_active_step_more_date_value, str, quantityString);
            } else {
                string = context.getResources().getString(R$string.IDS_active_step_less_date_value, str, quantityString);
            }
        }
        return eej.agW_(context, d, string, R.color._2131299154_res_0x7f090b52);
    }

    private SpannableString dqa_(Context context, boolean z, edr edrVar, String str) {
        String string;
        int ac = edrVar.ac();
        int o = edrVar.o();
        if (ac == 0 && o == 0) {
            return new SpannableString(context.getResources().getString(R$string.IDS_active_intensity_none, eej.d(5.0d), eej.d(30.0d)));
        }
        int abs = Math.abs(ac - o);
        String d = eej.d(abs);
        String quantityString = context.getResources().getQuantityString(R.plurals.IDS_single_circle_intensity_target_desc, abs, d);
        boolean z2 = abs <= 5;
        if (z) {
            if (z2) {
                return new SpannableString(context.getResources().getString(R$string.IDS_active_intensity_equal_value));
            }
            if (ac > o) {
                string = context.getResources().getString(R$string.IDS_active_intensity_more_value, quantityString);
            } else {
                string = context.getResources().getString(R$string.IDS_active_intensity_less_value, quantityString);
            }
        } else {
            if (z2) {
                return new SpannableString(context.getResources().getString(R$string.IDS_active_equals_date_value, str));
            }
            if (ac > o) {
                string = context.getResources().getString(R$string.IDS_active_more_date_value, str, quantityString);
            } else {
                string = context.getResources().getString(R$string.IDS_active_less_date_value, str, quantityString);
            }
        }
        return eej.agW_(context, d, string, R.color._2131296651_res_0x7f09018b);
    }

    private SpannableString dpZ_(Context context, boolean z, edr edrVar, String str) {
        String string;
        int ag = edrVar.ag();
        int r = edrVar.r();
        if (ag == 0 && r == 0) {
            return new SpannableString(context.getResources().getString(R$string.IDS_active_stand_none));
        }
        int abs = Math.abs(ag - r);
        String d = eej.d(abs);
        String quantityString = context.getResources().getQuantityString(R.plurals.IDS_single_circle_active_target_desc, abs, d);
        boolean z2 = abs == 0;
        if (z) {
            if (z2) {
                return new SpannableString(context.getResources().getString(R$string.IDS_active_stand_equal_value));
            }
            if (ag > r) {
                string = context.getResources().getString(R$string.IDS_active_stand_more_value, quantityString);
            } else {
                string = context.getResources().getString(R$string.IDS_active_stand_less_value, quantityString);
            }
        } else {
            if (z2) {
                return new SpannableString(context.getResources().getString(R$string.IDS_active_stand_equal_date_value, str));
            }
            if (ag > r) {
                string = context.getResources().getString(R$string.IDS_active_stand_more_date_value, str, quantityString);
            } else {
                string = context.getResources().getString(R$string.IDS_active_stand_less_date_value, str, quantityString);
            }
        }
        return eej.agW_(context, d, string, R.color._2131296457_res_0x7f0900c9);
    }

    private String f(Context context, boolean z, edr edrVar) {
        int ad = edrVar.ad();
        return context.getResources().getQuantityString(z ? R.plurals._2130903438_res_0x7f03018e : R.plurals._2130903440_res_0x7f030190, ad, eej.d(ad));
    }

    private String e(Context context, boolean z, edr edrVar) {
        int ac = edrVar.ac();
        return context.getResources().getQuantityString(z ? R.plurals._2130903442_res_0x7f030192 : R.plurals._2130903444_res_0x7f030194, ac, eej.d(ac));
    }

    private String b(Context context, boolean z, edr edrVar) {
        int ag = edrVar.ag();
        return context.getResources().getQuantityString(z ? R.plurals._2130903446_res_0x7f030196 : R.plurals._2130903448_res_0x7f030198, ag, eej.d(ag));
    }

    private String d(Context context, boolean z, edr edrVar) {
        int k = edrVar.k();
        return context.getResources().getQuantityString(z ? R.plurals._2130903439_res_0x7f03018f : R.plurals._2130903441_res_0x7f030191, k, eej.d(k));
    }

    private String a(Context context, boolean z, edr edrVar) {
        int o = edrVar.o();
        return context.getResources().getQuantityString(z ? R.plurals._2130903443_res_0x7f030193 : R.plurals._2130903445_res_0x7f030195, o, eej.d(o));
    }

    private String c(Context context, boolean z, edr edrVar) {
        int r = edrVar.r();
        return context.getResources().getQuantityString(z ? R.plurals._2130903447_res_0x7f030197 : R.plurals._2130903449_res_0x7f030199, r, eej.d(r));
    }
}
