package com.huawei.operation.h5pro.preload;

import android.content.Context;
import com.huawei.health.h5pro.preload.IH5PreloadStrategy;
import com.huawei.health.h5pro.scalable.storage.H5AppLoadStorageInfo;

/* loaded from: classes9.dex */
public class H5PreloadDietDiaryStrategy implements IH5PreloadStrategy {
    private final String[] h5Apps = {"com.huawei.health.h5.diet-diary", "com.huawei.health.h5.fasting-lite", "com.huawei.health.h5.custom-recipe"};

    @Override // com.huawei.health.h5pro.preload.IH5PreloadStrategy
    public boolean isPreLoadNow(Context context, String str) {
        H5ProAppLoadStorageServiceImpl h5ProAppLoadStorageServiceImpl = new H5ProAppLoadStorageServiceImpl();
        int i = 0;
        while (true) {
            String[] strArr = this.h5Apps;
            if (i >= strArr.length) {
                return false;
            }
            H5AppLoadStorageInfo info = h5ProAppLoadStorageServiceImpl.getInfo(context, strArr[i]);
            if (info != null && info.getCount() > 0) {
                return true;
            }
            i++;
        }
    }
}
