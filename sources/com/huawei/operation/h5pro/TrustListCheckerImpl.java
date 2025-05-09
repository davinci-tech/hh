package com.huawei.operation.h5pro;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.webkit.trustlist.TrustListChecker;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.Utils;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.pluginbase.PluginBaseAdapter;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class TrustListCheckerImpl implements TrustListChecker {
    private static final String TAG = "TrustListCheckerImpl";
    private static List<String> sMountSpecialJsModuleUrl;
    private static volatile AtomicReferenceArray<String> sSpecProxyHttpRequestUrls;
    private static volatile List<String> sTrustedUrls;
    private static List<String> sUseSpecialJsApiUrl;
    private static String sWechatPayUrlPrefix;
    private static final Pattern PATTERN_XSS = Pattern.compile("javascript|&#|%26%23|%24|%60|(\\.{2}/)|[$()<>'`]");
    private static final List<String> sTrustedDeeplinkList = Arrays.asList("hiresearch_scheme://com.huawei.study.hiresearch", "huaweiwear://huaweiwear.app", "hms://redirect_uri", "heart_scheme://com.plagh.heartstudy", "androidamap://navi", "androidamap://viewMap", "baidumap://map", "bdapp://map", "qqmap://map", "hicloud://cloudDrive", "hiresearchscheme://com.huawei.study.hiresearch");

    @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
    public boolean isTrusted(H5ProAppInfo h5ProAppInfo, String str) {
        return isTrustedH5MiniProgram(h5ProAppInfo, str) || isTrustedUrl(str);
    }

    @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
    public boolean isTrustedToLoad(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        PluginBaseAdapter adapter = PluginOperation.getInstance(BaseApplication.getContext()).getAdapter();
        PluginOperationAdapter initAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : WebViewHelp.initAdapter();
        if (Utils.isHttpOrHttps(str)) {
            return Utils.isWhiteUrlLogicJudge(str, initAdapter);
        }
        return isTrustedDeeplink(initAdapter, str);
    }

    private boolean isTrustedDeeplink(PluginOperationAdapter pluginOperationAdapter, String str) {
        Iterator<String> it = sTrustedDeeplinkList.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return WebViewUtils.isDeepLinkWhiteUrl(pluginOperationAdapter, str);
    }

    @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
    public boolean isRequestProxyNeeded(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (sSpecProxyHttpRequestUrls == null) {
            String[] stringArray = BaseApplication.getContext().getResources().getStringArray(R.array._2130968700_res_0x7f04007c);
            if (stringArray == null || stringArray.length == 0) {
                return false;
            }
            sSpecProxyHttpRequestUrls = new AtomicReferenceArray<>(stringArray);
        }
        for (int i = 0; i < sSpecProxyHttpRequestUrls.length(); i++) {
            if (str.startsWith(sSpecProxyHttpRequestUrls.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
    public boolean isWeChatPayUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(sWechatPayUrlPrefix)) {
            sWechatPayUrlPrefix = BaseApplication.getContext().getResources().getString(R.string._2130851657_res_0x7f023749);
        }
        return !TextUtils.isEmpty(sWechatPayUrlPrefix) && str.startsWith(sWechatPayUrlPrefix);
    }

    public static boolean isTrustedH5MiniProgram(H5ProAppInfo h5ProAppInfo, String str) {
        if (h5ProAppInfo == null) {
            LogUtil.w(TAG, "isTrustedH5MiniProgram: h5ProAppInfo is null");
            return false;
        }
        if (h5ProAppInfo.getH5ProAppType() == H5ProAppInfo.H5ProAppType.H5_LIGHT_APP) {
            return false;
        }
        if (TextUtils.isEmpty(str) || !TextUtils.equals(h5ProAppInfo.getPkgName(), H5MiniProgramSecurityHelper.getMiniProgramPackageName(str))) {
            LogUtil.w(TAG, "isTrustedH5MiniProgram: invalid url");
            return false;
        }
        return H5MiniProgramSecurityHelper.isTrustedMiniProgram(h5ProAppInfo);
    }

    public static boolean isTrustedUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(TAG, "isTrusted: url is empty");
            return false;
        }
        initTrustedGrsUrl();
        Iterator<String> it = sTrustedUrls.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static void initTrustedGrsUrl() {
        synchronized (TrustListCheckerImpl.class) {
            if (sTrustedUrls != null) {
                return;
            }
            sTrustedUrls = new ArrayList(10);
            initTrustedGrsUrl(sTrustedUrls, "domainContentcenterDbankcdnNew", R.array._2130968712_res_0x7f040088);
            if (CommonUtil.cc()) {
                initTrustedGrsUrl(sTrustedUrls, "domainVenusVmall", R.array._2130968716_res_0x7f04008c);
            } else {
                initTrustedGrsUrl(sTrustedUrls, "domainVenusVmall", R.array._2130968715_res_0x7f04008b);
            }
            sTrustedUrls = Collections.unmodifiableList(sTrustedUrls);
        }
    }

    private static void initTrustedGrsUrl(List<String> list, String str, int i) {
        String[] stringArray = BaseApplication.getContext().getResources().getStringArray(i);
        if (stringArray == null || stringArray.length == 0) {
            return;
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl(str);
        if (TextUtils.isEmpty(url)) {
            LogUtil.w(TAG, "initTrustedGrsUrl: domain is empty, " + str);
        } else {
            for (String str2 : stringArray) {
                list.add(TextUtils.concat(url, str2).toString());
            }
        }
    }

    public static boolean containsXss(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(TAG, "containsXss: url is empty");
            return false;
        }
        return PATTERN_XSS.matcher(str.toLowerCase(Locale.ENGLISH)).find();
    }

    public static boolean isMountSpecialJsModule(boolean z, String str) {
        synchronized (TrustListCheckerImpl.class) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.w(TAG, "isMountSpecialJsModule: packageNameOrUrl is empty");
                return false;
            }
            if (isUseSpecialJsApi(z, str)) {
                return true;
            }
            if (sMountSpecialJsModuleUrl == null) {
                sMountSpecialJsModuleUrl = new ArrayList(16);
                GRSManager a2 = GRSManager.a(BaseApplication.getContext());
                String url = a2.getUrl("domainKlgMapDtlUrl");
                sMountSpecialJsModuleUrl.add(TextUtils.concat(url, "/hwtips/protection_setup/").toString());
                sMountSpecialJsModuleUrl.add(TextUtils.concat(url, "/handbook/SingleJumppage/protection_setup/").toString());
                sMountSpecialJsModuleUrl.add(TextUtils.concat(a2.getUrl("helpCustomerUrl"), "/handbook/SingleJumppage/protection_setup/").toString());
                sMountSpecialJsModuleUrl = Collections.unmodifiableList(sMountSpecialJsModuleUrl);
            }
            Iterator<String> it = sMountSpecialJsModuleUrl.iterator();
            while (it.hasNext()) {
                if (str.startsWith(it.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean isUseSpecialJsApi(boolean z, String str) {
        synchronized (TrustListCheckerImpl.class) {
            LogUtil.i(TAG, "isUseSpecialJsApi: isMiniProgram -> " + z);
            if (TextUtils.isEmpty(str)) {
                LogUtil.w(TAG, "isUseSpecialJsApi: packageNameOrUrl is empty");
                return false;
            }
            if (z) {
                return H5MiniProgramSecurityHelper.isTrustedMiniProgram(str);
            }
            if (isTrustedUrl(str)) {
                return true;
            }
            if (sUseSpecialJsApiUrl == null) {
                sUseSpecialJsApiUrl = new ArrayList(16);
                GRSManager a2 = GRSManager.a(BaseApplication.getContext());
                sUseSpecialJsApiUrl.add(TextUtils.concat(a2.getUrl("activityUrl"), "/web/html/").toString());
                String url = a2.getUrl("domainContentcenterDbankcdnNew");
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, "/cch5/health/activity/web/html/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, "/sandbox/cch5/health/activity/web/html/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, "/sandbox/cch5/testappCCH5/activity/web/html/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, Constants.ACTIVITY_URL_OVERSEA).toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, Constants.ACTIVITY_URL_OVERSEA_BETA).toString());
                String url2 = a2.getUrl("messageCenterUrl");
                sUseSpecialJsApiUrl.add(TextUtils.concat(url2, "/recommendH5/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url2, "/messageH5/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, "/sandbox/cch5/testappCCH5/groupChallenge/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, "/sandbox/cch5/health/groupChallenge/").toString());
                sUseSpecialJsApiUrl.add(TextUtils.concat(url, "/cch5/health/groupChallenge/").toString());
                sUseSpecialJsApiUrl = Collections.unmodifiableList(sUseSpecialJsApiUrl);
            }
            Iterator<String> it = sUseSpecialJsApiUrl.iterator();
            while (it.hasNext()) {
                if (str.startsWith(it.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isUseSpecialJsApi(H5ProInstance h5ProInstance) {
        if (h5ProInstance == null) {
            return false;
        }
        String url = h5ProInstance.getUrl();
        H5ProAppInfo appInfo = h5ProInstance.getAppInfo();
        if (appInfo != null && appInfo.getH5ProAppType() == H5ProAppInfo.H5ProAppType.H5_MINI_PROGRAM) {
            String pkgName = appInfo.getPkgName();
            if (TextUtils.isEmpty(url)) {
                return isUseSpecialJsApi(true, pkgName);
            }
            if (TextUtils.equals(pkgName, H5MiniProgramSecurityHelper.getMiniProgramPackageName(url))) {
                return isUseSpecialJsApi(true, pkgName);
            }
        }
        return isUseSpecialJsApi(false, url);
    }
}
