package com.huawei.health.h5pro.jsbridge;

import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.system.analyzer.AnalyzerEntry;
import com.huawei.health.h5pro.jsbridge.system.container.WebViewControlEntry;
import com.huawei.health.h5pro.jsbridge.system.intl.IntlEntry;
import com.huawei.health.h5pro.jsbridge.system.logger.LoggerEntry;
import com.huawei.health.h5pro.jsbridge.system.media.MediaEntry;
import com.huawei.health.h5pro.jsbridge.system.permission.PermissionEntry;
import com.huawei.health.h5pro.jsbridge.system.servicebus.ServiceBusEntry;
import com.huawei.health.h5pro.jsbridge.system.share.ShareEntry;
import com.huawei.health.h5pro.jsbridge.system.shareplus.SharePlusEntry;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageEntry;
import com.huawei.health.h5pro.jsbridge.system.uikit.UiKitEntry;
import com.huawei.health.h5pro.jsbridge.system.util.UtilEntry;
import com.huawei.operation.beans.TitleBean;

/* loaded from: classes3.dex */
public enum H5ProJsModule {
    MODULE_LOGGER("logger", LoggerEntry.class),
    MODULE_SHARE(TitleBean.RIGHT_BTN_TYPE_SHARE, ShareEntry.class),
    MODULE_MEDIA("media", MediaEntry.class),
    MODULE_SHAREPLUS("shareplus", SharePlusEntry.class),
    MODULE_STORAGE("storage", StorageEntry.class),
    MODULE_UIKIT("uikit", UiKitEntry.class),
    MODULE_UTIL("util", UtilEntry.class),
    MODULE_SERVICEBUS("servicebus", ServiceBusEntry.class),
    MODULE_ANALYZER("analyzer", AnalyzerEntry.class),
    MODULE_PERMISSION("permission", PermissionEntry.class),
    MODULE_INTL("intl", IntlEntry.class),
    MODULE_WEBVIEW_CONTROL("WebViewControl", WebViewControlEntry.class);

    public Class<? extends JsBaseModule> moduleClass;
    public String moduleName;

    H5ProJsModule(String str, Class cls) {
        this.moduleName = str;
        this.moduleClass = cls;
    }
}
