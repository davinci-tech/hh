package defpackage;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public class jun {
    protected volatile boolean b;
    private Selector h;
    private final SimpleArrayMap<a, DatagramChannel> e = new SimpleArrayMap<>(8);

    /* renamed from: a, reason: collision with root package name */
    private final SimpleArrayMap<DatagramChannel, a> f14097a = new SimpleArrayMap<>(8);
    private final Set<DatagramChannel> c = new HashSet(16);
    private final Object g = new Object();
    private final ByteBuffer d = ByteBuffer.allocateDirect(65535);
    private long f = -1;

    public long d() {
        return this.f;
    }

    public void d(long j) {
        if (this.f > 0) {
            this.f = j;
        }
    }

    public void c(int i) throws IOException {
        LogUtil.d("UdpRelayingManager", "enter setup");
        d(i);
        this.h = SelectorProvider.provider().openSelector();
        this.b = true;
    }

    public void b() {
        this.b = false;
        Selector selector = this.h;
        if (selector != null) {
            selector.wakeup();
        }
    }

    public void a() {
        synchronized (this.g) {
            this.e.clear();
            this.f14097a.clear();
        }
        try {
            this.h.close();
        } catch (IOException unused) {
            Log.e("UdpRelayingManager", "UdpRelayingThread exception");
        }
    }

    public DatagramChannel d(String str, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) throws IOException {
        DatagramChannel datagramChannel;
        a aVar = new a(str, inetSocketAddress, inetSocketAddress2);
        synchronized (this.g) {
            datagramChannel = this.e.get(aVar);
        }
        if (datagramChannel != null) {
            return datagramChannel;
        }
        DatagramChannel open = DatagramChannel.open();
        open.connect(inetSocketAddress2);
        open.configureBlocking(false);
        synchronized (this.c) {
            this.c.add(open);
        }
        synchronized (this.g) {
            this.e.put(aVar, open);
            this.f14097a.put(open, aVar);
        }
        Selector selector = this.h;
        if (selector != null) {
            selector.wakeup();
        }
        return open;
    }

    public void e() throws IOException, ClosedSelectorException {
        while (this.b) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.h.select();
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
            Iterator<SelectionKey> it = this.h.selectedKeys().iterator();
            boolean z = false;
            while (it.hasNext()) {
                SelectionKey next = it.next();
                if (!next.isValid()) {
                    it.remove();
                } else {
                    try {
                        if (next.isReadable()) {
                            if (a(next) == 0) {
                                it.remove();
                            }
                            z = true;
                        } else {
                            LogUtil.d("UdpRelayingManager", "key is unreadable");
                            it.remove();
                        }
                    } catch (IOException unused) {
                        LogUtil.e("UdpRelayingManager", "Unable to read from the selected datagram channel");
                        d(next);
                    }
                }
            }
            if (h() || z) {
                LogUtil.d("UdpRelayingManager", "Select completed in ", Long.valueOf(elapsedRealtime2), " ms");
            } else {
                LogUtil.d("UdpRelayingManager", "Select completed in ", Long.valueOf(elapsedRealtime2), " ms and ", "resulted in no work performed ", Integer.valueOf(this.h.keys().size()));
            }
        }
        c();
        LogUtil.d("UdpRelayingManager", "Loop exited");
    }

    private int a(SelectionKey selectionKey) throws IOException {
        a aVar;
        SelectableChannel channel = selectionKey.channel();
        if (!(channel instanceof DatagramChannel)) {
            return -1;
        }
        DatagramChannel datagramChannel = (DatagramChannel) channel;
        synchronized (this.g) {
            aVar = this.f14097a.get(datagramChannel);
        }
        if (aVar == null) {
            LogUtil.c("UdpRelayingManager", "Reading a channel not recorded");
            d(selectionKey);
            return -1;
        }
        this.d.clear();
        int read = datagramChannel.read(this.d);
        if (read == -1) {
            LogUtil.c("UdpRelayingManager", "Datagram not available for a readable key");
            d(selectionKey);
            return read;
        }
        if (d() > 0 && aVar.a() + read > d()) {
            LogUtil.c("UdpRelayingManager", "Current channel's data is bigger than ", Long.valueOf(d()), "b, so need to closed.");
            sqo.f("send udp data Current channel's data is bigger than " + d() + " byte, so need to closed.");
            d(selectionKey);
            return -1;
        }
        this.d.flip();
        int remaining = this.d.remaining();
        byte[] bArr = new byte[remaining];
        this.d.get(bArr);
        if (remaining == 0) {
            return -1;
        }
        e(d(bArr, aVar.d().getAddress().getAddress(), aVar.d().getPort(), aVar.e().getAddress().getAddress(), aVar.e().getPort()), aVar.b);
        aVar.b(aVar.a() + read);
        return read;
    }

    public void e(jug jugVar, String str) {
        if (jugVar == null || TextUtils.isEmpty(str)) {
            LogUtil.c("UdpRelayingManager", "messageBean:", jugVar, "nodeId: ", str);
            sqo.f("sendUdpDataToBt messageBean is null or nodeId is empty");
        } else {
            byte[] b = jtz.b(d(jugVar, str), jugVar);
            LogUtil.d("UdpRelayingManager", "sendUdpDataToBt: ", "dstPort: ", Integer.valueOf(jugVar.e()), "srcPort:", Integer.valueOf(jugVar.k()));
            jum.a().a(b);
        }
    }

    private byte[] d(jug jugVar, String str) {
        return new bms().d(3, str).d(4, b(jugVar.n())).a(5, jugVar.k()).d(6, b(jugVar.c())).a(7, jugVar.e()).a(8, jugVar.j()).d();
    }

    private String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(b & 255);
            sb.append(".");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public jug d(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2) {
        jug jugVar = new jug();
        jugVar.c(6);
        jugVar.b(bArr2);
        jugVar.f(i);
        jugVar.e(bArr3);
        jugVar.e(i2);
        jugVar.c(bArr);
        return jugVar;
    }

    private void d(SelectionKey selectionKey) {
        LogUtil.d("UdpRelayingManager", "Close selection key ", selectionKey);
        selectionKey.cancel();
        SelectableChannel channel = selectionKey.channel();
        if (channel instanceof DatagramChannel) {
            d((DatagramChannel) channel);
            a aVar = this.f14097a.get(channel);
            if (aVar != null) {
                synchronized (this.g) {
                    this.e.remove(aVar);
                    this.f14097a.remove(channel);
                }
                LogUtil.d("UdpRelayingManager", "had removed from map");
            }
        }
    }

    private void d(DatagramChannel datagramChannel) {
        try {
            datagramChannel.close();
        } catch (IOException unused) {
            LogUtil.e("UdpRelayingManager", "IOException during closing channel");
        }
    }

    private boolean h() throws ClosedChannelException {
        LogUtil.d("UdpRelayingManager", "enter registerChannels");
        ArrayList arrayList = new ArrayList(10);
        synchronized (this.c) {
            arrayList.addAll(this.c);
            this.c.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((DatagramChannel) it.next()).register(this.h, 1);
        }
        return !arrayList.isEmpty();
    }

    private void c() {
        for (SelectionKey selectionKey : this.h.keys()) {
            if (selectionKey.channel().isOpen()) {
                d(selectionKey);
            }
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private InetSocketAddress f14098a;
        private String b;
        private InetSocketAddress c;
        private long d;

        private a(String str, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
            this.b = str;
            this.f14098a = inetSocketAddress;
            this.c = inetSocketAddress2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public InetSocketAddress e() {
            return this.f14098a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public InetSocketAddress d() {
            return this.c;
        }

        public long a() {
            return this.d;
        }

        public void b(long j) {
            this.d = j;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            return this.b.equals(aVar.b) && this.f14098a.equals(aVar.f14098a) && this.c.equals(aVar.c);
        }

        public int hashCode() {
            return ((((this.b.hashCode() + 527) * 31) + this.f14098a.hashCode()) * 31) + this.c.hashCode();
        }
    }
}
