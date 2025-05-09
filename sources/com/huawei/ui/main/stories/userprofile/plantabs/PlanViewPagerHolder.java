package com.huawei.ui.main.stories.userprofile.plantabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.userprofile.plantabs.PlanViewPagerHolder;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.rzs;
import defpackage.sbg;
import defpackage.sch;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class PlanViewPagerHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private int f10532a;
    private List<sch> b;
    private List<HealthTextView> c;
    private Context d;
    private Map<Integer, List<sch>> e;
    private long f;
    private LayoutInflater g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private ImageView k;
    private LinearLayout l;
    private ImageView m;
    private HealthTextView n;
    private LinearLayout o;
    private HealthTextView p;
    private FrameLayout t;

    public PlanViewPagerHolder(View view) {
        super(view);
        this.f = 0L;
        this.f10532a = 0;
        this.c = new ArrayList(10);
        Context context = BaseApplication.getContext();
        this.d = context;
        this.g = LayoutInflater.from(context);
        c();
    }

    private void c() {
        LogUtil.a("PlanViewPagerHolder", "initView");
        this.t = (FrameLayout) this.itemView.findViewById(R.id.plan_arrow_layout);
        this.m = (ImageView) this.itemView.findViewById(R.id.plan_arrow_right);
        this.l = (LinearLayout) this.itemView.findViewById(R.id.no_data_layout);
        this.i = (HealthTextView) this.itemView.findViewById(R.id.no_data_content);
        this.j = (HealthTextView) this.itemView.findViewById(R.id.no_data_goto);
        this.k = (ImageView) this.itemView.findViewById(R.id.no_data_icon);
        this.o = (LinearLayout) this.itemView.findViewById(R.id.plan_data_layout);
        this.n = (HealthTextView) this.itemView.findViewById(R.id.personal_tab_plan);
        this.p = (HealthTextView) this.itemView.findViewById(R.id.personal_tab_sport);
        this.h = (HealthTextView) this.itemView.findViewById(R.id.personal_tab_health);
        this.c.add(this.n);
        this.c.add(this.p);
        this.c.add(this.h);
        this.n.setOnClickListener(new View.OnClickListener() { // from class: sbz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlanViewPagerHolder.this.dVn_(view);
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() { // from class: scd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlanViewPagerHolder.this.dVo_(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: sca
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlanViewPagerHolder.this.dVp_(view);
            }
        });
    }

    public /* synthetic */ void dVn_(View view) {
        sbg.d(1, 0);
        d(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dVo_(View view) {
        sbg.d(1, 1);
        d(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dVp_(View view) {
        sbg.d(1, 2);
        d(2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(rzs rzsVar) {
        LogUtil.a("PlanViewPagerHolder", "fill");
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -2;
        if (!rzsVar.i()) {
            layoutParams.height = 0;
            layoutParams.width = 0;
            this.itemView.setVisibility(8);
        } else {
            this.itemView.setVisibility(0);
        }
        this.itemView.setLayoutParams(layoutParams);
        if (rzsVar.dUz_() != null) {
            this.t.setOnClickListener(rzsVar.dUz_());
        }
        if (LanguageUtil.bc(this.d)) {
            this.m.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (rzsVar.d() instanceof Map) {
            Map<Integer, List<sch>> map = (Map) rzsVar.d();
            this.e = map;
            LogUtil.a("PlanViewPagerHolder", "mDataMaps size = ", Integer.valueOf(map.size()));
            a();
        }
    }

    private void a() {
        long e = e(this.e, 0);
        int i = 1;
        long e2 = e(this.e, 1);
        int i2 = 2;
        long e3 = e(this.e, 2);
        if (e3 < 0) {
            i2 = 0;
            e3 = 0;
        }
        if (e2 < e3) {
            i = i2;
            e2 = e3;
        }
        if ((e >= jdl.e(System.currentTimeMillis()) || e < e2) && e2 != 0) {
            e = e2;
        } else {
            i = 0;
        }
        LogUtil.a("PlanViewPagerHolder", "processData pageIndex = ", Integer.valueOf(i));
        LogUtil.a("PlanViewPagerHolder", "processData mLastRecordTime = ", Long.valueOf(this.f), "lastRecordTime = ", Long.valueOf(e));
        if (this.f != e || e == 0) {
            d(i);
            this.f = e;
        } else if (!koq.b(this.b, this.e.get(Integer.valueOf(this.f10532a)), false)) {
            d(this.f10532a);
        }
        sbg.d(0, this.f10532a);
    }

    private long e(Map<Integer, List<sch>> map, int i) {
        if (!map.containsKey(Integer.valueOf(i))) {
            return 0L;
        }
        List<sch> list = map.get(Integer.valueOf(i));
        if (!koq.c(list) || list.get(0) == null) {
            return 0L;
        }
        return list.get(0).d();
    }

    private void d(int i) {
        LogUtil.a("PlanViewPagerHolder", "showTabPager pageIndex = ", Integer.valueOf(i));
        Map<Integer, List<sch>> map = this.e;
        if (map == null) {
            LogUtil.h("PlanViewPagerHolder", "mDataMaps is null or no data");
            return;
        }
        this.f10532a = i;
        List<sch> list = map.get(Integer.valueOf(i));
        this.b = list;
        if (koq.b(list)) {
            this.o.setVisibility(8);
            this.l.setVisibility(0);
            a(i);
        } else {
            LogUtil.a("PlanViewPagerHolder", "mDataMaps has data size = ", Integer.valueOf(this.b.size()));
            this.o.removeAllViews();
            this.o.setVisibility(0);
            this.l.setVisibility(8);
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                this.o.addView(dVl_(this.b, i2));
                if (i2 < this.b.size() - 1) {
                    this.o.addView(dVk_());
                }
            }
        }
        for (int i3 = 0; i3 < this.c.size(); i3++) {
            HealthTextView healthTextView = this.c.get(i3);
            if (i3 == i) {
                healthTextView.setTextColor(ContextCompat.getColor(this.d, R.color._2131299236_res_0x7f090ba4));
                healthTextView.setTypeface(nsk.cKO_());
            } else {
                healthTextView.setTextColor(ContextCompat.getColor(this.d, R.color._2131299244_res_0x7f090bac));
                healthTextView.setTypeface(nsk.cKP_());
            }
        }
    }

    private View dVl_(List<sch> list, int i) {
        sch schVar = list.get(i);
        View inflate = this.g.inflate(R.layout.personal_plan_fragment_item, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.plan_course_image);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.course_title);
        HealthDivider healthDivider = (HealthDivider) inflate.findViewById(R.id.course_item_divider);
        if (i == list.size() - 1) {
            healthDivider.setVisibility(8);
        } else {
            healthDivider.setVisibility(0);
        }
        if (schVar.b() instanceof String) {
            nrf.cIS_(imageView, (String) schVar.b(), nrf.c, 3, 0);
        } else if (schVar.b() instanceof Integer) {
            nrf.cIP_(imageView, ((Integer) schVar.b()).intValue(), nrf.c, 3, 0);
        } else {
            LogUtil.h("PlanViewPagerHolder", "IconPath error = ", schVar.b());
        }
        healthTextView.setText(schVar.h());
        dVm_(inflate, schVar);
        if (schVar.dVu_() != null) {
            inflate.setOnClickListener(schVar.dVu_());
        }
        return inflate;
    }

    private void dVm_(View view, sch schVar) {
        List<String> a2 = schVar.a();
        if (koq.b(a2)) {
            LogUtil.h("PlanViewPagerHolder", "descList is empty, return");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.plan_desc_layout);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.plan_desc_difficulty_duration);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.plan_desc_trained);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.plan_desc_difficulty);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.plan_desc_duration);
        if (nsn.p() || !LanguageUtil.h(BaseApplication.getContext())) {
            linearLayout.setOrientation(1);
        }
        if (koq.d(a2, 0) && !TextUtils.isEmpty(a2.get(0))) {
            healthTextView.setText(e(a2.get(0)));
        }
        if (koq.d(a2, 1) && !TextUtils.isEmpty(a2.get(1))) {
            linearLayout2.setVisibility(0);
            healthTextView2.setText(a2.get(1));
        }
        if (koq.d(a2, 2) && !TextUtils.isEmpty(a2.get(2))) {
            linearLayout2.setVisibility(0);
            healthTextView3.setText(a2.get(2));
        }
        if (schVar.dVt_() != null) {
            healthTextView.setOnClickListener(schVar.dVt_());
        }
    }

    private CharSequence e(String str) {
        String string = this.d.getString(R$string.IDS_plan_run_renew_now);
        if (!str.contains(string)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(string);
        SpannableString spannableString = new SpannableString(str);
        if (lastIndexOf == -1) {
            LogUtil.h("PlanViewPagerHolder", "splicingString not find linkName in originalStr");
            return spannableString;
        }
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.d, R.color._2131296651_res_0x7f09018b)), lastIndexOf, string.length() + lastIndexOf, 33);
        return spannableString;
    }

    private View dVk_() {
        View view = new View(this.d);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e)));
        return view;
    }

    private void a(int i) {
        LogUtil.a("PlanViewPagerHolder", "updateNoDataLayout pageIndex = ", Integer.valueOf(i));
        if (LanguageUtil.bc(this.d)) {
            this.k.setBackground(nrz.cKn_(this.d, R.mipmap._2131821054_res_0x7f1101fe));
        }
        if (i == 0) {
            this.i.setText(R$string.IDS_hwh_no_plan);
            this.j.setText(R$string.IDS_sug_start_plan);
            this.j.setOnClickListener(new View.OnClickListener() { // from class: scc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlanViewPagerHolder.this.dVq_(view);
                }
            });
        } else if (i == 1) {
            this.i.setText(R$string.IDS_course_no_used_record);
            this.j.setText(R$string.IDS_start_use);
            this.j.setOnClickListener(new View.OnClickListener() { // from class: scb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlanViewPagerHolder.this.dVr_(view);
                }
            });
        } else {
            if (i == 2) {
                this.i.setText(R$string.IDS_course_no_used_record);
                this.j.setText(R$string.IDS_start_use);
                this.j.setOnClickListener(new View.OnClickListener() { // from class: sce
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PlanViewPagerHolder.this.dVs_(view);
                    }
                });
                return;
            }
            LogUtil.h("PlanViewPagerHolder", "error pageIndex ", Integer.valueOf(i));
        }
    }

    public /* synthetic */ void dVq_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dVr_(View view) {
        i();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dVs_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        if (nsn.o()) {
            LogUtil.a("PlanViewPagerHolder", "onClick, isFastClick");
        } else {
            ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).launchPlanTab();
        }
    }

    private void i() {
        if (nsn.o()) {
            LogUtil.a("PlanViewPagerHolder", "onClick, isFastClick");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).b(268435456).c(this.d);
    }

    private void e() {
        if (nsn.o()) {
            LogUtil.a("PlanViewPagerHolder", "onClick, isFastClick");
        } else {
            LogUtil.a("PlanViewPagerHolder", "gotoSleepMusicCourseH5 ");
            AppRouter.zi_(Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&path=audioList&audioType=2&statusBarTextBlack&isImmerse")).b(268435456).c(this.d);
        }
    }
}
