package defpackage;

import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.LinkedList;

/* loaded from: classes7.dex */
public class sow {
    public static boolean e(sol solVar, WifiP2pTransferListener wifiP2pTransferListener, HashMap<Integer, LinkedList<sol>> hashMap, int i) {
        if (solVar == null || wifiP2pTransferListener == null || hashMap == null) {
            LogUtil.a("HwCommonFileWifiAdapter", "wifi queue param is null, please check.");
            return false;
        }
        LogUtil.c("HwCommonFileWifiAdapter", "wifiP2pTransferByQueue fileInfo is: ", Integer.valueOf(solVar.u()), "file name :", solVar.m());
        if (!HwWifiP2pTransferManager.d().b(solVar.u(), solVar.i())) {
            return false;
        }
        e(solVar);
        if (e(solVar, wifiP2pTransferListener, hashMap)) {
            return true;
        }
        Boolean b = b(solVar, wifiP2pTransferListener, i);
        if (b != null) {
            return b.booleanValue();
        }
        long v = solVar.v();
        LogUtil.c("HwCommonFileWifiAdapter", "totalSize:", Long.valueOf(v));
        if (v > PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE && e(solVar, wifiP2pTransferListener)) {
            LogUtil.c("HwCommonFileWifiAdapter", "wifiP2pTransferByQueue start p2p transfer");
            b();
            iyv.n();
            return true;
        }
        iyv.b();
        return false;
    }

    private static boolean e(sol solVar, WifiP2pTransferListener wifiP2pTransferListener, HashMap<Integer, LinkedList<sol>> hashMap) {
        soz i = HwWifiP2pTransferManager.d().i();
        if (i == null || a(i, solVar)) {
            return false;
        }
        LogUtil.c("HwCommonFileWifiAdapter", "wifi send file, wait queue.");
        HwWifiP2pTransferManager.d().c(solVar, wifiP2pTransferListener, 1);
        return true;
    }

    private static Boolean b(sol solVar, WifiP2pTransferListener wifiP2pTransferListener, int i) {
        if (!a(solVar, wifiP2pTransferListener)) {
            return null;
        }
        b();
        soz i2 = HwWifiP2pTransferManager.d().i();
        LogUtil.c("HwCommonFileWifiAdapter", "wifi is ok. send 5.54.5 or 5.54.9");
        if (i2 != null) {
            if (b(i, i2)) {
                return false;
            }
        } else {
            LogUtil.a("HwCommonFileWifiAdapter", "fileInfo is null. please check.");
        }
        iyv.n();
        return true;
    }

    private static boolean b(int i, soz sozVar) {
        if (i == sozVar.o() || sozVar.o() == 2 || !HwWifiP2pTransferManager.d().c(sozVar)) {
            LogUtil.c("HwCommonFileWifiAdapter", "wifi is ok. send 5.54.5 : ", Integer.valueOf(sozVar.o()));
            soy.a(sozVar);
            return false;
        }
        LogUtil.c("HwCommonFileWifiAdapter", "no search, send 5.54.9 : ", Integer.valueOf(sozVar.o()));
        if (soy.c(sozVar, 9) == 1) {
            if (sozVar.u() == 1) {
                soy.a(sozVar);
                return false;
            }
            if (sozVar.u() != 2) {
                return false;
            }
            soy.e(sozVar);
            return false;
        }
        HwWifiP2pTransferManager.d().d(sozVar.o());
        HwWifiP2pTransferManager.d().e();
        return true;
    }

    private static void e(sol solVar) {
        if (HwWifiP2pTransferManager.d().b(solVar.u())) {
            try {
                LogUtil.c("HwCommonFileWifiAdapter", "isHasFileInfoWithFileType is true");
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                LogUtil.e("HwCommonFileWifiAdapter", "InterruptedException is ", e.getMessage());
            }
        }
    }

    private static boolean a(soz sozVar, sol solVar) {
        return sozVar.o() == solVar.u() && sozVar.i().equals(solVar.m()) && ((long) solVar.v()) == sozVar.f();
    }

    private static boolean a(sol solVar, WifiP2pTransferListener wifiP2pTransferListener) {
        HwWifiP2pTransferManager d = HwWifiP2pTransferManager.d();
        if (!d.k() || !d.f()) {
            return false;
        }
        LogUtil.c("HwCommonFileWifiAdapter", "wifiP2pTransferChannelAvailable");
        d.c(solVar, wifiP2pTransferListener, 1);
        return true;
    }

