package com.huawei.browse;

import android.content.Context;

/* loaded from: classes.dex */
public interface BrowsingBiEvent {
    void loginBeforeBiEvent(String str);

    void loginSuccessBiEvent();

    void showFullServiceDialog(Context context);

    void updateCountryCodeAndUserId();
}
