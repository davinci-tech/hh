package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpRequestInfo;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory.HttpProxyParamsFactory;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class jwq {
    private static final Map<HttpRequestInfo.CloudType, HttpProxyParamsFactory> b;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(HttpRequestInfo.CloudType.COMMON_CLOUD, new jwn());
        hashMap.put(HttpRequestInfo.CloudType.HEALTH_CLOUD, new jwp());
    }

    public static HttpProxyParamsFactory e(HttpRequestInfo.CloudType cloudType) {
        if (cloudType == null) {
            LogUtil.h("RequestParamFactoryConfig", "cloudType is null");
            return null;
        }
        return b.get(cloudType);
    }
}
