package com.huawei.agconnect.apms;

import android.text.TextUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
public final class c extends EventListener {
    public EventListener abc;
    public wxy bcd;

    public c(EventListener eventListener, wxy wxyVar) {
        if (eventListener == null) {
            throw new NullPointerException("eventListener is null.");
        }
        this.abc = eventListener;
        this.bcd = wxyVar;
    }

    public final void abc(Throwable th) {
        wxy wxyVar = this.bcd;
        if (wxyVar != null) {
            wxyVar.abc(th.getMessage());
            this.bcd.qpo = r0.abc();
        }
    }

    @Override // okhttp3.EventListener
    public void callEnd(Call call) {
        this.bcd.abc(abc());
        this.abc.callEnd(call);
    }

    @Override // okhttp3.EventListener
    public void callFailed(Call call, IOException iOException) {
        this.bcd.abc(abc());
        this.abc.callFailed(call, iOException);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
    
        if (java.util.regex.Pattern.matches("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)", r1) != false) goto L7;
     */
    @Override // okhttp3.EventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void callStart(okhttp3.Call r4) {
        /*
            r3 = this;
            com.huawei.agconnect.apms.wxy r0 = r3.bcd     // Catch: java.lang.Throwable -> L2c
            okhttp3.Request r1 = r4.request()     // Catch: java.lang.Throwable -> L18
            okhttp3.HttpUrl r1 = r1.url()     // Catch: java.lang.Throwable -> L18
            java.lang.String r1 = r1.host()     // Catch: java.lang.Throwable -> L18
            if (r1 == 0) goto L18
            java.lang.String r2 = "([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)"
            boolean r2 = java.util.regex.Pattern.matches(r2, r1)     // Catch: java.lang.Throwable -> L18
            if (r2 == 0) goto L1a
        L18:
            java.lang.String r1 = ""
        L1a:
            boolean r2 = r0.edc()     // Catch: java.lang.Throwable -> L2c
            if (r2 != 0) goto L22
            r0.hij = r1     // Catch: java.lang.Throwable -> L2c
        L22:
            com.huawei.agconnect.apms.wxy r0 = r3.bcd     // Catch: java.lang.Throwable -> L2c
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L2c
            r0.cde(r1)     // Catch: java.lang.Throwable -> L2c
            goto L30
        L2c:
            r0 = move-exception
            r3.abc(r0)
        L30:
            okhttp3.EventListener r0 = r3.abc
            r0.callStart(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.c.callStart(okhttp3.Call):void");
    }

    @Override // okhttp3.EventListener
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        try {
            if (TextUtils.isEmpty(this.bcd.ijk)) {
                this.bcd.cde(inetSocketAddress.getAddress().getHostAddress());
            }
            mlk stu = this.bcd.stu();
            if (stu != null) {
                stu.cde = abc();
                if (stu.def == -1) {
                    stu.fgh = false;
                } else {
                    stu.fgh = true;
                }
                stu.ghi = protocol.getProtocol();
                stu.jkl = true;
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.connectEnd(call, inetSocketAddress, proxy, protocol);
    }

    @Override // okhttp3.EventListener
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
        try {
            mlk stu = this.bcd.stu();
            if (stu != null) {
                stu.cde = abc();
                stu.klm = iOException.getMessage();
                if (stu.def == -1) {
                    stu.fgh = false;
                } else {
                    stu.fgh = true;
                }
                stu.jkl = false;
            }
            wxy wxyVar = this.bcd;
            if (!wxyVar.edc()) {
                wxyVar.uts++;
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.connectFailed(call, inetSocketAddress, proxy, protocol, iOException);
    }

    @Override // okhttp3.EventListener
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        try {
            mlk mlkVar = new mlk(inetSocketAddress.toString(), abc());
            wxy wxyVar = this.bcd;
            if (!wxyVar.edc()) {
                wxyVar.srq.add(mlkVar);
                wxyVar.vut++;
            }
            wxy wxyVar2 = this.bcd;
            if (!wxyVar2.edc()) {
                wxyVar2.tsr++;
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.connectStart(call, inetSocketAddress, proxy);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x004d A[Catch: all -> 0x0064, TRY_LEAVE, TryCatch #0 {all -> 0x0064, blocks: (B:13:0x0003, B:15:0x0009, B:18:0x0014, B:3:0x0047, B:5:0x004d, B:19:0x001d, B:21:0x0023, B:23:0x002d, B:26:0x0038), top: B:12:0x0003 }] */
    @Override // okhttp3.EventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void connectionAcquired(okhttp3.Call r4, okhttp3.Connection r5) {
        /*
            r3 = this;
            if (r5 != 0) goto L3
            goto L45
        L3:
            java.net.Socket r0 = r5.socket()     // Catch: java.lang.Throwable -> L64
            if (r0 == 0) goto L1d
            java.net.Socket r0 = r5.socket()     // Catch: java.lang.Throwable -> L64
            java.net.InetAddress r1 = r0.getInetAddress()     // Catch: java.lang.Throwable -> L64
            if (r1 != 0) goto L14
            goto L45
        L14:
            java.net.InetAddress r0 = r0.getInetAddress()     // Catch: java.lang.Throwable -> L64
            java.lang.String r0 = r0.getHostAddress()     // Catch: java.lang.Throwable -> L64
            goto L47
        L1d:
            okhttp3.Route r0 = r5.getRoute()     // Catch: java.lang.Throwable -> L64
            if (r0 == 0) goto L45
            okhttp3.Route r0 = r5.getRoute()     // Catch: java.lang.Throwable -> L64
            java.net.InetSocketAddress r1 = r0.socketAddress()     // Catch: java.lang.Throwable -> L64
            if (r1 == 0) goto L45
            java.net.InetSocketAddress r1 = r0.socketAddress()     // Catch: java.lang.Throwable -> L64
            java.net.InetAddress r1 = r1.getAddress()     // Catch: java.lang.Throwable -> L64
            if (r1 != 0) goto L38
            goto L45
        L38:
            java.net.InetSocketAddress r0 = r0.socketAddress()     // Catch: java.lang.Throwable -> L64
            java.net.InetAddress r0 = r0.getAddress()     // Catch: java.lang.Throwable -> L64
            java.lang.String r0 = r0.getHostAddress()     // Catch: java.lang.Throwable -> L64
            goto L47
        L45:
            java.lang.String r0 = ""
        L47:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L64
            if (r1 != 0) goto L68
            com.huawei.agconnect.apms.wxy r1 = r3.bcd     // Catch: java.lang.Throwable -> L64
            r1.cde(r0)     // Catch: java.lang.Throwable -> L64
            okhttp3.Request r1 = r4.request()     // Catch: java.lang.Throwable -> L64
            okhttp3.HttpUrl r1 = r1.url()     // Catch: java.lang.Throwable -> L64
            java.lang.String r1 = r1.host()     // Catch: java.lang.Throwable -> L64
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r2 = com.huawei.agconnect.apms.xyz.bcd     // Catch: java.lang.Throwable -> L64
            r2.put(r1, r0)     // Catch: java.lang.Throwable -> L64
            goto L68
        L64:
            r0 = move-exception
            r3.abc(r0)
        L68:
            okhttp3.EventListener r0 = r3.abc
            r0.connectionAcquired(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.c.connectionAcquired(okhttp3.Call, okhttp3.Connection):void");
    }

    @Override // okhttp3.EventListener
    public void connectionReleased(Call call, Connection connection) {
        this.abc.connectionReleased(call, connection);
    }

    @Override // okhttp3.EventListener
    public void dnsEnd(Call call, String str, List<InetAddress> list) {
        try {
            nml rst = this.bcd.rst();
            if (rst != null) {
                rst.cde = abc();
                rst.def = list.toString();
                rst.efg = true;
            }
            wxy wxyVar = this.bcd;
            wxyVar.getClass();
            int abc = xyz.abc(wxyVar);
            if (!wxyVar.edc()) {
                wxyVar.zyx = abc;
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.dnsEnd(call, str, list);
    }

    @Override // okhttp3.EventListener
    public void dnsStart(Call call, String str) {
        try {
            this.bcd.abc(new nml(str, abc()));
            wxy wxyVar = this.bcd;
            wxyVar.fed();
            if (wxyVar.zab > 0) {
                if (wxyVar.zyx == -1 && !wxyVar.edc()) {
                    wxyVar.yxw++;
                }
                if (!wxyVar.edc()) {
                    wxyVar.zyx = -1;
                }
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.dnsStart(call, str);
    }

    @Override // okhttp3.EventListener
    public void requestBodyEnd(Call call, long j) {
        wxy wxyVar = this.bcd;
        long abc = abc();
        if (!wxyVar.edc()) {
            wxyVar.opq = abc;
        }
        this.abc.requestBodyEnd(call, j);
    }

    @Override // okhttp3.EventListener
    public void requestBodyStart(Call call) {
        wxy wxyVar = this.bcd;
        long abc = abc();
        if (!wxyVar.edc()) {
            wxyVar.pqr = abc;
        }
        this.abc.requestBodyStart(call);
    }

    @Override // okhttp3.EventListener
    public void requestHeadersEnd(Call call, Request request) {
        wxy wxyVar = this.bcd;
        long abc = abc();
        if (!wxyVar.edc()) {
            wxyVar.nop = abc;
        }
        this.abc.requestHeadersEnd(call, request);
    }

    @Override // okhttp3.EventListener
    public void requestHeadersStart(Call call) {
        wxy wxyVar = this.bcd;
        long abc = abc();
        if (!wxyVar.edc()) {
            wxyVar.mno = abc;
        }
        this.abc.requestHeadersStart(call);
    }

    @Override // okhttp3.EventListener
    public void responseBodyEnd(Call call, long j) {
        this.abc.responseBodyEnd(call, j);
    }

    @Override // okhttp3.EventListener
    public void responseBodyStart(Call call) {
        this.abc.responseBodyStart(call);
    }

    @Override // okhttp3.EventListener
    public void responseHeadersEnd(Call call, Response response) {
        wxy wxyVar = this.bcd;
        long abc = abc();
        if (!wxyVar.edc()) {
            wxyVar.rst = abc;
        }
        if (response.isRedirect()) {
            wxy wxyVar2 = this.bcd;
            if (!wxyVar2.edc()) {
                wxyVar2.wxy++;
            }
        }
        this.abc.responseHeadersEnd(call, response);
    }

    @Override // okhttp3.EventListener
    public void responseHeadersStart(Call call) {
        wxy wxyVar = this.bcd;
        long abc = abc();
        if (!wxyVar.edc()) {
            wxyVar.stu = abc;
        }
        this.abc.responseHeadersStart(call);
    }

    @Override // okhttp3.EventListener
    public void secureConnectEnd(Call call, Handshake handshake) {
        try {
            mlk stu = this.bcd.stu();
            if (stu != null) {
                stu.efg = abc();
                stu.hij = handshake.tlsVersion().javaName();
                stu.ijk = handshake.cipherSuite().javaName();
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.secureConnectEnd(call, handshake);
    }

    @Override // okhttp3.EventListener
    public void secureConnectStart(Call call) {
        try {
            mlk stu = this.bcd.stu();
            if (stu != null) {
                stu.def = abc();
            }
        } catch (Throwable th) {
            abc(th);
        }
        this.abc.secureConnectStart(call);
    }

    public final int abc() {
        long currentTimeMillis = System.currentTimeMillis() - this.bcd.xyz;
        if (currentTimeMillis < 0 || currentTimeMillis >= 2147483647L) {
            return -1;
        }
        return (int) currentTimeMillis;
    }
}
