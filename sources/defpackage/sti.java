package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.ToStringConverterFactory;
import com.huawei.hms.framework.network.restclient.converter.gson.GsonConverterFactory;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes7.dex */
public class sti {
    private static Map<Integer, RestClient> d = new ConcurrentHashMap();
    private static final Lock e = new ReentrantLock();

    public static RestClient c(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        RestClient restClient = d.get(Integer.valueOf(str.hashCode()));
        if (restClient != null) {
            return restClient;
        }
        Lock lock = e;
        lock.lock();
        try {
            RestClient restClient2 = d.get(Integer.valueOf(str.hashCode()));
            if (restClient2 == null) {
                RestClient build = new RestClient.Builder(context).baseUrl(str).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(GsonConverterFactory.create()).httpClient(new HttpClient.Builder().retryTimeOnConnectionFailure(0).connectTimeout(60000).readTimeout(60000).hostnameVerifier(SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER).build()).build();
                d.put(Integer.valueOf(str.hashCode()), build);
                lock.unlock();
                return build;
            }
            lock.unlock();
            return restClient2;
        } catch (Throwable th) {
            e.unlock();
            throw th;
        }
    }
}
