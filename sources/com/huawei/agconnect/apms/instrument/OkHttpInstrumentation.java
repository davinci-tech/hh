package com.huawei.agconnect.apms.instrument;

import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.zab;
import com.huawei.agconnect.apms.zyx;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes8.dex */
public class OkHttpInstrumentation {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();

    private static HttpURLConnection getHttpURLConnection(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null && !Agent.isDisabled()) {
            try {
                return httpURLConnection instanceof HttpsURLConnection ? new zyx((HttpsURLConnection) httpURLConnection) : new zab(httpURLConnection);
            } catch (Throwable th) {
                LOG.warn("skipping APMS OkHttp proxy: " + th.getMessage());
            }
        }
        return httpURLConnection;
    }

    public static HttpURLConnection open(HttpURLConnection httpURLConnection) {
        return getHttpURLConnection(httpURLConnection);
    }

    public static HttpURLConnection openWithProxy(HttpURLConnection httpURLConnection) {
        return getHttpURLConnection(httpURLConnection);
    }

    public static HttpURLConnection urlFactoryOpen(HttpURLConnection httpURLConnection) {
        return getHttpURLConnection(httpURLConnection);
    }
}
