package com.huawei.ui.main.stories.fitness.activity.pressuremeasure;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class PressureIntroduceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9880a;
    private HealthTextView b;
    private Context c;
    private HealthTextView d;
    private HealthTextView e;
    private String f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pressure_introduce);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, findViewById(R.id.pressure_introduce_ll));
        this.c = this;
        b();
        e();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.pressure_introduce_content_one_one);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.pressure_introduce_content_one_two);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.pressure_introduce_content_second_one);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.pressure_introduce_content_second_two);
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.pressure_introduce_content_second_three);
        healthTextView.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_frist_detail));
        healthTextView2.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_frist_content_one));
        healthTextView3.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_second_detail));
        healthTextView4.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_second_content_one));
        healthTextView5.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_second_content_two));
        a();
        c();
    }

    private void b() {
        this.h = (HealthTextView) findViewById(R.id.pressure_introduce_title_one);
        this.g = (HealthTextView) findViewById(R.id.pressure_introduce_title_second);
        this.j = (HealthTextView) findViewById(R.id.pressure_introduce_title_third);
        this.i = (HealthTextView) findViewById(R.id.pressure_introduce_title_fourth);
        this.f9880a = (HealthTextView) findViewById(R.id.pressure_advice_summarize_introduce_fourth_content1);
        this.b = (HealthTextView) findViewById(R.id.pressure_advice_summarize_introduce_fourth_content2);
        this.d = (HealthTextView) findViewById(R.id.pressure_advice_summarize_introduce_fourth_content3);
        this.e = (HealthTextView) findViewById(R.id.pressure_advice_summarize_introduce_fourth_content4);
    }

    private void e() {
        this.m = UnitUtil.e(1.0d, 1, 0);
        this.n = UnitUtil.e(2.0d, 1, 0);
        this.o = UnitUtil.e(3.0d, 1, 0);
        this.f = UnitUtil.e(4.0d, 1, 0);
        this.y = UnitUtil.e(2011.0d, 1, 0);
        this.x = UnitUtil.e(2015.0d, 1, 0);
        this.w = UnitUtil.e(1983.0d, 1, 0);
        this.r = UnitUtil.e(71.0d, 1, 0);
        this.p = UnitUtil.e(385.0d, 1, 0);
        this.q = UnitUtil.e(396.0d, 1, 0);
        this.v = UnitUtil.e(97.0d, 1, 0);
        this.u = UnitUtil.e(80.0d, 1, 0);
        this.k = UnitUtil.e(108.0d, 1, 0);
        this.t = UnitUtil.e(370.0d, 1, 0);
        this.s = UnitUtil.e(377.0d, 1, 0);
        this.l = UnitUtil.e(36.0d, 1, 0);
    }

    private void a() {
        this.h.setText(String.format(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_frist_title), 1));
        this.g.setText(String.format(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_second_title), 2));
        this.j.setText(String.format(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_third_title), 3));
        this.i.setText(String.format(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_fourth_title), 4));
    }

    private void c() {
        this.f9880a.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_fourth_content1, this.m, this.y, this.v, this.k));
        this.b.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_fourth_content2, this.n, this.w, this.p, this.q));
        this.d.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_fourth_content3, this.o, this.y, this.l, this.r, this.u));
        this.e.setText(this.c.getString(R$string.IDS_hw_pressure_advice_advice_summarize_introduce_fourth_content4, this.f, this.x, this.t, this.s));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
