package com.huawei.health;

import android.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.common.util.AccountHelper;
import com.huawei.common.util.AccountInteractors;
import com.huawei.common.util.StorageIsCloud;
import com.huawei.common.util.VersionIsCloud;
import com.huawei.featureuserprofile.target.cloud.ActiveTargetManager;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.common.dfx.storage.StorageMonitor;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.MainActivity;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.constants.BundleTransferdDataContants;
import com.huawei.health.health.player.MiniBottomPlayer;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.health.interactor.OperationAdInteractor;
import com.huawei.health.interactor.WeiXinInteractor;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.motiontrack.api.MotionTrackPretreatmentService;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.receiver.AccountChangeReceiver;
import com.huawei.health.receiver.DeviceBindReceiver;
import com.huawei.health.receiver.OperationFeatureReceiver;
import com.huawei.health.receiver.PermissionDialogReceiver;
import com.huawei.health.receiver.TriggerLoginReceiver;
import com.huawei.health.service.HandoffService;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.h5pro.AiSportVoiceHelper;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.utils.MainInteractorsUtils;
import com.huawei.health.utils.NavigationHelper;
import com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataHiDeviceInfoListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwauthutil.utils.PackageManagerHelper;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.application.RunningForegroundListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.login.ui.login.util.ILoginCallback;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlOperate;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.pluginachievement.manager.service.LanguageResReceiver;
import com.huawei.pluginachievement.manager.service.OnceMovementReceiver;
import com.huawei.pluginachievement.ui.AchieveMedalNewActivity;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginsport.helper.AbsAiModelConfigHelper;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.HuaweiMobileServiceSetDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.scrollview.HealthBottomView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity;
import com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity;
import com.huawei.ui.homehealth.HomeFragment;
import com.huawei.ui.homehealth.device.DeviceFragment;
import com.huawei.ui.homehealth.healthtrend.HealthTrendCardData;
import com.huawei.ui.homehealth.interactors.GolfUpdateMapInteractor;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity;
import com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment;
import com.huawei.ui.main.stories.discover.fragment.DiscoverFragment;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.KnitHeartRateActivity;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity;
import com.huawei.ui.main.stories.smartcenter.activity.SmartMsgSkipActivity;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment;
import com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.byd;
import defpackage.byh;
import defpackage.byq;
import defpackage.byu;
import defpackage.byv;
import defpackage.bzs;
import defpackage.bzu;
import defpackage.cas;
import defpackage.cpa;
import defpackage.csb;
import defpackage.cub;
import defpackage.dow;
import defpackage.dsl;
import defpackage.dzn;
import defpackage.dzu;
import defpackage.efb;
import defpackage.ehu;
import defpackage.eoi;
import defpackage.ezj;
import defpackage.ezt;
import defpackage.ezu;
import defpackage.ezy;
import defpackage.fbi;
import defpackage.fep;
import defpackage.fhp;
import defpackage.gge;
import defpackage.gmn;
import defpackage.gmz;
import defpackage.gnm;
import defpackage.gnv;
import defpackage.goc;
import defpackage.gof;
import defpackage.goj;
import defpackage.gol;
import defpackage.gou;
import defpackage.gow;
import defpackage.gpa;
import defpackage.gpd;
import defpackage.gpo;
import defpackage.gso;
import defpackage.gtx;
import defpackage.gvy;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.gws;
import defpackage.gww;
import defpackage.gyg;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdh;
import defpackage.jeg;
import defpackage.jeq;
import defpackage.jki;
import defpackage.jpt;
import defpackage.jqh;
import defpackage.jqx;
import defpackage.koq;
import defpackage.kor;
import defpackage.kzc;
import defpackage.lsp;
import defpackage.mcv;
import defpackage.mod;
import defpackage.nbr;
import defpackage.nhj;
import defpackage.nkr;
import defpackage.nmh;
import defpackage.nqf;
import defpackage.nrh;
import defpackage.nrw;
import defpackage.nsn;
import defpackage.nsw;
import defpackage.nsy;
import defpackage.ntd;
import defpackage.nue;
import defpackage.odh;
import defpackage.oia;
import defpackage.oli;
import defpackage.opf;
import defpackage.oqr;
import defpackage.owp;
import defpackage.owq;
import defpackage.pem;
import defpackage.pep;
import defpackage.qbd;
import defpackage.qtn;
import defpackage.qup;
import defpackage.sbc;
import defpackage.sbk;
import defpackage.scn;
import defpackage.sds;
import defpackage.slp;
import defpackage.slr;
import defpackage.sqf;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BuildConfigProperties;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.DeviceConfigInit;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.KeyManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback, RunningForegroundListener, SportEntranceFragment.SportEntranceCallback {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2175a = new Object();
    private static boolean b = false;
    private static String f;
    private static Uri g;
    private static Uri h;
    private static Uri i;
    private static Uri j;
    private static Uri l;
    private static String o;
    private String ab;
    private Uri ad;
    private HuaweiMobileServiceSetDialog.Builder ag;
    private LinearLayout ak;
    private ExecutorService al;
    private Handler ar;
    private HuaweiApiClient ay;
    private slr ba;
    private oqr bb;
    private a bd;
    private boolean bw;
    private boolean bx;
    private MainInteractors cd;
    private ViewGroup cg;
    private NavigationHelper ch;
    private TextView cj;
    private HuaweiMobileServiceSetDialog cm;
    private NoTitleCustomAlertDialog cp;
    private String cq;
    private PermissionDialogReceiver cr;
    private PersonalCenterFragment ct;
    private dzn cv;
    private int cy;
    String d;
    private SportEntranceFragment dg;
    private int dh;
    private String di;
    private int dj;
    private String dk;
    private HealthBottomView dl;
    private UserLabelServiceApi dq;
    private Uri dt;
    private gow dv;
    private WeiXinInteractor dy;
    private ViewStub dz;
    private byq k;
    private NoTitleCustomAlertDialog p;
    private HealthApplication q;
    private OperationAdInteractor r;
    private gpd t;
    private NoTitleCustomAlertDialog u;
    private NoTitleCustomAlertDialog v;
    private NoTitleCustomAlertDialog w;
    private byv x;
    private Context y;
    private int m = Color.rgb(MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE, 127, 50);
    String c = Constants.HOME;
    int e = 0;
    private byu ax = null;
    private HomeFragment bc = null;
    private DiscoverFragment ai = null;
    private DeviceFragment z = null;
    private HealthViewPager dr = null;
    private MainInteractorsUtils dw = new MainInteractorsUtils();
    private String ds = null;
    private int aw = -1;
    private Uri du = null;
    private String ck = null;
    private String n = null;
    private String ci = null;
    private String cs = null;
    private String aj = null;
    private String db = null;
    private String da = null;
    private String dd = null;
    private String dc = null;
    private String cw = null;
    private String cx = null;
    private String df = null;
    private String af = null;
    private String de = null;
    private String ah = null;
    private Uri cz = null;
    private boolean bg = false;
    private long am = 0;
    private long ap = 0;
    private long cl = 0;
    private long s = 0;
    private int aa = -1;
    private String cu = "";
    private boolean br = false;
    private boolean bs = false;
    private boolean bk = false;
    private int cn = -1;
    private long dn = 0;
    private boolean ce = false;
    private boolean by = false;
    private boolean ca = false;
    private boolean bz = false;
    private boolean bv = false;
    private boolean cf = false;
    private int cc = -1;
    private boolean bu = false;
    private boolean bo = false;
    private boolean bp = false;
    private boolean cb = false;
    private boolean bf = false;
    private boolean bj = false;
    private boolean be = false;
    private boolean bn = true;
    private boolean ao = true;
    private long co = 0;
    private boolean bl = false;
    private boolean au = false;
    private boolean bm = false;
    private boolean bq = false;
    private boolean bi = false;
    private boolean av = false;
    private boolean bh = false;
    private String dp = null;
    private int az = 0;
    private long dm = 0;

    /* renamed from: do, reason: not valid java name */
    private long f0do = 0;
    private goc aq = new goc();
    private List<Integer> ac = new ArrayList();
    private ArrayList<Fragment> an = new ArrayList<>();
    private int ae = -1;
    private boolean at = false;
    private boolean bt = false;
    private Observer as = new Observer() { // from class: com.huawei.health.MainActivity.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            MainActivity.this.bw = false;
            MainActivity.this.ef();
            MainActivity.this.bp();
            Iterator it = MainActivity.this.ac.iterator();
            while (it.hasNext()) {
                MainActivity.this.ar.sendEmptyMessage(((Integer) it.next()).intValue());
            }
        }
    };

    private void b(Object obj) {
        if (obj instanceof Intent) {
            Intent intent = (Intent) obj;
            if (TextUtils.isEmpty(intent.getAction())) {
                return;
            }
            o();
            if (Action.ACTION_HW_ACCOUNT_LOGOUT.equals(intent.getAction())) {
                ReleaseLogUtil.b("Login_MainActivity", "receive the not static BroadcastReceiver , logout");
                goj.a().aQB_(this, intent);
                this.bg = true;
                KeyValDbManager.b(this.y).a("cloud_st_invalid_flag", "0", new StorageParams(1));
                ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).deleteToken();
                this.bj = false;
            }
        }
    }

    private void o() {
        if (this.dl == null || gpo.b() == this.bx) {
            return;
        }
        boolean b2 = gpo.b();
        this.bx = b2;
        if (b2) {
            this.dl.dZL_(R.string._2130845630_res_0x7f021fbe, this.y.getResources().getDrawable(R.drawable._2131430807_res_0x7f0b0d97), this.ae, false);
            if (this.cj == null) {
                this.cj = Bg_();
            }
            int i2 = this.cn;
            if (i2 == this.ae && i2 != -1) {
                nsy.cMu_(this.cj, this.m);
            }
            ed();
            return;
        }
        this.dl.dZL_(R.string._2130839532_res_0x7f0207ec, this.y.getResources().getDrawable(R.drawable._2131430802_res_0x7f0b0d92), this.ae, false);
    }

    private TextView Bg_() {
        for (View view : Bf_(this.dl.getChildAt(this.ae))) {
            if (view instanceof TextView) {
                return (TextView) view;
            }
        }
        return null;
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        LogUtil.c("Login_MainActivity", "Enter attachBaseContext");
        super.attachBaseContext(context);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        LogUtil.c("Login_MainActivity", "Enter onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ReleaseLogUtil.b("Login_MainActivity", "Enter MainActivity onCreate");
        this.ao = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        nsn.cLf_(this, bundle);
        super.onCreate(bundle);
        this.ar = new h(Looper.getMainLooper(), this);
        BaseDialog.sSplashAdViewId = R.id.splash_ad_view;
        BaseActivity.setNavigationBarVisibility(this, 0);
        this.co = System.currentTimeMillis();
        this.y = this;
        this.dw.j(this);
        bi();
        k();
        StorageIsCloud.storageIsCloud(getApplicationContext());
        StorageIsCloud.isAllowedLogin(getApplicationContext());
        az();
        cancelAdaptRingRegion();
        if (!bo()) {
            if (this.cc == 5) {
                LogUtil.c("Login_MainActivity", "isActivityStackRoot don't continue, handleLaunchFromSportingNotification");
                ay();
            }
            LogUtil.a("Login_MainActivity", "there is already a MainActivity, this are abundant and will be finished");
            this.ao = false;
            finish();
            return;
        }
        dd();
        bn();
        di();
        ezj.aug_(this, this.ar);
        dk();
        ezu.c(BaseApplication.getContext());
        TriggerLoginReceiver.aus_(this.ar);
        ObserverManagerUtil.c("com.huawei.plugin.trigger.checklogin", new Object[0]);
        e(0);
        if (sbc.i()) {
            ey();
        } else {
            byh.BK_(this, this.ar);
        }
        this.bo = true;
        b = false;
        this.dq = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        this.ch = new NavigationHelper(this);
        setMinibarBottomMargin(getResources().getDimensionPixelSize(R.dimen._2131363076_res_0x7f0a0504));
        ReleaseLogUtil.b("Login_MainActivity", "onCreate finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void bn() {
        getWindow().setFlags(16778240, 16778240);
        overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim._2130771980_res_0x7f01000c);
        setContentView(R.layout.layout_activity_mainui);
        getWindow().setBackgroundDrawableResource(R.color._2131296690_res_0x7f0901b2);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.hw_health_main_viewpager);
        this.dr = healthViewPager;
        healthViewPager.setIsScroll(false);
        this.dl = (HealthBottomView) findViewById(R.id.hw_main_tabs);
        this.dz = (ViewStub) findViewById(R.id.viewstub_mainui_startpage);
        this.dv = new gow(this, this.dz);
        this.ak = (LinearLayout) findViewById(R.id.member_guide_view);
    }

    private void ey() {
        String ifNeedShowAreaAlert = VersionIsCloud.getIfNeedShowAreaAlert(this.y);
        LogUtil.c("Login_MainActivity", "todoCheckLogin():ifNeedShowAreaAlert = ", ifNeedShowAreaAlert);
        if ("1".equals(ifNeedShowAreaAlert) && !FoundationCommonUtil.isSameTelephonyNetWorkAndSim(this.y)) {
            this.cd.a(false);
        } else {
            byh.BK_(this, this.ar);
        }
    }

    private void dk() {
        if (CommonUtil.av() || ((CommonUtil.bf() && !CommonUtil.ak()) || Utils.o())) {
            LogUtil.c("Login_MainActivity", "registerPermissionReceiver don't need dialog");
            return;
        }
        LogUtil.c("Login_MainActivity", "registerPermissionReceiver");
        this.cr = new PermissionDialogReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.ACTION_PERMISSION_REQUEST");
        intentFilter.addAction("com.huawei.health.action.ACTION_PERMISSION_REQUEST_FINISH");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.cr, intentFilter);
    }

    private void fb() {
        if (this.cr == null) {
            return;
        }
        LogUtil.c("Login_MainActivity", "unRegisterPermissionReceiver");
        try {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.cr);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("Login_MainActivity", "unRegisterPermissionReceiver IllegalArgumentException");
        }
    }

    private void k() {
        PlanApi planApi;
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.MainActivity.15
            @Override // java.lang.Runnable
            public void run() {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                nbr.c(MainActivity.this.y);
                MainActivity.this.fg();
                odh.a(MainActivity.this.y, MainActivity.this.bo());
                qtn.c();
                H5proUtil.initH5ProAndRefreshVersionDataOnce();
                if (efb.m()) {
                    HealthTrendCardData.d();
                    LogUtil.c("Login_MainActivity", "loading h5 health trend");
                }
                MainActivity.de();
                MultiUsersManager.INSTANCE.regUserInfoListener();
                AbsAiModelConfigHelper.preDownloadConfig();
                MainActivity.this.dm();
                ReleaseLogUtil.b("Login_MainActivity", "asyncInit finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        });
        if (AuthorizationUtils.a(BaseApplication.getContext()) && aa() && owp.f(this.y) == 2 && LoginInit.getInstance(this.y).getIsLogined() && (planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class)) != null) {
            planApi.preLoadPlan();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dm() {
        if ("HTY".equals(BuildConfigProperties.b())) {
            try {
                ReflectionUtils.c(ReflectionUtils.d("com.huawei.health.developer.util.dataexport.HtyDataExportHelper"), "reflectResetAlarm");
            } catch (ClassNotFoundException unused) {
                LogUtil.e("Login_MainActivity", "resetHtyAlarm() ClassNotFoundException");
            }
        }
    }

    private void m() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.MainActivity.24
            @Override // java.lang.Runnable
            public void run() {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                AbsAiModelConfigHelper.preDownloadConfig();
                dzu.a().c();
                ReleaseLogUtil.b("Login_MainActivity", "asyncInitAfterAgreement finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void de() {
        SharedPreferenceManager.e(com.huawei.haf.application.BaseApplication.e(), Integer.toString(10000), "apk_build_time", BuildConfig.BUILD_TIME, new StorageParams());
    }

    private void bi() {
        this.cv = new dzn(this.y, this.ar);
        MainInteractors mainInteractors = new MainInteractors(this.y);
        this.cd = mainInteractors;
        mainInteractors.aaK_(this.ar);
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.al = newSingleThreadExecutor;
        this.cd.d(newSingleThreadExecutor);
        this.cd.x();
        this.cd.d(false);
        this.r = new OperationAdInteractor(this);
        HealthApplication healthApplication = (HealthApplication) getApplication();
        this.q = healthApplication;
        healthApplication.c(this);
    }

    private void dh() {
        boolean e2 = gpa.e();
        this.bw = e2;
        if (e2) {
            ObserverManagerUtil.d(this.as, "MAIN_GUIDE_VIEW_COMPLETED");
        }
    }

    private void fd() {
        if (this.bw) {
            ObserverManagerUtil.e(this.as, "MAIN_GUIDE_VIEW_COMPLETED");
        }
    }

    /* renamed from: do, reason: not valid java name */
    private void m131do() {
        LogUtil.c("Login_MainActivity", "saveBindingDeviceToOdmf enter");
        HiHealthManager.d(this.y).readDeviceInfo(new d());
    }

    /* loaded from: classes3.dex */
    static class d implements HiDataHiDeviceInfoListener {
        private d() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataHiDeviceInfoListener
        public void onResult(List<HiDeviceInfo> list) {
            if (list == null) {
                LogUtil.a("Login_MainActivity", "saveBindingDeviceToOdmf deviceInfos = null");
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDeviceTypeMapping() != -1) {
                    ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).saveBindingDeviceToOdmf(list.get(i).getDeviceInfoToODMF());
                }
            }
        }
    }

    private void eb() {
        LogUtil.c("Login_MainActivity", "showHmsErrorDialog");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.y);
        builder.b("").d(R.string._2130841236_res_0x7f020e94).cyU_(R.string._2130841237_res_0x7f020e95, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Login_MainActivity", "showHmsErrorDialog ok");
                MainInteractors.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    private void az() {
        boolean z;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Intent intent = getIntent();
        if (intent != null) {
            try {
                Bh_(intent);
            } catch (Exception unused) {
                LogUtil.c("Login_MainActivity", "handleIntentData MainActivity encounteredClassNotFoundException");
            }
            if (TextUtils.isEmpty(this.dd) && TextUtils.isEmpty(this.dc) && TextUtils.isEmpty(this.cw) && TextUtils.isEmpty(this.dk)) {
                z = false;
                c(this.aw, z, true ^ TextUtils.isEmpty(this.dk));
                SharedPreferenceManager.e(this.y, String.valueOf(20000), "from", intent.getStringExtra("from"), (StorageParams) null);
                this.ah = intent.getStringExtra("moduleName");
                Bx_(intent);
                boolean booleanExtra = intent.getBooleanExtra("isFromTrainDetail", false);
                this.bl = booleanExtra;
                LogUtil.c("Login_MainActivity", "handleIntentData mIsFromTrainDetail = ", Boolean.valueOf(booleanExtra));
                Bv_(intent);
            }
            z = true;
            c(this.aw, z, true ^ TextUtils.isEmpty(this.dk));
            SharedPreferenceManager.e(this.y, String.valueOf(20000), "from", intent.getStringExtra("from"), (StorageParams) null);
            this.ah = intent.getStringExtra("moduleName");
            Bx_(intent);
            boolean booleanExtra2 = intent.getBooleanExtra("isFromTrainDetail", false);
            this.bl = booleanExtra2;
            LogUtil.c("Login_MainActivity", "handleIntentData mIsFromTrainDetail = ", Boolean.valueOf(booleanExtra2));
            Bv_(intent);
        }
        LogUtil.d("Login_MainActivity", "handleIntentData mSchemeUrl : ", this.db, ", mSmartMsgId = ", Integer.valueOf(this.dj), ", mSmartMsgType = ", Integer.valueOf(this.dh), ", mSmartMsgContent = ", this.di, ", mDeviceType : ", Integer.valueOf(this.aa), ", mProductName : ", this.cu, ", mIsPorc : ", Boolean.valueOf(this.bs), ", sSchemeGroup : ", f, ", mSchemeQrCode : ", this.da, ", mIsNeedLogin : ", Boolean.valueOf(this.br), ", sSchemeKaKaUri = ", g, ", sSchemeAchieveUri = ", h, ", sSchemeNfcUri = ", j, ", sSchemeQrCodeUri = ", l, ", sSchemeColumnUri = ", i, ", mDownloadPluginModuleName = ", this.ah);
        SharedPreferenceManager.e(this.y, String.valueOf(20000), "needLogin", String.valueOf(this.br), (StorageParams) null);
        LogUtil.c("Login_MainActivity", "handleIntentData finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void Bh_(Intent intent) {
        this.ds = intent.getStringExtra("webUrl");
        this.dd = intent.getStringExtra("entrance");
        this.dc = intent.getStringExtra("sleepDetail");
        if (efb.a(this.y)) {
            this.cw = intent.getStringExtra("ecgDetail");
        } else {
            this.cw = intent.getStringExtra("ecgDetail");
        }
        this.cx = intent.getStringExtra("extra_service_id");
        this.df = intent.getStringExtra("walletUri");
        this.db = intent.getStringExtra("schemeUrl");
        f = intent.getStringExtra("healthgroup");
        this.da = intent.getStringExtra("schemeQrCode");
        this.cy = intent.getIntExtra("schemeDataType", -1);
        this.cz = (Uri) intent.getParcelableExtra(" schemeParamUri");
        g = (Uri) intent.getParcelableExtra("schemeKaKa");
        h = (Uri) intent.getParcelableExtra("schemeAchieve");
        j = (Uri) intent.getParcelableExtra("schemeNfc");
        l = (Uri) intent.getParcelableExtra("schemeQrCodeUri");
        i = (Uri) intent.getParcelableExtra("schemeColumnUri");
        this.de = intent.getStringExtra("schemeTrackFilePath");
        this.du = (Uri) intent.getParcelableExtra("weekMonthReportUri");
        this.ck = intent.getStringExtra("achieveMedal");
        this.af = intent.getStringExtra("directConnectDevice");
        this.dj = intent.getIntExtra("health_smartmsg_id", -1);
        this.dh = intent.getIntExtra("health_smartmsg_type", -1);
        this.di = intent.getStringExtra("health_smartmsg_content");
        this.dt = intent.getData();
        this.aw = intent.getIntExtra(com.huawei.health.messagecenter.model.CommonUtil.PARAM_HEALTH_TYPE, -1);
        this.aa = intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -1);
        this.cu = intent.getStringExtra("dname");
        this.bs = intent.getBooleanExtra("isPorc", false);
        this.cq = intent.getStringExtra("pairGuideSelectAddress");
        String stringExtra = intent.getStringExtra(Constants.HOME_TAB_NAME);
        this.ab = stringExtra;
        this.by = Constants.HOME.equals(stringExtra);
        this.dk = nbr.crG_(intent);
        this.br = intent.getBooleanExtra("needLogin", false);
        this.ci = intent.getStringExtra("memberUri");
        this.n = intent.getStringExtra("activeTarget");
        this.cs = intent.getStringExtra("orderCode");
        this.dp = intent.getStringExtra("type");
        this.aj = intent.getStringExtra("from");
    }

    private void c(int i2, boolean z, boolean z2) {
        this.x = new byv.d().e(i2).d(z).a(z2).e(isFinishing()).c();
    }

    private void bh() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        dn();
        oqr oqrVar = new oqr();
        this.bb = oqrVar;
        oqrVar.d();
        ezy.b(this);
        ezt.c(this);
        ReleaseLogUtil.b("Login_MainActivity", "handleStartFirstStep finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        DeviceBindReceiver.d();
        AccountChangeReceiver.auf_(this.ar);
    }

    private void bg() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        StorageMonitor.e();
        dzu.a().c();
        cpa.s();
        cpa.n();
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).registerContactChangeObserver();
        ReleaseLogUtil.b("Login_MainActivity", "handleStartSecondStep finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void e(int i2) {
        ReleaseLogUtil.b("Login_MainActivity", "setStartPageVisibility visibility:", Integer.valueOf(i2));
        if (i2 != 0) {
            bc();
        }
        d(i2);
    }

    private void d(int i2) {
        gow gowVar = this.dv;
        if (gowVar == null) {
            LogUtil.a("Login_MainActivity", "showOrHideStartPage startPageHelper is null");
        } else if (i2 != 0 && this.bc == null) {
            LogUtil.a("Login_MainActivity", "showOrHideStartPage mHomeFragment is null");
        } else {
            gowVar.a(i2);
        }
    }

    public void h() {
        this.bn = true;
        ff();
        fe();
        dg();
    }

    private void ff() {
        HomeFragment homeFragment = this.bc;
        if (homeFragment == null) {
            ReleaseLogUtil.b("Login_MainActivity", "updateHomeFloatView return, mHomeFragment is null");
        } else if (this.bn) {
            homeFragment.i();
            ReleaseLogUtil.b("Login_MainActivity", "show HomeFragment float button");
        } else {
            ReleaseLogUtil.b("Login_MainActivity", "ad is still on, show no float button on HomeFragment");
        }
    }

    private void fe() {
        SportEntranceFragment sportEntranceFragment = this.dg;
        if (sportEntranceFragment == null) {
            ReleaseLogUtil.b("Login_MainActivity", "updateHomeFloatView return, mHomeFragment is null");
        } else if (this.bn) {
            sportEntranceFragment.b(false);
            ReleaseLogUtil.b("Login_MainActivity", "show mSportEntranceFragment float button");
        } else {
            ReleaseLogUtil.b("Login_MainActivity", "ad is still on, show no float button on HomeFragment");
        }
    }

    private void bc() {
        getWindow().clearFlags(1024);
        BaseActivity.setNavigationBarVisibility(this, 0);
        e(true);
        if (this.be && this.k != null) {
            if (this.av && BaseActivity.setAttributesShortOrDefault(0, this)) {
                this.av = false;
            }
            bk();
        }
        this.k = null;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        ReleaseLogUtil.b("Login_MainActivity", "onConfigurationChanged: ", configuration);
        gow gowVar = this.dv;
        if (gowVar != null) {
            gowVar.e();
        }
        e(false);
        gpa.aRi_(this);
        db();
        super.onConfigurationChanged(configuration);
    }

    private void db() {
        if (sbc.d()) {
            b(this.y);
        } else if (dzn.a() || (LoginInit.getInstance(this.y).getIsLogined() && dzn.e())) {
            eo();
        }
    }

    private void b(Context context) {
        ViewStub viewStub;
        boolean z = false;
        BaseActivity.setNavigationBarVisibility(this, 0);
        if (isInMultiWindowMode()) {
            LogUtil.c("Login_MainActivity", "isInMultiWindowMode is true");
            viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_beta_user_agreement_half_screen);
            z = true;
        } else {
            viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_beta_user_agreement);
        }
        this.cv.abK_(context, z, viewStub);
    }

    private void d(boolean z) {
        boolean z2;
        ViewStub viewStub;
        BaseActivity.setNavigationBarVisibility(this, 0);
        if (isInMultiWindowMode()) {
            LogUtil.c("Login_MainActivity", "isInMultiWindowMode is true");
            viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_startpage_half_screen);
            z2 = true;
        } else {
            LogUtil.c("Login_MainActivity", "isInMultiWindowMode is false");
            z2 = false;
            viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_startpage);
        }
        this.cv.abM_(z, this.cd, viewStub, z2);
    }

    private void dv() {
        ViewStub viewStub;
        ViewStub viewStub2;
        boolean z = false;
        BaseActivity.setNavigationBarVisibility(this, 0);
        if (isInMultiWindowMode()) {
            LogUtil.c("Login_MainActivity", "isInMultiWindowMode is true");
            z = true;
        }
        if (dzn.h()) {
            if (z) {
                viewStub2 = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_hong_kong_half_screen);
            } else {
                viewStub2 = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_hong_kong_startpage);
            }
            this.cv.abN_(viewStub2, this.cd, z);
            return;
        }
        if (z) {
            viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_overseas_half_screen);
        } else {
            viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_privacy_startpage_overseas);
        }
        this.cv.abL_(viewStub, this.cd, z);
    }

    private void at() {
        SportDataOutputApi sportDataOutputApi;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.dw.x();
        this.cd.j();
        boolean c2 = byq.c(this.y, this.x);
        this.be = c2;
        ReleaseLogUtil.b("Login_MainActivity", "gotoMain, mIsAvailableAds:", Boolean.valueOf(c2));
        if (this.be) {
            if (BaseActivity.setAttributesShortOrDefault(1, this)) {
                this.av = true;
            }
            this.bn = false;
            et();
        } else {
            ff();
            fe();
            e(0);
            ReleaseLogUtil.b("Login_MainActivity", "gotoMain, show StartPage");
            byq.a();
        }
        boolean o2 = Utils.o();
        boolean isLogined = LoginInit.getInstance(this.y).getIsLogined();
        if (!o2) {
            OperationFeatureReceiver.aur_(this.ar);
        }
        if (isLogined && fep.a()) {
            this.cd.d(true);
            qbd.a();
        }
        this.dl.setVisibility(8);
        this.dr.setVisibility(8);
        if (isLogined && AuthorizationUtils.a(this.y) && (sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)) != null && sportDataOutputApi.isSportServiceRunning()) {
            cas.d(cas.e());
        }
        LogUtil.c("Login_MainActivity", "gotoMain finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void et() {
        byq byqVar = new byq(this);
        this.k = byqVar;
        byqVar.j();
        this.k.f();
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.SportEntranceFragment.SportEntranceCallback
    public void onSportEntranceCallback() {
        if (bq()) {
            this.dr.setCurrentItem(2, false);
        } else {
            this.dr.setCurrentItem(3, false);
        }
        this.dl.setItemChecked(this.dr.getCurrentItem());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bo() {
        int i2;
        if (ai() || w() || ad() || x() || ae() || this.da != null || this.aw != -1 || isTaskRoot() || z() || p() || gnv.aPQ_(getIntent()) != -1 || gnv.aPO_(getIntent()) != null || y() || this.cs != null || "order".equals(this.aj) || this.ad != null || (i2 = this.cc) == 4) {
            return true;
        }
        LogUtil.a("Login_MainActivity", "isActivityStackRoot isTaskRoot: false! mLaunchSource : ", Integer.valueOf(i2));
        return false;
    }

    private boolean y() {
        return (this.df == null && i == null && this.ci == null) ? false : true;
    }

    private boolean p() {
        return (this.af == null && this.de == null && this.n == null) ? false : true;
    }

    private boolean z() {
        return (this.dc == null && this.cw == null && this.ck == null) ? false : true;
    }

    private boolean ae() {
        return this.ce || this.ca || this.bl || this.bz || this.by;
    }

    private boolean x() {
        return (this.dh == -1 && this.cy == -1) ? false : true;
    }

    private boolean ad() {
        return (j == null && l == null && this.dd == null) ? false : true;
    }

    private boolean w() {
        return (f == null && g == null && h == null) ? false : true;
    }

    private boolean ai() {
        return (this.ds == null && this.du == null && this.db == null) ? false : true;
    }

    private void ay() {
        LogUtil.c("Login_MainActivity", "handleLaunchFromSportingNotification handleClick");
        if (gtx.c(BaseApplication.getContext()).r() == 1) {
            gso.e().ab();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        MainInteractors mainInteractors;
        super.onNewIntent(intent);
        LogUtil.c("Login_MainActivity", "onNewIntent ", this);
        try {
            Bi_(intent);
            Parcelable parcelableExtra = intent.getParcelableExtra("schemeQrCodeUri");
            if ("DEVICE".equals(this.c) && this.dr != null) {
                e("DEVICE");
            } else if (!"ME".equals(this.c) || this.dr == null) {
                if (Constants.HOME.equals(this.c) && this.dr != null) {
                    this.by = true;
                    e(Constants.HOME);
                }
            } else {
                Bj_(intent);
            }
            if (parcelableExtra instanceof Uri) {
                l = (Uri) parcelableExtra;
            }
            if (this.bh && (mainInteractors = this.cd) != null) {
                mainInteractors.t();
            }
            Bx_(intent);
            Bv_(intent);
        } catch (BadParcelableException e2) {
            LogUtil.e("Login_MainActivity", "MainActivity BadParcelableException : ", e2.getMessage());
        }
        LogUtil.d("Login_MainActivity", "mSmartMsgId = ", Integer.valueOf(this.dj), ", mSmartMsgType = ", Integer.valueOf(this.dh), ", mSmartMsgContent = ", this.di, ", mProductName = ", this.cu, ", mDeviceType = ", Integer.valueOf(this.aa));
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "key_wether_to_auth");
        if (!TextUtils.isEmpty(this.dk) && "true".equals(b2)) {
            q();
            this.dk = "";
        }
        ac();
        if (!Utils.g()) {
            OperationFeatureReceiver.aur_(this.ar);
        }
        By_(intent);
        al();
        eu();
    }

    private void Bi_(Intent intent) {
        this.dj = intent.getIntExtra("health_smartmsg_id", -1);
        this.dh = intent.getIntExtra("health_smartmsg_type", -1);
        this.di = intent.getStringExtra("health_smartmsg_content");
        this.dk = nbr.crG_(intent);
        this.c = intent.getStringExtra(Constants.HOME_TAB_NAME);
        this.aa = intent.getIntExtra(DeviceCategoryFragment.DEVICE_TYPE, -1);
        this.cu = intent.getStringExtra("dname");
        this.bs = intent.getBooleanExtra("isPorc", false);
        this.bk = intent.getBooleanExtra("from_update_version", false);
        this.af = intent.getStringExtra("directConnectDevice");
        this.bh = intent.getBooleanExtra("from_oobe", false);
    }

    private void Bj_(Intent intent) {
        e("ME");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.bx) {
            this.mIsPageNeedShowMinibar = this.cn != this.ae;
        }
        super.onResume();
        this.bp = true;
        ixx.d().a(LoginInit.getInstance(this.y).getAccountInfo(1011));
        ixx.d().d(this.y, AnalyticsValue.HEALTH_SHOW_APP_PAGE_2050003.value(), new HashMap(), 0);
        if (CommonUtil.as()) {
            g();
        }
        this.f0do = System.currentTimeMillis();
        HealthViewPager healthViewPager = this.dr;
        if (healthViewPager == null) {
            Process.killProcess(Process.myPid());
            return;
        }
        if (!this.bi) {
            this.dl.setItemChecked(healthViewPager.getCurrentItem());
        }
        boolean bq = bq();
        b(!bq);
        as();
        OperationAdInteractor operationAdInteractor = this.r;
        if (operationAdInteractor != null) {
            operationAdInteractor.c();
        }
        DeviceFragment deviceFragment = this.z;
        if (deviceFragment != null) {
            deviceFragment.a();
        }
        MainInteractors mainInteractors = this.cd;
        if (mainInteractors != null) {
            mainInteractors.l();
        }
        s();
        an();
        if (b) {
            b = false;
        } else {
            t();
        }
        if (this.bk) {
            this.dr.setCurrentItem(0, false);
            this.dl.setItemChecked(this.dr.getCurrentItem());
        }
        c(bq);
        gou.aRh_(this.ar);
        ar();
        byu byuVar = this.ax;
        if (byuVar != null) {
            byuVar.b(this.dr.getCurrentItem());
        }
        ReleaseLogUtil.b("Login_MainActivity", "onResume finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void b(boolean z) {
        DiscoverFragment discoverFragment;
        if (z && this.dr.getCurrentItem() == 2 && (discoverFragment = this.ai) != null) {
            discoverFragment.setUserVisibleHint(true);
            if (this.bv) {
                this.ai.e(true);
                this.bv = false;
            }
        }
    }

    private void dg() {
        eoi c2 = eoi.c();
        if (nkr.d().cwW_() == null && c2.g() == 3 && !koq.b(c2.j())) {
            LogUtil.c("Login_MainActivity", "player is playing, init minibar:");
            nkr.d().cwX_(new MiniBottomPlayer(BaseApplication.getContext()));
            nkr.d().b(true);
            initMinibar();
        }
    }

    private void an() {
        if (this.cm == null || this.ag == null) {
            return;
        }
        LogUtil.c("Login_MainActivity", "dismissNoteDialog, noteDialog is not null, dismiss dialog first....");
        this.ag.d(this.cm);
        this.ag = null;
        this.cm = null;
        LogUtil.c("Login_MainActivity", "dismissNoteDialog, login once to check if 40....");
        MainInteractors mainInteractors = this.cd;
        if (mainInteractors != null) {
            mainInteractors.ab();
        }
    }

    private void as() {
        if (this.bo) {
            this.ar.sendEmptyMessage(8);
            this.ar.sendEmptyMessageDelayed(9, 2000L);
            this.ar.sendEmptyMessageDelayed(6300, 5000L);
            this.bo = false;
            return;
        }
        LogUtil.a("Login_MainActivity", "firstEntryOperation is other condition");
    }

    private void al() {
        if (this.af == null) {
            LogUtil.a("Login_MainActivity", "directConnectLogin mDirectConnectDetail is null");
        } else if (LoginInit.getInstance(this.y).isBrowseMode()) {
            LoginInit.getInstance(this.y).browsingToLogin(new IBaseResponseCallback() { // from class: bxz
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    MainActivity.this.e(i2, obj);
                }
            }, "");
        }
    }

    public /* synthetic */ void e(int i2, Object obj) {
        LogUtil.a("Login_MainActivity", "directConnectLogin browsingToLogin onResponse errorCode : ", Integer.valueOf(i2));
        if (i2 != 0) {
            this.af = null;
        }
    }

    private void eu() {
        if (l == null) {
            LogUtil.a("Login_MainActivity", "toCheckScanLogin sSchemeQrCodeUri is null");
        } else if (LoginInit.getInstance(this.y).isBrowseMode()) {
            LoginInit.getInstance(this.y).browsingToLogin(new IBaseResponseCallback() { // from class: byc
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    MainActivity.a(i2, obj);
                }
            }, "");
        }
    }

    public static /* synthetic */ void a(int i2, Object obj) {
        LogUtil.a("Login_MainActivity", "toCheckScanLogin onResponse errorCode : ", Integer.valueOf(i2));
        if (i2 != 0) {
            l = null;
        }
    }

    private void c(boolean z) {
        HealthViewPager healthViewPager;
        String str = this.ab;
        if (str == null || z) {
            return;
        }
        LogUtil.c("Login_MainActivity", "intellLifeHandover mCurrentFragmentName = ", str);
        if (!"DEVICE".equals(this.ab) || (healthViewPager = this.dr) == null) {
            return;
        }
        healthViewPager.setCurrentItem(3, false);
        this.dl.setItemChecked(this.dr.getCurrentItem());
    }

    private boolean bq() {
        if (Utils.o()) {
            return !OperationUtils.isOperation(LoginInit.getInstance(this.y).getAccountInfo(1010));
        }
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.c("Login_MainActivity", "onPause");
        super.onPause();
        this.bp = false;
        OperationAdInteractor operationAdInteractor = this.r;
        if (operationAdInteractor != null) {
            operationAdInteractor.g();
        }
        byu byuVar = this.ax;
        if (byuVar != null) {
            byuVar.b(-1);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        LogUtil.c("Login_MainActivity", "onStop");
        super.onStop();
        byq byqVar = this.k;
        if (byqVar != null) {
            byqVar.g();
        }
        if (!com.huawei.haf.application.BaseApplication.j()) {
            LogUtil.c("Login_MainActivity", "MainActivity_isForeground_stop");
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "health_click_home", Integer.toString(1), new StorageParams());
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "health_show_smartcard", "true", new StorageParams());
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "health_bi_opera", "1", new StorageParams());
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "health_report_click_home", Integer.toString(1), new StorageParams());
        }
        Handler handler = this.ar;
        if (handler != null) {
            handler.removeMessages(16);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        byq byqVar = this.k;
        if (byqVar != null) {
            byqVar.c();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void onTrimMemory(int i2, boolean z) {
        super.onTrimMemory(i2, z);
        Glide.get(this).trimMemory(i2);
        if (i2 == 20) {
            Glide.get(this).clearMemory();
        }
    }

    private void t() {
        LogUtil.c("Login_MainActivity", "enter checkAuthRelogin:");
    }

    private void s() {
        if (CommonUtil.z(this.y)) {
            ThirdPartyLoginManager.getInstance().queryUserInfo(new IBaseResponseCallback() { // from class: com.huawei.health.MainActivity.21
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.c("Login_MainActivity", "checkIsInstallHmsApk queryUserInfo errorCode = ", Integer.valueOf(i2));
                }
            }, false);
            return;
        }
        MainInteractors mainInteractors = this.cd;
        if (mainInteractors != null) {
            mainInteractors.s();
        }
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10015), "key_ui_login_exit_hms_apk");
        LogUtil.c("Login_MainActivity", "accountmigrate: checkIsInstallHmsApk() isDownLoadHmsApk = ", b2);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.a("Login_MainActivity", "checkIsInstallHmsApk() isDownLoadHmsApk is empty");
            return;
        }
        if ("1".equals(b2)) {
            if (AccountInteractors.isInstallHwIdApk(this.y)) {
                LogUtil.c("Login_MainActivity", "accountmigrate: checkIsInstallHmsApk() isDownLoadHmsApk = 1,install HWIdAPK");
                this.ar.sendEmptyMessage(5016);
                return;
            } else {
                LogUtil.c("Login_MainActivity", "accountmigrate: checkIsInstallHmsApk() isDownLoadHmsApk = 1,not install HWIdAPK");
                this.ar.sendEmptyMessage(FitnessStatusCodes.UNSUPPORTED_ACCOUNT);
                return;
            }
        }
        if ("2".equals(b2)) {
            if (AccountInteractors.isInstallHwIdApk(this.y)) {
                LogUtil.c("Login_MainActivity", "accountmigrate: checkIsInstallHmsApk() isDownLoadHmsApk = 2, install HWIdAPK");
                this.ar.sendEmptyMessage(5017);
                return;
            } else {
                LogUtil.c("Login_MainActivity", "accountmigrate: checkIsInstallHmsApk() isDownLoadHmsApk = 2,not install HWIdAPK");
                this.ar.sendEmptyMessage(FitnessStatusCodes.DISABLED_BLUETOOTH);
                return;
            }
        }
        LogUtil.a("Login_MainActivity", "accountmigrate: checkIsInstallHmsApk() is other condition");
    }

    private void bm() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        SharedPreferenceManager.e(this.y, Integer.toString(10000), "hw_health_start_count_key", String.valueOf(CommonUtil.h(SharedPreferenceManager.b(this.y, Integer.toString(10000), "hw_health_start_count_key")) + 1), (StorageParams) null);
        br();
        if (!this.be) {
            bk();
            ReleaseLogUtil.b("Login_MainActivity", "initOtherLoginedData finished");
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        dsl.k();
        ReleaseLogUtil.b("Login_MainActivity", "initHealthModel finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime2));
        DeviceConfigInit.create();
        qup.e().b();
        nsn.d();
        ReleaseLogUtil.b("Login_MainActivity", "initLoginedData finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void em() {
        if (CommonUtil.bt()) {
            LogUtil.c("Login_MainActivity", "initLoginedData, isRooted phone");
            this.dw.f(this.y);
        }
    }

    private void bk() {
        if (this.bj) {
            return;
        }
        this.bj = !LoginInit.getInstance(this.y).isBrowseMode();
        ExecutorService executorService = this.al;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: bxu
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.this.d();
                }
            });
        }
        this.ab = null;
    }

    public /* synthetic */ void d() {
        af();
        Handler handler = this.ar;
        MainInteractors mainInteractors = this.cd;
        if (handler == null || mainInteractors == null) {
            return;
        }
        if (!this.bw) {
            ef();
        }
        this.dw.aQQ_(this.ar);
        if (!Utils.g() || OperationUtils.isOperation(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010))) {
            BD_(handler);
            SharedPreferenceManager.e(this.y, Integer.toString(10009), "health_is_first_in", "health_is_first_in", new StorageParams());
        }
        handler.sendEmptyMessage(11);
    }

    private void BD_(Handler handler) {
        if (this.dy == null) {
            this.dy = new WeiXinInteractor(this.y);
        }
        boolean c2 = this.dy.c();
        LogUtil.c("Login_MainActivity", "initLoginedData queryIsShowBindDialog : ", Boolean.valueOf(c2));
        if (c2) {
            handler.sendEmptyMessage(204);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ef() {
        LogUtil.c("Login_MainActivity", "enter showPermissionDialog mIsOnResume = ", Boolean.valueOf(this.bp));
        if (this.dh != 3) {
            try {
                if (this.bp) {
                    this.cd.e();
                }
            } catch (Exception unused) {
                LogUtil.c("Login_MainActivity", "initOtherLoginedData mainInteractors addPermissions exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dz() {
        synchronized (f2175a) {
            if (Utils.o() && (!Utils.o() || (Utils.i() && !OperationUtils.switchToMarketingTwo()))) {
                LogUtil.c("Login_MainActivity", "showAdvDialog mIsShowedAd = ", Boolean.valueOf(this.cb));
                if (this.cb) {
                    return;
                }
                if (LoginInit.getInstance(this.y).isKidAccount()) {
                    LogUtil.c("Login_MainActivity", "showAdvDialog isKidAccount");
                    return;
                }
                if ("true".equals(SharedPreferenceManager.b(this.y, Integer.toString(10000), "sync_cloud_data_show_process_flag"))) {
                    LogUtil.c("Login_MainActivity", "showAdvDialog progress dialog is showing");
                    return;
                }
                String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10009), "health_is_first_in");
                LogUtil.c("Login_MainActivity", "showAdvDialog isFirstIn = ", b2);
                if ("health_is_first_in".equals(b2)) {
                    this.cb = true;
                }
                ExecutorService executorService = this.al;
                if (executorService != null) {
                    executorService.execute(new Runnable() { // from class: com.huawei.health.MainActivity.23
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.er();
                        }
                    });
                    return;
                }
                return;
            }
            LogUtil.c("Login_MainActivity", "showAdvDialog not oversea or isNoCloudVersion or switchToMarketingTwo");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void er() {
        if (this.dy == null) {
            this.dy = new WeiXinInteractor(this.y);
        }
        boolean c2 = this.dy.c();
        LogUtil.c("Login_MainActivity", "showAdvDialog queryIsShowBindDialog : ", Boolean.valueOf(c2));
        if (!c2 || OperationUtils.isOperation(LoginInit.getInstance(this.y).getAccountInfo(1010))) {
            bt();
        }
    }

    private void bt() {
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10009), "health_is_first_in");
        LogUtil.c("Login_MainActivity", "judgeShowAdvDialog isFirstIn = ", b2);
        if ("health_is_first_in".equals(b2)) {
            LogUtil.c("Login_MainActivity", "judgeShowAdvDialog getHomeDialogMessage after wechat");
            OperationAdInteractor operationAdInteractor = this.r;
            if (operationAdInteractor != null) {
                operationAdInteractor.aaP_(this.ar);
                synchronized (f2175a) {
                    this.cb = true;
                }
                return;
            }
            LogUtil.c("Login_MainActivity", "judgeShowAdvDialog adInteactor is null");
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        try {
            super.onRestoreInstanceState(bundle);
        } catch (Exception e2) {
            ReleaseLogUtil.c("Login_MainActivity", "onRestoreInstanceState with exception", LogAnonymous.b((Throwable) e2));
        }
    }

    private void br() {
        ReleaseLogUtil.b("Login_MainActivity", "initViewFrame");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!this.be) {
            qbd.a(false);
        }
        byq byqVar = this.k;
        if (byqVar != null) {
            byqVar.f();
        }
        byd.b(this);
        new gol().b(this.y);
        if (Utils.i()) {
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            if (csb.b()) {
                csb.a().e();
            }
            ReleaseLogUtil.b("Login_MainActivity", "UpdateDeviceControl finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime2));
            dy();
        }
        if (this.k == null) {
            ReleaseLogUtil.b("Login_MainActivity", "initViewFrame hide StartPage");
            bc();
        }
        this.dr.setVisibility(0);
        this.dl.setVisibility(0);
        boolean bq = bq();
        boolean aa = aa();
        dh();
        d(bq, aa);
        d(8);
        if (this.bw) {
            ReleaseLogUtil.b("Login_MainActivity", "initViewFrame loadGuideView");
            cx();
        } else {
            bp();
            nmh.d(ehu.b);
            ReleaseLogUtil.b("Login_MainActivity", "initViewFrame finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private void cx() {
        if (this.cg == null) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.viewstub_mainui_update_guide);
            if (viewStub == null) {
                return;
            }
            View inflate = viewStub.inflate();
            if (!(inflate instanceof ViewGroup)) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) inflate;
            this.cg = viewGroup;
            HomeFragment homeFragment = this.bc;
            if (homeFragment != null) {
                homeFragment.cWo_(viewGroup);
            }
        }
        gpa.aRn_(this.cg, this.dl.getChildCount(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bp() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        dw();
        bf();
        if (this.dr.getOffscreenPageLimit() <= 1) {
            this.dr.setOffscreenPageLimit(1);
        }
        if (!bq()) {
            dr();
        }
        ca();
        dt();
        ds();
        if (CommonUtil.as()) {
            this.cd.u();
        }
        boolean aa = aa();
        HealthViewPager healthViewPager = this.dr;
        if (healthViewPager != null && aa) {
            healthViewPager.setCurrentItem(1, false);
            this.dl.setItemChecked(this.dr.getCurrentItem());
        }
        this.dk = "";
        if (!this.cb && this.bf) {
            a();
        }
        em();
        ReleaseLogUtil.b("Login_MainActivity", "initViewsAferGuideViewHide finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void Bw_(Bundle bundle, boolean z, boolean z2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.bc == null) {
            HomeFragment homeFragment = new HomeFragment();
            this.bc = homeFragment;
            boolean z3 = !z2;
            homeFragment.c(z3);
            this.bc.setArguments(bundle);
            this.bc.a(z3);
            ff();
            this.an.add(this.bc);
        }
        Utils.o();
        if (this.dg == null && !SportSupportUtil.g()) {
            SportEntranceFragment sportEntranceFragment = new SportEntranceFragment();
            this.dg = sportEntranceFragment;
            sportEntranceFragment.dgj_(this.ak);
            this.an.add(this.dg);
            fe();
        }
        if (!z && this.ai == null) {
            DiscoverFragment discoverFragment = new DiscoverFragment(this.dl);
            this.ai = discoverFragment;
            discoverFragment.setArguments(bundle);
            this.an.add(this.ai);
        }
        if (this.z == null && !EnvironmentInfo.k()) {
            DeviceFragment deviceFragment = new DeviceFragment(this.dl);
            this.z = deviceFragment;
            this.an.add(deviceFragment);
        }
        if (this.ct == null) {
            PersonalCenterFragment personalCenterFragment = new PersonalCenterFragment(this.dl);
            this.ct = personalCenterFragment;
            personalCenterFragment.setArguments(bundle);
            this.an.add(this.ct);
        }
        LogUtil.c("Login_MainActivity", "initFragments finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void d(boolean z, boolean z2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int r = nsn.r(this.y);
        Window window = getWindow();
        if (window != null) {
            ReleaseLogUtil.b("Login_MainActivity", "initViewPager, statusBarColor is : ", Integer.valueOf(window.getStatusBarColor()), " statusBarHeight is : ", Integer.valueOf(r));
        }
        AppBundle.b().loadResources(this);
        LogUtil.c("Login_MainActivity", "initViewPager loadResources time cost：", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        Bundle bundle = new Bundle();
        bundle.putInt("statusBarHeight", r);
        bundle.putBoolean(BundleTransferdDataContants.IS_SHOW_GUIDE, this.bw);
        bundle.putString("pairGuideSelectAddress", this.cq);
        ReleaseLogUtil.b("Login_MainActivity", "start to load some fragments");
        int size = this.an.size();
        Bw_(bundle, z, z2);
        if (this.ax == null) {
            this.ax = new byu(getSupportFragmentManager(), this.an);
            this.dr.setOffscreenPageLimit(1);
            this.dr.setAdapter(this.ax);
            this.dr.addOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: com.huawei.health.MainActivity.26
                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i2) {
                }

                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f2, int i3) {
                }

                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    ReleaseLogUtil.b("Login_MainActivity", "main page been selected: " + i2);
                    MainActivity.this.ax.b(i2);
                }
            });
            a(z);
        } else if (this.an.size() != size) {
            ReleaseLogUtil.a("Login_MainActivity", "view pager size changed, before: ", Integer.valueOf(size), ", now we have: ", Integer.valueOf(this.an.size()));
            this.ax.c(this.an);
        }
        LogUtil.c("Login_MainActivity", "initViewPager finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void dw() {
        this.dl.setBottomNavListener(new HwBottomNavigationView.BottomNavListener() { // from class: com.huawei.health.MainActivity.28
            @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavListener
            public void onBottomNavItemReselected(MenuItem menuItem, int i2) {
            }

            @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavListener
            public void onBottomNavItemSelected(MenuItem menuItem, int i2) {
                ReleaseLogUtil.b("Login_MainActivity", "main tab been selected:" + ((Object) menuItem.getTitle()));
                MainActivity.this.cn = i2;
                if (!MainActivity.this.at) {
                    MainActivity.this.b(i2);
                }
                if (i2 == MainActivity.this.ae && MainActivity.this.bx) {
                    nsy.cMu_(MainActivity.this.cj, MainActivity.this.m);
                }
                if (i2 == 1) {
                    MainActivity.this.dq();
                } else {
                    MainActivity.this.dr.setCurrentItem(i2, false);
                }
                MainActivity.this.c(i2);
            }

            @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavListener
            public void onBottomNavItemUnselected(MenuItem menuItem, int i2) {
                ReleaseLogUtil.b("Login_MainActivity", "main tab been unselected:" + ((Object) menuItem.getTitle()));
            }
        });
        Window window = getWindow();
        if (window == null) {
            ReleaseLogUtil.a("Login_MainActivity", "setTabsListener window is null");
            return;
        }
        if (this.bd == null) {
            ReleaseLogUtil.b("Login_MainActivity", "setTabsListener mInnerAccessibilityNodeInfoCallback is null");
            this.bd = new a(this);
        }
        jcf.bEt_(window.getDecorView(), this.bd);
    }

    /* loaded from: classes3.dex */
    static class a implements AccessibilityNodeInfoCallback {
        private final WeakReference<MainActivity> b;

        a(MainActivity mainActivity) {
            this.b = new WeakReference<>(mainActivity);
        }

        @Override // com.huawei.hwcommonmodel.accessibility.AccessibilityNodeInfoCallback
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            MainActivity mainActivity = this.b.get();
            if (mainActivity != null && !mainActivity.isDestroyed() && !mainActivity.isFinishing() && accessibilityNodeInfoCompat != null) {
                int d = mainActivity.d("DEVICE");
                int d2 = mainActivity.d("ME");
                LogUtil.c("Login_MainActivity", "InnerAccessibilityNodeInfoCallback onInitializeAccessibilityNodeInfo indexOfDevice ", Integer.valueOf(d), " indexOfMe ", Integer.valueOf(d2), " activity.mNowBottomItemIndex ", Integer.valueOf(mainActivity.cn));
                if (mainActivity.cn != d) {
                    if (mainActivity.cn == d2) {
                        jcf.bEs_(accessibilityNodeInfoCompat, mainActivity.findViewById(R.id.personal_scroll));
                    }
                } else {
                    jcf.bEs_(accessibilityNodeInfoCompat, mainActivity.findViewById(R.id.device_health_scrollview));
                }
                jcf.bEs_(accessibilityNodeInfoCompat, mainActivity.findViewById(R.id.hw_main_tabs_layout));
                return;
            }
            LogUtil.a("Login_MainActivity", "InnerAccessibilityNodeInfoCallback onInitializeAccessibilityNodeInfo activity ", mainActivity, " infoCompat ", accessibilityNodeInfoCompat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        if (this.dr.getCurrentItem() >= 2 || i2 >= 2) {
            ReleaseLogUtil.b("Login_MainActivity", "click too fast, processing lazyLoadAllFragment");
            cw();
        } else if (i2 == 0) {
            ReleaseLogUtil.b("Login_MainActivity", "from sport to health,should forceLoad health");
            this.bc.tryToLoad(true);
        }
    }

    private void bf() {
        if (TextUtils.isEmpty(this.dk)) {
            if (this.bh) {
                bd();
                return;
            } else {
                if (this.bq) {
                    return;
                }
                LogUtil.c("Login_MainActivity", "handleShortcutsData, set selected item to HomeFragment");
                this.dr.setCurrentItem(0, false);
                this.dl.setItemChecked(this.dr.getCurrentItem());
                return;
            }
        }
        q();
    }

    private void dr() {
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10009), "health_is_first_in_home");
        boolean isFinishing = isFinishing();
        LogUtil.d("Login_MainActivity", "setFirstInSpValue isFirstInHome = ", b2, "isFinishing = ", Boolean.valueOf(isFinishing));
        if ("health_is_first_in_home".equals(b2) || isFinishing) {
            return;
        }
        SharedPreferenceManager.e(this.y, Integer.toString(10009), "health_is_first_in_home", "health_is_first_in_home", new StorageParams());
    }

    private void ca() {
        boolean o2 = Utils.o();
        g(o2);
        f(o2);
        ec();
        scn.a((WeakReference<Context>) new WeakReference(this.y));
        gnv.aQq_(o2, this.bg, this.ar);
    }

    private void ec() {
        if (au() <= System.currentTimeMillis()) {
            LogUtil.c("Login_MainActivity", "showEfficientRest rest is over");
            return;
        }
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "sp_key_briefs_is_resting");
        try {
            if (TextUtils.isEmpty(b2) || !Boolean.parseBoolean(b2)) {
                return;
            }
            AppRouter.b("/PluginSleepBriefs/EfficientRestActivity").c(this.y);
        } catch (NumberFormatException e2) {
            LogUtil.e("Login_MainActivity", "showEfficientRest exception : ", e2.getMessage());
        }
    }

    private long au() {
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "sp_key_briefs_over_time");
        if (TextUtils.isEmpty(b2)) {
            return 0L;
        }
        return CommonUtils.g(b2);
    }

    private void dt() {
        if (!Utils.f()) {
            this.ar.removeMessages(4024);
            this.ar.sendEmptyMessageDelayed(4024, 3000L);
        }
        Handler handler = this.ar;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(MainLoginCallBack.MSG_HMS_VERSION_ERROR, 1000L);
            this.ar.sendEmptyMessageDelayed(8002, 900L);
            this.ar.sendEmptyMessageDelayed(8001, 1000L);
            this.ar.sendEmptyMessageDelayed(16, 5000L);
        }
    }

    private void ds() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.q.k() != 0) {
            long j2 = this.co;
            if (j2 != 0) {
                long j3 = currentTimeMillis - j2;
                if (j3 <= 8000) {
                    this.cd.a(j3);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
    
        r0 = health.compact.a.SharedPreferenceManager.b(r5.y, java.lang.Integer.toString(20002), "EXIT_APP_AT_SPORT_TAB");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean aa() {
        /*
            r5 = this;
            boolean r0 = defpackage.gww.c()
            r1 = 0
            if (r0 != 0) goto L66
            java.lang.String r0 = r5.dk
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L66
            android.content.Context r0 = r5.y
            r2 = 20002(0x4e22, float:2.8029E-41)
            java.lang.String r2 = java.lang.Integer.toString(r2)
            java.lang.String r3 = "EXIT_APP_AT_SPORT_TAB"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.b(r0, r2, r3)
            if (r0 == 0) goto L66
            boolean r2 = r0.isEmpty()     // Catch: java.lang.NumberFormatException -> L41
            if (r2 != 0) goto L66
            boolean r2 = java.lang.Boolean.parseBoolean(r0)     // Catch: java.lang.NumberFormatException -> L41
            if (r2 == 0) goto L66
            boolean r2 = defpackage.gwg.h()     // Catch: java.lang.NumberFormatException -> L41
            if (r2 != 0) goto L3f
            android.content.Context r2 = r5.y     // Catch: java.lang.NumberFormatException -> L41
            boolean r2 = defpackage.gwe.d(r2)     // Catch: java.lang.NumberFormatException -> L41
            if (r2 != 0) goto L3f
            boolean r0 = health.compact.a.Utils.o()     // Catch: java.lang.NumberFormatException -> L41
            if (r0 != 0) goto L40
        L3f:
            r1 = 1
        L40:
            return r1
        L41:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "numberFormatException, src: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = ", error: "
            r3.append(r0)
            java.lang.String r0 = r2.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r2 = "Login_MainActivity"
            health.compact.a.LogUtil.c(r2, r0)
        L66:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.MainActivity.aa():boolean");
    }

    private void bd() {
        if ("DISCOVER".equals(this.c) && this.dr != null) {
            e("DISCOVER");
        } else {
            if (!"SPORT".equals(this.c) || this.dr == null) {
                return;
            }
            e("SPORT");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void q() {
        char c2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.dr == null || this.dl == null) {
            LogUtil.a("Login_MainActivity", "checkJumpFragmentAndActivity, mViewPager == null || tabs == null");
            return;
        }
        if (!gww.c()) {
            nbr.e(this.y, this.dk);
            String str = this.dk;
            str.hashCode();
            switch (str.hashCode()) {
                case -1638419013:
                    if (str.equals("SC_KAKA")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1638359389:
                    if (str.equals("SC_MALL")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -470109465:
                    if (str.equals("SC_EXERCISE")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -353311597:
                    if (str.equals("SC_FA_CARD_MAIN")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 52485042:
                    if (str.equals("SC_FA_CARD_EXERCISE")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1535953701:
                    if (str.equals("SC_DEVICE")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                sqf.b(this.y);
            } else if (c2 != 1) {
                if (c2 != 2) {
                    if (c2 == 3) {
                        this.dl.setItemChecked(0);
                    } else if (c2 != 4) {
                        if (c2 == 5) {
                            if (bq()) {
                                this.dr.setCurrentItem(2, false);
                            } else {
                                this.dr.setCurrentItem(3, false);
                            }
                            this.dl.setItemChecked(this.dr.getCurrentItem());
                        } else {
                            LogUtil.a("Login_MainActivity", "checkJumpFragmentAndActivity, Shortcuts id is not support!");
                        }
                    }
                }
                if (!SportSupportUtil.g()) {
                    this.dl.setItemChecked(1);
                    r();
                } else {
                    this.dl.setItemChecked(0);
                }
            } else {
                sqf.e(this.y);
            }
        }
        LogUtil.c("Login_MainActivity", "checkJumpFragmentAndActivity finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void r() {
        if (nsn.ae(this.y)) {
            Bundle bundle = new Bundle();
            bundle.putString("SKIP_ALL_COURSE_KEY", "");
            AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).c(this.y);
            overridePendingTransition(R.anim._2130771980_res_0x7f01000c, R.anim.fade_out);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dq() {
        this.bi = true;
        if (Utils.o()) {
            v();
            gvy.b(this.y);
        } else {
            gvy.e(this.y);
            u();
        }
        ObserverManagerUtil.c("RUN_PLAN_UPDATE_HELLO_TIME", "");
    }

    private void v() {
        if (!CommonUtil.bh() || (gwg.h() && !gwe.d(this.y))) {
            nrw.b(this.y, 2);
            bj();
            ei();
            ba();
            return;
        }
        if (!gwg.h() && gwe.d(this.y)) {
            nrw.b(this.y, 3);
            if (!gwg.h(this.y)) {
                bj();
                ej();
            }
            ba();
            return;
        }
        if (!gwg.h() && !gwe.d(this.y)) {
            nrw.b(this.y, -1);
            eh();
            return;
        }
        nrw.b(this.y);
        if (nrw.e()) {
            if (gwg.j(this.y)) {
                el();
                return;
            }
            if (bv()) {
                bj();
                this.u.show();
                ba();
                return;
            } else {
                if (bs()) {
                    bj();
                    this.w.show();
                    ba();
                    return;
                }
                ba();
                return;
            }
        }
        if (nrw.c()) {
            if (gwg.f(this.y)) {
                el();
                return;
            } else {
                ba();
                return;
            }
        }
        LogUtil.c("Login_MainActivity", "checkOverseaMapType, auto map type");
        ba();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ei() {
        if (bv() || bu()) {
            this.u.show();
        } else if (bs()) {
            this.w.show();
        } else {
            LogUtil.c("Login_MainActivity", "showNotSupportGoogleDialog, No Dialog show");
        }
    }

    private void eh() {
        ba();
        if (SportSupportUtil.g()) {
            return;
        }
        new NoTitleCustomAlertDialog.Builder(this.y).e(this.y.getString(R.string._2130843247_res_0x7f02166f)).czC_(R.string._2130843855_res_0x7f0218cf, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    public void a() {
        if (Utils.o() && !Utils.i()) {
            LogUtil.c("Login_MainActivity", "checkSyncStatus isNoCloudVersion");
            return;
        }
        MainInteractors mainInteractors = this.cd;
        if (mainInteractors != null && mainInteractors.k()) {
            LogUtil.c("Login_MainActivity", "checkSyncStatus and showAdvDialog");
            dz();
        } else {
            HiHealthManager.d(this.y).checkDataStatus(HiSyncType.b(), new j(this));
        }
    }

    private boolean bv() {
        return !cub.g(this.y) && nrw.e(this.y) && gyg.b(this.y);
    }

    private boolean bu() {
        return nrw.a(this.y) == 1 && gyg.b(this.y) && nrw.e(this.y) && !cub.g(this.y);
    }

    private boolean bs() {
        if (nrw.a(this.y) != 1) {
            return !Utils.o() && nrw.a(this.y) == 0 && cub.g(this.y);
        }
        return true;
    }

    private void a(boolean z) {
        NavigationHelper navigationHelper;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.dl.c();
        a(R.string._2130843252_res_0x7f021674, R.drawable._2131430805_res_0x7f0b0d95);
        int i2 = R.drawable._2131430803_res_0x7f0b0d93;
        if (!z) {
            if (!SportSupportUtil.g()) {
                if (LanguageUtil.bc(this.y)) {
                    i2 = R.drawable._2131430804_res_0x7f0b0d94;
                }
                a(R.string._2130837618_res_0x7f020072, i2);
                this.ae = 2;
            } else {
                this.ae = 1;
            }
            this.ai.c(this.ae);
            ek();
        } else {
            this.ae = -1;
            if (!SportSupportUtil.g()) {
                if (LanguageUtil.bc(this.y)) {
                    i2 = R.drawable._2131430804_res_0x7f0b0d94;
                }
                a(R.string._2130837618_res_0x7f020072, i2);
            }
        }
        if (!EnvironmentInfo.k()) {
            a(R.string.IDS_device_title_use, R.drawable.main_device_drawable);
        }
        a(R.string._2130837626_res_0x7f02007a, R.drawable._2131430806_res_0x7f0b0d96);
        PersonalCenterFragment personalCenterFragment = this.ct;
        if (personalCenterFragment != null) {
            personalCenterFragment.d(this.dl);
        }
        this.dl.setItemChecked(this.dr.getCurrentItem());
        if (this.ai != null && (navigationHelper = this.ch) != null) {
            navigationHelper.b(this.dl);
        }
        LogUtil.c("Login_MainActivity", "initTabsMenus finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void ek() {
        boolean b2 = gpo.b();
        this.bx = b2;
        if (b2) {
            a(R.string._2130845630_res_0x7f021fbe, R.drawable._2131430807_res_0x7f0b0d97);
            if (this.cj == null) {
                this.cj = Bg_();
            }
            ed();
            return;
        }
        a(R.string._2130839532_res_0x7f0207ec, R.drawable._2131430802_res_0x7f0b0d92);
    }

    private void ed() {
        if (TextUtils.isEmpty(SharedPreferenceManager.b(this.y, Integer.toString(10000), "show_member_tab"))) {
            this.dl.c(this.ae, true);
        } else {
            this.dl.c(this.ae, false);
        }
    }

    private void a(int i2, int i3) {
        this.dl.dZI_(i2, getResources().getDrawable(i3), false);
    }

    private void g(boolean z) {
        if (z) {
            LogUtil.c("Login_MainActivity", "showRecommentDialog is Oversea version");
            return;
        }
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "show_product_recomment");
        String b3 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "show_personalized_recomment");
        c("show_product_recomment", b2);
        c("show_personalized_recomment", b3);
    }

    private void c(String str, String str2) {
        str.hashCode();
        if (str.equals("show_personalized_recomment")) {
            if (TextUtils.isEmpty(str2)) {
                Context context = this.y;
                String num = Integer.toString(10000);
                long currentTimeMillis = System.currentTimeMillis();
                SharedPreferenceManager.e(context, num, "show_personalized_recomment", String.valueOf(currentTimeMillis), new StorageParams());
                return;
            }
            d(str2, this.y.getString(R.string._2130845468_res_0x7f021f1c), "show_personalized_recomment", "personalized_recommend", this.y.getString(R.string._2130842134_res_0x7f021216));
            return;
        }
        if (str.equals("show_product_recomment")) {
            if (TextUtils.isEmpty(str2)) {
                Context context2 = this.y;
                String num2 = Integer.toString(10000);
                long currentTimeMillis2 = System.currentTimeMillis();
                SharedPreferenceManager.e(context2, num2, "show_product_recomment", String.valueOf(currentTimeMillis2), new StorageParams());
                return;
            }
            d(str2, this.y.getString(R.string._2130845353_res_0x7f021ea9), "show_product_recomment", "health_product_recommend", this.y.getString(R.string._2130845350_res_0x7f021ea6));
        }
    }

    private void d(String str, String str2, String str3, String str4, String str5) {
        long j2;
        try {
            j2 = Long.parseLong(str);
        } catch (Exception unused) {
            LogUtil.e("Login_MainActivity", "recommentDialogShow parseLong Exception");
            j2 = 0;
        }
        if (j2 != 0) {
            float currentTimeMillis = (System.currentTimeMillis() - j2) / 60000.0f;
            LogUtil.c("Login_MainActivity", "recommentDialogShow minutes = ", Float.valueOf(currentTimeMillis));
            if (currentTimeMillis < 10080.0f) {
                return;
            }
            String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), str4);
            gmz d2 = gmz.d();
            if ("1".equals(b2)) {
                return;
            }
            if ("personalized_recommend".equals(str4) && TextUtils.isEmpty(b2)) {
                return;
            }
            CustomTextAlertDialog a2 = b(d2, str2, str5, str4).a();
            a2.setCancelable(false);
            a2.show();
            SharedPreferenceManager.e(this.y, Integer.toString(10000), str3, String.valueOf(0), new StorageParams());
        }
    }

    private CustomTextAlertDialog.Builder b(final gmz gmzVar, String str, String str2, final String str3) {
        return new CustomTextAlertDialog.Builder(this).b(str2).e(str).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.a(gmzVar, str3, true, "1");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.a(gmzVar, str3, false, "0");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(gmz gmzVar, String str, boolean z, String str2) {
        if ("health_product_recommend".equals(str)) {
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "health_product_recommend", str2, new StorageParams());
            gmzVar.c(10, z, String.valueOf(10), (IBaseResponseCallback) null);
        } else if ("personalized_recommend".equals(str)) {
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "personalized_recommend", str2, new StorageParams());
            gmzVar.c(12, z, String.valueOf(12), (IBaseResponseCallback) null);
        } else {
            LogUtil.a("Login_MainActivity", "saveRecommendSwitchStatus is other condition");
        }
    }

    private void f(boolean z) {
        long j2;
        if (!z) {
            LogUtil.c("Login_MainActivity", "showOverseaOperationDialog Is Chinese version");
            return;
        }
        if (!Utils.i()) {
            LogUtil.c("Login_MainActivity", "showOverseaOperationDialog Not allowed to login version");
            return;
        }
        if (LoginInit.getInstance(this.y).isKidAccount()) {
            LogUtil.c("Login_MainActivity", "showOverseaOperationDialog Is isKidAccount version");
            return;
        }
        if (!OperationUtils.isOperation(LoginInit.getInstance(this.y).getAccountInfo(1010))) {
            LogUtil.c("Login_MainActivity", "showOverseaOperationDialog isOperation is false");
            return;
        }
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "show_oversea");
        if (TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "show_oversea", String.valueOf(System.currentTimeMillis()), new StorageParams());
            return;
        }
        try {
            j2 = Long.parseLong(b2);
        } catch (NumberFormatException unused) {
            LogUtil.e("Login_MainActivity", "showOverseaOperationDialog NumberFormatException");
            j2 = 0;
        }
        if (j2 != 0) {
            float currentTimeMillis = (System.currentTimeMillis() - j2) / 60000.0f;
            LogUtil.c("Login_MainActivity", "showOverseaOperationDialog minutes = ", Float.valueOf(currentTimeMillis));
            if (currentTimeMillis >= 10080.0f && !com.huawei.health.messagecenter.model.CommonUtil.isSystemBarNoticeSwitchOnOrDefault(this.y)) {
                l().show();
                SharedPreferenceManager.e(this.y, Integer.toString(10000), "show_oversea", String.valueOf(0), new StorageParams());
            }
        }
    }

    private CustomTextAlertDialog l() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R.string._2130837782_res_0x7f020116).e(this.y.getString(R.string._2130841240_res_0x7f020e98)).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.huawei.health.messagecenter.model.CommonUtil.setPushSwitch(MainActivity.this.y, true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.huawei.health.messagecenter.model.CommonUtil.setPushSwitch(MainActivity.this.y, false);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        return a2;
    }

    private void c(String str) {
        String accountInfo = LoginInit.getInstance(this.y).getAccountInfo(1011);
        o = accountInfo;
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.d("Login_MainActivity", "handleQrCode handleMessage MESSAGE_ID_GETQRCODETICKET get unvalid userId");
            sbk.a(this.y).c();
        } else {
            LogUtil.d("Login_MainActivity", "handleQrCode MESSAGE_ID_GETQRCODETICKET userid is ", o);
            kor.a().c(new c(str, o, this.y));
        }
    }

    /* loaded from: classes3.dex */
    static class c implements IBaseResponseCallback {
        private String c;
        private WeakReference<Context> d;
        private String e;

        c(String str, String str2, Context context) {
            this.e = str;
            this.c = str2;
            this.d = new WeakReference<>(context);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Context context = this.d.get();
            if (context == null) {
                LogUtil.a("Login_MainActivity", " GetFitnessDataCallback: context is null");
                return;
            }
            int i2 = 0;
            if (i == 0 && (obj instanceof List)) {
                List list = (List) obj;
                int i3 = 0;
                while (true) {
                    if (i3 >= list.size()) {
                        break;
                    }
                    FitnessTotalData fitnessTotalData = (FitnessTotalData) list.get(i3);
                    if (fitnessTotalData.getSportType() == 221) {
                        i2 = fitnessTotalData.getSteps();
                        break;
                    }
                    i3++;
                }
            }
            LogUtil.d("Login_MainActivity", "GetFitnessDataCallback wechat_total_step = ", Integer.valueOf(i2));
            String str = this.e + "#" + this.c + "#" + i2;
            LogUtil.d("Login_MainActivity", "MESSAGE_ID_GETQRCODETICKET jumpToHwPublic trdTicket = ", str);
            sbk.a(context).a(str);
            sbk.a(context).c();
        }
    }

    @Override // com.huawei.hwcommonmodel.application.RunningForegroundListener
    public void goForegroundTime(long j2) {
        this.ap = j2;
        this.s += j2 - this.am;
    }

    @Override // com.huawei.hwcommonmodel.application.RunningForegroundListener
    public void goBackgroundTime(long j2) {
        this.cl += j2 - this.ap;
    }

    /* loaded from: classes3.dex */
    public static class h extends BaseHandler<MainActivity> {
        public h(Looper looper, MainActivity mainActivity) {
            super(looper, mainActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: BF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MainActivity mainActivity, Message message) {
            if (mainActivity == null || message == null) {
                LogUtil.a("Login_MainActivity", "handleMessageWhenReferenceNotNull obj or msg is null.");
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            LogUtil.c("Login_MainActivity", "receive msg:" + message.what);
            mainActivity.Bp_(mainActivity, message);
            if (message.what == 200) {
                mainActivity.BB_(mainActivity, message, this);
            }
            ReleaseLogUtil.b("Login_MainActivity", "msg:" + message.what + " finished, time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private void Bn_(Message message) {
        switch (message.what) {
            case MainActivityHandlerMsg.ON_AGREEMENT_GRANTED /* 20230625 */:
                m();
                break;
            case MainActivityHandlerMsg.CHECK_BETA_AGREEMENTS_UPDATE /* 20231122 */:
                this.cv.abC_(this.ar);
                break;
            case MainActivityHandlerMsg.BETA_AGREEMENTS_DIALOG /* 20231123 */:
                byh.BG_(this, this.ar);
                break;
            case 20241105:
                GolfUpdateMapInteractor.dcE_(this, message);
                break;
            case MainActivityHandlerMsg.ON_ACCOUNT_CHANGE_BROADCAST /* 2023060703 */:
                b(message.obj);
                break;
        }
    }

    private void Bs_(MainActivity mainActivity, Message message) {
        switch (message.what) {
            case 16:
                cw();
                break;
            case 17:
                lsp.d().d(this.y, this.ah);
                this.ah = null;
                break;
            case 19:
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                ThreadPoolManager.d().execute(new Runnable() { // from class: bxv
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity.e(countDownLatch);
                    }
                });
                try {
                    ReleaseLogUtil.b("Login_MainActivity", "DeviceConfigInit create isOnTime:", Boolean.valueOf(countDownLatch.await(2000L, TimeUnit.MILLISECONDS)));
                    break;
                } catch (InterruptedException unused) {
                    LogUtil.e("Login_MainActivity", "DeviceConfigInit create catch InterruptedException");
                    return;
                }
            case 4022:
                if (this.dr.getCurrentItem() == 0 && this.bc != null) {
                    this.aq.c(this.y);
                    break;
                }
                break;
            case 4023:
                this.aq.aQv_(this.y, this.ar);
                break;
            case 4024:
                this.aq.aQu_(this.ar);
                break;
            case FitnessStatusCodes.INVALID_TIMESTAMP /* 5022 */:
                aj();
                break;
            case MainActivityHandlerMsg.AFTER_LAUNCH_CHECK /* 20230214 */:
                at();
                break;
            case MainActivityHandlerMsg.REFRESH_START_PAGE /* 20230604 */:
                this.dv.a(message.arg1);
                break;
            case MainActivityHandlerMsg.ON_OPERATION_FEATURE_BROADCAST /* 2023060701 */:
                n();
                break;
            default:
                Bn_(message);
                break;
        }
    }

    public static /* synthetic */ void e(CountDownLatch countDownLatch) {
        DeviceConfigInit.create();
        countDownLatch.countDown();
    }

    private void Bt_(MainActivity mainActivity, Message message) {
        int i2 = message.what;
        if (i2 == 15) {
            gou.c(4, 10004, 0);
            return;
        }
        if (i2 == 4025) {
            gnv.aQr_(this.y, message.getData());
            return;
        }
        if (i2 == 6300) {
            fj();
            return;
        }
        if (i2 == 6400) {
            fm();
            return;
        }
        if (i2 == 8005) {
            if (this.bw || (dzn.c() && !dzn.f())) {
                this.ac.add(Integer.valueOf(message.what));
                return;
            } else {
                gmn.b(this.y);
                return;
            }
        }
        if (i2 == 4020) {
            gnv.c(this.y);
            return;
        }
        if (i2 == 4021) {
            gnv.b(this.y);
            return;
        }
        if (i2 == 6007) {
            LogUtil.c("Login_MainActivity", "Enter REQ_TRIGGER_ACCOUNT_LOGIN()");
            this.bq = true;
            ez();
        } else if (i2 == 6008) {
            LogUtil.c("Login_MainActivity", "Enter REQ_TRIGGER_HMS_PUSH_UPDATE_ALERT()");
            af();
        } else if (i2 == 8002) {
            be();
        } else if (i2 == 8003) {
            bl();
        } else {
            Bs_(mainActivity, message);
        }
    }

    private void Bo_(MainActivity mainActivity, Message message) {
        WeiXinInteractor weiXinInteractor;
        int i2 = message.what;
        if (i2 == 204) {
            if (((Activity) this.y).isDestroyed() || ((Activity) this.y).isFinishing() || (weiXinInteractor = mainActivity.dy) == null) {
                return;
            }
            weiXinInteractor.d();
            return;
        }
        if (i2 == 300) {
            LogUtil.d("Login_MainActivity", "get message MSG_DISMISS_ADS");
            mainActivity.r.a();
            return;
        }
        if (i2 == 4013) {
            mainActivity.cd.c(this.y.getString(R.string._2130837692_res_0x7f0200bc), this.y.getString(R.string._2130837706_res_0x7f0200ca), this.y.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase(), 0);
            return;
        }
        if (i2 == 4017) {
            mainActivity.cd.b();
            return;
        }
        if (i2 == 5020) {
            mainActivity.dw.v();
            return;
        }
        if (i2 == 6001) {
            LogUtil.c("Login_MainActivity", "get message MSG_GET_HW_USERINFO_SUCCESS");
            mainActivity.dw.u();
            return;
        }
        if (i2 == 6006) {
            this.cd.y();
            return;
        }
        if (i2 == 6010) {
            this.dw.aQX_(this.ar);
            return;
        }
        if (i2 == 10001) {
            Bl_(message);
        } else if (i2 == 10003) {
            Bm_(message);
        } else {
            Bt_(mainActivity, message);
        }
    }

    private void Bq_(MainActivity mainActivity, Message message) {
        int i2 = message.what;
        if (i2 == 4011) {
            mainActivity.cd.c(this.y.getString(R.string._2130837692_res_0x7f0200bc), this.y.getString(R.string._2130837703_res_0x7f0200c7), this.y.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase(), 1);
            return;
        }
        if (i2 == 4012) {
            mainActivity.cd.v();
            return;
        }
        if (i2 == 5013) {
            mainActivity.cd.b(1);
            return;
        }
        if (i2 == 5014) {
            mainActivity.cd.b(2);
            return;
        }
        if (i2 == 5016) {
            mainActivity.cd.d(1);
            return;
        }
        if (i2 != 5017) {
            switch (i2) {
                case 4014:
                    mainActivity.dw.e(message.arg1, this.al);
                    break;
                case 4015:
                    mainActivity.cd.c(this.y.getString(R.string._2130837692_res_0x7f0200bc), this.y.getString(R.string._2130837865_res_0x7f020169), this.y.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase(), 0);
                    break;
                case 4016:
                    mainActivity.cd.c(this.y.getString(R.string._2130837692_res_0x7f0200bc), String.format(Locale.ENGLISH, getResources().getString(R.string._2130843385_res_0x7f0216f9), 16), this.y.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase(), 0);
                    break;
                default:
                    Bo_(mainActivity, message);
                    break;
            }
            return;
        }
        mainActivity.cd.d(2);
    }

    private void Bu_(MainActivity mainActivity, Message message) {
        int i2 = message.what;
        if (i2 == 203) {
            sbk.a(this.y).c();
            Context context = this.y;
            Toast.makeText(context, context.getResources().getText(R.string._2130841551_res_0x7f020fcf), 0).show();
            return;
        }
        if (i2 == 4018) {
            mainActivity.cd.z();
            return;
        }
        if (i2 == 5019) {
            this.dw.w();
            return;
        }
        if (i2 != 40091) {
            switch (i2) {
                case 4007:
                    mainActivity.cd.g(0);
                    break;
                case WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED /* 4008 */:
                    mainActivity.cd.g(1);
                    break;
                case 4009:
                    mainActivity.cd.c(this.y.getString(R.string._2130839155_res_0x7f020673), this.y.getString(R.string._2130837707_res_0x7f0200cb), this.y.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase(), 0);
                    break;
                case 4010:
                    mainActivity.cd.c(this.y.getString(R.string._2130837692_res_0x7f0200bc), this.y.getString(R.string._2130837683_res_0x7f0200b3), this.y.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase(), 0);
                    break;
                default:
                    Bq_(mainActivity, message);
                    break;
            }
            return;
        }
        Context context2 = this.y;
        Toast.makeText(context2, context2.getResources().getText(R.string._2130837684_res_0x7f0200b4), 0).show();
    }

    private void Br_(MainActivity mainActivity, Message message) {
        int i2 = message.what;
        if (i2 == 1) {
            ai(mainActivity);
            return;
        }
        if (i2 == 2) {
            LogUtil.d("Login_MainActivity", "handle_checkDataStatus_and_show_dialog");
            ae(mainActivity);
            return;
        }
        if (i2 == 3) {
            mainActivity.cd.aaL_((Intent) message.obj);
            return;
        }
        if (i2 == 6) {
            LogUtil.c("Login_MainActivity", "accountmigrate: MSG_DATA_SYNC_AFTER_MIGRAGE");
            mainActivity.dw.f();
            return;
        }
        if (i2 == 7) {
            mainActivity.cd.j();
            this.dl.setVisibility(8);
            this.dr.setVisibility(8);
            return;
        }
        if (i2 == 201) {
            c(message.obj.toString());
            return;
        }
        if (i2 != 202) {
            switch (i2) {
                case 10088:
                    b(mainActivity, true);
                    break;
                case 10089:
                    ah(mainActivity);
                    break;
                case 10090:
                    LogUtil.c("Login_MainActivity", "get message MSG_SHOW_PRIVACY_UPDATE_DIALOG");
                    BC_(message);
                    break;
                default:
                    Bu_(mainActivity, message);
                    break;
            }
            return;
        }
        sbk.a(this.y).c();
        Context context = this.y;
        Toast.makeText(context, context.getResources().getText(R.string._2130841588_res_0x7f020ff4), 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Bp_(MainActivity mainActivity, Message message) {
        int i2 = message.what;
        if (i2 == 4) {
            ee();
        }
        if (i2 == 14) {
            LogUtil.c("Login_MainActivity", "get message MSG_CHECK_NEW_VERSION_OR_RESOURCE");
            this.cd.c(true);
            return;
        }
        if (i2 == 4019) {
            eb();
            return;
        }
        if (i2 == 5021) {
            eg();
            return;
        }
        switch (i2) {
            case 8:
                LogUtil.c("Login_MainActivity", "get message MSG_START_APP_FIRST_STEP");
                bh();
                break;
            case 9:
                LogUtil.c("Login_MainActivity", "get message MSG_START_APP_SECOND_STEP");
                bg();
                break;
            case 10:
                LogUtil.c("Login_MainActivity", "get message MSG_JUMP_TO_DEEPLINK_TARGET");
                By_(getIntent());
                break;
            case 11:
                LogUtil.c("Login_MainActivity", "get message MSG_CHECK_LANGUAGE_UPDATE_STATUS");
                this.cd.i();
                break;
            case 12:
                LogUtil.c("Login_MainActivity", "get message MSG_NEED_START_MAINACTIVITY");
                dl();
                break;
            default:
                switch (i2) {
                    case Constants.ON_PAGE_STARTED /* 10085 */:
                        LogUtil.c("Login_MainActivity", "get message MSG_SHOW_PRIVACY_BETA_USER_DLG");
                        b(this.y);
                        break;
                    case 10086:
                        LogUtil.c("Login_MainActivity", "get message MSG_SHOW_PRIVACY_DLG");
                        en();
                        break;
                    case Constants.FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5 /* 10087 */:
                        LogUtil.c("Login_MainActivity", "get message MSG_SHOW_PRIVACY_DIALOG");
                        ag(mainActivity);
                        break;
                    default:
                        Br_(mainActivity, message);
                        break;
                }
        }
    }

    private List<View> Bf_(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                arrayList.add(childAt);
                arrayList.addAll(Bf_(childAt));
            }
        }
        return arrayList;
    }

    private void cw() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.at) {
            LogUtil.e("Login_MainActivity", "all fragments have been loaded");
            return;
        }
        this.at = true;
        HealthViewPager healthViewPager = this.dr;
        if (healthViewPager != null) {
            healthViewPager.setOffscreenPageLimit(4);
            LogUtil.c("Login_MainActivity", "load other three fragment finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
            return;
        }
        LogUtil.e("Login_MainActivity", "load all fragment failed, mViewPager is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void BB_(MainActivity mainActivity, Message message, Handler handler) {
        if (this.k == null) {
            mainActivity.r.e(mainActivity.y, (OperationAdInteractor.a) message.obj);
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = message.what;
        obtain.obj = message.obj;
        handler.sendMessageDelayed(obtain, 500L);
    }

    private void bl() {
        MainInteractors mainInteractors = this.cd;
        if (mainInteractors != null) {
            mainInteractors.p();
            this.cd.f();
            this.cd.n();
            this.cd.m();
        }
    }

    private void be() {
        MainInteractors mainInteractors = this.cd;
        if (mainInteractors != null) {
            mainInteractors.o();
        }
    }

    private void ez() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bxs
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.this.f();
            }
        });
    }

    public /* synthetic */ void f() {
        this.cd.e(true);
    }

    private void Bm_(Message message) {
        Bundle data = message.getData();
        if (data != null) {
            String string = data.getString("token");
            Bd_(this.ar, data.getString("agrUrl"), string);
        }
    }

    private void Bl_(Message message) {
        Bundle data = message.getData();
        if (data != null) {
            String string = data.getString("token");
            String string2 = data.getString("result");
            b(this.y, data.getString("agrUrl"), string2, string);
        }
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str) || this.dl == null) {
            return;
        }
        int d2 = d(str);
        if (d2 >= 0) {
            this.dl.setItemChecked(d2);
        } else {
            LogUtil.a("Login_MainActivity", str, " tab name not exist.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int d(String str) {
        ArrayList<Fragment> arrayList;
        char c2;
        if (TextUtils.isEmpty(str) || (arrayList = this.an) == null || arrayList.isEmpty()) {
            return -1;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 2456:
                if (str.equals("ME")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 2223327:
                if (str.equals(Constants.HOME)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 79114068:
                if (str.equals("SPORT")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1055811561:
                if (str.equals("DISCOVER")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 2013139542:
                if (str.equals("DEVICE")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            return this.an.indexOf(this.ct);
        }
        if (c2 == 1) {
            return this.an.indexOf(this.bc);
        }
        if (c2 == 2) {
            return this.an.indexOf(this.dg);
        }
        if (c2 == 3) {
            return this.an.indexOf(this.ai);
        }
        if (c2 != 4) {
            return -1;
        }
        return this.an.indexOf(this.z);
    }

    private void ai(MainActivity mainActivity) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.br = Boolean.parseBoolean(SharedPreferenceManager.b(this.y, String.valueOf(20000), "needLogin"));
        mainActivity.bm();
        By_(getIntent());
        ci();
        bx();
        ck();
        cn();
        ct();
        cl();
        by();
        cs();
        cv();
        cq();
        bb();
        da();
        cr();
        cc();
        ce();
        ac();
        bz();
        cy();
        cu();
        cf();
        cj();
        cp();
        cm();
        cb();
        fep.awH_(this.ar);
        this.cd.b(System.currentTimeMillis() - this.q.k());
        this.q.b(0L);
        ReleaseLogUtil.b("Login_MainActivity", "jumpToTargetPage finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void ar() {
        LogUtil.a("Login_MainActivity", "enter downloadPlugin");
        this.ar.removeMessages(17);
        this.ar.removeMessages(19);
        this.ar.sendEmptyMessageDelayed(19, 4000L);
        if (TextUtils.isEmpty(this.ah)) {
            LogUtil.a("Login_MainActivity", "downloadPlugin mDownloadPluginModuleName is empty");
        } else {
            this.ar.sendEmptyMessageDelayed(17, 5000L);
        }
    }

    private void ah(MainActivity mainActivity) {
        String string = this.y.getString(R.string._2130843389_res_0x7f0216fd);
        if (nsn.ae(this.y)) {
            string = this.y.getString(R.string._2130844346_res_0x7f021aba);
        }
        mainActivity.cd.b(true, string);
    }

    private void BC_(Message message) {
        Bundle data = message.getData();
        if (data != null) {
            e(data.getString("agreementUrl"), data.getString("token"));
        }
    }

    private void ag(MainActivity mainActivity) {
        ReleaseLogUtil.b("Login_MainActivity", "showPrivacyDialog");
        this.au = true;
        mainActivity.e(8);
        if (dzn.d()) {
            d(false);
        } else {
            dv();
        }
    }

    private void en() {
        if (this.au || !this.bm) {
            LogUtil.c("Login_MainActivity", "showPrivacyDlg, privacy has showed, drop it");
            return;
        }
        this.bm = false;
        if (dzn.d()) {
            d(true);
        } else {
            dv();
        }
    }

    private void eo() {
        LogUtil.c("Login_MainActivity", "showPrivacyWindowChangeDialog enter");
        if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20000), "hw_health_last_login_site_id"))) {
            LogUtil.c("Login_MainActivity", "do not need show privacy dialog");
        } else if (dzn.d()) {
            d(false);
        } else {
            dv();
        }
    }

    private void ee() {
        if (!LoginInit.getInstance(this.y).getIsLogined()) {
            this.cd.c(this.y.getString(R.string.IDS_hw_app_name), this.y.getString(R.string._2130837665_res_0x7f0200a1), this.y.getString(R.string._2130841131_res_0x7f020e2b).toUpperCase(), 1);
        }
        this.cd.g();
    }

    private void aj() {
        LogUtil.c("Login_MainActivity", "doAbTestAction");
        if (this.dr == null || this.dl == null) {
            LogUtil.a("Login_MainActivity", "doAbTestAction mViewPager || tabs is null");
            return;
        }
        String d2 = fep.d();
        if ("[A]".equals(d2)) {
            this.dr.setCurrentItem(1, false);
            this.dl.setItemChecked(this.dr.getCurrentItem());
        } else if ("[B]".equals(d2)) {
            this.dr.setCurrentItem(2, false);
            this.dl.setItemChecked(this.dr.getCurrentItem());
        } else {
            LogUtil.c("Login_MainActivity", "doAbTestAction strategy C do nothing");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        LogUtil.c("Login_MainActivity", "enter_MSG_OBTAIN_TOKEN");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.a("Login_MainActivity", "MSG_OBTAIN_TOKEN token or agrUrl is empty");
            return;
        }
        dzn dznVar = this.cv;
        if (dznVar != null) {
            dznVar.d(this.y);
        }
        if (TextUtils.isEmpty(SharedPreferenceManager.b(this.y, Integer.toString(10000), "if_first_agr_sign")) && !dzn.c()) {
            Be_(this.y, this.ar, str, str2);
            return;
        }
        if ("0".equals(this.d)) {
            Bundle bundle = new Bundle();
            bundle.putString("token", str2);
            bundle.putString("agreementUrl", str);
            Message obtainMessage = this.ar.obtainMessage(10090);
            obtainMessage.setData(bundle);
            this.ar.sendMessage(obtainMessage);
            return;
        }
        Bd_(this.ar, str, str2);
    }

    private void fm() {
        LogUtil.c("Login_MainActivity", "get message BINDING_DEVICE_UPLOAD_TO_ODMF");
        String b2 = SharedPreferenceManager.b(this.y, Integer.toString(10000), "SAVE_BIND_TO_ODMF");
        LogUtil.c("Login_MainActivity", "uploadBindingDevices mSaveBindToOdmfFlag = ", b2);
        if ("".equals(b2)) {
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "SAVE_BIND_TO_ODMF", "TRUE", (StorageParams) null);
            m131do();
            this.dq.saveThirtyDaysTrackDataToOdmf(this.y);
        }
    }

    private void fj() {
        LogUtil.c("Login_MainActivity", "uploadTodayLog, Enter check dfx ");
        if (CommonUtil.as()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bxx
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.this.j();
                }
            });
        }
        Utils.o();
        LogUtil.c("Login_MainActivity", "uploadTodayLog, notifyHistoryRecord when goto mianActivity.");
        ExecutorService executorService = this.al;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.al.execute(new Runnable() { // from class: com.huawei.health.MainActivity.9
            @Override // java.lang.Runnable
            public void run() {
                jqh.a();
                jki.c(MainActivity.this.y);
            }
        });
    }

    public /* synthetic */ void j() {
        jeq.e().init(this.y);
        if (Utils.o()) {
            jeq.e().setProductType(20);
        } else {
            jeq.e().setProductType(1);
        }
    }

    private void bz() {
        if (this.cy == -1 || !AuthorizationUtils.a(this.y)) {
            return;
        }
        ah();
    }

    private void cy() {
        Intent intent = getIntent();
        Context context = this.y;
        if (context == null || intent == null || this.df == null || !AuthorizationUtils.a(context)) {
            return;
        }
        Uri parse = Uri.parse(intent.getStringExtra("walletUri"));
        if (jqx.a()) {
            if (!ComponentInfo.PluginPay_A_120.equals(this.df)) {
                ReleaseLogUtil.a("Login_MainActivity", "jumpToWalletMarket not TARTET_WALLET_ACTIVITY mSchemeWalletMarket: ", this.df);
                finish();
                return;
            }
            bzu.b().initHealthPayAdapter(this);
            Intent intent2 = new Intent();
            intent2.putExtra("activityCode", parse.getQueryParameter("activityCode"));
            intent2.setClassName(BaseApplication.getContext().getPackageName(), this.df);
            LogUtil.c("Login_MainActivity", "jumpToWalletMarket go to ", this.df);
            try {
                bzu.b().launchActivity(this, intent2);
            } catch (ActivityNotFoundException unused) {
                LogUtil.a("Login_MainActivity", "jumpToWalletMarket is activityNotFoundException.");
            }
            finish();
            return;
        }
        Intent intent3 = new Intent();
        intent3.setPackage(BaseApplication.getContext().getPackageName());
        intent3.setClassName(BaseApplication.getContext().getPackageName(), "com.huawei.health.MainActivity");
        intent3.setFlags(AppRouterExtras.COLDSTART);
        intent3.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
        DeviceInfo a2 = jpt.a("Login_MainActivity");
        int i2 = (a2 == null || a2.getDeviceConnectState() != 2) ? R.string.IDS_device_pair_connect_detail : R.string.IDS_qrcode_no_support_device_title;
        LogUtil.c("Login_MainActivity", "jumpToWalletMarket go to DeviceMainActivity");
        Toast.makeText(getApplicationContext(), i2, 1).show();
        try {
            startActivity(intent3);
        } catch (ActivityNotFoundException unused2) {
            LogUtil.a("Login_MainActivity", "jumpToMainActivity is activityNotFoundException.");
        }
        finish();
    }

    private void b(MainActivity mainActivity, boolean z) {
        String string = this.y.getString(R.string._2130843390_res_0x7f0216fe);
        if (nsn.ae(this.y)) {
            string = this.y.getString(R.string._2130844347_res_0x7f021abb);
        }
        mainActivity.cd.b(z, string);
    }

    private void cr() {
        Intent intent = getIntent();
        Context context = this.y;
        if (context == null || intent == null || this.dc == null || !AuthorizationUtils.a(context)) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToSleepDetail mSchemeSleepDetail = ", this.dc);
        Intent intent2 = new Intent(this.y, (Class<?>) KnitSleepDetailActivity.class);
        long longExtra = intent.getLongExtra("startTime", 0L);
        long longExtra2 = intent.getLongExtra("endTime", 0L);
        String stringExtra = intent.getStringExtra("jumpFromFileSyncNotify");
        intent2.putExtra("startTime", longExtra);
        intent2.putExtra("endTime", longExtra2);
        intent2.putExtra("jumpFromFileSyncNotify", stringExtra);
        startActivity(intent2);
        overridePendingTransition(0, 0);
    }

    private void ce() {
        Intent intent = getIntent();
        Context context = this.y;
        if (context == null || intent == null || this.cx == null || !AuthorizationUtils.a(context)) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToHealthDataDetail mSchemeHealthDataDetail = ", this.cx);
        HealthDataDetailActivity.c(this.y, intent.getStringExtra("extra_service_id"), intent.getIntExtra("extra_page_type", 0), intent.getLongExtra("extra_time_stamp", 0L));
    }

    private void cb() {
        if (this.br || this.n == null || LoginInit.getInstance(this.y).isBrowseMode() || !AuthorizationUtils.a(this.y)) {
            LogUtil.a("Login_MainActivity", "jumpToActiveTarget return");
        } else {
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).gotoH5ActiveTarget(this);
            this.n = null;
        }
    }

    private void ch() {
        Intent intent = getIntent();
        Context context = this.y;
        if (context == null || intent == null || this.ck == null || !AuthorizationUtils.a(context)) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToMedalDetail mMedalDetail = ", this.ck);
        Intent intent2 = new Intent();
        intent2.setClass(this.y, AchieveMedalNewActivity.class);
        startActivity(intent2);
        this.ck = null;
    }

    private void cj() {
        if (this.br || this.ci == null || LoginInit.getInstance(this.y).isBrowseMode() || !AuthorizationUtils.a(this.y)) {
            LogUtil.a("Login_MainActivity", "jumpToMemberDetail return");
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToMemberDetail mMemberDetail = ", this.ci);
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(this.ci);
        bzs.e().loadH5ProApp(this, "com.huawei.health.h5.vip", builder);
        this.ci = null;
    }

    private void cp() {
        if (this.br || this.cs == null || LoginInit.getInstance(this.y).isBrowseMode() || !AuthorizationUtils.a(this.y)) {
            LogUtil.a("Login_MainActivity", "jumpToOrderDetail return");
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToOrderDetail mOrderCode = ", this.cs);
        Bundle bundle = new Bundle();
        bundle.putString("orderCode", this.cs);
        AppRouter.b("/TradeService/TradeOrderDetailActivity").zF_(bundle).c(BaseApplication.getContext());
        this.cs = null;
    }

    private void cm() {
        if (this.br || !"order".equals(this.aj) || LoginInit.getInstance(this.y).isBrowseMode() || !AuthorizationUtils.a(this.y)) {
            LogUtil.a("Login_MainActivity", "jumpToOrderList return");
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToOrderList mFrom = ", this.aj);
        AppRouter.b("/TradeService/TradeOrderListActivity").c(BaseApplication.getContext());
        this.aj = null;
    }

    private void cc() {
        Intent intent = getIntent();
        if (efb.a(this.y)) {
            Context context = this.y;
            if (context == null || intent == null || this.cw == null || !AuthorizationUtils.a(context)) {
                return;
            }
            LogUtil.c("Login_MainActivity", "jumpToEcgDetail mSchemeEcgDetail is ", this.dc);
            String stringExtra = intent.getStringExtra("ecgDetail");
            if (efb.f()) {
                Intent intent2 = new Intent();
                intent2.setClassName(this.y.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
                IntentParams build = IntentParams.builder().addBiFrom("Today_0001", "healthTrends".equals(stringExtra) ? "healthTrend_entry" : "", "").addPageId(ArkUIXConstants.HEART_RATE).addPageType("0").build();
                com.huawei.hwlogsmodel.LogUtil.a("Login_MainActivity", "params.toString()", build.toString());
                intent2.putExtra(com.alipay.sdk.m.p.e.n, build.toString());
                gnm.aPC_(this.y, intent2);
                return;
            }
            Intent intent3 = new Intent(this.y, (Class<?>) HeartRateDetailActivity.class);
            intent3.putExtra("ecgDetail", stringExtra);
            startActivity(intent3);
            return;
        }
        Intent intent4 = new Intent(this.y, (Class<?>) KnitHeartRateActivity.class);
        long longExtra = intent.getLongExtra("startTime", 0L);
        long longExtra2 = intent.getLongExtra("endTime", 0L);
        String stringExtra2 = intent.getStringExtra("jumpFromFileSyncNotify");
        intent4.putExtra("startTime", longExtra);
        intent4.putExtra("endTime", longExtra2);
        intent4.putExtra("jumpFromFileSyncNotify", stringExtra2);
        startActivity(intent4);
    }

    private void da() {
        if (this.dd == null || !AuthorizationUtils.a(this.y) || getIntent() == null) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToTrackDetail mSchemeTrackDetail is ", this.dd);
        String stringExtra = getIntent().getStringExtra("entrance");
        Intent intent = new Intent(this, (Class<?>) SportHistoryActivity.class);
        intent.putExtra("entrance", stringExtra);
        startActivity(intent);
        LogUtil.c("Login_MainActivity", "jumpToTrackDetail start SportHistoryActivity");
        this.dd = null;
    }

    private void cq() {
        if (!opf.c(this.y) || this.da == null) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToQrCodeSchemePage jump to QrCodeSchemeActivity not first time");
        if (!Utils.o() || QrCodeSchemeActivity.a(this.da)) {
            Intent intent = new Intent(this.y, (Class<?>) QrCodeSchemeActivity.class);
            intent.putExtra("schemeQrCode", this.da);
            Intent intent2 = getIntent();
            if (intent2 != null) {
                intent.putExtra("src", intent2.getStringExtra("src"));
            }
            startActivity(intent);
            this.da = null;
        }
    }

    private void bb() {
        if (pem.e()) {
            cg();
        }
    }

    private void cg() {
        if (this.af != null) {
            LogUtil.c("Login_MainActivity", "Enter jumpToDeviceConnectPage.");
            Intent intent = new Intent();
            intent.setClassName(this.y, "com.huawei.ui.homewear21.home.DirectConnectDeviceActivity");
            startActivity(intent);
            this.af = null;
        }
    }

    private void cv() {
        if (f == null || !AuthorizationUtils.a(this.y) || Utils.o()) {
            return;
        }
        cd();
    }

    private void cd() {
        if (this.br || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToHealthCheckQrCodeActivity jump to group");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/?joinGroupQr=" + f);
        bzs.e().loadH5ProApp(this.y, "com.huawei.health.h5.groups", builder);
        f = null;
    }

    private void cs() {
        if ((this.br && LoginInit.getInstance(this.y).isBrowseMode()) || this.db == null || !AuthorizationUtils.a(this.y) || Utils.o()) {
            return;
        }
        if ("aar".equals(this.dp)) {
            LogUtil.c("Login_MainActivity", "jumpToSchemeUrlPage, jump to aar webview activity.");
            pep.e(this.y, this.db);
        } else {
            PluginOperation.getInstance(this).startOperationWebPage(this.db);
        }
        this.db = null;
    }

    private void cu() {
        if (this.br || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToSchemeBasic jump to HealthSchemeUtil before type", Integer.valueOf(this.aw));
        gof.aQy_(this, this.aw, this.dt);
        this.aw = -1;
    }

    private void cf() {
        if (this.br || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        gnv.aQa_(this, getIntent());
        gnv.aQb_(this, getIntent());
    }

    private void ci() {
        if (this.br || g == null || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToKaKaByScheme jump to kaka");
        Intent intent = new Intent();
        intent.setClassName(this.y, "com.huawei.health.browseraction.HwSchemeKakaActivity");
        intent.setData(g);
        startActivity(intent);
        g = null;
    }

    private void bx() {
        if (this.br || h == null || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.y, "com.huawei.health.browseraction.HwSchemeAchieveActivity");
        intent.setData(h);
        startActivity(intent);
        h = null;
    }

    private void ck() {
        if (this.br || j == null || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToNfcByScheme jump to NFC");
        Intent intent = new Intent();
        intent.setClassName(this.y, "com.huawei.health.nfcqrcodeaction.NfcAndQrCodeActionActivity");
        intent.setData(j);
        startActivity(intent);
        j = null;
    }

    private void cn() {
        if (this.br || l == null || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToQrCodeByScheme jump to QrCode");
        Intent intent = new Intent();
        intent.setClassName(this.y, "com.huawei.health.browseraction.HwSchemeQrCodeActivity");
        intent.setData(l);
        startActivity(intent);
        l = null;
    }

    private void ct() {
        if (this.de == null) {
            return;
        }
        LogUtil.c("Login_MainActivity", "jumpToRouteDetailByScheme jump to trackFile");
        Intent intent = new Intent(this.y, (Class<?>) MyRouteDetailActivity.class);
        intent.putExtra("fromFlag", "file_flag");
        intent.putExtra("filePath", this.de);
        startActivity(intent);
        this.de = null;
    }

    private void by() {
        cz();
        ch();
    }

    private void cz() {
        Uri uri = this.du;
        if (uri != null) {
            try {
                int parseInt = Integer.parseInt(uri.getQueryParameter("max_report_no"));
                int parseInt2 = Integer.parseInt(this.du.getQueryParameter("min_report_no"));
                int parseInt3 = Integer.parseInt(this.du.getQueryParameter("report_stype"));
                LogUtil.d("Login_MainActivity", "jumpToWeekMonthReport handleDetailUri==>max-->", Integer.valueOf(parseInt), ":min-->", Integer.valueOf(parseInt2), ":reportType-->", Integer.valueOf(parseInt3));
                mcv.d(getApplicationContext()).b(this, parseInt2, parseInt, parseInt3);
            } catch (NumberFormatException e2) {
                LogUtil.e("Login_MainActivity", e2.getMessage());
            }
        }
    }

    private void cl() {
        if (this.ds != null) {
            PluginOperation.getInstance(this).startOperationWebPage(this.ds);
            this.ds = null;
        }
    }

    private void ae(MainActivity mainActivity) {
        if (!HealthApplication.e().getSharedPreferences("is_clone", 0).getBoolean("is_clone", false)) {
            mainActivity.cd.w();
            return;
        }
        String a2 = KeyManager.a();
        String accountInfo = LoginInit.getInstance(this.y).getAccountInfo(1011);
        if (TextUtils.isEmpty(a2) || !a2.equals(accountInfo) || mainActivity.dw.p()) {
            KeyManager.d();
            mainActivity.cd.w();
        } else {
            b(mainActivity, false);
        }
    }

    private void dy() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        dzn.b(this.y);
        long n = CommonUtil.n(this.y, SharedPreferenceManager.b(this.y, Integer.toString(10000), "agr_last_query_time"));
        this.d = SharedPreferenceManager.b(this.y, Integer.toString(10000), "agr_if_agree_authorize");
        float currentTimeMillis = (System.currentTimeMillis() - n) / 60000.0f;
        if (n == 0 || currentTimeMillis > 1440.0f || !"1".equals(this.d)) {
            df();
        }
        ReleaseLogUtil.b("Login_MainActivity", "shouldQuery finished intervalTime = ", Float.valueOf(currentTimeMillis), " lastQueryTime = ", n + ", time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void df() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: byb
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.this.i();
            }
        });
    }

    public /* synthetic */ void i() {
        String url = GRSManager.a(this.y).getUrl("agreementservice");
        String accountInfo = LoginInit.getInstance(this.y).getAccountInfo(1015);
        if (!TextUtils.isEmpty(accountInfo)) {
            LogUtil.c("Login_MainActivity", "obtainTokenInThread token is not empty");
            a(url, accountInfo);
        } else {
            b(url);
        }
    }

    private void b(final String str) {
        LoginInit.getInstance(this.y).refreshAccessToken(new ILoginCallback() { // from class: com.huawei.health.MainActivity.7
            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginSuccess(Object obj) {
                LogUtil.c("Login_MainActivity", "obtainTokenInThread token login success");
                if (obj instanceof String) {
                    MainActivity.this.a(str, (String) obj);
                } else {
                    LogUtil.a("Login_MainActivity", "obtainTokenInThread object convert fail");
                }
            }

            @Override // com.huawei.login.ui.login.util.ILoginCallback
            public void onLoginFailed(Object obj) {
                LogUtil.a("Login_MainActivity", "obtainTokenInThread token login failed");
            }
        });
    }

    private static void Be_(Context context, final Handler handler, final String str, final String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("Login_MainActivity", "agrSign MainActivty_signAgrHttp aToken is null");
            return;
        }
        if (context == null) {
            LogUtil.a("Login_MainActivity", "agrSign MainActivty context is null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
        LogUtil.c("Login_MainActivity", "agrSign MainActivty_signAgrHttp country is ", accountInfo);
        if (TextUtils.isEmpty(accountInfo)) {
            return;
        }
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (TextUtils.isEmpty(language)) {
            language = Locale.getDefault().getLanguage();
            if (TextUtils.isEmpty(language)) {
                language = MLAsrConstants.LAN_ZH;
            }
        }
        if (TextUtils.isEmpty(country)) {
            country = Locale.getDefault().getCountry();
            if (TextUtils.isEmpty(country)) {
                country = accountInfo;
            }
        }
        final String str3 = language + "_" + country;
        ThreadPoolManager.d().execute(new Runnable() { // from class: bxw
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.BA_(str2, str, accountInfo, str3, handler);
            }
        });
    }

    public static /* synthetic */ void BA_(String str, String str2, String str3, String str4, Handler handler) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(118);
        arrayList.add(10009);
        new AgrHttp().signHttpReq(str, str2, true, arrayList, str3, str4, new e(handler, str2, str, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class e implements HttpResCallBack {

        /* renamed from: a, reason: collision with root package name */
        private final String f2179a;
        private WeakReference<Handler> b;
        private final String c;
        private final List<Integer> e;

        public e(Handler handler, String str, String str2, List<Integer> list) {
            this.b = new WeakReference<>(handler);
            this.f2179a = str;
            this.c = str2;
            this.e = list;
        }

        @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
        public void onFinished(int i, String str) {
            LogUtil.c("Login_MainActivity", "agrSign First sign resultCode = ", Integer.valueOf(i), " result = ", str);
            if (i != 200 || !sds.c(str)) {
                LogUtil.a("Login_MainActivity", "agrSign privacy sign failed");
                return;
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "if_first_agr_sign", "1", (StorageParams) null);
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "agr_authorized_versions", new Gson().toJson(this.e), new StorageParams());
            dzn.b(true);
            Handler handler = this.b.get();
            if (handler == null) {
                LogUtil.a("Login_MainActivity", "agrSign First sign handler is null");
                return;
            }
            Message obtainMessage = handler.obtainMessage(10003);
            Bundle bundle = new Bundle();
            bundle.putString("token", this.c);
            bundle.putString("agrUrl", this.f2179a);
            obtainMessage.setData(bundle);
            handler.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class b implements HttpResCallBack {
        private WeakReference<Handler> c;
        private final String d;
        private final String e;

        public b(Handler handler, String str, String str2) {
            this.c = new WeakReference<>(handler);
            this.d = str;
            this.e = str2;
        }

        @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
        public void onFinished(int i, String str) {
            Handler handler;
            if (i != 200 || (handler = this.c.get()) == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("result", str);
            bundle.putString("token", this.e);
            bundle.putString("agrUrl", this.d);
            Message obtainMessage = handler.obtainMessage(10001);
            obtainMessage.setData(bundle);
            handler.sendMessage(obtainMessage);
            LogUtil.c("Login_MainActivity", "agr_query_sign_response data is ", str);
        }
    }

    private static void Bd_(final Handler handler, final String str, final String str2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bxy
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.Bz_(str2, str, handler);
            }
        });
    }

    public static /* synthetic */ void Bz_(String str, String str2, Handler handler) {
        LogUtil.c("Login_MainActivity", "agrQuery MainActivty_signAgrHttp aToken");
        new AgrHttp().queryHttpReq(str, str2, false, new b(handler, str2, str));
    }

    private void b(Context context, String str, String str2, String str3) {
        int i2;
        LogUtil.c("Login_MainActivity", "enter_handleAgrResData");
        if (context == null || !sds.c(str2)) {
            LogUtil.a("Login_MainActivity", "handleAgrResData privacy query failed");
            return;
        }
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_query_time", Long.toString(System.currentTimeMillis()), (StorageParams) null);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("growUpSignIndication") && (i2 = jSONObject.getInt("growUpSignIndication")) != 0) {
                ReleaseLogUtil.b("Login_MainActivity", "growUpSignIndication is ", Integer.valueOf(i2));
                this.bm = true;
                e(context);
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.VMALL_SIGN_INFO);
            LogUtil.c("Login_MainActivity", "handleAgrResData agr_query_sign_response JSONArray : ", Integer.valueOf(jSONArray.length()));
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() > 0) {
                if (d(context, jSONArray, arrayList)) {
                    return;
                }
            } else {
                arrayList.add(118);
                arrayList.add(10009);
            }
            if (arrayList.size() <= 0) {
                LogUtil.a("Login_MainActivity", "handleAgrResData QueryAgrResInfo list is null");
            } else if (dzn.c() && !dzn.f()) {
                LogUtil.c("Login_MainActivity", "handleAgrResData, need show Privacy");
                ag(this);
            } else {
                d(context, arrayList, str, str3);
            }
        } catch (JSONException e2) {
            LogUtil.e("Login_MainActivity", "handleAgrResData Show Sign Dialog JSONException e = ", e2.getMessage());
        }
    }

    private boolean d(Context context, JSONArray jSONArray, List<Integer> list) throws JSONException {
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            int optInt = jSONObject.optInt(Constants.VMALL_ARG_TYPE);
            if (optInt == 118 || optInt == 10009) {
                if (jSONObject.optBoolean("needSign")) {
                    LogUtil.c("Login_MainActivity", "handleAgrResData agr_query_sign_response agrType = ", Integer.valueOf(optInt));
                    list.add(Integer.valueOf(optInt));
                } else if (b(context, jSONObject.optLong("signTime"))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean b(Context context, long j2) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1006);
        if (TextUtils.isEmpty(accountInfo)) {
            return false;
        }
        int m = CommonUtil.m(context, accountInfo) + 180000;
        int b2 = DateFormatUtil.b(j2);
        int b3 = DateFormatUtil.b(System.currentTimeMillis());
        boolean isMinorAccount = LoginInit.getInstance(context).isMinorAccount();
        LogUtil.c("Login_MainActivity", "handleAgrResData, growUpDate:", Integer.valueOf(m), " signDate:", Integer.valueOf(b2), " currentDate:", Integer.valueOf(b3), "  isMinorAccount:", Boolean.valueOf(isMinorAccount));
        if (b2 > m || m >= b3 || isMinorAccount) {
            return false;
        }
        ReleaseLogUtil.b("Login_MainActivity", "handleAgrResData, minor grown up need show Privacy");
        this.bm = true;
        e(context);
        return true;
    }

    private void e(Context context) {
        en();
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_query_time", "", (StorageParams) null);
        SharedPreferenceManager.e(context, Integer.toString(10000), "if_first_agr_sign", "", (StorageParams) null);
    }

    private void d(Context context, List<Integer> list, String str, String str2) {
        int[] iArr = new int[2];
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).intValue() == 118) {
                iArr[0] = 1;
            }
            if (list.get(i2).intValue() == 10009) {
                iArr[1] = 1;
            }
        }
        int i3 = iArr[0] + (iArr[1] * 2);
        LogUtil.c("Login_MainActivity", "handleAgrResData Show Sign Dialog Type is ", Integer.valueOf(i3));
        gpd gpdVar = new gpd(context, str, i3, str2);
        this.t = gpdVar;
        gpdVar.d();
    }

    private void e(String str, String str2) {
        int i2;
        if ("0".equals(this.d)) {
            LogUtil.c("Login_MainActivity", "showLastDialog Show Sign Dialog lastTime have cancel ");
            try {
                i2 = Integer.parseInt(SharedPreferenceManager.b(this.y, Integer.toString(10000), "agr_cancel_or_agree_type"));
            } catch (NumberFormatException unused) {
                LogUtil.e("Login_MainActivity", "showLastDialog parseLong Exception");
                i2 = 0;
            }
            gpd gpdVar = new gpd(this.y, str, i2, str2);
            this.t = gpdVar;
            gpdVar.d();
        }
    }

    private void dl() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.c("Login_MainActivity", "onDestroy ", this, ", mHasInit = ", Boolean.valueOf(this.ao));
        this.q.c((RunningForegroundListener) null);
        if (!this.ao) {
            super.onDestroy();
            return;
        }
        ak();
        oia.c().m();
        fc();
        ObserverManagerUtil.e("HEALTH_FLOAT_BAR_STATE_CHANGE");
        super.onDestroy();
        fa();
        AccountHelper.compareAndSetLogining(true, false);
        if (this.y == null) {
            return;
        }
        HuaweiApiClient huaweiApiClient = this.ay;
        if (huaweiApiClient != null) {
            huaweiApiClient.disconnect();
            this.ay = null;
        }
        b = false;
        this.bg = false;
        if (!this.bt) {
            MainInteractorsUtils.e(MainInteractorsUtils.e, this.cl);
        }
        if (SmartClipManager.e()) {
            jdh.c().a(HandoffService.getTransferNotificationId());
        }
        byq byqVar = this.k;
        if (byqVar != null) {
            byqVar.f();
        }
        fn();
        ao();
        this.cd.r();
        eq();
        this.dl.c();
        this.dl.setBottomNavListener(null);
        gwe.a();
        aq();
        this.an = null;
        nsw.b();
        BaseDialog.clearResource();
        dp();
        eoi.c().e();
        ActiveTargetManager.e().f();
    }

    private void fn() {
        WeiXinInteractor weiXinInteractor = this.dy;
        if (weiXinInteractor != null) {
            weiXinInteractor.b();
            this.dy = null;
        }
    }

    private void ao() {
        ExecutorService executorService = this.al;
        if (executorService != null) {
            executorService.shutdown();
            this.al = null;
        }
    }

    private void dp() {
        View cwW_ = nkr.d().cwW_();
        if (!(cwW_ instanceof MiniBottomPlayer)) {
            LogUtil.a("Login_MainActivity", "remove minibar sPlayerMiniBar is null");
            return;
        }
        ((MiniBottomPlayer) cwW_).e();
        dow.c().e();
        LogUtil.c("Login_MainActivity", "removeMinibar");
    }

    private void ak() {
        ObserverManagerUtil.c("EXIT_APP", new Object[0]);
        if (!CommonUtil.g(this, "com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity")) {
            HealthSportingNotificationHelper.a();
        }
        oli a2 = oli.a();
        a2.i(-3);
        a2.z();
        ag();
        nrh.e();
        fd();
        AiSportVoiceHelper.getInstance().stopAll();
        AudioControlOperate.INSTANCE.release();
        this.cv.n();
        eoi.c().l();
        nhj.q();
    }

    private void fa() {
        ObserverManagerUtil.e("REFRESH_HEALTH_HEADLINES_SHOW_STATUS");
        ObserverManagerUtil.e("SHOW_HEALTH_HEADLINES_TIPS");
        ObserverManagerUtil.e("HOME_RECYCLE_VIEW_MOVE");
        ObserverManagerUtil.e("HEALTH_HEADLINES_CARD_REFRESH_UI");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        HomeFragment homeFragment = this.bc;
        if (homeFragment != null) {
            homeFragment.f();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void ag() {
        byq byqVar = this.k;
        if (byqVar != null) {
            byqVar.d();
            this.k = null;
        }
    }

    private void fc() {
        OperationFeatureReceiver.c();
        ezy.a(this);
        AccountChangeReceiver.c();
        TriggerLoginReceiver.b();
        fi();
        DeviceBindReceiver.e();
        fh();
        ezj.b(this);
        fb();
        MultiUsersManager.INSTANCE.unRegUserInfoListener();
        ezu.a(BaseApplication.getContext());
        ezt.a(this);
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).unRegisterContactChangeObserver();
        oqr oqrVar = this.bb;
        if (oqrVar != null) {
            oqrVar.e();
            this.bb = null;
        }
    }

    private void eq() {
        Handler handler = this.ar;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.y = null;
        nqf.c();
        OperationAdInteractor operationAdInteractor = this.r;
        if (operationAdInteractor != null) {
            operationAdInteractor.d();
            this.r = null;
        }
        Handler handler2 = this.ar;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
    }

    private void aq() {
        this.cm = null;
        this.ag = null;
        this.v = null;
        this.u = null;
        this.w = null;
        this.ax = null;
        this.bc = null;
        this.dg = null;
        this.ai = null;
        this.ct = null;
        this.z = null;
        this.dl = null;
        this.dr = null;
        this.ba = null;
        this.cv = null;
        this.p = null;
        this.cp = null;
        this.ch = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onActivityResult(i2, i3, intent);
        LogUtil.d("Login_MainActivity", "onActivityResult Main_thread1 : ", Thread.currentThread().getName());
        if (i2 == 1000) {
            dc();
        } else if (i2 == 6003) {
            d(i2, i3, elapsedRealtime);
        } else if (i2 == 6013) {
            b(i2, i3, elapsedRealtime);
        } else if (i2 == 6004) {
            LogUtil.c("Login_MainActivity", "onActivityResult REQ_SHOW_AREA_SELECT_ALERT, requestCode = ", Integer.valueOf(i2), ",resultCode = ", Integer.valueOf(i3));
            if (i3 != 0) {
                return;
            }
            if (sbc.i()) {
                byh.BK_(this, this.ar);
            } else {
                this.cd.ab();
            }
        } else if (i2 == 5000) {
            ex();
        } else if (i2 != 1) {
            SportEntranceFragment sportEntranceFragment = this.dg;
            if (sportEntranceFragment != null && sportEntranceFragment.e(i2)) {
                this.dg.onActivityResult(i2, i3, intent);
            } else if (i2 == 7667713) {
                if (intent != null && intent.hasExtra("KEY_SUG_COURSE_IDS_RESULT")) {
                    PluginSuggestion.getInstance().notifyActivityResult(i2, i3, intent);
                }
            } else {
                c(i2, i3, elapsedRealtime);
                this.cd.a(i2);
            }
        } else if (intent != null && i3 == 2) {
            nue.cNU_(intent, this, nue.e(i3, true, intent.getIntExtra("product_type", -1), false));
        }
        LogUtil.c("Login_MainActivity", "onActivityResult finished, requestCode:" + i2 + ", resultCode:" + i3 + ", time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void b(int i2, int i3, long j2) {
        if (i3 == -1) {
            LogUtil.c("Login_MainActivity", "onActivityResult Activity.RESULT_OK == resultCode ");
            this.t.c();
            return;
        }
        this.t.b();
        LogUtil.c("Login_MainActivity", "finish MainActivity in onActivityResult, requestCode:" + i2 + ", resultCode:" + i3 + ", time cost: " + (SystemClock.elapsedRealtime() - j2));
        finish();
    }

    private void d(int i2, int i3, long j2) {
        if (i3 == -1) {
            LogUtil.c("Login_MainActivity", "onActivityResult Activity.RESULT_OK == resultCode ");
            SharedPreferenceManager.e(this.y, Integer.toString(10000), "hw_health_show_grant_pwd", Integer.toString(1), new StorageParams());
            this.cd.g();
            return;
        }
        LogUtil.c("Login_MainActivity", "finish MainActivity in onActivityResult, requestCode:" + i2 + ", resultCode:" + i3 + ", time cost: " + (SystemClock.elapsedRealtime() - j2));
        finish();
    }

    private void c(int i2, int i3, long j2) {
        if (i2 == 101) {
            if (i3 == 102) {
                LogUtil.c("Login_MainActivity", "finish MainActivity in onActivityResult, requestCode:" + i2 + ", resultCode:" + i3 + ", time cost: " + (SystemClock.elapsedRealtime() - j2));
                finish();
            }
            if (i3 == 103) {
                this.cd.t();
            }
        }
    }

    private void dc() {
        if (efb.a()) {
            HomeFragment homeFragment = this.bc;
            if (homeFragment != null) {
                LogUtil.c("Login_MainActivity", "onActivityResult() logHomeFragmentStatus mHomeFragment.isCardInitialized() = ", Boolean.valueOf(homeFragment.a()));
                return;
            } else {
                LogUtil.a("Login_MainActivity", "onActivityResult() logHomeFragmentStatus mHomeFragment is null");
                return;
            }
        }
        HomeFragment homeFragment2 = this.bc;
        if (homeFragment2 != null && homeFragment2.a()) {
            LogUtil.c("Login_MainActivity", "onActivityResult() refresh home card");
            this.bc.g();
            return;
        }
        HomeFragment homeFragment3 = this.bc;
        if (homeFragment3 != null) {
            LogUtil.c("Login_MainActivity", "onActivityResult() logHomeFragmentStatus mHomeFragment.isCardInitialized() = ", Boolean.valueOf(homeFragment3.a()));
        } else {
            LogUtil.a("Login_MainActivity", "onActivityResult() logHomeFragmentStatus mHomeFragment is null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        LogUtil.c("Login_MainActivity", "Activity-onRequestPermissionsResult() PermissionsManager.notifyPermissionsChange()");
        if (iArr != null && strArr != null) {
            jeg.d().d(strArr, iArr);
        }
        cs();
        if (f != null && !Utils.o()) {
            cd();
        }
        co();
        cg();
        if (this.cy != -1) {
            ah();
        }
        if (iArr == null) {
            LogUtil.a("Login_MainActivity", "onRequestPermissionsResult grantResults null");
            return;
        }
        this.dw.f();
        this.cd.c(false);
        if (i2 == 10) {
            if (iArr.length == 0) {
                LogUtil.a("Login_MainActivity", "onRequestPermissionsResult grantResults length 0");
                return;
            }
            LogUtil.c("Login_MainActivity", "getpermission onRequestPermissionsResult back");
            if (iArr[0] == 0) {
                LogUtil.d("Login_MainActivity", "onRequestPermissionsResult The permission is obtained successfully.");
            } else {
                LogUtil.c("Login_MainActivity", "onRequestPermissionsResult getpermission onRequestPermissionsResult rejected");
            }
        }
    }

    private void co() {
        if (this.da == null || LoginInit.getInstance(this.y).isBrowseMode()) {
            return;
        }
        if (!Utils.o() || QrCodeSchemeActivity.a(this.da)) {
            Intent intent = new Intent(this.y, (Class<?>) QrCodeSchemeActivity.class);
            intent.putExtra("schemeQrCode", this.da);
            Intent intent2 = getIntent();
            if (intent2 != null) {
                intent.putExtra("src", intent2.getStringExtra("src"));
            }
            startActivity(intent);
            this.da = null;
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove("android:support:fragments");
        bundle.remove("android:fragments");
        bundle.remove("androidx.lifecycle.BundlableSavedStateRegistry.key");
    }

    private void ah() {
        if (this.cy != -1) {
            if (bw()) {
                LogUtil.a("Login_MainActivity", "dealSchemeActions finish MainAcitivity for cause: is in motino");
                finish();
            } else {
                am();
            }
        }
    }

    private void am() {
        Uri uri = this.cz;
        String query = uri != null ? uri.getQuery() : null;
        int i2 = this.cy;
        if (i2 == 1) {
            if (query == null) {
                ax();
            } else {
                Bk_(this.cz);
            }
        } else if (i2 == 2) {
            if (ab()) {
                b();
            } else {
                BE_(this.cz);
            }
        } else {
            LogUtil.a("Login_MainActivity", "distributeSchemeActions mSchemeDataType is other type");
        }
        this.cy = -1;
    }

    private boolean ab() {
        Uri uri = this.cz;
        if (uri == null) {
            LogUtil.a("Login_MainActivity", "[checkSchemeParameter] schemeParamUri is null.");
            return true;
        }
        try {
            return "all_fitness".equals(uri.getQueryParameter("skip_type"));
        } catch (IllegalArgumentException e2) {
            LogUtil.e("Login_MainActivity", "checkSchemeParameter IllegalArgumentException : ", e2.getMessage());
            return true;
        } catch (Exception unused) {
            LogUtil.e("Login_MainActivity", "checkSchemeParameter Exception.");
            return true;
        }
    }

    private void ax() {
        SportEntranceFragment sportEntranceFragment = this.dg;
        if (sportEntranceFragment != null && this.dr != null) {
            sportEntranceFragment.d(this, this.bu, this.e, this.cc);
            e("SPORT");
        } else {
            LogUtil.a("Login_MainActivity", "goToSportTrackReady SportEntanceFragment or ViewPager is not initialed");
        }
    }

    private void Bk_(Uri uri) {
        try {
            gso.e().c(0, a(Integer.parseInt(uri.getQueryParameter(BleConstants.SPORT_TYPE))), a(uri.getQueryParameter(WorkoutRecord.Extend.COURSE_TARGET_TYPE)), Float.parseFloat(uri.getQueryParameter(WorkoutRecord.Extend.COURSE_TARGET_VALUE)) / 1000.0f, null, this.y);
        } catch (NumberFormatException e2) {
            LogUtil.e("Login_MainActivity", "goToSportTrack NumberFormatException : ", e2.getMessage());
        } catch (Exception unused) {
            LogUtil.e("Login_MainActivity", "goToSportTrack Exception");
        }
    }

    private int a(int i2) {
        MotionTrackPretreatmentService motionTrackPretreatmentService = (MotionTrackPretreatmentService) AppRouter.a(MotionTrackPretreatmentService.class);
        if (motionTrackPretreatmentService != null) {
            return motionTrackPretreatmentService.getSportType(i2);
        }
        Integer num = av().get(Integer.valueOf(i2));
        if (num instanceof Integer) {
            return num.intValue();
        }
        return 0;
    }

    private int a(String str) {
        MotionTrackPretreatmentService motionTrackPretreatmentService = (MotionTrackPretreatmentService) AppRouter.a(MotionTrackPretreatmentService.class);
        if (motionTrackPretreatmentService != null) {
            return motionTrackPretreatmentService.getTargetType(str);
        }
        Integer num = aw().get(str);
        if (num instanceof Integer) {
            return num.intValue();
        }
        return -1;
    }

    private Map<Integer, Integer> av() {
        LogUtil.a("Login_MainActivity", "getSportTypeMap");
        HashMap hashMap = new HashMap(5);
        hashMap.put(2, 258);
        hashMap.put(1, 257);
        hashMap.put(3, 259);
        return hashMap;
    }

    private Map<String, Integer> aw() {
        LogUtil.a("Login_MainActivity", "getTargetTypeMap");
        HashMap hashMap = new HashMap(5);
        hashMap.put("km", 1);
        hashMap.put(FitRunPlayAudio.OPPORTUNITY_M, 1);
        hashMap.put("s", 0);
        hashMap.put("cal", 2);
        return hashMap;
    }

    public void BE_(Uri uri) {
        try {
            final String queryParameter = uri.getQueryParameter("id");
            final String queryParameter2 = uri.getQueryParameter("version");
            Services.b("PluginFitnessAdvice", PluginSuggestion.class, this.y, new Consumer() { // from class: bxt
                @Override // com.huawei.framework.servicemgr.Consumer
                public final void accept(Object obj) {
                    MainActivity.this.c(queryParameter, queryParameter2, (PluginSuggestion) obj);
                }
            });
        } catch (IllegalArgumentException e2) {
            LogUtil.e("Login_MainActivity", "goFitnessPage IllegalArgumentException : ", e2.getMessage());
        } catch (Exception unused) {
            LogUtil.e("Login_MainActivity", "goFitnessPage Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void c(String str, String str2, PluginSuggestion pluginSuggestion) {
        pluginSuggestion.getFitWorkout(str, str2, new UiCallback<FitWorkout>() { // from class: com.huawei.health.MainActivity.10
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str3) {
                LogUtil.c("Login_MainActivity", "goFitnessPage getFitWorkout onFailure : ", Integer.valueOf(i2));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(FitWorkout fitWorkout) {
                if (fitWorkout == null) {
                    LogUtil.a("Login_MainActivity", "goFitnessPage getFitWorkout onSuccess data is null ");
                } else {
                    LogUtil.c("Login_MainActivity", "goFitnessPage getFitWorkout onSuccess ");
                    MainActivity.this.e(fitWorkout);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(FitWorkout fitWorkout) {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        if (fitWorkout == null) {
            LogUtil.a("Login_MainActivity", "start fitness failed with the null fitWorkout");
        } else {
            gge.d((String) null);
            mod.d(this.y, fitWorkout);
        }
    }

    public void b() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(this.y);
    }

    private void dd() {
        if (this.dj == -1 && this.dh == -1 && this.di == null) {
            return;
        }
        if (bw()) {
            LogUtil.a("Login_MainActivity", "processSkipActivity, finish MainAcitivity for cause: skip is in motino");
            finish();
        } else {
            if (isTaskRoot()) {
                return;
            }
            LogUtil.c("Login_MainActivity", "processSkipActivity, is not TaskRoot, start checkSkipActivity");
            ac();
        }
    }

    private boolean bw() {
        if (gso.e().p()) {
            return true;
        }
        int b2 = fhp.c().b();
        LogUtil.c("Login_MainActivity", "isSportOrFitRunning fitState = ", Integer.valueOf(b2));
        return b2 == 2 || b2 == 5;
    }

    private void ac() {
        if (this.ca) {
            if (gpo.b() && !this.cf) {
                AppRouter.b("/OperationBundle/MemberRelayActivity").c(this.y);
            } else {
                if (this.cf) {
                    this.cf = false;
                }
                e("DISCOVER");
            }
        } else if (this.ce) {
            ax();
        } else if (this.bz) {
            e("DEVICE");
        } else if (this.dj == -1) {
            es();
        } else {
            ew();
        }
        if (isTaskRoot() || this.ce || this.bl || this.by || this.bz || this.ca) {
            return;
        }
        if (this.cz == null || this.dt == null) {
            LogUtil.a("Login_MainActivity", "checkSkipActivity finish MainAcitivity for cause: is task root");
            finish();
        }
    }

    private void es() {
        int i2 = this.dh;
        if (i2 == 1) {
            ex();
            return;
        }
        if (i2 == 2) {
            ep();
        } else if (i2 == 3) {
            ev();
        } else {
            LogUtil.a("Login_MainActivity", "checkSkipActivity finish mSmartMsgType is other type");
        }
    }

    private void ex() {
        LogUtil.c("Login_MainActivity", "startSportActivity");
        byd.d(AnalyticsValue.HEALTH_HOME_FROM_NEGATIVE_BUTTON_CLICK_2010053.value(), "1");
        HealthViewPager healthViewPager = this.dr;
        if (healthViewPager != null) {
            healthViewPager.setCurrentItem(1, false);
        }
    }

    private void ep() {
        LogUtil.c("Login_MainActivity", "startFitnessExerciseActivity");
        byd.d(AnalyticsValue.HEALTH_HOME_FROM_NEGATIVE_BUTTON_CLICK_2010053.value(), "2");
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(this.y);
    }

    private void ew() {
        LogUtil.c("Login_MainActivity", "startSmartMsgSkipActivity");
        byd.d(AnalyticsValue.HEALTH_HOME_FROM_NEGATIVE_SMARTCARD_CLICK_2010054.value(), String.valueOf(this.dh));
        Intent intent = new Intent();
        intent.putExtra("id", this.dj);
        intent.putExtra("msgType", this.dh);
        intent.putExtra("msgContent", this.di);
        intent.putExtra("from", 1);
        intent.setClass(this, SmartMsgSkipActivity.class);
        startActivity(intent);
    }

    private void dj() {
        HealthBottomView healthBottomView;
        if (getWindow() == null || (healthBottomView = this.dl) == null || healthBottomView.getBackground() == null) {
            LogUtil.a("Login_MainActivity", "refreshNavigationBarColor:getWindow() or tabs or tabs.getBackground() is null");
        } else if (CommonUtil.bh()) {
            getWindow().setNavigationBarColor(((ColorDrawable) this.dl.getBackground().mutate()).getColor());
        }
    }

    private void ev() {
        LogUtil.c("Login_MainActivity", "startPairDeviceActivity");
        LogUtil.c("Login_MainActivity", "mSmartMsgId = ", Integer.valueOf(this.dj), ", mSmartMsgType = ", Integer.valueOf(this.dh), ", mSmartMsgContent = ", this.di, ", mProductName = ", this.cu, ", mDeviceType = ", Integer.valueOf(this.aa));
        Intent intent = new Intent();
        intent.setFlags(268435456);
        if (this.aa == 11) {
            LogUtil.c("Login_MainActivity", "startPairDeviceActivity mDeviceType is r1 ");
            intent.putExtra(TemplateStyleRecord.STYLE, 4);
            intent.putExtra("isFromWearR1", true);
            intent.setClass(this.y, AddDeviceChildActivity.class);
        } else {
            LogUtil.c("Login_MainActivity", "startPairDeviceActivity mDeviceType is not r1 ");
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, this.aa);
            intent.putExtra("dname", this.cu);
            intent.putExtra("isPorc", this.bs);
            intent.putExtra("isFromWear", true);
            intent.setClass(this.y, AddDeviceIntroActivity.class);
        }
        try {
            this.y.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.e("Login_MainActivity", "ActivityNotFoundException e:", e2.getMessage());
        }
    }

    private void fi() {
        if (Utils.i()) {
            LogUtil.c("Login_MainActivity", "unregisterAchievementBroadcastReceiver enter");
            OnceMovementReceiver h2 = mcv.d(this.y).h();
            if (h2 != null) {
                LogUtil.c("Login_MainActivity", "unregisteronceMovementReceiver != null");
                LocalBroadcastManager.getInstance(this.y).unregisterReceiver(h2);
            }
            LanguageResReceiver l2 = mcv.d(this.y).l();
            if (l2 != null) {
                LogUtil.c("Login_MainActivity", "unregisterlanguageResReceiver != null");
                LocalBroadcastManager.getInstance(this.y).unregisterReceiver(l2);
            }
        }
    }

    private void n() {
        this.dr.setCurrentItem(2, false);
        this.ai.a();
        this.dl.setItemChecked(2);
    }

    private void eg() {
        ReleaseLogUtil.b("Login_MainActivity", "enter showNoteHwidRunBackDialog:");
        this.ag = null;
        this.cm = null;
        HuaweiMobileServiceSetDialog.Builder builder = new HuaweiMobileServiceSetDialog.Builder(this.y);
        this.ag = builder;
        HuaweiMobileServiceSetDialog a2 = builder.a();
        this.cm = a2;
        a2.show();
    }

    private void dn() {
        this.dq.registerCallback(1);
    }

    private void fh() {
        this.dq.unRegisterCallback(1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ReleaseLogUtil.b("Login_MainActivity", "onBackPressed to enter");
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.dn >= 2000) {
            if (byd.e(this)) {
                if (this.bc != null) {
                    nrh.b(this.y, R.string._2130837848_res_0x7f020158);
                }
            } else {
                nrh.d(this.y, getString(R.string._2130837847_res_0x7f020157));
            }
            this.dn = currentTimeMillis;
            return;
        }
        LogUtil.c("Login_MainActivity", "onBackPressed finish MainActivity for cause: double click");
        HiHealthNativeApi.a(this.y).synCloudCancel();
        SharedPreferenceManager.e(this.y, Integer.toString(10000), "sync_cloud_data_show_process_flag", "false", (StorageParams) null);
        owq.a(false);
        MainInteractorsUtils.e(MainInteractorsUtils.c, this.cl);
        this.bt = true;
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        HomeFragment homeFragment;
        ReleaseLogUtil.b("Login_MainActivity", "onWindowFocusChanged : ", Boolean.valueOf(z));
        super.onWindowFocusChanged(z);
        this.bf = z;
        if (z && (homeFragment = this.bc) != null && homeFragment.m) {
            a();
        }
    }

    private void af() {
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        if (CommonUtil.z(this.y)) {
            messageCenterApi.initHms();
            return;
        }
        if (Utils.i()) {
            if (this.ay == null) {
                int a2 = new PackageManagerHelper(this.y).a(CommonUtil.p());
                LogUtil.c("Login_MainActivity", "connectHuaweiApiClient hms versionCode : ", Integer.valueOf(a2));
                if (a2 >= 30000000) {
                    messageCenterApi.initHms();
                }
            }
            LogUtil.c("Login_MainActivity", "connectHuaweiApiClient:");
            HuaweiApiClient huaweiApiClient = this.ay;
            if (huaweiApiClient != null) {
                if (huaweiApiClient.isConnecting() || this.ay.isConnected()) {
                    return;
                }
                this.ay.connect(this);
                return;
            }
            LogUtil.a("Login_MainActivity", "connectHuaweiApiClient mHuaweiApiClient is null.");
            return;
        }
        new gws().d(this.y);
    }

    private void Bv_(Intent intent) {
        Uri avu_ = fbi.avu_(intent);
        this.ad = avu_;
        if (avu_ != null) {
            LogUtil.c("Login_MainActivity", "initDeeplinkParams");
        }
    }

    private void By_(final Intent intent) {
        final Uri uri = this.ad;
        if (uri == null) {
            return;
        }
        if (!LoginInit.getInstance(this.y).isBrowseMode()) {
            this.ad = null;
            fbi.avw_(this, uri, intent);
            return;
        }
        IBaseResponseCallback browseCallback = LoginInit.getBrowseCallback();
        if (browseCallback != null) {
            LogUtil.a("Login_MainActivity", "jumpToDeepLinkTarget browsingToLogin callback=", browseCallback);
            if (intent == null || intent == getIntent()) {
                return;
            }
            setIntent(intent);
            return;
        }
        this.ad = null;
        LoginInit.getInstance(this.y).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.health.MainActivity.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.c("Login_MainActivity", "jumpToDeepLinkTarget browsingToLogin onResponse errorCode : ", Integer.valueOf(i2));
                fbi.avw_(MainActivity.this, uri, intent);
            }
        }, "");
    }

    private void Bx_(Intent intent) {
        if (intent == null) {
            LogUtil.a("Login_MainActivity", "initSportParams intent is null");
            return;
        }
        this.ce = intent.getBooleanExtra("isToSportTab", false);
        this.ca = intent.getBooleanExtra("openDiscover", false);
        this.bv = intent.getBooleanExtra("scrollDown", false);
        this.bz = "DEVICE".equals(intent.getStringExtra(Constants.HOME_TAB_NAME));
        if (!this.ca) {
            this.ca = "DISCOVER".equals(intent.getStringExtra(Constants.HOME_TAB_NAME));
        }
        if (this.ca) {
            boolean z = true;
            if (!intent.getBooleanExtra("jumpVipTabWithQrCode", false) && intent.getIntExtra("from", 7) != 1 && intent.getIntExtra("from", 7) != 2) {
                z = false;
            }
            this.cf = z;
        }
        this.cc = intent.getIntExtra("mLaunchSource", -1);
        if (!this.ce) {
            this.bu = false;
            this.e = 0;
        } else {
            this.bu = intent.getBooleanExtra("isSelected", false);
            this.e = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
        }
        if (!this.ce) {
            boolean equals = "SPORT".equals(intent.getStringExtra(Constants.HOME_TAB_NAME));
            this.ce = equals;
            if (equals) {
                this.e = a(intent.getIntExtra(BleConstants.SPORT_TYPE, 0));
                this.cc = 7;
            }
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: bya
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.this.e();
            }
        });
        LogUtil.c("Login_MainActivity", "initSportParams intent = ", intent);
    }

    public /* synthetic */ void e() {
        if (!this.ce && this.cc == 12) {
            MainInteractorsUtils.b(this.y, 1, 1);
        }
        if (this.cc == 12) {
            MainInteractorsUtils.d(this.y, 4, AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value());
        }
        if (this.cc == 13) {
            MainInteractorsUtils.d(this.y, 5, AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value());
            MainInteractorsUtils.a(this.y);
        }
    }

    private void e(boolean z) {
        try {
            slp e2 = slp.e();
            LogUtil.c("Login_MainActivity", "initNavigationBarView hwBlurEngine = ", e2);
            if (e2 != null) {
                LogUtil.c("Login_MainActivity", "initNavigationBarView isShowHwBlur = ", Boolean.valueOf(e2.c()));
                d(e2);
            }
            if (z) {
                ntd.b().cMD_(this.dl, false);
            }
        } catch (Exception unused) {
            LogUtil.e("Login_MainActivity", "initNavigationBarView Exception");
        }
        dj();
    }

    private void d(slp slpVar) {
        if (!slpVar.c() || this.dl == null) {
            return;
        }
        if (this.ba == null) {
            this.ba = new slr(this);
        }
        if (CommonUtil.ar() && !nsn.z(this.y)) {
            this.ba.c((nsn.ac(this.y) || isInMultiWindowMode()) ? false : true);
        }
        this.dl.setBlurEnable(true);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        LogUtil.c("Login_MainActivity", "onMultiWindowModeChanged");
        e(true);
        db();
        gow gowVar = this.dv;
        if (gowVar != null) {
            gowVar.d();
        }
        if (this.bw) {
            cx();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ba() {
        LogUtil.c("Login_MainActivity", "gotoSportTab");
        this.dr.setCurrentItem(1, false);
        SportEntranceFragment sportEntranceFragment = this.dg;
        if (sportEntranceFragment != null) {
            sportEntranceFragment.e();
        }
        this.bi = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (i2 == 0) {
            ixx.d().d(this.y, AnalyticsValue.HEALTH_HOME_TAB_2010038.value(), hashMap, 0);
            this.dm = System.currentTimeMillis();
        } else if (i2 == 2) {
            String value = AnalyticsValue.HEALTH_DISCOVER_FRAGMENT_2020028.value();
            hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, Integer.valueOf(gpo.b() ? 2 : 1));
            ixx.d().d(this.y, value, hashMap, 0);
            this.dm = System.currentTimeMillis();
            NavigationHelper navigationHelper = this.ch;
            if (navigationHelper != null && navigationHelper.d()) {
                this.ch.c(2);
            }
        } else if (i2 == 3) {
            ixx.d().d(this.y, AnalyticsValue.HEALTH_DEVICE_TAB_2010100.value(), hashMap, 0);
            this.dm = System.currentTimeMillis();
        } else if (i2 == 4) {
            ixx.d().d(this.y, AnalyticsValue.HEALTH_MINE_TAB_2040001.value(), hashMap, 0);
            this.dm = System.currentTimeMillis();
        } else {
            LogUtil.a("Login_MainActivity", "setBiInfoForTab position is other number");
        }
        int i3 = this.az;
        if (i2 != i3) {
            long j2 = (this.dm - this.f0do) - this.s;
            LogUtil.d("Login_MainActivity", "setBiInfoForTab mInitPosition = ", Integer.valueOf(i3), "--stayTime---", Long.valueOf(j2));
            this.s = 0L;
            HashMap hashMap2 = new HashMap();
            hashMap2.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, Integer.valueOf(this.az));
            hashMap2.put("duration", Long.valueOf(j2));
            if (j2 > 1000) {
                ixx.d().d(this.y, AnalyticsValue.HEALTH_TAB_STAY_TIME_2010071.value(), hashMap2, 0);
            }
            this.f0do = this.dm;
        }
        this.az = i2;
    }

    private void u() {
        nrw.b(this.y);
        if (!gwg.h() && !gwe.d(this.y)) {
            nrw.b(this.y, 1);
            ba();
            if (gwg.g(this.y)) {
                ej();
                return;
            }
            return;
        }
        if (nrw.a()) {
            if (gwg.g(this.y)) {
                el();
                return;
            } else {
                ba();
                return;
            }
        }
        if (nrw.e()) {
            if (gwg.j(this.y)) {
                el();
                return;
            }
            if (bv() || bu()) {
                bj();
                this.u.show();
                ba();
                return;
            } else {
                if (bs()) {
                    bj();
                    this.w.show();
                    ba();
                    return;
                }
                ba();
                return;
            }
        }
        if (nrw.c()) {
            if (gwg.f(this.y)) {
                el();
                return;
            } else {
                ba();
                return;
            }
        }
        ba();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bj() {
        if (this.v == null || this.u == null || this.w == null) {
            dx();
            du();
        }
    }

    private void dx() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.y);
        builder.e(this.y.getString(R.string._2130837688_res_0x7f0200b8)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.ba();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final NoTitleCustomAlertDialog e2 = builder.e();
        new NoTitleCustomAlertDialog.Builder(this.y).e(this.y.getString(R.string._2130837693_res_0x7f0200bd)).czC_(R.string._2130837695_res_0x7f0200bf, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (gwg.c(MainActivity.this.y)) {
                    nrw.b(MainActivity.this.y, 2);
                    MainActivity.this.ba();
                    MainActivity.this.ea();
                } else {
                    e2.show();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130837696_res_0x7f0200c0, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.ba();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ea() {
        if (bv()) {
            this.u.show();
        }
        SportEntranceFragment sportEntranceFragment = this.dg;
        if (sportEntranceFragment != null) {
            sportEntranceFragment.g();
        }
    }

    private void du() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.y);
        builder.e(this.y.getString(R.string._2130837694_res_0x7f0200be)).czC_(R.string._2130837695_res_0x7f0200bf, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nrw.b(MainActivity.this.y, 1);
                MainActivity.this.ba();
                if (MainActivity.this.dg != null) {
                    MainActivity.this.dg.g();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130837696_res_0x7f0200c0, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.ba();
                if (nrw.c(MainActivity.this.y) || !gyg.b(MainActivity.this.y) || cub.g(MainActivity.this.y)) {
                    if (cub.g(MainActivity.this.y)) {
                        MainActivity.this.w.show();
                    } else {
                        LogUtil.d("Login_MainActivity", "setDialogBuilder no google dialog");
                    }
                } else {
                    MainActivity.this.u.show();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog.Builder builder2 = new NoTitleCustomAlertDialog.Builder(this.y);
        NoTitleCustomAlertDialog.Builder builder3 = new NoTitleCustomAlertDialog.Builder(this.y);
        builder3.e(R.string._2130837845_res_0x7f020155).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder2.e(R.string._2130837844_res_0x7f020154).czC_(R.string._2130841133_res_0x7f020e2d, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.u = builder2.e();
        this.v = builder.e();
        this.w = builder3.e();
    }

    private void el() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.y);
        builder.e(R.string._2130843249_res_0x7f021671).czC_(R.string._2130837695_res_0x7f0200bf, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nrw.b(MainActivity.this.y, 0);
                MainActivity.this.ba();
                if (MainActivity.this.dg != null && MainActivity.this.dg.b()) {
                    MainActivity.this.dg.g();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130837696_res_0x7f0200c0, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nrw.e()) {
                    MainActivity.this.bj();
                    MainActivity.this.ei();
                } else {
                    MainActivity.this.ej();
                }
                MainActivity.this.ba();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        this.p = e2;
        e2.setCanceledOnTouchOutside(false);
        this.p.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ej() {
        if (SportSupportUtil.g()) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.y);
        builder.e(R.string._2130843247_res_0x7f02166f).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.MainActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        this.cp = e2;
        e2.setCanceledOnTouchOutside(false);
        this.cp.show();
    }

    private void g() {
        int c2 = HiDateUtil.c(SharedPreferenceManager.b(Integer.toString(10000), "health_app_beta_version_boot_time", 0L));
        if (HiDateUtil.c(System.currentTimeMillis()) == c2) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("boot_day", String.valueOf(c2));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_BETA_VERSION_BOOT_2129016.value(), linkedHashMap);
        SharedPreferenceManager.e(Integer.toString(10000), "health_app_beta_version_boot_time", System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fg() {
        SharedPreferences.Editor edit;
        String e2 = CommonUtil.e(getApplicationContext());
        boolean z = false;
        SharedPreferences sharedPreferences = getSharedPreferences("IndoorEquipServiceRunning" + e2, 0);
        if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        boolean z2 = sharedPreferences.getBoolean("IsIndoorEquipServiceRunning" + e2, false);
        long elapsedRealtime = SystemClock.elapsedRealtime() - sharedPreferences.getLong("elapsedRealtime", 0L);
        if (z2 && elapsedRealtime < 2000 && elapsedRealtime > 0) {
            z = true;
        }
        kzc.n().a(z);
        edit.putBoolean("IsIndoorEquipServiceRunning" + e2, kzc.n().q());
        edit.apply();
    }

    private void di() {
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.MainActivity.22
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                boolean z = false;
                if (!koq.e(objArr, 0)) {
                    LogUtil.e("Login_MainActivity", "null args!");
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    LogUtil.c("Login_MainActivity", "registerFloatBarStateObserver visibility:", Integer.valueOf(intValue));
                    MainActivity mainActivity = MainActivity.this;
                    if (intValue == 8 && (!mainActivity.bx || MainActivity.this.cn != MainActivity.this.ae)) {
                        z = true;
                    }
                    mainActivity.mIsPageNeedShowMinibar = z;
                    if (MainActivity.this.mIsPageNeedShowMinibar) {
                        MainActivity.this.showMinibar();
                    } else {
                        MainActivity.this.hideMinibar();
                    }
                }
            }
        }, "HEALTH_FLOAT_BAR_STATE_CHANGE");
    }

    /* loaded from: classes3.dex */
    static class j implements HiCommonListener {
        private final WeakReference<MainActivity> d;

        j(MainActivity mainActivity) {
            this.d = new WeakReference<>(mainActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            LogUtil.c("Login_MainActivity", "checkSyncStatus onSuccess");
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("Login_MainActivity", "checkSyncStatus onFailure errMsg = ", obj, " , errCode = ", Integer.valueOf(i));
            MainActivity mainActivity = this.d.get();
            if (mainActivity != null) {
                mainActivity.dz();
            }
        }
    }
}
