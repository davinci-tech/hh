package defpackage;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Message;
import org.eclipse.californium.core.coap.MessageObserver;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;

/* loaded from: classes7.dex */
public class uxt extends Message {

    /* renamed from: a, reason: collision with root package name */
    private final CoAP.Code f17578a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private Map<String, String> g;
    private String h;
    private uxr i;
    private volatile Throwable j;

    public uxt(CoAP.Code code) {
        this(code, CoAP.Type.CON);
    }

    public uxt(CoAP.Code code, CoAP.Type type) {
        super(type);
        this.f17578a = code;
    }

    public CoAP.Code e() {
        return this.f17578a;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public int getRawCode() {
        CoAP.Code code = this.f17578a;
        if (code == null) {
            return 0;
        }
        return code.value;
    }

    public String c() {
        String str = this.h;
        return str == null ? "coap" : str;
    }

    public void e(String str) {
        this.h = str;
    }

    public boolean h() {
        return this.b;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public boolean isIntendedPayload() {
        return (this.f17578a == CoAP.Code.GET || this.f17578a == CoAP.Code.DELETE || this.f17578a == null) ? false : true;
    }

    @Override // org.eclipse.californium.core.coap.Message
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public uxt setPayload(String str) {
        super.setPayload(str);
        return this;
    }

    @Override // org.eclipse.californium.core.coap.Message
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public uxt setPayload(byte[] bArr) {
        super.setPayload(bArr);
        return this;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void assertPayloadMatchsBlocksize() {
        uxh f = getOptions().f();
        if (f != null) {
            f.b(getPayloadSize());
        }
    }

    public uxt c(String str) {
        if (this.f) {
            throw new IllegalStateException("CoAP URI is set!");
        }
        if (this.e) {
            throw new IllegalStateException("Proxy Scheme is set!");
        }
        getOptions().j(str);
        this.d = true;
        return this;
    }

    public boolean f() {
        return this.d;
    }

    public uxt b(String str) {
        if (this.d) {
            throw new IllegalStateException("Proxy URI is set!");
        }
        getOptions().d(str);
        this.e = true;
        return this;
    }

    public uxt a(String str) {
        String str2;
        if (str == null) {
            throw new NullPointerException("URI must not be null");
        }
        try {
            if (str.contains("://")) {
                str2 = str;
            } else {
                str2 = "coap://" + str;
                LOGGER.warn("update your code to supply an RFC 7252 compliant URI including a scheme");
            }
            return e(new URI(str2));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("invalid uri: " + str, e);
        }
    }

    public uxt e(URI uri) {
        InetSocketAddress peerAddress;
        b(uri);
        String host = uri.getHost() == null ? "localhost" : uri.getHost();
        String scheme = uri.getScheme();
        boolean b = vcb.b(host);
        try {
            EndpointContext destinationContext = getDestinationContext();
            if (destinationContext == null) {
                int port = uri.getPort();
                InetAddress byName = InetAddress.getByName(host);
                String str = b ? null : host;
                if (port <= 0) {
                    port = CoAP.b(scheme);
                }
                peerAddress = new InetSocketAddress(byName, port);
                destinationContext = new uzw(peerAddress, str, null);
            } else {
                peerAddress = destinationContext.getPeerAddress();
            }
            c(uri, peerAddress, b);
            setDestinationContext(destinationContext);
            this.h = scheme.toLowerCase();
            this.f = true;
            return this;
        } catch (UnknownHostException unused) {
            throw new IllegalArgumentException("cannot resolve host name: " + host);
        }
    }

    private void b(URI uri) {
        if (this.d) {
            throw new IllegalStateException("Proxy URI is set!");
        }
        if (uri == null) {
            throw new NullPointerException("URI must not be null");
        }
        if (!CoAP.c(uri.getScheme())) {
            throw new IllegalArgumentException("URI scheme '" + uri.getScheme() + "' is not supported!");
        }
        if (uri.getFragment() != null) {
            throw new IllegalArgumentException("URI must not contain a fragment '" + uri.getFragment() + "'!");
        }
        if (uri.getSchemeSpecificPart() == null || uri.getHost() != null) {
            return;
        }
        throw new IllegalArgumentException("URI expected host '" + uri.getSchemeSpecificPart() + "' is invalid!");
    }

    private void c(URI uri, InetSocketAddress inetSocketAddress, boolean z) {
        if (inetSocketAddress == null) {
            throw new NullPointerException("destination address must not be null!");
        }
        uxv options = getOptions();
        String host = uri.getHost();
        if (host != null) {
            if (z) {
                try {
                    if (InetAddress.getByName(host).equals(inetSocketAddress.getAddress())) {
                        host = null;
                    }
                } catch (UnknownHostException unused) {
                    LOGGER.warn("could not parse IP address of URI despite successful IP address pattern matching");
                }
            } else if (!vcb.a(host)) {
                throw new IllegalArgumentException("URI's hostname '" + host + "' is invalid!'");
            }
            if (host != null) {
                options.i(host.toLowerCase());
            }
        }
        if (host == null) {
            options.au();
        }
        int port = uri.getPort();
        if (port <= 0) {
            port = CoAP.b(uri.getScheme());
        }
        if (port == inetSocketAddress.getPort()) {
            port = -1;
        }
        if (port > 0) {
            options.g(port);
        } else {
            options.aw();
        }
        String path = uri.getPath();
        if (path != null && path.length() > 1) {
            options.h(path);
        } else {
            options.b();
        }
        String query = uri.getQuery();
        if (query != null) {
            options.f(query);
        } else {
            options.a();
        }
    }

    public boolean j() {
        return this.f;
    }

    public String i() {
        uxv options = getOptions();
        String y = options.y();
        Integer ac = options.ac();
        if (y == null) {
            y = getDestinationContext() != null ? getDestinationContext().getPeerAddress().getAddress().getHostAddress() : "localhost";
        }
        String str = y;
        if (ac == null) {
            if (getDestinationContext() != null) {
                ac = Integer.valueOf(getDestinationContext().getPeerAddress().getPort());
            } else {
                ac = -1;
            }
        }
        if (ac.intValue() > 0) {
            if (CoAP.c(c()) && CoAP.b(c()) == ac.intValue()) {
                ac = -1;
            }
        } else {
            ac = -1;
        }
        try {
            return new URI(c(), null, str, ac.intValue(), "/" + options.u(), options.w() > 0 ? options.z() : null, null).toASCIIString();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("cannot create URI from request", e);
        }
    }

    @Override // org.eclipse.californium.core.coap.Message
    public boolean hasBlock(uxh uxhVar) {
        return hasBlock(uxhVar, getOptions().f());
    }

    @Override // org.eclipse.californium.core.coap.Message
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public uxt setDestinationContext(EndpointContext endpointContext) {
        super.setRequestDestinationContext(endpointContext);
        this.b = (endpointContext == null || endpointContext.getPeerAddress().isUnresolved() || !NetworkInterfacesUtil.b(endpointContext.getPeerAddress().getAddress())) ? false : true;
        return this;
    }

    public void b(InetSocketAddress inetSocketAddress, boolean z) {
        super.setLocalAddress(inetSocketAddress);
        this.b = z;
    }

    public final boolean o() {
        return c(0);
    }

    public final boolean m() {
        return c(1);
    }

    private final boolean c(int i) {
        Integer t = getOptions().t();
        return t != null && t.intValue() == i;
    }

    public void b(uxr uxrVar) {
        if (uxrVar == null) {
            throw new NullPointerException("no CoAP response!");
        }
        synchronized (this) {
            this.i = uxrVar;
            notifyAll();
        }
        Iterator<MessageObserver> it = getMessageObservers().iterator();
        while (it.hasNext()) {
            it.next().onResponse(uxrVar);
        }
    }

    public uxr d(long j) throws InterruptedException {
        uxr uxrVar;
        long d = ClockUtil.d();
        long nanos = TimeUnit.MILLISECONDS.toNanos(j);
        synchronized (this) {
            long j2 = j;
            while (!this.c && this.i == null) {
                wait(j2);
                if (j > 0) {
                    long d2 = (d + nanos) - ClockUtil.d();
                    if (d2 <= 0) {
                        break;
                    }
                    j2 = TimeUnit.NANOSECONDS.toMillis(d2) + 1;
                }
            }
            uxrVar = this.i;
            this.c = false;
            this.i = null;
        }
        return uxrVar;
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void setTimedOut(boolean z) {
        super.setTimedOut(z);
        if (z) {
            synchronized (this) {
                this.c = true;
                notifyAll();
            }
        }
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void setCanceled(boolean z) {
        super.setCanceled(z);
        if (z) {
            synchronized (this) {
                this.c = true;
                notifyAll();
            }
        }
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void setRejected(boolean z) {
        super.setRejected(z);
        if (z) {
            synchronized (this) {
                this.c = true;
                notifyAll();
            }
        }
    }

    @Override // org.eclipse.californium.core.coap.Message
    public void setSendError(Throwable th) {
        super.setSendError(th);
        if (th != null) {
            synchronized (this) {
                this.c = true;
                notifyAll();
            }
        }
    }

    public Throwable d() {
        return this.j;
    }

    public void d(Throwable th) {
        this.j = th;
        if (this.j != null) {
            Iterator<MessageObserver> it = getMessageObservers().iterator();
            while (it.hasNext()) {
                it.next().onResponseHandlingError(this.j);
            }
            synchronized (this) {
                this.c = true;
                notifyAll();
            }
        }
    }

    public Map<String, String> g() {
        return this.g;
    }

    public uxt c(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            this.g = Collections.emptyMap();
        } else {
            this.g = Collections.unmodifiableMap(new HashMap(map));
        }
        return this;
    }

    public String toString() {
        CoAP.Code e = e();
        return toTracingString(e == null ? "PING" : e.toString());
    }

    public static uxt a() {
        return new uxt(CoAP.Code.GET);
    }

    public static uxt b() {
        return new uxt(CoAP.Code.POST);
    }
}
