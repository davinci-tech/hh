package com.huawei.openalliance.ad.net.http;

import com.huawei.hms.network.embedded.g3;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.openalliance.ad.utils.cz;
import java.util.WeakHashMap;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* loaded from: classes5.dex */
public class d extends EventListener implements i {

    /* renamed from: a, reason: collision with root package name */
    private WeakHashMap<Request, HttpConnection> f7303a;

    @Override // okhttp3.EventListener
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
        if (connection == null) {
            return;
        }
        HttpConnection httpConnection = new HttpConnection(connection);
        ho.b(g3.h, "address:" + cz.f(httpConnection.a()));
        Request request = call.request();
        if (request == null) {
            return;
        }
        if (this.f7303a == null) {
            this.f7303a = new WeakHashMap<>();
        }
        ho.b(g3.h, "size: %s", Integer.valueOf(this.f7303a.size()));
        this.f7303a.put(request, httpConnection);
    }

    @Override // com.huawei.openalliance.ad.net.http.i
    public OkHttpClient.Builder a(OkHttpClient.Builder builder) {
        if (builder == null) {
            return null;
        }
        return builder.eventListener(this);
    }

    @Override // com.huawei.openalliance.ad.net.http.i
    public HttpConnection a(Request request) {
        HttpConnection httpConnection = (bl.a(this.f7303a) || request == null || !this.f7303a.containsKey(request)) ? null : this.f7303a.get(request);
        return httpConnection == null ? new HttpConnection() : httpConnection;
    }
}
