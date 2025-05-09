package com.huawei.hwdevice.phoneprocess.mgr.btproxy;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkTcpSocketIoManager;
import defpackage.bkv;
import defpackage.blt;
import defpackage.bms;
import defpackage.cun;
import defpackage.jtz;
import defpackage.jug;
import defpackage.jui;
import defpackage.juj;
import defpackage.juk;
import defpackage.jum;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SocketChannel;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class DistributionNetworkProxyManager implements DistributionNetworkTcpSocketIoManager.Delegator {
    private static final int COMMAND_QUEUE_SLEEP = 3;
    private static final int COMMAND_QUEUE_WARN = 6;
    private static final int DEFAULT_BYTES_VALUE = -1;
    private static final int DEFAULT_SEQ_NUM = 0;
    private static final int DELAY_TIME_ONCE = 9;
    private static final int DIVISOR_NUM = 3;
    private static final int MAX_TRANSMISSION_SIZE = 990;
    private static final int SERVICE_STATE_STARTED = 3;
    private static final int SERVICE_STATE_STARTING = 2;
    private static final int SERVICE_STATE_STOPPED = 0;
    private static final int SERVICE_STATE_STOPPING = 1;
    private static final String TAG = "DistributionNetworkProxyManager";
    private static final String TAG_RELEASE = "BTSYNC_DistributionNetworkProxyManager";
    private static final int VALID_QUEUE_ROUND_NUMBER_FIVE = 5;
    private String mDeviceMac;
    private Thread mTcpServingThread;
    private volatile int mServiceState = 0;
    private final jui mStreamsMap = new jui();
    private long mTcpMaxSupportPackageSize = 209715200;
    protected final DistributionNetworkTcpSocketIoManager mDistributionNetworkTcpSocketIoManager = new DistributionNetworkTcpSocketIoManager(this);

    protected abstract void doStartService(int i);

    protected abstract void onMessageReceived(jug jugVar);

    protected abstract void startUdpRelayingThread(int i);

    protected abstract void stopUdpRelayingThread();

    public DistributionNetworkProxyManager() {
        LogUtil.d(TAG, "DistributionNetworkProxyManager() init end");
    }

    public void startProxyService(int i, int i2) {
        ReleaseLogUtil.e(TAG_RELEASE, "send start proxy service.");
        if (this.mServiceState == 3 || this.mServiceState == 2) {
            LogUtil.c(TAG, "Received start proxy service request but service is not stopped/stopping yet.");
            return;
        }
        this.mServiceState = 2;
        setTcpMaxSupportPackageSize(i);
        doStartService(i2);
        this.mServiceState = 3;
    }

    public void stopProxyService() {
        ReleaseLogUtil.e(TAG_RELEASE, "send stop proxy Service.");
        if (this.mServiceState == 0 || this.mServiceState == 1) {
            LogUtil.d(TAG, "Received stop proxy service request but service is stopped/stopping.");
            return;
        }
        this.mServiceState = 1;
        doStopService();
        this.mServiceState = 0;
    }

    public void sendMessage(jug jugVar) {
        if (jugVar == null) {
            LogUtil.c(TAG, "sendMessage btProxyMessageBean is null.");
        } else {
            LogUtil.d(TAG, "send message.");
            onMessageReceived(jugVar);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkTcpSocketIoManager.Delegator
    public void doRead(SocketChannel socketChannel, ByteBuffer byteBuffer) {
        if (socketChannel == null || byteBuffer == null) {
            LogUtil.c(TAG, "channel or buffer is null.");
            return;
        }
        LogUtil.d(TAG, "enter Tcp DoRead:");
        juk stream = getStream(socketChannel);
        if (stream != null && stream.l()) {
            int remaining = byteBuffer.remaining();
            if (remaining == 0) {
                LogUtil.d(TAG, "doRead: avoiding sending zero bytes.");
                return;
            }
            byte[] bArr = new byte[remaining];
            long j = remaining;
            if (stream.h() + j > getTcpMaxSupportPackageSize()) {
                LogUtil.d(TAG, "Current channel's data is bigger than ", Long.valueOf(getTcpMaxSupportPackageSize()), " byte, so need to closed.");
                this.mDistributionNetworkTcpSocketIoManager.c(stream.f());
                sendCloseToNode(stream.i(), stream.j());
                sqo.f("send tcp data Current channel's data is bigger than " + getTcpMaxSupportPackageSize() + " byte, so need to closed.");
                return;
            }
            byteBuffer.get(bArr);
            long e = stream.e();
            jug jugVar = new jug();
            jugVar.a(stream.i());
            jugVar.c(5);
            jugVar.i(stream.j());
            jugVar.c(bArr);
            jugVar.b(String.valueOf(e));
            sendTcpDataToBt(jugVar);
            stream.a(e);
            stream.c(stream.h() + j);
            LogUtil.d(TAG, "doRead: forwarded TCP packets to node: ", blt.a(stream.i()), ", seqId: ", Integer.valueOf(stream.j()), ", seqNumOfThisPacket: ", Long.valueOf(e));
            return;
        }
        LogUtil.c(TAG, "doRead: unexpected inactive stream.");
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkTcpSocketIoManager.Delegator
    public int doWrite(SocketChannel socketChannel) {
        int i = -1;
        if (socketChannel == null) {
            LogUtil.c(TAG, "doWrite channel is null.");
            return -1;
        }
        LogUtil.d(TAG, "enter TcpDoWrite:");
        juk stream = getStream(socketChannel);
        if (stream == null) {
            LogUtil.c(TAG, "Ignoring write for invalid stream channel.");
        } else {
            try {
                LogUtil.d(TAG, "Writing now to stream: ", Integer.valueOf(stream.j()), ", node: ", blt.a(stream.i()));
                i = stream.n();
                if (!stream.o() && stream.g()) {
                    LogUtil.d(TAG, "Closing Stream streamId: ", Integer.valueOf(stream.j()), ", node:", blt.a(stream.i()), ", closed and all writes flushed.");
                    this.mDistributionNetworkTcpSocketIoManager.c(socketChannel);
                }
            } catch (IOException unused) {
                LogUtil.e(TAG, "Failed to write data to stream: ", Integer.valueOf(stream.j()), ", for node", blt.a(stream.i()), ", IOException");
                this.mDistributionNetworkTcpSocketIoManager.c(socketChannel);
            }
        }
        return i;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkTcpSocketIoManager.Delegator
    public void doClose(SocketChannel socketChannel) {
        if (socketChannel == null) {
            LogUtil.c(TAG, "doClose channel is null.");
            return;
        }
        LogUtil.d(TAG, "enter tcpDoClose:");
        juk removeStream = removeStream(socketChannel);
        if (removeStream == null || removeStream.g()) {
            return;
        }
        LogUtil.d(TAG, "Closed TCP Stream: ", Integer.valueOf(removeStream.j()), ", for node: ", blt.a(removeStream.i()));
        sendCloseToNode(removeStream.i(), removeStream.j());
        removeStream.m();
    }

    protected void doStopService() {
        LogUtil.d(TAG, "enter doStopService.");
        stopTcpRelayingThread();
        stopUdpRelayingThread();
        synchronized (this.mStreamsMap) {
            this.mStreamsMap.c();
        }
        this.mDeviceMac = null;
        juj.e();
        BtProxyNetworkChangeReceiver.a();
        jum.a().c();
        jum.a();
        jum.e();
        LogUtil.d(TAG, "end doStopService.");
    }

    private void sendCloseToNode(String str, int i) {
        LogUtil.d(TAG, "Sending close message to node: ", blt.a(str), ", stream: ", Integer.valueOf(i));
        jug jugVar = new jug();
        jugVar.a(str);
        jugVar.i(i);
        jugVar.c(new byte[0]);
        jugVar.b(String.valueOf(0));
        jugVar.c(4);
        sendTcpDataToBt(jugVar);
    }

    private juk getStream(SocketChannel socketChannel) {
        juk d;
        synchronized (this.mStreamsMap) {
            d = this.mStreamsMap.d(socketChannel);
        }
        return d;
    }

    protected juk getStream(String str, int i) {
        juk b;
        if (TextUtils.isEmpty(str) || i < 0) {
            return new juk(false);
        }
        synchronized (this.mStreamsMap) {
            b = this.mStreamsMap.b(str, i);
        }
        return b;
    }

    private juk removeStream(SocketChannel socketChannel) {
        juk a2;
        synchronized (this.mStreamsMap) {
            a2 = this.mStreamsMap.a(socketChannel);
        }
        return a2;
    }

    protected void addStream(juk jukVar) {
        synchronized (this.mStreamsMap) {
            this.mStreamsMap.c(jukVar);
        }
    }

    protected void handleWriteFromNode(String str, jug jugVar) {
        if (TextUtils.isEmpty(str) || jugVar == null) {
            LogUtil.c(TAG, "node id: ", blt.a(str), ", messageBean: ", jugVar);
            return;
        }
        LogUtil.d(TAG, "handleWriteFromNode seqId: ", Integer.valueOf(jugVar.g()), ", seqNum: ", jugVar.o());
        int g = jugVar.g();
        long n = CommonUtil.n(BaseApplication.getContext(), jugVar.o());
        byte[] f = jugVar.f();
        juk stream = getStream(str, g);
        if (stream == null) {
            LogUtil.c(TAG, "Stream: ", Integer.valueOf(g), ", deviceId: ", blt.a(str), ", seqNum: ", Long.valueOf(n), ", dataLength:", Integer.valueOf(f.length));
            sendCloseToNode(str, g);
            return;
        }
        long c = stream.c();
        LogUtil.d(TAG, "expectedSeqNum: ", Long.valueOf(c), "incomingSeqNum: ", Long.valueOf(n), "nodeId: ", blt.a(stream.i()));
        if (n != c) {
            LogUtil.d(TAG, "out of order packet streamId: ", Integer.valueOf(g), ", node id: ", blt.a(str), ", dataLength: ", Integer.valueOf(f.length), ", incomingSeqNum: ", Long.valueOf(n), ", expectedSeqNum: ", Long.valueOf(c));
            this.mDistributionNetworkTcpSocketIoManager.c(stream.f());
        } else {
            stream.d(n);
            stream.e(stream.d() + f.length);
            stream.e(ByteBuffer.wrap(f));
            this.mDistributionNetworkTcpSocketIoManager.b(stream.f());
        }
    }

    protected void handleCloseFromNode(String str, jug jugVar) {
        int g = jugVar.g();
        LogUtil.d(TAG, "Closing streamId: ", Integer.valueOf(g), ", as requested by nodeId: ", blt.a(str));
        juk stream = getStream(str, g);
        if (stream == null) {
            LogUtil.c(TAG, "Ignoring close for invalid streamId: ", Integer.valueOf(g), ", node: ", blt.a(str));
            return;
        }
        if (!stream.g()) {
            stream.m();
            if (!stream.o()) {
                LogUtil.d(TAG, "streamId: ", Integer.valueOf(g), ", nodeId: ", blt.a(str));
                this.mDistributionNetworkTcpSocketIoManager.c(stream.f());
                return;
            } else {
                LogUtil.d(TAG, "streamId: ", Integer.valueOf(g), ", nodeId: ", blt.a(str));
                return;
            }
        }
        LogUtil.d(TAG, "getSourceNodeClosed is true.");
    }

    protected void startTcpRelayingThread() {
        String str = TAG;
        LogUtil.d(TAG, "Start TCP serving thread.");
        try {
            this.mDistributionNetworkTcpSocketIoManager.d();
        } catch (IOException unused) {
            LogUtil.e(TAG, "Failed to setup TCP proxy.");
        }
        Thread thread = new Thread(str) { // from class: com.huawei.hwdevice.phoneprocess.mgr.btproxy.DistributionNetworkProxyManager.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    DistributionNetworkProxyManager.this.mDistributionNetworkTcpSocketIoManager.c();
                } catch (IOException | ClosedSelectorException unused2) {
                    LogUtil.e(DistributionNetworkProxyManager.TAG, " btProxy TCP serving thread stopped due to exception.");
                }
            }
        };
        this.mTcpServingThread = thread;
        thread.start();
    }

    private void stopTcpRelayingThread() {
        if (this.mTcpServingThread != null) {
            DistributionNetworkTcpSocketIoManager distributionNetworkTcpSocketIoManager = this.mDistributionNetworkTcpSocketIoManager;
            if (distributionNetworkTcpSocketIoManager != null) {
                distributionNetworkTcpSocketIoManager.a();
                try {
                    this.mTcpServingThread.join();
                    this.mDistributionNetworkTcpSocketIoManager.e();
                } catch (IOException unused) {
                    LogUtil.e(TAG, "Failed to clean up status of TCP proxy.");
                } catch (InterruptedException unused2) {
                    LogUtil.e(TAG, "Failed to join TCP relaying thread.");
                }
                this.mTcpServingThread = null;
                LogUtil.d(TAG, "btProxy TCP relaying thread stopped.");
                return;
            }
            LogUtil.c(TAG, "mDistributionNetworkTcpSocketIoManager is null.");
            return;
        }
        LogUtil.c(TAG, "tcpServingThread is null.");
    }

    private void sendTcpDataToBt(jug jugVar) {
        byte[] b = jtz.b(getTcpCommandTlvBytes(jugVar), jugVar);
        jum.a().a(b);
        try {
            int length = b.length;
            LogUtil.d(TAG, "sendTcpDataToBt length: ", Integer.valueOf(length));
            int i = length / MAX_TRANSMISSION_SIZE;
            int i2 = 9;
            if (i != 0) {
                i2 = 9 * i;
            }
            String deviceMac = getDeviceMac();
            int a2 = bkv.a(deviceMac);
            LogUtil.d(TAG, "commandQueueSize is : ", Integer.valueOf(a2), "defaultDelay is : ", Integer.valueOf(i2));
            if (a2 >= 6) {
                LogUtil.d(TAG, "commandQueueSize over warn value: ", Integer.valueOf(i2));
                Thread.sleep(i2);
                setCommandQueueSleep(deviceMac, i2, 5);
            } else if (a2 > 3) {
                int i3 = (i2 / 3) << 1;
                Thread.sleep(i3);
                LogUtil.d(TAG, "sendTcpDataToBt else. sleep: ", Integer.valueOf(i3));
            }
        } catch (InterruptedException unused) {
            LogUtil.e(TAG, "sendTcpDataToBt occur InterruptedException.");
        }
    }

    private String getDeviceMac() {
        if (!TextUtils.isEmpty(this.mDeviceMac)) {
            return this.mDeviceMac;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, TAG);
        if (deviceList == null || deviceList.isEmpty()) {
            return this.mDeviceMac;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo != null) {
            this.mDeviceMac = deviceInfo.getDeviceIdentify();
        }
        return this.mDeviceMac;
    }

    private void setCommandQueueSleep(String str, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int a2 = bkv.a(str);
            LogUtil.d(TAG, "setCommandQueueSleep commandQueueSize: ", Integer.valueOf(a2));
            if (a2 < 6) {
                return;
            }
            try {
                Thread.sleep(i);
            } catch (InterruptedException unused) {
                LogUtil.e(TAG, "setCommandQueue occur InterruptedException.");
            }
        }
    }

    private byte[] getTcpCommandTlvBytes(jug jugVar) {
        return new bms().a(1, jugVar.g()).d(2, jugVar.o()).d(3, jugVar.d()).a(8, jugVar.j()).d();
    }

    public long getTcpMaxSupportPackageSize() {
        return this.mTcpMaxSupportPackageSize;
    }

    public void setTcpMaxSupportPackageSize(long j) {
        if (j > 0) {
            this.mTcpMaxSupportPackageSize = j;
        }
    }
}
