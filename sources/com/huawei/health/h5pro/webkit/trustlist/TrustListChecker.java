package com.huawei.health.h5pro.webkit.trustlist;

import com.huawei.health.h5pro.vengine.H5ProAppInfo;

/* loaded from: classes.dex */
public interface TrustListChecker {
    boolean isRequestProxyNeeded(String str);

    boolean isTrusted(H5ProAppInfo h5ProAppInfo, String str);

    boolean isTrustedToLoad(String str);

    boolean isWeChatPayUrl(String str);
}
