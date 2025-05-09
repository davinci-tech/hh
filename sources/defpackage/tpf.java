package defpackage;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes9.dex */
public class tpf {

    /* renamed from: a, reason: collision with root package name */
    private RemoteCallbackList<ServiceConnectCallback> f17320a;
    private ExecutorService c;
    private RemoteCallbackList<ServiceConnectCallback> d;

    private tpf() {
        this.c = Executors.newSingleThreadExecutor();
        this.f17320a = new RemoteCallbackList<>();
        this.d = new RemoteCallbackList<>();
    }

    static class e {
        private static final tpf d = new tpf();
    }

    public static tpf b() {
        return e.d;
    }

    public int c(ServiceConnectCallback serviceConnectCallback) {
        if (serviceConnectCallback == null) {
            tos.e("ServiceConnectionCallbackManager", "registerOtaServiceConnectCallback connectCallback is null");
            return 5;
        }
        if (!this.f17320a.register(serviceConnectCallback)) {
            tos.e("ServiceConnectionCallbackManager", "registerOtaServiceConnectCallback register failed");
            return 12;
        }
        tos.a("ServiceConnectionCallbackManager", "after registerOtaServiceConnectCallback, size:" + this.f17320a.getRegisteredCallbackCount());
        return 0;
    }

    public int b(ServiceConnectCallback serviceConnectCallback) {
        if (serviceConnectCallback == null) {
            tos.e("ServiceConnectionCallbackManager", "registerConnectCallback connectCallback is null");
            return 5;
        }
        if (!this.d.register(serviceConnectCallback)) {
            tos.e("ServiceConnectionCallbackManager", "registerConnectCallback register failed");
            return 12;
        }
        tos.a("ServiceConnectionCallbackManager", "after registerConnectCallback, size:" + this.d.getRegisteredCallbackCount());
        return 0;
    }

    public int e(ServiceConnectCallback serviceConnectCallback) {
        if (serviceConnectCallback == null) {
            tos.e("ServiceConnectionCallbackManager", "unregisterOtaServiceConnectCallback connectCallback is null");
            return 5;
        }
        if (!this.f17320a.unregister(serviceConnectCallback)) {
            tos.a("ServiceConnectionCallbackManager", "unregisterOtaServiceConnectCallback connectCallback is not registered, still success");
        }
        tos.a("ServiceConnectionCallbackManager", "after unregisterOtaServiceConnectCallback, size:" + this.f17320a.getRegisteredCallbackCount());
        return 0;
    }

    public int d(ServiceConnectCallback serviceConnectCallback) {
        if (serviceConnectCallback == null) {
            tos.e("ServiceConnectionCallbackManager", "unregisterConnectCallback connectCallback is null");
            return 5;
        }
        if (!this.d.unregister(serviceConnectCallback)) {
            tos.a("ServiceConnectionCallbackManager", "unregisterConnectCallback connectCallback is not registered, still success");
        }
        tos.a("ServiceConnectionCallbackManager", "after unregisterConnectCallback, size:" + this.d.getRegisteredCallbackCount());
        return 0;
    }

    public void c() {
        fdb_("executeOtaServiceConnectCallback", this.f17320a);
    }

    public void d() {
        fdb_("executePhoneServiceConnectCallback", this.d);
    }

    private void fdb_(final String str, final RemoteCallbackList<ServiceConnectCallback> remoteCallbackList) {
        this.c.submit(new Runnable() { // from class: tpf.5
            @Override // java.lang.Runnable
            public void run() {
                tpf.this.fdc_(str, remoteCallbackList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fdc_(String str, RemoteCallbackList<ServiceConnectCallback> remoteCallbackList) {
        StringBuilder sb;
        int beginBroadcast = remoteCallbackList.beginBroadcast();
        tos.a("ServiceConnectionCallbackManager", str + " callbackNum:" + beginBroadcast);
        for (int i = 0; i < beginBroadcast; i++) {
            try {
                try {
                    ServiceConnectCallback broadcastItem = remoteCallbackList.getBroadcastItem(i);
                    if (broadcastItem == null) {
                        tos.e("ServiceConnectionCallbackManager", str + " callback is null, index:" + i);
                    } else {
                        broadcastItem.onServiceDisconnect();
                        tos.a("ServiceConnectionCallbackManager", str + " callback ok");
                    }
                } catch (RemoteException unused) {
                    tos.e("ServiceConnectionCallbackManager", str + " RemoteException");
                    try {
                        remoteCallbackList.finishBroadcast();
                        return;
                    } catch (IllegalStateException unused2) {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append(" finishBroadcast IllegalStateException");
                        tos.e("ServiceConnectionCallbackManager", sb.toString());
                    }
                }
            } catch (Throwable th) {
                try {
                    remoteCallbackList.finishBroadcast();
                } catch (IllegalStateException unused3) {
                    tos.e("ServiceConnectionCallbackManager", str + " finishBroadcast IllegalStateException");
                }
                throw th;
            }
        }
        try {
            remoteCallbackList.finishBroadcast();
        } catch (IllegalStateException unused4) {
            sb = new StringBuilder();
            sb.append(str);
            sb.append(" finishBroadcast IllegalStateException");
            tos.e("ServiceConnectionCallbackManager", sb.toString());
        }
    }
}
