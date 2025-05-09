package com.huawei.pluginmessagecenter.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.webkit.ProxyConfig;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi;
import com.huawei.health.adapterwear.api.AdapterWearMgrApi;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.msgcontent.NotificationMsgContent;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPay;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.pluginachievement.AchieveNavigationApi;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.stories.about.AboutApi;
import defpackage.bzs;
import defpackage.bzu;
import defpackage.cei;
import defpackage.cun;
import defpackage.cvt;
import defpackage.dqj;
import defpackage.eme;
import defpackage.fhp;
import defpackage.gge;
import defpackage.gic;
import defpackage.gpn;
import defpackage.gpo;
import defpackage.grz;
import defpackage.ixx;
import defpackage.iyk;
import defpackage.jdw;
import defpackage.lsp;
import defpackage.mqw;
import defpackage.mra;
import defpackage.mrj;
import defpackage.mxv;
import defpackage.njn;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class DispatchSkipEventActivity extends BaseActivity {
    private static volatile Runnable b;
    private Bundle f;
    private String g;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String s;
    private static final String d = BaseApplication.getAppPackage();
    private static final Object c = new Object();
    private static Handler e = new Handler();

    /* renamed from: a, reason: collision with root package name */
    private static final List<Integer> f8490a = new ArrayList<Integer>() { // from class: com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity.3
        {
            add(10002);
            add(10003);
            add(10006);
        }
    };
    private String r = null;
    private String t = null;
    private ExecutorService i = Executors.newCachedThreadPool();
    private boolean h = false;
    private final Handler j = new Handler() { // from class: com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                DispatchSkipEventActivity.e.removeCallbacks(DispatchSkipEventActivity.b);
            }
            super.handleMessage(message);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        cnB_(intent);
        Bundle bundle2 = this.f;
        String string = bundle2 != null ? bundle2.getString(CommonUtil.DETAIL_URI) : intent.getStringExtra(CommonUtil.DETAIL_URI);
        if (TextUtils.isEmpty(this.g) || TextUtils.isEmpty(string)) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "msgId or detailUri is null");
            finish();
            return;
        }
        ThreadPoolManager.d().execute(new a(this));
        LogUtil.c("UIDV_DispatchSkipEventActivity", "onCreate detailUri" + string + "  msgId = " + this.g);
        if (string.startsWith("huaweischeme")) {
            m(string);
        } else if (string.startsWith("huaweihealth")) {
            j(string);
        } else {
            c(string);
        }
        if (this.h) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "isLogining，not need finish");
        } else {
            finish();
            overridePendingTransition(0, 0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        cnB_(intent);
    }

    /* loaded from: classes6.dex */
    static class a implements Runnable {
        private WeakReference<DispatchSkipEventActivity> d;

        a(DispatchSkipEventActivity dispatchSkipEventActivity) {
            this.d = new WeakReference<>(dispatchSkipEventActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            DispatchSkipEventActivity dispatchSkipEventActivity = this.d.get();
            if (dispatchSkipEventActivity != null && !dispatchSkipEventActivity.isFinishing() && !dispatchSkipEventActivity.isDestroyed()) {
                mqw.b(BaseApplication.getContext()).a(dispatchSkipEventActivity.g);
            } else {
                LogUtil.h("UIDV_DispatchSkipEventActivity", "activity is null or finishing or destroyed");
            }
        }
    }

    private void j(String str) {
        if (str.contains("com.huawei.health.h5.my-annual-flag")) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "isAnnualFlag jump");
            l();
        } else {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            jdw.bGh_(intent, getApplicationContext());
        }
    }

    private void l() {
        LoginInit loginInit = LoginInit.getInstance(this);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "LoginInit status", Boolean.valueOf(loginInit.getIsLogined()));
        if (!loginInit.getIsLogined() || !AuthorizationUtils.a(this) || health.compact.a.CommonUtil.af(this)) {
            c("activeTarget", "activeTarget");
        } else {
            g("activeTarget");
        }
    }

    private void cnB_(Intent intent) {
        String stringExtra;
        if (intent.getExtras() != null) {
            this.f = intent.getExtras();
        }
        Bundle bundle = this.f;
        this.g = bundle != null ? bundle.getString("msgId") : intent.getStringExtra("msgId");
        Bundle bundle2 = this.f;
        this.o = bundle2 != null ? bundle2.getString("notifiUri") : intent.getStringExtra("notifiUri");
        Bundle bundle3 = this.f;
        this.m = bundle3 != null ? bundle3.getString("messageContent") : intent.getStringExtra("messageContent");
        Bundle bundle4 = this.f;
        if (bundle4 != null) {
            stringExtra = bundle4.getString("EXTRA_BI_NAME");
        } else {
            stringExtra = intent.getStringExtra("EXTRA_BI_NAME");
        }
        this.s = stringExtra;
        Bundle bundle5 = this.f;
        this.q = bundle5 != null ? bundle5.getString("EXTRA_BI_SOURCE") : intent.getStringExtra("EXTRA_BI_SOURCE");
        Bundle bundle6 = this.f;
        this.p = bundle6 != null ? bundle6.getString("SHOW_TIME_BI") : intent.getStringExtra("SHOW_TIME_BI");
        Bundle bundle7 = this.f;
        this.n = bundle7 != null ? bundle7.getString("OPEN_STYLE") : intent.getStringExtra("OPEN_STYLE");
        Bundle bundle8 = this.f;
        this.k = bundle8 != null ? bundle8.getString(MessageConstant.MESSAGE_TITLE) : intent.getStringExtra(MessageConstant.MESSAGE_TITLE);
        Bundle bundle9 = this.f;
        this.l = bundle9 != null ? bundle9.getString("module") : intent.getStringExtra("module");
        Bundle bundle10 = this.f;
        this.r = bundle10 != null ? bundle10.getString("pushId") : intent.getStringExtra("pushId");
        Bundle bundle11 = this.f;
        this.t = bundle11 != null ? bundle11.getString("serviceId") : intent.getStringExtra("serviceId");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.j.removeCallbacksAndMessages(null);
    }

    private void m(String str) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "skipScheme");
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        } else {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "marketRouterApi = null");
        }
    }

    private void c(String str) {
        String scheme;
        String host;
        if (String.valueOf(82).equals(this.l)) {
            str = "messagecenter://ecgDetail?ecgPageType=monthReport";
        }
        Uri parse = Uri.parse(str);
        if (parse == null || (scheme = parse.getScheme()) == null || (host = parse.getHost()) == null) {
            return;
        }
        LogUtil.a("UIDV_DispatchSkipEventActivity", "handleDetailUri scheme = " + scheme);
        if (!TextUtils.isEmpty(this.o)) {
            a(scheme, host);
        }
        e(scheme, str);
        cnM_(host, parse);
        h(host);
        cnI_(parse);
        cnG_(parse);
        cnH_(parse);
        f(host);
        cnE_(parse, host);
        g(host);
        e(str);
    }

    private void g(String str) {
        if ("activeTarget".equals(str)) {
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).gotoH5ActiveTarget(this);
        }
    }

    private void cnE_(Uri uri, String str) {
        if ("nativeDevice".equals(str)) {
            String queryParameter = uri.getQueryParameter("action");
            String queryParameter2 = uri.getQueryParameter("arg1");
            String queryParameter3 = uri.getQueryParameter("arg2");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(queryParameter)) {
                intent.setAction(queryParameter);
            }
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString("arg1", queryParameter2);
            }
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString("arg2", queryParameter3);
            }
            if ("SWITCH_PLUGINDEVICE".equals(queryParameter)) {
                LogUtil.a("UIDV_DispatchSkipEventActivity", "goto device");
                intent.setPackage("com.huaei.health");
                intent.setClassName(d, "com.huawei.health.device.ui.DeviceMainActivity");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    private void e(String str, String str2) {
        if (str.equals("http") || str.equals(ProxyConfig.MATCH_HTTPS)) {
            LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
            boolean isBrowseMode = loginInit.isBrowseMode();
            OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            if (isBrowseMode && operationUtilsApi != null && operationUtilsApi.isNotSupportBrowseUrl(str2)) {
                this.h = true;
                loginInit.browsingToLogin(new c(this, str2, ProxyConfig.MATCH_HTTPS), null);
            } else {
                o(str2);
            }
            d(this.l);
        }
        "messagecenter".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(String str) {
        if (!"BROWSER".equals(this.n)) {
            bzs e2 = bzs.e();
            PluginBaseAdapter adapter = e2.getAdapter(this);
            if (!LoginInit.getInstance(this).getIsLogined()) {
                LogUtil.a("UIDV_DispatchSkipEventActivity", "not login!");
                cnP_(this, str, null);
                return;
            } else if (adapter != null) {
                e(e2, str);
                return;
            } else {
                LogUtil.a("UIDV_DispatchSkipEventActivity", "adaper not ready, jump to main.");
                cnP_(this, str, null);
                return;
            }
        }
        LogUtil.a("UIDV_DispatchSkipEventActivity", "BROWSER is not equals mOpenStyle");
    }

    private void e(bzs bzsVar, String str) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "adapter ready jump to h5");
        SharedPreferences.Editor edit = getSharedPreferences("pushNotificationIntent", 0).edit();
        edit.putBoolean(Constants.KEY_IS_PUSH_NOTIFICATION, true);
        edit.apply();
        if ("SHOW_TIME_BI".equals(this.p)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putString("EXTRA_BI_ID", this.g);
            bundle.putString("EXTRA_BI_NAME", this.s);
            bundle.putString("EXTRA_BI_SOURCE", this.q);
            bundle.putString("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
            AppRouter.b("/PluginOperation/WebViewActivity").zF_(bundle).c(this);
            return;
        }
        bzsVar.startOperationWebPage(this, str);
    }

    private void cnP_(Context context, String str, Uri uri) {
        Intent intent = new Intent();
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                String str2 = d;
                intent.setComponent(new ComponentName(str2, packageManager.getLaunchIntentForPackage(str2).getComponent().getClassName()));
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setFlags(268468224);
                intent.putExtra("webUrl", str);
                intent.putExtra("weekMonthReportUri", uri);
                context.startActivity(intent);
            } catch (IllegalStateException e2) {
                LogUtil.b("UIDV_DispatchSkipEventActivity", "getHealthAPPIntent()", e2.getMessage());
            }
        }
    }

    private void cnM_(String str, Uri uri) {
        cnt_(str, uri);
        cnO_(str, uri);
        cnA_(str, uri);
        cny_(str, uri);
        cns_(str, uri);
    }

    private void e(String str) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "commonJump, strUri = ", str);
        if (TextUtils.isEmpty(str) || !str.contains(MessageConstant.SKIP_TYPE)) {
            return;
        }
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter(MessageConstant.SKIP_TYPE);
        queryParameter.hashCode();
        if (queryParameter.equals("1")) {
            String queryParameter2 = parse.getQueryParameter("destination");
            if (!TextUtils.isEmpty(queryParameter2) && mra.c.contains(queryParameter2)) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("common_jump_uri", str);
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
                LogUtil.a("UIDV_DispatchSkipEventActivity", "OpAnalytics finish");
                Intent intent = new Intent();
                for (Map.Entry<String, String> entry : AppRouterUtils.zv_(parse).entrySet()) {
                    if (!"destination".equals(entry.getKey()) && !MessageConstant.SKIP_TYPE.equals(entry.getKey())) {
                        intent.putExtra(entry.getKey(), entry.getValue());
                    }
                }
                if ("com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity".equals(queryParameter2)) {
                    intent.setFlags(603979776);
                    long longValue = Long.valueOf(parse.getQueryParameter("key_marker_view_time")).longValue();
                    intent.putExtra("key_marker_view_time", longValue);
                    intent.putExtra("key_bundle_health_last_data_time", longValue);
                }
                intent.setClassName(this, queryParameter2);
                startActivity(intent);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void cnt_(String str, Uri uri) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1687929050:
                if (str.equals("achieveMedal")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1132818117:
                if (str.equals("kakaMessage")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -768165809:
                if (str.equals(NotificationMsgContent.MSG_TYPE_HISTORY_BEST_MSG)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -657194552:
                if (str.equals("sportReport")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -374822315:
                if (str.equals("dataShare")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -280460940:
                if (str.equals("nps_question")) {
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
            d(str, uri.getQueryParameter(ParsedFieldTag.MEDAL_ID));
            return;
        }
        if (c2 == 1) {
            Intent intent = getIntent();
            if (intent != null) {
                mrj.com_(this, uri, intent.getIntExtra("from", 1));
                return;
            }
            return;
        }
        if (c2 == 2) {
            AppRouter.b("/Main/FitnessStepDetailActivity").c(this);
            return;
        }
        if (c2 == 3) {
            if (g()) {
                cnP_(this, null, uri);
                return;
            } else {
                cnz_(uri);
                return;
            }
        }
        if (c2 != 4) {
            if (c2 != 5) {
                return;
            }
            AppRouter.b("/Main/QuestionMainActivity").c(this);
        } else {
            Intent intent2 = new Intent();
            String str2 = d;
            intent2.setPackage(str2);
            intent2.setClassName(str2, "com.huawei.ui.thirdpartservice.activity.ThirdPartServiceActivity");
            startActivity(intent2);
        }
    }

    private void cnz_(Uri uri) {
        Guidepost b2;
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        if (achieveDataApi == null || achieveDataApi.getAdapter(this) == null) {
            cnP_(this, null, uri);
            return;
        }
        try {
            int parseInt = Integer.parseInt(uri.getQueryParameter("max_report_no"));
            int parseInt2 = Integer.parseInt(uri.getQueryParameter("min_report_no"));
            int parseInt3 = Integer.parseInt(uri.getQueryParameter("report_stype"));
            LogUtil.c("UIDV_DispatchSkipEventActivity", "handleDetailUri==>max-->" + parseInt + ":min-->" + parseInt2 + ":reportType-->" + parseInt3);
            Bundle bundle = new Bundle();
            bundle.putString("max_report_no", String.valueOf(parseInt));
            bundle.putString("min_report_no", String.valueOf(parseInt2));
            bundle.putString("report_stype", String.valueOf(parseInt3));
            if (parseInt3 == 0) {
                b2 = AppRouter.b("/PluginAchievement/AchieveMonthReportActivity");
            } else {
                b2 = AppRouter.b("/PluginAchievement/AchieveWeekReportActivity");
            }
            b2.a(276824064).zF_(bundle).c(this);
        } catch (NumberFormatException e2) {
            LogUtil.b("UIDV_DispatchSkipEventActivity", e2.getMessage());
        }
    }

    private void cnO_(String str, Uri uri) {
        AboutApi aboutApi;
        if ("intPlan".equals(str)) {
            FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
            if (fitnessAdviceApi == null) {
                LogUtil.b("UIDV_DispatchSkipEventActivity", "fitnessAdviceApi is null.");
                return;
            } else {
                fitnessAdviceApi.launchPlanTab();
                return;
            }
        }
        if ("device_guide".equals(str)) {
            cnQ_(uri);
            return;
        }
        if (!"aw70_device_guide".equals(str) || (aboutApi = (AboutApi) Services.a("About", AboutApi.class)) == null) {
            return;
        }
        Bundle bundle = new Bundle();
        String queryParameter = uri.getQueryParameter(MedalConstants.EVENT_KEY);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "handleDetailUri productType ", queryParameter);
        try {
            bundle.putString("url", aboutApi.getAw70HelpUrl(Integer.parseInt(queryParameter), this));
            bundle.putInt(Constants.JUMP_MODE_KEY, 0);
            AppRouter.b("/PluginOperation/WebViewActivity").zF_(bundle).c(this);
        } catch (NumberFormatException e2) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "handleDetailUri NumberFormatException ", e2.getMessage());
        }
    }

    private void cnA_(String str, Uri uri) {
        if ("device_app_update_health".equals(str)) {
            if (AuthorizationUtils.a(this)) {
                LogUtil.a("UIDV_DispatchSkipEventActivity", "start AppUpdateDialogActivity");
                ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).showNewVersionDialog(this, new Intent());
                return;
            } else {
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(268468224);
                    startActivity(launchIntentForPackage);
                    LogUtil.a("UIDV_DispatchSkipEventActivity", "jump to main activity!");
                    return;
                }
                return;
            }
        }
        if ("device_ota".equals(str)) {
            Intent intent = new Intent();
            intent.putExtra("device_id", uri.getQueryParameter(MedalConstants.EVENT_KEY));
            intent.setClassName(this, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
            startActivity(intent);
            return;
        }
        if ("device_ota1".equals(str)) {
            Intent intent2 = new Intent();
            intent2.putExtra("device_id", uri.getQueryParameter(MedalConstants.EVENT_KEY));
            intent2.setClassName(this, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
            startActivity(intent2);
            return;
        }
        if ("download_plugin".equals(str)) {
            j();
        }
    }

    private void j() {
        String stringExtra = getIntent().getStringExtra("moduleName");
        LogUtil.a("UIDV_DispatchSkipEventActivity", "moduleName:", stringExtra);
        if (health.compact.a.CommonUtil.af(this)) {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.putExtra("moduleName", stringExtra);
                launchIntentForPackage.addFlags(268468224);
                startActivity(launchIntentForPackage);
            }
            finish();
            return;
        }
        lsp.d().d(this, stringExtra);
    }

    private void cny_(String str, Uri uri) {
        if ("device_scale_ota".equals(str)) {
            cei.b().scaleUpdateJump(this, uri);
        }
        if ("special_person_setting".equals(str)) {
            AppRouter.b("/Main/PrivacyCenterActivity").c(this);
            return;
        }
        if ("userinfo_weight".equals(str)) {
            Intent intent = new Intent();
            intent.putExtra("base_health_data_type_key", 1);
            intent.putExtra("weight_user_id", uri.getQueryParameter(MedalConstants.EVENT_KEY));
            intent.putExtra(ParamConstants.Param.FLAGS, AppRouterExtras.COLDSTART);
            grz.aST_(String.valueOf(10), intent);
            return;
        }
        if ("claim_weight".equals(str)) {
            AppRouter.b("/Main/ClaimMeasureDataActivity").c(this);
            return;
        }
        if ("wifi_device".equals(str)) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "showAuthNotify productId ", uri.getQueryParameter("productId"));
            Intent intent2 = new Intent();
            intent2.putExtra(ArkUIXConstants.FROM_TYPE, "push");
            intent2.putExtra("deviceId", uri.getQueryParameter("deviceId"));
            String str2 = d;
            intent2.setPackage(str2);
            intent2.setFlags(AppRouterExtras.COLDSTART);
            intent2.addFlags(536870912);
            intent2.setClassName(str2, "com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity");
            startActivity(intent2);
            return;
        }
        if ("choose_device".equals(str)) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "showAuthNotify kind ", uri.getQueryParameter("kind"));
            Intent intent3 = new Intent();
            String str3 = d;
            intent3.setPackage(str3);
            intent3.setClassName(str3, "com.huawei.health.device.ui.DeviceMainActivity");
            intent3.putExtra("view", "ListDevice");
            intent3.putExtra("root_in_me", "me");
            intent3.putExtra("kind", "HDK_UNKNOWN");
            startActivity(intent3);
        }
    }

    private void cns_(String str, Uri uri) {
        if ("wifi_device_auth_release".equals(str)) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "otherJump() wifi device auth release");
            Intent intent = new Intent();
            String str2 = d;
            intent.setPackage(str2);
            intent.setClassName(str2, "com.huawei.health.MainActivity");
            intent.setFlags(AppRouterExtras.COLDSTART);
            intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
            startActivity(intent);
            return;
        }
        if ("wifi_device_auth_list".equals(str)) {
            cnC_(uri);
            return;
        }
        if ("device_management_page".equals(str)) {
            cnF_(uri);
            return;
        }
        if (MessageConstant.GROUP_MEDAL_TYPE.equals(str)) {
            cnJ_(uri);
            return;
        }
        if (MessageConstant.MEMBER_TYPE.equals(str)) {
            cnK_(uri);
            return;
        }
        if ("trade".equals(str)) {
            i(uri.getQueryParameter("orderCode"));
            return;
        }
        if (MessageConstant.BENEFIT_TYPE.equals(str)) {
            cnD_(uri);
        } else if ("asset".equals(str)) {
            try {
                njn.a(Integer.parseInt(uri.getQueryParameter("type")), 1);
            } catch (NumberFormatException unused) {
                LogUtil.b("UIDV_DispatchSkipEventActivity", "jump asset NumberFormatException");
            }
        }
    }

    private void cnD_(Uri uri) {
        String newPathConcat;
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpBenefit");
        String queryParameter = uri.getQueryParameter("type");
        String queryParameter2 = uri.getQueryParameter(MedalConstants.EVENT_KEY);
        LogUtil.a("UIDV_DispatchSkipEventActivity", queryParameter, com.huawei.openalliance.ad.constant.Constants.LINK, queryParameter2);
        if (TextUtils.isEmpty(queryParameter2) && Constants.NULL.equals(queryParameter2)) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "linkUrl is empty");
            return;
        }
        String d2 = gic.d(queryParameter2);
        if (TextUtils.isEmpty(this.o)) {
            newPathConcat = CommonUtil.newPathConcat(d2, "from", String.valueOf(4));
        } else {
            newPathConcat = CommonUtil.newPathConcat(d2, "from", String.valueOf(12));
        }
        Context context = BaseApplication.getContext();
        if ("1".equals(queryParameter)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", newPathConcat);
            Intent createWebViewIntent = bzs.e().createWebViewIntent(context, bundle, 268435456);
            if (createWebViewIntent != null) {
                context.startActivity(createWebViewIntent);
                LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpBenefit jump to webviewActivity");
                return;
            } else {
                LogUtil.h("UIDV_DispatchSkipEventActivity", "jumpBenefit h5 intent is null");
                return;
            }
        }
        if ("2".equals(queryParameter)) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(newPathConcat));
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            jdw.bGh_(intent, context);
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpBenefitt jump to scheme page");
            return;
        }
        LogUtil.h("UIDV_DispatchSkipEventActivity", "jumpBenefit error linkType");
    }

    private void i(String str) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpOrder");
        LoginInit loginInit = LoginInit.getInstance(this);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "LoginInit status", Boolean.valueOf(loginInit.getIsLogined()));
        if (!loginInit.getIsLogined() || !AuthorizationUtils.a(this)) {
            c("orderCode", str);
        } else {
            l(str);
        }
        f();
    }

    private void f() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, 2);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.TRADE_REFUND_MESSAGE.value(), hashMap, 0);
    }

    /* loaded from: classes6.dex */
    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private String f8495a;
        private WeakReference<DispatchSkipEventActivity> b;
        private String c;

        c(DispatchSkipEventActivity dispatchSkipEventActivity, String str, String str2) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "LoginedCallback type:", str2);
            this.b = new WeakReference<>(dispatchSkipEventActivity);
            this.c = str2;
            this.f8495a = str;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            DispatchSkipEventActivity dispatchSkipEventActivity = this.b.get();
            if (dispatchSkipEventActivity != null) {
                if (i == 0) {
                    String str = this.c;
                    str.hashCode();
                    if (str.equals(ProxyConfig.MATCH_HTTPS)) {
                        dispatchSkipEventActivity.o(this.f8495a);
                    } else if (str.equals("trade")) {
                        dispatchSkipEventActivity.l(this.f8495a);
                    } else {
                        LogUtil.h("UIDV_DispatchSkipEventActivity", "mType not match");
                    }
                } else {
                    LogUtil.h("UIDV_DispatchSkipEventActivity", "login fail");
                }
                dispatchSkipEventActivity.h = false;
                dispatchSkipEventActivity.finish();
                return;
            }
            LogUtil.h("UIDV_DispatchSkipEventActivity", "LoginedCallback activity is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("orderCode", str);
        AppRouter.b("/TradeService/TradeOrderDetailActivity").zF_(bundle).c(BaseApplication.getContext());
    }

    private void cnK_(Uri uri) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpMember");
        String queryParameter = uri.getQueryParameter("type");
        String queryParameter2 = uri.getQueryParameter(MessageConstant.MESSAGE_TITLE);
        String queryParameter3 = uri.getQueryParameter("activityId");
        String queryParameter4 = uri.getQueryParameter("orderCode");
        n(queryParameter3);
        b(queryParameter2, queryParameter);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "orderCode:", queryParameter4);
        if (!TextUtils.isEmpty(queryParameter4) && !Constants.NULL.equals(queryParameter4) && b(queryParameter)) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "ordercode is not empty");
            i(queryParameter4);
            return;
        }
        int i = TextUtils.isEmpty(this.o) ? 3 : 4;
        if (gpo.b()) {
            AppRouter.b("/OperationBundle/MemberRelayActivity").c("from", i).e("memberTabRelayUri", uri.toString()).c(this);
        } else {
            d(i);
        }
    }

    private boolean b(String str) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "type:", str);
        try {
            return f8490a.contains(Integer.valueOf(Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            LogUtil.b("UIDV_DispatchSkipEventActivity", "isNeedSkipOrder NumberFormatException");
            return false;
        }
    }

    private void d(int i) {
        LoginInit loginInit = LoginInit.getInstance(this);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "LoginInit status", Boolean.valueOf(loginInit.getIsLogined()));
        String str = "from=" + i;
        if (!loginInit.getIsLogined() || !AuthorizationUtils.a(this)) {
            c("memberUri", gpn.c(str));
        } else {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jump to loading data activity");
            gpn.c(this, str);
        }
    }

    private void n(String str) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "setMemberMessageRead");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "messageId is empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "getVipMessages VipApi is null");
        } else {
            vipApi.setVipMessageRead(arrayList);
        }
    }

    private void b(String str, String str2) {
        String value;
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("title", str);
        hashMap.put("type", str2);
        if (TextUtils.isEmpty(this.o)) {
            value = AnalyticsValue.VIP_MESSAGE_CLICK.value();
            dqj.e(3);
        } else {
            value = AnalyticsValue.VIP_NOTIFICATION_CLICK.value();
            dqj.e(4);
        }
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), value, hashMap, 0);
    }

    private void cnJ_(Uri uri) {
        String queryParameter = uri.getQueryParameter("type");
        String queryParameter2 = uri.getQueryParameter("groupId");
        String queryParameter3 = uri.getQueryParameter("activityId");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        if (TextUtils.isEmpty(queryParameter3)) {
            builder.addPath("#/?messageType=" + queryParameter + "&groupId=" + queryParameter2);
        } else {
            builder.addPath("#/?messageType=" + queryParameter + "&groupId=" + queryParameter2 + "&activityId=" + queryParameter3);
        }
        bzs.e().loadH5ProApp(this, "com.huawei.health.h5.groups", builder);
    }

    private void d(String str, String str2) {
        if (o()) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "PluginAchieveAdapter == null || PluginOperationAdapter == null!");
            h();
            c(str, str);
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(this);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "LoginInit ", Boolean.valueOf(loginInit.getIsLogined()));
        if (!loginInit.getIsLogined() || !AuthorizationUtils.a(this)) {
            c(str, str);
            return;
        }
        AchieveNavigationApi achieveNavigationApi = (AchieveNavigationApi) Services.a("PluginAchievement", AchieveNavigationApi.class);
        if (achieveNavigationApi != null) {
            achieveNavigationApi.showAchieveMedalById(this, str2, "2");
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jump to loading data activity");
        } else {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "achieveNavigationApi is null");
        }
    }

    private boolean o() {
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        return (achieveDataApi != null ? achieveDataApi.getAdapter(this) : null) == null || bzs.e().getAdapter(this) == null;
    }

    private void c(String str, String str2) {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(268468224);
            launchIntentForPackage.putExtra(str, str2);
            launchIntentForPackage.putExtra("needLogin", true);
            startActivity(launchIntentForPackage);
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jump to main activity");
        }
    }

    private boolean g() {
        return (LoginInit.getInstance(this).getIsLogined() && AuthorizationUtils.a(this)) ? false : true;
    }

    private void h(String str) {
        Intent intent = getIntent();
        if (!"sportHistory".equals(str) || intent == null) {
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(this);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "LoginInit ", Boolean.valueOf(loginInit.getIsLogined()));
        String stringExtra = intent.getStringExtra("jumpFromFileSyncNotify");
        LogUtil.a("UIDV_DispatchSkipEventActivity", str, stringExtra);
        if (!AuthorizationUtils.a(this) || (Utils.i() && !loginInit.getIsLogined())) {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.putExtra("entrance", stringExtra);
                launchIntentForPackage.addFlags(268468224);
                launchIntentForPackage.putExtra(str, str);
                startActivity(launchIntentForPackage);
                LogUtil.a("UIDV_DispatchSkipEventActivity", "jump to main activity");
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("entrance", stringExtra);
        Guidepost b2 = AppRouter.b("/Main/SportHistoryActivity");
        int b3 = fhp.c().b();
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromFileSyncNotify fitState = ", Integer.valueOf(b3));
        boolean z = b3 == 2 || b3 == 5;
        if (!eme.b().isSportingStatus() && !z) {
            b2.a(AppRouterExtras.COLDSTART);
        }
        b2.zF_(bundle).c(this);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jump to history activity");
    }

    private void cnI_(Uri uri) {
        String host = uri.getHost();
        Intent intent = getIntent();
        if (!"sleepDetail".equals(host) || intent == null) {
            return;
        }
        i();
        long longExtra = intent.getLongExtra("startTime", 0L);
        long longExtra2 = intent.getLongExtra("endTime", 0L);
        if (longExtra == 0) {
            try {
                longExtra = Long.parseLong(uri.getQueryParameter("startTime"));
            } catch (NumberFormatException e2) {
                LogUtil.b("UIDV_DispatchSkipEventActivity", e2);
            }
        }
        if (longExtra2 == 0) {
            longExtra2 = Long.parseLong(uri.getQueryParameter("endTime"));
        }
        String stringExtra = intent.getStringExtra("jumpFromFileSyncNotify");
        LogUtil.a("UIDV_DispatchSkipEventActivity", "startTime ", Long.valueOf(longExtra), " endTime ", Long.valueOf(longExtra2), " jumpFromTag ", stringExtra);
        LoginInit loginInit = LoginInit.getInstance(this);
        if (AuthorizationUtils.a(this) && loginInit.getIsLogined()) {
            Bundle bundle = new Bundle();
            bundle.putLong("endTime", longExtra2);
            bundle.putString("jumpFromFileSyncNotify", stringExtra);
            AppRouter.b("/Main/KnitSleepDetailActivity").zF_(bundle).c(this);
            return;
        }
        LogUtil.a("UIDV_DispatchSkipEventActivity", "without login, jump to main activity");
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.putExtra("startTime", longExtra);
            launchIntentForPackage.putExtra("endTime", longExtra2);
            launchIntentForPackage.putExtra("jumpFromFileSyncNotify", stringExtra);
            launchIntentForPackage.addFlags(268468224);
            launchIntentForPackage.putExtra("sleepDetail", host);
            startActivity(launchIntentForPackage);
            finish();
        }
    }

    private void cnG_(Uri uri) {
        String host = uri.getHost();
        String queryParameter = uri.getQueryParameter("ecgPageType");
        if ("ecgDetail".equals(host)) {
            d(this.l);
            LoginInit loginInit = LoginInit.getInstance(this);
            if (!AuthorizationUtils.a(this) || ((Utils.i() && !loginInit.getIsLogined()) || health.compact.a.CommonUtil.af(this))) {
                LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromEcgSyncNotify,jump to MainActivity");
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                if (launchIntentForPackage == null) {
                    LogUtil.h("UIDV_DispatchSkipEventActivity", "jumpFromEcgSyncNotify,launchIntentForPackage is null");
                    finish();
                    return;
                }
                launchIntentForPackage.putExtra("pullEcgQuick", 1);
                launchIntentForPackage.putExtra("ecgPageType", queryParameter);
                if ("donate".equals(queryParameter)) {
                    launchIntentForPackage.putExtra("donateId", uri.getQueryParameter("donateId"));
                }
                launchIntentForPackage.putExtra("needLogin", !loginInit.getIsLogined());
                startActivity(launchIntentForPackage);
                finish();
                return;
            }
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromEcgSyncNotify, startEcgQuick");
            mxv.crB_(this, uri.getQueryParameter("donateId"), queryParameter);
        }
    }

    private void f(String str) {
        char c2;
        Intent intent = getIntent();
        if (intent == null || !"com.huawei.wallet".equals(str)) {
            return;
        }
        Uri parse = Uri.parse(intent.getStringExtra(CommonUtil.DETAIL_URI));
        PluginPay pluginPay = PluginPay.getInstance(com.huawei.haf.application.BaseApplication.e());
        AdapterWearMgrApi adapterWearMgrApi = (AdapterWearMgrApi) Services.a("HwAdapterWearMgr", AdapterWearMgrApi.class);
        if (adapterWearMgrApi != null) {
            pluginPay.setAdapter(adapterWearMgrApi.getPayAdapterImpl(this));
        } else {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "adapterWearMgrApi is null");
        }
        LoginInit loginInit = LoginInit.getInstance(this);
        if (!AuthorizationUtils.a(this) || (Utils.i() && !loginInit.getIsLogined())) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromWalletNotify, jump to Device");
            c((Context) this, "login");
            finish();
            return;
        }
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromWalletNotify, start wallet market activity.");
        String c3 = c();
        c3.hashCode();
        int hashCode = c3.hashCode();
        if (hashCode == -2115809389) {
            if (c3.equals("newDeviceConnected")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -1708699727) {
            if (hashCode == 1080704861 && c3.equals("oldDevice")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (c3.equals("deviceDisconnected")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            cnx_(this, parse);
            finish();
            return;
        }
        if (c2 == 1) {
            c((Context) this, "device");
            cnv_(this, parse, "checkDeviceToPlugin");
            finish();
        } else {
            if (c2 == 2) {
                c((Context) this, "oldDevice");
                cnv_(this, parse, "checkDeviceToPlugin");
                finish();
                return;
            }
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromWalletNotify, unknown device connection state.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return bzu.b().isPluginAvaiable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cnx_(Context context, Uri uri) {
        if (a()) {
            cnL_(context, uri);
        } else {
            c(context, "walletPlugin");
            cnv_(context, uri, "checkPlugin");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cnw_(Context context, Uri uri) {
        char c2;
        String c3 = c();
        c3.hashCode();
        int hashCode = c3.hashCode();
        if (hashCode == -2115809389) {
            if (c3.equals("newDeviceConnected")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -1708699727) {
            if (hashCode == 1080704861 && c3.equals("oldDevice")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (c3.equals("deviceDisconnected")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            cnL_(context, uri);
            return;
        }
        if (c2 == 1) {
            c(context, "device");
            cnv_(context, uri, "checkDevice");
        } else if (c2 == 2) {
            c(context, "oldDevice");
            cnv_(context, uri, "checkDevice");
        } else {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "detectDeviceToWallet, unknown device connection state.");
        }
    }

    private void cnv_(final Context context, final Uri uri, final String str) {
        b();
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        Handler handler = e;
        Runnable runnable = new Runnable() { // from class: com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity.5
            @Override // java.lang.Runnable
            public void run() {
                Message obtain = Message.obtain();
                obtain.what = 1;
                if (SystemClock.elapsedRealtime() - elapsedRealtime >= 60000) {
                    DispatchSkipEventActivity.this.j.sendMessage(obtain);
                }
                if (str.equals("checkDevice") && DispatchSkipEventActivity.this.c().equals("newDeviceConnected")) {
                    DispatchSkipEventActivity.this.j.sendMessage(obtain);
                    DispatchSkipEventActivity.this.cnL_(context, uri);
                    return;
                }
                if (str.equals("checkDeviceToPlugin") && DispatchSkipEventActivity.this.c().equals("newDeviceConnected")) {
                    DispatchSkipEventActivity.this.j.sendMessage(obtain);
                    DispatchSkipEventActivity.this.cnx_(context, uri);
                } else if (!str.equals("checkPlugin") || !DispatchSkipEventActivity.this.a()) {
                    DispatchSkipEventActivity.e.postDelayed(this, 1000L);
                } else {
                    DispatchSkipEventActivity.this.j.sendMessage(obtain);
                    DispatchSkipEventActivity.this.cnw_(context, uri);
                }
            }
        };
        b = runnable;
        handler.postDelayed(runnable, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        PluginBaseAdapter adapter = PluginPay.getInstance(this).getAdapter();
        DeviceInfo deviceInfo = null;
        PluginPayAdapter pluginPayAdapter = adapter instanceof PluginPayAdapter ? (PluginPayAdapter) adapter : null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "UIDV_DispatchSkipEventActivity");
        if (deviceList == null) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "getActiveDeviceInfo() deviceInfoList is null.", "UIDV_DispatchSkipEventActivity");
        } else {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
            LogUtil.a("UIDV_DispatchSkipEventActivity", "getActiveDeviceInfo deviceInfo ", deviceInfo, " , tag is ", "UIDV_DispatchSkipEventActivity");
        }
        return (deviceInfo == null || deviceInfo.getDeviceConnectState() == 3) ? "deviceDisconnected" : (deviceInfo.getDeviceConnectState() == 2 && pluginPayAdapter != null && pluginPayAdapter.isWalletOpenCard()) ? "newDeviceConnected" : "oldDevice";
    }

    private void b() {
        if (b != null) {
            synchronized (c) {
                if (b != null) {
                    e.removeCallbacks(b);
                    b = null;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(Context context, String str) {
        char c2;
        Intent intent = new Intent();
        String str2 = d;
        intent.setPackage(str2);
        intent.setClassName(str2, "com.huawei.health.MainActivity");
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
        str.hashCode();
        switch (str.hashCode()) {
            case -1335157162:
                if (str.equals("device")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 103149417:
                if (str.equals("login")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 485232780:
                if (str.equals("walletPlugin")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1080704861:
                if (str.equals("oldDevice")) {
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
            Toast.makeText(getApplicationContext(), R$string.IDS_device_pair_connect_detail, 1).show();
        } else if (c2 == 1) {
            Toast.makeText(getApplicationContext(), R$string.IDS_device_login_to_connect, 1).show();
        } else if (c2 == 2) {
            Toast.makeText(getApplicationContext(), R$string.IDS_device_download_bundle, 1).show();
        } else if (c2 == 3) {
            Toast.makeText(getApplicationContext(), R$string.IDS_device_old_wearable, 1).show();
        } else {
            throw new IllegalStateException("Unexpected value: " + str);
        }
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cnL_(Context context, Uri uri) {
        bzu.b().initHealthPayAdapter(context);
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), ComponentInfo.PluginPay_A_47);
        String queryParameter = uri.getQueryParameter("needConnect");
        intent.putExtra("fromPush", "true");
        intent.putExtra("issuerId", uri.getQueryParameter("issuerId"));
        intent.putExtra("aarVersion", uri.getQueryParameter("aarVersion"));
        intent.putExtra("activityCode", uri.getQueryParameter("activityCode"));
        intent.putExtra("classPath", uri.getQueryParameter("classPath"));
        if (queryParameter == null || queryParameter.isEmpty()) {
            queryParameter = "true";
        }
        intent.putExtra("needConnect", queryParameter);
        LogUtil.a("UIDV_DispatchSkipEventActivity", "go to ", ComponentInfo.PluginPay_A_47);
        bzu.b().launchActivity(context, intent);
    }

    private void cnH_(Uri uri) {
        String queryParameter;
        int parseInt;
        long parseLong;
        Bundle bundle;
        LoginInit loginInit;
        if ("healthDetail".equals(uri.getHost()) && getIntent() != null) {
            try {
                queryParameter = uri.getQueryParameter("extra_service_id");
                parseInt = Integer.parseInt(uri.getQueryParameter("extra_page_type"));
                parseLong = Long.parseLong(uri.getQueryParameter("extra_time_stamp"));
                bundle = new Bundle();
                loginInit = LoginInit.getInstance(this);
            } catch (NumberFormatException e2) {
                LogUtil.b("UIDV_DispatchSkipEventActivity", e2);
            }
            if (AuthorizationUtils.a(this) && loginInit.getIsLogined()) {
                LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromHealthSyncNotify, startDetail=", queryParameter, ", ", Integer.valueOf(parseInt), ", ", Long.valueOf(parseLong));
                if ("BloodSugarCardConstructor".equals(queryParameter)) {
                    cnu_(uri);
                }
                cnN_(queryParameter, parseInt, parseLong, bundle);
                AppRouter.b("/Main/HealthDataDetailActivity").zF_(bundle).c(this);
                finish();
                return;
            }
            cnR_(queryParameter, parseInt, parseLong, bundle);
            finish();
        }
    }

    private void cnN_(String str, int i, long j, Bundle bundle) {
        bundle.putString("extra_service_id", str);
        bundle.putLong("extra_time_stamp", j);
        bundle.putInt("extra_page_type", i);
        Bundle bundle2 = new Bundle();
        bundle2.putString("jump_from_tag", "jump_from_notify");
        bundle.putBundle("extra_bundle", bundle2);
    }

    private void cnu_(Uri uri) {
        String queryParameter = uri.getQueryParameter("notification_type");
        HashMap hashMap = new HashMap(16);
        if ("sport_notification".equals(queryParameter)) {
            hashMap.put("type", 1);
        }
        if (queryParameter != null && queryParameter.equals("sleep_notification")) {
            hashMap.put("type", 2);
        }
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_JUMP_DETAIL_2040132.value();
        hashMap.put("click", "1");
        gge.e(value, hashMap);
    }

    private void cnR_(String str, int i, long j, Bundle bundle) {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpFromHealthSyncNotify, jump to MainActivity");
            bundle.putString("jump_from_tag", "jump_from_notify");
            launchIntentForPackage.addFlags(268468224);
            launchIntentForPackage.putExtra("extra_service_id", str);
            launchIntentForPackage.putExtra("extra_page_type", i);
            launchIntentForPackage.putExtra("extra_time_stamp", j);
            launchIntentForPackage.putExtra("extra_bundle", bundle);
            startActivity(launchIntentForPackage);
        }
    }

    private void h() {
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        AdapterHealthMgrApi adapterHealthMgrApi = (AdapterHealthMgrApi) Services.a("HwAdpaterHealthMgr", AdapterHealthMgrApi.class);
        if (achieveDataApi != null) {
            if (achieveDataApi.getAdapter(this) == null) {
                LogUtil.h("UIDV_DispatchSkipEventActivity", "pluginAchieveAdapter = null");
                if (adapterHealthMgrApi != null) {
                    achieveDataApi.initAdapter(this, adapterHealthMgrApi.getAchieveAdapterImpl());
                } else {
                    LogUtil.h("UIDV_DispatchSkipEventActivity", "adapterHealth is null");
                }
            }
            i();
            return;
        }
        LogUtil.h("UIDV_DispatchSkipEventActivity", "achieveDataApi = null");
    }

    private void i() {
        AdapterHealthMgrApi adapterHealthMgrApi = (AdapterHealthMgrApi) Services.a("HwAdpaterHealthMgr", AdapterHealthMgrApi.class);
        if (adapterHealthMgrApi == null) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "initPluginOperation adapterHealth is null");
        } else {
            bzs.e().initAdapter(this, adapterHealthMgrApi.getOperationAdapterImpl(this));
        }
    }

    private void cnQ_(final Uri uri) {
        this.i.execute(new Runnable() { // from class: com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity.2
            @Override // java.lang.Runnable
            public void run() {
                AboutApi aboutApi = (AboutApi) Services.a("About", AboutApi.class);
                if (aboutApi != null) {
                    String queryParameter = uri.getQueryParameter(MedalConstants.EVENT_KEY);
                    String queryParameter2 = uri.getQueryParameter(BleConstants.BLE_HI_LINK);
                    if (queryParameter == null || "".equals(queryParameter)) {
                        LogUtil.h("UIDV_DispatchSkipEventActivity", "productType is null");
                        return;
                    }
                    LogUtil.a("UIDV_DispatchSkipEventActivity", "handleDetailUri productType ", queryParameter);
                    final String adaptUrlInMessageCenter = aboutApi.adaptUrlInMessageCenter(DispatchSkipEventActivity.this, health.compact.a.CommonUtil.h(queryParameter), queryParameter2);
                    DispatchSkipEventActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            Bundle bundle = new Bundle();
                            bundle.putString("url", adaptUrlInMessageCenter);
                            bundle.putInt(Constants.JUMP_MODE_KEY, 0);
                            AppRouter.b("/PluginOperation/WebViewActivity").zF_(bundle).c(DispatchSkipEventActivity.this);
                        }
                    });
                }
            }
        });
    }

    private void cnC_(Uri uri) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jump device share auth list activity");
        String queryParameter = uri.getQueryParameter("devId");
        if (TextUtils.isEmpty(queryParameter)) {
            LogUtil.b("UIDV_DispatchSkipEventActivity", "jumpAuthList fail: devId is empty");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("dev_id", queryParameter);
        AppRouter.b("/PluginDevice/WifiDeviceAuthRequestListActivity").a(536870912).zF_(bundle).c(this);
    }

    private void cnF_(Uri uri) {
        LogUtil.a("UIDV_DispatchSkipEventActivity", "jumpDeviceManagementPage:jump device management page");
        String queryParameter = uri.getQueryParameter("proId");
        if (TextUtils.isEmpty(queryParameter)) {
            LogUtil.b("UIDV_DispatchSkipEventActivity", "jumpDeviceManagementPage fail: proId is empty");
            return;
        }
        Intent intent = new Intent();
        String str = d;
        intent.setPackage(str);
        intent.setClassName(str, "com.huawei.health.device.ui.DeviceMainActivity");
        intent.addFlags(536870912);
        intent.putExtra("view", "deviceManage");
        intent.putExtra("productId", queryParameter);
        intent.putExtra("goto", "devicebind");
        startActivity(intent);
    }

    private void a(String str, String str2) {
        ixx.d().c(new iyk.e().a(1).d(this.s).a(this.o).e(this.r).c(this.t).b(this.m).b());
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        Uri parse = Uri.parse(this.o);
        if (str.equals("http") || str.equals(ProxyConfig.MATCH_HTTPS)) {
            if (parse == null) {
                return;
            }
            String queryParameter = parse.getQueryParameter("activityId");
            if (TextUtils.isEmpty(queryParameter)) {
                queryParameter = "noActivity";
            }
            hashMap.put("type", queryParameter);
        } else if ("messagecenter".equals(str)) {
            if ("sportReport".equals(str2)) {
                String queryParameter2 = parse.getQueryParameter("report_stype");
                a(queryParameter2);
                if (queryParameter2 != null && "1".equals(queryParameter2)) {
                    hashMap.put("type", "1");
                } else if (queryParameter2 != null && "0".equals(queryParameter2)) {
                    hashMap.put("type", "2");
                } else {
                    hashMap.put("type", "WEEK_MONTH_REPORT");
                }
            }
            if ("kakaMessage".equals(str2)) {
                hashMap.put("type", "3");
            }
            if (NotificationMsgContent.MSG_TYPE_HISTORY_BEST_MSG.equals(str2)) {
                hashMap.put("type", "4");
            }
        } else {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "setNotifyBiAnalyticsEvent is other scheme");
        }
        LogUtil.a("UIDV_DispatchSkipEventActivity", "setNotifyBiAnalyticsEvent type = ", hashMap.get("type"));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_NOTIFICATION_MESSAGE_CENTER_2110001.value(), hashMap, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str) {
        char c2;
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UIDV_DispatchSkipEventActivity", "type is empty");
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 1785:
                if (str.equals("81")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1786:
                if (str.equals("82")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1787:
                if (str.equals("83")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1788:
            default:
                c2 = 65535;
                break;
            case 1789:
                if (str.equals("85")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1790:
                if (str.equals("86")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
        }
        if (c2 == 0 || c2 == 1 || c2 == 2) {
            str2 = "ECGacquisition";
        } else {
            if (c2 != 3 && c2 != 4) {
                LogUtil.h("UIDV_DispatchSkipEventActivity", "doEcgBiEvent is not support, type = ", str);
                return;
            }
            str2 = "electrocardiogram";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", str);
        hashMap.put("serviceType", str2);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.ECG_MESSAGE_CLICK_11000317.value(), hashMap, 0);
    }

    private void a(String str) {
        HashMap hashMap = new HashMap();
        if (str != null && "1".equals(str)) {
            hashMap.put("report", "1");
        } else if (str != null && "0".equals(str)) {
            hashMap.put("report", "2");
        }
        hashMap.put("from", 3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_REPORT_1100009.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
