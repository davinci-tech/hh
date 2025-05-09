package com.huawei.operation.h5pro.preload;

import android.content.Context;
import com.huawei.health.h5pro.preload.IH5PreloadStrategy;
import com.huawei.health.h5pro.scalable.H5ProScalableServiceManager;
import com.huawei.health.h5pro.scalable.storage.H5AppLoadStorageInfo;
import com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService;
import com.huawei.health.h5pro.utils.LogUtil;
import defpackage.grz;
import health.compact.a.BuildTypeConfig;

/* loaded from: classes.dex */
public class H5PreloadWeightStrategy implements IH5PreloadStrategy {
    private static final long H5_WEIGHT_PRELOAD_INTERVAL = 43200000;
    private static final long H5_WEIGHT_RELEASE_PRELOAD_INTERVAL = 2592000000L;
    private static final String TAG = "H5Pro_H5PreloadWeightStrategy";

    @Override // com.huawei.health.h5pro.preload.IH5PreloadStrategy
    public boolean isPreLoadNow(Context context, String str) {
        IH5ProAppLoadStorageService h5ProAppLoadStorageService;
        long currentTimeMillis = System.currentTimeMillis();
        if (grz.c(0L, currentTimeMillis) <= 0.0f || (h5ProAppLoadStorageService = H5ProScalableServiceManager.getInstance().getH5ProAppLoadStorageService()) == null) {
            return false;
        }
        H5AppLoadStorageInfo info = h5ProAppLoadStorageService.getInfo(context, str);
        LogUtil.i(TAG, "isPreLoadNow, info = " + info);
        if (info == null || (info.getPreLoadTime() == 0 && info.getLoadTime() == 0)) {
            return true;
        }
        return Math.min(currentTimeMillis - info.getPreLoadTime(), currentTimeMillis - info.getLoadTime()) >= (BuildTypeConfig.a() ? H5_WEIGHT_RELEASE_PRELOAD_INTERVAL : 43200000L);
    }
}
