package defpackage;

import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.healthheadlines.HealthHeadLinesJsApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;

@ApiDefine(uri = HealthHeadLinesJsApi.class)
@Singleton
/* loaded from: classes6.dex */
public class oks implements HealthHeadLinesJsApi {
    @Override // com.huawei.health.healthheadlines.HealthHeadLinesJsApi
    public Class<? extends JsModuleBase> getCommonJsModule(String str) {
        if ("healthHeadlines".equals(str)) {
            LogUtil.a("HealthHeadLinesImpl", "getCommonJsModule success");
            return okx.class;
        }
        LogUtil.h("HealthHeadLinesImpl", "unknown JsModule:", str);
        return JsModuleBase.class;
    }
}
