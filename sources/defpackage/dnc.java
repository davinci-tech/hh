package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.featuremarketing.route.IMarketRouteHelper;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.weight.WeightApi;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginoperation.util.DietKakaUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class dnc implements IMarketRouteHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f11726a;
    private IBaseResponseCallback c;

    public dnc(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.f11726a = str;
        this.c = iBaseResponseCallback;
    }

    @Override // com.huawei.health.featuremarketing.route.IMarketRouteHelper
    public void jumpActivity() {
        if (TextUtils.isEmpty(this.f11726a)) {
            LogUtil.h("WebViewRouterHelper", "mLinkUrl is null");
            IBaseResponseCallback iBaseResponseCallback = this.c;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        if (this.f11726a.contains("localeUrl:moduleName=openService")) {
            AppRouter.b("/OpenService/1.0/OpenServiceActivity").a(268435456).j();
            IBaseResponseCallback iBaseResponseCallback2 = this.c;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(0, null);
                return;
            }
            return;
        }
        String queryParameter = Uri.parse(this.f11726a).getQueryParameter("address");
        Uri parse = Uri.parse(nsa.g(this.f11726a));
        String queryParameter2 = parse.getQueryParameter("systemBrower");
        String queryParameter3 = parse.getQueryParameter("h5pro");
        String queryParameter4 = parse.getQueryParameter("thirdDevProdId");
        a(this.f11726a, queryParameter, queryParameter3);
        if (!TextUtils.isEmpty(queryParameter2)) {
            nsa.i(this.f11726a);
            IBaseResponseCallback iBaseResponseCallback3 = this.c;
            if (iBaseResponseCallback3 != null) {
                iBaseResponseCallback3.d(0, null);
                return;
            }
            return;
        }
        if ("true".equals(queryParameter3)) {
            if (this.f11726a.contains("thirdDevProdId")) {
                if (TextUtils.isEmpty(queryParameter4)) {
                    LogUtil.a("WebViewRouterHelper", "productId is null");
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("productId", queryParameter4);
                bzs.e().initThirdDeviceH5Pro(a(this.f11726a), contentValues, "#/measure/hw_authorized/?type=4");
                return;
            }
            bzs.e().initH5Pro();
            H5ProLaunchOption.Builder c = c(this.f11726a);
            WR_(parse, c);
            if (nsa.f(this.f11726a)) {
                LogUtil.a("WebViewRouterHelper", "mLinkUrl is url.");
                b(this.f11726a);
                H5ProClient.startH5LightApp(BaseApplication.e(), this.f11726a, c.build());
            } else if (nsa.f(queryParameter)) {
                LogUtil.a("WebViewRouterHelper", "mLinkUrl contains url.");
                H5ProClient.startH5LightApp(BaseApplication.e(), queryParameter, c.build());
            } else {
                LogUtil.a("WebViewRouterHelper", "mLinkUrl is h5 mini program.");
                b(c);
            }
            IBaseResponseCallback iBaseResponseCallback4 = this.c;
            if (iBaseResponseCallback4 != null) {
                iBaseResponseCallback4.d(0, null);
                return;
            }
            return;
        }
        d(this.f11726a);
    }

    private void b(final H5ProLaunchOption.Builder builder) {
        this.f11726a = a(this.f11726a);
        final Context e = BaseApplication.e();
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        String str = this.f11726a;
        if (str == null) {
            return;
        }
        if (str.contains("com.huawei.health.h5.fasting-lite")) {
            builder.setNeedSoftInputAdapter().addPath("#/guide_index");
            if (weightApi != null) {
                H5ProServiceManager.getInstance().registerService(weightApi.getFastingLiteRepositoryApi());
            } else {
                LogUtil.h("WebViewRouterHelper", "weightApi is null.");
            }
            H5ProClient.startH5MiniProgram(e, "com.huawei.health.h5.fasting-lite", builder.build());
            return;
        }
        if (this.f11726a.contains("com.huawei.health.h5.diet-diary")) {
            builder.setNeedSoftInputAdapter().addCustomizeJsModule("DietKakaUtil", DietKakaUtil.class);
            if (weightApi != null) {
                H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
            } else {
                LogUtil.h("WebViewRouterHelper", "weightApi is null.");
            }
            H5ProClient.startH5MiniProgram(e, "com.huawei.health.h5.diet-diary", builder.build());
            return;
        }
        if (this.f11726a.contains("com.huawei.health.h5.ecg") && !this.f11726a.contains("com.huawei.health.h5.ecgce")) {
            Class<? extends JsModuleBase> commonJsModule = ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getCommonJsModule("healthZoneApi");
            if (commonJsModule != null) {
                builder.addCustomizeJsModule("healthZoneApi", commonJsModule);
            } else {
                LogUtil.h("WebViewRouterHelper", "startH5ProActivity: healthZoneApi is null");
            }
            Class<? extends JsModuleBase> commonJsModule2 = bzs.e().getCommonJsModule("shareToCaasApi");
            if (commonJsModule2 != null) {
                builder.addCustomizeJsModule("shareToCaasApi", commonJsModule2);
            } else {
                LogUtil.h("WebViewRouterHelper", "startH5ProActivity: shareToCaasApi is null");
            }
            builder.setForceDarkMode(1);
            H5ProClient.startH5MiniProgram(e, "com.huawei.health.h5.ecg", builder.build());
            return;
        }
        if (this.f11726a.contains("com.huawei.health.h5.ecgce")) {
            bzs.e().loadH5ProApp(e, "com.huawei.health.h5.ecgce", builder);
            return;
        }
        if (this.f11726a.contains("com.huawei.health.h5.ppg")) {
            mpj.a().launchActivity(e, new Intent(), new AppBundleLauncher.InstallCallback() { // from class: dne
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return dnc.WQ_(e, builder, context, intent);
                }
            });
        } else if (this.f11726a.contains("com.huawei.health.h5.vascular-health")) {
            bzs.e().loadH5ProApp(e, "com.huawei.health.h5.vascular-health", builder);
        } else {
            H5ProClient.startH5MiniProgram(e, this.f11726a, builder.build());
        }
    }

    static /* synthetic */ boolean WQ_(Context context, H5ProLaunchOption.Builder builder, Context context2, Intent intent) {
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.ppg", builder);
        return false;
    }

    private void d(String str) {
        Intent createWebViewIntent = bzs.e().createWebViewIntent(BaseApplication.e(), new Bundle(), 268435456);
        if (createWebViewIntent != null) {
            createWebViewIntent.putExtra("url", str);
            BaseApplication.e().getApplicationContext().startActivity(createWebViewIntent);
            IBaseResponseCallback iBaseResponseCallback = this.c;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        LogUtil.b("WebViewRouterHelper", "startH5Activity intent is null");
        IBaseResponseCallback iBaseResponseCallback2 = this.c;
        if (iBaseResponseCallback2 != null) {
            iBaseResponseCallback2.d(-1, null);
        }
    }

    private static H5ProLaunchOption.Builder c(String str) {
        H5ProLaunchOption.Builder addCustomizeJsModule = new H5ProLaunchOption.Builder().addCustomizeJsModule("message", ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).getCommonJsModule("message")).addCustomizeJsModule("ecgJsModule", bzs.e().getCommonJsModule("ecgJsModule")).addCustomizeJsModule(RemoteMessageConst.NOTIFICATION, bzs.e().getCommonJsModule(RemoteMessageConst.NOTIFICATION)).addCustomizeJsModule("notificationAudioControl", bzs.e().getCommonJsModule("notificationAudioControl")).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("breathtrain", bzs.e().getCommonJsModule("breathtrain")).addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
        if (str.contains("isImmerse")) {
            addCustomizeJsModule.setImmerse();
        }
        if (str.contains("showStatusBar")) {
            addCustomizeJsModule.showStatusBar();
        }
        if (str.contains("statusBarTextBlack")) {
            addCustomizeJsModule.setStatusBarTextBlack(true);
        }
        addCustomizeJsModule.setAnim("none");
        return addCustomizeJsModule;
    }

    private static String a(String str) {
        int indexOf;
        return (!str.contains("?") || (indexOf = str.indexOf("?")) <= 0) ? str : str.substring(0, indexOf);
    }

    private static void b(String str) {
        LogUtil.a("WebViewRouterHelper", "interceptOperationMusic");
        String str2 = EnvironmentHelper.getInstance().getUrl() + "operationmusic" + File.separator;
        String replace = str2.replace("/sandbox", "");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith(str2) || str.startsWith(replace)) {
            ThreadPoolManager.d().d("WebViewRouterHelper", new Runnable() { // from class: dnc.3
                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.e(), "H5_OTHER_SHAREDPREFERENCE", "H5_PATCH_OPERATIONMUSIC_DEL_FLAG"))) {
                        LogUtil.a("WebViewRouterHelper", "interceptOperationMusic start clean dir.");
                        File file = new File(StorageUtil.getNativeFilePath(BaseApplication.e(), "operationmusic"));
                        if (file.exists()) {
                            FileUtils.e(file);
                        }
                        SharedPreferenceManager.e(BaseApplication.e(), "H5_OTHER_SHAREDPREFERENCE", "H5_PATCH_OPERATIONMUSIC_DEL_FLAG", "1", new StorageParams(0));
                    }
                }
            });
        }
    }

    private void a(String str, String str2, String str3) {
        LogUtil.a("WebViewRouterHelper", "putBiEventFromDeeplink");
        String e = e(str, str2, str3);
        if (TextUtils.isEmpty(e)) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("Steps", 1);
        hashMap.put("H5package", e);
        hashMap.put("H5url", str);
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.H5_RESOURCE_LOAD_EVENT_VALUE.value(), hashMap, 0);
    }

    private String e(String str, String str2, String str3) {
        if (nsa.f(str)) {
            return str;
        }
        if (nsa.f(str2)) {
            return str2;
        }
        if (!"true".equals(str3)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            return !TextUtils.isEmpty(str) ? a(str) : "";
        }
        return a(str2);
    }

    private void WR_(Uri uri, H5ProLaunchOption.Builder builder) {
        if (uri.isOpaque()) {
            return;
        }
        for (String str : uri.getQueryParameterNames()) {
            if (Analyzer.isGlobalParamKeys(str)) {
                String queryParameter = uri.getQueryParameter(str);
                if (!TextUtils.isEmpty(queryParameter)) {
                    builder.addGlobalBiParam(str, queryParameter);
                }
            }
        }
    }
}
