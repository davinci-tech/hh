package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class giu implements IRequest {
    private Map<String, String> d = new HashMap();

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("domainThemeCloud") + "/servicesupport/theme/ttdservice/service/v1/product/querybytype?";
    }

    public Map<String, String> a() {
        return this.d;
    }

    public void e(Map<String, String> map) {
        this.d = map;
    }
}