    public static void b() {
        HwWifiP2pTransferManager.d().p();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v5 int, still in use, count: 2, list:
          (r3v5 int) from 0x002c: IF  (r3v5 int) >= (4 int)  -> B:10:0x0030 A[HIDDEN]
          (r3v5 int) from 0x0030: PHI (r3v3 int) = (r3v2 int), (r3v5 int) binds: [B:19:0x002f, B:8:0x002c] A[DONT_GENERATE, DONT_INLINE]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:114)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:62)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:45)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:67)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:35)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:34)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
        */
    private static boolean e(defpackage.sol r5, com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            int r1 = r5.u()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "file name : "
            java.lang.String r3 = r5.m()
            java.lang.String r4 = "startP2p fileInfo id is: "
            java.lang.Object[] r1 = new java.lang.Object[]{r4, r1, r2, r3}
            java.lang.String r2 = "HwCommonFileWifiAdapter"
            health.compact.a.LogUtil.c(r2, r1)
            com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager r1 = com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager.d()
            boolean r3 = r1.l()
            if (r3 != 0) goto L2f
            int r3 = r1.c(r5)
            r4 = 4
            if (r3 < r4) goto L68
            goto L30
        L2f:
            r3 = r0
        L30:
            r5.o(r3)
            r3 = 1
            r1.c(r5, r6, r3)
            java.lang.String r6 = "startP2p WifiP2P transfer"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.LogUtil.c(r2, r6)
            com.huawei.unitedevice.entity.UniteDevice r6 = r5.i()
            r4 = 0
            if (r6 == 0) goto L50
            com.huawei.unitedevice.entity.UniteDevice r6 = r5.i()
            java.lang.String r6 = r6.getIdentify()
            goto L51
        L50:
            r6 = r4
        L51:
            boolean r6 = r1.c(r6, r4)
            if (r6 == 0) goto L61
            java.lang.String r5 = "startP2p WifiP2P transfer getWifiP2pEnable"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            health.compact.a.LogUtil.c(r2, r5)
            return r3
        L61:
            int r5 = r5.u()
            r1.d(r5)
        L68:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sow.e(sol, com.huawei.unitedevice.hwwifip2ptransfermgr.WifiP2pTransferListener):boolean");
    }

    public static boolean e(CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) {
        if (iTransferFileCallback == null) {
            LogUtil.a("HwCommonFileWifiAdapter", "wifi stop file callback is null.");
            return false;
        }
        soz i = HwWifiP2pTransferManager.d().i();
        if (i != null && commonFileInfoParcel != null) {
            String fileName = commonFileInfoParcel.getFileName();
            int fileType = commonFileInfoParcel.getFileType();
            String i2 = i.i();
            LogUtil.c("HwCommonFileWifiAdapter", "file name : ", fileName, " fileType : ", Integer.valueOf(fileType), "nowName : ", i2, "nowType : ", Integer.valueOf(i.o()));
            if (b(fileType, i.o(), fileName, i2)) {
                LogUtil.c("HwCommonFileWifiAdapter", "remove file is using wifi, stop this.");
                b(i);
                HwWifiP2pTransferManager.d().w();
                return false;
            }
            LogUtil.a("HwCommonFileWifiAdapter", "file now equal, please check.");
            HwWifiP2pTransferManager.d().d(fileType);
            try {
                iTransferFileCallback.onResponse(20003, "");
                return true;
            } catch (RemoteException unused) {
                LogUtil.e("HwCommonFileWifiAdapter", "stopTransferByQueue RemoteException");
                return true;
            }
        }
        LogUtil.a("HwCommonFileWifiAdapter", "wifiP2pStopTransferFileByQueue file info is null.");
        try {
            iTransferFileCallback.onResponse(20003, "");
        } catch (RemoteException unused2) {
            LogUtil.e("HwCommonFileWifiAdapter", "stopTransferByQueue RemoteException");
        }
        HwWifiP2pTransferManager.d().e();
        return true;
    }

    private static void b(soz sozVar) {
        int ac = sozVar.ac();
        if (ac == 2 || ac == 4) {
            LogUtil.c("HwCommonFileWifiAdapter", "linking wifi p2p, stop task.");
            int o = sozVar.o();
            HwWifiP2pTransferManager.d().r();
            sozVar.k().onFail(20003, "link wifi user stop task", o);
        }
    }

    private static boolean b(int i, int i2, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return str.equals(str2) && i == i2;
        }
        LogUtil.a("HwCommonFileWifiAdapter", "file name is empty, please check.");
        return false;
    }
}
