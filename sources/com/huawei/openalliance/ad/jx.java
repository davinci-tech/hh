package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.hquic.HQUICManager;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.embedded.k;
import com.huawei.openalliance.ad.net.http.e;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class jx {
    private static jx f;
    private static final byte[] h = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private Context f7148a;
    private volatile boolean b;
    private volatile boolean c;
    private volatile boolean d;
    private final byte[] e = new byte[0];
    private gc g;

    public void c() {
        final int T;
        synchronized (this.e) {
            try {
                Log.i("QuicNetworkKit", "setUp");
                T = this.g.T();
                Log.i("QuicNetworkKit", "networkkit configure:" + T);
            } catch (Throwable th) {
                ho.c("QuicNetworkKit", "setUp network kit err, %s", th.getClass().getSimpleName());
            }
            if (com.huawei.openalliance.ad.utils.bv.a() && (T == 1 || T == 2)) {
                if (this.b) {
                    if (T == 2) {
                        d();
                    } else {
                        ho.b("QuicNetworkKit", "if quic open, can not close quic until app restart.");
                    }
                    ho.b("QuicNetworkKit", "network kit has been init");
                } else {
                    if (T == 2 && com.huawei.openalliance.ad.utils.bv.b()) {
                        this.c = true;
                        HQUICManager.asyncInit(this.f7148a.getApplicationContext(), k.c.f5333a, new a());
                    }
                    ho.b("QuicNetworkKit", "init network kit");
                    NetworkKit.init(this.f7148a, new NetworkKit.Callback() { // from class: com.huawei.openalliance.ad.jx.1
                        @Override // com.huawei.hms.network.NetworkKit.Callback
                        public void onResult(boolean z) {
                            Log.i("QuicNetworkKit", "network kit init result:" + z);
                            ho.b("QuicNetworkKit", "network kit init result:" + z);
                            jx.this.b = z;
                            if (jx.this.b && T == 2) {
                                jx.this.d();
                            }
                        }
                    });
                }
                return;
            }
            this.b = false;
            ho.b("QuicNetworkKit", "not support network kit");
        }
    }

    public boolean b() {
        return this.c;
    }

    public boolean a() {
        return this.b;
    }

    private void e() {
        new com.huawei.openalliance.ad.net.http.k(this.f7148a).a(new e.a(this.f7148a).c(2).c(this.c).a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!this.b) {
            ho.b("QuicNetworkKit", "configureQuicHint isNetworkKitEnable:" + this.b);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("adxServer");
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String a2 = this.g.a(this.f7148a, (String) it.next());
            if (!TextUtils.isEmpty(a2)) {
                arrayList2.add(a2);
                ho.a("QuicNetworkKit", "get quic url: %s", a2);
            }
        }
        if (arrayList2.size() <= 0 || this.d) {
            ho.b("QuicNetworkKit", "add quic url, quicUrlList is empty or hasAddQuicHint: %s", Boolean.valueOf(this.d));
        } else {
            NetworkKit.getInstance().addQuicHint(true, (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            this.d = true;
            ho.b("QuicNetworkKit", "add quic success.");
        }
        e();
    }

    static class a implements HQUICManager.HQUICInitCallback {
        public void onSuccess() {
            Log.i("QuicNetworkKit", "HQUICManager.asyncInit success");
            ho.b("QuicNetworkKit", "HQUICManager.asyncInit success");
        }

        public void onFail(Exception exc) {
            Log.i("QuicNetworkKit", "HQUICManager.asyncInit failed.");
            ho.b("QuicNetworkKit", "HQUICManager.asyncInit failed.");
        }

        private a() {
        }
    }

    public static jx a(Context context) {
        jx jxVar;
        synchronized (h) {
            if (f == null) {
                f = new jx(context);
            }
            jxVar = f;
        }
        return jxVar;
    }

    private jx(Context context) {
        this.f7148a = context.getApplicationContext();
        this.g = fh.b(context);
    }
}
