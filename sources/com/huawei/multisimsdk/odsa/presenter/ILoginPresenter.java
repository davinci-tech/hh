package com.huawei.multisimsdk.odsa.presenter;

import android.content.Intent;

/* loaded from: classes5.dex */
public interface ILoginPresenter {
    String getTokenValue(String str);

    void onDestroy();

    void onStart();

    void onStop();

    void processAuthResult(int i, int i2, Intent intent);

    void processAuthResultFromWebView(String str);

    void setUrlWithAuthorizationCodeFromWebView(String str);

    void startLogin();
}
