package defpackage;

import health.compact.a.LogUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/* loaded from: classes7.dex */
public class spc {
    private static final Object c = new Object();
    private static volatile spc d;

    /* renamed from: a, reason: collision with root package name */
    private Socket f17192a;
    private ServerSocket e = null;

    private spc() {
    }

    public int e() {
        try {
            if (this.e != null) {
                LogUtil.c("WifiP2pTransferServerSocket", "serverSocket is ok.");
                return this.e.getLocalPort();
            }
            ServerSocket serverSocket = new ServerSocket();
            this.e = serverSocket;
            serverSocket.setReuseAddress(true);
            this.e.bind(new InetSocketAddress(0));
            return this.e.getLocalPort();
        } catch (IOException e) {
            LogUtil.e("WifiP2pTransferServerSocket", "createWifiP2pTransferServerSocket IOException is: ", bll.a(e));
            return 0;
        }
    }

    public void c() {
        if (this.e != null) {
            try {
                LogUtil.c("WifiP2pTransferServerSocket", "ServerSocket ready accept, will lock until tcp create success.");
                this.f17192a = this.e.accept();
                LogUtil.c("WifiP2pTransferServerSocket", "socket accept success");
                return;
            } catch (IOException e) {
                LogUtil.e("WifiP2pTransferServerSocket", "If lock accept, please check tcp dump. Before TCP, watch need check ARP,accept IOException is: ", bll.a(e));
                return;
            }
        }
        LogUtil.e("WifiP2pTransferServerSocket", "mServerSocket is null. Please check.");
    }

    public Socket a() {
        return this.f17192a;
    }

    public static spc d() {
        spc spcVar;
        synchronized (c) {
            if (d == null) {
                d = new spc();
            }
            spcVar = d;
        }
        return spcVar;
    }

    public void b() {
        LogUtil.c("WifiP2pTransferServerSocket", "closeSocket");
        iyv.g();
        try {
            Socket socket = this.f17192a;
            if (socket != null) {
                blv.d(socket.getOutputStream());
            }
        } catch (IOException e) {
            LogUtil.e("WifiP2pTransferServerSocket", "closeSocket: ", bll.a(e));
        }
        blv.d(this.e);
        blv.d(this.f17192a);
        this.e = null;
        this.f17192a = null;
    }
}
