package com.huawei.health;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPay;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity;
import com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity;
import com.huawei.ui.main.stories.smartcenter.activity.SmartMsgSkipActivity;
import defpackage.bzu;
import defpackage.fhp;
import defpackage.gso;
import defpackage.ixx;
import defpackage.kfp;
import defpackage.nbq;
import defpackage.pep;
import defpackage.sds;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes8.dex */
public class StartHealthActivity extends BaseActivity {
    private String b;
    private Context d;
    private String e;
    private String h;
    private a j;
    private LinearLayout k;

    /* renamed from: a, reason: collision with root package name */
    Map<String, String> f2184a = new HashMap<String, String>() { // from class: com.huawei.health.StartHealthActivity.2
        {
            put("com.huawei.health.MULTI_SIM_AUTH", "com.huawei.sim.multisim.MultiSimAuth");
            put("com.google.android.wearable.action.CONFIGURE_CELLULAR", "com.huawei.sim.esim.view.WirelessManagerActivity");
            put("com.google.android.wearable.action.CONFIGURE_PAYMENTS", ComponentInfo.PluginPay_A_31);
            put("com.google.android.wearable.action.CONFIGURE_ADDBANK", "com.huawei.nfc.carrera.ui.cardlist.AddBankOrBusCardActivity");
            put("com.huawei.health.CORE_SLEEP", "com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity");
            put("com.huawei.health.GALILEO_OPEN_APP_ESIM_ACTION", "com.huawei.sim.esim.view.EsimManagerActivity");
        }
    };
    private int n = -1;
    private int m = -1;
    private int g = -1;
    private String i = "";
    private boolean f = false;
    private String c = "";
    private Runnable l = new Runnable() { // from class: com.huawei.health.StartHealthActivity.3
        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("Login_StartHealthActivity", "mWizardRunnable run");
            if (StartHealthActivity.this.d == null) {
                StartHealthActivity.this.i();
                return;
            }
            try {
                Intent intent = new Intent();
                intent.setClassName(StartHealthActivity.this.d, "com.huawei.health.MainActivity");
                intent.setFlags(AppRouterExtras.COLDSTART);
                StartHealthActivity.this.d.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.b("Login_StartHealthActivity", "startActivity catch e:", e.getMessage());
            }
            StartHealthActivity.this.i();
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        BaseActivity.setNavigationBarVisibility(this, 8);
        if (getIntent() == null) {
            i();
            return;
        }
        if (!h()) {
            i();
            return;
        }
        this.d = this;
        this.j = new a();
        String action = getIntent().getAction();
        this.e = action;
        if ("com.huawei.health.CORE_SLEEP".equals(action)) {
            j();
            return;
        }
        if (!TextUtils.isEmpty(this.e) && a(this.e)) {
            LogUtil.a("Login_StartHealthActivity", "get actionName not null==", this.e);
            MainInteractors.c(this.f2184a.get(this.e));
            a();
            d(this.e);
            return;
        }
        c();
        LoginInit loginInit = LoginInit.getInstance(this.d);
        LogUtil.a("Login_StartHealthActivity", "loginit_isLogined", Boolean.valueOf(loginInit.getIsLogined()));
        if (!MainInteractors.a() || !loginInit.getIsLogined()) {
            LogUtil.a("Login_StartHealthActivity", "StartHealth to MainActivity");
            Intent intent = new Intent();
            intent.putExtra("health_smartmsg_id", this.n);
            intent.putExtra("health_smartmsg_type", this.m);
            intent.putExtra("health_smartmsg_content", this.h);
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.g);
            intent.putExtra("dname", this.i);
            intent.putExtra("isPorc", this.f);
            intent.putExtra("isFromWear", true);
            intent.setClass(this.d, MainActivity.class);
            this.d.startActivity(intent);
            i();
            return;
        }
        g();
    }

    private void c() {
        if (getIntent() != null) {
            try {
                this.n = getIntent().getIntExtra("health_smartmsg_id", -1);
                this.m = getIntent().getIntExtra("health_smartmsg_type", -1);
                this.h = getIntent().getStringExtra("health_smartmsg_content");
                this.g = getIntent().getIntExtra("produceType", -1);
                this.i = getIntent().getStringExtra("produceName");
                this.f = getIntent().getBooleanExtra("isPorc", false);
                String stringExtra = getIntent().getStringExtra("health_activity_id");
                this.b = stringExtra;
                if (TextUtils.isEmpty(stringExtra)) {
                    return;
                }
                if (!kfp.f14351a) {
                    i();
                }
                kfp.f14351a = false;
            } catch (Exception unused) {
                LogUtil.b("Login_StartHealthActivity", "getIntentData Exception.");
            }
        }
    }

    private void g() {
        if (this.n != -1 || this.m != -1 || this.h != null) {
            int b = ((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) != null ? fhp.c().b() : -1;
            int i = gso.e().getAdapter() != null ? gso.e().i() : 0;
            LogUtil.a("Login_StartHealthActivity", "sportState=", Integer.valueOf(i), ", fitState=", Integer.valueOf(b));
            if (i == 1 || i == 2 || b == 2 || b == 5) {
                i();
                return;
            } else {
                d();
                return;
            }
        }
        if (!TextUtils.isEmpty(this.b)) {
            LogUtil.a("Login_StartHealthActivity", "activityID=", this.b);
            a();
            b(this.b);
            i();
            return;
        }
        LogUtil.h("Login_StartHealthActivity", "judgeSkipActivity is other condition");
    }

    private void j() {
        String callingPackage = getCallingPackage();
        LogUtil.a("Login_StartHealthActivity", "callingPackage : ", callingPackage);
        if (!TextUtils.isEmpty(callingPackage)) {
            if (!HsfSignValidator.c(callingPackage)) {
                i();
                return;
            }
            if (!AuthorizationUtils.a(this.d) || !LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
                startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
                i();
                return;
            }
            Intent intent = new Intent();
            intent.setFlags(AppRouterExtras.COLDSTART);
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity");
            this.d.startActivity(intent);
            i();
            return;
        }
        LogUtil.h("Login_StartHealthActivity", "callingPackage is empty.");
        i();
    }

    private void d() {
        if (this.n == -1) {
            int i = this.m;
            if (i == 1) {
                l();
            } else if (i == 2) {
                m();
            } else if (i == 3) {
                o();
            } else {
                LogUtil.h("Login_StartHealthActivity", "checkSkipActivity is other condition");
            }
        } else {
            n();
        }
        i();
    }

    private void m() {
        LogUtil.a("Login_StartHealthActivity", "startFitnessExerciseActivity");
        c(AnalyticsValue.HEALTH_HOME_FROM_NEGATIVE_BUTTON_CLICK_2010053.value(), "2");
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null) {
            Intent intent = new Intent(this.d, (Class<?>) MainActivity.class);
            intent.putExtra("id", this.n);
            intent.putExtra("msgContent", this.h);
            intent.putExtra("msgType", this.m);
            this.d.startActivity(intent);
            return;
        }
        if (pluginSuggestion.isInitComplete()) {
            Bundle bundle = new Bundle();
            bundle.putString("SKIP_ALL_COURSE_KEY", "");
            AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(this.d);
        }
    }

    private void o() {
        LogUtil.a("Login_StartHealthActivity", "startPairDeviceActivity deviceType:", Integer.valueOf(this.g));
        Intent intent = new Intent();
        intent.putExtra("id", this.n);
        intent.putExtra("msgContent", this.h);
        intent.putExtra("msgType", this.m);
        intent.setFlags(268435456);
        if (this.g == 11) {
            LogUtil.a("Login_StartHealthActivity", "startPairDeviceActivity is r1 ");
            intent.putExtra(TemplateStyleRecord.STYLE, 4);
            intent.putExtra("isFromWearR1", true);
            intent.setClass(this.d, AddDeviceChildActivity.class);
        } else {
            LogUtil.a("Login_StartHealthActivity", "startPairDeviceActivity is not r1 ");
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.g);
            intent.putExtra("dname", this.i);
            intent.putExtra("isPorc", this.f);
            intent.putExtra("isFromWear", true);
            intent.setClass(this.d, AddDeviceIntroActivity.class);
        }
        this.d.startActivity(intent);
    }

    private void l() {
        LogUtil.a("Login_StartHealthActivity", "startSportActivity");
        c(AnalyticsValue.HEALTH_HOME_FROM_NEGATIVE_BUTTON_CLICK_2010053.value(), "1");
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) MainActivity.class);
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra(BleConstants.SPORT_TYPE, 0);
        intent.putExtra("isToSportTab", true);
        intent.putExtra("mLaunchSource", 3);
        LogUtil.a("Login_StartHealthActivity", "negative button to params");
        startActivity(intent);
    }

    private void n() {
        LogUtil.a("Login_StartHealthActivity", "startSmartMsgSkipActivity");
        c(AnalyticsValue.HEALTH_HOME_FROM_NEGATIVE_SMARTCARD_CLICK_2010054.value(), String.valueOf(this.m));
        Intent intent = new Intent();
        intent.putExtra("id", this.n);
        intent.putExtra("msgContent", this.h);
        intent.putExtra("msgType", this.m);
        intent.putExtra("from", 1);
        intent.setClass(this, SmartMsgSkipActivity.class);
        startActivity(intent);
    }

    private boolean b() {
        return nbq.e(this.d).b();
    }

    private boolean e() {
        return PluginPay.getInstance(this.d).isShowPay();
    }

    private void b(String str) {
        if (("com.huawei.sim.esim.view.WirelessManagerActivity".equals(str) || "com.huawei.sim.esim.view.EsimManagerActivity".equals(str) || "com.huawei.sim.esim.view.EsimActivationActivity".equals(str)) && b()) {
            LogUtil.c("Login_StartHealthActivity", "startActivityByClassName already in esim");
            return;
        }
        if ("com.huawei.nfc.carrera.ui.cardlist.AddBankOrBusCardActivity".equals(str)) {
            if (e()) {
                LogUtil.c("Login_StartHealthActivity", "startActivityByClassName already in wallet");
                return;
            } else if (!bzu.b().isPluginAvaiable()) {
                LogUtil.a("Login_StartHealthActivity", "startActivityByClassName not PluginAvailable");
                str = "com.huawei.health.MainActivity";
            } else {
                pep.c(this.d, "");
                return;
            }
        }
        if (this.d == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(this.d, str);
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Login_StartHealthActivity", "startActivity catch e:", e.getMessage());
        }
    }

    private void c(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.d, str, hashMap, 0);
    }

    private boolean a(String str) {
        return this.f2184a.get(str) != null;
    }

    private void d(String str) {
        LoginInit loginInit = LoginInit.getInstance(this.d);
        LogUtil.a("Login_StartHealthActivity", "loginit_isLogined", Boolean.valueOf(loginInit.getIsLogined()));
        if (!MainInteractors.a() || !loginInit.getIsLogined()) {
            LogUtil.a("Login_StartHealthActivity", "StartHealth to MainActivity");
            Intent intent = new Intent();
            intent.setClass(this.d, MainActivity.class);
            this.d.startActivity(intent);
            i();
            return;
        }
        setContentView(R.layout.layout_activity_mainui);
        a(0);
        String str2 = this.f2184a.get(str);
        this.c = str2;
        LogUtil.a("Login_StartHealthActivity", "showStartUpPageAndSkip activity", str2);
        this.j.removeCallbacks(this.l);
        this.j.postDelayed(this.l, 1000L);
    }

    private void a(int i) {
        if (this.k == null) {
            if (i == 8) {
                return;
            }
            ViewStub viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_startpage);
            if (viewStub == null) {
                LogUtil.a("Login_StartHealthActivity", "setStartPageVisibility ViewStub is loaded fail.");
                return;
            }
            View inflate = viewStub.inflate();
            if (inflate instanceof LinearLayout) {
                this.k = (LinearLayout) inflate;
            }
            LogUtil.a("Login_StartHealthActivity", "init start page ok.");
        }
        LinearLayout linearLayout = this.k;
        if (linearLayout == null) {
            LogUtil.h("Login_StartHealthActivity", "mStartPage==null");
        } else {
            ((HealthTextView) linearLayout.findViewById(R.id.hw_copyrights)).setText(sds.a());
            this.k.setVisibility(i);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        LinearLayout linearLayout = this.k;
        if (linearLayout != null && linearLayout.getVisibility() == 0) {
            f();
        }
        super.onConfigurationChanged(configuration);
    }

    static class a extends Handler {
        private a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                LogUtil.a("Login_StartHealthActivity", "Enter MSG_PAGE_SKIP");
            } else {
                LogUtil.c("Login_StartHealthActivity", "enter default");
            }
        }
    }

    private void a() {
        pep.b(this.d);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("Login_StartHealthActivity", "onActivityResult requesetCode ", Integer.valueOf(i), ";resultCode:", Integer.valueOf(i2));
        if (i == 1) {
            if (i2 == 2 && intent != null) {
                c(intent.getStringExtra("device_id"));
            }
            i();
        }
        super.onActivityResult(i, i2, intent);
    }

    private void c(String str) {
        Intent intent = new Intent();
        intent.setClassName(this, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent.putExtra("device_id", str);
        startActivity(intent);
    }

    private boolean h() {
        String str;
        if (getIntent() == null) {
            return false;
        }
        try {
            str = getIntent().getStringExtra("health_activity_id");
        } catch (Exception unused) {
            LogUtil.b("Login_StartHealthActivity", "Check Activity Exception.");
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        Iterator<String> it = this.f2184a.values().iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        LogUtil.a("Login_StartHealthActivity", "this params is not in white lists");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        setIntent(null);
        finish();
    }

    private void f() {
        this.k.findViewById(R.id.hw_health_start_page).setBackgroundResource(R.drawable._2131431547_res_0x7f0b107b);
    }
}
