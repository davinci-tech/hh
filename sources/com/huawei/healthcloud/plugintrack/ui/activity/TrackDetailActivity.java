package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.nps.api.NpsExternalApi;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.socialshare.model.SaveBitampCallback;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface;
import com.huawei.healthcloud.plugintrack.model.ShareInitCallback;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.DetailFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.HeartRateFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.PaceFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.SegmentFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingPerformanceFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.SportShortTrackShareFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackShareAllDataFragment;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackSwimSegmentFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.TriathlonSubDataFrag;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.js.JsInteractAddition;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.cwa;
import defpackage.ehr;
import defpackage.fdu;
import defpackage.fdz;
import defpackage.feh;
import defpackage.gnp;
import defpackage.gso;
import defpackage.gvv;
import defpackage.gwa;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.gwk;
import defpackage.gwo;
import defpackage.gwq;
import defpackage.gwy;
import defpackage.gxp;
import defpackage.gxy;
import defpackage.gya;
import defpackage.hjd;
import defpackage.hji;
import defpackage.hjw;
import defpackage.hpq;
import defpackage.ixx;
import defpackage.jcu;
import defpackage.jdv;
import defpackage.jdx;
import defpackage.koq;
import defpackage.ktl;
import defpackage.nqx;
import defpackage.nrv;
import defpackage.nrw;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.smy;
import defpackage.sqc;
import defpackage.sqd;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

