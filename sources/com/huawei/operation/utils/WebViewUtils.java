package com.huawei.operation.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.share.ResultCallback;
import com.huawei.operation.view.ConfigConstants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.ceb;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class WebViewUtils {
    private static final String APP_PATH_SD_CARD = "/healthshop/.";
    private static final String APP_PATH_SD_CARD_ROOT = "/huawei/";
    private static final int FILE_CHOOSER_RESULT_CODE = 10086;
    private static final int FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_FIVE = 10087;
    private static final String TAG = "PluginOperation_WebViewUtils";
    private static final int TEXT_SIZE_ZOOM_DEFAULT = 100;
    private static final String VMALL_URL = "vmall.com";
    private static final String WECHAT_PKG_NAME = "com.tencent.mm";
    private static final String WECHAT_WAP_PAY = "weixin://wap/pay?";
    private static int sAppSiteId = 0;
    private static String sDomainMyHuaweiUpLogin = "";
    private static String sHealthRecommendHost = "";
    private static String sHealthVmallHost = "";
    private static String sIdVmallUlr = "";
    private static String sOAuthAccessHost = "";
    private static String sOpenApiVmallHost = "";
    private static List<String> sThirdPkgNames = new ArrayList(Arrays.asList("com.pa.health", "com.zhongan.insurance"));
    private static List<String> sCloudSchemeList = new ArrayList(Arrays.asList("huaweihealth", "huaweischeme", "geo:", "hwt:", "hwmediacenter:", "himovie:", "financeclient:", "wallet:", "mailto:", KakaConstants.SCHEME_TEL));

    public static boolean isLeftLarger(int i, int i2) {
        return i >= i2;
    }

    private WebViewUtils() {
    }

    public static boolean isWhiteThirdPkg(String str) {
        return !TextUtils.isEmpty(str) && sThirdPkgNames.contains(str);
    }

    public static boolean schemeHandle(Context context, PluginOperationAdapter pluginOperationAdapter, String str) {
        if (context == null || pluginOperationAdapter == null || TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "mAdapter is null ");
            return false;
        }
        if (!isDeepLinkWhiteUrl(pluginOperationAdapter, str)) {
            return false;
        }
        if (str.startsWith(WECHAT_WAP_PAY)) {
            LogUtil.a(TAG, "checkInstalledWeChatOrAlipay weixin");
            if (!CommonUtil.e(context, "com.tencent.mm")) {
                LogUtil.a(TAG, "not install weixin");
                downloadApkByPackageName(context, "com.tencent.mm");
                return true;
            }
        }
        LogUtil.a(TAG, "scheme in the list return true");
        jumpToActivity(context, str);
        return true;
    }

    public static void downloadApkByPackageName(Context context, String str) {
        health.compact.a.LogUtil.c(TAG, "downloadApkByPackageName");
        if (context == null || str == null) {
            health.compact.a.LogUtil.a(TAG, "downloadApkByPackageName null");
            return;
        }
        Uri parse = Uri.parse("market://details?id=" + str);
        if (parse != null) {
            health.compact.a.LogUtil.c(TAG, "downloadApkByPackageName startActivity");
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
            intent.addFlags(268435456);
            startActivitySecurity(context, intent, context.getString(R.string.IDS_device_fragment_application_market));
            return;
        }
        health.compact.a.LogUtil.e(TAG, "uri is null!");
    }

    public static boolean isDeepLinkWhiteUrl(PluginOperationAdapter pluginOperationAdapter, String str) {
        if (pluginOperationAdapter == null || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "adapter or url is null ");
            return false;
        }
        if (isCloudScheme(str)) {
            return true;
        }
        List<String> queryUrlList = pluginOperationAdapter.queryUrlList("DEEPLINKWHITEURL");
        if (queryUrlList != null && !queryUrlList.isEmpty()) {
            LogUtil.a(TAG, "deepLink whiteUrl size = ", Integer.valueOf(queryUrlList.size()));
        } else {
            queryUrlList = Utils.fetchLocalDeepLinkWhiteUrlList();
            LogUtil.a(TAG, "deepLink whiteUrl in fetchLocalWhiteUrlList");
        }
        return checkDeepLinkInQueryList(queryUrlList, str);
    }

    private static boolean checkDeepLinkInQueryList(List<String> list, String str) {
        for (String str2 : list) {
            if (TextUtils.isEmpty(str2)) {
                return false;
            }
            if (str.startsWith(str2)) {
                return true;
            }
        }
        LogUtil.a(TAG, "scheme not in the list return false.");
        return false;
    }

    public static boolean schemeHandleVmall(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            boolean z = false;
            for (String str3 : context.getResources().getStringArray(R.array._2130968718_res_0x7f04008e)) {
                z = str2.contains(str3);
                if (z) {
                    break;
                }
            }
            if (z && str.startsWith(Constants.VMALL_OPEN_SCHEME_URL)) {
                jumpToActivity(context, str);
                return true;
            }
        }
        return false;
    }

    private static boolean isCloudScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = sCloudSchemeList.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static void startActivitySecurity(Context context, Intent intent, String str) {
        ResolveInfo resolveActivity;
        LogUtil.a(TAG, "startActivitySecurity with ", str);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
        intent.setComponent(componentName);
        nsn.cLM_(intent, componentName.getPackageName(), context, str);
    }

    private static void jumpToActivity(Context context, String str) {
        LogUtil.a(TAG, "jumpToActivity");
        try {
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setFlags(268435456);
            intent.setData(Uri.parse(str));
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setComponent(null);
            intent.setSelector(null);
            context.startActivity(intent);
            LogUtil.a(TAG, "jump to scheme view");
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "jumpToActivity ActivityNotFoundException");
        }
    }

    public static void openFileChooserImpl(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType(Constants.IMAGE_TYPE);
            activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 10086);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a(TAG, "activity not found exception1.");
        }
    }

    public static void openFileChooserImplForAndroid5(Activity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(Constants.IMAGE_TYPE);
        try {
            Intent intent2 = new Intent("android.intent.action.CHOOSER");
            intent2.putExtra("android.intent.extra.INTENT", intent);
            intent2.putExtra("android.intent.extra.TITLE", "Image Chooser");
            activity.startActivityForResult(intent2, 10087);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a(TAG, "activity not found exception2.");
        }
    }

    public static File createImageFile() throws IOException {
        File file;
        if (PermissionUtil.c()) {
            file = BaseApplication.getContext().getExternalFilesDir(APP_PATH_SD_CARD + Environment.DIRECTORY_PICTURES);
        } else {
            file = null;
        }
        if (file == null) {
            file = Environment.getExternalStoragePublicDirectory("/huawei/" + BaseApplication.getContext().getPackageName() + APP_PATH_SD_CARD + Environment.DIRECTORY_PICTURES);
        }
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.c(TAG, "storageDir.mkdirs failure");
        }
        return File.createTempFile("JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_", ".jpg", file);
    }

    public static boolean isUnreasonableTitle(ArrayList<String> arrayList, String str) {
        if (arrayList == null || TextUtils.isEmpty(str) || arrayList.isEmpty()) {
            return true;
        }
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            if (normalize.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void setTokenKeyUrlSp(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferenceManager.e(context, Integer.toString(10011), "WEB_VIEW_GRS_TOKEN_URL_KEY", str, new StorageParams());
    }

    public static String getTokenKeyUrlSp(Context context) {
        return context == null ? "" : SharedPreferenceManager.b(context, Integer.toString(10011), "WEB_VIEW_GRS_TOKEN_URL_KEY");
    }

    public static void setActivityKeyUrlSp(Context context, String str) {
        SharedPreferenceManager.e(context, Integer.toString(10011), "WEB_VIEW_GRS_ACTIVITY_URL_KEY", str, new StorageParams());
    }

    public static String getActivityKeyUrlSp(Context context) {
        return SharedPreferenceManager.b(context, Integer.toString(10011), "WEB_VIEW_GRS_ACTIVITY_URL_KEY");
    }

    public static void setActivityPortalUrlSp(Context context, String str) {
        SharedPreferenceManager.e(context, Integer.toString(10011), "WEB_VIEW_GRS_ACTIVITY_PORTAL_KEY", str, new StorageParams());
    }

    public static String getActivityPortalUrlSp(Context context) {
        return SharedPreferenceManager.b(context, Integer.toString(10011), "WEB_VIEW_GRS_ACTIVITY_PORTAL_KEY");
    }

    public static void goToPkgAppDialog(final Context context, final String str, String str2, final String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !(context instanceof Activity)) {
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(context.getString(R.string._2130843263_res_0x7f02167f)).e(String.format(Locale.ROOT, context.getString(R.string._2130842772_res_0x7f021494), str2)).cyV_(context.getString(R.string._2130841379_res_0x7f020f23), new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(str3)) {
                    try {
                        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                        if (launchIntentForPackage != null) {
                            context.startActivity(launchIntentForPackage);
                        }
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.a(WebViewUtils.TAG, "activity not found exception3.");
                    }
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.setClassName(str, str3);
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b(WebViewUtils.TAG, "goToPkgAppDialog ActivityNotFoundException", e.getMessage());
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(context.getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: com.huawei.operation.utils.WebViewUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a(WebViewUtils.TAG, "goToPkgAppDialog setNegativeButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (((Activity) context).isFinishing()) {
            LogUtil.a(TAG, "goToPkgAppDialog Activity isFinishing");
            return;
        }
        CustomTextAlertDialog a2 = builder.a();
        a2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.operation.utils.WebViewUtils$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                LogUtil.a(WebViewUtils.TAG, "goToPkgAppDialog setOnCancelListener");
            }
        });
        a2.show();
    }

    public static boolean isVmallUrl(String str) {
        return !TextUtils.isEmpty(str) && str.contains("vmall.com");
    }

    public static boolean isHealthVmall(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c(TAG, "isHealthVmall is empty url");
            return false;
        }
        String healthRecommendHost = getHealthRecommendHost();
        if (healthRecommendHost == null) {
            LogUtil.b(TAG, "isHealthVmall healthRecommendHost is null");
            return false;
        }
        if (CommonUtil.cc()) {
            if (str.startsWith(healthRecommendHost + "/healthMallPlat/vmall/index.html#/agrSign")) {
                return false;
            }
            if (str.startsWith(healthRecommendHost + "/healthMallPlat/")) {
                LogUtil.c(TAG, "isHealthVmall is true in test version");
                return true;
            }
        } else {
            if (str.startsWith(healthRecommendHost + "/healthMallPlat/vmall/index.html#/agrSign")) {
                return false;
            }
            if (str.startsWith(healthRecommendHost + "/healthMallPlat/")) {
                LogUtil.c(TAG, "isHealthVmall is true in release version");
                return true;
            }
        }
        LogUtil.c(TAG, "isHealthVmall is false");
        return false;
    }

    public static boolean isVmallMcpLoginUrl(String str) {
        String openApiVmallHost = getOpenApiVmallHost();
        LogUtil.c(TAG, "isVmallMcpLoginUrl openApiVmallHost = ", openApiVmallHost);
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith(openApiVmallHost + UriConstants.URL_VMALL_CAS_MCP_LOGIN)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUpLoginUrl(String str) {
        String healthIdVmallHost = getHealthIdVmallHost();
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith(healthIdVmallHost + UriConstants.URL_VMALL_CAS_ST_LOGIN)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        return lowerCase.startsWith("https://") || lowerCase.startsWith("http://") || lowerCase.startsWith(Constants.PREFIX_FILE);
    }

    public static String replaceSpace(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace(" ", Constants.PERCENT_20);
    }

    public static boolean cannotGoBackUrl(String str) {
        if (!TextUtils.isEmpty(str) && !isVmallMcpLoginUrl(str) && !isUpLoginUrl(str)) {
            if (!str.startsWith(getHealthRecommendHost() + UriConstants.URL_VMALL_SIGN)) {
                if (!str.startsWith(getOAuthAccessHost() + UriConstants.OAUTH_URL_AUTHORIZE)) {
                    if (!str.startsWith(getDomainMyHuaweiUpLogin() + UriConstants.OAUTH_URL_LOGINAUTH) && !str.contains(UriConstants.OAUTH_URL_LOGINCALLBACK)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String getHealthRecommendHost() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        if (m == sAppSiteId && !TextUtils.isEmpty(sHealthRecommendHost)) {
            return sHealthRecommendHost;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("healthRecommendUrl");
        sAppSiteId = m;
        sHealthRecommendHost = url;
        return url;
    }

    public static String getHealthIdVmallHost() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        if (m == sAppSiteId && !TextUtils.isEmpty(sIdVmallUlr)) {
            return sIdVmallUlr;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHwidVmall");
        sAppSiteId = m;
        sIdVmallUlr = url;
        return url;
    }

    public static String getHealthVmallHost() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        if (m == sAppSiteId && !TextUtils.isEmpty(sHealthVmallHost)) {
            return sHealthVmallHost;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHealthVmall");
        sAppSiteId = m;
        sHealthVmallHost = url;
        return url;
    }

    public static String getOpenApiVmallHost() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        if (m == sAppSiteId && !TextUtils.isEmpty(sOpenApiVmallHost)) {
            return sOpenApiVmallHost;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainOpenapiVmall");
        sAppSiteId = m;
        sOpenApiVmallHost = url;
        return url;
    }

    public static String getUrl(String str) {
        return Constants.JAVA_SCRIPT + str;
    }

    public static String getUrl(String str, String str2) {
        return Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET + str2 + Constants.RIGHT_BRACKET;
    }

    public static String getUrl(String str, String str2, String str3) {
        return Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET + str2 + Constants.RIGHT_BRACKET + str3;
    }

    public static String getUrlForHtml(String str, String str2) {
        return Constants.JAVA_SCRIPT + str + Constants.LEFT_BRACKET_ONLY + str2 + Constants.RIGHT_BRACKET_ONLY;
    }

    public static ArrayList<String> getTitleList() {
        ArrayList<String> arrayList = new ArrayList<>(12);
        arrayList.add("http:");
        arrayList.add("https:");
        arrayList.add("404 Not Found");
        arrayList.add(".html");
        arrayList.add(".xhtml");
        arrayList.add(".htm");
        arrayList.add(".asp");
        arrayList.add(".jsp");
        arrayList.add(".php");
        arrayList.add(WatchFaceConstant.XML_SUFFIX);
        arrayList.add(".css");
        return arrayList;
    }

    public static ArrayList<TitleBean> getLocalTitleBeans(Context context) {
        ArrayList<TitleBean> arrayList = new ArrayList<>(26);
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.MIOAMORE_URL, "x", TitleBean.RIGHT_BTN_TYPE_MORE));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.HW_HEALTH_SHOP_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.HW_HEALTH_MESSAGEH5, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.HW_HEALTH_ACTIVITY_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.HW_HEALTH_ACHIEVEMENT_URL, "x", ""));
        addTripartiteLink(arrayList);
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.WENJUAN_NET_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.SOJUMP_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.WENJUAN_COM_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.MIKECRM_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.WJX_URL, "x", ""));
        String healthVmallHost = getHealthVmallHost();
        if (TextUtils.isEmpty(healthVmallHost)) {
            LogUtil.h(TAG, "getLocalTitleBeans healthVmall is empty");
            healthVmallHost = "http:/";
        }
        LogUtil.c(TAG, "getLocalTitleBeans healthVmall = ", healthVmallHost);
        arrayList.add(OperationUtils.getTitleBean(healthVmallHost + ConfigConstants.HELP_URL, TitleBean.LEFT_BTN_TYPE_ARROW, ""));
        arrayList.add(OperationUtils.getTitleBean("vmall.com", TitleBean.LEFT_BTN_TYPE_ARROW, ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.WATCH_FACE_URL, TitleBean.LEFT_BTN_TYPE_ARROW, ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.WATCH_FACE_NEW_URL, TitleBean.LEFT_BTN_TYPE_ARROW, ""));
        arrayList.add(OperationUtils.getTitleBean(getActivityKeyUrlSp(context), "x", ""));
        return arrayList;
    }

    private static void addTripartiteLink(ArrayList<TitleBean> arrayList) {
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.HEARTIDE_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.BOOHEE_COM_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.BOOHEE_CN_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.ZHONG_AN_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.ALIPAY_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.UNIONPAY_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.PAY_95516_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.MIAOHEALTH_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.TENPAY_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.CHUNYUYISHENG_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.CODOON_URL, "x", ""));
        arrayList.add(OperationUtils.getTitleBean(ConfigConstants.GUAHAO_URL, "x", ""));
    }

    public static void processSslError(SslError sslError) {
        switch (sslError.getPrimaryError()) {
            case 0:
                LogUtil.a(TAG, "onReceivedSslError SSL_NOTYETVALID");
                break;
            case 1:
                LogUtil.a(TAG, "onReceivedSslError SSL_EXPIRED");
                break;
            case 2:
                LogUtil.a(TAG, "onReceivedSslError SSL_IDMISMATCH");
                break;
            case 3:
                LogUtil.a(TAG, "onReceivedSslError SSL_UNTRUSTED");
                break;
            case 4:
                LogUtil.a(TAG, "onReceivedSslError SSL_DATE_INVALID");
                break;
            case 5:
                LogUtil.a(TAG, "onReceivedSslError SSL_INVALID");
                break;
            case 6:
                LogUtil.a(TAG, "onReceivedSslError SSL_MAX_ERROR");
                break;
        }
    }

    public static boolean isFullScreen(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, " url is null");
            return false;
        }
        try {
            if ("ON".equals(Uri.parse(str).getQueryParameter("FullScreen"))) {
                LogUtil.a(TAG, " FullScreen=ON");
                return true;
            }
        } catch (UnsupportedOperationException e) {
            LogUtil.b(TAG, "isFullScreen Exception = ", e.getMessage());
        }
        return false;
    }

    @ApiDefine(uri = ActivityHtmlPathApi.class)
    public static class ActivityHtmlPathImpl implements ActivityHtmlPathApi {
        @Override // com.huawei.operation.utils.ActivityHtmlPathApi
        public String getActivityHtmlPath() {
            return WebViewUtils.getHtmlPath(null);
        }

        @Override // com.huawei.operation.utils.ActivityHtmlPathApi
        public String getActivityHtmlPath(ceb cebVar) {
            return WebViewUtils.getHtmlPath(cebVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getHtmlPath(ceb cebVar) {
        if (health.compact.a.Utils.o()) {
            return CommonUtil.as() ? Constants.ACTIVITY_URL_OVERSEA_BETA : Constants.ACTIVITY_URL_OVERSEA;
        }
        if (cebVar != null && cebVar.w() == 1) {
            return getGroupPkHtmlPath();
        }
        return getGroupPkHtmlPath() + Constants.ACTIVITY_URL_CN;
    }

    private static String getGroupPkHtmlPath() {
        return (CommonUtil.as() || CommonUtil.aq()) ? Constants.H5_URL_BASE_PATH_BETA : (CommonUtil.cc() || CommonUtil.bc()) ? Constants.H5_URL_BASE_PATH_TEST : Constants.H5_URL_BASE_PATH_RELEASE;
    }

    public static void setWebSettings(Context context, WebView webView, boolean z) {
        if (webView == null) {
            LogUtil.h(TAG, "webView is null");
            return;
        }
        WebSettings settings = webView.getSettings();
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        if (CommonUtil.aa(context)) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setSupportZoom(true);
        settings.setTextSize(WebSettings.TextSize.SMALLER);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(false);
        if (z) {
            settings.setTextZoom(100);
        }
    }

    public static String strBuilder(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(strArr.length * 16);
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String getOAuthAccessHost() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        if (m == sAppSiteId && !TextUtils.isEmpty(sOAuthAccessHost)) {
            return sOAuthAccessHost;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainAccessTokenUrl");
        sAppSiteId = m;
        sOAuthAccessHost = url;
        return url;
    }

    public static String getDomainMyHuaweiUpLogin() {
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        if (m == sAppSiteId && !TextUtils.isEmpty(sDomainMyHuaweiUpLogin)) {
            return sDomainMyHuaweiUpLogin;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainMyHuaweiUpLogin");
        sAppSiteId = m;
        sDomainMyHuaweiUpLogin = url;
        return url;
    }

    public static void initMyTabGrsUrl(ResultCallback resultCallback) {
        setTokenKeyUrlSp(BaseApplication.getContext(), GRSManager.a(BaseApplication.getContext()).getUrl("getToken"));
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainContentcenterDbankcdnNew");
        LogUtil.a(TAG, "miao host name = ", url);
        setActivityKeyUrlSp(BaseApplication.getContext(), url);
        setActivityPortalUrlSp(BaseApplication.getContext(), GRSManager.a(BaseApplication.getContext()).getUrl("activityUrl"));
        String url2 = GRSManager.a(BaseApplication.getContext()).getUrl("domainMVmall");
        String healthRecommendHost = getHealthRecommendHost();
        ArrayList arrayList = new ArrayList();
        arrayList.add(url2);
        arrayList.add(healthRecommendHost + "/miniShop/html/");
        arrayList.add(healthRecommendHost);
        arrayList.add(url);
        resultCallback.onResult(0, arrayList);
    }
}
