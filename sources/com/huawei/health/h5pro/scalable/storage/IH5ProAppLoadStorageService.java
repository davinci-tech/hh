package com.huawei.health.h5pro.scalable.storage;

import android.content.Context;

/* loaded from: classes.dex */
public interface IH5ProAppLoadStorageService {
    H5AppLoadStorageInfo getInfo(Context context, String str);

    int incrLoadCount(Context context, String str);

    int updatePreLoadTime(Context context, String str);
}
