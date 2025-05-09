package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.util.Pair;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarDietMonitorCardView;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryRepository;
import defpackage.qaa;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class BloodSugarDietMonitorCardView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9958a;
    private View b;
    private HealthTextView c;
    private HealthTextView d;
    private final Context e;
    private ImageView f;
    private HealthTextView g;
    private ImageView h;
    private HealthTextView i;
    private HealthDivider j;
    private View k;
    private LinearLayout o;

    public BloodSugarDietMonitorCardView(Context context) {
        super(context);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.width = -1;
            layoutParams2.height = -1;
        }
        setOrientation(1);
        this.e = context;
    }

    public void d(qaa qaaVar, List<Pair<Long, Float>> list, long j, long j2, float[] fArr) {
        LogUtil.a("BloodSugarDietMonitorCardView", "data size=", Integer.valueOf(list.size()));
        e();
        b(list, j, j2, fArr);
        setInfoView(qaaVar);
    }

    private void e() {
        LayoutInflater.from(this.e).inflate(R.layout.layout_blood_sugar_diet_monitor_card_view, this);
        this.k = findViewById(R.id.item_diet_analysis_heat);
        this.o = (LinearLayout) findViewById(R.id.line_chart_container);
        this.f = (ImageView) findViewById(R.id.blood_sugar_heat_diet_img);
        this.f9958a = (HealthTextView) findViewById(R.id.blood_sugar_diet_title);
        this.d = (HealthTextView) findViewById(R.id.blood_sugar_diet_calorie);
        this.c = (HealthTextView) findViewById(R.id.blood_sugar_diet_carbohydrates);
        this.b = findViewById(R.id.blood_sugar_diet_highGi_view);
        this.j = (HealthDivider) findViewById(R.id.divider_last);
        this.h = (ImageView) findViewById(R.id.blood_sugar_food_diet_img);
        this.i = (HealthTextView) findViewById(R.id.blood_sugar_diet_highGi_title);
        this.g = (HealthTextView) findViewById(R.id.blood_sugar_diet_highGi_food);
    }

    private void b(List<Pair<Long, Float>> list, long j, long j2, float[] fArr) {
        this.o.addView(d(j, j2, list, fArr), -1, -1);
    }

    private void setInfoView(final qaa qaaVar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(qaaVar.m() * 1000);
        c(qaaVar, (HealthTextView) findViewById(R.id.text_title), UnitUtil.c(calendar, 1), UnitUtil.e(2.0d, 1, 0));
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.text_content);
        if (BigDecimal.valueOf(qaaVar.j()).setScale(0, 1).compareTo(BigDecimal.valueOf(qaaVar.j())) == 0) {
            healthTextView.setText(UnitUtil.e(qaaVar.j(), 2, 0));
        } else {
            healthTextView.setText(UnitUtil.e(qaaVar.j(), 2, 1));
        }
        if (LanguageUtil.be(this.e) || LanguageUtil.p(this.e)) {
            this.d.setText(String.format(Locale.ROOT, "%s%s", UnitUtil.e(qaaVar.c(), 1, 1), " " + this.e.getString(R$string.IDS_hw_show_sport_cal_unit)));
        } else {
            this.d.setText(String.format(Locale.ROOT, "%s%s", UnitUtil.e(qaaVar.c(), 1, 1), this.e.getString(R$string.IDS_hw_show_sport_cal_unit)));
        }
        this.c.setText(this.e.getResources().getQuantityString(R.plurals._2130903246_res_0x7f0300ce, (int) qaaVar.a(), Integer.valueOf((int) qaaVar.a())));
        this.k.setOnClickListener(new View.OnClickListener() { // from class: pze
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarDietMonitorCardView.this.dvR_(qaaVar, view);
            }
        });
        if (!LanguageUtil.m(this.e) || qaaVar.h().isEmpty()) {
            this.b.setVisibility(8);
            this.j.setVisibility(8);
        } else {
            this.g.setText(qaaVar.h());
        }
    }

    public /* synthetic */ void dvR_(qaa qaaVar, View view) {
        String str = "#/breakfast_detail?whichMeal=" + qaaVar.k() + "&whichMealLabel=" + ((Object) this.f9958a.getText()) + "&date=" + (HiDateUtil.t(qaaVar.m() * 1000) / 1000) + "&eatTime=" + qaaVar.f();
        LogUtil.a("BloodSugarDietMonitorCardView", "jumpDietDetails uri = ", str);
        DietDiaryRepository.jumpDietDetails(this.e, str);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(qaa qaaVar, HealthTextView healthTextView, String str, String str2) {
        int k = qaaVar.k();
        if (k == 10) {
            healthTextView.setText(this.e.getString(R$string.IDS_diet_percentage1, 2));
            this.f9958a.setText(this.e.getString(R$string.IDS_bsdiet_breakfast));
            this.i.setText(this.e.getString(R$string.IDS_bsdiet_highgi_breakfast));
            this.f.setImageResource(R.drawable._2131429764_res_0x7f0b0984);
            this.h.setImageResource(R.drawable._2131429764_res_0x7f0b0984);
            return;
        }
        if (k == 11) {
            healthTextView.setText(this.e.getString(R$string.IDS_other_diet_percentage1, str, str2));
            this.f9958a.setText(this.e.getString(R$string.IDS_bsdiet_breakfast_extra));
            this.i.setText(this.e.getString(R$string.IDS_bsdiet_highgi_breakfast));
            this.f.setImageResource(R.drawable._2131429764_res_0x7f0b0984);
            this.h.setImageResource(R.drawable._2131429764_res_0x7f0b0984);
            return;
        }
        if (k == 20) {
            healthTextView.setText(this.e.getString(R$string.IDS_diet_percentage2, 2));
            this.f9958a.setText(this.e.getString(R$string.IDS_bsdiet_lunch));
            this.i.setText(this.e.getString(R$string.IDS_bsdiet_highgi_lunch));
            this.f.setImageResource(R.drawable._2131429767_res_0x7f0b0987);
            this.h.setImageResource(R.drawable._2131429767_res_0x7f0b0987);
            return;
        }
        if (k == 21) {
            healthTextView.setText(this.e.getString(R$string.IDS_other_diet_percentage2, str, str2));
            this.f9958a.setText(this.e.getString(R$string.IDS_bsdiet_afternoon_extra));
            this.i.setText(this.e.getString(R$string.IDS_bsdiet_highgi_lunch));
            this.f.setImageResource(R.drawable._2131429767_res_0x7f0b0987);
            this.h.setImageResource(R.drawable._2131429767_res_0x7f0b0987);
            return;
        }
        if (k == 30) {
            healthTextView.setText(this.e.getString(R$string.IDS_diet_percentage3, 2));
            this.f9958a.setText(this.e.getString(R$string.IDS_bsdiet_dinner));
            this.i.setText(this.e.getString(R$string.IDS_bsdiet_highgi_dinner));
            this.f.setImageResource(R.drawable._2131429766_res_0x7f0b0986);
            this.h.setImageResource(R.drawable._2131429766_res_0x7f0b0986);
            return;
        }
        if (k == 31) {
            healthTextView.setText(this.e.getString(R$string.IDS_other_diet_percentage3, str, str2));
            this.f9958a.setText(this.e.getString(R$string.IDS_bsdiet_dinner_extra));
            this.i.setText(this.e.getString(R$string.IDS_bsdiet_highgi_dinner));
            this.f.setImageResource(R.drawable._2131429766_res_0x7f0b0986);
            this.h.setImageResource(R.drawable._2131429766_res_0x7f0b0986);
            return;
        }
        healthTextView.setText("");
        this.f9958a.setText("");
        LogUtil.h("BloodSugarDietMonitorCardView", "setDietTypeText no support mDietType");
    }

    private BloodSugarDietMonitorCharView d(long j, long j2, List<Pair<Long, Float>> list, float[] fArr) {
        BloodSugarDietMonitorCharView bloodSugarDietMonitorCharView = new BloodSugarDietMonitorCharView(this.e);
        bloodSugarDietMonitorCharView.setStartTime(j);
        bloodSugarDietMonitorCharView.setEndTime(j2);
        bloodSugarDietMonitorCharView.setData(list, fArr);
        return bloodSugarDietMonitorCharView;
    }
}
