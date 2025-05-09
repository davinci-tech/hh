package com.huawei.health.configuredpage.api;

import android.widget.LinearLayout;

/* loaded from: classes3.dex */
public interface ConfiguredPageApi {
    void initOperationConfiguredPage(int i, LinearLayout linearLayout, ConfiguredPageDataCallback configuredPageDataCallback);

    void refreshConfiguredPageUi(int i, LinearLayout linearLayout);
}
