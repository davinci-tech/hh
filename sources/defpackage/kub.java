package defpackage;

import com.huawei.agconnect.apms.instrument.apacheclient.ApacheClientInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwnetworkmodel.TrafficMonitoringService;
import com.huawei.hwnetworkmodel.WrappedHttpResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

/* loaded from: classes5.dex */
public class kub {
    private HttpClient c;

    public kub(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.c = new DefaultHttpClient(clientConnectionManager, httpParams);
    }

    private long d(HttpRequest httpRequest) {
        long length = httpRequest.getRequestLine().toString().length();
        for (Header header : httpRequest.getAllHeaders()) {
            length = length + header.getName().length() + header.getValue().length() + 1;
        }
        HttpParams params = httpRequest.getParams();
        for (Field field : params.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(params) instanceof HashMap) {
                    for (Map.Entry entry : ((HashMap) field.get(params)).entrySet()) {
                        length = length + entry.getKey().toString().length() + entry.getValue().toString().length() + 1;
                    }
                }
            } catch (IllegalAccessException unused) {
                LogUtil.b("LimitedHttpClient countRequest IllegalAccessException", new Object[0]);
            }
        }
        return httpRequest instanceof HttpPost ? length + ((HttpPost) httpRequest).getEntity().getContentLength() : length;
    }

    private long a(HttpResponse httpResponse) {
        int length = (int) (0 + httpResponse.getStatusLine().toString().length());
        for (Header header : httpResponse.getAllHeaders()) {
            length = length + header.getName().length() + header.getValue().length() + 1;
        }
        return length;
    }

    public HttpResponse a(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        TrafficMonitoringService.b(d(httpRequest));
        if (TrafficMonitoringService.e()) {
            throw new IOException("Mobile Over flow!");
        }
        HttpClient httpClient = this.c;
        HttpResponse execute = !(httpClient instanceof HttpClient) ? httpClient.execute(httpHost, httpRequest) : ApacheClientInstrumentation.execute(httpClient, httpHost, httpRequest);
        TrafficMonitoringService.e(a(execute));
        return new WrappedHttpResponse(execute);
    }

    public ClientConnectionManager b() {
        return this.c.getConnectionManager();
    }

    public HttpParams a() {
        return this.c.getParams();
    }
}
