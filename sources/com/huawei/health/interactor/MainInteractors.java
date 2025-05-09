package com.huawei.health.interactor;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.common.util.AccountHelper;
import com.huawei.common.util.AccountInteractors;
import com.huawei.common.util.VersionIsCloud;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.BuildConfig;
import com.huawei.health.R;
import com.huawei.health.ServiceAreaAlertActivity;
import com.huawei.health.developerkit.TrackDeveloperKitProxy;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.utils.MainInteractorsUtils;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwauthutil.utils.PackageManagerHelper;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataReq;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.h5pro.jsmodules.EcgJsModule;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.SampleConfigUtils;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.homehealth.interactors.GolfUpdateMapInteractor;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import com.huawei.ui.main.stories.nps.npsstate.NpsUserShowController;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.request.HttpRequestBase;
import com.huawei.utils.FoundationCommonUtil;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import defpackage.cej;
import defpackage.cun;
import defpackage.czs;
import defpackage.dsl;
import defpackage.dss;
import defpackage.dze;
import defpackage.dzk;
import defpackage.dzn;
import defpackage.dzu;
import defpackage.ehx;
import defpackage.fep;
import defpackage.gmz;
import defpackage.gnc;
import defpackage.gnm;
import defpackage.gno;
import defpackage.gok;
import defpackage.gou;
import defpackage.gov;
import defpackage.guw;
import defpackage.gve;
import defpackage.gwg;
import defpackage.hpp;
import defpackage.jbs;
import defpackage.jdi;
import defpackage.jeq;
import defpackage.jfb;
import defpackage.jfd;
import defpackage.jki;
import defpackage.jli;
import defpackage.kor;
import defpackage.kts;
import defpackage.kvt;
import defpackage.mcv;
import defpackage.mex;
import defpackage.mkx;
import defpackage.nkx;
import defpackage.nrh;
import defpackage.ntd;
import defpackage.owk;
import defpackage.owq;
import defpackage.ppk;
import defpackage.qbd;
import defpackage.qif;
import defpackage.sds;
import defpackage.sqt;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HwEncryptUtil;
import health.compact.a.KeyManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.StorageResult;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class MainInteractors {
    private static boolean b = false;
    private Handler aa;
    private VersionMgrApi ab;
    private MainInteractorsUtils ac;
    private UserProfileMgrApi ad;
    private String ae;
    private String ag;
    private MergeUserAllDataReq ai;
    private CustomTextAlertDialog e;
    private g f;
    private c i;
    private BroadcastReceiver j;
    private Context l;
    private dze n;
    private ExecutorService o;
    private IWXAPI s;
    private BroadcastReceiver t;
    private n u;
    private final UserLabelServiceApi v;
    private GolfUpdateMapInteractor x;
    private BroadcastReceiver y;
    private Activity z;
    private AchieveMedalsBehaviorTriggedByUi g = null;
    private String d = "";
    private boolean h = false;
    private int ah = 0;
    private boolean m = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2506a = false;
    private int af = 0;
    private ExecutorService c = null;
    private boolean r = false;
    private boolean p = false;
    private String[] q = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACTIVITY_RECOGNITION"};
    private String[] w = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACTIVITY_RECOGNITION"};
    private a k = new a(this);
    private int al = -1;

    /* loaded from: classes8.dex */
    interface UpdateType {
        public static final int GOOGLE_PLAY = 1;
        public static final int GOOGLE_PLAY_WAP = 2;
    }

    static class f extends BroadcastReceiver {
        private WeakReference<MainInteractors> c;

        public f(MainInteractors mainInteractors) {
            this.c = new WeakReference<>(mainInteractors);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MainInteractors mainInteractors = this.c.get();
            if (mainInteractors != null) {
                LogUtil.a("Login_MainInteractors", "mLoginSuccessReceiver intent = ", intent.getAction());
                if ("com.huawei.plugin.account.login".equals(intent.getAction())) {
                    mainInteractors.ai();
                    MainInteractorsUtils.c(context);
                    dzu.a().c();
                    if (CommonUtil.as()) {
                        mainInteractors.h();
                    }
                }
            }
        }
    }

    public void h() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.2
            @Override // java.lang.Runnable
            public void run() {
                Object[] objArr = new Object[2];
                objArr[0] = "initCrowdSdk mContext == null?";
                objArr[1] = Boolean.valueOf(MainInteractors.this.l == null);
                LogUtil.b("Login_MainInteractors", objArr);
                if (MainInteractors.this.l == null) {
                    LogUtil.b("Login_MainInteractors", "initCrowdSdk final mContext == null");
                    MainInteractors.this.l = BaseApplication.getContext();
                }
                jeq.e().init(MainInteractors.this.l);
                if (Utils.o()) {
                    jeq.e().setProductType(20);
                } else {
                    jeq.e().setProductType(1);
                }
            }
        });
    }

    public boolean k() {
        return this.r;
    }

    public static boolean a() {
        return b;
    }

    public void aaK_(Handler handler) {
        this.aa = handler;
        GolfUpdateMapInteractor golfUpdateMapInteractor = this.x;
        if (golfUpdateMapInteractor != null) {
            golfUpdateMapInteractor.dcG_(handler);
        }
        LogUtil.a("Login_MainInteractors", "setMainHandler() mainHandler = ", handler);
    }

    public void d(ExecutorService executorService) {
        this.o = executorService;
    }

    public MainInteractors(Context context) {
        this.s = null;
        this.l = context;
        MainInteractorsUtils mainInteractorsUtils = new MainInteractorsUtils();
        this.ac = mainInteractorsUtils;
        mainInteractorsUtils.j(context);
        this.z = (Activity) context;
        this.ad = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        this.v = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        if (this.s == null) {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.l.getApplicationContext(), "wx36bda3d35fbcfd06", false);
            this.s = createWXAPI;
            createWXAPI.registerApp("wx36bda3d35fbcfd06");
        }
        this.n = dze.b(context);
        this.i = new c(this);
        AccountHelper.setIsNoCloudTermsUpdate(dzn.c(context));
        af();
        ap();
        as();
        this.x = new GolfUpdateMapInteractor();
    }

    private void ap() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("sync_cloud_data_again_action");
            g gVar = new g(this);
            this.f = gVar;
            BroadcastManagerUtil.bFz_(this.l, gVar, intentFilter);
        } catch (Exception unused) {
            LogUtil.b("Login_MainInteractors", "registerSyncCloudDataReceiver Exception");
        }
    }

    private void af() {
        gve.aUt_(new TrackDeveloperKitProxy(), new o());
        dsl.r();
    }

    static class o implements IBaseResponseCallback {
        private o() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                gve.aUu_(new TrackDeveloperKitProxy());
            }
        }
    }

    public void d(boolean z) {
        this.p = z;
    }

    public void b(boolean z) {
        if ("health_app_first_start".equals(SharedPreferenceManager.b(this.l, Integer.toString(10005), "health_guide_page"))) {
            if (z) {
                j();
                return;
            } else {
                LogUtil.a("Login_MainInteractors", "checkFirstStart isNeedJump = false");
                return;
            }
        }
        this.ac.e();
        if (z) {
            this.aa.sendEmptyMessage(7);
        }
    }

    public static void d() {
        MainInteractorsUtils.c();
    }

    public void j() {
        ExecutorService executorService = this.o;
        if (executorService != null) {
            if (executorService.isShutdown()) {
                this.o = Executors.newSingleThreadExecutor();
            }
            this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.13
                @Override // java.lang.Runnable
                public void run() {
                    dss.c(MainInteractors.this.l).a();
                    MainInteractors.this.ab = (VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class);
                    MainInteractors.this.ac();
                    MainInteractors.this.ac.aa();
                }
            });
        }
        this.ac.r();
    }

    static class e implements HiCommonListener {
        private WeakReference<MainInteractors> b;

        e(MainInteractors mainInteractors) {
            this.b = new WeakReference<>(mainInteractors);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            MainInteractors mainInteractors = this.b.get();
            if (mainInteractors != null) {
                if (!mainInteractors.a(0, true)) {
                    return;
                }
                mainInteractors.ae();
                if (!mainInteractors.r) {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: checkAccountSync isSameAccount = false");
                    HiHealthManager.d(mainInteractors.l).checkDataStatus(HiSyncType.b(), new b(mainInteractors));
                }
                UserProfileMgrApi userProfileMgrApi = mainInteractors.ad;
                LoginInit.getInstance(BaseApplication.getContext());
                userProfileMgrApi.init(new gnc(LoginInit.shouldLogin()));
                mainInteractors.ao();
                if (Utils.i()) {
                    LogUtil.a("Login_MainInteractors", "goto downloadUserPrivacy.");
                    gmz.d().c(new j(this.b.get()));
                    ehx.e(mainInteractors.l).e();
                }
                LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit onSuccess");
                mainInteractors.n.e(mainInteractors.l);
                if (LoginInit.getInstance(mainInteractors.l).isLoginedByWear()) {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit isLoginedByWear");
                    if (AccountHelper.getAccountAidlInfoWear() != null && !mainInteractors.o.isShutdown()) {
                        mainInteractors.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.e.5
                            @Override // java.lang.Runnable
                            public void run() {
                                MainInteractors mainInteractors2 = (MainInteractors) e.this.b.get();
                                if (mainInteractors2 != null) {
                                    mainInteractors2.ax();
                                }
                            }
                        });
                    } else {
                        LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit null == accountAidlInfoWear ", "mExecutorService.isShutdown() = ", Boolean.valueOf(mainInteractors.o.isShutdown()));
                        return;
                    }
                }
            }
            gwg.c(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            if (CommonUtil.z(BaseApplication.getContext())) {
                ThirdPartyLoginManager.getInstance().saveUserInfo();
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit onFailure");
            MainInteractors mainInteractors = this.b.get();
            if (mainInteractors != null) {
                UserProfileMgrApi userProfileMgrApi = mainInteractors.ad;
                LoginInit.getInstance(BaseApplication.getContext());
                userProfileMgrApi.init(new gnc(LoginInit.shouldLogin()));
            }
        }
    }

    public void g() {
        this.ac.t();
        this.af = 0;
        this.f2506a = false;
        AccountHelper.setIsNoCloudNeedLogin(false);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtil.a("Login_MainInteractors", "doInitPluginLoginHiHealth mainThread");
            ExecutorService executorService = this.o;
            if (executorService == null || executorService.isShutdown()) {
                LogUtil.a("Login_MainInteractors", "mExecutorService is null or shutdown");
                this.o = Executors.newSingleThreadExecutor();
            }
            this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.15
                @Override // java.lang.Runnable
                public void run() {
                    MainInteractors.this.g();
                }
            });
            return;
        }
        if (dzn.e()) {
            LogUtil.a("Login_MainInteractors", "doInitPluginLoginHiHealth isNeedShowAfterLogin = true");
            this.aa.sendEmptyMessage(Constants.FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5);
            return;
        }
        aj();
        HttpRequestBase.resetBaseUrl(GRSManager.a(this.l), "domainSettingHicloud");
        this.v.subscribeTrackDataChanged(this.l);
        gok.b().e(-1);
        am();
        if (!gnm.aPA_(this.z)) {
            LogUtil.a("Login_MainInteractors", "activity.isDestroyed() || activity.isFinishing()");
            return;
        }
        ah();
        if (Utils.b(this.l)) {
            jli.d(BaseApplication.getContext()).b();
        }
    }

    private void ak() {
        this.v.registerCallback(3);
    }

    public void i() {
        gok.b().aQC_(this.l, this.aa);
    }

    public void o() {
        ExecutorService executorService = this.o;
        if (executorService == null || executorService.isShutdown()) {
            LogUtil.a("Login_MainInteractors", "initMigrateData mExecutorService is null or shutdown");
        } else {
            this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.14
                @Override // java.lang.Runnable
                public void run() {
                    MainInteractors.this.ad();
                    MainInteractors.this.ac.aQM_(3, MainInteractors.this.ae, MainInteractors.this.aa);
                    MainInteractors.this.ac.aQF_(MainInteractors.this.o, MainInteractors.this.aa);
                    String accountInfo = LoginInit.getInstance(MainInteractors.this.l).getAccountInfo(1011);
                    String oldHid = AccountHelper.getOldHid();
                    if ((("0".equals(oldHid) || "com.huawei.health".equals(oldHid)) && !oldHid.equals(accountInfo)) || AccountHelper.isNeedShowMigrateDataDig(accountInfo)) {
                        MainInteractors.this.aa.sendEmptyMessage(SleepBaseBarLineChartProvider.MESSAGE_JUMP_TO_DAY_TAB);
                    }
                }
            });
        }
    }

    private static void i(boolean z) {
        b = z;
    }

    public void t() {
        this.ac.aQG_(this.aa);
        if (this.aa != null) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit MSG_UPDATE_VIEW");
            this.aa.sendMessage(this.aa.obtainMessage(1));
            return;
        }
        LogUtil.a("Login_MainInteractors", "sendMsgShowView() mainHandler = null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ax() {
        Bitmap bitmap;
        LoginInit.getInstance(this.l).setAccountInfo(1002, AccountHelper.getAccountAidlInfoWear().getUserName(), new StorageDataCallback() { // from class: com.huawei.health.interactor.MainInteractors.11
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit UserName result = ", storageResult);
            }
        });
        byte[] headPicByts = AccountHelper.getAccountAidlInfoWear().getHeadPicByts();
        if (headPicByts != null) {
            bitmap = BitmapFactory.decodeByteArray(headPicByts, 0, headPicByts.length);
            LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit = 3 headpicbytes != not null");
        } else {
            bitmap = null;
        }
        if (bitmap != null) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit = 3 headImage != null");
            Context context = this.l;
            this.d = AccountInteractors.saveImage(context, LoginInit.getInstance(context).getAccountInfo(1011), bitmap);
            LogUtil.c("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit = 3 headImage != null newPath = " + this.d);
        } else {
            LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit = 3 headImage = null");
        }
        if (!"".equals(this.d)) {
            LoginInit.getInstance(this.l).setAccountInfo(1003, this.d, new StorageDataCallback() { // from class: com.huawei.health.interactor.MainInteractors.12
                @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
                public void onProcessed(StorageResult storageResult) {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit headPicPath result = ", storageResult);
                }
            });
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setAction("com.huawei.plugin.account.login");
        if (LocalBroadcastManager.getInstance(this.l) != null) {
            LocalBroadcastManager.getInstance(this.l).sendBroadcast(intent);
        }
        if (this.l != null) {
            LogUtil.a("Login_MainInteractors", "----send broadcast to social----");
            this.l.sendBroadcast(intent, LocalBroadcast.c);
        } else {
            LogUtil.a("Login_MainInteractors", "----mContext is null----");
        }
    }

    public void c(String str, String str2, String str3, int i2) {
        this.ac.d(str, str2, str3, i2);
    }

    public void b(final boolean z, String str) {
        if (this.m) {
            return;
        }
        this.m = true;
        LogUtil.a("Login_MainInteractors", "showNoTitleSingleButtonDialog goto downloadUserPrivacy");
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.l).e(str).czE_(this.l.getString(R.string._2130841554_res_0x7f020fd2), new View.OnClickListener() { // from class: com.huawei.health.interactor.MainInteractors.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractors", "showNoTitleSingleButtonDialog click known button");
                if (!Utils.l()) {
                    if (z) {
                        MainInteractors.this.ac.j();
                    } else {
                        MainInteractors.this.w();
                    }
                }
                KeyManager.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public void s() {
        CustomTextAlertDialog customTextAlertDialog = this.e;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
            this.e = null;
        }
    }

    public void b(final int i2) {
        LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateDialogHaveDownHMS enter type = ", Integer.valueOf(i2));
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.l);
        builder.b(this.l.getString(R.string._2130837692_res_0x7f0200bc)).e(this.l.getString(R.string._2130837704_res_0x7f0200c8)).cyV_(this.l.getString(R.string._2130837705_res_0x7f0200c9), new View.OnClickListener() { // from class: com.huawei.health.interactor.MainInteractors.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateDialogHaveDownHMS OK");
                if (MainInteractors.this.o != null && !MainInteractors.this.o.isShutdown()) {
                    MainInteractors.this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.17.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (i2 == 1) {
                                MainInteractors.this.aa();
                            } else if (i2 == 2) {
                                MainInteractors.this.h(2);
                            }
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateDialogHaveDownHMS mExecutorService is null or shutdown");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).cyS_(this.l.getString(R.string._2130841455_res_0x7f020f6f), new View.OnClickListener() { // from class: com.huawei.health.interactor.MainInteractors.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateDialogHaveDownHMS cancel");
                MainInteractors.this.x();
                LogUtil.h("Login_MainActivity", "finish MainAcitivity for cause:", "cancel download HMS ...");
                MainInteractors.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.e = a2;
        a2.setCancelable(false);
        this.e.show();
    }

    public void v() {
        LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateToastCloudTimeout() enter" + this.ah);
        if (this.ah == 1) {
            this.ah = 2;
            MergeUserAllDataReq mergeUserAllDataReq = this.ai;
            if (mergeUserAllDataReq != null) {
                AccountInteractors.saveMigrateInfoToDb(mergeUserAllDataReq.getOriginalHuid(), this.ai.getOriginalServiceToken(), LoginInit.getInstance(this.l).getAccountInfo(1011), false);
            }
            String upperCase = this.l.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase();
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.l);
            builder.b(this.l.getString(R.string._2130837692_res_0x7f0200bc)).e(this.l.getString(R.string._2130837702_res_0x7f0200c6)).cyV_(upperCase, new View.OnClickListener() { // from class: com.huawei.health.interactor.MainInteractors.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.h("Login_MainActivity", "finish MainAcitivity for cause:", "accountmigrate: showDataMigrateToastCloudTimeout() ok..");
                    MainInteractors.d();
                    SharedPreferenceManager.e(MainInteractors.this.l, Integer.toString(10015), "last_exception_exit", "1", new StorageParams(0));
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            a2.setCancelable(false);
            a2.show();
        }
    }

    public void d(final int i2) {
        LogUtil.a("Login_MainInteractors", "accountmigrate: doagainToBranchCheckIsLoginForHealth(final int type) type = ", Integer.valueOf(i2));
        ExecutorService executorService = this.o;
        if (executorService == null || executorService.isShutdown()) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: doagainToBranchCheckIsLoginForHealth mExecutorService is null or shutdown");
        } else {
            this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.4
                @Override // java.lang.Runnable
                public void run() {
                    MainInteractors.this.x();
                    int i3 = i2;
                    if (i3 == 1) {
                        MainInteractors.this.h(1);
                    } else if (i3 == 2) {
                        MainInteractors.this.h(2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        LogUtil.a("Login_MainInteractors", "accountmigrate: checkLastException enter");
        ArrayList<jfd> d2 = jfb.e().d(LoginInit.getInstance(this.l).getAccountInfo(1011));
        if (d2 == null) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: null == migrateTables");
            return;
        }
        int size = d2.size();
        for (int i2 = 0; i2 < size; i2++) {
            LogUtil.c("Login_MainInteractors", "accountmigrate: checkLastException enter migrateTables = ", d2.get(i2).toString());
            if (!d2.get(i2).a()) {
                this.h = true;
                this.ae = d2.get(i2).d();
                this.ag = d2.get(i2).c();
                ExecutorService executorService = this.o;
                if (executorService == null || executorService.isShutdown()) {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: checkLastException mExecutorService is null or shutdown! ");
                    return;
                } else {
                    this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.5
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.a("Login_MainInteractors", "accountmigrate: checkLastException sendMigrageDataToCloud");
                            MainInteractors.this.au();
                        }
                    });
                    return;
                }
            }
            final jfd jfdVar = d2.get(i2);
            if (!jfdVar.h() && jfdVar.b().equals(LoginInit.getInstance(this.l).getAccountInfo(1011))) {
                ExecutorService executorService2 = this.o;
                if (executorService2 == null || executorService2.isShutdown()) {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: checkLastException mExecutorService is null or shutdown.. ");
                    return;
                }
                this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a("Login_MainInteractors", "accountmigrate: checkLastException copyByHuid");
                        MainInteractors.this.ac.aQL_(jfdVar.d(), MainInteractors.this.aa);
                    }
                });
            }
        }
    }

    static class b implements HiCommonListener {
        private WeakReference<MainInteractors> b;

        b(MainInteractors mainInteractors) {
            this.b = new WeakReference<>(mainInteractors);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            MainInteractors mainInteractors = this.b.get();
            if (mainInteractors == null || obj == null) {
                return;
            }
            LogUtil.a("Login_MainInteractors", "accountmigrate: checkAccountSync and onsuccess");
            if (((Boolean) obj).booleanValue()) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: checkAccountSync and onsuccess show dialog");
                mainInteractors.aa.sendMessage(mainInteractors.aa.obtainMessage(2));
                LogUtil.a("Login_MainInteractors", "checkDataStatus_data_false2 ", mainInteractors.aa);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: checkAccountSync onFailure errMsg = " + obj + ",errCode = " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String ae() {
        String str;
        String b2 = SharedPreferenceManager.b(this.l, "main_id", "main_key");
        String accountInfo = LoginInit.getInstance(this.l).getAccountInfo(1011);
        StorageParams storageParams = new StorageParams();
        this.r = false;
        try {
            try {
                String b3 = HwEncryptUtil.c(this.l).b(1, accountInfo);
                if (b3 != null) {
                    try {
                        str = HwEncryptUtil.c(this.l).a(1, b2);
                    } catch (Exception unused) {
                        SharedPreferenceManager.e(this.l, "main_id", "main_key", b3, storageParams);
                        str = "";
                    }
                    if (str.equals(HwEncryptUtil.c(this.l).a(1, b3))) {
                        this.r = true;
                    } else {
                        SharedPreferenceManager.e(this.l, "main_id", "main_key", b3, storageParams);
                    }
                }
            } catch (RuntimeException e2) {
                throw e2;
            }
        } catch (Exception unused2) {
            LogUtil.b("Login_MainInteractors", "getUserId Exception");
        }
        return accountInfo;
    }

    static class a implements Runnable {
        private WeakReference<MainInteractors> e;

        a(MainInteractors mainInteractors) {
            this.e = new WeakReference<>(mainInteractors);
        }

        @Override // java.lang.Runnable
        public void run() {
            MainInteractors mainInteractors = this.e.get();
            if (mainInteractors != null) {
                mainInteractors.ac();
            }
        }
    }

    public void ab() {
        ExecutorService executorService = this.o;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.o.execute(this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        AccountHelper.checkLogin(this.l, new c(this));
    }

    static class c implements MainLoginCallBack {
        private WeakReference<MainInteractors> c;

        c(MainInteractors mainInteractors) {
            this.c = new WeakReference<>(mainInteractors);
        }

        @Override // com.huawei.login.ui.login.MainLoginCallBack
        public boolean isNoAgreePrivacy(int i) {
            return dzn.c(i, true);
        }

        @Override // com.huawei.login.ui.login.MainLoginCallBack
        public boolean isHmsUsableVersion(Context context) {
            return Utils.b(context);
        }

        @Override // com.huawei.login.ui.login.MainLoginCallBack
        public void browseCallback(boolean z) {
            nkx.c(z);
        }

        @Override // com.huawei.login.ui.login.MainLoginCallBack
        public void handlerMessage(int i) {
            MainInteractors mainInteractors = this.c.get();
            if (mainInteractors == null) {
                return;
            }
            mainInteractors.c(i);
        }

        @Override // com.huawei.login.ui.login.MainLoginCallBack
        public void handlerPhase(int i) {
            MainInteractors mainInteractors = this.c.get();
            if (mainInteractors == null) {
                LogUtil.a("Login_MainInteractors", "CheckLoginCallBack handlerPhase mainInteractors == null");
            } else {
                mainInteractors.e(i);
            }
        }
    }

    public void c(int i2) {
        int i3;
        switch (i2) {
            case 8000:
                i3 = 4010;
                break;
            case 8001:
                i3 = 4007;
                break;
            case 8002:
                i3 = WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED;
                break;
            case MainLoginCallBack.MSG_HMS_VERSION_ERROR /* 8003 */:
                i3 = 4019;
                break;
            case MainLoginCallBack.MSG_HWID_ACCOUNT_NOT_LOGIN /* 8004 */:
                i3 = AuthCode.StatusCode.PERMISSION_EXPIRED;
                break;
            case MainLoginCallBack.MSG_NO_NETWORK /* 8005 */:
                i3 = 4;
                break;
            case MainLoginCallBack.MSG_HWID_STOPED /* 8006 */:
                i3 = FitnessStatusCodes.DATASET_TIMESTAMP_INCONSISTENT_WITH_UPDATE_TIME_RANGE;
                break;
            case MainLoginCallBack.MSG_START_APK_SERVICE_ERROR /* 8007 */:
                i3 = FitnessStatusCodes.INVALID_DATA_POINT;
                break;
            case MainLoginCallBack.MSG_SHOW_HMS_DIALOG /* 8008 */:
                i3 = FitnessStatusCodes.INVALID_SESSION_TIMESTAMPS;
                break;
            case MainLoginCallBack.MSG_HWID_IS_LOW_VERSION /* 8009 */:
                this.f2506a = true;
                this.af++;
                return;
            default:
                return;
        }
        this.aa.sendEmptyMessage(i3);
    }

    public void e(int i2) {
        switch (i2) {
            case 9000:
                bb();
                break;
            case 9001:
                Intent intent = new Intent(this.z, (Class<?>) ServiceAreaAlertActivity.class);
                intent.setFlags(65536);
                this.z.startActivityForResult(intent, AuthCode.StatusCode.PERMISSION_NOT_EXIST);
                break;
            case 9002:
                g();
                break;
            case 9003:
                this.ac.g();
                break;
            case 9004:
                au();
                break;
            case 9006:
                this.ad.getUserInfo();
                break;
            case MainLoginCallBack.PHASE_NOCLOUD_NOLOGIN /* 9007 */:
                UserProfileMgrApi userProfileMgrApi = this.ad;
                LoginInit.getInstance(BaseApplication.getContext());
                userProfileMgrApi.init(new gnc(LoginInit.shouldLogin()));
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i2, boolean z) {
        return AccountHelper.isNeedDoCallBack(i2, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bb() {
        e(false);
    }

    public void e(boolean z) {
        LogUtil.a("Login_MainInteractors", "todoCheckLogin():ifHeadImageTrigger = ", Boolean.valueOf(z));
        String ifNeedShowAreaAlert = VersionIsCloud.getIfNeedShowAreaAlert(this.l);
        LogUtil.a("Login_MainInteractors", "todoCheckLogin():ifNeedShowAreaAlert = ", ifNeedShowAreaAlert);
        if ("1".equals(ifNeedShowAreaAlert) && !FoundationCommonUtil.isSameTelephonyNetWorkAndSim(this.l)) {
            a(false);
            return;
        }
        if (dzn.a()) {
            LogUtil.a("Login_MainInteractors", "todoCheckLogin:isNeedShowBeforeLogin = true");
            this.aa.sendEmptyMessage(Constants.FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5);
            return;
        }
        KeyValDbManager.b(this.l).e("key_wether_to_auth", String.valueOf(true));
        gok.b().a(this.l, true);
        this.d = "";
        AccountHelper.todoCheckLogin(this.l, z);
        qif.e(com.huawei.health.messagecenter.model.CommonUtil.isSystemBarNoticeSwitchOnOrDefault(this.l));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        if (!LoginInit.getInstance(this.l).getIsLogined() || LoginInit.getInstance(this.l).getHealthLoginChannel() == -1) {
            return;
        }
        LogUtil.a("Login_MainInteractors", "accountmigrate: loginByAccountType backgroundAutoSync ", Boolean.valueOf(LoginInit.getInstance(this.l).getIsLogined()));
        if (KeyManager.h()) {
            String a2 = KeyManager.a();
            String accountInfo = LoginInit.getInstance(this.l).getAccountInfo(1011);
            if (TextUtils.isEmpty(a2) || !a2.equals(accountInfo) || this.ac.p()) {
                KeyManager.d();
                this.ac.j();
                return;
            } else {
                this.aa.sendEmptyMessage(10088);
                return;
            }
        }
        this.ac.j();
    }

    static class j implements CommonCallback {
        private WeakReference<MainInteractors> e;

        j(MainInteractors mainInteractors) {
            this.e = new WeakReference<>(mainInteractors);
        }

        @Override // com.huawei.up.callback.CommonCallback
        public void onSuccess(Bundle bundle) {
            LogUtil.a("Login_MainInteractors", "toGetPrivacySportData from cloud success.");
            MainInteractors mainInteractors = this.e.get();
            if (mainInteractors != null) {
                mainInteractors.aw();
                mainInteractors.ag();
                mainInteractors.at();
            }
            sds.e();
            dzn.j();
            MainInteractorsUtils.d(BaseApplication.getContext());
        }

        @Override // com.huawei.up.callback.CommonCallback
        public void onFail(int i) {
            LogUtil.a("Login_MainInteractors", "toGetPrivacySportData from cloud failure.");
            MainInteractors mainInteractors = this.e.get();
            if (mainInteractors != null) {
                mainInteractors.at();
            }
            sds.e();
            MainInteractorsUtils.d(BaseApplication.getContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aw() {
        this.ac.aQW_(this.aa);
    }

    public void b() {
        this.ac.aQE_(this.o, this.aa);
    }

    public void g(final int i2) {
        LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateTipsDialog(final int type) type = ", Integer.valueOf(i2));
        LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateTipsDialog isPackgeNameHuid = ", Boolean.valueOf(AccountHelper.getIsPackgeNameHuid()));
        if (AccountHelper.getIsPackgeNameHuid()) {
            ExecutorService executorService = this.o;
            if (executorService == null || executorService.isShutdown()) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateTipsDialog mExecutorService is null or shutdown");
            } else {
                this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.10
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i2 == 0) {
                            LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateTipsDialog(final int type) type = 0");
                            MainInteractors mainInteractors = MainInteractors.this;
                            mainInteractors.j(LoginInit.getInstance(mainInteractors.l).getLoginByWear());
                            MainInteractors.this.au();
                            return;
                        }
                        LogUtil.a("Login_MainInteractors", "accountmigrate: showDataMigrateTipsDialog(final int type) type = else");
                        MainInteractors.this.i(0);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud enter");
        this.ai = new MergeUserAllDataReq();
        an();
        this.ah = 1;
        if (!Utils.g() && !"com.huawei.health".equals(this.ai.getOriginalHuid())) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud mergeUserAllData enter mergeUserAllDataReq origalHuid is ", this.ai.getOriginalHuid());
            jbs.a(this.l).a(this.ai, new ICloudOperationResult<MergeUserAllDataRsp>() { // from class: com.huawei.health.interactor.MainInteractors.8
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void operationResult(MergeUserAllDataRsp mergeUserAllDataRsp, String str, boolean z) {
                    LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud mergeUserAllData back");
                    if (MainInteractors.this.ah != 2) {
                        MainInteractors.this.ah = 0;
                        MainInteractors.this.c(mergeUserAllDataRsp, str);
                    } else {
                        LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloudmergeUserAllDataback but already timeout,exit app");
                    }
                }
            });
        } else {
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but is no cloud or default packgehuid");
            jfb.e().c(this.ai.getOriginalHuid(), LoginInit.getInstance(this.l).getAccountInfo(1011));
            this.ah = 0;
            this.ac.aQN_(this.ai, Boolean.valueOf(this.h), this.ae, this.aa);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2) {
        AccountHelper.loginByAccountType(this.l, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        AccountHelper.branchCheckIsLoginForHealth(this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i2) {
        AccountHelper.branchMobileHwid(this.l, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i2) {
        AccountHelper.branchGuidUserDownLoadHms(this.l, i2);
    }

    public void f() {
        kvt.e(this.l).d();
    }

    public void n() {
        cej.e().setAdapter(czs.a());
        cej.e().init(this.l);
    }

    public void e() {
        aq();
        ar();
        Context context = this.l;
        if (dzk.b(context, context.getPackageName())) {
            LogUtil.a("Login_MainInteractors", "addPermissions() is SystemApp");
            if (dzk.d()) {
                LogUtil.a("Login_MainInteractors", "addPermissions() supportNewPermissionCheck");
                boolean c2 = dzk.c();
                if (dzk.b()) {
                    LogUtil.a("Login_MainInteractors", "addPermissions() is ChinaRom, has wear device");
                    e(this.l, dzk.a(c2));
                    return;
                }
                e(this.l, c(this.q));
                return;
            }
            e(this.l, c(this.w));
            return;
        }
        LogUtil.a("Login_MainInteractors", "addPermissions() is not SystemApp");
        if (CommonUtil.ag(this.l)) {
            e(this.l, c(this.q));
        } else {
            e(this.l, c(this.w));
        }
    }

    private String[] c(String[] strArr) {
        long b2 = SharedPreferenceManager.b(Integer.toString(10000), "HAS_APPLY_ACTIVITY_RECOGNITION", 0L);
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        if (System.currentTimeMillis() - b2 <= com.huawei.openalliance.ad.constant.Constants.ANALYSIS_EVENT_KEEP_TIME) {
            LogUtil.a("Login_MainInteractors", "checkHavePermission getApplyPermissionsLists index  size ", Integer.valueOf(arrayList.size()));
            arrayList.remove("android.permission.ACTIVITY_RECOGNITION");
        }
        if (PermissionUtil.g()) {
            arrayList.add(com.huawei.openalliance.ad.constant.Constants.POST_NOTIFICATIONS);
            arrayList.remove("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[0]);
        LogUtil.a("Login_MainInteractors", "checkHavePermission getApplyPermissionsLists permissions size ", Integer.valueOf(strArr2.length));
        return strArr2;
    }

    private void e(Context context, String[] strArr) {
        if (!jdi.c(context, strArr)) {
            String[] aPG_ = gno.aPG_(strArr, this.z);
            jdi.bFM_(this.z, aPG_, new h(aPG_), 1);
            for (String str : aPG_) {
                if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(str)) {
                    CommonUtil.k(context, "android.permission.WRITE_EXTERNAL_STORAGE");
                } else if ("android.permission.ACCESS_COARSE_LOCATION".equals(str)) {
                    CommonUtil.k(context, "android.permission.ACCESS_COARSE_LOCATION");
                }
            }
            return;
        }
        c(false);
        this.ac.f();
        LogUtil.a("Login_MainInteractors", "checkHavePermission isHasPermissions");
    }

    public void c(boolean z) {
        if (!z) {
            this.aa.sendEmptyMessageDelayed(14, 5000L);
        } else {
            this.ac.aQH_(this.aa, this.o, this.ab);
        }
    }

    static class d extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<MainInteractors> f2511a;

        public d(MainInteractors mainInteractors) {
            this.f2511a = new WeakReference<>(mainInteractors);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MainInteractors mainInteractors = this.f2511a.get();
            if (mainInteractors == null || intent == null) {
                LogUtil.h("Login_MainInteractors", "AppAutoCheckNewVersionReceiver onReceive mainInteractors or intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("Login_MainInteractors", "mAppAutoCheckNewVersionReceiver onReceive: action = " + action);
            if (!"action_band_auto_check_new_version_result".equals(action)) {
                LogUtil.h("Login_MainInteractors", "mAppAutoCheckNewVersionReceiver onReceive: action nonconformity");
                return;
            }
            int intExtra = intent.getIntExtra("result", 6);
            LogUtil.a("Login_MainInteractors", "result = " + intExtra);
            if (intExtra == 5) {
                LogUtil.a("Login_MainInteractors", "ACTION_APP_AUTO_CHECK_NEW_VERSION_RESULT:AUTO_CHECK_SUCCESS ");
                Intent intent2 = new Intent();
                if (CommonUtil.as()) {
                    intent2.putExtra("hasNewBetaVersion", intent.getBooleanExtra("hasNewBetaVersion", false));
                    intent2.putExtra("isManual", intent.getBooleanExtra("isManual", false));
                    mainInteractors.ab.showNewVersionDialog(mainInteractors.l, intent2);
                    return;
                }
                if (!CommonUtil.bh()) {
                    String stringExtra = intent.getStringExtra("name");
                    if (stringExtra != null) {
                        if (CommonUtil.ag(mainInteractors.l) && stringExtra.contains("Beta")) {
                            LogUtil.a("Login_MainInteractors", "mAppNewVersion is " + stringExtra);
                            return;
                        } else {
                            if (CommonUtil.as() && !stringExtra.contains("Beta")) {
                                LogUtil.a("Login_MainInteractors", "version is " + stringExtra);
                                return;
                            }
                            int intExtra2 = intent.getIntExtra("size", -1);
                            String stringExtra2 = intent.getStringExtra("changelog");
                            boolean booleanExtra = intent.getBooleanExtra("isForced", false);
                            intent2.putExtra("name", stringExtra);
                            intent2.putExtra("size", intExtra2);
                            intent2.putExtra("message", stringExtra2);
                            intent2.putExtra("isForced", Boolean.valueOf(booleanExtra));
                            mainInteractors.ab.showNewVersionDialog(mainInteractors.l, intent2);
                        }
                    } else {
                        LogUtil.b("Login_MainInteractors", "mCheckNewVersionName is null");
                        return;
                    }
                }
                mainInteractors.ab.makeMessage(context);
            }
        }
    }

    private void aq() {
        LogUtil.a("Login_MainInteractors", "registerAutoCheckBroadcast()");
        if (this.j == null) {
            this.j = new d(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_band_auto_check_new_version_result");
        BroadcastManagerUtil.bFA_(this.z.getApplicationContext(), this.j, intentFilter, LocalBroadcast.c, null);
    }

    private void bc() {
        if (this.j != null) {
            try {
                this.z.getApplicationContext().unregisterReceiver(this.j);
            } catch (Exception unused) {
                LogUtil.c("Login_MainInteractors", "unRegisterAppCheckBroadcast exception");
            }
            this.j = null;
        }
    }

    static class l extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MainInteractors> f2513a;

        public l(MainInteractors mainInteractors) {
            this.f2513a = new WeakReference<>(mainInteractors);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MainInteractors mainInteractors = this.f2513a.get();
            if (mainInteractors == null || intent == null) {
                return;
            }
            String action = intent.getAction();
            LogUtil.a("Login_MainInteractors", "mScaleAutoCheckNewVersionReceiver onReceive: action = " + action);
            if (!"action_start_check_scale".equals(action)) {
                LogUtil.h("Login_MainInteractors", "mScaleAutoCheckNewVersionReceiver onReceive: action nonconformity");
                return;
            }
            ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
            if (contentValues == null) {
                LogUtil.h("Login_MainInteractors", "mScaleAutoCheckNewVersionReceiver onReceive: deviceInfo == null");
            } else {
                LogUtil.a("Login_MainInteractors", "mScaleAutoCheckNewVersionReceiver onReceive deviceName:", contentValues.getAsString("name"), "  uniqueId:", contentValues.getAsString("uniqueId"));
                mainInteractors.ac.aQJ_(mainInteractors.o, contentValues);
            }
        }
    }

    private void ar() {
        LogUtil.a("Login_MainInteractors", "registerScaleCheckBroadcast()");
        if (this.y == null) {
            this.y = new l(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("action_start_check_scale");
            BroadcastManagerUtil.bFC_(this.z.getApplicationContext(), this.y, intentFilter, LocalBroadcast.c, null);
        }
    }

    private void ay() {
        if (this.y != null) {
            try {
                this.z.getApplicationContext().unregisterReceiver(this.y);
            } catch (Exception unused) {
                LogUtil.h("Login_MainInteractors", "unRegisterScaleCheckBroadcast exception");
            }
            this.y = null;
        }
    }

    public void w() {
        ao();
        gou.c(0, 20000, 0);
        owq.a(true);
        SharedPreferenceManager.e(this.l, Integer.toString(10000), "sync_cloud_data_show_process_flag", "true", (StorageParams) null);
    }

    public void aaL_(Intent intent) {
        LogUtil.a("Login_MainInteractors", "updateSyncCloudDataStatus to enter");
        if (intent == null) {
            LogUtil.h("Login_MainInteractors", "updateSyncCloudDataStatus intent null");
        } else if ("true".equals(SharedPreferenceManager.b(this.l, Integer.toString(10000), "sync_cloud_data_show_process_flag"))) {
            aaJ_(intent);
        } else {
            aaI_(intent);
        }
    }

    private void al() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Login_MainInteractors");
        ThreadPoolManager.d().execute(new Runnable() { // from class: dzd
            @Override // java.lang.Runnable
            public final void run() {
                MainInteractors.c();
            }
        });
        SampleConfigUtils.resendAsyncWhenReconnect(deviceInfo, "Login_MainInteractors");
    }

    public static /* synthetic */ void c() {
        EcgJsModule.setFeatureReminder("com.huawei.health.h5.ecg");
        EcgJsModule.setFeatureReminder("com.huawei.health.h5.ecgce");
        EcgJsModule.setFeatureReminder("com.huawei.health.h5.vascular-health");
    }

    private void aaJ_(Intent intent) {
        LogUtil.a("Login_MainInteractors", "updateSyncStatusInView sync cloud data process flag is true");
        int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
        if (2 == intExtra) {
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInView HiBroadcastAction.SyncStatusAction.DONE");
            this.aa.sendEmptyMessage(6400);
            SharedPreferenceManager.e(this.l, Integer.toString(10000), "sync_cloud_data_show_process_flag", "false", (StorageParams) null);
            Context context = this.l;
            nrh.d(context, context.getResources().getString(R.string._2130837675_res_0x7f0200ab));
            mcv.d(this.l).d();
            mex.b(this.l).b(mkx.d(-4, System.currentTimeMillis(), 1), System.currentTimeMillis(), 11, null);
            owq.a(false);
            d("sync_cloud_data_finish", 100.0d);
            kts.c(2);
            al();
            return;
        }
        if (3 == intExtra) {
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInView HiBroadcastAction.SyncStatusAction.FAIL");
            if (CommonUtil.aj()) {
                owq.a(false);
            } else {
                owq.a(true);
            }
            SharedPreferenceManager.e(this.l, Integer.toString(10000), "sync_cloud_data_show_process_flag", "false", (StorageParams) null);
            d("sync_cloud_data_fail", 0.0d);
            return;
        }
        if (1 == intExtra) {
            double doubleExtra = intent.getDoubleExtra("com.huawei.hihealth.action_sync_process", 0.0d);
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInView HiBroadcastAction.SyncStatusAction.ONGOING process = ", Double.valueOf(doubleExtra));
            owq.a(true);
            d("ongoing_sync_cloud_data", doubleExtra);
            return;
        }
        if (intExtra == 0) {
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInView HiBroadcastAction.SyncStatusAction.BEGIN");
            owq.a(true);
            d("start_sync_cloud_data", 0.0d);
        }
    }

    private void aaI_(Intent intent) {
        LogUtil.a("Login_MainInteractors", "updateSyncStatusInBackstage sync cloud data process flag is false");
        int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
        if (2 == intExtra) {
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInBackstage HiBroadcastAction.SyncStatusAction.DONE backstage");
            mcv.d(this.l).d();
            this.aa.sendEmptyMessage(6400);
            d("sync_cloud_data_backstage", 100.0d);
            kts.c(2);
            al();
            return;
        }
        if (3 == intExtra) {
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInBackstage HiBroadcastAction.SyncStatusAction.FAIL backstage");
        } else if (1 == intExtra) {
            LogUtil.c("Login_MainInteractors", "updateSyncStatusInBackstage HiBroadcastAction.SyncStatusAction.ONGOING backstage process = ", Double.valueOf(intent.getDoubleExtra("com.huawei.hihealth.action_sync_process", 0.0d)));
        } else if (intExtra == 0) {
            LogUtil.a("Login_MainInteractors", "updateSyncStatusInBackstage HiBroadcastAction.SyncStatusAction.BEGIN backstage");
        }
    }

    private void d(String str, double d2) {
        Intent intent = new Intent();
        intent.setAction("sync_cloud_data_action");
        intent.putExtra("sync_cloud_data_status", str);
        intent.putExtra("sync_cloud_data_process", d2);
        BroadcastManagerUtil.bFI_(this.l, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        LogUtil.a("Login_MainInteractors", "registerSyncBroadcastReceiver enter");
        if (this.u == null) {
            this.u = new n(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        intentFilter.addAction("com.huawei.hihealth.action_sqlite_upgrade_done");
        intentFilter.addAction("com.huawei.hihealth.action_sqlite_upgrade_working");
        intentFilter.addAction("com.huawei.hihealth.action_sqlite_upgrade_exception");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager.getInstance(this.l).registerReceiver(this.u, intentFilter);
        LogUtil.a("Login_MainInteractors", "registerSyncBroadcastReceiver end");
    }

    private void as() {
        LogUtil.a("Login_MainInteractors", "registerLoginSuccessReceiver enter");
        if (this.t == null) {
            this.t = new f(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        LocalBroadcastManager.getInstance(this.l).registerReceiver(this.t, intentFilter);
        LogUtil.a("Login_MainInteractors", "registerLoginSuccessReceiver end");
    }

    private void ba() {
        if (this.t != null) {
            LogUtil.a("Login_MainInteractors", "unregisterLoginSuccessReceiver mReceiver != null");
            LocalBroadcastManager.getInstance(this.l).unregisterReceiver(this.t);
        }
    }

    private void be() {
        if (this.u != null) {
            LogUtil.a("Login_MainInteractors", "unregisterSyncBroadcastReceiver mReceiver != null");
            LocalBroadcastManager.getInstance(this.l).unregisterReceiver(this.u);
            this.u = null;
        }
    }

    static class n extends BroadcastReceiver {
        private WeakReference<MainInteractors> c;

        public n(MainInteractors mainInteractors) {
            this.c = new WeakReference<>(mainInteractors);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            LogUtil.c("Login_MainInteractors", "SyncBroadcastReceiver onReceive()");
            MainInteractors mainInteractors = this.c.get();
            if (mainInteractors != null) {
                if (intent == null) {
                    LogUtil.b("Login_MainInteractors", "onReceive mReceiver intent null");
                    return;
                }
                try {
                    str = intent.getAction();
                } catch (Exception unused) {
                    LogUtil.b("Login_MainInteractors", "SyncBroadcastReceiver getAction exception");
                    str = null;
                }
                Handler handler = mainInteractors.aa;
                if (handler == null) {
                    return;
                }
                mainInteractors.aaG_(str, handler, intent);
                if ("com.huawei.plugin.account.logout".equals(str)) {
                    guw.b();
                    if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                        mainInteractors.ai();
                    } else {
                        Message obtain = Message.obtain();
                        obtain.what = 18;
                        obtain.obj = false;
                        mainInteractors.aa.sendMessage(obtain);
                    }
                    mainInteractors.ac.aQU_(context, intent);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        fep.awH_(this.aa);
    }

    public void q() {
        AchieveMedalsBehaviorTriggedByUi achieveMedalsBehaviorTriggedByUi = new AchieveMedalsBehaviorTriggedByUi(this.l);
        this.g = achieveMedalsBehaviorTriggedByUi;
        achieveMedalsBehaviorTriggedByUi.behave();
        mcv.d(this.l).setAdapter(new PluginAchieveAdapterImpl());
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.7
            @Override // java.lang.Runnable
            public void run() {
                mcv.d(MainInteractors.this.l).t();
                mcv.d(MainInteractors.this.l).n();
                if (MainInteractors.this.g.d()) {
                    return;
                }
                LogUtil.a("Login_MainInteractors", "sync again,toReadSingleTrackData");
                MainInteractors.this.g.b();
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    public void p() {
        this.ac.b(this.o);
        NpsUserShowController.getInstance(BaseApplication.getContext()).threadExecuteNpsModule();
    }

    public void r() {
        be();
        bc();
        ba();
        GolfUpdateMapInteractor golfUpdateMapInteractor = this.x;
        if (golfUpdateMapInteractor != null) {
            golfUpdateMapInteractor.a();
        }
        ay();
        av();
        BroadcastManagerUtil.bFJ_(this.l, this.f);
        az();
        kvt.e(this.l).c();
        PluginOperationAdapterImpl.destroy();
        x();
        s();
        this.v.unSubscribeTrackData(this.l);
        this.v.unRegisterCallback(3);
        AccountHelper.setIsHmsVersionOutDate(false);
        if (PluginSuggestion.getInstance() != null) {
            PluginSuggestion.getInstance().finish();
        }
        kor.a().d();
        ExecutorService executorService = this.o;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.o.shutdown();
    }

    private void az() {
        LogUtil.a("Login_MainInteractors", "stopService()");
        BroadcastManagerUtil.bFF_(this.z.getApplicationContext(), new Intent("ACTION_ACTIVITY_PAUSE_CITY_AS"));
    }

    private boolean d(Context context) {
        boolean z = new PackageManagerHelper(context).a(CommonUtil.p()) >= 20501300;
        LogUtil.b("Login_MainInteractors", "isHMSUpdated:" + z);
        return z;
    }

    private void aaH_(Activity activity) {
        LogUtil.a("Login_MainInteractors", "Enter gotoGooglePlayWeb");
        this.al = 2;
        try {
            activity.startActivityForResult(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("https://play.google.com/store/apps/details?id=com.huawei.hwid")), this.ac.i());
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Login_MainInteractors", "can not find web to hold update hms apk");
        }
    }

    public void a(int i2) {
        Activity activity = this.z;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        int i3 = this.al;
        if (i3 == 1 && i2 == 2002) {
            if (d(activity)) {
                h(AccountHelper.getGuidType());
                return;
            } else {
                aaH_(activity);
                return;
            }
        }
        if (i3 == 2 && i2 == 2003) {
            if (d(activity)) {
                h(AccountHelper.getGuidType());
                return;
            }
            int h2 = this.ac.h();
            LogUtil.a("Login_MainInteractors", "onActivityResult mTryCount:" + h2);
            if (h2 >= 5) {
                a(true);
            } else {
                this.ac.v();
            }
        }
    }

    public static void c(String str) {
        LogUtil.a("Login_MainInteractors", "Enter originalProcess");
        new GuideInteractors(BaseApplication.getContext()).c(str);
    }

    public void l() {
        boolean z = this.f2506a;
        if (z) {
            LogUtil.a("Login_MainInteractors", "loginAfterUpdateHMS(),hwid_is_low_version :", Boolean.valueOf(z), ",timeCount = ", Integer.valueOf(this.af));
            this.f2506a = false;
            if (this.af < 2) {
                ab();
                return;
            }
            LogUtil.h("Login_MainInteractors", "cancel update hms...");
            this.af = 0;
            d();
        }
    }

    public void a(boolean z) {
        AccountHelper.startUserSelectForResult(this.l, z, this.i);
    }

    public void y() {
        LogUtil.a("Login_MainInteractors", "showAccountLoginAlertDialog");
        String upperCase = this.l.getString(R.string._2130841130_res_0x7f020e2a).toUpperCase();
        String upperCase2 = this.l.getString(R.string._2130841210_res_0x7f020e7a).toUpperCase();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.l);
        builder.b(this.l.getString(R.string._2130841209_res_0x7f020e79)).e(this.l.getString(R.string._2130841211_res_0x7f020e7b)).cyV_(upperCase2, new View.OnClickListener() { // from class: com.huawei.health.interactor.MainInteractors.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractors", "onClick(): login immediately.");
                if (MainInteractors.this.o != null && !MainInteractors.this.o.isShutdown()) {
                    MainInteractors.this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.6.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AccountHelper.setIsNoCloudNeedLogin(true);
                            MainInteractors.this.bb();
                        }
                    });
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(upperCase, new View.OnClickListener() { // from class: com.huawei.health.interactor.MainInteractors.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractors", "onClick(): cancel");
                AccountHelper.setIsFirstOpenApp(false);
                MainInteractors.this.ad.init(new gnc(true));
                HuaweiLoginManager.setCloudVersion("0", null);
                CommonUtil.ab("0");
                VersionIsCloud.setIfNeedShowAreaAlert(MainInteractors.this.l, "0");
                MainInteractors.this.ac.e("if_need_set_account_login_entry", true);
                if (MainInteractors.this.o != null && !MainInteractors.this.o.isShutdown()) {
                    MainInteractors.this.o.execute(new Runnable() { // from class: com.huawei.health.interactor.MainInteractors.9.3
                        @Override // java.lang.Runnable
                        public void run() {
                            MainInteractors.this.bb();
                        }
                    });
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public void x() {
        SharedPreferenceManager.e(this.l, Integer.toString(10015), "key_ui_login_exit_hms_apk", "", new StorageParams(0));
        LogUtil.a("Login_MainInteractors", "set KEY_UI_LOGIN_EXIT_HMS_APK null.");
    }

    public void a(long j2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("duration", String.valueOf(j2));
        linkedHashMap.put("status", "0");
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_BOOTING_90010001.value(), linkedHashMap);
    }

    public void u() {
        this.ac.d(this.c);
    }

    private void av() {
        ExecutorService executorService = this.c;
        if (executorService != null) {
            executorService.shutdownNow();
            this.c = null;
        }
    }

    public void m() {
        HwEncryptUtil.b();
    }

    public void z() {
        AccountHelper.updateAccountInfo(this.l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        this.ac.o();
    }

    static class g extends BroadcastReceiver {
        private WeakReference<MainInteractors> b;

        public g(MainInteractors mainInteractors) {
            this.b = new WeakReference<>(mainInteractors);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MainInteractors mainInteractors = this.b.get();
            if (mainInteractors != null) {
                if (intent == null) {
                    LogUtil.h("Login_MainInteractors", "onReceive intent is null");
                    return;
                }
                LogUtil.a("Login_MainInteractors", "RequestAgainSyncCloudDataReceiver onReceive to enter, action = ", intent.getAction());
                if (intent.getBooleanExtra("sync_cloud_data_again", false)) {
                    mainInteractors.w();
                }
            }
        }
    }

    private void am() {
        ThreadPoolManager.d().execute(new i(this));
    }

    static class i implements Runnable {
        private final WeakReference<MainInteractors> d;

        i(MainInteractors mainInteractors) {
            this.d = new WeakReference<>(mainInteractors);
        }

        @Override // java.lang.Runnable
        public void run() {
            MainInteractors mainInteractors = this.d.get();
            if (mainInteractors == null) {
                LogUtil.h("Login_MainInteractors", "SaveLoginDataRunnable mainInteractors is null");
                return;
            }
            Context context = BaseApplication.getContext();
            boolean i = Utils.i();
            String b = SharedPreferenceManager.b(context, String.valueOf(20000), "huawei_account_login_init");
            LogUtil.a("Login_MainInteractors", "loginFlag：", b);
            boolean isBrowseMode = LoginInit.getInstance(context).isBrowseMode();
            LogUtil.a("Login_MainInteractors", "isBrowse: ", Boolean.valueOf(isBrowseMode));
            if (!isBrowseMode && (i || !TextUtils.isEmpty(b))) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit isAllowedLogin");
                HiAccountInfo y = mainInteractors.ac.y();
                AccountInteractors.saveAccountInfo(context, y);
                HiHealthNativeApi.a(context).hiLogin(y, new e(mainInteractors));
            }
            if (i) {
                return;
            }
            LogUtil.a("Login_MainInteractors", "accountmigrate: hiloginAndPluginInit setNotAllowLogin");
            mainInteractors.ac.aRa_(mainInteractors.aa);
        }
    }

    private void ah() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!Utils.g()) {
            this.ac.q();
        }
        this.ac.m();
        this.ac.s();
        q();
        i(true);
        if (this.ac.a(false, BuildConfig.VERSION_CODE)) {
            this.ac.b(BuildConfig.VERSION_CODE);
        } else {
            t();
        }
        OpAnalyticsUtil.getInstance().init(this.l);
        this.ac.f();
        this.ac.n();
        this.ac.k();
        this.ac.ad();
        MainInteractorsUtils.d();
        this.ac.aQS_(this.aa);
        this.ac.z();
        ak();
        if (jki.d() != null) {
            jki.d();
            jki.c(this.l, false);
        }
        this.ac.aQR_(this.aa);
        ppk.e(this.l);
        this.v.clickRecord(1010001, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        owk.b();
        ntd.b().a();
        hpp.d();
        gov.i();
        SampleConfigUtils.resendAsyncWhenReconnect(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Login_MainInteractors"), "Login_MainInteractors");
        sqt.d();
        LogUtil.a("Login_MainInteractors", "initAdapterAndView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void aj() {
        if (fep.a() && !this.p) {
            d(true);
            qbd.a();
        }
        MainInteractorsUtils.c(this.l);
    }

    private void an() {
        if (AccountHelper.getAccountType() == 2) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud accountType == 2   2.0account");
            this.ai.setOriginalHuid(AccountHelper.getHiAccountInfo().getHuid());
            this.ai.setOriginalServiceToken(AccountHelper.getHiAccountInfo().getServiceToken());
        } else {
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud all null");
        }
        if (this.h) {
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud isSendMigrateCommandTimeOut");
            if (TextUtils.isEmpty(this.ae) || TextUtils.isEmpty(this.ag)) {
                return;
            }
            this.ai.setOriginalHuid(this.ae);
            this.ai.setOriginalServiceToken(this.ag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MergeUserAllDataRsp mergeUserAllDataRsp, String str) {
        if (mergeUserAllDataRsp != null) {
            jfb.e().c(this.ae, LoginInit.getInstance(this.l).getAccountInfo(1011));
            if (mergeUserAllDataRsp.getResultCode().intValue() == 0) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but cloud return  var1.getResultCode() = 0");
                this.ac.aQN_(this.ai, Boolean.valueOf(this.h), this.ae, this.aa);
                return;
            } else {
                LogUtil.b("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but cloud return var1 != null but var1.getResultCode() ! = 0");
                return;
            }
        }
        LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud cancel var1 = null");
        try {
            long stringToLong = AccountInteractors.stringToLong(str);
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but cloud return faild error_coed = ", Long.valueOf(stringToLong));
            jfb.e().c(this.ae, LoginInit.getInstance(this.l).getAccountInfo(1011));
            if (stringToLong == 999) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but cloud return faild var2.getResultCode() = 999 ");
                this.aa.sendEmptyMessage(4011);
            } else if (stringToLong == 31005) {
                LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but cloud return faild var2.getResultCode() = 31005 ");
                SharedPreferenceManager.e(this.l, Integer.toString(10015), "is_auth_failed_migrate", "is_auth_failed_migrate_true", new StorageParams(0));
                this.ac.aQN_(this.ai, Boolean.valueOf(this.h), this.ae, this.aa);
            }
            AccountInteractors.uploadMigrateerrorCode(stringToLong);
        } catch (NumberFormatException e2) {
            this.ah = 1;
            AccountInteractors.uploadMigrateerrorCode(1003L);
            LogUtil.a("Login_MainInteractors", "accountmigrate: sendMigrageDataToCloud but cloud return faild var2.getResultCode() NumberFormatException : " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaG_(String str, Handler handler, Intent intent) {
        if ("com.huawei.hihealth.action_sync".equals(str)) {
            this.ac.aQZ_(handler, intent);
        } else if ("com.huawei.hihealth.action_sqlite_upgrade_done".equals(str) || "com.huawei.hihealth.action_sqlite_upgrade_exception".equals(str)) {
            this.ac.aQY_(this.ae, this.aa);
        } else {
            LogUtil.a("Login_MainInteractors", "equalsAction: not action");
        }
    }

    public void b(long j2) {
        if (!CommonUtil.as() || j2 > 8000) {
            LogUtil.h("Login_MainInteractors", "setColdStartEvent not betaVersion or costTime invalid");
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(OpAnalyticsConstants.COLD_START_COST_TIME, String.valueOf(j2));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_BOOTING_90010001.value(), linkedHashMap);
    }

    static class h extends PermissionsResultAction {
        private final String[] b;

        private h(String[] strArr) {
            this.b = strArr;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("Login_MainInteractors", "checkHavePermission onGranted()");
            for (String str : this.b) {
                if ("android.permission.ACTIVITY_RECOGNITION".equals(str)) {
                    LogUtil.a("Login_MainInteractors", "ACTIVITY_RECOGNITION onGranted");
                    Intent intent = new Intent();
                    intent.setPackage(BaseApplication.getAppPackage());
                    intent.setAction("com.huawei.health.start_step_counter");
                    BaseApplication.getContext().sendBroadcast(intent, SecurityConstant.d);
                    gno.a(2);
                }
            }
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            LogUtil.a("Login_MainInteractors", "checkHavePermission onDenied() ");
            for (String str2 : this.b) {
                if ("android.permission.ACTIVITY_RECOGNITION".equals(str2)) {
                    LogUtil.a("Login_MainInteractors", "ACTIVITY_RECOGNITION onDenied");
                    gno.e();
                }
            }
        }
    }
}
