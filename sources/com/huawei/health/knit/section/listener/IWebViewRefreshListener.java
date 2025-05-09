package com.huawei.health.knit.section.listener;

import com.huawei.health.h5pro.core.H5ProWebView;

/* loaded from: classes3.dex */
public interface IWebViewRefreshListener {
    void notifyDataChanged(long j);

    void notifyWebViewLoad(H5ProWebView h5ProWebView);
}
