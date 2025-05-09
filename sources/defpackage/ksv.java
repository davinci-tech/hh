package defpackage;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;

/* loaded from: classes5.dex */
public class ksv {
    public static RestClient c(Context context, String str) {
        RestClientGlobalInstance.getInstance().init(context);
        return new RestClient.Builder(context).baseUrl(str).build();
    }
}
