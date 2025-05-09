package org.eclipse.californium.scandium;

import com.huawei.openalliance.ad.constant.Constants;
import defpackage.vbn;
import defpackage.vbw;
import defpackage.vbz;
import defpackage.vcb;
import defpackage.vcd;
import defpackage.vcp;
import defpackage.vdz;
import defpackage.vha;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import okio.Utf8;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.NodeConnectionIdGenerator;
import org.slf4j.Logger;

/* loaded from: classes10.dex */
public class DtlsClusterConnector extends DTLSConnector {
    private final boolean k;
    private final vbw l;
    protected final DtlsClusterHealth m;
    protected volatile DatagramSocket o;
    private volatile ClusterNodesProvider p;
    private final List<Thread> q;
    private final InetSocketAddress r;
    private final boolean s;
    private final NodeConnectionIdGenerator t;
    private static final Logger n = vha.a((Class<?>) DtlsClusterConnector.class);
    public static final Byte f = Byte.valueOf(Utf8.REPLACEMENT_BYTE);
    public static final Byte h = (byte) 62;

    public interface ClusterNodesProvider {
        boolean available(InetSocketAddress inetSocketAddress);

        InetSocketAddress getClusterNode(int i);
    }

    protected void a(DatagramPacket datagramPacket) throws IOException {
    }

    protected int b() {
        return 0;
    }

    @Override // org.eclipse.californium.scandium.DTLSConnector
    protected DtlsHealth d(vcd vcdVar) {
        return new vbz(vcdVar.r());
    }

    @Override // org.eclipse.californium.scandium.DTLSConnector
    protected void b(InetSocketAddress inetSocketAddress, DatagramSocket datagramSocket, Integer num) throws IOException {
        try {
            this.o = new DatagramSocket(this.r);
            super.b(inetSocketAddress, datagramSocket, num);
            if (this.s) {
                g();
            }
        } catch (IOException e) {
            n.error("cluster-node {}: management-interface {} failed!", Integer.valueOf(j()), vcb.b((SocketAddress) this.r));
            throw e;
        }
    }

    protected void g() {
        int intValue = ((Integer) this.f15889a.d(DtlsConfig.z)).intValue();
        for (int i = 0; i < intValue; i++) {
            DTLSConnector.Worker worker = new DTLSConnector.Worker("DTLS-Cluster-" + j() + "-Receiver-" + i + Constants.LINK + this.r) { // from class: org.eclipse.californium.scandium.DtlsClusterConnector.5
                private final byte[] c;
                private final DatagramPacket d;

                {
                    int i2 = DtlsClusterConnector.this.g + 28;
                    byte[] bArr = new byte[i2];
                    this.c = bArr;
                    this.d = new DatagramPacket(bArr, i2);
                }

                @Override // org.eclipse.californium.scandium.DTLSConnector.Worker
                public void doWork() throws Exception {
                    this.d.setData(this.c);
                    DtlsClusterConnector.this.o.receive(this.d);
                    Byte e = DtlsClusterConnector.this.e(this.d);
                    if (e != null) {
                        if (DtlsClusterConnector.this.d(e, this.d)) {
                            DtlsClusterConnector.this.c(e, this.d);
                            return;
                        } else {
                            if (DtlsClusterConnector.this.m != null) {
                                DtlsClusterConnector.this.m.dropForwardMessage();
                                return;
                            }
                            return;
                        }
                    }
                    DtlsClusterConnector.this.a(this.d);
                }
            };
            worker.setDaemon(true);
            worker.start();
            this.q.add(worker);
        }
        n.info("cluster-node {}: started {}", Integer.valueOf(j()), this.o.getLocalSocketAddress());
    }

    @Override // org.eclipse.californium.scandium.DTLSConnector, org.eclipse.californium.elements.Connector
    public void stop() {
        super.stop();
        synchronized (this) {
            this.o.close();
            for (Thread thread : this.q) {
                thread.interrupt();
                try {
                    thread.join(500L);
                } catch (InterruptedException unused) {
                }
            }
            this.q.clear();
        }
    }

    public int j() {
        return this.t.getNodeId();
    }

    protected Byte e(DatagramPacket datagramPacket) {
        byte b = datagramPacket.getData()[datagramPacket.getOffset()];
        Byte b2 = f;
        if (b == b2.byteValue()) {
            return b2;
        }
        Byte b3 = h;
        if (b == b3.byteValue()) {
            return b3;
        }
        return null;
    }

