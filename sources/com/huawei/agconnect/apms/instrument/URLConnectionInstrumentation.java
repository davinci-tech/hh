package com.huawei.agconnect.apms.instrument;

import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.zab;
import com.huawei.agconnect.apms.zyx;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes.dex */
public class URLConnectionInstrumentation {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();

    private URLConnectionInstrumentation() {
    }

    private static URLConnection getURLConnection(URLConnection uRLConnection) {
        if (uRLConnection != null && !Agent.isDisabled()) {
            try {
                if (uRLConnection instanceof HttpsURLConnection) {
                    return new zyx((HttpsURLConnection) uRLConnection);
                }
                if (uRLConnection instanceof HttpURLConnection) {
                    return new zab((HttpURLConnection) uRLConnection);
                }
            } catch (Throwable th) {
                LOG.warn("skipping APMS URLConnection proxy: " + th.getMessage());
            }
        }
        return uRLConnection;
    }

    public static URLConnection openConnection(URLConnection uRLConnection) {
        return getURLConnection(uRLConnection);
    }

    public static URLConnection openConnectionWithProxy(URLConnection uRLConnection) {
        return getURLConnection(uRLConnection);
    }
}
