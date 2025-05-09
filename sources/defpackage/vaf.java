package defpackage;

import java.net.InetSocketAddress;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.MessageCallback;

/* loaded from: classes7.dex */
public final class vaf {

    /* renamed from: a, reason: collision with root package name */
    private final EndpointContext f17630a;
    private final MessageCallback b;
    public final byte[] c;
    private final boolean d;
    private final InetSocketAddress e;
    private final long h;

    private vaf(byte[] bArr, EndpointContext endpointContext, MessageCallback messageCallback, boolean z, long j, InetSocketAddress inetSocketAddress) {
        if (bArr == null) {
            throw new NullPointerException("Data must not be null");
        }
        if (endpointContext == null) {
            throw new NullPointerException("Peer's EndpointContext must not be null");
        }
        this.c = bArr;
        this.f17630a = endpointContext;
        this.b = messageCallback;
        this.d = z;
        this.h = j;
        this.e = inetSocketAddress;
    }

    public static vaf c(byte[] bArr, EndpointContext endpointContext, boolean z, long j, InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress == null) {
            throw new NullPointerException("Connectors's address must not be null");
        }
        return new vaf(bArr, endpointContext, null, z, j, inetSocketAddress);
    }

    public static vaf d(byte[] bArr, EndpointContext endpointContext, MessageCallback messageCallback, boolean z) {
        return new vaf(bArr, endpointContext, messageCallback, z, 0L, null);
    }

    public byte[] e() {
        return this.c;
    }

    public int f() {
        return this.c.length;
    }

    public long d() {
        return this.h;
    }

    public boolean i() {
        return this.d;
    }

    public InetSocketAddress b() {
        return this.e;
    }

    public InetSocketAddress a() {
        return this.f17630a.getPeerAddress();
    }

    public EndpointContext c() {
        return this.f17630a;
    }

    public void h() {
        MessageCallback messageCallback = this.b;
        if (messageCallback != null) {
            messageCallback.onConnecting();
        }
    }

    public void c(int i) {
        MessageCallback messageCallback = this.b;
        if (messageCallback != null) {
            messageCallback.onDtlsRetransmission(i);
        }
    }

    public void b(EndpointContext endpointContext) {
        MessageCallback messageCallback = this.b;
        if (messageCallback != null) {
            messageCallback.onContextEstablished(endpointContext);
        }
    }

    public void j() {
        MessageCallback messageCallback = this.b;
        if (messageCallback != null) {
            messageCallback.onSent();
        }
    }

    public void b(Throwable th) {
        if (this.b != null) {
            if (th == null) {
                th = new UnknownError();
            }
            this.b.onError(th);
        }
    }
}
