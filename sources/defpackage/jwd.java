package defpackage;

import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.network.embedded.y;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpProxyInterface;
import com.huawei.networkclient.ResultCallback;
import java.util.Map;

/* loaded from: classes5.dex */
public class jwd implements HttpProxyInterface {
    private lqi e = lqi.a(d());

    private static HttpClient d() {
        RestClientGlobalInstance.getInstance().init(BaseApplication.getContext());
        return new HttpClient.Builder().connectTimeout(y.c).writeTimeout(5000).readTimeout(5000).build();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpProxyInterface
    public void post(String str, Map<String, String> map, String str2, ResultCallback<byte[]> resultCallback) {
        this.e.b(str, map, str2, byte[].class, resultCallback);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpProxyInterface
    public void get(String str, Map<String, String> map, Map<String, String> map2, ResultCallback<byte[]> resultCallback) {
        this.e.c(str, map, map2, byte[].class, resultCallback);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpProxyInterface
    public void put(String str, Map<String, String> map, String str2, ResultCallback<byte[]> resultCallback) {
        this.e.c(str, map, str2, byte[].class, resultCallback);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.httpproxy.HttpProxyInterface
    public void delete(String str, Map<String, String> map, ResultCallback<byte[]> resultCallback) {
        this.e.e(str, map, byte[].class, resultCallback);
    }
}
