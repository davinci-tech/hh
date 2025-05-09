package defpackage;

import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class blk {
    private bmt d = new bmt();
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f431a = {40, 44};
    private static blk e = null;

    public static blk e() {
        blk blkVar;
        synchronized (b) {
            if (e == null) {
                e = new blk();
            }
            blkVar = e;
        }
        return blkVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a2, code lost:
    
        r9 = e(r9.getDeviceMac(), r10, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int c(com.huawei.devicesdk.entity.DeviceInfo r9, int r10, byte[] r11) {
        /*
            r8 = this;
            java.lang.String r0 = "getSocketChannelForDestPackageName serviceId: , "
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "DeviceDualChannelHelper"
            health.compact.a.LogUtil.c(r1, r0)
            r0 = 0
            if (r9 == 0) goto Ldc
            if (r11 != 0) goto L16
            goto Ldc
        L16:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Boolean> r2 = defpackage.bgz.i
            java.lang.String r3 = r9.getDeviceMac()
            java.lang.Object r2 = r2.get(r3)
            if (r2 != 0) goto L2c
            java.lang.String r9 = "getSocketChannelForDestPackageName SUPPORT_EXTEND_SOCKET is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.LogUtil.a(r1, r9)
            return r0
        L2c:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Boolean> r2 = defpackage.bgz.i
            java.lang.String r3 = r9.getDeviceMac()
            java.lang.Object r2 = r2.get(r3)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L4c
            java.lang.String r9 = "getSocketChannelForDestPackageName not support dual socket "
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r2)
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10}
            health.compact.a.LogUtil.a(r1, r9)
            return r0
        L4c:
            java.util.TreeSet r2 = new java.util.TreeSet     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            r2.<init>()     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            int r3 = r11.length     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            r4 = 2
            int r3 = r3 - r4
            byte[] r5 = new byte[r3]     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            java.lang.System.arraycopy(r11, r4, r5, r0, r3)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            r11 = 52
            r3 = 1
            r6 = 4
            if (r10 != r11) goto L67
            java.lang.Byte r11 = java.lang.Byte.valueOf(r6)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            r2.add(r11)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            goto L7c
        L67:
            r11 = 55
            if (r10 != r11) goto L73
            java.lang.Byte r11 = java.lang.Byte.valueOf(r4)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            r2.add(r11)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            goto L7c
        L73:
            java.lang.Object[] r11 = new java.lang.Object[r3]     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            java.lang.String r7 = "getSocketChannelForDestPackageName serviceId else"
            r11[r0] = r7     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            health.compact.a.LogUtil.a(r1, r11)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
        L7c:
            bmt r11 = r8.d     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            bmq r11 = r11.d(r5, r2)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            java.util.List r11 = r11.d()     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            java.util.Iterator r11 = r11.iterator()     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            java.lang.String r2 = ""
        L8c:
            boolean r5 = r11.hasNext()     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            if (r5 == 0) goto Lab
            java.lang.Object r2 = r11.next()     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            bmu r2 = (defpackage.bmu) r2     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            java.lang.String r2 = r8.a(r10, r2)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            if (r5 != 0) goto L8c
            java.lang.String r9 = r9.getDeviceMac()     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            int r9 = r8.e(r9, r10, r2)     // Catch: java.lang.NumberFormatException -> Lc7 defpackage.bmk -> Ld1
            goto Lac
        Lab:
            r9 = r0
        Lac:
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            java.lang.String r11 = "serviceId 52 or 55 destPackageName: "
            r10[r0] = r11     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            r10[r3] = r2     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            java.lang.String r11 = " socketChannel: "
            r10[r4] = r11     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            java.lang.Integer r11 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            r0 = 3
            r10[r0] = r11     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            health.compact.a.LogUtil.c(r1, r10)     // Catch: java.lang.NumberFormatException -> Lc3 defpackage.bmk -> Lc5
            goto Ldb
        Lc3:
            r0 = r9
            goto Lc7
        Lc5:
            r0 = r9
            goto Ld1
        Lc7:
            java.lang.String r9 = "getSocketChannelForDestPackageName NumberFormatException"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.LogUtil.e(r1, r9)
            goto Lda
        Ld1:
            java.lang.String r9 = "getSocketChannelForDestPackageName TlvException"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.LogUtil.e(r1, r9)
        Lda:
            r9 = r0
        Ldb:
            return r9
        Ldc:
            java.lang.String r9 = "getSocketChannelForDestPackageName params is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.LogUtil.a(r1, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.blk.c(com.huawei.devicesdk.entity.DeviceInfo, int, byte[]):int");
    }

    private String a(int i, bmu bmuVar) {
        try {
            if (i == 52) {
                if (bmuVar.a() == 4) {
                    return new String(bmuVar.c(), "UTF-8");
                }
                LogUtil.a("DeviceDualChannelHelper", "getDestPackageNameByTlv 52 deal else value");
            } else if (i == 55) {
                if (bmuVar.a() == 2) {
                    return new String(bmuVar.c(), "UTF-8");
                }
                LogUtil.a("DeviceDualChannelHelper", "getDestPackageNameByTlv 55 deal else value");
            } else {
                LogUtil.a("DeviceDualChannelHelper", "getDestPackageNameByTlv else serviceId");
            }
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("DeviceDualChannelHelper", "getDestPackageNameByTlv UnsupportedEncodingException");
        }
        return "";
    }

    public int e(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("DeviceDualChannelHelper", "getSocketChannelByPackageName param is invalid");
            return 0;
        }
        LogUtil.c("DeviceDualChannelHelper", "getSocketChannelByPackageName identify: ", blt.b(str), " serviceId: ", Integer.valueOf(i), " destPackageName: ", str2);
        List<bjd> list = bgz.c.get(str);
        if (list == null) {
            return 0;
        }
        for (bjd bjdVar : list) {
            if (bjdVar.e() == i && bjdVar.c() != null && bjdVar.c().contains(str2)) {
                return bgz.b.get(str).intValue();
            }
        }
        return 0;
    }

    public int b(DeviceInfo deviceInfo, bir birVar, int i, int i2) {
        for (int i3 : f431a) {
            if (i3 == i) {
                LogUtil.c("DeviceDualChannelHelper", "sendCommand serviceId not with channel");
                LogUtil.c("DeviceDualChannelHelper", "sendCommand not with channel");
                return birVar.i();
            }
        }
        return d(deviceInfo, i, i2);
    }

    private int d(DeviceInfo deviceInfo, int i, int i2) {
        if (bgz.d.get(deviceInfo.getDeviceMac()) == null) {
            LogUtil.a("DeviceDualChannelHelper", "dealElseCommand SUPPORT_DUAL_SOCKET is null");
            return 0;
        }
        boolean booleanValue = bgz.d.get(deviceInfo.getDeviceMac()).booleanValue();
        if (!booleanValue) {
            LogUtil.c("DeviceDualChannelHelper", "isNewSocketChannel not support dual socket ", Boolean.valueOf(booleanValue));
            return 0;
        }
        int intValue = bgz.b.get(deviceInfo.getDeviceMac()) != null ? bgz.b.get(deviceInfo.getDeviceMac()).intValue() : 0;
        if (d(deviceInfo.getDeviceMac(), i) || d(deviceInfo.getDeviceMac(), i, i2)) {
            return intValue;
        }
        return 0;
    }

    private boolean d(String str, int i, int i2) {
        boolean z = false;
        if (str == null) {
            LogUtil.a("DeviceDualChannelHelper", "isNewSocketExtendChannel input is invalid.");
            return false;
        }
        if (bgz.i.get(str) == null) {
            LogUtil.a("DeviceDualChannelHelper", "isNewSocketExtendChannel SUPPORT_EXTEND_SOCKET is null.");
            return false;
        }
        boolean booleanValue = bgz.i.get(str).booleanValue();
        if (!booleanValue) {
            LogUtil.c("DeviceDualChannelHelper", "isNewSocketExtendChannel not support dual socket ", Boolean.valueOf(booleanValue));
            return false;
        }
        List<bjd> list = bgz.c.get(str);
        if (list == null) {
            LogUtil.a("DeviceDualChannelHelper", "isNewSocketExtendChannel mcuCommands is null, identify: ", blt.b(str));
            return false;
        }
        Iterator<bjd> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            bjd next = it.next();
            if (next.e() == i && next.d() != null && next.d().contains(Integer.valueOf(i2))) {
                z = true;
                break;
            }
        }
        LogUtil.c("DeviceDualChannelHelper", "isNewSocketExtendChannel isRunMcuSocket: ", Boolean.valueOf(z));
        return z;
    }

    public boolean d(String str, int i) {
        if (str == null) {
            LogUtil.a("DeviceDualChannelHelper", "getChannelId input is invalid.");
            return false;
        }
        SparseBooleanArray sparseBooleanArray = bgz.f369a.get(str);
        if (sparseBooleanArray != null) {
            return sparseBooleanArray.get(i);
        }
        LogUtil.c("DeviceDualChannelHelper", "getChannelId dualServices is null.");
        return false;
    }

    public void d() {
        b();
    }

    private static void b() {
        synchronized (b) {
            e = null;
        }
    }
}
