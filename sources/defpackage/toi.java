package defpackage;

import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.client.ServiceConnectionListener;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public class toi {

    /* renamed from: a, reason: collision with root package name */
    private static volatile toi f17279a;
    private volatile ServiceConnectionListener d;
    private volatile ServiceConnectCallback c = new ServiceConnectCallback.Stub() { // from class: toi.1
        @Override // com.huawei.wearengine.connect.ServiceConnectCallback
        public void onServiceDisconnect() {
            if (toi.this.d != null) {
                toi.this.d.onServiceDisconnect();
            }
        }
    };
    private tok e = new tok();

    private toi(ServiceConnectionListener serviceConnectionListener) {
        this.d = serviceConnectionListener;
    }

    public static toi b(ServiceConnectionListener serviceConnectionListener) {
        if (f17279a == null) {
            synchronized (toi.class) {
                if (f17279a == null) {
                    f17279a = new toi(serviceConnectionListener);
                }
            }
        }
        return f17279a;
    }

    public Task<Void> a() {
        WearEngineClientInner.c().d(this.d);
        return Tasks.callInBackground(new Callable<Void>() { // from class: toi.4
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Void call() {
                int registerConnectCallback = toi.this.e.registerConnectCallback(toi.this.c);
                if (registerConnectCallback == 0) {
                    return null;
                }
                throw new tnx(registerConnectCallback);
            }
        });
    }

    public Task<Void> d() {
        WearEngineClientInner.c().e();
        return Tasks.callInBackground(new Callable<Void>() { // from class: toi.3
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                int unregisterConnectCallback = toi.this.e.unregisterConnectCallback(toi.this.c);
                if (unregisterConnectCallback == 0) {
                    return null;
                }
                throw new tnx(unregisterConnectCallback);
            }
        });
    }

    public Task<Void> b() {
        return Tasks.callInBackground(new Callable<Void>() { // from class: toi.5
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                int releaseConnection = toi.this.e.releaseConnection();
                if (releaseConnection == 0) {
                    return null;
                }
                throw new tnx(releaseConnection);
            }
        });
    }
}
