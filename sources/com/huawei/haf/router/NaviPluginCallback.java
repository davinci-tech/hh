package com.huawei.haf.router;

/* loaded from: classes.dex */
public interface NaviPluginCallback {
    void onPluginFound(Guidepost guidepost, String str);

    void onPluginLost(Guidepost guidepost, String str);

    void onPluginNone(Guidepost guidepost);
}
