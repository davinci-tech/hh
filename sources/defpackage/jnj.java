package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.ToStringConverterFactory;
import com.huawei.hms.framework.network.restclient.converter.gson.GsonConverterFactory;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.secure.android.common.SecureSSLSocketFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public class jnj {
    private static Map<Integer, RestClient> e = new ConcurrentHashMap(16);
    private static final Lock d = new ReentrantLock();

    public static RestClient e(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        Lock lock = d;
        lock.lock();
        try {
            RestClient restClient = e.get(Integer.valueOf(str.hashCode()));
            if (restClient == null) {
                RestClient build = new RestClient.Builder(context).baseUrl(str).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(GsonConverterFactory.create()).httpClient(new HttpClient.Builder().retryTimeOnConnectionFailure(0).connectTimeout(60000).readTimeout(60000).hostnameVerifier(SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER).build()).build();
                e.put(Integer.valueOf(str.hashCode()), build);
                lock.unlock();
                return build;
            }
            lock.unlock();
            return restClient;
        } catch (Throwable th) {
            d.unlock();
            throw th;
        }
    }
}
