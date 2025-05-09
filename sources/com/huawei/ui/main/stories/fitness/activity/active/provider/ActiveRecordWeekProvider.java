package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity;
import com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.SportIntensityExplain;
import defpackage.edr;
import defpackage.eeb;
import defpackage.gnm;
import defpackage.jdl;
import defpackage.jec;
import defpackage.koq;
import defpackage.nhj;
import defpackage.nip;
import defpackage.nir;
import defpackage.niw;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsg;
import defpackage.nsn;
import defpackage.pxp;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class ActiveRecordWeekProvider extends MinorProvider<edr> {

    /* renamed from: a, reason: collision with root package name */
    private int f9735a;
    private boolean b;
    private edr e;
    private SectionBean g;
    private final Context c = BaseApplication.e();
    private boolean d = true;
    private boolean h = true;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.d && !nhj.n();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, edr edrVar) {
        if (edrVar != null) {
            this.e = edrVar;
            this.d = edrVar.af();
        }
        super.onSetSectionBeanData(sectionBean, edrVar);
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        Map.Entry<Long, Integer> entry;
        super.e(context, sectionBean);
        this.g = sectionBean;
        edr edrVar = this.e;
        List<Map.Entry<Long, Integer>> e2 = edrVar != null ? e(edrVar.z()) : null;
        if (!koq.c(e2) || (entry = e2.get(0)) == null || jdl.e(entry.getKey().longValue(), System.currentTimeMillis()) <= 3) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("900200007");
            niw.b(arrayList, 1, new e());
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, edr edrVar) {
        super.parseParams(context, hashMap, edrVar);
        if (context == null || hashMap == null || edrVar == null) {
            ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "parseParams context ", context, " outParams ", hashMap, " data ", edrVar);
            return;
        }
        this.e = edrVar;
        this.b = jdl.z(edrVar.j());
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(e(this.e));
        arrayList.add(d(this.e));
        arrayList.add(c(this.e));
        hashMap.put("VIEW_LIST", arrayList);
        hashMap.put("CLICK_EVENT_LISTENER", a(context));
    }

    private OnClickSectionListener a(final Context context) {
        return new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordWeekProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                ReleaseLogUtil.b("R_ActiveRecordWeekProvider", "getClickSectionListener onClick subView ", str);
                ActiveRecordWeekProvider.this.a(context, str);
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                ReleaseLogUtil.b("R_ActiveRecordWeekProvider", "getClickSectionListener onClick position ", Integer.valueOf(i));
                if (!nsn.o()) {
                    ActiveRecordWeekProvider.this.b(context, i);
                } else {
                    ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "getClickSectionListener onClick isFastClick position ", Integer.valueOf(i));
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
                ReleaseLogUtil.b("R_ActiveRecordWeekProvider", "getClickSectionListener onClick index ", Integer.valueOf(i), " position ", Integer.valueOf(i2));
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                ReleaseLogUtil.b("R_ActiveRecordWeekProvider", "getClickSectionListener onClick index ", Integer.valueOf(i), " subView ", str);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.b("R_ActiveRecordWeekProvider", "getClickSectionListener onClick view ", view);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        str.hashCode();
        if (!str.equals("GOAL_BUTTON")) {
            if (str.equals("RIGHT_ICON_CLICK_EVENT")) {
                gnm.aPB_(context, new Intent(this.c, (Class<?>) SportIntensityExplain.class));
                return;
            } else {
                ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "setClick default subViewType ", str);
                return;
            }
        }
        nip.a("900200007", this.f9735a * 1000, new ResponseCallback() { // from class: pim
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                ObserverManagerUtil.c("observer_refresh_active_record_provider", "");
            }
        });
        edr edrVar = this.e;
        if (edrVar != null) {
            int c = edrVar.c();
            int i = this.f9735a;
            pxp.c(4, 1, pxp.d(c, i, 4, i), this.f9735a);
            nrh.b(this.c, R$string.IDS_active_heat_target_toast);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, int i) {
        Intent intent = new Intent();
        if (i == 0) {
            Bundle bundle = new Bundle();
            bundle.putLong("intent_key_query_start_time", jec.n(jec.b()));
            intent.putExtra("bundle_key_data", bundle);
            intent.setClass(this.c, FitnessCalorieDetailActivity.class);
            edr edrVar = this.e;
            if (edrVar != null) {
                intent.putExtra("today_current_colories_total", edrVar.e() * 1000);
                intent.putExtra("default_time_millis", this.e.j());
            }
            gnm.aPB_(context, intent);
            pxp.a();
            pxp.e(1, 4);
            return;
        }
        if (i == 1) {
            intent.setClass(this.c, FitnessSportIntensityDetailActivity.class);
            edr edrVar2 = this.e;
            if (edrVar2 != null) {
                intent.putExtra("today_current_middle_and_high_total", edrVar2.i());
                intent.putExtra("default_time_millis", this.e.j());
            }
            gnm.aPB_(context, intent);
            pxp.a(4);
            pxp.e(1, 2);
            return;
        }
        if (i == 2) {
            edr edrVar3 = this.e;
            if (edrVar3 == null) {
                KnitActiveHoursActivity.e(context);
            } else {
                KnitActiveHoursActivity.e(context, edrVar3.j());
            }
            pxp.a(3);
            pxp.e(1, 3);
            return;
        }
        ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "setClick default position ", Integer.valueOf(i));
    }

    private List<Map.Entry<Long, Integer>> e(Map<Long, Integer> map) {
        if (map == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<Long, Integer>>() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordWeekProvider.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<Long, Integer> entry, Map.Entry<Long, Integer> entry2) {
                return Long.compare(entry != null ? entry.getKey().longValue() : 0L, entry2 != null ? entry2.getKey().longValue() : 0L);
            }
        });
        return arrayList;
    }

    private List<Integer> d(Map<Long, Integer> map) {
        ArrayList arrayList = new ArrayList(7);
        for (Map.Entry<Long, Integer> entry : e(map)) {
            if (entry != null) {
                arrayList.add(entry.getValue());
            }
        }
        for (int size = arrayList.size(); size < 8; size++) {
            arrayList.add(0);
        }
        return arrayList;
    }

    private eeb e(edr edrVar) {
        eeb eebVar = new eeb(edrVar.j(), R.drawable._2131431718_res_0x7f0b1126, nsf.h(R$string.IDS_active_caloric), 0);
        eebVar.e(e(edrVar, eebVar));
        int e2 = edrVar.e();
        eebVar.c(c(c(e2), UnitUtil.e(e2, 1, 0)));
        int n = edrVar.n();
        eebVar.a(c(c(n), UnitUtil.e(n, 1, 0)));
        eebVar.d(edrVar.c());
        eebVar.e(d(edrVar.z()));
        eebVar.a(ContextCompat.getColor(this.c, R.color._2131296433_res_0x7f0900b1));
        eebVar.c(ContextCompat.getColor(this.c, R.color._2131296434_res_0x7f0900b2));
        eebVar.e(ContextCompat.getColor(this.c, R.color._2131296437_res_0x7f0900b5));
        return eebVar;
    }

    private eeb d(edr edrVar) {
        eeb eebVar = new eeb(edrVar.j(), R.drawable._2131431723_res_0x7f0b112b, nsf.h(R$string.IDS_active_workout), R.drawable._2131430067_res_0x7f0b0ab3);
        eebVar.e(a(edrVar));
        eebVar.b(0);
        int i = edrVar.i();
        eebVar.c(c(a(i), UnitUtil.e(i, 1, 0)));
        int o = edrVar.o();
        eebVar.a(c(a(o), UnitUtil.e(o, 1, 0)));
        eebVar.d(edrVar.f());
        eebVar.e(d(edrVar.ah()));
        eebVar.a(ContextCompat.getColor(this.c, R.color._2131296449_res_0x7f0900c1));
        eebVar.c(ContextCompat.getColor(this.c, R.color._2131296450_res_0x7f0900c2));
        eebVar.e(ContextCompat.getColor(this.c, R.color._2131296453_res_0x7f0900c5));
        return eebVar;
    }

    private eeb c(edr edrVar) {
        eeb eebVar = new eeb(edrVar.j(), R.drawable._2131431720_res_0x7f0b1128, nsf.h(R$string.IDS_active_hours), 0);
        eebVar.e(b(edrVar));
        eebVar.b(0);
        int s = edrVar.s();
        eebVar.c(c(e(s), UnitUtil.e(s, 1, 0)));
        int l = edrVar.l();
        eebVar.a(c(e(l), UnitUtil.e(l, 1, 0)));
        eebVar.d(edrVar.q());
        eebVar.e(d(edrVar.aa()));
        eebVar.a(ContextCompat.getColor(this.c, R.color._2131296438_res_0x7f0900b6));
        eebVar.c(ContextCompat.getColor(this.c, R.color._2131296445_res_0x7f0900bd));
        eebVar.e(ContextCompat.getColor(this.c, R.color._2131296443_res_0x7f0900bb));
        return eebVar;
    }

    private CharSequence c(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        int indexOf = str.indexOf(str2);
        if (indexOf <= -1) {
            ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "getValue index ", Integer.valueOf(indexOf));
            return spannableString;
        }
        int length = indexOf + str2.length();
        int length2 = str.length();
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), length, length2, 17);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.c, R.color._2131299241_res_0x7f090ba9)), length, length2, 17);
        spannableString.setSpan(new TypefaceSpan(nsf.h(R$string.textFontFamilyRegular)), length, length2, 17);
        return spannableString;
    }

    private CharSequence e(edr edrVar, eeb eebVar) {
        int c = edrVar.c();
        int i = this.f9735a;
        if (i > 0 && i != c && !this.b) {
            eebVar.b(1);
            if (this.h) {
                pxp.b(2);
                this.h = false;
            }
            return nsf.b(R$string.IDS_active_week_heat_target_change, c(c), c(this.f9735a));
        }
        eebVar.b(0);
        int e2 = edrVar.e();
        int subtractExact = Math.subtractExact(e2, edrVar.n());
        String c2 = c(Math.abs(subtractExact));
        if (this.b) {
            if (subtractExact >= 50) {
                return nsf.h(R$string.IDS_active_week_heat_tip_history_1);
            }
            if (subtractExact > 0) {
                return nsf.b(R$string.IDS_active_week_heat_tip_history_2, c2);
            }
            if (subtractExact == 0) {
                return nsf.h(R$string.IDS_active_week_heat_tip_history_4);
            }
            return nsf.b(R$string.IDS_active_week_heat_tip_history_3, c2);
        }
        if (e2 < c) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(Integer.valueOf(R$string.IDS_active_week_heat_tip_1));
            arrayList.add(Integer.valueOf(R$string.IDS_active_week_heat_tip_2));
            return nsf.b(((Integer) arrayList.get(nsg.b(arrayList.size()))).intValue(), c(Math.subtractExact(c, e2)));
        }
        if (subtractExact >= 50) {
            return nsf.h(R$string.IDS_active_week_heat_tip_done_1);
        }
        if (subtractExact > 0) {
            return nsf.b(R$string.IDS_active_week_heat_tip_done_2, c2);
        }
        if (subtractExact == 0) {
            return nsf.h(R$string.IDS_active_week_heat_tip_done_4);
        }
        return nsf.b(R$string.IDS_active_week_heat_tip_done_3, c2);
    }

    private CharSequence a(edr edrVar) {
        int i = edrVar.i();
        int subtractExact = Math.subtractExact(i, edrVar.o());
        String d = d(Math.abs(subtractExact));
        if (this.b) {
            if (subtractExact >= 20) {
                return nsf.h(R$string.IDS_active_week_workout_tip_history_1);
            }
            if (subtractExact > 0) {
                return nsf.b(R$string.IDS_active_week_workout_tip_history_2, d);
            }
            if (subtractExact == 0) {
                return nsf.h(R$string.IDS_active_week_workout_tip_history_4);
            }
            return nsf.b(R$string.IDS_active_week_workout_tip_history_3, d);
        }
        int f = edrVar.f();
        if (i >= f) {
            Map<Long, Integer> ah = edrVar.ah();
            int i2 = 0;
            if (ah != null) {
                for (Map.Entry<Long, Integer> entry : ah.entrySet()) {
                    if (entry != null) {
                        i2 += entry.getValue().intValue();
                    }
                }
            }
            if (i2 >= 150) {
                return nsf.b(R$string.IDS_active_week_workout_tip_done_1, d(150));
            }
            if (subtractExact >= 20) {
                return nsf.b(R$string.IDS_active_week_workout_tip_done_2, a(i2), a(150), a(75));
            }
            if (subtractExact > 0) {
                return nsf.b(R$string.IDS_active_week_workout_tip_done_3, a(Math.abs(subtractExact)), a(i2), a(150), a(75));
            }
            if (subtractExact == 0) {
                return nsf.b(R$string.IDS_active_week_workout_tip_done_5, a(i2), a(150), a(75));
            }
            return nsf.b(R$string.IDS_active_week_workout_tip_done_4, d, d(i2), d(150), d(75));
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(Integer.valueOf(R$string.IDS_active_week_workout_tip_1));
        arrayList.add(Integer.valueOf(R$string.IDS_active_week_workout_tip_5));
        ArrayList arrayList2 = new ArrayList(4);
        int subtractExact2 = Math.subtractExact(f, i);
        String d2 = d(subtractExact2);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(nsf.b(((Integer) it.next()).intValue(), d2));
        }
        arrayList2.add(c(R.plurals._2130903381_res_0x7f030155, subtractExact2));
        arrayList2.add(c(R.plurals._2130903382_res_0x7f030156, subtractExact2));
        return (CharSequence) arrayList2.get(nsg.b(arrayList2.size()));
    }

    private CharSequence b(edr edrVar) {
        int a2 = edrVar.a();
        String b = b(a2);
        if (this.b) {
            if (a2 >= 5) {
                return nsf.b(R$string.IDS_active_week_hour_tip_history_1, b);
            }
            if (a2 >= 2) {
                return nsf.b(R$string.IDS_active_week_hour_tip_history_2, b);
            }
            if (a2 > 0) {
                return nsf.b(R$string.IDS_active_week_hour_tip_history_3, b);
            }
            return nsf.h(R$string.IDS_active_week_hour_tip_history_4);
        }
        int s = edrVar.s();
        int q = edrVar.q();
        if (s >= q) {
            return nsf.b(R$string.IDS_active_week_hour_tip_done, b);
        }
        int subtractExact = Math.subtractExact(q, s);
        if (subtractExact > Math.subtractExact(24, Calendar.getInstance().get(11))) {
            return nsf.h(R$string.IDS_active_week_hour_tip_4);
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(nsf.b(R$string.IDS_active_week_hour_tip_2, e(subtractExact)));
        arrayList.add(c(R.plurals._2130903383_res_0x7f030157, subtractExact));
        return (CharSequence) arrayList.get(nsg.b(arrayList.size()));
    }

    private String c(int i) {
        return c(R.plurals._2130903083_res_0x7f03002b, i);
    }

    private String b(int i) {
        return c(R.plurals._2130903331_res_0x7f030123, i);
    }

    private String e(int i) {
        return c(R.plurals._2130903199_res_0x7f03009f, i);
    }

    private String d(int i) {
        return c(R.plurals._2130903200_res_0x7f0300a0, i);
    }

    private String a(int i) {
        return c(R.plurals._2130903370_res_0x7f03014a, i);
    }

    private String c(int i, int i2) {
        return nsf.a(i, i2, UnitUtil.e(i2, 1, 0));
    }

    static class e implements IBaseResponseCallback {
        private final WeakReference<ActiveRecordWeekProvider> e;

        private e(ActiveRecordWeekProvider activeRecordWeekProvider) {
            this.e = new WeakReference<>(activeRecordWeekProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ActiveRecordWeekProvider activeRecordWeekProvider = this.e.get();
            if (activeRecordWeekProvider == null) {
                ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "InternalCallback onResponse provider is null");
                return;
            }
            if (i != 0) {
                ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "InternalCallback onResponse errorCode ", Integer.valueOf(i));
                return;
            }
            if (!(obj instanceof HashMap)) {
                ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "InternalCallback onResponse object ", obj);
                return;
            }
            nir nirVar = (nir) ((HashMap) obj).get("900200007");
            if (nirVar == null) {
                ReleaseLogUtil.a("R_ActiveRecordWeekProvider", "InternalCallback onResponse goalModel is null");
                return;
            }
            if (nirVar.b() == 0) {
                return;
            }
            activeRecordWeekProvider.f9735a = nirVar.e() / 1000;
            if (activeRecordWeekProvider.g == null || activeRecordWeekProvider.e == null) {
                return;
            }
            activeRecordWeekProvider.g.e(activeRecordWeekProvider.e);
        }
    }
}