    protected boolean d(Byte b, DatagramPacket datagramPacket) {
        int length = datagramPacket.getLength();
        return length >= 33 && length > (((datagramPacket.getData()[datagramPacket.getOffset() + 3] & 255) + 4) + b()) + 25;
    }

    protected void c(Byte b, DatagramPacket datagramPacket) throws IOException {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) datagramPacket.getSocketAddress();
        DatagramPacket h2 = h(datagramPacket);
        if (h2 == null) {
            DtlsClusterHealth dtlsClusterHealth = this.m;
            if (dtlsClusterHealth != null) {
                dtlsClusterHealth.dropForwardMessage();
                return;
            }
            return;
        }
        if (f.equals(b)) {
            n.trace("cluster-node {}: received forwarded message", Integer.valueOf(j()));
            super.a(h2, inetSocketAddress);
            DtlsClusterHealth dtlsClusterHealth2 = this.m;
            if (dtlsClusterHealth2 != null) {
                dtlsClusterHealth2.processForwardedMessage();
                return;
            }
            return;
        }
        if (h.equals(b)) {
            n.trace("cluster-node {}: received backwarded outgoing message", Integer.valueOf(j()));
            super.b(h2);
            DtlsClusterHealth dtlsClusterHealth3 = this.m;
            if (dtlsClusterHealth3 != null) {
                dtlsClusterHealth3.sendBackwardedMessage();
            }
        }
    }

    protected void d(DatagramPacket datagramPacket) throws IOException {
        this.o.send(datagramPacket);
    }

    @Override // org.eclipse.californium.scandium.DTLSConnector
    protected void a(DatagramPacket datagramPacket, InetSocketAddress inetSocketAddress) {
        int offset = datagramPacket.getOffset();
        int length = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();
        InetSocketAddress inetSocketAddress2 = (InetSocketAddress) datagramPacket.getSocketAddress();
        if (data[offset] != ContentType.TLS12_CID.getCode()) {
            n.trace("cluster-node {}: received no CID message from {}, {} bytes.", Integer.valueOf(j()), vcb.b((SocketAddress) inetSocketAddress2), Integer.valueOf(length));
        } else if (length > 13) {
            vcp e = vdz.e(new vbn(data, offset, length), this.c);
            if (e != null) {
                int nodeId = this.t.getNodeId(e);
                if (j() != nodeId) {
                    Logger logger = n;
                    logger.trace("cluster-node {}: received foreign message for {} from {}", Integer.valueOf(j()), Integer.valueOf(nodeId), vcb.b((SocketAddress) inetSocketAddress2));
                    InetSocketAddress clusterNode = this.p.getClusterNode(nodeId);
                    if (clusterNode != null) {
                        DatagramPacket e2 = e(f.byteValue(), datagramPacket, (byte[]) null);
                        e2.setSocketAddress(clusterNode);
                        try {
                            logger.trace("cluster-node {}: forwards received message from {} to {}, {} bytes", Integer.valueOf(j()), vcb.b((SocketAddress) inetSocketAddress2), vcb.b((SocketAddress) clusterNode), Integer.valueOf(length));
                            d(e2);
                            DtlsClusterHealth dtlsClusterHealth = this.m;
                            if (dtlsClusterHealth != null) {
                                dtlsClusterHealth.forwardMessage();
                                return;
                            }
                            return;
                        } catch (IOException e3) {
                            n.info("cluster-node {}: error forwarding to {}/{}:", Integer.valueOf(j()), Integer.valueOf(nodeId), vcb.b((SocketAddress) clusterNode), e3);
                            DtlsClusterHealth dtlsClusterHealth2 = this.m;
                            if (dtlsClusterHealth2 != null) {
                                dtlsClusterHealth2.dropForwardMessage();
                            } else {
                                this.d.receivingRecord(true);
                            }
                        }
                    } else {
                        this.l.a("cluster-node {}: received foreign message from {} for unknown node {}, {} bytes, dropping.", Integer.valueOf(j()), vcb.b((SocketAddress) inetSocketAddress2), Integer.valueOf(nodeId), Integer.valueOf(length));
                        DtlsClusterHealth dtlsClusterHealth3 = this.m;
                        if (dtlsClusterHealth3 != null) {
                            dtlsClusterHealth3.dropForwardMessage();
                        } else {
                            this.d.receivingRecord(true);
                        }
                    }
                } else {
                    n.trace("cluster-node {}: received own message from {}, {} bytes", Integer.valueOf(j()), vcb.b((SocketAddress) inetSocketAddress2), Integer.valueOf(length));
                }
            } else {
                this.l.a("cluster-node {}: received broken CID message from {}", Integer.valueOf(j()), vcb.b((SocketAddress) inetSocketAddress2));
            }
        } else {
            this.l.a("cluster-node {}: received too short CID message from {}", Integer.valueOf(j()), vcb.b((SocketAddress) inetSocketAddress2));
        }
        super.a(datagramPacket, (InetSocketAddress) null);
    }

    @Override // org.eclipse.californium.scandium.DTLSConnector
    protected void e(vdz vdzVar) throws IOException {
        InetSocketAddress i = vdzVar.i();
        InetSocketAddress g = vdzVar.g();
        if (g != null && this.k) {
            if (this.p.available(g)) {
                byte[] n2 = vdzVar.n();
                int length = n2.length;
                int i2 = length + 28;
                n.trace("cluster-node {}: backwards send message for {} to {}, {} bytes", Integer.valueOf(j()), vcb.b((SocketAddress) i), g, Integer.valueOf(length));
                DatagramPacket e = e(h.byteValue(), new DatagramPacket(new byte[i2], i2, i), n2);
                e.setSocketAddress(g);
                try {
                    d(e);
                    DtlsClusterHealth dtlsClusterHealth = this.m;
                    if (dtlsClusterHealth != null) {
                        dtlsClusterHealth.backwardMessage();
                        return;
                    }
                    return;
                } catch (IOException e2) {
                    n.debug("cluster-node {}: sending internal message failed!", Integer.valueOf(j()), e2);
                    DtlsClusterHealth dtlsClusterHealth2 = this.m;
                    if (dtlsClusterHealth2 != null) {
                        dtlsClusterHealth2.dropBackwardMessage();
                    }
                    throw e2;
                }
            }
            DtlsClusterHealth dtlsClusterHealth3 = this.m;
            if (dtlsClusterHealth3 != null) {
                dtlsClusterHealth3.dropBackwardMessage();
            }
            throw new IOException("Cluster internal destination " + vcb.c(g) + " not longer available!");
        }
        n.trace("cluster-node {}: sends message to {}, {} bytes", Integer.valueOf(j()), vcb.b((SocketAddress) i), Integer.valueOf(vdzVar.o()));
        super.e(vdzVar);
    }

    private DatagramPacket e(byte b, DatagramPacket datagramPacket, byte[] bArr) {
        int length;
        byte[] address = datagramPacket.getAddress().getAddress();
        int length2 = address.length + 4 + b();
        byte[] data = datagramPacket.getData();
        if (bArr == null) {
            int offset = datagramPacket.getOffset();
            length = datagramPacket.getLength();
            if (offset != length2) {
                System.arraycopy(data, offset, data, length2, length);
            }
        } else {
            length = bArr.length;
            System.arraycopy(bArr, 0, data, length2, length);
        }
        data[0] = b;
        data[1] = (byte) datagramPacket.getPort();
        data[2] = (byte) (datagramPacket.getPort() >> 8);
        data[3] = (byte) address.length;
        System.arraycopy(address, 0, data, 4, address.length);
        datagramPacket.setData(data, 0, length + length2);
        return datagramPacket;
    }

    private DatagramPacket h(DatagramPacket datagramPacket) {
        try {
            byte[] data = datagramPacket.getData();
            int offset = datagramPacket.getOffset();
            int length = datagramPacket.getLength();
            int i = data[offset + 3] & 255;
            byte b = data[offset + 1];
            byte b2 = data[offset + 2];
            int i2 = offset + 4;
            byte[] copyOfRange = Arrays.copyOfRange(data, i2, i2 + i);
            int b3 = i + 4 + b();
            datagramPacket.setAddress(InetAddress.getByAddress(copyOfRange));
            datagramPacket.setPort((b & 255) | ((b2 & 255) << 8));
            datagramPacket.setData(data, offset + b3, length - b3);
            return datagramPacket;
        } catch (RuntimeException | UnknownHostException unused) {
            return null;
        }
    }
}
