package com.huawei.openalliance.ad;

import android.content.Context;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.android.hms.ppskit.RemoteInstallReq;
import com.huawei.android.hms.ppskit.b;
import com.huawei.android.hms.ppskit.c;
import com.huawei.openalliance.ad.ipc.b;

/* loaded from: classes5.dex */
public class mu extends com.huawei.openalliance.ad.ipc.b<com.huawei.android.hms.ppskit.b> {
    private static mu d;
    private static final byte[] e = new byte[0];

    public static abstract class a implements com.huawei.android.hms.ppskit.c {
        public abstract void a(String str);

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public boolean f() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String h() {
        return "com.huawei.android.hms.ppskit.PpsInstallationService";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String g() {
        return "44";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String d() {
        return com.huawei.openalliance.ad.utils.i.e(this.f7074a);
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String c() {
        return "com.huawei.openalliance.ad.INSTALL_SERVICE";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public final String b() {
        return "PPSInstallServiceManager";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ipc.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.huawei.android.hms.ppskit.b a(IBinder iBinder) {
        return b.a.a(iBinder);
    }

    public void a(RemoteInstallReq remoteInstallReq, Uri uri, a aVar, long j) {
        if (aVar == null) {
            return;
        }
        a(new c(aVar, remoteInstallReq, uri), j);
    }

    static class c extends b.a<com.huawei.android.hms.ppskit.b> {

        /* renamed from: a, reason: collision with root package name */
        private a f7278a;
        private RemoteInstallReq b;
        private Uri c;

        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(String str) {
            a aVar = this.f7278a;
            if (aVar != null) {
                aVar.a(str);
            }
        }

        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(com.huawei.android.hms.ppskit.b bVar) {
            try {
                ho.b("PPSInstallServiceManager", "call install service");
                bVar.a(this.b, this.c, new b(this.f7278a));
            } catch (RemoteException e) {
                ho.c("PPSInstallServiceManager", "pkg install RemoteException");
                a aVar = this.f7278a;
                if (aVar != null) {
                    aVar.a("pkg install RemoteException: " + e.getClass().getSimpleName());
                }
            }
        }

        c(a aVar, RemoteInstallReq remoteInstallReq, Uri uri) {
            this.f7278a = aVar;
            this.b = remoteInstallReq;
            this.c = uri;
        }
    }

    public void a(RemoteInstallReq remoteInstallReq, Uri uri, a aVar) {
        a(remoteInstallReq, uri, aVar, 3000L);
    }

    static class b extends c.a {

        /* renamed from: a, reason: collision with root package name */
        private a f7277a;

        @Override // com.huawei.android.hms.ppskit.c
        public void a(boolean z, int i) {
            a aVar = this.f7277a;
            if (aVar != null) {
                aVar.a(z, i);
            }
        }

        b(a aVar) {
            this.f7277a = aVar;
        }
    }

    public static mu a(Context context) {
        mu muVar;
        synchronized (e) {
            if (d == null) {
                d = new mu(context);
            }
            muVar = d;
        }
        return muVar;
    }

    private mu(Context context) {
        super(context);
        this.c = new com.huawei.openalliance.ad.ipc.a(context, b(), this);
    }
}
