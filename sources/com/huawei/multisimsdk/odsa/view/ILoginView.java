package com.huawei.multisimsdk.odsa.view;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public interface ILoginView {
    public static final int REQUEST_CODE_AUTH = 1;

    void finishView();

    Context getAppContext();

    void loadUrl(String str);

    void startAuthActivity(Intent intent, int i);
}
