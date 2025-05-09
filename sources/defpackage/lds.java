package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.linkage.sportlinkage.AppLinkageApi;
import com.huawei.linkage.sportlinkage.LinkagePlatformApi;
import health.compact.a.Services;

/* loaded from: classes5.dex */
public class lds {
    public static LinkagePlatformApi c() {
        LogUtil.a("INKAGE_LinkageApiManager", "getReverseLinkApi");
        return (LinkagePlatformApi) Services.a("LinkagePlatform", LinkagePlatformApi.class);
    }

    public static AppLinkageApi a() {
        LogUtil.a("INKAGE_LinkageApiManager", "getAppLinkageApi");
        return (AppLinkageApi) Services.a("SportService", AppLinkageApi.class);
    }
}
