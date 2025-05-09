package com.huawei.health.h5pro.jsbridge.system.container;

import com.huawei.health.h5pro.core.ImmerseInfo;

/* loaded from: classes3.dex */
public interface H5Container {
    void exit();

    void exitWithResult(String str);

    void goBack();

    void hideTitleBarIcon(String str);

    void keepScreenOn(boolean z);

    void registryTitleBarCallback(long j, String str);

    void setImmerse(ImmerseInfo immerseInfo);

    void setPageTitle(String str);

    void setScreenLandscape(boolean z);
}
