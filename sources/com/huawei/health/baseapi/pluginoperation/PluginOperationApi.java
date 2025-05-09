package com.huawei.health.baseapi.pluginoperation;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.pluginbase.PluginBaseAdapter;

/* loaded from: classes.dex */
public interface PluginOperationApi {
    Intent createWebViewIntent(Context context, Bundle bundle, Integer num);

    void functionEntrance(String str, boolean z);

    PluginBaseAdapter getAdapter(Context context);

    Class<? extends JsModuleBase> getCommonJsModule(String str);

    Class<? extends JsModuleBase> getJsInteraction();

    void initAdapter(Context context, PluginBaseAdapter pluginBaseAdapter);

    void initH5Pro();

    void initThirdDeviceH5Pro(String str, ContentValues contentValues, String str2);

    boolean isSuperTrustedMiniProgram(String str, String str2);

    void launchH5Compact(Context context, String str, boolean z);

    void loadEmbeddedH5(H5ProWebView h5ProWebView, String str, H5ProLaunchOption.Builder builder);

    void loadH5ProApp(Context context, String str, H5ProLaunchOption.Builder builder);

    void putBiEventFromH5Deeplink(String str, String str2);

    void startOperationWebPage(Context context, String str);

    boolean switchToMarketingTwo();
}
