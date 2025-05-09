package com.huawei.health.h5pro.preload;

import android.content.Context;
import com.huawei.health.h5pro.scalable.H5ProScalableServiceManager;
import com.huawei.health.h5pro.scalable.storage.H5AppLoadStorageInfo;
import com.huawei.health.h5pro.scalable.storage.IH5ProAppLoadStorageService;
import com.huawei.health.h5pro.utils.LogUtil;

/* loaded from: classes3.dex */
public class H5PreloadIntervalStrategy implements IH5PreloadStrategy {
    public long b;

    @Override // com.huawei.health.h5pro.preload.IH5PreloadStrategy
    public boolean isPreLoadNow(Context context, String str) {
        IH5ProAppLoadStorageService h5ProAppLoadStorageService = H5ProScalableServiceManager.getInstance().getH5ProAppLoadStorageService();
        if (h5ProAppLoadStorageService != null) {
            H5AppLoadStorageInfo info = h5ProAppLoadStorageService.getInfo(context, str);
            LogUtil.i("H5PRO_H5PreloadIntervalStrategy", "isPreLoadNow, info=" + info);
            long currentTimeMillis = System.currentTimeMillis();
            if (info == null || ((info.getPreLoadTime() <= 0 && info.getLoadTime() <= 0) || (currentTimeMillis - info.getPreLoadTime() >= this.b && currentTimeMillis - info.getLoadTime() >= this.b))) {
                return true;
            }
        }
        return false;
    }

    public H5PreloadIntervalStrategy(long j) {
        this.b = j;
    }
}
