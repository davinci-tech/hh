package com.huawei.health.h5pro.webkit.trustlist;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.webkit.WebKitUtil;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class H5ProTrustListChecker {

    /* renamed from: a, reason: collision with root package name */
    public static TrustListCheckerProxy f2477a;

    /* loaded from: classes3.dex */
    public static class TrustListCheckerProxy implements TrustListChecker {
        public static final Pattern d = Pattern.compile("javascript|&#|%26%23|%24|%60|(\\.{2}/)|[$()<>'`]");
        public final TrustListChecker e;

        @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
        public boolean isWeChatPayUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                return this.e.isWeChatPayUrl(str);
            }
            LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isWeChatPayUrl: url is null");
            return false;
        }

        @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
        public boolean isTrustedToLoad(String str) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrustedToLoad: url is null");
                return false;
            }
            if (d(str)) {
                LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrustedToLoad: url cannot contain Xss");
                return false;
            }
            if (!H5ProTrustListChecker.disableHttpLoad(str)) {
                return this.e.isTrustedToLoad(str);
            }
            LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrustedToLoad: disableHttpLoad");
            return false;
        }

        @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
        public boolean isTrusted(H5ProAppInfo h5ProAppInfo, String str) {
            if (this.e == null) {
                LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrusted: Checker not implemented");
                return false;
            }
            if (h5ProAppInfo == null || TextUtils.isEmpty(str)) {
                LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrusted: h5ProAppInfo or url is null");
                return false;
            }
            if (d(str)) {
                LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrusted: url cannot contain Xss");
                return false;
            }
            if (!H5ProTrustListChecker.disableHttpLoad(str)) {
                return this.e.isTrusted(h5ProAppInfo, str);
            }
            LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isTrusted: disableHttpLoad");
            return false;
        }

        @Override // com.huawei.health.h5pro.webkit.trustlist.TrustListChecker
        public boolean isRequestProxyNeeded(String str) {
            if (!TextUtils.isEmpty(str)) {
                return this.e.isRequestProxyNeeded(str);
            }
            LogUtil.w("H5PRO_H5ProTrustListChecker", "TrustListCheckerProxy#isRequestProxyNeeded: url is null");
            return false;
        }

        private boolean d(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return d.matcher(str.toLowerCase(Locale.ENGLISH)).find();
        }

        public TrustListCheckerProxy(TrustListChecker trustListChecker) {
            this.e = trustListChecker;
        }
    }

    public static void setTrustListChecker(TrustListChecker trustListChecker) {
        LogUtil.i("H5PRO_H5ProTrustListChecker", "setTrustListChecker");
        f2477a = new TrustListCheckerProxy(trustListChecker);
    }

    public static boolean isWeChatPayUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.w("H5PRO_H5ProTrustListChecker", "isWeChatPayUrl#isWeChatPayUrl: sTrustListCheckerProxy is null");
            return false;
        }
        TrustListCheckerProxy trustListCheckerProxy = f2477a;
        if (trustListCheckerProxy != null) {
            return trustListCheckerProxy.isWeChatPayUrl(str);
        }
        LogUtil.w("H5PRO_H5ProTrustListChecker", "isWeChatPayUrl: sTrustListCheckerProxy is null");
        return false;
    }

    public static boolean isTrustedToLoad(String str) {
        TrustListCheckerProxy trustListCheckerProxy = f2477a;
        if (trustListCheckerProxy != null) {
            return trustListCheckerProxy.isTrustedToLoad(str);
        }
        LogUtil.w("H5PRO_H5ProTrustListChecker", "isTrustedToLoad: sTrustListCheckerProxy is null");
        return false;
    }

    public static boolean isTrusted(H5ProInstance h5ProInstance) {
        if (h5ProInstance != null) {
            return isTrusted(h5ProInstance.getAppInfo(), h5ProInstance.getUrl());
        }
        LogUtil.w("H5PRO_H5ProTrustListChecker", "isTrusted: h5ProInstance is null");
        return false;
    }

    public static boolean isTrusted(H5ProAppInfo h5ProAppInfo, String str) {
        TrustListCheckerProxy trustListCheckerProxy = f2477a;
        if (trustListCheckerProxy != null) {
            return trustListCheckerProxy.isTrusted(h5ProAppInfo, str);
        }
        LogUtil.w("H5PRO_H5ProTrustListChecker", "isTrusted: sTrustListCheckerProxy is null");
        return false;
    }

    public static boolean isRequestProxyNeeded(String str) {
        TrustListCheckerProxy trustListCheckerProxy = f2477a;
        if (trustListCheckerProxy != null) {
            return trustListCheckerProxy.isRequestProxyNeeded(str);
        }
        LogUtil.w("H5PRO_H5ProTrustListChecker", "isRequestProxyNeeded: sTrustListCheckerProxy is null");
        return false;
    }

    public static boolean disableHttpLoad(String str) {
        if (EnvironmentHelper.getInstance().getBuildType() == EnvironmentHelper.BuildType.RELEASE || EnvironmentHelper.getInstance().getBuildType() == EnvironmentHelper.BuildType.BETA) {
            return WebKitUtil.isHttpUrl(str);
        }
        return false;
    }
}
