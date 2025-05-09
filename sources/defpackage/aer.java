package defpackage;

import android.content.Context;
import com.huawei.appgallery.agd.api.AgdApiClient;
import com.huawei.appgallery.agd.api.ConnectionResult;
import com.huawei.appgallery.agd.internal.support.log.AgdLog;
import com.huawei.appgallery.agd.internalapi.CrossClientApi;
import com.huawei.appgallery.coreservice.api.ApiClient;
import com.huawei.appgallery.coreservice.api.CoreServiceApi;
import com.huawei.appgallery.coreservice.api.IConnectionResult;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public class aer implements AgdApiClient {

    /* renamed from: a, reason: collision with root package name */
    private final Context f190a;
    private final ApiClient b;
    private final Set<AgdApiClient.ConnectionCallbacks> e;
    private Runnable g;
    private boolean d = false;
    private boolean c = false;
    private boolean j = false;

    @Override // com.huawei.appgallery.agd.api.AgdApiClient, com.huawei.appgallery.coreservice.api.ApiClient
    public boolean isConnecting() {
        return b() && this.j;
    }

    @Override // com.huawei.appgallery.agd.api.AgdApiClient, com.huawei.appgallery.coreservice.api.ApiClient
    public boolean isConnected() {
        return this.c || this.d;
    }

    @Override // com.huawei.appgallery.coreservice.api.ApiClient
    public ApiClient getDelegate() {
        return this.b;
    }

    @Override // com.huawei.appgallery.agd.api.AgdApiClient, com.huawei.appgallery.coreservice.api.ApiClient
    public Context getContext() {
        return this.f190a;
    }

    @Override // com.huawei.appgallery.agd.api.AgdApiClient, com.huawei.appgallery.coreservice.api.ApiClient
    public void disconnect() {
        if (this.d) {
            Iterator<AgdApiClient.ConnectionCallbacks> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().onConnectionSuspended(0);
            }
        }
        this.d = false;
        this.b.disconnect();
        this.c = false;
        this.j = false;
    }

    @Override // com.huawei.appgallery.agd.api.AgdApiClient, com.huawei.appgallery.coreservice.api.ApiClient
    public void connect() {
        if (!CrossClientApi.needCrossClient(this.f190a)) {
            AgdLog.LOG.i("AgdApiClientImpl", "[AG connect]start to connect");
            this.j = true;
            this.b.connect();
            return;
        }
        this.d = true;
        Set<AgdApiClient.ConnectionCallbacks> set = this.e;
        if (set != null) {
            Iterator<AgdApiClient.ConnectionCallbacks> it = set.iterator();
            while (it.hasNext()) {
                it.next().onConnected();
            }
        }
        AgdLog.LOG.i("AgdApiClientImpl", "AG not exist, connect by cross client");
    }

    class a implements ApiClient.ConnectionCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Set f191a;

        @Override // com.huawei.appgallery.coreservice.api.ApiClient.ConnectionCallback
        public void onConnectionSuspended(int i) {
            aer.this.a(false);
            if (aer.this.d) {
                return;
            }
            Iterator it = this.f191a.iterator();
            while (it.hasNext()) {
                ((AgdApiClient.ConnectionCallbacks) it.next()).onConnectionSuspended(i);
            }
        }

        @Override // com.huawei.appgallery.coreservice.api.ApiClient.ConnectionCallback
        public void onConnectionFailed(IConnectionResult iConnectionResult) {
            aer.this.a(false);
            if (aer.this.d) {
                return;
            }
            Iterator it = this.f191a.iterator();
            while (it.hasNext()) {
                ((AgdApiClient.ConnectionCallbacks) it.next()).onConnectionFailed(new ConnectionResult(iConnectionResult));
            }
        }

        @Override // com.huawei.appgallery.coreservice.api.ApiClient.ConnectionCallback
        public void onConnected() {
            aer.this.a(true);
            if (aer.this.d) {
                return;
            }
            Iterator it = this.f191a.iterator();
            while (it.hasNext()) {
                ((AgdApiClient.ConnectionCallbacks) it.next()).onConnected();
            }
        }

        a(Set set) {
            this.f191a = set;
        }
    }

    private boolean b() {
        return CoreServiceApi.getAppGalleryPkg(this.f190a) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.c = z;
        this.j = false;
        Runnable runnable = this.g;
        if (runnable == null || !z) {
            return;
        }
        runnable.run();
        this.g = null;
    }

    public aer(ApiClient.Builder builder, Context context, Set<AgdApiClient.ConnectionCallbacks> set) {
        builder.addConnectionCallbacks(new a(set));
        this.b = builder.build();
        this.f190a = context;
        this.e = set;
        if (CrossClientApi.needCrossClient(context)) {
            CrossClientApi.init(context);
        }
    }
}
