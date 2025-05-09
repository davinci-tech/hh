package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.grs.GrsUtils;
import com.huawei.hms.framework.network.restclient.hwhttp.plugin.BasePlugin;
import com.huawei.hms.framework.network.restclient.hwhttp.plugin.PluginInterceptor;
import java.io.IOException;

@Deprecated
/* loaded from: classes9.dex */
public class GrsManager implements BasePlugin {
    private static final int KEY_INDEX = 1;
    private static final int SERVICE_INDEX = 0;
    private static final String TAG = "GrsManager";
    private static volatile GrsManager instance;
    private GrsConfig grsConfig;
    private GrsInterceptor grsInterceptor;

    @Deprecated
    public boolean initGrs(Context context, GrsConfig grsConfig) {
        GrsConfig grsConfig2 = this.grsConfig;
        if (grsConfig2 != null && grsConfig2.equal(grsConfig)) {
            return true;
        }
        this.grsConfig = grsConfig;
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        GrsConfig grsConfig3 = this.grsConfig;
        GrsApi.grsSdkInit(applicationContext, grsConfig3 != null ? grsConfig3.getGrsBaseInfo(applicationContext) : null);
        return true;
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.plugin.BasePlugin
    @Deprecated
    public PluginInterceptor getInterceptor() {
        if (this.grsInterceptor == null) {
            this.grsInterceptor = new GrsInterceptor();
        }
        return this.grsInterceptor;
    }

    @Deprecated
    public static String parseGrs(String str) {
        String[] parseParams = GrsUtils.parseParams(str);
        String serviceNameUrl = getServiceNameUrl(parseParams[0], parseParams[1]);
        if (TextUtils.isEmpty(serviceNameUrl)) {
            throw new IOException("can not get url, do grsUrl(serviceName or key) error?");
        }
        return GrsUtils.fixResult(parseParams, serviceNameUrl);
    }

    @Deprecated
    public static boolean isGRSSchema(String str) {
        return GrsUtils.isGRSSchema(str);
    }

    @Deprecated
    public static String getServiceNameUrl(String str, String str2) {
        return GrsApi.synGetGrsUrl(str, str2);
    }

    @Deprecated
    public static GrsManager getInstance() {
        if (instance == null) {
            synchronized (GrsManager.class) {
                if (instance == null) {
                    instance = new GrsManager();
                }
            }
        }
        return instance;
    }

    @Deprecated
    private GrsManager() {
    }
}
