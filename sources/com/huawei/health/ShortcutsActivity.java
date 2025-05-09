package com.huawei.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.ShortcutsActivity;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.fhp;
import defpackage.gso;
import defpackage.gww;
import defpackage.ixx;
import defpackage.kzc;
import defpackage.moj;
import defpackage.nbr;
import defpackage.sqf;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class ShortcutsActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private String f2182a;
    private Context e;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        this.e = this;
        String d = d();
        this.f2182a = d;
        ReleaseLogUtil.e("R_ShortcutsActivity", "mShortcutId: ", d);
        if (AuthorizationUtils.a(this.e)) {
            b();
        }
        int b = fhp.c().b();
        boolean z2 = true;
        boolean z3 = b == 2 || b == 5;
        boolean isLogined = LoginInit.getInstance(this.e).getIsLogined();
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi != null) {
            z = sportDataOutputApi.isSportServiceRunning();
            if (!z || !sportDataOutputApi.isDeviceMirrorLink()) {
                z2 = false;
            }
        } else {
            z2 = false;
            z = false;
        }
        LogUtil.a("ShortcutsActivity", "PluginSportTrack.getInstance().isSportingStatus()", Boolean.valueOf(gso.e().p()));
        LogUtil.a("ShortcutsActivity", "PluginSportTrack.getInstance().isSupportAutoTrackStatus()", Boolean.valueOf(gso.e().q()));
        if (gso.e().p() && gso.e().q()) {
            gso.e().ab();
            finish();
            return;
        }
        if (gso.e().p() || z2) {
            startActivity(new Intent(this.e, (Class<?>) TrackMainMapActivity.class));
        } else if (z3) {
            AppRouter.b("/PluginFitnessAdvice/CoachActivity").c(this.e);
        } else if (isLogined && z && AuthorizationUtils.a(this.e)) {
            a(sportDataOutputApi);
        } else {
            if (TextUtils.isEmpty(this.f2182a)) {
                LogUtil.h("ShortcutsActivity", "shortcutId is empty");
                finish();
                return;
            }
            c(this.f2182a);
        }
        overridePendingTransition(0, 0);
        finish();
    }

    private void a(SportDataOutputApi sportDataOutputApi) {
        Intent intent = new Intent();
        if (sportDataOutputApi.getSportType() == 283) {
            BV_(sportDataOutputApi, intent);
        } else {
            BU_(intent);
        }
        startActivity(intent);
    }

    private void BV_(SportDataOutputApi sportDataOutputApi, Intent intent) {
        if (sportDataOutputApi.getDataSource() == 7) {
            intent.setClass(this.e, SportBaseActivity.class);
        }
        if (sportDataOutputApi.getDataSource() == 5) {
            if (kzc.n().q()) {
                BU_(intent);
            } else {
                intent.setClass(this.e, SportBaseActivity.class);
            }
        }
    }

    private void BU_(Intent intent) {
        if (kzc.n().t()) {
            intent.setClass(this.e, IndoorEquipDisplayActivity.class);
        } else if (kzc.n().x()) {
            intent.setClass(this.e, IndoorEquipLandDisplayActivity.class);
        } else {
            intent.setClass(this.e, IndoorEquipConnectedActivity.class);
        }
    }

    private void b() {
        LogUtil.a("ShortcutsActivity", "need init");
        Context context = BaseApplication.getContext();
        ixx.d().a(LoginInit.getInstance(context).getAccountInfo(1011));
        ixx.d().e(LoginInit.getInstance(context).getAccountInfo(1010));
        OpAnalyticsUtil.getInstance().init(context, new IBaseResponseCallback() { // from class: byp
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ShortcutsActivity.this.e(i, obj);
            }
        });
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", 3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("ShortcutsActivity", "BiEvent create shortcuts shortcutId", this.f2182a);
        if (i == 0) {
            if ("SC_FA_CARD_EXERCISE".equals(this.f2182a) || "SC_FA_CARD_MAIN".equals(this.f2182a)) {
                nbr.b(this.e, this.f2182a);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1638359389:
                if (str.equals("SC_MALL")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -470109465:
                if (str.equals("SC_EXERCISE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -353311597:
                if (str.equals("SC_FA_CARD_MAIN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 52485042:
                if (str.equals("SC_FA_CARD_EXERCISE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1535953701:
                if (str.equals("SC_DEVICE")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0 && c != 1) {
            if (c == 2) {
                nbr.a(this.e, "SC_FA_CARD_MAIN");
                return;
            } else if (c == 3) {
                nbr.a(this.e, "SC_FA_CARD_EXERCISE");
                return;
            } else if (c != 4) {
                b(str);
                return;
            }
        }
        nbr.a(this.e, str);
    }

    private String d() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("ShortcutsActivity", "intent == null");
            return null;
        }
        String dataString = intent.getDataString();
        if (dataString == null && intent.hasExtra(e.n)) {
            Map<String, String> a2 = moj.a(intent.getStringExtra(e.n));
            if (a2.containsKey("shortcutId")) {
                String str = a2.get("shortcutId");
                LogUtil.c("ShortcutsActivity", "facard shortcutId :", str);
                return str;
            }
        }
        return dataString;
    }

    private void b(String str) {
        if (gww.c() || !nbr.b(this.e)) {
            nbr.a(this.e, str);
            return;
        }
        if (!"SC_KAKA".equals(str)) {
            LogUtil.h("ShortcutsActivity", "dealOtherShortcutsId shortcutsId not match");
            return;
        }
        nbr.e(this.e, str);
        if (c()) {
            LogUtil.a("ShortcutsActivity", "dealOtherShortcutsId isEfficientResting");
            AppRouter.b("/PluginSleepBriefs/EfficientRestActivity").c(this.e);
        } else if (e()) {
            LogUtil.a("ShortcutsActivity", "dealOtherShortcutsId isRecordSleep");
            AppRouter.b("/PluginSleepDetection/SleepDetectionActivity").c(this.e);
        } else {
            sqf.b(this.e);
        }
    }

    private boolean e() {
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10000), "sp_key_record_sleep");
        try {
            if (TextUtils.isEmpty(b)) {
                return false;
            }
            return Boolean.parseBoolean(b);
        } catch (NumberFormatException e) {
            LogUtil.b("ShortcutsActivity", "isRecordSleep exception : ", e.getMessage());
            return false;
        }
    }

    private boolean c() {
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10000), "sp_key_briefs_is_resting");
        try {
            if (TextUtils.isEmpty(b)) {
                return false;
            }
            return Boolean.parseBoolean(b);
        } catch (NumberFormatException e) {
            LogUtil.b("ShortcutsActivity", "showEfficientRest exception : ", e.getMessage());
            return false;
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        ReleaseLogUtil.e("R_ShortcutsActivity", "onPause");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("R_ShortcutsActivity", "onDestroy");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
