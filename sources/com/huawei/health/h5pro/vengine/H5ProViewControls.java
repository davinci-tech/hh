package com.huawei.health.h5pro.vengine;

import android.view.View;

/* loaded from: classes3.dex */
public interface H5ProViewControls {
    H5ProNativeView getNativeView(String str);

    void removeAllNativeView();

    void removeNativeView(String str);

    @Deprecated
    String showNativeView(View view);

    String showNativeView(H5ProNativeView h5ProNativeView);

    void updateNativeView(String str, H5ProNativeView h5ProNativeView);
}
