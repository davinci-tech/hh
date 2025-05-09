package defpackage;

import android.content.Context;
import com.huawei.appgallery.coreservice.api.ApiClient;
import com.huawei.appgallery.coreservice.api.ConnectConfig;
import com.huawei.appgallery.coreservice.c;
import com.huawei.appgallery.coreservice.e;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class aeo implements ApiClient {

    /* renamed from: a, reason: collision with root package name */
    private Context f188a;
    private final Set<ApiClient.ConnectionCallback> b;
    private e d;

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public ApiClient getDelegate() {
        return null;
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public boolean isConnecting() {
        return this.d.e();
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public boolean isConnected() {
        return this.d.b();
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public Context getContext() {
        return this.f188a;
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public void disconnect() {
        this.d.a();
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public void connect() {
        this.d.c(this.b);
    }

    public c a() {
        return this.d;
    }

    public aeo(Context context, Set<ApiClient.ConnectionCallback> set, ConnectConfig connectConfig) {
        HashSet hashSet = new HashSet();
        this.b = hashSet;
        this.f188a = context.getApplicationContext();
        hashSet.addAll(set);
        e eVar = new e(context);
        this.d = eVar;
        eVar.a(connectConfig);
    }
}
