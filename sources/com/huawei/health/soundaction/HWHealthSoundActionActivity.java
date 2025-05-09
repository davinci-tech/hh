package com.huawei.health.soundaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.MainActivity;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.hihealth.HiHealthActivities;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.djr;
import defpackage.dum;
import defpackage.fei;
import defpackage.fej;
import defpackage.fek;
import defpackage.fem;
import defpackage.feq;
import defpackage.gso;
import defpackage.gtx;
import defpackage.gww;
import defpackage.gxf;
import defpackage.kzc;
import defpackage.nsn;
import defpackage.owp;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.StorageParams;

/* loaded from: classes8.dex */
public class HWHealthSoundActionActivity extends Activity {
    private Context c;
    private SportDataOutputApi i;
    private boolean e = true;
    private Intent b = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2985a = true;
    private Handler d = new Handler() { // from class: com.huawei.health.soundaction.HWHealthSoundActionActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 10086) {
                HWHealthSoundActionActivity.this.e(message.arg1);
            }
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.c("Track_HWHealthSoundActionActivity", "onCreate()");
        super.onCreate(bundle);
        this.c = this;
        LoginInit loginInit = LoginInit.getInstance(this);
        LogUtil.a("Track_HWHealthSoundActionActivity", "loginit_isLogined", Boolean.valueOf(loginInit.getIsLogined()));
        if (!AuthorizationUtils.a(this.c) || !loginInit.getIsLogined()) {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                startActivity(launchIntentForPackage);
            }
        } else {
            e(15);
        }
        if (this.f2985a) {
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (this.e) {
            this.b = getIntent();
            this.e = false;
        }
        if (i > 0) {
            boolean e = dum.d().e();
            LogUtil.a("Track_HWHealthSoundActionActivity", "initVoiceManager : retryTimes", Integer.valueOf(i), " ret ", Boolean.valueOf(e));
            if (!e) {
                Message obtainMessage = this.d.obtainMessage(10086);
                obtainMessage.arg1 = i - 1;
                this.d.removeMessages(10086);
                this.d.sendMessageDelayed(obtainMessage, 100L);
                return;
            }
        }
        b();
        fej.e().c();
        fei.c().a();
        this.i = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        SharedPreferences sharedPreferences = this.c.getSharedPreferences("IndoorEquipServiceRunning" + CommonUtil.e(this.c), 0);
        if (sharedPreferences != null) {
            boolean z = sharedPreferences.getBoolean("IsIndoorEquipServiceRunning" + CommonUtil.e(this.c), false);
            long elapsedRealtime = SystemClock.elapsedRealtime() - sharedPreferences.getLong("elapsedRealtime", 0L);
            if (z && elapsedRealtime < 2000 && elapsedRealtime > 0) {
                c();
            }
        }
        try {
            awD_(this.b);
        } catch (Exception e2) {
            LogUtil.h("Track_HWHealthSoundActionActivity", LogAnonymous.b((Throwable) e2));
        }
    }

    private void b() {
        gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(this.c));
        gso.e().init(this.c);
        if (((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) == null) {
            Services.a("PluginFitnessAdvice", PluginSuggestion.class, BaseApplication.e(), new Consumer<PluginSuggestion>() { // from class: com.huawei.health.soundaction.HWHealthSoundActionActivity.2
                @Override // com.huawei.framework.servicemgr.Consumer
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void accept(PluginSuggestion pluginSuggestion) {
                    if (pluginSuggestion == null) {
                        LogUtil.h("Track_HWHealthSoundActionActivity", "PluginSuggestion is null.");
                    } else {
                        pluginSuggestion.init(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
                    }
                }
            }, true);
        }
    }

    public void awD_(Intent intent) {
        LogUtil.c("Track_HWHealthSoundActionActivity", "handleCommand(Intent intent)");
        if (intent == null) {
            LogUtil.c("Track_HWHealthSoundActionActivity", "handleCommand(Intent intent) intent == null");
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            LogUtil.c("Track_HWHealthSoundActionActivity", "handleCommand(Intent intent) data == null");
            return;
        }
        LogUtil.c("Track_HWHealthSoundActionActivity", "handleCommand data = ", data);
        String host = data.getHost();
        LogUtil.c("Track_HWHealthSoundActionActivity", "Host ", host);
        if ("com.android.mediacenter".equals(host)) {
            awC_(intent);
            awy_(intent);
            return;
        }
        try {
            String queryParameter = data.getQueryParameter("action");
            LogUtil.c("Track_HWHealthSoundActionActivity", "handleCommand(Intent intent) queryParameter = ", queryParameter);
            if (feq.b() == 2) {
                LogUtil.h("Track_HWHealthSoundActionActivity", "handleCommand conflict");
                if (kzc.n().q()) {
                    return;
                }
                fek.c().d(R.raw._2131886176_res_0x7f120060);
                return;
            }
            awB_(data, queryParameter);
        } catch (IllegalArgumentException e) {
            LogUtil.b("Track_HWHealthSoundActionActivity", "handleCommand IllegalArgumentException:", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            LogUtil.b("Track_HWHealthSoundActionActivity", "handleCommand Exception:", LogAnonymous.b((Throwable) e2));
        }
    }

    private void awB_(Uri uri, String str) {
        if ("start".equals(str)) {
            gso.e().b(2);
            aww_(awv_(uri), uri);
            return;
        }
        if (VastAttribute.PAUSE.equals(str)) {
            gso.e().b(2);
            a();
        } else if ("resume".equals(str)) {
            gso.e().b(2);
            e();
        } else if ("stop".equals(str)) {
            gso.e().b(2);
            d();
        } else {
            gso.e().b(2);
            fem.b().dispatchEvent(uri);
        }
    }

    private void awC_(Intent intent) {
        try {
            int f = owp.f(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
            LogUtil.a("Track_HWHealthSoundActionActivity", "contentId ", intent.getStringExtra("contentId"), " ", "contentElement ", intent.getStringExtra("contentElement"), " ", "operatorType ", Integer.valueOf(intent.getIntExtra("operatorType", 0)), " sportType = ", Integer.valueOf(f));
            gww gwwVar = new gww(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
            gwwVar.a(intent.getIntExtra("operatorType", 0), f);
            gwwVar.d(intent.getStringExtra("contentType"), f);
            gwwVar.b(intent.getStringExtra("contentId"), f);
            gwwVar.e(intent.getStringExtra("contentElement"), f);
        } catch (Exception e) {
            LogUtil.b("Track_HWHealthSoundActionActivity", LogAnonymous.b((Throwable) e));
        }
    }

    private void awy_(Intent intent) {
        LogUtil.a("Track_HWHealthSoundActionActivity", "handleHwMusicRequest");
        boolean awu_ = awu_(intent);
        gtx c = gtx.c(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
        if (c.bc()) {
            if (c.r() == 1 && intent != null) {
                aww_(258, intent.getData());
                return;
            }
            Intent intent2 = new Intent(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), (Class<?>) TrackMainMapActivity.class);
            intent2.setFlags(AppRouterExtras.COLDSTART);
            intent2.putExtra("isSelected", awu_);
            LogUtil.a("Track_HWHealthSoundActionActivity", "huaweimusic to sporting");
            startActivity(intent2);
            return;
        }
        if (gso.e().h()) {
            LogUtil.a("Track_HWHealthSoundActionActivity", "huaweimusic to sportIntent");
            Object paras = this.i.getParas(SportParamsType.SPORT_LAUNCH_PARAS);
            SportLaunchParams sportLaunchParams = paras instanceof SportLaunchParams ? (SportLaunchParams) paras : null;
            if (sportLaunchParams == null) {
                LogUtil.b("Track_HWHealthSoundActionActivity", "handleHwMusicRequest sportLaunchParams is null.");
                return;
            }
            if (sportLaunchParams.getLaunchActivity() == null || sportLaunchParams.getLaunchActivity().isEmpty()) {
                return;
            }
            Intent intent3 = new Intent();
            intent3.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getPackageName(), sportLaunchParams.getLaunchActivity());
            intent3.setFlags(AppRouterExtras.COLDSTART);
            intent3.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
            intent3.putExtra("isSelected", awu_);
            startActivity(intent3);
            return;
        }
        if (djr.c().e()) {
            Intent intent4 = new Intent(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), (Class<?>) DeviceMainActivity.class);
            intent4.setFlags(603979776);
            startActivity(intent4);
            return;
        }
        Intent intent5 = new Intent(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), (Class<?>) MainActivity.class);
        intent5.setFlags(AppRouterExtras.COLDSTART);
        intent5.putExtra("isSelected", awu_);
        intent5.putExtra(BleConstants.SPORT_TYPE, 258);
        intent5.putExtra("isToSportTab", true);
        intent5.putExtra("mLaunchSource", 1);
        LogUtil.a("Track_HWHealthSoundActionActivity", "huaweimusic to params");
        startActivity(intent5);
    }

    private boolean awu_(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            boolean booleanExtra = intent.getBooleanExtra("isSelected", false);
            LogUtil.a("Track_HWHealthSoundActionActivity", "huaweimusic isSelected", Boolean.valueOf(booleanExtra));
            return booleanExtra;
        } catch (BadParcelableException e) {
            LogUtil.h("Track_HWHealthSoundActionActivity", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    private int awv_(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if ("running".equals(lastPathSegment)) {
            return 258;
        }
        if ("walking".equals(lastPathSegment)) {
            return 257;
        }
        if (HiHealthActivities.CYCLING.equals(lastPathSegment)) {
            return 259;
        }
        return "indoorRunning".equals(lastPathSegment) ? 264 : 0;
    }

    static class d {
        private float c;
        private int e;

        private d() {
            this.e = -1;
            this.c = -1.0f;
        }

        public int c() {
            return this.e;
        }

        public void c(int i) {
            this.e = i;
        }

        public float e() {
            return this.c;
        }

        public void b(float f) {
            this.c = f;
        }
    }

    private void aww_(int i, Uri uri) {
        feq.c("start");
        if (d(i)) {
            return;
        }
        d dVar = new d();
        if (awA_(i, uri, dVar) || awx_(uri, dVar)) {
            return;
        }
        awz_(uri, dVar);
        gtx c = gtx.c(gxf.d());
        if (gso.e().i() == 1 && c.r() != 1) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "gotoSport already sporting");
            if (kzc.n().q()) {
                return;
            }
            fek.c().d(R.raw._2131886171_res_0x7f12005b);
            return;
        }
        if (gso.e().i() == 2 && c.r() != 1) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "gotoSport pausing");
            e();
            return;
        }
        gso.e().c(0, i, dVar.c(), dVar.e(), null, this.c);
        int c2 = dVar.c();
        float e = dVar.e();
        owp.c(this.c, i, c2);
        owp.c(this.c, e, i);
        LogUtil.c("Track_HWHealthSoundActionActivity", "", Integer.valueOf(i), ":", Integer.valueOf(dVar.c()), ":", Float.valueOf(dVar.e()));
        if (c2 == -1 || e == -1.0f || Math.abs(e) <= 1.0E-6d) {
            return;
        }
        owp.e(this.c, i, true);
        owp.b(this.c, i, c2, e);
    }

    private void awz_(Uri uri, d dVar) {
        String queryParameter = uri.getQueryParameter("marathon");
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        if ("marathon".equals(queryParameter)) {
            dVar.b(c("42.195"));
        } else if ("half_marathon".equals(queryParameter)) {
            dVar.b(c("21.0975"));
        }
        dVar.c(1);
    }

    private boolean awx_(Uri uri, d dVar) {
        String queryParameter = uri.getQueryParameter("duration");
        if (!TextUtils.isEmpty(queryParameter)) {
            dVar.b(c(queryParameter));
            if (b(dVar.e())) {
                return true;
            }
            dVar.c(0);
        }
        return false;
    }

    private float c(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            LogUtil.b("Track_HWHealthSoundActionActivity", "throwExceptionToDeal NumberFormatException:", LogAnonymous.b((Throwable) e));
            return 0.0f;
        }
    }

    private boolean awA_(int i, Uri uri, d dVar) {
        String queryParameter = uri.getQueryParameter("unit");
        if ("cal".equals(queryParameter)) {
            String queryParameter2 = uri.getQueryParameter("number");
            if (TextUtils.isEmpty(queryParameter2)) {
                dVar.c(-1);
                return false;
            }
            float c = c(queryParameter2) * 1000.0f;
            dVar.b(c);
            if (d(c)) {
                return true;
            }
            dVar.c(2);
            return false;
        }
        if ("km".equals(queryParameter)) {
            String queryParameter3 = uri.getQueryParameter("number");
            if (TextUtils.isEmpty(queryParameter3)) {
                dVar.c(-1);
                return false;
            }
            dVar.b(c(queryParameter3));
            if (c(i, dVar.e())) {
                return true;
            }
            dVar.c(1);
            return false;
        }
        if ("metre".equals(queryParameter)) {
            String queryParameter4 = uri.getQueryParameter("number");
            if (TextUtils.isEmpty(queryParameter4)) {
                dVar.c(-1);
                return false;
            }
            float c2 = c(queryParameter4) / 1000.0f;
            dVar.b(c2);
            if (c(i, c2)) {
                return true;
            }
            dVar.c(1);
            return false;
        }
        LogUtil.a("Track_HWHealthSoundActionActivity", "gotoSport slot value = ", queryParameter);
        return false;
    }

    private boolean d(float f) {
        if (f >= 100000.0f && f <= 5000000.0f) {
            return false;
        }
        if (kzc.n().q()) {
            return true;
        }
        fek.c().d(R.raw._2131886179_res_0x7f120063);
        return true;
    }

    private boolean b(float f) {
        if (f >= 600.0f && f <= 86400.0f) {
            return false;
        }
        if (kzc.n().q()) {
            return true;
        }
        fek.c().d(R.raw._2131886182_res_0x7f120066);
        return true;
    }

    private boolean c(int i, float f) {
        if (i != 259 && (Float.compare(f, 0.1f) < 0 || Float.compare(f, 100.0f) > 0)) {
            if (!kzc.n().q()) {
                fek.c().d(R.raw._2131886180_res_0x7f120064);
            }
            return true;
        }
        if (i != 259) {
            return false;
        }
        if (Float.compare(f, 0.1f) >= 0 && Float.compare(f, 200.0f) <= 0) {
            return false;
        }
        if (!kzc.n().q()) {
            fek.c().d(R.raw._2131886181_res_0x7f120065);
        }
        return true;
    }

    private boolean d(int i) {
        PermissionUtil.PermissionType e = PermissionUtil.e(i);
        if (PermissionUtil.e(this.c, e) != PermissionUtil.PermissionResult.FOREVER_DENIED) {
            return false;
        }
        this.f2985a = false;
        if (isFinishing()) {
            return true;
        }
        try {
            d(this.c, e);
            return true;
        } catch (WindowManager.BadTokenException e2) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "showHealthAppSettingGuide exception ", LogAnonymous.b((Throwable) e2));
            return true;
        }
    }

    private void a() {
        feq.c(VastAttribute.PAUSE);
        if (gso.e().i() == 0) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "pauseSport failed");
            if (kzc.n().q()) {
                return;
            }
            fek.c().d(R.raw._2131886177_res_0x7f120061);
            return;
        }
        if (gso.e().i() == 2) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "pauseSport failed");
            if (kzc.n().q()) {
                return;
            }
            fek.c().d(R.raw._2131886170_res_0x7f12005a);
            return;
        }
        gso.e().v();
    }

    private void e() {
        feq.c("resume");
        if (gso.e().i() == 0) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "resumeSport failed");
            if (kzc.n().q()) {
                return;
            }
            fek.c().d(R.raw._2131886174_res_0x7f12005e);
            return;
        }
        if (gso.e().i() == 1) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "resumeSport failed");
            if (kzc.n().q()) {
                return;
            }
            fek.c().d(R.raw._2131886175_res_0x7f12005f);
            return;
        }
        gso.e().y();
    }

    private void d() {
        feq.c("stop");
        if (gso.e().i() == 0) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "stopSport failed");
            if (kzc.n().q()) {
                return;
            }
            fek.c().d(R.raw._2131886178_res_0x7f120062);
            return;
        }
        gso.e().d(new ISportDataCallback() { // from class: com.huawei.health.soundaction.HWHealthSoundActionActivity.3
            @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
            public void getSportInfo(Bundle bundle) {
            }

            @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
            public void onSummary(MotionPathSimplify motionPathSimplify) {
            }
        });
    }

    private void d(final Context context, PermissionUtil.PermissionType permissionType) {
        if (!(context instanceof Activity)) {
            LogUtil.h("Track_HWHealthSoundActionActivity", "showHealthAppSettingGuide context is not activity context.");
            return;
        }
        new CustomTextAlertDialog.Builder(context).b(R.string._2130842089_res_0x7f0211e9).e(context.getResources().getString(nsn.d(permissionType))).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.soundaction.HWHealthSoundActionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Track_HWHealthSoundActionActivity", "showPermissionSettingGuide cancel");
                HWHealthSoundActionActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: com.huawei.health.soundaction.HWHealthSoundActionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Track_HWHealthSoundActionActivity", "showPermissionSettingGuide cancel");
                nsn.ak(context);
                HWHealthSoundActionActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        setIntent(null);
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
