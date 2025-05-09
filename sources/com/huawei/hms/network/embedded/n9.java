package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.t7;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.annotation.Nullable;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: classes9.dex */
public final class n9 implements n7 {
    public static final int b = 20;

    /* renamed from: a, reason: collision with root package name */
    public final q7 f5392a;

    @Override // com.huawei.hms.network.embedded.n7
    public v7 intercept(n7.a aVar) throws IOException {
        v8 a2;
        t7 a3;
        t7 request = aVar.request();
        k9 k9Var = (k9) aVar;
        d9 f = k9Var.f();
        StringBuffer stringBuffer = new StringBuffer("{");
        int i = 0;
        v7 v7Var = null;
        while (true) {
            f.prepareToConnect(request);
            if (f.isCanceled()) {
                throw new IOException("Canceled");
            }
            try {
                try {
                    v7 a4 = k9Var.a(request, f, null);
                    if (v7Var != null) {
                        a4 = a4.D().c(v7Var.D().a((w7) null).a()).a();
                    }
                    v7Var = a4;
                    a2 = c8.f5203a.a(v7Var);
                    a3 = a(v7Var, a2 != null ? a2.b().b() : null);
                } catch (b9 e) {
                    if (!a(e.b(), f, false, request)) {
                        throw e.a();
                    }
                } catch (IOException e2) {
                    if (!a(e2, f, true ^ (e2 instanceof q9), request)) {
                        throw e2;
                    }
                }
                if (a3 == null) {
                    if (a2 != null && a2.f()) {
                        f.timeoutEarlyExit();
                    }
                    if (stringBuffer.length() > 1) {
                        return v7Var.D().a(t2.c0, stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length()).append("}").toString()).a();
                    }
                    return v7Var;
                }
                u7 b2 = a3.b();
                if (b2 != null && b2.isOneShot()) {
                    return v7Var;
                }
                f8.a(v7Var.s());
                if (f.hasExchange()) {
                    a2.c();
                }
                i++;
                if (i > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + i);
                }
                if (v7Var.w() >= 300 && v7Var.w() < 400) {
                    stringBuffer.append(a3.k().h()).append("=").append(v7Var.w()).append(", ");
                }
                request = a3;
            } finally {
                f.exchangeDoneDueToException();
            }
        }
    }

    private boolean a(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private boolean a(IOException iOException, t7 t7Var) {
        u7 b2 = t7Var.b();
        return (b2 != null && b2.isOneShot()) || (iOException instanceof FileNotFoundException);
    }

    private boolean a(IOException iOException, d9 d9Var, boolean z, t7 t7Var) {
        if (d9Var.getExchangeFinder() != null && d9Var.getSelection() != null) {
            d9Var.getSelection().h();
        }
        if (this.f5392a.A()) {
            return !(z && a(iOException, t7Var)) && a(iOException, z) && d9Var.canRetry();
        }
        return false;
    }

    private t7 a(v7 v7Var, @Nullable x7 x7Var) throws IOException {
        String b2;
        m7 d;
        if (v7Var == null) {
            throw new IllegalStateException();
        }
        int w = v7Var.w();
        String h = v7Var.H().h();
        if (w == 307 || w == 308) {
            if (!h.equals("GET") && !h.equals("HEAD")) {
                return null;
            }
        } else {
            if (w == 401) {
                return this.f5392a.a().b(x7Var, v7Var);
            }
            if (w == 503) {
                if ((v7Var.E() == null || v7Var.E().w() != 503) && a(v7Var, Integer.MAX_VALUE) == 0) {
                    return v7Var.H();
                }
                return null;
            }
            if (w == 407) {
                if ((x7Var != null ? x7Var.b() : this.f5392a.w()).type() == Proxy.Type.HTTP) {
                    return this.f5392a.x().b(x7Var, v7Var);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (w == 408) {
                if (!this.f5392a.A()) {
                    return null;
                }
                u7 b3 = v7Var.H().b();
                if (b3 != null && b3.isOneShot()) {
                    return null;
                }
                if ((v7Var.E() == null || v7Var.E().w() != 408) && a(v7Var, 0) <= 0) {
                    return v7Var.H();
                }
                return null;
            }
            switch (w) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.f5392a.m() || (b2 = v7Var.b("Location")) == null || (d = v7Var.H().k().d(b2)) == null) {
            return null;
        }
        if (!d.s().equals(v7Var.H().k().s()) && !this.f5392a.n()) {
            return null;
        }
        t7.a i = v7Var.H().i();
        if (j9.b(h)) {
            boolean d2 = j9.d(h);
            if (j9.c(h)) {
                i.a("GET", (u7) null);
            } else {
                i.a(h, d2 ? v7Var.H().b() : null);
            }
            if (!d2) {
                i.b("Transfer-Encoding");
                i.b("Content-Length");
                i.b("Content-Type");
            }
        }
        if (!f8.a(v7Var.H().k(), d)) {
            i.b("Authorization");
        }
        return i.a(d).a();
    }

    private int a(v7 v7Var, int i) {
        String b2 = v7Var.b(q0.f);
        if (b2 == null) {
            return i;
        }
        if (b2.matches("\\d+")) {
            return Integer.valueOf(b2).intValue();
        }
        return Integer.MAX_VALUE;
    }

    public n9(q7 q7Var) {
        this.f5392a = q7Var;
    }
}
