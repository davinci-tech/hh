package com.huawei.ui.main.stories.settings.activity.heartrate;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes7.dex */
public class InstructionOfHrrHeartRateActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10454a;
    private HealthTextView b;
    private HealthTextView c;
    private Context d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView o;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("InstructionOfHrrHeartRateActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.activity_instruction_hrr_heart_rate);
        this.d = getApplicationContext();
        d();
        c();
    }

    private void d() {
        this.o = (HealthTextView) nsy.cMc_(this, R.id.des_title0);
        this.h = (HealthTextView) nsy.cMc_(this, R.id.des_title1);
        this.g = (HealthTextView) nsy.cMc_(this, R.id.des_title2);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.des_title3);
        this.k = (HealthTextView) nsy.cMc_(this, R.id.des_title4);
        this.j = (HealthTextView) nsy.cMc_(this, R.id.des_title5);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.des_context0);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.des_context1);
        this.e = (HealthTextView) nsy.cMc_(this, R.id.des_context2);
        this.f = (HealthTextView) nsy.cMc_(this, R.id.des_context3);
        this.f10454a = (HealthTextView) nsy.cMc_(this, R.id.des_context4);
        this.b = (HealthTextView) nsy.cMc_(this, R.id.des_context5);
    }

    private void c() {
        this.c.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R$string.IDS_rate_zone_hrr_des_context5), UnitUtil.e(59.0d, 2, 0), "～ " + UnitUtil.e(74.0d, 2, 0)));
        this.e.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R$string.IDS_rate_zone_hrr_des_context4), UnitUtil.e(74.0d, 2, 0), "～ " + UnitUtil.e(84.0d, 2, 0)));
        this.f.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R$string.IDS_rate_zone_hrr_des_context3), UnitUtil.e(84.0d, 2, 0), "～ " + UnitUtil.e(88.0d, 2, 0)));
        this.f10454a.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R$string.IDS_rate_zone_hrr_des_context2), UnitUtil.e(88.0d, 2, 0), "～ " + UnitUtil.e(95.0d, 2, 0)));
        this.b.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R$string.IDS_rate_zone_hrr_des_context1), UnitUtil.e(95.0d, 2, 0), "～ " + UnitUtil.e(100.0d, 2, 0)));
        this.o.setText(R$string.IDS_main_watch_detail_max_heart_rate_unsport_interval);
        this.h.setText(this.d.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 1));
        this.g.setText(this.d.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 2));
        this.l.setText(this.d.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 3));
        this.k.setText(this.d.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 4));
        this.j.setText(this.d.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_sport_title, 5));
        this.i.setText(String.format(Locale.ENGLISH, this.d.getResources().getString(R$string.IDS_main_watch_detail_max_heart_rate_unsport_context), UnitUtil.e(59.0d, 2, 0)));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
