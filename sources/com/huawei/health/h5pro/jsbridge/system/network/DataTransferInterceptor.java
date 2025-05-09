package com.huawei.health.h5pro.jsbridge.system.network;

import com.huawei.health.h5pro.vengine.H5ProAppInfo;

/* loaded from: classes.dex */
public interface DataTransferInterceptor {
    RequestObj dataIntercept(H5ProAppInfo h5ProAppInfo, String str, RequestObj requestObj);
}
