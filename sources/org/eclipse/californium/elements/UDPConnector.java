package org.eclipse.californium.elements;

import com.huawei.openalliance.ad.constant.Constants;
import defpackage.vaf;
import defpackage.vam;
import defpackage.van;
import defpackage.vaz;
import defpackage.vbd;
import defpackage.vbj;
import defpackage.vcb;
import defpackage.vha;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.eclipse.californium.elements.util.ClockUtil;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class UDPConnector implements Connector {
    public static final Logger d = vha.a((Class<?>) UDPConnector.class);
    static final ThreadGroup e;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f15868a;
    protected final InetSocketAddress b;
    protected volatile InetSocketAddress c;
    private final Integer f;
    protected volatile boolean g;
    private volatile EndpointContextMatcher h;
    private final Integer i;
    private volatile RawDataChannel k;
    private Integer l;
    private final BlockingQueue<vaf> m;
    private final int n;
    private final int o;
    private final int q;
    private Integer r;
    private boolean s;
    private volatile DatagramSocket u;
    private final List<Thread> t = new LinkedList();
    private final List<Thread> p = new LinkedList();
    private final List<vam> j = new CopyOnWriteArrayList();

    static {
        ThreadGroup threadGroup = new ThreadGroup("Californium/Elements");
        e = threadGroup;
        threadGroup.setDaemon(false);
    }

    public UDPConnector(InetSocketAddress inetSocketAddress, Configuration configuration) {
        if (inetSocketAddress == null) {
            this.b = new InetSocketAddress(0);
        } else {
            this.b = inetSocketAddress;
        }
        this.g = false;
        this.c = this.b;
        this.m = new LinkedBlockingQueue(((Integer) configuration.a((BasicDefinition) vaz.c)).intValue());
        this.o = ((Integer) configuration.a((BasicDefinition) vaz.b)).intValue();
        this.q = ((Integer) configuration.a((BasicDefinition) vaz.i)).intValue();
        this.n = ((Integer) configuration.a((BasicDefinition) vaz.d)).intValue();
        Integer num = (Integer) configuration.a((BasicDefinition) vaz.f17640a);
        this.i = num;
        Integer num2 = (Integer) configuration.a((BasicDefinition) vaz.j);
        this.f = num2;
        this.l = num;
        this.r = num2;
    }

    @Override // org.eclipse.californium.elements.Connector
    public boolean isRunning() {
        return this.g;
    }

    @Override // org.eclipse.californium.elements.Connector
    public void start() throws IOException {
        synchronized (this) {
            if (this.g) {
                return;
            }
            Iterator<vam> it = this.j.iterator();
            while (it.hasNext()) {
                it.next().start();
            }
            DatagramSocket datagramSocket = new DatagramSocket((SocketAddress) null);
            datagramSocket.setReuseAddress(this.s);
            datagramSocket.bind(this.b);
            d(datagramSocket);
        }
    }

    protected void d(DatagramSocket datagramSocket) throws IOException {
        this.u = datagramSocket;
        this.c = (InetSocketAddress) datagramSocket.getLocalSocketAddress();
        Integer num = this.i;
        if (num != null) {
            datagramSocket.setReceiveBufferSize(num.intValue());
        }
        this.l = Integer.valueOf(datagramSocket.getReceiveBufferSize());
        Integer num2 = this.f;
        if (num2 != null) {
            datagramSocket.setSendBufferSize(num2.intValue());
        }
        this.r = Integer.valueOf(datagramSocket.getSendBufferSize());
        this.g = true;
        d.info("UDPConnector starts up {} sender threads and {} receiver threads", Integer.valueOf(this.q), Integer.valueOf(this.o));
        int i = 0;
        while (true) {
            if (i >= this.o) {
                break;
            }
            this.t.add(new d("UDP-Receiver-" + this.b + "[" + i + "]"));
            i++;
        }
        if (!this.f15868a) {
            for (int i2 = 0; i2 < this.q; i2++) {
                this.p.add(new c("UDP-Sender-" + this.b + "[" + i2 + "]"));
            }
        }
        Iterator<Thread> it = this.t.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
        Iterator<Thread> it2 = this.p.iterator();
        while (it2.hasNext()) {
            it2.next().start();
        }
        d.info("UDPConnector listening on {}, recv buf = {}, send buf = {}, recv packet size = {}", this.c, this.l, this.r, Integer.valueOf(this.n));
    }

    @Override // org.eclipse.californium.elements.Connector
    public void stop() {
        ArrayList arrayList = new ArrayList(this.m.size());
        synchronized (this) {
            if (this.g) {
                this.g = false;
                d.debug("UDPConnector on [{}] stopping ...", this.c);
                Iterator<vam> it = this.j.iterator();
                while (it.hasNext()) {
                    it.next().stop();
                }
                Iterator<Thread> it2 = this.p.iterator();
                while (it2.hasNext()) {
                    it2.next().interrupt();
                }
                Iterator<Thread> it3 = this.t.iterator();
                while (it3.hasNext()) {
                    it3.next().interrupt();
                }
                this.m.drainTo(arrayList);
                if (this.u != null) {
                    this.u.close();
                    this.u = null;
                }
                for (Thread thread : this.p) {
                    thread.interrupt();
                    try {
                        thread.join(1000L);
                    } catch (InterruptedException unused) {
                    }
                }
                this.p.clear();
                for (Thread thread2 : this.t) {
                    thread2.interrupt();
                    try {
                        thread2.join(1000L);
                    } catch (InterruptedException unused2) {
                    }
                }
                this.t.clear();
                d.debug("UDPConnector on [{}] has stopped.", this.c);
                Iterator it4 = arrayList.iterator();
                while (it4.hasNext()) {
                    a((vaf) it4.next());
                }
            }
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public void destroy() {
        stop();
        Iterator<vam> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        this.k = null;
    }

    @Override // org.eclipse.californium.elements.Connector
    public void send(vaf vafVar) {
        boolean z;
        boolean offer;
        if (vafVar == null) {
            throw new NullPointerException("Message must not be null");
        }
        if (this.f15868a) {
            throw new IllegalStateException("Connector is a multicast receiver!");
        }
        if (vafVar.a().getPort() == 0) {
            String c2 = vcb.c(vafVar.a());
            d.trace("Discarding message with {} bytes to [{}] without destination-port", Integer.valueOf(vafVar.f()), c2);
            vafVar.b(new IOException("CoAP message to " + c2 + " dropped, destination port 0!"));
            return;
        }
        synchronized (this) {
            z = this.g;
            offer = z ? this.m.offer(vafVar) : false;
        }
        if (!z) {
            a(vafVar);
        } else {
            if (offer) {
                return;
            }
            vafVar.b(new InterruptedIOException("Connector overloaded."));
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public void setRawDataReceiver(RawDataChannel rawDataChannel) {
        this.k = rawDataChannel;
        Iterator<vam> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().setRawDataReceiver(rawDataChannel);
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public void setEndpointContextMatcher(EndpointContextMatcher endpointContextMatcher) {
        this.h = endpointContextMatcher;
        Iterator<vam> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().setEndpointContextMatcher(endpointContextMatcher);
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public InetSocketAddress getAddress() {
        return this.c;
    }

    private void a(vaf vafVar) {
        vafVar.b(new InterruptedIOException("Connector is not running."));
    }

    abstract class NetworkStageThread extends Thread {
        protected abstract void work() throws Exception;

        protected NetworkStageThread(String str) {
            super(UDPConnector.e, str);
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UDPConnector.d.debug("Starting network stage thread [{}]", getName());
            while (UDPConnector.this.g) {
                try {
                    work();
                } catch (InterruptedIOException e) {
                    UDPConnector.d.trace("Network stage thread [{}] was stopped successfully at:", getName(), e);
                } catch (IOException e2) {
                    if (UDPConnector.this.g) {
                        UDPConnector.d.error("Exception in network stage thread [{}]:", getName(), e2);
                    } else {
                        UDPConnector.d.trace("Network stage thread [{}] was stopped successfully at:", getName(), e2);
                    }
                } catch (InterruptedException e3) {
                    UDPConnector.d.trace("Network stage thread [{}] was stopped successfully at:", getName(), e3);
                } catch (Throwable th) {
                    UDPConnector.d.error("Exception in network stage thread [{}]:", getName(), th);
                }
                if (!UDPConnector.this.g) {
                    UDPConnector.d.debug("Network stage thread [{}] was stopped successfully", getName());
                    return;
                }
                continue;
            }
        }
    }

    class d extends NetworkStageThread {
        private final int b;
        private final DatagramPacket d;

        private d(String str) {
            super(str);
            int i = UDPConnector.this.n + 1;
            this.b = i;
            this.d = new DatagramPacket(new byte[i], i);
        }

        @Override // org.eclipse.californium.elements.UDPConnector.NetworkStageThread
        protected void work() throws IOException {
            this.d.setLength(this.b);
            DatagramSocket datagramSocket = UDPConnector.this.u;
            if (datagramSocket != null) {
                datagramSocket.receive(this.d);
                UDPConnector.this.processDatagram(this.d);
            }
        }
    }

    class c extends NetworkStageThread {
        private final DatagramPacket d;

        private c(String str) {
            super(str);
            this.d = new DatagramPacket(vbj.c, 0);
        }

        @Override // org.eclipse.californium.elements.UDPConnector.NetworkStageThread
        protected void work() throws InterruptedException {
            vaf vafVar = (vaf) UDPConnector.this.m.take();
            EndpointContext c = vafVar.c();
            InetSocketAddress peerAddress = c.getPeerAddress();
            van vanVar = new van(peerAddress);
            EndpointContextMatcher endpointContextMatcher = UDPConnector.this.h;
            if (endpointContextMatcher != null && !endpointContextMatcher.isToBeSent(c, vanVar)) {
                UDPConnector.d.warn("UDPConnector ({}) drops {} bytes to {}", UDPConnector.this.c, Integer.valueOf(this.d.getLength()), vcb.b((SocketAddress) peerAddress));
                vafVar.b(new vbd("UDP sending"));
                return;
            }
            this.d.setData(vafVar.e());
            this.d.setSocketAddress(peerAddress);
            DatagramSocket datagramSocket = UDPConnector.this.u;
            if (datagramSocket != null) {
                try {
                    vafVar.b(vanVar);
                    datagramSocket.send(this.d);
                    vafVar.j();
                } catch (IOException e) {
                    vafVar.b(e);
                }
                UDPConnector.d.debug("UDPConnector ({}) sent {} bytes to {}", this, Integer.valueOf(this.d.getLength()), vcb.b((SocketAddress) peerAddress));
                return;
            }
            vafVar.b(new IOException("socket already closed!"));
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public void processDatagram(DatagramPacket datagramPacket) {
        InetSocketAddress inetSocketAddress = this.c;
        RawDataChannel rawDataChannel = this.k;
        if (datagramPacket.getPort() == 0) {
            d.trace("Discarding message with {} bytes from [{}] without source-port", Integer.valueOf(datagramPacket.getLength()), vcb.b(datagramPacket.getSocketAddress()));
            return;
        }
        if (datagramPacket.getLength() > this.n) {
            d.debug("UDPConnector ({}) received truncated UDP datagram from {}. Maximum size allowed {}. Discarding ...", inetSocketAddress, vcb.b(datagramPacket.getSocketAddress()), Integer.valueOf(this.n));
            return;
        }
        if (rawDataChannel == null) {
            d.debug("UDPConnector ({}) received UDP datagram from {} without receiver. Discarding ...", inetSocketAddress, vcb.b(datagramPacket.getSocketAddress()));
            return;
        }
        long d2 = ClockUtil.d();
        String c2 = vcb.c(inetSocketAddress);
        if (this.f15868a) {
            c2 = "mc/" + c2;
        }
        d.debug("UDPConnector ({}) received {} bytes from {}", c2, Integer.valueOf(datagramPacket.getLength()), vcb.b(datagramPacket.getSocketAddress()));
        rawDataChannel.receiveData(vaf.c(Arrays.copyOfRange(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength()), new van(new InetSocketAddress(datagramPacket.getAddress(), datagramPacket.getPort())), this.f15868a, d2, inetSocketAddress));
    }

    public String toString() {
        return getProtocol() + Constants.LINK + vcb.c(getAddress());
    }

    @Override // org.eclipse.californium.elements.Connector
    public String getProtocol() {
        return "UDP";
    }
}
