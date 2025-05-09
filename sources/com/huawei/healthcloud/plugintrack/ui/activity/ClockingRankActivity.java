package com.huawei.healthcloud.plugintrack.ui.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.district.DistrictSearchQuery;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.device.model.OperatorStatus;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.sport.utils.RunPopularRoutesUtil;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.healthcloud.plugintrack.runningroute.data.RouterAltitude;
import com.huawei.healthcloud.plugintrack.runningroute.manager.RunningRouteCoordinatorLayout;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteConstants;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.runningroute.view.IRouteDetail;
import com.huawei.healthcloud.plugintrack.runningroute.view.RouterAltitudeChart;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.LandScapeImagesAdapter;
import com.huawei.healthcloud.plugintrack.ui.view.RouteHintMvpView;
import com.huawei.healthcloud.plugintrack.ui.viewholder.IRouteViewHolder;
import com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.model.ICityLatLonCallBack;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.bgj;
import defpackage.cun;
import defpackage.eil;
import defpackage.emc;
import defpackage.emj;
import defpackage.emk;
import defpackage.emp;
import defpackage.emq;
import defpackage.emt;
import defpackage.emu;
import defpackage.emv;
import defpackage.emz;
import defpackage.enc;
import defpackage.ene;
import defpackage.enf;
import defpackage.ggx;
import defpackage.gso;
import defpackage.gyq;
import defpackage.gza;
import defpackage.gzb;
import defpackage.gzh;
import defpackage.gzi;
import defpackage.hab;
import defpackage.hjd;
import defpackage.hmm;
import defpackage.hpp;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kxz;
import defpackage.mwu;
import defpackage.mxb;
import defpackage.mxc;
import defpackage.npv;
import defpackage.nrh;
import defpackage.nrv;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class ClockingRankActivity extends BaseActivity implements IRouteViewHolder, IRouteDetail {

    /* renamed from: a, reason: collision with root package name */
    private HealthBottomSheet f3635a;
    private HealthTextView aa;
    private HealthTextView ac;
    private ImageButton ad;
    private HealthTextView ae;
    private String af;
    private HealthTextView ag;
    private GpsPoint ah;
    private HealthTextView ai;
    private int aj;
    private HealthButton ak;
    private hjd al;
    private RelativeLayout am;
    private gyq an;
    private LinearLayout ao;
    private LinearLayout ap;
    private HealthButton aq;
    private HealthButton as;
    private HealthButton at;
    private CustomTitleBar au;
    private HealthTextView av;
    private TabLayout ax;
    private ImageButton az;
    private AppBarLayout b;
    private ViewPager bb;
    private LinearLayout bc;
    private WeakReference<ClockingRankActivity> c;
    private RouterAltitudeChart d;
    private FrameLayout e;
    private HealthTextView f;
    private RunningRouteCoordinatorLayout g;
    private int h;
    private LinearLayout i;
    private String j;
    private enc k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean p;
    private boolean q;
    private HealthRecycleView u;
    private RelativeLayout v;
    private LinearLayout w;
    private boolean y;
    private int z;
    private hmm ab = null;
    private List<enf> o = new ArrayList();
    private boolean r = true;
    private boolean x = true;
    private boolean s = false;
    private Handler aw = new h(this);
    private HwBottomSheet.SheetState ar = HwBottomSheet.SheetState.COLLAPSED;
    private boolean t = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Track_ClockingRankActivity", "onCreate");
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_clocking_rank);
        this.p = Utils.o();
        af();
        ab();
        al();
        LogUtil.a("Track_ClockingRankActivity", "pathId = ", gzi.a(), ", isShowPathDetail = ", Boolean.valueOf(this.y), ", cityInfo: ", gzi.d());
        ao();
        ai();
    }

    private void al() {
        if (this.p) {
            this.z = 1;
        }
        if (this.an == null) {
            this.an = new gyq(this.z);
        }
    }

    private void ai() {
        nsy.cMA_(this.v, 0);
        this.v.postDelayed(new Runnable() { // from class: hdf
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.h();
            }
        }, 2000L);
    }

    public /* synthetic */ void h() {
        nsy.cMA_(this.v, 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        if (PermissionUtil.e(this, PermissionUtil.PermissionType.LOCATION) != PermissionUtil.PermissionResult.GRANTED) {
            d("Unauthorized");
        }
        RunningRouteUtils.e();
    }

    private void ab() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("Track_ClockingRankActivity", "intent is null");
            return;
        }
        try {
            baA_(intent);
        } catch (BadParcelableException unused) {
            ReleaseLogUtil.c("Track_ClockingRankActivity", "initIntent catch BadParcelableException");
        }
    }

    private void baA_(Intent intent) {
        this.y = intent.getBooleanExtra("IS_SHOW_PATH_DETAIL", true);
        this.h = intent.getIntExtra("ENTRANCE_ACTIVITY", 0);
        this.z = intent.getIntExtra("pathClass", 0);
        this.c = new WeakReference<>(this);
        HotPathCityInfo hotPathCityInfo = (HotPathCityInfo) intent.getParcelableExtra("RUNNING_PATH_CITY_INFO");
        if (hotPathCityInfo != null) {
            gzi.d(hotPathCityInfo);
        }
        Serializable serializableExtra = intent.getSerializableExtra("ROUTE_INFO");
        if (serializableExtra instanceof gyq) {
            this.an = (gyq) serializableExtra;
        }
        Serializable serializableExtra2 = intent.getSerializableExtra("pathLocation");
        if (serializableExtra2 instanceof hjd) {
            this.al = (hjd) serializableExtra2;
        }
        String stringExtra = intent.getStringExtra("PATH_ID");
        if (!TextUtils.isEmpty(stringExtra)) {
            if (aq()) {
                gzi.e(stringExtra);
            } else {
                gzi.a(stringExtra);
            }
        }
        if (this.h == RunningRouteConstants.BiFromActivity.INVALID.getIndex()) {
            this.h = RunningRouteConstants.BiFromActivity.FROM_SPORT_RECORD.getIndex();
        }
        baB_(intent);
    }

    private void baB_(Intent intent) {
        Uri zs_ = AppRouterUtils.zs_(intent);
        if (zs_ == null || zs_.isOpaque()) {
            LogUtil.h("Track_ClockingRankActivity", "uri is invalid");
            return;
        }
        String queryParameter = zs_.getQueryParameter("pathId");
        gzi.a(queryParameter);
        LogUtil.a("Track_ClockingRankActivity", "pathId from deeplink = ", queryParameter);
        this.n = zs_.getBooleanQueryParameter("isBackMap", false);
        this.af = zs_.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        this.z = CommonUtils.h(zs_.getQueryParameter("pathClass"));
        boolean z = this.n;
        this.q = z;
        this.s = true;
        LogUtil.a("Track_ClockingRankActivity", "isBackMap from deeplink = ", Boolean.valueOf(z));
        if (this.h == RunningRouteConstants.BiFromActivity.FROM_SPORT_RECORD.getIndex()) {
            this.h = RunningRouteConstants.BiFromActivity.FROM_DEEPLINK.getIndex();
        }
        if (this.n) {
            r();
        } else {
            v();
        }
    }

    private void ao() {
        this.ap = (LinearLayout) findViewById(R.id.scroll_view_layout);
        this.ad = (ImageButton) findViewById(R.id.location_button);
        this.e = (FrameLayout) findViewById(R.id.content);
        this.b = (AppBarLayout) findViewById(R.id.app_bar_layout);
        this.au = (CustomTitleBar) findViewById(R.id.clocking_rank_place_title_bar);
        this.d = (RouterAltitudeChart) findViewById(R.id.clocking_rank_place_altitude_chart);
        this.f3635a = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.az = (ImageButton) findViewById(R.id.sort_button);
        this.v = (RelativeLayout) findViewById(R.id.running_route_loading_layout);
        aj();
        an();
        am();
        ak();
        ap();
        ac();
        ae();
        ah();
    }

    private void aj() {
        if (e()) {
            nsy.cMA_(this.g, 8);
            this.am = (RelativeLayout) findViewById(R.id.clocking_rank_draw_card_route_draw);
            this.i = (LinearLayout) findViewById(R.id.clocking_rank_draw_card_layout);
            this.f = (HealthTextView) this.am.findViewById(R.id.route_draw_cp_num);
        } else {
            this.am = (RelativeLayout) findViewById(R.id.clocking_rank_draw_card);
            this.u = (HealthRecycleView) findViewById(R.id.landscape_images_recyclerview);
            RunningRouteCoordinatorLayout runningRouteCoordinatorLayout = (RunningRouteCoordinatorLayout) findViewById(R.id.coordinator_layout);
            this.g = runningRouteCoordinatorLayout;
            nsy.cMA_(runningRouteCoordinatorLayout, 0);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.clocking_rank_place_start_run_layout);
            this.ao = linearLayout;
            nsy.cMA_(linearLayout, 0);
            this.ak = (HealthButton) findViewById(R.id.clocking_rank_place_start_run);
            this.aq = (HealthButton) findViewById(R.id.clocking_rank_place_to_device);
            this.ax = (TabLayout) findViewById(R.id.clocking_rank_sub_tab);
            this.bb = (ViewPager) findViewById(R.id.clocking_rank_place_view_pager);
        }
        this.ag = (HealthTextView) this.am.findViewById(R.id.clocking_rank_place_location);
        this.ai = (HealthTextView) this.am.findViewById(R.id.clocking_rank_place_name);
        this.av = (HealthTextView) this.am.findViewById(R.id.clocking_rank_place_mile);
        this.ae = (HealthTextView) this.am.findViewById(R.id.clocking_rank_place_introduction);
        this.ac = (HealthTextView) this.am.findViewById(R.id.clocking_rank_place_location_mile);
        this.w = (LinearLayout) this.am.findViewById(R.id.labelContainer);
        this.aa = (HealthTextView) this.am.findViewById(R.id.clocking_rank_place_person_count);
        this.bc = (LinearLayout) this.am.findViewById(R.id.clocking_rank_place_heads);
    }

    private void an() {
        if (nsy.cMe_(this.ae)) {
            TextView textView = (TextView) this.am.findViewById(R.id.clocking_rank_place_introduction_show_more);
            nsy.cMA_(textView, 0);
            textView.setOnClickListener(new View.OnClickListener() { // from class: hdm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.baw_(view);
                }
            });
        }
    }

    public static /* synthetic */ void baw_(View view) {
        LogUtil.a("Track_ClockingRankActivity", "show more");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void am() {
        if (this.y) {
            return;
        }
        eil.alQ_(this, (LinearLayout) findViewById(R.id.marketing_layout), 1017);
    }

    private void ak() {
        if (e() || this.ao == null) {
            nsy.cMA_(this.ao, 8);
        } else {
            this.ap.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hde
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    ClockingRankActivity.this.i();
                }
            });
        }
    }

    public /* synthetic */ void i() {
        if (this.y && (this.ap.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.ap.getLayoutParams();
            LogUtil.a("Track_ClockingRankActivity", "mRunBottomLayout.getHeight1: ", Integer.valueOf(this.ao.getHeight()));
            layoutParams.bottomMargin = this.ao.getHeight();
        }
    }

    private void ap() {
        LogUtil.a("Track_ClockingRankActivity", "initTitleBar, mIsShowPathDetail: ", Boolean.valueOf(this.y));
        if (this.au == null) {
            LogUtil.a("Track_ClockingRankActivity", "mTitleBar is null");
            return;
        }
        if (this.y) {
            z();
        } else {
            ag();
        }
        if (!this.p) {
            this.au.setRightSoftkeyVisibility(0);
            this.au.setRightSoftkeyBackground(nsf.cKq_(R.drawable._2131430558_res_0x7f0b0c9e), nsf.h(R.string._2130850608_res_0x7f023330));
            this.au.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: hds
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baK_(view);
                }
            });
            this.au.setRightButtonVisibility(0);
            this.au.setRightButtonDrawable(nsf.cKq_(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R.string._2130850635_res_0x7f02334b));
        }
        this.au.setLeftButtonTextColor(nsf.cKq_(R.drawable._2131429371_res_0x7f0b07fb), nsf.c(R.color._2131299236_res_0x7f090ba4), nsf.h(R.string._2130850617_res_0x7f023339));
    }

    public /* synthetic */ void baK_(View view) {
        if (nsn.o()) {
            LogUtil.a("Track_ClockingRankActivity", "right soft key click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        gzh.d();
        d(1, BleConstants.BLE_THIRD_DEVICE_H5);
        boolean z = this.y;
        int i = z ? 3 : 4;
        if (this.s || z) {
            LogUtil.a("Track_ClockingRankActivity", "begin to jump to routeList, mCityInfo = ", gzi.d());
            gzi.a(i);
            RunningRouteListActivity.d(this, gzi.d(), this.s, this.an);
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra("ROUTE_INFO", this.an);
            intent.putExtra("IS_CITY_CHANGE", this.m);
            setResult(-1, intent);
            finish();
            overridePendingTransition(0, 0);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void z() {
        if (!this.p) {
            this.au.setRightButtonOnClickListener(new View.OnClickListener() { // from class: hdn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baD_(view);
                }
            });
            this.au.setRightThirdKeyVisibility(8);
            this.au.setRightFourKeyVisibility(aq() ? 8 : 0);
            this.au.setRightFourKeyBackground(nsf.cKq_(R.drawable._2131430386_res_0x7f0b0bf2), nsf.h(R.string._2130846049_res_0x7f022161));
            this.au.setRightFourKeyOnClickListener(new View.OnClickListener() { // from class: hdv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baE_(view);
                }
            });
        }
        this.au.getViewTitle().setBackgroundResource(0);
        this.au.setTitleBarBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
        bb();
    }

    public /* synthetic */ void baD_(View view) {
        bf();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void baE_(View view) {
        bl();
        d(1, "h4");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        if (!this.n) {
            LogUtil.a("Track_ClockingRankActivity", "is not from deeplink, no bi");
        } else {
            gzh.c();
        }
    }

    private void ag() {
        if (!this.p) {
            this.au.setRightThirdKeyVisibility(0);
            this.au.setRightThirdKeyBackground(nsf.cKq_(R.drawable._2131431370_res_0x7f0b0fca), nsf.h(R.string._2130847322_res_0x7f02265a));
            this.au.setRightThirdKeyOnClickListener(new View.OnClickListener() { // from class: hdo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baH_(view);
                }
            });
            this.au.setRightFourKeyVisibility(8);
            this.au.setRightButtonOnClickListener(new View.OnClickListener() { // from class: hdp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baI_(view);
                }
            });
        }
        bb();
        this.au.getViewTitle().setBackgroundResource(R.drawable.hwspinner_selector_background_emui);
        this.au.getViewTitle().setOnClickListener(new View.OnClickListener() { // from class: hdr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClockingRankActivity.this.baJ_(view);
            }
        });
        this.au.getViewTitle().setGravity(16);
        this.au.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131298875_res_0x7f090a3b));
    }

    public /* synthetic */ void baH_(View view) {
        if (nsn.o()) {
            LogUtil.a("Track_ClockingRankActivity", "third key click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            Intent intent = new Intent(this, (Class<?>) RunningRouteLocationSearchActivity.class);
            intent.putExtra(DistrictSearchQuery.KEYWORDS_CITY, gzi.d() == null ? "" : gzi.d().getCityName());
            startActivityForResult(intent, 1);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void baI_(View view) {
        bg();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void baJ_(View view) {
        RunningCitysActivity.bcm_(this, gzi.d(), 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bb() {
        this.au.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hct
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClockingRankActivity.this.baO_(view);
            }
        });
    }

    public /* synthetic */ void baO_(View view) {
        as();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void as() {
        emc.d().removeLocationUpdates();
        if (this.q) {
            this.q = false;
            t();
            ai();
            this.y = false;
            ap();
            this.d.a();
            bm();
            this.ab.d();
            nsy.cMA_((ViewGroup) this.f3635a.getIndicateView().getParent(), 8);
            nsy.cMA_(this.i, 8);
            d();
            nsy.cMA_(this.ad, 0);
            HandlerExecutor.d(new Runnable() { // from class: hdk
                @Override // java.lang.Runnable
                public final void run() {
                    ClockingRankActivity.this.g();
                }
            }, 200L);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("IS_RETURN_TO_BEFORE_ACTIVITY", true);
        intent.putExtra("ROUTE_INFO", this.an);
        intent.putExtra("IS_CITY_CHANGE", this.m);
        setResult(-1, intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public /* synthetic */ void g() {
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) this.b.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior behavior2 = (AppBarLayout.Behavior) behavior;
            if (behavior2.getTopAndBottomOffset() != 0) {
                behavior2.setTopAndBottomOffset(0);
            }
        }
        this.f3635a.setSheetState(HwBottomSheet.SheetState.HIDDEN);
        nsy.cMA_(this.ao, 8);
        if (koq.c(this.o) && this.o.size() > 100) {
            p();
        } else {
            LogUtil.a("Track_ClockingRankActivity", "less than 100 routes, continue requesting data");
            at();
        }
        hjd a2 = RunningRouteUtils.a();
        gzi.b(a2);
        this.ab.c(a2);
        this.n = false;
        d();
    }

    private void p() {
        hjd hjdVar;
        if (this.n) {
            GpsPoint cityCenter = gzi.d().getCityCenter();
            boolean z = PermissionUtil.e(this, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED;
            if (this.x && z) {
                hjdVar = gzi.f();
            } else {
                hjdVar = new hjd(cityCenter.getLatitude(), cityCenter.getLongitude());
            }
            LogUtil.a("Track_ClockingRankActivity", "mIsRouteAndUserInSameCity = ", Boolean.valueOf(this.x), ", mUserLocation = ", gzi.f(), " ,cityCenter = ", cityCenter, ", mapFocusPoint = ", hjdVar);
            this.ab.a(hjdVar);
        } else {
            this.ab.j();
        }
        if (gzi.j()) {
            this.ab.d(gzi.h());
        }
        this.ab.c(this.f3635a.getContext(), this.o);
        c(false);
    }

    private void bm() {
        LinearLayout linearLayout = this.ap;
        if (linearLayout == null || !(linearLayout.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.ap.getLayoutParams();
        layoutParams.bottomMargin = 0;
        this.ap.setLayoutParams(layoutParams);
    }

    private void bf() {
        d(1, "h1");
        ArrayList arrayList = new ArrayList(Arrays.asList(getResources().getString(R.string._2130845879_res_0x7f0220b7), getResources().getString(R.string.IDS_clock_transfer_to_wearable_device)));
        if (!e()) {
            arrayList.add(getResources().getString(R.string._2130847824_res_0x7f022850));
        }
        if (RunningRouteUtils.h()) {
            arrayList.add(File.separator);
            arrayList.add(getResources().getString(R.string._2130847824_res_0x7f022850));
        }
        new PopViewList(this, this.au, arrayList).e(new PopViewList.PopViewClickListener() { // from class: hdq
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                ClockingRankActivity.this.b(i);
            }
        });
    }

    public /* synthetic */ void b(int i) {
        if (i == 0) {
            d(1, "m6");
            RunningRouteUtils.b(this, gzi.a(), this.j);
        } else {
            if (i == 1) {
                ba();
                return;
            }
            LogUtil.a("Track_ClockingRankActivity", "startSportAssistSettingsActivity");
            d(1, "m1");
            bi();
        }
    }

    private void ba() {
        gzh.d(gzi.a());
        d(1, "m7");
        ay();
        LogUtil.a("Track_ClockingRankActivity", "transfer to wearable device");
    }

    private void bi() {
        Intent intent = new Intent();
        intent.setClassName(this, "com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity");
        intent.putExtra("currentSportType", 258);
        startActivity(intent);
    }

    private void bg() {
        PopViewList popViewList = new PopViewList(this, this.au, new ArrayList(Arrays.asList(getResources().getString(R.string._2130840069_res_0x7f020a05), getResources().getString(R.string._2130840070_res_0x7f020a06))));
        gzh.a(0);
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: hcu
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                ClockingRankActivity.this.e(i);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        if (i == 0) {
            bl();
        } else {
            if (i != 1) {
                return;
            }
            hpp.c();
            gzh.a(1);
            bj();
        }
    }

    private void bl() {
        startActivity(new Intent(this, (Class<?>) HistoricalRoutesActivity.class));
    }

    private void bj() {
        startActivity(new Intent(this, (Class<?>) RunningRouteIntroductionActivity.class));
    }

    private void ac() {
        LogUtil.a("Track_ClockingRankActivity", "initBottomSheet");
        HealthBottomSheet healthBottomSheet = this.f3635a;
        if (healthBottomSheet == null) {
            LogUtil.a("Track_ClockingRankActivity", "mBottomSheet is null");
            return;
        }
        healthBottomSheet.getIndicateView().setOnTouchListener(new View.OnTouchListener() { // from class: heb
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ClockingRankActivity.this.baC_(view, motionEvent);
            }
        });
        this.f3635a.setAnchorPoint(0.0f);
        this.f3635a.setIndicateSafeInsetsEnabled(true);
        this.f3635a.setForceShowIndicateEnabled(false);
        this.f3635a.setIndicateViewClickable(false);
        this.f3635a.setHeightGap(200);
        this.f3635a.setSheetHeight(getResources().getDimensionPixelOffset(R.dimen._2131362961_res_0x7f0a0491));
        this.f3635a.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.1
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                ClockingRankActivity.this.ar = sheetState2;
                LogUtil.a("Track_ClockingRankActivity", "onSheetStateChanged, newState: ", sheetState2);
                ClockingRankActivity.this.d(sheetState2);
            }
        });
    }

    public /* synthetic */ boolean baC_(View view, MotionEvent motionEvent) {
        RunningRouteCoordinatorLayout runningRouteCoordinatorLayout;
        if (!this.f3635a.isEnabled() || !this.f3635a.c()) {
            return false;
        }
        if (this.ar == HwBottomSheet.SheetState.EXPANDED && (runningRouteCoordinatorLayout = this.g) != null && runningRouteCoordinatorLayout.getIsOnTop()) {
            this.f3635a.setSheetState(HwBottomSheet.SheetState.COLLAPSED);
            return false;
        }
        if (this.ar != HwBottomSheet.SheetState.COLLAPSED) {
            return false;
        }
        this.f3635a.setSheetState(HwBottomSheet.SheetState.EXPANDED);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HwBottomSheet.SheetState sheetState) {
        int dimensionPixelOffset;
        if (sheetState == HwBottomSheet.SheetState.COLLAPSED) {
            if (nsy.cMe_(this.ai)) {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131362970_res_0x7f0a049a);
            } else {
                dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131362961_res_0x7f0a0491);
            }
            int sheetHeight = this.f3635a.getSheetHeight();
            if (sheetHeight != dimensionPixelOffset) {
                this.f3635a.setSheetHeight(dimensionPixelOffset);
            }
            LogUtil.a("Track_ClockingRankActivity", "oldHeight=", Integer.valueOf(sheetHeight), "sheetHeight = ", Integer.valueOf(dimensionPixelOffset));
        }
    }

    private void ae() {
        LinearLayout linearLayout;
        LogUtil.a("Track_ClockingRankActivity", "initLocationView");
        if (this.p || (linearLayout = (LinearLayout) this.am.findViewById(R.id.clocking_rank_place_location_layout)) == null) {
            return;
        }
        linearLayout.setVisibility(0);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: hdi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClockingRankActivity.this.baG_(view);
            }
        });
    }

    public /* synthetic */ void baG_(View view) {
        RunningRouteUtils.d(this, this.ah);
        d(1, "i5");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ah() {
        LogUtil.a("Track_ClockingRankActivity", "initMapViewHolder");
        hmm hmmVar = new hmm(getWindow().getDecorView().getRootView(), this, this);
        this.ab = hmmVar;
        hjd hjdVar = this.al;
        if (hjdVar == null) {
            hjdVar = gzi.f();
        }
        hmmVar.a(this, hjdVar);
        LogUtil.a("Track_ClockingRankActivity", "begin to draw, current location = ", Double.valueOf(gzi.f().b), ", ", Double.valueOf(gzi.f().d));
        this.ab.c(gzi.f());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("Track_ClockingRankActivity", "onResume: mIsInit = ", Boolean.valueOf(this.t));
        super.onResume();
        if (!this.r) {
            this.r = true;
            return;
        }
        if (this.t && this.y) {
            LogUtil.a("Track_ClockingRankActivity", "skip refresh ranking data");
            return;
        }
        this.t = true;
        if (this.n || ar()) {
            return;
        }
        ai();
        at();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        if (e()) {
            this.as = (HealthButton) findViewById(R.id.clocking_rank_to_device);
            HealthButton healthButton = (HealthButton) findViewById(R.id.clocking_rank_to_start);
            this.at = healthButton;
            nsy.cMn_(healthButton, new View.OnClickListener() { // from class: hcz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baF_(view);
                }
            });
            RouteTransferDataHolder.e(this.k.h(), (ResponseCallback<Boolean>) new ResponseCallback() { // from class: hda
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    ClockingRankActivity.this.e(i, (Boolean) obj);
                }
            });
            b(false);
        }
    }

    public /* synthetic */ void baF_(View view) {
        bk();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void e(int i, Boolean bool) {
        b(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        hmm hmmVar = this.ab;
        if (hmmVar != null) {
            hmmVar.i();
        }
    }

    public void b(boolean z) {
        Handler handler = this.aw;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(3);
            obtainMessage.arg1 = z ? R.string._2130847256_res_0x7f022618 : R.string.IDS_clock_transfer_to_wearable_device;
            this.aw.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        HealthButton healthButton = this.as;
        if (healthButton != null) {
            healthButton.setText(i);
            this.as.setOnClickListener(new View.OnClickListener() { // from class: hdx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baL_(i, view);
                }
            });
        }
    }

    public /* synthetic */ void baL_(int i, View view) {
        if (nsn.o()) {
            LogUtil.a("Track_ClockingRankActivity", "fast click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (i == R.string.IDS_clock_transfer_to_wearable_device) {
            ay();
            d(1, "p7");
        } else {
            az();
            d(1, "p8");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void ay() {
        String str;
        String str2;
        if (this.k == null) {
            ReleaseLogUtil.c("Track_ClockingRankActivity", "mHotPathDetailInfo is null in sendDrawToDevice");
            return;
        }
        LogUtil.a("Track_ClockingRankActivity", "click transfer to device");
        RouteTransferDataHolder routeTransferDataHolder = new RouteTransferDataHolder(this);
        if (this.k.m() != null) {
            str = this.k.m().d();
            str2 = this.k.m().a();
        } else {
            str = "";
            str2 = "";
        }
        routeTransferDataHolder.a(str2);
        routeTransferDataHolder.e(str, RunningRouteUtils.d(this.k), this.k.r());
    }

    private void az() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_ClockingRankActivity");
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            nrh.b(this, R.string._2130840135_res_0x7f020a47);
            return;
        }
        bgj.c().a(deviceInfo, "huaweischeme://wearable/sport/sportdoodle/routelist?trackid=" + this.k.h(), new IBaseResponseCallback() { // from class: hdc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ClockingRankActivity.this.b(i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Object obj) {
        int i2;
        LogUtil.a("Track_ClockingRankActivity", "sendDevicePullPage response ", obj);
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (intValue == 0) {
                i2 = R.string._2130840134_res_0x7f020a46;
            } else if (intValue == 2) {
                b(false);
                i2 = R.string._2130840136_res_0x7f020a48;
            } else {
                i2 = R.string._2130840135_res_0x7f020a47;
            }
            nrh.b(this.c.get(), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        LogUtil.a("Track_ClockingRankActivity", "requestDataAndShowView");
        ap();
        this.d.a();
        this.e.setPadding(0, 0, 0, 0);
        if (this.y) {
            bd();
            return;
        }
        emc.d().removeLocationUpdates();
        nsy.cMA_((ViewGroup) this.f3635a.getIndicateView().getParent(), 8);
        d();
        nsy.cMA_(this.ad, 0);
        HandlerExecutor.d(new Runnable() { // from class: hcq
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.m();
            }
        }, 200L);
        nsy.cMA_(this.ao, 8);
        aw();
    }

    public /* synthetic */ void m() {
        this.f3635a.setSheetState(HwBottomSheet.SheetState.HIDDEN);
    }

    private void bd() {
        nsy.cMA_((ViewGroup) this.f3635a.getIndicateView().getParent(), 0);
        hjd a2 = RunningRouteUtils.a();
        gzi.b(a2);
        this.ab.c(a2);
        nsy.cMA_(this.az, 8);
        nsy.cMA_(this.ad, 8);
        HandlerExecutor.d(new Runnable() { // from class: hdw
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.k();
            }
        }, 200L);
        String a3 = gzi.a();
        if (aq()) {
            a3 = getIntent().getStringExtra("PATH_ID");
        }
        if (!e()) {
            nsy.cMA_(this.ao, 0);
            nsy.cMA_(this.g, 0);
        }
        a(a3);
        c(a3);
        bc();
        this.ab.c();
    }

    public /* synthetic */ void k() {
        if (this.f3635a.getSheetState() != HwBottomSheet.SheetState.HIDDEN || e()) {
            return;
        }
        this.f3635a.setSheetState(HwBottomSheet.SheetState.COLLAPSED);
    }

    private void a(String str) {
        LogUtil.a("Track_ClockingRankActivity", "bindHotPathDetailToView, pathId: ", str);
        emc.d().getHotPathDetail(new emu.b().d(str).b(), new d(this));
    }

    public boolean e() {
        return this.z == 1 || this.p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(enc encVar, GpsPoint gpsPoint) {
        LogUtil.a("Track_ClockingRankActivity", "miles: ", getResources().getString(R.string._2130849091_res_0x7f022d43, UnitUtil.e(encVar.r() / 1000.0d, 1, 2)), ", punch person: ", UnitUtil.e(encVar.f(), 1, 0));
        b(encVar);
        nsy.cMr_(this.ai, encVar.n());
        this.ai.postDelayed(new Runnable() { // from class: hdu
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.f();
            }
        }, 700L);
        nsy.cMr_(this.aa, UnitUtil.e(encVar.f(), 1, 0));
        gzh.a(encVar.f());
        nsy.cMr_(this.av, RunningRouteUtils.a(R.plurals._2130903096_res_0x7f030038, R.plurals._2130903095_res_0x7f030037, encVar.r()));
        if (!TextUtils.isEmpty(encVar.d())) {
            nsy.cMr_(this.ae, encVar.d());
            nsy.cMA_(this.ae, 0);
        }
        nsy.cMr_(this.ac, RunningRouteUtils.b(R.plurals._2130903406_res_0x7f03016e, R.plurals._2130903407_res_0x7f03016f, RunningRouteUtils.a(RunningRouteUtils.a(), RunningRouteUtils.b(gpsPoint)), 2));
        if (!this.s) {
            gzh.e(encVar.q(), this.h, this.af, this.aj);
        }
        if (gpsPoint != null && !this.p) {
            gza.b().a(new LatLonPoint(gpsPoint.getLatitude(), gpsPoint.getLongitude()), new b(this, 2));
        }
        e(encVar);
    }

    public /* synthetic */ void f() {
        d(this.ar);
        n();
    }

    private void b(enc encVar) {
        if (encVar.a() == null || !koq.c(encVar.a().e())) {
            return;
        }
        int size = encVar.a().e().size();
        nsy.cMr_(this.f, nsf.a(R.plurals._2130903414_res_0x7f030176, size, Integer.valueOf(size)));
        nsy.cMA_(this.f, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(emj emjVar, enc encVar) {
        this.w.removeAllViews();
        bat_(e(emjVar, 1, encVar.l()), this.w);
        bat_(e(emjVar, 0, encVar.s()), this.w);
    }

    private List<String> e(emj emjVar, int i, List<Integer> list) {
        if (emjVar == null || koq.b(list)) {
            return new ArrayList();
        }
        if (i == 0) {
            return RunningRouteUtils.c(emjVar.c(), list);
        }
        return RunningRouteUtils.c(emjVar.e(), list);
    }

    private void bat_(List<String> list, LinearLayout linearLayout) {
        if (koq.c(list)) {
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    linearLayout.addView(bau_(str), bav_());
                }
            }
        }
    }

    private View bau_(String str) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.list_item_running_route_label, (ViewGroup) null).findViewById(R.id.text1);
        nsy.cMr_(textView, str);
        return textView;
    }

    private LinearLayout.LayoutParams bav_() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7));
        return layoutParams;
    }

    private void n() {
        ViewPager viewPager = this.bb;
        if (viewPager == null || this.ax == null) {
            return;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.4
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("Track_ClockingRankActivity", "onPageSelected Position: ", Integer.valueOf(i));
                ClockingRankActivity.this.f3635a.setSheetState(HwBottomSheet.SheetState.EXPANDED);
                ClockingRankActivity.this.d(1, i == 0 ? "i7" : "i6");
            }
        });
        this.ax.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.5
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.a("Track_ClockingRankActivity", "onTabSelected Position: ", Integer.valueOf(tab.getPosition()), "title: ", tab.getText().toString());
                ViewClickInstrumentation.selectClickOnTabLayout(this, tab);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtil.a("Track_ClockingRankActivity", "onTabUnselected Position: ", Integer.valueOf(tab.getPosition()), "title: ", tab.getText().toString());
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.a("Track_ClockingRankActivity", "onTabReselected Position: ", Integer.valueOf(tab.getPosition()), "title: ", tab.getText().toString());
                ClockingRankActivity.this.f3635a.setSheetState(HwBottomSheet.SheetState.EXPANDED);
            }
        });
    }

    private void e(enc encVar) {
        if (this.u == null || encVar == null || encVar.m() == null || encVar.m().e() == null || e()) {
            LogUtil.a("Track_ClockingRankActivity", "no land scape images");
            return;
        }
        this.u.setHasFixedSize(true);
        this.u.setNestedScrollingEnabled(false);
        this.u.a(false);
        this.u.d(false);
        this.u.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.u.setAdapter(new LandScapeImagesAdapter(this, encVar.m().e()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(enc encVar) {
        RouterAltitude routerAltitude;
        LogUtil.a("Track_ClockingRankActivity", "bindAltitudeChart");
        if (encVar == null || encVar.k() == null) {
            LogUtil.a("Track_ClockingRankActivity", "get altitude fail");
            return;
        }
        List<GpsPoint> k = encVar.k();
        float r = ((float) encVar.r()) / k.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < k.size(); i++) {
            if (i == k.size() - 1) {
                routerAltitude = new RouterAltitude((float) encVar.r(), (float) k.get(i).getAltitude());
            } else {
                routerAltitude = new RouterAltitude(0 + (i * r), (float) k.get(i).getAltitude());
            }
            arrayList.add(routerAltitude);
        }
        this.d.setIsTouchEventConsumption(false);
        this.d.d(arrayList);
    }

    private void c(String str) {
        LogUtil.a("Track_ClockingRankActivity", "bindHotPathDetailToView, pathId: ", str);
        HealthButton healthButton = this.ak;
        if (healthButton == null) {
            LogUtil.a("Track_ClockingRankActivity", "mRunBottom is null");
        } else {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: hdd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baN_(view);
                }
            });
        }
    }

    public /* synthetic */ void baN_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            bk();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void bc() {
        HealthButton healthButton = this.aq;
        if (healthButton == null) {
            LogUtil.a("Track_ClockingRankActivity", "mSendRunRouteBtn is null");
        } else {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: hdh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ClockingRankActivity.this.baM_(view);
                }
            });
        }
    }

    public /* synthetic */ void baM_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ba();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void bk() {
        boolean a2 = ggx.a(this, BluetoothAdapter.getDefaultAdapter() != null ? BluetoothAdapter.getDefaultAdapter().isEnabled() : false);
        boolean c2 = ggx.c(this);
        boolean i = kxz.i(BaseApplication.getContext());
        ReleaseLogUtil.e("Track_ClockingRankActivity", "isConnect", Boolean.valueOf(a2), "isIndependentSportDevice", Boolean.valueOf(c2), "update status", Boolean.valueOf(i));
        if (a2 && c2 && !i) {
            mwu.d().a(new e(this));
            Handler handler = this.aw;
            if (handler != null) {
                handler.sendEmptyMessageDelayed(1, 1000L);
                return;
            }
            return;
        }
        HandlerExecutor.e(new Runnable() { // from class: hdg
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.l();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: aa, reason: merged with bridge method [inline-methods] */
    public void l() {
        AudioResProviderInterface audioResProviderInterface = (AudioResProviderInterface) AppRouter.e(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_DOWNLOAD, AudioResProviderInterface.class);
        boolean c2 = mxb.a().c(com.huawei.haf.application.BaseApplication.e());
        Object[] objArr = new Object[4];
        objArr[0] = "goToSport service = ";
        objArr[1] = Boolean.valueOf(audioResProviderInterface != null);
        objArr[2] = "goToSport isEnableCurLang = ";
        objArr[3] = Boolean.valueOf(c2);
        ReleaseLogUtil.e("Track_ClockingRankActivity", objArr);
        if (audioResProviderInterface == null || !c2) {
            w();
        } else {
            audioResProviderInterface.queryOrDownAudioResource(this, mxc.a(com.huawei.haf.application.BaseApplication.e(), "Sport"), nsf.h(R.string._2130845830_res_0x7f022086), new AudioResDownloadListener() { // from class: hdb
                @Override // com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener
                public final void onResult(int i) {
                    ClockingRankActivity.this.a(i);
                }
            });
        }
    }

    public /* synthetic */ void a(int i) {
        ReleaseLogUtil.e("Track_ClockingRankActivity", "goToSport result = ", Integer.valueOf(i));
        HandlerExecutor.e(new Runnable() { // from class: hdj
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.w();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        String a2;
        Bundle aTm_ = gso.e().aTm_(258, -1, -1.0f, -1);
        aTm_.putInt("pathClass", this.z);
        if (aq()) {
            a2 = gzi.b();
        } else {
            a2 = gzi.a();
        }
        String str = a2;
        LogUtil.a("Track_ClockingRankActivity", "curPath from historicalRoutes = ", Boolean.valueOf(aq()), ", pathId = ", str);
        gso.e().aTs_(0, aTm_, null, this, str);
        s();
        gso.e().a(258, -1, -1.0f);
        this.t = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void be() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130839499_res_0x7f0207cb).czC_(R.string._2130841379_res_0x7f020f23, new View.OnClickListener() { // from class: hcv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClockingRankActivity.this.baQ_(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: hcx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ClockingRankActivity.bay_(view);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void baQ_(View view) {
        LogUtil.a("Track_ClockingRankActivity", "ignore link and continue");
        hab.c(true);
        l();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void bay_(View view) {
        LogUtil.a("Track_ClockingRankActivity", "cancel sport");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void s() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(this.h));
        hashMap.put("routeID", gzi.a());
        hashMap.put("routeIName", this.j);
        gyq gyqVar = this.an;
        hashMap.put("routeStyle", Integer.valueOf(gyqVar != null ? gyqVar.d() : 0));
        hashMap.put(BleConstants.SPORT_TYPE, 258);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), "1040085", hashMap, 0);
        d(1, "p4");
    }

    private void d(String str) {
        if (this.l) {
            return;
        }
        this.l = true;
        RunningRouteUtils.a(0, str);
    }

    private void aw() {
        LogUtil.a("Track_ClockingRankActivity", "setRouterMarker");
        hjd a2 = RunningRouteUtils.a();
        gzi.b(a2);
        this.ab.c(a2);
        if (gzi.j()) {
            this.ab.d(gzi.h());
            this.ab.b(gzi.h());
        }
        if (gzi.d() == null) {
            this.au.setTitleText(getString(R.string._2130840071_res_0x7f020a07));
            this.ab.b(gzi.f());
            HandlerExecutor.d(new Runnable() { // from class: hcw
                @Override // java.lang.Runnable
                public final void run() {
                    ClockingRankActivity.this.o();
                }
            }, 200L);
        } else if (gzi.d().getCityId() != null) {
            y();
        }
    }

    public /* synthetic */ void o() {
        nsy.cMA_(this.v, 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        hjd h2 = gzi.j() ? gzi.h() : gzi.f();
        if (h2 == null) {
            LogUtil.h("Track_ClockingRankActivity", "cannot get user location");
        } else {
            gza.b().b(new LatLonPoint(h2.b, h2.d), new b(this, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, hjd hjdVar) {
        LogUtil.a("Track_ClockingRankActivity", "userLocation: ", hjdVar);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(this.z));
        this.ab.b(hjdVar);
        d(new ene.a().d(hjdVar.b).e(hjdVar.d).c(str).i(1).d(0).d(arrayList).a(0).h(0).b(1).c(100).a(), hjdVar);
    }

    private void d(ene eneVar, hjd hjdVar) {
        if (eneVar != null) {
            emc.d().getHotPaths(eneVar, new g(this, eneVar, hjdVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ene eneVar) {
        LogUtil.a("Track_ClockingRankActivity", "requestHotPaths");
        if (eneVar != null) {
            emc.d().getHotPaths(eneVar, new c(this, eneVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        this.ab.e(z);
    }

    public gyq c() {
        return this.an;
    }

    public void c(gyq gyqVar) {
        if (gyqVar != null) {
            this.an = gyqVar;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            LogUtil.a("Track_ClockingRankActivity", "onActivityResult, data is null");
            return;
        }
        if (i == 0 && i2 == -1) {
            ai();
            baz_(intent);
            return;
        }
        if (i == 1 && i2 == -1) {
            String stringExtra = intent.getStringExtra("SEARCH_JUMP_CITY_INFO");
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            HotPathCityInfo hotPathCityInfo = (HotPathCityInfo) nrv.b(stringExtra, HotPathCityInfo.class);
            if (hotPathCityInfo == null || hotPathCityInfo.getCityCenter() == null) {
                LogUtil.a("Track_ClockingRankActivity", "onActivityResult, error city info");
                return;
            }
            gzi.d(hotPathCityInfo);
            GpsPoint cityCenter = hotPathCityInfo.getCityCenter();
            hjd hjdVar = new hjd(cityCenter.getLatitude(), cityCenter.getLongitude());
            this.ab.d();
            this.ab.b(hjdVar);
            gzi.d(true);
            gzi.a(hjdVar);
            emc.d().getCityInfoWithGps(new emq.d().e(hjdVar.b).b(hjdVar.d).b(), new a(this, hjdVar));
        }
    }

    private void baz_(Intent intent) {
        this.y = intent.getBooleanExtra("IS_SHOW_PATH_DETAIL", true);
        if (intent.getBooleanExtra("IS_CITY_CHANGE", false)) {
            ReleaseLogUtil.e("Track_ClockingRankActivity", "city is changed");
            this.an = new gyq(this.z);
            this.m = true;
            if (this.ab != null) {
                d();
            }
        }
        HotPathCityInfo hotPathCityInfo = (HotPathCityInfo) intent.getParcelableExtra("RUNNING_PATH_CITY_INFO");
        if (hotPathCityInfo == null) {
            return;
        }
        gzi.d(hotPathCityInfo);
        this.au.setTitleText(gzi.d().getCityName());
        gzi.d(false);
    }

    public void d() {
        gyq gyqVar = this.an;
        if (gyqVar == null || gyqVar.a() == null) {
            nsy.cMA_(this.az, 8);
            ReleaseLogUtil.c("Track_ClockingRankActivity", "drawInfo is null in changeSortButtonImage");
            return;
        }
        if (this.an.b() == 0 && this.an.d() == 0) {
            nsy.cMm_(this.az, nrz.cKl_(com.huawei.haf.application.BaseApplication.e(), R.drawable._2131430233_res_0x7f0b0b59));
        } else {
            nsy.cMm_(this.az, nrz.cKl_(com.huawei.haf.application.BaseApplication.e(), R.drawable._2131430234_res_0x7f0b0b5a));
        }
        nsy.cMA_(this.az, 0);
    }

    private void r() {
        LogUtil.a("Track_ClockingRankActivity", "checkRunningRoutePermission");
        c(new RunPopularRoutesUtil.DialogCallBack() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.2
            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void goNext() {
                LogUtil.a("Track_ClockingRankActivity", "RunningRoutePermission is granted, begin to jump to route detail page");
                ClockingRankActivity.this.q();
            }

            @Override // com.huawei.health.sport.utils.RunPopularRoutesUtil.DialogCallBack
            public void notGoNext() {
                LogUtil.a("Track_ClockingRankActivity", "RunningRoutePermission is denied, cannot jump to route detail page");
                nsn.o();
                ClockingRankActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogUtil.a("Track_ClockingRankActivity", "checkLocationPermission");
        if (PermissionUtil.e(this, PermissionUtil.PermissionType.LOCATION) != PermissionUtil.PermissionResult.GRANTED) {
            PermissionUtil.b(this, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(this) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.3
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("Track_ClockingRankActivity", "location permission is granted, begin to init location");
                    ClockingRankActivity.this.af();
                    ClockingRankActivity.this.v();
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    LogUtil.a("Track_ClockingRankActivity", "location permission is denied, use default location");
                    super.onDenied(str);
                    ClockingRankActivity.this.v();
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                    LogUtil.a("Track_ClockingRankActivity", "location permission is forever denied, use default location");
                    ClockingRankActivity.this.v();
                }
            });
            return;
        }
        LogUtil.a("Track_ClockingRankActivity", "has location permission");
        af();
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (ar()) {
            av();
        } else {
            ax();
        }
    }

    private void c(final RunPopularRoutesUtil.DialogCallBack dialogCallBack) {
        if (RunPopularRoutesUtil.b(this)) {
            dialogCallBack.goNext();
            return;
        }
        LogUtil.a("Track_ClockingRankActivity", "showFirstPopularRoutesDialog enter");
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(this).cyp_(View.inflate(this, R.layout.dialog_first_popular_routes_tip, null)).cyn_(R.string.IDS_device_release_user_profile_log_collect_cancel, new DialogInterface.OnClickListener() { // from class: hdz
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ClockingRankActivity.bax_(RunPopularRoutesUtil.DialogCallBack.this, dialogInterface, i);
            }
        }).cyo_(R.string.IDS_device_release_user_profile_log_collect_agree, new DialogInterface.OnClickListener() { // from class: hea
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ClockingRankActivity.this.baP_(dialogCallBack, dialogInterface, i);
            }
        }).c();
        HealthTextView healthTextView = (HealthTextView) c2.findViewById(R.id.second);
        HealthButton healthButton = (HealthButton) c2.findViewById(R.id.dialog_btn_positive);
        if (!this.p) {
            healthButton.setTextColor(getResources().getColor(R.color._2131298273_res_0x7f0907e1, null));
            healthButton.setBackgroundResource(R.drawable.button_background_emphasize);
        } else {
            healthTextView.setText(getString(R.string._2130847335_res_0x7f022667));
        }
        ((HealthButtonBarLayout) c2.findViewById(R.id.button_bar)).setDividerDrawable(getResources().getDrawable(R.drawable._2131427926_res_0x7f0b0256, null));
        c2.setCancelable(false);
        c2.show();
    }

    public static /* synthetic */ void bax_(RunPopularRoutesUtil.DialogCallBack dialogCallBack, DialogInterface dialogInterface, int i) {
        LogUtil.a("Track_ClockingRankActivity", "showFirstPopularRoutesDialog click cancel");
        dialogCallBack.notGoNext();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void baP_(RunPopularRoutesUtil.DialogCallBack dialogCallBack, DialogInterface dialogInterface, int i) {
        LogUtil.a("Track_ClockingRankActivity", "showFirstPopularRoutesDialog click agree");
        getSharedPreferences("popularRoutes_sharedpreference_msg", 0).edit().putBoolean("enter_popular_routes", true).commit();
        dialogCallBack.goNext();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ar() {
        return "0".equals(gzi.a());
    }

    private void av() {
        emc.d().getCityInfoWithGps(new emq.d().e(gzi.f().b).b(gzi.f().d).b(), new UiCallback<emp>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.7
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Track_ClockingRankActivity", "cannot get cityInfo with gps, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emp empVar) {
                if (empVar == null || empVar.c() == null) {
                    int i = PermissionUtil.e(ClockingRankActivity.this, PermissionUtil.PermissionType.LOCATION) != PermissionUtil.PermissionResult.GRANTED ? R.string._2130840178_res_0x7f020a72 : R.string._2130840058_res_0x7f0209fa;
                    ClockingRankActivity clockingRankActivity = ClockingRankActivity.this;
                    nrh.d(clockingRankActivity, ((ClockingRankActivity) clockingRankActivity.c.get()).getString(i));
                    ClockingRankActivity.this.finish();
                    return;
                }
                HotPathCityInfo d2 = empVar.d();
                String cityId = d2.getCityId();
                LogUtil.a("Track_ClockingRankActivity", "cityInfo = ", d2, " cityId = ", cityId);
                if (ClockingRankActivity.this.ar()) {
                    gzi.d(d2);
                    gzi.c(cityId);
                }
                if (TextUtils.isEmpty(cityId)) {
                    return;
                }
                LogUtil.a("Track_ClockingRankActivity", "get cityId, begin to requestHotPaths");
                ClockingRankActivity.this.x = gzi.c().equals(empVar.d().getCityId());
                ClockingRankActivity.this.e(gzi.c(), gzi.f());
            }
        });
    }

    private void ax() {
        emc.d().getHotPathDetail(new emu.b().d(gzi.a()).b(), new UiCallback<emv>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Track_ClockingRankActivity", "cannot get hotPathDetail, errorCode = ", Integer.valueOf(i), ", errorInfo = " + str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emv emvVar) {
                if (emvVar == null || emvVar.b() == null) {
                    ClockingRankActivity clockingRankActivity = ClockingRankActivity.this;
                    nrh.d(clockingRankActivity, ((ClockingRankActivity) clockingRankActivity.c.get()).getString(R.string._2130840058_res_0x7f0209fa));
                    ClockingRankActivity.this.finish();
                } else {
                    String b2 = emvVar.b().b();
                    if (!TextUtils.isEmpty(b2)) {
                        LogUtil.a("Track_ClockingRankActivity", "get cityId = ", b2, " begin to requestHotPaths");
                        gzi.c(b2);
                    }
                    ClockingRankActivity.this.au();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        emc.d().getCityInfoList(new emk.c().c(RunningRouteUtils.b()).e(), new UiCallback<emt>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.9
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a("Track_ClockingRankActivity", "getCityInfoList failed, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emt emtVar) {
                for (HotPathCityInfo hotPathCityInfo : emtVar.a()) {
                    if (hotPathCityInfo.getCityId().equals(gzi.c())) {
                        LogUtil.c("Track_ClockingRankActivity", "hotPathCityInfo = ", hotPathCityInfo);
                        gzi.d(hotPathCityInfo);
                    }
                }
                if (!ClockingRankActivity.this.y || ClockingRankActivity.this.n) {
                    ClockingRankActivity.this.y();
                }
            }
        });
    }

    private boolean aq() {
        return this.h == RunningRouteConstants.BiFromActivity.FROM_HISTORY.getIndex();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        gzh.c(i, str, gzi.a(), this.j, e());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        as();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        emc.d().removeLocationUpdates();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.ab.b();
        super.onDestroy();
    }

    @Override // com.huawei.healthcloud.plugintrack.runningroute.view.IRouteDetail
    public void show(final String str) {
        LogUtil.a("Track_ClockingRankActivity", "show pathId: ", str, " detail");
        HandlerExecutor.e(new Runnable() { // from class: hdt
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.b(str);
            }
        });
    }

    public /* synthetic */ void b(String str) {
        gzi.a(str);
        this.h = 2;
        this.y = true;
        this.q = true;
        this.ab.g();
        at();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(npv npvVar) {
        d(npvVar.d() == null ? "PositioningFailed" : null);
        boolean equals = gzi.d().getCityName().equals(npvVar.b());
        this.x = equals;
        LogUtil.a("Track_ClockingRankActivity", "mIsRouteAndUserInSameCity = ", Boolean.valueOf(equals));
        gzi.c(gzi.d().getCityId());
        if (gzi.j()) {
            e(gzi.c(), gzi.h());
            return;
        }
        if (this.x) {
            LogUtil.a("Track_ClockingRankActivity", "same city, mCityId = ", gzi.c());
            e(gzi.c(), gzi.f());
        } else {
            LogUtil.a("Track_ClockingRankActivity", "different city, mCityId = ", gzi.c());
            GpsPoint cityCenter = gzi.d().getCityCenter();
            if (cityCenter != null) {
                e(gzi.c(), new hjd(cityCenter.getLatitude(), cityCenter.getLongitude()));
            }
        }
        HandlerExecutor.d(new Runnable() { // from class: hdl
            @Override // java.lang.Runnable
            public final void run() {
                ClockingRankActivity.this.j();
            }
        }, 200L);
    }

    public /* synthetic */ void j() {
        nsy.cMA_(this.v, 8);
    }

    public boolean b() {
        return this.y;
    }

    public void d(boolean z) {
        this.q = z;
    }

    public boolean a() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        this.ab.a(this.k);
        d(0, "m0");
    }

    /* loaded from: classes4.dex */
    public static class d extends UiCallback<emv> {
        private WeakReference<ClockingRankActivity> e;

        public d(ClockingRankActivity clockingRankActivity) {
            this.e = new WeakReference<>(clockingRankActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emv emvVar) {
            WeakReference<ClockingRankActivity> weakReference = this.e;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("Track_ClockingRankActivity", "HotPathsSizeCallBack weakRef is null");
                return;
            }
            final ClockingRankActivity clockingRankActivity = this.e.get();
            if (clockingRankActivity == null || clockingRankActivity.isFinishing() || clockingRankActivity.isDestroyed()) {
                LogUtil.h("Track_ClockingRankActivity", "ClockingRankActivity is finish");
                return;
            }
            if (emvVar == null || emvVar.b() == null) {
                nrh.d(clockingRankActivity, clockingRankActivity.getString(R.string._2130840058_res_0x7f0209fa));
                clockingRankActivity.finish();
                return;
            }
            enc b = emvVar.b();
            clockingRankActivity.j = b.n();
            clockingRankActivity.z = b.g();
            clockingRankActivity.k = b;
            if (koq.c(b.k())) {
                clockingRankActivity.ah = b.k().get(0);
            }
            if (clockingRankActivity.y) {
                clockingRankActivity.u();
            }
            clockingRankActivity.aj = (int) b.j();
            clockingRankActivity.d(b, clockingRankActivity.ah);
            clockingRankActivity.c(emvVar.d(), b);
            clockingRankActivity.c(b);
            if (b.f() == 0) {
                clockingRankActivity.aa.setVisibility(8);
                clockingRankActivity.bc.setVisibility(8);
            } else {
                clockingRankActivity.aa.setVisibility(0);
                clockingRankActivity.bc.setVisibility(0);
            }
            if (b.g() != 1) {
                clockingRankActivity.d(b);
            } else {
                clockingRankActivity.ad();
                nsy.cMA_(clockingRankActivity.i, 0);
                clockingRankActivity.bh();
            }
            clockingRankActivity.b.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: hdy
                @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
                public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    ClockingRankActivity.d.c(ClockingRankActivity.this, appBarLayout, i);
                }
            });
            nsy.cMA_(clockingRankActivity.v, 8);
        }

        public static /* synthetic */ void c(ClockingRankActivity clockingRankActivity, AppBarLayout appBarLayout, int i) {
            if (clockingRankActivity.g == null || clockingRankActivity.f3635a == null) {
                return;
            }
            clockingRankActivity.g.setIsOnTop(clockingRankActivity.f3635a, i == 0);
            clockingRankActivity.f3635a.setIsOnTop(i == 0);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Track_ClockingRankActivity", "getHotPathDetail fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(enc encVar) {
        if (this.bb == null || this.ax == null) {
            return;
        }
        gzb gzbVar = new gzb(this, this.ax, this.bb);
        gzbVar.c(this.f3635a);
        gzbVar.d(new BaseResponseCallback() { // from class: hcy
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            public final void onResponse(int i, Object obj) {
                ClockingRankActivity.this.b(i, (String) obj);
            }
        });
        gzbVar.aXe_(this.bc);
        gzbVar.a(encVar);
    }

    public /* synthetic */ void b(int i, String str) {
        LogUtil.a("Track_ClockingRankActivity", "activity.mIsShowPathDetail = ", Boolean.valueOf(this.y));
        if (this.y) {
            final RouteHintMvpView routeHintMvpView = new RouteHintMvpView(this);
            if (i == 0) {
                routeHintMvpView.setContent(getResources().getString(R.string._2130840202_res_0x7f020a8a));
            } else {
                routeHintMvpView.setContent(getResources().getString(R.string._2130840203_res_0x7f020a8b));
            }
            routeHintMvpView.setHeadView(str, new BaseResponseCallback() { // from class: hco
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                public final void onResponse(int i2, Object obj) {
                    ClockingRankActivity.this.a(routeHintMvpView, i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void a(RouteHintMvpView routeHintMvpView, int i, Object obj) {
        if (isFinishing()) {
            return;
        }
        LogUtil.a("Track_ClockingRankActivity", "setMvpMark");
        this.ab.d(routeHintMvpView);
    }

    /* loaded from: classes4.dex */
    static class c extends UiCallback<emz> {
        private ene c;
        private WeakReference<ClockingRankActivity> d;

        public c(ClockingRankActivity clockingRankActivity, ene eneVar) {
            this.d = new WeakReference<>(clockingRankActivity);
            this.c = eneVar;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emz emzVar) {
            LogUtil.a("Track_ClockingRankActivity", "hotPaths: ", emzVar.toString());
            WeakReference<ClockingRankActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("Track_ClockingRankActivity", "HotPathsSizeCallBack weakRef is null");
                return;
            }
            final ClockingRankActivity clockingRankActivity = this.d.get();
            final List<enf> e = emzVar.e();
            if (e != null) {
                clockingRankActivity.o.addAll(e);
                LogUtil.a("Track_ClockingRankActivity", "hotPathOperationInfo size: ", Integer.valueOf(e.size()));
                if (clockingRankActivity.n) {
                    if (clockingRankActivity.ar()) {
                        gzi.a(e.get(0).i());
                        LogUtil.a("Track_ClockingRankActivity", "mCurPathId = ", gzi.a());
                    }
                    clockingRankActivity.at();
                    return;
                }
                clockingRankActivity.runOnUiThread(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity.c.2
                    @Override // java.lang.Runnable
                    public void run() {
                        clockingRankActivity.ab.c(clockingRankActivity, e);
                        clockingRankActivity.c(c.this.c.a() == 1);
                    }
                });
                return;
            }
            LogUtil.a("Track_ClockingRankActivity", "hotPathOperationInfo is null");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Track_ClockingRankActivity", "getHotPaths fail, errorCode: ", Integer.valueOf(i), ", errorInfo: ", str);
        }
    }

    /* loaded from: classes4.dex */
    static class g extends UiCallback<emz> {

        /* renamed from: a, reason: collision with root package name */
        private ene f3640a;
        private WeakReference<ClockingRankActivity> d;
        private hjd e;

        public g(ClockingRankActivity clockingRankActivity, ene eneVar, hjd hjdVar) {
            this.d = new WeakReference<>(clockingRankActivity);
            this.f3640a = eneVar;
            this.e = hjdVar;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emz emzVar) {
            WeakReference<ClockingRankActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("Track_ClockingRankActivity", "HotPathsSizeCallBack weakRef is null");
                return;
            }
            ClockingRankActivity clockingRankActivity = this.d.get();
            if (emzVar == null) {
                LogUtil.a("Track_ClockingRankActivity", "getHotPathsRsp is null");
                return;
            }
            int b = emzVar.b();
            if (emzVar.c() != null && clockingRankActivity.an != null) {
                clockingRankActivity.an.e(emzVar.c());
                clockingRankActivity.d();
            }
            if (clockingRankActivity.ab.a(b)) {
                clockingRankActivity.ab.d();
                clockingRankActivity.ab.c(gzi.f());
                int ceil = (int) Math.ceil((b * 1.0f) / 100.0f);
                clockingRankActivity.o.clear();
                for (int i = 1; i <= ceil; i++) {
                    this.f3640a.d(i);
                    this.f3640a.c(100);
                    if (!clockingRankActivity.ar() || i <= 1) {
                        clockingRankActivity.d(this.f3640a);
                    } else {
                        LogUtil.a("Track_ClockingRankActivity", "get nearest route");
                        return;
                    }
                }
                gyq gyqVar = clockingRankActivity.an;
                if (clockingRankActivity.s && gyqVar != null) {
                    gzh.e(gyqVar.d(), clockingRankActivity.h, clockingRankActivity.af, clockingRankActivity.aj);
                }
            }
            if (gzi.j()) {
                clockingRankActivity.ab.d(gzi.h());
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Track_ClockingRankActivity", "errorCode: ", Integer.valueOf(i), ", errorInfo: ", str);
            WeakReference<ClockingRankActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("Track_ClockingRankActivity", "HotPathsSizeCallBack weakRef is null");
                return;
            }
            ClockingRankActivity clockingRankActivity = this.d.get();
            if (!gzi.j()) {
                clockingRankActivity.ab.b(this.e);
            } else {
                clockingRankActivity.ab.d(gzi.h());
            }
        }
    }

    /* loaded from: classes4.dex */
    static class a extends UiCallback<emp> {
        private WeakReference<ClockingRankActivity> b;
        private hjd c;

        public a(ClockingRankActivity clockingRankActivity, hjd hjdVar) {
            this.b = new WeakReference<>(clockingRankActivity);
            this.c = hjdVar;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emp empVar) {
            WeakReference<ClockingRankActivity> weakReference = this.b;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("Track_ClockingRankActivity", "GetCityInfoWithGpsCallBack weakRef is null");
                return;
            }
            ClockingRankActivity clockingRankActivity = this.b.get();
            if (empVar == null || empVar.d() == null || TextUtils.isEmpty(empVar.d().getCityId())) {
                LogUtil.a("Track_ClockingRankActivity", "invalid city id");
                return;
            }
            String cityId = gzi.d().getCityId();
            if (TextUtils.isEmpty(cityId) || !cityId.equals(empVar.d().getCityId())) {
                gzi.d(empVar.d());
                gzi.c(empVar.d().getCityId());
                clockingRankActivity.at();
            }
            if (gzi.j()) {
                return;
            }
            clockingRankActivity.e(empVar.d().getCityId(), this.c);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Track_ClockingRankActivity", "getCityInfoWithGps fail, errorCode: ", Integer.valueOf(i), "errorInfo: ", str);
        }
    }

    /* loaded from: classes4.dex */
    static class b implements ICityLatLonCallBack {
        private int c;
        private WeakReference<ClockingRankActivity> d;

        public b(ClockingRankActivity clockingRankActivity, int i) {
            this.d = new WeakReference<>(clockingRankActivity);
            this.c = i;
        }

        @Override // com.huawei.ui.commonui.model.ICityLatLonCallBack
        public void onPointBack(npv npvVar) {
            Object[] objArr = new Object[2];
            objArr[0] = "addressInfo: ";
            objArr[1] = npvVar == null ? Constants.NULL : npvVar.toString();
            LogUtil.a("Track_ClockingRankActivity", objArr);
            WeakReference<ClockingRankActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("Track_ClockingRankActivity", "GetCityCenterPointCallBack weakRef is null");
                return;
            }
            ClockingRankActivity clockingRankActivity = this.d.get();
            if (npvVar == null) {
                LogUtil.a("Track_ClockingRankActivity", "regeocodeAddressInfo is null");
                return;
            }
            int i = this.c;
            if (i == 1) {
                LogUtil.a("Track_ClockingRankActivity", "refresh map markers");
                clockingRankActivity.d(npvVar);
            } else {
                if (i != 2) {
                    return;
                }
                LogUtil.a("Track_ClockingRankActivity", "refresh route detail address");
                nsy.cMr_(clockingRankActivity.ag, npvVar.c());
            }
        }
    }

    /* loaded from: classes4.dex */
    static class h extends BaseHandler<ClockingRankActivity> {
        h(ClockingRankActivity clockingRankActivity) {
            super(clockingRankActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: baR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ClockingRankActivity clockingRankActivity, Message message) {
            if (clockingRankActivity == null || message == null) {
                ReleaseLogUtil.e("Track_ClockingRankActivity", "mStartSportHandler msg is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                clockingRankActivity.l();
            } else if (i == 2) {
                clockingRankActivity.be();
            } else {
                if (i != 3) {
                    return;
                }
                clockingRankActivity.c(message.arg1);
            }
        }
    }

    /* loaded from: classes4.dex */
    static class e implements IBaseResponseCallback {
        WeakReference<ClockingRankActivity> e;

        e(ClockingRankActivity clockingRankActivity) {
            this.e = new WeakReference<>(clockingRankActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 100000 && obj != null && (obj instanceof String)) {
                OperatorStatus operatorStatus = (OperatorStatus) new Gson().fromJson(CommonUtil.z((String) obj), OperatorStatus.class);
                ClockingRankActivity clockingRankActivity = this.e.get();
                if (clockingRankActivity == null || clockingRankActivity.aw == null) {
                    return;
                }
                Handler handler = clockingRankActivity.aw;
                handler.removeMessages(1);
                if (operatorStatus.getTrainMonitorState() == 0) {
                    ReleaseLogUtil.e("Track_ClockingRankActivity", "device is not running");
                    handler.sendMessage(handler.obtainMessage(1));
                } else {
                    ReleaseLogUtil.e("Track_ClockingRankActivity", "MSG_SHOW_DIALOG");
                    handler.sendMessage(handler.obtainMessage(2));
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
