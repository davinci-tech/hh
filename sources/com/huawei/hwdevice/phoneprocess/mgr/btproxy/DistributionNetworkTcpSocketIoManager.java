package com.huawei.hwdevice.phoneprocess.mgr.btproxy;

import android.os.SystemClock;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public class DistributionNetworkTcpSocketIoManager {

    /* renamed from: a, reason: collision with root package name */
    private final Delegator f6333a;
    private volatile boolean b;
    private boolean e;
    private Selector h;
    private ServerSocketChannel j;
    private final ByteBuffer d = ByteBuffer.allocate(20480);
    private final Set<SocketChannel> g = new HashSet(16);
    private final Set<SocketChannel> c = new HashSet(16);
    private final Set<SocketChannel> f = new HashSet(16);

    public interface Delegator {
        void doAccept(SocketChannel socketChannel);

        void doClose(SocketChannel socketChannel);

        void doRead(SocketChannel socketChannel, ByteBuffer byteBuffer);

        int doWrite(SocketChannel socketChannel);
    }

    public DistributionNetworkTcpSocketIoManager(Delegator delegator) {
        this.f6333a = delegator;
    }

    public void c(SocketChannel socketChannel) {
        synchronized (this.g) {
            this.g.add(socketChannel);
        }
        Selector selector = this.h;
        if (selector != null) {
            selector.wakeup();
        }
    }

    public void e(SocketChannel socketChannel) {
        synchronized (this.c) {
            this.c.add(socketChannel);
        }
        Selector selector = this.h;
        if (selector != null) {
            selector.wakeup();
        }
    }

    public void b(SocketChannel socketChannel) {
        synchronized (this.f) {
            this.f.add(socketChannel);
        }
        Selector selector = this.h;
        if (selector != null) {
            selector.wakeup();
        }
    }

    public void a() {
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "Shutdown requested");
        this.b = false;
        Selector selector = this.h;
        if (selector != null) {
            selector.wakeup();
        }
    }

    public void d() throws IOException {
        this.b = true;
        this.h = SelectorProvider.provider().openSelector();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 0);
        ServerSocketChannel open = ServerSocketChannel.open();
        this.j = open;
        open.configureBlocking(false);
        this.j.socket().bind(inetSocketAddress);
        this.j.register(this.h, 16);
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "Listening for TCP sockets at ", this.j.socket());
    }

    public void e() throws IOException {
        ServerSocketChannel serverSocketChannel = this.j;
        if (serverSocketChannel != null) {
            try {
                serverSocketChannel.close();
            } catch (IOException unused) {
                LogUtil.e("IOException", new Object[0]);
            }
            this.j = null;
        }
        this.h.close();
    }

    public void c() throws IOException, ClosedSelectorException {
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "Tcp relaying thread started, entering into select loop");
        while (this.b) {
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "enter running tcp loop");
            this.e = false;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.h.select();
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
            e(this.h.selectedKeys().iterator());
            if (i()) {
                this.e = true;
            }
            if (g()) {
                this.e = true;
            }
            if (b()) {
                this.e = true;
            }
            if (this.e) {
                LogUtil.d("DistributionNetworkTcpSocketIoManager", "Select completed in ", Long.valueOf(elapsedRealtime2), " ms");
            } else {
                LogUtil.d("DistributionNetworkTcpSocketIoManager", "Select completed in ", Long.valueOf(elapsedRealtime2), " ms and ", "resulted in no work performed ", Integer.valueOf(this.h.keys().size()));
            }
        }
        e(this.j);
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "Loop exited");
    }

    private void e(Iterator<SelectionKey> it) {
        while (it.hasNext()) {
            SelectionKey next = it.next();
            if (!next.isValid()) {
                LogUtil.c("DistributionNetworkTcpSocketIoManager", "key is Invalid");
                it.remove();
            } else {
                try {
                    if (next.isConnectable()) {
                        b(next);
                        it.remove();
                        this.e = true;
                    } else if (next.isAcceptable()) {
                        a(next);
                        it.remove();
                        this.e = true;
                    } else {
                        e(it, next);
                    }
                } catch (IOException unused) {
                    LogUtil.e("DistributionNetworkTcpSocketIoManager", "Error during operating socket channels");
                    d(next);
                }
            }
        }
    }

    private void e(Iterator<SelectionKey> it, SelectionKey selectionKey) throws IOException {
        if (selectionKey.isReadable()) {
            if (c(selectionKey) == 0) {
                it.remove();
            }
            this.e = true;
        } else {
            if (selectionKey.isWritable()) {
                if (e(selectionKey) == 0) {
                    it.remove();
                }
                this.e = true;
                return;
            }
            it.remove();
        }
    }

    private boolean b() {
        ArrayList arrayList = new ArrayList(16);
        synchronized (this.g) {
            arrayList.addAll(this.g);
            this.g.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d((SocketChannel) it.next());
        }
        return !arrayList.isEmpty();
    }

    private boolean i() throws IOException {
        ArrayList arrayList = new ArrayList(16);
        synchronized (this.c) {
            arrayList.addAll(this.c);
            this.c.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((SocketChannel) it.next()).register(this.h, 8);
        }
        if (!arrayList.isEmpty()) {
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "Registered ", Integer.valueOf(arrayList.size()), " connecting channels");
        }
        return !arrayList.isEmpty();
    }

    private boolean g() throws IOException {
        ArrayList arrayList = new ArrayList(16);
        synchronized (this.f) {
            arrayList.addAll(this.f);
            this.f.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SelectionKey keyFor = ((SocketChannel) it.next()).keyFor(this.h);
            if (keyFor != null && keyFor.isValid()) {
                keyFor.interestOps(keyFor.interestOps() | 4);
            }
        }
        if (!arrayList.isEmpty()) {
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "Registered ", Integer.valueOf(arrayList.size()), " writing channels");
        }
        return !arrayList.isEmpty();
    }

    private void e(SelectableChannel selectableChannel) {
        for (SelectionKey selectionKey : this.h.keys()) {
            if (selectionKey.channel() != selectableChannel && selectionKey.channel().isOpen()) {
                d(selectionKey);
            }
        }
    }

    private void b(SelectionKey selectionKey) throws IOException {
        if (!((SocketChannel) selectionKey.channel()).finishConnect()) {
            d(selectionKey);
        } else {
            selectionKey.interestOps((selectionKey.interestOps() & (-9)) | 1);
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "Channel connected");
        }
    }

    private void a(SelectionKey selectionKey) throws IOException {
        SocketChannel accept = ((ServerSocketChannel) selectionKey.channel()).accept();
        accept.configureBlocking(false);
        accept.socket().setTcpNoDelay(true);
        accept.register(this.h, 1);
        this.f6333a.doAccept(accept);
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "Accepted connection");
    }

    private int c(SelectionKey selectionKey) throws IOException {
        if (!(selectionKey.channel() instanceof SocketChannel)) {
            return -1;
        }
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        this.d.clear();
        int read = socketChannel.read(this.d);
        if (read == -1) {
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "Channel has reached end-of-stream, closing");
            d(selectionKey);
            return read;
        }
        this.d.flip();
        this.f6333a.doRead(socketChannel, this.d);
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "Read ", Integer.valueOf(read), " bytes");
        return read;
    }

    private int e(SelectionKey selectionKey) {
        LogUtil.d("DistributionNetworkTcpSocketIoManager", "enterDoWrite");
        if (!(selectionKey.channel() instanceof SocketChannel)) {
            return -1;
        }
        int doWrite = this.f6333a.doWrite((SocketChannel) selectionKey.channel());
        if (doWrite == 0) {
            selectionKey.interestOps(selectionKey.interestOps() & (-5));
        }
        if (doWrite != 0) {
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "Wrote ", Integer.valueOf(doWrite), " bytes");
        } else {
            LogUtil.c("DistributionNetworkTcpSocketIoManager", "No bytes written: losing interest in channel write ability");
        }
        return doWrite;
    }

    private void d(SocketChannel socketChannel) {
        try {
            this.f6333a.doClose(socketChannel);
            socketChannel.close();
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "Closed channel");
        } catch (IOException unused) {
            LogUtil.e("DistributionNetworkTcpSocketIoManager", "Failed to close channel ");
        }
    }

    private void d(SelectionKey selectionKey) {
        selectionKey.cancel();
        SelectableChannel channel = selectionKey.channel();
        if (channel instanceof SocketChannel) {
            d((SocketChannel) channel);
            return;
        }
        try {
            LogUtil.d("DistributionNetworkTcpSocketIoManager", "channel close");
            channel.close();
        } catch (IOException unused) {
            LogUtil.e("DistributionNetworkTcpSocketIoManager", "Failed to close non-socket channel");
        }
    }
}
