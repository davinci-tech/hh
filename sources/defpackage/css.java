package defpackage;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.wifi.control.logic.AccessNetWorkTask;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Locale;
import okhttp3.internal.connection.RealConnection;

/* loaded from: classes3.dex */
public class css extends AccessNetWorkTask {
    private static long c;
    private static byte[] d = new byte[2048];
    private BaseCallbackInterface b;
    private Context f;
    private ctw i;
    private int j;
    private a k;
    private String l;
    private int[] m;
    private int[] o;
    private WifiManager.MulticastLock q;
    private c r;
    private final WifiManager v;
    private final int[] t = {2, 8, 16};
    private final int[] w = {2, 3, 6};
    private final int[] e = {2, 5, 10};
    private int y = 0;
    private int s = 0;
    private long x = 0;
    private int u = 0;
    private int p = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f11449a = 0;
    private boolean h = false;
    private boolean g = false;
    private Handler n = new Handler() { // from class: css.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                css.this.LV_(message);
            } else if (i == 1) {
                cpw.e(false, "MulticastTask", "Device config failure ,result report ui ", css.this.a());
                css.this.i.a("config network failure", String.valueOf(2108));
                css.this.b();
                css.this.stopTask();
                ctv.e(css.this.f);
                if (css.this.b != null) {
                    css.this.b.onFailure(2108);
                }
            } else if (i == 2) {
                cpw.a(false, "MulticastTask", "stop send package", Integer.valueOf(css.this.t[0]), " send ", Integer.valueOf(css.this.p), " multicast", Integer.valueOf(css.this.f11449a), " broadcast");
                if (css.this.b != null) {
                    css.this.b.onStatus(2210);
                }
                css.this.pauseTask();
            } else if (i == 5) {
                cpw.e(false, "MulticastTask", "UDP receive error，result report ui ", css.this.a());
                css.this.stopTask();
                css.this.i.a("config network failure", String.valueOf(2101));
                ctv.e(css.this.f);
                if (css.this.b != null) {
                    css.this.b.onFailure(2101);
                }
            } else {
                cpw.c(false, "MulticastTask", "mHandler default");
            }
            css.this.LU_(message);
        }
    };

    public css(Context context, csr csrVar, BaseCallbackInterface baseCallbackInterface) {
        this.f = context;
        this.b = baseCallbackInterface;
        this.m = csrVar.d();
        this.l = csrVar.a();
        this.o = csn.e(this.m);
        this.k = new a();
        this.r = new c();
        this.v = (WifiManager) this.f.getSystemService("wifi");
        ctw ctwVar = new ctw("modeMulti/Brocast/Probe");
        this.i = ctwVar;
        ctwVar.a();
    }

    public void c() {
        WifiManager.MulticastLock createMulticastLock = this.v.createMulticastLock("health_wifi.test");
        this.q = createMulticastLock;
        createMulticastLock.acquire();
    }

    public void b() {
        int i = this.y;
        if (i == 1) {
            this.y = 0;
        } else {
            this.y = i + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LU_(Message message) {
        int i = message.what;
        if (i == 3) {
            cpw.a(false, "MulticastTask", "low speed mode 2");
            a(2);
            return;
        }
        if (i == 4) {
            cpw.a(false, "MulticastTask", "median speed mode 1");
            a(1);
            return;
        }
        if (i == 6) {
            this.y = 1;
            cpw.a(false, "MulticastTask", "change mode ", 1);
            this.k.c(this.y);
        } else if (i == 7) {
            this.v.startScan();
            this.n.sendEmptyMessageDelayed(7, 2000L);
        } else if (i == 2207) {
            cpw.c(false, "MulticastTask", "MULTICAST_STATUS_COAP_SCAN_OK");
            LV_(message);
        } else {
            cpw.c(false, "MulticastTask", "mHandler handleHandlerMessage default");
        }
    }

    private void e() {
        this.n.removeMessages(0);
        this.n.removeMessages(3);
        this.n.removeMessages(4);
        this.n.removeMessages(2);
        this.n.removeMessages(1);
        this.n.removeMessages(6);
        this.n.removeMessages(7);
    }

    private void d() {
        long nanoTime = System.nanoTime();
        this.x = nanoTime;
        cpw.a(false, "MulticastTask", "startMulticast: start time is ", Long.valueOf(nanoTime));
        d(1);
        int i = this.j;
        if (i == 0) {
            this.n.sendEmptyMessageDelayed(2, OpAnalyticsConstants.H5_LOADING_DELAY);
        } else if (i == 1) {
            this.y = 0;
            this.n.sendEmptyMessageDelayed(6, PreConnectManager.CONNECT_INTERNAL);
            this.n.sendEmptyMessageDelayed(2, 60000L);
        } else {
            this.n.sendEmptyMessageDelayed(2, 60000L);
        }
        this.n.sendEmptyMessageDelayed(1, 90000L);
        a aVar = this.k;
        if (aVar != null) {
            aVar.c();
        }
        c cVar = this.r;
        if (cVar != null) {
            cVar.a();
        }
        this.n.sendEmptyMessage(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LV_(Message message) {
        try {
            String str = message.obj instanceof String ? (String) message.obj : null;
            if (str != null) {
                cpw.a(true, "MulticastTask", "baseurl is ", str);
                stopTask();
                this.i.d("config network success");
                BaseCallbackInterface baseCallbackInterface = this.b;
                if (baseCallbackInterface != null) {
                    baseCallbackInterface.onSuccess(str);
                }
            } else {
                stopTask();
                BaseCallbackInterface baseCallbackInterface2 = this.b;
                if (baseCallbackInterface2 != null) {
                    baseCallbackInterface2.onFailure(2101);
                }
                this.i.a("config network success ,get url failure", String.valueOf(2101));
            }
        } catch (IndexOutOfBoundsException e) {
            cpw.e(false, "MulticastTask", e.getMessage());
        }
        cpw.a(false, "MulticastTask", "total send ", Integer.valueOf(this.u), " unicast，", Integer.valueOf(this.p), " multicast", Integer.valueOf(this.f11449a), " broadcast");
    }

    public void a(int i) {
        this.s = i;
    }

    public void d(int i) {
        this.j = i;
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void startTask() {
        this.h = true;
        this.g = true;
        c();
        d();
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void pauseTask() {
        cpw.c(false, "MulticastTask", "disableMulticastAp");
        a aVar = this.k;
        if (aVar != null) {
            aVar.e();
        }
        WifiManager.MulticastLock multicastLock = this.q;
        if (multicastLock == null || !multicastLock.isHeld()) {
            return;
        }
        this.q.release();
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void stopTask() {
        cpw.a(false, "MulticastTask", "disableMulticast: in");
        e();
        a aVar = this.k;
        if (aVar != null) {
            aVar.e();
        }
        c cVar = this.r;
        if (cVar != null) {
            cVar.e();
        }
        WifiManager.MulticastLock multicastLock = this.q;
        if (multicastLock == null || !multicastLock.isHeld()) {
            return;
        }
        this.q.release();
    }

    class a implements Runnable {
        private volatile boolean b;
        boolean d;

        private a() {
            this.d = false;
            this.b = false;
        }

        public void c(int i) {
            if (i == 0) {
                this.d = true;
            } else if (i == 1) {
                this.d = false;
            } else {
                this.d = true;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0070  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 268
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: css.a.run():void");
        }

        private void a(MulticastSocket multicastSocket, DatagramSocket datagramSocket) {
            if (multicastSocket != null) {
                multicastSocket.close();
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

        public void c() {
            this.b = false;
            ThreadPoolManager.d().d("MulticastTask", this);
        }

        public void e() {
            this.b = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DatagramSocket datagramSocket, int i) {
        a(2, datagramSocket, i, ctx.a("255"), AccessNetWorkTask.DEFAULT_BROADCAST_PORT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MulticastSocket multicastSocket, int i, String str) {
        a(0, multicastSocket, i, str, 58000);
    }

    private void a(int i, DatagramSocket datagramSocket, int i2, String str, int i3) {
        long nanoTime = System.nanoTime();
        long j = nanoTime - c;
        if (j < 0) {
            j = 0;
        }
        c = nanoTime;
        try {
            if (i == 0) {
                r7 = this.t.length > this.s ? (r0[r13] * 1000000) - j : 0L;
                this.p++;
                if (!csn.a(str)) {
                    return;
                }
            } else if (i == 2) {
                r7 = this.e.length > this.s ? (r0[r13] * 1000000) - j : 0L;
                this.f11449a++;
            }
            b(r7);
        } catch (IOException e) {
            e = e;
        }
        try {
            DatagramPacket datagramPacket = new DatagramPacket(d, i2, InetAddress.getByName(str), i3);
            long nanoTime2 = System.nanoTime();
            if (datagramSocket != null) {
                datagramSocket.send(datagramPacket);
            } else {
                LogUtil.h("MulticastTask", " the socket is null send packet failure");
            }
            long nanoTime3 = System.nanoTime();
            if (this.h) {
                e(nanoTime3, nanoTime2);
            }
        } catch (IOException e2) {
            e = e2;
            cpw.e(false, "MulticastTask", e.getMessage(), "current Index", Integer.valueOf(i2));
        }
    }

    private void b(long j) {
        if (j <= 0 || j >= 200000000) {
            return;
        }
        try {
            c += j;
            Thread.sleep(j / 1000000, (int) (j % 1000000));
        } catch (InterruptedException e) {
            cpw.e(false, "MulticastTask", "Unit sleep failed, ", e.getMessage());
        }
    }

    private void e(long j, long j2) {
        int[] iArr;
        int i;
        long j3 = j - j2;
        if (j3 > 5000000) {
            if (this.g && (i = (iArr = this.t)[0]) <= 2) {
                iArr[0] = i + 4;
                int[] iArr2 = this.w;
                iArr2[0] = iArr2[0] + 1;
                int[] iArr3 = this.e;
                iArr3[0] = iArr3[0] + 4;
            } else if (j3 > 50000000) {
                int[] iArr4 = this.t;
                iArr4[0] = iArr4[0] + 2;
                int[] iArr5 = this.w;
                iArr5[0] = iArr5[0] + 1;
                int[] iArr6 = this.e;
                iArr6[0] = iArr6[0] + 2;
            } else {
                int[] iArr7 = this.t;
                iArr7[0] = iArr7[0] + 1;
                int[] iArr8 = this.e;
                iArr8[0] = iArr8[0] + 1;
            }
            int[] iArr9 = this.t;
            if (iArr9[0] < 10 && j - this.x >= RealConnection.IDLE_CONNECTION_HEALTHY_NS) {
                iArr9[0] = 10;
                this.w[0] = 10;
                this.e[0] = 10;
            }
            if (iArr9[0] > 200) {
                iArr9[0] = 200;
                this.w[0] = 200;
                this.e[0] = 200;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DatagramSocket datagramSocket, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            a(datagramSocket, 8);
            a(datagramSocket, 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MulticastSocket multicastSocket, int i) {
        String str = this.l + "127.141";
        for (int i2 = 0; i2 < i; i2++) {
            e(multicastSocket, 304, str);
        }
    }

    class c implements Runnable {
        DatagramSocket b;
        private volatile boolean d;

        private c() {
            this.b = null;
            this.d = false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0082, code lost:
        
            if (r6 == null) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0084, code lost:
        
            r6 = r6.toString().replaceAll("/", "");
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0093, code lost:
        
            defpackage.cpw.c(false, "MulticastTask", "UdpServer: ipClientAddress is ", r6);
            defpackage.cpw.c(false, "MulticastTask", "UdpServer get message: ", r6);
            r7 = android.os.Message.obtain();
            r7.what = 0;
            r7.obj = r6;
            r12.e.n.sendMessage(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0091, code lost:
        
            r6 = "0.0.0.0";
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 290
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: css.c.run():void");
        }

        public void a() {
            css.this.r.d = false;
            jdx.b(css.this.r);
        }

        public void e() {
            css.this.r.d = true;
            if (css.this.r.b == null) {
                return;
            }
            cpw.c(false, "MulticastTask", "mUdpServer: is close ", Boolean.valueOf(css.this.r.b.isClosed()));
            css.this.r.b.close();
            cpw.e(false, "MulticastTask", "UdpServer: stop() serverSocket closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        int i = this.u / 500;
        if (i > 99) {
            i = 99;
        }
        int i2 = this.p / 500;
        if (i2 > 99) {
            i2 = 99;
        }
        int i3 = this.f11449a / 500;
        int i4 = i3 <= 99 ? i3 : 99;
        String format = String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i));
        String format2 = String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i2));
        String format3 = String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i4));
        String format4 = String.format(Locale.ENGLISH, "-%03d", Integer.valueOf(this.t[0]));
        StringBuilder sb = new StringBuilder(16);
        sb.append(this.y);
        sb.append(format);
        sb.append(format2);
        sb.append(format3);
        sb.append(format4);
        return sb.toString();
    }
}
