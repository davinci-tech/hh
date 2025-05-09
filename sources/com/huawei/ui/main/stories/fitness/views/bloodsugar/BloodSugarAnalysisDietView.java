package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDiffActivity;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository;
import defpackage.gge;
import defpackage.gnm;
import defpackage.nsn;
import defpackage.pzy;
import defpackage.qaa;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import health.compact.a.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class BloodSugarAnalysisDietView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private pzy f9953a;
    private HealthTextView b;
    private Context c;
    private HealthTextView d;
    private ImageView e;
    private ImageView f;
    private LinearLayout g;
    private HealthTextView h;
    private int i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private LinearLayout m;
    private RelativeLayout n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private long r;
    private ImageView s;
    private HealthDivider t;
    private int v;
    private RelativeLayout y;

    public BloodSugarAnalysisDietView(Context context) {
        this(context, null);
    }

    public BloodSugarAnalysisDietView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarAnalysisDietView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
        a();
    }

    private void a() {
        View inflate;
        Context context = this.c;
        if (context == null) {
            return;
        }
        LayoutInflater from = LayoutInflater.from(context);
        if (nsn.r()) {
            inflate = from.inflate(R.layout.health_data_blood_sugar_analysis_diet_large, this);
        } else {
            inflate = from.inflate(R.layout.health_data_blood_sugar_analysis_diet, this);
        }
        this.m = (LinearLayout) inflate.findViewById(R.id.blood_sugar_difference_view);
        this.o = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diff_num);
        this.k = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diff_des);
        this.l = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diff_status_text);
        this.g = (LinearLayout) inflate.findViewById(R.id.blood_sugar_diet_root);
        this.n = (RelativeLayout) inflate.findViewById(R.id.blood_sugar_diet_view);
        this.j = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diet_title);
        this.b = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diet_calorie);
        this.d = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diet_carbohydrates);
        this.f = (ImageView) inflate.findViewById(R.id.blood_sugar_diet_img);
        this.s = (ImageView) inflate.findViewById(R.id.blood_sugar_gi_img);
        this.e = (ImageView) inflate.findViewById(R.id.blood_sugar_diet_carbohydrates_icon);
        this.t = (HealthDivider) inflate.findViewById(R.id.blood_sugar_diet_divide);
        this.h = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diet_description);
        this.y = (RelativeLayout) inflate.findViewById(R.id.blood_sugar_diet_highGi_view);
        this.q = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diet_highGi_title);
        this.p = (HealthTextView) inflate.findViewById(R.id.blood_sugar_diet_highGi_food);
        this.g.setVisibility(8);
        this.y.setVisibility(8);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
    }

    public void setDietRootVisibility(int i) {
        this.g.setVisibility(i);
    }

    public void setDiffData(qaa qaaVar, pzy pzyVar) {
        if (qaaVar == null) {
            LogUtil.c("BloodSugarDietAnalysisView", "setDietData bean can not null!");
            return;
        }
        this.m.setVisibility(0);
        this.f9953a = pzyVar;
        this.i = qaaVar.d();
        this.o.setText(d(qaaVar.e()));
        setDiffStatusText(qaaVar.g());
        d();
    }

    public void setDietData(qaa qaaVar) {
        if (qaaVar == null) {
            LogUtil.c("BloodSugarDietAnalysisView", "setDietData bean can not null!");
            return;
        }
        float c = qaaVar.c();
        if (c == 0.0f) {
            this.g.setVisibility(8);
            return;
        }
        this.g.setVisibility(0);
        this.i = qaaVar.d();
        this.v = qaaVar.k();
        this.r = qaaVar.f();
        c();
        this.b.setText(String.format("%s%s", Float.valueOf(c), this.c.getString(R$string.IDS_hw_show_sport_cal_unit)));
        if (qaaVar.a() == 0.0f) {
            LogUtil.d("BloodSugarDietAnalysisView", "Carbohydrates is 0");
        }
        this.d.setVisibility(0);
        this.e.setVisibility(0);
        this.d.setText(this.c.getResources().getQuantityString(R.plurals._2130903246_res_0x7f0300ce, (int) qaaVar.a(), Integer.valueOf((int) qaaVar.a())));
        if (StringUtils.g(qaaVar.b())) {
            this.t.setVisibility(8);
            this.h.setVisibility(8);
            this.n.setPadding(0, 0, 0, 4);
        } else {
            this.h.setText(qaaVar.b());
            this.h.setVisibility(0);
            this.t.setVisibility(0);
            this.n.setPadding(0, 0, 0, 0);
        }
        if (!LanguageUtil.m(this.c) || qaaVar.h().isEmpty()) {
            this.y.setVisibility(8);
            this.h.setPadding(0, 0, 0, 4);
        } else {
            this.y.setVisibility(0);
            this.h.setPadding(0, 0, 0, 0);
            this.p.setText(qaaVar.h());
        }
    }

    private void d() {
        int i = this.i;
        if (i == 0) {
            this.k.setText(this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_breakfast_difference));
            return;
        }
        if (i == 1) {
            this.k.setText(this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_lunch_difference));
        } else if (i == 2) {
            this.k.setText(this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_dinner_difference));
        } else {
            LogUtil.c("BloodSugarDietAnalysisView", "setDiffTypeText no support mDietType");
        }
    }

    private void c() {
        int i = this.i;
        if (i == 0) {
            this.j.setText(this.c.getString(R$string.IDS_bsdiet_breakfast));
            this.q.setText(this.c.getString(R$string.IDS_bsdiet_highgi_breakfast));
            this.f.setImageResource(R.drawable._2131429764_res_0x7f0b0984);
            this.s.setImageResource(R.drawable._2131429764_res_0x7f0b0984);
        } else if (i == 1) {
            this.j.setText(this.c.getString(R$string.IDS_bsdiet_lunch));
            this.q.setText(this.c.getString(R$string.IDS_bsdiet_highgi_lunch));
            this.f.setImageResource(R.drawable._2131429767_res_0x7f0b0987);
            this.s.setImageResource(R.drawable._2131429767_res_0x7f0b0987);
        } else if (i == 2) {
            this.j.setText(this.c.getString(R$string.IDS_bsdiet_dinner));
            this.q.setText(this.c.getString(R$string.IDS_bsdiet_highgi_dinner));
            this.f.setImageResource(R.drawable._2131429766_res_0x7f0b0986);
            this.s.setImageResource(R.drawable._2131429766_res_0x7f0b0986);
        } else {
            LogUtil.c("BloodSugarDietAnalysisView", "setDietTypeText no support mDietType");
        }
        b(this.i, AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_FEED_FINGER_2040137.value());
    }

    private void b(int i, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("click", "1");
        gge.e(str, hashMap);
    }

    private void setDiffStatusText(int i) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 1001:
                        b(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_low, R.color._2131296797_res_0x7f09021d);
                        break;
                    case 1002:
                        b(R$string.IDS_hw_health_show_healthdata_status_low, R.color._2131296797_res_0x7f09021d);
                        break;
                    case 1003:
                        break;
                    case 1004:
                        break;
                    case 1005:
                        b(R$string.IDS_hw_show_healthdata_bloodsugar_status_high, R.color._2131296795_res_0x7f09021b);
                        break;
                    case 1006:
                        b(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_high, R.color._2131296795_res_0x7f09021b);
                        break;
                    default:
                        LogUtil.c("BloodSugarDietAnalysisView", "no support blood type");
                        break;
                }
            }
            b(R$string.IDS_hw_health_show_healthdata_status_high, R.color._2131296795_res_0x7f09021b);
            return;
        }
        b(R$string.IDS_hw_health_show_healthdata_status_normal, R.color._2131296799_res_0x7f09021f);
    }

    private void b(int i, int i2) {
        this.l.setText(getResources().getString(i));
        this.l.setTextColor(getResources().getColor(i2));
    }

    private String d(float f) {
        return UnitUtil.e(f, 1, 1) + this.c.getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol);
    }

    private void b() {
        Intent intent = new Intent(this.c, (Class<?>) BloodSugarDiffActivity.class);
        int i = this.i;
        if (i == 0) {
            intent.putExtra("bloodSugar_diff_title", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_breakfast_difference));
            intent.putExtra("bloodSugar_diff_befor_message", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast));
            intent.putExtra("bloodSugar_diff_after_message", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast));
        } else if (i == 1) {
            intent.putExtra("bloodSugar_diff_title", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_lunch_difference));
            intent.putExtra("bloodSugar_diff_befor_message", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch));
            intent.putExtra("bloodSugar_diff_after_message", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch));
        } else if (i == 2) {
            intent.putExtra("bloodSugar_diff_title", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_dinner_difference));
            intent.putExtra("bloodSugar_diff_befor_message", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner));
            intent.putExtra("bloodSugar_diff_after_message", this.c.getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner));
        } else {
            LogUtil.c("BloodSugarDietAnalysisView", "startActivityWithParams param can not null");
            return;
        }
        pzy pzyVar = this.f9953a;
        if (pzyVar == null || pzyVar.e() == null || this.f9953a.d() == null) {
            LogUtil.c("BloodSugarDietAnalysisView", "startActivityWithParams model , beforeModel or afterModel can not null");
            return;
        }
        pzy e = this.f9953a.e();
        pzy d = this.f9953a.d();
        intent.putExtra("bloodSugar_diff_time", a(Long.valueOf(this.f9953a.k()), true));
        intent.putExtra("bloodSugar_diff_number", this.f9953a.a());
        intent.putExtra("bloodSugar_diff_status", this.f9953a.j());
        intent.putExtra("bloodSugar_diff_befor_title", e.a());
        intent.putExtra("bloodSugar_diff_befor_status", e.j());
        intent.putExtra("bloodSUgar_diff_befor_time", a(Long.valueOf(e.k()), false));
        intent.putExtra("bloodSugar_diff_after_title", d.a());
        intent.putExtra("bloodSugar_diff_after_status", d.j());
        intent.putExtra("bloodSugar_diff_after_time", a(Long.valueOf(d.k()), false));
        gnm.aPB_(this.c, intent);
    }

    private String a(Long l, boolean z) {
        return new SimpleDateFormat(z ? "yyyy/MM/dd HH:mm" : "HH:mm").format(l);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String string;
        if (view.getId() == R.id.blood_sugar_difference_view) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            hashMap.put("type", String.valueOf(2));
            hashMap.put(FunctionSetBeanReader.BI_ELEMENT, String.valueOf(11));
            gge.e(AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value(), hashMap);
            b(1, AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_DIE_2040135.value());
            b();
        }
        if (view == this.n) {
            int i = this.i;
            if (i == 0) {
                string = this.c.getString(R$string.IDS_bsdiet_breakfast);
            } else if (i == 1) {
                string = this.c.getString(R$string.IDS_bsdiet_lunch);
            } else if (i == 2) {
                string = this.c.getString(R$string.IDS_bsdiet_dinner);
            } else {
                LogUtil.c("BloodSugarDietAnalysisView", "onClick other mDietType ", Integer.valueOf(i));
                string = "";
            }
            String str = "#/breakfast_detail?whichMeal=" + this.v + "&whichMealLabel=" + string + "&date=" + (TimeUtil.d(this.r * 1000) / 1000) + "&eatTime=" + this.r;
            LogUtil.d("BloodSugarDietAnalysisView", "jumpDietDetails uri = ", str);
            b(0, AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_DIE_2040135.value());
            DietDiaryRepository.jumpDietDetails(this.c, str);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
