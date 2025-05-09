package defpackage;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Patterns;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.wifi.control.logic.AccessNetWorkTask;
import com.huawei.health.device.wifi.entity.model.Entity;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.ecologydevice.connectivity.ScanFilter;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.cth;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.Charset;
import java.util.Locale;
import okhttp3.internal.connection.RealConnection;

/* loaded from: classes3.dex */
public class csm extends AccessNetWorkTask {

    /* renamed from: a, reason: collision with root package name */
    private static long f11439a;
    private static byte[] e = new byte[2048];
    private d ab;
    private ScanFilter ad;
    private WifiManager af;
    private c ai;
    private int[] an;
    private final csv i;
    private BaseCallbackInterface j;
    private int[][] k;
    private String l;
    private final csp m;
    private int n;
    private Context o;
    private int[] p;
    private int q;
    private int[] s;
    private ctw t;
    private WifiManager.MulticastLock u;
    private String v;
    private final int[] ah = {2, 8, 16};
    private final int[] ae = {2, 3, 6};
    private final int[] ac = {2, 5, 10};
    private int z = 0;
    private int x = 0;
    private long aa = 0;
    private int ag = 0;
    private int y = 0;
    private int g = 0;
    private boolean b = false;
    private boolean f = false;
    private boolean c = false;
    private boolean d = false;
    private boolean h = false;
    private CountDownTimer w = new CountDownTimer(90000, 5000) { // from class: csm.3
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            cpw.c(false, "CombinationTask", "mScanCoapDevicesTimer: onTick");
            csm csmVar = csm.this;
            csmVar.c(csmVar.l);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            cpw.c(false, "CombinationTask", "mScanCoapDevicesTimer: onFinish");
        }
    };
    private Handler r = new Handler() { // from class: csm.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                cpw.a(false, "CombinationTask", "mHandler msg is null");
                return;
            }
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    csm.this.h();
                    return;
                }
                if (i == 3) {
                    csm.this.g();
                    return;
                }
                if (i != 2207) {
                    switch (i) {
                        case 5:
                            cpw.a(false, "CombinationTask", "stop send package");
                            if (csm.this.j != null) {
                                csm.this.j.onStatus(2210);
                            }
                            csm.this.pauseTask();
                            break;
                        case 6:
                            cpw.a(false, "CombinationTask", "low speed 2");
                            csm.this.d(2);
                            break;
                        case 7:
                            cpw.a(false, "CombinationTask", "median speed");
                            csm.this.d(1);
                            break;
                        case 8:
                            cpw.a(false, "CombinationTask", "expand mode");
                            csm.this.z = 1;
                            csm.this.ab.b(csm.this.z);
                            break;
                        case 9:
                            csm.this.f();
                            break;
                        default:
                            cpw.a(false, "CombinationTask", "mHandler default");
                            break;
                    }
                    return;
                }
            }
            csm.this.LM_(message);
        }
    };

    public csm(Context context, csr csrVar, BaseCallbackInterface baseCallbackInterface) {
        this.af = null;
        this.o = context;
        this.j = baseCallbackInterface;
        if (csrVar != null) {
            this.p = csrVar.d();
            this.v = csrVar.a();
            this.l = csrVar.e();
            this.ad = csrVar.c();
        }
        this.t = new ctw();
        cpw.c(false, "CombinationTask", "mIpData: ", this.p);
        if (this.p == null) {
            this.p = new int[1];
        }
        this.s = csn.e(this.p);
        this.ab = new d();
        this.ai = new c();
        this.i = new csv();
        this.m = csp.c(this.o);
        if (this.o == null) {
            cpw.c(false, "CombinationTask", "mContext is null");
            this.o = BaseApplication.getContext();
        }
        if (this.o.getSystemService("wifi") instanceof WifiManager) {
            this.af = (WifiManager) this.o.getSystemService("wifi");
        } else {
            LogUtil.h("CombinationTask", "mContext.getSystemService(Context.WIFI_SERVICE) not instanceof WifiManager");
        }
        this.k = cst.a(this.p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        cpw.d(false, "CombinationTask", "TIMEOUT:Device configuration failed, results reported UI ", a());
        b();
        stopTask();
        ctv.e(this.o);
        this.t.a("network failed", String.valueOf(2108));
        if (this.j != null) {
            cpw.e(false, "CombinationTask", "  MULTICAST_ERROR_MULT_REV_TIMEOUT");
            this.j.onFailure(2108);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        cpw.d(false, "CombinationTask", "UDP receive exception, results escalate UI ", a());
        stopTask();
        ctv.e(this.o);
        this.t.a("network failed", String.valueOf(2101));
        if (this.j != null) {
            cpw.e(false, "CombinationTask", "  MULTICAST_ERROR_CONFIG_FAIL");
            this.j.onFailure(2101);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        jdx.b(new Runnable() { // from class: csm.4
            @Override // java.lang.Runnable
            public void run() {
                if (!csm.this.d) {
                    csm.this.d();
                    csm.this.d = true;
                }
                if (Build.VERSION.SDK_INT >= 28 && csm.this.an != null) {
                    if (CommonUtil.bh()) {
                        for (int i : csm.this.an) {
                            csm.this.af.enableNetwork(i, true);
                        }
                    } else {
                        cpw.a(false, "CombinationTask", "Manufacture is not huawei.");
                    }
                }
                csm.this.af.startScan();
            }
        });
        this.r.sendEmptyMessageDelayed(9, 2000L);
    }

    public void e(int i) {
        this.n = i;
    }

    public void c() {
        WifiManager wifiManager = this.af;
        if (wifiManager != null) {
            WifiManager.MulticastLock createMulticastLock = wifiManager.createMulticastLock("health.test");
            this.u = createMulticastLock;
            createMulticastLock.acquire();
            return;
        }
        cpw.a(false, "CombinationTask", "enableMulticast mWifiManager is null");
    }

    public void b() {
        int i = this.z;
        if (i == 1) {
            this.z = 0;
        } else {
            this.z = i + 1;
        }
    }

    private void j() {
        this.r.removeMessages(1);
        this.r.removeMessages(6);
        this.r.removeMessages(7);
        this.r.removeMessages(5);
        this.r.removeMessages(2);
        this.r.removeMessages(8);
        this.r.removeMessages(9);
    }

    public void e() {
        long nanoTime = System.nanoTime();
        this.aa = nanoTime;
        cpw.a(false, "CombinationTask", "startMulticast: start time is ", Long.valueOf(nanoTime));
        a(1);
        int i = this.q;
        if (i == 0) {
            this.r.sendEmptyMessageDelayed(5, OpAnalyticsConstants.H5_LOADING_DELAY);
        } else if (i == 1) {
            this.z = 0;
            this.r.sendEmptyMessageDelayed(8, PreConnectManager.CONNECT_INTERNAL);
            this.r.sendEmptyMessageDelayed(5, 60000L);
        } else {
            this.r.sendEmptyMessageDelayed(5, 60000L);
        }
        this.r.sendEmptyMessageDelayed(2, 90000L);
        d dVar = this.ab;
        if (dVar != null) {
            dVar.b();
        }
        c cVar = this.ai;
        if (cVar != null) {
            cVar.c();
        }
        if ("Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            this.r.sendEmptyMessageDelayed(9, 15000L);
        } else {
            this.r.sendEmptyMessage(9);
        }
        this.w.start();
        this.c = false;
        this.t.e("modeDevAp");
        this.t.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        cta ctaVar = new cta();
        ctaVar.b(ctx.c(8));
        ctaVar.c("1.0");
        this.i.a(ctaVar, new Entity.EntityResponseCallback() { // from class: csm.1
            @Override // com.huawei.health.device.wifi.entity.model.Entity.EntityResponseCallback
            public void onResponse(ctc ctcVar) {
                cth cthVar;
                cpw.a(false, "CombinationTask", "startScanDeviceCoap: onResponse");
                if (ctcVar != null) {
                    if (ctcVar instanceof cth) {
                        cthVar = (cth) ctcVar;
                        cpw.a(false, "CombinationTask", "startScanDeviceCoap: response ", cthVar.toString());
                    } else {
                        cthVar = null;
                    }
                    cpw.a(false, "CombinationTask", "configMode:", Integer.valueOf(csm.this.n));
                    if (csm.this.n != 4 || cthVar == null) {
                        csm.this.d(cthVar, str);
                        return;
                    } else {
                        csm.this.b(cthVar);
                        return;
                    }
                }
                cpw.d(false, "CombinationTask", "startScanDeviceCoap: response is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cth cthVar, String str) {
        ctn a2 = this.m.a(cthVar);
        if (a2.e() != null && !a2.e().isEmpty()) {
            cpw.d(false, "CombinationTask", "startScanDeviceCoap: device has been registered, deviceId is ：", a2.e(), ",Scan device info is", a2);
        }
        if (a2.c() == null) {
            return;
        }
        cpw.c(false, "CombinationTask", "baseurl is ", a2.d(), "SN ", str, " info.getDeviceSn() ", a2.c());
        if (!TextUtils.equals(str, a2.c()) || this.c) {
            return;
        }
        this.c = true;
        this.w.cancel();
        Message obtain = Message.obtain();
        obtain.what = 2207;
        String substring = a2.d().substring(a2.d().indexOf(File.separator + File.separator) + 2, a2.d().lastIndexOf(":"));
        cpw.c(true, "CombinationTask", "startScanDeviceCoap str:", substring);
        obtain.obj = substring;
        this.r.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cth cthVar) {
        cth.a a2 = cthVar.a();
        if (a2 != null) {
            cpw.a(false, "CombinationTask", "startScanDeviceCoap deviceinfo.prodId", a2.b(), "isCoapFindFlag:", Boolean.valueOf(this.c));
            if (a2.b() == null || !this.ad.a(a2.b()) || this.c) {
                return;
            }
            this.c = true;
            this.w.cancel();
            Message obtain = Message.obtain();
            obtain.what = 2207;
            String substring = cthVar.d().substring(cthVar.d().indexOf(File.separator + File.separator) + 2, cthVar.d().lastIndexOf(":"));
            cpw.c(true, "CombinationTask", "startScanDeviceCoap str:", substring);
            obtain.obj = substring;
            this.r.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LM_(Message message) {
        String str;
        this.w.cancel();
        this.c = true;
        try {
            if (!(message.obj instanceof String)) {
                str = "";
            } else {
                str = (String) message.obj;
            }
            cpw.c(true, "CombinationTask", "baseurl is ", str);
            cpw.c(false, "CombinationTask", "baseurl is true", Boolean.valueOf(Patterns.IP_ADDRESS.matcher(str).matches()));
            if (str != null && Patterns.IP_ADDRESS.matcher(str).matches()) {
                stopTask();
                cpw.a(false, "CombinationTask", "isStartFlag: ", Boolean.valueOf(this.h));
                this.t.d("Network Success");
                BaseCallbackInterface baseCallbackInterface = this.j;
                if (baseCallbackInterface != null && this.h) {
                    this.h = false;
                    baseCallbackInterface.onSuccess(str);
                }
            } else {
                stopTask();
                this.t.a("network failed-failed to get URL", String.valueOf(2101));
                if (this.j != null) {
                    this.h = false;
                    cpw.e(false, "CombinationTask", "runAfterGetMessage MULTICAST_ERROR_CONFIG_FAIL");
                    this.j.onFailure(2101);
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            cpw.e(false, "CombinationTask", e2.getMessage());
        }
        cpw.a(false, "CombinationTask", "Total seconds sent ", Integer.valueOf(this.ag), "Unicast，", Integer.valueOf(this.y), "multicast ", Integer.valueOf(this.g), "broadcast");
    }

    public void d(int i) {
        this.x = i;
    }

    public void a(int i) {
        this.q = i;
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void startTask() {
        cpw.a(false, "CombinationTask", "startTask SDK_INT ", Integer.valueOf(Build.VERSION.SDK_INT));
        this.h = true;
        this.b = true;
        this.f = true;
        c();
        e();
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void pauseTask() {
        cpw.a(false, "CombinationTask", "disableMulticast: pauseTask");
        cst.c(this.o);
        d dVar = this.ab;
        if (dVar != null) {
            dVar.d();
        }
        WifiManager.MulticastLock multicastLock = this.u;
        if (multicastLock == null || !multicastLock.isHeld()) {
            return;
        }
        this.u.release();
    }

    @Override // com.huawei.health.device.wifi.control.logic.AccessNetWorkTask
    public void stopTask() {
        cpw.a(false, "CombinationTask", "disableMulticast: stopTask");
        j();
        cst.c(this.o);
        d dVar = this.ab;
        if (dVar != null) {
            dVar.d();
        }
        c cVar = this.ai;
        if (cVar != null) {
            cVar.b();
        }
        WifiManager.MulticastLock multicastLock = this.u;
        if (multicastLock != null && multicastLock.isHeld()) {
            this.u.release();
        }
        this.w.cancel();
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private volatile boolean f11443a;
        boolean d;
        boolean e;

        private d() {
            this.d = false;
            this.e = false;
            this.f11443a = false;
        }

        public void b(int i) {
            if (i == 0) {
                this.d = true;
                this.e = false;
            } else if (i == 1) {
                this.d = false;
                this.e = true;
            } else {
                this.d = true;
                this.e = true;
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
                Method dump skipped, instructions count: 263
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: csm.d.run():void");
        }

        private void e(int i, DatagramSocket datagramSocket) {
            if (i % 30 == 0) {
                csm.this.b(datagramSocket, 4);
            }
        }

        private void e(MulticastSocket multicastSocket, DatagramSocket datagramSocket) {
            if (multicastSocket != null) {
                multicastSocket.close();
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

        public void b() {
            this.f11443a = false;
            ThreadPoolManager.d().d("CombinationTask", this);
        }

        public void d() {
            this.f11443a = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DatagramSocket datagramSocket, int i) {
        d(2, datagramSocket, i, ctx.a("255"), AccessNetWorkTask.DEFAULT_BROADCAST_PORT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MulticastSocket multicastSocket, int i, String str) {
        d(0, multicastSocket, i, str, 58000);
    }

    private void d(int i, DatagramSocket datagramSocket, int i2, String str, int i3) {
        long j;
        long nanoTime = System.nanoTime();
        long j2 = nanoTime - f11439a;
        if (j2 < 0) {
            j2 = 0;
        }
        f11439a = nanoTime;
        try {
            if (i == 0) {
                j = this.ah.length > this.x ? (r11[r9] * 1000000) - j2 : 0L;
                this.y++;
                if (!csn.a(str)) {
                    return;
                }
            } else if (i != 2) {
                j = 0;
            } else {
                j = this.ac.length > this.x ? (r11[r9] * 1000000) - j2 : 0L;
                this.g++;
            }
            if (j > 0 && j < 200000000) {
                f11439a += j;
                Thread.sleep(j / 1000000, (int) (j % 1000000));
            }
            DatagramPacket datagramPacket = new DatagramPacket(e, i2, InetAddress.getByName(str), i3);
            long nanoTime2 = System.nanoTime();
            if (datagramSocket != null) {
                datagramSocket.send(datagramPacket);
            }
            long nanoTime3 = System.nanoTime();
            if (this.b) {
                d(nanoTime3, nanoTime2);
            }
        } catch (IOException e2) {
            cpw.e(false, "CombinationTask", e2.getMessage(), "current Index", Integer.valueOf(i2));
        } catch (InterruptedException e3) {
            cpw.e(false, "CombinationTask", "Unit sleep failed, ", e3.getMessage());
        }
    }

    private void d(long j, long j2) {
        int[] iArr;
        int i;
        long j3 = j - j2;
        if (j3 > 5000000) {
            if (this.f && (i = (iArr = this.ah)[0]) <= 2) {
                iArr[0] = i + 4;
                int[] iArr2 = this.ae;
                iArr2[0] = iArr2[0] + 1;
                int[] iArr3 = this.ac;
                iArr3[0] = iArr3[0] + 4;
            } else if (j3 > 50000000) {
                int[] iArr4 = this.ah;
                iArr4[0] = iArr4[0] + 2;
                int[] iArr5 = this.ae;
                iArr5[0] = iArr5[0] + 1;
                int[] iArr6 = this.ac;
                iArr6[0] = iArr6[0] + 2;
            } else {
                int[] iArr7 = this.ah;
                iArr7[0] = iArr7[0] + 1;
                int[] iArr8 = this.ac;
                iArr8[0] = iArr8[0] + 1;
            }
            int[] iArr9 = this.ah;
            if (iArr9[0] < 10 && j - this.aa >= RealConnection.IDLE_CONNECTION_HEALTHY_NS) {
                iArr9[0] = 10;
                this.ae[0] = 10;
                this.ac[0] = 10;
            }
            if (iArr9[0] > 200) {
                iArr9[0] = 200;
                this.ae[0] = 200;
                this.ac[0] = 200;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DatagramSocket datagramSocket, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            c(datagramSocket, 8);
            c(datagramSocket, 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MulticastSocket multicastSocket, int i) {
        String str = this.v + "127.141";
        for (int i2 = 0; i2 < i; i2++) {
            c(multicastSocket, 304, str);
        }
    }

    class c implements Runnable {
        private volatile boolean d;
        DatagramSocket e;

        private c() {
            this.e = null;
            this.d = false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0075, code lost:
        
            if (r7 == null) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0077, code lost:
        
            r7 = r7.toString().replaceAll("/", "");
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0086, code lost:
        
            defpackage.cpw.c(true, "CombinationTask", "UdpServer: ipClientAddress is ", r7);
            defpackage.cpw.a(false, "CombinationTask", "UdpServer get message: ", r7);
            r8 = android.os.Message.obtain();
            r8.what = 1;
            r8.obj = r7;
            r12.f11442a.r.sendMessage(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0084, code lost:
        
            r7 = "0.0.0.0";
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 276
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: csm.c.run():void");
        }

        public void c() {
            if (csm.this.ai != null) {
                csm.this.ai.d = false;
                jdx.b(csm.this.ai);
            } else {
                cpw.e(false, "CombinationTask", "UdpServer: start() mUdpServer is null");
            }
        }

        public void b() {
            if (csm.this.ai != null) {
                csm.this.ai.d = true;
                if (csm.this.ai.e == null) {
                    return;
                }
                cpw.c(false, "CombinationTask", "mUdpServer: is close ", Boolean.valueOf(csm.this.ai.e.isClosed()));
                csm.this.ai.e.close();
                cpw.e(false, "CombinationTask", "UdpServer: stop() serverSocket closed");
                return;
            }
            cpw.e(false, "CombinationTask", "UdpServer: stop() mUdpServer is null");
        }
    }

    private String a() {
        int i = this.ag / 500;
        if (i > 99) {
            i = 99;
        }
        int i2 = this.y / 500;
        if (i2 > 99) {
            i2 = 99;
        }
        int i3 = this.g / 500;
        int i4 = i3 <= 99 ? i3 : 99;
        String format = String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i));
        String format2 = String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i2));
        String format3 = String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i4));
        String format4 = String.format(Locale.ENGLISH, "-%03d", Integer.valueOf(this.ah[0]));
        StringBuilder sb = new StringBuilder(16);
        sb.append(this.z);
        sb.append(format);
        sb.append(format2);
        sb.append(format3);
        sb.append(format4);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int[][] iArr = this.k;
        if (iArr != null) {
            if (iArr.length > 0) {
                this.an = new int[iArr.length];
            }
            int i = 0;
            while (true) {
                int[][] iArr2 = this.k;
                if (i >= iArr2.length) {
                    return;
                }
                int addNetwork = this.af.addNetwork(cst.LW_(new String(ctv.d(iArr2[i]), Charset.forName("UTF-8"))));
                this.an[i] = addNetwork;
                this.af.saveConfiguration();
                cpw.a(false, "CombinationTask", "addNetwork ", Integer.valueOf(addNetwork), " sdk api ", Integer.valueOf(Build.VERSION.SDK_INT));
                if (Build.VERSION.SDK_INT < 28) {
                    this.af.enableNetwork(addNetwork, false);
                }
                i++;
            }
        } else {
            cpw.a(false, "CombinationTask", "mConfigMsg is null");
        }
    }
}
