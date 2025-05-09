package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.AchievePopMessageManager;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveRecordThreeCircleProvider;
import com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.climb.FitnessClimbDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.distance.FitnessDistanceDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.sportintensity.FitnessSportIntensityDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity;
import com.huawei.ui.main.stories.fitness.interactors.StepModuleChartStorageHelper;
import defpackage.edr;
import defpackage.eea;
import defpackage.eej;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.pxp;
import health.compact.a.LogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class ActiveRecordThreeCircleProvider extends MinorProvider<edr> {

    /* renamed from: a, reason: collision with root package name */
    private edr f9731a;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        this.f9731a = new edr();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, edr edrVar) {
        LogUtil.c("SCUI_ActiveRecordThreeCircleProvider", "parseParams data is ", edrVar);
        if (eej.c(edrVar, this.f9731a)) {
            hashMap.put("SECTION_HEALTH_DICT_BEAN", null);
        } else {
            b(edrVar);
            hashMap.put("SECTION_HEALTH_DICT_BEAN", d());
        }
    }

    private void b(edr edrVar) {
        if (edrVar == null || edrVar.t() == 0) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "setActiveRecordData data is null");
            return;
        }
        this.f9731a.d(edrVar.j());
        this.f9731a.n(edrVar.y());
        this.f9731a.l(edrVar.p());
        this.f9731a.b(edrVar.i());
        this.f9731a.c(edrVar.f());
        this.f9731a.m(edrVar.s());
        this.f9731a.g(edrVar.q());
        this.f9731a.b(edrVar.m());
        this.f9731a.a(edrVar.g());
        this.f9731a.d(edrVar.e());
        this.f9731a.e(edrVar.h());
        this.f9731a.e(edrVar.c());
        this.f9731a.d(edrVar.af());
        this.f9731a.b(edrVar.x());
        this.f9731a.c(edrVar.u());
        this.f9731a.k(edrVar.v());
        this.f9731a.t(edrVar.ab());
        this.f9731a.o(edrVar.w());
        this.f9731a.a(edrVar.t());
    }

    private eea d() {
        eea eeaVar = new eea();
        eeaVar.c(this.f9731a.h());
        eeaVar.e(this.f9731a.g());
        a(eeaVar);
        j(eeaVar);
        i(eeaVar);
        b(eeaVar);
        eeaVar.b(this.f9731a.g() > 0.0f || this.f9731a.m());
        final HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        eeaVar.agK_(new View.OnClickListener() { // from class: pii
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRecordThreeCircleProvider.this.dpR_(hashMap, view);
            }
        });
        e(eeaVar);
        return eeaVar;
    }

    public /* synthetic */ void dpR_(Map map, View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        pxp.e(2, 6);
        map.put("type", "2");
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HOME_STEP_CLIMB_2010047.value(), map, 0);
        a(this.f9731a.g() * 10.0f, this.f9731a.j());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(eea eeaVar) {
        AchievePopMessageManager achievePopMessageManager = new AchievePopMessageManager(this.f9731a, BaseApplication.e());
        eea.a aVar = new eea.a();
        aVar.d = achievePopMessageManager.b();
        aVar.e = achievePopMessageManager.c();
        aVar.f11975a = achievePopMessageManager.d();
        aVar.c = achievePopMessageManager.e();
        eeaVar.a(aVar);
    }

    private void b(eea eeaVar) {
        c(eeaVar);
        d(eeaVar);
    }

    private void c(eea eeaVar) {
        eea.d dVar = new eea.d();
        if (this.f9731a.af()) {
            dVar.f11977a = nsf.h(R$string.IDS_settings_steps);
            dVar.e = UnitUtil.e(this.f9731a.y(), 1, 0);
            dVar.d = nsf.h(R$string.IDS_plugin_achievement_report_steps);
            dVar.c = R.drawable._2131430062_res_0x7f0b0aae;
            dVar.b = new View.OnClickListener() { // from class: pij
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActiveRecordThreeCircleProvider.this.dpS_(view);
                }
            };
        } else {
            b(eeaVar, dVar);
        }
        eeaVar.b(dVar);
    }

    public /* synthetic */ void dpS_(View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a(this.f9731a.y(), this.f9731a.j());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(eea eeaVar, eea.d dVar) {
        dVar.f11977a = nsf.h(R$string.IDS_sport_distance);
        double floatValue = new BigDecimal(Float.toString(eeaVar.b())).divide(new BigDecimal("1"), 2, 4).floatValue();
        if (UnitUtil.h()) {
            floatValue = UnitUtil.e(floatValue, 3);
            dVar.d = nsf.h(R$string.IDS_band_data_sport_distance_unit_en);
        } else {
            dVar.d = nsf.h(R$string.IDS_motiontrack_show_sport_unit_km);
        }
        dVar.e = UnitUtil.e(floatValue, 1, 2);
        dVar.c = R.drawable._2131429989_res_0x7f0b0a65;
        dVar.b = new View.OnClickListener() { // from class: pig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRecordThreeCircleProvider.this.dpU_(view);
            }
        };
    }

    public /* synthetic */ void dpU_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a() {
        pxp.a(2);
        pxp.e(2, 5);
        c(this.f9731a.h(), this.f9731a.j());
    }

    private void d(eea eeaVar) {
        eea.d dVar = new eea.d();
        if (this.f9731a.af()) {
            b(eeaVar, dVar);
        } else {
            dVar.f11977a = nsf.h(R$string.IDS_start_track_target_type_calorie);
            dVar.d = nsf.h(R$string.IDS_hw_show_sport_cal_unit);
            dVar.e = UnitUtil.e(this.f9731a.e(), 1, 0);
            dVar.c = R.drawable._2131430004_res_0x7f0b0a74;
            dVar.b = new View.OnClickListener() { // from class: pik
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActiveRecordThreeCircleProvider.this.dpT_(view);
                }
            };
        }
        eeaVar.d(dVar);
    }

    public /* synthetic */ void dpT_(View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void b() {
        pxp.e(2, 4);
        c(this.f9731a.e(), this.f9731a.j());
    }

    private void j(eea eeaVar) {
        eea.b bVar = new eea.b();
        bVar.b = nsf.h(R$string.IDS_active_workout);
        bVar.h = this.f9731a.f();
        bVar.f = this.f9731a.i();
        bVar.i = R.drawable._2131430518_res_0x7f0b0c76;
        bVar.e = new View.OnClickListener() { // from class: pif
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRecordThreeCircleProvider.this.dpX_(view);
            }
        };
        if (this.f9731a.af()) {
            bVar.g = R.drawable._2131430081_res_0x7f0b0ac1;
            bVar.d = new int[]{com.huawei.hwcommonmodel.application.BaseApplication.getContext().getColor(R.color._2131298872_res_0x7f090a38), BaseApplication.e().getColor(R$color.new_time_circle_process_second_color), BaseApplication.e().getColor(R$color.new_time_circle_process_third_color), com.huawei.hwcommonmodel.application.BaseApplication.getContext().getColor(R.color._2131298870_res_0x7f090a36)};
            bVar.c = new int[]{com.huawei.hwcommonmodel.application.BaseApplication.getContext().getColor(R.color._2131298869_res_0x7f090a35), com.huawei.hwcommonmodel.application.BaseApplication.getContext().getColor(R.color._2131298868_res_0x7f090a34)};
        } else {
            bVar.g = R.drawable._2131430042_res_0x7f0b0a9a;
            bVar.d = new int[]{com.huawei.hwcommonmodel.application.BaseApplication.getContext().getColor(R.color._2131299158_res_0x7f090b56), com.huawei.hwcommonmodel.application.BaseApplication.getContext().getColor(R.color._2131299157_res_0x7f090b55)};
            bVar.c = new int[]{BaseApplication.e().getColor(R.color._2131299156_res_0x7f090b54), BaseApplication.e().getColor(R.color._2131299155_res_0x7f090b53)};
        }
        e(bVar, R.plurals.IDS_single_circle_intensity_target_desc);
        eeaVar.e(1, bVar);
    }

    public /* synthetic */ void dpX_(View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        pxp.a(4);
        pxp.e(2, 2);
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) FitnessSportIntensityDetailActivity.class);
        intent.putExtra("today_current_middle_and_high_total", this.f9731a.i());
        intent.putExtra("default_time_millis", this.f9731a.j());
        gnm.aPB_(BaseApplication.e(), intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i(eea eeaVar) {
        eea.b bVar = new eea.b();
        bVar.b = nsf.h(R$string.IDS_three_circle_card_activity_hours);
        bVar.h = this.f9731a.q();
        bVar.f = this.f9731a.s();
        bVar.i = R.drawable._2131429943_res_0x7f0b0a37;
        bVar.e = new View.OnClickListener() { // from class: pih
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActiveRecordThreeCircleProvider.this.dpY_(view);
            }
        };
        if (this.f9731a.af()) {
            bVar.g = R.drawable._2131429980_res_0x7f0b0a5c;
            bVar.d = new int[]{BaseApplication.e().getColor(R.color._2131298856_res_0x7f090a28), BaseApplication.e().getColor(R$color.new_activity_circle_process_second_color), BaseApplication.e().getColor(R$color.new_activity_circle_process_third_color), BaseApplication.e().getColor(R.color._2131298854_res_0x7f090a26)};
            bVar.c = new int[]{BaseApplication.e().getColor(R.color._2131299256_res_0x7f090bb8), BaseApplication.e().getColor(R.color._2131299255_res_0x7f090bb7)};
        } else {
            bVar.g = R.drawable._2131430050_res_0x7f0b0aa2;
            bVar.d = new int[]{BaseApplication.e().getColor(R.color._2131296457_res_0x7f0900c9), BaseApplication.e().getColor(R.color._2131296456_res_0x7f0900c8)};
            bVar.c = new int[]{BaseApplication.e().getColor(R.color._2131296455_res_0x7f0900c7), BaseApplication.e().getColor(R.color._2131296454_res_0x7f0900c6)};
        }
        e(bVar, R.plurals._2130903199_res_0x7f03009f);
        eeaVar.e(2, bVar);
    }

    public /* synthetic */ void dpY_(View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            pxp.a(3);
            pxp.e(2, 3);
            KnitActiveHoursActivity.e(BaseApplication.e(), this.f9731a.j());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void e(eea.b bVar, int i) {
        bVar.f11976a = eej.agX_(BaseApplication.e(), eej.d(bVar.f), nsf.a(i, bVar.f, nsf.b(R$string.IDS_current_total_time, eej.d(bVar.f), eej.d(bVar.h))));
    }

    private void a(eea eeaVar) {
        eea.b bVar = new eea.b();
        if (this.f9731a.af()) {
            bVar.h = this.f9731a.c();
            bVar.f = this.f9731a.e();
            bVar.b = nsf.h(R$string.IDS_active_caloric);
            bVar.g = R.drawable._2131429984_res_0x7f0b0a60;
            bVar.i = R.drawable._2131430085_res_0x7f0b0ac5;
            bVar.e = new View.OnClickListener() { // from class: pil
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActiveRecordThreeCircleProvider.this.dpV_(view);
                }
            };
            bVar.d = new int[]{BaseApplication.e().getColor(R.color._2131298862_res_0x7f090a2e), BaseApplication.e().getColor(R$color.new_cal_circle_process_second_color), BaseApplication.e().getColor(R$color.new_cal_circle_process_third_color), BaseApplication.e().getColor(R.color._2131298860_res_0x7f090a2c)};
            bVar.c = new int[]{BaseApplication.e().getColor(R.color._2131297705_res_0x7f0905a9), BaseApplication.e().getColor(R.color._2131297704_res_0x7f0905a8)};
            e(bVar, R.plurals._2130903380_res_0x7f030154);
        } else {
            bVar.g = R.drawable._2131429980_res_0x7f0b0a5c;
            bVar.h = this.f9731a.p();
            bVar.f = this.f9731a.y();
            bVar.b = nsf.h(R$string.IDS_settings_steps);
            bVar.i = R.drawable._2131430495_res_0x7f0b0c5f;
            bVar.e = new View.OnClickListener() { // from class: pip
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActiveRecordThreeCircleProvider.this.dpW_(view);
                }
            };
            bVar.d = new int[]{BaseApplication.e().getColor(R.color._2131299154_res_0x7f090b52), BaseApplication.e().getColor(R.color._2131299153_res_0x7f090b51)};
            bVar.c = new int[]{BaseApplication.e().getColor(R.color._2131299152_res_0x7f090b50), BaseApplication.e().getColor(R.color._2131299151_res_0x7f090b4f)};
            e(bVar, R.plurals._2130903205_res_0x7f0300a5);
        }
        eeaVar.e(0, bVar);
    }

    public /* synthetic */ void dpV_(View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void dpW_(View view) {
        if (nsn.o()) {
            LogUtil.a("SCUI_ActiveRecordThreeCircleProvider", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a(this.f9731a.y(), this.f9731a.j());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c(int i, long j) {
        ReleaseLogUtil.e("SCUI_ActiveRecordThreeCircleProvider", "click into calories = ", Integer.valueOf(i));
        pxp.a();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(BaseApplication.e(), FitnessCalorieDetailActivity.class);
        intent.putExtra("bundle_key_data", bundle);
        intent.putExtra("today_current_colories_total", i * 1000);
        intent.putExtra("default_time_millis", j);
        gnm.aPB_(BaseApplication.e(), intent);
        if (BaseApplication.wa_() != null) {
            BaseApplication.wa_().overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }

    private void c(float f, long j) {
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) FitnessDistanceDetailActivity.class);
        intent.putExtra("today_current_distance_total", (int) (f * 1000.0f));
        intent.putExtra("default_time_millis", j);
        gnm.aPB_(BaseApplication.e(), intent);
        if (BaseApplication.wa_() != null) {
            BaseApplication.wa_().overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }

    private void a(int i, long j) {
        pxp.a(1);
        pxp.e(2, 1);
        StepModuleChartStorageHelper.e(true);
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) FitnessStepDetailActivity.class);
        intent.putExtra("today_current_steps_total", i);
        intent.putExtra("default_time_millis", j);
        gnm.aPB_(BaseApplication.e(), intent);
        if (BaseApplication.wa_() != null) {
            BaseApplication.wa_().overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }

    private void a(float f, long j) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(BaseApplication.e(), FitnessClimbDetailActivity.class);
        intent.putExtra("bundle_key_data", bundle);
        intent.putExtra("today_current_climb_total", (int) f);
        intent.putExtra("default_time_millis", j);
        gnm.aPB_(BaseApplication.e(), intent);
        if (BaseApplication.wa_() != null) {
            BaseApplication.wa_().overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }
}
