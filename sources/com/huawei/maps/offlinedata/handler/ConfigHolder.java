package com.huawei.maps.offlinedata.handler;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.maps.offlinedata.handler.dto.ConfigParams;
import com.huawei.maps.offlinedata.jsbridge.b;
import com.huawei.maps.offlinedata.service.cloud.utils.f;
import com.huawei.maps.offlinedata.utils.a;
import com.huawei.maps.offlinedata.utils.d;

/* loaded from: classes5.dex */
public class ConfigHolder extends JsBaseModule {
    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        b.a().a(this.mH5ProInstance.getWebView());
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        if (com.huawei.maps.offlinedata.service.download.b.a().c().isEmpty() && com.huawei.maps.offlinedata.service.download.b.a().d().isEmpty()) {
            com.huawei.maps.offlinedata.service.network.b.a().c();
        }
    }

    @JavascriptInterface
    public void queryNetwork() {
        if (com.huawei.maps.offlinedata.service.network.b.a().b()) {
            return;
        }
        b.a().b("configHolder.network", String.valueOf(NetworkUtil.getNetworkType(a.a())));
    }

    @JavascriptInterface
    public String getConfig() {
        ConfigParams configParams = new ConfigParams();
        configParams.setApiKey(com.huawei.maps.offlinedata.service.config.a.a().e());
        configParams.setDeviceType(com.huawei.maps.offlinedata.service.config.a.a().f());
        configParams.setDataTypes(com.huawei.maps.offlinedata.service.config.a.a().h());
        configParams.setCloudServiceUrl(com.huawei.maps.offlinedata.service.config.a.a().i());
        configParams.setPolitical(com.huawei.maps.offlinedata.service.config.a.a().g());
        configParams.setDeviceName(com.huawei.maps.offlinedata.service.config.a.a().c());
        configParams.setVersionCode(String.valueOf(f.a()));
        configParams.setRecommendCityIds(com.huawei.maps.offlinedata.service.config.a.a().b());
        return d.a(configParams);
    }

    @JavascriptInterface
    public void accessTraceLogPusher(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "invalid param", j);
            return;
        }
        com.huawei.maps.offlinedata.logpush.dto.a aVar = (com.huawei.maps.offlinedata.logpush.dto.a) d.a(str, com.huawei.maps.offlinedata.logpush.dto.a.class);
        if (aVar == null) {
            com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "invalid param", j);
        } else {
            com.huawei.maps.offlinedata.logpush.b.a(aVar);
        }
    }
}
