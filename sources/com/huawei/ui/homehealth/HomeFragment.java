package com.huawei.ui.homehealth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.constants.BundleTransferdDataContants;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.knit.model.mainpage.MainPageConfig;
import com.huawei.health.knit.model.mainpage.MainPageGroupConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager;
import com.huawei.hwdevice.mainprocess.receiver.SyncFitnessPrivateBroadcastReceiver;
import com.huawei.indoorequip.util.DownloadUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.SampleConfigUtils;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.ui.commonui.base.FragmentForViewPager;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.QueueDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.swiperefreshlayout.HealthSwipeRefreshLayout;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homehealth.HomeFragment;
import com.huawei.ui.homehealth.HomeFragmentUtil;
import com.huawei.ui.homehealth.achievementcard.AchievementCardData;
import com.huawei.ui.homehealth.adapter.HomeCardAdapter;
import com.huawei.ui.homehealth.functionsetcard.FunctionSetCardData;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardData;
import com.huawei.ui.homehealth.healthtrend.HealthTrendCardData;
import com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor;
import com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData;
import com.huawei.ui.homehealth.operationcard.OperationCardData;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import com.huawei.ui.homehealth.threecirclecard.IAnimationCompletedCallback;
import com.huawei.ui.homewear21.receiver.UpgradeMessageReceiver;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import com.huawei.ui.main.stories.healthzone.model.HasFollowListCallBack;
import com.huawei.ui.main.stories.recommendcloud.RecommendCloud;
import com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout;
import defpackage.cjn;
import defpackage.dpf;
import defpackage.drl;
import defpackage.dsl;
import defpackage.dum;
import defpackage.eab;
import defpackage.efb;
import defpackage.eil;
import defpackage.fbh;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.jeg;
import defpackage.jfu;
import defpackage.kch;
import defpackage.knl;
import defpackage.koq;
import defpackage.kts;
import defpackage.niv;
import defpackage.njn;
import defpackage.nrh;
import defpackage.nrv;
import defpackage.nsd;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntd;
import defpackage.oae;
import defpackage.oaf;
import defpackage.obb;
import defpackage.odd;
import defpackage.odo;
import defpackage.ohr;
import defpackage.ong;
import defpackage.opj;
import defpackage.oum;
import defpackage.oun;
import defpackage.ovg;
import defpackage.owi;
import defpackage.owl;
import defpackage.owo;
import defpackage.owq;
import defpackage.oxa;
import defpackage.pwj;
import defpackage.rbt;
import defpackage.siq;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class HomeFragment extends FragmentForViewPager {
    public static boolean d = false;
    private e af;
    private View ag;
    private CustomTitleBar ak;
    private odo.b al;
    private IBaseResponseCallback as;
    private boolean au;
    private ong ay;
    private KnitFragment ba;
    private LinearLayoutManager bb;
    private ViewGroup bc;
    private ImageView bh;
    private a bi;
    private View bj;
    private LinearLayout bk;
    private HealthRecycleView bl;
    private oun bm;
    private WifiScaleMultiUserInteractor bn;
    private HealthTextView bq;
    private LinearLayout br;
    public Context c;
    public odd e;
    public HealthSwipeRefreshLayout f;
    public Handler h;
    public InputBoxTemplate j;
    private HomeCardAdapter n;
    public boolean o;
    private LinearLayout p;
    private ImageView q;
    private oum s;
    private ImageView t;
    private ArrayList<AbstractBaseCardData> u;
    private ArrayList<AbstractBaseCardData> y;
    private HomeFragmentUtil.c z;
    public boolean m = false;
    public boolean i = true;
    public boolean g = true;

    /* renamed from: a, reason: collision with root package name */
    public boolean f9349a = true;
    public boolean b = false;
    private boolean ap = true;
    private final BroadcastReceiver w = new c();
    private DeviceInfo v = null;
    private DeviceCapability x = null;
    private boolean ao = false;
    private AchievementCardData l = null;
    private ohr aa = null;
    private ovg bp = null;
    private HealthHeadLinesCardData ae = null;
    private FunctionMenuCardData ad = null;
    private OperaMsgCardData an = null;
    private FunctionSetCardData ac = null;
    private HealthTrendCardData aj = null;
    private OperationCardData bd = null;
    private long az = 1000;
    private boolean ai = false;
    private final BroadcastReceiver bo = new UpgradeMessageReceiver();
    private boolean aq = false;
    private boolean aw = true;
    private int am = 0;
    private boolean at = true;
    private volatile boolean ax = false;
    private PermissionsResultAction bg = null;
    private final BroadcastReceiver r = new HomeFragmentUtil.InnerAw70BroadcastReceiver();
    private boolean av = true;
    private boolean ah = false;
    private boolean k = true;
    private Observer be = new Observer() { // from class: com.huawei.ui.homehealth.HomeFragment.3
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            char c2;
            boolean booleanValue;
            if (str == null) {
                return;
            }
            str.hashCode();
            switch (str.hashCode()) {
                case -118366297:
                    if (str.equals("com.huawei.plugin.account.login")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 625618188:
                    if (str.equals("com.huawei.plugin.account.logout")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1102093210:
                    if (str.equals("MAIN_GUIDE_VIEW_COMPLETED")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1513222050:
                    if (str.equals("EQUITY_AND_VIBRANT")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1618268251:
                    if (str.equals("ALLOW_VISIBLE_HINT_LOAD")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1655319770:
                    if (str.equals("PluginHealthModel")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 != 0) {
                if (c2 != 1) {
                    if (c2 == 2) {
                        LogUtil.c("UIHLH_HomeFragment", "initMarketing after main guide view hide");
                        HomeFragment.this.o = false;
                        HomeFragment.this.e();
                        if (HomeFragment.this.h != null) {
                            HomeFragment.this.h.sendEmptyMessage(103);
                        }
                        if (HomeFragment.this.aa != null) {
                            HomeFragment.this.aa.e(HomeFragment.this.o);
                            HomeFragment.this.aa.b();
                        }
                        if (HomeFragment.this.ai) {
                            ObserverManagerUtil.c("EQUITY_AND_VIBRANT", new Object[0]);
                            HomeFragment.this.ai = false;
                            return;
                        }
                        return;
                    }
                    if (c2 == 3) {
                        if (HomeFragment.this.bc != null && HomeFragment.this.bc.getVisibility() == 0) {
                            HomeFragment.this.ai = true;
                            LogUtil.c("UIHLH_HomeFragment", "main guide is showing, showDialog delay");
                            return;
                        } else {
                            QueueDialog.dequeShowing();
                            return;
                        }
                    }
                    if (c2 != 4) {
                        if (c2 != 5) {
                            return;
                        }
                        HomeFragment.this.c(str);
                        return;
                    } else {
                        if (objArr == null || objArr.length <= 0) {
                            return;
                        }
                        Object obj = objArr[0];
                        if (!(obj instanceof Boolean) || (booleanValue = ((Boolean) obj).booleanValue()) == HomeFragment.this.ar) {
                            return;
                        }
                        LogUtil.c("UIHLH_HomeFragment", "setAllowLoadInSetUserVisibleHint: ", Boolean.valueOf(booleanValue));
                        HomeFragment.this.a(booleanValue);
                        return;
                    }
                }
                if (HomeFragment.this.aa != null) {
                    HomeFragment.this.aa.d();
                }
            }
            if (HomeFragment.this.aj != null) {
                LogUtil.c("UIHLH_HomeFragment", "refresh HealthTrendCardData ");
                HomeFragment.this.aj.refreshCardData();
            }
            HomeFragment.this.c(str);
        }
    };
    private boolean ar = true;
    private final BroadcastReceiver bf = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.HomeFragment.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.e("UIHLH_HomeFragment", "mNonLocalBroadcastReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.c("UIHLH_HomeFragment", "mNonLocalBroadcastReceiver(): intent.getAction() = ", action);
            if (HomeFragment.this.cWl_(action, intent, this)) {
                return;
            }
            try {
                HomeFragment.this.cWe_(intent);
            } catch (ClassCastException unused) {
                LogUtil.e("UIHLH_HomeFragment", "DeviceInfo deviceInfo error");
            }
        }
    };
    private final BroadcastReceiver bw = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.HomeFragment.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            LogUtil.c("UIHLH_HomeFragment", "before onReceive WifiUserInfoBroadcast");
            if (intent != null && "com.huawei.health.action.ACTION_WIFI_USERINFO_ACTION".equals(intent.getAction())) {
                LogUtil.c("UIHLH_HomeFragment", "onReceive WifiUserInfoBroadcast");
                jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.14.5
                    @Override // java.lang.Runnable
                    public void run() {
                        HomeFragment.this.cWn_(intent, 0);
                    }
                });
            } else if (intent != null && "com.huawei.health.action.ACTION_WIFI_DEVICE_UNIT_ACTION".equals(intent.getAction())) {
                jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.14.3
                    @Override // java.lang.Runnable
                    public void run() {
                        HomeFragment.this.cWn_(intent, 1);
                    }
                });
            } else if (intent != null && "com.huawei.health.action.ACTION_WIFI_OTA_UPDATE_ACTION".equals(intent.getAction())) {
                HomeFragment.this.cWn_(intent, 2);
            } else {
                LogUtil.e("UIHLH_HomeFragment", "wifi info is other");
            }
        }
    };
    private BroadcastReceiver ab = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.HomeFragment.19
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ReleaseLogUtil.a("UIHLH_HomeFragment", "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                ReleaseLogUtil.a("UIHLH_HomeFragment", "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            ReleaseLogUtil.b("UIHLH_HomeFragment", "mDeviceStatusReceiver onReceive action :", action);
            action.hashCode();
            if (action.equals("com.huawei.health.action.BIND_DEVICE_SUCCESS")) {
                HomeFragment.this.cWd_(intent);
            } else if (action.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                HomeFragment.this.cWg_(intent);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Handler handler = this.h;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(103, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cWe_(Intent intent) {
        DeviceInfo deviceInfo;
        try {
            deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
        } catch (BadParcelableException unused) {
            LogUtil.e("UIHLH_HomeFragment", "mNonLocalBroadcastReceiver onReceive BadParcelableException");
            deviceInfo = null;
        }
        if (efb.a() && this.bl == null) {
            LogUtil.e("UIHLH_HomeFragment", "initView is not completed");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.e("UIHLH_HomeFragment", "deviceInfo is null");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.d("UIHLH_HomeFragment", "connectedChanged(): ", deviceInfo.getDeviceName(), ",state = ", Integer.valueOf(deviceConnectState));
        DeviceInfo f = oxa.a().f();
        this.v = f;
        if (f != null) {
            LogUtil.c("UIHLH_HomeFragment", "mCurrentDeviceInfo.getProductType():", Integer.valueOf(f.getProductType()));
            LogUtil.c("UIHLH_HomeFragment", "deviceInfo.getProductType():", Integer.valueOf(deviceInfo.getProductType()));
            if (a(deviceConnectState, this.v.getProductType(), deviceInfo.getProductType())) {
                return;
            }
        }
        if (deviceConnectState == 2) {
            this.ao = true;
            oae.c(this.c).f();
            LogUtil.e("UIHLH_HomeFragment", "connect change istoEsimOrWallet ==", Boolean.valueOf(this.at));
            if (this.at) {
                z();
            }
        } else {
            this.ao = false;
        }
        LogUtil.d("UIHLH_HomeFragment", "connectedChanged() mIsConnected:", Boolean.valueOf(this.ao));
    }

    private boolean a(int i, int i2, int i3) {
        return HomeFragmentUtil.e(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean cWl_(String str, Intent intent, BroadcastReceiver broadcastReceiver) {
        str.hashCode();
        if (str.equals("com.huawei.bone.action.open_gps")) {
            ba();
            broadcastReceiver.abortBroadcast();
            return true;
        }
        if (str.equals("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS")) {
            return true;
        }
        return cWf_(str, intent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean cWf_(String str, Intent intent) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1321688226:
                if (str.equals("com.huawei.health.fitness_detail_sync_success")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -995860066:
                if (str.equals("com.huawei.health.weight_detail_sync_success")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -19011148:
                if (str.equals("android.intent.action.LOCALE_CHANGED")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 286760081:
                if (str.equals("com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            LogUtil.c("UIHLH_HomeFragment", "FITNESS_DETAIL_SYNC_SUCCESS");
            DeviceInfo f = oae.c(this.c).f();
            if (f != null && f.getDeviceConnectState() == 2) {
                LogUtil.c("UIHLH_HomeFragment", "SYNC_SUCCESS device startNps");
                oxa.a().a(this.c);
            }
            return true;
        }
        if (c2 == 1) {
            LogUtil.c("UIHLH_HomeFragment", "WEIGHT_DETAIL_SYNC_SUCCESS");
            cWk_(intent);
            return true;
        }
        if (c2 == 2) {
            LogUtil.c("UIHLH_HomeFragment", "language changed!");
            jfu.n();
            return true;
        }
        if (c2 != 3) {
            return false;
        }
        if (ProcessUtil.b().equals(intent.getStringExtra("sendProcess"))) {
            LogUtil.c("UIHLH_HomeFragment", "update language ready!");
            jfu.n();
            HwDeviceReplyPhraseEngineManager.e().b();
        }
        return true;
    }

    public boolean a() {
        return this.aq;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        setTag("UIHLH_HomeFragment");
        an();
        ReleaseLogUtil.b("UIHLH_HomeFragment", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        ReleaseLogUtil.b("UIHLH_HomeFragment", "onAttach");
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.c = getActivity();
        View inflate = layoutInflater.inflate(R.layout.swipe_recycleview_layout, viewGroup, false);
        this.bj = inflate;
        cWh_(inflate);
        this.h = new HomeFragmentUtil.d(this);
        this.e = new odd(this, this.c);
        this.br = (LinearLayout) this.bj.findViewById(R.id.sync_root_layout);
        this.t = (ImageView) this.bj.findViewById(R.id.background1);
        this.q = (ImageView) this.bj.findViewById(R.id.background2);
        this.bh = (ImageView) this.bj.findViewById(R.id.maskView);
        this.p = (LinearLayout) this.bj.findViewById(R.id.container);
        ac();
        if (!efb.a()) {
            KnitFragment r = r();
            if (r != null) {
                getChildFragmentManager().beginTransaction().replace(R.id.knit_container_layout, r).commitNow();
            }
        } else {
            this.bj.findViewById(R.id.knit_container_layout).setVisibility(8);
        }
        ReleaseLogUtil.b("UIHLH_HomeFragment", "onCreateView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return this.bj;
    }

    private KnitFragment r() {
        MainPageGroupConfig c2 = eab.c(getContext(), "MainPageSectionsConfig.json");
        if (c2 == null) {
            LogUtil.e("UIHLH_HomeFragment", "addSubTagFragments cause pageGroupConfig is null!");
            return null;
        }
        ArrayList<MainPageConfig> pagesConfig = c2.getPagesConfig();
        if (koq.b(pagesConfig)) {
            LogUtil.e("UIHLH_HomeFragment", "addSubTagFragments cause pageConfigs is empty!");
            return null;
        }
        for (int i = 0; i < pagesConfig.size(); i++) {
            MainPageConfig mainPageConfig = pagesConfig.get(i);
            if (mainPageConfig == null) {
                LogUtil.e("UIHLH_HomeFragment", "addSubTagFragments cause pageConfig1 is null!");
            } else {
                int pageType = mainPageConfig.getPageType();
                int resPosId = mainPageConfig.getResPosId();
                String a2 = nrv.a(mainPageConfig);
                if (pageType == 18) {
                    Bundle arguments = getArguments();
                    arguments.putBoolean("isHomeFragment", true);
                    KnitFragment newInstance = KnitFragment.newInstance(a2, new BasePageResTrigger(getContext(), resPosId, null).setExtra(arguments));
                    this.ba = newInstance;
                    newInstance.setIsAllowResumeRefresh(false);
                    return this.ba;
                }
            }
        }
        return null;
    }

    private void at() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("sync_cloud_data_action");
            a aVar = new a();
            this.bi = aVar;
            BroadcastManagerUtil.bFz_(this.c, aVar, intentFilter);
        } catch (Exception unused) {
            LogUtil.e("UIHLH_HomeFragment", "registerSyncCloudDataReceiver Exception");
        }
    }

    private void cWh_(View view) {
        this.bg = new CustomPermissionAction(this.c) { // from class: com.huawei.ui.homehealth.HomeFragment.20
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("UIHLH_HomeFragment", "initHealthTitleBar() mQrCodeAction onGranted");
                HomeFragment.this.t();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("UIHLH_HomeFragment", "initHealthTitleBar() mQrCodeAction onDenied");
                HomeFragment.this.t();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("UIHLH_HomeFragment", "initHealthTitleBar() mQrCodeAction onForeverDenied");
                HomeFragment.this.t();
            }
        };
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.health_tab_titlebar);
        this.ak = customTitleBar;
        HomeFragmentUtil.b(customTitleBar, this.c);
        if (EnvironmentInfo.k()) {
            this.ak.setRightTextButtonVisibility(8);
        }
        bc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.c == null) {
            LogUtil.a("UIHLH_HomeFragment", "gotoScan: mContext is null");
            return;
        }
        try {
            this.c.startActivity(new Intent(this.c, (Class<?>) QrCodeScanningActivity.class));
        } catch (ActivityNotFoundException e2) {
            LogUtil.e("UIHLH_HomeFragment", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private void bc() {
        this.ak.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.HomeFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArrayList arrayList = new ArrayList(Arrays.asList(HomeFragment.this.getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add), HomeFragment.this.getResources().getString(R.string.IDS_hw_device_manager_add_device)));
                if (!niv.a(HomeFragment.this.c) && pwj.e().a()) {
                    arrayList.add(HomeFragment.this.getResources().getString(R.string.IDS_device_data_source));
                }
                new PopViewList(HomeFragment.this.c, HomeFragment.this.ak, arrayList).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.ui.homehealth.HomeFragment.18.2
                    @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                    public void setOnClick(int i) {
                        if (i == 0) {
                            HomeFragmentUtil.d(HomeFragment.this.c, HomeFragment.this.bg);
                        } else if (i == 1) {
                            HomeFragment.this.h();
                        } else {
                            if (i != 2) {
                                return;
                            }
                            pwj.e().a(HomeFragment.this.c, 3);
                        }
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.c == null) {
            LogUtil.e("UIHLH_HomeFragment", "addDevice error: context is null");
        } else {
            ixx.d().d(this.c, AnalyticsValue.HEALTH_HEALTH_MY_DEVICE_2030030.value(), new HashMap(), 0);
            obb.c(this);
        }
    }

    private void p() {
        this.z = new HomeFragmentUtil.c(this.c);
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            EventBus.d(this.z, 2, "evebus_weight_measure_notification", "multi_user_auto_cancle_dialog");
            this.bn = new WifiScaleMultiUserInteractor(this.c);
        }
    }

    private void w() {
        this.f = (HealthSwipeRefreshLayout) this.bj.findViewById(R.id.swiperefreshlayout);
        this.ag = this.bj.findViewById(R.id.headline_tips);
        this.f.setPadding(0, 0, 0, 0);
        if (efb.a()) {
            this.bl = (HealthRecycleView) this.bj.findViewById(R.id.id_recyclerview);
        }
        this.bk = (LinearLayout) this.bj.findViewById(R.id.sync_cloud_data_layout);
        this.bq = (HealthTextView) this.bj.findViewById(R.id.sync_progress_percent);
        if (efb.a()) {
            HealthRecycleView healthRecycleView = this.bl;
            if (healthRecycleView == null) {
                LogUtil.a("UIHLH_HomeFragment", "initListView mRecyclerView == null");
                return;
            }
            healthRecycleView.a(false);
            this.bl.d(false);
            ScrollUtil.cKy_(this.bl, getActivity().getWindow().getDecorView(), 3001, new d(this));
        }
    }

    private void q() {
        HomeFragmentUtil.cWw_(this.c, this.h, this.l);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onActivityCreated(bundle);
        w();
        ReleaseLogUtil.b("UIHLH_HomeFragment", "onActivityCreated, initLayout finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void an() {
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.BIND_DEVICE_SUCCESS");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.ab, intentFilter, LocalBroadcast.c, null);
    }

    private void be() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.ab);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("UIHLH_HomeFragment", "DeviceStatusBroadcast not register before");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cWg_(Intent intent) {
        try {
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
            if (deviceInfo == null) {
                LogUtil.a("UIHLH_HomeFragment", "mDeviceStatusReceiver deviceInfo is null.");
                return;
            }
            if (deviceInfo.getDeviceConnectState() == 2) {
                LogUtil.c("UIHLH_HomeFragment", "mDeviceStatusReceiver connectState is connected");
                c(deviceInfo);
                if (isResumed()) {
                    b(deviceInfo);
                }
                az();
                SampleConfigUtils.resendAsyncWhenReconnect(deviceInfo, "UIHLH_HomeFragment");
                drl.e(null);
            }
        } catch (ClassCastException unused) {
            LogUtil.e("UIHLH_HomeFragment", "mDeviceStatusReceiver ClassCastException.");
        }
    }

    private void az() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ocx
            @Override // java.lang.Runnable
            public final void run() {
                HomeFragment.c();
            }
        });
    }

    public static /* synthetic */ void c() {
        if (BloodPressureSyncManager.e()) {
            BloodPressureSyncManager.c().d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cWd_(Intent intent) {
        try {
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
            if (deviceInfo == null) {
                ReleaseLogUtil.a("UIHLH_HomeFragment", "mDeviceStatusReceiver bind deviceInfo is null.");
                return;
            }
            if (deviceInfo.getDeviceConnectState() == 2) {
                ReleaseLogUtil.b("UIHLH_HomeFragment", "mDeviceStatusReceiver bind success, connectState is connected");
                PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
                if (payApi != null) {
                    payApi.activateDeviceBenefitPage(njn.e(deviceInfo, DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL, true));
                } else {
                    ReleaseLogUtil.a("UIHLH_HomeFragment", "activateDeviceBenefitPage failed, payApi is null");
                }
            }
        } catch (ClassCastException unused) {
            ReleaseLogUtil.c("UIHLH_HomeFragment", "mDeviceStatusReceiver bind ClassCastException.");
        }
    }

    private void b(DeviceInfo deviceInfo) {
        HomeFragmentUtil.b(this.c, deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo) {
        HomeFragmentUtil.e(this.c, getActivity(), deviceInfo);
    }

    private void k() {
        HomeFragmentUtil.cWx_(this.h, this.c);
    }

    private void u() {
        if (efb.a()) {
            this.y = new ArrayList<>();
            this.u = new ArrayList<>();
            this.n = new HomeCardAdapter(this.c, this.y);
            this.bm = new oun(this.c, this);
            this.s = new oum(this.c, this.t, this.q, this.p, this.bh, this.f);
            odo.b(this.bl, this.c, this.n);
            s();
            odo.b bVar = new odo.b();
            this.al = bVar;
            bVar.f(this.bp);
            HomeFragmentUtil.e(this.al, this.l, this.ac, this.ad, this.aa, this.bd, this.u, this.y, this.an, this.au, this.aj, this.ae);
            odo.c(this.c, this.al, this.bl, this.n);
        }
    }

    private void af() {
        this.bl.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.ui.homehealth.HomeFragment.17
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (!recyclerView.canScrollVertically(1) && System.currentTimeMillis() - HomeFragment.this.az > 2000) {
                    HomeFragment.this.az = System.currentTimeMillis();
                }
                if (i == 1) {
                    owi.b(HomeFragment.this.c, 1);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (recyclerView.canScrollVertically(1)) {
                    return;
                }
                owi.e(HomeFragment.this.c);
            }
        });
    }

    private void c(final DeviceInfo deviceInfo) {
        if (CommonUtil.x(this.c)) {
            LogUtil.c("UIHLH_HomeFragment", "setThreadPoolManager isBackground");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.16
                @Override // java.lang.Runnable
                public void run() {
                    boolean hasEmergencyInfoProvider = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).hasEmergencyInfoProvider();
                    String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "emergency_info");
                    LogUtil.c("UIHLH_HomeFragment", "mDeviceStatusReceiver emergencyInfoStatus:", b);
                    if (!hasEmergencyInfoProvider && TextUtils.isEmpty(b)) {
                        LogUtil.c("UIHLH_HomeFragment", "mDeviceStatusReceiver connectState less isEmui110");
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "emergency_info", "true", new StorageParams());
                    }
                    if (hasEmergencyInfoProvider) {
                        LogUtil.c("UIHLH_HomeFragment", "mDeviceStatusReceiver connectState isEmui110");
                        if (TextUtils.isEmpty(b)) {
                            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "emergency_info", "false", new StorageParams());
                        } else {
                            if (Boolean.parseBoolean(b)) {
                                if (LoginInit.getInstance(HomeFragment.this.c).isBrowseMode()) {
                                    return;
                                }
                                HomeFragment.this.h.post(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.16.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        HomeFragment.this.e(deviceInfo);
                                    }
                                });
                                return;
                            }
                            LogUtil.a("UIHLH_HomeFragment", "mDeviceStatusReceiver connectState is else");
                        }
                    }
                }
            });
        }
    }

    /* renamed from: com.huawei.ui.homehealth.HomeFragment$23, reason: invalid class name */
    public class AnonymousClass23 implements Runnable {
        AnonymousClass23() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (HomeFragment.this.c != null && HomeFragment.this.getActivity() != null && !HomeFragment.this.getActivity().isFinishing()) {
                HomeFragment.this.ak();
                if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
                    HomeFragment.this.au();
                }
                if (CommonUtil.ce()) {
                    HomeFragment.this.av();
                    HomeFragment.this.ax();
                    jfu.f();
                    HomeFragment.this.al();
                    oaf.b(HomeFragment.this.c).c();
                }
                HomeFragment.this.y();
                dum.d().c(new IBaseResponseCallback() { // from class: odf
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        HomeFragment.AnonymousClass23.this.d(i, obj);
                    }
                });
                return;
            }
            LogUtil.a("UIHLH_HomeFragment", "initExecutors Activity is destroyed");
        }

        public /* synthetic */ void d(int i, Object obj) {
            if (i == 6) {
                HomeFragment.this.am();
            }
        }
    }

    private void x() {
        ThreadPoolManager.d().c("UIHLH_HomeFragment", new AnonymousClass23());
    }

    private void s() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.an = new OperaMsgCardData(this.c);
        this.l = new AchievementCardData(this.c);
        a(oun.a());
        this.aa = new ohr(this.c, this.o);
        this.ac = new FunctionSetCardData(this.c);
        this.bd = new OperationCardData(this.c);
        if (efb.o()) {
            this.ad = new FunctionMenuCardData();
        }
        if (efb.m()) {
            this.aj = new HealthTrendCardData(this.c);
        }
        if (!this.au) {
            this.ae = new HealthHeadLinesCardData(this.c);
        }
        ReleaseLogUtil.b("UIHLH_HomeFragment", "initCardConstructor finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void a(String str) {
        ovg ovgVar = new ovg(this.c, str, this.bm);
        this.bp = ovgVar;
        odo.e(ovgVar, this.c);
    }

    public void b(final String str, boolean z) {
        LogUtil.c("UIHLH_HomeFragment", "notifyStepCardChanged currentType: ", str);
        if (!ad() && d) {
            LogUtil.c("UIHLH_HomeFragment", "pull refreshing, not support to switch");
            if (nsn.aa(this.c)) {
                Context context = this.c;
                nrh.c(context, context.getResources().getString(R.string._2130847211_res_0x7f0225eb));
                return;
            }
            return;
        }
        final int i = 0;
        if (!z) {
            LogUtil.c("UIHLH_HomeFragment", "switch without Animation");
            if ("threeCircleCard".equals(str)) {
                this.bp.d("threeLeafCard", false);
            } else {
                this.bp.d("threeCircleCard", false);
            }
            c(str, false);
            return;
        }
        this.bp.c().d(str);
        this.f.setInterceptTouchEvent(true);
        LogUtil.c("UIHLH_HomeFragment", "mCardData: ", this.y);
        View findViewById = ((Activity) this.c).findViewById(R.id.home_item_three_circle_card_layout);
        if (findViewById != null) {
            int[] iArr = new int[2];
            findViewById.getLocationOnScreen(iArr);
            i = (iArr[1] - this.ak.getHeight()) - this.br.getHeight();
        }
        if ("threeCircleCard".equals(str)) {
            this.s.a(this.bl, this.bp, i, new IAnimationCompletedCallback() { // from class: com.huawei.ui.homehealth.HomeFragment.5
                @Override // com.huawei.ui.homehealth.threecirclecard.IAnimationCompletedCallback
                public void onCompleted(IAnimationCompletedCallback iAnimationCompletedCallback) {
                    HomeFragment.this.b(str, i);
                }
            });
        } else if ("threeLeafCard".equals(str)) {
            this.s.a(this.bl, this.bp, i, new IAnimationCompletedCallback() { // from class: com.huawei.ui.homehealth.HomeFragment.2
                @Override // com.huawei.ui.homehealth.threecirclecard.IAnimationCompletedCallback
                public void onCompleted(IAnimationCompletedCallback iAnimationCompletedCallback) {
                    HomeFragment.this.a(str, i);
                }
            });
        } else {
            health.compact.a.util.LogUtil.d("UIHLH_HomeFragment", "card type is error");
        }
    }

    private boolean ad() {
        int i;
        HealthRecycleView healthRecycleView = this.bl;
        if (healthRecycleView != null) {
            int[] iArr = new int[2];
            healthRecycleView.getLocationOnScreen(iArr);
            i = iArr[1];
        } else {
            i = 0;
        }
        CustomTitleBar customTitleBar = this.ak;
        if (customTitleBar != null) {
            i -= customTitleBar.getHeight();
        }
        return i <= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        this.s.d(this.bl, this.bp, i, str);
        c(str, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i) {
        this.s.d(this.bl, this.bp, i, str);
        c(str, true);
    }

    private void c(String str, boolean z) {
        if (!"threeLeafCard".equals(str)) {
            this.bm.b("threeLeafCard");
        } else {
            this.bm.b("threeCircleCard");
        }
        this.bm.c(str);
        ixx.d().a(LoginInit.getInstance(this.c).getAccountInfo(1011));
        e(str, z);
    }

    private void e(String str, boolean z) {
        Message obtain = Message.obtain();
        obtain.what = 104;
        obtain.obj = str;
        Handler handler = this.h;
        if (handler != null) {
            handler.sendMessageDelayed(obtain, z ? 1700L : 0L);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.c("UIHLH_HomeFragment", "Enter onActivityResult requestcode:", Integer.valueOf(i), ";resultcode:", Integer.valueOf(i2));
        super.onActivityResult(i, i2, intent);
        if (i == 101) {
            LogUtil.c("UIHLH_HomeFragment", "Enter checkGpsPermission");
            l();
        }
        if (i == 102) {
            obb.d(this.c);
        }
    }

    private void v() {
        this.f.setRefreshPushText(this.c.getResources().getString(R.string._2130837653_res_0x7f020095));
        this.f.setCallback(new HwSwipeRefreshLayout.Callback() { // from class: com.huawei.ui.homehealth.HomeFragment.1
            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public boolean needToWait() {
                return true;
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public boolean isEnabled() {
                boolean isBrowseMode = LoginInit.getInstance(HomeFragment.this.c).isBrowseMode();
                HomeFragment homeFragment = HomeFragment.this;
                homeFragment.ax = homeFragment.ax || !(isBrowseMode || owq.e());
                if (!efb.a()) {
                    if (HomeFragment.this.ax && HomeFragment.this.ba.getHealthScrollView() != null) {
                        HomeFragment homeFragment2 = HomeFragment.this;
                        homeFragment2.ax = homeFragment2.ba.getHealthScrollView().getScrollY() == 0;
                    }
                    return HomeFragment.this.ax;
                }
                return HomeFragment.this.ax;
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public void onRefreshStart() {
                LogUtil.c("UIHLH_HomeFragment", "onRefreshStart");
                HomeFragment.d = true;
                if (HomeFragment.this.h.hasMessages(1006)) {
                    LogUtil.c("UIHLH_HomeFragment", "card data refresh not finished");
                    return;
                }
                kts.d(2);
                long currentTimeMillis = System.currentTimeMillis();
                HomeFragmentUtil.a();
                HomeFragment.this.am();
                HomeFragment.this.ae();
                HomeFragment.this.ag();
                LogUtil.c("UIHLH_HomeFragment", " start to send Message UPDATE_UI_STOP_REFRESH_LAYOUT");
                Message obtainMessage = HomeFragment.this.h.obtainMessage();
                obtainMessage.what = 1004;
                if (HomeFragment.this.au) {
                    LocalBroadcastManager.getInstance(HomeFragment.this.c).sendBroadcast(new Intent("com.huawei.sync_activity_to_third_platform"));
                }
                HomeFragment.this.h.sendMessageDelayed(obtainMessage, 120000L);
                LogUtil.c("UIHLH_HomeFragment", "onRefreshStart cost time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public void onScrollUp() {
                HomeFragment.this.ax = false;
            }
        });
    }

    private void bd() {
        LogUtil.c("UIHLH_HomeFragment", "syncBloodSugarTask");
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), new Intent("com.huawei.health.action.SYNC_BLOOD_SUGAR_DATA"));
    }

    private void ac() {
        if (this.ak == null) {
            return;
        }
        dpf.Yn_(this.h, 4036);
        this.ak.setRightSoftkeyVisibility(dpf.e() ? 0 : 8);
        nsy.cMh_(this.ak.getRightSoftKey(), 1.0f);
        this.ak.setRightSoftkeyBackground(ContextCompat.getDrawable(this.c, R.drawable._2131431370_res_0x7f0b0fca), nsf.h(R.string._2130847322_res_0x7f02265a));
        this.ak.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.HomeFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                fbh.d(HomeFragment.this.c, 4036, HomeFragment.this.j);
                dpf.a(HomeFragment.this.c, HomeFragment.this.j);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void c(boolean z) {
        this.av = z;
    }

    public boolean b() {
        HealthRecycleView healthRecycleView = this.bl;
        if (healthRecycleView == null) {
            return false;
        }
        return healthRecycleView.getScrollState() != 0 || this.bl.isComputingLayout();
    }

    public void i() {
        LogUtil.c("UIHLH_HomeFragment", "showTodoBtn mIsShowAdNow:", Boolean.valueOf(this.g));
        this.g = false;
        Handler handler = this.h;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(103, 50L);
            this.h.sendEmptyMessageDelayed(FitnessSleepType.HW_FITNESS_WAKE, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        LogUtil.c("UIHLH_HomeFragment", "refreshWearSdkData");
        if (this.as == null) {
            this.as = new HomeFragmentUtil.g(this);
        }
        oxa.a().b();
        dum.d().e(0, this.as);
        bd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        String e2 = HomeFragmentUtil.e();
        if (TextUtils.isEmpty(e2)) {
            LogUtil.e("UIHLH_HomeFragment", "push press,device mac is null");
            return;
        }
        DeviceCapability e3 = DeviceSettingsInteractors.d(this.c).e(e2);
        this.x = e3;
        HomeFragmentUtil.a(e3, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        String e2 = HomeFragmentUtil.e();
        if (TextUtils.isEmpty(e2)) {
            LogUtil.a("UIHLH_HomeFragment", "push weather,device mac is null");
            return;
        }
        DeviceCapability e3 = DeviceSettingsInteractors.d(this.c).e(e2);
        this.x = e3;
        HomeFragmentUtil.d(e3, this.c);
    }

    public void g() {
        HealthRecycleView healthRecycleView;
        HealthTrendCardData healthTrendCardData;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Map<String, Integer> c2 = odo.c(this.c);
        AchievementCardData achievementCardData = this.l;
        if (achievementCardData != null) {
            achievementCardData.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.ACHIEVEMENT_CARD.getName()).intValue());
        }
        this.ac.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.FUNCTION_SET_CARD.getName()).intValue());
        FunctionMenuCardData functionMenuCardData = this.ad;
        if (functionMenuCardData != null) {
            functionMenuCardData.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.FUNCTION_MENU_CARD.getName()).intValue());
        }
        this.aa.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.DIALOG_CARD.getName()).intValue());
        this.bd.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.OPERATION_CARD.getName()).intValue());
        this.an.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.OPERA_MSG_CARD.getName()).intValue());
        if (efb.m() && (healthTrendCardData = this.aj) != null) {
            healthTrendCardData.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.HEALTH_TREND_CARD.getName()).intValue());
        }
        if (!this.au) {
            this.ae.setCardPositionTag(c2.get(CardFlowInteractors.CardNameConstants.FUNCTION_SET_CARD.getName()).intValue() - 1);
        }
        this.y.clear();
        this.y.addAll(this.u);
        HomeCardAdapter homeCardAdapter = this.n;
        if (homeCardAdapter != null && (healthRecycleView = this.bl) != null) {
            LogUtil.c("UIHLH_HomeFragment", "updateCardSort() mAdapter = ", homeCardAdapter, " mRecyclerView = ", healthRecycleView, " mCardData.size = ", Integer.valueOf(this.y.size()));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.c);
            this.bb = linearLayoutManager;
            this.bl.setLayoutManager(linearLayoutManager);
            this.bl.getItemAnimator().setChangeDuration(0L);
            this.n.b(this.y);
            this.bl.setAdapter(this.n);
        }
        ReleaseLogUtil.b("UIHLH_HomeFragment", "updateCardSort finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void e() {
        if (this.o || this.g || this.ah) {
            return;
        }
        Context context = getContext();
        if (context == null) {
            LogUtil.a("UIHLH_HomeFragment", "initMarketing context is null");
        } else if (HomeFragmentUtil.b(context)) {
            this.ah = true;
        }
    }

    private void ap() {
        boolean z = getArguments().getBoolean(BundleTransferdDataContants.IS_SHOW_GUIDE);
        this.o = z;
        if (z) {
            ObserverManagerUtil.d(this.be, "MAIN_GUIDE_VIEW_COMPLETED");
        }
    }

    private void aj() {
        HomeFragmentUtil.c(this.be);
    }

    private void as() {
        ObserverManagerUtil.d(this.be, "com.huawei.plugin.account.login");
        ObserverManagerUtil.d(this.be, "com.huawei.plugin.account.logout");
    }

    private void ao() {
        if (dsl.c() == null) {
            ObserverManagerUtil.d(this.be, "PluginHealthModel");
        }
    }

    private void ar() {
        try {
            ObserverManagerUtil.d(this.be, "EQUITY_AND_VIBRANT");
        } catch (IllegalArgumentException | IllegalStateException unused) {
            LogUtil.e("UIHLH_HomeFragment", "registerEquityVibrantObserver exception");
        }
    }

    private void bk() {
        try {
            ObserverManagerUtil.e(this.be, "EQUITY_AND_VIBRANT");
        } catch (IllegalArgumentException | IllegalStateException unused) {
            LogUtil.e("UIHLH_HomeFragment", "UnregisterEquityVibrantObserver exception");
        }
    }

    private void bg() {
        ObserverManagerUtil.e(this.be, "MAIN_GUIDE_VIEW_COMPLETED");
    }

    private void bf() {
        HomeFragmentUtil.d(this.be);
    }

    private void bi() {
        ObserverManagerUtil.e(this.be, "com.huawei.plugin.account.login");
        ObserverManagerUtil.e(this.be, "com.huawei.plugin.account.logout");
    }

    private void bh() {
        ObserverManagerUtil.e(this.be, "PluginHealthModel");
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onConfigurationChanged(configuration);
        if (efb.a()) {
            ovg ovgVar = this.bp;
            if (ovgVar != null) {
                ovgVar.onConfigurationChanged(configuration);
            }
            FunctionSetCardData functionSetCardData = this.ac;
            if (functionSetCardData != null) {
                functionSetCardData.onConfigurationChanged(configuration);
            }
            FunctionMenuCardData functionMenuCardData = this.ad;
            if (functionMenuCardData != null) {
                functionMenuCardData.onConfigurationChanged(configuration);
            }
            ohr ohrVar = this.aa;
            if (ohrVar != null) {
                ohrVar.onConfigurationChanged(configuration);
            }
            OperationCardData operationCardData = this.bd;
            if (operationCardData != null) {
                operationCardData.onConfigurationChanged(configuration);
            }
        }
        ReleaseLogUtil.b("UIHLH_HomeFragment", "onConfigurationChanged, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void ay() {
        HomeFragmentUtil.cWz_(this.h);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.c("UIHLH_HomeFragment", "onPause ", this);
        if (efb.a()) {
            ohr ohrVar = this.aa;
            if (ohrVar != null) {
                ohrVar.h();
            }
            ovg ovgVar = this.bp;
            if (ovgVar != null) {
                ovgVar.g();
            }
        }
        HomeFragmentUtil.g(this.c);
    }

    public void cWo_(ViewGroup viewGroup) {
        this.bc = viewGroup;
    }

    @Override // com.huawei.ui.commonui.base.FragmentForViewPager
    public void loadAfterViewPagerOnResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        DownloadUtil.b(new HomeFragmentUtil.b()).bVQ_(getActivity(), false);
        ap();
        aq();
        aj();
        as();
        ao();
        ar();
        HomeFragmentUtil.c();
        this.au = Utils.o();
        at();
        FragmentActivity activity = getActivity();
        this.c = activity;
        if (activity == null) {
            LogUtil.e("UIHLH_HomeFragment", "onActivityCreated mContext== null");
            return;
        }
        p();
        if (!efb.a()) {
            owl.b(this.c).a();
        }
        u();
        this.aq = true;
        v();
        x();
        aa();
        ntd.b().cME_(this, this.bj);
        if (HomeFragmentUtil.e.b(BaseApplication.getContext())) {
            Context context = this.c;
            b(context, LoginInit.getInstance(context).getAccountInfo(1011));
            HomeFragmentUtil.e.d();
        }
        ReleaseLogUtil.b("UIHLH_HomeFragment", "loadAfterViewPagerOnResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* renamed from: com.huawei.ui.homehealth.HomeFragment$7, reason: invalid class name */
    public class AnonymousClass7 implements Observer {
        AnonymousClass7() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!koq.e(objArr, 1)) {
                LogUtil.e("UIHLH_HomeFragment", "null args!");
                return;
            }
            Object obj = objArr[0];
            if ((obj instanceof Integer) && (objArr[1] instanceof Integer)) {
                final int intValue = ((Integer) obj).intValue();
                final int intValue2 = ((Integer) objArr[1]).intValue();
                HandlerExecutor.a(new Runnable() { // from class: ocy
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeFragment.AnonymousClass7.this.e(intValue, intValue2);
                    }
                });
            }
        }

        public /* synthetic */ void e(int i, int i2) {
            if (i == -1 && i2 == -1) {
                LogUtil.c("UIHLH_HomeFragment", "mHeadlinesTipsView should gone!");
                nsy.cMA_(HomeFragment.this.ag, 8);
                return;
            }
            LogUtil.c("UIHLH_HomeFragment", "mHeadlinesTipsView should be visible!");
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) HomeFragment.this.ag.getLayoutParams();
            layoutParams.leftMargin = i - nsn.c(HomeFragment.this.c, 86.0f);
            layoutParams.topMargin = i2;
            HomeFragment.this.ag.setLayoutParams(layoutParams);
            HomeFragment.this.ag.setVisibility(0);
        }
    }

    private void aq() {
        if (this.k) {
            ObserverManagerUtil.d(new AnonymousClass7(), "SHOW_HEALTH_HEADLINES_TIPS");
            ObserverManagerUtil.d(new AnonymousClass8(), "HOME_RECYCLE_VIEW_MOVE");
            this.k = false;
        }
    }

    /* renamed from: com.huawei.ui.homehealth.HomeFragment$8, reason: invalid class name */
    public class AnonymousClass8 implements Observer {
        AnonymousClass8() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!koq.e(objArr, 0)) {
                LogUtil.c("UIHLH_HomeFragment", "null args!");
                return;
            }
            Object obj = objArr[0];
            if ((obj instanceof Boolean) && !((Boolean) obj).booleanValue()) {
                HandlerExecutor.a(new Runnable() { // from class: odb
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeFragment.AnonymousClass8.this.e();
                    }
                });
            }
        }

        public /* synthetic */ void e() {
            HomeFragment.this.bl.scrollToPosition(0);
        }
    }

    private void aa() {
        if (efb.a()) {
            af();
        }
        cjn.e(this.c).c();
        q();
        if (!this.au) {
            bb();
        }
        if (this.au && Utils.i()) {
            siq.a().b();
        }
        k();
        Handler handler = this.h;
        handler.sendMessageDelayed(handler.obtainMessage(FitnessSleepType.HW_FITNESS_WAKE), 1000L);
        ah();
        if (!Utils.g()) {
            ab();
        }
        if (getUserVisibleHint()) {
            if (this.ay == null) {
                this.ay = new ong();
            }
            this.ay.a(this.au);
            this.ay.d();
        }
        owo.d(this.c).djc_(this.h);
        cjn.e(this.c).b();
        m();
        ay();
        this.h.sendEmptyMessageDelayed(103, 50L);
        if (getActivity() != null) {
            this.f9349a = nsd.a("FRAGMENT_SHOW_NOVA");
        }
    }

    @Override // com.huawei.ui.commonui.base.FragmentForViewPager
    public void normalOnResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (getUserVisibleHint() && this.av) {
            nsn.e(this.c, 0);
        }
        ntd.b().cME_(this, this.bj);
        this.av = true;
        if (getUserVisibleHint() || CardFlowInteractors.a(this.c, "HomeCardRefreshIndex", -2) == -1) {
            LogUtil.c("UIHLH_HomeFragment", "!mIsFirstCreate");
            opj.b(-1);
            d();
            e(true);
        } else if (efb.a()) {
            HomeFragmentUtil.b(this.bp);
            HomeFragmentUtil.b(this.aa);
        } else {
            LogUtil.c("UIHLH_HomeFragment", "condition is invalid");
        }
        if (efb.a() && !this.au) {
            HomeFragmentUtil.b(this.ae);
        }
        ah();
        cjn.e(this.c).b();
        m();
        ay();
        this.h.sendEmptyMessageDelayed(103, 50L);
        FunctionMenuCardData functionMenuCardData = this.ad;
        if (functionMenuCardData != null) {
            functionMenuCardData.j();
        }
        LogUtil.c("UIHLH_HomeFragment", "normalOnResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.ui.commonui.base.FragmentForViewPager, com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        LogUtil.c("UIHLH_HomeFragment", "setUserVisibleHint begin, visible:", Boolean.valueOf(z));
        super.setUserVisibleHint(z);
        this.m = z;
        this.i = true;
        if (isResumed()) {
            cjn.e(this.c).b(z);
        }
        b(z);
        Handler handler = this.h;
        if (handler != null) {
            handler.sendEmptyMessage(103);
        }
        if (getView() != null) {
            eil.alV_(getActivity().getWindow().getDecorView(), z, 3001, "Health");
        }
        if (z) {
            this.b = true;
        } else {
            this.b = false;
        }
        e(z);
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.6
            @Override // java.lang.Runnable
            public void run() {
                ObserverManagerUtil.c("HOME_FRAGMENT_IS_VISIBLE_TO_USER", Boolean.valueOf(z));
            }
        }, 500L);
        LogUtil.c("UIHLH_HomeFragment", "setUserVisibleHint finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void e(boolean z) {
        if (this.ad == null) {
            return;
        }
        LogUtil.c("UIHLH_HomeFragment", "handleFunctionMenuGifs isVisibleToUser = ", Boolean.valueOf(z));
        if (z) {
            this.ad.a();
        } else {
            this.ad.f();
        }
    }

    private void b(boolean z) {
        InputBoxTemplate inputBoxTemplate;
        if (z && this.c != null) {
            LogUtil.c("UIHLH_HomeFragment", "load in setUserVisibleHint");
            if (efb.a()) {
                HomeFragmentUtil.b(this.ac);
            }
            o();
            if (this.av) {
                nsn.e(this.c, 0);
            }
            this.av = true;
            if (this.ap && (inputBoxTemplate = this.j) != null) {
                fbh.e(this.c, 4036, inputBoxTemplate);
            }
            if (this.ap) {
                LogUtil.c("UIHLH_HomeFragment", "initMarketing in first visible");
                e();
            }
            this.ap = false;
            return;
        }
        Handler handler = this.h;
        if (handler != null) {
            handler.removeMessages(FitnessSleepType.HW_FITNESS_WAKE);
        }
    }

    public void f() {
        nsy.cMA_(this.ag, 8);
    }

    private void o() {
        HomeFragmentUtil.e(getContext());
    }

    private void ab() {
        if (!CommonUtil.aa(this.c)) {
            LogUtil.c("UIHLH_HomeFragment", "no net to RecommendCloud");
        } else {
            RecommendCloud.getInstance(this.c).doRefreshBatch(new HomeFragmentUtil.f());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        HomeFragmentUtil.d(this.c);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        LogUtil.c("UIHLH_HomeFragment", "onStop ", this);
    }

    private void bm() {
        bl();
        be();
        bg();
        bf();
        bi();
        bh();
        bk();
        if (this.au && Utils.i()) {
            siq.a().c();
        }
        BroadcastManagerUtil.bFJ_(this.c, this.bi);
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            bo();
            WifiScaleMultiUserInteractor wifiScaleMultiUserInteractor = this.bn;
            if (wifiScaleMultiUserInteractor != null) {
                wifiScaleMultiUserInteractor.b();
                this.bn = null;
            }
        }
        dum.d().l();
        if (CommonUtil.ce()) {
            bp();
            bj();
            bn();
            Handler handler = this.h;
            if (handler != null) {
                handler.removeMessages(9);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.FragmentForViewPager, androidx.fragment.app.Fragment
    public void onDestroy() {
        HealthRecycleView healthRecycleView;
        HomeFragmentUtil.c cVar;
        super.onDestroy();
        ReleaseLogUtil.b("UIHLH_HomeFragment", "onDestroy ", this);
        j();
        bm();
        cjn.e(this.c).d();
        new GuideInteractors(this.c).c((String) null);
        Object[] objArr = new Object[2];
        objArr[0] = "onDestroy mEventBusCallback ";
        objArr[1] = Boolean.valueOf(this.z != null);
        LogUtil.d("UIHLH_HomeFragment", objArr);
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false) && (cVar = this.z) != null) {
            EventBus.a(cVar, "evebus_weight_measure_notification", "multi_user_auto_cancle_dialog");
        }
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = this.f;
        if (healthSwipeRefreshLayout != null) {
            healthSwipeRefreshLayout.setCallback(null);
            this.f = null;
        }
        if (efb.a() && (healthRecycleView = this.bl) != null) {
            healthRecycleView.setOnScrollListener(null);
            this.bl = null;
        }
        owo.d(this.c).djc_(null);
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.h = null;
        }
        n();
        if (this.af != null) {
            jeg.d().b(this.af);
            this.af = null;
        }
    }

    private void n() {
        this.c = null;
        if (efb.a()) {
            this.aj = null;
            this.ae = null;
            this.bd = null;
            this.l = null;
            this.aa = null;
            this.ad = null;
            this.bp = null;
            this.n = null;
            ArrayList<AbstractBaseCardData> arrayList = this.y;
            if (arrayList != null) {
                arrayList.clear();
                this.y = null;
            }
            ArrayList<AbstractBaseCardData> arrayList2 = this.u;
            if (arrayList2 != null) {
                arrayList2.clear();
                this.u = null;
            }
        }
        this.z = null;
        this.as = null;
        if (efb.a()) {
            this.ac = null;
            this.an = null;
        } else {
            this.ba.setScrollViewListener(null);
        }
        ((TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class)).removeTodoFloatView();
    }

    private void j() {
        HomeFragmentUtil.c(this.c, this.l, this.aa, this.bp, this.ac, this.ad, this.bd, this.an, this.au, this.aj, this.ae, this.n);
        this.aq = false;
    }

    public void d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        HomeFragmentUtil.f(this.c);
        if (efb.a()) {
            int d2 = opj.d();
            if (d2 == -1) {
                aw();
            } else if (d2 == 1) {
                HomeFragmentUtil.b(this.bp);
                HomeFragmentUtil.b(this.aa);
            }
        }
        HomeFragmentUtil.c(this.c);
        ReleaseLogUtil.b("UIHLH_HomeFragment", "cardsResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void aw() {
        ReleaseLogUtil.b("UIHLH_HomeFragment", "begin resume");
        c(this.an);
        c(this.l);
        c(this.aa);
        c(this.bp);
        c(this.ac);
        c(this.ad);
        c(this.aj);
        ReleaseLogUtil.b("UIHLH_HomeFragment", "end resume");
    }

    private void c(final AbstractBaseCardData abstractBaseCardData) {
        if (abstractBaseCardData == null) {
            return;
        }
        if (b()) {
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.9
                @Override // java.lang.Runnable
                public void run() {
                    abstractBaseCardData.onResume();
                }
            }, 100L);
        } else {
            abstractBaseCardData.onResume();
        }
    }

    public static void aa(HomeFragment homeFragment) {
        HomeFragmentUtil.e(homeFragment);
    }

    private void cWk_(Intent intent) {
        HomeFragmentUtil.cWH_(intent, this.c, this.ao);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ax() {
        LogUtil.c("UIHLH_HomeFragment", "registerWearBroadcast");
        HomeFragmentUtil.cWG_(this.c, this.bf);
    }

    private void bp() {
        HomeFragmentUtil.cWK_(this.c, this.bf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        HomeFragmentUtil.cWy_(this.c, this.w);
    }

    private void bj() {
        HomeFragmentUtil.cWE_(this.c, this.w);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        LogUtil.c("UIHLH_HomeFragment", "enter registerUpgradeBroadcast");
        if (this.c == null) {
            LogUtil.e("UIHLH_HomeFragment", "failed to registerUpgradeBroadcast, cause context is null!");
        } else {
            BroadcastManagerUtil.bFC_(this.c, this.bo, new IntentFilter("com.huawei.bone.action.UPDATE_DEVICE"), LocalBroadcast.c, null);
        }
    }

    private void bn() {
        HomeFragmentUtil.cWC_(this.c, this.bo);
    }

    static class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HomeFragmentUtil.cWt_(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        HomeFragmentUtil.cWJ_(this.c, this.bw);
    }

    private void bo() {
        HomeFragmentUtil.cWF_(this.c, this.bw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        HomeFragmentUtil.cWu_(this.c, this.r);
    }

    private void bl() {
        HomeFragmentUtil.cWD_(this.c, this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cWn_(Intent intent, int i) {
        HomeFragmentUtil.cWI_(getActivity(), intent, i, this.c);
    }

    private void m() {
        LogUtil.c("UIHLH_HomeFragment", "checkGpsSwitch");
        if (SyncFitnessPrivateBroadcastReceiver.b()) {
            ba();
        }
    }

    private void ba() {
        LogUtil.c("UIHLH_HomeFragment", "need show gps switch note");
        SyncFitnessPrivateBroadcastReceiver.c(false);
        if (!kch.d(this.c)) {
            a(R.string._2130841420_res_0x7f020f4c, 101);
        } else {
            l();
        }
    }

    private void l() {
        if (this.af == null) {
            this.af = new e(this.c);
        }
        PermissionUtil.b(getActivity(), PermissionUtil.PermissionType.LOCATION, this.af);
    }

    private void bb() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), knl.e("sIsFirstHealth"));
        final String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "familyHealthDialogFrequency");
        if (TextUtils.isEmpty(b) || !b.equals("1")) {
            try {
                if (TextUtils.isEmpty(b2)) {
                    h("-1");
                    LogUtil.c("UIHLH_HomeFragment", "showFamilyHealthDialog updateFamilyHealthDialogTimeAndFrequency: -1");
                } else if ((TextUtils.isEmpty(b2) || Integer.parseInt(b2) < 3) && HomeFragmentUtil.d()) {
                    rbt.e(new HasFollowListCallBack() { // from class: com.huawei.ui.homehealth.HomeFragment.10
                        @Override // com.huawei.ui.main.stories.healthzone.model.HasFollowListCallBack
                        public void hasFollowList(boolean z) {
                            if (z) {
                                return;
                            }
                            HomeFragment.this.d(b2);
                        }
                    });
                }
            } catch (NumberFormatException unused) {
                LogUtil.e("UIHLH_HomeFragment", "showFamilyHealthDialog is crash number format exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str) {
        Handler handler = this.h;
        if (handler == null) {
            LogUtil.a("UIHLH_HomeFragment", "showHomeHealthDialogOnUiThread mHandler is null");
        } else {
            handler.post(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragment.13
                @Override // java.lang.Runnable
                public void run() {
                    HomeFragment.this.b(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.dialog_home_health, (ViewGroup) null);
        Context context = getContext();
        cWm_((HealthTextView) inflate.findViewById(R.id.home_health_dialog_text), (ImageView) inflate.findViewById(R.id.home_health_dialog_img), str);
        CustomViewDialog e2 = HomeFragmentUtil.cWA_(inflate, this.c, context).e();
        if (e2.isShowing() || getActivity().isFinishing()) {
            return;
        }
        e2.show();
        HomeFragmentUtil.c(this.c, str);
        h(str);
    }

    private void cWm_(HealthTextView healthTextView, ImageView imageView, String str) {
        if (healthTextView == null || imageView == null) {
            return;
        }
        HomeFragmentUtil.cWv_(healthTextView, imageView, str, this.c);
    }

    private void h(String str) {
        LogUtil.c("UIHLH_HomeFragment", "updateFamilyHealthDialogTimeAndFrequency familyHealthDialogFrequency", str);
        HomeFragmentUtil.e(this.c, str);
    }

    private void a(int i, final int i2) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.c).b(R.string.IDS_device_replace_dialog_title_notification).d(i).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.HomeFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UIHLH_HomeFragment", "showGPSSettingDialog():");
                Intent intent = new Intent();
                intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                try {
                    HomeFragment.this.startActivityForResult(intent, i2);
                } catch (ActivityNotFoundException unused) {
                    intent.setAction("android.settings.SETTINGS");
                    try {
                        HomeFragment.this.startActivityForResult(intent, i2);
                    } catch (Exception unused2) {
                        LogUtil.e("UIHLH_HomeFragment", "startActivity exception");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.HomeFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        if (a2.isShowing() || getActivity().isFinishing()) {
            return;
        }
        a2.show();
    }

    private void ah() {
        LogUtil.c("UIHLH_HomeFragment", "outsideOpenActivityOrAddDevice enter");
        if (HomeFragmentUtil.b()) {
            if (ai()) {
                LogUtil.c("UIHLH_HomeFragment", "outsideOpenActivityOrAddDevice the device is leo");
            } else {
                HomeFragmentUtil.i(this.c);
                this.at = false;
            }
        }
    }

    private boolean ai() {
        this.v = oxa.a().f();
        GuideInteractors guideInteractors = new GuideInteractors(BaseApplication.getContext());
        String d2 = guideInteractors.d();
        DeviceInfo deviceInfo = this.v;
        if (deviceInfo != null && (deviceInfo.getProductType() == 10 || ((this.v.getProductType() == 57 || this.v.getProductType() == 78 || this.v.getProductType() == 85) && "com.huawei.sim.multisim.MultiSimAuth".equals(d2)))) {
            LogUtil.c("UIHLH_HomeFragment", "outsideOpenActivity producttype: ", Integer.valueOf(this.v.getProductType()));
            boolean z = this.v.getDeviceConnectState() == 2;
            this.ao = z;
            LogUtil.c("UIHLH_HomeFragment", "outsideOpenActivity mIsConnected: ", Boolean.valueOf(z));
            d(guideInteractors, d2);
            return true;
        }
        if ("com.huawei.sim.esim.view.WirelessManagerActivity".equals(d2) || ComponentInfo.PluginPay_A_31.equals(d2)) {
            guideInteractors.c((String) null);
        }
        return false;
    }

    private void d(GuideInteractors guideInteractors, String str) {
        if (this.ao) {
            HomeFragmentUtil.a(this.c, guideInteractors, str, e(str));
        }
    }

    private boolean e(String str) {
        DeviceCapability e2 = DeviceSettingsInteractors.d(this.c).e();
        this.x = e2;
        return HomeFragmentUtil.e(false, str, e2);
    }

    private void z() {
        LogUtil.c("UIHLH_HomeFragment", "openActivity enter");
        LogUtil.c("UIHLH_HomeFragment", "openActivity name:", new GuideInteractors(BaseApplication.getContext()).d());
        if (HomeFragmentUtil.b()) {
            ai();
        }
    }

    public void a(boolean z) {
        this.ar = z;
    }

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || HomeFragment.this.c == null) {
                LogUtil.a("UIHLH_HomeFragment", "SyncCloudDataReceiver onReceive intent is null or context is null");
            } else {
                HomeFragmentUtil.cWB_(HomeFragment.this.c, intent, HomeFragment.this.bk, HomeFragment.this.bq, HomeFragment.this.aa, HomeFragment.this.bp, HomeFragment.this.bj);
            }
        }
    }

    static class d implements Consumer<Boolean> {
        private final WeakReference<HomeFragment> e;

        public d(HomeFragment homeFragment) {
            this.e = new WeakReference<>(homeFragment);
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void accept(Boolean bool) {
            HomeFragment homeFragment = this.e.get();
            if (homeFragment == null) {
                return;
            }
            if (bool == null || homeFragment.ad == null) {
                LogUtil.a("UIHLH_HomeFragment", "isTouchDown or mFunctionMenuCardData is null");
            } else if (bool.booleanValue()) {
                homeFragment.ad.c();
            } else {
                homeFragment.ad.b();
            }
        }
    }

    static class e extends CustomPermissionAction {
        private e(Context context) {
            super(context);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.c("UIHLH_HomeFragment", "checkGpsPermission onGranted");
        }
    }

    private void b(final Context context, final String str) {
        String string = context.getString(R.string._2130843863_res_0x7f0218d7);
        String string2 = context.getString(R.string._2130847452_res_0x7f0226dc);
        String upperCase = context.getString(R.string.IDS_device_log_enhancement_ok).toUpperCase();
        String upperCase2 = context.getString(R.string.IDS_device_release_user_profile_log_collect_cancel).toUpperCase();
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(context).a(string).c(string2).cyo_(R.string.IDS_device_log_enhancement_ok, new DialogInterface.OnClickListener() { // from class: oda
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HomeFragment.cWi_(context, str, dialogInterface, i);
            }
        }).cyn_(R.string.IDS_device_release_user_profile_log_collect_cancel, new DialogInterface.OnClickListener() { // from class: ocz
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                HomeFragment.cWj_(context, str, dialogInterface, i);
            }
        });
        cyn_.d().setText(upperCase2);
        cyn_.b().setText(upperCase);
        CustomAlertDialog c2 = cyn_.c();
        c2.setCancelable(false);
        c2.show();
    }

    public static /* synthetic */ void cWi_(Context context, String str, DialogInterface dialogInterface, int i) {
        ReleaseLogUtil.b("UIHLH_HomeFragment", "showAnalAndImproGuideDialog agree");
        HomeFragmentUtil.e.b(true);
        HomeFragmentUtil.e.b(context, str);
        HomeFragmentUtil.e.d(context, "anal_and_improt_last_show_date_" + str, System.currentTimeMillis());
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void cWj_(Context context, String str, DialogInterface dialogInterface, int i) {
        ReleaseLogUtil.b("UIHLH_HomeFragment", "showAnalAndImproGuideDialog cancel");
        HomeFragmentUtil.e.b(false);
        HomeFragmentUtil.e.b(context, str);
        HomeFragmentUtil.e.d(context, "anal_and_improt_last_show_date_" + str, System.currentTimeMillis());
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }
}
