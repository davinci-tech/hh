package com.huawei.health.browseraction;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import com.alipay.sdk.m.p.e;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.MainActivity;
import com.huawei.health.R;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.browseraction.HwSchemeBasicHealthActivity;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.activity.DeviceUsageDescriptionActivity;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.ecologydevice.util.FAUtil;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.health.utils.UpdateProductMapCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.appmarket.HwSmartAppMarketLoadingActivity;
import com.huawei.ui.device.activity.music.MusicMainActivity;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitMindActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepPopularCoursesActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.openservice.ui.OpenServiceActivity;
import com.huawei.up.model.UserInfomation;
import defpackage.bzs;
import defpackage.ccg;
import defpackage.cfi;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.cun;
import defpackage.cvs;
import defpackage.cvt;
import defpackage.cwi;
import defpackage.dij;
import defpackage.dks;
import defpackage.dsl;
import defpackage.efb;
import defpackage.gni;
import defpackage.gnm;
import defpackage.gnv;
import defpackage.gof;
import defpackage.gop;
import defpackage.gpn;
import defpackage.grz;
import defpackage.hps;
import defpackage.ixx;
import defpackage.iyg;
import defpackage.jah;
import defpackage.jbw;
import defpackage.jds;
import defpackage.jdw;
import defpackage.jdx;
import defpackage.jez;
import defpackage.jfu;
import defpackage.jiw;
import defpackage.jjd;
import defpackage.jjj;
import defpackage.jpt;
import defpackage.jrg;
import defpackage.knu;
import defpackage.koq;
import defpackage.moj;
import defpackage.mqs;
import defpackage.mtp;
import defpackage.mxv;
import defpackage.oau;
import defpackage.obb;
import defpackage.oxi;
import defpackage.pep;
import defpackage.pex;
import defpackage.qmf;
import defpackage.quh;
import defpackage.qui;
import defpackage.qul;
import defpackage.qve;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class HwSchemeBasicHealthActivity extends BaseActivity {
    private static String b;
    private static final Map<String, String> d = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.3
        {
            put("00M00D", "e835d102-af95-48a6-ae13-2983bc06f5c0");
            put("00M00F", "b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
            put("00M0CJ", "c943c933-442e-4c34-bcd0-66597f24aaed");
        }
    });
    private Rect ac;
    private CustomTextAlertDialog c;
    private String f;
    private int g;
    private Context i;
    private String n;
    private String r;

    /* renamed from: a, reason: collision with root package name */
    private NoTitleCustomAlertDialog f2200a = null;
    private Uri w = null;
    private String s = "";
    private String x = "";
    private String l = "";
    private boolean e = false;
    private boolean q = false;
    private String m = "-1";
    private String y = null;
    private boolean o = false;
    private boolean k = false;
    private boolean h = false;
    private String v = null;
    private String u = null;
    private String p = null;
    private String t = null;
    private IBaseResponseCallback j = new IBaseResponseCallback() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("HwSchemeBasicHealthActivity", "getAuthorizationStatus");
            if (obj instanceof Integer) {
                HwSchemeBasicHealthActivity.this.g = ((Integer) obj).intValue();
                final DeviceInfo deviceInfo = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "HwSchemeBasicHealthActivity").get(0);
                final String deviceIdentify = deviceInfo.getDeviceIdentify();
                if (HwSchemeBasicHealthActivity.this.g == 100) {
                    LogUtil.a("HwSchemeBasicHealthActivity", "authorizationStatus == AUTHORIZED_STATUS");
                    HwSchemeBasicHealthActivity.this.b(deviceIdentify, (Class<?>) MusicMainActivity.class);
                } else {
                    HwSchemeBasicHealthActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.a("HwSchemeBasicHealthActivity", "Jump To MusicSecondaryMenuActivity");
                            HwSchemeBasicHealthActivity.this.b(deviceInfo, 2);
                            Intent intent = new Intent(HwSchemeBasicHealthActivity.this.i, (Class<?>) MusicSecondaryMenuActivity.class);
                            if (!TextUtils.isEmpty(deviceIdentify)) {
                                intent.putExtra("device_id", deviceIdentify);
                            }
                            intent.putExtra("isFromDeepLink", true);
                            intent.putExtra("musicAuthorizationStatus", false);
                            intent.setFlags(AppRouterExtras.COLDSTART);
                            HwSchemeBasicHealthActivity.this.startActivity(intent);
                            HwSchemeBasicHealthActivity.this.au();
                        }
                    });
                }
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = this;
        Object[] objArr = new Object[2];
        objArr[0] = "savedInstanceState ";
        objArr[1] = Boolean.valueOf(bundle == null);
        LogUtil.a("HwSchemeBasicHealthActivity", objArr);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "intent is null");
            return;
        }
        this.o = false;
        CL_(intent);
        CI_(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        CL_(intent);
    }

    private void CL_(Intent intent) {
        try {
            this.s = intent.getStringExtra("DEVICE_MODULE_ID");
        } catch (BadParcelableException unused) {
            LogUtil.b("HwSchemeBasicHealthActivity", "onCreate getStringExtra catch BadParcelableException");
        }
        this.w = intent.getData();
        this.ac = intent.getSourceBounds();
        this.r = intent.getStringExtra("payload");
        this.h = intent.getBooleanExtra("closeBLEConnection", false);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.v = extras.getString("pushId");
            this.u = extras.getString("serviceId");
            this.p = extras.getString("notifiUri");
            this.t = extras.getString("messageContent");
            LogUtil.a("HwSchemeBasicHealthActivity", "mPushId = ", this.v, "mServiceId =", this.u);
        }
    }

    private void CI_(Intent intent) {
        if (!TextUtils.isEmpty(this.s)) {
            ReleaseLogUtil.e("R_HwSchemeBasicHealthActivity", "is from iconnect dialog");
            this.e = true;
            CN_(intent);
            return;
        }
        if (CG_(intent)) {
            ReleaseLogUtil.e("R_HwSchemeBasicHealthActivity", "from notifyKeepAppAlive gotoKeepAliveSetting");
            return;
        }
        gnv.aPW_(this, intent);
        gnv.aPT_(this, intent);
        mqs.cnm_(this, intent);
        LogUtil.a("HwSchemeBasicHealthActivity", "intent data mSourceRect:", this.ac);
        if (this.ac == null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            Rect rect = new Rect();
            this.ac = rect;
            rect.set(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2, displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2);
            LogUtil.h("HwSchemeBasicHealthActivity", "mSourceRect reset");
        }
        Uri uri = this.w;
        if (uri == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "intent data is null");
            return;
        }
        String path = uri.getPath();
        if (path == null || path.length() <= 0) {
            LogUtil.h("HwSchemeBasicHealthActivity", "path is null or length is 0");
            finish();
            return;
        }
        if (!path.equals("/basicHealth") && !"/devicemanagement".equals(path) && !"/ecgBasicHealth".equals(path) && !"/devicemgr".equals(path)) {
            LogUtil.h("HwSchemeBasicHealthActivity", path + " is error");
            finish();
            return;
        }
        if ("/devicemgr".equals(path)) {
            LogUtil.h("HwSchemeBasicHealthActivity", "SCHEME_PATH_DEVICE_MGR.equals(path)");
            bb();
        }
        this.k = "com.huawei.ohos.health.device".equalsIgnoreCase(ba());
        o();
        f(path);
        ay();
    }

    private boolean CG_(Intent intent) {
        try {
        } catch (Exception e) {
            ReleaseLogUtil.d("R_HwSchemeBasicHealthActivity", "gotoKeepAliveSetting Exception:", ExceptionUtils.d(e));
        }
        if (!jrg.bIX_(intent)) {
            return false;
        }
        jdx.b(new Runnable() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.7
            @Override // java.lang.Runnable
            public void run() {
                OpAnalyticsUtil.getInstance().setRiskWarningEvent("gotoKeepAliveSetting", "NotifyKeepAppAlive");
                obb.d(HwSchemeBasicHealthActivity.this, "11073");
            }
        });
        finish();
        return true;
    }

    private void ay() {
        if (AuthorizationUtils.a(this)) {
            Context context = BaseApplication.getContext();
            ixx.d().a(LoginInit.getInstance(context).getAccountInfo(1011));
            ixx.d().e(LoginInit.getInstance(context).getAccountInfo(1010));
            OpAnalyticsUtil.getInstance().init(context, new IBaseResponseCallback() { // from class: cby
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HwSchemeBasicHealthActivity.this.b(i, obj);
                }
            });
            n();
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        String queryParameter = this.w.getQueryParameter("from");
        if (TextUtils.isEmpty(queryParameter) || !"fa".equals(queryParameter)) {
            return;
        }
        k();
    }

    private void n() {
        String queryParameter = this.w.getQueryParameter("from");
        gop.c(gop.aRd_(queryParameter, this.v, this.u, this.t, this.w), queryParameter, !TextUtils.isEmpty(this.p));
    }

    private void k() {
        LogUtil.a("HwSchemeBasicHealthActivity", "doBiFromFaStartApp");
        HashMap hashMap = new HashMap(16);
        hashMap.put("from", 1);
        hashMap.put("click", 1);
        hashMap.put("FAPackageName", this.w.getQueryParameter("FAPackageName"));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    private void o() {
        if (this.k && Utils.i() && !Utils.f()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ccc
                @Override // java.lang.Runnable
                public final void run() {
                    FAUtil.c(new FAUtil.ResultCallback() { // from class: cca
                        @Override // com.huawei.health.ecologydevice.util.FAUtil.ResultCallback
                        public final void onResult(boolean z) {
                            LogUtil.a("HwSchemeBasicHealthActivity", "checkCachedDataFromFA needUpdate:", Boolean.valueOf(z));
                        }
                    });
                }
            });
        }
    }

    private void bb() {
        if (getApplicationContext().getTheme() == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "setApplicationTheme theme is null");
            return;
        }
        int identifier = getResources().getIdentifier("HealthTheme", TemplateStyleRecord.STYLE, BaseApplication.getAppPackage());
        if (identifier == 0) {
            LogUtil.h("HwSchemeBasicHealthActivity", "setApplicationTheme themeId is 0");
        } else {
            LogUtil.a("HwSchemeBasicHealthActivity", "setApplicationTheme else");
            setTheme(identifier);
        }
    }

    private void aq() {
        LogUtil.a("HwSchemeBasicHealthActivity", "startActivityWithIconnect to MainActivity");
        startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
        au();
    }

    private void CN_(Intent intent) {
        String str;
        if (intent == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "startActivityWithIconnect intent = null");
            au();
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(this.i);
        if (!AuthorizationUtils.a(this.i) || (Utils.i() && !loginInit.getIsLogined())) {
            aq();
            return;
        }
        this.x = d.get(this.s);
        try {
            str = intent.getStringExtra("DEVICE_ID");
        } catch (BadParcelableException unused) {
            LogUtil.b("HwSchemeBasicHealthActivity", "startActivityWithIconnect getStringExtra catch BadParcelableException");
            str = null;
        }
        if (TextUtils.isEmpty(this.x) || TextUtils.isEmpty(str)) {
            LogUtil.h("HwSchemeBasicHealthActivity", "startActivityWithIconnect mProductId = null or macAddress = null");
            au();
            return;
        }
        String c = iyg.c(str);
        this.l = c;
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("HwSchemeBasicHealthActivity", "startActivityWithIconnect mDeviceIdentify");
            au();
            return;
        }
        ReleaseLogUtil.e("R_HwSchemeBasicHealthActivity", "startActivityWithIconnect mDeviceIdentify: ", CommonUtil.l(this.l), ", productId = ", this.x);
        if (cjx.e().c(this.l) != null) {
            bg();
            au();
            return;
        }
        ReleaseLogUtil.e("R_HwSchemeBasicHealthActivity", "not bind this device");
        if (ResourceManager.e().d(this.x) == null) {
            LogUtil.a("HwSchemeBasicHealthActivity", "has not download this device, go to DeviceCategoryFragment");
            dks.e(this.i, "HDK_WEIGHT");
            au();
        } else {
            y();
            au();
        }
    }

    private void y() {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(this.i, "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("DeviceType", "HDK_WEIGHT");
        intent.putExtra("arg1", "DeviceBindWaitingFragment");
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.l);
        contentValues.put("productId", this.x);
        intent.putExtra("commonDeviceInfo", contentValues);
        startActivity(intent);
    }

    private void bg() {
        Bundle bundle = new Bundle();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.x);
        contentValues.put("uniqueId", this.l);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putString("productId", this.x);
        Intent intent = new Intent(this, (Class<?>) DeviceMainActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("kind", HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
        intent.putExtra("view", "MeasureDevice");
        intent.putExtra("isFromIconnectDialog", this.e);
        intent.putExtra("macAddress", this.l);
        intent.setFlags(268435456);
        startActivity(intent);
    }

    private void f(String str) {
        char c;
        LoginInit loginInit = LoginInit.getInstance(this.i);
        LogUtil.a("HwSchemeBasicHealthActivity", "loginit_isLogined ", Boolean.valueOf(loginInit.getIsLogined()));
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1671961272) {
            if (str.equals("/devicemanagement")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -620976973) {
            if (hashCode == 259271376 && str.equals("/ecgBasicHealth")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("/devicemgr")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            e(loginInit);
            return;
        }
        if (c == 1) {
            c(loginInit);
        } else if (c == 2) {
            gnv.aPV_(this, this.w);
        } else {
            b(loginInit);
        }
    }

    private void e(LoginInit loginInit) {
        if (!AuthorizationUtils.a(this.i) || !loginInit.getIsLogined()) {
            LogUtil.a("HwSchemeBasicHealthActivity", "StartHealth to MainActivity");
            startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
            finish();
            return;
        }
        CK_(this.w);
    }

    private void b(LoginInit loginInit) {
        int p = p();
        if ("thirdDevice".equals(q())) {
            f(p);
            return;
        }
        if (!AuthorizationUtils.a(this.i) || !MainInteractors.a() || (!loginInit.getIsLogined() && CommonUtil.af(this))) {
            b(p, loginInit);
        } else if (!loginInit.getIsLogined()) {
            e(p);
        } else {
            f(p);
        }
    }

    private void c(LoginInit loginInit) {
        LogUtil.a("HwSchemeBasicHealthActivity", "Start to devicemgr");
        if (!MainInteractors.a() && t() != 1 && t() != 8) {
            b(this.i);
        } else if (!loginInit.getIsLogined()) {
            LoginInit.getInstance(this.i).browsingToLogin(new IBaseResponseCallback() { // from class: cci
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HwSchemeBasicHealthActivity.this.a(i, obj);
                }
            }, "");
        } else {
            bd();
        }
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (i == 0) {
            bd();
        } else {
            finish();
        }
    }

    private void e(final int i) {
        LogUtil.a("HwSchemeBasicHealthActivity", "jumpLogin to healthType", Integer.valueOf(i));
        LoginInit.getInstance(this.i).browsingToLogin(new IBaseResponseCallback() { // from class: cbt
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                HwSchemeBasicHealthActivity.this.a(i, i2, obj);
            }
        }, "");
    }

    public /* synthetic */ void a(int i, int i2, Object obj) {
        LogUtil.c("HwSchemeBasicHealthActivity", "jumpLogin errorCode =", Integer.valueOf(i2));
        if (i2 == 0) {
            f(i);
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), BaseApplication.getAppPackage() + ".MainActivity");
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
        intent.putExtra("SHORTCUT", "SC_DEVICE");
        gnm.aPB_(context, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        setIntent(null);
        finish();
    }

    private void b(int i, LoginInit loginInit) {
        LogUtil.a("HwSchemeBasicHealthActivity", "jumpToPage healthType", Integer.valueOf(i));
        if (!loginInit.getIsLogined() || !AuthorizationUtils.a(this.i)) {
            LogUtil.a("HwSchemeBasicHealthActivity", "jumpToPage no login");
            ArrayList arrayList = new ArrayList(Arrays.asList(10, 11, 1, 2, 3, 9, 5, 8, 4, 7, 13, 22, 6, 6001, 110));
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            launchIntentForPackage.putExtra(com.huawei.health.messagecenter.model.CommonUtil.PARAM_HEALTH_TYPE, i > -1 ? i : 0);
            if (arrayList.contains(Integer.valueOf(i))) {
                launchIntentForPackage.setData(this.w);
            }
            launchIntentForPackage.putExtra("needLogin", !loginInit.getIsLogined());
            launchIntentForPackage.setPackage(null);
            launchIntentForPackage.setClass(this, MainActivity.class);
            startActivity(launchIntentForPackage);
            finish();
            return;
        }
        this.o = true;
        if (i != 2) {
            Intent intent = new Intent();
            intent.putExtra(com.huawei.health.messagecenter.model.CommonUtil.PARAM_HEALTH_TYPE, 0);
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
        }
        OpAnalyticsUtil.getInstance().init(BaseApplication.getContext());
        f(i);
    }

    private void CK_(Uri uri) {
        LogUtil.a("HwSchemeBasicHealthActivity", "jumpToDeviceActivity enter and schemeData: ", uri);
        try {
            String queryParameter = uri.getQueryParameter("DeviceType");
            this.n = queryParameter;
            LogUtil.a("HwSchemeBasicHealthActivity", "onCreate deviceType = ", queryParameter);
            if ("06E".equals(this.n) || "06D".equals(this.n)) {
                LogUtil.a("HwSchemeBasicHealthActivity", "onCreate go to band or watch");
                ccg.CW_(uri, this.n, this.i, this.ac, getIntent().getBooleanExtra("isClick", false));
                finish();
            } else if ("025".equals(this.n) && !TextUtils.isEmpty(cpa.j.get(uri.getQueryParameter("ProductID")))) {
                LogUtil.a("HwSchemeBasicHealthActivity", "onCreate go to scale");
                CM_(uri);
            } else if (dks.d(this.n) != null) {
                a(uri.getQueryParameter("ProductID"), uri.getQueryParameter("SubMAC"), uri.getQueryParameter("Opcode"), this.n, uri.getQueryParameter("WiseDeviceId"));
            } else {
                if ("082".equals(this.n)) {
                    CH_(uri);
                    return;
                }
                LogUtil.a("HwSchemeBasicHealthActivity", "onCreate go to other condition");
                AppRouter.b("/home/main").e(Constants.HOME_TAB_NAME, "DEVICE").j();
                finish();
            }
        } catch (UnsupportedOperationException unused) {
            LogUtil.b("HwSchemeBasicHealthActivity", "jumpToDeviceActivity error is UnsupportedOperationException");
        }
    }

    private void CH_(final Uri uri) {
        LogUtil.a("HwSchemeBasicHealthActivity", "handleSmartHeadphones");
        final String queryParameter = uri.getQueryParameter("ProductID");
        uri.getQueryParameter("Opcode");
        c(new UpdateProductMapCallback() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.6
            @Override // com.huawei.health.utils.UpdateProductMapCallback
            public void success() {
                ProductMapInfo d2 = ProductMap.d(queryParameter);
                if (d2 == null) {
                    LogUtil.a("HwSchemeBasicHealthActivity", "handleUpdateProductMap success");
                    ccg.a(HwSchemeBasicHealthActivity.this);
                    HwSchemeBasicHealthActivity.this.finish();
                    return;
                }
                String h = d2.h();
                Intent intent = new Intent(HwSchemeBasicHealthActivity.this, (Class<?>) DeviceMainActivity.class);
                intent.putExtra("PID_FROM_QRCODE", queryParameter);
                intent.putExtra("productId", h);
                intent.putExtra("Device_Type", uri.getQueryParameter("DeviceType"));
                intent.putExtra("macAddress", uri.getQueryParameter("SubMAC"));
                intent.putExtra("FROM_SMART_LIFE", true);
                intent.setFlags(268435456);
                HwSchemeBasicHealthActivity.this.startActivity(intent);
                HwSchemeBasicHealthActivity.this.finish();
            }

            @Override // com.huawei.health.utils.UpdateProductMapCallback
            public void onFailure() {
                LogUtil.a("HwSchemeBasicHealthActivity", "handleUpdateProductMap onFailure");
                ccg.a(HwSchemeBasicHealthActivity.this);
                HwSchemeBasicHealthActivity.this.finish();
            }
        });
    }

    private void CM_(Uri uri) {
        if (uri == null) {
            return;
        }
        String queryParameter = uri.getQueryParameter("ProductID");
        String queryParameter2 = uri.getQueryParameter("SubMAC");
        String queryParameter3 = uri.getQueryParameter("WiseDeviceId");
        String str = cpa.j.get(queryParameter);
        LogUtil.a("HwSchemeBasicHealthActivity", "smartProductId ", queryParameter, " productId ", str, " subMac ", queryParameter2, "wiseDeviceId ", queryParameter3);
        if (TextUtils.isEmpty(str)) {
            ccg.a(this);
            finish();
        } else {
            String queryParameter4 = uri.getQueryParameter("Opcode");
            LogUtil.a("HwSchemeBasicHealthActivity", "had Device smartProductId ", queryParameter, " operateCode ", queryParameter4, " productId ", str);
            if ("Add".equals(queryParameter4)) {
                LogUtil.a("HwSchemeBasicHealthActivity", "add Device smartLifeJumpScale OPERATE_ADD");
                HealthDevice.HealthDeviceKind d2 = dks.d(this.n);
                if (d2 != null) {
                    dks.a(this, d2.name(), str);
                }
            } else if ("Delete".equals(queryParameter4)) {
                LogUtil.a("HwSchemeBasicHealthActivity", "del Device smartProductId ", queryParameter);
                if (cjx.e().a(str) != null) {
                    e("DeviceInfoList", str, queryParameter4, queryParameter2, queryParameter3);
                }
            } else if (cjx.e().a(str) != null) {
                e("DeviceInfoList", str, queryParameter4, queryParameter2, queryParameter3);
            } else {
                e("syncDevice", str, queryParameter4, queryParameter2, queryParameter3);
            }
        }
        finish();
    }

    private void a(final String str, final String str2, final String str3, String str4, final String str5) {
        LogUtil.c("HwSchemeBasicHealthActivity", "smartProductId=", str, ", deviceType=", str4, ", operateCode=", str3, ", wiseDeviceId=", str5);
        final HealthDevice.HealthDeviceKind d2 = dks.d(str4);
        c(new UpdateProductMapCallback() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.9
            @Override // com.huawei.health.utils.UpdateProductMapCallback
            public void success() {
                ProductMapInfo d3 = ProductMap.d(str);
                if (d3 == null) {
                    ccg.a(HwSchemeBasicHealthActivity.this);
                    HwSchemeBasicHealthActivity.this.finish();
                    return;
                }
                String h = d3.h();
                if ("Add".equals(str3)) {
                    if (dks.b(HwSchemeBasicHealthActivity.this)) {
                        dks.a(HwSchemeBasicHealthActivity.this, d2.name(), h);
                        HwSchemeBasicHealthActivity.this.finish();
                        return;
                    }
                    Intent intent = new Intent(HwSchemeBasicHealthActivity.this, (Class<?>) DeviceMainActivity.class);
                    intent.putExtra("view", "bindSport");
                    intent.putExtra("productId", h);
                    ContentValues aQw_ = gof.aQw_(str5, str2, h);
                    intent.putExtra("commonDeviceInfo", aQw_);
                    intent.putExtra("Device_Type", d2.name());
                    intent.putExtra("FROM_SMART_LIFE", true);
                    intent.setFlags(268435456);
                    LogUtil.c("HwSchemeBasicHealthActivity", "smartLifeJumpSport deviceInfo ", aQw_.getAsString("uniqueId"), aQw_);
                    HwSchemeBasicHealthActivity.this.startActivity(intent);
                } else if (cjx.e().a(h) != null) {
                    HwSchemeBasicHealthActivity.this.e("DeviceInfoList", h, str3, str2, str5);
                } else if (!"Delete".equals(str3)) {
                    HwSchemeBasicHealthActivity.this.e("syncDevice", h, str3, str2, str5);
                }
                HwSchemeBasicHealthActivity.this.finish();
            }

            @Override // com.huawei.health.utils.UpdateProductMapCallback
            public void onFailure() {
                LogUtil.a("HwSchemeBasicHealthActivity", "handleUpdateProductMap onFailure");
                ccg.a(HwSchemeBasicHealthActivity.this);
                HwSchemeBasicHealthActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        intent.setAction("SWITCH_PLUGINDEVICE");
        ContentValues aQw_ = gof.aQw_(str5, str4, str2);
        LogUtil.c("HwSchemeBasicHealthActivity", "startDeviceManager deviceInfo ", aQw_);
        aQw_.put("productId", str2);
        Bundle bundle = new Bundle();
        bundle.putString("arg1", str);
        bundle.putParcelable("commonDeviceInfo", aQw_);
        bundle.putString("productId", str2);
        bundle.putString("mDeviceId", str5);
        bundle.putString("mac", str4);
        bundle.putString("operateCode", str3);
        bundle.putString("Device_Type", this.n);
        LogUtil.a("HwSchemeBasicHealthActivity", "deviceType is=", this.n);
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("FROM_SMART_LIFE", true);
        intent.putExtras(bundle);
        intent.setFlags(268435456);
        startActivity(intent);
    }

    private void c(final UpdateProductMapCallback updateProductMapCallback) {
        if (jbw.c(this.i)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    if (jbw.d(HwSchemeBasicHealthActivity.this.i, 2)) {
                        updateProductMapCallback.success();
                        LogUtil.a("HwSchemeBasicHealthActivity", "isDownloadMapSuccess is true");
                    } else {
                        LogUtil.h("HwSchemeBasicHealthActivity", "isDownloadMapSuccess is false");
                        updateProductMapCallback.onFailure();
                    }
                }
            });
        } else if (ProductMapParseUtil.b(this.i)) {
            LogUtil.a("HwSchemeBasicHealthActivity", "loadProductMapConfig is true");
            updateProductMapCallback.success();
        } else {
            LogUtil.h("HwSchemeBasicHealthActivity", "loadProductMapConfig is false");
            updateProductMapCallback.onFailure();
        }
    }

    private void w() {
        if (ccg.e("005W") == null) {
            ccg.d(this, "06D");
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setClassName(this.i, "com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity");
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, 34);
        intent.putExtra("dname", "HUAWEI WATCH GT 2 Pro");
        intent.putExtra("isFromWear", true);
        startActivity(intent);
        finish();
    }

    private void f(int i) {
        LogUtil.a("HwSchemeBasicHealthActivity", "skipActivity healthType = ", Integer.valueOf(i));
        if (i == 1) {
            a(1);
            if ("series_course_detail".equals(this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE))) {
                af();
                return;
            }
            if ("series_course".equals(this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE))) {
                c(1);
                return;
            } else if ("detection".equals(this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE))) {
                al();
                return;
            } else {
                ah();
                return;
            }
        }
        if (i == 2) {
            bh();
            return;
        }
        if (i == 3) {
            if ("series_course".equals(this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE))) {
                c(3);
                return;
            } else {
                c(false);
                return;
            }
        }
        if (i == 4) {
            aj();
            return;
        }
        if (i == 5) {
            u();
            return;
        }
        if (i == 7) {
            w();
            return;
        }
        if (i == 30) {
            if ("series_course".equals(this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE))) {
                c(30);
                return;
            } else {
                bf();
                return;
            }
        }
        j(i);
    }

    private void al() {
        this.q = "true".equals(this.w.getQueryParameter("isStart"));
        mtp.d().d(this, this.q, this.w.getQueryParameter(ArkUIXConstants.FROM_TYPE));
        finish();
    }

    private void j(int i) {
        if (i == 6) {
            a(this.i);
            finish();
        }
        if (i == 20) {
            am();
            return;
        }
        if (i == 80) {
            d(this.w.getQueryParameter("deviceId"), this.w.getQueryParameter("sn"));
            return;
        }
        if (i == 90) {
            z();
            return;
        }
        if (i == 6001) {
            l();
            return;
        }
        switch (i) {
            case 8:
                ad();
                break;
            case 9:
                v();
                break;
            case 10:
                ab();
                break;
            case 11:
                a(9);
                ac();
                break;
            case 12:
                aa();
                break;
            default:
                b(i);
                break;
        }
    }

    private void b(int i) {
        if (i == 13) {
            c(true);
            return;
        }
        if (i == 51) {
            ap();
            return;
        }
        if (i == 100) {
            dks.e(this.i, "HDK_ECG");
            finish();
            return;
        }
        if (i != 110) {
            switch (i) {
                case 21:
                    ai();
                    break;
                case 22:
                    c(this.w.getQueryParameter(BleConstants.KEY_PATH), true);
                    break;
                case 23:
                    ae();
                    break;
                default:
                    LogUtil.h("HwSchemeBasicHealthActivity", "skipActivity default");
                    startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
                    finish();
                    break;
            }
            return;
        }
        ag();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, boolean z) {
        LogUtil.a("HwSchemeBasicHealthActivity", "gotoDietDiaryGuideH5");
        if (z) {
            bzs.e().putBiEventFromH5Deeplink(this.w.toString(), "com.huawei.health.h5.diet-diary");
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a("HwSchemeBasicHealthActivity", "gotoDietDiaryGuideH5 path ", str);
            if (str.contains("Analysis") && z) {
                ao();
                return;
            } else {
                sb.append(Constants.H5PRO_PAGE_PREFIX);
                sb.append(str);
            }
        } else {
            if (!z) {
                d(3);
            }
            sb.append("#/diet_recording_tool");
        }
        String query = this.w.getQuery();
        if (!TextUtils.isEmpty(query)) {
            sb.append("?");
            sb.append(query);
        }
        LogUtil.a("HwSchemeBasicHealthActivity", "gotoDietDiaryGuideH5 gotoDietDiaryPath ", sb.toString());
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(sb.toString());
        H5proUtil.putH5ProGlobalBiParams("com.huawei.health.h5.diet-diary?" + query, builder);
        bzs.e().loadH5ProApp(this.i, "com.huawei.health.h5.diet-diary", builder);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").a(268435456).c(this);
        d(2);
        finish();
    }

    private void d(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("operation_type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DIET_ANALYSIS_DEEP_LINK_2040210.value(), hashMap, 0);
    }

    private void ao() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "USER_VIP_INFO_KEY");
        if (TextUtils.isEmpty(b2)) {
            at();
            LogUtil.a("HwSchemeBasicHealthActivity", "initJumpDietAnalysis not member");
            return;
        }
        UserMemberInfo userMemberInfo = (UserMemberInfo) moj.e(b2, UserMemberInfo.class);
        if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1 || gpn.d(userMemberInfo)) {
            at();
            LogUtil.a("HwSchemeBasicHealthActivity", "initJumpDietAnalysis not member");
        } else {
            r();
        }
    }

    private void at() {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/PrivilegeDetail?functionId=3");
        H5proUtil.putH5ProGlobalBiParams("com.huawei.health.h5.vip?" + this.w.getQuery(), builder);
        bzs.e().loadH5ProApp(this.i, "com.huawei.health.h5.vip", builder);
        d(1);
        finish();
    }

    private void r() {
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        ReleaseLogUtil.e("R_HwSchemeBasicHealthActivity", "getDietRecord dayFormat ", Integer.valueOf(b2));
        grz.e(b2, b2, (ResponseCallback<List<quh>>) new b(this));
    }

    static class b implements ResponseCallback<List<quh>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<HwSchemeBasicHealthActivity> f2206a;

        public b(HwSchemeBasicHealthActivity hwSchemeBasicHealthActivity) {
            this.f2206a = new WeakReference<>(hwSchemeBasicHealthActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            Uri data;
            LogUtil.a("HwSchemeBasicHealthActivity", "DietRecordListener errorCode ", Integer.valueOf(i), " list ", list);
            HwSchemeBasicHealthActivity hwSchemeBasicHealthActivity = this.f2206a.get();
            if (hwSchemeBasicHealthActivity == null || hwSchemeBasicHealthActivity.isDestroyed() || hwSchemeBasicHealthActivity.isFinishing()) {
                ReleaseLogUtil.d("R_HwSchemeBasicHealthActivity", "DietRecordListener activity ", hwSchemeBasicHealthActivity);
                return;
            }
            String str = "";
            if (koq.b(list)) {
                ReleaseLogUtil.d("R_HwSchemeBasicHealthActivity", "DietRecordListener list ", list);
                hwSchemeBasicHealthActivity.c("", false);
                return;
            }
            quh quhVar = list.get(0);
            if (quhVar == null) {
                ReleaseLogUtil.d("R_HwSchemeBasicHealthActivity", "DietRecordListener dietRecord is null list ", list);
                hwSchemeBasicHealthActivity.c("", false);
                return;
            }
            List<qul> a2 = quhVar.a();
            if (koq.b(a2)) {
                ReleaseLogUtil.d("R_HwSchemeBasicHealthActivity", "DietRecordListener mealList ", a2);
                hwSchemeBasicHealthActivity.c("", false);
                return;
            }
            boolean z = false;
            for (qul qulVar : a2) {
                if (qulVar != null && !koq.b(qulVar.c())) {
                    z = true;
                }
            }
            ReleaseLogUtil.e("R_HwSchemeBasicHealthActivity", "DietRecordListener hasFood ", Boolean.valueOf(z));
            if (!z) {
                hwSchemeBasicHealthActivity.c("", false);
                return;
            }
            UserInfomation c = gni.c();
            LogUtil.a("HwSchemeBasicHealthActivity", "DietRecordListener userInformation ", c);
            if (c == null) {
                ReleaseLogUtil.d("R_HwSchemeBasicHealthActivity", "DietRecordListener userInformation is null");
                hwSchemeBasicHealthActivity.av();
                return;
            }
            if (c.getAge() <= 0 || !c.isHeightValid() || !c.isWeightValid()) {
                hwSchemeBasicHealthActivity.av();
                return;
            }
            Intent intent = hwSchemeBasicHealthActivity.getIntent();
            if (intent != null && (data = intent.getData()) != null) {
                str = data.getQueryParameter(BleConstants.KEY_PATH);
            }
            LogUtil.a("HwSchemeBasicHealthActivity", "DietRecordListener path ", str);
            hwSchemeBasicHealthActivity.c(str, false);
        }
    }

    private void ag() {
        try {
            String queryParameter = this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE);
            String queryParameter2 = this.w.getQueryParameter("devicePackageName");
            if (TextUtils.isEmpty(queryParameter2)) {
                mxv.b(this.i, queryParameter);
            } else {
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                builder.addCustomizeArg("enterType", queryParameter);
                builder.addCustomizeArg("devicePackageName", queryParameter2);
                bzs.e().loadH5ProApp(this, "com.huawei.health.h5.ecg", builder);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwSchemeBasicHealthActivity", "goToQuickAppEcg IllegalArgumentException:", e.getMessage());
        }
        finish();
    }

    private void bd() {
        int t = t();
        LogUtil.a("HwSchemeBasicHealthActivity", "skipDeviceMgrActivity deviceMgrType:", Integer.valueOf(t));
        switch (t) {
            case 1:
                aw();
                break;
            case 2:
                as();
                break;
            case 3:
                h();
                break;
            case 4:
                ar();
                break;
            case 5:
                bk();
                break;
            case 6:
                be();
                break;
            case 7:
                bi();
                break;
            case 8:
                ax();
                break;
            case 9:
                AppRouter.b("/HomeHealth/DeviceMoreListActivity").c(com.huawei.haf.application.BaseApplication.e());
                au();
                break;
            default:
                LogUtil.h("HwSchemeBasicHealthActivity", "skipDeviceMgrActivity default");
                b(this.i);
                break;
        }
    }

    private void aw() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "HwSchemeBasicHealthActivity");
        b(koq.b(deviceList) ? new DeviceInfo() : deviceList.get(0), 1);
        x();
    }

    /* renamed from: com.huawei.health.browseraction.HwSchemeBasicHealthActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        AnonymousClass8() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String unused = HwSchemeBasicHealthActivity.b = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainContentcenterDbankcdnNew", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: cch
                @Override // java.lang.Runnable
                public final void run() {
                    HwSchemeBasicHealthActivity.AnonymousClass8.this.c();
                }
            });
        }

        public /* synthetic */ void c() {
            if (!TextUtils.isEmpty(HwSchemeBasicHealthActivity.b)) {
                HwSchemeBasicHealthActivity.this.ak();
                return;
            }
            LogUtil.h("HwSchemeBasicHealthActivity", "mWatchFaceGrsUrl is empty");
            HwSchemeBasicHealthActivity hwSchemeBasicHealthActivity = HwSchemeBasicHealthActivity.this;
            hwSchemeBasicHealthActivity.b(hwSchemeBasicHealthActivity.i);
        }
    }

    private void x() {
        LogUtil.a("HwSchemeBasicHealthActivity", "start to get watchFace url");
        ThreadPoolManager.d().execute(new AnonymousClass8());
    }

    private void as() {
        String deviceIdentify;
        DeviceCapability e;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || defaultAdapter.isEnabled()) {
                List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "HwSchemeBasicHealthActivity");
                if (koq.b(deviceList)) {
                    LogUtil.h("HwSchemeBasicHealthActivity", "jumpMusic currentDeviceInfo is null");
                    b(this.i);
                    return;
                }
                for (DeviceInfo deviceInfo : deviceList) {
                    if (deviceInfo.getDeviceConnectState() == 2 && (e = cvs.e((deviceIdentify = deviceInfo.getDeviceIdentify()))) != null && e.isSupportMusicInfoList()) {
                        b(deviceInfo, 2);
                        b(deviceIdentify, MusicSecondaryMenuActivity.class);
                        return;
                    }
                }
                LogUtil.h("HwSchemeBasicHealthActivity", "jumpMusic not support Music");
                b(this.i);
                return;
            }
            LogUtil.c("HwSchemeBasicHealthActivity", "jumpMusic adapter is not Enabled");
            b(this.i);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("HwSchemeBasicHealthActivity", "addDevice activity not found exception.");
        }
    }

    private void h() {
        try {
            if ((this.i.getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) this.i.getSystemService("bluetooth") : null) == null) {
                LogUtil.h("HwSchemeBasicHealthActivity", "addDevice bluetoothManager is null");
                bc();
            } else {
                bm();
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("HwSchemeBasicHealthActivity", "addDevice activity not found exception.");
        }
    }

    private void bm() {
        b(new DeviceInfo(), 3);
        Intent intent = new Intent();
        intent.setClassName(this.i, "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
        gnm.aPB_(this.i, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, int i) {
        HashMap hashMap = new HashMap();
        if (deviceInfo != null) {
            hashMap.put("device_name_key", deviceInfo.getDeviceName());
            hashMap.put("product_type", Integer.valueOf(deviceInfo.getProductType()));
        }
        hashMap.put("operation_type", Integer.valueOf(i));
        String value = AnalyticsValue.DEVICE_MANAGER_DEEP_LINK_2060094.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            Object value2 = entry.getValue();
            if (value2 == null) {
                LogUtil.h("HwSchemeBasicHealthActivity", "the mapValue is null when mapKey is:", str);
            } else {
                linkedHashMap.put(str, value2.toString());
            }
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(value, linkedHashMap);
    }

    private void bc() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.i).e(R.string._2130844216_res_0x7f021a38).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwSchemeBasicHealthActivity", "Bluetooth not supported");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    private void ar() {
        LogUtil.a("HwSchemeBasicHealthActivity", "enter jumpAppHelp");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwSchemeBasicHealthActivity");
        if (koq.b(deviceList)) {
            deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HwSchemeBasicHealthActivity");
        }
        if (koq.b(deviceList)) {
            LogUtil.h("HwSchemeBasicHealthActivity", "jumpAppHelp lists is empty");
            b(this.i);
            return;
        }
        final DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "jumpAppHelp currentDeviceInfo is null");
            b(this.i);
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (!jfu.c(deviceInfo.getProductType()).z()) {
            LogUtil.h("HwSchemeBasicHealthActivity", "jumpAppHelp no support Help");
            b(this.i);
            return;
        }
        DeviceCapability e = cvs.e(deviceIdentify);
        if (e != null && e.isSupportHelp()) {
            b(deviceInfo, 4);
            ThreadPoolManager.d().execute(new Runnable() { // from class: cbu
                @Override // java.lang.Runnable
                public final void run() {
                    HwSchemeBasicHealthActivity.this.d(deviceInfo);
                }
            });
        } else {
            LogUtil.h("HwSchemeBasicHealthActivity", "currentDeviceInfo not support appHelp");
            b(this.i);
        }
    }

    public /* synthetic */ void d(DeviceInfo deviceInfo) {
        String d2 = pex.a().d(deviceInfo);
        this.f = d2;
        LogUtil.c("HwSchemeBasicHealthActivity", "openAppHelpActivity url is:", d2);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: cbq
            @Override // java.lang.Runnable
            public final void run() {
                HwSchemeBasicHealthActivity.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: an, reason: merged with bridge method [inline-methods] */
    public void c() {
        Intent intent = new Intent(this.i, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", this.f);
        intent.putExtra(Constants.JUMP_MODE_KEY, 0);
        startActivity(intent);
        au();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, Class<?> cls) {
        Intent intent = new Intent(this.i, cls);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("device_id", str);
        }
        intent.setFlags(AppRouterExtras.COLDSTART);
        startActivity(intent);
        au();
    }

    private void be() {
        if (jiw.a().f()) {
            LogUtil.h("HwSchemeBasicHealthActivity", "startAppMarket isDeviceVersionNotSupport");
            az();
        } else {
            jiw.a().e(this.i, new AppBundleLauncher.InstallCallback() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.14
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    jiw.a().j();
                    if (cwi.c(jpt.a("HwSchemeBasicHealthActivity"), 44)) {
                        try {
                            Intent intent2 = new Intent();
                            intent2.setClass(HwSchemeBasicHealthActivity.this.i, HwSmartAppMarketLoadingActivity.class);
                            gnm.aPB_(HwSchemeBasicHealthActivity.this.i, intent2);
                            HwSchemeBasicHealthActivity.this.finish();
                            return false;
                        } catch (ActivityNotFoundException unused) {
                            LogUtil.b("HwSchemeBasicHealthActivity", "startAppMarket NotFoundException");
                            return false;
                        }
                    }
                    jiw.a().d(true);
                    return false;
                }
            });
            oau.a();
        }
    }

    private void az() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.i).b(R.string._2130839506_res_0x7f0207d2).d(R.string.IDS_app_market_device_update).cyR_(R.string._2130841855_res_0x7f0210ff, new View.OnClickListener() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HwSchemeBasicHealthActivity.this.c != null) {
                    HwSchemeBasicHealthActivity.this.c.dismiss();
                    HwSchemeBasicHealthActivity.this.c = null;
                }
                if (!BaseApplication.getActivity().isFinishing()) {
                    HwSchemeBasicHealthActivity.this.au();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130841856_res_0x7f021100, new View.OnClickListener() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwSchemeBasicHealthActivity.this.bl();
                if (!BaseApplication.getActivity().isFinishing()) {
                    HwSchemeBasicHealthActivity.this.au();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.c = a2;
        a2.setCancelable(false);
        if (this.c.isShowing() || BaseApplication.getActivity().isFinishing()) {
            return;
        }
        this.c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bl() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: cbz
            @Override // java.lang.Runnable
            public final void run() {
                HwSchemeBasicHealthActivity.this.j();
            }
        });
    }

    public /* synthetic */ void j() {
        final DeviceInfo a2 = jpt.a("HwSchemeBasicHealthActivity");
        if (a2 == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "startDeviceUpdate deviceInfo is null");
        } else {
            BaseApplication.getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(HwSchemeBasicHealthActivity.this.i, UpdateVersionActivity.class);
                    intent.putExtra("device_id", a2.getDeviceIdentify());
                    gnm.aPB_(HwSchemeBasicHealthActivity.this.i, intent);
                }
            });
        }
    }

    private void bk() {
        DeviceInfo a2 = jpt.a("HwSchemeBasicHealthActivity");
        oau.g();
        pep.b(this.i);
        if (a2 == null) {
            pep.c(this.i, "");
        } else {
            pep.c(this.i, a2.getDeviceIdentify());
        }
        au();
    }

    private void bi() {
        String str = jah.c().e("domain_play_machine") + "/hwtips/app/health_app/zh-CN/index.html#/app?cid=11068";
        Intent intent = new Intent();
        intent.setClass(this.i, WebViewActivity.class);
        intent.putExtra("url", str);
        gnm.aPB_(this.i, intent);
        au();
    }

    private void ax() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
            LogUtil.c("HwSchemeBasicHealthActivity", "jumpMusic adapter is not Enabled");
            b(this.i);
        } else {
            if (jez.e() != null) {
                g();
                return;
            }
            LogUtil.h("HwSchemeBasicHealthActivity", "jumpMusicList getDeviceList iPhoneServiceAIDL is null");
            jez.a(BaseApplication.getContext());
            new Handler().postDelayed(new Runnable() { // from class: cbs
                @Override // java.lang.Runnable
                public final void run() {
                    HwSchemeBasicHealthActivity.this.g();
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public void g() {
        LogUtil.a("HwSchemeBasicHealthActivity", "beginJumpMusicList");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "HwSchemeBasicHealthActivity");
        if (koq.b(deviceList) || cvt.c(deviceList.get(0).getProductType())) {
            LogUtil.h("HwSchemeBasicHealthActivity", "jumpMusic currentDeviceInfo is null");
            b("", MusicMainActivity.class);
            return;
        }
        String deviceIdentify = deviceList.get(0).getDeviceIdentify();
        DeviceCapability e = cvs.e(deviceIdentify);
        if (e == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "deviceCapability is null");
            b(this.i);
        } else if (!e.isSupportMusicInfoList()) {
            LogUtil.h("HwSchemeBasicHealthActivity", "jumpMusicList not support Music");
            b(this.i);
        } else {
            b(deviceIdentify);
        }
    }

    private void b(String str) {
        LogUtil.a("HwSchemeBasicHealthActivity", "go to jumpMusicListImpl");
        if (jjj.g()) {
            LogUtil.a("HwSchemeBasicHealthActivity", "isSupportAuthorizationStatus()");
            jjd.b(BaseApplication.getContext()).e(this.j);
        } else {
            LogUtil.a("HwSchemeBasicHealthActivity", "go to musicMainActivity");
            b(str, MusicMainActivity.class);
        }
    }

    private int d(String str) {
        try {
            return CommonUtils.h(this.w.getQueryParameter(str));
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            LogUtil.b("HwSchemeBasicHealthActivity", "get type IllegalArgumentException:", str, LogAnonymous.b(e));
            return -1;
        }
    }

    private String c(String str) {
        Uri uri = this.w;
        if (uri == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "getQueryParameter mSchemeData is null");
            return "";
        }
        try {
            return uri.getQueryParameter(str);
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            LogUtil.b("HwSchemeBasicHealthActivity", "getQueryParameter exception ", LogAnonymous.b(e));
            return "";
        }
    }

    private void a(Context context) {
        String c = c("id");
        String c2 = c("from");
        LogUtil.a("HwSchemeBasicHealthActivity", "bundleGotoHealthModel id ", c, " from ", c2);
        if (TextUtils.isEmpty(c) && TextUtils.isEmpty(c2)) {
            dsl.ZK_(context, null);
        } else {
            m();
            dsl.ZL_(context, this.w);
        }
    }

    private void l() {
        if (dsl.c() == null) {
            LogUtil.h("HwSchemeBasicHealthActivity", "bundleGotoHealthModel healthModelApi is null");
            finish();
        } else {
            AppRouter.b("/PluginHealthModel/HealthWeekReportActivity").e("isFromExclusiveRightsPage", true).c(this);
            finish();
        }
    }

    private int p() {
        return d(com.huawei.health.messagecenter.model.CommonUtil.PARAM_HEALTH_TYPE);
    }

    private String q() {
        try {
            return this.w.getQueryParameter("from");
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwSchemeBasicHealthActivity", "getSchemeFrom IllegalArgumentException:", e.getMessage());
            return "";
        }
    }

    private String s() {
        try {
            return this.w.getQueryParameter("deviceId");
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwSchemeBasicHealthActivity", "getProductId IllegalArgumentException:", e.getMessage());
            return "";
        }
    }

    private int t() {
        return d("deviceMgrType");
    }

    private void ah() {
        LogUtil.a("HwSchemeBasicHealthActivity", "mSchemeData: ", this.w.toString());
        this.y = this.w.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE);
        this.q = "true".equals(this.w.getQueryParameter("isStart"));
        String queryParameter = this.w.getQueryParameter(ArkUIXConstants.FROM_TYPE);
        String queryParameter2 = this.w.getQueryParameter("from");
        if (!TextUtils.isEmpty(queryParameter)) {
            this.m = queryParameter;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("isStartPhoneSleep", "detection".equals(this.y));
        bundle.putBoolean("isStartNow", this.q);
        bundle.putString(ArkUIXConstants.FROM_TYPE, this.m);
        bundle.putString("pullTime", this.w.getQueryParameter("pullTime"));
        int c = "fa".equals(queryParameter2) ? 5 : jds.c(queryParameter2, 10);
        LogUtil.a("HwSchemeBasicHealthActivity", "fromVal: ", Integer.valueOf(c));
        bundle.putInt("from", c);
        KnitSleepDetailActivity.dqM_(this, bundle);
        m();
        finish();
    }

    private void c(int i) {
        Intent intent = new Intent(this, (Class<?>) SleepPopularCoursesActivity.class);
        intent.setPackage(this.i.getPackageName());
        intent.setFlags(268435456);
        intent.putExtra(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, i);
        jdw.bGh_(intent, this.i);
        finish();
    }

    private void af() {
        Intent intent = new Intent(this, (Class<?>) SeriesCourseListActivity.class);
        intent.putExtra("id", this.w.getQueryParameter("id"));
        intent.putExtra(WebViewHelp.BI_KEY_PULL_FROM, this.w.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM));
        intent.putExtra("resourceId", this.w.getQueryParameter("resourceId"));
        intent.putExtra("resourceName", this.w.getQueryParameter("resourceName"));
        intent.putExtra("pullOrder", this.w.getQueryParameter("pullOrder"));
        intent.putExtra("from", this.w.getQueryParameter("from"));
        intent.putExtra(WebViewHelp.BI_KEY_PULL_FROM, this.w.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM));
        intent.putExtra("resourceId", this.w.getQueryParameter("resourceId"));
        intent.putExtra("resourceName", this.w.getQueryParameter("resourceName"));
        intent.setPackage(this.i.getPackageName());
        intent.setFlags(268435456);
        jdw.bGh_(intent, this.i);
        finish();
    }

    private void bf() {
        Intent intent = new Intent(this, (Class<?>) KnitMindActivity.class);
        intent.setPackage(this.i.getPackageName());
        intent.setFlags(268435456);
        jdw.bGh_(intent, this.i);
        finish();
    }

    private void c(final boolean z) {
        b(new IBaseResponseCallback() { // from class: ccb
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HwSchemeBasicHealthActivity.this.d(z, i, obj);
            }
        });
    }

    public /* synthetic */ void d(final boolean z, int i, Object obj) {
        HandlerExecutor.a(new Runnable() { // from class: cbv
            @Override // java.lang.Runnable
            public final void run() {
                HwSchemeBasicHealthActivity.this.b(z);
            }
        });
    }

    public /* synthetic */ void b(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("start_game", z);
        qmf.dFD_(this.i, bundle);
        finish();
    }

    private void bh() {
        int d2 = d(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE);
        LogUtil.a("HwSchemeBasicHealthActivity", "goToWeightActivity pageType is ", Integer.valueOf(d2), " ,mIsColdStart ", Boolean.valueOf(this.o), ",mIsFromFA:" + this.k);
        Intent intent = new Intent();
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser != null) {
            intent.putExtra("weight_user_id", currentUser.i());
        }
        if (this.k) {
            intent.putExtra("moveTaskToBack", true);
            intent.setFlags(268435456);
        }
        if (d2 != 10 && this.o) {
            intent.putExtra("base_health_data_type_key", 1);
            if (this.h) {
                startActivityForResult(intent, 1003);
                return;
            }
            intent.putExtra("BASE_HEALTH_DATA_TYPE_COLD_START_KEY", "weight");
            grz.aST_("", intent);
            finish();
            return;
        }
        if (d2 == 10 && this.o) {
            intent.putExtra("base_health_data_type_key", 10);
            intent.putExtra("BASE_HEALTH_DATA_TYPE_COLD_START_KEY", TextUtils.isEmpty(this.w.getQuery()) ? "#/guide_index" : this.w.getQuery());
            grz.aST_(String.valueOf(2), intent);
            finish();
            return;
        }
        if (d2 == 10) {
            CJ_(intent);
            return;
        }
        intent.putExtra("base_health_data_type_key", 1);
        if (this.h) {
            startActivityForResult(intent, 1003);
            return;
        }
        intent.putExtra(ParamConstants.Param.FLAGS, AppRouterExtras.COLDSTART);
        grz.aST_("", intent);
        finish();
    }

    private void CJ_(Intent intent) {
        LogUtil.a("HwSchemeBasicHealthActivity", "is from fasting deeplink");
        bzs.e().putBiEventFromH5Deeplink(this.w.toString(), "com.huawei.health.h5.fasting-lite");
        ThreadPoolManager.d().execute(new Runnable() { // from class: cbr
            @Override // java.lang.Runnable
            public final void run() {
                HwSchemeBasicHealthActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        qui c = qve.c();
        boolean z = c != null && c.c();
        if (c != null && c.d() != null && c.d().b() != null && c.d().b().g() > 0) {
            runOnUiThread(new Runnable() { // from class: cce
                @Override // java.lang.Runnable
                public final void run() {
                    HwSchemeBasicHealthActivity.this.a();
                }
            });
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        String query = this.w.getQuery();
        String str = z ? "#/plan_setting" : "#/guide_index";
        LogUtil.a("HwSchemeBasicHealthActivity", "isJoinFastingLite=", Boolean.valueOf(z), ", path=", str);
        if (TextUtils.isEmpty(query)) {
            builder.addPath(str);
        } else {
            LogUtil.a("HwSchemeBasicHealthActivity", "initWeightDeepLink parameters ", query);
            builder.addPath(str + "?" + query);
        }
        builder.addCustomizeJsModule("tradeApi", JsTradeApi.class);
        H5proUtil.putH5ProGlobalBiParams("com.huawei.health.h5.fasting-lite?" + query, builder);
        bzs.e().loadH5ProApp(this.i, "com.huawei.health.h5.fasting-lite", builder);
        finish();
    }

    public /* synthetic */ void a() {
        new NoTitleCustomAlertDialog.Builder(this).e(R.string._2130838567_res_0x7f020427).czC_(R.string._2130838282_res_0x7f02030a, new View.OnClickListener() { // from class: ccd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HwSchemeBasicHealthActivity.this.CO_(view);
            }
        }).e().show();
    }

    public /* synthetic */ void CO_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{44306});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("HwSchemeBasicHealthActivity", "getStressStatisticsDatas errorCode = " + i);
                if (obj == null) {
                    iBaseResponseCallback.d(100001, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    iBaseResponseCallback.d(100001, null);
                } else if (((HiHealthData) ((List) sparseArray.get(44306)).get(0)) != null) {
                    iBaseResponseCallback.d(0, null);
                } else {
                    iBaseResponseCallback.d(100001, null);
                }
            }
        });
    }

    private void aj() {
        d(AnalyticsValue.TEMPERATURE_2060073.value(), 1);
        if (efb.j()) {
            Intent intent = new Intent();
            intent.setClass(this, KnitTemperatureActivity.class);
            gnm.aPB_(this.i, intent);
        } else {
            HealthDataDetailActivity.a(this.i, "BodyTemperatureCardConstructor", 24);
        }
        finish();
    }

    private void u() {
        Intent intent = new Intent();
        intent.setClass(this, KnitBloodOxygenDetailActivity.class);
        gnm.aPB_(this.i, intent);
        finish();
    }

    private void ad() {
        g(AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_DETAIL_2010027.value());
        if (this.k) {
            Intent intent = new Intent(this, (Class<?>) HealthDataDetailActivity.class);
            intent.putExtra("extra_service_id", "BloodSugarCardConstructor");
            intent.putExtra("extra_time_stamp", 0L);
            intent.putExtra("extra_page_type", 8);
            intent.setFlags(268468224);
            gnm.aPB_(this.i, intent);
        } else {
            HealthDataDetailActivity.a(this, "BloodSugarCardConstructor", 8);
        }
        finish();
    }

    private void d(String str, String str2) {
        LogUtil.a("HwSchemeBasicHealthActivity", "goToBloodSugarDeviceManagePage productId: ", str, ";uniqueId: ", CommonUtil.l(str2));
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setAction("SWITCH_PLUGINDEVICE");
        bundle.putString("arg1", "DeviceInfoList");
        bundle.putString("productId", str);
        bundle.putString("uniqueId", str2);
        intent.setPackage(getPackageName());
        intent.setClassName(getPackageName(), "com.huawei.health.device.ui.DeviceMainActivity");
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", str);
        contentValues.put("uniqueId", str2);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        intent.putExtras(bundle);
        intent.putExtra("isBindSuccess", true);
        intent.putExtra("thirdDeviceToApp", true);
        startActivity(intent);
        finish();
    }

    private void z() {
        Intent intent = new Intent();
        intent.putExtra("productId", s());
        intent.setClass(this, DeviceUsageDescriptionActivity.class);
        if (this.k) {
            intent.setFlags(268468224);
        }
        startActivity(intent);
        finish();
    }

    private void g(String str) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.i.getApplicationContext(), str, hashMap, 0);
    }

    private void d(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(getApplicationContext(), str, hashMap, 0);
    }

    private void v() {
        Intent intent = new Intent();
        intent.setClass(this, KnitBloodPressureActivity.class);
        if (this.k) {
            intent.setFlags(268468224);
        }
        gnm.aPB_(this.i, intent);
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void am() {
        /*
            r5 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            android.net.Uri r1 = r5.w
            java.lang.String r2 = "sportType"
            r3 = -1
            if (r1 == 0) goto L1c
            java.lang.String r1 = r1.getQueryParameter(r2)
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L1c
            int r1 = health.compact.a.CommonUtils.h(r1)
            goto L1d
        L1c:
            r1 = r3
        L1d:
            if (r1 != r3) goto L21
            r1 = 283(0x11b, float:3.97E-43)
        L21:
            r0.putExtra(r2, r1)
            java.lang.Class<com.huawei.ui.main.stories.history.SportHistoryActivity> r1 = com.huawei.ui.main.stories.history.SportHistoryActivity.class
            r0.setClass(r5, r1)
            android.content.Context r1 = r5.i
            defpackage.gnm.aPB_(r1, r0)
            r5.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.browseraction.HwSchemeBasicHealthActivity.am():void");
    }

    private void ai() {
        Intent intent = new Intent();
        intent.putExtra(BleConstants.SPORT_TYPE, OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE);
        if (this.k) {
            intent.addFlags(268468224);
        }
        intent.setClass(this, SportHistoryActivity.class);
        gnm.aPB_(this.i, intent);
        finish();
    }

    private void ae() {
        LogUtil.a("HwSchemeBasicHealthActivity", "goToSportDataDetailActivity");
        hps.a(e("startTime"), e("endTime"), new IBaseResponseCallback() { // from class: cbw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HwSchemeBasicHealthActivity.this.e(i, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("HwSchemeBasicHealthActivity", "onResponse code: ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof knu)) {
            LogUtil.a("HwSchemeBasicHealthActivity", "JumpToTrackDetailActivity");
            Bundle CF_ = CF_((knu) obj);
            CF_.putBoolean("ExitApp", this.k);
            Guidepost zF_ = AppRouter.b("/PluginMotionTrack/TrackDetailActivity").zF_(CF_);
            if (this.k) {
                zF_.a(268468224);
            }
            zF_.c(this.i);
            finish();
        }
    }

    private void ap() {
        LogUtil.c("HwSchemeBasicHealthActivity", "gotoWeightHistoryRecord");
        Intent intent = new Intent(this, (Class<?>) HealthDataHistoryActivity.class);
        if (this.k) {
            intent.putExtra("moveTaskToBack", true);
            intent.setFlags(268435456);
        }
        if (this.h) {
            startActivityForResult(intent, 1003);
        } else {
            gnm.aPB_(this.i, intent);
            finish();
        }
    }

    private long e(String str) {
        try {
            String queryParameter = this.w.getQueryParameter(str);
            LogUtil.a("HwSchemeBasicHealthActivity", "getSchemeStartTime  parameter=", queryParameter);
            return CommonUtils.g(queryParameter);
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwSchemeBasicHealthActivity", "getSchemeFrom IllegalArgumentException:", e.getMessage());
            return 0L;
        }
    }

    private Bundle CF_(knu knuVar) {
        if (TextUtils.isEmpty(knuVar.d())) {
            ReleaseLogUtil.c("HwSchemeBasicHealthActivity", "showTrackMap contentpath is err!");
            return null;
        }
        if (knuVar.b() == null) {
            ReleaseLogUtil.c("HwSchemeBasicHealthActivity", "showTrackMap simplifyData is null!");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", knuVar.b());
        bundle.putString("contentpath", knuVar.d());
        if (knuVar.b().requestSportType() == 512 && (Collections.EMPTY_LIST instanceof Serializable)) {
            bundle.putSerializable("subTrackDetail", (Serializable) Collections.EMPTY_LIST);
        }
        bundle.putBoolean("isAfterSport", false);
        bundle.putBoolean("isNotNeedDeleteFile", false);
        return bundle;
    }

    private void ab() {
        String query = this.w.getQuery();
        if (TextUtils.isEmpty(query)) {
            AppRouter.b("/PluginHealthZone/FamilyHealthTempActivity").c(this.i);
        } else {
            AppRouter.zi_(Uri.parse("huaweischeme://healthapp/PluginHealthZone/FamilyHealthTempActivity?" + query)).c(this.i);
        }
        finish();
    }

    private void aa() {
        Intent intent = new Intent();
        intent.setClass(this, OpenServiceActivity.class);
        startActivity(intent);
        finish();
    }

    private void ac() {
        if (efb.f()) {
            Intent intent = new Intent();
            intent.setClassName(this.i.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
            IntentParams build = IntentParams.builder().addBiFrom("Today_0001", "healthTrends".equals(this.w.getQueryParameter("from")) ? "healthTrend_entry" : "", "").addPageId(ArkUIXConstants.HEART_RATE).addPageType("0").build();
            LogUtil.a("HwSchemeBasicHealthActivity", "params.toString()", build.toString());
            intent.putExtra(e.n, build.toString());
            gnm.aPC_(this.i, intent);
        } else {
            Intent intent2 = new Intent();
            intent2.setClass(this, HeartRateDetailActivity.class);
            startActivity(intent2);
        }
        finish();
    }

    private void a(int i) {
        if ("hearthealth".equals(this.w.getQueryParameter("from"))) {
            String value = AnalyticsValue.HEALTH_BACK_FROM_HEART_STUDY_2041083.value();
            HashMap hashMap = new HashMap();
            hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, Integer.valueOf(i));
            ixx.d().d(this.i, value, hashMap, 0);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        setIntent(null);
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        LogUtil.a("HwSchemeBasicHealthActivity", "gotoWatchFace");
        pep.g(BaseApplication.getContext());
        new oxi(this, "HwSchemeBasicHealthActivity").b(-1, true);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1003) {
            if (TextUtils.isEmpty(this.r)) {
                LogUtil.h("HwSchemeBasicHealthActivity", "mPayLoad is empty，disconnect fail");
                finish();
            } else {
                jdx.b(new Runnable() { // from class: cbx
                    @Override // java.lang.Runnable
                    public final void run() {
                        HwSchemeBasicHealthActivity.this.f();
                    }
                });
            }
        }
    }

    public /* synthetic */ void f() {
        String btMac;
        LogUtil.a("HwSchemeBasicHealthActivity", "disconnect bluetooth:", this.r);
        QrCodeOrNfcInfo analysisQrCodeOrNfc = QrCodeOrNfcInfo.analysisQrCodeOrNfc(this.r);
        if (analysisQrCodeOrNfc == null) {
            ReleaseLogUtil.d("HwSchemeBasicHealthActivity", "onActivityResult qrCodeOrNfcInfo is null");
            btMac = "";
        } else {
            btMac = analysisQrCodeOrNfc.getBtMac();
        }
        if (TextUtils.isEmpty(btMac)) {
            LogUtil.h("HwSchemeBasicHealthActivity", "mac is empty， disconnect fail");
        } else {
            LogUtil.h("HwSchemeBasicHealthActivity", "disconnect result :" + dij.c(btMac));
        }
        finish();
    }

    private String ba() {
        try {
            Field declaredField = Class.forName("android.app.Activity").getDeclaredField("mReferrer");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            return obj instanceof String ? (String) obj : String.valueOf(obj);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            LogUtil.b("HwSchemeBasicHealthActivity", "Reflect GetReferrer Exception");
            return "UnKnowReferrer";
        }
    }

    private void m() {
        LogUtil.a("HwSchemeBasicHealthActivity", "doBiFromStartApp");
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", 3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
