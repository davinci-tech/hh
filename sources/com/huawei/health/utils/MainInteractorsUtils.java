package com.huawei.health.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.common.util.AccountHelper;
import com.huawei.common.util.AccountInteractors;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.exception.HafRuntimeException;
import com.huawei.haf.common.utils.AbiUtil;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.HuaweiLoginActivity;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.splash.GuideActivity;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.utils.MainInteractorsUtils;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwauthutil.utils.PackageManagerHelper;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.userprofile.GetUserMergeInfoReq;
import com.huawei.hwcloudmodel.model.userprofile.GetUserMergeInfoRsp;
import com.huawei.hwcloudmodel.model.userprofile.MergeUserAllDataReq;
import com.huawei.hwcloudmodel.model.userprofile.UserMergeInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.trade.PayApi;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.googlefit.GoogleFitDataManage;
import defpackage.bzs;
import defpackage.dss;
import defpackage.eil;
import defpackage.fei;
import defpackage.fej;
import defpackage.gmz;
import defpackage.gog;
import defpackage.goj;
import defpackage.gok;
import defpackage.gou;
import defpackage.gso;
import defpackage.ixx;
import defpackage.jaa;
import defpackage.jbs;
import defpackage.jfb;
import defpackage.jfu;
import defpackage.jpt;
import defpackage.knx;
import defpackage.lqi;
import defpackage.mcv;
import defpackage.mex;
import defpackage.mkx;
import defpackage.mle;
import defpackage.oaj;
import defpackage.rvo;
import defpackage.rvw;
import defpackage.shu;
import health.compact.a.CloudImpl;
import health.compact.a.CommonUtil;
import health.compact.a.KeyManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class MainInteractorsUtils {
    public static int c = 1;
    private static int d = a();
    public static int e = 2;
    private Context b;
    private CustomTextAlertDialog g;
    private Activity j;
    private int i = -1;

    /* renamed from: a, reason: collision with root package name */
    private long f3489a = 0;

    /* loaded from: classes8.dex */
    public interface UpdateType {
        public static final int GOOGLE_PLAY = 1;
        public static final int GOOGLE_PLAY_WAP = 2;
    }

    public void j(Context context) {
        this.b = context;
        this.j = (Activity) context;
    }

    public static void c(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MainInteractorsUtils.b(context);
            }
        });
    }

    static /* synthetic */ void b(Context context) {
        if (jaa.e(context)) {
            if (jaa.b(context)) {
                LogUtil.a("Login_MainInteractorsUtils", "updateGrsConfig deleteGrsConfig");
                jaa.c(context);
            }
            jaa.e(context, 2);
        }
    }

    public static String e(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c2 = charArray[i];
            if (c2 != 65292 && c2 != 12290) {
                if (c2 == 12288) {
                    charArray[i] = ' ';
                } else if (c2 > 65280 && c2 < 65375) {
                    charArray[i] = (char) (c2 - 65248);
                }
            }
        }
        return new String(charArray);
    }

    public static void d(Context context, String str, long j) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferenceManager.e(context, Integer.toString(10000), str, String.valueOf(j), new StorageParams(0));
    }

    private static double e(Map<String, Double> map, String str) {
        Double d2;
        if (map == null || TextUtils.isEmpty(str) || (d2 = map.get(str)) == null) {
            return 0.0d;
        }
        return d2.doubleValue();
    }

    public void a(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, String str5) {
        HashMap<String, Double> t = CommonUtil.t(this.b);
        double e2 = e(t, str);
        double e3 = e(t, str2);
        double e4 = e(t, str3);
        double e5 = e(t, str4);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (e2 > i) {
            linkedHashMap.put(OpAnalyticsConstants.WIFI_TRAFFIC_RX, CommonUtil.b(e2, str5));
            d(this.b, str, i + i);
        }
        if (e3 > i2) {
            linkedHashMap.put(OpAnalyticsConstants.WIFI_TRAFFIC_TX, CommonUtil.b(e3, str5));
            d(this.b, str2, i2 + i2);
        }
        if (e4 > i4) {
            linkedHashMap.put(OpAnalyticsConstants.MOBILE_TRAFFIC_RX, CommonUtil.b(e4, str5));
            d(this.b, str3, i4 + i4);
        }
        if (e5 > i3) {
            linkedHashMap.put(OpAnalyticsConstants.MOBILE_TRAFFIC_TX, CommonUtil.b(e5, str5));
            d(this.b, str4, i3 + i3);
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_TRAFFIC_85010001.value(), linkedHashMap);
    }

    public static class b implements HiDataOperateListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MainInteractorsUtils> f3499a;
        private WeakReference<Context> b;
        private WeakReference<Handler> c;
        private String d;

        b(MainInteractorsUtils mainInteractorsUtils, Handler handler, String str, Context context) {
            this.f3499a = new WeakReference<>(mainInteractorsUtils);
            this.d = str;
            this.b = new WeakReference<>(context);
            this.c = new WeakReference<>(handler);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            WeakReference<MainInteractorsUtils> weakReference = this.f3499a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyAToBByHiDATA onResult errorCode = ", Integer.valueOf(i));
            b(AccountHelper.getOldHid(), this.d, i);
        }

        private void b(String str, String str2, int i) {
            Handler handler = this.c.get();
            if (handler == null) {
                return;
            }
            if (i == 0 || i == 2) {
                jfb.e().d(str, str2);
                Context context = this.b.get();
                if (context != null && "is_auth_failed_migrate_true".equals(SharedPreferenceManager.b(context, Integer.toString(10015), "is_auth_failed_migrate"))) {
                    handler.sendEmptyMessage(4009);
                    SharedPreferenceManager.e(context, Integer.toString(10015), "is_auth_failed_migrate", "is_auth_failed_migrate_false", new StorageParams(0));
                    return;
                }
                return;
            }
            if (i == 3) {
                jfb.e().d(str, str2);
                if (!"0".equals(str) && !"com.huawei.health".equals(str) && !TextUtils.isEmpty(str) && !str.equals(str2)) {
                    handler.sendEmptyMessage(4013);
                }
                Bundle bundle = new Bundle();
                bundle.putLong("error_code", 3L);
                LogUtil.bRh_(907127009, "Login_MainInteractorsUtils", bundle, false, "notice local migrate account data but return RESULT_NO_DETAIL_DATA message." + bundle);
                return;
            }
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: showDataMigrateDialog copyHealthDatasFromAtoB down and failed");
            Bundle bundle2 = new Bundle();
            bundle2.putLong("error_code", i);
            LogUtil.bRh_(907127009, "Login_MainInteractorsUtils", bundle2, false, "notice local migrate account data but return error message." + bundle2);
        }
    }

    public void aQK_(Handler handler, String str, String str2, boolean z) {
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyAToBByHiDATA enter oldHuid is ", str, " newHuid is ", str2, " isSync is ", Boolean.valueOf(z));
        HiDataUpdateOption hiDataUpdateOption = new HiDataUpdateOption();
        hiDataUpdateOption.putString("old_huid", str);
        hiDataUpdateOption.putString("new_huid", str2);
        hiDataUpdateOption.putBoolean("copy_sync_status", z);
        HiHealthManager.d(this.b).updateHiHealthData(hiDataUpdateOption, new b(this, handler, str2, this.b));
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyAToBByHiDATA end");
    }

    public void aQV_(SpannableString spannableString) {
        View inflate = View.inflate(this.b, R.layout.smart_gender_tips_dialog_view, null);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_smartmsg_gender_tips_content)).setText(spannableString);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.b);
        builder.a(this.b.getString(R.string._2130837692_res_0x7f0200bc)).czg_(inflate).czf_(this.b.getString(R.string._2130841425_res_0x7f020f51).toUpperCase(), new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(MainInteractorsUtils.this.b);
                } catch (Exception unused) {
                    LogUtil.a("Login_MainInteractorsUtils", "open systemmanager failed");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czd_(this.b.getString(R.string._2130845098_res_0x7f021daa).toUpperCase(), new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    public SpannableString aQP_(String str, String str2) {
        SpannableString spannableString = new SpannableString(e(str));
        int indexOf = spannableString.toString().indexOf(str2);
        if (indexOf != -1) {
            spannableString.setSpan(new ForegroundColorSpan(this.b.getResources().getColor(R.color._2131297045_res_0x7f090315)), indexOf, str2.length() + indexOf, 33);
        }
        return spannableString;
    }

    public void c(String str, String str2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Login_MainInteractorsUtils", "Enter showHmsDialog ");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.b);
        builder.b(str).e(str2).cyV_(this.j.getString(R.string._2130841131_res_0x7f020e2b), new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                iBaseResponseCallback.d(1, "");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(this.j.getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractorsUtils", "cancel click");
                iBaseResponseCallback.d(2, "");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public int i() {
        int i = this.i;
        if (i == 1) {
            return 2002;
        }
        return i == 2 ? 2003 : 2001;
    }

    public static boolean e(Context context) {
        int a2 = new PackageManagerHelper(context).a(CommonUtil.p());
        boolean z = a2 > 0;
        LogUtil.b("Login_MainInteractorsUtils", "versionCode:" + a2);
        return z;
    }

    public void l() {
        LogUtil.a("Login_MainInteractorsUtils", "Enter gotoGooglePlayForUadate");
        this.i = 1;
        Activity activity = this.j;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.huawei.hwid"));
            intent.setPackage("com.android.vending");
            activity.startActivityForResult(intent, i());
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Login_MainInteractorsUtils", "can not open google play");
        }
    }

    public static int a() {
        ArrayList arrayList = new ArrayList(0);
        arrayList.add("arm64-v8a");
        arrayList.add("armeabi-v7a");
        try {
            return "arm64-v8a".equalsIgnoreCase(AbiUtil.b(arrayList)) ? 1 : 0;
        } catch (HafRuntimeException unused) {
            LogUtil.h("Login_MainInteractorsUtils", "findBasePrimaryAbi failed");
            return 0;
        }
    }

    public void f() {
        if (!Utils.o() || Utils.i()) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: checkAccountSync enter");
            String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1011);
            ixx.d().a(accountInfo);
            ixx.d().e(LoginInit.getInstance(this.b).getAccountInfo(1010));
            LogUtil.c("Login_MainInteractorsUtils", "accountmigrate checkAccountSync userId:", accountInfo);
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            ixx.d().d(this.b, AnalyticsValue.HEALTH_LOGIN_APP_2050001.value(), hashMap, 0);
            hashMap.put("arch64", String.valueOf(d));
            hashMap.put("sdk_ver", Build.VERSION.RELEASE);
            hashMap.put("sdk_int", String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("dev_manufacturer", Build.MANUFACTURER);
            hashMap.put("dev_board", Build.BOARD);
            hashMap.put("dev_model", Build.MODEL);
            ixx.d().d(this.b, AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: checkAccountSync end");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        AccountHelper.setDownloadHmsTryCnt(this.b, i);
    }

    public int h() {
        return AccountHelper.getDownloadHmsTryCnt(this.b);
    }

    public static void c() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }

    public void v() {
        String string;
        String string2;
        LogUtil.c("Login_MainInteractorsUtils", "showAlertDialog");
        if (e(this.j)) {
            string = this.j.getResources().getString(R.string._2130842078_res_0x7f0211de);
            string2 = this.j.getResources().getString(R.string._2130842080_res_0x7f0211e0);
        } else {
            string = this.j.getResources().getString(R.string.IDS_service_area_notice_title);
            string2 = this.j.getResources().getString(R.string._2130842079_res_0x7f0211df);
        }
        c(string, string2, new IBaseResponseCallback() { // from class: com.huawei.health.utils.MainInteractorsUtils.21
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (1 == i) {
                    int h = MainInteractorsUtils.this.h();
                    if (CommonUtil.aa(MainInteractorsUtils.this.b)) {
                        MainInteractorsUtils.this.c(h + 1);
                    }
                    MainInteractorsUtils.this.l();
                    return;
                }
                LogUtil.h("Login_MainActivity", "finish MainAcitivity for cause:", "cancel update hms by google");
                MainInteractorsUtils.c();
            }
        });
    }

    public void x() {
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(10000), "start_huawei_health_current_time");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.a("Login_MainInteractorsUtils", " setFirstStartTime setSharedPreference result is ", Integer.valueOf(SharedPreferenceManager.e(this.b, Integer.toString(10000), "start_huawei_health_current_time", String.valueOf(System.currentTimeMillis()), (StorageParams) null)));
        } else {
            LogUtil.a("Login_MainInteractorsUtils", " setFirstStartTime getSharedPreference startTime is ", b2);
        }
    }

    public void e(String str, boolean z) {
        SharedPreferenceManager.e(this.b, Integer.toString(10015), str, z ? "1" : "0", new StorageParams(0));
    }

    public void e() {
        AccountHelper.setIsFirstOpenApp(true);
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(this.b, AnalyticsValue.HEALTH_GUIDE_PAGE_2070001.value(), hashMap, 0);
        StorageParams storageParams = new StorageParams();
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10005), "health_guide_page", "health_app_first_start", storageParams);
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10005), "is_over_rride", "0", storageParams);
    }

    public void r() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.22
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferenceUtil.getInstance(MainInteractorsUtils.this.b).loadAllHealthData();
                gok.b().a();
                DfxBaseHandler.deleteOtherAllDfxLogFile(new File(CommonUtil.n(MainInteractorsUtils.this.b)));
            }
        });
    }

    public void g() {
        LogUtil.a("Login_MainInteractorsUtils", "enter gotoAccountAuth.");
        Activity activity = this.j;
        if (activity == null) {
            LogUtil.h("Login_MainInteractorsUtils", "gotoAccountAuth() context is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.23
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent(MainInteractorsUtils.this.b, (Class<?>) HuaweiLoginActivity.class);
                    intent.putExtra("requestCode", SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON);
                    intent.putExtra(CommonConstant.REALNAMERESULT.KEY_VERIFY_TYPE, 1);
                    MainInteractorsUtils.this.j.startActivityForResult(intent, AuthCode.StatusCode.CERT_FINGERPRINT_ERROR);
                }
            });
        }
    }

    public boolean a(boolean z, int i) {
        if (!z) {
            LogUtil.a("Login_MainInteractorsUtils", "shouldShowGuide ", "SUPPORT_GUIDE ", Boolean.valueOf(z));
            return false;
        }
        if (!LanguageUtil.m(this.b) || Utils.g()) {
            LogUtil.a("Login_MainInteractorsUtils", "shouldShowGuide ", "language ", Boolean.valueOf(LanguageUtil.m(this.b)), "version ", Boolean.valueOf(Utils.g()));
            return false;
        }
        LogUtil.a("Login_MainInteractorsUtils", "versionCode = ", String.valueOf(i), "versionCodeSp = ", SharedPreferenceManager.b(this.b, Integer.toString(10005), "version_code_sp"));
        return !r6.equals(r7);
    }

    public void b(int i) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10005), "version_code_sp", String.valueOf(i), new StorageParams());
        Intent intent = new Intent();
        intent.setClass(this.j, GuideActivity.class);
        this.j.startActivityForResult(intent, 101);
    }

    public void aQQ_(final Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "getUserInfo getUser");
        LoginInit.getInstance(BaseApplication.getContext()).getUserInfo(new IBaseResponseCallback() { // from class: gom
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                MainInteractorsUtils.this.aQT_(handler, i, obj);
            }
        });
    }

    public /* synthetic */ void aQT_(Handler handler, int i, Object obj) {
        if (i == 0 && (obj instanceof HiUserInfo)) {
            handler.removeMessages(6001);
            handler.sendEmptyMessage(6001);
            b(true);
            return;
        }
        b(false);
    }

    private void b(boolean z) {
        bzs.e().functionEntrance("Login_MainInteractorsUtils", z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void u() {
        char c2;
        LogUtil.c("Login_MainInteractorsUtils", "MainActivit_showGenderDialog");
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(10000), "hw_health_gender_value");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005);
        LogUtil.c("Login_MainInteractorsUtils", "MainActivit_showGenderDialog_showgender", b2, "+ newGenderValue = ", accountInfo);
        String b3 = SharedPreferenceManager.b(this.b, Integer.toString(10000), "hw_health_huid_value");
        String accountInfo2 = LoginInit.getInstance(this.b).getAccountInfo(1011);
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(b3) || !b3.equals(accountInfo2)) {
            LogUtil.a("Login_MainInteractorsUtils", "genderValue is null");
            SharedPreferenceManager.e(this.b, Integer.toString(10000), "hw_health_gender_value", accountInfo, new StorageParams(1));
            SharedPreferenceManager.e(this.b, Integer.toString(10000), "hw_health_huid_value", accountInfo2, new StorageParams(1));
            return;
        }
        if (TextUtils.isEmpty(accountInfo) || b2.equals(accountInfo)) {
            return;
        }
        accountInfo.hashCode();
        switch (accountInfo.hashCode()) {
            case 48:
                if (accountInfo.equals("0")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 49:
                if (accountInfo.equals("1")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 50:
                if (accountInfo.equals("2")) {
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
            String lowerCase = this.b.getString(R.string._2130842091_res_0x7f0211eb).toLowerCase();
            aQV_(aQP_(this.b.getString(R.string._2130842090_res_0x7f0211ea, lowerCase), lowerCase));
        } else if (c2 == 1) {
            String lowerCase2 = this.b.getString(R.string._2130842092_res_0x7f0211ec).toLowerCase();
            aQV_(aQP_(this.b.getString(R.string._2130842090_res_0x7f0211ea, lowerCase2), lowerCase2));
        } else if (c2 == 2) {
            aQV_(new SpannableString(e(this.b.getString(R.string._2130837712_res_0x7f0200d0))));
        }
        SharedPreferenceManager.e(this.b, Integer.toString(10000), "hw_health_gender_value", accountInfo, new StorageParams(1));
    }

    public void b(ExecutorService executorService) {
        if (executorService != null) {
            Context context = this.b;
            if (context == null) {
                LogUtil.b("Login_MainInteractorsUtils", "pullAchievementAdapter mContext = null");
            } else {
                final boolean k = mcv.d(context).k();
                executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.25
                    @Override // java.lang.Runnable
                    public void run() {
                        mcv.d(MainInteractorsUtils.this.b).c(k);
                        mcv.d(MainInteractorsUtils.this.b).i();
                        mcv.d(MainInteractorsUtils.this.b).c();
                        long h = CommonUtil.h(SharedPreferenceManager.b(MainInteractorsUtils.this.b, Integer.toString(10000), "hw_health_start_count_key"));
                        LogUtil.a("Login_MainInteractorsUtils", "initLoginedData startCount = ", Long.valueOf(h));
                        if (h == 1) {
                            mex.b(MainInteractorsUtils.this.b).b(mkx.d(-4, System.currentTimeMillis(), 1), System.currentTimeMillis(), 11, null);
                            LogUtil.a("Login_MainInteractorsUtils", "pullAchievement generateReportData！");
                        }
                    }
                });
            }
        }
    }

    public boolean p() {
        rvo e2 = rvo.e(this.b);
        return "true".equals(e2.a(2)) && "true".equals(e2.a(3)) && "true".equals(e2.a(6));
    }

    public void aQW_(Handler handler) {
        gmz d2 = gmz.d();
        String c2 = d2.c(3);
        LogUtil.a("Login_MainInteractorsUtils", "shouldSetData sportData ", c2);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.a("Login_MainInteractorsUtils", "PRIVACY_SPORT_DATA  is null");
            if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10005), "is_over_rride"))) {
                LogUtil.a("Login_MainInteractorsUtils", "isOverride and sport data is Empty");
                d2.c(3, true, (String) null, (IBaseResponseCallback) null);
                d2.c(6, true, (String) null, (IBaseResponseCallback) null);
                d2.c(7, true, (String) null, (IBaseResponseCallback) null);
                return;
            }
            LogUtil.a("Login_MainInteractorsUtils", "new install data is empty");
            handler.sendEmptyMessage(MainLoginCallBack.MSG_NO_NETWORK);
            return;
        }
        handler.sendEmptyMessage(MainLoginCallBack.MSG_NO_NETWORK);
    }

    public boolean i(Context context) {
        return CommonUtil.aa(context);
    }

    public void aQF_(ExecutorService executorService, final Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: checkIsReceivedCloudPush() getUserMergeInfo call back");
        if (AccountInteractors.isReceivedCloudPush(this.b) && i(this.b) && !Utils.g()) {
            if (executorService == null || executorService.isShutdown()) {
                LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: checkIsReceivedCloudPush() mExecutorService is shutdown");
                return;
            } else {
                executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.24
                    @Override // java.lang.Runnable
                    public void run() {
                        jbs.a(MainInteractorsUtils.this.b).c(new GetUserMergeInfoReq(), new ICloudOperationResult<GetUserMergeInfoRsp>() { // from class: com.huawei.health.utils.MainInteractorsUtils.24.4
                            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                            /* renamed from: d, reason: merged with bridge method [inline-methods] */
                            public void operationResult(GetUserMergeInfoRsp getUserMergeInfoRsp, String str, boolean z) {
                                LogUtil.a("Login_MainInteractorsUtils", "accountmigrate:checkIsReceivedCloudPush() getUserMergeInfo call back");
                                if (getUserMergeInfoRsp != null) {
                                    LogUtil.c("Login_MainInteractorsUtils", "accountmigrate: checkIsReceivedCloudPush() getUserMergeInfo " + getUserMergeInfoRsp.toString() + " text = " + str + "isSuccess = " + z);
                                    AccountInteractors.clearCloudPushRecevieTag(MainInteractorsUtils.this.b);
                                    List<UserMergeInfo> userMergeInfos = getUserMergeInfoRsp.getUserMergeInfos();
                                    for (int i = 0; i < userMergeInfos.size(); i++) {
                                        String originalHuid = userMergeInfos.get(i).getOriginalHuid();
                                        if (userMergeInfos.get(i).getStatus().intValue() == 1) {
                                            jfb.e().e(originalHuid, LoginInit.getInstance(MainInteractorsUtils.this.b).getAccountInfo(1011));
                                        }
                                    }
                                    handler.sendEmptyMessage(4017);
                                    return;
                                }
                                LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: checkIsReceivedCloudPush() getUserMergeInfo == null");
                            }
                        });
                    }
                });
                return;
            }
        }
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate:checkIsReceivedCloudPush() else");
    }

    public void aQE_(ExecutorService executorService, final Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate:  checkCloudAndLocalMigrated()");
        if (executorService == null || executorService.isShutdown()) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate:  checkCloudAndLocalMigrated() is shutdown");
        } else {
            executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.4
                @Override // java.lang.Runnable
                public void run() {
                    int checkCloudAndLocalDataMigrated = AccountInteractors.checkCloudAndLocalDataMigrated(MainInteractorsUtils.this.b);
                    LogUtil.a("Login_MainInteractorsUtils", "accountmigrate:  checkCloudAndLocalMigrated() bothMigrated = ", Integer.valueOf(checkCloudAndLocalDataMigrated));
                    for (int i = 0; i < checkCloudAndLocalDataMigrated; i++) {
                        handler.sendEmptyMessage(4009);
                    }
                    AccountInteractors.clearCloudAndLocalDataMigratedTag(MainInteractorsUtils.this.b);
                }
            });
        }
    }

    public void aQN_(MergeUserAllDataReq mergeUserAllDataReq, Boolean bool, String str, Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyDataByType");
        AccountInteractors.saveMigrateInfoToDb(mergeUserAllDataReq.getOriginalHuid(), mergeUserAllDataReq.getOriginalServiceToken(), LoginInit.getInstance(this.b).getAccountInfo(1011), true);
        if (AccountHelper.getAccountType() == 1) {
            aQM_(3, str, handler);
            return;
        }
        if (bool.booleanValue()) {
            aQM_(4, str, handler);
        } else if (AccountHelper.getAccountType() == 2) {
            aQM_(2, str, handler);
        } else {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyDataByType fail");
        }
    }

    public void aQM_(int i, String str, Handler handler) {
        LogUtil.a("TimeEat_MainInteractorsUtils", "accountmigrate: Enter copyData and type = ", Integer.valueOf(i));
        String e2 = KeyValDbManager.b(this.b).e("is_database_update_success");
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData isdatabaseupdatesuccessful == " + e2);
        if (i == 2) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData type == 2");
            str = AccountHelper.getHiAccountInfo().getHuid();
        } else if (i == 3) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData type == 3, need judge database whether update done");
            if ("1".equals(e2)) {
                LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData type == 3, judge database update done");
                str = SharedPreferenceManager.b(this.b, Integer.toString(10015), "old_huid_need_to_migrate");
            } else {
                LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData type == 3, judge database update not complete, next enter do again!");
                return;
            }
        } else if (i == 4) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData type == 4, last cloud timeout and exception exit");
            if (TextUtils.isEmpty(str)) {
                LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData sendMigrateOrigalHuid is empty");
                return;
            }
        } else {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData not type");
            str = null;
        }
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData  isdatabaseupdatesuccessful == ", e2, "and getDatabasePath(DataBaseHelper.DATABASE_NAME).exists() is", Boolean.valueOf(BaseApplication.getContext().getDatabasePath("health_cloud.db").exists()));
        aQL_(str, handler);
        LogUtil.a("TimeEat_MainInteractorsUtils", "Leave copyData");
    }

    public void aQL_(String str, Handler handler) {
        LogUtil.a("TimeEat_MainInteractorsUtils", "accountmigrate: copyByHuid = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyByHuid old is Empty");
            return;
        }
        String e2 = KeyValDbManager.b(this.b).e("is_database_update_success");
        KeyValDbManager.b(this.b).e("is_database_update_success", "0");
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData  isdatabaseupdatesuccessful == ", e2, "and getDatabasePath(DataBaseHelper.DATABASE_NAME).exists() is", Boolean.valueOf(BaseApplication.getContext().getDatabasePath("health_cloud.db").exists()));
        if (!"1".equals(e2) && BaseApplication.getContext().getDatabasePath("health_cloud.db").exists()) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData  accountType == 1 and 1 is databaseupdatesuccessful");
            return;
        }
        String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1011);
        try {
        } catch (Exception unused) {
            LogUtil.b("Login_MainInteractorsUtils", "accountmigrate: copyData copyHealthDatasFromAtoB copyByHuid Exception");
        }
        if (!"0".equals(str) && !"com.huawei.health".equals(str)) {
            aQK_(handler, str, accountInfo, false);
            LogUtil.a("TimeEat_MainInteractorsUtils", "Leave copyByHuid");
        }
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: copyData old = defualt");
        aQK_(handler, "com.huawei.health", accountInfo, false);
        LogUtil.a("TimeEat_MainInteractorsUtils", "Leave copyByHuid");
    }

    private void ab() {
        if (!CommonUtil.aa(this.b)) {
            LogUtil.a("Login_MainInteractorsUtils", "doSyncGoogleFit network is not connected!");
            return;
        }
        boolean c2 = shu.b().c();
        LogUtil.a("Login_MainInteractorsUtils", "doSyncGoogleFit.isConnect = " + c2);
        if (c2) {
            new GoogleFitDataManage(this.b).b();
            LogUtil.a("Login_MainInteractorsUtils", "doSyncGoogleFit end");
        }
    }

    private void ac() {
        LogUtil.c("Login_MainInteractorsUtils", "doSyncThirdPartService start.");
        if (CommonUtil.bx()) {
            ab();
        }
    }

    public void aQZ_(Handler handler, Intent intent) {
        LogUtil.c("Login_MainInteractorsUtils", "SyncBroadcastReceiver action = ACTION_SYNC");
        handler.sendMessage(handler.obtainMessage(3, 0, 0, intent));
        int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", -1);
        LogUtil.c("Login_MainInteractorsUtils", "HiBroadcastAction =" + intExtra);
        if (1000 == intExtra) {
            ac();
        }
    }

    public void aQY_(String str, Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: SyncBroadcastReceiver action = ACTION_SQLITE_UPGRADE_DONE oldhuidhistory = ", Integer.valueOf(AccountHelper.getOldHidHistory()));
        if (AccountHelper.getOldHidHistory() != 1) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: SyncBroadcastReceiver action = ACTION_SQLITE_UPGRADE_DONE oldhuidhistory not 1");
            aQM_(1, str, handler);
        }
    }

    public HiAccountInfo y() {
        HiAccountInfo hiAccountInfo = new HiAccountInfo();
        LoginInit loginInit = LoginInit.getInstance(this.b);
        String accountInfo = loginInit.getAccountInfo(1015);
        hiAccountInfo.setAccessToken(accountInfo);
        hiAccountInfo.setHuid(loginInit.getAccountInfo(1011));
        hiAccountInfo.setServiceToken(accountInfo);
        hiAccountInfo.setSiteId(CommonUtil.m(this.b, loginInit.getAccountInfo(1009)));
        return hiAccountInfo;
    }

    public void aRa_(Handler handler) {
        HuaweiLoginManager.setIsAllowedLoginValueToDB(this.b, "0");
        String a2 = KeyManager.a();
        String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1011);
        LogUtil.a("Login_MainInteractorsUtils", "doInitPluginLoginHiHealth isClone=", Boolean.valueOf(KeyManager.h()));
        if (KeyManager.h()) {
            if (TextUtils.isEmpty(a2) || a2.equals(accountInfo)) {
                handler.sendEmptyMessage(10089);
            } else {
                KeyManager.d();
            }
        }
    }

    public void o() {
        String c2 = gmz.d().c(403);
        LogUtil.a("Login_MainInteractorsUtils", "initKakaCheckInReminder status = ", c2);
        if (String.valueOf(true).equals(c2)) {
            mle.c();
        } else {
            mle.a();
        }
    }

    public void aQU_(Context context, Intent intent) {
        HwNpsManager.getInstance().setSharedPreference(HwNpsManager.NPS_CLOUD_CONFIG, "");
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.f3489a) < 5000) {
            LogUtil.a("Login_MainInteractorsUtils", "currentTime - mLastAccessTime  < INTERVAL_TIME_VALUE");
            return;
        }
        this.f3489a = currentTimeMillis;
        boolean booleanExtra = intent.getBooleanExtra("logoutNotExit", false);
        AccountHelper.setLoginnotexit(booleanExtra);
        lqi.d().a();
        LogUtil.c("Login_MainInteractorsUtils", "accountmigrate: relogin SyncBroadcastReceiver action = ACTION_LOGOUT_SUCCESSFUL loginnotexit 111 invalidST = " + intent.getStringExtra("invalidst"));
        if (booleanExtra && !AccountHelper.getIsCheckLogining()) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: relogin SyncBroadcastReceiver action = ACTION_LOGOUT_SUCCESSFUL loginnotexit");
            return;
        }
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: relogin: SyncBroadcastReceiver action = ACTION_LOGOUT_SUCCESSFUL loginnotexit = false");
        SharedPreferenceManager.e(this.b, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN, "0", new StorageParams(0));
    }

    public void aQR_(Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "judgeAccountOverAge");
        String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1010);
        if (!"true".equalsIgnoreCase(SharedPreferenceManager.b(this.b, Integer.toString(20000), "key_ui_age_less_minimum")) || TextUtils.isEmpty(accountInfo) || "CN".equalsIgnoreCase(accountInfo)) {
            return;
        }
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(20000), "key_ui_if_show_age_less_minimum_alert");
        if (TextUtils.isEmpty(b2) || "false".equalsIgnoreCase(b2)) {
            LogUtil.a("Login_MainInteractorsUtils", "judgeAccountOverAge show alert");
            handler.sendEmptyMessage(4016);
            SharedPreferenceManager.e(this.b, Integer.toString(20000), "key_ui_if_show_age_less_minimum_alert", String.valueOf(true), new StorageParams());
        }
    }

    public void aQS_(Handler handler) {
        String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo) || CloudImpl.c(this.b).isOverseaJudgeByCountry(accountInfo)) {
            return;
        }
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(20000), "key_ui_if_show_no_cloud_account_alert");
        if (TextUtils.isEmpty(b2) || "false".equalsIgnoreCase(b2)) {
            handler.sendEmptyMessage(4015);
            SharedPreferenceManager.e(this.b, Integer.toString(20000), "key_ui_if_show_no_cloud_account_alert", String.valueOf(true), new StorageParams());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.g.dismiss();
        this.g = null;
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "oversea_user_agreed", str, new StorageParams(0));
    }

    public void b(final int i, ExecutorService executorService) {
        if (executorService != null) {
            if (executorService.isShutdown()) {
                executorService = Executors.newSingleThreadExecutor();
            }
            executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.5
                @Override // java.lang.Runnable
                public void run() {
                    ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByDeviceType(jfu.h(i) ? "SMART_BAND" : "SMART_WATCH");
                }
            });
        }
    }

    public void e(final int i, final ExecutorService executorService) {
        LogUtil.a("Login_MainInteractorsUtils", "enter showOverSeaDialog");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.b);
        builder.b(R.string.IDS_service_area_notice_title).d(R.string.IDS_device_download_resoure_tip_content_message_new).cyU_(R.string._2130842122_res_0x7f02120a, new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainInteractorsUtils.this.a("true");
                MainInteractorsUtils.this.b(i, executorService);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainInteractorsUtils.this.a("false");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.g = a2;
        a2.setCancelable(false);
        this.g.show();
        LogUtil.a("Login_MainInteractorsUtils", "end showOverSeaDialog");
    }

    public void aQO_(int i, ExecutorService executorService, Handler handler) {
        if (!Utils.o()) {
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByDeviceType(jfu.h(i) ? "SMART_BAND" : "SMART_WATCH");
            return;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "oversea_user_agreed");
        LogUtil.a("Login_MainInteractorsUtils", "wear device plugin download is user agreed :" + b2);
        if (TextUtils.equals(b2, "true")) {
            b(i, executorService);
        } else if (handler != null) {
            Message obtainMessage = handler.obtainMessage(4014);
            obtainMessage.arg1 = i;
            handler.sendMessage(obtainMessage);
        }
    }

    public void aQI_(final ExecutorService executorService, final Handler handler) {
        if (!CommonUtil.ce() || executorService == null || executorService.isShutdown()) {
            return;
        }
        executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.9
            @Override // java.lang.Runnable
            public void run() {
                DeviceInfo a2 = jpt.a("Login_MainInteractorsUtils");
                if (a2 != null && jfu.m(a2.getProductType())) {
                    int productType = a2.getProductType();
                    LogUtil.a("Login_MainInteractorsUtils", "current device support download type:", Integer.valueOf(productType));
                    if (Boolean.valueOf(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(jfu.j(productType))).booleanValue()) {
                        LogUtil.a("Login_MainInteractorsUtils", "current device has downloaded!!!");
                        return;
                    } else {
                        LogUtil.a("Login_MainInteractorsUtils", "current device has not downloaded!!!");
                        MainInteractorsUtils.this.aQO_(productType, executorService, handler);
                        return;
                    }
                }
                LogUtil.h("Login_MainInteractorsUtils", "current device is null or not support download!");
            }
        });
    }

    public void a(ExecutorService executorService) {
        if (Utils.l() || executorService == null || executorService.isShutdown()) {
            return;
        }
        executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.10
            @Override // java.lang.Runnable
            public void run() {
                oaj.a().d();
            }
        });
    }

    public void aQJ_(ExecutorService executorService, final ContentValues contentValues) {
        if (Utils.l() || executorService == null || executorService.isShutdown()) {
            return;
        }
        executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.8
            @Override // java.lang.Runnable
            public void run() {
                oaj.a().cTE_(contentValues);
            }
        });
    }

    public void b(VersionMgrApi versionMgrApi) {
        LogUtil.a("Login_MainInteractorsUtils", "autoCheckAppNewVersionService()");
        if (versionMgrApi != null) {
            versionMgrApi.autoCheckAppNewVersionService();
            if (versionMgrApi.haveNewAppVersion(this.b)) {
                return;
            }
            versionMgrApi.deleteMessage();
        }
    }

    public void aQH_(Handler handler, ExecutorService executorService, VersionMgrApi versionMgrApi) {
        if (com.huawei.haf.application.BaseApplication.j()) {
            LogUtil.a("Login_MainInteractorsUtils", "autoCheckAppNewVersionService_isForeground_stop");
            b(versionMgrApi);
            AppBundle.e().updateModules(this.b, false);
            aQI_(executorService, handler);
            a(executorService);
            gog.b();
        } else {
            LogUtil.a("Login_MainInteractorsUtils", "autoCheckAppNewVersionService_isForeground_fail");
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", "wifi_push_regist_key");
        if (b2 != null && "1".equals(b2)) {
            gou.c(2, HiSyncType.HiSyncDataType.c, 2);
        }
        LogUtil.a("Login_MainInteractorsUtils", "Trigger one sync cloud in mainActivity， syncingFlag=", b2);
    }

    public void d(ExecutorService executorService) {
        if (executorService == null) {
            executorService = Executors.newSingleThreadExecutor();
        }
        executorService.execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.7
            private int c = 100;
            private int d = 30;
            private int e = 50;
            private int b = 20;

            @Override // java.lang.Runnable
            public void run() {
                String b2 = SharedPreferenceManager.b(MainInteractorsUtils.this.b, Integer.toString(10000), "traffic_time");
                long currentTimeMillis = System.currentTimeMillis();
                if (CommonUtil.e(b2, currentTimeMillis)) {
                    MainInteractorsUtils.d(MainInteractorsUtils.this.b, "traffic_time", currentTimeMillis);
                    MainInteractorsUtils.d(MainInteractorsUtils.this.b, "wifi_rx", 100L);
                    MainInteractorsUtils.d(MainInteractorsUtils.this.b, "wifi_tx", 30L);
                    MainInteractorsUtils.d(MainInteractorsUtils.this.b, "mobile_rx", 50L);
                    MainInteractorsUtils.d(MainInteractorsUtils.this.b, "mobile_tx", 20L);
                } else {
                    this.c = CommonUtil.e(SharedPreferenceManager.b(MainInteractorsUtils.this.b, Integer.toString(10000), "wifi_rx"), this.c);
                    this.d = CommonUtil.e(SharedPreferenceManager.b(MainInteractorsUtils.this.b, Integer.toString(10000), "wifi_tx"), this.d);
                    this.e = CommonUtil.e(SharedPreferenceManager.b(MainInteractorsUtils.this.b, Integer.toString(10000), "mobile_rx"), this.e);
                    this.b = CommonUtil.e(SharedPreferenceManager.b(MainInteractorsUtils.this.b, Integer.toString(10000), "mobile_tx"), this.b);
                }
                try {
                    MainInteractorsUtils.this.a("wifi_rx", "wifi_tx", "mobile_rx", "mobile_tx", this.c, this.d, this.b, this.e, "0.00");
                } catch (Throwable th) {
                    LogUtil.b("Login_MainInteractorsUtils", "Time error Throwable = " + th.getMessage());
                }
            }
        });
    }

    public void f(final Context context) {
        LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: showRootedDialog");
        if (!TextUtils.isEmpty(SharedPreferenceManager.b(context, Integer.toString(10015), "key_ui_login_is_rooted"))) {
            LogUtil.a("Login_MainInteractorsUtils", "accountmigrate: showRootedDialog has showed");
            return;
        }
        String upperCase = context.getString(R.string._2130841794_res_0x7f0210c2).toUpperCase();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(context.getString(R.string._2130837692_res_0x7f0200bc)).e(context.getString(R.string._2130842074_res_0x7f0211da)).cyV_(upperCase, new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e(context, Integer.toString(10015), "key_ui_login_is_rooted", "key_ui_login_is_rooted_clicked", new StorageParams(0));
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public void d(String str, String str2, String str3, final int i) {
        LogUtil.a("Login_MainInteractorsUtils", "showSingleButtonDialog enter", " title = ", str, " content = ", str2, " buttonText = ", str3, " type = ", Integer.valueOf(i));
        Context activity = BaseApplication.getActivity() != null ? BaseApplication.getActivity() : this.b;
        if (activity == null) {
            ReleaseLogUtil.a("Login_MainInteractorsUtils", "topActivity and context are null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(str).e(str2).cyV_(str3, new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractorsUtils", "showSingleButtonDialog ok");
                if (i == 1) {
                    LogUtil.h("Login_MainActivity", "finish MainAcitivity for cause:", "no network,click I know, quit.");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public void aQG_(final Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "enter checkLoginByDeeplink");
        if (Boolean.parseBoolean(SharedPreferenceManager.b(this.b, String.valueOf(20000), "needLogin"))) {
            if (!LoginInit.getInstance(this.b).isBrowseMode()) {
                SharedPreferenceManager.e(this.b, String.valueOf(20000), "needLogin", String.valueOf(false), (StorageParams) null);
            } else if (!"fa".equals(SharedPreferenceManager.b(this.b, String.valueOf(20000), "from"))) {
                LogUtil.a("Login_MainInteractorsUtils", "directNeedLogin goto login");
                LoginInit.getInstance(this.b).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.health.utils.MainInteractorsUtils.14
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        SharedPreferenceManager.e(MainInteractorsUtils.this.b, String.valueOf(20000), "needLogin", String.valueOf(false), (StorageParams) null);
                        LogUtil.h("Login_MainInteractorsUtils", "onResponse errorCode:", Integer.valueOf(i));
                        Handler handler2 = handler;
                        if (handler2 == null || i == -1) {
                            return;
                        }
                        handler2.sendEmptyMessage(10);
                    }
                }, "");
            } else {
                SharedPreferenceManager.e(this.b, String.valueOf(20000), "from", "", (StorageParams) null);
            }
        }
    }

    public void aQX_(final Handler handler) {
        LogUtil.a("Login_MainInteractorsUtils", "showMigrateDataAlertDialog()");
        SharedPreferenceManager.e(this.b, Integer.toString(10015), "hasRemindMigrateData", String.valueOf(true), (StorageParams) null);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.b);
        builder.e(R.string._2130841306_res_0x7f020eda).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractorsUtils", "showMigrateDataAlertDialog ok");
                SharedPreferenceManager.e(MainInteractorsUtils.this.b, Integer.toString(10015), "neverMigrateData", String.valueOf(false), (StorageParams) null);
                MainInteractorsUtils.this.aQK_(handler, "com.huawei.health", LoginInit.getInstance(MainInteractorsUtils.this.b).getAccountInfo(1011), false);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractorsUtils", "showMigrateDataAlertDialog cancle");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    public void z() {
        String str;
        String str2;
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(10015), "key_ui_login_last_time");
        String valueOf = String.valueOf(System.currentTimeMillis());
        if (TextUtils.isEmpty(b2)) {
            str2 = valueOf + "," + valueOf;
            str = valueOf;
        } else {
            String[] split = b2.split(",");
            String str3 = split.length > 1 ? split[1] : valueOf;
            str = str3;
            str2 = str3 + "," + valueOf;
        }
        SharedPreferenceManager.e(this.b, Integer.toString(10015), "key_ui_login_last_time", str2, new StorageParams());
        if (str.equals(valueOf)) {
            SharedPreferenceManager.c(Integer.toString(10015), "user_first_login_app", "first_login");
        }
        LogUtil.c("Login_MainInteractorsUtils", "updateLastLogin lastLogin = ", str2);
    }

    public void t() {
        if (Utils.i() && LoginInit.getInstance(this.b).getIsLogined() && ThirdPartyLoginManager.getInstance().getLogoutStatus() && !HuaweiLoginManager.checkIsInstallHuaweiAccount(this.b)) {
            Intent intent = new Intent(this.b, (Class<?>) goj.class);
            intent.setAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
            intent.putExtra(JsbMapKeyNames.H5_USER_ID, LoginInit.getInstance(this.b).getAccountInfo(1011));
            goj.a().aQB_(this.b, intent);
            CommonUtil.cn();
        }
    }

    public void ad() {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi != null) {
            payApi.startRetrievingOrders();
        }
    }

    public void g(Context context) {
        String country;
        if (LoginInit.getInstance(context).getIsLogined()) {
            country = LoginInit.getInstance(context).getAccountInfo(1010);
        } else {
            country = context.getResources().getConfiguration().locale.getCountry();
        }
        StorageParams storageParams = new StorageParams();
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_first_sign_country", country, storageParams);
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_first_sign_language", context.getResources().getConfiguration().locale.getLanguage(), storageParams);
    }

    static class a implements IBaseResponseCallback {
        private a() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a("Login_MainInteractorsUtils", "initAssetMessage success");
                String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
                long currentTimeMillis = System.currentTimeMillis();
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_asset_time", String.valueOf(currentTimeMillis), (StorageParams) null);
                return;
            }
            LogUtil.h("Login_MainInteractorsUtils", "initAssetMessage errorCode:", Integer.valueOf(i));
        }
    }

    public static void d() {
        LogUtil.a("Login_MainInteractorsUtils", "enter initAssetMessage");
        Context context = BaseApplication.getContext();
        if (LoginInit.getInstance(context).isBrowseMode() || Utils.o() || LoginInit.getInstance(context).isKidAccount()) {
            LogUtil.a("Login_MainInteractorsUtils", "not need initAssetMessage");
            return;
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (!CommonUtil.e(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_asset_time"), System.currentTimeMillis())) {
            LogUtil.a("Login_MainInteractorsUtils", "today has init");
            return;
        }
        final PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("Login_MainInteractorsUtils", "payApi = null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.utils.MainInteractorsUtils.20
                @Override // java.lang.Runnable
                public void run() {
                    PayApi.this.assetMsgNotify(1, new a());
                }
            });
        }
    }

    public void w() {
        LogUtil.a("Login_MainInteractorsUtils", "showHwIdStopedDialog");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.b);
        builder.b(this.b.getString(R.string._2130837692_res_0x7f0200bc)).e(this.b.getString(R.string._2130837708_res_0x7f0200cc)).cyV_(this.b.getString(R.string._2130841425_res_0x7f020f51).toUpperCase(), new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Login_MainInteractorsUtils", "showHwIdStopedDialog ok");
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.MAIN");
                    intent.setClassName("com.android.settings", "com.android.settings.ManageApplications");
                    MainInteractorsUtils.this.b.startActivity(intent);
                    LogUtil.h("Login_MainActivity", "finish MainAcitivity for cause:", "HwIdStopedDialog,goto com.android.settings");
                    MainInteractorsUtils.c();
                    ViewClickInstrumentation.clickOnView(view);
                } catch (Exception unused) {
                    LogUtil.a("Login_MainInteractorsUtils", "open systemmanager failed");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).cyS_(this.b.getString(R.string._2130839505_res_0x7f0207d1).toUpperCase(), new View.OnClickListener() { // from class: com.huawei.health.utils.MainInteractorsUtils.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.h("Login_MainActivity", "finish MainAcitivity for cause:", "showHwIdStopedDialog cancel");
                MainInteractorsUtils.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    public void aa() {
        LogUtil.a("Login_MainInteractorsUtils", "startDaemonService");
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.putExtra("THE_MAIN_UI_START_DAEMON_SERVICE", true);
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.manager.DaemonService");
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalStateException unused) {
            LogUtil.b("Login_MainInteractorsUtils", "startDaemonService illegalStateException");
        } catch (SecurityException unused2) {
            LogUtil.a("Login_MainInteractorsUtils", "startDaemonService securityException");
        }
    }

    public void j() {
        gou.c(1, 20000, 0);
    }

    public void n() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        PluginOperation pluginOperation = PluginOperation.getInstance(this.b);
        pluginOperation.setAdapter(PluginOperationAdapterImpl.getInstance(this.b));
        pluginOperation.init(this.b);
        LogUtil.a("Login_MainInteractorsUtils", "initOperationAdapter finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void s() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        PluginHealthTrackAdapterImpl pluginHealthTrackAdapterImpl = PluginHealthTrackAdapterImpl.getInstance(this.b);
        gso e2 = gso.e();
        e2.setAdapter(pluginHealthTrackAdapterImpl);
        e2.init(this.b);
        fei.c().a();
        LogUtil.a("Login_MainInteractorsUtils", "initPluginTrackApapter finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void k() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) != null) {
            fej.e().c();
            LogUtil.a("Login_MainInteractorsUtils", "initPluginSuggestinAdapter finished, time cost: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        } else {
            try {
                Services.a("PluginFitnessAdvice", PluginSuggestion.class, com.huawei.haf.application.BaseApplication.e(), new Consumer() { // from class: gon
                    @Override // com.huawei.framework.servicemgr.Consumer
                    public final void accept(Object obj) {
                        MainInteractorsUtils.this.c((PluginSuggestion) obj);
                    }
                }, true);
            } catch (IllegalStateException e2) {
                ReleaseLogUtil.c("Login_MainInteractorsUtils", "initPluginSuggestinAdapter IllegalStateException:", ExceptionUtils.d(e2));
            }
        }
    }

    public /* synthetic */ void c(PluginSuggestion pluginSuggestion) {
        if (pluginSuggestion != null) {
            pluginSuggestion.init(this.b);
        }
        k();
    }

    public void m() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        dss.c(this.b).a();
        LogUtil.a("Login_MainInteractorsUtils", "initOpenSDK finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void q() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String b2 = SharedPreferenceManager.b(this.b, Integer.toString(10000), "IS_FIRST_RULE");
        LogUtil.a("Login_MainInteractorsUtils", "isFirst = ", b2);
        if (!"0".equals(b2)) {
            rvw.d(this.b);
            SharedPreferenceManager.e(this.b, String.valueOf(10000), "IS_FIRST_RULE", "0", new StorageParams());
        }
        LogUtil.a("Login_MainInteractorsUtils", "initSmartAiRule finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public static void b(Context context, int i, int i2) {
        if (context == null) {
            LogUtil.h("Login_MainInteractorsUtils", "doBiClickFromStepNotification context is null");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("jumpTo", Integer.valueOf(i2));
        ixx.d().d(context, AnalyticsValue.HEALTH_PLUGIN_DAEMON_STEPS_NOTIFICATION_2080001.value(), hashMap, 0);
    }

    public static void d(Context context, int i, String str) {
        if (context == null) {
            LogUtil.h("Login_MainInteractorsUtils", "doBiClickFromOpenAppSource context is null");
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        ixx.d().d(context, str, hashMap, 0);
    }

    public static void b() {
        LogUtil.a("Login_MainInteractorsUtils", "doBiFromWidgetStartApp");
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("from", 6);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    public static void e(int i, long j) {
        LogUtil.a("Login_MainInteractorsUtils", "doBiFromExitApp duration = ", Long.valueOf(j));
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("duration", Integer.valueOf((int) (j / 1000)));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_EXIT_APP_2050002.value(), hashMap, 0);
    }

    public static void d(Context context) {
        LogUtil.a("Login_MainInteractorsUtils", "doBiFromLoginInApp");
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("BI_switchStatus", knx.a());
        hashMap.put("healthService_switchStatus", Boolean.valueOf(eil.e(context)));
        hashMap.put("personalContent_switchStatus", Boolean.valueOf(eil.b(context)));
        rvo e2 = rvo.e(context);
        hashMap.put("personalized_Ads", e2.a(13));
        hashMap.put("huawei_Ads", e2.a(14));
        hashMap.put("tripartite_Ads", e2.a(15));
        ixx.d().d(context, AnalyticsValue.HEALTH_LOGIN_APP_2050009.value(), hashMap, 0);
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.h("Login_MainInteractorsUtils", "doBiClickFromWiget context is null");
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(context, AnalyticsValue.HEALTH_PLUGIN_DAEMON_WIDGET_2080003.value(), hashMap, 0);
    }
}