/* loaded from: classes.dex */
public class TrackDetailActivity extends TrackBaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private nqx f3668a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private int af;
    private MotionPathSimplify ag;
    private PaceFrag ah;
    private MotionPath ai;
    private d aj;
    private boolean ak;
    private final Resources al;
    private SkippingPerformanceFrag am;
    private HealthViewPager an;
    private List<String> ao;
    private SegmentFrag ap;
    private Bitmap aq;
    private TriathlonSubDataFrag ar;
    private ShareInitCallback as;
    private List<gya> at;
    private TrackShareAllDataFragment au;
    private hjw av;
    private HealthSubTabWidget aw;
    private TrackScreenFrag ax;
    private int ay;
    private SharedPreferences az;
    private String b;
    private TrackSwimSegmentFrag ba;
    private CommonDialog21 bb;
    private HealthViewPager bc;
    private final Context c;
    private Bundle f;
    private long[] g;
    private int h;
    private BroadcastReceiver i;
    private long j;
    private String k;
    private boolean l;
    private DetailFrag m;
    private CustomTitleBar n;
    private Context o;
    private Map<String, Integer> p;
    private String q;
    private float r;
    private List<Fragment> s;
    private LoadMapListener t;
    private boolean u;
    private boolean v;
    private HealthTextView w;
    private final c x;
    private HeartRateFrag y;
    private long z;
    private static final int[] e = {2, 1, 10};
    private static final int[] d = {1, 10};

    /* loaded from: classes4.dex */
    public interface LoadMapListener {
        void endLoadMap();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public float getMaxFontScale() {
        return 1.0f;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected ViewHolderInterface initViewHolder() {
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected boolean isNeedLocationPermission() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected boolean isNeedStorgePermission() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected void setActvityLayoutModel() {
    }

    public TrackDetailActivity() {
        Context e2 = BaseApplication.e();
        this.c = e2;
        this.al = e2.getResources();
        this.x = new c(this);
        this.f = null;
        this.aj = null;
        this.ai = null;
        this.ag = new MotionPathSimplify();
        this.av = null;
        this.at = new ArrayList(10);
        this.o = null;
        this.z = 0L;
        this.r = 1.0f;
        this.ab = false;
        this.ad = false;
        this.l = false;
        this.g = new long[7];
        this.h = 0;
        this.ac = false;
        this.p = new HashMap(7);
        this.af = 0;
        this.aq = null;
        this.bb = null;
        this.s = null;
        this.ae = false;
        this.ay = 0;
        this.ao = new ArrayList(10);
        this.bc = null;
        this.v = false;
        this.u = true;
        this.t = new LoadMapListener() { // from class: hfn
            @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity.LoadMapListener
            public final void endLoadMap() {
                TrackDetailActivity.this.h();
            }
        };
        this.as = new ShareInitCallback() { // from class: hfq
            @Override // com.huawei.healthcloud.plugintrack.model.ShareInitCallback
            public final void onFinish() {
                TrackDetailActivity.this.j();
            }
        };
        this.ak = false;
        this.i = new b();
    }

    public /* synthetic */ void h() {
        c(true);
    }

    public /* synthetic */ void j() {
        this.x.sendEmptyMessage(2);
    }

    private void al() {
        int requestSportType;
        setContentView(R.layout.track_detail_page);
        hjw hjwVar = this.av;
        if (hjwVar != null && hjwVar.e() != null && ((requestSportType = this.av.e().requestSportType()) == 283 || requestSportType == 290)) {
            ((CustomTitleBar) findViewById(R.id.track_detail_title_bar)).setTitleBarBackgroundColor(ContextCompat.getColor(this.c, R.color._2131296690_res_0x7f0901b2));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this.c, R.color._2131296690_res_0x7f0901b2));
        }
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.track_share_viewpager);
        this.bc = healthViewPager;
        healthViewPager.setPageMargin(this.al.getDimensionPixelSize(R.dimen._2131362058_res_0x7f0a010a));
        av();
        ac();
        c(false);
        this.aw = (HealthSubTabWidget) findViewById(R.id.track_detail_sub_tab_layout);
        af();
        d(this.ab);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.guide_user_share_content);
        this.w = healthTextView;
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: hfk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDetailActivity.this.bcP_(view);
            }
        });
    }

    public /* synthetic */ void bcP_(View view) {
        z();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        TrackScreenFrag trackScreenFrag;
        if (this.u && ((trackScreenFrag = this.ax) == null || trackScreenFrag.a())) {
            z();
            this.u = false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void av() {
        if (this.bc == null) {
            LogUtil.h("Track_TrackDetailActivity", "[setViewPagerHeight] view is null");
            return;
        }
        Object systemService = this.o.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.b("Track_TrackDetailActivity", "object is not instanceof WindowManager");
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = this.bc.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = (i * 8) / 5;
        this.bc.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        av();
        at();
    }

    private void ac() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.track_detail_title_bar);
        this.n = customTitleBar;
        customTitleBar.setRightSoftkeyBackground(ContextCompat.getDrawable(this.c, R.drawable._2131427714_res_0x7f0b0182), nsf.h(R.string._2130844500_res_0x7f021b54));
        this.n.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrackDetailActivity.this.x();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (LanguageUtil.bc(this)) {
            this.n.setRightButtonDrawable(ContextCompat.getDrawable(this.c, R.drawable._2131430036_res_0x7f0b0a94), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.n.setRightButtonDrawable(ContextCompat.getDrawable(this.c, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.n.setRightButtonOnClickListener(new View.OnClickListener() { // from class: hfx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDetailActivity.this.bcN_(view);
            }
        });
        this.n.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: hfg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackDetailActivity.this.bcO_(view);
            }
        });
    }

    public /* synthetic */ void bcN_(View view) {
        if (PermissionUtil.c()) {
            u();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.o, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(this.o) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity.3
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    TrackDetailActivity.this.u();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void bcO_(View view) {
        if (!this.l) {
            this.x.postDelayed(new Runnable() { // from class: hfh
                @Override // java.lang.Runnable
                public final void run() {
                    TrackDetailActivity.this.i();
                }
            }, 50L);
        }
        if (aj()) {
            be();
        }
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void i() {
        if (!CommonUtil.af(this.o) || this.v) {
            return;
        }
        LogUtil.h("Track_TrackDetailActivity", "restartApp.");
        a(this.o);
    }

    private void af() {
        ai();
        String y = y();
        ab();
        e(y);
    }

    private void e(String str) {
        MotionPathSimplify motionPathSimplify;
        if (this.ax != null) {
            this.f3668a.c(this.aw.c(this.al.getString(R.string._2130839771_res_0x7f0208db)), this.ax, true);
        }
        if (this.ah != null || this.ba != null) {
            smy c2 = this.aw.c(str);
            if (this.ac) {
                this.f3668a.c(c2, this.ba, false);
            } else {
                this.f3668a.c(c2, this.ah, false);
            }
        }
        if (this.ap != null && (motionPathSimplify = this.ag) != null) {
            this.f3668a.c(this.aw.c(hji.a(this.o, motionPathSimplify.requestSportType(), gvv.c(this.ag))), this.ap, false);
        }
        if (this.y != null) {
            this.f3668a.c(this.aw.c(this.al.getString(R.string._2130839772_res_0x7f0208dc)), this.y, false);
        }
        if (this.m != null) {
            smy c3 = this.aw.c(this.al.getString(R.string._2130839773_res_0x7f0208dd));
            hjw hjwVar = this.av;
            if (hjwVar != null && hjwVar.b(0)) {
                this.f3668a.c(c3, this.m, true);
            } else {
                this.f3668a.c(c3, this.m, false);
            }
        }
        ae();
        if (this.ar != null) {
            this.f3668a.c(this.aw.c(this.al.getString(R.string._2130839849_res_0x7f020929)), this.ar, this.ax == null);
        }
    }

    private void ae() {
        if (this.am != null) {
            this.aw.setBackgroundResource(R.color._2131296690_res_0x7f0901b2);
            this.f3668a.c(this.aw.c(this.al.getString(R.string._2130847651_res_0x7f0227a3)), this.am, "fromRopePerformanceHistoryFragment".equals(this.q));
        }
    }

    private void ai() {
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.pager);
        this.an = healthViewPager;
        healthViewPager.setIsScroll(false);
        d dVar = new d();
        this.aj = dVar;
        this.an.addOnPageChangeListener(dVar);
        this.an.setOffscreenPageLimit(7);
        this.f3668a = new nqx(this, this.an, this.aw);
    }

    private String y() {
        hjw hjwVar = this.av;
        if (hjwVar != null && hjwVar.e() != null) {
            int requestSportType = this.av.e().requestSportType();
            if (this.av.aw()) {
                return this.al.getString(R.string._2130844076_res_0x7f0219ac);
            }
            if (requestSportType == 262 || requestSportType == 266) {
                this.ac = true;
                return this.al.getString(R.string._2130839835_res_0x7f02091b);
            }
            return this.al.getString(R.string._2130844083_res_0x7f0219b3);
        }
        return this.al.getString(R.string._2130844083_res_0x7f0219b3);
    }

    public long c() {
        Bundle extras;
        if (getIntent() == null || (extras = getIntent().getExtras()) == null) {
            return -1L;
        }
        return extras.getLong("hotTrackParticipateNumKey", -1L);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        TrackScreenFrag trackScreenFrag;
        if (aj()) {
            be();
        }
        if (this.aa && (trackScreenFrag = this.ax) != null) {
            trackScreenFrag.j();
        }
        if (!this.l && !this.ae) {
            this.x.postDelayed(new Runnable() { // from class: hfr
                @Override // java.lang.Runnable
                public final void run() {
                    TrackDetailActivity.this.g();
                }
            }, 50L);
        }
        super.onBackPressed();
    }

    public /* synthetic */ void g() {
        if (!CommonUtil.af(this.o) || this.v) {
            return;
        }
        LogUtil.h("Track_TrackDetailActivity", "restartApp.");
        a(this.o);
    }

    @Override // android.app.Activity
    public void finish() {
        LogUtil.a("Track_TrackDetailActivity", "finish.", Boolean.valueOf(this.ae));
        if (this.ae) {
            moveTaskToBack(true);
        }
        super.finish();
    }

    private static void a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return;
        }
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            LogUtil.h("Track_TrackDetailActivity", "restartApp intent is null.");
            return;
        }
        try {
            launchIntentForPackage.addFlags(AppRouterExtras.COLDSTART);
            context.startActivity(launchIntentForPackage);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_TrackDetailActivity", "restartApp ActivityNotFoundException.");
        }
    }

    private void m() {
        hjw hjwVar = new hjw();
        this.av = hjwVar;
        hjwVar.e(this.ag);
        this.av.e(this.ai);
        this.av.c(this.at);
        this.av.k();
        this.av.a(am());
    }

    private boolean am() {
        if (this.f == null || this.ag == null) {
            ReleaseLogUtil.c("Track_TrackDetailActivity", "mBundle or mMotionPathSimplify is null");
            return false;
        }
        boolean equals = "fromTrackMainMap".equals(this.q);
        int i = this.f.getInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, -1);
        LogUtil.a("Track_TrackDetailActivity", "isFromMainMapActivity ", Boolean.valueOf(equals), " TargetType ", Integer.valueOf(i));
        if (!equals || i != -1) {
            return false;
        }
        if (!gwy.e(this.ag.requestTotalTime(), this.ag.requestTrackType())) {
            ReleaseLogUtil.d("Track_TrackDetailActivity", "sport total time is over");
            return false;
        }
        return gwy.e(this.ag.requestSportType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (d()) {
            LogUtil.a("Track_TrackDetailActivity", "onClick() if (isClickFast())");
            return;
        }
        HeartRateFrag heartRateFrag = this.y;
        if (heartRateFrag != null) {
            if (heartRateFrag.e()) {
                LogUtil.a("Track_TrackDetailActivity", "dont click share and chart sametime");
                return;
            }
            this.y.b();
        }
        bf();
        a(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (d()) {
            LogUtil.a("Track_TrackDetailActivity", "onClick() if (isClickFast())");
            return;
        }
        HeartRateFrag heartRateFrag = this.y;
        if (heartRateFrag != null) {
            if (heartRateFrag.e()) {
                LogUtil.a("Track_TrackDetailActivity", "dont click share and chart same time");
                return;
            }
            this.y.b();
        }
        z();
        bi();
        a(4);
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("trackType", Integer.valueOf(e(hashMap)));
        ixx.d().d(this.c, AnalyticsValue.MOTION_TRACK_1040022.value(), hashMap, 0);
    }

    private int e(Map<String, Object> map) {
        MotionPathSimplify e2;
        hjw hjwVar = this.av;
        if (hjwVar == null || (e2 = hjwVar.e()) == null) {
            return 0;
        }
        map.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.av.e().requestSportType()));
        int requestTrackType = e2.requestTrackType();
        if (e2.requestSportDataSource() == 2) {
            return 6;
        }
        return requestTrackType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        TrackScreenFrag trackScreenFrag = this.ax;
        if (trackScreenFrag != null) {
            trackScreenFrag.d(i);
        }
        d(i);
        if (i == this.p.get("paceFrag").intValue() || i == this.p.get("subSport").intValue()) {
            this.aa = true;
        } else {
            this.aa = false;
        }
    }

    private void d(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        long[] jArr = this.g;
        int i2 = this.h;
        jArr[i2] = jArr[i2] + (currentTimeMillis - this.j);
        this.h = i;
        this.j = currentTimeMillis;
    }

    public boolean d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (1000 > currentTimeMillis - this.z) {
            LogUtil.a("Track_TrackDetailActivity", "onClick", ">_< >_< click too much");
            this.z = currentTimeMillis;
            return true;
        }
        this.z = currentTimeMillis;
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("android:support:fragments", null);
            Bundle bundle2 = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
            if (bundle2 != null) {
                bundle2.putParcelable("android:support:fragments", null);
            }
        }
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        cancelMarginAdaptation();
        cancelAdaptRingRegion();
        this.j = System.currentTimeMillis();
        r();
        this.o = this;
        p();
        q();
        m();
        aa();
        this.r = this.al.getConfiguration().fontScale;
        al();
        bg();
        l();
        bc();
        ag();
        au();
        ao();
    }

    /* loaded from: classes4.dex */
    class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            ReleaseLogUtil.e("Track_TrackDetailActivity", "ProcessReceiver enter ");
            Serializable serializableExtra = intent.getSerializableExtra("AfterProcessTrack");
            if (serializableExtra instanceof Map) {
                TrackDetailActivity.this.d((Map<Long, double[]>) serializableExtra);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<Long, double[]> map) {
        hjw hjwVar = this.av;
        if (hjwVar == null || hjwVar.d() == null) {
            ReleaseLogUtil.c("Track_TrackDetailActivity", "mTrackDetailDataManager is null in ProcessReceiver");
            return;
        }
        if (map == null) {
            ReleaseLogUtil.c("Track_TrackDetailActivity", "gpsInfoMap is null in ProcessReceiver");
            return;
        }
        LogUtil.a("Track_TrackDetailActivity", "refreshMotionPathAfterProecess size ", Integer.valueOf(map.size()));
        MotionPath d2 = this.av.d();
        d2.saveLbsDataMap(map);
        this.av.e(d2);
        this.av.bc();
        HandlerExecutor.a(new Runnable() { // from class: hfj
            @Override // java.lang.Runnable
            public final void run() {
                TrackDetailActivity.this.f();
            }
        });
        gwq.a().j();
    }

    public /* synthetic */ void f() {
        TrackScreenFrag trackScreenFrag = this.ax;
        if (trackScreenFrag == null || this.av == null) {
            return;
        }
        trackScreenFrag.e();
        this.av.a(false);
    }

    private void ao() {
        if (gwq.a().b()) {
            ReleaseLogUtil.e("Track_TrackDetailActivity", "refreshMotionPathAfterProcess enter ");
            d(gwq.a().c());
            return;
        }
        ReleaseLogUtil.e("Track_TrackDetailActivity", "registerAfterProcessBroadcast enter ");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.update_process_track");
        BroadcastManagerUtil.bFA_(this.o, this.i, intentFilter, SecurityConstant.d, null);
        this.ak = true;
    }

    private void bk() {
        Context context;
        BroadcastReceiver broadcastReceiver;
        if (!this.ak || (context = this.o) == null || (broadcastReceiver = this.i) == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
        this.ak = false;
    }

    private void au() {
        hjw hjwVar = this.av;
        if (hjwVar == null || hjwVar.e() == null) {
            return;
        }
        MotionPathSimplify e2 = this.av.e();
        HashMap hashMap = new HashMap(16);
        hashMap.put("sportId", e2.requestSportId());
        if (!Utils.o()) {
            hashMap.put("startTime", String.valueOf(e2.requestStartTime()));
            hashMap.put("endTime", String.valueOf(e2.requestEndTime()));
            hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, Integer.valueOf((int) TimeUnit.MILLISECONDS.toSeconds(e2.requestTotalTime())));
            hashMap.put("distances", Integer.valueOf(e2.requestTotalDistance()));
            hashMap.put("calories", Integer.valueOf(e2.requestTotalCalories()));
            hashMap.put("totalSteps", Integer.valueOf(e2.requestTotalSteps()));
            hashMap.put("avgHeartRate", Integer.valueOf(e2.requestAvgHeartRate()));
        }
        hashMap.put("trackType", Integer.valueOf(e(hashMap)));
        ixx.d().d(this.c, AnalyticsValue.BI_TRACK_SPORT_DETAIL_SPORT_KEY.value(), hashMap, 0);
    }

    private void bc() {
        if (this.av.s()) {
            LogUtil.a("Track_TrackDetailActivity", "isAutomaticIdentification");
            ay();
        } else if (this.av.az()) {
            LogUtil.a("Track_TrackDetailActivity", "isTrackAbnormal");
            this.x.sendEmptyMessageDelayed(13, 500L);
        } else if (this.av.p()) {
            LogUtil.a("Track_TrackDetailActivity", "isDuplicated");
            bb();
        } else {
            MotionPathSimplify motionPathSimplify = this.ag;
            if (motionPathSimplify != null && "1".equals(motionPathSimplify.requestExtendDataMap().get("STEP_STATICS_EXCEPTION"))) {
                hpq.c(this.o);
            } else {
                LogUtil.a("Track_TrackDetailActivity", "normal");
            }
        }
        if (this.ag != null && ak() && "fromTrackMainMap".equals(this.q)) {
            ehr.b(this.o).e(this.o, this.ag.requestTotalTime(), this.ag.requestTotalDistance());
            MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
            if (marketingApi == null) {
                LogUtil.a("Track_TrackDetailActivity", "marketingApi is null");
            } else {
                a(marketingApi);
                ax();
            }
        }
    }

    private void ax() {
        if (!an()) {
            LogUtil.a("Track_TrackDetailActivity", "not nps target ");
        } else {
            ((NpsExternalApi) Services.c("Main", NpsExternalApi.class)).sendNpsAfterRun();
        }
    }

    private boolean an() {
        return this.ag.requestSportType() == 258 && (this.ag.requestTotalTime() > 1800000 || this.ag.requestTotalDistance() > 3000);
    }

    private void a(MarketingApi marketingApi) {
        if (this.ag.requestSportType() == 258) {
            b(marketingApi, 230);
            d(marketingApi, 27, 230);
            d(marketingApi, 200, 230);
        } else if (this.ag.requestSportType() == 264) {
            b(marketingApi, 230);
            d(marketingApi, 30, 230);
            d(marketingApi, 200, 230);
        } else if (this.ag.requestSportType() == 257) {
            b(marketingApi, 250);
            d(marketingApi, 38, 250);
        } else {
            LogUtil.h("Track_TrackDetailActivity", "mMotionPathSimplify.requestSportType() is other");
        }
    }

    private void d(MarketingApi marketingApi, int i, int i2) {
        MarketingOption.Builder builder = new MarketingOption.Builder();
        builder.setContext(this.o);
        builder.setPageId(i2);
        builder.setTypeId(i);
        if (i == 200) {
            HashMap hashMap = new HashMap();
            hashMap.put("running_distance_data", Integer.valueOf(this.ag.requestTotalDistance()));
            builder.setTriggerEventParams(hashMap);
        }
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    private void b(MarketingApi marketingApi, int i) {
        marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(this.o).setPageId(i).build());
    }

    private boolean ak() {
        int requestSportDataSource = this.ag.requestSportDataSource();
        return (requestSportDataSource == 2 || requestSportDataSource == 1 || this.ag.requestAbnormalTrack() != 0) ? false : true;
    }

    private void p() {
        if (getIntent() == null) {
            LogUtil.b("Track_TrackDetailActivity", "entranceFromNotifyBi intent is null!");
            finish();
            return;
        }
        Bundle extras = getIntent().getExtras();
        this.f = extras;
        if (extras == null) {
            LogUtil.b("Track_TrackDetailActivity", "entranceFromNotifyBi mBundle is null!");
            finish();
        }
    }

    private void l() {
        int c2;
        if (this.ax == null) {
            LogUtil.h("Track_TrackDetailActivity", "checkMapType mTrackFrag is null.");
            return;
        }
        gwg.b(this.o);
        double[] dArr = new double[2];
        if (!this.av.t()) {
            c2 = e(dArr, this.av);
        } else {
            c2 = c(dArr);
        }
        this.af = gwg.a();
        int e2 = gwg.e(this.o, dArr[0], dArr[1]);
        if (Utils.o()) {
            b(dArr, c2, e2);
        } else {
            e(dArr, c2, e2);
        }
    }

    private int c(double[] dArr) {
        hjw next;
        Iterator<hjw> it = this.av.i().iterator();
        int i = -1;
        while (it.hasNext() && ((next = it.next()) == null || next.d() == null || (i = e(dArr, next)) == -1)) {
        }
        return i;
    }

    private int e(double[] dArr, hjw hjwVar) {
        int i = -1;
        if (hjwVar == null) {
            LogUtil.b("Track_TrackDetailActivity", "getArea trackDetailDataManager is null");
            return -1;
        }
        MotionPath d2 = hjwVar.d();
        if (d2 == null) {
            LogUtil.b("Track_TrackDetailActivity", "getArea motionPath is null");
            return -1;
        }
        Map<Long, double[]> requestLbsDataMap = d2.requestLbsDataMap();
        if (requestLbsDataMap == null) {
            LogUtil.b("Track_TrackDetailActivity", "getArea lbsDataMap is null");
            return -1;
        }
        LogUtil.a("Track_TrackDetailActivity", "getArea lbsDataMap Size = ", Integer.valueOf(requestLbsDataMap.size()));
        Iterator<Map.Entry<Long, double[]>> it = requestLbsDataMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Long, double[]> next = it.next();
            if (next != null && next.getValue() != null && next.getValue().length >= 2) {
                dArr[0] = next.getValue()[0];
                dArr[1] = next.getValue()[1];
                if (!gwe.c(new hjd(dArr[0], dArr[1]))) {
                    i = ktl.b(dArr[0], dArr[1]);
                    break;
                }
            }
        }
        LogUtil.a("Track_TrackDetailActivity", "getArea area is ", Integer.valueOf(i));
        return i;
    }

    private void e(final double[] dArr, int i, int i2) {
        int i3 = this.af;
        if (i3 == 0) {
            b(i2, dArr[0], dArr[1]);
            return;
        }
        if (i3 == 1) {
            if (i == 2) {
                n();
            }
        } else if (i3 != 2) {
            if (i3 != 3) {
                return;
            }
            jdx.b(new Runnable() { // from class: hfu
                @Override // java.lang.Runnable
                public final void run() {
                    TrackDetailActivity.this.e(dArr);
                }
            });
        } else {
            if (as()) {
                return;
            }
            n();
        }
    }

    public /* synthetic */ void e(double[] dArr) {
        if (gwg.d(this.o, dArr[0], dArr[1])) {
            return;
        }
        d(dArr[0], dArr[1]);
        Message obtain = Message.obtain(this.x);
        if (obtain != null) {
            obtain.what = 12;
            obtain.sendToTarget();
        }
    }

    private void b(final double[] dArr, int i, int i2) {
        int i3 = this.af;
        if (i3 == -1) {
            n();
            return;
        }
        if (i3 == 0) {
            b(i2, dArr[0], dArr[1]);
            return;
        }
        if (i3 == 2) {
            if (as()) {
                return;
            }
            n();
        } else if (i3 == 3) {
            jdx.b(new Runnable() { // from class: hfl
                @Override // java.lang.Runnable
                public final void run() {
                    TrackDetailActivity.this.d(dArr);
                }
            });
        } else {
            n();
        }
    }

    public /* synthetic */ void d(double[] dArr) {
        if (gwg.d(this.o, dArr[0], dArr[1])) {
            return;
        }
        d(dArr[0], dArr[1]);
        Message obtain = Message.obtain(this.x);
        if (obtain != null) {
            obtain.what = 12;
            obtain.sendToTarget();
        }
    }

    private void b(int i, final double d2, final double d3) {
        if (i == 1) {
            if (ktl.b(d2, d3) == 2) {
                n();
            }
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            jdx.b(new Runnable() { // from class: hfm
                @Override // java.lang.Runnable
                public final void run() {
                    TrackDetailActivity.this.e(d2, d3);
                }
            });
        } else if (!gwg.c(this.o)) {
            bd();
        } else if (!as()) {
            n();
        } else {
            LogUtil.a("Track_TrackDetailActivity", "Support Google");
        }
    }

    public /* synthetic */ void e(double d2, double d3) {
        if (gwg.d(this.o, d2, d3)) {
            return;
        }
        d(d2, d3);
        Message obtain = Message.obtain(this.x);
        if (obtain != null) {
            obtain.what = 12;
            obtain.sendToTarget();
        }
    }

    private void bd() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.o);
        builder.e(this.c.getString(R.string._2130837688_res_0x7f0200b8)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: hft
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    private boolean as() {
        if (nrw.e(this.o) && !gwg.n(this.o)) {
            return false;
        }
        if (nrw.a(this.o) == 2) {
            return true;
        }
        if (nrw.a(this.o) == 0) {
            return !gwg.n(this.o) || Utils.o();
        }
        return false;
    }

    private void aa() {
        this.p.put("trackScreen", 0);
        this.p.put("paceFrag", 1);
        this.p.put("heartRateFrag", 2);
        this.p.put("detailFrag", 3);
        this.p.put("subSport", 4);
        this.p.put("segmentFrag", 5);
        this.p.put("performanceFrag", 6);
    }

    private void bg() {
        if (this.av.e() != null) {
            int requestSportType = this.av.e().requestSportType();
            String e2 = gwg.e(this.o, requestSportType);
            if (this.av.e().requestRunCourseId() != null) {
                String d2 = gwg.d(this.o, this.av.e().requestRunCourseId(), this.av.e().getExtendDataString("courseName", ""));
                if (!TextUtils.isEmpty(d2)) {
                    HashMap hashMap = new HashMap(1);
                    hashMap.put("click", "runCourseHistoryItem");
                    ixx.d().d(this.c, AnalyticsValue.HEALTH_CLICK_RUN_COURSE_HISTORY_ITEM_FROM_WEAR_1040059.value(), hashMap, 0);
                    e2 = d2;
                }
            } else {
                Map<String, String> requestExtendDataMap = this.av.e().requestExtendDataMap();
                String str = requestExtendDataMap.containsKey(HwExerciseConstants.JSON_NAME_RECORD_FLAG) ? requestExtendDataMap.get(HwExerciseConstants.JSON_NAME_RECORD_FLAG) : "";
                if (258 == requestSportType && !TextUtils.isEmpty(str) && "7".equals(str)) {
                    e2 = this.al.getString(R.string._2130845268_res_0x7f021e54);
                }
                if (requestSportType == 290) {
                    e2 = this.ag.getExtendDataString("alias", gwg.e(this.o, requestSportType));
                }
                String e3 = gwg.e(this.av.e());
                if (requestSportType == 222 && StringUtils.i(e3) && LanguageUtil.h(this.o)) {
                    e2 = String.format(this.o.getString(R.string._2130843638_res_0x7f0217f6), e2, e3);
                }
            }
            this.n.setTitleText(e2);
        }
    }

    private void r() {
        this.ax = null;
        this.ah = null;
        this.y = null;
        this.m = null;
        this.ba = null;
        this.ar = null;
    }

    private void q() {
        if (getIntent() != null) {
            try {
                this.b = getIntent().getStringExtra("movement");
            } catch (BadParcelableException unused) {
                LogUtil.b("Track_TrackDetailActivity", "getData catch BadParcelableException");
            }
            if (!TextUtils.isEmpty(this.b)) {
                Bundle aPD_ = gnp.aPD_(this.b);
                this.f = aPD_;
                if (aPD_ == null) {
                    LogUtil.b("Track_TrackDetailActivity", "jsonToBundle is null!");
                    finish();
                    return;
                }
                try {
                    MotionPathSimplify motionPathSimplify = (MotionPathSimplify) HiJsonUtil.e(aPD_.getString("simplifyDatakey"), MotionPathSimplify.class);
                    this.ag = motionPathSimplify;
                    if (motionPathSimplify == null) {
                        ReleaseLogUtil.e("Track_TrackDetailActivity", "mMotionPathSimplify is null in Json");
                        finish();
                        return;
                    }
                    if (motionPathSimplify.requestSportType() == 257 || this.ag.requestSportType() == 281) {
                        gnp.d("movement", this.o, -1L);
                    }
                    if (this.ag.requestSportType() == 258 || this.ag.requestSportType() == 264) {
                        gnp.d("movement_run", this.o, -1L);
                    }
                } catch (JsonSyntaxException unused2) {
                    LogUtil.b("Track_TrackDetailActivity", "parse MotionPathSimplify error");
                }
            } else {
                Bundle extras = getIntent().getExtras();
                this.f = extras;
                if (extras == null) {
                    LogUtil.b("Track_TrackDetailActivity", "mBundle is null!");
                    finish();
                    return;
                }
                this.ag = (MotionPathSimplify) extras.getSerializable("simplifyDatakey");
            }
            this.ad = this.f.getBoolean("isNotNeedDeleteFile", false);
            this.v = this.f.getBoolean("ExitApp", false);
            this.l = this.f.getBoolean("closeBLEConnection", false);
            this.q = this.f.getString("entrance");
            this.ae = this.f.getBoolean("moveTaskToBack", false);
            MotionPathSimplify motionPathSimplify2 = this.ag;
            if (motionPathSimplify2 == null) {
                LogUtil.b("Track_TrackDetailActivity", "getData() mMotionPathSimplify is null");
                finish();
                return;
            }
            if (motionPathSimplify2.requestSportType() == 512) {
                v();
                return;
            }
            w();
            PluginSportTrackAdapter c2 = gso.e().c();
            boolean c3 = gwa.c(this.ag.requestSportType());
            if (c2 != null && c2.isFitnessCourseDisplay() && c3) {
                this.ab = this.f.getBoolean("isAfterSport", false);
                return;
            }
            return;
        }
        LogUtil.b("Track_TrackDetailActivity", "intent is null!");
        finish();
    }

    private void w() {
        Bundle bundle = this.f;
        if (bundle == null) {
            LogUtil.b("Track_TrackDetailActivity", "mBundle is null!");
            finish();
            return;
        }
        String string = bundle.getString("contentpath");
        this.k = string;
        if (string == null) {
            LogUtil.c("Track_TrackDetailActivity", "gmConntentPath is null,get mMotionPath");
            this.ai = (MotionPath) this.f.getSerializable("motionPath");
        }
        if (this.ai == null) {
            String str = this.k;
            if (str == null || str.equals("contentpath") || this.k.length() == 0) {
                LogUtil.b("Track_TrackDetailActivity", "getData mContentPath is invalid!");
                finish();
                return;
            } else {
                if (!d(this.k) && TextUtils.isEmpty(this.b)) {
                    finish();
                    return;
                }
                this.ai = gwk.c(this, this.k, this.ag.requestSportType());
            }
        }
        c(this.ai);
    }

    private void v() {
        Bundle bundle = this.f;
        if (bundle == null) {
            LogUtil.b("Track_TrackDetailActivity", "mBundle is null!");
            finish();
            return;
        }
        Serializable serializable = bundle.getSerializable("subTrackDetail");
        if (!koq.e(serializable, gxy.class)) {
            LogUtil.b("Track_TrackDetailActivity", "subTrackDetail type is null!");
            finish();
            return;
        }
        this.at.clear();
        boolean z = false;
        for (gxy gxyVar : (List) serializable) {
            if (gxyVar != null) {
                MotionPath motionPath = null;
                if (gxyVar.b() != null) {
                    if (d(gxyVar.a())) {
                        motionPath = gwk.c(this, gxyVar.a(), gxyVar.b().requestSportType());
                    } else {
                        z = true;
                    }
                }
                this.at.add(new gya(gxyVar, motionPath));
                c(motionPath);
            }
        }
        if (z) {
            LogUtil.b("Track_TrackDetailActivity", "subTrackDetail data is abnormal(has simplfy and no track file)!");
            finish();
        }
    }

    private boolean d(String str) {
        if (FileUtils.getFile(getFilesDir().getPath() + File.separator + str).exists()) {
            return true;
        }
        LogUtil.b("Track_TrackDetailActivity", "getData mContentPath:", str, " is not exists!");
        return false;
    }

    private void c(MotionPath motionPath) {
        if (motionPath == null) {
            LogUtil.b("Track_TrackDetailActivity", "getData() mMotionPath is null");
        } else {
            b(motionPath);
        }
    }

    private void b(MotionPath motionPath) {
        if (motionPath.isValidRealtimePaceList()) {
            LogUtil.a("Track_TrackDetailActivity", "RealtimePaceList size = ", Integer.valueOf(motionPath.requestRealTimePaceList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "RealtimePaceList is null");
        }
        if (motionPath.isValidAltitudeList()) {
            LogUtil.a("Track_TrackDetailActivity", "motionPath AltitudeList", Integer.valueOf(motionPath.requestAltitudeList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "requestAltitudeList is null");
        }
        if (motionPath.isValidHeartRateList()) {
            LogUtil.a("Track_TrackDetailActivity", "HeartRateList size = ", Integer.valueOf(motionPath.requestHeartRateList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "HeartRateList is null");
        }
        if (motionPath.isValidStepRateList()) {
            LogUtil.a("Track_TrackDetailActivity", "StepRateList size = ", Integer.valueOf(motionPath.requestStepRateList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "StepRateList is null");
        }
        if (motionPath.isValidCadenceData()) {
            LogUtil.a("Track_TrackDetailActivity", "RidePostureDataList size = ", Integer.valueOf(motionPath.requestRidePostureDataList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "CadenceList is null");
        }
        if (motionPath.isValidPaddleData()) {
            LogUtil.a("Track_TrackDetailActivity", "PaddleDataList size = ", Integer.valueOf(motionPath.requestPaddleFrequencyList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "PaddleDataList is null");
        }
        if (motionPath.isValidPowerData()) {
            LogUtil.a("Track_TrackDetailActivity", "PowerDataList size = ", Integer.valueOf(motionPath.requestPowerList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "PowerDataList is null");
        }
        a(motionPath);
    }

    private void a(MotionPath motionPath) {
        if (motionPath.isValidInvalidHeartRateList()) {
            LogUtil.a("Track_TrackDetailActivity", "InvalidHRate size = ", Integer.valueOf(motionPath.requestInvalidHeartRateList().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "InvalidHRate is null");
        }
        if (motionPath.requestPaceMap() != null) {
            LogUtil.a("Track_TrackDetailActivity", "paceMap size = ", Integer.valueOf(motionPath.requestPaceMap().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "paceMap is null");
        }
        if (motionPath.requestBritishPaceMap() != null) {
            LogUtil.a("Track_TrackDetailActivity", "britishPaceMap size = ", Integer.valueOf(motionPath.requestBritishPaceMap().size()));
        } else {
            LogUtil.h("Track_TrackDetailActivity", "britishPaceMap is null");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected void initSport() {
        LogUtil.a("Track_TrackDetailActivity", "initSport");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected void initSport(boolean z) {
        LogUtil.a("Track_TrackDetailActivity", "initSport isAutoTrack ", Boolean.valueOf(z));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b(1.0f);
        this.j = System.currentTimeMillis();
        at();
    }

    private void at() {
        HealthSubTabWidget healthSubTabWidget = this.aw;
        if (healthSubTabWidget != null) {
            ViewGroup.LayoutParams layoutParams = healthSubTabWidget.getLayoutParams();
            layoutParams.width = nsn.n();
            this.aw.setLayoutParams(layoutParams);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Track_TrackDetailActivity", "onActivityResult resultCode ", Integer.valueOf(i2));
        if (intent == null) {
            LogUtil.h("Track_TrackDetailActivity", "onActivityResult data is null");
            return;
        }
        int intExtra = intent.getIntExtra(CommonConstant.KEY_GENDER, 0);
        int intExtra2 = intent.getIntExtra("birthday", 0);
        if (i2 == 100) {
            DetailFrag detailFrag = this.m;
            if (detailFrag != null && (intExtra != detailFrag.b() || intExtra2 != this.m.e())) {
                this.m.b(true);
                LogUtil.a("Track_TrackDetailActivity", "onActivityResult showVo2maxAbility ");
            } else {
                LogUtil.h("Track_TrackDetailActivity", "mDetailFrag = null");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        long[] jArr = this.g;
        int i = this.h;
        jArr[i] = jArr[i] + (System.currentTimeMillis() - this.j);
    }

    private void b(float f) {
        Configuration configuration = this.al.getConfiguration();
        configuration.fontScale = f;
        Resources resources = this.al;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        b(this.r);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_TrackDetailActivity", "onDestroy");
        gxp.b();
        HashMap hashMap = new HashMap(2);
        long j = this.g[0];
        if (j == -1) {
            hashMap.put("mapHide", 1);
        } else {
            hashMap.put("mapTime", Long.valueOf(j));
        }
        long j2 = this.g[1];
        if (j2 == -1) {
            hashMap.put("paceHide", 1);
        } else {
            hashMap.put("paceTime", Long.valueOf(j2));
        }
        long j3 = this.g[2];
        if (j3 == -1) {
            hashMap.put("chartHide", 1);
        } else {
            hashMap.put("chartTime", Long.valueOf(j3));
        }
        bk();
        s();
        aq();
        ap();
        hashMap.put("detailTime", Long.valueOf(this.g[3]));
        ixx.d().d(this, AnalyticsValue.MOTION_TRACK_1040026.value(), hashMap, 0);
        this.av = null;
    }

    private void ap() {
        this.x.removeCallbacksAndMessages(null);
        if (this.mPermissionHandler != null) {
            this.mPermissionHandler.removeCallbacksAndMessages(null);
        }
        this.as = null;
    }

    private void aq() {
        HealthSubTabWidget healthSubTabWidget = this.aw;
        if (healthSubTabWidget != null) {
            healthSubTabWidget.h();
        }
        List<Fragment> list = this.s;
        if (list != null) {
            list.clear();
        }
        List<gya> list2 = this.at;
        if (list2 != null) {
            list2.clear();
        }
        HealthViewPager healthViewPager = this.bc;
        if (healthViewPager != null) {
            healthViewPager.removeAllViews();
            this.bc = null;
        }
        this.n = null;
        this.aj = null;
        this.aw = null;
        this.an = null;
        this.f3668a = null;
        this.ax = null;
        this.ah = null;
        this.ap = null;
        this.am = null;
        this.y = null;
        this.m = null;
        this.ba = null;
        this.ar = null;
        this.t = null;
    }

    private void s() {
        if (this.ad) {
            return;
        }
        String str = this.k;
        if (str != null) {
            if (str.equals("motion_path2.txt")) {
                String b2 = SharedPreferenceManager.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), Integer.toString(20002), "save_DB_End");
                MotionPathSimplify motionPathSimplify = this.ag;
                String valueOf = motionPathSimplify != null ? String.valueOf(motionPathSimplify.requestStartTime()) : null;
                LogUtil.c("Track_TrackDetailActivity", valueOf, " ", b2);
                if (valueOf == null || !valueOf.equals(b2)) {
                    return;
                }
                gwo.a();
                LogUtil.c("Track_TrackDetailActivity", "deleteTempFileOfBreakPoint");
                return;
            }
            if (c(this.k)) {
                LogUtil.b("Track_TrackDetailActivity", "mContentPath is ", this.k);
                return;
            }
            gwo.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), this.k);
            gwo.e(this.o, "motion_path_process.txt");
            LogUtil.a("Track_TrackDetailActivity", "deleteFile MOTION_PATH_PROCESS_FILE_NAME");
            return;
        }
        hjw hjwVar = this.av;
        if (hjwVar == null) {
            LogUtil.b("Track_TrackDetailActivity", "mTrackDetailDataManager is null");
            return;
        }
        List<gya> o = hjwVar.o();
        if (koq.c(this.av.o())) {
            Iterator<gya> it = o.iterator();
            while (it.hasNext()) {
                String a2 = it.next().a();
                if (c(a2)) {
                    LogUtil.b("Track_TrackDetailActivity", "pathDetail is ", a2);
                } else {
                    gwo.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), a2);
                }
            }
        }
    }

    private boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_TrackDetailActivity", "pathName is ", str);
            return true;
        }
        return !sqd.e(new File(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getFilesDir(), str), "HiTrack_");
    }

    private void ab() {
        if (this.av == null) {
            LogUtil.h("Track_TrackDetailActivity", " initHideAndShowView() mTrackDetailDataManager is null, do not hide tab");
        } else {
            ah();
            t();
        }
    }

    private void ah() {
        if (a(b(true))) {
            this.aw.setVisibility(8);
        }
    }

    private boolean a(boolean z) {
        if (this.av.b(3)) {
            this.m = null;
            this.g[3] = -1;
        } else {
            this.m = new DetailFrag();
        }
        if (this.av.b(4)) {
            this.ar = null;
            this.g[4] = -1;
            return z;
        }
        this.ar = new TriathlonSubDataFrag();
        if (this.ax == null) {
            return true;
        }
        return z;
    }

    private boolean b(boolean z) {
        if (this.av.b(0)) {
            c(true);
            this.ax = null;
            this.g[0] = -1;
        } else {
            TrackScreenFrag trackScreenFrag = new TrackScreenFrag();
            this.ax = trackScreenFrag;
            trackScreenFrag.d(this.t);
            z = false;
        }
        if (this.av.b(1)) {
            this.ah = null;
            this.g[1] = -1;
        } else {
            if (this.ac) {
                this.ba = new TrackSwimSegmentFrag();
            } else {
                this.ah = new PaceFrag();
            }
            z = false;
        }
        if (this.av.b(2)) {
            this.y = null;
            this.g[2] = -1;
        } else {
            this.y = new HeartRateFrag();
            z = false;
        }
        if (this.av.b(5)) {
            this.ap = null;
            this.g[5] = -1;
        } else {
            this.ap = new SegmentFrag();
            z = false;
        }
        if (this.av.b(6)) {
            this.am = null;
            this.g[6] = -1;
        } else {
            this.am = new SkippingPerformanceFrag();
            if (this.m == null) {
                return false;
            }
        }
        return z;
    }

    private void t() {
        hjw hjwVar = this.av;
        if (hjwVar == null || hjwVar.f() == 0) {
            this.an.setCurrentItem(0);
        } else {
            this.an.setCurrentItem(this.f3668a.getCount() - 1);
        }
    }

    private void bf() {
        Intent intent = new Intent();
        intent.setPackage(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
        intent.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage(), "com.huawei.health.receiver.MainProcessHelperService");
        intent.setAction("start_main_process_for_pluginsuggestion");
        Context context = com.huawei.hwcommonmodel.application.BaseApplication.getContext();
        LogUtil.a("Track_TrackDetailActivity", "startMainProcess ", context);
        context.startForegroundService(intent);
    }

    private boolean aj() {
        Bundle bundle = this.f;
        if (bundle != null) {
            return bundle.getBoolean("isAfterSport", false) && ar();
        }
        LogUtil.h("Track_TrackDetailActivity", "isStartSportResultActivity mBundle is null");
        return false;
    }

    private boolean ar() {
        if (this.av.e() == null) {
            LogUtil.h("Track_TrackDetailActivity", "isValidSportType mTrackDetailDataManager.acquireMotionPathSimplify() is null.");
            return false;
        }
        int requestSportType = this.av.e().requestSportType();
        return requestSportType == 258 || requestSportType == 264 || requestSportType == 257 || requestSportType == 259 || requestSportType == 283;
    }

    private void be() {
        String appPackage = com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage();
        Intent intent = new Intent();
        intent.setPackage(appPackage);
        intent.setClassName(appPackage, "com.huawei.health.suggestion.ui.run.activity.SportResultActivity");
        intent.putExtra("runningCourseId", this.av.e().requestRunCourseId());
        intent.putExtra(BleConstants.SPORT_TYPE, this.av.e().requestSportType());
        intent.putExtra("avgHeartRate", this.av.e().requestAvgHeartRate());
        intent.putExtra("avgPace", this.av.e().requestAvgPace());
        intent.putExtra("distances", this.av.e().requestTotalDistance());
        intent.putExtra("calories", this.av.e().requestTotalCalories());
        intent.putExtra(ParsedFieldTag.NPES_SPORT_TIME, this.av.e().requestTotalTime());
        intent.putExtra("endTime", this.av.e().requestEndTime());
        intent.putExtra("startTime", this.av.e().requestStartTime());
        intent.putExtra("calories", this.av.e().requestTotalCalories());
        bcM_(this.av.e(), intent);
        LogUtil.c("Track_TrackDetailActivity", "startSportResultActivity ExitApp ", Boolean.valueOf(this.v));
        if (this.v) {
            intent.setFlags(1409318912);
        } else {
            intent.setFlags(268435456);
        }
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_TrackDetailActivity", "startSportResultActivity ActivityNotFoundException.");
        }
    }

    private void bcM_(MotionPathSimplify motionPathSimplify, Intent intent) {
        String extendDataString = motionPathSimplify.getExtendDataString("planInfo");
        if (TextUtils.isEmpty(extendDataString) || intent == null) {
            ReleaseLogUtil.d("Track_TrackDetailActivity", "planInfo is null or intent is null");
            return;
        }
        RecordPlanInfo recordPlanInfo = (RecordPlanInfo) nrv.b(extendDataString, RecordPlanInfo.class);
        if (recordPlanInfo != null) {
            intent.putExtra("plan_execute_time", recordPlanInfo.getPlanTrainDate());
        }
    }

    private void bi() {
        TrackScreenFrag trackScreenFrag;
        b(getString(R.string._2130839497_res_0x7f0207c9));
        hjw hjwVar = this.av;
        if (hjwVar == null) {
            LogUtil.h("Track_TrackDetailActivity", "mTrackDetailDataManager is null");
            b();
        } else if (hjwVar.b(0) || (trackScreenFrag = this.ax) == null) {
            LogUtil.a("Track_TrackDetailActivity", "trackFrag is hided or null");
            this.x.sendEmptyMessageDelayed(1, 200L);
        } else {
            trackScreenFrag.bgn_(this.x);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        TrackScreenFrag trackScreenFrag;
        gxp a2 = gxp.a();
        a2.e(this.av);
        if (this.aq != null && (trackScreenFrag = this.ax) != null) {
            a2.b(trackScreenFrag.b());
            a2.aVI_(this.aq);
        } else {
            a2.b(false);
            a2.aVI_(null);
        }
        TrackScreenFrag trackScreenFrag2 = this.ax;
        if (trackScreenFrag2 == null) {
            a2.d(null);
        } else {
            List<hjd> i = trackScreenFrag2.i();
            if (koq.b(i)) {
                a2.d(null);
            } else {
                a2.d(i);
            }
        }
        ad();
    }

    private void ad() {
        this.s = o();
        this.bc.setOffscreenPageLimit(5);
        this.bc.setAdapter(new HealthFragmentStatePagerAdapter(getSupportFragmentManager()) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity.1
            @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter, com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
            public Parcelable saveState() {
                return null;
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
            public Fragment getItem(int i) {
                if (!koq.b(TrackDetailActivity.this.s, i)) {
                    return (Fragment) TrackDetailActivity.this.s.get(i);
                }
                LogUtil.a("Track_TrackDetailActivity", "getItem ", "The position is out of bounds");
                return null;
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
            public int getCount() {
                return TrackDetailActivity.this.s.size();
            }
        });
    }

    private List<Fragment> o() {
        ArrayList arrayList = new ArrayList(16);
        e(arrayList);
        return arrayList;
    }

    private void e(List<Fragment> list) {
        this.ao.clear();
        if (gxp.a().e()) {
            list.add(new SportShortTrackShareFragment());
            this.ay = 1;
            if (this.av.ax()) {
                this.ao.add(this.al.getString(R.string._2130847255_res_0x7f022617));
            } else {
                this.ao.add(this.al.getString(R.string._2130839867_res_0x7f02093b));
            }
        }
        TrackShareAllDataFragment trackShareAllDataFragment = new TrackShareAllDataFragment();
        this.au = trackShareAllDataFragment;
        trackShareAllDataFragment.d(this.as);
        list.add(trackShareAllDataFragment);
        this.ao.add(getString(R.string._2130839868_res_0x7f02093c));
        list.add(new SportShareNewDetailFragment());
        this.ao.add(getString(R.string._2130841788_res_0x7f0210bc));
    }

    private Bitmap bcF_(Fragment fragment) {
        View view;
        if (fragment instanceof TrackShareAllDataFragment) {
            view = ((TrackShareAllDataFragment) fragment).bgv_();
        } else if (fragment instanceof SportShareNewDetailFragment) {
            view = ((SportShareNewDetailFragment) fragment).bfx_();
        } else {
            view = fragment.getView();
        }
        Bitmap bGg_ = jdv.bGg_(view);
        if (bGg_ == null) {
            LogUtil.a("Track_TrackDetailActivity", "screenShotBitmap is null");
        }
        return bGg_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ba() {
        fdu fduVar = new fdu(6);
        List<String> aw = aw();
        LogUtil.a("Track_TrackDetailActivity", "shareImage shareBitmapList size", Integer.valueOf(aw.size()));
        int size = aw.size();
        int[] iArr = e;
        if (size != iArr.length) {
            iArr = d;
        }
        b(fduVar, aw, iArr);
        fduVar.d(true);
        fduVar.d(this.ay);
        fdu e2 = e(fduVar);
        if (CommonUtil.bu()) {
            LogUtil.a("Track_TrackDetailActivity", "doShare shareToLocal storeDemoRun");
            e2.c(new SaveBitampCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity.5
                @Override // com.huawei.health.socialshare.model.SaveBitampCallback
                public void onSuccess(Uri uri) {
                    String uri2 = uri.toString();
                    LogUtil.a("Track_TrackDetailActivity", "storeDemoRun uri = ", uri2);
                    SharedPreferenceManager.e(TrackDetailActivity.this.o, JsInteractAddition.BI_ERROR_CODE_INVALID_AT, "SP_REPORT_RUN", uri2, (StorageParams) null);
                }

                @Override // com.huawei.health.socialshare.model.SaveBitampCallback
                public void onFail(int i) {
                    LogUtil.h("Track_TrackDetailActivity", "doShare shareToLocal err = ", Integer.valueOf(i));
                }
            });
        }
        e2.b(40002);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(e2, this);
    }

    private void b(fdu fduVar, List<String> list, int[] iArr) {
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                fduVar.b(c(list.get(i), iArr[i], i));
            } else {
                fdz fdzVar = new fdz();
                fdzVar.c(false);
                fdzVar.d(list.get(i));
                fdzVar.b(b(iArr[i]));
                if (this.av.e() != null) {
                    fdzVar.b(this.av.e().requestSportType());
                }
                fdzVar.a(this.ao.get(i));
                fduVar.b(fdzVar);
            }
        }
    }

    private fdz c(String str, int i, int i2) {
        List<Fragment> list = this.s;
        Fragment fragment = list.get(list.size() - 1);
        fdz fdzVar = new fdz();
        if (fragment instanceof SportShareNewDetailFragment) {
            SportShareNewDetailFragment sportShareNewDetailFragment = (SportShareNewDetailFragment) fragment;
            ArrayList<Integer> d2 = sportShareNewDetailFragment.d();
            feh b2 = sportShareNewDetailFragment.b();
            fdzVar.d(d2);
            fdzVar.b(b2);
            fdzVar.b(sportShareNewDetailFragment.a());
        }
        fdzVar.c(true);
        fdzVar.d(str);
        if (this.av.e() != null) {
            fdzVar.b(this.av.e().requestSportType());
        }
        fdzVar.b(b(i));
        if (koq.d(this.ao, i2)) {
            fdzVar.a(this.ao.get(i2));
        }
        return fdzVar;
    }

    private List<String> aw() {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < this.s.size(); i++) {
            Bitmap bcF_ = bcF_(this.s.get(i));
            if (bcF_ != null) {
                arrayList.add(bcL_(bcF_, i));
                bcF_.recycle();
            }
        }
        return arrayList;
    }

    private String bcL_(Bitmap bitmap, int i) {
        String str;
        File file = new File(jcu.f);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.h("Track_TrackDetailActivity", "saveBitmapToFile:mkdirs error");
        }
        File file2 = new File(file, i + ".jpg");
        try {
            str = file2.getCanonicalPath();
        } catch (IOException e2) {
            LogUtil.h("Track_TrackDetailActivity", "getCanonicalPath", e2.getMessage());
            str = null;
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(file2);
            if (str != null) {
                try {
                    if (!sqc.a(file2, str)) {
                        LogUtil.h("Track_TrackDetailActivity", "invalidate file path");
                        if (file2.exists()) {
                            file2.deleteOnExit();
                        }
                        if (openOutputStream != null) {
                            openOutputStream.close();
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    if (openOutputStream != null) {
                        try {
                            openOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, openOutputStream);
            file2.deleteOnExit();
            openOutputStream.flush();
            if (openOutputStream != null) {
                openOutputStream.close();
            }
        } catch (IOException e3) {
            LogUtil.h("Track_TrackDetailActivity", "saveBitmapToFile:IOException ", e3.getMessage());
        } catch (IllegalArgumentException e4) {
            LogUtil.h("Track_TrackDetailActivity", "saveBitmapToFile:IllegalArgumentException ", e4.getMessage());
        }
        return str;
    }

    private fdu e(fdu fduVar) {
        int requestSportType;
        fduVar.c((String) null);
        fduVar.a((String) null);
        fduVar.f(null);
        fduVar.b(AnalyticsValue.HEALTH_SHARE_TRACK_SHARE_2100005.value());
        fduVar.e(1);
        fduVar.i(false);
        fduVar.b(1);
        hjw hjwVar = this.av;
        if (hjwVar != null && ((requestSportType = hjwVar.e().requestSportType()) == 264 || requestSportType == 258 || requestSportType == 280)) {
            fduVar.b(6);
        }
        return fduVar;
    }

    private Map b(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.av.e().requestSportType()));
        hashMap.put("deviceType", Integer.valueOf(this.av.e().requestDeviceType()));
        hashMap.put("prodId", this.av.e().requestProductId());
        hashMap.put("trackShareIndex", Integer.valueOf(i));
        return hashMap;
    }

    public void b(String str) {
        if (this.bb != null && !isDestroyed() && !isFinishing()) {
            this.bb.dismiss();
            this.bb = null;
        }
        new CommonDialog21(this.o, R.style.app_update_dialogActivity);
        CommonDialog21 a2 = CommonDialog21.a(this.o);
        this.bb = a2;
        a2.e(str);
        this.bb.setCancelable(false);
        this.bb.a();
    }

    public void b() {
        if (this.bb != null) {
            if (!isDestroyed() && !isFinishing() && this.bb.isShowing()) {
                this.bb.dismiss();
            }
            this.bb = null;
        }
    }

    public hjw e() {
        return this.av;
    }

    private void ay() {
        if (this.o == null) {
            LogUtil.a("Track_TrackDetailActivity", "showAbnormalTrackDialog mContext is null");
        } else {
            LogUtil.a("Track_TrackDetailActivity", "showAutomaticIdentificationTrackDialog");
            new CustomTextAlertDialog.Builder(this.o).b(R.string.IDS_service_area_notice_title).e(this.c.getString(R.string._2130843460_res_0x7f021744)).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: hfw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void az() {
        String string;
        if (this.o == null || this.ag == null) {
            LogUtil.a("Track_TrackDetailActivity", "showAbnormalTrackDialog mContext is null");
            return;
        }
        LogUtil.a("Track_TrackDetailActivity", "showAbnormalTrackDialog");
        if (this.ag.requestSportType() == 283) {
            string = this.c.getString(R.string._2130839997_res_0x7f0209bd);
        } else if (this.ag.requestAbnormalTrack() == 7) {
            string = this.c.getString(R.string._2130840045_res_0x7f0209ed, cwa.d(554, this.o, this.c.getPackageName(), this.ag.requestProductId()));
        } else {
            string = this.c.getString(R.string._2130840054_res_0x7f0209f6);
        }
        new CustomTextAlertDialog.Builder(this.o).b(R.string.IDS_service_area_notice_title).e(string).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: hfo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    private void bb() {
        if (this.o == null) {
            LogUtil.a("Track_TrackDetailActivity", "showDuplicatedDialog mContext is null");
            return;
        }
        LogUtil.a("Track_TrackDetailActivity", "showDuplicatedDialog");
        String string = this.c.getString(R.string._2130842963_res_0x7f021553);
        if (!Utils.i()) {
            string = this.c.getString(R.string._2130842837_res_0x7f0214d5);
        }
        new CustomTextAlertDialog.Builder(this.o).b(R.string.IDS_service_area_notice_title).e(string).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: hfs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    private void c(boolean z) {
        this.n.setRightButtonVisibility(z ? 0 : 8);
    }

    public View bcQ_() {
        return this.n.getRightIconImage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (z) {
            this.n.setRightSoftkeyVisibility(0);
        } else {
            this.n.setRightSoftkeyVisibility(8);
        }
    }

    public void n() {
        Context context = this.o;
        if (context == null) {
            LogUtil.a("Track_TrackDetailActivity", "showAreaAlertDialog mContext is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string._2130843247_res_0x7f02166f).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: hfp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* loaded from: classes4.dex */
    class d implements HealthViewPager.OnPageChangeListener {
        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        private d() {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            Fragment item = TrackDetailActivity.this.f3668a.getItem(i);
            if (item instanceof HeartRateFrag) {
                TrackDetailActivity trackDetailActivity = TrackDetailActivity.this;
                trackDetailActivity.e(((Integer) trackDetailActivity.p.get("heartRateFrag")).intValue());
                return;
            }
            if (item instanceof PaceFrag) {
                TrackDetailActivity trackDetailActivity2 = TrackDetailActivity.this;
                trackDetailActivity2.e(((Integer) trackDetailActivity2.p.get("paceFrag")).intValue());
                return;
            }
            if (item instanceof TrackScreenFrag) {
                if (TrackDetailActivity.this.ab) {
                    TrackDetailActivity.this.d(true);
                }
                TrackDetailActivity trackDetailActivity3 = TrackDetailActivity.this;
                trackDetailActivity3.e(((Integer) trackDetailActivity3.p.get("trackScreen")).intValue());
                return;
            }
            if (item instanceof DetailFrag) {
                if (TrackDetailActivity.this.ab && TrackDetailActivity.this.av != null && TrackDetailActivity.this.av.b(0)) {
                    TrackDetailActivity.this.d(true);
                }
                TrackDetailActivity trackDetailActivity4 = TrackDetailActivity.this;
                trackDetailActivity4.e(((Integer) trackDetailActivity4.p.get("detailFrag")).intValue());
                return;
            }
            if (item instanceof TriathlonSubDataFrag) {
                TrackDetailActivity.this.d(false);
                TrackDetailActivity trackDetailActivity5 = TrackDetailActivity.this;
                trackDetailActivity5.e(((Integer) trackDetailActivity5.p.get("subSport")).intValue());
                return;
            }
            LogUtil.b("Track_TrackDetailActivity", "page select error");
        }
    }

    private void ag() {
        if (!a()) {
            LogUtil.h("Track_TrackDetailActivity", "The account language is not supported.");
            return;
        }
        SharedPreferences sharedPreferences = this.o.getSharedPreferences(Integer.toString(20002), 0);
        this.az = sharedPreferences;
        if (sharedPreferences == null) {
            LogUtil.h("Track_TrackDetailActivity", "Show bubble sharedPreferences is null.");
            return;
        }
        boolean z = sharedPreferences.getBoolean("is_first_time_guide_user_share", true);
        ViewGroup.LayoutParams layoutParams = this.w.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginEnd(nsn.c(this.c, 6.0f) + ((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
            this.w.setLayoutParams(layoutParams2);
        }
        if (z) {
            this.w.setVisibility(0);
        } else {
            this.w.setVisibility(8);
        }
    }

    private void z() {
        if (!a()) {
            LogUtil.h("Track_TrackDetailActivity", "The account language is not supported.");
            return;
        }
        this.w.setVisibility(8);
        if (this.az == null) {
            LogUtil.h("Track_TrackDetailActivity", "Hide bubble sharedPreferences is null.");
            this.az = this.o.getSharedPreferences(Integer.toString(20002), 0);
        }
        SharedPreferences.Editor edit = this.az.edit();
        if (edit == null) {
            LogUtil.h("Track_TrackDetailActivity", "Hide bubble editor is null.");
        } else {
            edit.putBoolean("is_first_time_guide_user_share", false);
            edit.commit();
        }
    }

    public static boolean a() {
        return LanguageUtil.m(com.huawei.hwcommonmodel.application.BaseApplication.getContext()) && !Utils.o();
    }

    private void d(double d2, double d3) {
        if (this.ag == null) {
            LogUtil.h("Track_TrackDetailActivity", "printLatLonInvalidInfo: simplify is null");
        } else if (!gwe.d(d2) && !gwe.a(d3)) {
            LogUtil.a("Track_TrackDetailActivity", "printLatLonInvalidInfo: lat and lon is valid");
        } else {
            ReleaseLogUtil.e("sportType = ", Integer.valueOf(this.ag.requestSportType()), " trackType = ", Integer.valueOf(this.ag.requestTrackType()), " start = ", Long.valueOf(this.ag.requestStartTime()), " end = ", Long.valueOf(this.ag.requestEndTime()), " distance = ", Integer.valueOf(this.ag.requestTotalDistance()), " calories = ", Integer.valueOf(this.ag.requestTotalCalories()));
        }
    }

    /* loaded from: classes4.dex */
    static class c extends BaseHandler<TrackDetailActivity> {
        public c(TrackDetailActivity trackDetailActivity) {
            super(trackDetailActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bcR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TrackDetailActivity trackDetailActivity, Message message) {
            LogUtil.a("Track_TrackDetailActivity", "InternalHandler handleMessageWhenReferenceNotNull message.what ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof Bitmap) {
                    trackDetailActivity.aq = (Bitmap) message.obj;
                }
                trackDetailActivity.bh();
            } else if (i == 2) {
                trackDetailActivity.ba();
                trackDetailActivity.b();
                trackDetailActivity.au.d((ShareInitCallback) null);
            } else if (i == 12) {
                trackDetailActivity.n();
            } else {
                if (i != 13) {
                    return;
                }
                trackDetailActivity.az();
            }
        }
    }
}
