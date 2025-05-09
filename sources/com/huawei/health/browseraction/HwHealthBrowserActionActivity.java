package com.huawei.health.browseraction;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.MainActivity;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.browseraction.HwHealthBrowserActionActivity;
import com.huawei.health.marketing.datatype.VipPageSourceArea;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.H5DeepLinkUsePermissionsHelper;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.ceo;
import defpackage.dqj;
import defpackage.fbi;
import defpackage.gop;
import defpackage.goy;
import defpackage.gpo;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.mpj;
import defpackage.nrv;
import defpackage.pep;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes.dex */
public class HwHealthBrowserActionActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f2199a;
    private boolean c;
    private String j;
    private Uri o;
    private String g = null;
    private String b = null;
    private String k = null;
    private Observer h = null;
    private boolean d = false;
    private String f = null;
    private String m = null;
    private String i = null;
    private String e = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.c("HwHealthBrowserActionActivity", "onCreate()");
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("HwHealthBrowserActionActivity", "handleCommand(Intent intent) intent == null");
            finish();
            return;
        }
        try {
            Uri zs_ = AppRouterUtils.zs_(intent);
            this.o = zs_;
            if (zs_ == null) {
                LogUtil.h("HwHealthBrowserActionActivity", "handleCommand(Intent intent) schemeData == null");
                finish();
                return;
            }
            CD_(intent);
            String uri = this.o.toString();
            String substring = uri.substring(uri.indexOf("address") + 8);
            this.g = substring;
            String trim = TextUtils.isEmpty(substring) ? "" : Uri.decode(this.g).trim();
            if (trim.startsWith("http://")) {
                if (!CommonUtil.bv() && !CommonUtil.as()) {
                    this.g = trim;
                }
                LogUtil.h("HwHealthBrowserActionActivity", "onCreate: disableHttpLoad -> " + this.g);
                finish();
                return;
            }
            this.k = this.o.getQueryParameter("type");
            this.b = this.o.getQueryParameter("from");
            this.j = this.o.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
            this.d = this.o.getBooleanQueryParameter("scrollDown", false);
            this.c = this.o.getBooleanQueryParameter("isNeedLogin", false);
            s();
            LogUtil.a("HwHealthBrowserActionActivity", "mQueryParameter = ", this.g, ", mFromWhere = ", this.b, ", schemeDataStr = ", uri);
            if (TextUtils.isEmpty(this.g)) {
                LogUtil.h("HwHealthBrowserActionActivity", "The schemeData mQueryParameter is unsafe! ");
                finish();
            } else {
                this.f2199a = this;
                i();
                finish();
            }
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwHealthBrowserActionActivity", "onCreate mQueryParameter IllegalArgumentException:", e.getMessage());
            finish();
        } catch (Exception unused) {
            LogUtil.b("HwHealthBrowserActionActivity", "onCreate mQueryParameter Exception");
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        CD_(intent);
    }

    private void CD_(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.f = extras.getString("pushId");
            this.m = extras.getString("serviceId");
            this.i = extras.getString("notifiUri");
            this.e = extras.getString("messageContent");
            LogUtil.a("HwHealthBrowserActionActivity", "mPushId = ", this.f, "mServiceId =", this.m);
        }
    }

    private void i() {
        if (Utils.o() && H5DeepLinkUsePermissionsHelper.isOnlySupportedInChina(this.g)) {
            LogUtil.a("HwHealthBrowserActionActivity", "deepLink: not supported oversea");
            fbi.c(this);
            return;
        }
        PluginOperation pluginOperation = PluginOperation.getInstance(this.f2199a);
        PluginBaseAdapter adapter = pluginOperation.getAdapter();
        PluginOperationAdapter pluginOperationAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : null;
        LogUtil.a("HwHealthBrowserActionActivity", "adapter=", pluginOperationAdapter);
        if (pluginOperationAdapter == null) {
            a();
        }
        a(pluginOperation);
    }

    private void a(PluginOperation pluginOperation) {
        LoginInit loginInit = LoginInit.getInstance(this.f2199a);
        boolean z = true;
        boolean z2 = (!Utils.o() || OperationUtils.isOperation(loginInit.getAccountInfo(1010)) || h() || j() || g()) ? false : true;
        boolean z3 = (!loginInit.isKidAccount() || H5DeepLinkUsePermissionsHelper.isSupportKid(this.g) || g()) ? false : true;
        boolean z4 = !loginInit.getIsLogined();
        boolean z5 = !AuthorizationUtils.a(this.f2199a);
        boolean z6 = z5 || z4;
        if (d(this.b)) {
            d(z6, z2, loginInit, pluginOperation);
            return;
        }
        if ((!Utils.i() || !f()) && !H5DeepLinkUsePermissionsHelper.isSupportNoCloud(this.g)) {
            z = false;
        }
        if ((z2 && !z) || z3) {
            fbi.c(this);
            return;
        }
        if (z6) {
            if (z4 && !z5 && gpo.b() && v()) {
                return;
            }
            r();
            return;
        }
        b(pluginOperation);
    }

    private boolean g() {
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.h("HwHealthBrowserActionActivity", "isArkuixSupportOperationDeeplink mQueryParameter is empty.");
            return false;
        }
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("HwHealthBrowserActionActivity", "isArkuixSupportOperationDeeplink mIntent is null.");
            return false;
        }
        int intExtra = intent.getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
        LogUtil.a("HwHealthBrowserActionActivity", "isArkuixSupportOperationDeeplink fromType=", Integer.valueOf(intExtra));
        if (intExtra == 1) {
            return this.g.contains("com.huawei.health.h5.setting") || this.g.contains("com.huawei.health.h5.health-data-subpage");
        }
        LogUtil.h("HwHealthBrowserActionActivity", "isArkuixSupportOperationDeeplink is not from arkuix.");
        return false;
    }

    private boolean f() {
        return !TextUtils.isEmpty(this.g) && this.g.contains("h5pro=true");
    }

    private boolean l() {
        return !TextUtils.isEmpty(this.g) && this.g.contains("com.huawei.health.h5.watchface");
    }

    private void b(PluginOperation pluginOperation) {
        if ("aar".equals(this.k)) {
            LogUtil.a("HwHealthBrowserActionActivity", "jump to aar webviewactivity");
            pep.a(this.f2199a, this.g);
            finish();
            return;
        }
        if (l()) {
            goy.a(this.g);
            finish();
            return;
        }
        if (gpo.b() && v()) {
            return;
        }
        if ("openDiscover".equals(this.b)) {
            Intent intent = new Intent(this.f2199a, (Class<?>) MainActivity.class);
            intent.putExtra("openDiscover", true);
            intent.addFlags(AppRouterExtras.COLDSTART);
            intent.putExtra("scrollDown", this.d);
            this.f2199a.startActivity(intent);
            return;
        }
        if ("walletBanner".equals(this.b)) {
            k();
            return;
        }
        if ("thirdDevice".equals(this.b)) {
            t();
            return;
        }
        if ("thirdDeviceToApp".equals(this.b)) {
            o();
            return;
        }
        if ("order".equals(this.b)) {
            m();
            return;
        }
        if ("healthRecord".equals(this.b)) {
            n();
        } else if ("7".equals(this.b)) {
            c(pluginOperation);
        } else {
            pluginOperation.startOperationWebPage(this.f2199a, this.g, this.b);
        }
    }

    private boolean h() {
        if (TextUtils.isEmpty(this.g)) {
            return false;
        }
        return this.g.startsWith("com.huawei.health.h5.health-record") && "micro_report_detail".equals(Uri.parse(this.g).getQueryParameter(BleConstants.KEY_PATH));
    }

    private boolean j() {
        if (TextUtils.isEmpty(this.g)) {
            return false;
        }
        return this.g.startsWith("com.huawei.health.h5.weight") && "DataPage".equals(Uri.parse(this.g).getQueryParameter(BleConstants.KEY_PATH));
    }

    private void q() {
        if (Uri.parse(this.g).isOpaque()) {
            return;
        }
        LogUtil.a("HwHealthBrowserActionActivity", "putBiEventFromDeeplink");
        String b = b();
        if (TextUtils.isEmpty(b)) {
            return;
        }
        H5proUtil.putBiEventFromH5Deeplink(this.o.toString(), b);
    }

    private String b() {
        if (this.g.startsWith("http://") || this.g.startsWith("https://") || this.g.startsWith(Constants.PREFIX_FILE)) {
            return this.g;
        }
        return this.g.contains("h5pro=true") ? H5proUtil.getPackageNameFromUrl(this.g) : "";
    }

    private boolean v() {
        String queryParameter = this.o.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        String queryParameter2 = this.o.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        if (!TextUtils.isEmpty(this.g) && this.g.contains("vip")) {
            String queryParameter3 = this.o.getQueryParameter(BleConstants.KEY_PATH);
            LogUtil.a("HwHealthBrowserActionActivity", "jump to h5 vip page");
            if (!TextUtils.isEmpty(queryParameter3) && !queryParameter3.equals("VipSubscribe") && !queryParameter3.equals("MemberCenter")) {
                LogUtil.a("HwHealthBrowserActionActivity", "not jump to vip center page");
                return false;
            }
            if (this.g.contains("customContent")) {
                String queryParameter4 = this.o.getQueryParameter("customContent");
                LogUtil.a("HwHealthBrowserActionActivity", "jump to h5 vip page with qrcode, customContent is", queryParameter4);
                VipPageSourceArea vipPageSourceArea = (VipPageSourceArea) nrv.b(queryParameter4, VipPageSourceArea.class);
                dqj.d(vipPageSourceArea.getProvince());
                dqj.e(vipPageSourceArea.getCity());
                dqj.g(queryParameter);
                dqj.c(queryParameter2);
                AppRouter.b("/home/main").e("openDiscover", true).e("jumpVipTabWithQrCode", true).c(this.f2199a);
            } else {
                LogUtil.a("HwHealthBrowserActionActivity", "jump to h5 vip page, redirect to relay vip page");
                AppRouter.b("/OperationBundle/MemberRelayActivity").e("memberTabRelayUri", this.o.toString()).c(this.f2199a);
            }
            return true;
        }
        if (!"openDiscover".equals(this.b)) {
            return false;
        }
        LogUtil.a("HwHealthBrowserActionActivity", "jump to from=openDiscover, redirect to relay vip page");
        AppRouter.b("/OperationBundle/MemberRelayActivity").e("memberTabRelayUri", this.o.toString()).e("scrollDown", this.d).c(this.f2199a);
        return true;
    }

    private void r() {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage == null) {
            return;
        }
        launchIntentForPackage.putExtra("schemeUrl", this.g);
        launchIntentForPackage.putExtra("type", this.k);
        launchIntentForPackage.putExtra("needLogin", this.c);
        launchIntentForPackage.putExtra("from", this.b);
        startActivity(launchIntentForPackage);
    }

    private void c(final PluginOperation pluginOperation) {
        String queryParameter = this.o.getQueryParameter("address");
        if (!TextUtils.isEmpty(queryParameter)) {
            String str = queryParameter.split("\\?")[0];
            LogUtil.a("HwHealthBrowserActionActivity", "jumpToPpgResult split url=", str);
            if ("com.huawei.health.h5.ppg".equals(str)) {
                LogUtil.a("HwHealthBrowserActionActivity", "jumpToPpgResult jumping...");
                mpj.a().launchActivity(this.f2199a, new Intent(), new AppBundleLauncher.InstallCallback() { // from class: cbo
                    @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                    public final boolean call(Context context, Intent intent) {
                        return HwHealthBrowserActionActivity.this.CE_(pluginOperation, context, intent);
                    }
                });
                return;
            } else {
                LogUtil.a("HwHealthBrowserActionActivity", "jumpToPpgResult failed, jumping by WebViewActivity...");
                pluginOperation.startOperationWebPage(this.f2199a, this.g, this.b);
                return;
            }
        }
        LogUtil.b("HwHealthBrowserActionActivity", "jumpToPpgResult failed, address is empty!");
    }

    public /* synthetic */ boolean CE_(PluginOperation pluginOperation, Context context, Intent intent) {
        pluginOperation.startOperationWebPage(this.f2199a, this.g, this.b);
        return false;
    }

    private void k() {
        StringBuilder sb = new StringBuilder("wallet://com.huawei.wallet/walletbus?action=com.huawei.wallet.transit.action.COMMON&classPath=com.huawei.nfc.carrera.ui.bus.opencard.BindBusCardAddActivity");
        Uri parse = Uri.parse(this.g);
        String queryParameter = parse.getQueryParameter("issuerId");
        String queryParameter2 = parse.getQueryParameter("aarVersion");
        String queryParameter3 = parse.getQueryParameter("activityCode");
        if (!TextUtils.isEmpty(queryParameter)) {
            sb.append("&issuerId=");
            sb.append(queryParameter);
        }
        if (!TextUtils.isEmpty(queryParameter2)) {
            sb.append("&aarVersion=");
            sb.append(queryParameter2);
        }
        if (!TextUtils.isEmpty(queryParameter3)) {
            sb.append("&activityCode=");
            sb.append(queryParameter3);
        }
        String queryParameter4 = parse.getQueryParameter("channel");
        if (!TextUtils.isEmpty(queryParameter4)) {
            sb.append("&channel=");
            sb.append(queryParameter4);
        }
        Bundle bundle = new Bundle();
        bundle.putString(com.huawei.health.messagecenter.model.CommonUtil.DETAIL_URI, sb.toString());
        bundle.putString("msgId", "123456");
        AppRouter.b("/PluginMessageCenter/DispatchSkipEventActivity").zF_(bundle).c(this.f2199a);
    }

    private void d(boolean z, boolean z2, LoginInit loginInit, PluginOperation pluginOperation) {
        if (z2 || z || loginInit.isKidAccount()) {
            p();
            r();
        } else {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            pluginOperation.startOperationWebPage(this.g, this.b);
        }
    }

    private boolean d(String str) {
        return !TextUtils.isEmpty(str) && "fa".equals(str);
    }

    private void p() {
        Observer observer = new Observer() { // from class: com.huawei.health.browseraction.HwHealthBrowserActionActivity.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                LogUtil.a("HwHealthBrowserActionActivity", "registerObservers, notify");
                HwHealthBrowserActionActivity.this.e();
                if (HwHealthBrowserActionActivity.this.h != null) {
                    ObserverManagerUtil.e(HwHealthBrowserActionActivity.this.h, "com.huawei.plugin.trigger.checklogin");
                }
                HwHealthBrowserActionActivity.this.h = null;
            }
        };
        this.h = observer;
        ObserverManagerUtil.d(observer, "com.huawei.plugin.trigger.checklogin");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LoginInit.getInstance(this.f2199a).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.health.browseraction.HwHealthBrowserActionActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                SharedPreferenceManager.e(HwHealthBrowserActionActivity.this.f2199a, String.valueOf(20000), "needLogin", String.valueOf(false), (StorageParams) null);
                LogUtil.h("HwHealthBrowserActionActivity", "onResponse errorCode = ", Integer.valueOf(i));
            }
        }, "");
    }

    private void t() {
        Uri parse = Uri.parse(this.g);
        String queryParameter = parse.getQueryParameter("productId");
        String queryParameter2 = parse.getQueryParameter("sn");
        if (!ceo.d().h(queryParameter2) || TextUtils.isEmpty(queryParameter)) {
            LogUtil.a("HwHealthBrowserActionActivity", "The device is not bound to jump to the home page.");
            AppRouter.b("/home/main").e(Constants.HOME_TAB_NAME, Constants.HOME).c(this);
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", queryParameter);
        contentValues.put("uniqueId", queryParameter2);
        new BleJsInteractionCompact().startH5Pro(BaseApplication.getContext(), "com.huawei.health.device." + queryParameter, contentValues, "#/type=3");
    }

    private void o() {
        LogUtil.a("HwHealthBrowserActionActivity", "jumpToThirdGprsDeviceAppByPush mQueryParameter: ", this.g);
        Uri parse = Uri.parse(this.g);
        String queryParameter = parse.getQueryParameter("productId");
        String queryParameter2 = parse.getQueryParameter("sn");
        if (!ceo.d().h(queryParameter2) || TextUtils.isEmpty(queryParameter)) {
            LogUtil.a("HwHealthBrowserActionActivity", "The device is not bound to jump to the home page.");
            AppRouter.b("/home/main").e(Constants.HOME_TAB_NAME, Constants.HOME).c(this);
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", queryParameter);
        contentValues.put("uniqueId", queryParameter2);
        Intent intent = new Intent();
        intent.putExtra("commonDeviceInfo", contentValues);
        intent.setPackage("com.huawei.health");
        intent.setClassName("com.huawei.health", "com.huawei.health.ecologydevice.ui.healthdata.activity.BloodSugarHistoryActivity");
        jdw.bGh_(intent, this);
    }

    private void m() {
        AppRouter.b("/TradeService/TradeOrderListActivity").c(BaseApplication.getContext());
    }

    private void s() {
        if (AuthorizationUtils.a(this.f2199a)) {
            c();
            if (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.j)) {
                return;
            }
            final Context context = BaseApplication.getContext();
            ixx.d().a(LoginInit.getInstance(context).getAccountInfo(1011));
            ixx.d().e(LoginInit.getInstance(context).getAccountInfo(1010));
            OpAnalyticsUtil.getInstance().init(context, new IBaseResponseCallback() { // from class: cbn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HwHealthBrowserActionActivity.this.b(context, i, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(Context context, int i, Object obj) {
        d(context);
        e(context);
        q();
    }

    private void d(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("from", this.b);
        hashMap.put("url", this.g);
        ixx.d().d(context, AnalyticsValue.HEALTH_BROWSER_1100044.value(), hashMap, 0);
    }

    private void e(Context context) {
        if (d(this.b)) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("click_enter_health_app", 1);
            hashMap.put("card_id", this.o.getQueryParameter("cardId"));
            hashMap.put("card_type", this.o.getQueryParameter("cardType"));
            ixx.d().d(context, AnalyticsValue.FA_ENTER_APP.value(), hashMap, 0);
            d();
        }
    }

    private void d() {
        LogUtil.a("HwHealthBrowserActionActivity", "doBiFromFaStartApp");
        HashMap hashMap = new HashMap(16);
        hashMap.put("from", 1);
        hashMap.put("click", 1);
        hashMap.put("FAPackageName", this.o.getQueryParameter("FAPackageName"));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_LOGIN_APP_WHITE_2050007.value(), hashMap, 0);
    }

    private void c() {
        gop.c(gop.aRd_(this.b, this.f, this.m, this.e, this.o), this.b, !TextUtils.isEmpty(this.i));
    }

    private void a() {
        PluginOperation pluginOperation = PluginOperation.getInstance(this.f2199a);
        pluginOperation.setAdapter(PluginOperationAdapterImpl.getInstance(this.f2199a));
        pluginOperation.init(this.f2199a);
    }

    private void n() {
        String queryParameter = Uri.parse(this.g).getQueryParameter("examinationId");
        if (TextUtils.isEmpty(queryParameter)) {
            queryParameter = "1";
        }
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).gotoH5HealthRecord(this.f2199a, queryParameter, "-1");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void finish() {
        setIntent(null);
        super.finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
