package com.huawei.health.h5pro.jsbridge.system.share;

import android.content.Context;
import com.huawei.health.h5pro.jsbridge.system.share.Share;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;

/* loaded from: classes.dex */
public interface ShareExtApi extends Share {
    void share(Context context, H5ProAppInfo h5ProAppInfo, String str, Share.ShareCallback shareCallback);
}
