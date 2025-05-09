package org.eclipse.californium.scandium;

import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.param.CanvasConfig;
import defpackage.uzz;
import defpackage.vab;
import defpackage.vaf;
import defpackage.vbd;
import defpackage.vbg;
import defpackage.vbj;
import defpackage.vbn;
import defpackage.vbp;
import defpackage.vbr;
import defpackage.vbv;
import defpackage.vbw;
import defpackage.vby;
import defpackage.vcb;
import defpackage.vcd;
import defpackage.vcf;
import defpackage.vcg;
import defpackage.vci;
import defpackage.vck;
import defpackage.vcm;
import defpackage.vcn;
import defpackage.vcp;
import defpackage.vct;
import defpackage.vcu;
import defpackage.vdb;
import defpackage.vdc;
import defpackage.vdg;
import defpackage.vdl;
import defpackage.vdm;
import defpackage.vdp;
import defpackage.vdu;
import defpackage.vdw;
import defpackage.vdz;
import defpackage.veb;
import defpackage.vec;
import defpackage.vfe;
import defpackage.vha;
import defpackage.vhg;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.californium.elements.Connector;
import org.eclipse.californium.elements.EndpointContext;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.PersistentComponent;
import org.eclipse.californium.elements.PersistentConnector;
import org.eclipse.californium.elements.RawDataChannel;
import org.eclipse.californium.elements.util.ClockUtil;
import org.eclipse.californium.elements.util.LimitedRunnable;
import org.eclipse.californium.elements.util.NetworkInterfacesUtil;
import org.eclipse.californium.elements.util.SerialExecutor;
import org.eclipse.californium.scandium.config.DtlsConfig;
import org.eclipse.californium.scandium.dtls.AlertMessage;
import org.eclipse.californium.scandium.dtls.ConnectionIdGenerator;
import org.eclipse.californium.scandium.dtls.ContentType;
import org.eclipse.californium.scandium.dtls.DTLSConnectionState;
import org.eclipse.californium.scandium.dtls.DTLSMessage;
import org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode;
import org.eclipse.californium.scandium.dtls.HandshakeMessage;
import org.eclipse.californium.scandium.dtls.HandshakeType;
import org.eclipse.californium.scandium.dtls.Handshaker;
import org.eclipse.californium.scandium.dtls.MaxFragmentLengthExtension;
import org.eclipse.californium.scandium.dtls.ReadWriteLockConnectionStore;
import org.eclipse.californium.scandium.dtls.RecordLayer;
import org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore;
import org.eclipse.californium.scandium.dtls.SessionListener;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class DTLSConnector implements Connector, PersistentConnector, PersistentComponent, RecordLayer {
    public static final boolean b;
    private static final Logger f;
    private static final long h;
    private static final int k;
    private static final vbw l;
    private static final Logger m;
    private static final vbw n;
    private static final vbw o;
    private static final int t;

    /* renamed from: a, reason: collision with root package name */
    protected final vcd f15889a;
    private volatile EndpointContextMatcher aa;
    private int ab;
    private boolean ac;
    private volatile ExecutorService ad;
    private final int ae;
    private InetSocketAddress af;
    private final int ag;
    private final int ah;
    private final int ai;
    private Integer aj;
    private final AtomicInteger ak;
    private final AtomicInteger al;
    private volatile RawDataChannel am;
    private final SessionListener an;
    private final AtomicInteger ao;
    private final List<Thread> ap;
    private final AtomicInteger aq;
    private ScheduledFuture<?> ar;
    private final vdu as;
    private final String at;
    private final ResumptionVerifier au;
    private final Queue<vcm> av;
    private AtomicBoolean aw;
    private final AtomicInteger ax;
    private final boolean ay;
    private final int az;
    private volatile DatagramSocket ba;
    private ScheduledFuture<?> bb;
    private final SessionListener bc;
    private final int bd;
    private final boolean be;
    private final boolean bf;
    private final boolean bg;
    protected final ConnectionIdGenerator c;
    protected final DtlsHealth d;
    protected final ExtendedMasterSecretMode e;
    protected int g;
    protected ScheduledExecutorService i;
    protected final boolean j;
    private final Long p;
    private final ConnectionListener q;
    private final ResumptionSupportingConnectionStore r;
    private volatile AlertHandler s;
    private final DtlsConfig.DtlsRole u;
    private final SessionListener v;
    private final DatagramFilter w;
    private final vby x;
    private final String y;
    private int z;

    protected void e(Handshaker handshaker) {
    }

    static {
        boolean z;
        Logger a2 = vha.a((Class<?>) DTLSConnector.class);
        m = a2;
        Logger d = vha.d(a2.getName() + ".drops");
        f = d;
        n = new vbw(d, 3L, 30L, TimeUnit.SECONDS);
        o = new vbw(d, 3L, 30L, TimeUnit.SECONDS);
        l = new vbw(d, 3L, 30L, TimeUnit.SECONDS);
        int overallMaxCiphertextExpansion = CipherSuite.getOverallMaxCiphertextExpansion();
        k = overallMaxCiphertextExpansion;
        t = overallMaxCiphertextExpansion + 16409;
        h = (vby.b * 2) + TimeUnit.SECONDS.toNanos(15L);
        try {
            vhg.d();
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        b = z;
    }

    public DTLSConnector(vcd vcdVar) {
        this(vcdVar, c(vcdVar));
    }

    protected static ResumptionSupportingConnectionStore c(vcd vcdVar) {
        if (((Boolean) vcdVar.d(DtlsConfig.ad)).booleanValue()) {
            return new vdl(((Integer) vcdVar.d(DtlsConfig.l)).intValue(), vcdVar.b(DtlsConfig.at, TimeUnit.SECONDS).longValue(), vcdVar.y(), ((Boolean) vcdVar.d(DtlsConfig.aj)).booleanValue()).e(vcdVar.r());
        }
        return new vdm(((Integer) vcdVar.d(DtlsConfig.l)).intValue(), vcdVar.b(DtlsConfig.at, TimeUnit.SECONDS).longValue(), vcdVar.y()).a(vcdVar.r());
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x01b4, code lost:
    
        if (r14.isEnabled() == false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected DTLSConnector(defpackage.vcd r14, final org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore r15) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.DTLSConnector.<init>(vcd, org.eclipse.californium.scandium.dtls.ResumptionSupportingConnectionStore):void");
    }

    public boolean d() {
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth instanceof DtlsHealthExtended) {
            ((DtlsHealthExtended) dtlsHealth).setConnections(this.ah - this.r.remainingCapacity());
        }
        DtlsHealth dtlsHealth2 = this.d;
        if (!(dtlsHealth2 instanceof DtlsHealthExtended2)) {
            return false;
        }
        DtlsHealthExtended2 dtlsHealthExtended2 = (DtlsHealthExtended2) dtlsHealth2;
        int i = this.ag - this.aq.get();
        dtlsHealthExtended2.setPendingOutgoingJobs(i);
        if (i > 0) {
            m.debug("Pending out jobs {}", Integer.valueOf(i));
        }
        boolean z = i > 0;
        int i2 = this.ae - this.ao.get();
        dtlsHealthExtended2.setPendingIncomingJobs(i2);
        if (i2 > 0) {
            m.debug("Pending in jobs {}", Integer.valueOf(i2));
        }
        boolean z2 = i2 > 0;
        int i3 = this.ai - this.al.get();
        dtlsHealthExtended2.setPendingHandshakeJobs(i3);
        if (i3 > 0) {
            m.debug("Pending handshake jobs {}", Integer.valueOf(i3));
        }
        return (i3 > 0) | z | z2;
    }

    protected DtlsHealth d(vcd vcdVar) {
        return new vcg(vcdVar.r());
    }

    private final void a(Handshaker handshaker) {
        handshaker.addSessionListener(this.bc);
        handshaker.addSessionListener(this.v);
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth != null) {
            dtlsHealth.startHandshake();
        }
        e(handshaker);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d(Handshaker handshaker) {
        try {
            final vcm connection = handshaker.getConnection();
            this.r.putEstablishedSession(connection);
            SerialExecutor g = connection.g();
            List<vaf> takeDeferredApplicationData = handshaker.takeDeferredApplicationData();
            if (!takeDeferredApplicationData.isEmpty()) {
                m.trace("DTLS context with [{}] established, now process deferred {} outgoing messages", handshaker.getPeerAddress(), Integer.valueOf(takeDeferredApplicationData.size()));
                for (final vaf vafVar : takeDeferredApplicationData) {
                    g.execute(new Runnable() { // from class: org.eclipse.californium.scandium.DTLSConnector.14
                        @Override // java.lang.Runnable
                        public void run() {
                            DTLSConnector.this.d(vafVar, connection);
                        }
                    });
                }
            }
            List<vdz> takeDeferredRecordsOfNextEpoch = handshaker.takeDeferredRecordsOfNextEpoch();
            if (takeDeferredRecordsOfNextEpoch.isEmpty()) {
                return;
            }
            m.trace("DTLS context with [{}] established, now process deferred {} incoming messages", handshaker.getPeerAddress(), Integer.valueOf(takeDeferredRecordsOfNextEpoch.size()));
            for (final vdz vdzVar : takeDeferredRecordsOfNextEpoch) {
                g.execute(new Runnable() { // from class: org.eclipse.californium.scandium.DTLSConnector.12
                    @Override // java.lang.Runnable
                    public void run() {
                        DTLSConnector.this.processRecord(vdzVar, connection);
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
            m.debug("stopping.");
        }
    }

    private long b() {
        return ClockUtil.d() - h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(int r26) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.DTLSConnector.e(int):void");
    }

    protected void a() {
        this.av.clear();
        this.ax.set(0);
    }

    @Override // org.eclipse.californium.elements.Connector
    public final void start() throws IOException {
        synchronized (this) {
            a(this.f15889a.e());
        }
    }

    private ExecutorService j() {
        return this.ad;
    }

    protected void a(InetSocketAddress inetSocketAddress) throws IOException {
        if (this.aw.get()) {
            return;
        }
        b(inetSocketAddress, new DatagramSocket((SocketAddress) null), (Integer) null);
    }

    protected void b(InetSocketAddress inetSocketAddress, DatagramSocket datagramSocket, Integer num) throws IOException {
        Long p;
        this.ba = datagramSocket;
        this.aq.set(this.ag);
        this.ao.set(this.ae);
        this.al.set(this.ai);
        if (inetSocketAddress.getPort() != 0 && this.f15889a.ae().booleanValue()) {
            Logger logger = m;
            logger.info("Enable address reuse for socket!");
            datagramSocket.setReuseAddress(true);
            if (!datagramSocket.getReuseAddress()) {
                logger.warn("Enable address reuse for socket failed!");
            }
        }
        Integer num2 = (Integer) this.f15889a.d(DtlsConfig.ai);
        if (num2 != null && num2.intValue() > 0) {
            try {
                datagramSocket.setReceiveBufferSize(num2.intValue());
            } catch (IllegalArgumentException e) {
                m.error("failed to apply receive buffer size {}", num2, e);
            }
        }
        Integer num3 = (Integer) this.f15889a.d(DtlsConfig.ap);
        if (num3 != null && num3.intValue() > 0) {
            try {
                datagramSocket.setSendBufferSize(num3.intValue());
            } catch (IllegalArgumentException e2) {
                m.error("failed to apply send buffer size {}", num3, e2);
            }
        }
        int receiveBufferSize = datagramSocket.getReceiveBufferSize();
        int sendBufferSize = datagramSocket.getSendBufferSize();
        if (!datagramSocket.isBound()) {
            datagramSocket.bind(inetSocketAddress);
        }
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress(datagramSocket.getLocalAddress(), datagramSocket.getLocalPort());
        InetSocketAddress inetSocketAddress3 = this.af;
        if (inetSocketAddress3 != null && !inetSocketAddress2.equals(inetSocketAddress3)) {
            this.r.markAllAsResumptionRequired();
        }
        MaxFragmentLengthExtension.Length length = (MaxFragmentLengthExtension.Length) this.f15889a.d(DtlsConfig.r);
        if (length != null) {
            this.g = length.length() + k + 25;
        }
        Integer num4 = (Integer) this.f15889a.d(DtlsConfig.v);
        this.aj = num4;
        if (num4 != null) {
            m.info("Configured MTU [{}]", num4);
        } else if (num != null) {
            this.aj = num;
            m.info("Forced MTU [{}]", num);
        } else {
            InetAddress address = inetSocketAddress.getAddress();
            if (address.isAnyLocalAddress()) {
                this.z = NetworkInterfacesUtil.b();
                this.ab = NetworkInterfacesUtil.e();
                m.info("multiple network interfaces, using smallest MTU [IPv4 {}, IPv6 {}]", Integer.valueOf(this.z), Integer.valueOf(this.ab));
            } else {
                NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(address);
                boolean z = address instanceof Inet6Address;
                if (byInetAddress == null || byInetAddress.getMTU() <= 0) {
                    if (z) {
                        int e3 = NetworkInterfacesUtil.e();
                        this.ab = e3;
                        m.info("Cannot determine MTU of network interface, using minimum MTU [{}] of IPv6 instead", Integer.valueOf(e3));
                    } else {
                        int b2 = NetworkInterfacesUtil.b();
                        this.z = b2;
                        m.info("Cannot determine MTU of network interface, using minimum MTU [{}] of IPv4 instead", Integer.valueOf(b2));
                    }
                } else if (z) {
                    this.ab = byInetAddress.getMTU();
                } else {
                    this.z = byInetAddress.getMTU();
                }
            }
            Integer num5 = (Integer) this.f15889a.d(DtlsConfig.aa);
            if (num5 != null && num5.intValue() < this.g) {
                if (this.z > num5.intValue()) {
                    int intValue = num5.intValue();
                    this.z = intValue;
                    m.info("Limit MTU IPv4[{}]", Integer.valueOf(intValue));
                }
                if (this.ab > num5.intValue()) {
                    int intValue2 = num5.intValue();
                    this.ab = intValue2;
                    m.info("Limit MTU IPv6[{}]", Integer.valueOf(intValue2));
                }
            } else {
                int i = this.z;
                int i2 = this.g;
                if (i > i2) {
                    this.z = i2;
                    m.info("Buffersize MTU IPv4[{}]", Integer.valueOf(i2));
                }
                int i3 = this.ab;
                int i4 = this.g;
                if (i3 > i4) {
                    this.ab = i4;
                    m.info("Buffersize MTU IPv6[{}]", Integer.valueOf(i4));
                }
            }
        }
        this.af = inetSocketAddress2;
        if (this.ad instanceof ScheduledExecutorService) {
            this.i = (ScheduledExecutorService) this.ad;
        } else {
            this.i = vbr.b(new vbp("DTLS-Timer-" + this.af + "#", vbv.c));
        }
        if (this.ad == null) {
            int intValue3 = ((Integer) this.f15889a.d(DtlsConfig.j)).intValue();
            if (intValue3 > 1) {
                this.ad = vbr.a(intValue3 - 1, new vbp("DTLS-Worker-" + this.af + "#", vbv.c));
            } else {
                this.ad = this.i;
            }
            ResumptionSupportingConnectionStore resumptionSupportingConnectionStore = this.r;
            if (resumptionSupportingConnectionStore instanceof ReadWriteLockConnectionStore) {
                ((ReadWriteLockConnectionStore) resumptionSupportingConnectionStore).setExecutor(this.ad);
            }
            this.ac = true;
        }
        long b3 = b();
        ArrayList arrayList = new ArrayList();
        for (vcm vcmVar : this.r) {
            if (!vcmVar.u()) {
                vcmVar.e(this.ad, this.q);
            }
            if (vcmVar.q() && (p = vcmVar.p()) != null) {
                if (b3 - p.longValue() < 0) {
                    arrayList.add(vcmVar);
                } else {
                    vcmVar.a((vck) null);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            m.info("Restore {} recent handshakes!", Integer.valueOf(arrayList.size()));
            Collections.sort(arrayList, new Comparator<vcm>() { // from class: org.eclipse.californium.scandium.DTLSConnector.15
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(vcm vcmVar2, vcm vcmVar3) {
                    Long p2 = vcmVar2.p();
                    Long p3 = vcmVar3.p();
                    if (p2 == null || p3 == null) {
                        if (p2 == null) {
                            return -1;
                        }
                        return p3 == null ? 1 : 0;
                    }
                    long longValue = p2.longValue() - p3.longValue();
                    if (longValue > 0) {
                        return 1;
                    }
                    return longValue < 0 ? -1 : 0;
                }
            });
            this.av.addAll(arrayList);
            this.ax.set(arrayList.size());
        }
        this.aw.set(true);
        int intValue4 = ((Integer) this.f15889a.d(DtlsConfig.z)).intValue();
        for (int i5 = 0; i5 < intValue4; i5++) {
            Worker worker = new Worker("DTLS-Receiver-" + i5 + Constants.LINK + this.af) { // from class: org.eclipse.californium.scandium.DTLSConnector.13

                /* renamed from: a, reason: collision with root package name */
                private final DatagramPacket f15891a;
                private final byte[] d;

                {
                    byte[] bArr = new byte[DTLSConnector.this.g];
                    this.d = bArr;
                    this.f15891a = new DatagramPacket(bArr, DTLSConnector.this.g);
                }

                @Override // org.eclipse.californium.scandium.DTLSConnector.Worker
                public void doWork() throws Exception {
                    if (DTLSConnector.b) {
                        vhg.d();
                    }
                    this.f15891a.setData(this.d);
                    DTLSConnector.this.c(this.f15891a);
                }
            };
            worker.setDaemon(true);
            worker.start();
            this.ap.add(worker);
        }
        Integer num6 = this.aj;
        m.info("DTLSConnector listening on {}, recv buf = {}, send buf = {}, recv packet size = {}, MTU = {}", this.af, Integer.valueOf(receiveBufferSize), Integer.valueOf(sendBufferSize), Integer.valueOf(this.g), num6 != null ? num6.toString() : "IPv4 " + this.z + " / IPv6 " + this.ab);
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth != null && dtlsHealth.isEnabled()) {
            final int q = this.f15889a.q();
            long j = q;
            if ((this.d instanceof DtlsHealthExtended) && (q == 0 || q > 2000)) {
                j = 2000;
            }
            long j2 = j;
            if (j2 > 0) {
                this.bb = this.i.scheduleAtFixedRate(new Runnable() { // from class: org.eclipse.californium.scandium.DTLSConnector.16
                    private volatile long d = ClockUtil.d();

                    @Override // java.lang.Runnable
                    public void run() {
                        long d = ClockUtil.d();
                        if (q > 0 && TimeUnit.NANOSECONDS.toMillis(d - this.d) > q) {
                            DTLSConnector.this.d.dump(DTLSConnector.this.f15889a.r(), DTLSConnector.this.ah, DTLSConnector.this.r.remainingCapacity(), DTLSConnector.this.ak.get());
                            this.d = d;
                        } else {
                            DTLSConnector.this.d();
                        }
                    }
                }, j2, j2, TimeUnit.MILLISECONDS);
            }
            d();
        }
        this.ar = this.i.scheduleWithFixedDelay(new Runnable() { // from class: org.eclipse.californium.scandium.DTLSConnector.1
            private int e = 0;

            @Override // java.lang.Runnable
            public void run() {
                try {
                    DTLSConnector.this.e(this.e);
                } catch (Throwable th) {
                    DTLSConnector.m.warn("Cleanup recent handshakes failed (loop {})!", Integer.valueOf(this.e), th);
                }
                this.e++;
            }
        }, 5000L, 5000L, TimeUnit.MILLISECONDS);
    }

    private final DatagramSocket h() {
        return this.ba;
    }

    @Override // org.eclipse.californium.elements.Connector
    public void stop() {
        boolean compareAndSet;
        ExecutorService executorService;
        ExecutorService executorService2;
        ScheduledExecutorService scheduledExecutorService;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            compareAndSet = this.aw.compareAndSet(true, false);
            executorService = null;
            if (compareAndSet) {
                m.debug("DTLS connector on [{}] stopping ...", this.af);
                ScheduledFuture<?> scheduledFuture = this.bb;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                    this.bb = null;
                }
                ScheduledFuture<?> scheduledFuture2 = this.ar;
                if (scheduledFuture2 != null) {
                    scheduledFuture2.cancel(false);
                    this.ar = null;
                }
                a();
                Iterator<Thread> it = this.ap.iterator();
                while (it.hasNext()) {
                    it.next().interrupt();
                }
                if (this.ba != null) {
                    this.ba.close();
                    this.ba = null;
                }
                this.aj = null;
                this.z = RecordLayer.DEFAULT_IPV4_MTU;
                this.ab = 1280;
                this.r.stop(arrayList);
                ExecutorService executorService3 = this.ad;
                ScheduledExecutorService scheduledExecutorService2 = this.i;
                if (executorService3 != scheduledExecutorService2) {
                    arrayList.addAll(scheduledExecutorService2.shutdownNow());
                    scheduledExecutorService = this.i;
                    this.i = null;
                } else {
                    scheduledExecutorService = null;
                }
                if (this.ac) {
                    arrayList.addAll(this.ad.shutdownNow());
                    ExecutorService executorService4 = this.ad;
                    this.ad = null;
                    this.ac = false;
                    ResumptionSupportingConnectionStore resumptionSupportingConnectionStore = this.r;
                    if (resumptionSupportingConnectionStore instanceof ReadWriteLockConnectionStore) {
                        ((ReadWriteLockConnectionStore) resumptionSupportingConnectionStore).setExecutor(null);
                    }
                    executorService = executorService4;
                }
                for (Thread thread : this.ap) {
                    thread.interrupt();
                    try {
                        thread.join(500L);
                    } catch (InterruptedException unused) {
                    }
                }
                this.ap.clear();
                executorService2 = executorService;
                executorService = scheduledExecutorService;
            } else {
                executorService2 = null;
            }
        }
        if (executorService != null) {
            try {
                if (!executorService.awaitTermination(500L, TimeUnit.MILLISECONDS)) {
                    m.warn("Shutdown DTLS connector on [{}] timer not terminated in time!", this.af);
                }
            } catch (InterruptedException unused2) {
            }
        }
        if (executorService2 != null) {
            try {
                if (!executorService2.awaitTermination(500L, TimeUnit.MILLISECONDS)) {
                    m.warn("Shutdown DTLS connector on [{}] executor not terminated in time!", this.af);
                }
            } catch (InterruptedException unused3) {
            }
        }
        vbr.c(arrayList);
        if (compareAndSet) {
            m.debug("DTLS connector on [{}] stopped.", this.af);
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public void destroy() {
        stop();
        this.r.clear();
        this.am = null;
    }

    @Override // org.eclipse.californium.elements.PersistentComponent
    public String getLabel() {
        return this.at;
    }

    @Override // org.eclipse.californium.elements.PersistentComponent
    public int save(OutputStream outputStream, long j) throws IOException {
        if (isRunning()) {
            throw new IllegalStateException("Connector is running, save not possible!");
        }
        return this.r.saveConnections(outputStream, j);
    }

    @Override // org.eclipse.californium.elements.PersistentComponent
    public int load(InputStream inputStream, long j) throws IOException {
        return this.r.loadConnections(inputStream, j);
    }

    @Override // org.eclipse.californium.elements.PersistentConnector
    public int saveConnections(OutputStream outputStream, long j) throws IOException {
        return save(outputStream, j);
    }

    @Override // org.eclipse.californium.elements.PersistentConnector
    public int loadConnections(InputStream inputStream, long j) throws IOException {
        return load(inputStream, j);
    }

    private final vcm b(InetSocketAddress inetSocketAddress, vcp vcpVar, boolean z) {
        vcm vcmVar;
        vcm vcmVar2;
        ExecutorService j = j();
        ResumptionSupportingConnectionStore resumptionSupportingConnectionStore = this.r;
        if (resumptionSupportingConnectionStore instanceof ReadWriteLockConnectionStore) {
            ReadWriteLockConnectionStore readWriteLockConnectionStore = (ReadWriteLockConnectionStore) resumptionSupportingConnectionStore;
            if (vcpVar != null) {
                vcmVar2 = resumptionSupportingConnectionStore.get(vcpVar);
            } else {
                vcmVar2 = resumptionSupportingConnectionStore.get(inetSocketAddress);
            }
            if (z && vcmVar2 == null && vcpVar == null) {
                readWriteLockConnectionStore.writeLock().lock();
                try {
                    vcmVar2 = this.r.get(inetSocketAddress);
                    if (vcmVar2 == null) {
                        m.trace("create new connection for {}", inetSocketAddress);
                        vcm vcmVar3 = new vcm(inetSocketAddress);
                        vcmVar3.e(j, this.q);
                        if (this.aw.get()) {
                            if (!this.r.put(vcmVar3)) {
                                return null;
                            }
                        }
                        return vcmVar3;
                    }
                } finally {
                    readWriteLockConnectionStore.writeLock().unlock();
                }
            }
        } else {
            synchronized (resumptionSupportingConnectionStore) {
                if (vcpVar != null) {
                    vcmVar = this.r.get(vcpVar);
                } else {
                    vcm vcmVar4 = this.r.get(inetSocketAddress);
                    if (vcmVar4 == null && z) {
                        m.trace("create new connection for {}", inetSocketAddress);
                        vcm vcmVar5 = new vcm(inetSocketAddress);
                        vcmVar5.e(j, this.q);
                        if (!this.aw.get() || this.r.put(vcmVar5)) {
                            return vcmVar5;
                        }
                        return null;
                    }
                    vcmVar = vcmVar4;
                }
                if (this.aw.get() && vcmVar != null && !vcmVar.u()) {
                    vcmVar.e(j, this.q);
                    m.trace("revive connection for {},{}", inetSocketAddress, vcpVar);
                    return vcmVar;
                }
                vcmVar2 = vcmVar;
            }
        }
        if (vcmVar2 == null) {
            m.trace("no connection available for {},{}", inetSocketAddress, vcpVar);
        } else {
            m.trace("connection available for {},{}", inetSocketAddress, vcpVar);
        }
        return vcmVar2;
    }

    protected void c(DatagramPacket datagramPacket) throws IOException {
        DatagramSocket h2 = h();
        if (h2 == null) {
            return;
        }
        h2.receive(datagramPacket);
        if (datagramPacket.getLength() == 0) {
            return;
        }
        a(datagramPacket, (InetSocketAddress) null);
    }

    @Override // org.eclipse.californium.elements.Connector
    public void processDatagram(DatagramPacket datagramPacket) {
        a(datagramPacket, (InetSocketAddress) null);
    }

    protected void a(DatagramPacket datagramPacket, InetSocketAddress inetSocketAddress) {
        InetSocketAddress inetSocketAddress2 = (InetSocketAddress) datagramPacket.getSocketAddress();
        if (b) {
            vhg.b("PEER", vcb.c(inetSocketAddress2));
        }
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth != null) {
            dtlsHealth.receivingRecord(false);
        }
        long d = ClockUtil.d();
        if (inetSocketAddress2.getPort() == 0) {
            f.trace("Discarding record with {} bytes from [{}] without source-port", Integer.valueOf(datagramPacket.getLength()), vcb.b((SocketAddress) inetSocketAddress2));
            DtlsHealth dtlsHealth2 = this.d;
            if (dtlsHealth2 != null) {
                dtlsHealth2.receivingRecord(true);
                return;
            }
            return;
        }
        DatagramFilter datagramFilter = this.w;
        if (datagramFilter != null && !datagramFilter.onReceiving(datagramPacket)) {
            f.trace("Filter out packet with {} bytes from [{}]", Integer.valueOf(datagramPacket.getLength()), vcb.b((SocketAddress) inetSocketAddress2));
            DtlsHealth dtlsHealth3 = this.d;
            if (dtlsHealth3 != null) {
                dtlsHealth3.receivingRecord(true);
                return;
            }
            return;
        }
        List<vdz> d2 = vdz.d(new vbn(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength()), this.c, d);
        Logger logger = m;
        logger.trace("Received {} DTLS records from {} using a {} byte datagram buffer", Integer.valueOf(d2.size()), vcb.b((SocketAddress) inetSocketAddress2), Integer.valueOf(this.g));
        if (d2.isEmpty()) {
            f.trace("Discarding malicious record with {} bytes from [{}]", Integer.valueOf(datagramPacket.getLength()), vcb.b((SocketAddress) inetSocketAddress2));
            DtlsHealth dtlsHealth4 = this.d;
            if (dtlsHealth4 != null) {
                dtlsHealth4.receivingRecord(true);
                return;
            }
            return;
        }
        if (!this.aw.get()) {
            f.trace("Discarding {} records, startting with {} from [{}] on shutdown", Integer.valueOf(d2.size()), d2.get(0).j(), vcb.b((SocketAddress) inetSocketAddress2));
            logger.debug("Execution shutdown while processing incoming records from peer: {}", vcb.b((SocketAddress) inetSocketAddress2));
            DtlsHealth dtlsHealth5 = this.d;
            if (dtlsHealth5 != null) {
                dtlsHealth5.receivingRecord(true);
                return;
            }
            return;
        }
        d(d2, inetSocketAddress2, inetSocketAddress);
    }

    protected void d(List<vdz> list, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        final vdz vdzVar = list.get(0);
        if (list.size() == 1 && vdzVar.k()) {
            vdzVar.c(inetSocketAddress, inetSocketAddress2);
            if (this.u == DtlsConfig.DtlsRole.CLIENT_ONLY) {
                f.trace("client-only, discarding {} CLIENT_HELLO from [{}]!", Integer.valueOf(list.size()), vcb.b((SocketAddress) inetSocketAddress));
                DtlsHealth dtlsHealth = this.d;
                if (dtlsHealth != null) {
                    dtlsHealth.receivingRecord(true);
                    return;
                }
                return;
            }
            d(j(), inetSocketAddress, new LimitedRunnable(this.ao) { // from class: org.eclipse.californium.scandium.DTLSConnector.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (DTLSConnector.this.aw.get()) {
                            if (DTLSConnector.b) {
                                vhg.b("PEER", vcb.c(vdzVar.i()));
                            }
                            DTLSConnector.this.a(vdzVar);
                            if (DTLSConnector.b) {
                                vhg.d();
                            }
                        }
                    } finally {
                        onDequeueing();
                    }
                }
            });
            return;
        }
        vcp d = vdzVar.d();
        final vcm b2 = b(inetSocketAddress, d, false);
        if (b2 == null) {
            DtlsHealth dtlsHealth2 = this.d;
            if (dtlsHealth2 != null) {
                dtlsHealth2.receivingRecord(true);
            }
            if (d == null) {
                f.trace("Discarding {} records from [{}] received without existing connection", Integer.valueOf(list.size()), vcb.b((SocketAddress) inetSocketAddress));
                return;
            } else {
                f.trace("Discarding {} records from [{},{}] received without existing connection", Integer.valueOf(list.size()), vcb.b((SocketAddress) inetSocketAddress), d);
                return;
            }
        }
        SerialExecutor g = b2.g();
        for (final vdz vdzVar2 : list) {
            vdzVar2.c(inetSocketAddress, inetSocketAddress2);
            try {
                if (!d(g, inetSocketAddress, new LimitedRunnable(this.ao) { // from class: org.eclipse.californium.scandium.DTLSConnector.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (DTLSConnector.this.aw.get() && b2.u()) {
                                DTLSConnector.this.processRecord(vdzVar2, b2);
                            }
                        } finally {
                            onDequeueing();
                        }
                    }
                })) {
                    return;
                }
            } catch (RuntimeException e) {
                m.warn("Unexpected error occurred while processing record [type: {}, peer: {}]", vdzVar2.j(), vcb.b((SocketAddress) inetSocketAddress), e);
                d(b2);
                return;
            }
        }
    }

    protected boolean d(Executor executor, InetSocketAddress inetSocketAddress, LimitedRunnable limitedRunnable) {
        try {
            limitedRunnable.execute(executor);
            return true;
        } catch (RejectedExecutionException e) {
            if (limitedRunnable.isOverflown()) {
                n.c("Inbound jobs overflow! Dropping inbound message from peer [{}]", vcb.b((SocketAddress) inetSocketAddress));
            } else {
                m.debug("Execution rejected while processing record from peer [{}]", vcb.b((SocketAddress) inetSocketAddress), e);
            }
            DtlsHealth dtlsHealth = this.d;
            if (dtlsHealth == null) {
                return false;
            }
            dtlsHealth.receivingRecord(true);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    @Override // org.eclipse.californium.scandium.dtls.RecordLayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void processRecord(defpackage.vdz r17, defpackage.vcm r18) {
        /*
            Method dump skipped, instructions count: 849
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.DTLSConnector.processRecord(vdz, vcm):void");
    }

    private void b(vcm vcmVar) {
        vcn h2 = vcmVar.h();
        if (h2 != null) {
            m.trace("Closing connection with peer [{}]", vcmVar.o());
            c(vcmVar, h2, new AlertMessage(AlertMessage.AlertLevel.WARNING, AlertMessage.AlertDescription.CLOSE_NOTIFY));
            vcmVar.b(true);
        }
    }

    private void d(vcm vcmVar) {
        vcn b2 = vcmVar.b();
        if (b2 != null) {
            m.trace("Terminating connection with peer [{}], Internal Error", vcb.b((SocketAddress) vcmVar.o()));
            c(vcmVar, b2, new AlertMessage(AlertMessage.AlertLevel.FATAL, AlertMessage.AlertDescription.INTERNAL_ERROR));
        }
        this.r.remove(vcmVar, true);
    }

    private void c(vdz vdzVar, vcm vcmVar) {
        Handshaker l2 = vcmVar.l();
        if (vcmVar.h() == null || vcmVar.v()) {
            if (l2 != null) {
                l2.addRecordsOfNextEpochForDeferredProcessing(vdzVar);
                return;
            } else {
                f.debug("Discarding APPLICATION_DATA record received from peer [{}]", vcb.b((SocketAddress) vdzVar.i()));
                return;
            }
        }
        vcf vcfVar = (vcf) vdzVar.c();
        InetSocketAddress o2 = vcmVar.o();
        boolean a2 = a(vdzVar, vcmVar);
        RawDataChannel rawDataChannel = this.am;
        if (rawDataChannel != null) {
            vab.d dVar = new vab.d();
            if (a2) {
                dVar.d(uzz.m, Boolean.TRUE);
            }
            if (o2 != null && !vcmVar.b(o2)) {
                dVar.d(uzz.k, o2);
            }
            uzz e = vcmVar.e(dVar, vdzVar.i());
            m.trace("Received APPLICATION_DATA for {}", e);
            rawDataChannel.receiveData(vaf.c(vcfVar.b(), e, false, vdzVar.f(), this.af));
        }
    }

    private void d(vdz vdzVar, vcm vcmVar, vcn vcnVar) {
        AlertMessage alertMessage = (AlertMessage) vdzVar.c();
        Handshaker l2 = vcmVar.l();
        m.trace("Processing {} ALERT from [{}]: {}", alertMessage.e(), vcb.b((SocketAddress) vcmVar.o()), alertMessage.b());
        vdb vdbVar = null;
        if (AlertMessage.AlertDescription.CLOSE_NOTIFY.equals(alertMessage.b())) {
            if (vcmVar.q()) {
                a(vdzVar, vcmVar);
            } else {
                vdb vdbVar2 = new vdb("Received 'close notify'", alertMessage);
                if (l2 != null) {
                    l2.setFailureCause(vdbVar2);
                }
                vdbVar = vdbVar2;
            }
            if (!vcmVar.v()) {
                if (vcmVar.o() != null) {
                    c(vcmVar, vcnVar, new AlertMessage(AlertMessage.AlertLevel.WARNING, AlertMessage.AlertDescription.CLOSE_NOTIFY));
                }
                if (vcmVar.q()) {
                    vcmVar.c(vdzVar);
                } else {
                    this.r.remove(vcmVar, false);
                }
            }
        } else if (AlertMessage.AlertLevel.FATAL.equals(alertMessage.e())) {
            vdbVar = new vdb("Received 'fatal alert/" + alertMessage.b() + "'", alertMessage);
            if (l2 != null) {
                l2.setFailureCause(vdbVar);
            }
            this.r.remove(vcmVar, true);
        }
        d(vcmVar, alertMessage);
        if (vdbVar == null || l2 == null) {
            return;
        }
        l2.handshakeFailed(vdbVar);
    }

    private boolean a(vdz vdzVar, vcm vcmVar) {
        InetSocketAddress i;
        boolean b2 = vcmVar.b(vdzVar);
        if (b2 || !this.ay) {
            vcmVar.a(vdzVar.g());
            i = vdzVar.i();
        } else {
            i = null;
        }
        this.r.update(vcmVar, i);
        Handshaker l2 = vcmVar.l();
        if (l2 != null) {
            l2.handshakeCompleted();
        }
        ConnectionListener connectionListener = this.q;
        if (connectionListener != null && connectionListener.onConnectionUpdatesSequenceNumbers(vcmVar, false)) {
            b(vcmVar);
        }
        return b2;
    }

    private void e(vdz vdzVar, vcm vcmVar) {
        Handshaker l2 = vcmVar.l();
        if (l2 != null) {
            try {
                l2.processMessage(vdzVar);
                return;
            } catch (vdb e) {
                d(vdzVar, vcmVar, e);
                return;
            }
        }
        f.debug("Received CHANGE_CIPHER_SPEC record from peer [{}] with no handshake going on", vcb.b((SocketAddress) vdzVar.i()));
    }

    private void c(vdz vdzVar, vcm vcmVar, vcn vcnVar) {
        m.trace("Received {} record from peer [{}]", vdzVar.j(), vcb.b((SocketAddress) vdzVar.i()));
        if (vdzVar.k()) {
            throw new IllegalArgumentException("new CLIENT_HELLO must be processed by processClientHello!");
        }
        try {
            Handshaker l2 = vcmVar.l();
            int i = AnonymousClass9.d[((HandshakeMessage) vdzVar.c()).getMessageType().ordinal()];
            if (i == 1) {
                f.debug("Reject re-negotiation from peer [{}]", vcb.b((SocketAddress) vdzVar.i()));
                c(vcmVar, vcnVar, new AlertMessage(AlertMessage.AlertLevel.WARNING, AlertMessage.AlertDescription.NO_RENEGOTIATION));
            } else if (i != 2) {
                if (l2 != null) {
                    l2.processMessage(vdzVar);
                } else {
                    f.debug("Discarding HANDSHAKE message [epoch={}] from peer [{}], no ongoing handshake!", Integer.valueOf(vdzVar.e()), vcb.b((SocketAddress) vdzVar.i()));
                }
            } else if (l2 != null) {
                f.debug("Ignore HELLO_REQUEST received from peer [{}] during ongoing handshake", vcb.b((SocketAddress) vcmVar.o()));
            } else {
                f.debug("Reject HELLO_REQUEST received from peer [{}]", vcb.b((SocketAddress) vcmVar.o()));
                c(vcmVar, vcnVar, new AlertMessage(AlertMessage.AlertLevel.WARNING, AlertMessage.AlertDescription.NO_RENEGOTIATION));
            }
        } catch (vdb e) {
            d(vdzVar, vcmVar, e);
        }
    }

    /* renamed from: org.eclipse.californium.scandium.DTLSConnector$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] d;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HandshakeType.values().length];
            d = iArr;
            try {
                iArr[HandshakeType.CLIENT_HELLO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HandshakeType.HELLO_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ContentType.values().length];
            e = iArr2;
            try {
                iArr2[ContentType.APPLICATION_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[ContentType.ALERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[ContentType.CHANGE_CIPHER_SPEC.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                e[ContentType.HANDSHAKE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final vdz vdzVar) {
        final vcm c;
        InetSocketAddress i = vdzVar.i();
        Logger logger = m;
        if (logger.isTraceEnabled()) {
            logger.trace("Processing new CLIENT_HELLO from peer [{}]:{}{}", vcb.b((SocketAddress) i), vcb.a(), vdzVar);
        }
        try {
            try {
                vdzVar.a(DTLSConnectionState.NULL);
                DTLSMessage c2 = vdzVar.c();
                if (c2 instanceof vdc) {
                    logger.debug("Received unsupported fragmented CLIENT_HELLO from peer {}.", vcb.b((SocketAddress) i));
                    e(vdzVar, new vcu("Fragmented CLIENT_HELLO is not supported!"));
                    return;
                }
                vck vckVar = (vck) c2;
                byte[] d = this.x.d(i, vckVar);
                if (d(i, vckVar, d)) {
                    ExecutorService j = j();
                    ResumptionSupportingConnectionStore resumptionSupportingConnectionStore = this.r;
                    if (resumptionSupportingConnectionStore instanceof ReadWriteLockConnectionStore) {
                        ReadWriteLockConnectionStore readWriteLockConnectionStore = (ReadWriteLockConnectionStore) resumptionSupportingConnectionStore;
                        readWriteLockConnectionStore.writeLock().lock();
                        try {
                            c = c(i, vckVar, j);
                            readWriteLockConnectionStore.writeLock().unlock();
                        } catch (Throwable th) {
                            readWriteLockConnectionStore.writeLock().unlock();
                            throw th;
                        }
                    } else {
                        synchronized (resumptionSupportingConnectionStore) {
                            c = c(i, vckVar, j);
                        }
                    }
                    if (c != null) {
                        try {
                            c.g().execute(new Runnable() { // from class: org.eclipse.californium.scandium.DTLSConnector.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (DTLSConnector.this.aw.get()) {
                                        DTLSConnector.this.b(vdzVar, c);
                                    }
                                }
                            });
                            return;
                        } catch (RejectedExecutionException e) {
                            m.debug("Execution rejected while processing record [type: {}, peer: {}]", vdzVar.j(), vcb.b((SocketAddress) i), e);
                            return;
                        } catch (RuntimeException e2) {
                            m.warn("Unexpected error occurred while processing record [type: {}, peer: {}]", vdzVar.j(), vcb.b((SocketAddress) i), e2);
                            d(c);
                            return;
                        }
                    }
                }
                e(vckVar, vdzVar, d);
            } catch (RuntimeException e3) {
                m.warn("Processing new CLIENT_HELLO from peer [{}] failed!", vcb.b((SocketAddress) vdzVar.i()), e3);
            }
        } catch (GeneralSecurityException e4) {
            f.debug("Processing new CLIENT_HELLO from peer [{}] failed!", vcb.b((SocketAddress) vdzVar.i()), e4);
        } catch (vdb e5) {
            m.debug("Processing new CLIENT_HELLO from peer [{}] failed!", vcb.b((SocketAddress) vdzVar.i()), e5);
        }
    }

    private vcm c(InetSocketAddress inetSocketAddress, vck vckVar, ExecutorService executorService) {
        vcn h2;
        vcm vcmVar = this.r.get(inetSocketAddress);
        if (vcmVar != null && !vcmVar.c(vckVar)) {
            if (this.bf && !vckVar.i() && vckVar.hasSessionId() && !vbj.d(vcmVar.f(), vckVar.getSessionId())) {
                return null;
            }
            final Handshaker l2 = vcmVar.l();
            if (l2 != null && ((h2 = vcmVar.h()) == null || h2 != l2.getDtlsContext())) {
                final vcu vcuVar = new vcu("Received new CLIENT_HELLO from " + vcb.a(inetSocketAddress));
                try {
                    vcmVar.g().execute(new Runnable() { // from class: org.eclipse.californium.scandium.DTLSConnector.7
                        @Override // java.lang.Runnable
                        public void run() {
                            if (DTLSConnector.this.aw.get()) {
                                l2.handshakeFailed(vcuVar);
                            }
                        }
                    });
                } catch (RejectedExecutionException unused) {
                    m.trace("Execution rejected, connection already shutdown [peer: {}]", vcb.b((SocketAddress) inetSocketAddress));
                }
            }
            vcmVar = null;
        }
        if (vcmVar == null) {
            int max = Math.max(this.ah, 50);
            if (this.ax.get() > max) {
                m.error("Too many recent handshakes! {} max. allowed.", Integer.valueOf(max));
                return null;
            }
            vcmVar = new vcm(inetSocketAddress);
            vcmVar.e(executorService, this.q);
            vcmVar.a(vckVar);
            if (!this.r.put(vcmVar)) {
                return null;
            }
        }
        return vcmVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(vdz vdzVar, vcm vcmVar) {
        veb vebVar;
        if (vcmVar == null) {
            throw new NullPointerException("connection must not be null!");
        }
        if (!vcmVar.b(vdzVar.i())) {
            f.debug("Drop received CLIENT_HELLO, changed address {} => {}!", vcb.b((SocketAddress) vdzVar.i()), vcb.b((SocketAddress) vcmVar.o()));
            return;
        }
        if (vcmVar.q() || vcmVar.s()) {
            f.debug("Discarding received duplicate CLIENT_HELLO message [epoch={}] from peer [{}]!", Integer.valueOf(vdzVar.e()), vcb.b((SocketAddress) vdzVar.i()));
            return;
        }
        Logger logger = m;
        if (logger.isTraceEnabled()) {
            logger.trace("Processing CLIENT_HELLO from peer [{}]:{}{}", vcb.b((SocketAddress) vdzVar.i()), vcb.a(), vdzVar);
        }
        try {
            vck vckVar = (vck) vdzVar.c();
            if (this.au != null && vckVar.hasSessionId()) {
                vebVar = new vec(vdzVar.h(), vckVar.getMessageSeq(), this, this.i, vcmVar, this.f15889a);
                if (!vckVar.i()) {
                    this.ak.incrementAndGet();
                    vebVar.addSessionListener(this.an);
                }
            } else {
                vebVar = new veb(vdzVar.h(), vckVar.getMessageSeq(), this, this.i, vcmVar, this.f15889a);
            }
            a(vebVar);
            vebVar.processMessage(vdzVar);
        } catch (vdb e) {
            d(vdzVar, vcmVar, e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x001b, code lost:
    
        if (java.security.MessageDigest.isEqual(r7, r0) != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(java.net.InetSocketAddress r6, defpackage.vck r7, byte[] r8) {
        /*
            r5 = this;
            byte[] r0 = r7.b()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 <= 0) goto L48
            boolean r1 = java.security.MessageDigest.isEqual(r8, r0)
            if (r1 != 0) goto L26
            vby r4 = r5.x     // Catch: java.security.GeneralSecurityException -> L1e
            byte[] r7 = r4.b(r6, r7)     // Catch: java.security.GeneralSecurityException -> L1e
            if (r7 == 0) goto L26
            boolean r7 = java.security.MessageDigest.isEqual(r7, r0)     // Catch: java.security.GeneralSecurityException -> L1e
            if (r7 == 0) goto L26
            goto L27
        L1e:
            r7 = move-exception
            org.slf4j.Logger r3 = org.eclipse.californium.scandium.DTLSConnector.m
            java.lang.String r4 = "failed to generate past cookie."
            r3.debug(r4, r7)
        L26:
            r3 = r1
        L27:
            if (r3 != 0) goto L47
            org.slf4j.Logger r7 = org.eclipse.californium.scandium.DTLSConnector.m
            boolean r1 = r7.isDebugEnabled()
            if (r1 == 0) goto L47
            r1 = 6
            java.lang.String r0 = defpackage.vcb.b(r0, r2, r1)
            java.lang.String r8 = defpackage.vcb.b(r8, r2, r1)
            java.lang.Object r6 = defpackage.vcb.b(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r0, r8, r6}
            java.lang.String r8 = "provided cookie must {} match {}. Send verify request to {}"
            r7.debug(r8, r6)
        L47:
            return r3
        L48:
            boolean r6 = r5.bf
            if (r6 != 0) goto L4d
            return r3
        L4d:
            boolean r6 = r5.be
            if (r6 != 0) goto L62
            vcd r6 = r5.f15889a
            java.util.List r6 = r6.ab()
            java.util.List r6 = r7.a(r6)
            boolean r6 = org.eclipse.californium.scandium.dtls.cipher.CipherSuite.containsPskBasedCipherSuite(r6)
            if (r6 == 0) goto L62
            return r3
        L62:
            org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier r6 = r5.au
            if (r6 == 0) goto La5
            boolean r6 = r7.hasSessionId()
            if (r6 == 0) goto La5
            int r6 = r5.az
            if (r6 <= 0) goto La5
            java.util.concurrent.atomic.AtomicInteger r6 = r5.ak
            int r6 = r6.get()
            org.slf4j.Logger r8 = org.eclipse.californium.scandium.DTLSConnector.m
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            int r1 = r5.az
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "pending fast resumptions [{}], threshold [{}]"
            r8.trace(r3, r0, r1)
            int r8 = r5.az
            if (r6 >= r8) goto La5
            org.eclipse.californium.scandium.dtls.resumption.ResumptionVerifier r6 = r5.au
            boolean r8 = r6 instanceof org.eclipse.californium.scandium.dtls.resumption.ExtendedResumptionVerifier
            if (r8 == 0) goto L9c
            org.eclipse.californium.scandium.dtls.resumption.ExtendedResumptionVerifier r6 = (org.eclipse.californium.scandium.dtls.resumption.ExtendedResumptionVerifier) r6
            boolean r8 = r5.j
            org.eclipse.californium.scandium.dtls.ExtendedMasterSecretMode r0 = r5.e
            boolean r6 = r6.skipRequestHelloVerify(r7, r8, r0)
            return r6
        L9c:
            vej r7 = r7.getSessionId()
            boolean r6 = r6.skipRequestHelloVerify(r7)
            return r6
        La5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.DTLSConnector.d(java.net.InetSocketAddress, vck, byte[]):boolean");
    }

    private void e(vck vckVar, vdz vdzVar, byte[] bArr) throws GeneralSecurityException {
        if (bArr == null) {
            throw new NullPointerException("Cookie must not be null!");
        }
        m.trace("Verifying client IP address [{}] using HELLO_VERIFY_REQUEST", vcb.b((SocketAddress) vdzVar.i()));
        vdu vduVar = this.as;
        if (vduVar == null) {
            vduVar = vckVar.getProtocolVersion();
            if (vduVar.compareTo(vdu.c) < 0) {
                vduVar = vdu.c;
            } else if (vduVar.compareTo(vdu.f17688a) > 0) {
                vduVar = vdu.f17688a;
            }
        }
        vdu vduVar2 = vduVar;
        vdp vdpVar = new vdp(vduVar2, bArr);
        vdpVar.setMessageSeq(vckVar.getMessageSeq());
        vdz vdzVar2 = new vdz(ContentType.HANDSHAKE, vduVar2, vdzVar.h(), vdpVar);
        vdzVar2.c(vdzVar.i(), (InetSocketAddress) null);
        try {
            e(vdzVar2);
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(vdz vdzVar, vcm vcmVar, vdb vdbVar) {
        AlertMessage e = vdbVar.e();
        if (!AlertMessage.AlertLevel.FATAL.equals(e.e())) {
            if (vdzVar != null) {
                e(vdzVar, vdbVar);
            }
            d(vcmVar, e);
            return;
        }
        if (AlertMessage.AlertDescription.UNKNOWN_PSK_IDENTITY.equals(e.b())) {
            if (vdzVar != null) {
                e(vdzVar, vdbVar);
            }
            d(vcmVar, e);
            return;
        }
        Handshaker l2 = vcmVar.l();
        if (l2 != null) {
            Logger logger = m;
            if (logger.isTraceEnabled()) {
                logger.trace("Aborting handshake with peer [{}]:", vcb.b((SocketAddress) vcmVar.o()), vdbVar);
            } else if (logger.isDebugEnabled()) {
                logger.debug("Aborting handshake with peer [{}]: {}", vcb.b((SocketAddress) vcmVar.o()), vdbVar.getMessage());
            }
            l2.setFailureCause(vdbVar);
            vcn dtlsContext = l2.getDtlsContext();
            vcn h2 = vcmVar.h();
            if (h2 == dtlsContext) {
                if (e.b() == AlertMessage.AlertDescription.CLOSE_NOTIFY) {
                    logger.debug("Handshake with [{}] closed after session was established!", vcb.b((SocketAddress) l2.getPeerAddress()));
                } else {
                    logger.warn("Handshake with [{}] failed after session was established! {}", vcb.b((SocketAddress) l2.getPeerAddress()), e);
                }
            } else if (h2 != null) {
                logger.warn("Handshake with [{}] failed, but has an established session!", vcb.b((SocketAddress) l2.getPeerAddress()));
            }
            c(vcmVar, dtlsContext, e);
            l2.handshakeFailed(vdbVar);
            d(vcmVar, e);
        }
    }

    private void d(vcm vcmVar, AlertMessage alertMessage) {
        AlertHandler alertHandler;
        if (!vcmVar.d(alertMessage) || (alertHandler = this.s) == null) {
            return;
        }
        alertHandler.onAlert(vcmVar.o(), alertMessage);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0064 A[Catch: GeneralSecurityException -> 0x0068, IOException -> 0x0078, TRY_LEAVE, TryCatch #2 {IOException -> 0x0078, GeneralSecurityException -> 0x0068, blocks: (B:9:0x000d, B:13:0x0029, B:16:0x0030, B:17:0x0052, B:19:0x0064, B:23:0x0042), top: B:8:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void c(defpackage.vcm r12, defpackage.vcn r13, org.eclipse.californium.scandium.dtls.AlertMessage r14) {
        /*
            r11 = this;
            if (r12 == 0) goto L89
            if (r13 == 0) goto L81
            if (r14 == 0) goto L79
            boolean r2 = r12.v()
            if (r2 == 0) goto Ld
            return
        Ld:
            org.slf4j.Logger r2 = org.eclipse.californium.scandium.DTLSConnector.m     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            java.lang.String r3 = "send ALERT {} for peer {}."
            java.net.InetSocketAddress r4 = r12.o()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            java.lang.Object r4 = defpackage.vcb.b(r4)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r2.trace(r3, r14, r4)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            int r2 = r13.h()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r9 = 1
            if (r2 <= 0) goto L25
            r7 = r9
            goto L27
        L25:
            r2 = 0
            r7 = r2
        L27:
            if (r7 != 0) goto L42
            vdu r2 = r14.a()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            if (r2 != 0) goto L30
            goto L42
        L30:
            vdz r8 = new vdz     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            org.eclipse.californium.scandium.dtls.ContentType r3 = org.eclipse.californium.scandium.dtls.ContentType.ALERT     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            vdu r4 = r14.a()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            long r5 = r13.c()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r2 = r8
            r7 = r14
            r2.<init>(r3, r4, r5, r7)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            goto L52
        L42:
            vdz r10 = new vdz     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            org.eclipse.californium.scandium.dtls.ContentType r3 = org.eclipse.californium.scandium.dtls.ContentType.ALERT     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            int r4 = r13.h()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r8 = 0
            r2 = r10
            r5 = r14
            r6 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r8 = r10
        L52:
            java.net.InetSocketAddress r0 = r12.o()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            java.net.InetSocketAddress r2 = r12.m()     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r8.c(r0, r2)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            r11.e(r8)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            org.eclipse.californium.scandium.ConnectionListener r0 = r11.q     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            if (r0 == 0) goto L78
            r0.onConnectionUpdatesSequenceNumbers(r12, r9)     // Catch: java.security.GeneralSecurityException -> L68 java.io.IOException -> L78
            goto L78
        L68:
            r0 = move-exception
            org.slf4j.Logger r2 = org.eclipse.californium.scandium.DTLSConnector.f
            java.net.InetSocketAddress r1 = r12.o()
            java.lang.Object r1 = defpackage.vcb.b(r1)
            java.lang.String r3 = "Cannot create ALERT message for peer [{}]"
            r2.warn(r3, r1, r0)
        L78:
            return
        L79:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Alert must not be null"
            r0.<init>(r1)
            throw r0
        L81:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "DTLS Context must not be null"
            r0.<init>(r1)
            throw r0
        L89:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Connection must not be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.DTLSConnector.c(vcm, vcn, org.eclipse.californium.scandium.dtls.AlertMessage):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x012a  */
    @Override // org.eclipse.californium.elements.Connector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void send(final defpackage.vaf r12) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.scandium.DTLSConnector.send(vaf):void");
    }

    protected boolean b(Executor executor, InetSocketAddress inetSocketAddress, LimitedRunnable limitedRunnable) {
        try {
            limitedRunnable.execute(executor);
            return true;
        } catch (RejectedExecutionException e) {
            if (limitedRunnable.isOverflown()) {
                o.c("Outbound jobs overflow! Dropping outbound message to peer [{}]", vcb.b((SocketAddress) inetSocketAddress));
            } else {
                m.debug("Execution rejected while processing record to peer [{}]", vcb.b((SocketAddress) inetSocketAddress), e);
            }
            DtlsHealth dtlsHealth = this.d;
            if (dtlsHealth == null) {
                return false;
            }
            dtlsHealth.sendingRecord(true);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, vaf vafVar, vcm vcmVar) throws vdb {
        if (vcmVar.o() == null) {
            f.info("Drop outgoing record with {} bytes, connection lost address {}! (shift {}ms)", Integer.valueOf(vafVar.f()), vcb.b((SocketAddress) vafVar.a()), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(ClockUtil.d() - j)));
            vafVar.b(new vbg("connection not longer assigned to address!"));
            DtlsHealth dtlsHealth = this.d;
            if (dtlsHealth != null) {
                dtlsHealth.sendingRecord(true);
                return;
            }
            return;
        }
        m.trace("Sending application layer message to [{}]", vafVar.c());
        Handshaker l2 = vcmVar.l();
        if (l2 != null && !l2.hasContextEstablished()) {
            if (l2.isExpired()) {
                l2.handshakeAborted(new Exception("handshake already expired!"));
            } else if (l2.isProbing()) {
                if (a(vafVar, (EndpointContext) null)) {
                    vafVar.h();
                    l2.addApplicationDataForDeferredProcessing(vafVar);
                    return;
                }
                return;
            }
        }
        if (vcmVar.r()) {
            b(vafVar, vcmVar);
        } else {
            e(vafVar, vcmVar);
        }
    }

    private void e(vaf vafVar, vcm vcmVar) throws vdb {
        if (a(vafVar, (EndpointContext) null)) {
            Handshaker l2 = vcmVar.l();
            if (l2 == null) {
                if (this.u == DtlsConfig.DtlsRole.SERVER_ONLY) {
                    f.trace("DTLSConnector drops {} outgoing bytes to {}, server only, connection missing!", Integer.valueOf(vafVar.f()), vcb.b((SocketAddress) vafVar.a()));
                    vafVar.b(new vbg("server only, connection missing!"));
                    DtlsHealth dtlsHealth = this.d;
                    if (dtlsHealth != null) {
                        dtlsHealth.sendingRecord(true);
                        return;
                    }
                    return;
                }
                if (a(vafVar).contentEquals("none")) {
                    f.trace("DTLSConnector drops {} outgoing bytes to {}, connection missing!", Integer.valueOf(vafVar.f()), vcb.b((SocketAddress) vafVar.a()));
                    vafVar.b(new vbg("connection missing!"));
                    DtlsHealth dtlsHealth2 = this.d;
                    if (dtlsHealth2 != null) {
                        dtlsHealth2.sendingRecord(true);
                        return;
                    }
                    return;
                }
                vci vciVar = new vci(vafVar.c().getVirtualHost(), this, this.i, vcmVar, this.f15889a, false);
                a(vciVar);
                vafVar.h();
                vciVar.addApplicationDataForDeferredProcessing(vafVar);
                vciVar.d();
                return;
            }
            vafVar.h();
            l2.addApplicationDataForDeferredProcessing(vafVar);
        }
    }

    private void b(vaf vafVar, vcm vcmVar) throws vdb {
        vct vctVar;
        vci vdwVar;
        vcn h2 = vcmVar.h();
        String a2 = a(vafVar);
        boolean l2 = h2.l();
        if ("none".equals(a2)) {
            if (l2 || vcmVar.v()) {
                f.trace("DTLSConnector drops {} outgoing bytes to {}, resumption required!", Integer.valueOf(vafVar.f()), vcb.b((SocketAddress) vafVar.a()));
                vafVar.b(new vbg("resumption required!"));
                DtlsHealth dtlsHealth = this.d;
                if (dtlsHealth != null) {
                    dtlsHealth.sendingRecord(true);
                    return;
                }
                return;
            }
        } else {
            boolean equals = CanvasConfig.FULL_CONFIG.equals(a2);
            boolean equals2 = "probe".equals(a2);
            if (equals2 || equals || "force".equals(a2) || l2 || vcmVar.e(d(vafVar))) {
                if (this.u == DtlsConfig.DtlsRole.SERVER_ONLY) {
                    f.trace("DTLSConnector drops {} outgoing bytes to {}, server only, resumption requested failed!", Integer.valueOf(vafVar.f()), vcb.b((SocketAddress) vafVar.a()));
                    vafVar.b(new vbg("server only, resumption requested failed!"));
                    DtlsHealth dtlsHealth2 = this.d;
                    if (dtlsHealth2 != null) {
                        dtlsHealth2.sendingRecord(true);
                        return;
                    }
                    return;
                }
                vafVar.h();
                String virtualHost = vafVar.c().getVirtualHost();
                Handshaker l3 = vcmVar.l();
                if (equals) {
                    vctVar = null;
                } else {
                    vctVar = h2.a();
                    boolean d = vctVar.n().d();
                    if (!d && ((ExtendedMasterSecretMode) this.f15889a.d(DtlsConfig.o)).is(ExtendedMasterSecretMode.ENABLED) && (!vctVar.s())) {
                        m.debug("Extended Master Secrets not supported by server {}, fallback to full handshake!", vcb.b((SocketAddress) vafVar.a()));
                    }
                    equals = d;
                    if (!equals && (!vfe.e(virtualHost, vctVar.h()))) {
                        m.debug("Server Name Indication changed for server {}, fallback to full handshake!", vcb.b((SocketAddress) vafVar.a()));
                    }
                }
                if (equals) {
                    vdwVar = new vci(virtualHost, this, this.i, vcmVar, this.f15889a, equals2);
                } else {
                    vdwVar = new vdw(vctVar, this, this.i, vcmVar, this.f15889a, equals2);
                }
                if (equals2) {
                    vcmVar.b(false);
                } else {
                    this.r.removeFromEstablishedSessions(vcmVar);
                    vcmVar.x();
                }
                a(vdwVar);
                if (l3 != null) {
                    vdwVar.takeDeferredApplicationData(l3);
                    l3.handshakeAborted(new Exception("handshake replaced!"));
                }
                vdwVar.addApplicationDataForDeferredProcessing(vafVar);
                vdwVar.d();
                return;
            }
        }
        d(vafVar, vcmVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(vaf vafVar, vcm vcmVar) {
        try {
            vcn h2 = vcmVar.h();
            m.trace("send {}-{} using {}", vcmVar.c(), vcb.b((SocketAddress) vcmVar.o()), h2.a().n());
            uzz c = vcmVar.c(new vab.d());
            if (a(vafVar, c)) {
                vafVar.b(c);
                vdz vdzVar = new vdz(ContentType.APPLICATION_DATA, h2.h(), new vcf(vafVar.e()), h2, true, 0);
                vdzVar.c(vcmVar.o(), vcmVar.m());
                e(vdzVar);
                vafVar.j();
                this.r.update(vcmVar, null);
                ConnectionListener connectionListener = this.q;
                if (connectionListener == null || !connectionListener.onConnectionUpdatesSequenceNumbers(vcmVar, true)) {
                    return;
                }
                b(vcmVar);
            }
        } catch (IOException e) {
            vafVar.b(e);
        } catch (GeneralSecurityException e2) {
            f.warn("Cannot send APPLICATION record to peer [{}]", vcb.b((SocketAddress) vafVar.a()), e2);
            vafVar.b(e2);
        }
    }

    private boolean a(vaf vafVar, EndpointContext endpointContext) {
        EndpointContextMatcher i = i();
        if (i == null || i.isToBeSent(vafVar.c(), endpointContext)) {
            return true;
        }
        Logger logger = f;
        if (logger.isInfoEnabled()) {
            logger.info("DTLSConnector ({}) drops {} bytes outgoing, {} != {}", this, Integer.valueOf(vafVar.f()), i.toRelevantState(vafVar.c()), i.toRelevantState(endpointContext));
        }
        vafVar.b(new vbd("DTLS sending"));
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth == null) {
            return false;
        }
        dtlsHealth.sendingRecord(true);
        return false;
    }

    @Override // org.eclipse.californium.scandium.dtls.RecordLayer
    public void dropReceivedRecord(vdz vdzVar) {
        f.debug("Discarding {} record [epoch {}, rseqn {}] dropped by handshaker for peer [{}]", vdzVar.j(), Integer.valueOf(vdzVar.e()), Long.valueOf(vdzVar.h()), vcb.b((SocketAddress) vdzVar.i()));
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth != null) {
            dtlsHealth.receivingRecord(true);
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.RecordLayer
    public int getMaxDatagramSize(boolean z) {
        int i = z ? 128 : 64;
        Integer num = this.aj;
        int intValue = num != null ? num.intValue() : z ? this.ab : this.z;
        int i2 = intValue - i;
        if (i2 >= 64) {
            return i2;
        }
        Object[] objArr = new Object[3];
        objArr[0] = z ? "IPV6" : "IPv4";
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Integer.valueOf(intValue);
        throw new IllegalStateException(String.format("%s, datagram size %d, mtu %d", objArr));
    }

    @Override // org.eclipse.californium.scandium.dtls.RecordLayer
    public void sendFlight(List<DatagramPacket> list) throws IOException {
        for (DatagramPacket datagramPacket : list) {
            DtlsHealth dtlsHealth = this.d;
            if (dtlsHealth != null) {
                dtlsHealth.sendingRecord(false);
            }
            b(datagramPacket);
        }
    }

    @Override // org.eclipse.californium.scandium.dtls.RecordLayer
    public void processHandshakeException(vcm vcmVar, vdb vdbVar) {
        d((vdz) null, vcmVar, vdbVar);
    }

    protected void e(vdz vdzVar) throws IOException {
        if (this.d != null && vdzVar.j() != ContentType.APPLICATION_DATA) {
            this.d.sendingRecord(false);
        }
        byte[] n2 = vdzVar.n();
        b(new DatagramPacket(n2, n2.length, vdzVar.i()));
    }

    protected void b(DatagramPacket datagramPacket) throws IOException {
        DatagramSocket h2 = h();
        if (h2 != null && !h2.isClosed()) {
            if (datagramPacket.getPort() == 0) {
                String c = vcb.c(datagramPacket.getSocketAddress());
                f.trace("Discarding record with {} bytes to [{}] without destination-port", Integer.valueOf(datagramPacket.getLength()), c);
                DtlsHealth dtlsHealth = this.d;
                if (dtlsHealth != null) {
                    dtlsHealth.sendingRecord(true);
                }
                throw new IOException("DTLS Record to " + c + " dropped, destination port 0!");
            }
            try {
                h2.send(datagramPacket);
                return;
            } catch (PortUnreachableException unused) {
                if (!h2.isClosed()) {
                    m.warn("Could not send record, destination {} unreachable!", vcb.b(datagramPacket.getSocketAddress()));
                }
            } catch (IOException e) {
                if (!h2.isClosed()) {
                    m.warn("Could not send record", (Throwable) e);
                    throw e;
                }
            }
        }
        InetSocketAddress inetSocketAddress = this.af;
        if (inetSocketAddress == null) {
            inetSocketAddress = this.f15889a.e();
        }
        f.debug("Socket [{}] is closed, discarding packet ...", inetSocketAddress);
        throw new IOException("Socket closed.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final vdg vdgVar) {
        final vcm vcmVar = this.r.get(vdgVar.a());
        if (vcmVar != null) {
            if (vcmVar.s()) {
                try {
                    b(vcmVar.g(), vcmVar, new LimitedRunnable(this.al) { // from class: org.eclipse.californium.scandium.DTLSConnector.6
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                try {
                                    if (!DTLSConnector.this.aw.get() || !vcmVar.u()) {
                                        DTLSConnector.m.debug("Execution stopped while processing handshake result [{}]", vcmVar);
                                    } else {
                                        Handshaker l2 = vcmVar.l();
                                        if (l2 == null) {
                                            DTLSConnector.m.debug("No ongoing handshake for result [{}]", vcmVar);
                                        } else {
                                            l2.processAsyncHandshakeResult(vdgVar);
                                        }
                                    }
                                } catch (IllegalStateException e) {
                                    DTLSConnector.m.warn("Exception while processing handshake result [{}]", vcmVar, e);
                                } catch (vdb e2) {
                                    DTLSConnector.this.d((vdz) null, vcmVar, e2);
                                }
                            } finally {
                                onDequeueing();
                            }
                        }
                    });
                    return;
                } catch (RuntimeException e) {
                    m.warn("Unexpected error occurred while processing handshake result [{}]", vcmVar, e);
                    return;
                }
            }
            m.debug("No ongoing handshake for handshake result [{}]", vcmVar);
            return;
        }
        m.debug("No connection  for handshake result [{}]", vcmVar);
    }

    protected boolean b(Executor executor, vcm vcmVar, LimitedRunnable limitedRunnable) {
        try {
            limitedRunnable.execute(executor);
            return true;
        } catch (RejectedExecutionException e) {
            if (limitedRunnable.isOverflown()) {
                l.c("Handshake result jobs overflow! Dropping handshake result [{}]", vcmVar);
                return false;
            }
            m.debug("Execution rejected while processing handshake result [{}]", vcmVar, e);
            return false;
        }
    }

    private Long d(vaf vafVar) {
        Long l2 = this.p;
        Number number = (Number) vafVar.c().get(uzz.i);
        if (number == null) {
            return l2;
        }
        if (number.longValue() >= 0) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    @Override // org.eclipse.californium.elements.Connector
    public final InetSocketAddress getAddress() {
        int localPort;
        DatagramSocket h2 = h();
        if (h2 != null && h2.isBound() && (localPort = h2.getLocalPort()) > 0) {
            return new InetSocketAddress(h2.getLocalAddress(), localPort);
        }
        return this.f15889a.e();
    }

    @Override // org.eclipse.californium.elements.Connector
    public final boolean isRunning() {
        return this.aw.get();
    }

    protected abstract class Worker extends Thread {
        protected abstract void doWork() throws Exception;

        protected Worker(String str) {
            super(vbv.c, str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                DTLSConnector.m.info("Starting worker thread [{}]", getName());
                while (DTLSConnector.this.aw.get()) {
                    try {
                        try {
                            doWork();
                        } catch (InterruptedIOException unused) {
                            if (DTLSConnector.this.aw.get()) {
                                DTLSConnector.m.info("Worker thread [{}] IO has been interrupted", getName());
                            } else {
                                DTLSConnector.m.debug("Worker thread [{}] IO has been interrupted", getName());
                            }
                        }
                    } catch (InterruptedException unused2) {
                        if (DTLSConnector.this.aw.get()) {
                            DTLSConnector.m.info("Worker thread [{}] has been interrupted", getName());
                        } else {
                            DTLSConnector.m.debug("Worker thread [{}] has been interrupted", getName());
                        }
                    } catch (Exception e) {
                        if (DTLSConnector.this.aw.get()) {
                            DTLSConnector.m.debug("Exception thrown by worker thread [{}]", getName(), e);
                        } else {
                            DTLSConnector.m.trace("Exception thrown by worker thread [{}]", getName(), e);
                        }
                    }
                }
            } finally {
                if (DTLSConnector.this.aw.get()) {
                    DTLSConnector.m.info("Worker thread [{}] has terminated", getName());
                } else {
                    DTLSConnector.m.debug("Worker thread [{}] has terminated", getName());
                }
            }
        }
    }

    @Override // org.eclipse.californium.elements.Connector
    public void setRawDataReceiver(RawDataChannel rawDataChannel) {
        if (isRunning()) {
            throw new IllegalStateException("message handler cannot be set on running connector");
        }
        this.am = rawDataChannel;
    }

    @Override // org.eclipse.californium.elements.Connector
    public void setEndpointContextMatcher(EndpointContextMatcher endpointContextMatcher) {
        this.aa = endpointContextMatcher;
    }

    private EndpointContextMatcher i() {
        return this.aa;
    }

    private String a(vaf vafVar) {
        String string = vafVar.c().getString(uzz.f);
        return string == null ? this.y : string;
    }

    private void e(vdz vdzVar, Throwable th) {
        DtlsHealth dtlsHealth = this.d;
        if (dtlsHealth != null) {
            dtlsHealth.receivingRecord(true);
        }
        byte[] a2 = vdzVar.a();
        Logger logger = f;
        if (logger.isTraceEnabled()) {
            logger.trace("Discarding received {} record (epoch {}, payload: {}) from peer [{}]: ", vdzVar.j(), Integer.valueOf(vdzVar.e()), vcb.b(a2, (char) 0, 64), vcb.b((SocketAddress) vdzVar.i()), th);
        } else if (logger.isDebugEnabled()) {
            logger.debug("Discarding received {} record (epoch {}, payload: {}) from peer [{}]: {}", vdzVar.j(), Integer.valueOf(vdzVar.e()), vcb.b(a2, (char) 0, 16), vcb.b((SocketAddress) vdzVar.i()), th.getMessage());
        }
    }

    public String toString() {
        return getProtocol() + Constants.LINK + vcb.c(getAddress());
    }

    @Override // org.eclipse.californium.elements.Connector
    public String getProtocol() {
        return "DTLS";
    }
}
