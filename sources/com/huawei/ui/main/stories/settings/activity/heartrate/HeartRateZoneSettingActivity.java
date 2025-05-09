package com.huawei.ui.main.stories.settings.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateZoneSettingActivity;
import com.huawei.ui.main.stories.settings.fragment.BaseHeartRateZoneFragment;
import com.huawei.up.model.UserInfomation;
import defpackage.kor;
import defpackage.kox;
import defpackage.nqx;
import defpackage.nsy;

/* loaded from: classes7.dex */
public class HeartRateZoneSettingActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f10453a;
    private Context b;
    private BaseHeartRateZoneFragment f;
    private BaseHeartRateZoneFragment g;
    private BaseHeartRateZoneFragment h;
    private View i;
    private BaseHeartRateZoneFragment j;
    private UserInfomation k;
    private HealthSubTabWidget l;
    private HealthViewPager m;
    private nqx n;
    private boolean e = false;
    private boolean c = true;
    private IBaseResponseCallback d = new IBaseResponseCallback() { // from class: rvc
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public final void d(int i, Object obj) {
            HeartRateZoneSettingActivity.this.b(i, obj);
        }
    };

    public /* synthetic */ void b(int i, Object obj) {
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            this.c = booleanValue;
            LogUtil.a("HeartRateZoneSettingActivity", "onResponse objData ", Boolean.valueOf(booleanValue));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("HeartRateZoneSettingActivity", "Enter onCreate()");
        super.onCreate(bundle);
        dRH_(bundle);
        this.b = getApplicationContext();
        e();
        d();
    }

    private void dRH_(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("currentActivityHashCode", "");
            if (TextUtils.isEmpty(string) || string.equals(toString())) {
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.MainActivity");
            intent.setFlags(268468224);
            startActivity(intent);
            finish();
        }
    }

    private void d() {
        UserInfomation n = kor.a().n();
        this.k = n;
        this.f10453a = n.getAgeOrDefaultValue();
        c(this.k.isBirthdayValid() ? 8 : 0);
    }

    private void c() {
        UserInfomation n = kor.a().n();
        this.k = n;
        LogUtil.h("HeartRateZoneSettingActivity", "getUserInfo userInformation.isBirthdayValid() ", Boolean.valueOf(n.isBirthdayValid()));
        c(this.k.isBirthdayValid() ? 8 : 0);
        if (this.f10453a != this.k.getAgeOrDefaultValue()) {
            this.f10453a = this.k.getAgeOrDefaultValue();
            this.j.h();
            this.h.h();
            this.g.h();
            this.f.h();
        }
    }

    private void c(int i) {
        View view = this.i;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (this.e) {
            c();
            this.e = false;
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("currentActivityHashCode", toString());
    }

    private void e() {
        b();
        a();
    }

    private void a() {
        if (this.m == null) {
            return;
        }
        this.n = new nqx(this, this.m, this.l);
        this.j = new BaseHeartRateZoneFragment(1, this.d);
        this.h = new BaseHeartRateZoneFragment(2, this.d);
        this.g = new BaseHeartRateZoneFragment(3, this.d);
        this.f = new BaseHeartRateZoneFragment(4, this.d);
        this.n.c(this.l.c(this.b.getResources().getString(R$string.IDS_heart_raet_standing)), this.j, true);
        this.n.c(this.l.c(this.b.getResources().getString(R$string.IDS_heart_raet_riding)), this.h, false);
        this.n.c(this.l.c(this.b.getResources().getString(R$string.IDS_heart_raet_strokes)), this.g, false);
        this.n.c(this.l.c(this.b.getResources().getString(R$string.IDS_device_setting_other)), this.f, false);
    }

    private void b() {
        setContentView(R.layout.activity_heart_rate_zone_setting_gestures);
        this.i = findViewById(R.id.hw_health_rate_zone_tips);
        ((HealthTextView) findViewById(R.id.notice_message)).setText(this.b.getResources().getString(R$string.IDS_rate_zone_notice_set_msg, Integer.valueOf(HeartRateThresholdConfig.HEART_RATE_LIMIT)));
        this.l = (HealthSubTabWidget) nsy.cMc_(this, R.id.heart_rate_subTabLayout);
        this.m = (HealthViewPager) nsy.cMc_(this, R.id.heart_rate_detail_viewpager);
        findViewById(R.id.rate_zone_set_age_text).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateZoneSettingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeartRateZoneSettingActivity.this.e = true;
                AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(HeartRateZoneSettingActivity.this.b);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.a("HeartRateZoneSettingActivity", "onStop", Boolean.valueOf(this.c));
        if (this.c) {
            kox.e().f();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
