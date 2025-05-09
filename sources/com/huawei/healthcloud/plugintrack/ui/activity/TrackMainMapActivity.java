package com.huawei.healthcloud.plugintrack.ui.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.LoadHotTrackCallBack;
import com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import com.huawei.healthcloud.plugintrack.open.TrackService;
import com.huawei.healthcloud.plugintrack.service.KeepForegroundNoLocationService;
import com.huawei.healthcloud.plugintrack.service.KeepForegroundService;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog;
import com.huawei.healthcloud.plugintrack.ui.viewmodel.TrackMainViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.car;
import defpackage.enc;
import defpackage.ffd;
import defpackage.gnb;
import defpackage.gso;
import defpackage.gsx;
import defpackage.gtx;
import defpackage.guj;
import defpackage.guk;
import defpackage.guq;
import defpackage.gut;
import defpackage.gvv;
import defpackage.gwa;
import defpackage.gwf;
import defpackage.gwg;
import defpackage.gwo;
import defpackage.gww;
import defpackage.gxd;
import defpackage.gxi;
import defpackage.gxv;
import defpackage.haa;
import defpackage.hab;
import defpackage.hac;
import defpackage.hgg;
import defpackage.hiy;
import defpackage.hnu;
import defpackage.hoh;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class TrackMainMapActivity extends TrackBaseActivity implements IMapViewListener, SportLifecycle {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicBoolean f3672a = new AtomicBoolean(false);
    private static int c = 0;
    private static boolean d = false;
    private SystemLocaleChangeReceiver ad;
    private TrackMainViewModel ae;
    private gww ai;
    private WeakReference<Context> b;
    private a p;
    private CustomViewDialog s;
    private SportBeat v;
    private gtx w = null;
    private hnu ag = null;
    private Bundle k = null;
    private Handler j = new b();
    private Handler n = new Handler(Looper.getMainLooper());
    private int u = 0;
    private int y = -1;
    private float ac = -1.0f;
    private int l = -1;
    private int af = -1;
    private int h = 0;
    private int x = 0;
    private int e = -1;
    private ScreenListener t = null;
    private c r = null;
    private boolean o = false;
    private boolean g = false;
    private long ab = 0;
    private NoTitleCustomAlertDialog m = null;
    private CustomTextAlertDialog z = null;
    private guq ah = null;
    private boolean i = true;
    private CustomTextAlertDialog f = null;
    private final ITargetUpdateListener aa = new ITargetUpdateListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.5
        @Override // com.huawei.health.sport.ITargetUpdateListener
        public void onTargetDataUpdate(ffd ffdVar) {
        }

        @Override // com.huawei.health.sport.ITargetUpdateListener
        public void onStateUpdate(int i, String str) {
            if (i != 200) {
                return;
            }
            if (!SportSupportUtil.c(TrackMainMapActivity.this.u) || TrackMainMapActivity.this.af != 1) {
                TrackMainMapActivity.this.n();
            } else {
                TrackMainMapActivity.this.r();
            }
        }
    };
    private File q = null;

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected boolean isSport() {
        return true;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        hnu hnuVar = this.ag;
        if (hnuVar != null && !d) {
            hnuVar.c();
        }
        d = false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        nsn.cLf_(this, bundle);
        gso.e().t();
        c++;
        ad();
        as();
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        z();
        LogUtil.a("Track_TrackMainMapActivity", "onCreate");
        ar();
        if (bundle != null) {
            finish();
            return;
        }
        this.b = new WeakReference<>(this);
        this.w = hgg.c();
        BaseActivity.cancelLayoutById((RelativeLayout) findViewById(R.id.layout_StopOrResume));
        gwg.b(getApplicationContext());
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setVolumeControlStream(3);
        au();
        if (!this.w.aq()) {
            this.ah = new guq();
        }
        aj();
    }

    private static boolean af() {
        return Build.VERSION.SDK_INT >= 34;
    }

    public boolean a() {
        int i = this.u;
        return i == 264 || i == 265;
    }

    private void ar() {
        if (a() && CommonUtil.bh()) {
            if (CommonUtil.ba()) {
                getWindow().getDecorView().setSystemUiVisibility(9216);
                setStatusBarColor();
            }
            getWindow().setNavigationBarColor(-16777216);
        }
    }

    private void z() {
        if (e()) {
            Object[] objArr = new Object[6];
            objArr[0] = "initViewModel enter ";
            objArr[1] = Boolean.valueOf(this.ae == null);
            objArr[2] = " sportType ";
            objArr[3] = Integer.valueOf(this.u);
            objArr[4] = " sportMode ";
            objArr[5] = Integer.valueOf(this.x);
            ReleaseLogUtil.e("Track_TrackMainMapActivity", objArr);
            TrackMainViewModel trackMainViewModel = (TrackMainViewModel) new ViewModelProvider(this).get(TrackMainViewModel.class);
            this.ae = trackMainViewModel;
            if (trackMainViewModel != null) {
                trackMainViewModel.init(this.k);
                this.ae.observeSportLifeCycle("Track_TrackMainMapActivity", this);
                this.ae.onPreSport("DEVICE");
                this.ae.requestData();
                int i = this.h;
                if (i == 1) {
                    this.ae.onStartSport("DEVICE");
                } else if (i == 2) {
                    this.ae.onStartSport("DEVICE");
                    this.ae.onPauseSport("DEVICE");
                }
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        ReleaseLogUtil.e("Track_TrackMainMapActivity", "onResumeSport()");
        HandlerExecutor.a(new Runnable() { // from class: hgd
            @Override // java.lang.Runnable
            public final void run() {
                TrackMainMapActivity.this.h();
            }
        });
    }

    public /* synthetic */ void h() {
        hnu hnuVar = this.ag;
        if (hnuVar == null) {
            ReleaseLogUtil.c("Track_TrackMainMapActivity", "onResumeSport() failed mTrackMainViewHolder is null.");
        } else {
            hnuVar.d();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        ReleaseLogUtil.e("Track_TrackMainMapActivity", "onPauseSport()");
        HandlerExecutor.a(new Runnable() { // from class: hge
            @Override // java.lang.Runnable
            public final void run() {
                TrackMainMapActivity.this.i();
            }
        });
    }

    public /* synthetic */ void i() {
        hnu hnuVar = this.ag;
        if (hnuVar == null) {
            ReleaseLogUtil.c("Track_TrackMainMapActivity", "onPauseSport() failed mTrackMainViewHolder is null.");
            return;
        }
        hnuVar.b();
        this.ag.j();
        this.ag.x();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        ReleaseLogUtil.e("Track_TrackMainMapActivity", "onStopSport()");
        if (e()) {
            hnu hnuVar = this.ag;
            if (hnuVar != null && hnuVar.f() != null) {
                this.ag.f().setIsStop(true);
            }
            finish();
        }
    }

    public boolean e() {
        return this.x == 100;
    }

    private void ad() {
        if (getIntent() == null || getIntent().getBundleExtra("sportdataparams") == null) {
            this.u = 0;
            this.y = -1;
            this.ac = -1.0f;
            this.l = -1;
            return;
        }
        Bundle bundleExtra = getIntent().getBundleExtra("sportdataparams");
        this.k = bundleExtra;
        if (bundleExtra != null) {
            this.u = bundleExtra.getInt("map_tracking_sport_type_sportting", 0);
            this.y = this.k.getInt("sport_target_type_sportting", -1);
            this.ac = this.k.getFloat("sport_target_value_sportting", -1.0f);
            this.l = this.k.getInt("origintarget", -1);
            this.af = this.k.getInt("trackFrom", -1);
            this.e = this.k.getInt("biTrackFrom", -1);
            this.h = this.k.getInt("initStatus", 0);
            this.x = this.k.getInt("sport_data_source_sportting", 0);
        }
    }

    private void a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.healthcloud.plugintrack.trackSdk.TrackService");
        intent.setClass(this, TrackService.class);
        intent.setPackage(context.getPackageName());
        try {
            startService(intent);
        } catch (IllegalStateException e2) {
            ReleaseLogUtil.c("Track_TrackMainMapActivity", "startTrackService", LogAnonymous.b((Throwable) e2));
        }
    }

    private void j(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.healthcloud.plugintrack.trackSdk.TrackService");
        intent.setClass(this, TrackService.class);
        intent.setPackage(context.getPackageName());
        stopService(intent);
        b(context);
    }

    public static void b(Context context) {
        if (context == null) {
            LogUtil.b("Track_TrackMainMapActivity", "context is null");
            return;
        }
        if (CommonUtil.bk() && f3672a.get()) {
            try {
                LogUtil.a("Track_TrackMainMapActivity", "stopServiceForeground");
                Intent intent = new Intent();
                bcY_(context, intent);
                intent.putExtra("id", "Track_TrackMainMapActivity");
                intent.putExtra("isStop", true);
                intent.putExtra(BleConstants.SPORT_TYPE, gtx.c(BaseApplication.getContext()).n());
                intent.setPackage(context.getPackageName());
                intent.putExtra("stringKey", R.string._2130843715_res_0x7f021843);
                intent.putExtra("NOTIFICATION_TYPE", 1);
                context.startForegroundService(intent);
                f3672a.set(false);
            } catch (Throwable th) {
                ReleaseLogUtil.e("Track_TrackMainMapActivity", "Remote Service throwable is ", ExceptionUtils.d(th));
            }
        }
    }

    private static void bcY_(Context context, Intent intent) {
        if (PermissionUtil.e(context, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})) {
            intent.setClass(context, KeepForegroundService.class);
        } else {
            intent.setClass(context, KeepForegroundNoLocationService.class);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007a A[Catch: Exception -> 0x00a5, Exception | NoSuchMethodError -> 0x00a7, TryCatch #2 {Exception | NoSuchMethodError -> 0x00a7, blocks: (B:5:0x0025, B:7:0x002d, B:10:0x0038, B:12:0x003e, B:14:0x0049, B:16:0x004f, B:18:0x0061, B:22:0x0071, B:24:0x007a, B:27:0x006b, B:28:0x0089, B:31:0x0090, B:33:0x009f), top: B:4:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void setActvityLayoutModel() {
        /*
            r5 = this;
            int r0 = r5.u
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "setActvityLayoutModel type is "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "Track_TrackMainMapActivity"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            boolean r0 = r5.a()
            if (r0 == 0) goto L1f
            r0 = 2131167832(0x7f070a58, float:1.7949949E38)
            r5.setContentView(r0)
            goto L25
        L1f:
            r0 = 2131167747(0x7f070a03, float:1.7949776E38)
            r5.setContentView(r0)
        L25:
            android.view.Window r0 = r5.getWindow()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L38
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            java.lang.String r2 = "setActvityLayoutModel window is null"
            r0[r3] = r2     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            return
        L38:
            android.view.View r4 = r0.getDecorView()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r4 != 0) goto L49
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            java.lang.String r2 = "setActvityLayoutModel decorView is null"
            r0[r3] = r2     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            return
        L49:
            boolean r2 = health.compact.a.CommonUtil.bh()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r2 == 0) goto L89
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r0.addFlags(r2)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            r0.setStatusBarColor(r3)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            boolean r2 = defpackage.nrt.a(r2)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r2 != 0) goto L6b
            boolean r2 = r5.a()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r2 == 0) goto L68
            goto L6b
        L68:
            r2 = 9216(0x2400, float:1.2914E-41)
            goto L71
        L6b:
            int r2 = r4.getSystemUiVisibility()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            r2 = r2 & (-8193(0xffffffffffffdfff, float:NaN))
        L71:
            r4.setSystemUiVisibility(r2)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            boolean r2 = r5.a()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r2 == 0) goto L88
            android.content.res.Resources r2 = r5.getResources()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            r3 = 2131299266(0x7f090bc2, float:1.8216529E38)
            int r2 = r2.getColor(r3)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            r0.setNavigationBarColor(r2)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
        L88:
            return
        L89:
            boolean r0 = r5.a()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r0 != 0) goto L90
            return
        L90:
            int r0 = r4.getSystemUiVisibility()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            r0 = r0 & (-8193(0xffffffffffffdfff, float:NaN))
            r4.setSystemUiVisibility(r0)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            boolean r0 = d()     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            if (r0 == 0) goto Lb6
            r0 = 1280(0x500, float:1.794E-42)
            r4.setSystemUiVisibility(r0)     // Catch: java.lang.Exception -> La5 java.lang.NoSuchMethodError -> La7
            goto Lb6
        La5:
            r0 = move-exception
            goto La8
        La7:
            r0 = move-exception
        La8:
            java.lang.String r2 = "setActvityLayoutModel exception "
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.setActvityLayoutModel():void");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected void init() {
        hoh viewCell;
        super.init();
        if (this.mViewHolderInterface == null || (viewCell = this.mViewHolderInterface.getViewCell()) == null) {
            return;
        }
        this.ag = (hnu) viewCell.b();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected ViewHolderInterface initViewHolder() {
        return new hnu(getWindow().getDecorView().getRootView(), this, this.mTrackBaseActivity, this, this.k);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected void initSport() {
        LogUtil.a("Track_TrackMainMapActivity", "initSport");
        if (af()) {
            e((Context) this);
        }
        if (e()) {
            v();
            return;
        }
        if (!ac()) {
            ReleaseLogUtil.e("Track_TrackMainMapActivity", "initSportInner is false");
            return;
        }
        if (!UnitUtil.h()) {
            gxd.a().h("Track_TrackMainMapActivity");
        }
        this.w.bt();
        s();
        this.w.b("Track_TrackMainMapActivityTargetUpdate", this.aa);
        ap();
        w();
    }

    private void v() {
        if (e()) {
            LogUtil.a("Track_TrackMainMapActivity", " initMirrorSport()");
            if (!a()) {
                bcV_(this.ag, this.j);
            }
            this.ae.observeSportingData("allData", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity$$ExternalSyntheticLambda3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TrackMainMapActivity.this.e(obj);
                }
            });
            this.ae.observeSportingData("deviceDisconnectTime", this, new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity$$ExternalSyntheticLambda4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TrackMainMapActivity.this.a(obj);
                }
            });
            this.ae.observeForeverSportingData("allData", new Observer() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity$$ExternalSyntheticLambda5
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    TrackMainMapActivity.this.b(obj);
                }
            });
        }
    }

    /* synthetic */ void e(Object obj) {
        if (obj instanceof Bundle) {
            this.ag.updateSportViewFragment((Bundle) obj);
        }
    }

    /* synthetic */ void a(Object obj) {
        if (obj instanceof Integer) {
            this.ag.b(((Integer) obj).intValue());
        }
    }

    /* synthetic */ void b(Object obj) {
        if (isFinishing() || isDestroyed() || !(obj instanceof gxv)) {
            return;
        }
        gxv gxvVar = (gxv) obj;
        hiy[] b2 = gxvVar.b();
        if (b2 != null && b2.length == 2) {
            this.ag.c(gwf.e(b2[0]), gwf.e(b2[1]));
        }
        this.ag.b(gxvVar.a());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected void initSport(boolean z) {
        if (e()) {
            v();
            return;
        }
        boolean ac = ac();
        this.w.i(true);
        MotionData a2 = this.w.a(true);
        this.w.aa().b();
        if (a2 == null) {
            LogUtil.h("Track_TrackMainMapActivity", "AutoTrack motionData is null!");
            gwo.a();
        } else {
            this.ag.c(a2);
        }
        this.w.i(false);
        ReleaseLogUtil.e("Track_TrackMainMapActivity", " result ", Boolean.valueOf(ac));
    }

    private boolean ac() {
        gtx c2 = gtx.c(getApplicationContext());
        this.w = c2;
        if (c2.ap()) {
            LogUtil.b("Track_TrackMainMapActivity", "initSport() mPluginTrackAdapter is null can't start sport!");
            finish();
            return false;
        }
        this.w.as();
        if (this.ag == null) {
            return false;
        }
        if (!a()) {
            bcV_(this.ag, this.j);
            this.w.b(this.ag.l());
        } else {
            am();
        }
        this.w.d(this);
        a((Context) this);
        b("com.huawei.healthcloud.plugintrack.NOTIFY_SPORT_DATA", "com.android.keyguard.permission.SEND_STEP_INFO_COUNTER", "com.android.systemui", 1);
        getWindow().clearFlags(524288);
        this.w.b(true);
        return true;
    }

    private static void bcV_(hnu hnuVar, Handler handler) {
        InterfaceHiMap f = hnuVar.f();
        if (f != null) {
            f.onCameraChangeListener(handler);
            f.setIsStop(false);
        }
    }

    private void w() {
        if (gvv.a(this.u, this.w.at())) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: hfv
                @Override // java.lang.Runnable
                public final void run() {
                    TrackMainMapActivity.this.f();
                }
            });
        }
    }

    public /* synthetic */ void f() {
        gvv.b("9002", "900200020", new e());
    }

    /* loaded from: classes4.dex */
    public static class e implements HiDataReadResultListener {
        private WeakReference<TrackMainMapActivity> c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private e(TrackMainMapActivity trackMainMapActivity) {
            this.c = new WeakReference<>(trackMainMapActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            final TrackMainMapActivity trackMainMapActivity = this.c.get();
            if (trackMainMapActivity == null) {
                LogUtil.h("Track_TrackMainMapActivity", "onResponse: activity is null");
                return;
            }
            if (!koq.e(obj, HiSampleConfig.class)) {
                LogUtil.h("Track_TrackMainMapActivity", "onResponse: objData is not instanceof HiSampleConfig");
                return;
            }
            List list = (List) obj;
            if (!koq.b(list)) {
                trackMainMapActivity.v = (SportBeat) HiJsonUtil.e(((HiSampleConfig) list.get(0)).getConfigData(), SportBeat.class);
                if (trackMainMapActivity.v == null) {
                    LogUtil.h("Track_TrackMainMapActivity", "onResponse: activity.mSportBeat is null, loading default value");
                    return;
                } else {
                    trackMainMapActivity.runOnUiThread(new Runnable() { // from class: hgb
                        @Override // java.lang.Runnable
                        public final void run() {
                            r0.w.d(TrackMainMapActivity.this.v);
                        }
                    });
                    return;
                }
            }
            LogUtil.h("Track_TrackMainMapActivity", "onResponse: list is empty");
        }
    }

    public void j() {
        SportBeat sportBeat;
        if (gvv.a(this.u, this.w.at()) && (sportBeat = this.v) != null && sportBeat.isOpen() && this.w.ay()) {
            this.v.setPlayStatus(2);
            this.w.d(this.v);
        }
    }

    public void l() {
        if (gvv.a(this.u, this.w.at()) && this.v != null) {
            if (!this.w.ay()) {
                this.v.setPlayStatus(1);
            }
            this.w.d(this.v);
        }
    }

    private void ap() {
        Bundle bundle = this.k;
        if (bundle == null) {
            LogUtil.b("Track_TrackMainMapActivity", "mParamsBundle is null");
            return;
        }
        String string = bundle.getString("PATH_ID", null);
        if (TextUtils.isEmpty(string)) {
            LogUtil.a("Track_TrackMainMapActivity", "repuestHotTrackPath hotPathId null !");
        } else {
            this.w.e(string, new d(this));
        }
    }

    private void s() {
        if (this.w.am()) {
            al();
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.9
                @Override // java.lang.Runnable
                public void run() {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_BREAKPOINT_RECONNECT_85070017.value(), 0);
                }
            });
            gwo.a();
        }
    }

    private void al() {
        this.w.i(true);
        enc bj = this.w.bj();
        a(bj);
        gtx.c(BaseApplication.getContext()).b(bj);
        MotionData a2 = this.w.a(false);
        jdx.b(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.10
            @Override // java.lang.Runnable
            public void run() {
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_BREAKPOINT_RECONNECT_85070017.value(), 1);
            }
        });
        if (a2 == null) {
            LogUtil.h("Track_TrackMainMapActivity", "motionData is null!");
            gwo.a();
        } else {
            if (this.u != 264) {
                this.w.aa().b();
            }
            this.ag.c(a2);
            gsx.a(this);
        }
        this.w.i(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(enc encVar) {
        if (encVar == null) {
            LogUtil.h("Track_TrackMainMapActivity", "drawHotTrackPath hotPathDetailInfo is null");
            return;
        }
        hnu hnuVar = this.ag;
        if (hnuVar == null) {
            LogUtil.h("Track_TrackMainMapActivity", "mTrackMainViewHolder is null, return");
        } else {
            hnuVar.b(encVar);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
    public void updateGpsStatus(int i) {
        if (this.w.aw()) {
            this.ag.a(true);
        } else {
            this.ag.a(false);
        }
        this.ag.d(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
    public void startSport() {
        if (e()) {
            return;
        }
        if (this.w == null) {
            this.w = gtx.c(getApplicationContext());
        }
        if (this.w.aq()) {
            nrh.e(BaseApplication.getContext(), R.string._2130847803_res_0x7f02283b);
            return;
        }
        ax();
        if (this.ag == null || !gvv.a(this.u, this.w.at())) {
            return;
        }
        this.ag.ae();
    }

    private void ax() {
        InterfaceHiMap f;
        this.w.as();
        this.w.a(this.u);
        int i = this.l;
        if (i != 3 && i != 4) {
            this.w.d(this.y, this.ac);
        }
        this.w.d(this.l);
        this.w.by();
        nsn.o();
        if (this.w.w() != null && this.u != 264 && (f = this.ag.f()) != null) {
            f.animateCamera(this.w.w(), 0L, (InterfaceMapCallback) null);
        }
        LogUtil.a("Track_TrackMainMapActivity", "To show app lock screen");
        if (!af()) {
            e((Context) this);
        }
        if (this.t == null) {
            this.t = new ScreenListener(this);
            c cVar = new c(this);
            this.r = cVar;
            this.t.b(cVar);
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("sportId", this.w.an());
        if (!Utils.o()) {
            hashMap.put("startTime", this.w.aj());
            hashMap.put("goalValue", Integer.valueOf((int) this.ac));
        }
        hashMap.put("goalType", Integer.valueOf(this.y));
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.u));
        hashMap.put("trackType", Integer.valueOf(this.w.r()));
        int i2 = this.e;
        if (i2 != -1) {
            hashMap.put("from", Integer.valueOf(i2));
        }
        c(AnalyticsValue.BI_TRACK_SPORT_START_SPORT_KEY.value(), hashMap);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
    public void pauseSport(int i) {
        LogUtil.a("Track_TrackMainMapActivity", "pauseSport");
        this.w.bk();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("sportId", this.w.an());
        if (!Utils.o()) {
            hashMap.put("startTime", String.valueOf(this.w.aj()));
            hashMap.put("pauseTime", String.valueOf(System.currentTimeMillis()));
            hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, Long.valueOf(this.w.getSportDurationBySecond()));
            hashMap.put("distances", Integer.valueOf((int) this.w.j()));
            hashMap.put("avgPace", gvv.a(this.w.v()));
        }
        c(AnalyticsValue.BI_TRACK_SPORT_PAUSE_SPORT_KEY.value(), hashMap);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
    public void stopSport(boolean z) {
        LogUtil.a("Track_TrackMainMapActivity", "stopSport toSaveData is ", Boolean.valueOf(z));
        hac.a().j();
        haa.c().b();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("sportId", this.w.an());
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.u));
        hashMap.put("trackType", Integer.valueOf(this.w.r()));
        if (!Utils.o()) {
            hashMap.put("startTime", String.valueOf(this.w.aj()));
            hashMap.put("endTime", String.valueOf(System.currentTimeMillis()));
            hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, Long.valueOf(this.w.getSportDurationBySecond()));
        }
        c(AnalyticsValue.BI_TRACK_SPORT_STOP_SPORT_KEY.value(), hashMap);
        if (z) {
            d(60);
        } else {
            gso.e().b(this.u, -1L);
            finish();
            if (hab.g()) {
                hab.b();
            }
        }
        if (this.ag.f() != null) {
            this.ag.f().setIsStop(true);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
    public void resumeSport(int i) {
        this.w.bq();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("sportId", this.w.an());
        if (!Utils.o()) {
            hashMap.put("startTime", String.valueOf(this.w.aj()));
            hashMap.put("resumeTime", String.valueOf(System.currentTimeMillis()));
            hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, Long.valueOf(this.w.getSportDurationBySecond()));
            hashMap.put("distances", Integer.valueOf((int) this.w.j()));
        }
        c(AnalyticsValue.BI_TRACK_SPORT_RESUME_SPORT_KEY.value(), hashMap);
    }

    private void c(String str, Map<String, Object> map) {
        Context y = y();
        if (y == null) {
            LogUtil.h("Track_TrackMainMapActivity", "handBIAnalytics failed, context is null");
        } else {
            ixx.d().d(y, str, map, 0);
        }
    }

    public void o() {
        guk.a(true);
        if (isFinishing()) {
            return;
        }
        LogUtil.a("Track_TrackMainMapActivity", "start resumeSport ");
        if (e()) {
            this.ae.onResumeSport("APP");
            return;
        }
        resumeSport(0);
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.m;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        this.m.dismiss();
        this.m = null;
    }

    public void g() {
        if (e()) {
            this.ae.onPauseSport("APP");
            return;
        }
        guk.a(false);
        gxd.a().e();
        gxd.a().d();
        if (isFinishing()) {
            return;
        }
        pauseSport(0);
    }

    public void k() {
        if (e()) {
            this.ae.onStopSport("APP");
            finish();
        } else {
            if (isFinishing()) {
                return;
            }
            at();
        }
    }

    public void m() {
        LogUtil.a("Track_TrackMainMapActivity", "stopSportWithCourseEndDialog enter ");
        if (isFinishing()) {
            return;
        }
        if (e()) {
            this.ae.onStopSport("APP");
            finish();
        } else {
            bcZ_(new View.OnClickListener() { // from class: hgc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TrackMainMapActivity.this.bdb_(view);
                }
            });
        }
    }

    public /* synthetic */ void bdb_(View view) {
        this.f = null;
        at();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void at() {
        boolean bd = this.w.bd();
        boolean av = this.w.av();
        if (!bd) {
            av();
        } else if (av) {
            b(R.string._2130847366_res_0x7f022686, true);
        } else {
            bc();
        }
    }

    private void bc() {
        int b2 = gso.e().b();
        if (System.currentTimeMillis() - CommonUtil.g(this.w.aj()) <= 0) {
            ReleaseLogUtil.d("Track_TrackMainMapActivity", "The endTime is earlier than startTime,this track would not be saved");
            aw();
        } else {
            e(b2);
        }
    }

    private void av() {
        int b2 = gso.e().b();
        if (b2 == 2) {
            guq guqVar = this.ah;
            if (guqVar != null) {
                guqVar.i();
            }
            this.w.n(false);
            finish();
            return;
        }
        if (b2 == 1) {
            gso.e().b(0);
            this.w.n(false);
            finish();
        } else if (this.o) {
            b(R.string._2130839777_res_0x7f0208e1, false);
        } else {
            this.w.n(false);
            finish();
        }
    }

    private boolean aa() {
        CustomTextAlertDialog customTextAlertDialog = this.f;
        return customTextAlertDialog != null && customTextAlertDialog.isShowing();
    }

    private boolean ab() {
        return this.l == 4;
    }

    private boolean ah() {
        int i = this.u;
        return i == 264 || i == 258;
    }

    private void bcZ_(View.OnClickListener onClickListener) {
        if (aa()) {
            LogUtil.a("Track_TrackMainMapActivity", "showCourseEndConfigDialog dialog showing.");
            return;
        }
        boolean d2 = car.d(this);
        LogUtil.a("Track_TrackMainMapActivity", "showCourseEndConfigDialog dialogShown: ", Boolean.valueOf(d2), " isFromCourse: ", Boolean.valueOf(ab()), " isRunning: ", Boolean.valueOf(ah()));
        if (d2 || !ab() || !ah()) {
            onClickListener.onClick(null);
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(R.string._2130845412_res_0x7f021ee4).d(R.string._2130845413_res_0x7f021ee5).cyU_(R.string._2130845414_res_0x7f021ee6, onClickListener);
        Context y = y();
        if (LanguageUtil.f(y)) {
            builder.a(nsn.c(y, 20.0f), nsn.c(y, 14.0f));
        }
        CustomTextAlertDialog a2 = builder.a();
        this.f = a2;
        a2.setCancelable(false);
        this.f.show();
        car.b(this, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!ab() || !ah()) {
            LogUtil.a("Track_TrackMainMapActivity", "autoFinishByCourseEndConfig isFromCourse: ", Boolean.valueOf(ab()), " isRunning: ", Boolean.valueOf(ah()));
            return;
        }
        boolean a2 = car.a(BaseApplication.getContext());
        LogUtil.a("Track_TrackMainMapActivity", "autoFinishByCourseEndConfig isKeepMoving: ", Boolean.valueOf(a2), " isSporting: ", Boolean.valueOf(this.w.ay()), " isFinishing: ", Boolean.valueOf(isFinishing()));
        if (a2 || isFinishing()) {
            return;
        }
        r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.w.ay()) {
            runOnUiThread(new Runnable() { // from class: hfy
                @Override // java.lang.Runnable
                public final void run() {
                    TrackMainMapActivity.this.b();
                }
            });
            guk.a(false);
            this.w.bi();
        }
        runOnUiThread(new Runnable() { // from class: hfz
            @Override // java.lang.Runnable
            public final void run() {
                TrackMainMapActivity.this.k();
            }
        });
        gso.e().c(0);
    }

    public /* synthetic */ void b() {
        this.ag.e();
    }

    private void e(int i) {
        if (this.u == 264) {
            if (i == 2) {
                Bundle bundle = new Bundle();
                bundle.putString("voiceType", "distance");
                bundle.putInt(BleConstants.SPORT_TYPE, this.w.n());
                bundle.putFloat("distance", this.w.h());
                gxi gxiVar = new gxi(23, bundle);
                guq guqVar = this.ah;
                if (guqVar != null) {
                    guqVar.d(gxiVar);
                }
            }
            q();
            b("com.huawei.healthcloud.plugintrack.NOTIFY_SPORT_DATA", "com.android.keyguard.permission.SEND_STEP_INFO_COUNTER", "com.android.systemui", 0);
            j((Context) this);
            return;
        }
        ao();
    }

    private void aq() {
        hnu hnuVar = this.ag;
        if (hnuVar != null) {
            hnuVar.z();
        }
    }

    private void b(int i, final boolean z) {
        LogUtil.a("Track_TrackMainMapActivity", "====enter showLittleDataDialog()=====", Boolean.valueOf(z));
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.m;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.h("Track_TrackMainMapActivity", "LittleDataDialog is showing");
            return;
        }
        Context y = y();
        if (y == null) {
            LogUtil.h("Track_TrackMainMapActivity", "showLittleDataDialog failed, context is null");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(y).e(i).czC_(R.string._2130839724_res_0x7f0208ac, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TrackMainMapActivity.this.ag.f() != null) {
                    TrackMainMapActivity.this.ag.f().setIsStop(true);
                }
                if (TrackMainMapActivity.this.w != null) {
                    TrackMainMapActivity.this.w.n(z);
                }
                TrackMainMapActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130839723_res_0x7f0208ab, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_TrackMainMapActivity", "LittleDataDialog is showing, onClick");
                if (TrackMainMapActivity.this.ag.p()) {
                    TrackMainMapActivity.this.ag.aa();
                }
                if (TrackMainMapActivity.this.w != null) {
                    TrackMainMapActivity.this.w.d(7, gso.e().g());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.m = e2;
        e2.setCancelable(false);
        this.m.show();
        this.w.b(4);
        this.w.d(5, gso.e().g());
    }

    private void aw() {
        Context y = y();
        if (y == null) {
            LogUtil.h("Track_TrackMainMapActivity", "showSystemTimeErrorTipDialog failed, context is null");
            return;
        }
        LogUtil.a("Track_TrackMainMapActivity", "====enter showSystemTimeErrorTipDialog()=====");
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(y).d(R.string._2130839805_res_0x7f0208fd).b(R.string._2130839727_res_0x7f0208af).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TrackMainMapActivity.this.ag != null && TrackMainMapActivity.this.ag.f() != null) {
                    TrackMainMapActivity.this.ag.f().setIsStop(true);
                }
                TrackMainMapActivity.this.w.n(false);
                TrackMainMapActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.z = a2;
        a2.setCancelable(false);
        this.z.show();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(final int r11) {
        /*
            r10 = this;
            gtx r0 = r10.w
            r1 = 50
            if (r0 == 0) goto L19
            boolean r0 = r0.ax()
            if (r0 != 0) goto L19
            if (r11 <= 0) goto L19
            android.os.Handler r0 = r10.n
            hga r3 = new hga
            r3.<init>()
            r0.postDelayed(r3, r1)
            return
        L19:
            android.os.Handler r0 = r10.n
            r3 = 0
            r0.removeCallbacksAndMessages(r3)
            java.lang.String r0 = "Track_TrackMainMapActivity"
            if (r11 > 0) goto L2d
            java.lang.String r3 = "showTrackMap() with not saved."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)
        L2d:
            int r11 = 60 - r11
            long r3 = (long) r11
            long r3 = r3 * r1
            java.lang.Long r11 = java.lang.Long.valueOf(r3)
            java.lang.String r1 = "showTrackMap wait time:"
            java.lang.Object[] r11 = new java.lang.Object[]{r1, r11}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)
            gtx r11 = r10.w
            if (r11 != 0) goto L4e
            java.lang.String r11 = "mSportManager is null."
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)
            return
        L4e:
            com.huawei.hwfoundationmodel.trackmodel.MotionPath r11 = r11.ad()
            gtx r0 = r10.w
            com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify r0 = r0.ae()
            guj r1 = defpackage.guj.d()
            boolean r2 = r1.e()
            java.lang.String r3 = "target_motion_path.txt"
            if (r2 == 0) goto L9b
            int r2 = r1.b()
            r4 = 1
            if (r2 != r4) goto L9b
            gtx r2 = r10.w
            float r2 = r2.getDistance()
            float r1 = r1.h()
            float r2 = r2 - r1
            r1 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r1
            int r1 = java.lang.Math.round(r2)
            if (r1 < r4) goto L9b
            android.content.Context r11 = r10.y()
            java.lang.String r0 = "target_motion_simplify.txt"
            com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify r0 = defpackage.gwk.e(r11, r0)
            gtx r11 = r10.w
            r11.a(r0)
            android.content.Context r11 = r10.y()
            int r1 = r10.u
            com.huawei.hwfoundationmodel.trackmodel.MotionPath r11 = defpackage.gwk.c(r11, r3, r1)
            goto L9c
        L9b:
            r4 = 0
        L9c:
            if (r11 == 0) goto Ld3
            if (r0 == 0) goto Ld3
            if (r4 == 0) goto La3
            goto La6
        La3:
            java.lang.String r3 = "motion_path2.txt"
        La6:
            r5 = r3
            gso r4 = defpackage.gso.e()
            java.util.List r7 = java.util.Collections.EMPTY_LIST
            r8 = 1
            r9 = 0
            r6 = r0
            android.os.Bundle r11 = r4.aTo_(r5, r6, r7, r8, r9)
            gtx r1 = r10.w
            long r1 = r1.ab()
            if (r11 == 0) goto Ld3
            java.lang.String r3 = "hotTrackParticipateNumKey"
            r11.putLong(r3, r1)
            java.lang.String r1 = "entrance"
            java.lang.String r2 = "fromTrackMainMap"
            r11.putSerializable(r1, r2)
            java.lang.String r1 = "targetType"
            int r2 = r10.y
            r11.putInt(r1, r2)
            r10.bcX_(r0, r11)
        Ld3:
            r10.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.d(int):void");
    }

    public /* synthetic */ void c(int i) {
        d(i - 1);
    }

    private void bcX_(MotionPathSimplify motionPathSimplify, Bundle bundle) {
        ReleaseLogUtil.e("Track_TrackMainMapActivity", "sendSportResult sportType = ", Integer.valueOf(this.u), ", trackFrom = ", Integer.valueOf(this.af));
        gtx gtxVar = this.w;
        if (gtxVar != null && gtxVar.aq() && !BaseApplication.isRunningForeground()) {
            ReleaseLogUtil.e("Track_TrackMainMapActivity", "Auto track and is not running Foreground");
            return;
        }
        if (SportSupportUtil.c(this.u) && this.af == 1) {
            int requestTotalDistance = motionPathSimplify.requestTotalDistance();
            if (requestTotalDistance < this.ac * 1000.0f) {
                ReleaseLogUtil.e("Track_TrackMainMapActivity", "sendSportResult this is invalid result, totalDistance = ", Integer.valueOf(requestTotalDistance), ", mTargetValue = ", Float.valueOf(this.ac));
                gso.e().b(this.u, -1L);
                return;
            } else {
                gso.e().b(this.u, motionPathSimplify.requestTotalTime());
                return;
            }
        }
        gso.e().aTt_(bundle);
    }

    public static void e(Context context) {
        if (context == null) {
            LogUtil.b("Track_TrackMainMapActivity", "context is null");
            return;
        }
        try {
            if (CommonUtil.bk()) {
                Intent intent = new Intent();
                bcY_(context, intent);
                intent.putExtra(BleConstants.SPORT_TYPE, gtx.c(context.getApplicationContext()).n());
                intent.putExtra("isStop", false);
                intent.putExtra("id", "Track_TrackMainMapActivity");
                intent.putExtra("stringKey", R.string._2130843715_res_0x7f021843);
                intent.putExtra("NOTIFICATION_TYPE", 1);
                context.startForegroundService(intent);
                LogUtil.a("Track_TrackMainMapActivity", "startForegroundService");
                f3672a.set(true);
            }
        } catch (Throwable th) {
            ReleaseLogUtil.c("Track_TrackMainMapActivity", ExceptionUtils.d(th));
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        gtx gtxVar = this.w;
        if (gtxVar == null) {
            return true;
        }
        int m = gtxVar.m();
        LogUtil.c("Track_TrackMainMapActivity", "onKeyDown currentStatus = ", Integer.valueOf(m));
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.ab > 0 && System.currentTimeMillis() - this.ab < 2000) {
            return true;
        }
        if (nsn.ae(y())) {
            finish();
        }
        hnu hnuVar = this.ag;
        if (hnuVar != null && !hnuVar.m()) {
            if (m == 1) {
                nrh.b(BaseApplication.getContext(), R.string._2130839726_res_0x7f0208ae);
                this.ab = System.currentTimeMillis();
            } else if (m == 2) {
                nrh.b(BaseApplication.getContext(), R.string._2130842471_res_0x7f021367);
                this.ab = System.currentTimeMillis();
            }
        }
        return true;
    }

    private void au() {
        gut.aUn_(y(), new Intent("ACTION_ACTIVITY_PAUSE_CITY_AS"));
        LogUtil.a("Track_TrackMainMapActivity", "Stop OfflineMap Download service");
    }

    private void b(String str, String str2, String str3, int i) {
        Intent intent = new Intent(str);
        intent.setPackage(str3);
        Bundle bundle = new Bundle();
        bundle.putInt("state", i);
        intent.putExtras(bundle);
        sendBroadcast(intent, str2);
        LogUtil.a("Track_TrackMainMapActivity", "Send permission broadcast to com.android.systemui, state:", Integer.valueOf(i));
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
    public void updateSportStatusWhenLockScreen(int i) {
        this.ag.c(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        if (ag()) {
            startActivity(new Intent(this, (Class<?>) LauncherActivity.class));
        }
    }

    private boolean ag() {
        int m = gwg.m(y());
        if (m == 2) {
            LogUtil.a("Track_TrackMainMapActivity", " PHONE_STATE_CALL_STATE_RINGING");
            return false;
        }
        if (m != 1) {
            return true;
        }
        LogUtil.a("Track_TrackMainMapActivity", " PHONE_STATE_CALL_STATE_OFFHOOK");
        return false;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 2000) {
            if (intent != null && intent.hasExtra("sportBeatData")) {
                this.v = (SportBeat) intent.getParcelableExtra("sportBeatData");
            }
            l();
            return;
        }
        if (i != 1000) {
            if (i2 == -1 && i == 4) {
                u();
                return;
            }
            return;
        }
        hnu hnuVar = this.ag;
        if (hnuVar != null) {
            hnuVar.e(i);
        } else {
            finish();
        }
    }

    public void bdc_(Intent intent, int i) {
        if (intent == null) {
            ReleaseLogUtil.d("Track_TrackMainMapActivity", "startSettingActivity intent is null.");
        } else {
            startActivityForResult(intent, i);
        }
    }

    public void b(boolean z) {
        this.o = z;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.ag == null || ae()) {
            return;
        }
        this.ag.ab();
        this.ag.ac();
        this.ag.u();
        am();
        c cVar = this.r;
        if (cVar != null) {
            cVar.onScreenOn();
        }
        if (this.ag.f() != null) {
            this.ag.f().setScreenOnOrForegrand(true);
            this.ag.f().onResume();
        }
    }

    private void as() {
        if (this.ad == null) {
            LogUtil.b("Track_TrackMainMapActivity", "Enter registerSystemLanguageChange()");
            SystemLocaleChangeReceiver systemLocaleChangeReceiver = new SystemLocaleChangeReceiver();
            this.ad = systemLocaleChangeReceiver;
            BroadcastManager.wk_(systemLocaleChangeReceiver);
        }
    }

    /* loaded from: classes4.dex */
    public class SystemLocaleChangeReceiver extends BroadcastReceiver {
        public SystemLocaleChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("Track_TrackMainMapActivity", "SystemLocaleChangeReceiver intent is null");
                return;
            }
            if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                LogUtil.a("Track_TrackMainMapActivity", "SystemLocaleChangeReceiver language change");
                if (TrackMainMapActivity.this.ag != null) {
                    if (TrackMainMapActivity.this.ag.p()) {
                        TrackMainMapActivity.this.ag.ad();
                    } else {
                        TrackMainMapActivity.this.ag.n();
                    }
                }
                boolean unused = TrackMainMapActivity.d = true;
            }
        }
    }

    private void az() {
        if (this.ad != null) {
            LogUtil.a("Track_TrackMainMapActivity", "Enter unregisterSystemLanguageChange()");
            BroadcastManager.wy_(this.ad);
            this.ad = null;
        }
    }

    /* loaded from: classes4.dex */
    class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (TrackMainMapActivity.this.ag != null) {
                LogUtil.a("Track_TrackMainMapActivity", "RefreshSportingIconDataReceiver onReceive()");
                TrackMainMapActivity.this.ag.af();
            }
        }
    }

    private void aj() {
        Context y = y();
        if (y == null) {
            LogUtil.h("Track_TrackMainMapActivity", "registerRefreshSportingIconDataReceiver failed, context is null");
            return;
        }
        this.p = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("refreshSportingIconDataReceiver");
        BroadcastManagerUtil.bFA_(y, this.p, intentFilter, LocalBroadcast.c, null);
    }

    private void ba() {
        a aVar;
        Context y = y();
        if (y == null || (aVar = this.p) == null) {
            return;
        }
        y.unregisterReceiver(aVar);
        this.p = null;
    }

    private boolean ae() {
        return !ScreenUtil.a();
    }

    private Context y() {
        WeakReference<Context> weakReference = this.b;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("Track_TrackMainMapActivity", "onPause");
        if (this.ag == null) {
            LogUtil.a("Track_TrackMainMapActivity", "onPause mTrackMainViewHolder is null.");
            return;
        }
        p();
        if (this.ag.f() != null) {
            this.ag.f().onPause();
        }
        if (isFinishing()) {
            t();
        }
        LogUtil.a("Track_TrackMainMapActivity", "onPause mInstanceCount = " + c);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        LogUtil.a("Track_TrackMainMapActivity", "onStop");
        super.onStop();
        hnu hnuVar = this.ag;
        if (hnuVar == null || hnuVar.f() == null) {
            return;
        }
        this.ag.f().setScreenOnOrForegrand(false);
        this.ag.f().onStop();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("Track_TrackMainMapActivity", "onDestroy");
        super.onDestroy();
        if (ak()) {
            SportMusicController.a().a(1, (String) null);
        }
        p();
        aq();
        t();
        az();
        ba();
        gso.e().x();
        LogUtil.a("Track_TrackMainMapActivity", "onDestroy mInstanceCount = " + c);
    }

    private boolean ak() {
        if (this.ai == null) {
            x();
        }
        return CommonUtil.bd() && gwg.a(y()) && this.ai.f(this.u) == 1 && gvv.b(y()) == 0;
    }

    private void p() {
        if (e() || a()) {
            getWindow().clearFlags(128);
        }
        LogUtil.a("Track_TrackMainMapActivity", "cancel screen flag is on");
    }

    private void am() {
        if (e() || a()) {
            getWindow().addFlags(128);
        }
        LogUtil.a("Track_TrackMainMapActivity", "keep screen flag is on");
    }

    public boolean c() {
        Object systemService = getSystemService("sensor");
        if (!(systemService instanceof SensorManager)) {
            ReleaseLogUtil.d("Track_TrackMainMapActivity", "isSupportSteps object type is not SensorManager");
            return false;
        }
        if (((SensorManager) systemService).getDefaultSensor(19) == null) {
            return false;
        }
        int i = this.u;
        return i == 257 || i == 258 || i == 282 || i == 260;
    }

    private void t() {
        LogUtil.a("Track_TrackMainMapActivity", "destroy mIsDestroyed:", Boolean.valueOf(this.g));
        if (this.g) {
            return;
        }
        LogUtil.a("Track_TrackMainMapActivity", "destroy mInstanceCount = ", Integer.valueOf(c));
        if (c == 1 && !e()) {
            gxd.a().f("Track_TrackMainMapActivity");
            b("com.huawei.healthcloud.plugintrack.NOTIFY_SPORT_DATA", "com.android.keyguard.permission.SEND_STEP_INFO_COUNTER", "com.android.systemui", 0);
            j((Context) this);
            hnu hnuVar = this.ag;
            if (hnuVar != null) {
                hnuVar.g();
            }
            gtx c2 = gtx.c(getApplicationContext());
            if (c2 != null) {
                c2.a("Track_TrackMainMapActivityTargetUpdate");
                if (!c2.bd() && !c2.am()) {
                    gwo.a();
                }
                c2.ca();
                c2.bm();
            }
            gso.e().d();
        }
        ScreenListener screenListener = this.t;
        if (screenListener != null) {
            screenListener.c();
        }
        int i = c - 1;
        c = i;
        LogUtil.a("Track_TrackMainMapActivity", "destroy mInstanceCount = ", Integer.valueOf(i));
        this.g = true;
        guk.a(false);
    }

    private void q() {
        gtx gtxVar = this.w;
        if (gtxVar == null) {
            LogUtil.b("Track_TrackMainMapActivity", "mSportManager is null");
            return;
        }
        LogUtil.a("Track_TrackMainMapActivity", "calibrationDistance() acquireSportDistance = ", LogAnonymous.b((int) gtxVar.j()));
        if (this.y != 1 || Math.round((this.w.getDistance() - (this.ac * 1000.0f)) / 10.0f) < 1) {
            this.i = false;
        }
        Context y = y();
        if (y == null) {
            LogUtil.h("Track_TrackMainMapActivity", "calibrationDistance context is null");
        } else {
            new IndoorRunCalibrationDistanceDialog(y, this.w.h()).c(new IndoorRunCalibrationDistanceDialog.IndoorRunCalibrationDistanceInterface() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.11
                @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog.IndoorRunCalibrationDistanceInterface
                public void onClick(Dialog dialog, float f) {
                    TrackMainMapActivity.this.bcW_(dialog, f);
                }
            }).e(new IndoorRunCalibrationDistanceDialog.IndoorRunCalibrationDistanceInterface() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.15
                @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.IndoorRunCalibrationDistanceDialog.IndoorRunCalibrationDistanceInterface
                public void onClick(Dialog dialog, float f) {
                    if (dialog != null) {
                        TrackMainMapActivity.this.ao();
                        dialog.dismiss();
                    } else {
                        LogUtil.h("Track_TrackMainMapActivity", "onClick dialog is null.");
                    }
                }
            }).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bcW_(Dialog dialog, float f) {
        if (dialog == null) {
            LogUtil.h("Track_TrackMainMapActivity", "performCalibrationPositiveClick dialog is null.");
            return;
        }
        if (this.w == null) {
            LogUtil.a("Track_TrackMainMapActivity", "performCalibrationPositiveClick mSportManager is null");
            this.w = gtx.c(getApplicationContext());
        }
        int i = 1;
        double d2 = f;
        if (!UnitUtil.e(this.w.h(), 1, 2).equals(UnitUtil.e(d2, 1, 2))) {
            int i2 = (int) (1000.0f * f);
            LogUtil.a("Track_TrackMainMapActivity", "calibrationDistance() inputValue= ", Float.valueOf(f), " changeDistance is ", LogAnonymous.b(i2));
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", "1");
            hashMap.put("distances", Integer.valueOf((int) this.w.j()));
            hashMap.put("calibrationDistance", Integer.valueOf(i2));
            c(AnalyticsValue.BI_TRACK_SPORT_INDOOR_RUN_CALIBRATION_KEY.value(), hashMap);
            int sportDurationBySecond = (int) this.w.getSportDurationBySecond();
            int o = this.w.o();
            this.w.d(gwa.c(d2));
            if (sportDurationBySecond == 0) {
                LogUtil.h("Track_TrackMainMapActivity", "calibrationDistance() duration is 0 ");
            } else {
                i = sportDurationBySecond;
            }
            if (e(i, o)) {
                a(f, i, o);
            }
            LogUtil.a("Track_TrackMainMapActivity", "calibrationDistance(), saveData duration :" + LogAnonymous.d((int) this.w.getSportDuration()), " , step :" + LogAnonymous.d(this.w.o()), " ,distance :", LogAnonymous.b(i2));
        }
        ao();
        this.w.e(false);
        dialog.dismiss();
    }

    private void a(float f, int i, int i2) {
        x();
        gww gwwVar = this.ai;
        gwwVar.a("calibrate_distance_indoor_running_data_duration", gwa.a(gwwVar.b("calibrate_distance_indoor_running_data_duration"), i));
        gww gwwVar2 = this.ai;
        gwwVar2.a("calibrate_distance_indoor_running_data_step", gwa.a(gwwVar2.b("calibrate_distance_indoor_running_data_step"), i2));
        gww gwwVar3 = this.ai;
        gwwVar3.a("calibrate_distance_indoor_running_data_actual_distance", gwa.a(gwwVar3.b("calibrate_distance_indoor_running_data_actual_distance"), f * 1000.0f));
    }

    private boolean e(int i, int i2) {
        float minutes = i2 / TimeUnit.SECONDS.toMinutes(i);
        return minutes <= 220.0f && minutes >= 80.0f;
    }

    private void x() {
        this.ai = new gww(BaseApplication.getContext(), new StorageParams(1), Integer.toString(20002));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        Context y = y();
        if (y == null) {
            LogUtil.h("Track_TrackMainMapActivity", "showPrivicyComfirmDialog failed, context is null");
            return;
        }
        PluginSportTrackAdapter c2 = gso.e().c();
        if (c2 == null) {
            return;
        }
        if (this.ai == null) {
            x();
        }
        if (!Utils.i() || c2.isPrivacyOfSportDataSwitchOn() || this.ai.aa()) {
            c(y);
            return;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        gnb popOutWindowInfo = userProfileMgrApi.getPopOutWindowInfo(y, "privacy_sport_data_");
        int a2 = popOutWindowInfo.a();
        long c3 = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 >= 3 || currentTimeMillis - c3 <= 86400000) {
            c(y);
            return;
        }
        if (gso.e().b() == 2) {
            guq guqVar = this.ah;
            if (guqVar != null) {
                guqVar.f();
            }
            gso.e().b(0);
        }
        userProfileMgrApi.setPopOutWindowInfo(y, "privacy_sport_data_");
        bcT_(y, c2, View.inflate(y, R.layout.track_privacy_comfirm_dialog, null));
    }

    private void bcT_(final Context context, final PluginSportTrackAdapter pluginSportTrackAdapter, View view) {
        CustomViewDialog e2 = new CustomViewDialog.Builder(context).a(context.getString(R.string.IDS_service_area_notice_title)).czg_(view).czd_(context.getString(R.string._2130841129_res_0x7f020e29).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TrackMainMapActivity.this.d(context, pluginSportTrackAdapter, false);
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czf_(context.getString(R.string._2130841555_res_0x7f020fd3).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TrackMainMapActivity.this.d(context, pluginSportTrackAdapter, true);
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).e();
        this.s = e2;
        e2.setCancelable(false);
        this.s.show();
    }

    private void c(Context context) {
        if (SportSupportUtil.c(this.u) && this.af == 1) {
            a(true);
        } else if (ai()) {
            d(context);
        } else {
            a(false);
        }
    }

    private void a(boolean z) {
        guj.d().e(z);
        if (this.w == null) {
            this.w = gtx.c(getApplicationContext());
        }
        this.w.n(true);
    }

    private boolean ai() {
        return this.i && this.y == 1 && this.ac != -1.0f && Math.round((this.w.getDistance() - (this.ac * 1000.0f)) / 10.0f) >= 1;
    }

    private void d(Context context) {
        float f;
        float distance;
        LogUtil.a("Track_TrackMainMapActivity", "showDistanceChooseDialog enter");
        View inflate = View.inflate(context, R.layout.hw_show_choose_distance_view, null);
        if (inflate == null) {
            LogUtil.h("Track_TrackMainMapActivity", "showDistanceChooseDialog dialog layout fail");
            return;
        }
        if (UnitUtil.h()) {
            f = (float) UnitUtil.e(this.ac, 3);
            distance = (float) UnitUtil.e(this.w.getDistance() / 1000.0f, 3);
        } else {
            f = this.ac;
            distance = this.w.getDistance() / 1000.0f;
        }
        double d2 = f;
        String e2 = UnitUtil.e(d2, 1, Math.abs(d2 - 42.195d) >= 1.0E-5d ? Math.abs(d2 - 21.0975d) < 1.0E-5d ? 4 : 2 : 3);
        double d3 = distance;
        String e3 = UnitUtil.e(d3, 1, 2);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.stop_with_target_distance);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.stop_with_total_distance);
        int i = UnitUtil.h() ? R.plurals._2130903290_res_0x7f0300fa : R.plurals._2130903286_res_0x7f0300f6;
        int i2 = UnitUtil.h() ? R.plurals._2130903291_res_0x7f0300fb : R.plurals._2130903287_res_0x7f0300f7;
        healthTextView.setText(getResources().getQuantityString(i, (int) Math.ceil(d2), e2));
        healthTextView2.setText(getResources().getQuantityString(i2, (int) Math.ceil(d3), e3));
        bda_(inflate, new CustomViewDialog.Builder(context));
    }

    private void bda_(View view, CustomViewDialog.Builder builder) {
        builder.a(getString(R.string._2130844056_res_0x7f021998)).czh_(view, 0, 0).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (TrackMainMapActivity.this.w == null) {
                    LogUtil.a("Track_TrackMainMapActivity", "mSportManager is null stopSport");
                    TrackMainMapActivity trackMainMapActivity = TrackMainMapActivity.this;
                    trackMainMapActivity.w = gtx.c(trackMainMapActivity.getApplicationContext());
                }
                TrackMainMapActivity.this.w.n(true);
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        bcU_(view);
        CustomViewDialog e2 = builder.e();
        e2.setCancelable(false);
        guj.d().e(true);
        e2.show();
    }

    private void bcU_(View view) {
        final HealthRadioButton healthRadioButton = (HealthRadioButton) view.findViewById(R.id.choose_target_distance_radioButton);
        final HealthRadioButton healthRadioButton2 = (HealthRadioButton) view.findViewById(R.id.stop_with_total_distance_radioButton);
        healthRadioButton2.setClickable(false);
        healthRadioButton.setClickable(false);
        ((RelativeLayout) view.findViewById(R.id.choose_target_distance_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                healthRadioButton.setChecked(true);
                healthRadioButton2.setChecked(false);
                guj.d().e(true);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        ((RelativeLayout) view.findViewById(R.id.choose_total_distance_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                healthRadioButton.setChecked(false);
                healthRadioButton2.setChecked(true);
                guj.d().e(false);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        healthRadioButton.setChecked(true);
        healthRadioButton2.setChecked(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, PluginSportTrackAdapter pluginSportTrackAdapter, boolean z) {
        if (pluginSportTrackAdapter != null) {
            pluginSportTrackAdapter.setPrivacyOfSportDataSwitch(z);
        }
        c(context);
        this.s.dismiss();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected boolean isNeedLocationPermission() {
        return (a() || e()) ? false : true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity
    protected boolean isNeedStorgePermission() {
        return this.u == 264 && gvv.d(BaseApplication.getContext());
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (this.ae != null && e()) {
            this.ae.d(this);
        }
        overridePendingTransition(0, R.anim._2130772085_res_0x7f010075);
        hnu hnuVar = this.ag;
        if (hnuVar != null) {
            if (hnuVar.h() != null) {
                this.ag.h().dismiss();
            }
            if (this.ag.i() != null) {
                this.ag.i().dismiss();
            }
        }
    }

    /* loaded from: classes4.dex */
    static class b extends Handler {
        b() {
        }
    }

    /* loaded from: classes4.dex */
    static class d implements LoadHotTrackCallBack {
        private final WeakReference<TrackMainMapActivity> e;

        d(TrackMainMapActivity trackMainMapActivity) {
            this.e = new WeakReference<>(trackMainMapActivity);
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.LoadHotTrackCallBack
        public void onFailure() {
            TrackMainMapActivity trackMainMapActivity = this.e.get();
            if (trackMainMapActivity != null && !trackMainMapActivity.isFinishing()) {
                nrh.e(BaseApplication.getContext(), R.string._2130840056_res_0x7f0209f8);
            } else {
                LogUtil.b("Track_TrackMainMapActivity", "failed activity is finished");
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.LoadHotTrackCallBack
        public void onSuccess(enc encVar) {
            TrackMainMapActivity trackMainMapActivity = this.e.get();
            if (trackMainMapActivity != null && !trackMainMapActivity.isFinishing()) {
                trackMainMapActivity.a(encVar);
            } else {
                LogUtil.b("Track_TrackMainMapActivity", "success activity is finished");
            }
        }
    }

    /* loaded from: classes4.dex */
    static class c implements ScreenListener.ScreenStateListener {
        private final WeakReference<TrackMainMapActivity> c;

        /* renamed from: a, reason: collision with root package name */
        private boolean f3677a = true;
        private boolean e = CommonUtil.ac(BaseApplication.getContext());

        public c(TrackMainMapActivity trackMainMapActivity) {
            this.c = new WeakReference<>(trackMainMapActivity);
        }

        @Override // com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener.ScreenStateListener
        public void onScreenOn() {
            TrackMainMapActivity trackMainMapActivity = this.c.get();
            if (trackMainMapActivity == null) {
                ReleaseLogUtil.c("Track_TrackMainMapActivity", "WeakReference is null");
                return;
            }
            if (this.f3677a) {
                return;
            }
            boolean ac = CommonUtil.ac(BaseApplication.getContext());
            this.e = ac;
            LogUtil.a("Track_TrackMainMapActivity", "onScreenOn() IsScreenLocked: ", Boolean.valueOf(ac));
            this.f3677a = true;
            trackMainMapActivity.w.h(true);
            trackMainMapActivity.ag.e(true);
            LogUtil.a("Track_TrackMainMapActivity", "ScreenStateListenerImp onScreenOn");
        }

        @Override // com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener.ScreenStateListener
        public void onScreenOff() {
            TrackMainMapActivity trackMainMapActivity = this.c.get();
            if (trackMainMapActivity == null) {
                ReleaseLogUtil.c("Track_TrackMainMapActivity", "WeakReference is null");
                return;
            }
            this.f3677a = false;
            trackMainMapActivity.w.h(false);
            trackMainMapActivity.ag.e(false);
            trackMainMapActivity.getWindow().clearFlags(2621568);
            if (!this.e) {
                trackMainMapActivity.an();
            }
            boolean ac = CommonUtil.ac(BaseApplication.getContext());
            this.e = ac;
            LogUtil.a("Track_TrackMainMapActivity", "onScreenOff:", Boolean.valueOf(ac));
        }

        @Override // com.huawei.healthcloud.plugintrack.ui.activity.ScreenListener.ScreenStateListener
        public void onUserPresent() {
            this.e = false;
            LogUtil.a("Track_TrackMainMapActivity", "onUserPresent() IsScreenLocked:", Boolean.valueOf(CommonUtil.ac(BaseApplication.getContext())));
        }
    }

    public static boolean d() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).indexOf("samsung") != -1;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        hnu hnuVar = this.ag;
        if (hnuVar != null) {
            hnuVar.a();
        }
    }

    public void c(File file) {
        this.q = file;
    }

    private void u() {
        if (this.q == null) {
            LogUtil.b("Track_TrackMainMapActivity", "mPhotoFile is null, return.");
            return;
        }
        try {
            if (29 > Build.VERSION.SDK_INT) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.q.getCanonicalPath(), options);
                ContentValues contentValues = new ContentValues();
                contentValues.put("_data", this.q.getPath());
                contentValues.put("_size", Long.valueOf(this.q.length()));
                contentValues.put("height", Integer.valueOf(options.outHeight));
                contentValues.put("width", Integer.valueOf(options.outWidth));
                contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
                contentValues.put("_display_name", this.q.getName());
                getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else {
                MediaStore.Images.Media.insertImage(getContentResolver(), this.q.getCanonicalPath(), this.q.getName(), (String) null);
            }
        } catch (FileNotFoundException e2) {
            LogUtil.b("Track_TrackMainMapActivity", LogAnonymous.b((Throwable) e2));
        } catch (IOException e3) {
            LogUtil.b("Track_TrackMainMapActivity", LogAnonymous.b((Throwable) e3));
        }
        sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(this.q)));
    }
}
