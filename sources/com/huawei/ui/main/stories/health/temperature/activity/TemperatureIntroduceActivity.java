package com.huawei.ui.main.stories.health.temperature.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.nsn;
import defpackage.qpr;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class TemperatureIntroduceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10234a;
    private View aa;
    private String ab;
    private String ac;
    private String b;
    private String c;
    private Context d;
    private String e;
    private HealthTextView f;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private boolean p;
    private String q;
    private boolean r;
    private HealthTextView s;
    private String t;
    private String u;
    private String v;
    private String w;
    private HealthTextView x;
    private String y;
    private String z;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_temperature_introduce);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, findViewById(R.id.temperature_introduce_ll));
        this.d = this;
        this.r = Utils.o();
        this.p = UnitUtil.d();
        e();
        a();
        c();
        if (this.r) {
            h();
        } else {
            i();
        }
    }

    private void e() {
        this.f = (HealthTextView) findViewById(R.id.temperature_introduce_content_one);
        this.k = (HealthTextView) findViewById(R.id.temperature_introduce_content_two);
        this.j = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_one);
        this.m = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_two);
        this.n = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_three);
        this.o = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_four);
        this.i = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_five);
        this.l = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_six);
        this.h = (HealthTextView) findViewById(R.id.temperature_introduce_content_three_seven);
        if (LanguageUtil.bm(this.d)) {
            CardConstants.e(this.f);
            CardConstants.e(this.k);
            CardConstants.e(this.j);
            CardConstants.e(this.m);
            CardConstants.e(this.n);
            CardConstants.e(this.o);
            CardConstants.e(this.i);
            CardConstants.e(this.l);
            CardConstants.e(this.h);
        }
        this.g = (ImageView) findViewById(R.id.iv_four);
        if (nsn.t()) {
            findViewById(R.id.temperature_explain_icon).setVisibility(8);
            View inflate = ((ViewStub) findViewById(R.id.temperature_explain_icon_large_view_sub)).inflate();
            this.aa = inflate.findViewById(R.id.temperature_explain_icon_large);
            this.s = (HealthTextView) inflate.findViewById(R.id.temperature_less_than);
            this.x = (HealthTextView) inflate.findViewById(R.id.temperature_normal_range);
            this.f10234a = (HealthTextView) inflate.findViewById(R.id.temperature_more_than);
            return;
        }
        this.aa = findViewById(R.id.temperature_explain_icon);
        this.s = (HealthTextView) findViewById(R.id.temperature_less_than);
        this.x = (HealthTextView) findViewById(R.id.temperature_normal_range);
        this.f10234a = (HealthTextView) findViewById(R.id.temperature_more_than);
    }

    private void h() {
        this.f.setVisibility(8);
        this.i.setVisibility(8);
        this.g.setVisibility(8);
        this.aa.setVisibility(8);
        this.k.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_02, this.ac, this.ab));
        this.j.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_05));
        this.m.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_10, UnitUtil.e(10.0d, 1, 0)));
        this.n.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_08));
        this.o.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_04));
        this.l.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_07));
    }

    private void i() {
        String string = this.d.getString(R$string.IDS_hw_temperature_note_detail_11, this.v, this.y, this.q, this.z);
        this.f.setText(string);
        String string2 = this.d.getString(R$string.IDS_hw_temperature_note_detail_02, this.ac, this.ab);
        this.k.setText(string2);
        this.j.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_05));
        this.m.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_09, UnitUtil.e(10.0d, 1, 0), this.u));
        this.n.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_08));
        this.o.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_04));
        this.i.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_03, this.c, this.e));
        this.l.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_07));
        this.h.setText(this.d.getString(R$string.IDS_hw_temperature_note_detail_06, UnitUtil.e(30.0d, 1, 0)));
        this.s.setText(this.t);
        this.x.setText(this.w);
        this.f10234a.setText(this.b);
        if (LanguageUtil.y(this.d)) {
            a(string, this.f);
            a(string2, this.k);
            a(this.t, this.s);
            a(this.w, this.x);
            a(this.b, this.f10234a);
        }
        if (LanguageUtil.ab(this.d)) {
            HealthTextView healthTextView = this.s;
            healthTextView.setTextSize(0, healthTextView.getTextSize() * 0.7f);
            HealthTextView healthTextView2 = this.x;
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() * 0.7f);
            HealthTextView healthTextView3 = this.f10234a;
            healthTextView3.setTextSize(0, healthTextView3.getTextSize() * 0.7f);
        }
    }

    private void a() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.temperature_introduce_title_one);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.temperature_introduce_title_two);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.temperature_introduce_title_three);
        if (this.r) {
            healthTextView.setVisibility(8);
            healthTextView2.setText(getString(R$string.IDS_health_skin_temperature));
            healthTextView3.setText(getString(R$string.IDS_hw_temperature_note_third_title));
        } else {
            healthTextView.setText(getString(R$string.IDS_settings_health_temperature));
            healthTextView2.setText(getString(R$string.IDS_health_skin_temperature));
            healthTextView3.setText(getString(R$string.IDS_hw_temperature_note_third_title));
        }
    }

    private void c() {
        if (this.p) {
            b();
        } else {
            d();
        }
    }

    private void b() {
        String e = UnitUtil.e(37.20000076293945d, 1, 1);
        String e2 = UnitUtil.e(38.0d, 1, 1);
        this.t = getString(R$string.IDS_temperature_less_than, new Object[]{e});
        this.q = getString(R$string.IDS_temperature_more_than, new Object[]{e});
        this.w = getString(R$string.IDS_temperature_normal_range, new Object[]{e, e2});
        this.b = getString(R$string.IDS_temperature_more_than, new Object[]{e2});
        this.v = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(36.0d, 1, 1)});
        this.y = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(37.0d, 1, 1)});
        this.e = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(35.0d, 1, 1)});
        this.z = getString(R$string.IDS_temperature_more_than, new Object[]{e2});
        this.ac = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(32.0d, 1, 1)});
        this.ab = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(34.0d, 1, 1)});
        this.u = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(25.0d, 1, 1)});
        this.c = getString(R$string.IDS_temp_desc_celsius_unit, new Object[]{UnitUtil.e(15.0d, 1, 1)});
    }

    private void d() {
        String e = UnitUtil.e(qpr.c(37.2f), 1, 1);
        String e2 = UnitUtil.e(qpr.c(38.0f), 1, 1);
        this.t = getString(R$string.IDS_temperature_fahrenheit_less, new Object[]{e});
        this.q = getString(R$string.IDS_temperature_fahrenheit_more, new Object[]{e});
        this.w = getString(R$string.IDS_temperature_fahrenheit_normal, new Object[]{e, e2});
        this.b = getString(R$string.IDS_temperature_fahrenheit_more, new Object[]{e2});
        this.v = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(36.0f), 1, 1)});
        this.y = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(37.0f), 1, 1)});
        this.e = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(35.0f), 1, 1)});
        this.z = getString(R$string.IDS_temperature_fahrenheit_more, new Object[]{e2});
        this.ac = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(32.0f), 1, 1)});
        this.ab = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(34.0f), 1, 1)});
        this.u = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(25.0f), 1, 1)});
        this.c = getString(R$string.IDS_temp_desc_fahrenheit_unit, new Object[]{UnitUtil.e(qpr.c(15.0f), 1, 1)});
    }

    private void a(String str, HealthTextView healthTextView) {
        if (str != null) {
            healthTextView.setText(str.replace("٫", "."));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
