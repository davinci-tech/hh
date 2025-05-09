package com.huawei.appgallery.marketinstallerservice.api;

/* loaded from: classes3.dex */
public interface InstallCallback {
    void onFailed(FailResultParam failResultParam);

    void onSuccess(MarketInfo marketInfo);
}
