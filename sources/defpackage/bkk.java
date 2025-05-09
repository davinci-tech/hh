package defpackage;

import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class bkk extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private int f423a;
    private final AtomicBoolean b;
    private DeviceInfo c;
    private MessageReceiveCallback d;
    private final LinkedBlockingQueue<byte[]> e;
    private String h;

    public bkk(DeviceInfo deviceInfo, MessageReceiveCallback messageReceiveCallback, int i) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.b = atomicBoolean;
        String str = "ReceiverDataAsyncProcessor5A" + i;
        this.h = str;
        LogUtil.c("ReceiverDataAsyncProcessor5A", "ReceiverDataAsyncProcessor5A mtag ", str);
        this.e = new LinkedBlockingQueue<>();
        if (deviceInfo != null && deviceInfo.getDeviceMac() != null) {
            this.c = deviceInfo;
            int length = deviceInfo.getDeviceMac().length();
            String deviceMac = deviceInfo.getDeviceMac();
            if (length >= 3) {
                setName("ReceiverDataAsyncProcessor5A-" + deviceMac.substring(length - 3, length));
            } else {
                setName("ReceiverDataAsyncProcessor5A-" + deviceMac);
            }
        }
        if (messageReceiveCallback == null) {
            LogUtil.a("ReceiverDataAsyncProcessor5A", "ReceiverDataAsyncProcessor5A receiveCallback is null.");
        }
        this.d = messageReceiveCallback;
        atomicBoolean.set(true);
        this.f423a = i;
    }

    public boolean c(byte[] bArr) {
        if (this.e == null) {
            LogUtil.a(this.h, "receive data queue is invalid.");
            return false;
        }
        if (bArr == null) {
            LogUtil.a(this.h, "data is invalid.");
            return false;
        }
        LogUtil.c(this.h, "add receive data device: ", blt.a(this.c.getDeviceMac()));
        if (this.e.size() >= 10000) {
            LogUtil.e(this.h, "add receive data error. queue is max than ", 10000);
            return false;
        }
        return this.e.offer(bArr);
    }

    public void e() {
        LogUtil.c(this.h, "stop receive data  thread.", blt.a(this.c.getDeviceMac()));
        this.b.set(false);
        LinkedBlockingQueue<byte[]> linkedBlockingQueue = this.e;
        if (linkedBlockingQueue != null) {
            linkedBlockingQueue.clear();
            this.e.offer(new byte[0]);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        LogUtil.c(this.h, "receive data thread start.", blt.a(this.c));
        if (this.e == null) {
            LogUtil.a(this.h, "receive data queue is invalid.", blt.a(this.c));
            return;
        }
        while (this.b.get()) {
            try {
                byte[] take = this.e.take();
                if (take != null && take.length > 0) {
                    blt.d(this.h, take, blt.a(this.c), " Device-->SDK : ");
                    d(take);
                }
            } catch (InterruptedException unused) {
                LogUtil.e(this.h, "mReceiveDataQueue take InterruptedException");
            }
        }
        LogUtil.c(this.h, "receive data thread stop.", blt.a(this.c));
    }

    private void d(byte[] bArr) {
        biu biuVar = new biu();
        biuVar.d(bArr);
        biuVar.d(this.f423a);
        if (this.d == null || this.c == null) {
            return;
        }
        LogUtil.c(this.h, "dataLen: ", Integer.valueOf(bArr.length), "mSocketChannel: ", Integer.valueOf(this.f423a));
        this.d.onDataReceived(this.c, biuVar, 0);
    }
}
