package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkProxyManager;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SocketChannel;

/* loaded from: classes5.dex */
public class juh extends DistributionNetworkProxyManager {
    private Thread c;
    private jun e = new jun();

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkProxyManager
    public void startUdpRelayingThread(int i) {
        String str = "DistributionNetworkPhoneProxy";
        LogUtil.d("DistributionNetworkPhoneProxy", "Start btProxy UDP relaying thread.");
        try {
            this.e.c(i);
        } catch (IOException unused) {
            LogUtil.e("DistributionNetworkPhoneProxy", "Failed to setup UDP relaying thread");
        }
        Thread thread = new Thread(str) { // from class: juh.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    juh.this.e.e();
                } catch (IOException | ClosedSelectorException e) {
                    LogUtil.e("DistributionNetworkPhoneProxy", "btProxy UDP serving thread stopped due to ", ExceptionUtils.d(e));
                }
            }
        };
        this.c = thread;
        thread.start();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkProxyManager
    public void stopUdpRelayingThread() {
        if (this.c != null) {
            jun junVar = this.e;
            if (junVar != null) {
                junVar.b();
                try {
                    this.c.join();
                    this.e.a();
                } catch (InterruptedException unused) {
                    LogUtil.e("DistributionNetworkPhoneProxy", "Failed to join UDP relaying thread");
                }
                LogUtil.d("DistributionNetworkPhoneProxy", "btProxy UDP relaying thread stopped");
                this.c = null;
                return;
            }
            LogUtil.c("DistributionNetworkPhoneProxy", "mUdpRelayingManager is null");
            return;
        }
        LogUtil.c("DistributionNetworkPhoneProxy", "stopUdpRelayingThread() udpRelayingManager is null");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkProxyManager
    public void doStartService(int i) {
        startTcpRelayingThread();
        startUdpRelayingThread(i);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkProxyManager
    public void onMessageReceived(jug jugVar) {
        if (jugVar == null) {
            LogUtil.c("DistributionNetworkPhoneProxy", "messageBean is null");
            return;
        }
        String d = jugVar.d();
        int j = jugVar.j();
        LogUtil.d("DistributionNetworkPhoneProxy", "onMessageReceived proxy type: ", Integer.valueOf(j));
        if (j == 3) {
            a(jugVar, d);
            return;
        }
        if (j == 4) {
            handleCloseFromNode(d, jugVar);
            return;
        }
        if (j == 5) {
            handleWriteFromNode(d, jugVar);
        } else {
            if (j == 6) {
                try {
                    d(jugVar, d);
                    return;
                } catch (IOException unused) {
                    LogUtil.e("DistributionNetworkPhoneProxy", "Exception sending UDP packets out");
                    return;
                }
            }
            LogUtil.c("DistributionNetworkPhoneProxy", "onMessageReceived Unhandled packet type: ", Integer.valueOf(j));
        }
    }

    private void a(jug jugVar, String str) {
        int g = jugVar.g();
        byte[] c = jugVar.c();
        int e = jugVar.e();
        try {
            SocketChannel open = SocketChannel.open();
            open.configureBlocking(false);
            open.connect(new InetSocketAddress(InetAddress.getByAddress(c), e));
            addStream(new juk(open, str, g, true));
            if (this.mDistributionNetworkTcpSocketIoManager != null) {
                this.mDistributionNetworkTcpSocketIoManager.e(open);
            } else {
                LogUtil.c("DistributionNetworkPhoneProxy", "DistributionNetworkTcpSocketIoManager is null");
            }
            LogUtil.c("DistributionNetworkPhoneProxy", "handleTcpConnect success.");
        } catch (SocketException unused) {
            LogUtil.e("DistributionNetworkPhoneProxy", "socket is closed or socket is not connected or socket input is shutdown.");
        } catch (UnknownHostException unused2) {
            LogUtil.e("DistributionNetworkPhoneProxy", "if the IP address of the host could not be determined.");
        } catch (IOException unused3) {
            LogUtil.e("DistributionNetworkPhoneProxy", "if an I/O error occurs when creating the socket.");
        }
    }

    private void d(jug jugVar, String str) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByAddress(jugVar.n()), jugVar.k());
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress(InetAddress.getByAddress(jugVar.c()), jugVar.e());
        byte[] f = jugVar.f();
        LogUtil.d("DistributionNetworkPhoneProxy", "handleUdpData: srcPort: ", Integer.valueOf(jugVar.k()), " dstPort: ", Integer.valueOf(jugVar.e()));
        if (this.c != null) {
            jun junVar = this.e;
            if (junVar != null) {
                junVar.d(str, inetSocketAddress, inetSocketAddress2).send(ByteBuffer.wrap(f), inetSocketAddress2);
                LogUtil.c("DistributionNetworkPhoneProxy", "handleUdpData success.");
                return;
            } else {
                LogUtil.c("DistributionNetworkPhoneProxy", "udpRelayingManager is null");
                return;
            }
        }
        LogUtil.c("DistributionNetworkPhoneProxy", "udpRelayingThread is null");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkTcpSocketIoManager.Delegator
    public void doAccept(SocketChannel socketChannel) {
        LogUtil.d("DistributionNetworkPhoneProxy", "doAccept");
    }
}
