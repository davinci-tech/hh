package com.huawei.health.h5pro.webkit;

import android.view.View;
import android.webkit.WebChromeClient;

/* loaded from: classes3.dex */
public interface WebChromeCustomViewListener {
    void onHideCustomView();

    void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback);
}
