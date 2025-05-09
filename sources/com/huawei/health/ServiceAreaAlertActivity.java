package com.huawei.health;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.common.util.VersionIsCloud;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.ServiceAreaAlertActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ContentProviderUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.goq;
import defpackage.nsn;
import defpackage.scm;
import health.compact.a.CloudImpl;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

/* loaded from: classes3.dex */
public class ServiceAreaAlertActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f2181a;
    private HealthTextView b = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2181a = this;
        setContentView(R.layout.show_service_area_alert_layout);
        String b = SharedPreferenceManager.b(this.f2181a, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        if (goq.a(this.f2181a)) {
            b = "TW";
        }
        if (!TextUtils.isEmpty(b)) {
            c(b);
        } else {
            d();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        super.onActivityResult(i, i2, intent);
        if (i != 6005 || i2 != 0 || intent == null || (extras = intent.getExtras()) == null || this.b == null) {
            return;
        }
        String string = (!extras.containsKey("service_area_country") || extras.getString("service_area_country") == null) ? "" : extras.getString("service_area_country");
        if (goq.a(this.f2181a)) {
            this.b.setText("中国台湾");
        } else {
            this.b.setText(string);
        }
        String c = scm.c(string);
        SharedPreferenceManager.e(this.f2181a, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country", c, (StorageParams) null);
        ContentProviderUtil.getInstance(this.f2181a).setCountryCode(c, null);
        LogUtil.a("Login_ServiceAreaAlertActivity", "onActivityResult, mCountryText = ", string, "  strCountry = ", c);
    }

    private void d() {
        ((CustomTitleBar) findViewById(R.id.third_party_title_bar)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: byk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServiceAreaAlertActivity.this.BS_(view);
            }
        });
        this.b = (HealthTextView) findViewById(R.id.service_area_country);
        String d = scm.d(FoundationCommonUtil.getCountryStrByTelephonyMcc(this.f2181a));
        if (TextUtils.isEmpty(d)) {
            d = b();
        }
        if (goq.a(this.f2181a)) {
            d = "中国台湾";
        }
        this.b.setText(d);
        ((LinearLayout) findViewById(R.id.service_area_country_layout)).setOnClickListener(new View.OnClickListener() { // from class: byo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServiceAreaAlertActivity.this.BT_(view);
            }
        });
        e();
    }

    public /* synthetic */ void BS_(View view) {
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void BT_(View view) {
        startActivityForResult(new Intent(this.f2181a, (Class<?>) SelectCountryListActivity.class), AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        if (LanguageUtil.bc(this.f2181a)) {
            ((ImageView) findViewById(R.id.service_area_select_button)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.service_area_cancel_bottom);
        if (LanguageUtil.as(this.f2181a)) {
            healthButton.setTextSize(1, 13.0f);
        }
        String upperCase = getString(R.string._2130841130_res_0x7f020e2a).toUpperCase(Locale.getDefault());
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.service_area_agree_bottom);
        String upperCase2 = getString(R.string._2130841128_res_0x7f020e28).toUpperCase(Locale.getDefault());
        if (nsn.s()) {
            if (upperCase2.length() > upperCase.length()) {
                healthButton.setText(d(upperCase));
            } else {
                healthButton2.setText(d(upperCase2));
            }
            h();
        } else {
            healthButton.setText(upperCase);
            healthButton2.setText(upperCase2);
        }
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: byj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServiceAreaAlertActivity.this.BQ_(view);
            }
        });
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: bym
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServiceAreaAlertActivity.this.BR_(view);
            }
        });
    }

    public /* synthetic */ void BQ_(View view) {
        SharedPreferenceManager.e(this.f2181a, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country", "", (StorageParams) null);
        moveTaskToBack(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void BR_(View view) {
        f();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        String obj = this.b.getText().toString();
        if (goq.a(this.f2181a)) {
            obj = "中国台湾";
        }
        String c = scm.c(obj);
        LogUtil.a("Login_ServiceAreaAlertActivity", "startBtn setOnClickListener strCountryName = ", obj, "strCountry = ", c);
        SharedPreferenceManager.e(this.f2181a, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country", c, (StorageParams) null);
        if (Utils.l() || LoginInit.getInstance(this.f2181a).isBrowseMode()) {
            SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setCountryCode(c);
            SharedPreferenceUtil.updateLastCountryCode(c);
        }
        if ("cn".equalsIgnoreCase(c)) {
            HuaweiLoginManager.setCloudVersion("1", null);
        } else {
            HuaweiLoginManager.setCloudVersion("0", null);
        }
        boolean isOverseaJudgeByCountry = CloudImpl.c(this.f2181a).isOverseaJudgeByCountry(c);
        if (isOverseaJudgeByCountry) {
            HuaweiLoginManager.setIsAllowedLoginValueToDB(this.f2181a, "1");
            LogUtil.a("Login_ServiceAreaAlertActivity", "judgeIfInAccountArea HWUserProfileMgr.ACCOUNT_AREA !");
        } else {
            HuaweiLoginManager.setIsAllowedLoginValueToDB(this.f2181a, "0");
            LogUtil.a("Login_ServiceAreaAlertActivity", "judgeIfInAccountArea HWUserProfileMgr.NO_ACCOUNT_AREA !");
        }
        VersionIsCloud.setIfNeedShowAreaAlert(this.f2181a, "0");
        ReleaseLogUtil.e("Login_ServiceAreaAlertActivity", "judgeIfInAccountArea countryCode:", c, " isOverseaJudgeByCountry:", Boolean.valueOf(isOverseaJudgeByCountry));
        if (isOverseaJudgeByCountry) {
            a();
        } else {
            a(this.f2181a);
        }
    }

    private void a() {
        setResult(0, new Intent());
        finish();
        if (LanguageUtil.bc(this.f2181a)) {
            overridePendingTransition(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
        } else {
            overridePendingTransition(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
        }
    }

    private String b() {
        String str;
        List<String> b = scm.b();
        if (b == null || b.size() <= 0) {
            return null;
        }
        String str2 = b.get(0);
        Locale locale = getResources().getConfiguration().locale;
        String c = scm.c(locale);
        try {
            str = locale.getISO3Country();
        } catch (MissingResourceException unused) {
            LogUtil.b("Login_ServiceAreaAlertActivity", "MissingResourceException");
            str = "";
        }
        if (!TextUtils.isEmpty(str) && ("TWN".equalsIgnoreCase(str) || "HKG".equalsIgnoreCase(str) || "MAC".equalsIgnoreCase(str))) {
            LogUtil.a("Login_ServiceAreaAlertActivity", "getInitDisplayCountry sensitive country");
            return c;
        }
        Iterator<String> it = b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (next.equalsIgnoreCase(c)) {
                str2 = next;
                break;
            }
        }
        LogUtil.a("Login_ServiceAreaAlertActivity", "strDisplayCountry initServiceAreaView() , strDisplayCountry=", str2);
        return str2;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("Login_ServiceAreaAlertActivity", "onBackPressed()");
        c();
    }

    private void c() {
        LogUtil.a("Login_ServiceAreaAlertActivity", "quitApplication()");
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
        finish();
    }

    private void c(String str) {
        LogUtil.a("Login_ServiceAreaAlertActivity", "nextMain strCountry= ", str);
        if (Utils.l() || LoginInit.getInstance(this.f2181a).isBrowseMode()) {
            SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setCountryCode(str);
            SharedPreferenceUtil.updateLastCountryCode(str);
        }
        if ("cn".equalsIgnoreCase(str)) {
            HuaweiLoginManager.setCloudVersion("1", null);
        } else {
            HuaweiLoginManager.setCloudVersion("0", null);
        }
        if (CloudImpl.c(this.f2181a).isOverseaJudgeByCountry(str)) {
            HuaweiLoginManager.setIsAllowedLoginValueToDB(this.f2181a, "1");
            LogUtil.a("Login_ServiceAreaAlertActivity", "judgeIfInAccountArea HWUserProfileMgr.ACCOUNT_AREA !");
        } else {
            HuaweiLoginManager.setIsAllowedLoginValueToDB(this.f2181a, "0");
            LogUtil.a("Login_ServiceAreaAlertActivity", "judgeIfInAccountArea HWUserProfileMgr.NO_ACCOUNT_AREA !");
        }
        VersionIsCloud.setIfNeedShowAreaAlert(this.f2181a, "0");
        setResult(0, new Intent());
        finish();
        if (LanguageUtil.bc(this.f2181a)) {
            overridePendingTransition(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
        } else {
            overridePendingTransition(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
        }
    }

    private String d(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(0, "  ");
        sb.append("  ");
        return sb.toString();
    }

    private void h() {
        ((HealthTextView) findViewById(R.id.service_area_info_value)).setTextSize(1, 24.0f);
    }

    private void a(Context context) {
        String string = context.getString(R.string._2130841512_res_0x7f020fa8);
        String string2 = context.getString(R.string._2130847344_res_0x7f022670);
        String upperCase = context.getString(R.string._2130845895_res_0x7f0220c7).toUpperCase();
        String upperCase2 = context.getString(R.string._2130847345_res_0x7f022671).toUpperCase();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(string).e(string2).cyV_(upperCase, new View.OnClickListener() { // from class: byn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServiceAreaAlertActivity.this.BO_(view);
            }
        }).cyS_(upperCase2, new View.OnClickListener() { // from class: byl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ServiceAreaAlertActivity.this.BP_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void BO_(View view) {
        ReleaseLogUtil.e("Login_ServiceAreaAlertActivity", "checkShowNoCloudDialog ok");
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void BP_(View view) {
        ReleaseLogUtil.e("Login_ServiceAreaAlertActivity", "checkShowNoCloudDialog cancel");
        startActivityForResult(new Intent(this.f2181a, (Class<?>) SelectCountryListActivity.class), AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED);
        SharedPreferenceManager.e(this.f2181a, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country", "", (StorageParams) null);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
