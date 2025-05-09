package com.huawei.ui.main.stories.settings.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes7.dex */
public class InstructionOfMaxHeartRateActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10455a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private RelativeLayout g;
    private HealthTextView h;
    private HealthTextView i;
    private CustomTitleBar j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView s;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("InstructionOfMaxHeartRateActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.activity_instruction_max_heart_rate);
        this.f10455a = getApplicationContext();
        c();
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("isPaceRange", false)) {
            d();
        } else {
            a();
        }
    }

    private void c() {
        this.j = (CustomTitleBar) findViewById(R.id.max_heart_rate_title_layout);
        this.h = (HealthTextView) nsy.cMc_(this, R.id.rate_zone_calculation_des);
        this.g = (RelativeLayout) findViewById(R.id.rate_zone_non_sport);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.des_title0);
        this.s = (HealthTextView) nsy.cMc_(this, R.id.des_title1);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.des_title2);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.des_title3);
        this.d = (HealthTextView) nsy.cMc_(this, R.id.des_title4);
        this.f = (HealthTextView) nsy.cMc_(this, R.id.des_title5);
        this.k = (HealthTextView) nsy.cMc_(this, R.id.des_context0);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.des_context1);
        this.o = (HealthTextView) nsy.cMc_(this, R.id.des_context2);
        this.b = (HealthTextView) nsy.cMc_(this, R.id.des_context3);
        this.e = (HealthTextView) nsy.cMc_(this, R.id.des_context4);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.des_context5);
    }

    private void a() {
        this.j.setTitleText(getString(R$string.IDS_main_max_heart_rate_percentage));
        this.h.setVisibility(8);
        String format = String.format(Locale.ENGLISH, this.f10455a.getResources().getString(R$string.IDS_rate_zone_des_context5), UnitUtil.e(50.0d, 2, 0), "～ " + UnitUtil.e(60.0d, 2, 0));
        String format2 = String.format(Locale.ENGLISH, this.f10455a.getResources().getString(R$string.IDS_rate_zone_des_context4), UnitUtil.e(60.0d, 2, 0), "～ " + UnitUtil.e(70.0d, 2, 0));
        this.m.setText(format);
        this.o.setText(format2);
        this.b.setText(String.format(Locale.ENGLISH, this.f10455a.getResources().getString(R$string.IDS_rate_zone_des_context3), UnitUtil.e(70.0d, 2, 0), "～ " + UnitUtil.e(80.0d, 2, 0)));
        this.e.setText(String.format(Locale.ENGLISH, this.f10455a.getResources().getString(R$string.IDS_rate_zone_des_context2), UnitUtil.e(80.0d, 2, 0), "～ " + UnitUtil.e(90.0d, 2, 0)));
        this.i.setText(String.format(Locale.ENGLISH, this.f10455a.getResources().getString(R$string.IDS_rate_zone_des_context1), UnitUtil.e(90.0d, 2, 0), "～ " + UnitUtil.e(100.0d, 2, 0)));
        this.l.setText(R$string.IDS_main_watch_detail_max_heart_rate_unsport_interval);
        this.s.setText(this.f10455a.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 1));
        this.n.setText(this.f10455a.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 2));
        this.c.setText(this.f10455a.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 3));
        this.d.setText(this.f10455a.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 4));
        this.f.setText(this.f10455a.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 5));
        this.k.setText(String.format(Locale.ENGLISH, this.f10455a.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_unsport_context), UnitUtil.e(50.0d, 2, 0)));
    }

    private void d() {
        this.j.setTitleText(getString(R$string.IDS_hwh_motiontrack_running_pace_range));
        this.g.setVisibility(8);
        this.k.setVisibility(8);
        this.h.setText(getString(R$string.IDS_pace_explain_title));
        this.s.setText(a(1));
        this.m.setText(getString(R$string.IDS_pace_explain_ragne_one));
        this.n.setText(a(2));
        this.o.setText(getString(R$string.IDS_pace_explain_ragne_two));
        this.c.setText(a(3));
        this.b.setText(getString(R$string.IDS_pace_explain_ragne_three, new Object[]{5, 1}));
        this.d.setText(a(4));
        this.e.setText(getString(R$string.IDS_pace_explain_ragne_four));
        this.f.setText(a(5));
        this.i.setText(getString(R$string.IDS_pace_explain_ragne_five, new Object[]{1, 1}));
    }

    private String a(int i) {
        return getResources().getString(R$string.IDS_pace_range_label_number, Integer.valueOf(i));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
