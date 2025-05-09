package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.wifi.control.logic.AccessNetWorkTask;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class csl {

    /* renamed from: a, reason: collision with root package name */
    private csu f11437a;
    private byte[] b;
    private BaseCallbackInterface d;
    private String f;
    private volatile int g;
    private b i = null;
    private a h = null;
    private Context e = null;
    private Handler c = new Handler() { // from class: csl.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                cpw.a(false, "AddSoftApDeviceTask", "msg is null");
                return;
            }
            cpw.a(false, "AddSoftApDeviceTask", "mDevSoftApTaskHandler msg.what:", Integer.valueOf(message.what));
            super.handleMessage(message);
            int i = message.what;
            if (i != 0) {
                if (i == 1 || i == 2) {
                    csl.this.c();
                    return;
                } else if (i == 3) {
                    csl.this.c();
                    csl.this.d.onSuccess("send data success");
                    return;
                } else {
                    cpw.e(false, "AddSoftApDeviceTask", "mDevSoftApTaskHandler default");
                    return;
                }
            }
            if (csl.this.i == null) {
                csl cslVar = csl.this;
                cslVar.i = new b();
            }
            if (csl.this.h == null) {
                csl cslVar2 = csl.this;
                cslVar2.h = new a();
            }
            csl.this.h.e();
            csl.this.i.c();
            csl.this.c.sendEmptyMessageDelayed(1, 55000L);
        }
    };

    public csl(csu csuVar, String str, BaseCallbackInterface baseCallbackInterface) {
        this.g = 0;
        this.d = baseCallbackInterface;
        this.f11437a = csuVar;
        this.f = str;
        this.g = 0;
    }

    public void a(Context context) {
        cpw.c(false, "AddSoftApDeviceTask", "startDeviceSoftApTask enter:", Integer.valueOf(this.g));
        this.e = context;
        if (this.g == 0) {
            if (Executors.newSingleThreadExecutor().submit(new c()).isDone()) {
                cpw.a(false, "AddSoftApDeviceTask", "submit isDone");
                this.g = 1;
                return;
            }
            return;
        }
        cpw.c(false, "AddSoftApDeviceTask", "softAp task is doing");
    }

    public void d() {
        cpw.c(false, "AddSoftApDeviceTask", "stopDeviceSoftApTask enter");
        b bVar = this.i;
        if (bVar != null) {
            bVar.e();
        }
        this.c.removeMessages(1);
        this.c.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        b bVar = this.i;
        if (bVar != null) {
            bVar.e();
            this.i = null;
        }
        a aVar = this.h;
        if (aVar != null) {
            aVar.c();
            this.h = null;
        }
        this.c.removeCallbacksAndMessages(null);
        this.g = 0;
    }

    class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            byte[] bArr;
            int length;
            cpw.a(false, "AddSoftApDeviceTask", "AssembleDataPackage enter");
            byte[] a2 = ctx.a();
            String d = ctx.d();
            cpw.a(true, "AddSoftApDeviceTask", "AssembleDataPackage aes128Key  ", cpw.d(d));
            byte[] d2 = csl.d(csl.this.f11437a.h());
            byte[] b = ctu.b(d2, d, a2);
            if (TextUtils.isEmpty(csl.this.f)) {
                cpw.a(false, "AddSoftApDeviceTask", "AssembleDataPackage mRegMessage is null");
                bArr = null;
                length = 0;
            } else {
                byte[] b2 = ctu.b(csl.d(csl.this.f), d, a2);
                bArr = b2;
                length = b2.length;
            }
            b(b, d, d2, bArr, length);
            cpw.c(true, "AddSoftApDeviceTask", "AssembleDataPackage mDataPakage:", ctv.a(csl.this.b));
            csl.this.c.sendEmptyMessage(0);
            cpw.c(false, "AddSoftApDeviceTask", "AssembleDataPackage leave");
        }

        private void b(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, int i) {
            int i2;
            byte[] a2 = csl.this.a(bArr);
            byte[] a3 = ctv.a(csl.this.f11437a.b(), a2, str);
            byte[] d = csl.d(csl.this.f11437a.i());
            csl cslVar = csl.this;
            String e = cslVar.e(cslVar.f11437a.e());
            byte[] d2 = csl.d(e);
            boolean z = a3 == null || d2 == null;
            boolean z2 = d == null || bArr == null;
            if (z || z2 || a3.length != 16) {
                csl.this.d.onFailure(ExceptionCode.CHECK_FILE_SIZE_FAILED);
                cpw.e(false, "AddSoftApDeviceTask", "encrypt data length is not correct");
                return;
            }
            if (bArr2 != null) {
                cpw.c(true, "AddSoftApDeviceTask", "AssembleDataPackage pwdArray len:", Integer.valueOf(bArr2.length));
            }
            cpw.c(false, "AddSoftApDeviceTask", "AssembleDataPackage ssid: ", cpw.d(csl.this.f11437a.i()), ";AssembleDataPackage deviceSsid: ", cpw.d(csl.this.f11437a.e()), ";AssembleDataPackage encodedPwd: len:", Integer.valueOf(bArr.length), ";AssembleDataPackage huaweiKeyIv: len:", Integer.valueOf(a2.length), ";AssembleDataPackage encodedKey: len:", Integer.valueOf(a3.length), ";AssembleDataPackage ssidArray: len:", Integer.valueOf(d.length), ";AssembleDataPackage info:", e);
            if (bArr3 != null) {
                i2 = 1;
                cpw.c(true, "AddSoftApDeviceTask", "AssembleDataPackage regMsg: ", csl.this.f, ";AssembleDataPackage endcodeRegMsg: len:", Integer.valueOf(bArr3.length));
            } else {
                i2 = 1;
            }
            int length = d.length + 20 + bArr.length + i2 + i;
            cpw.a(false, "AddSoftApDeviceTask", "AssembleDataPackage dataPakageLen: ", Integer.valueOf(length));
            if ((length & 1) == i2) {
                csl.this.b = new byte[length + 1];
                csl.this.b[length] = 0;
            } else {
                csl.this.b = new byte[length];
            }
            csl.this.b(d, bArr, d2, bArr3, a3);
            csl.this.b[i2] = csl.b(csl.this.b, 2, length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        int i = 0;
        this.b[0] = (byte) (ctx.c(bArr3) & 255);
        this.b[2] = b(this.f11437a);
        int i2 = 3;
        int i3 = 0;
        while (i3 < 16) {
            this.b[i2] = bArr5[i3];
            i3++;
            i2++;
        }
        int i4 = i2 + 1;
        this.b[i2] = (byte) (ctx.a(bArr.length, bArr2.length) & 255);
        int i5 = 0;
        while (i5 < bArr.length) {
            this.b[i4] = bArr[i5];
            i5++;
            i4++;
        }
        int i6 = 0;
        while (i6 < bArr2.length) {
            this.b[i4] = bArr2[i6];
            i6++;
            i4++;
        }
        if (bArr4 != null) {
            int i7 = i4 + 1;
            this.b[i4] = (byte) ((bArr4.length >> 4) & 255);
            while (i < bArr4.length) {
                this.b[i7] = bArr4[i];
                i++;
                i7++;
            }
            return;
        }
        this.b[i4] = 0;
    }

    public static byte[] d(String str) {
        if (str != null) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                cpw.e(false, "AddSoftApDeviceTask", "UnsupportedEncodingException e:", e.getMessage());
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        if (bArr != null && bArr.length >= 16) {
            for (int i = 0; i < 16; i++) {
                bArr2[i] = bArr[i];
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str) {
        String[] split = str.split(Constants.LINK);
        return split.length < 4 ? "" : split[3].substring(1, 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte b(byte[] bArr, int i, int i2) {
        int i3 = 255;
        while (i < i2) {
            i3 ^= bArr[i] & 255;
            for (int i4 = 0; i4 < 8; i4++) {
                int i5 = i3 & 1;
                i3 >>= 1;
                if (i5 != 0) {
                    i3 ^= 184;
                }
            }
            i++;
        }
        return (byte) (i3 & 255);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:
    
        if (r5 != 110) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte b(defpackage.csu r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.e()
            java.lang.String r1 = "-"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r2 = 0
            r3 = 3
            if (r1 <= r3) goto L14
            r0 = r0[r3]
            r0.charAt(r2)
        L14:
            int r0 = r5.b()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "AssembleDataPackageget VersionMode: "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "AddSoftApDeviceTask"
            defpackage.cpw.a(r2, r1, r0)
            int r5 = r5.b()
            r0 = 100
            if (r5 == r0) goto L3a
            r0 = 101(0x65, float:1.42E-43)
            if (r5 == r0) goto L38
            r0 = 110(0x6e, float:1.54E-43)
            if (r5 == r0) goto L3b
            goto L3a
        L38:
            r3 = 2
            goto L3b
        L3a:
            r3 = 1
        L3b:
            int r5 = r3 << 4
            byte r5 = (byte) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.csl.b(csu):byte");
    }

    class b implements Runnable {
        private volatile boolean d;

        private b() {
            this.d = false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object[]] */
        @Override // java.lang.Runnable
        public void run() {
            Throwable th;
            DatagramSocket datagramSocket;
            cpw.a(false, "AddSoftApDeviceTask", "SendThread run");
            ?? r0 = 0;
            DatagramSocket datagramSocket2 = null;
            try {
                try {
                    datagramSocket = new DatagramSocket();
                } catch (Throwable th2) {
                    DatagramSocket datagramSocket3 = r0;
                    th = th2;
                    datagramSocket = datagramSocket3;
                }
            } catch (IOException unused) {
            }
            try {
                datagramSocket.setSendBufferSize(1024);
                DatagramPacket datagramPacket = new DatagramPacket(csl.this.b, csl.this.b.length, InetAddress.getByName(ctx.a("255")), 5683);
                Object systemService = csl.this.e.getSystemService("connectivity");
                Object systemService2 = csl.this.e.getSystemService("wifi");
                if ((systemService instanceof ConnectivityManager) && (systemService2 instanceof WifiManager)) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                    WifiManager wifiManager = (WifiManager) systemService2;
                    while (!this.d) {
                        LO_(connectivityManager, wifiManager, false, datagramSocket, datagramPacket);
                    }
                }
                datagramSocket.close();
            } catch (IOException unused2) {
                datagramSocket2 = datagramSocket;
                cpw.e(false, "AddSoftApDeviceTask", "Create socket failed ");
                if (datagramSocket2 != null) {
                    datagramSocket2.close();
                }
                r0 = new Object[]{"SendThread send package data thread out"};
                cpw.c(false, "AddSoftApDeviceTask", r0);
            } catch (Throwable th3) {
                th = th3;
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                throw th;
            }
            r0 = new Object[]{"SendThread send package data thread out"};
            cpw.c(false, "AddSoftApDeviceTask", r0);
        }

        private void LO_(ConnectivityManager connectivityManager, WifiManager wifiManager, boolean z, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
            DhcpInfo dhcpInfo;
            NetworkInfo activeNetworkInfo;
            try {
                if (!cum.c() && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                    cum.MT_(wifiManager);
                }
                if (z) {
                    Thread.sleep(200L);
                } else {
                    NetworkInfo activeNetworkInfo2 = connectivityManager.getActiveNetworkInfo();
                    boolean z2 = activeNetworkInfo2 != null && activeNetworkInfo2.isConnected();
                    if (cum.c() && z2 && (dhcpInfo = wifiManager.getDhcpInfo()) != null && dhcpInfo.gateway != 0) {
                        cpw.a(true, "AddSoftApDeviceTask", "SendThread Ip:", Integer.valueOf(dhcpInfo.gateway));
                    }
                    Thread.sleep(60L);
                }
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                cpw.e(false, "AddSoftApDeviceTask", "SendThread socket failed, ", e.getMessage());
            } catch (InterruptedException e2) {
                cpw.e(false, "AddSoftApDeviceTask", "SendThread socket failed, ", e2.getMessage());
            }
            cpw.e(false, "AddSoftApDeviceTask", "SendThread send package data onetime");
        }

        public void c() {
            this.d = false;
            if (Executors.newSingleThreadExecutor().submit(csl.this.i).isDone()) {
                cpw.a(false, "AddSoftApDeviceTask", "submit isDone");
            }
            cpw.a(false, "AddSoftApDeviceTask", "send package data thread start");
        }

        public void e() {
            cpw.a(false, "AddSoftApDeviceTask", "send package data thread stop");
            this.d = true;
        }
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        DatagramSocket f11438a;
        private volatile boolean d;

        private a() {
            this.f11438a = null;
            this.d = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            cpw.a(false, "AddSoftApDeviceTask", "receive run enter");
            try {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(AccessNetWorkTask.DEFAULT_UDOREV_PORT);
                    this.f11438a = datagramSocket;
                    datagramSocket.setReuseAddress(true);
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[256], 256);
                    cpw.a(false, "AddSoftApDeviceTask", " receive : new DatagramSocket ");
                    while (!this.d) {
                        try {
                            this.f11438a.setSoTimeout(1000);
                            this.f11438a.receive(datagramPacket);
                        } catch (SocketTimeoutException e) {
                            cpw.e(false, "AddSoftApDeviceTask", "receive socket timeout, ", e.getMessage());
                        } catch (IOException e2) {
                            cpw.e(false, "AddSoftApDeviceTask", "receive socket failed, ", e2.getMessage());
                            try {
                                Thread.sleep(20L);
                            } catch (InterruptedException e3) {
                                cpw.e(false, "AddSoftApDeviceTask", "receive socket sleep interrupt, ", e3.getMessage());
                            }
                        }
                        String b = b(datagramPacket);
                        if (!"HI-ACK".equals(b)) {
                            cpw.e(false, "AddSoftApDeviceTask", "Receiver something but not ACK readMsg:", b);
                        } else {
                            InetAddress address = datagramPacket.getAddress();
                            if (address == null) {
                                str = "0.0.0.0";
                            } else {
                                str = address.toString().replaceAll("/", "");
                            }
                            cpw.a(false, "AddSoftApDeviceTask", "Receiver: success ipClientAddress:", cpw.d(str));
                            csl.this.c.sendEmptyMessage(3);
                        }
                    }
                    DatagramSocket datagramSocket2 = this.f11438a;
                    if (datagramSocket2 != null) {
                        datagramSocket2.disconnect();
                        this.f11438a.close();
                    }
                    cpw.c(false, "AddSoftApDeviceTask", "Receiver: finally close resource");
                } catch (SocketException e4) {
                    cpw.e(false, "AddSoftApDeviceTask", "Receiver: ", e4.getMessage());
                    DatagramSocket datagramSocket3 = this.f11438a;
                    if (datagramSocket3 != null) {
                        datagramSocket3.disconnect();
                        this.f11438a.close();
                    }
                    cpw.c(false, "AddSoftApDeviceTask", "Receiver: finally close resource");
                }
                cpw.a(false, "AddSoftApDeviceTask", "Receiver run leave");
            } catch (Throwable th) {
                DatagramSocket datagramSocket4 = this.f11438a;
                if (datagramSocket4 != null) {
                    datagramSocket4.disconnect();
                    this.f11438a.close();
                }
                cpw.c(false, "AddSoftApDeviceTask", "Receiver: finally close resource");
                throw th;
            }
        }

        private String b(DatagramPacket datagramPacket) {
            cpw.c(false, "AddSoftApDeviceTask", "Receiver offset:", Integer.valueOf(datagramPacket.getOffset()), " len:", Integer.valueOf(datagramPacket.getLength()));
            if (datagramPacket.getOffset() >= 0 && datagramPacket.getLength() > 0 && datagramPacket.getOffset() + datagramPacket.getLength() < 256) {
                return new String(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength(), Charset.forName("UTF-8"));
            }
            cpw.e(false, "AddSoftApDeviceTask", "Receiver offset:", Integer.valueOf(datagramPacket.getOffset()), " len:", Integer.valueOf(datagramPacket.getLength()));
            return null;
        }

        public void e() {
            this.d = false;
            jdx.b(csl.this.h);
            cpw.c(false, "AddSoftApDeviceTask", "mReceiveAckThread: start");
        }

        public void c() {
            cpw.c(false, "AddSoftApDeviceTask", "mReceiveAckThread: stoped!");
            this.d = true;
        }
    }
}
