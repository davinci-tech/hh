package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.featureuserprofile.route.activity.MyRouteActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.sport.utils.RunPopularRoutesUtil;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteConstants;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteLocationHelper;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.model.ICityLatLonCallBack;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.ash;
import defpackage.eil;
import defpackage.emc;
import defpackage.emp;
import defpackage.emq;
import defpackage.emz;
import defpackage.gyq;
import defpackage.gzh;
import defpackage.gzi;
import defpackage.hjd;
import defpackage.hpp;
import defpackage.ixx;
import defpackage.ktj;
import defpackage.npq;
import defpackage.npv;
import defpackage.nqx;
import defpackage.nrv;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class RunningRouteListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f3661a;
    private TextView b;
    private ViewGroup c;
    private ImageView d;
    private HotPathCityInfo e;
    private boolean f;
    private boolean g = true;
    private RunningRouteListFragment h;
    private boolean i;
    private boolean j;
    private boolean k;
    private RunningRouteLocationHelper l;
    private ViewGroup m;
    private boolean n;
    private RunningRouteListFragment o;
    private LinearLayout p;
    private HealthSubTabWidget q;
    private int r;
    private int s;
    private nqx t;
    private CustomTitleBar u;
    private HealthViewPager v;

    public static void d(Context context, HotPathCityInfo hotPathCityInfo, boolean z, gyq gyqVar) {
        gzi.d(hotPathCityInfo);
        Intent intent = new Intent(context, (Class<?>) RunningRouteListActivity.class);
        intent.putExtra("IS_FROM_DEEPLINK", z);
        intent.putExtra("ROUTE_INFO", gyqVar);
        context.startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_running_route_list);
        getWindow().setBackgroundDrawable(null);
        k();
        s();
        q();
        o();
        if (this.n) {
            this.r = 1;
        }
        t();
        p();
        n();
    }

    private void o() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("Sport_RunningPathListActivity", "intent is null");
            return;
        }
        Uri zs_ = AppRouterUtils.zs_(intent);
        if (zs_ == null || zs_.isOpaque()) {
            LogUtil.h("Sport_RunningPathListActivity", "uri is invalid");
        } else {
            this.r = CommonUtils.h(zs_.getQueryParameter("pathClass"));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void d() {
        if (this.m.getVisibility() == 0) {
            this.m.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        this.m.setVisibility(0);
    }

    private void k() {
        this.n = Utils.o();
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        this.u = customTitleBar;
        if (!this.n) {
            customTitleBar.setRightThirdKeyVisibility(8);
            this.u.setRightThirdKeyBackground(ContextCompat.getDrawable(this, R.drawable._2131431370_res_0x7f0b0fca), nsf.h(R.string._2130847322_res_0x7f02265a));
            this.u.setRightThirdKeyOnClickListener(new View.OnClickListener() { // from class: hez
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunningRouteListActivity.this.bcs_(view);
                }
            });
            this.u.setRightSoftkeyVisibility(0);
            this.u.setRightSoftkeyBackground(ContextCompat.getDrawable(this, R.drawable._2131430393_res_0x7f0b0bf9), nsf.h(R.string._2130850609_res_0x7f023331));
            this.u.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: hex
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunningRouteListActivity.this.bct_(view);
                }
            });
        }
        this.u.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.u.setRightButtonClickable(true);
        this.u.setRightButtonVisibility(0);
        this.u.setRightButtonOnClickListener(new View.OnClickListener() { // from class: hfa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteListActivity.this.bcu_(view);
            }
        });
        this.u.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hey
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteListActivity.this.bcv_(view);
            }
        });
        this.u.getViewTitle().setBackgroundResource(R.drawable.hwspinner_selector_background_emui);
        this.u.getViewTitle().setOnClickListener(new View.OnClickListener() { // from class: hfe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteListActivity.this.bcw_(view);
            }
        });
        this.u.getViewTitle().setGravity(16);
        this.u.setTitleText(getString(R.string._2130840071_res_0x7f020a07));
    }

    public /* synthetic */ void bcs_(View view) {
        if (r()) {
            LogUtil.a("Sport_RunningPathListActivity", "third key click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            Intent intent = new Intent(this, (Class<?>) RunningRouteLocationSearchActivity.class);
            intent.putExtra(DistrictSearchQuery.KEYWORDS_CITY, gzi.d() == null ? "" : gzi.d().getCityName());
            startActivityForResult(intent, 1);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void bct_(View view) {
        gzh.a();
        Intent intent = new Intent(this, (Class<?>) ClockingRankActivity.class);
        intent.putExtra("IS_SHOW_PATH_DETAIL", false);
        intent.putExtra("pathClass", this.r);
        intent.putExtra("ENTRANCE_ACTIVITY", 1);
        intent.putExtra("ROUTE_INFO", h());
        startActivityForResult(intent, 1000);
        overridePendingTransition(0, 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bcu_(View view) {
        b(this.u);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bcv_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bcw_(View view) {
        if (r()) {
            LogUtil.a("Sport_RunningPathListActivity", "mTitleBar click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            RunningCitysActivity.bcm_(this, gzi.d(), 0);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void s() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.marketing_layout);
        this.p = linearLayout;
        linearLayout.setVisibility(8);
        eil.alQ_(this, this.p, 1016);
    }

    private void q() {
        if (this.n) {
            return;
        }
        findViewById(R.id.route_card_layout).setVisibility(0);
        HealthCardView healthCardView = (HealthCardView) findViewById(R.id.hw_running_route_import_routes);
        healthCardView.setVisibility(0);
        healthCardView.setOnClickListener(new View.OnClickListener() { // from class: hfc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteListActivity.this.bcy_(view);
            }
        });
        HealthCardView healthCardView2 = (HealthCardView) findViewById(R.id.hw_running_route_history_routes);
        healthCardView2.setVisibility(0);
        healthCardView2.setOnClickListener(new View.OnClickListener() { // from class: hff
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteListActivity.this.bcz_(view);
            }
        });
    }

    public /* synthetic */ void bcy_(View view) {
        if (r()) {
            LogUtil.a("Sport_RunningPathListActivity", "importRoutesCardView click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ab();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void bcz_(View view) {
        if (r()) {
            LogUtil.a("Sport_RunningPathListActivity", "historyRoutesCardView click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ad();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void p() {
        this.m = (ViewGroup) findViewById(R.id.loading_layout);
        this.c = (ViewGroup) findViewById(R.id.common_no_data_layout);
        ImageView imageView = (ImageView) findViewById(R.id.common_no_data_img);
        this.d = imageView;
        imageView.setImageTintList(null);
        this.b = (TextView) findViewById(R.id.common_no_data_text);
        HealthButton healthButton = (HealthButton) findViewById(R.id.common_no_data_btn);
        this.f3661a = healthButton;
        healthButton.setText(R.string._2130840073_res_0x7f020a09);
        this.f3661a.setOnClickListener(new View.OnClickListener() { // from class: her
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteListActivity.this.bcx_(view);
            }
        });
    }

    public /* synthetic */ void bcx_(View view) {
        nsn.ak(BaseApplication.e());
        this.k = true;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        this.q = (HealthSubTabWidget) findViewById(R.id.hw_running_route_type);
        this.v = (HealthViewPager) findViewById(R.id.hw_route_list_viewpage);
        this.t = new nqx(this, this.v, this.q);
        if (!this.n) {
            this.q.setVisibility(0);
            RunningRouteListFragment runningRouteListFragment = new RunningRouteListFragment();
            this.o = runningRouteListFragment;
            runningRouteListFragment.e(0);
            this.o.c(c());
            this.t.c(this.q.c(nsf.j(R.string._2130846048_res_0x7f022160)), this.o, this.r == 0);
        }
        RunningRouteListFragment runningRouteListFragment2 = new RunningRouteListFragment();
        this.h = runningRouteListFragment2;
        runningRouteListFragment2.e(1);
        this.h.c(c());
        this.t.c(this.q.c(nsf.j(R.string._2130847254_res_0x7f022616)), this.h, this.r == 1);
        this.v.setOffscreenPageLimit(1);
        this.v.addOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity.4
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (!RunningRouteListActivity.this.n) {
                    if (i == 1) {
                        RunningRouteListActivity.this.r = 1;
                    } else {
                        RunningRouteListActivity.this.r = 0;
                    }
                }
                ReleaseLogUtil.e("Sport_RunningPathListActivity", "onPageSelected mPathClass is ", Integer.valueOf(RunningRouteListActivity.this.r));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        RunningRouteListFragment runningRouteListFragment = this.o;
        if (runningRouteListFragment != null) {
            runningRouteListFragment.d();
        }
        RunningRouteListFragment runningRouteListFragment2 = this.h;
        if (runningRouteListFragment2 != null) {
            runningRouteListFragment2.d();
        }
        gzi.a(RunningRouteConstants.BiFromActivity.INVALID.getIndex());
    }

    private gyq h() {
        Fragment item = this.t.getItem(this.v.getCurrentItem());
        if (item instanceof RunningRouteListFragment) {
            return ((RunningRouteListFragment) item).b();
        }
        ReleaseLogUtil.d("Sport_RunningPathListActivity", "currentFrag is null");
        return new gyq(this.r);
    }

    private RunningRouteListFragment.HotPathListCallBack c() {
        return new RunningRouteListFragment.HotPathListCallBack() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity.3
            @Override // com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment.HotPathListCallBack
            public void onError(int i, String str) {
                RunningRouteListActivity.this.c(0);
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.fragment.RunningRouteListFragment.HotPathListCallBack
            public void onSuccess(emz emzVar) {
                RunningRouteListActivity.this.s = emzVar.b();
                RunningRouteListActivity runningRouteListActivity = RunningRouteListActivity.this;
                runningRouteListActivity.c(runningRouteListActivity.s);
                RunningRouteListActivity.this.i();
            }
        };
    }

    private void n() {
        gzi.e(this.r);
        RunPopularRoutesUtil.e(this, -1, new RunPopularRoutesUtil.DialogCallBack() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity.1
            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void goNext() {
            }

            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void notGoNext() {
                RunningRouteListActivity.this.finish();
            }
        });
        this.i = g();
        this.l = new RunningRouteLocationHelper();
        if (getIntent() == null) {
            return;
        }
        HotPathCityInfo hotPathCityInfo = (HotPathCityInfo) getIntent().getParcelableExtra("RUNNING_PATH_CITY_INFO");
        if (hotPathCityInfo != null) {
            gzi.d(hotPathCityInfo);
        }
        this.f = getIntent().getBooleanExtra("IS_FROM_DEEPLINK", false);
        if (gzi.d() == null) {
            gzi.d(f());
        }
    }

    private HotPathCityInfo f() {
        String b2 = ash.b("RUNNING_PATH_CITY_INFO");
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        return (HotPathCityInfo) nrv.b(b2, HotPathCityInfo.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        i();
        this.u.setTitleText(gzi.d().getCityName());
        if (gzi.d().getCityId() == null || gzi.d().getCityId().equals("INVALIDE_CITY_ID")) {
            RunningRouteListFragment runningRouteListFragment = this.o;
            if (runningRouteListFragment != null) {
                runningRouteListFragment.a();
            }
            j();
            return;
        }
        this.p.setVisibility(0);
        d();
    }

    private boolean a() {
        if (ktj.e(BaseApplication.e())) {
            return false;
        }
        RunningRouteUtils.a(2, "PositioningFailed");
        if (gzi.d() == null) {
            return false;
        }
        af();
        l();
        return true;
    }

    private void b() {
        if (PermissionUtil.e(this, PermissionUtil.PermissionType.LOCATION) != PermissionUtil.PermissionResult.GRANTED) {
            if (gzi.d() != null) {
                af();
                l();
            }
            PermissionUtil.b(this, PermissionUtil.PermissionType.LOCATION, new a());
            return;
        }
        if (a()) {
            return;
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (gzi.d() != null) {
            LogUtil.a("Sport_RunningPathListActivity", "onRequestPermissionsResult mCityInfo != null");
        } else {
            y();
        }
    }

    /* loaded from: classes4.dex */
    static class d extends UiCallback<emp> {
        private final WeakReference<RunningRouteListActivity> d;
        private final long e;

        d(RunningRouteListActivity runningRouteListActivity, long j) {
            this.d = new WeakReference<>(runningRouteListActivity);
            this.e = j;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            RunningRouteListActivity runningRouteListActivity = this.d.get();
            if (runningRouteListActivity == null) {
                LogUtil.a("Sport_RunningPathListActivity", "LocationUiCallback onFailure activity == null");
                return;
            }
            ReleaseLogUtil.e("Sport_RunningPathListActivity", "getCityInfoWithGps onFailure", Integer.valueOf(i), " info: ", str);
            if (i == -2) {
                runningRouteListActivity.d(runningRouteListActivity.getString(R.string._2130840075_res_0x7f020a0b));
            } else {
                runningRouteListActivity.b(R.drawable.pic_no_line_available, R.string._2130840075_res_0x7f020a0b);
            }
            if (runningRouteListActivity.l.isLocationFailed(i)) {
                RunningRouteUtils.a(2, "PositioningFailed");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emp empVar) {
            RunningRouteListActivity runningRouteListActivity = this.d.get();
            if (runningRouteListActivity == null) {
                LogUtil.a("Sport_RunningPathListActivity", "LocationUiCallback onSuccess activity == null");
                return;
            }
            RunningRouteUtils.b(1001, SystemClock.elapsedRealtime() - this.e);
            RunningRouteUtils.e(empVar.a());
            HotPathCityInfo c = empVar.c();
            if (c != null) {
                LogUtil.a("Sport_RunningPathListActivity", "getCityInfoWithGps onSuccess cityInfo ", c);
                if (!runningRouteListActivity.f || gzi.d() == null) {
                    LogUtil.a("Sport_RunningPathListActivity", "cityInfo is changed");
                    gzi.d(c);
                }
                runningRouteListActivity.af();
                ash.a("RUNNING_PATH_CITY_INFO", nrv.a(gzi.d()));
                runningRouteListActivity.l();
                return;
            }
            LogUtil.a("Sport_RunningPathListActivity", "getCityInfoWithGps onSuccess current city not support");
            HotPathCityInfo hotPathCityInfo = runningRouteListActivity.e;
            if (hotPathCityInfo != null) {
                if (gzi.d() == null || TextUtils.isEmpty(gzi.d().getCityName())) {
                    gzi.d(hotPathCityInfo);
                }
                String cityName = hotPathCityInfo.getCityName();
                if (!TextUtils.isEmpty(cityName)) {
                    runningRouteListActivity.u.setTitleText(cityName);
                }
                ash.a("RUNNING_PATH_CITY_INFO", nrv.a(gzi.d()));
            }
            runningRouteListActivity.c(0);
            if (!runningRouteListActivity.g()) {
                ReleaseLogUtil.d("Sport_RunningPathListActivity", "initCityInfoWithGpsRspUiCallback: no network");
                runningRouteListActivity.aa();
            } else {
                ReleaseLogUtil.d("Sport_RunningPathListActivity", "current city not support");
                runningRouteListActivity.x();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (gzi.e() == RunningRouteConstants.BiFromActivity.INVALID.getIndex()) {
            LogUtil.a("Sport_RunningPathListActivity", "jumpToListType is invalid, no bi");
            return;
        }
        if (!this.g || i <= 0) {
            return;
        }
        this.g = false;
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(gzi.e()));
        hashMap.put("sportPage", 258);
        hashMap.put("routeNum", Integer.valueOf(i));
        ixx.d().d(BaseApplication.e(), "1040077", hashMap, 0);
    }

    private void j() {
        if (gzi.d() == null || gzi.d().getCityCenter() == null) {
            return;
        }
        LogUtil.a("Sport_RunningPathListActivity", "getLocationWithGps begin");
        emc.d().getCityInfoWithGps(new emq.d().e(gzi.d().getCityCenter().getLatitude()).b(gzi.d().getCityCenter().getLongitude()).b(), new d(this, SystemClock.elapsedRealtime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.l.getLocation(null, new b(this), new d(this, SystemClock.elapsedRealtime()));
    }

    /* loaded from: classes4.dex */
    static class b implements ICityLatLonCallBack {
        private final WeakReference<RunningRouteListActivity> c;

        b(RunningRouteListActivity runningRouteListActivity) {
            this.c = new WeakReference<>(runningRouteListActivity);
        }

        @Override // com.huawei.ui.commonui.model.ICityLatLonCallBack
        public void onPointBack(npv npvVar) {
            RunningRouteListActivity runningRouteListActivity = this.c.get();
            if (runningRouteListActivity == null || npvVar == null || npvVar.d() == null) {
                return;
            }
            npq d = npvVar.d();
            RunningRouteUtils.a(2, (String) null);
            runningRouteListActivity.e = new HotPathCityInfo();
            runningRouteListActivity.e.setCityName(npvVar.b());
            runningRouteListActivity.e.setCityId("INVALIDE_CITY_ID");
            runningRouteListActivity.e.setCityCenter(new GpsPoint(d.f15429a, d.c));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        RunningRouteListFragment runningRouteListFragment = this.o;
        if (runningRouteListFragment != null) {
            runningRouteListFragment.onActivityResult(i, i2, intent);
        }
        RunningRouteListFragment runningRouteListFragment2 = this.h;
        if (runningRouteListFragment2 != null) {
            runningRouteListFragment2.onActivityResult(i, i2, intent);
        }
        if (i == 0 && i2 == -1) {
            u();
            return;
        }
        Intent intent2 = getIntent();
        if (intent2 == null) {
            LogUtil.h("Sport_RunningPathListActivity", "intent is null");
            return;
        }
        if (i != 1000) {
            if (i == 1 && i2 == -1) {
                bcr_(intent2);
                return;
            } else {
                LogUtil.a("Sport_RunningPathListActivity", "onActivityResult");
                return;
            }
        }
        LogUtil.a("Sport_RunningPathListActivity", "jump from running route map");
        if (intent2.getBooleanExtra("IS_RETURN_TO_BEFORE_ACTIVITY", false)) {
            finish();
            overridePendingTransition(0, 0);
        } else if (!g()) {
            ReleaseLogUtil.d("Sport_RunningPathListActivity", "onActivityResult: no network");
            aa();
        } else if (gzi.d() != null) {
            LogUtil.a("Sport_RunningPathListActivity", "onActivityResult doFilter");
            b(gzi.d());
        }
    }

    private void u() {
        HotPathCityInfo d2 = gzi.d();
        if (d2 == null) {
            return;
        }
        b(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HotPathCityInfo hotPathCityInfo) {
        gzi.d(hotPathCityInfo);
        af();
        ash.a("RUNNING_PATH_CITY_INFO", nrv.a(gzi.d()));
    }

    private void b(CustomTitleBar customTitleBar) {
        ArrayList arrayList = new ArrayList(Arrays.asList(getResources().getString(R.string._2130840070_res_0x7f020a06)));
        if (!this.n) {
            arrayList.add(getResources().getString(R.string._2130847824_res_0x7f022850));
            if (RunningRouteUtils.h()) {
                arrayList.add(File.separator);
                arrayList.add(getResources().getString(R.string._2130847824_res_0x7f022850));
            }
        }
        gzh.a(0);
        new PopViewList(this, customTitleBar, arrayList).e(new PopViewList.PopViewClickListener() { // from class: hew
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                RunningRouteListActivity.this.e(i);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        if (i == 0) {
            hpp.c();
            gzh.a(1);
            z();
            return;
        }
        ac();
    }

    private void ac() {
        Intent intent = new Intent();
        intent.setClassName(this, "com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity");
        intent.putExtra("currentSportType", 258);
        startActivity(intent);
    }

    private void ad() {
        Intent intent = new Intent(this, (Class<?>) HistoricalRoutesActivity.class);
        intent.putExtra("from", 1);
        startActivity(intent);
    }

    private void z() {
        startActivity(new Intent(this, (Class<?>) RunningRouteIntroductionActivity.class));
    }

    private void ab() {
        startActivity(new Intent(this, (Class<?>) MyRouteActivity.class));
    }

    private void bcr_(Intent intent) {
        LogUtil.a("Sport_RunningPathListActivity", "jump to search page from running route list");
        String stringExtra = intent.getStringExtra("SEARCH_JUMP_CITY_INFO");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        HotPathCityInfo hotPathCityInfo = (HotPathCityInfo) nrv.b(stringExtra, HotPathCityInfo.class);
        LogUtil.a("Sport_RunningPathListActivity", "cityInfo = ", hotPathCityInfo);
        if (hotPathCityInfo == null) {
            LogUtil.a("Sport_RunningPathListActivity", "onActivityResult, error city info");
            return;
        }
        gzi.d(true);
        hjd hjdVar = new hjd(hotPathCityInfo.getCityCenter().getLatitude(), hotPathCityInfo.getCityCenter().getLongitude());
        gzi.a(hjdVar);
        emc.d().getCityInfoWithGps(new emq.d().e(hjdVar.b).b(hjdVar.d).b(), new c(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("Sport_RunningPathListActivity", "onResume: mIsInit = ", Boolean.valueOf(this.j), ", mHasNetWork = ", Boolean.valueOf(this.i), ", mIsStartAppSetting = ", Boolean.valueOf(this.k));
        if (gzi.e() == 3 || gzi.e() == 4) {
            this.g = true;
            c(this.s);
        }
        if (!this.j) {
            if (this.i) {
                m();
            } else {
                aa();
            }
            this.j = true;
            return;
        }
        if (!this.i && g()) {
            m();
            this.i = true;
        } else if (this.k) {
            this.k = false;
            v();
            if (PermissionUtil.e(this, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED) {
                e();
            }
        }
    }

    private void m() {
        v();
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return CommonUtil.aa(BaseApplication.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        b(i, getString(i2));
    }

    private void b(int i, String str) {
        d();
        this.c.setVisibility(0);
        this.d.setImageResource(i);
        this.b.setText(str);
        this.f3661a.setVisibility(8);
        this.c.setOnClickListener(null);
    }

    private void y() {
        b(R.drawable.pic_no_location_available, R.string._2130840075_res_0x7f020a0b);
        this.f3661a.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        b(R.drawable._2131430905_res_0x7f0b0df9, R.string._2130844161_res_0x7f021a01);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        b(R.drawable.pic_no_line_available, R.string._2130840113_res_0x7f020a31);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        b(R.drawable._2131430905_res_0x7f0b0df9, str);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunningRouteListActivity.this.i();
                RunningRouteListActivity.this.v();
                RunningRouteListActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.c.setVisibility(8);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        GolfGetLocation.getInstance().unRegisterLocationListener();
        ash.a("RUNNING_PATH_CITY_INFO", nrv.a(this.e));
        super.onDestroy();
    }

    private boolean r() {
        return nsn.a(500);
    }

    /* loaded from: classes4.dex */
    static class c extends UiCallback<emp> {
        private WeakReference<RunningRouteListActivity> c;

        public c(RunningRouteListActivity runningRouteListActivity) {
            this.c = new WeakReference<>(runningRouteListActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Sport_RunningPathListActivity", "getCityInfoWithGps failed: errorInfo = ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emp empVar) {
            RunningRouteListActivity runningRouteListActivity = this.c.get();
            if (runningRouteListActivity == null || runningRouteListActivity.isFinishing()) {
                LogUtil.h("Sport_RunningPathListActivity", "getCityInfoWithGps is finishing");
            } else if (empVar != null && empVar.d() != null) {
                runningRouteListActivity.b(empVar.d());
            } else {
                LogUtil.a("Sport_RunningPathListActivity", "invalid city id");
            }
        }
    }

    /* loaded from: classes4.dex */
    static class a extends CustomPermissionAction {
        private final WeakReference<RunningRouteListActivity> d;

        private a(RunningRouteListActivity runningRouteListActivity) {
            super(runningRouteListActivity);
            this.d = new WeakReference<>(runningRouteListActivity);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("Sport_RunningPathListActivity", "onRequestPermissionsResult getLocation");
            RunningRouteListActivity runningRouteListActivity = this.d.get();
            if (runningRouteListActivity == null) {
                ReleaseLogUtil.c("Sport_RunningPathListActivity", "activity is null in onGranted");
            } else if (gzi.d() == null) {
                runningRouteListActivity.e();
            } else {
                LogUtil.a("Sport_RunningPathListActivity", "onRequestPermissionsResult mCityInfo != null");
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            RunningRouteListActivity runningRouteListActivity = this.d.get();
            if (runningRouteListActivity != null) {
                runningRouteListActivity.w();
                RunningRouteUtils.a(2, "Unauthorized");
            } else {
                ReleaseLogUtil.c("Sport_RunningPathListActivity", "activity is null in onDenied");
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            LogUtil.a("Sport_RunningPathListActivity", "onRequestPermissionsResult onForeverDenied");
            RunningRouteListActivity runningRouteListActivity = this.d.get();
            if (runningRouteListActivity != null) {
                runningRouteListActivity.w();
                RunningRouteUtils.a(2, "Unauthorized");
            } else {
                ReleaseLogUtil.c("Sport_RunningPathListActivity", "activity is null in onForeverDenied");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
