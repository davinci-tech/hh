package com.huawei.health.h5pro.vengine;

import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatusListener;

/* loaded from: classes3.dex */
public interface H5ProPackageManager {
    H5ProAppInfo getAppInfo(String str);

    void installApp(String str, H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk);

    String joinUrl(String str);

    boolean preInstallHpk(String str);

    String readPreRequestsJson(String str);

    void setInstanceStatusListener(H5ProInstanceStatusListener h5ProInstanceStatusListener);
}
