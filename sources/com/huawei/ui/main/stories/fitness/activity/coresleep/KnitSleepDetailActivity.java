package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.section.listener.HealthPageResTrigger;
import com.huawei.health.knit.section.listener.IPageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepJobService;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.SleepBootPagerHelper;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepAudioClearDialog;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepShareBriefView;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.service.SleepServiceImpl;
import com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity;
import com.huawei.ui.main.stories.template.PopMenuManager;
import com.huawei.ui.main.stories.template.data.IPopMenuItemClick;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import com.huawei.up.model.UserInfomation;
import defpackage.bzs;
import defpackage.dlf;
import defpackage.efb;
import defpackage.fcd;
import defpackage.fdu;
import defpackage.gge;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jcu;
import defpackage.jdl;
import defpackage.jpt;
import defpackage.koq;
import defpackage.moj;
import defpackage.mtp;
import defpackage.nhj;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.ple;
import defpackage.pob;
import defpackage.pom;
import defpackage.pqb;
import defpackage.pqe;
import defpackage.pqg;
import defpackage.pqm;
import defpackage.ryf;
import defpackage.scn;
import defpackage.scw;
import defpackage.sdc;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class KnitSleepDetailActivity extends KnitHealthDetailActivity {

    /* renamed from: a, reason: collision with root package name */
    private static String f9778a = "";
    private long ab;
    private TimerTask ae;
    private List<Integer> af;
    private BroadcastReceiver ag;
    private Timer ah;
    private CustomTitleBar ai;
    private String[] aj;
    private String[] ak;
    private long al;
    private String[] an;
    private long b;
    private SleepBootPagerHelper d;
    private LinearLayout f;
    private Context i;
    private pqe j;
    private HealthPageResTrigger n;
    private KnitFragment o;
    private boolean u;
    private LastTimeHealthDataReader<KnitSleepDetailActivity> v;
    private c w;
    private Boolean x;
    private String y;
    private boolean am = false;
    private boolean m = false;
    private boolean t = false;
    private boolean r = false;
    private boolean ac = true;
    private int aa = -1;
    private int l = -1;
    private boolean q = true;
    private long h = 0;
    private String g = "0";
    private boolean p = true;
    private boolean e = false;
    private boolean c = false;
    private boolean k = false;
    private Observer s = new AnonymousClass5();
    private Observer z = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.12
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a("Track_KnitSleepDetailActivity", "start sleep");
            if ("START_SLEEP_TAG".equals(str)) {
                KnitSleepDetailActivity.this.e = true;
            }
        }
    };
    private Observer ad = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.13
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a("Track_KnitSleepDetailActivity", "stop sleep");
            if ("FINISH_SLEEP_TAG".equals(str)) {
                KnitSleepDetailActivity.this.e = false;
                KnitSleepDetailActivity.this.h();
                if (KnitSleepDetailActivity.this.u) {
                    nhj.b(nhj.h(), System.currentTimeMillis());
                }
            }
        }
    };

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configNoNetPage() {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_sleep;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configToolBar(HealthToolBar healthToolBar) {
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public int getPageType() {
        return 1;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "openFaCardAgds requestCode: ", Integer.valueOf(i), "resultCode: ", Integer.valueOf(i2));
        FaCardAgdsApi c2 = dlf.c();
        if (c2 != null && i == 500) {
            pqm.dsh_(1, i, intent);
            c2.close();
        } else if (i == 100) {
            if (i2 == c2.getResultcodeAgreeProtocol()) {
                pqm.d(this.j);
                pqm.c(this.j, 500);
            } else if (i2 == c2.getResultcodeNotAgreeProtocol()) {
                ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "openFaCardAgds NotAgreeProtocol");
            }
        }
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements Observer {
        AnonymousClass5() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(final String str, Object... objArr) {
            HandlerExecutor.d(new Runnable() { // from class: plk
                @Override // java.lang.Runnable
                public final void run() {
                    KnitSleepDetailActivity.AnonymousClass5.this.c(str);
                }
            }, 300L);
        }

        public /* synthetic */ void c(String str) {
            LogUtil.a("Track_KnitSleepDetailActivity", "mIntelSectionLoadedObserver");
            if ("INTEL_SECTION_LOADED".equals(str) && KnitSleepDetailActivity.this.aa == 1) {
                KnitSleepDetailActivity.this.c(R.id.layout_webview);
            }
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Track_KnitSleepDetailActivity", "onCreate");
        try {
            super.onCreate(bundle);
        } catch (BadParcelableException unused) {
            LogUtil.b("Track_KnitSleepDetailActivity", "BadParcelableException for savedInstanceState");
        }
        aa();
        fcd.d(true);
        this.i = this;
        a();
        dqL_(getIntent());
        scn.a((WeakReference<Context>) new WeakReference(this.i));
        j();
        boolean n = nhj.n();
        this.u = n;
        if (n) {
            HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(pqg.i(), new a(this));
            e(System.currentTimeMillis(), nhj.d());
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void checkEmptyFragment(long j) {
        LogUtil.a("Track_KnitSleepDetailActivity", "checkEmptyFragment, latestDataTime: ", Long.valueOf(j));
        this.mEmptyFragmentView = findViewById(R.id.knit_health_detail_empty_layout);
        setEmptyFragmentBelowTitleBar(this.mEmptyFragmentView, this.ai);
        this.mEmptyFragment = onCreateEmptyFragment(this.mNoDataPageConfig);
    }

    private void g() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.b < ProfileExtendConstants.TIME_OUT) {
            return;
        }
        this.b = elapsedRealtime;
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Track_KnitSleepDetailActivity", "checkFaLink intent == null");
            return;
        }
        if (intent.getBooleanExtra("HAS_JUMP_TO_MANUAL", false)) {
            return;
        }
        Uri zs_ = AppRouterUtils.zs_(intent);
        if (zs_ == null) {
            LogUtil.a("Track_KnitSleepDetailActivity", "schemeData == null");
        } else {
            dqO_(zs_);
        }
    }

    private void f() {
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            LogUtil.h("Track_KnitSleepDetailActivity", "not from intelSleep");
        } else if (intent.getExtras().getInt("from") == 7) {
            LogUtil.a("Track_KnitSleepDetailActivity", "checkIntelSleepLink");
            this.aa = 1;
        }
    }

    private void y() {
        if (this.aa == 1) {
            c(R.id.layout_webview);
        } else {
            LogUtil.a("Track_KnitSleepDetailActivity", "pageType is not from intel sleep");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        View findViewById = findViewById(i);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sleep_relativelayout);
        ScrollView scrollView = (ScrollView) findViewById(R.id.fitness_scroll_view);
        if (relativeLayout == null || scrollView == null || findViewById == null) {
            LogUtil.h("Track_KnitSleepDetailActivity", "view is null");
            return;
        }
        int[] iArr = new int[2];
        findViewById.getLocationOnScreen(iArr);
        int i2 = iArr[1];
        relativeLayout.getLocationOnScreen(iArr);
        LogUtil.a("Track_KnitSleepDetailActivity", "view:", Integer.valueOf(i2), " relativeLayout:", Integer.valueOf(iArr[1]), " ScrollView:", Integer.valueOf(i2 - iArr[1]));
        scrollView.smoothScrollTo(0, i2 - iArr[1]);
        this.aa = -1;
    }

    private void dqO_(Uri uri) {
        String queryParameter = uri.getQueryParameter("from");
        if (TextUtils.isEmpty(queryParameter) || !"fa".equals(queryParameter)) {
            return;
        }
        String queryParameter2 = uri.getQueryParameter(ArkUIXConstants.FROM_TYPE);
        LogUtil.a("Track_KnitSleepDetailActivity", "onCreate fromType", queryParameter2);
        if (TextUtils.isEmpty(queryParameter2) || !"1".equals(queryParameter2)) {
            return;
        }
        b(uri.getQueryParameter("pullFrom"), uri.getQueryParameter("whichDate"));
        getIntent().putExtra("HAS_JUMP_TO_MANUAL", true);
    }

    private void b(String str, String str2) {
        H5ProClient.getServiceManager().registerService(SleepJsApi.class);
        if (this.i == null) {
            LogUtil.h("Track_KnitSleepDetailActivity", "jumpToManualEntry: context is null");
            return;
        }
        bzs.e().loadH5ProApp(this.i, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/sleepDataInput?pullFrom=" + str + "&whichDate=" + str2).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepDetection", mtp.d().getJsModule()).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        if (getIsNoDataFragment() && !this.k) {
            this.k = true;
            hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, "noHistorical");
            LogUtil.a("Track_KnitSleepDetailActivity", "AnalyticsValue.HEALTH_HEALTH_TAB_2010011.value() bi map: ", hashMap.toString());
            ixx.d().d(this, AnalyticsValue.HEALTH_HEALTH_TAB_2010011.value(), hashMap, 0);
            return;
        }
        d(hashMap);
    }

    private void d(final Map<String, Object> map) {
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.16
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                ObserverManagerUtil.e(this, "SLEEP_DAY_TYPE_BI_TYPE");
                if (!"SLEEP_DAY_TYPE_BI_TYPE".equals(str) || objArr.length <= 0) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof HashMap) {
                    HashMap hashMap = (HashMap) obj;
                    int e = CommonUtils.e(String.valueOf(hashMap.get("dataType")), -1);
                    map.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, e != 0 ? e != 1 ? e != 3 ? e != 4 ? e != 5 ? "" : "manual" : "normal" : "phone" : "scientific" : "noCurrent");
                    map.put("redDot", hashMap.get("key_bundle_health_red_dot"));
                    map.put("alertData", hashMap.get("MGMT_DAILY_SLEEP_PROCESS"));
                    map.put("hasSleepAidProgram", hashMap.get("OPEN_STATUS"));
                    LogUtil.a("Track_KnitSleepDetailActivity", "AnalyticsValue.HEALTH_HEALTH_TAB_2010011.value() bi map: ", map.toString());
                    ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_TAB_2010011.value(), map, 0);
                }
            }
        }, "SLEEP_DAY_TYPE_BI_TYPE");
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.a("Track_KnitSleepDetailActivity", "onSaveInstanceState");
        if (bundle != null) {
            LogUtil.a("Track_KnitSleepDetailActivity", "outState: " + bundle.toString());
        }
        super.onSaveInstanceState(bundle);
        bundle.remove("android:support:fragments");
    }

    private void dqL_(Intent intent) {
        LogUtil.a("Track_KnitSleepDetailActivity", "handleSleepDetection");
        if (nsn.ae(BaseApplication.getContext())) {
            LogUtil.a("Track_KnitSleepDetailActivity", "isPad");
            return;
        }
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("isStartPhoneSleep", false);
            boolean booleanExtra2 = intent.getBooleanExtra("isStartNow", false);
            String stringExtra = intent.getStringExtra("pullTime");
            String stringExtra2 = intent.getStringExtra(ArkUIXConstants.FROM_TYPE);
            if (TextUtils.isEmpty(stringExtra2)) {
                stringExtra2 = "-1";
            }
            if (!a(stringExtra)) {
                LogUtil.a("Track_KnitSleepDetailActivity", "judge notify scene and pull from background scene");
            } else {
                if (booleanExtra) {
                    LogUtil.a("Track_KnitSleepDetailActivity", "gotoSleepDetection");
                    f9778a = "CACHE_SLEEP_KEY";
                    mtp.d().d(this, booleanExtra2, stringExtra2);
                    return;
                }
                f9778a = "CACHE_REST_KEY";
            }
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String e = SharedPreferenceManager.e(String.valueOf(10000), "pullTime", "");
        LogUtil.a("Track_KnitSleepDetailActivity", "time is ", str, "lastTime is ", e);
        if (!TextUtils.isEmpty(e) && str.equals(e)) {
            return false;
        }
        SharedPreferenceManager.c(String.valueOf(10000), "pullTime", str);
        return true;
    }

    public static String b() {
        return f9778a;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        String str;
        LogUtil.a("Track_KnitSleepDetailActivity", "onNewIntent");
        if (intent == null) {
            LogUtil.h("Track_KnitSleepDetailActivity", "onNewIntent intent is null");
            return;
        }
        super.onNewIntent(intent);
        LogUtil.a("Track_KnitSleepDetailActivity", "intent: ", intent.toString());
        long longExtra = intent.getLongExtra("key_bundle_health_last_data_time", 0L);
        boolean booleanExtra = intent.getBooleanExtra(Constants.CORE_SLEEP_TODAY_HAS_DATA, false);
        boolean booleanExtra2 = intent.getBooleanExtra(Constants.SLEEP_TYPE_KEY, false);
        LogUtil.a("Track_KnitSleepDetailActivity", "key_bundle_health_last_data_time: ", Long.valueOf(longExtra));
        LogUtil.a("Track_KnitSleepDetailActivity", "core_sleep_today_has_data: ", Boolean.valueOf(booleanExtra));
        LogUtil.a("Track_KnitSleepDetailActivity", "sleep_type_key: ", Boolean.valueOf(booleanExtra2));
        setIntent(intent);
        dqL_(intent);
        long longExtra2 = intent.getLongExtra("endTime", 0L);
        LogUtil.a("Track_KnitSleepDetailActivity", "SCHEME_END_TIME: ", Long.valueOf(longExtra2));
        if (longExtra2 > 0) {
            str = DateFormatUtil.b(longExtra2, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT);
            e(str);
        } else {
            str = null;
        }
        f();
        LogUtil.a("Track_KnitSleepDetailActivity", "onNewIntent time = ", str);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        LogUtil.a("Track_KnitSleepDetailActivity", "onRestoreInstanceState");
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LogUtil.a("Track_KnitSleepDetailActivity", "onStart");
        HealthViewPager viewPager = getViewPager();
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(4);
        }
        if (!this.c) {
            LogUtil.a("Track_KnitSleepDetailActivity", "registerObserver");
            ObserverManagerUtil.d(this.z, "START_SLEEP_TAG");
            ObserverManagerUtil.d(this.ad, "FINISH_SLEEP_TAG");
            this.c = true;
        }
        h();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public DataInfos getSubPageDataInfos(int i) {
        if (i == 0) {
            return DataInfos.CoreSleepDayDetail;
        }
        if (i == 1) {
            return DataInfos.CoreSleepWeekDetail;
        }
        if (i == 2) {
            return DataInfos.CoreSleepMonthDetail;
        }
        if (i == 3) {
            return DataInfos.CoreSleepYearDetail;
        }
        return DataInfos.NoDataPlaceHolder;
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        LogUtil.a("Track_KnitSleepDetailActivity", "onRestart");
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_KnitSleepDetailActivity", "onResume");
        if (CommonUtil.d(ComponentInfo.PluginSleepDetection_S_0, this)) {
            LogUtil.a("Track_KnitSleepDetailActivity", "AlarmService running ");
            mtp.d().d(this, false, "-1");
        }
        this.h = System.currentTimeMillis();
        if (this.v == null) {
            this.v = new LastTimeHealthDataReader<>(this, this.w);
        }
        y(this);
        g();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "onConfigurationChanged newConfig ", configuration, " mContainer ", this.f, " mBootPageHelper ", this.d, " mHasBedScienceSleep ", Boolean.valueOf(this.m));
        SleepBootPagerHelper sleepBootPagerHelper = this.d;
        if (sleepBootPagerHelper == null || this.f == null || !this.m) {
            return;
        }
        sleepBootPagerHelper.b(true);
        this.f.removeAllViews();
        this.d.dsi_(this.f);
    }

    private void y(KnitSleepDetailActivity knitSleepDetailActivity) {
        Intent intent;
        if (this.x == null && (intent = knitSleepDetailActivity.getIntent()) != null && intent.hasExtra("key_is_open_sleep_management")) {
            d(Boolean.valueOf(intent.getBooleanExtra("key_is_open_sleep_management", false)));
        }
        d(pob.a((Boolean) null));
        pob.d(new IBaseResponseCallback() { // from class: plf
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.a("Track_KnitSleepDetailActivity", "get open status :", obj);
            }
        });
    }

    private void d(Boolean bool) {
        Boolean bool2 = this.x;
        if (bool2 == null || bool2 != bool || (Boolean.FALSE.equals(this.x) && this.ab == 0)) {
            this.x = bool;
            b(bool);
            a(this, bool);
        }
    }

    private void aa() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("Track_KnitSleepDetailActivity", "setBiEventFromDeepLink intent = null");
            return;
        }
        Uri zs_ = AppRouterUtils.zs_(intent);
        this.l = intent.getIntExtra("from", this.l);
        if (zs_ != null) {
            String queryParameter = zs_.getQueryParameter("from");
            if ("fa".equals(queryParameter)) {
                this.l = 5;
            } else if (this.l == -1) {
                this.l = CommonUtils.e(queryParameter, -1);
            }
        }
        LogUtil.a("Track_KnitSleepDetailActivity", "setBiEventFromDeepLink uri = ", zs_, "from=", Integer.valueOf(this.l));
    }

    /* loaded from: classes6.dex */
    static class a implements HiSubscribeListener {
        private final WeakReference<KnitSleepDetailActivity> b;

        a(KnitSleepDetailActivity knitSleepDetailActivity) {
            this.b = new WeakReference<>(knitSleepDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            KnitSleepDetailActivity knitSleepDetailActivity = this.b.get();
            if (knitSleepDetailActivity == null || knitSleepDetailActivity.isFinishing() || knitSleepDetailActivity.isDestroyed()) {
                ReleaseLogUtil.d("Track_KnitSleepDetailActivity", "InnerHiSubscribeListener onResult activity ", knitSleepDetailActivity);
            } else {
                ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "InnerHiSubscribeListener onResult successList ", list);
                knitSleepDetailActivity.af = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (!ArkUIXConstants.DELETE.equals(str)) {
                ReleaseLogUtil.d("Track_KnitSleepDetailActivity", "InnerHiSubscribeListener onChange changeKey ", str, " type ", Integer.valueOf(i));
            } else {
                ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "InnerHiSubscribeListener onChange type ", Integer.valueOf(i));
                nhj.b(nhj.f(), System.currentTimeMillis());
            }
        }
    }

    void b(final Boolean bool) {
        HandlerExecutor.e(new Runnable() { // from class: pll
            @Override // java.lang.Runnable
            public final void run() {
                KnitSleepDetailActivity.this.e(bool);
            }
        });
    }

    public /* synthetic */ void e(Boolean bool) {
        if (Boolean.TRUE.equals(bool)) {
            LogUtil.a("Track_KnitSleepDetailActivity", "already open");
            this.ab = jdl.t(System.currentTimeMillis());
            this.w.e(this, false);
            this.w.a(this);
            y();
            e(this.l);
        }
        this.v.b(LastTimeHealthDataReader.CardData.SLEEP);
    }

    private void a(KnitSleepDetailActivity knitSleepDetailActivity, Boolean bool) {
        if (!knitSleepDetailActivity.q) {
            LogUtil.h("Track_KnitSleepDetailActivity", "not first onresume, return");
            return;
        }
        knitSleepDetailActivity.q = false;
        if (Boolean.TRUE.equals(bool)) {
            long currentTimeMillis = System.currentTimeMillis();
            pob.e((SleepJobService.e) null, jdl.u(currentTimeMillis), jdl.f(currentTimeMillis));
        }
        pob.b(bool);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        char c2;
        super.onPause();
        LogUtil.a("Track_KnitSleepDetailActivity", "onPause");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("startTime", Long.valueOf(this.h));
        hashMap.put("time", Long.valueOf(System.currentTimeMillis() - this.h));
        hashMap.put("type", this.g);
        String str = this.g;
        str.hashCode();
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 51:
                if (str.equals("3")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, this.aj);
        } else if (c2 == 1) {
            hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, this.ak);
        } else if (c2 == 2) {
            hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, this.an);
        }
        gge.e(AnalyticsValue.HEALTH_SLEEP_WEEK_MONTH_YEAR_TIME_21300033.value(), hashMap);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.a("Track_KnitSleepDetailActivity", "onStop, isRecordSleep: ", Boolean.valueOf(this.e));
        if (this.e) {
            ab();
        }
    }

    private void ab() {
        LogUtil.a("Track_KnitSleepDetailActivity", "startTimer");
        this.ah = new Timer("Track_KnitSleepDetailActivity");
        TimerTask timerTask = new TimerTask() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.19
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LogUtil.a("Track_KnitSleepDetailActivity", "KnitSleepDetailActivity finish");
                KnitSleepDetailActivity.this.finish();
            }
        };
        this.ae = timerTask;
        this.ah.schedule(timerTask, 601000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("Track_KnitSleepDetailActivity", "cancelTimer");
        Timer timer = this.ah;
        if (timer != null) {
            timer.cancel();
            this.ah = null;
        }
        TimerTask timerTask = this.ae;
        if (timerTask != null) {
            timerTask.cancel();
            this.ae = null;
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_KnitSleepDetailActivity", "onDestroy");
        if (koq.c(this.af)) {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.af, new HiUnSubscribeListener() { // from class: plh
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("Track_KnitSleepDetailActivity", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
        }
        if (this.ag != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.getContext(), this.ag);
        }
        pqm.c(this.j);
        if (this.c) {
            LogUtil.a("Track_KnitSleepDetailActivity", "unregisterObserver");
            ObserverManagerUtil.e(this.z, "START_SLEEP_TAG");
            ObserverManagerUtil.e("FINISH_SLEEP_TAG");
            this.c = false;
        }
        ObserverManagerUtil.e("SLEEP_DAY_TYPE_BI_TYPE");
        ObserverManagerUtil.e("SLEEP_CHART_NOW_DATE");
        ObserverManagerUtil.e(this.s, "INTEL_SECTION_LOADED");
        h();
        ObserverManagerUtil.c("KNIT_SLEEP_ACTIVITY_DESTROY", new Object[0]);
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public Fragment onCreateEmptyFragment(KnitSubPageConfig knitSubPageConfig) {
        if (knitSubPageConfig == null) {
            this.o = null;
        } else {
            Bundle bundle = new Bundle();
            int resPosId = knitSubPageConfig.getResPosId();
            String e = moj.e(knitSubPageConfig);
            IPageResTrigger extra = getResTrigger(getPageType(), resPosId, false).setExtra(bundle);
            if (extra instanceof HealthPageResTrigger) {
                this.n = (HealthPageResTrigger) extra;
            }
            this.o = KnitFragment.newInstance(e, this.n);
        }
        return this.o;
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configTitleBar(final CustomTitleBar customTitleBar) {
        this.ai = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_detail_title_txtsleep_value));
        customTitleBar.setRightButtonVisibility(0);
        customTitleBar.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        final PopMenuManager popMenuManager = new PopMenuManager() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.20
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.ui.main.stories.template.PopMenuManager
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public CustomTitleBar getTitleBar() {
                return customTitleBar;
            }

            @Override // com.huawei.ui.main.stories.template.IMenuItemManager
            public List<ryf> getMenuItemList() {
                return KnitSleepDetailActivity.this.i();
            }
        };
        final HashMap hashMap = new HashMap(16);
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KnitSleepDetailActivity.this.c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "2");
                hashMap.put("click", "1");
                gge.e(AnalyticsValue.HEALTH_SLEEP_SHARE_DAY_DETAIL_21300034.value(), hashMap);
                popMenuManager.showPopMenu();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ryf> i() {
        ryf r = r();
        ryf s = s();
        ryf o = o();
        ryf m = m();
        ryf l = l();
        ArrayList<ryf> arrayList = new ArrayList<>();
        if (this.ac) {
            arrayList.add(r);
        }
        if (efb.b(BaseApplication.getContext())) {
            arrayList.add(s);
            arrayList.add(o);
        }
        if (!EnvironmentInfo.k()) {
            arrayList.add(m);
        }
        if (VersionControlUtil.isShowSmartSleepImprovement()) {
            arrayList.add(k());
        }
        arrayList.add(l);
        if (pqm.c()) {
            arrayList.add(n());
        }
        a(arrayList);
        if (pqm.c() && !pqm.b(this.j)) {
            arrayList.add(new ryf(File.separator, null));
            arrayList.add(new ryf(getResources().getString(R$string.IDS_service_express_card), null));
        }
        return arrayList;
    }

    private ryf k() {
        return new ryf(getResources().getString(R$string.IDS_Intel_sleep_improvement), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.17
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.u();
            }
        });
    }

    private ryf l() {
        return new ryf(getResources().getString(R$string.IDS_privacy_all_data), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.24
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.p();
            }
        });
    }

    private ryf n() {
        return new ryf(getResources().getString(R$string.IDS_service_express_card), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.1
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                pqm.d(KnitSleepDetailActivity.this.j);
                pqm.c(KnitSleepDetailActivity.this.j, 500);
            }
        });
    }

    private ryf m() {
        return new ryf(getResources().getString(R$string.IDS_core_sleep_switch), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.3
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.q();
            }
        });
    }

    private ryf o() {
        return new ryf(getResources().getString(R$string.IDS_daydream_snore_favorites), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.4
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.t();
            }
        });
    }

    private ryf s() {
        return new ryf(getResources().getString(R$string.IDS_sleep_setting), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.2
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.v();
            }
        });
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void showEmptyFragment() {
        super.showEmptyFragment();
        if (this.mSubTabWidget != null) {
            this.mSubTabWidget.setVisibility(8);
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void showNonEmptyFragment() {
        super.showNonEmptyFragment();
        if (this.mSubTabWidget != null) {
            this.mSubTabWidget.setVisibility(0);
        }
    }

    private ryf r() {
        return new ryf(getResources().getString(R$string.IDS_motiontrack_share_activity_btn_text), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.8
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.w();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        LoginInit.getInstance(this.i).browsingToLogin(new IBaseResponseCallback() { // from class: plm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                KnitSleepDetailActivity.this.c(i, obj);
            }
        }, null);
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            if (PermissionUtil.c()) {
                z();
                return;
            } else {
                PermissionUtil.b(this.i, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this.i) { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.7
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        KnitSleepDetailActivity.this.z();
                    }
                });
                return;
            }
        }
        ReleaseLogUtil.d("Track_KnitSleepDetailActivity", "jumpToShare browsingToLogin not login ", Integer.valueOf(i));
    }

    public void d(String[] strArr) {
        this.aj = strArr;
    }

    public void c(String[] strArr) {
        this.ak = strArr;
    }

    public void a(String[] strArr) {
        this.an = strArr;
    }

    private void a(ArrayList<ryf> arrayList) {
        arrayList.add(new ryf(getResources().getString(R$string.IDS_fitness_core_sleep_explain_title), new IPopMenuItemClick() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.9
            @Override // com.huawei.ui.main.stories.template.data.IPopMenuItemClick
            public void onItemClick() {
                KnitSleepDetailActivity.this.x();
            }
        }));
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void configExtraView(LinearLayout linearLayout) {
        if (efb.b()) {
            this.f = linearLayout;
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public boolean getExtra(Bundle bundle) {
        Intent intent = getIntent();
        this.w = new c(this, bundle, null);
        if (intent != null) {
            this.ab = intent.getLongExtra("key_bundle_health_last_data_time", 0L);
            this.t = intent.getBooleanExtra(Constants.CORE_SLEEP_TODAY_HAS_DATA, false);
            this.r = intent.getBooleanExtra(Constants.SLEEP_TYPE_KEY, false);
            this.al = intent.getLongExtra("endTime", 0L);
            this.y = intent.getStringExtra("jumpFromFileSyncNotify");
            Uri zs_ = AppRouterUtils.zs_(intent);
            if (zs_ != null) {
                this.aa = SleepServiceImpl.dRv_(zs_);
            }
        }
        pom.d().c(this.r, this.t);
        if ("jumpFromFileSyncNotify".equals(this.y)) {
            long j = this.al;
            if (j > 0) {
                LogUtil.a("Track_KnitSleepDetailActivity", "onCreate sleep notification wakeUpTimeScheme = ", Long.valueOf(j));
                gge.e(AnalyticsValue.HEALTH_JUMP_SLEEP_DETAIL_FROM_NOTIFY_2119017.value());
                this.l = 1;
                bundle.putLong("endTime", this.al);
                bundle.putString("jumpFromFileSyncNotify", this.y);
            }
        }
        boolean z = !VersionControlUtil.isSupportSleepManagement() && this.ab > 0;
        boolean ac = jdl.ac(this.ab);
        LogUtil.a("Track_KnitSleepDetailActivity", "getExtra jump, hasQueriedData:", Boolean.valueOf(z), " hasTodayData:", Boolean.valueOf(ac));
        if (z || ac || this.al > 0) {
            bundle.putLong("key_bundle_health_last_data_time", this.ab);
            bundle.putBoolean("key_is_open_sleep_management", Boolean.TRUE.equals(this.x));
            bundle.putLong("endTime", this.al);
            hideEmptyFragment();
            showNonEmptyFragment();
            this.am = true;
            return true;
        }
        if (Utils.o()) {
            hideEmptyFragment();
            showNonEmptyFragment();
            this.am = true;
            return true;
        }
        this.am = false;
        return false;
    }

    public void e(String str) {
        LogUtil.a("Track_KnitSleepDetailActivity", "enter jumpToDetailDay().");
        ObserverManagerUtil.c("SLEEP_MESSAGE_JUMP_TAG", str);
        getViewPager().setCurrentItem(0);
    }

    public static void b(Context context, boolean z, boolean z2, long j, boolean z3) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) KnitSleepDetailActivity.class);
        intent.putExtra(Constants.CORE_SLEEP_TODAY_HAS_DATA, z);
        intent.putExtra(Constants.SLEEP_TYPE_KEY, z2);
        intent.putExtra("key_bundle_health_last_data_time", j);
        intent.putExtra("isStartPhoneSleep", z3);
        intent.putExtra("from", 8);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Track_KnitSleepDetailActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    public static void dqM_(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) KnitSleepDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Track_KnitSleepDetailActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    public boolean c() {
        return this.r;
    }

    protected void a() {
        if (getViewPager() != null) {
            getViewPager().setScrollHeightArea(200);
        }
        cancelAdaptRingRegion();
        cancelMarginAdaptation();
        ObserverManagerUtil.d(this.s, "INTEL_SECTION_LOADED");
        this.j = pqm.dsg_(this, findViewById(R.id.hwappbarpattern_ok_icon), 1);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (efb.b()) {
            f();
            SleepBootPagerHelper sleepBootPagerHelper = new SleepBootPagerHelper(BaseApplication.getContext());
            this.d = sleepBootPagerHelper;
            sleepBootPagerHelper.setHealthPageResTrigger(this.n);
            LogUtil.a("Track_KnitSleepDetailActivity", "boot page cost: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void refreshNoDataTitleBar(CustomTitleBar customTitleBar) {
        super.refreshNoDataTitleBar(customTitleBar);
        if (isDeviceConnected()) {
            customTitleBar.setTitleBarBackgroundColor(getColor(R.color._2131298875_res_0x7f090a3b));
            customTitleBar.setLeftButtonTextColor(ContextCompat.getDrawable(this, scw.b()), getColor(R.color._2131299236_res_0x7f090ba4), nsf.h(R$string.accessibility_go_back));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "3");
        Intent intent = new Intent();
        intent.setFlags(536870912);
        intent.setClass(this, SleepDescActivity.class);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        PrivacyDataModelActivity.b(this, new PageModelArgs(103, "PrivacyDataConstructor", 3, 1));
        c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "4");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        mtp.d().d(this.i, "#/Favorites");
        c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "7");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogUtil.a("Track_KnitSleepDetailActivity", "go to IntelSleepManagement H5");
        c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "8");
        ple.d("#/about");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        mtp.d().d(this.i, "#/SleepSetting");
        c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "6");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (pqg.h()) {
            sdc.e(this.i, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_connect);
            return;
        }
        DeviceInfo d = jpt.d("Track_KnitSleepDetailActivity");
        if (!pqg.j() || d == null) {
            sdc.e(this.i, R$string.IDS_device_wifi_go_connect, R$string.IDS_core_sleep_not_support);
            return;
        }
        String multiLinkBleMac = d.getMultiLinkBleMac();
        Intent intent = new Intent();
        intent.setClassName(this.i, "com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity");
        intent.setFlags(268435456);
        if (!TextUtils.isEmpty(multiLinkBleMac)) {
            intent.putExtra("device_id", multiLinkBleMac);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        gnm.aPB_(this.i, intent);
        c(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), "5");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_SLEEP_SHARE_DAY_DETAIL_21300034.value(), hashMap);
        createShareBitmap();
        e();
        this.shareContainer.post(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.10
            @Override // java.lang.Runnable
            public void run() {
                KnitSleepDetailActivity knitSleepDetailActivity = KnitSleepDetailActivity.this;
                knitSleepDetailActivity.a(knitSleepDetailActivity.shareContainer.getWidth());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        int i2;
        int i3;
        ImageView imageView = new ImageView(this.i);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Drawable drawable = ContextCompat.getDrawable(this.i, R.drawable._2131430450_res_0x7f0b0c32);
        if (drawable != null) {
            i2 = drawable.getIntrinsicWidth();
            i3 = drawable.getIntrinsicHeight();
        } else {
            i2 = 0;
            i3 = 0;
        }
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(i, (int) (i * (i3 / i2))));
        imageView.setImageDrawable(drawable);
        RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.item_for_share_sleep_bitmap_top_layout, this.shareContainer, false);
        relativeLayout.addView(imageView, 0);
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        String shareNickName = socialShareCenterApi.getShareNickName();
        HealthTextView healthTextView = (HealthTextView) relativeLayout.findViewById(R.id.core_sleep_name);
        if (TextUtils.isEmpty(shareNickName)) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setText(socialShareCenterApi.getShareNickName());
        }
        socialShareCenterApi.updateShareUserView("Track_KnitSleepDetailActivity", healthTextView, relativeLayout.findViewById(R.id.core_sleep_share_user_icon));
        dqJ_(userInfo, relativeLayout, shareNickName);
    }

    private void dqJ_(UserInfomation userInfomation, RelativeLayout relativeLayout, String str) {
        String picPath = userInfomation != null ? userInfomation.getPicPath() : null;
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.core_sleep_share_user_icon);
        if (!TextUtils.isEmpty(picPath)) {
            Bitmap cIe_ = nrf.cIe_(this.i, picPath);
            if (cIe_ != null) {
                imageView.setImageBitmap(cIe_);
            } else {
                LogUtil.h("Track_KnitSleepDetailActivity", "handleWhenGetUserInfoSuccess()bmp != null ");
            }
        } else {
            LogUtil.h("Track_KnitSleepDetailActivity", "handleWhenGetUserInfoSuccess()! headImgPath is null! ");
        }
        if (Utils.g() && TextUtils.isEmpty(str)) {
            imageView.setVisibility(4);
        }
        if (!StringUtils.g(this.currentDayMsg)) {
            ((HealthTextView) relativeLayout.findViewById(R.id.core_sleep_date)).setText(this.currentDayMsg);
        }
        SleepShareBriefView sleepShareBriefView = (SleepShareBriefView) relativeLayout.findViewById(R.id.sleep_share_brief_view);
        if (this.sleepScoringData != null && this.sleepEfficientDesc != null) {
            String string = this.sleepScoringData.getString(KnitHealthDetailActivity.KEY_SLEEP_SCORING);
            String string2 = this.sleepScoringData.getString(KnitHealthDetailActivity.KEY_SLEEP_STATUS);
            String string3 = this.sleepScoringData.getString(KnitHealthDetailActivity.KEY_SLEEP_SCORING_DESC);
            String string4 = this.sleepScoringData.getString(KnitHealthDetailActivity.KEY_SLEEP_COMMON_SLEEP);
            String string5 = this.sleepEfficientDesc.getString(KnitHealthDetailActivity.KEY_SLEEP_EFFICIENT);
            if (!TextUtils.isEmpty(string)) {
                sleepShareBriefView.setScoring(string, string3);
            } else if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(string5)) {
                sleepShareBriefView.setStatus(string2);
            } else if (!TextUtils.isEmpty(string4) && TextUtils.isEmpty(string2) && TextUtils.isEmpty(string5)) {
                sleepShareBriefView.setSleepTime(string4);
            } else if (!TextUtils.isEmpty(string5) && !TextUtils.isEmpty(string2)) {
                sleepShareBriefView.setSleepEfficiency(string5, string2);
            } else {
                LogUtil.h("Track_KnitSleepDetailActivity", "no scoring data need to set!");
            }
        }
        this.shareContainer.addView(relativeLayout, 0);
        relativeLayout.post(new Runnable() { // from class: pli
            @Override // java.lang.Runnable
            public final void run() {
                KnitSleepDetailActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        dqK_(this.shareContainer.getHoleViewBitmap());
    }

    private void e() {
        this.shareContainer.addView(getLayoutInflater().inflate(R.layout.item_for_share_sleep_bitmap_bottom_layout, (ViewGroup) this.shareContainer, false));
    }

    private void dqK_(Bitmap bitmap) {
        fdu fduVar;
        String dqN_ = bitmap != null ? dqN_(bitmap) : null;
        if (dqN_ != null && dqN_.length() == 0) {
            LogUtil.h("Track_KnitSleepDetailActivity", "The path is invalid, return");
            return;
        }
        try {
            if (Utils.g()) {
                fduVar = new fdu(1);
                fduVar.awp_(bitmap);
            } else {
                fdu fduVar2 = new fdu(4);
                fduVar2.d(dqN_);
                fduVar = fduVar2;
            }
            fduVar.c((String) null);
            fduVar.a((String) null);
            fduVar.f(null);
            fduVar.e(1);
            fduVar.i(false);
            fduVar.b(7);
            fduVar.b("11");
            fduVar.c(false);
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, BaseApplication.getContext());
        } catch (OutOfMemoryError e) {
            LogUtil.h("Track_KnitSleepDetailActivity", "shareTrackData ", e.getMessage());
        }
    }

    private String dqN_(Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        String str = "";
        File file = new File(jcu.f);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.h("Track_KnitSleepDetailActivity", "saveBmpToFile:mkdirs error ");
        }
        File file2 = new File(file, "sleep_share_img.jpg");
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                String canonicalPath = file2.getCanonicalPath();
                try {
                    if (!CommonUtil.a(file2, canonicalPath)) {
                        LogUtil.h("Track_KnitSleepDetailActivity", "invalidate file path");
                        return "";
                    }
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                        fileOutputStream.flush();
                        try {
                            fileOutputStream.close();
                            return canonicalPath;
                        } catch (IOException e) {
                            LogUtil.h("Track_KnitSleepDetailActivity", "close IOException ", e.getMessage());
                            return canonicalPath;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream2 = fileOutputStream;
                        str = canonicalPath;
                        LogUtil.h("Track_KnitSleepDetailActivity", "saveBmpToFile:IOException ", e.getMessage());
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e3) {
                                LogUtil.h("Track_KnitSleepDetailActivity", "close IOException ", e3.getMessage());
                            }
                        }
                        return str;
                    } catch (IllegalArgumentException e4) {
                        e = e4;
                        fileOutputStream2 = fileOutputStream;
                        str = canonicalPath;
                        LogUtil.h("Track_KnitSleepDetailActivity", "saveBmpToFile:IllegalArgumentException ", e.getMessage());
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e5) {
                                LogUtil.h("Track_KnitSleepDetailActivity", "close IOException ", e5.getMessage());
                            }
                        }
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                                LogUtil.h("Track_KnitSleepDetailActivity", "close IOException ", e6.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                    e = e7;
                } catch (IllegalArgumentException e8) {
                    e = e8;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (IOException e9) {
            e = e9;
        } catch (IllegalArgumentException e10) {
            e = e10;
        }
    }

    public void d(boolean z) {
        if ("0".equals(this.g)) {
            if (z) {
                this.ac = false;
            } else {
                this.ac = true;
            }
        }
        this.p = z;
    }

    /* loaded from: classes6.dex */
    public static class c implements IBaseResponseCallback {
        private Bundle c;
        private final WeakReference<KnitSleepDetailActivity> d;

        /* synthetic */ c(KnitSleepDetailActivity knitSleepDetailActivity, Bundle bundle, AnonymousClass5 anonymousClass5) {
            this(knitSleepDetailActivity, bundle);
        }

        private c(KnitSleepDetailActivity knitSleepDetailActivity, Bundle bundle) {
            this.d = new WeakReference<>(knitSleepDetailActivity);
            this.c = bundle;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse, reason: merged with bridge method [inline-methods] */
        public void d(final int i, final Object obj) {
            KnitSleepDetailActivity knitSleepDetailActivity;
            if (!HandlerExecutor.c()) {
                HandlerExecutor.a(new Runnable() { // from class: plo
                    @Override // java.lang.Runnable
                    public final void run() {
                        KnitSleepDetailActivity.c.this.d(i, obj);
                    }
                });
                return;
            }
            WeakReference<KnitSleepDetailActivity> weakReference = this.d;
            if (weakReference == null || (knitSleepDetailActivity = weakReference.get()) == null) {
                return;
            }
            knitSleepDetailActivity.m = false;
            if (Utils.o()) {
                e(knitSleepDetailActivity, true);
            } else if (!(obj instanceof HiHealthData)) {
                if (Boolean.TRUE.equals(knitSleepDetailActivity.x)) {
                    return;
                }
                b(knitSleepDetailActivity);
                LogUtil.h("Track_KnitSleepDetailActivity", "read last data time from database,mLastTimestamp=0");
            } else {
                HiHealthData hiHealthData = (HiHealthData) obj;
                knitSleepDetailActivity.ab = hiHealthData.getEndTime();
                LogUtil.a("Track_KnitSleepDetailActivity", "read last data time from database,mLastTimestamp=", Long.valueOf(knitSleepDetailActivity.ab), "hihealthdata is", hiHealthData.toString());
                if (Boolean.TRUE.equals(knitSleepDetailActivity.x)) {
                    if (knitSleepDetailActivity.ab > 0) {
                        knitSleepDetailActivity.m = hiHealthData.getBoolean("HAS_BED_SCIENCE");
                        knitSleepDetailActivity.d.b(knitSleepDetailActivity.m);
                        return;
                    }
                    return;
                }
                if (knitSleepDetailActivity.ab != 0) {
                    knitSleepDetailActivity.m = hiHealthData.getBoolean("HAS_BED_SCIENCE");
                    e(knitSleepDetailActivity, true);
                } else {
                    b(knitSleepDetailActivity);
                }
            }
            a(knitSleepDetailActivity);
            knitSleepDetailActivity.e(knitSleepDetailActivity.l);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(KnitSleepDetailActivity knitSleepDetailActivity) {
            if (this.c != null && !knitSleepDetailActivity.am) {
                LogUtil.a("Track_KnitSleepDetailActivity", "reset start time, mFrom: ", Integer.valueOf(knitSleepDetailActivity.l));
                knitSleepDetailActivity.am = true;
                this.c.putLong("key_bundle_health_last_data_time", knitSleepDetailActivity.ab);
                this.c.putBoolean("key_is_open_sleep_management", Boolean.TRUE.equals(knitSleepDetailActivity.x));
                this.c.putInt("from", knitSleepDetailActivity.l);
                knitSleepDetailActivity.onBundleArrived(this.c);
                return;
            }
            LogUtil.a("Track_KnitSleepDetailActivity", "configHealthDetailActivity");
            knitSleepDetailActivity.configHealthDetailActivity();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(KnitSleepDetailActivity knitSleepDetailActivity, boolean z) {
            if (z) {
                knitSleepDetailActivity.d.b(knitSleepDetailActivity.m);
            }
            knitSleepDetailActivity.hideEmptyFragment();
            knitSleepDetailActivity.showNonEmptyFragment();
            knitSleepDetailActivity.refreshTitleBar(true);
        }

        private void b(KnitSleepDetailActivity knitSleepDetailActivity) {
            knitSleepDetailActivity.d.b(false);
            knitSleepDetailActivity.showEmptyFragment();
            knitSleepDetailActivity.hideNonEmptyFragment();
            knitSleepDetailActivity.refreshTitleBar(false);
        }
    }

    private void e(final long j, final List<Integer> list) {
        this.ag = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    ReleaseLogUtil.d("Track_KnitSleepDetailActivity", "registerSyncStatusBroadcastReceiver onReceive intent is null");
                    return;
                }
                String action = intent.getAction();
                if (!"com.huawei.hihealth.action_sync_data_result".equals(action)) {
                    ReleaseLogUtil.d("Track_KnitSleepDetailActivity", "registerSyncStatusBroadcastReceiver onReceive action ", action);
                    return;
                }
                boolean booleanExtra = intent.getBooleanExtra("sync_data_result_success", true);
                long longExtra = intent.getLongExtra("sync_data_result_id", 0L);
                ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "registerSyncStatusBroadcastReceiver onReceive isSyncSuccess ", Boolean.valueOf(booleanExtra), " syncDataResultSyncId ", Long.valueOf(longExtra), " syncId ", Long.valueOf(j));
                if (j == longExtra) {
                    ObserverManagerUtil.c("SLEEP_SUMMARY_SYNC_STATUS", Boolean.valueOf(booleanExtra));
                    return;
                }
                String stringExtra = intent.getStringExtra("sync_data_result_type");
                boolean e = nhj.e((List<Integer>) list, stringExtra);
                ReleaseLogUtil.e("Track_KnitSleepDetailActivity", "registerSyncStatusBroadcastReceiver onReceive isSyncSleepSummaryType ", Boolean.valueOf(e), " syncDataResultType ", stringExtra, " syncDataTypeList ", list);
                if (e) {
                    ObserverManagerUtil.c("SLEEP_SUMMARY_SYNC_STATUS", Boolean.valueOf(booleanExtra));
                }
            }
        };
        BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.ag, new IntentFilter("com.huawei.hihealth.action_sync_data_result"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.i, str, hashMap, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public void onKnitPageSelected(int i) {
        char c2;
        super.onKnitPageSelected(i);
        boolean z = false;
        if (!"0".equals(this.g)) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            hashMap.put("startTime", Long.valueOf(this.h));
            hashMap.put("time", Long.valueOf(System.currentTimeMillis() - this.h));
            hashMap.put("type", this.g);
            String str = this.g;
            str.hashCode();
            switch (str.hashCode()) {
                case 49:
                    if (str.equals("1")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 50:
                    if (str.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 51:
                    if (str.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, this.aj);
            } else if (c2 == 1) {
                hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, this.ak);
            } else if (c2 == 2) {
                hashMap.put(FunctionSetBeanReader.BI_HAS_DATA_TYPE, this.an);
            }
            gge.e(AnalyticsValue.HEALTH_SLEEP_WEEK_MONTH_YEAR_TIME_21300033.value(), hashMap);
        }
        this.h = System.currentTimeMillis();
        this.g = String.valueOf(i);
        if (i == 0 && !this.p && this.o != null) {
            z = true;
        }
        this.ac = z;
    }

    private void j() {
        pqb.c();
        if (pqb.d()) {
            HandlerExecutor.a(new Runnable() { // from class: plj
                @Override // java.lang.Runnable
                public final void run() {
                    KnitSleepDetailActivity.this.ac();
                }
            });
            pqb.d(System.currentTimeMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        SleepAudioClearDialog a2 = new SleepAudioClearDialog.Builder(this.i).d(nsf.b(R$string.IDS_sleep_audio_clear_tltle, pqb.e())).dwW_(R$string.IDS_sleep_audio_clear_goto_claer, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("Track_KnitSleepDetailActivity", "onClick too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("Track_KnitSleepDetailActivity", "jump to ClearDataCacheActivity");
                Intent intent = new Intent(KnitSleepDetailActivity.this, (Class<?>) ClearDataCacheActivity.class);
                intent.putExtra("showSleepAlertDialog", true);
                KnitSleepDetailActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dwW_(R$string.IDS_sleep_audio_clear_auto_claer, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("Track_KnitSleepDetailActivity", "onClick too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("Track_KnitSleepDetailActivity", "jump to DreamTalkSetting");
                    mtp.d().d(KnitSleepDetailActivity.this.i, "#/DreamTalkSetting");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).dwW_(R$string.IDS_sleep_audio_clear_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("Track_KnitSleepDetailActivity", "onClick too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("Track_KnitSleepDetailActivity", "cancel");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).a();
        a2.setCancelable(true);
        if (isFinishing()) {
            return;
        }
        a2.show();
    }

    @Override // com.huawei.health.knit.ui.KnitHealthDetailActivity
    public String getLogTag() {
        return "Track_KnitSleepDetailActivity";
    }
}
