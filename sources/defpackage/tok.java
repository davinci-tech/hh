package defpackage;

import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.WearEngineManager;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class tok implements WearEngineManager, WearEngineBinderClient {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17280a = false;
    private final Object c = new Object();
    private IBinder.DeathRecipient e = new IBinder.DeathRecipient() { // from class: tok.5
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tov.b("WearEngineProxy", "binderDied enter");
            if (tok.this.d != null) {
                tok.this.d.asBinder().unlinkToDeath(tok.this.e, 0);
                tok.this.d = null;
            }
        }
    };
    private volatile WearEngineManager d = null;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    public tok() {
        c();
    }

    private void c(String str) throws RemoteException {
        synchronized (this.c) {
            if (this.d == null) {
                if (TextUtils.isEmpty(str)) {
                    WearEngineClientInner.c().d();
                } else {
                    WearEngineClientInner.c().e(str);
                }
                d();
            }
        }
    }

    private void d() throws RemoteException {
        IBinder fcR_ = WearEngineClientInner.c().fcR_(6);
        if (fcR_ == null) {
            throw new tnx(2);
        }
        this.d = WearEngineManager.Stub.asInterface(fcR_);
        this.d.asBinder().linkToDeath(this.e, 0);
    }

    @Override // com.huawei.wearengine.WearEngineManager
    public int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) {
        try {
            c("getAllBondedDevices");
            if (this.d != null) {
                int registerConnectCallback = this.d.registerConnectCallback(serviceConnectCallback);
                if (registerConnectCallback == 0) {
                    this.f17280a = true;
                }
                return registerConnectCallback;
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("WearEngineProxy", "registerConnectCallback RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.WearEngineManager
    public int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) {
        try {
            top.a(serviceConnectCallback, "serviceConnectCallback is null");
            if (!this.f17280a || this.d == null) {
                tov.a("WearEngineProxy", "unregisterConnectCallback not by remote");
                return 0;
            }
            int unregisterConnectCallback = this.d.unregisterConnectCallback(serviceConnectCallback);
            if (unregisterConnectCallback == 0) {
                this.f17280a = false;
            }
            return unregisterConnectCallback;
        } catch (RemoteException unused) {
            tov.d("WearEngineProxy", "unregisterConnectCallback RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.WearEngineBinderClient
    public void clearBinderProxy() {
        this.d = null;
        tov.b("WearEngineProxy", "clearBinderProxy");
    }

    @Override // com.huawei.wearengine.WearEngineManager
    public int releaseConnection() {
        String str;
        try {
            try {
                try {
                    c((String) null);
                    if (this.d != null) {
                        int releaseConnection = this.d.releaseConnection();
                        if (releaseConnection != 0) {
                            tov.d("WearEngineProxy", "releaseConnection failed, ret: " + releaseConnection);
                            str = "releaseConnection, result: " + WearEngineClientInner.c().a();
                        } else {
                            releaseConnection = WearEngineClientInner.c().a();
                            str = "releaseConnection, result: " + releaseConnection;
                        }
                        tov.a("WearEngineProxy", str);
                        return releaseConnection;
                    }
                    throw new tnx(6);
                } catch (IllegalStateException e) {
                    throw tnx.e(e);
                }
            } catch (RemoteException unused) {
                tov.d("WearEngineProxy", "releaseConnection RemoteException");
                throw new tnx(12);
            }
        } catch (Throwable th) {
            tov.a("WearEngineProxy", "releaseConnection, result: " + WearEngineClientInner.c().a());
            throw th;
        }
    }

    private void c() {
        WearEngineClientInner.c().a(new tob(new WeakReference(this)));
    }
}
