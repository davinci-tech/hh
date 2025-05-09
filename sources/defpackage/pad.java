package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitManager;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitOptions;
import com.huawei.maps.offlinedata.health.init.OnCreateResultListener;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.device.activity.alarm.AlarmActivity;
import com.huawei.ui.device.activity.appmarket.HwSmartAppMarketLoadingActivity;
import com.huawei.ui.device.activity.finddevice.FindDeviceActivity;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.activity.notificationlive.NotificationLiveActivity;
import com.huawei.ui.device.activity.selectcontact.ContactMainActivity;
import com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity;
import com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import com.huawei.ui.device.activity.weatherreport.WeatherReportActivity;
import com.huawei.ui.homewear21.home.MusicSecondaryMenuActivity;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.WearHomeHealthMonitoringActivity;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager;
import com.huawei.ui.main.stories.settings.activity.heartrate.StudentHeartRateActivity;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.openservice.db.control.OpenServiceControl;
import com.huawei.ui.openservice.db.model.OpenService;
import com.huawei.watchface.WatchFaceApi;
import defpackage.pad;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class pad {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f16017a;
    private HealthButton b;
    private HealthProgressBar c;
    private ImageView d;
    private View e;
    private HealthTextView f;
    private HealthButton g;
    private CustomAlertDialog h;
    private CustomAlertDialog i;
    private HealthTextView j;
    private CustomTextAlertDialog k;
    private CustomAlertDialog l;
    private CustomAlertDialog m;
    private WearHomeActivity n;
    private String o;
    private Context p;
    private CustomAlertDialog q;
    private CustomTextAlertDialog r;
    private LinearLayout s;
    private LinearLayout t;
    private List<OpenService> u;

    public pad(Context context, WearHomeActivity wearHomeActivity) {
        this.n = wearHomeActivity;
        this.p = context;
    }

    public void u() {
        Intent intent = new Intent();
        intent.putExtra("device_id", this.n.g);
        intent.setClass(this.p, WearHomeHealthMonitoringActivity.class);
        this.n.startActivity(intent);
        nsn.ai(this.p);
    }

    public void d() {
        Intent intent = new Intent(this.p, (Class<?>) NotificationSettingActivity.class);
        intent.putExtra("device_id", this.n.g);
        this.n.startActivity(intent);
        nsn.ai(this.p);
        oau.a(this.n.b);
    }

    public void d(final DeviceInfo deviceInfo, final OnCreateResultListener onCreateResultListener) {
        if (!CommonUtil.aa(this.p)) {
            nrh.b(this.p, R.string._2130841393_res_0x7f020f31);
            LogUtil.h("WearHomeFeatureAction", "Network not Available");
        } else {
            jdx.b(new Runnable() { // from class: ozz
                @Override // java.lang.Runnable
                public final void run() {
                    pad.e(DeviceInfo.this, onCreateResultListener);
                }
            });
        }
    }

    static /* synthetic */ void e(DeviceInfo deviceInfo, OnCreateResultListener onCreateResultListener) {
        H5proUtil.initH5pro();
        OfflineMapInitOptions offlineMapInitOptions = new OfflineMapInitOptions();
        offlineMapInitOptions.setContext(BaseApplication.vZ_());
        offlineMapInitOptions.setActivity(BaseApplication.wa_());
        offlineMapInitOptions.setDeviceName(deviceInfo.getDeviceName());
        offlineMapInitOptions.setUniqueDevice(deviceInfo.getDeviceUdid());
        offlineMapInitOptions.setH5proUrl(EnvironmentHelper.getInstance().getUrl());
        boolean createOfflineDataMap = OfflineMapInitManager.getInstance().createOfflineDataMap(offlineMapInitOptions, onCreateResultListener);
        ReleaseLogUtil.e("WearHomeFeatureAction", "jumpToOfflineMap enter ", Boolean.valueOf(createOfflineDataMap));
        if (createOfflineDataMap) {
            return;
        }
        sqo.ad("open map page error");
    }

    public void m() {
        HashMap hashMap = new HashMap(16);
        LogUtil.a("WearHomeFeatureAction", "gotoEsimCard");
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HOME_1010021.value(), hashMap, 0);
        nbq e = nbq.e(this.p);
        if (e != null) {
            if (this.n.f9644a != null && this.n.f9644a.isSupportNewEsim()) {
                LogUtil.a("WearHomeFeatureAction", "openEsimCard isSupportNewEsim : true");
                boolean c = cwi.c(jpt.a("WearHomeFeatureAction"), 148);
                LogUtil.a("WearHomeFeatureAction", "openEsimCard isSupportStandaloneNum = ", Boolean.valueOf(c));
                if (c) {
                    e.a(this.n);
                    return;
                } else {
                    e.b(this.n);
                    return;
                }
            }
            e.d(this.n);
        }
    }

    public void a() {
        this.n.x.execute(new Runnable() { // from class: pad.2
            @Override // java.lang.Runnable
            public void run() {
                pad.this.o = pex.a().d(pad.this.n.b);
                LogUtil.c("WearHomeFeatureAction", "initGrsUrl url is:", pad.this.o);
            }
        });
    }

    public void j() {
        oau.d(this.n.b);
        if (!TextUtils.isEmpty(this.o)) {
            LogUtil.a("WearHomeFeatureAction", "mBandUrl url is not empty");
            ad();
        } else {
            LogUtil.h("WearHomeFeatureAction", "openAppHelpActivity mBandUrl is null");
            this.n.x.execute(new Runnable() { // from class: pad.13
                @Override // java.lang.Runnable
                public void run() {
                    pad.this.o = pex.a().d(pad.this.n.b);
                    if (pad.this.n.djG_() != null) {
                        pad.this.n.djG_().post(new Runnable() { // from class: pad.13.3
                            @Override // java.lang.Runnable
                            public void run() {
                                pad.this.ad();
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        Intent intent = new Intent(this.p, (Class<?>) WebViewActivity.class);
        LogUtil.c("WearHomeFeatureAction", "openAppHelpActivity url is:", this.o);
        intent.putExtra("url", this.o);
        intent.putExtra(Constants.JUMP_MODE_KEY, 0);
        this.n.startActivity(intent);
    }

    public void e() {
        this.n.x.execute(new Runnable() { // from class: pad.15
            @Override // java.lang.Runnable
            public void run() {
                if (pad.this.n.b != null) {
                    oau.c(100107, pad.this.n.b.getDeviceName());
                }
                obb.d(pad.this.n, "11073");
            }
        });
    }

    public void l() {
        Intent intent = new Intent();
        intent.putExtra("device_id", this.n.g);
        intent.setClass(this.p, MusicSecondaryMenuActivity.class);
        this.n.startActivity(intent);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HOME_1090080.value(), hashMap);
    }

    public void k() {
        if (jiw.a().f()) {
            LogUtil.h("WearHomeFeatureAction", "openAppMarketView isDeviceVersionNotSupport");
            sqo.e("openAppMarketView isDeviceVersionNotSupport");
            ae();
        } else {
            jiw.a().e(this.n, new AppBundleLauncher.InstallCallback() { // from class: pad.14
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    jiw.a().j();
                    if (cwi.c(pad.this.n.b, 44)) {
                        try {
                            Intent intent2 = new Intent();
                            intent2.setClass(pad.this.p, HwSmartAppMarketLoadingActivity.class);
                            pad.this.n.startActivity(intent2);
                            return false;
                        } catch (ActivityNotFoundException unused) {
                            LogUtil.b("WearHomeFeatureAction", "openAppMarketView NotFoundException");
                            return false;
                        }
                    }
                    jiw.a().g();
                    return false;
                }
            });
            oau.a();
            ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HOME_101002042.value(), new HashMap(16), 0);
        }
    }

    private void ae() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.p).b(R.string._2130839506_res_0x7f0207d2).d(R$string.IDS_app_market_device_update).cyR_(R.string._2130841855_res_0x7f0210ff, new View.OnClickListener() { // from class: pad.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (pad.this.r != null) {
                    pad.this.r.dismiss();
                    pad.this.r = null;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130841856_res_0x7f021100, new View.OnClickListener() { // from class: pad.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pad.this.ah();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.r = a2;
        a2.setCancelable(false);
        if (this.r.isShowing() || this.n.isFinishing()) {
            return;
        }
        this.r.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        Intent intent = new Intent();
        intent.setClass(this.p, UpdateVersionActivity.class);
        intent.putExtra("device_id", this.n.g);
        this.n.startActivity(intent);
    }

    public void y() {
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HOME_1010018.value(), new HashMap(16), 0);
        if (WalletAppManager.getInstance().isOverseaService()) {
            WalletAppManager.getInstance().startNfcWalletH5();
            return;
        }
        pep.b(this.p);
        WearHomeActivity wearHomeActivity = this.n;
        pep.c(wearHomeActivity, wearHomeActivity.g);
    }

    public void v() {
        pep.b(this.p);
        pep.e(this.n);
    }

    public void r() {
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HOME_1010028.value(), new HashMap(16), 0);
        Intent intent = new Intent(this.p, (Class<?>) AlarmActivity.class);
        intent.putExtra("device_id", this.n.g);
        this.n.startActivity(intent);
    }

    public void z() {
        Intent intent = new Intent(this.p, (Class<?>) WeatherReportActivity.class);
        Bundle bundle = new Bundle();
        if (this.n.f9644a != null) {
            bundle.putParcelable("key_bundle_cache_capability", this.n.f9644a);
        } else {
            LogUtil.h("WearHomeFeatureAction", "mActivity.mDeviceCapability is null.");
        }
        intent.putExtra("key_cache_capability", bundle);
        this.n.startActivity(intent);
        nsn.ai(this.p);
        OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.HOME_1010030.value(), new LinkedHashMap<>(16));
    }

    public void q() {
        this.n.b(ContactMainActivity.class);
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.HOME_1010031.value(), new HashMap(16), 0);
    }

    public void t() {
        try {
            Intent intent = new Intent(this.p, (Class<?>) SmsQuickReplyActivity.class);
            intent.putExtra("device_id", this.n.g);
            this.p.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeFeatureAction", "ActivityNotFoundException SmsQuickReplyActivity");
        }
    }

    public void s() {
        this.n.b(StudentHeartRateActivity.class);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.CLICK_HEART_RATE_FAMILY_MODE.value(), hashMap, 0);
    }

    public void x() {
        try {
            Intent intent = new Intent();
            intent.setPackage(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage());
            intent.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.me.activity.StudentInfoActivity");
            this.p.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeFeatureAction", "ActivityNotFoundException STUDENT_INFO_ACTIVITY_NAME");
        }
        oau.c();
    }

    public void i() {
        try {
            if (jdm.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "com.huawei.study.hiresearch")) {
                nsn.cLM_(this.n.getPackageManager().getLaunchIntentForPackage("com.huawei.study.hiresearch"), "com.huawei.study.hiresearch", this.n, this.n.getString(R.string._2130847783_res_0x7f022827).replace("\n", ""));
            } else {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.huawei.study.hiresearch"));
                intent.addFlags(268435456);
                dkc_(intent, "/#/app/C102552669", "");
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeFeatureAction", "jumpToHiResearchHealth ActivityNotFoundException");
        }
    }

    public void a(boolean z) {
        try {
            if (z) {
                LogUtil.a("WearHomeFeatureAction", "isInstalledBloodSugar");
                Context context = this.p;
                if (context == null) {
                    LogUtil.h("WearHomeFeatureAction", "jumpToContinueBloodSugar mContext is null");
                    return;
                } else {
                    HealthDataDetailActivity.a(context, "BloodSugarCardConstructor", 8);
                    return;
                }
            }
            ab();
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeFeatureAction", "Exception ActivityNotFoundException");
        }
    }

    private void ab() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.n);
        builder.d(R.string.IDS_device_blood_sugar_con).b(R.string._2130839506_res_0x7f0207d2).cyU_(R.string._2130841237_res_0x7f020e95, new View.OnClickListener() { // from class: pad.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("WearHomeFeatureAction", "cancel showEcgDurationDialog");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.k = a2;
        a2.show();
    }

    private void dkc_(Intent intent, String str, String str2) {
        if (jdm.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "com.huawei.appmarket")) {
            intent.setPackage("com.huawei.appmarket");
            WearHomeActivity wearHomeActivity = this.n;
            nsn.cLM_(intent, "com.huawei.appmarket", wearHomeActivity, wearHomeActivity.getString(R.string.IDS_device_fragment_application_market));
            return;
        }
        Intent intent2 = new Intent();
        intent2.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent2.setData(Uri.parse(pbu.e() + str + str2));
        PackageManager packageManager = this.n.getPackageManager();
        if (packageManager != null) {
            LogUtil.a("WearHomeFeatureAction", "checkIsInstalledMarket packageManager is not null");
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent2, 65536);
            if (resolveActivity != null) {
                LogUtil.a("WearHomeFeatureAction", "checkIsInstalledMarket resolveInfo is not null");
                ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
                intent2.setComponent(componentName);
                String packageName = componentName.getPackageName();
                WearHomeActivity wearHomeActivity2 = this.n;
                nsn.cLM_(intent2, packageName, wearHomeActivity2, wearHomeActivity2.getString(R.string._2130847432_res_0x7f0226c8));
            }
        }
    }

    public void h() {
        LogUtil.a("WearHomeFeatureAction", "enter jumpToQuickAppEcg");
        mxv.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "com.huawei.health.ecg.collection", 3, null);
    }

    public void f() {
        LogUtil.a("WearHomeFeatureAction", "enter jumpToEcgAnalysis");
        nsd.e(false);
        mxv.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "com.huawei.health.h5.ecgce", 3, null);
    }

    public void g() {
        LogUtil.a("WearHomeFeatureAction", "enter jumpToVascularElasticityTest");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/?from=3");
        bzs.e().loadH5ProApp(this.n, "com.huawei.health.h5.vascular-health", builder);
    }

    public void c() {
        this.u = new ArrayList(10);
        List<OpenService> queryAllService = OpenServiceControl.getInstance(this.n).queryAllService();
        if (koq.c(queryAllService)) {
            LogUtil.a("WearHomeFeatureAction", "initOpenService: mServiceList size is ", Integer.valueOf(queryAllService.size()));
            for (OpenService openService : queryAllService) {
                String serviceID = openService.getServiceID();
                if (serviceID == null) {
                    LogUtil.h("WearHomeFeatureAction", "initOpenService: serviceId is null");
                } else if (serviceID.contains("xinqing")) {
                    this.u.add(openService);
                } else {
                    LogUtil.a("WearHomeFeatureAction", "initOpenService: serviceId ", serviceID);
                }
            }
        }
        OpenService.orderOpenService(this.u);
        LogUtil.a("WearHomeFeatureAction", "mServiceList selected size is ", Integer.valueOf(this.u.size()));
        if (cwi.c(this.n.b, 112)) {
            bkt.e();
        }
    }

    public void n() {
        LogUtil.a("WearHomeFeatureAction", "openHeartIndexActivity: start xinqing service");
        String url = GRSManager.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getUrl("healthRecommendUrl");
        LogUtil.c("WearHomeFeatureAction", "openHeartIndexActivity: openHeartIndexActivity recommendHost = ", url);
        if (this.u.size() > 0) {
            c(this.u.get(0));
            return;
        }
        if (!TextUtils.isEmpty(url)) {
            LogUtil.a("WearHomeFeatureAction", "openHeartIndexActivity: xinqing service is null , create OpenService");
            OpenService openService = new OpenService();
            openService.setServiceUrl(url + "/heartIndex/index.html");
            openService.setServiceID("xinqing");
            openService.setProductName(this.n.getString(R.string._2130842596_res_0x7f0213e4));
            c(openService);
            return;
        }
        LogUtil.h("WearHomeFeatureAction", "openHeartIndexActivity: recommendHost is empty");
    }

    private void c(OpenService openService) {
        Intent intent = new Intent(this.n, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", openService.getServiceUrl());
        intent.putExtra("EXTRA_BI_ID", openService.getServiceID());
        intent.putExtra("EXTRA_BI_NAME", openService.getProductName());
        intent.putExtra("EXTRA_BI_SOURCE", "HeadSetManager");
        intent.putExtra(Constants.IS_START_FROM_HEART_RATE_BOARD, true);
        intent.putExtra("type", Constants.OPEN_SERVICE_TYPE);
        this.n.startActivity(intent);
        oau.c(openService.getServiceID(), openService.getProductName());
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("WearHomeFeatureAction", "Entering startFindDeviceActivity");
        Intent intent = new Intent(this.n, (Class<?>) FindDeviceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("device", deviceInfo);
        intent.putExtras(bundle);
        this.n.startActivity(intent);
        pep.d(this.n);
    }

    public void e(DeviceInfo deviceInfo) {
        LogUtil.a("WearHomeFeatureAction", "Entering startSetWheelSizeActivity");
        Intent intent = new Intent(this.n, (Class<?>) SetWheelSizeActivity.class);
        intent.putExtra("rimSize", jnr.b().c(this.n.b));
        intent.putExtra("device", deviceInfo);
        LogUtil.a("rimSize device " + deviceInfo, new Object[0]);
        this.n.startActivity(intent);
        pep.d(this.n);
    }

    public void c(String str) {
        LogUtil.a("WearHomeFeatureAction", "startExclusiveGuardian status = ", str);
        if ("1".equals(str)) {
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addPath("#/ExclusiveGuard?from=2");
            bzs.e().loadH5ProApp(this.n, "com.huawei.health.h5.vip", builder);
        } else {
            if ("2".equals(str) || "3".equals(str)) {
                H5ProLaunchOption.Builder builder2 = new H5ProLaunchOption.Builder();
                builder2.addPath("#/ExclusiveGuardService?from=3");
                bzs.e().loadH5ProApp(this.n, "com.huawei.health.h5.vip", builder2);
                return;
            }
            LogUtil.h("WearHomeFeatureAction", "startExclusiveGuardian else branch");
        }
    }

    public void b(final cuz cuzVar) {
        LogUtil.a("WearHomeFeatureAction", "startHeadsetHealth enter");
        String j = jfu.j(this.n.f);
        if (TextUtils.isEmpty(j)) {
            LogUtil.b("WearHomeFeatureAction", "startHeadsetHealth uuid is null!");
            return;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.b("WearHomeFeatureAction", "startHeadsetHealth descriptionInfo or getWearDeviceInfo is null!");
            return;
        }
        final String an = pluginInfoByUuid.f().an();
        LogUtil.a("WearHomeFeatureAction", "startHeadsetHealth getEarPhoneManagerUri :", an);
        if (TextUtils.isEmpty(an)) {
            return;
        }
        this.n.runOnUiThread(new Runnable() { // from class: pad.17
            @Override // java.lang.Runnable
            public void run() {
                bzt.c().initAudioDeviceKitAdapter(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
                Intent intent = new Intent();
                intent.setData(Uri.parse(an));
                Bundle bundle = new Bundle();
                bundle.putString("mac", cuzVar.c());
                bundle.putString("deviceName", cuzVar.b());
                bundle.putString("productId", "ZAAA");
                intent.putExtras(bundle);
                LogUtil.a("WearHomeFeatureAction", "startEarphoneHealth detail page. ");
                bzt.c().launchActivity(pad.this.p, intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final cuz cuzVar) {
        LogUtil.a("WearHomeFeatureAction", "startFindEarphonePage enter");
        String j = jfu.j(this.n.f);
        if (TextUtils.isEmpty(j)) {
            LogUtil.b("WearHomeFeatureAction", "startFindEarphonePage uuid is null!");
            return;
        }
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            LogUtil.b("WearHomeFeatureAction", "startFindEarphonePage descriptionInfo or getWearDeviceInfo is null!");
            return;
        }
        final String al = pluginInfoByUuid.f().al();
        LogUtil.a("WearHomeFeatureAction", "startFindEarphonePage getEarPhoneFindrUri :", al);
        if (TextUtils.isEmpty(al)) {
            return;
        }
        this.n.runOnUiThread(new Runnable() { // from class: pad.16
            @Override // java.lang.Runnable
            public void run() {
                bzt.c().initAudioDeviceKitAdapter(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
                Intent intent = new Intent();
                intent.setData(Uri.parse(al));
                Bundle bundle = new Bundle();
                bundle.putString("mac", cuzVar.c());
                bundle.putString("deviceName", cuzVar.b());
                bundle.putString("productId", "ZAAA");
                intent.putExtras(bundle);
                LogUtil.a("WearHomeFeatureAction", "startFindEarphonePage");
                bzt.c().launchActivity(pad.this.p, intent);
            }
        });
    }

    public boolean b() {
        CustomAlertDialog customAlertDialog;
        CustomAlertDialog customAlertDialog2;
        CustomAlertDialog customAlertDialog3;
        CustomAlertDialog customAlertDialog4 = this.i;
        return (customAlertDialog4 != null && customAlertDialog4.isShowing()) || ((customAlertDialog = this.m) != null && customAlertDialog.isShowing()) || (((customAlertDialog2 = this.l) != null && customAlertDialog2.isShowing()) || ((customAlertDialog3 = this.h) != null && customAlertDialog3.isShowing()));
    }

    public void p() {
        LogUtil.a("WearHomeFeatureAction", "startArrhythmia");
        final H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/?from=2");
        mpj.a().launchActivity(this.n, new Intent(), new AppBundleLauncher.InstallCallback() { // from class: paa
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return pad.this.dkd_(builder, context, intent);
            }
        });
    }

    /* synthetic */ boolean dkd_(H5ProLaunchOption.Builder builder, Context context, Intent intent) {
        bzs.e().loadH5ProApp(this.n, "com.huawei.health.h5.ppg", builder);
        return false;
    }

    public void o() {
        ReleaseLogUtil.e("WearHomeFeatureAction", "openMyWatchFace");
        WatchFaceApi watchFaceApi = (WatchFaceApi) Services.a("WatchFaceApiManager", WatchFaceApi.class);
        if (watchFaceApi != null) {
            watchFaceApi.openMyWatchFace(new IBaseResponseCallback() { // from class: pad.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0 && (obj instanceof H5ProLaunchOption.Builder)) {
                        H5ProClient.startH5MiniProgram(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), "com.huawei.health.h5.watchface", ((H5ProLaunchOption.Builder) obj).build());
                        jqh.c("e2");
                    }
                }
            });
        }
    }

    public void b(int i) {
        CustomAlertDialog customAlertDialog = this.m;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
            d(this.n.b, i);
        }
    }

    public void b(final DeviceInfo deviceInfo, final int i, final boolean z) {
        LogUtil.a("WearHomeFeatureAction", "checkEarphoneState in. ");
        aa();
        jgs.c().c(deviceInfo, new EarPhoneResponseCallback() { // from class: pad.4
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
            public void onResponse(int i2, cuz cuzVar) {
                if (cuzVar == null || TextUtils.isEmpty(cuzVar.c())) {
                    pad.this.af();
                    return;
                }
                if (!jgs.c().b(cuzVar.c())) {
                    pad.this.c(cuzVar, z, deviceInfo, i);
                    return;
                }
                LogUtil.a("WearHomeFeatureAction", "checkEarphoneState in. type: ", Integer.valueOf(i));
                int i3 = i;
                if (i3 == 103) {
                    pad.this.b(cuzVar);
                } else {
                    if (i3 != 113) {
                        return;
                    }
                    pad.this.c(cuzVar);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(cuz cuzVar, boolean z, DeviceInfo deviceInfo, int i) {
        if (!e(cuzVar)) {
            af();
            return;
        }
        if (!d(cuzVar)) {
            d(deviceInfo, i);
        } else if (z) {
            b(deviceInfo, i);
        } else {
            a(deviceInfo, cuzVar, i);
        }
    }

    private boolean e(cuz cuzVar) {
        return (cuzVar.d() & 1) > 0;
    }

    private boolean d(cuz cuzVar) {
        return (cuzVar.e() == 1) && (cuzVar.a() != 0);
    }

    private void aa() {
        View inflate = View.inflate(this.p, R.layout.wear_home_earphone_dialog_layout, null);
        this.e = inflate;
        HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.earphone_pair_dialog_loading_image);
        this.c = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        ImageView imageView = (ImageView) this.e.findViewById(R.id.earphone_pair_dialog_image);
        this.d = imageView;
        imageView.setBackgroundResource(R.drawable._2131430621_res_0x7f0b0cdd);
        this.f = (HealthTextView) this.e.findViewById(R.id.earphone_pair_dialog_title);
        this.j = (HealthTextView) this.e.findViewById(R.id.earphone_pair_dialog_tip);
        this.f16017a = (LinearLayout) this.e.findViewById(R.id.earphone_dialog_btn_layout);
        this.g = (HealthButton) this.e.findViewById(R.id.earphone_dialog_btn_positive);
        this.b = (HealthButton) this.e.findViewById(R.id.earphone_dialog_btn_negative);
    }

    /* renamed from: pad$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ DeviceInfo b;
        final /* synthetic */ int c;
        final /* synthetic */ cuz d;

        AnonymousClass3(cuz cuzVar, DeviceInfo deviceInfo, int i) {
            this.d = cuzVar;
            this.b = deviceInfo;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            pad.this.c.setVisibility(8);
            pad.this.f.setVisibility(0);
            pad.this.f.setText(this.d.b());
            pad.this.j.setVisibility(8);
            pad.this.g.setVisibility(8);
            pad.this.b.setVisibility(8);
            pad.this.f16017a.setVisibility(8);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(pad.this.p);
            if (pad.this.i != null) {
                pad.this.i.dismiss();
            }
            pad.this.i = builder.c();
            if (pad.this.e.getParent() != null) {
                ViewParent parent = pad.this.e.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(pad.this.e);
                }
            }
            builder.cyp_(pad.this.e);
            final DeviceInfo deviceInfo = this.b;
            final int i = this.c;
            builder.cyo_(R.string._2130841388_res_0x7f020f2c, new DialogInterface.OnClickListener() { // from class: pac
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    pad.AnonymousClass3.this.dkf_(deviceInfo, i, dialogInterface, i2);
                }
            });
            builder.cyn_(R.string._2130841939_res_0x7f021153, new DialogInterface.OnClickListener() { // from class: pab
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    pad.AnonymousClass3.dke_(dialogInterface, i2);
                }
            });
            pad.this.i.setCancelable(false);
            pad.this.i.show();
        }

        /* synthetic */ void dkf_(DeviceInfo deviceInfo, int i, DialogInterface dialogInterface, int i2) {
            pad.this.i.dismiss();
            pad.this.b(deviceInfo, i);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
        }

        static /* synthetic */ void dke_(DialogInterface dialogInterface, int i) {
            LogUtil.h("WearHomeFeatureAction", "onClick showEarphonePairDialog NegativeButton");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    private void a(DeviceInfo deviceInfo, cuz cuzVar, int i) {
        LogUtil.a("WearHomeFeatureAction", "showEarphonePairDialog. ");
        this.n.runOnUiThread(new AnonymousClass3(cuzVar, deviceInfo, i));
    }

    /* renamed from: pad$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f16018a;
        final /* synthetic */ DeviceInfo c;

        AnonymousClass1(int i, DeviceInfo deviceInfo) {
            this.f16018a = i;
            this.c = deviceInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            pad.this.c.setVisibility(0);
            pad.this.f.setVisibility(0);
            pad.this.j.setVisibility(8);
            pad.this.g.setVisibility(8);
            pad.this.b.setVisibility(8);
            pad.this.f16017a.setVisibility(8);
            pad.this.f.setText(R.string._2130841387_res_0x7f020f2b);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(pad.this.p);
            if (pad.this.m != null) {
                pad.this.m.dismiss();
            }
            pad.this.m = builder.c();
            if (pad.this.e.getParent() != null) {
                ViewParent parent = pad.this.e.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(pad.this.e);
                }
            }
            builder.cyp_(pad.this.e);
            builder.cyn_(R.string._2130846096_res_0x7f022190, new DialogInterface.OnClickListener() { // from class: paf
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    pad.AnonymousClass1.this.dkg_(dialogInterface, i);
                }
            });
            pad.this.m.setCancelable(false);
            pad.this.m.show();
            if (pad.this.n != null && pad.this.n.djG_() != null) {
                Message obtainMessage = pad.this.n.djG_().obtainMessage();
                obtainMessage.what = 1040;
                obtainMessage.obj = Integer.valueOf(this.f16018a);
                pad.this.n.djG_().sendMessageDelayed(obtainMessage, 60000L);
            }
            jgs.c().b(this.c, new EarPhoneResponseCallback() { // from class: pad.1.1
                @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
                public void onResponse(int i, cuz cuzVar) {
                    pad.this.d(i, cuzVar, AnonymousClass1.this.f16018a, AnonymousClass1.this.c);
                }
            });
        }

        /* synthetic */ void dkg_(DialogInterface dialogInterface, int i) {
            LogUtil.h("WearHomeFeatureAction", "onClick showEarphonePairingDialog NegativeButton");
            pad.this.m.dismiss();
            pad.this.m = null;
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, int i) {
        LogUtil.a("WearHomeFeatureAction", "showEarphonePairingDialog. ");
        this.n.runOnUiThread(new AnonymousClass1(i, deviceInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, cuz cuzVar, int i2, DeviceInfo deviceInfo) {
        WearHomeActivity wearHomeActivity = this.n;
        if (wearHomeActivity != null && wearHomeActivity.djG_() != null) {
            this.n.djG_().removeMessages(1040);
        }
        CustomAlertDialog customAlertDialog = this.m;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            LogUtil.h("WearHomeFeatureAction", "EarphonePairingDialog is not showing. ");
            return;
        }
        this.m.dismiss();
        this.m = null;
        if (i == 1) {
            LogUtil.a("WearHomeFeatureAction", "earphonePairCallBack in. type: ", Integer.valueOf(i2));
            if (i2 == 103) {
                b(cuzVar);
                return;
            } else {
                if (i2 != 113) {
                    return;
                }
                c(cuzVar);
                return;
            }
        }
        d(deviceInfo, i2);
    }

    /* renamed from: pad$8, reason: invalid class name */
    class AnonymousClass8 implements Runnable {
        final /* synthetic */ int c;
        final /* synthetic */ DeviceInfo e;

        AnonymousClass8(DeviceInfo deviceInfo, int i) {
            this.e = deviceInfo;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            pad.this.ac();
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(pad.this.p);
            pad.this.l = builder.c();
            if (pad.this.e.getParent() != null) {
                ViewParent parent = pad.this.e.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(pad.this.e);
                }
            }
            builder.cyp_(pad.this.e);
            pad.this.g.setOnClickListener(new View.OnClickListener() { // from class: pad.8.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.h("WearHomeFeatureAction", "onClick showEarphonePairFailedDialog PositiveButton");
                    pad.this.l.dismiss();
                    jgs.c().c(AnonymousClass8.this.e, new EarPhoneResponseCallback() { // from class: pad.8.5.5
                        @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
                        public void onResponse(int i, cuz cuzVar) {
                            pad.this.c(cuzVar, true, AnonymousClass8.this.e, AnonymousClass8.this.c);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            pad.this.b.setOnClickListener(new View.OnClickListener() { // from class: pad.8.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.h("WearHomeFeatureAction", "onClick showEarphonePairFailedDialog NegativeButton");
                    pad.this.l.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            pad.this.l.setCancelable(false);
            pad.this.l.show();
        }
    }

    private void d(DeviceInfo deviceInfo, int i) {
        LogUtil.a("WearHomeFeatureAction", "showEarphonePairFailedDialog. ");
        this.n.runOnUiThread(new AnonymousClass8(deviceInfo, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        this.g = (HealthButton) this.e.findViewById(R.id.earphone_dialog_btn_positive);
        this.b = (HealthButton) this.e.findViewById(R.id.earphone_dialog_btn_negative);
        this.f16017a.setVisibility(0);
        this.f16017a.setOrientation(0);
        if (LanguageUtil.aw(this.p)) {
            LogUtil.a("WearHomeFeatureAction", "language444 in:");
            this.g = (HealthButton) this.e.findViewById(R.id.earphone_dialog_btn_negative);
            this.b = (HealthButton) this.e.findViewById(R.id.earphone_dialog_btn_positive);
            this.f16017a.setOrientation(1);
            this.f16017a.setShowDividers(0);
        }
        this.c.setVisibility(8);
        this.f.setVisibility(0);
        this.j.setVisibility(0);
        this.f.setText(R.string._2130845793_res_0x7f022061);
        this.j.setText(R.string._2130846093_res_0x7f02218d);
        this.g.setText(R.string._2130843255_res_0x7f021677);
        this.b.setText(R.string._2130841939_res_0x7f021153);
        this.g.setVisibility(0);
        this.b.setVisibility(0);
    }

    /* renamed from: pad$7, reason: invalid class name */
    class AnonymousClass7 implements Runnable {
        AnonymousClass7() {
        }

        @Override // java.lang.Runnable
        public void run() {
            pad.this.c.setVisibility(8);
            pad.this.f.setVisibility(0);
            pad.this.j.setVisibility(0);
            pad.this.g.setVisibility(8);
            pad.this.b.setVisibility(8);
            pad.this.f16017a.setVisibility(8);
            pad.this.f.setText(R.string._2130846095_res_0x7f02218f);
            pad.this.j.setText(R.string._2130846094_res_0x7f02218e);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(pad.this.p);
            if (pad.this.e.getParent() != null) {
                ViewParent parent = pad.this.e.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(pad.this.e);
                }
            }
            builder.cyp_(pad.this.e);
            builder.cyn_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: pah
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    pad.AnonymousClass7.dkh_(dialogInterface, i);
                }
            });
            pad.this.h = builder.c();
            pad.this.h.setCancelable(false);
            pad.this.h.show();
        }

        static /* synthetic */ void dkh_(DialogInterface dialogInterface, int i) {
            LogUtil.h("WearHomeFeatureAction", "onClick NegativeButton");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        LogUtil.a("WearHomeFeatureAction", "showEarphoneNotFoundDialog. ");
        this.n.runOnUiThread(new AnonymousClass7());
    }

    public void b(final DeviceInfo deviceInfo) {
        CustomAlertDialog customAlertDialog = this.q;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            this.q.dismiss();
        }
        View inflate = View.inflate(this.p, R.layout.find_device_dialog_layout, null);
        this.s = (LinearLayout) inflate.findViewById(R.id.find_watch_layout);
        this.t = (LinearLayout) inflate.findViewById(R.id.find_earphone_layout);
        this.s.setOnClickListener(new View.OnClickListener() { // from class: pad.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pad.this.d(deviceInfo);
                pad.this.q.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: pad.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pad.this.b(deviceInfo, 113, false);
                pad.this.q.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.p);
        builder.a("");
        builder.cyp_(inflate);
        builder.cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: pad.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                pad.this.q.dismiss();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        this.q = c;
        c.setCancelable(false);
        this.q.show();
    }

    public void w() {
        boolean c = cwi.c(this.n.b, FitnessSleepType.HW_FITNESS_NOON);
        LogUtil.a("WearHomeFeatureAction", "isSupportLiveView", Boolean.valueOf(c));
        Intent intent = new Intent(this.p, (Class<?>) NotificationLiveActivity.class);
        intent.putExtra("device_id", this.n.g);
        intent.putExtra("LiveViewType", c);
        gnm.aPB_(this.n, intent);
    }
}
