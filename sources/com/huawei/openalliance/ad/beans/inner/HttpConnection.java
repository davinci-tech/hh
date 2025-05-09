package com.huawei.openalliance.ad.beans.inner;

import android.text.TextUtils;
import com.huawei.openalliance.ad.utils.bl;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Connection;

/* loaded from: classes5.dex */
public class HttpConnection {
    private String connectionInfo;
    private Map<String, String> headers;
    private String host;
    private String ipAddress;

    public String c() {
        return this.ipAddress;
    }

    public String b() {
        return this.host;
    }

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        this.headers.put(str, str2);
    }

    public String a(String str) {
        if (bl.a(this.headers) || TextUtils.isEmpty(str)) {
            return null;
        }
        return this.headers.get(str);
    }

    public String a() {
        return this.connectionInfo;
    }

    public HttpConnection(Connection connection) {
        try {
            this.connectionInfo = connection.toString();
            this.host = connection.route().address().url().host();
            this.ipAddress = connection.route().socketAddress().getAddress().getHostAddress();
        } catch (Throwable unused) {
        }
    }

    public HttpConnection() {
    }
}
