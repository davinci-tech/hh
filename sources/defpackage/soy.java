package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener;
import defpackage.spn;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes7.dex */
public class soy {
    public static void b(spa spaVar) {
        HwWifiP2pTransferManager.d().r();
        soz i = HwWifiP2pTransferManager.d().i();
        if (i != null) {
            LogUtil.c("WifiP2pTransferCommand", "set wait 2");
            i.o(2);
        }
        if (spaVar == null) {
            HwWifiP2pTransferManager.d().d(1008, "wifi go info wrong");
            LogUtil.a("WifiP2pTransferCommand", "sendWifiInfoToDevice, wifiP2pGoInfo is null");
            return;
        }
        byte[] f = f(spaVar.d());
        byte[] h = h(spaVar.e());
        byte[] b = b();
        byte[] e = e();
        byte[] c = c();
        byte[] a2 = a();
        byte[] f2 = f(spaVar.c());
        int length = f.length;
        int length2 = h.length;
        int length3 = e.length;
        int length4 = c.length;
        int length5 = a2.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 1 + length2 + length3 + length4 + length5 + b.length + f2.length);
        allocate.put((byte) 1).put(f).put(h).put(b).put(e).put(c).put(a2).put(f2);
        blt.d("WifiP2pTransferCommand", allocate.array(), "5.54.1 send ");
        spn e2 = e(allocate);
        if (e2 == null) {
            LogUtil.e("WifiP2pTransferCommand", "sendWifiInfoToDevice, sendMessage is null");
            HwWifiP2pTransferManager.d().d(1009, "wrap 5.54.1 info wrong, please check.");
        } else {
            HwWifiP2pTransferManager.d().y();
            HwWifiP2pTransferManager.d().a(e2);
        }
    }

    private static spn e(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            LogUtil.a("WifiP2pTransferCommand", "createMessage messageByteBuffer is null");
            return null;
        }
        spn.b bVar = new spn.b();
        bVar.c(byteBuffer.array());
        return bVar.e();
    }

    public static void b(byte[] bArr) {
        if (bArr == null) {
            LogUtil.a("WifiP2pTransferCommand", "handleDeviceCommonResponseCode dataInfos is null");
            return;
        }
        bmj e = spe.e(bArr);
        if (e == null) {
            return;
        }
        Iterator<bmi> it = e.b().iterator();
        while (it.hasNext()) {
            LogUtil.c("WifiP2pTransferCommand", "handleDeviceCommonResponseCode value is: ", Integer.valueOf(bli.e(it.next().c())));
        }
    }

    public static void d(byte[] bArr) {
        HwWifiP2pTransferManager.d().r();
        if (d()) {
            return;
        }
        if (bArr == null) {
            LogUtil.a("WifiP2pTransferCommand", "handleDeviceReportP2pConnectResult dataInfos is null");
            HwWifiP2pTransferManager.d().d(1005, "5.54.2 data is null.");
            return;
        }
        bmj e = spe.e(bArr);
        if (e == null) {
            return;
        }
        int i = 1;
        for (bmi bmiVar : e.b()) {
            if (bli.e(bmiVar.e()) == 1) {
                i = bli.e(bmiVar.c());
            }
        }
        if (i == 0) {
            LogUtil.c("WifiP2pTransferCommand", "handleDeviceReportP2pConnectResult createServerSocket");
            int a2 = HwWifiP2pTransferManager.d().a();
            ThreadPoolManager.d().execute(new Runnable() { // from class: soy.1
                @Override // java.lang.Runnable
                public void run() {
                    HwWifiP2pTransferManager.d().b();
                }
            });
            o(a2);
            HwWifiP2pTransferManager.d().e(true);
            return;
        }
        iyv.f();
        iyv.c("WifiP2PTransfer", "Device replies 5.54.2, wifi connect failed.");
        HwWifiP2pTransferManager.d().d(1006, "5.54.2 device connect fail");
    }

    private static boolean d() {
        if (HwWifiP2pTransferManager.d().g() == null || TransferFileQueueManager.d().c("bt_change_wifi_check_cancel") != null) {
            return false;
        }
        HwWifiP2pTransferManager.d().d((HwWifiP2pTransferManager.TransferBleToWifiCallback) null);
        HwWifiP2pTransferManager.d().e();
        HwWifiP2pTransferManager.d().d("no bt task");
        return true;
    }

    public static void a(soz sozVar) {
        ByteBuffer allocate;
        if (sozVar == null) {
            return;
        }
        LogUtil.c("WifiP2pTransferCommand", "set wait 5");
        sozVar.o(5);
        byte[] d = d(sozVar.i());
        byte[] i = i(sozVar.o());
        byte[] a2 = a((int) sozVar.f());
        byte[] a3 = a(sozVar.c());
        if (sozVar.o() == 7) {
            allocate = e(sozVar, d, i, a2, a3);
        } else if (sozVar.o() == 1) {
            byte[] p = p(sozVar.i());
            byte[] t = t(sozVar.i());
            int length = d.length;
            int length2 = a2.length;
            int length3 = a3.length;
            int length4 = i.length;
            ByteBuffer allocate2 = ByteBuffer.allocate(length + 1 + length2 + length3 + length4 + p.length + t.length);
            allocate2.put((byte) 5).put(d).put(a2).put(i).put(a3).put(p).put(t);
            allocate = allocate2;
        } else {
            int length5 = d.length;
            int length6 = a2.length;
            allocate = ByteBuffer.allocate(length5 + 1 + length6 + a3.length + i.length);
            allocate.put((byte) 5).put(d).put(a2).put(i).put(a3);
        }
        d(allocate);
    }

    public static void e(soz sozVar) {
        if (sozVar == null) {
            return;
        }
        byte[] d = d(sozVar.i());
        byte[] i = i(sozVar.o());
        ByteBuffer allocate = ByteBuffer.allocate(d.length + 1 + i.length);
        allocate.put((byte) 10).put(d).put(i);
        d(allocate);
    }

    private static ByteBuffer e(soz sozVar, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] b = b(sozVar.g());
        byte[] g = g(sozVar.w());
        byte[] e = e(sozVar.a());
        byte[] j = j(sozVar.v());
        byte[] c = c(sozVar.d());
        int length = bArr.length;
        int length2 = bArr3.length;
        int length3 = bArr4.length;
        int length4 = bArr2.length;
        int length5 = b.length;
        int length6 = g.length;
        int length7 = e.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 1 + length2 + length3 + length4 + length5 + length6 + length7 + j.length + c.length);
        allocate.put((byte) 5).put(bArr).put(bArr3).put(bArr2).put(bArr4).put(b).put(g).put(e).put(j).put(c);
        return allocate;
    }

    private static void d(ByteBuffer byteBuffer) {
        blt.d("WifiP2pTransferCommand", byteBuffer.array(), "5.54.5 content is: ");
        spn e = e(byteBuffer);
        if (e == null) {
            LogUtil.a("WifiP2pTransferCommand", "sendNotifyDevice, sendMessage is null");
        } else {
            HwWifiP2pTransferManager.d().y();
            HwWifiP2pTransferManager.d().a(e);
        }
    }

    private static byte[] b(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = BaseType.Float;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] g(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = BaseType.Double;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] e(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 13;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] j(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = BaseType.Vector;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] c(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = BaseType.Obj;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] d(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] i(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 2;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] a(int i) {
        byte[] i2 = blq.i(i);
        byte[] d = blq.d(i2.length);
        byte[] bArr = new byte[i2.length + 1 + d.length];
        bArr[0] = 3;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(i2, 0, bArr, d.length + 1, i2.length);
        return bArr;
    }

    private static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        byte[] a2 = blq.a(str);
        byte[] d = blq.d(a2.length);
        byte[] bArr = new byte[a2.length + 1 + d.length];
        bArr[0] = 4;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(a2, 0, bArr, d.length + 1, a2.length);
        return bArr;
    }

    private static byte[] p(String str) {
        String[] split = str.split("_");
        if (split.length != 2) {
            return null;
        }
        byte[] c = blq.c(split[0]);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 7;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] t(String str) {
        String[] split = str.split("_");
        if (split.length != 2) {
            return null;
        }
        byte[] c = blq.c(split[1]);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 8;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    public static void c(byte[] bArr) {
        HwWifiP2pTransferManager.d().r();
        if (HwWifiP2pTransferManager.d().i() == null) {
            LogUtil.a("WifiP2pTransferCommand", "fileInfo is null, please check");
            return;
        }
        if (bArr == null) {
            LogUtil.e("WifiP2pTransferCommand", "handleGetTransferFileInfoResponse dataInfos is null");
            HwWifiP2pTransferManager.d().d(1007, "5.54.10 is null");
            return;
        }
        bmj e = spe.e(bArr);
        if (e == null) {
            return;
        }
        int i = 100000;
        String str = "";
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (bmi bmiVar : e.b()) {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 1) {
                String[] split = blq.d(bmiVar.c()).split("_");
                str = split[split.length - 1];
            } else if (e2 == 3) {
                i2 = bli.e(bmiVar.c());
            } else if (e2 == 5) {
                i3 = bli.e(bmiVar.c());
            } else if (e2 == 10) {
                i4 = bli.e(bmiVar.c());
            } else if (e2 == 127) {
                i = bli.e(bmiVar.c());
            } else {
                LogUtil.a("WifiP2pTransferCommand", "unknown type : ", Integer.valueOf(e2));
            }
        }
        LogUtil.c("WifiP2pTransferCommand", "raw file size:" + i2 + " code: " + i + " fileId: " + i3 + " socketBufferSize:" + i4 + "crc: " + str);
        c(i2, i, i3, i4, str);
    }

    private static void c(int i, int i2, int i3, int i4, String str) {
        WifiP2pTransferListener k;
        if (i2 != 100000 || i <= 0) {
            soz h = HwWifiP2pTransferManager.d().h();
            if (h == null || (k = h.k()) == null) {
                return;
            }
            LogUtil.c("WifiP2pTransferCommand", "downloadFileInfoCallback get error,");
            k.onFail(i2, "downloadFileInfoCallback get error", h.o());
            return;
        }
        soz i5 = HwWifiP2pTransferManager.d().i();
        i5.b(i);
        i5.b(i3);
        i5.d(str);
        i5.d(i4);
        c(i5);
    }

    private static void c(soz sozVar) {
        byte[] d = d(sozVar.i());
        byte[] a2 = a((int) sozVar.f());
        byte[] i = i(sozVar.o());
        int length = d.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 1 + a2.length + i.length);
        allocate.put(BaseType.Float).put(d).put(i).put(a2);
        sozVar.h(2);
        LogUtil.c("WifiP2pTransferCommand", "start file : ", sozVar, "file path : ", sozVar.j(), " tag : ", Integer.valueOf(sozVar.y()));
        HwWifiP2pTransferManager.d().a(sozVar);
        d(allocate);
    }

    public static void a(byte[] bArr) {
        HwWifiP2pTransferManager.d().r();
        if (HwWifiP2pTransferManager.d().i() == null) {
            LogUtil.a("WifiP2pTransferCommand", "fileInfo is null, please check");
            return;
        }
        if (bArr == null) {
            LogUtil.e("WifiP2pTransferCommand", "handleNotifyTransferFileInfoResponse dataInfos is null");
            HwWifiP2pTransferManager.d().d(1007, "5.54.5 is null");
            return;
        }
        bmj e = spe.e(bArr);
        if (e == null) {
            return;
        }
        long j = 0;
        int i = 100000;
        int i2 = 0;
        int i3 = 0;
        for (bmi bmiVar : e.b()) {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 5) {
                i2 = bli.e(bmiVar.c());
            } else if (e2 == 6) {
                j = bli.c(bmiVar.c());
            } else if (e2 == 10) {
                i3 = bli.e(bmiVar.c());
            } else if (e2 == 127) {
                i = bli.e(bmiVar.c());
            } else {
                LogUtil.a("WifiP2pTransferCommand", "unknown type : ", Integer.valueOf(e2));
            }
        }
        a(i2, (int) j, i3, i);
    }

    private static void a(int i, int i2, int i3, int i4) {
        WifiP2pTransferListener k;
        if (i4 == 100005) {
            soz h = HwWifiP2pTransferManager.d().h();
            if (h == null || (k = h.k()) == null) {
                return;
            }
            LogUtil.c("WifiP2pTransferCommand", "device wi-fi send file.");
            k.onFail(1007, "device is using wi-fi", h.o());
            return;
        }
        HwWifiP2pTransferManager d = HwWifiP2pTransferManager.d();
        soz i5 = d.i();
        if (i5 == null) {
            HwWifiP2pTransferManager.d().e(1024, "5.54.5 unknown error, file info is empty.", i);
            return;
        }
        if (d.b(i, i5.e())) {
            if (d.i() != null) {
                LogUtil.c("WifiP2pTransferCommand", "5.54.5 rec");
                soz i6 = d.i();
                if (i2 > i6.f()) {
                    HwWifiP2pTransferManager.d().e(1024, "5.54.5 offset bigger than file size, wrong.", i);
                    return;
                }
                i6.b(i);
                i6.e(i2);
                i6.h(2);
                i6.d(i3);
                LogUtil.c("WifiP2pTransferCommand", "start file : ", i6, "file path : ", i6.j(), " tag : ", Integer.valueOf(i6.y()));
                d.a(i6);
                return;
            }
            HwWifiP2pTransferManager.d().e(1024, "5.54.5 unknown error, file info is empty.", i);
            return;
        }
        LogUtil.e("WifiP2pTransferCommand", "no support file : ", Integer.valueOf(i));
        HwWifiP2pTransferManager.d().e(1023, "5.54.5 device file type is wrong, capability not support : " + i, i);
    }

    public static void b(int i, int i2) {
        HwWifiP2pTransferManager.d().r();
        byte[] j = j(i2);
        byte[] c = c(i);
        ByteBuffer allocate = ByteBuffer.allocate(j.length + 1 + c.length);
        allocate.put((byte) 6).put(j).put(c);
        spn e = e(allocate);
        if (e == null) {
            LogUtil.e("WifiP2pTransferCommand", "notifyDeviceCancelTransfer, sendMessage is null");
            return;
        }
        blt.d("WifiP2pTransferCommand", allocate.array(), "5.54.6 send : ");
        HwWifiP2pTransferManager.d().y();
        HwWifiP2pTransferManager.d().a(e);
    }

    private static byte[] j(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] c(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 2;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] e(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    public static void d(int i) {
        byte[] e = e(i);
        ByteBuffer allocate = ByteBuffer.allocate(e.length + 1);
        allocate.put((byte) 7);
        allocate.put(e);
        blt.d("WifiP2pTransferCommand", allocate.array(), "5.54.7 content is:");
        spn e2 = e(allocate);
        if (e2 == null) {
            LogUtil.e("WifiP2pTransferCommand", "notifyDeviceFileTransferFinish, sendMessage is null");
        } else {
            HwWifiP2pTransferManager.d().a(e2);
        }
    }

    public static void b(int i) {
        byte[] j = j(i);
        ByteBuffer allocate = ByteBuffer.allocate(j.length + 1);
        allocate.put((byte) 8);
        allocate.put(j);
        spn e = e(allocate);
        if (e == null) {
            LogUtil.e("WifiP2pTransferCommand", "notifyDeviceStopWifiP2p, sendMessage is null");
        } else {
            LogUtil.c("WifiP2pTransferCommand", "5.54.8 send");
            HwWifiP2pTransferManager.d().a(e);
        }
    }

    public static void e(byte[] bArr) {
        HwWifiP2pTransferManager.d().r();
        if (d()) {
            return;
        }
        if (bArr == null) {
            LogUtil.e("WifiP2pTransferCommand", "handleDeviceReportSocketConnectResult, dataInfos is null");
            HwWifiP2pTransferManager.d().d(1025, "5.54.4 device info is null");
            return;
        }
        bmj e = spe.e(bArr);
        if (e == null) {
            return;
        }
        Iterator<bmi> it = e.b().iterator();
        int i = 1;
        while (it.hasNext()) {
            i = bli.e(it.next().c());
        }
        soz i2 = HwWifiP2pTransferManager.d().i();
        if (i2 == null) {
            LogUtil.a("WifiP2pTransferCommand", "5.54.4 file info is null, please check.");
            return;
        }
        int m = i2.m();
        LogUtil.c("WifiP2pTransferCommand", "5.54.4 value : ", Integer.valueOf(i), "model : ", Integer.valueOf(m));
        d(i, m, i2);
    }

    private static void d(int i, final int i2, final soz sozVar) {
        if (i == 0) {
            if (spc.d().a() == null) {
                LogUtil.c("WifiP2pTransferCommand", "check at once, no socket.");
                new Timer("adapterValue").schedule(new TimerTask() { // from class: soy.5
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (spc.d().a() != null) {
                            soy.e(0, i2, sozVar);
                        } else {
                            LogUtil.c("WifiP2pTransferCommand", "check socket, no socket, link fail");
                            soy.e(1, i2, sozVar);
                        }
                    }
                }, 2000L);
                return;
            } else {
                e(i, i2, sozVar);
                return;
            }
        }
        e(i, i2, sozVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(int i, int i2, soz sozVar) {
        if (i == 0) {
            a(i2, sozVar);
            return;
        }
        HwWifiP2pTransferManager.TransferBleToWifiCallback g = HwWifiP2pTransferManager.d().g();
        if (g != null) {
            LogUtil.c("WifiP2pTransferCommand", "callback is not null.");
            g.onResult(false);
            HwWifiP2pTransferManager.d().d((HwWifiP2pTransferManager.TransferBleToWifiCallback) null);
        } else {
            iyv.f();
            iyv.c("WifiP2PTransfer", "Devices replies 5.54.4 socket connect failed.");
            HwWifiP2pTransferManager.d().d(1013, "5.54.4 not connect success.");
        }
    }

    private static void a(int i, soz sozVar) {
        WifiP2pTransferListener k;
        iyv.j();
        if ((i & 1) == 1) {
            LogUtil.e("WifiP2pTransferCommand", "handleDeviceReportSocketConnectResult, send 54.5");
            HwWifiP2pTransferManager.TransferBleToWifiCallback g = HwWifiP2pTransferManager.d().g();
            if (g != null) {
                LogUtil.c("WifiP2pTransferCommand", "callback is not null.");
                g.onResult(true);
                HwWifiP2pTransferManager.d().d((HwWifiP2pTransferManager.TransferBleToWifiCallback) null);
                return;
            } else {
                LogUtil.c("WifiP2pTransferCommand", String.format(Locale.ROOT, "processSuccessResult, fileType is {%d}", Integer.valueOf(sozVar.o())));
                if (sozVar.u() == 2) {
                    e(sozVar);
                } else {
                    a(sozVar);
                }
            }
        }
        if ((i & 2) != 2 || (k = sozVar.k()) == null) {
            return;
        }
        k.onSuccess(1000, "send 5.9.9", -1);
    }

    private static byte[] f(String str) {
        if (str == null) {
            LogUtil.e("WifiP2pTransferCommand", "getSsidTlv, networkName is null");
            return new byte[0];
        }
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] h(String str) {
        if (str == null) {
            LogUtil.e("WifiP2pTransferCommand", "getPwdTlv, passPhrase is null");
            return new byte[0];
        }
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 2;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] e() {
        byte[] g = blq.g(0);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 5;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static void o(int i) {
        spa m = HwWifiP2pTransferManager.d().m();
        if (m == null) {
            LogUtil.e("WifiP2pTransferCommand", "notifySocketServerInfo, wifiP2pGoInfo is null");
            HwWifiP2pTransferManager.d().d(1010, "5.54.3 wifi go exception");
            return;
        }
        byte[] i2 = i(m.b());
        if (i2.length == 0) {
            LogUtil.e("WifiP2pTransferCommand", "notifySocketServerInfo, serverSocketIp is null");
            HwWifiP2pTransferManager.d().d(1011, "5.54.3 wifi ip wrong");
            return;
        }
        soz i3 = HwWifiP2pTransferManager.d().i();
        if (i3 != null) {
            LogUtil.c("WifiP2pTransferCommand", "set wait 4");
            i3.o(4);
        }
        LogUtil.c("WifiP2pTransferCommand", "send port : ", Integer.valueOf(i));
        byte[] g = g(i);
        ByteBuffer allocate = ByteBuffer.allocate(i2.length + g.length + 1);
        allocate.put((byte) 3).put(i2).put(g);
        blt.d("WifiP2pTransferCommand", allocate.array(), "5.54.3 content is:");
        spn e = e(allocate);
        if (e == null) {
            LogUtil.e("WifiP2pTransferCommand", "notifySocketServerInfo, sendMessage is null");
            HwWifiP2pTransferManager.d().d(1012, "5.54.3 wrap info exception");
        } else {
            HwWifiP2pTransferManager.d().y();
            HwWifiP2pTransferManager.d().a(e);
        }
    }

    private static byte[] i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("WifiP2pTransferCommand", "getServerSocketIpTlv, serverSocketIp is null");
            return new byte[0];
        }
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] g(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 2;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] c() {
        byte[] g = blq.g(spe.a());
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 6;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] a() {
        byte[] c = blq.c(spe.b(HwWifiP2pTransferManager.d().c()));
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 7;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] b() {
        byte[] g = blq.g(2);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 3;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] f(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 8;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    public static void d(soz sozVar) {
        if (sozVar == null) {
            LogUtil.a("WifiP2pTransferCommand", "commonFileInfo is null");
            return;
        }
        spn b = b(sozVar);
        if (b == null) {
            LogUtil.e("WifiP2pTransferCommand", "sendWifiInfoToDevice, sendMessage is null");
            HwWifiP2pTransferManager.d().d(1009, "wrap 5.54.9 info wrong, please check.");
        } else {
            HwWifiP2pTransferManager.d().y();
            HwWifiP2pTransferManager.d().a(b);
        }
    }

    private static spn b(soz sozVar) {
        LogUtil.c("WifiP2pTransferCommand", "getFileType:", Integer.valueOf(sozVar.o()), "getFileDescription:", sozVar.g(), "getSrcPackageName:", sozVar.w(), "getDesPackageName:", sozVar.a(), "getSrcCertificate:", sozVar.v(), "getDesCertificate:", sozVar.d());
        byte[] h = h(sozVar.o());
        byte[] l = l(sozVar.g());
        byte[] k = k(sozVar.w());
        byte[] m = m(sozVar.a());
        byte[] n = n(sozVar.v());
        byte[] o = o(sozVar.d());
        int length = h.length;
        int length2 = l.length;
        int length3 = k.length;
        int length4 = m.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 1 + length2 + length3 + length4 + n.length + o.length);
        allocate.put((byte) 9).put(h).put(l).put(k).put(m).put(n).put(o);
        blt.d("WifiP2pTransferCommand", allocate.array(), "5.54.9 send ");
        return e(allocate);
    }

    public static int c(soz sozVar, int i) {
        if (sozVar == null) {
            LogUtil.a("WifiP2pTransferCommand", "commonFileInfo is null");
            return 0;
        }
        LogUtil.c("WifiP2pTransferCommand", "findCommonFileInfoSupportStatus : ", Integer.valueOf(i));
        spn b = b(sozVar);
        if (b == null) {
            LogUtil.e("WifiP2pTransferCommand", "sendWifiInfoToDevice, sendMessage is null");
            return 0;
        }
        return j(HwWifiP2pTransferManager.d().e(b, i));
    }

    private static int j(byte[] bArr) {
        int i = 0;
        if (bArr != null && bArr.length != 0) {
            bmj e = spe.e(bArr);
            if (e == null) {
                return 0;
            }
            for (bmi bmiVar : e.b()) {
                if (bli.e(bmiVar.e()) == 2) {
                    i = bli.e(bmiVar.c());
                } else {
                    LogUtil.a("WifiP2pTransferCommand", "unknown tag : ", Integer.valueOf(bli.e(bmiVar.e())));
                }
            }
        }
        return i;
    }

    private static byte[] h(int i) {
        byte[] g = blq.g(i);
        byte[] d = blq.d(g.length);
        byte[] bArr = new byte[g.length + 1 + d.length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(g, 0, bArr, d.length + 1, g.length);
        return bArr;
    }

    private static byte[] l(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 3;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] k(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 4;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] m(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 5;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] n(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 6;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }

    private static byte[] o(String str) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[c.length + 1 + d.length];
        bArr[0] = 7;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        return bArr;
    }
}
