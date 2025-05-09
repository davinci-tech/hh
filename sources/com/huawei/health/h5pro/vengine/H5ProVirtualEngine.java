package com.huawei.health.h5pro.vengine;

import android.content.Context;
import android.webkit.WebView;

/* loaded from: classes3.dex */
public interface H5ProVirtualEngine {
    H5ProInstance createInstance(Context context, H5ProAppLoadListener h5ProAppLoadListener);

    H5ProInstance createInstance(WebView webView, H5ProAppLoadListener h5ProAppLoadListener);

    void registerJsModule(String str, Object obj);
}
