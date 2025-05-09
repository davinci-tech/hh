package com.huawei.operation.h5pro;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.huawei.common.util.Utils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.bloodpressure.BloodPressureApi;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.ext.H5ProResidentExtManager;
import com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.jsbridge.system.network.NetWorkEntry;
import com.huawei.health.h5pro.jsbridge.system.share.ShareEntry;
import com.huawei.health.h5pro.load.expression.ExpressionFunctionRegistry;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogPrintOperate;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.health.healthheadlines.HealthHeadLinesJsApi;
import com.huawei.health.heartratesetting.HeartRateSettingApi;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pressure.PressureApi;
import com.huawei.health.recognizekit.h5.RecognizeH5Helper;
import com.huawei.health.sleep.SleepApi;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.weight.WeightApi;
import com.huawei.healthcloud.plugintrack.golf.h5.GolfH5DownloadInterface;
import com.huawei.healthcloud.plugintrack.golf.h5.GolfH5NativeInterface;
import com.huawei.healthcloud.plugintrack.physicalfitness.h5.PhysicalFitnessH5Interface;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.operation.h5pro.bridgeimpl.PermissionImpl;
import com.huawei.operation.h5pro.bridgeimpl.ShareImpl;
import com.huawei.operation.h5pro.jsmodules.ArkUIJsApi;
import com.huawei.operation.h5pro.jsmodules.Device;
import com.huawei.operation.h5pro.jsmodules.JsInteractionCompact;
import com.huawei.operation.h5pro.jsmodules.WeChatJsApi;
import com.huawei.operation.h5pro.jsmodules.WeightJsApi;
import com.huawei.operation.h5pro.jsmodules.common.HealthCommonApi;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintJsApi;
import com.huawei.operation.h5pro.jsmodules.crypto.CryptoApi;
import com.huawei.operation.h5pro.jsmodules.device.BasePairManagerJsApi;
import com.huawei.operation.h5pro.jsmodules.device.DeviceResourceJsApi;
import com.huawei.operation.h5pro.jsmodules.device.FileServiceJsApi;
import com.huawei.operation.h5pro.jsmodules.device.MessageNotificationJsApi;
import com.huawei.operation.h5pro.jsmodules.device.UpdateDeviceJsApi;
import com.huawei.operation.h5pro.jsmodules.dialog.DialogApi;
import com.huawei.operation.h5pro.jsmodules.healthengine.autoLoginH5.AutoLoginH5Entry;
import com.huawei.operation.h5pro.jsmodules.privacy.PrivacyCollectionJsApi;
import com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi;
import com.huawei.operation.h5pro.preload.H5PreloadWeightStrategy;
import com.huawei.operation.h5pro.preload.H5ProAppLoadStorageServiceImpl;
import com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask;
import com.huawei.operation.h5pro.prerequest.CloudFunctions;
import com.huawei.operation.h5pro.versionmanage.H5ProVersionManager;
import com.huawei.operation.h5pro.versionmanage.H5ProVersionUpgradeExitImpl;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.pluginoperation.util.DietKakaUtil;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.cun;
import defpackage.dsl;
import defpackage.gxf;
import defpackage.hyk;
import defpackage.ife;
import defpackage.ixx;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BuildTypeConfig;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class H5proUtil {
    private static final long HOME_PRELOAD_DELAY = 3000;
    private static final String OAuth_Login = "oauth2/v3/authorize.html";
    private static final String PARAM_PATH = "path";
    private static final int STEP_H5_DEEPLINK_START = 1;
    private static final String TAG = "H5PRO_H5proUtil";
    private static final String URL_BETA_PATH = "/sandbox/cch5/health/";
    private static final String URL_RELEASE_PATH = "/cch5/health/";
    private static final String URL_TEST_PATH = "/sandbox/cch5/testappCCH5/";
    private static final List<String> GLOBAL_BI_PARAM_KEYS = Arrays.asList(WebViewHelp.BI_KEY_PULL_FROM, "fromPageTitle", "resourceName", "resourceId", "pullOrder", "itemId", "algId", x2.AB_INFO);
    private static boolean sHasInit = false;
    private static final int GROUP_BACKGROUND_COLOR = Color.rgb(241, 243, 245);
    private static final String[] HOME_PRELOAD_H5_PKG_ARRAY = {"com.huawei.health.h5.weight"};
    private static boolean isOpenH5VersionGraySwitch = true;

    private H5proUtil() {
    }

    public static void initH5pro() {
        initH5pro("");
    }

    public static void initH5pro(String str) {
        initBiAnalyser();
        initJsSdk();
        initUserFlag();
        if (TextUtils.isEmpty(EnvironmentHelper.getInstance().getUrl())) {
            initEnvironment();
        }
        if (sHasInit) {
            return;
        }
        sHasInit = true;
        LogPrintOperate.setLogDecodePrintInterceptor(new LogDecodePrintInterceptorImpl());
        H5ProTrustListChecker.setTrustListChecker(new TrustListCheckerImpl());
        ife.d(BaseApplication.getAppPackage());
        initJsBridgeModule();
        initSalableServices();
        initBiAnalyserOnce();
        NetWorkEntry.setDataTransferInterceptor(new DataTransferInterceptorImpl());
        ExpressionFunctionRegistry.register("CloudFunctions", new CloudFunctions());
    }

    private static void initJsSdk() {
        String str;
        H5ProClient.setAccountGrsAppName(AccountConstants.GRS_APP_NAME);
        boolean z = CommonUtil.z(BaseApplication.getContext());
        if (z) {
            Utils.initGrsOld(Utils.getCountryCode(), BaseApplication.getContext());
            str = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        } else {
            str = "";
        }
        H5ProClient.useHmsLite(z, str);
    }

    private static void initUserFlag() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo) && accountInfo.length() >= 6) {
            H5ProClient.setUserFlag(accountInfo.substring(accountInfo.length() - 6));
        } else {
            LogUtil.b(TAG, "initUserFlag -> huid: empty or length < 6");
        }
    }

    public static void jumpFromDeeplink(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "jumpFromDeeplink: url is empty");
            return;
        }
        if (TrustListCheckerImpl.containsXss(str)) {
            LogUtil.h(TAG, "jumpFromDeeplink: url cannot contain Xss");
            return;
        }
        if (context == null) {
            context = BaseApplication.getContext();
        }
        if (context == null) {
            LogUtil.h(TAG, "jumpFromDeeplink: context is null");
            return;
        }
        H5ProLaunchOption.Builder createLaunchOptionBuilderForDeeplink = createLaunchOptionBuilderForDeeplink(str);
        if (str.startsWith("http://") || str.startsWith("https://")) {
            LogUtil.a(TAG, "jumpFromDeeplink: the url is http(s)");
            startH5WithHttp(context, str, createLaunchOptionBuilderForDeeplink);
        } else {
            if (str.startsWith(Constants.PREFIX_FILE)) {
                LogUtil.a(TAG, "jumpFromDeeplink: the url is FILE_SCHEME");
                startH5WithFile(context, str, createLaunchOptionBuilderForDeeplink);
                return;
            }
            LogUtil.a(TAG, "jumpFromDeeplink: the url is h5 mini program");
            String packageNameFromUrl = getPackageNameFromUrl(str);
            addInternalApi(true, packageNameFromUrl, createLaunchOptionBuilderForDeeplink);
            addParam(createLaunchOptionBuilderForDeeplink, str);
            bzs.e().loadH5ProApp(context, packageNameFromUrl, createLaunchOptionBuilderForDeeplink);
        }
    }

    private static void addInternalApi(boolean z, String str, H5ProLaunchOption.Builder builder) {
        if (TrustListCheckerImpl.isMountSpecialJsModule(z, str)) {
            builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        }
    }

    private static void startH5WithHttp(Context context, String str, H5ProLaunchOption.Builder builder) {
        PluginBaseAdapter adapter = PluginOperation.getInstance(context).getAdapter();
        if (adapter instanceof PluginOperationAdapter) {
            if (!com.huawei.operation.utils.Utils.isWhiteUrlLogicJudge(str, (PluginOperationAdapter) adapter)) {
                LogUtil.h(TAG, "url is not in whiteList.");
            } else {
                startH5LightApp(context, str, builder);
            }
        }
    }

    private static void startH5WithFile(Context context, String str, H5ProLaunchOption.Builder builder) {
        if (str.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
            LogUtil.h(TAG, "jumpToH5Application invalid url");
        } else {
            startH5LightApp(context, str, builder);
        }
    }

    private static void startH5LightApp(Context context, String str, H5ProLaunchOption.Builder builder) {
        initH5pro();
        addInternalApi(false, str, builder);
        builder.addCustomizeJsModule("JsInteraction", JsInteractionCompact.class);
        builder.addCustomizeJsModule("WeChatJsApi", WeChatJsApi.class);
        interceptComplaintCenter(str, builder);
        H5ProClient.startH5LightApp(context, str, builder.build());
    }

    private static void addParam(H5ProLaunchOption.Builder builder, String str) {
        String queryParameter = Uri.parse(Constants.OPEN_HEALTH_SPORT_PREFIX + str).getQueryParameter("path");
        LogUtil.c(TAG, "get path = " + queryParameter);
        if (!TextUtils.isEmpty(queryParameter)) {
            builder.addPath(Constants.H5PRO_PAGE_PREFIX + queryParameter + getQueryParamFromUrl(str));
            return;
        }
        builder.addPath(Constants.H5PRO_PAGE_PREFIX + getQueryParamFromUrl(str));
    }

    private static H5ProLaunchOption.Builder createLaunchOptionBuilderForDeeplink(String str) {
        H5ProLaunchOption.Builder addCustomizeJsModule = new H5ProLaunchOption.Builder().addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
        if (str.contains("isImmerse")) {
            addCustomizeJsModule.setImmerse();
        }
        if (str.contains("showStatusBar")) {
            addCustomizeJsModule.showStatusBar();
        }
        if (str.contains("statusBarTextBlack")) {
            addCustomizeJsModule.setStatusBarTextBlack(true);
        }
        if (str.contains("needSoftInputAdapter")) {
            addCustomizeJsModule.setNeedSoftInputAdapter();
        }
        if (str.contains("enableSlowWholeDocumentDraw=true")) {
            addCustomizeJsModule.enableSlowWholeDocumentDraw();
        }
        if (str.contains("isStartAtBottomOfStatusBar=true")) {
            addCustomizeJsModule.startAtBottomOfStatusBar();
        }
        if (str.contains("fitness-game")) {
            LogUtil.a(TAG, "interceptBodySensorOption");
            interceptBodySensorOption(addCustomizeJsModule);
        }
        if (str.contains("isNewSingleInstance=true")) {
            addCustomizeJsModule.setActivityStartFlag(AppRouterExtras.COLDSTART);
        }
        if (str.contains(OAuth_Login)) {
            addCustomizeJsModule.addCustomizeJsModule("autoLoginH5", AutoLoginH5Entry.class);
        }
        ArrayMap<String, String> parseUri = com.huawei.operation.utils.Utils.parseUri(str);
        if (!TextUtils.isEmpty(parseUri.get("anim"))) {
            addCustomizeJsModule.setAnim(parseUri.get("anim"));
        }
        String str2 = parseUri.get("forceDarkMode");
        if (!TextUtils.isEmpty(str2) && StringUtils.a(str2)) {
            try {
                addCustomizeJsModule.setForceDarkMode(Integer.parseInt(str2));
            } catch (NumberFormatException unused) {
                LogUtil.b(TAG, "forceDarkMode: NumberFormatException");
            }
        }
        String str3 = parseUri.get("startMode");
        if (!TextUtils.isEmpty(str3) && StringUtils.a(str3)) {
            try {
                addCustomizeJsModule.setStartMode(Integer.parseInt(str3));
            } catch (NumberFormatException unused2) {
                LogUtil.b(TAG, "forceDarkMode: NumberFormatException");
            }
        }
        putH5ProGlobalBiParams(str, addCustomizeJsModule);
        return addBundleForDeeplink(parseUri, addCustomizeJsModule);
    }

    private static H5ProLaunchOption.Builder addBundleForDeeplink(ArrayMap<String, String> arrayMap, H5ProLaunchOption.Builder builder) {
        if (arrayMap != null && !arrayMap.isEmpty()) {
            H5ProBundle h5ProBundle = new H5ProBundle();
            for (Map.Entry<String, String> entry : arrayMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (TextUtils.isEmpty(value)) {
                    LogUtil.c(TAG, String.format(Locale.ENGLISH, "addBundle: %s is empty", key));
                } else {
                    h5ProBundle.putString(key, value);
                }
            }
            builder.addBundle(h5ProBundle);
        }
        return builder;
    }

    public static String getPackageNameFromUrl(String str) {
        int indexOf;
        LogUtil.c(TAG, "Base Url = " + str);
        return (!str.contains("?") || (indexOf = str.indexOf("?")) <= 0) ? "" : str.substring(0, indexOf);
    }

    private static String getQueryParamFromUrl(String str) {
        int indexOf;
        return (!str.contains("?") || (indexOf = str.indexOf("?")) <= 0) ? "" : str.substring(indexOf);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static H5ProLaunchOption buildOption(String str, H5ProLaunchOption.Builder builder) {
        char c;
        if (builder == null) {
            builder = new H5ProLaunchOption.Builder();
        }
        buildCommon(str, builder);
        if (!TextUtils.isEmpty(str) && str.startsWith("com.huawei.health.h5.annual-report-")) {
            return interceptAnnualReport(builder);
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1391389376:
                if (str.equals("com.huawei.health.h5.ecg")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1391378402:
                if (str.equals("com.huawei.health.h5.ppg")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1391372844:
                if (str.equals("com.huawei.health.h5.vip")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1390358110:
                if (str.equals("com.huawei.health.h5.ecgce")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1244321934:
                if (str.equals("com.huawei.health.h5.ai-body-shape")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -789387129:
                if (str.equals("com.huawei.health.h5.setting")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -722204190:
                if (str.equals("com.huawei.health.h5.my-annual-flag")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -420752163:
                if (str.equals("com.huawei.health.h5.vascular-health")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -390333327:
                if (str.equals("com.huawei.health.h5.annual-report-2021")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -390333326:
                if (str.equals("com.huawei.health.h5.annual-report-2022")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -250808724:
                if (str.equals("com.huawei.health.h5.assets")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -197172476:
                if (str.equals("com.huawei.health.h5.course")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -183421155:
                if (str.equals("com.huawei.health.h5.dive")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -183326325:
                if (str.equals("com.huawei.health.h5.golf")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -80061251:
                if (str.equals("com.huawei.health.h5.groups")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 124403036:
                if (str.equals("com.huawei.health.h5.ai-fitness-course")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 125027692:
                if (str.equals("com.huawei.health.h5.sleeping-music")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 398951486:
                if (str.equals("com.huawei.health.h5.fasting-lite")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 522625811:
                if (str.equals("com.huawei.health.h5.diet-diary")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 980007009:
                if (str.equals("com.huawei.health.h5.custom-recipe")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 1226826462:
                if (str.equals("com.huawei.health.h5.breath-training")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1926532258:
                if (str.equals("com.huawei.health.h5.sleep-management")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 3:
                return interceptEcgOption(builder);
            case 1:
                return interceptPpgOption(builder);
            case 2:
                return interceptVipOption(builder);
            case 4:
                return interceptAiBodyShapeOption(builder);
            case 5:
            case '\n':
                return initInnerFullScreenApp(builder);
            case 6:
                return interceptActiveTargetOption(builder);
            case 7:
                return interceptVascularOption(builder);
            case '\b':
            case '\t':
                return interceptAnnualReport(builder);
            case 11:
                return initInnerSeriesCourse(builder);
            case '\f':
                return interceptDiveOption(builder);
            case '\r':
                return interceptGolfOption(builder);
            case 14:
                return interceptGroupOption(builder);
            case 15:
                return initAiFitnessCourse(builder);
            case 16:
            case 21:
                return initInnerSleepMusic(builder);
            case 17:
                return interceptFastingLiteOption(builder);
            case 18:
                return interceptDietDiaryOption(builder);
            case 19:
                return interceptCustomRecipeOption(builder);
            case 20:
                return interceptBreathOption(builder);
            default:
                return buildOptionAddition(str, builder);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static H5ProLaunchOption buildOptionAddition(String str, H5ProLaunchOption.Builder builder) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2132471250:
                if (str.equals("com.huawei.health.h5.cycle-calendar")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -2052467947:
                if (str.equals("com.huawei.health.h5.health-trend")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1678750612:
                if (str.equals("com.wequee.healthscores")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1568904206:
                if (str.equals("com.huawei.health.h5.dm")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1318771422:
                if (str.equals("com.huawei.health.h5.complaint")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1078204612:
                if (str.equals("com.huawei.health.h5.sleep-apnea")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -957089660:
                if (str.equals("com.huawei.health.h5.physical-fitness-test")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -849481059:
                if (str.equals("com.huawei.health.h5.marketing")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -342275020:
                if (str.equals("com.huawei.health.h5.ai-weight")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -183517433:
                if (str.equals("com.huawei.health.h5.abpm")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -183220651:
                if (str.equals(KakaConstants.KAKA_H5_PACKAGE_NAME)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 139997025:
                if (str.equals("com.huawei.health.h5.privacy-collection-display")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 168067735:
                if (str.equals("com.huawei.health.h5.health-headlines")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 362206477:
                if (str.equals("com.huawei.health.h5.walkup")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 365806945:
                if (str.equals("com.huawei.health.h5.weight")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 375732181:
                if (str.equals("com.huawei.health.h5.snack-control")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 728680921:
                if (str.equals("com.huawei.health.h5.health-record")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1099014452:
                if (str.equals("com.huawei.health.h5.health-data-subpage")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 1229172542:
                if (str.equals("com.huawei.health.h5.external-task")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1395702561:
                if (str.equals("com.huawei.healthkit.auto-login-h5")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return interceptMenstrual(builder);
            case 1:
                return interceptHealthTrend(builder);
            case 2:
                return interceptHealthVitalityOption(builder);
            case 3:
                return interceptDevicePair(builder);
            case 4:
                return interceptComplaint(builder);
            case 5:
                return interceptSleepApnea(builder);
            case 6:
                return interceptFitnessTestOption(builder);
            case 7:
                return interceptMarketingOption(builder);
            case '\b':
                return interceptAiWeight(builder);
            case '\t':
                return interceptAbpmOption(builder);
            case '\n':
                return interceptKakaMall(builder);
            case 11:
                return interceptPersonalInfo(builder);
            case '\f':
                return interceptHealthHeadLinesOption(builder);
            case '\r':
                return interceptWalkUpOption(builder);
            case 14:
                return interceptWeight(builder);
            case 15:
                return interceptSnackControl(builder);
            case 16:
                return interceptHealthRecordOption(builder);
            case 17:
                return interceptSubPage(builder);
            case 18:
                return interceptExternalTask(builder);
            case 19:
                return interceptAutoLoginOption(builder);
            default:
                LogUtil.a(TAG, "no intercept");
                return builder.build();
        }
    }

    private static H5ProLaunchOption interceptFitnessTestOption(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        H5ProClient.getServiceManager().registerService(CryptoApi.class);
        H5ProServiceManager.getInstance().registerService(PhysicalFitnessH5Interface.class);
        builder.addCustomizeJsModule("JsInteraction", bzs.e().getCommonJsModule("JsInteraction")).enableSlowWholeDocumentDraw().setNeedSoftInputAdapter();
        return builder.build();
    }

    private static H5ProLaunchOption initAiFitnessCourse(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi == null) {
            LogUtil.b(TAG, "fitnessAdviceApi is null.");
        } else {
            H5ProClient.getServiceManager().registerService(fitnessAdviceApi.getAiFitnessServiceH5RepositoryApi());
            H5ProClient.getServiceManager().registerService(SeriesCourseH5Repository.class);
        }
        return builder.build();
    }

    private static H5ProLaunchOption interceptAutoLoginOption(H5ProLaunchOption.Builder builder) {
        builder.addCustomizeJsModule("autoLoginH5", AutoLoginH5Entry.class);
        return interceptHealthVitalityOption(builder);
    }

    private static H5ProLaunchOption interceptHealthVitalityOption(H5ProLaunchOption.Builder builder) {
        builder.setImmerse().showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true);
        return builder.build();
    }

    private static H5ProLaunchOption interceptWalkUpOption(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.setImmerse().addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("device", e.getCommonJsModule("device")).showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true).enableImageCache().hideBottomVirtualKeys();
        return builder.build();
    }

    private static H5ProLaunchOption initInnerSleepMusic(H5ProLaunchOption.Builder builder) {
        builder.addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("notificationAudioControl", bzs.e().getCommonJsModule("notificationAudioControl")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).enableOnPauseCallback().setForceDarkMode(1);
        SeriesCourseH5Repository.registerService();
        return builder.build();
    }

    private static H5ProLaunchOption interceptVascularOption(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.setImmerse().addCustomizeJsModule("device", e.getCommonJsModule("device")).addCustomizeJsModule("ecgJsModule", e.getCommonJsModule("ecgJsModule")).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("wearengine", e.getCommonJsModule("wearengine")).showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true);
        return builder.build();
    }

    private static H5ProLaunchOption interceptAiBodyShapeOption(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        FamilyHealthZoneApi familyHealthZoneApi = (FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class);
        Class<? extends JsModuleBase> commonJsModule = familyHealthZoneApi != null ? familyHealthZoneApi.getCommonJsModule("healthZoneApi") : null;
        if (commonJsModule != null) {
            builder.addCustomizeJsModule("healthZoneApi", commonJsModule);
        } else {
            LogUtil.h(TAG, "interceptAiBodyShapeOption: healthZoneApi is null");
        }
        return builder.enableOnResumeCallback().build();
    }

    private static H5ProLaunchOption interceptHealthHeadLinesOption(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        bzs e = bzs.e();
        HealthHeadLinesJsApi healthHeadLinesJsApi = (HealthHeadLinesJsApi) Services.a("HealthHeadLines", HealthHeadLinesJsApi.class);
        Class<? extends JsModuleBase> commonJsModule = healthHeadLinesJsApi != null ? healthHeadLinesJsApi.getCommonJsModule("healthHeadlines") : null;
        if (commonJsModule != null) {
            builder.addCustomizeJsModule("healthHeadlines", commonJsModule);
        } else {
            LogUtil.h(TAG, "interceptHealthHeadLinesOption: healthHeadLinesInnerApi is null");
        }
        builder.addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).setImmerse().setStatusBarTextBlack(true).enableSlowWholeDocumentDraw().enableImageCache().showStatusBar();
        return builder.build();
    }

    private static H5ProLaunchOption interceptActiveTargetOption(H5ProLaunchOption.Builder builder) {
        H5ProServiceManager.getInstance().registerService(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getActiveTargetService());
        initInnerFullScreenApp(builder);
        builder.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL));
        builder.setNeedSoftInputAdapter();
        return builder.build();
    }

    private static H5ProLaunchOption interceptHealthRecordOption(H5ProLaunchOption.Builder builder) {
        H5ProServiceManager.getInstance().registerService(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getHealthRecordsJsApi());
        dsl.o();
        PressureApi pressureApi = (PressureApi) Services.c("Main", PressureApi.class);
        H5ProServiceManager.getInstance().registerService((Class<?>) pressureApi.getPressureJsApi());
        H5ProServiceManager.getInstance().registerService((Class<?>) ((BloodPressureApi) Services.c("Main", BloodPressureApi.class)).getBloodPressureJsApi());
        initInnerFullScreenApp(builder);
        builder.addCustomizeArg("examinationId", "0");
        builder.setNeedSoftInputAdapter();
        builder.addCustomizeJsModule("JsInteraction", JsInteractionCompact.class);
        builder.addCustomizeJsModule("device", Device.class);
        builder.addCustomizeJsModule("PressureNewJsApi", pressureApi.getPressureNewJsApi());
        return builder.build();
    }

    private static H5ProLaunchOption interceptSleepApnea(H5ProLaunchOption.Builder builder) {
        cun.c().checkBindPhoneService();
        initInnerFullScreenApp(builder);
        bzs e = bzs.e();
        builder.addCustomizeJsModule("device", e.getCommonJsModule("device"));
        builder.addCustomizeJsModule("ecgJsModule", e.getCommonJsModule("ecgJsModule"));
        builder.addCustomizeJsModule("healthosa", hyk.b().getOsaJsModule("healthosa"));
        return builder.build();
    }

    private static H5ProLaunchOption interceptMenstrual(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        bzs e = bzs.e();
        builder.addCustomizeJsModule("menstrual", e.getCommonJsModule("menstrual")).addCustomizeJsModule("JsInteraction", e.getCommonJsModule("JsInteraction")).addCustomizeJsModule("AchieveUtil", bzw.e().getCommonJsModule("achievement"));
        return builder.build();
    }

    private static H5ProLaunchOption interceptDiveOption(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, e.getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL));
        builder.addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi"));
        return builder.build();
    }

    private static H5ProLaunchOption interceptGolfOption(H5ProLaunchOption.Builder builder) {
        H5ProClient.getServiceManager().registerService(CryptoApi.class);
        H5ProServiceManager.getInstance().registerService(GolfH5NativeInterface.class);
        builder.addCustomizeJsModule("healthZoneApi", ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getCommonJsModule("healthZoneApi"));
        builder.addCustomizeJsModule("GolfDeviceInterface", GolfH5DownloadInterface.class);
        builder.addCustomizeJsModule("device", Device.class);
        return builder.build();
    }

    private static H5ProLaunchOption interceptWeight(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
            H5ProServiceManager.getInstance().registerService(weightApi.getCustomRecipe());
            H5ProServiceManager.getInstance().registerService(weightApi.getFastingLiteRepositoryApi());
        } else {
            LogUtil.h(TAG, "interceptWeight: weightApi is null");
        }
        H5ProServiceManager.getInstance().registerService(((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).getIntelligentPlanH5RepositoryApi());
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("weight", WeightJsApi.class).addCustomizeJsModule("device", Device.class).enableOnPauseCallback().enableOnDestroyCallback();
        return builder.build();
    }

    private static H5ProLaunchOption interceptDevicePair(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        builder.addCustomizeJsModule("BasePairManager", BasePairManagerJsApi.class).addCustomizeJsModule("MessageNotificationManager", MessageNotificationJsApi.class).addCustomizeJsModule("UpdateDeviceManager", UpdateDeviceJsApi.class).addCustomizeJsModule("DeviceResourceManager", DeviceResourceJsApi.class).addCustomizeJsModule("FileService", FileServiceJsApi.class).enableOnPauseCallback().enableOnDestroyCallback();
        return builder.build();
    }

    private static H5ProLaunchOption interceptAiWeight(H5ProLaunchOption.Builder builder) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getCustomRecipe());
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
        } else {
            LogUtil.h(TAG, "interceptCustomRecipeOption: weightApi is null");
        }
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi == null) {
            LogUtil.b(TAG, "fitnessAdviceApi is null.");
        } else {
            H5ProServiceManager.getInstance().registerService(fitnessAdviceApi.getIntelligentPlanH5RepositoryApi());
        }
        H5ProClient.getServiceManager().registerService(SeriesCourseH5Repository.class);
        builder.setImmerse().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("weight", bzs.e().getCommonJsModule("weight")).addCustomizeJsModule("device", Device.class).showStatusBar().setStatusBarTextBlack(true).setNeedSoftInputAdapter().setForceDarkMode(1);
        return builder.build();
    }

    private static H5ProLaunchOption initInnerFullScreenApp(H5ProLaunchOption.Builder builder) {
        builder.setImmerse().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true);
        HeartRateSettingApi heartRateSettingApi = (HeartRateSettingApi) Services.a("Main", HeartRateSettingApi.class);
        if (heartRateSettingApi != null) {
            H5ProServiceManager.getInstance().registerService(heartRateSettingApi.getCustomDataApi());
        } else {
            LogUtil.h(TAG, "initInnerFullScreenApp: heartRateSettingApi is null");
        }
        return builder.build();
    }

    private static H5ProLaunchOption initInnerSeriesCourse(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).addCustomizeJsModule("course", ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).getCourseDetailH5Bridge()).addCustomizeJsModule(BleConstants.BLE_JSINTERACTION, BleJsInteractionCompact.class).addCustomizeJsModule(BleConstants.BLE_HI_LINK, BleJsInteractionCompact.class).addCustomizeJsModule("device", e.getCommonJsModule("device")).enableOnPauseCallback().setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(1);
        SeriesCourseH5Repository.registerService();
        HeartRateSettingApi heartRateSettingApi = (HeartRateSettingApi) Services.a("Main", HeartRateSettingApi.class);
        if (heartRateSettingApi != null) {
            H5ProServiceManager.getInstance().registerService(heartRateSettingApi.getCustomDataApi());
        } else {
            LogUtil.h(TAG, "initInnerSeriesCourse: heartRateSettingApi is null");
        }
        return builder.build();
    }

    private static H5ProLaunchOption interceptPpgOption(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.setImmerse().addCustomizeJsModule("ppgJsModule", e.getCommonJsModule("ppgJsModule")).addCustomizeJsModule("ecgJsModule", e.getCommonJsModule("ecgJsModule")).addCustomizeJsModule("device", e.getCommonJsModule("device")).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true);
        return builder.build();
    }

    private static H5ProLaunchOption interceptBreathOption(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.setImmerse().addCustomizeJsModule("breathtrain", e.getCommonJsModule("breathtrain")).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, e.getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true);
        return builder.build();
    }

    private static H5ProLaunchOption interceptDietDiaryOption(H5ProLaunchOption.Builder builder) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
            weightApi.joinDietDiary();
            weightApi.syncDietData();
        } else {
            LogUtil.h(TAG, "interceptDietDiaryOption: weightApi is null");
        }
        builder.setImmerse().addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).showStatusBar().setStatusBarTextBlack(true).setNeedSoftInputAdapter().addCustomizeJsModule("DietKakaUtil", DietKakaUtil.class).addCustomizeJsModule("RecognizeH5Helper", RecognizeH5Helper.class).setForceDarkMode(1);
        return builder.build();
    }

    private static H5ProLaunchOption interceptCustomRecipeOption(H5ProLaunchOption.Builder builder) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getCustomRecipe());
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
            weightApi.syncDietData();
        } else {
            LogUtil.h(TAG, "interceptCustomRecipeOption: weightApi is null");
        }
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi == null) {
            LogUtil.b(TAG, "fitnessAdviceApi is null.");
        } else {
            H5ProServiceManager.getInstance().registerService(fitnessAdviceApi.getIntelligentPlanH5RepositoryApi());
        }
        builder.setImmerse().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", JsTradeApi.class).showStatusBar().setStatusBarTextBlack(true).setNeedSoftInputAdapter().addCustomizeJsModule("DietKakaUtil", DietKakaUtil.class).setForceDarkMode(1);
        return builder.build();
    }

    private static H5ProLaunchOption interceptFastingLiteOption(H5ProLaunchOption.Builder builder) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getFastingLiteRepositoryApi());
        } else {
            LogUtil.h(TAG, "interceptFastingLiteOption: weightApi is null");
        }
        builder.setImmerse().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", JsTradeApi.class).showStatusBar().setStatusBarTextBlack(true).setNeedSoftInputAdapter().setForceDarkMode(1);
        return builder.build();
    }

    private static H5ProLaunchOption interceptAbpmOption(H5ProLaunchOption.Builder builder) {
        LogUtil.a(TAG, "interceptAbpmOption");
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("device", bzs.e().getCommonJsModule("device"));
        BloodPressureApi bloodPressureApi = (BloodPressureApi) Services.c("Main", BloodPressureApi.class);
        LogUtil.a(TAG, "interceptAbpmOption bloodPressureApi", bloodPressureApi.getBloodPressureJsApi().getSimpleName());
        H5ProServiceManager.getInstance().registerService((Class<?>) bloodPressureApi.getBloodPressureJsApi());
        return builder.build();
    }

    private static H5ProLaunchOption interceptVipOption(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        builder.enableOnResumeCallback();
        bzs e = bzs.e();
        builder.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, e.getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL));
        builder.addCustomizeJsModule("device", e.getCommonJsModule("device"));
        builder.addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi != null) {
            builder.addCustomizeJsModule("VipInnerApi", vipApi.getCommonJsModule("VipInnerApi"));
        }
        H5ProServiceManager.getInstance().registerService((Class<?>) ((BloodPressureApi) Services.c("Main", BloodPressureApi.class)).getBloodPressureJsApi());
        dsl.o();
        return builder.build();
    }

    private static H5ProLaunchOption interceptEcgOption(H5ProLaunchOption.Builder builder) {
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        bzs e = bzs.e();
        builder.setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(1).addCustomizeJsModule("message", messageCenterApi.getCommonJsModule("message")).addCustomizeJsModule("ecgJsModule", e.getCommonJsModule("ecgJsModule")).addCustomizeJsModule("device", e.getCommonJsModule("device")).addCustomizeJsModule(RemoteMessageConst.NOTIFICATION, e.getCommonJsModule(RemoteMessageConst.NOTIFICATION)).addCustomizeJsModule("notificationAudioControl", e.getCommonJsModule("notificationAudioControl")).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("shareToCaasApi", e.getCommonJsModule("shareToCaasApi"));
        Class<? extends JsModuleBase> commonJsModule = ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).getCommonJsModule("healthZoneApi");
        if (commonJsModule != null) {
            builder.addCustomizeJsModule("healthZoneApi", commonJsModule);
        } else {
            LogUtil.h(TAG, "interceptEcgOption: healthZoneApi is null");
        }
        return builder.build();
    }

    private static H5ProLaunchOption interceptGroupOption(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, e.getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).addCustomizeJsModule("sport", e.getCommonJsModule("sport")).setImmerse().showStatusBar().setBackgroundColor(GROUP_BACKGROUND_COLOR).setForceDarkMode(0).setStatusBarTextBlack(true).setNeedSoftInputAdapter();
        return builder.build();
    }

    private static H5ProLaunchOption interceptAnnualReport(H5ProLaunchOption.Builder builder) {
        bzs e = bzs.e();
        builder.setImmerse().addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("JsInteraction", e.getCommonJsModule("JsInteraction")).addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, e.getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL)).addCustomizeJsModule("AchieveUtil", bzw.e().getCommonJsModule("achievement")).showStatusBar().enableSlowWholeDocumentDraw().enableImageCache().setStatusBarTextBlack(true);
        return builder.build();
    }

    private static H5ProLaunchOption interceptSnackControl(H5ProLaunchOption.Builder builder) {
        H5ProServiceManager.getInstance().registerService(((TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class)).getTodoJsClass());
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            H5ProServiceManager.getInstance().registerService(weightApi.getDietDiaryRepositoryApi());
            weightApi.syncDietData();
        } else {
            LogUtil.h(TAG, "weightApi is null.");
        }
        builder.setImmerse().enableOnResumeCallback().showStatusBar().setNeedSoftInputAdapter().addCustomizeJsModule("tradeApi", JsTradeApi.class).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setForceDarkMode(1);
        return builder.build();
    }

    private static void interceptBodySensorOption(H5ProLaunchOption.Builder builder) {
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi == null) {
            LogUtil.b(TAG, "fitnessAdviceApi is null.");
        } else {
            H5ProServiceManager.getInstance().registerService(fitnessAdviceApi.getBodySenseCourseH5RepositoryApi());
            builder.addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("bodySenseApi", fitnessAdviceApi.getBodySenseJsBridgeApi()).setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(0);
        }
    }

    private static void initJsBridgeModule() {
        ShareEntry.setShareImpl(new ShareImpl());
        H5ProResidentExtManager.setPermissionExtApi(new PermissionImpl());
        H5ProClient.getServiceManager().registerService(HealthCommonApi.class);
        H5ProClient.registerBridgeClass("healthyliving", bzs.e().getCommonJsModule("healthyliving"));
        H5ProClient.registerBridgeClass("healthengine", bzs.e().getCommonJsModule("healthengine"));
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            H5ProServiceManager.getInstance().registerService(sleepApi.getSleepManagementJsApi());
        } else {
            LogUtil.a(TAG, "sleepServiceApi is null");
        }
    }

    private static void initSalableServices() {
        H5ProClient.registerScalableService(new H5ProAppLoadStorageServiceImpl());
        if (isOpenH5VersionGraySwitch) {
            H5ProClient.registerVersionInterceptor(new H5ProInterceptor<String, String>() { // from class: com.huawei.operation.h5pro.H5proUtil.1
                @Override // com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor
                public void intercept(String str, H5ProInterceptor.InterceptCallback<String> interceptCallback) {
                    H5ProVersionManager.checkH5Upgrade(str, interceptCallback);
                }
            });
        }
        H5ProClient.registerVersionUpgradeApi(new H5ProVersionUpgradeExitImpl());
        H5ProClient.registerHostRuntimeApi(new H5ProAppRuntimeImpl());
    }

    private static boolean isOpenDebugLog() {
        return Boolean.parseBoolean(SharedPreferenceManager.b(gxf.d(), "2040", "beta_debug_log_switch"));
    }

    private static void initEnvironment() {
        EnvironmentHelper.BuildType buildType;
        synchronized (H5proUtil.class) {
            if (CommonUtil.bv()) {
                buildType = EnvironmentHelper.BuildType.RELEASE;
            } else if (CommonUtil.cc()) {
                buildType = EnvironmentHelper.BuildType.TEST;
            } else if (CommonUtil.as()) {
                buildType = isOpenDebugLog() ? EnvironmentHelper.BuildType.DEBUG : EnvironmentHelper.BuildType.BETA;
            } else if (CommonUtil.bc()) {
                buildType = EnvironmentHelper.BuildType.GREEN;
            } else {
                if (!CommonUtil.aq() && !CommonUtil.cg()) {
                    buildType = EnvironmentHelper.BuildType.RELEASE;
                }
                buildType = EnvironmentHelper.BuildType.DEBUG;
            }
            H5ProClient.setBuildType(buildType);
            H5ProClient.setMobileAppEngine(EnvironmentInfo.k());
            setEnvironmentUrl(buildType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setEnvironmentUrl(final EnvironmentHelper.BuildType buildType) {
        String url;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.H5proUtil$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    H5proUtil.setEnvironmentUrl(EnvironmentHelper.BuildType.this);
                }
            });
            return;
        }
        GRSManager a2 = GRSManager.a(BaseApplication.getContext());
        if (health.compact.a.Utils.l()) {
            url = a2.getNoCheckUrl("domainContentcenterDbankcdnNew", a2.getCountryCode());
        } else {
            url = a2.getUrl("domainContentcenterDbankcdnNew");
        }
        if (TextUtils.isEmpty(url)) {
            LogUtil.h(TAG, "setEnvironmentUrl: domain name is empty");
            return;
        }
        ReleaseLogUtil.e("R_H5PRO_H5proUtil", "setEnvironmentUrl: buildType -> " + buildType.name() + "--" + url.contains(BleConstants.WEIGHT_KEY));
        if (buildType == EnvironmentHelper.BuildType.TEST || buildType == EnvironmentHelper.BuildType.GREEN) {
            H5ProClient.setBaseUrl(String.format(Locale.ENGLISH, "%s%s", url, "/sandbox/cch5/testappCCH5/"));
        } else if (buildType == EnvironmentHelper.BuildType.DEBUG || buildType == EnvironmentHelper.BuildType.BETA) {
            H5ProClient.setBaseUrl(String.format(Locale.ENGLISH, "%s%s", url, "/sandbox/cch5/health/"));
        } else {
            H5ProClient.setBaseUrl(String.format(Locale.ENGLISH, "%s%s", url, "/cch5/health/"));
        }
        LogUtil.a(TAG, "setEnvironmentUrl: end");
    }

    private static void initBiAnalyser() {
        Analyzer.enable(ixx.b());
        Analyzer.setEnvironmentParam(ixx.d().c());
    }

    private static void initBiAnalyserOnce() {
        Analyzer.addForceBiPackageName("com.huawei.health.h5.vip");
        Analyzer.addGlobalParamKeys(GLOBAL_BI_PARAM_KEYS);
        Analyzer.setAnalyzerProxy(new AnalyzerProxyImpl());
    }

    public static String bytesToHexString(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return sb.toString();
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            if (!TextUtils.isEmpty(str)) {
                sb.append(hexString);
                sb.append(str);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.trim().toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static H5ProLaunchOption interceptMarketingOption(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        builder.addCustomizeJsModule("JsInteraction", bzs.e().getCommonJsModule("JsInteraction")).enableSlowWholeDocumentDraw().setNeedSoftInputAdapter();
        return builder.build();
    }

    private static H5ProLaunchOption interceptComplaint(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        return builder.build();
    }

    private static H5ProLaunchOption interceptPersonalInfo(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        builder.addCustomizeJsModule("privacyCollection", PrivacyCollectionJsApi.class);
        return builder.build();
    }

    private static H5ProLaunchOption interceptComplaintCenter(String str, H5ProLaunchOption.Builder builder) {
        Uri parse = Uri.parse(str);
        if (parse.getBooleanQueryParameter("isComplaint", false)) {
            builder.addCustomizeJsModule(com.huawei.openalliance.ad.constant.Constants.GET_COMPLAIN_ADD_INFO_JS_NAME, ComplaintJsApi.class);
            H5ProBundle h5ProBundle = new H5ProBundle(com.huawei.openalliance.ad.constant.Constants.GET_COMPLAIN_ADD_INFO_JS_NAME);
            for (String str2 : ComplaintConstants.COMPLAINT_PARAM_KEYS) {
                h5ProBundle.putString(str2, parse.getQueryParameter(str2));
            }
            builder.addBundle(h5ProBundle);
        }
        return builder.build();
    }

    private static H5ProLaunchOption interceptKakaMall(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        bzw e = bzw.e();
        builder.addCustomizeJsModule("DialogApi", DialogApi.class).addCustomizeJsModule("AchieveUtil", e.getCommonJsModule("achievement")).addCustomizeJsModule("CloudUtil", e.getCommonJsModule("CloudUtil")).addCustomizeJsModule("JsInteraction", JsInteractionCompact.class).enableImageCache();
        return builder.build();
    }

    private static H5ProLaunchOption interceptHealthTrend(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        builder.addCustomizeJsModule("JsInteraction", JsInteractionCompact.class);
        builder.addCustomizeJsModule("device", Device.class);
        BloodPressureApi bloodPressureApi = (BloodPressureApi) Services.c("Main", BloodPressureApi.class);
        LogUtil.a(TAG, "interceptHealthTrend bloodPressureApi", bloodPressureApi.getBloodPressureJsApi().getSimpleName());
        H5ProServiceManager.getInstance().registerService((Class<?>) bloodPressureApi.getBloodPressureJsApi());
        return builder.build();
    }

    private static H5ProLaunchOption interceptExternalTask(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        return builder.build();
    }

    private static void buildCommon(String str, H5ProLaunchOption.Builder builder) {
        builder.addCustomizeJsModule("DialogApi", DialogApi.class).addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
    }

    private static H5ProLaunchOption interceptSubPage(H5ProLaunchOption.Builder builder) {
        initInnerFullScreenApp(builder);
        builder.addCustomizeJsModule("ArkUIJsApi", ArkUIJsApi.class);
        return builder.build();
    }

    public static void putH5ProGlobalBiParams(String str, H5ProLaunchOption.Builder builder) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            str = Constants.OPEN_HEALTH_SPORT_PREFIX + str;
        }
        Uri parse = Uri.parse(str);
        if (parse.isOpaque()) {
            return;
        }
        for (String str2 : GLOBAL_BI_PARAM_KEYS) {
            String queryParameter = parse.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                builder.addGlobalBiParam(str2, queryParameter);
            }
        }
    }

    public static void initH5ProAndRefreshVersionDataOnce() {
        if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.h(TAG, "initH5ProAndRefreshVersionDataOnce: getAuthorizationStatus -> false");
            return;
        }
        if (BuildTypeConfig.a()) {
            initH5pro();
        }
        if (isOpenH5VersionGraySwitch) {
            H5ProVersionManager.syncH5VersionSummary();
        }
        H5ProPkgPreloadSyncTask.startTaskWithDelay(BaseApplication.getContext(), HOME_PRELOAD_H5_PKG_ARRAY, new H5PreloadWeightStrategy(), HOME_PRELOAD_DELAY);
    }

    public static void putBiEventFromH5Deeplink(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("Steps", 1);
        hashMap.put("H5package", str2);
        hashMap.put("H5url", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.H5_RESOURCE_LOAD_EVENT_VALUE.value(), hashMap, 0);
    }
}
