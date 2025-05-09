package com.huawei.openalliance.ad;

import android.content.Context;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.beans.metadata.CellInfo;
import com.huawei.openalliance.ad.beans.metadata.Device;
import com.huawei.openalliance.ad.beans.metadata.Network;

/* loaded from: classes5.dex */
public class ek {

    /* renamed from: a, reason: collision with root package name */
    private static ek f6840a;
    private static final byte[] b = new byte[0];
    private final byte[] c = new byte[0];
    private Network d;
    private gc e;
    private App f;
    private Device g;
    private Pair<Integer, Pair<String, String>> h;
    private Context i;

    public void f() {
        a((Pair<Integer, Pair<String, String>>) null);
    }

    public void e() {
        final boolean aL = this.e.aL();
        com.huawei.openalliance.ad.utils.m.i(new Runnable() { // from class: com.huawei.openalliance.ad.ek.1
            @Override // java.lang.Runnable
            public void run() {
                ek.this.a(new App(ek.this.i));
                Pair<Integer, Pair<String, String>> f = com.huawei.openalliance.ad.utils.bv.f(ek.this.i);
                ek.this.a(f);
                Network network = new Network(ek.this.i, false);
                if (aL && f != null) {
                    CellInfo cellInfo = new CellInfo();
                    cellInfo.a(f);
                    network.b().add(cellInfo);
                }
                ek.this.a(network);
                ek.this.a(new Device(ek.this.i, aL));
            }
        });
    }

    public Pair<Integer, Pair<String, String>> d() {
        Pair<Integer, Pair<String, String>> pair;
        synchronized (this.c) {
            pair = this.h;
        }
        return pair;
    }

    public Device c() {
        Device device;
        synchronized (this.c) {
            device = this.g;
        }
        return device;
    }

    public App b() {
        App app;
        synchronized (this.c) {
            app = this.f;
        }
        return app;
    }

    public void a(Network network) {
        synchronized (this.c) {
            this.d = network;
        }
    }

    public void a(Device device) {
        synchronized (this.c) {
            this.g = device;
        }
    }

    public void a(App app) {
        synchronized (this.c) {
            this.f = app;
        }
    }

    public void a(Pair<Integer, Pair<String, String>> pair) {
        synchronized (this.c) {
            this.h = pair;
        }
    }

    public Network a() {
        Network network;
        synchronized (this.c) {
            network = this.d;
        }
        return network;
    }

    private static ek b(Context context) {
        ek ekVar;
        synchronized (b) {
            if (f6840a == null) {
                f6840a = new ek(context);
            }
            ekVar = f6840a;
        }
        return ekVar;
    }

    public static ek a(Context context) {
        return b(context);
    }

    private ek(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.i = applicationContext;
        this.e = fh.b(applicationContext);
    }
}
