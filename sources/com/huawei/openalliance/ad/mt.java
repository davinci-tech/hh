package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.android.hms.ppskit.a;
import com.huawei.openalliance.ad.ipc.b;

/* loaded from: classes5.dex */
public class mt extends com.huawei.openalliance.ad.ipc.b<com.huawei.android.hms.ppskit.a> {
    private static mt d;
    private static final byte[] e = new byte[0];

    public interface a {
        void a(String str, String str2);
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public boolean e() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public boolean f() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String h() {
        return "com.huawei.android.hms.ppskit.PpsChannelInfoService";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String g() {
        return "42";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String d() {
        return com.huawei.openalliance.ad.utils.i.e(this.f7074a);
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public String c() {
        return "com.huawei.android.hms.CHANNEL_SERVICE";
    }

    @Override // com.huawei.openalliance.ad.ipc.b
    public final String b() {
        return "ChannelInfoService";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.ipc.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.huawei.android.hms.ppskit.a a(IBinder iBinder) {
        return a.AbstractBinderC0038a.a(iBinder);
    }

    public void a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        a(new c(str, str2, i), 3000L);
    }

    public void a(String str, a aVar) {
        if (TextUtils.isEmpty(str)) {
            ho.b("ChannelInfoService", " pkgName is empty");
        } else {
            a(new b(str, aVar), 3000L);
        }
    }

    static class b extends b.a<com.huawei.android.hms.ppskit.a> {

        /* renamed from: a, reason: collision with root package name */
        private String f7275a;
        private a b;

        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(com.huawei.android.hms.ppskit.a aVar) {
            try {
                String a2 = aVar.a(this.f7275a);
                a aVar2 = this.b;
                if (aVar2 != null) {
                    aVar2.a(this.f7275a, a2);
                }
            } catch (RemoteException unused) {
                ho.c("ChannelInfoService", "QueryChannelInfo RemoteException");
            }
        }

        public b(String str, a aVar) {
            this.f7275a = str;
            this.b = aVar;
        }
    }

    static class c extends b.a<com.huawei.android.hms.ppskit.a> {

        /* renamed from: a, reason: collision with root package name */
        private String f7276a;
        private String b;
        private int c;

        @Override // com.huawei.openalliance.ad.ipc.b.a
        public void a(com.huawei.android.hms.ppskit.a aVar) {
            try {
                aVar.a(this.f7276a, this.b, this.c);
            } catch (RemoteException unused) {
                ho.c("ChannelInfoService", "setInstallSource RemoteException");
            }
        }

        public c(String str, String str2, int i) {
            this.f7276a = str;
            this.b = str2;
            this.c = i;
        }
    }

    public static mt a(Context context) {
        mt mtVar;
        synchronized (e) {
            if (d == null) {
                d = new mt(context);
            }
            mtVar = d;
        }
        return mtVar;
    }

    private mt(Context context) {
        super(context);
        this.c = new com.huawei.openalliance.ad.ipc.a(context, b(), this);
    }
}
